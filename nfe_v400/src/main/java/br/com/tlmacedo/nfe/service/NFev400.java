package br.com.tlmacedo.nfe.service;

import br.com.tlmacedo.nfe.model.enums.TASK_NFE;
import br.com.tlmacedo.nfe.model.vo.EnviNfeVO;
import br.com.tlmacedo.nfe.v400.EnviNfe_v400;
import javafx.concurrent.Task;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NFev400 {

    private static boolean AMB_PRODUCAO;
    private static ZoneId ZONE_ID;
    private static DateTimeFormatter DTF_NFE_TO_LOCAL_DATE;
    //= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", new Locale("pt", "br"));

    private static ServiceLoadCertificates CERTIFICATES;
    private static boolean PRINT_PROMPT;
    private static String XML_CONS_RECIBO;
    private static String DIG_VAL;

    private String xml;
    private String xmlAssinado;
    private String xmlAutorizado;
    private String xmlRetAutorizacao;
    private String xmlProcNfe;

    private EnviNfeVO enviNfeVO;

    private List<TASK_NFE> taskList = new ArrayList<>();

    public NFev400(boolean ambProducao, boolean printPrompt) {
        setDtfNfeToLocalDate(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", new Locale(System.getProperty("nfeLang", "pt"),
                System.getProperty("nfeCountry", "br"))));
        setZoneId(ZoneId.of(System.getProperty("nfeZoneId", "America/Manaus")));
        setAmbProducao(ambProducao);
        setPrintPrompt(printPrompt);

        if (getCERTIFICATES() == null)
            setCERTIFICATES(new ServiceLoadCertificates());

    }


    public void setNewNFe(EnviNfeVO enviNfeVO) {

        setEnviNfeVO(enviNfeVO);
        getTaskList().add(TASK_NFE.NFE_GERAR);
        getTaskList().add(TASK_NFE.NFE_ASSINAR);
        newNFev400_xmlAssinado(null);

    }

    public void newNFev400_xmlAssinado(String xml) {

        System.out.printf("%s:[%s]\n", "newNFev400_xmlAssinado", xml);
        if (xml != null)
            setXmlAssinado(xml);
        getTaskList().add(TASK_NFE.NFE_TRANSMITIR);
        newNFev400_xmlConsRecibo(null);

    }

    public void newNFev400_xmlConsRecibo(String xml) {

        System.out.printf("%s:[%s]\n", "newNFev400_xmlConsRecibo", xml);
        if (xml != null)
            XML_CONS_RECIBO = xml;
        getTaskList().add(TASK_NFE.NFE_RETORNO);
        getTaskList().add(TASK_NFE.NFE_PROC);
        newNFev400_xmlProtNfe(null);

    }

    public void newNFev400_xmlProtNfe(String xml) {

        System.out.printf("%s:[%s]\n", "newNFev400_xmlProtNfe", xml);
        if (xml != null)
            setXmlProcNfe(xml);
        getTaskList().add(TASK_NFE.NFE_DANFE);

    }

    public boolean errNoCertificado() throws Exception {
        if (CERTIFICATES.getX509Certificate() == null)
            CERTIFICATES.loadToken();
        return false;

    }

    public Task newTaskNFe() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Loading...");
                int contadorTarefa = 0, qtdTarefas = getTaskList().size();
                for (TASK_NFE taskNfe : getTaskList()) {
                    updateProgress(contadorTarefa++, qtdTarefas);
                    updateMessage(taskNfe.getDescricao());
                    switch (taskNfe) {
                        case NFE_GERAR:
                            if (getEnviNfeVO() == null)
                                Thread.currentThread().interrupt();
                            setXml(new EnviNfe_v400(getEnviNfeVO()).getXml());
                            break;
                        case NFE_ASSINAR:
                            if (getXml() == null)
                                Thread.currentThread().interrupt();
                            setXmlAssinado(new NFeAssinar(getXml()).getXmlAssinado());
                            break;
                        case NFE_TRANSMITIR:
                            if (getXmlAssinado() == null)
                                Thread.currentThread().interrupt();
                            setXmlAutorizado(new NFeAutorizacao(getXmlAssinado()).getXmlAutorizado());
                            break;
                        case NFE_RETORNO:
                            if ((XML_CONS_RECIBO = new NFeConsRecibo(getXmlAutorizado()).getXmlConsRecibo()) == null)
                                Thread.currentThread().interrupt();
                            setXmlRetAutorizacao(new NFeRetAutorizacao(XML_CONS_RECIBO).getXmlRetornoAutorizacao());
                            break;
                        case NFE_PROC:
                            if (getXmlRetAutorizacao() == null)
                                Thread.currentThread().interrupt();
                            setXmlProcNfe(new NFeProc(getXmlAssinado(), getXmlRetAutorizacao()).getXmlNFeProc());
                            break;
                        case NFE_DANFE:
                            if (getXmlProcNfe() == null)
                                Thread.currentThread().interrupt();
                            NFePrintPrompt.print("imprimirNFeProc", getXmlProcNfe());
                            break;
                    }
                }
                updateMessage("tarefa concluída!!!");
                updateProgress(getTaskList().size(), getTaskList().size());
                return null;
            }
        };
    }

    /**
     * Begin Getters and Setters
     */

    public static boolean isAmbProducao() {
        return AMB_PRODUCAO;
    }

    public static void setAmbProducao(boolean ambProducao) {
        AMB_PRODUCAO = ambProducao;
    }

    public static ZoneId getZoneId() {
        return ZONE_ID;
    }

    public static void setZoneId(ZoneId zoneId) {
        ZONE_ID = zoneId;
    }

    public static DateTimeFormatter getDtfNfeToLocalDate() {
        return DTF_NFE_TO_LOCAL_DATE;
    }

    public static void setDtfNfeToLocalDate(DateTimeFormatter dtfNfeToLocalDate) {
        DTF_NFE_TO_LOCAL_DATE = dtfNfeToLocalDate;
    }

    public static ServiceLoadCertificates getCERTIFICATES() {
        return CERTIFICATES;
    }

    public static void setCERTIFICATES(ServiceLoadCertificates CERTIFICATES) {
        NFev400.CERTIFICATES = CERTIFICATES;
    }

    public static boolean isPrintPrompt() {
        return PRINT_PROMPT;
    }

    public static void setPrintPrompt(boolean printPrompt) {
        PRINT_PROMPT = printPrompt;
    }

    public static String getXmlConsRecibo() {
        return XML_CONS_RECIBO;
    }

    public static void setXmlConsRecibo(String xmlConsRecibo) {
        XML_CONS_RECIBO = xmlConsRecibo;
    }

    public static String getDigVal() {
        return DIG_VAL;
    }

    public static void setDigVal(String digVal) {
        DIG_VAL = digVal;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getXmlAssinado() {
        return xmlAssinado;
    }

    public void setXmlAssinado(String xmlAssinado) {
        this.xmlAssinado = xmlAssinado;
    }

    public String getXmlAutorizado() {
        return xmlAutorizado;
    }

    public void setXmlAutorizado(String xmlAutorizado) {
        this.xmlAutorizado = xmlAutorizado;
    }

    public String getXmlRetAutorizacao() {
        return xmlRetAutorizacao;
    }

    public void setXmlRetAutorizacao(String xmlRetAutorizacao) {
        this.xmlRetAutorizacao = xmlRetAutorizacao;
    }

    public String getXmlProcNfe() {
        return xmlProcNfe;
    }

    public void setXmlProcNfe(String xmlProcNfe) {
        this.xmlProcNfe = xmlProcNfe;
    }

    public EnviNfeVO getEnviNfeVO() {
        return enviNfeVO;
    }

    public void setEnviNfeVO(EnviNfeVO enviNfeVO) {
        this.enviNfeVO = enviNfeVO;
    }

    public List<TASK_NFE> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TASK_NFE> taskList) {
        this.taskList = taskList;
    }

    /**
     * END Getters and Setters
     */


}
