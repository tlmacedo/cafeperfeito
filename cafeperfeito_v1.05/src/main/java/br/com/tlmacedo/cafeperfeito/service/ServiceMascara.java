package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.CriteriosValidationFields;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.EnderecoTipo;
import com.google.common.base.Splitter;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.image.Image;
import javafx.util.Callback;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.text.MaskFormatter;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.*;
import static br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema.MY_LOCALE;
import static br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema.TCONFIG;

/**
 * @author thiagomacedo
 * @
 */
@SuppressWarnings("Duplicates")
public class ServiceMascara {

    private static Pattern pattern;
    private static Matcher matcher;
    private static MaskFormatter formatter;
    private String mascara;
    StringBuilder resultado;

    public static String getNumeroMask(int len, int decimal) {
        if (len == 0) len = 12;
        String retorno = String.format("%0" + (len - 1) + "d", 0).replace("0", TCONFIG.getSis().getMaskCaracter().getDigit());
        retorno += "0";
        if (decimal > 0)
            retorno = String.format("%s.%0" + decimal + "d", retorno.substring(decimal), 0);
        return retorno;
    }

    public static String getNumeroMilMask(int len) {
        return getNumeroMask(len, 0) + ".";
    }

    public static String getMoeda(String value, int decimal) {
        return formataNumeroDecimal(value, decimal);
    }

    public static String getMoeda2(BigDecimal value, int decimal) {
        if (value.toString().contains(".") || value.toString().contains(","))
            return formataNumeroDecimal(value.setScale(decimal).toString(), decimal);
        else
            return formataNumeroDecimal(value.toString(), decimal);
    }

    private static String getValueFormatado(String value, String mask) {
        try {
            formatter = new MaskFormatter(mask);
            formatter.setValueContainsLiteralCharacters(false);
            String ret = formatter.valueToString(value);
            if (!ret.equals(""))
                while (!Character.isDigit(ret.charAt(ret.length() - 1)))
                    ret = ret.substring(0, ret.length() - 1);
            return ret;
        } catch (ParseException | StringIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
        }
        return "";
    }

    public static String getNcm(String value) {
        return getValueFormatado(value, MASK_NCM);
    }

    public static String getChaveNfeCte(String value) {
        return value.replaceAll(REGEX_NFE_CHAVE, REGEX_NFE_CHAVE_FS).trim();
    }

    public static String getCest(String value) {
        return getValueFormatado(value, MASK_CEST);
    }

    public static String getTelefone(String value) {
        String strValue = value.replaceAll("\\D", "").trim();
        if (strValue.length() > 11) strValue = strValue.substring(0, 11);
        pattern = Pattern.compile(REGEX_TELEFONE);
        matcher = pattern.matcher(strValue);
        if (matcher.find()) {
            return String.format("%s%s%s",
                    matcher.group(1) == null
                            ? ""
                            : String.format("(%s) ", matcher.group(1)),
                    matcher.group(2) == null
                            ? ""
                            : String.format("%s-", matcher.group(2)),
                    matcher.group(3) == null
                            ? ""
                            : String.format("%s", matcher.group(3))
            );
        }
        return strValue;
    }

    public static String getTextoMask(int len, String caractere) {
        if (len == 0) len = 120;
        if (caractere == null || caractere.equals(""))
            caractere = TCONFIG.getSis().getMaskCaracter().getUpper();
        return String.format("%0" + len + "d", 0).replace("0", caractere);
    }

    public static String getTexto(String value) {
        return getValueFormatado(value, getTextoMask(value.length(), null));
    }

    public static String getRgMask(int len) {
        if (len == 0) len = 11;
        return getNumeroMask(len, 0);
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public static String getCodigoBarraMask() {
        return String.format("%013d", 0).replace("0", TCONFIG.getSis().getMaskCaracter().getDigit());
    }

    public static String getCodigoBarra(String value) {
        return getValueFormatado(value, getCodigoBarraMask());
    }

    public static String getEmailHomeMask(int len) {
        if (len == 0) len = 120;
        return String.format("%0" + len + "d", 0).replace("0", TCONFIG.getSis().getMaskCaracter().getLower());
    }

    public static String getEmailHomePage(String value) {
        return getValueFormatado(value, getEmailHomeMask(value.length()));
    }

    public static String getCnpj(String value) {
        return getValueFormatado(value, MASK_CNPJ);
    }

    public static String getCpf(String value) {
        return getValueFormatado(value, MASK_CPF);
    }

    public static String getRg(String value) {
        return getValueFormatado(value, getRgMask(value.length()));
    }

    public static String getIe(String value, String uf) {
        return getValueFormatado(value, getMascaraIE(uf));
    }

//            case "rg":
//    len = 11;
//                return String.format(getFormato(len), 0).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "telefone":
//                    if (len == 0) len = 8;
//                return String.format(getFormato(len), 0).replaceAll(REGEX_FS_TELEFONE.getKey(), REGEX_FS_TELEFONE.getValue()).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "celular":
//                    if (len == 0) len = 9;
//                return String.format(getFormato(len), 0).replaceAll(REGEX_FS_CELULAR.getKey(), REGEX_FS_CELULAR.getValue()).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "ean":
//                    case "barcode":
//                    case "barras":
//                    case "codbarra":
//                    case "codbarras":
//                    case "codigobarra":
//                    case "codigobarras":
//    len = 13;
//                return String.format(getFormato(len), 0).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "numero":
//                    if (len == 0) len = 9;
//                return String.format(getFormato(len), 0).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "moeda":
//                    case "valor":
//                    case "peso":
//                    if (len == 0) len = 11;
//    len -= (decimal + 1);
//    String maskMoeda = String.format(getFormato(len), 0) + ".";
//    maskMoeda = maskMoeda.replaceAll("(\\d{2}\\.)", ",$1");
//    int qtdLoop = (maskMoeda.length() - 3) / 3;
//    int count = 0;
//                while (qtdLoop > count) {
//        count++;
//        maskMoeda = maskMoeda.replaceAll("(\\d+)(\\d{3}\\,)", "$1,$2"); //Coloca o resto dos pontos
//    }
//    maskMoeda = maskMoeda.substring(0, maskMoeda.length() - 1).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//    maskMoeda += "0" + (decimal > 0 ? "." + String.format("%0" + decimal + "d", 0) : "");
//                return String.format("%s;-%s", maskMoeda, maskMoeda);
//            case "cep":
//    len = 8;
//                return String.format(getFormato(len), 0).replaceAll(REGEX_FS_CEP.getKey(), REGEX_FS_CEP.getValue()).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "ncm":
//    len = 8;
//                return String.format(getFormato(len), 0).replaceAll(REGEX_FS_NCM.getKey(), REGEX_FS_NCM.getValue()).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "nfecest":
//    len = 7;
//                return String.format(getFormato(len), 0).replaceAll(REGEX_FS_CEST.getKey(), REGEX_FS_CEST.getValue()).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "nfechave":
//    len = 44;
//                return String.format(getFormato(len), 0).replaceAll(REGEX_FS_NFE_CHAVE.getKey(), REGEX_FS_NFE_CHAVE.getValue()).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "nfenumero":
//    len = 9;
//                return String.format(getFormato(len), 0).replaceAll(REGEX_FS_NFE_NUMERO.getKey(), REGEX_FS_NFE_NUMERO.getValue()).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//            case "nfedocorigem":
//    len = 12;
//                return String.format(getFormato(len), 0).replaceAll(REGEX_FS_NFE_DOC_ORIGEM.getKey(), REGEX_FS_NFE_DOC_ORIGEM.getValue()).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getDigit());
//}
//        len = 60;
//                return String.format(getFormato(len), 0).replace("0", ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getAsterisco());
//
//

//    public static Double getDoubleFromTextField(String value, int decimal) {
//        value = Long.valueOf(value.replaceAll("\\D", "")).toString();
//        String sinal = "";
//        int addZeros = ((decimal + 1) - value.length());
//        if (addZeros > 0)
//            value = String.format("%0" + addZeros + "d", 0) + value;
//        if (decimal > 0)
//            value = value.replaceAll("(\\d{1})(\\d{" + decimal + "})$", "$1.,$2");
//        if (value.substring(0, 1).equals("-")) sinal = "-";
//        return Double.parseDouble(sinal + value);
//    }

    public static void fatorarColunaCheckBox(TreeTableColumn colunaGenerica) {
        colunaGenerica.getStyleClass().add("chkbox");
        colunaGenerica.setCellFactory(new Callback<TreeTableColumn, TreeTableCell>() {
            @Override
            public TreeTableCell call(TreeTableColumn param) {
                CheckBoxTreeTableCell cell = new CheckBoxTreeTableCell();
                cell.setAlignment(Pos.TOP_CENTER);
                return cell;
            }
        });
    }

    public static Pair<String, String> getFieldFormatPair(String accessibleText, String keyFormat) {
        if (accessibleText.equals("")) return null;
        HashMap<String, String> hashMap = getFieldFormatMap(accessibleText);
        return new Pair<String, String>(keyFormat, hashMap.get(keyFormat));
    }

    public static HashMap<String, String> getFieldFormatMap(String accessibleText) {
        if (accessibleText.equals("")) return null;
        return new HashMap<String, String>(Splitter.on(";").omitEmptyStrings().withKeyValueSeparator(Splitter.onPattern("\\:\\:")).split(accessibleText));
    }

    public static HashMap<CriteriosValidationFields, String> getHasMapCriteriosValidationFields(String accessibleText) {
        if (accessibleText.equals("")) return null;
        HashMap<CriteriosValidationFields, String> hashMap = new HashMap<>();
        CriteriosValidationFields key;
        String value;
        for (String split1 : accessibleText.split(";")) {
            key = null;
            value = null;
            for (String part : split1.split("\\:\\:")) {
                if (key == null) {
                    for (CriteriosValidationFields tmp : CriteriosValidationFields.getList())
                        if (tmp.getDescricao().equals(part)) {
                            key = tmp;
                            break;
                        }
                } else {
                    value = part;
                }
            }
            hashMap.put(key, value);
        }
        return hashMap;
    }

    public static String getDataExtenso(String municipio, LocalDate localDate) {
        if (municipio == null)
            if (LogadoInf.getLojaUser().getEnderecoList() != null)
                municipio = LogadoInf.getLojaUser().getEnderecoList().stream().filter(endereco -> endereco.getTipo().equals(EnderecoTipo.PRINCIPAL)).findFirst().orElse(null).getMunicipio().getDescricao();
            else
                municipio = TCONFIG.getInfLoja().getMunicipio();
        return String.format("%s,   %02d   de   %s    de    %04d",
                municipio,
                localDate.getDayOfMonth(),
                localDate.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, MY_LOCALE),
                localDate.getYear()
        );
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

    public static BigDecimal getBigDecimalFromTextField(String value, int decimal) {
        if (value.equals("") || value == null) return BigDecimal.ZERO.setScale(decimal);
        BigDecimal result = new BigDecimal(formataNumeroDecimal(value, decimal).replace(".", "")
                .replace(",", ".")).setScale(decimal, RoundingMode.HALF_UP);
        return result.toString() != "0.00" ? result : BigDecimal.ZERO;
    }

    public static Double getDoubleFromTextField(String value, int decimal) {
        return getBigDecimalFromTextField(value, decimal).doubleValue();
    }

    public byte[] getImgToByte(Image image) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage) image, "png", output);
            return output.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public void fieldMask(JFXTextField textField, String tipMascara) {
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
                } else {
                    if (getMascara().equals("#############") || getMascara().equals("##############"))
                        if (value.length() >= 13 && Integer.valueOf(value.substring(0, 1)) <= 6)
                            setMascara("##############");
                        else
                            setMascara("#############");
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
            try {
                textField.setText(resultado.toString());
            } catch (Exception ex) {
                if (!(ex instanceof RuntimeException))
                    ex.printStackTrace();
            }
//            Platform.runLater(() -> {
            if (getMascara().contains(".0"))
                textField.positionCaret(resultado.length());
            else
                textField.positionCaret(finalPosicao);
        });
//        });
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


}
