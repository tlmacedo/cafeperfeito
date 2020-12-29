package br.com.tlmacedo.cafeperfeito.model.vo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LogadoInf implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Usuario USER_LOG;
    private static Empresa LojaUser;
    private static LocalDateTime USER_LOG_DATE_TIME = LocalDateTime.now();

    public LogadoInf() {
    }

    public static Usuario getUserLog() {
        return USER_LOG;
    }

    public static void setUserLog(Usuario userLog) {
        USER_LOG = userLog;
    }

    public static Empresa getLojaUser() {
        return LojaUser;
    }

    public static void setLojaUser(Empresa lojaUser) {
        LojaUser = lojaUser;
    }

    public static LocalDateTime getUserLogDateTime() {
        return USER_LOG_DATE_TIME;
    }

    @Override
    public String toString() {
        return StringUtils.capitalize(getUserLog().getApelido());
    }
}


//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        TabColaboradorVO colaboradorVO = new TabColaboradorDAO().getTabColaboradorVO(1);
//        ServiceVariavelSistema.USUARIO_LOGADO_ID = String.valueOf(colaboradorVO.getId());
//        ServiceVariavelSistema.USUARIO_LOGADO_NOME = colaboradorVO.getNome();
//        ServiceVariavelSistema.USUARIO_LOGADO_APELIDO = colaboradorVO.getApelido();
//        ServiceVariavelSistema.DATA_HORA = LocalDateTime.now();
//        ServiceVariavelSistema.DATA_HORA_STR = ServiceVariavelSistema.DATA_HORA.format(DTF_DATAHORA);
//        ServiceVariavelSistema.USUARIO_LOGADO_DATA = LocalDate.now();
//        ServiceVariavelSistema.USUARIO_LOGADO_DATA_STR = ServiceVariavelSistema.USUARIO_LOGADO_DATA.format(DTF_DATA);
//        ServiceVariavelSistema.USUARIO_LOGADO_HORA = LocalTime.now();
//        ServiceVariavelSistema.USUARIO_LOGADO_HORA_STR = ServiceVariavelSistema.USUARIO_LOGADO_HORA.format(DTF_HORA);
//
//        openViewPrincipal();
//    }
