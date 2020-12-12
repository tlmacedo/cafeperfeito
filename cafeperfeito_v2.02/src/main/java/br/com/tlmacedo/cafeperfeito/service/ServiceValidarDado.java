package br.com.tlmacedo.cafeperfeito.service;


import br.com.tlmacedo.cafeperfeito.model.dao.RecebimentoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.model.vo.UsuarioLogado;
import br.com.tlmacedo.cafeperfeito.service.alert.Alert_Ok;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.REGEX_EMAIL;
import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.REGEX_TELEFONE;

public class ServiceValidarDado {
    private static final int[] pesoCpf = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCnpj = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCafe = {3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static Pattern p, pt, pd;
    private static Matcher m, mt, md;

    public static boolean isCnpjCpfValido(String value) {
        value = value.replaceAll("\\W", "");

        if ((value == null) || (value.length() != 11 && value.length() != 14)
                || (value.matches(value.charAt(0) + "{11}") && value.matches(value.charAt(0) + "{14}")))
            return false;
        String base = value.substring(0, value.length() - 2);
        String dv = value.substring(value.length() - 2);
        int[] peso;
        if (value.length() == 14)
            peso = pesoCnpj;
        else peso = pesoCpf;

        return dv.equals(calculaDv(base, peso));
    }

    static String calculaDv(final String base, final int[] peso) {
        Integer[] digitoDV = {0, 0};
        String valor = base;
        for (int i = 0; i < 2; i++) {
            int soma = 0;
            if (i == 1)
                valor += digitoDV[0];
            for (int indice = valor.length() - 1, digito; indice >= 0; indice--) {
                digito = Integer.parseInt(valor.substring(indice, indice + 1));
                soma += digito * peso[peso.length - valor.length() + indice];
            }
            soma = 11 - soma % 11;
            digitoDV[i] = soma > 9 ? 0 : soma;
        }
        return digitoDV[0].toString() + digitoDV[1].toString();
    }


    public static String gerarCodigoCafePerfeito(Class classe, LocalDate dtDocumento) {
        String value = "";
        if (classe.equals(Recebimento.class)) {
            if (dtDocumento == null)
                dtDocumento = LocalDate.now();
            value = String.format("%02d%02d%02d%03d",
                    dtDocumento.getYear(),
                    dtDocumento.getMonthValue(),
                    dtDocumento.getDayOfMonth(),
                    new RecebimentoDAO().getAll(classe, String.format("dtCadastro BETWEEN '%s' AND '%s'",
                            dtDocumento.atTime(0, 0, 0),
                            dtDocumento.atTime(23, 59, 59)), "dtCadastro DESC").stream()
                            .filter(o -> {
                                if (((Recebimento) o).documentoProperty().getValue().length() > 0)
                                    return Character.isDigit(((Recebimento) o).documentoProperty().getValue().charAt(0));
                                return false;
                            })
                            .count() + 1
            );
            return gerarCodigoCafePerfeito(value.substring(2));
        }
        return "não gerado";
    }

    public static String gerarCodigoCafePerfeito(String value) {
        //value = value.replaceAll("\\D", "");
        value = String.format("%09d", Long.valueOf(value.replaceAll("\\D", "")));
        return String.format("%s-%s", value, calculaDv(value, pesoCafe));
    }

    public static boolean isCodigoCafePerfeito(String value) {
        value = value.replaceAll("\\D", "");

        if (value == null || value.length() != 11
                || value.matches(value.charAt(0) + "{11}"))
            return false;
        String base = value.substring(0, value.length() - 2);
        String dv = value.substring(value.length() - 2);
        return dv.equals(calculaDv(base, pesoCafe));
    }


//    public static WebTipo isEmailHomePageValido(final String value, boolean getMsgFaill) {
//        WebTipo webTipo = null;
//        if (value.contains("@")) {
//            try {
//                webTipo = WebTipo.EMAIL;
//                p = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE);
//                m = p.matcher(value);
//                if (!m.find()) {
//                    webTipo = WebTipo.HOMEPAGE;
//                    p = Pattern.compile(REGEX_HOME_PAGE, Pattern.CASE_INSENSITIVE);
//                    m = p.matcher(value);
//                }
//            } catch (Exception ex) {
//                webTipo = WebTipo.HOMEPAGE;
//                p = Pattern.compile(REGEX_HOME_PAGE, Pattern.CASE_INSENSITIVE);
//                m = p.matcher(value);
//            }
//        } else {
//            webTipo = WebTipo.HOMEPAGE;
//            p = Pattern.compile(REGEX_HOME_PAGE, Pattern.CASE_INSENSITIVE);
//            m = p.matcher(value);
//        }
//        if (m.find())
//            return webTipo;
//        if (getMsgFaill) {
//            ServiceAlertMensagem alertMensagem = new ServiceAlertMensagem();
//            alertMensagem.setCabecalho("Dados inválidos");
//            alertMensagem.setStrIco("ic_msg_alerta_triangulo_white_24dp.png");
//            alertMensagem.setPromptText(String.format("%s, o email/home page informado: [%s], é inválido!",
//                    StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
//                    value));
//            alertMensagem.getRetornoAlert_OK();
//        }
//        return null;
//    }

    public static List<String> getEmailsList(final String value) {
        p = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        List<String> mail = new ArrayList<>();
        while (m.find())
            mail.add(m.group());
        return mail;
    }

    public static boolean isEan13Valido(final String value) {
        final String codBarras = String.format("%018d", Long.valueOf(value));
        int[] numeros = codBarras.chars().map(Character::getNumericValue).toArray();
        int resultado = 0;
        for (int i = 0; i < numeros.length - 1; i++)
            if (i % 2 == 0)
                resultado += (numeros[i] * 3);
            else
                resultado += numeros[i];
        int digitoVerificador = 10 - (resultado % 10);
        if (digitoVerificador > 9)
            digitoVerificador = 0;
        return digitoVerificador == numeros[numeros.length - 1];
    }

    static int charToInt(char c) {
        return Integer.parseInt(String.valueOf(c));
    }

    public static boolean isTelefoneValido(final String value, boolean getMsgFaill) {
        p = Pattern.compile(REGEX_TELEFONE);
        m = p.matcher(value.replaceAll("\\D", ""));
        if (m.find())
            return true;
        if (getMsgFaill)
            new Alert_Ok("Dados inválidos",
                    String.format("%s, telefone informado: [%s], é inválido!",
                            StringUtils.capitalize(UsuarioLogado.getUsuario().getApelido()),
                            value),
                    "ic_msg_alerta_triangulo_white_24dp.png");
        return false;

    }

    public static List<String> getTelefoneList(final String value) {
        p = Pattern.compile(REGEX_TELEFONE);
        List<String> telefoneList = new ArrayList<>();
        if (!value.equals(""))
            for (String tel : value.split(" / ")) {
                tel = tel.replaceAll("\\D", "");
                if (tel.substring(0, 1).equals("0"))
                    tel = tel.substring(1);
                if (tel.length() == 10 && Integer.valueOf(tel.substring(2, 3)) > 5)
                    tel = String.format("%s9%s",
                            tel.substring(0, 2),
                            tel.substring(2)
                    );
                m = p.matcher(tel);
                while (m.find())
                    telefoneList.add(m.group());
            }
        return telefoneList;
    }


}
