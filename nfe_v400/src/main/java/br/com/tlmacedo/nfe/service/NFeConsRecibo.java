package br.com.tlmacedo.nfe.service;

import br.inf.portalfiscal.xsd.nfe.consReciNFe.TConsReciNFe;
import br.inf.portalfiscal.xsd.nfe.retEnviNFe.TRetEnviNFe;

import javax.xml.bind.JAXBException;

public class NFeConsRecibo {

    private static TConsReciNFe tConsReciNFe;
    private static TRetEnviNFe tRetEnviNFe;
    private String xmlConsRecibo;

    public NFeConsRecibo(String xmlRetornoAutorizacao) throws JAXBException {
        settRetEnviNFe(ServiceUtilXml.xmlToObject(xmlRetornoAutorizacao, TRetEnviNFe.class));
        setXmlConsRecibo(ServiceUtilXml.objectToXml(newConsReciNFe()));
        NFePrintPrompt.print("xmlConsultaReciNFe", getXmlConsRecibo());
    }

    private static TConsReciNFe newConsReciNFe() {
        settConsReciNFe(new TConsReciNFe());
        gettConsReciNFe().setVersao(gettRetEnviNFe().getVersao());
        gettConsReciNFe().setTpAmb(gettRetEnviNFe().getTpAmb());
        gettConsReciNFe().setNRec(gettRetEnviNFe().getInfRec().getNRec());
        return gettConsReciNFe();
    }

    /**
     * Begin Getters and Setters
     */
    public static TConsReciNFe gettConsReciNFe() {
        return tConsReciNFe;
    }

    public static void settConsReciNFe(TConsReciNFe tConsReciNFe) {
        NFeConsRecibo.tConsReciNFe = tConsReciNFe;
    }

    public static TRetEnviNFe gettRetEnviNFe() {
        return tRetEnviNFe;
    }

    public static void settRetEnviNFe(TRetEnviNFe tRetEnviNFe) {
        NFeConsRecibo.tRetEnviNFe = tRetEnviNFe;
    }

    public String getXmlConsRecibo() {
        return xmlConsRecibo;
    }

    public void setXmlConsRecibo(String xmlConsRecibo) {
        this.xmlConsRecibo = xmlConsRecibo;
    }

    /**
     * END Getters and Setters
     */
}
