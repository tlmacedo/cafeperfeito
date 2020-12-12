package br.com.tlmacedo.nfe.service;

import br.inf.portalfiscal.wsdl.nfe.prod.NfeRetAutorizacao4.NfeRetAutorizacao4Stub;
import br.inf.portalfiscal.xsd.nfe.retConsReciNFe.TProtNFe.InfProt;
import br.inf.portalfiscal.xsd.nfe.retConsReciNFe.TRetConsReciNFe;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;
import java.util.Base64;

public class NFeRetAutorizacao {

    private OMElement ome = null;
    private String xmlRetornoAutorizacao;
    private TRetConsReciNFe tRetConsReciNFe = null;
    private InfProt infProt = null;


    private NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg_Prod;
    private NfeRetAutorizacao4Stub stub_Prod;
    private NfeRetAutorizacao4Stub.NfeResultMsg resultMsg_Prod;

    private br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg_Hom;
    private br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub stub_Hom;
    private br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeResultMsg resultMsg_Hom;


    public NFeRetAutorizacao(String xmlConsRecibo) throws RemoteException, XMLStreamException, JAXBException, InterruptedException, ExceptionWsNFe {
        try {
            setOme(AXIOMUtil.stringToOM(xmlConsRecibo));

            if (NFev400.isAmbProducao()) {
                setDadosMsg_Prod(new NfeRetAutorizacao4Stub.NfeDadosMsg());
                setStub_Prod(new NfeRetAutorizacao4Stub());
                getDadosMsg_Prod().setExtraElement(getOme());
            } else {
                setDadosMsg_Hom(new br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeDadosMsg());
                setStub_Hom(new br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub());
                getDadosMsg_Hom().setExtraElement(getOme());
            }

            while (!ConsultaRetAutorizacao())
                Thread.sleep(1000);

            setInfProt(gettRetConsReciNFe().getProtNFe().get(0).getInfProt());

            switch (getInfProt().getCStat()) {
                case "100":
                    break;
                default:
                    throw new ExceptionWsNFe(Integer.valueOf(getInfProt().getTpAmb()),
                            Integer.valueOf(getInfProt().getCStat()),
                            getInfProt().getXMotivo());
            }
            System.out.printf("passouDireto: [%s]\n", "NFeRetAutorizacao");
        } catch (Exception ex) {
            System.out.printf("errorAqui: [%s]\n", "NFeRetAutorizacao");
            ex.printStackTrace();
        }

    }

    public boolean ConsultaRetAutorizacao() throws JAXBException, RemoteException {

        boolean retorno = false;
        if (NFev400.isAmbProducao()) {
            setResultMsg_Prod(getStub_Prod().nfeRetAutorizacaoLote(getDadosMsg_Prod()));
            setXmlRetornoAutorizacao(getResultMsg_Prod().getExtraElement().toString());
        } else {
            setResultMsg_Hom(getStub_Hom().nfeRetAutorizacaoLote(getDadosMsg_Hom()));
            setXmlRetornoAutorizacao(getResultMsg_Hom().getExtraElement().toString());
        }
        settRetConsReciNFe(ServiceUtilXml.xmlToObject(getXmlRetornoAutorizacao(), TRetConsReciNFe.class));
        if (!gettRetConsReciNFe().getCStat().equals("105"))
            retorno = true;
        NFePrintPrompt.print("xmlNFeRetAutorizacao", getXmlRetornoAutorizacao());

        return retorno;

    }

    private String getStrInfProt() {
        return String.format("\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n" +
                        "<resultado>\n" +
                        "\t<tpAmb>%s</tpAmb>\n" +
                        "\t<verAplic>%s</verAplic>\n" +
                        "\t<chNFe>%s</chNFe>\n" +
                        "\t<dhRecbto>%s</dhRecbto>\n" +
                        "\t<nProt>%s</nProt>\n" +
                        "\t<digVal>%s</digVal>\n" +
                        "\t<cStat>%s</cStat>\n" +
                        "\t<xMotivo>%s</xMotivo>\n" +
                        "</resultado>" +
                        "\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n",
                getInfProt().getTpAmb(),
                getInfProt().getVerAplic(),
                getInfProt().getChNFe(),
                getInfProt().getDhRecbto(),
                getInfProt().getNProt(),
                (getInfProt().getDigVal() == null) ? "" :
                        Base64.getEncoder().encodeToString(getInfProt().getDigVal()),
                getInfProt().getCStat(),
                getInfProt().getXMotivo()
        );
    }

    /**
     * Begin Getters and Setters
     */

    public OMElement getOme() {
        return ome;
    }

    public void setOme(OMElement ome) {
        this.ome = ome;
    }

    public String getXmlRetornoAutorizacao() {
        return xmlRetornoAutorizacao;
    }

    public void setXmlRetornoAutorizacao(String xmlRetornoAutorizacao) {
        this.xmlRetornoAutorizacao = xmlRetornoAutorizacao;
    }

    public TRetConsReciNFe gettRetConsReciNFe() {
        return tRetConsReciNFe;
    }

    public void settRetConsReciNFe(TRetConsReciNFe tRetConsReciNFe) {
        this.tRetConsReciNFe = tRetConsReciNFe;
    }

    public InfProt getInfProt() {
        return infProt;
    }

    public void setInfProt(InfProt infProt) {
        this.infProt = infProt;
    }

    public NfeRetAutorizacao4Stub.NfeDadosMsg getDadosMsg_Prod() {
        return dadosMsg_Prod;
    }

    public void setDadosMsg_Prod(NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg_Prod) {
        this.dadosMsg_Prod = dadosMsg_Prod;
    }

    public NfeRetAutorizacao4Stub getStub_Prod() {
        return stub_Prod;
    }

    public void setStub_Prod(NfeRetAutorizacao4Stub stub_Prod) {
        this.stub_Prod = stub_Prod;
    }

    public NfeRetAutorizacao4Stub.NfeResultMsg getResultMsg_Prod() {
        return resultMsg_Prod;
    }

    public void setResultMsg_Prod(NfeRetAutorizacao4Stub.NfeResultMsg resultMsg_Prod) {
        this.resultMsg_Prod = resultMsg_Prod;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeDadosMsg getDadosMsg_Hom() {
        return dadosMsg_Hom;
    }

    public void setDadosMsg_Hom(br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg_Hom) {
        this.dadosMsg_Hom = dadosMsg_Hom;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub getStub_Hom() {
        return stub_Hom;
    }

    public void setStub_Hom(br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub stub_Hom) {
        this.stub_Hom = stub_Hom;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeResultMsg getResultMsg_Hom() {
        return resultMsg_Hom;
    }

    public void setResultMsg_Hom(br.inf.portalfiscal.wsdl.nfe.hom.NfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeResultMsg resultMsg_Hom) {
        this.resultMsg_Hom = resultMsg_Hom;
    }

    /**
     * END Getters and Setters
     */
}
