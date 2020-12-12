package br.com.tlmacedo.nfe.service;


import br.inf.portalfiscal.wsdl.nfe.prod.NfeAutorizacao4.NfeAutorizacao4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

public class NFeAutorizacao {

    private OMElement ome = null;

    private NfeAutorizacao4Stub.NfeDadosMsg dadosMsg_Prod;
    private NfeAutorizacao4Stub stub_Prod;
    private NfeAutorizacao4Stub.NfeResultMsg resultMsg_Prod;
    private String xmlAutorizado;

    private br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg dadosMsg_Hom;
    private br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub stub_Hom;
    private br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub.NfeResultMsg resultMsg_Hom;


    public NFeAutorizacao(String xmlNFeAssinada) throws XMLStreamException, RemoteException {

        setOme(AXIOMUtil.stringToOM(xmlNFeAssinada));

        if (NFev400.isAmbProducao()) {
            setDadosMsg_Prod(new NfeAutorizacao4Stub.NfeDadosMsg());
            setStub_Prod(new NfeAutorizacao4Stub());
            getDadosMsg_Prod().setExtraElement(getOme());
            setResultMsg_Prod(getStub_Prod().nfeAutorizacaoLote(getDadosMsg_Prod()));
            setXmlAutorizado(getResultMsg_Prod().getExtraElement().toString());
        } else {
            setDadosMsg_Hom(new br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg());
            setStub_Hom(new br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub());
            getDadosMsg_Hom().setExtraElement(getOme());
            setResultMsg_Hom(getStub_Hom().nfeAutorizacaoLote(getDadosMsg_Hom()));
            setXmlAutorizado(getResultMsg_Hom().getExtraElement().toString());
        }

        NFePrintPrompt.print("xmlAutorizado", getXmlAutorizado());

    }

//    public String getXmlAutorizacaoNFe() throws JAXBException {
//        if (NFev400.isAmbProducao())
//            setXmlAutorizado(getResultMsg_Prod().getExtraElement().toString());
//        else
//            setXmlAutorizado(getResultMsg_Hom().getExtraElement().toString());
//
////        if (NFev400.PRINT_PROMPT)
////            System.out.printf("\n%sxmlNFeAutorizacao: \n%s\n",
////                    (NFev400.AMB_PRODUCAO) ? "prod_" : "hom_",
////                    getXml());
//
////        NFeConsRecibo.getXmlConsReciNFe(getXml());
//
//        return getXmlAutorizado();
//
//    }

    /**
     * Begin Getters and Setters
     */

    public OMElement getOme() {
        return ome;
    }

    public void setOme(OMElement ome) {
        this.ome = ome;
    }

    public NfeAutorizacao4Stub.NfeDadosMsg getDadosMsg_Prod() {
        return dadosMsg_Prod;
    }

    public void setDadosMsg_Prod(NfeAutorizacao4Stub.NfeDadosMsg dadosMsg_Prod) {
        this.dadosMsg_Prod = dadosMsg_Prod;
    }

    public NfeAutorizacao4Stub getStub_Prod() {
        return stub_Prod;
    }

    public void setStub_Prod(NfeAutorizacao4Stub stub_Prod) {
        this.stub_Prod = stub_Prod;
    }

    public NfeAutorizacao4Stub.NfeResultMsg getResultMsg_Prod() {
        return resultMsg_Prod;
    }

    public void setResultMsg_Prod(NfeAutorizacao4Stub.NfeResultMsg resultMsg_Prod) {
        this.resultMsg_Prod = resultMsg_Prod;
    }

    public String getXmlAutorizado() {
        return xmlAutorizado;
    }

    public void setXmlAutorizado(String xmlAutorizado) {
        this.xmlAutorizado = xmlAutorizado;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg getDadosMsg_Hom() {
        return dadosMsg_Hom;
    }

    public void setDadosMsg_Hom(br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg dadosMsg_Hom) {
        this.dadosMsg_Hom = dadosMsg_Hom;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub getStub_Hom() {
        return stub_Hom;
    }

    public void setStub_Hom(br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub stub_Hom) {
        this.stub_Hom = stub_Hom;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub.NfeResultMsg getResultMsg_Hom() {
        return resultMsg_Hom;
    }

    public void setResultMsg_Hom(br.inf.portalfiscal.wsdl.nfe.hom.NfeAutorizacao4.NfeAutorizacao4Stub.NfeResultMsg resultMsg_Hom) {
        this.resultMsg_Hom = resultMsg_Hom;
    }

    /**
     * END Getters and Setters
     */
}
