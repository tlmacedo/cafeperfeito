package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import br.com.tlmacedo.cafeperfeito.model.enums.EnumEnderecoTipo;
import br.com.tlmacedo.cafeperfeito.model.vo.Endereco;
import com.google.common.base.Splitter;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.*;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.MY_LOCALE;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class ServiceMascara {

    private static Pattern pattern;
    private static Matcher matcher;
    private static MaskFormatter formatter;
    private String mascara;
    private StringBuilder resultado;

    public static String getTextoMask(int len, String caractere) {
        if (len == 0) len = 120;
        if (caractere == null || caractere.equals(""))
            caractere = TCONFIG.getSis().getMaskCaracter().getUpper();
        return String.format("%0" + len + "d", 0).replace("0", caractere);
    }

    private static String getValueFormatado(String value, String mask) {
        try {
            setFormatter(new MaskFormatter(mask));
            getFormatter().setValueContainsLiteralCharacters(false);
            String ret = getFormatter().valueToString(value);
            if (!ret.equals(""))
                while (!Character.isDigit(ret.charAt(ret.length() - 1)))
                    ret = ret.substring(0, ret.length() - 1);
            return ret;
        } catch (ParseException | StringIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
        }
        return "";
    }

    public static String getNumeroMask(int len, int decimal) {
        if (len == 0) len = 12;
        String retorno = String.format("%0" + (len - 1) + "d", 0).replace("0", TCONFIG.getSis().getMaskCaracter().getDigit());
        retorno += "0";
        if (decimal > 0)
            retorno = String.format("%s.%0" + decimal + "d", retorno.substring(decimal), 0);
        return retorno;
    }

    public static String getRgMask(int len) {
        if (len == 0) len = 11;
        return getNumeroMask(len, 0);
    }

    public static String getCnpj(String value) {
        return getValueFormatado(value, MASK_CNPJ);
    }

    public static String getCpf(String value) {
        return getValueFormatado(value, MASK_CPF);
    }

    public static String getIe(String value, String uf) {
        return getValueFormatado(value, getMascaraIE(uf));
    }

    public static String getNumeroMilMask(int len) {
        return getNumeroMask(len, 0) + ".";
    }

    public static HashMap<String, String> getFieldFormatMap(String accessibleText) {
        if (accessibleText.equals("") || accessibleText == null)
            return null;
        return new HashMap<String, String>(Splitter.on(";").omitEmptyStrings().withKeyValueSeparator(Splitter.onPattern("\\:\\:")).split(accessibleText));
    }

    private static String formataNumeroDecimal(String value, int decimal) {
        String sinal = "";
        if (value.substring(0, 1).equals("-"))
            sinal = "-";
        value = Long.valueOf(value.replaceAll("\\D", "")).toString();
        int addZeros = ((decimal + 1) - value.length());
        if (addZeros > 0)
            value = String.format("%0" + addZeros + "d", 0) + value;

        value = value.replaceAll("(\\d{1})(\\d{" + (decimal + 18) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (decimal + 15) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (decimal + 12) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (decimal + 9) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (decimal + 6) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (decimal + 3) + "})$", "$1.$2");
        if (decimal > 0)
            value = value.replaceAll("(\\d{1})(\\d{" + decimal + "})$", "$1,$2");
        return sinal + value;
    }

    public static String getMoeda(String value, int decimal) {
        return formataNumeroDecimal(value, decimal);
    }

    public static String getMoeda(BigDecimal value, int decimal) {
        try {
            if (value.toString().contains(".") || value.toString().contains(","))
                return formataNumeroDecimal(value.setScale(decimal, RoundingMode.HALF_UP).toString(), decimal);
            else
                return formataNumeroDecimal(value.toString(), decimal);
        } catch (Exception ex) {
            if (ex instanceof NullPointerException)
                return formataNumeroDecimal(BigDecimal.ZERO.setScale(decimal).toString(), decimal);
            return null;
        }
    }

    public static String getMoeda2(BigDecimal value, int decimal) {
        if (value.toString().contains(".") || value.toString().contains(","))
            return formataNumeroDecimal(value.setScale(decimal).toString(), decimal);
        else
            return formataNumeroDecimal(value.toString(), decimal);
    }

    public static String getDataExtenso(String municipio, LocalDate localDate) {
        if (municipio == null) {
            Endereco endereco;
            if ((endereco = ControllerPrincipal.getUsuarioLogado().getLojaLocado().getEndereco(EnumEnderecoTipo.PRINCIPAL)) != null)
                municipio = endereco.getMunicipio().getDescricao();
            else
                municipio = TCONFIG.getInfLoja().getMunicipio();
        }
        return String.format("%s,   %02d   de   %s    de    %04d",
                municipio,
                localDate.getDayOfMonth(),
                localDate.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, MY_LOCALE),
                localDate.getYear()
        );
    }


    public static BigDecimal getBigDecimalFromTextField(String value, int decimal) {
        if (value.equals("") || value == null) return BigDecimal.ZERO.setScale(decimal);
        BigDecimal ini = new BigDecimal(value.replace(".", "").replace(",", ".")).setScale(decimal, RoundingMode.HALF_UP);
        BigDecimal result = new BigDecimal(formataNumeroDecimal(ini.toString(), decimal).replace(".", "")
                .replace(",", ".")).setScale(decimal, RoundingMode.HALF_UP);
        return result.toString() != "0.00" ? result : BigDecimal.ZERO;
    }

    public static String getTelefone(String value) {
        String strValue = value.replaceAll("\\D", "").trim();
        if (strValue.length() > 11) strValue = strValue.substring(0, 11);
        setPattern(Pattern.compile(REGEX_TELEFONE_BD));
        setMatcher(getPattern().matcher(strValue));
        if (getMatcher().find()) {
            return String.format("%s%s%s",
                    getMatcher().group(1) == null
                            ? ""
                            : String.format("(%s) ", getMatcher().group(1)),
                    getMatcher().group(2) == null
                            ? ""
                            : String.format("%s-", getMatcher().group(2)),
                    getMatcher().group(3) == null
                            ? ""
                            : String.format("%s", getMatcher().group(3))
            );
        }
        return strValue;
    }

    public static String getReciboQuebraLinha(String value, int qtd) {
        return StringUtils.rightPad(value + " ", qtd, "*");
    }

    public void fieldMask(TextField textField, String tipMascara) {
        setMascara(tipMascara);
        textField.textProperty().addListener((ov, o, n) -> {
            StringBuilder resultado = new StringBuilder("");
            int posicao = 0;
            if (n != null && !n.equals("")) {
                try {
                    posicao = textField.getCaretPosition() + ((n.length() > o.length()) ? 1 : 0);
                } catch (Exception ex) {
                    posicao = 0;
                }
                String strValue = n != null ? n : "",
                        value = n != null ? n : "",
                        maskDigit = "";
                if (getMascara().contains("#0.")) {
                    if (strValue.equals(""))
                        strValue = "0";
                    int qtdMax = getMascara().replaceAll(".-/]", "").length();
                    int qtdDecimal = (getMascara().replaceAll("\\D", "").length() - 1);
                    if (strValue.length() > qtdMax)
                        strValue = strValue.substring(0, qtdMax);
                    resultado.append(formataNumeroDecimal(strValue, qtdDecimal));
                } else if (getMascara().contains("(##) ")) {
                    if (value.length() > 2) {
                        resultado.append(getTelefone(value));
                    } else {
                        resultado.append(value);
                    }
                } else if (getMascara().equals("##############") || getMascara().equals("##############")) {
                    if (value.length() >= 13 && Integer.valueOf(value.substring(1, 2)) <= 6)
                        setMascara("##############");
                    else
                        setMascara("#############");
                    resultado.append(value);
                } else {
                    if (strValue.length() > 0) {
                        int digitado = 0;
                        Pattern p = Pattern.compile(REGEX_PONTUACAO);
                        Matcher m = p.matcher(getMascara());
                        if (m.find())
                            value = strValue.replaceAll("\\W", "");
                        for (int i = 0; i < getMascara().length(); i++) {
                            if (digitado < value.length()) {
                                switch ((maskDigit = getMascara().substring(i, i + 1))) {
                                    case "#":
                                    case "0":
                                        if (Character.isDigit(value.charAt(digitado))) {
                                            resultado.append(value.substring(digitado, digitado + 1));
                                            digitado++;
                                        }
                                        break;
                                    case "U":
                                    case "A":
                                    case "L":
                                        if ((Character.isLetterOrDigit(value.charAt(digitado))
                                                || Character.isSpaceChar(value.charAt(digitado))
                                                || Character.isDefined(value.charAt(digitado)))) {
                                            if (maskDigit.equals("L"))
                                                resultado.append(value.substring(digitado, digitado + 1).toLowerCase());
                                            else
                                                resultado.append(value.substring(digitado, digitado + 1).toUpperCase());
                                            digitado++;
                                        }
                                        break;
                                    case "?":
                                    case "*":
                                        resultado.append(value.substring(digitado, digitado + 1));
                                        digitado++;
                                        break;
                                    default:
                                        resultado.append(getMascara().substring(i, i + 1));
                                        break;
                                }
                            }
                        }
                    }
                }
            }
            int finalPosicao = posicao;
//            Platform.runLater(() -> {
            textField.setText(resultado.toString());
//            Platform.runLater(() -> {
            if (getMascara().contains(".0"))
                textField.positionCaret(resultado.length());
            else
                textField.positionCaret(finalPosicao);
        });
//        });
    }

    public void fieldMaskJFX(JFXTextField jfxTextField, String tipMascara) {
        setMascara(tipMascara);
        jfxTextField.textProperty().addListener((ov, o, n) -> {
            StringBuilder resultado = new StringBuilder("");
            int posicao = 0;
            if (n != null && !n.equals("")) {
                try {
                    posicao = jfxTextField.getCaretPosition() + ((n.length() > o.length()) ? 1 : 0);
                } catch (Exception ex) {
                    posicao = 0;
                }
                String strValue = n != null ? n : "",
                        value = n != null ? n : "",
                        maskDigit = "";
                if (getMascara().contains("#0.")) {
                    if (strValue.equals(""))
                        strValue = "0";
                    int qtdMax = getMascara().replaceAll(".-/]", "").length();
                    int qtdDecimal = (getMascara().replaceAll("\\D", "").length() - 1);
                    if (strValue.length() > qtdMax)
                        strValue = strValue.substring(0, qtdMax);
                    resultado.append(formataNumeroDecimal(strValue, qtdDecimal));
                } else if (getMascara().contains("(##) ")) {
                    if (value.length() > 2) {
                        resultado.append(getTelefone(value));
                    } else {
                        resultado.append(value);
                    }
                } else if (getMascara().equals("##############") || getMascara().equals("##############")) {
                    if (value.length() >= 13 && Integer.valueOf(value.substring(1, 2)) <= 6)
                        setMascara("##############");
                    else
                        setMascara("#############");
                    resultado.append(value);
                } else {
                    if (strValue.length() > 0) {
                        int digitado = 0;
                        Pattern p = Pattern.compile(REGEX_PONTUACAO);
                        Matcher m = p.matcher(getMascara());
                        if (m.find())
                            value = strValue.replaceAll("\\W", "");
                        for (int i = 0; i < getMascara().length(); i++) {
                            if (digitado < value.length()) {
                                switch ((maskDigit = getMascara().substring(i, i + 1))) {
                                    case "#":
                                    case "0":
                                        if (Character.isDigit(value.charAt(digitado))) {
                                            resultado.append(value.substring(digitado, digitado + 1));
                                            digitado++;
                                        }
                                        break;
                                    case "U":
                                    case "A":
                                    case "L":
                                        if ((Character.isLetterOrDigit(value.charAt(digitado))
                                                || Character.isSpaceChar(value.charAt(digitado))
                                                || Character.isDefined(value.charAt(digitado)))) {
                                            if (maskDigit.equals("L"))
                                                resultado.append(value.substring(digitado, digitado + 1).toLowerCase());
                                            else
                                                resultado.append(value.substring(digitado, digitado + 1).toUpperCase());
                                            digitado++;
                                        }
                                        break;
                                    case "?":
                                    case "*":
                                        resultado.append(value.substring(digitado, digitado + 1));
                                        digitado++;
                                        break;
                                    default:
                                        resultado.append(getMascara().substring(i, i + 1));
                                        break;
                                }
                            }
                        }
                    }
                }
            }
            int finalPosicao = posicao;
//            Platform.runLater(() -> {
            jfxTextField.setText(resultado.toString());
//            Platform.runLater(() -> {
            if (getMascara().contains(".0"))
                jfxTextField.positionCaret(resultado.length());
            else
                jfxTextField.positionCaret(finalPosicao);
        });
//        });
    }

    public static String getMascaraIE(String uf) {
//        String caracter = ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit();
        switch (uf) {
            case "AC":
                return "##.###.###/###-##";
            case "AL":
                return "#########";
            case "AM":
                return "##.###.###-#";
            case "AP":
                return "#########";
            case "BA":
                return "###.###.##-#";
            case "CE":
                return "########-#";
            case "DF":
                return "###########-##";
            case "ES":
                return "###.###.##-#";
            case "GO":
                return "##.###.###-#";
            case "MA":
                return "#########";
            case "MG":
                return "###.###.###/####";
            case "MS":
                return "#########";
            case "MT":
                return "#########";
            case "PA":
                return "##-######-#";
            case "PB":
                return "########-#";
            case "PE":
                return "##.#.###.#######-#";
            case "PI":
                return "#########";
            case "PR":
                return "########-##";
            case "RJ":
                return "##.###.##-#";
            case "RN":
                return "##.###.###-#";
            case "RO":
                return "###.#####-#";
            case "RR":
                return "########-#";
            case "RS":
                return "###-#######";
            case "SC":
                return "###.###.###";
            case "SE":
                return "#########-#";
            case "SP":
                return "###.###.###.###";
            case "TO":
                return "###########";
            default:
                return "##############";
        }
    }

    /**
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     */

    public static Pattern getPattern() {
        return pattern;
    }

    public static void setPattern(Pattern pattern) {
        ServiceMascara.pattern = pattern;
    }

    public static Matcher getMatcher() {
        return matcher;
    }

    public static void setMatcher(Matcher matcher) {
        ServiceMascara.matcher = matcher;
    }

    public static MaskFormatter getFormatter() {
        return formatter;
    }

    public static void setFormatter(MaskFormatter formatter) {
        ServiceMascara.formatter = formatter;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public StringBuilder getResultado() {
        return resultado;
    }

    public void setResultado(StringBuilder resultado) {
        this.resultado = resultado;
    }
}
