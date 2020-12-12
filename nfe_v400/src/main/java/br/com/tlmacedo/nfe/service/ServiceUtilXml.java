package br.com.tlmacedo.nfe.service;

import br.inf.portalfiscal.xsd.nfe.consReciNFe.TConsReciNFe;
import br.inf.portalfiscal.xsd.nfe.consStatServ.ObjectFactory;
import br.inf.portalfiscal.xsd.nfe.consStatServ.TConsStatServ;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TEnviNFe;
import br.inf.portalfiscal.xsd.nfe.nfe.TNFe;
import br.inf.portalfiscal.xsd.nfe.procNFe.TNfeProc;
import br.inf.portalfiscal.xsd.nfe.retConsReciNFe.TProtNFe;
import br.inf.portalfiscal.xsd.nfe.retConsReciNFe.TRetConsReciNFe;
import br.inf.portalfiscal.xsd.nfe.retEnviNFe.TRetEnviNFe;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.*;


public class ServiceUtilXml {

    private static final String STATUS = "TConsStatServ";
    private static final String TENVINFE = "TEnviNFe";
    private static final String TNFE = "TNFe";
    private static final String TNFEPROC = "TNfeProc";
    private static final String TPROD_LEIAUTE = "TNfeProc";
    private static final String TCONSRECINFE = "TConsReciNFe";
    private static final String RETORNO_ENVIO = "TRetEnviNFe";


    public static String FileXml4String(FileReader arquivo) {
        StringBuilder xml = new StringBuilder();
        BufferedReader in;
        try {
            in = new BufferedReader(arquivo);
            String linha;
            while ((linha = in.readLine()) != null)
                xml.append(linha);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Ler Xml: " + e.getMessage());
        }
        return xml.toString();

    }


    public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classe);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();

    }

    public static <T> String objectToXml(Object obj) throws JAXBException {
        JAXBContext context = null;
        JAXBElement<?> element = null;
        switch (obj.getClass().getSimpleName()) {
            case "TConsStatServ":
                context = JAXBContext.newInstance(TConsStatServ.class);
                element = new ObjectFactory().createConsStatServ((TConsStatServ) obj);
                break;
            case "TEnviNFe":
                context = JAXBContext.newInstance(TEnviNFe.class);
                element = new br.inf.portalfiscal.xsd.nfe.enviNFe.ObjectFactory().createEnviNFe((TEnviNFe) obj);
                break;
            case "TConsReciNFe":
                context = JAXBContext.newInstance(TConsReciNFe.class);
                element = new br.inf.portalfiscal.xsd.nfe.consReciNFe.ObjectFactory().createConsReciNFe((TConsReciNFe) obj);
                break;
            case "TRetEnviNFe":
                context = JAXBContext.newInstance(TRetEnviNFe.class);
                element = new br.inf.portalfiscal.xsd.nfe.retEnviNFe.ObjectFactory().createRetEnviNFe((TRetEnviNFe) obj);
                break;
            case "TNFe":
                context = JAXBContext.newInstance(TNFe.class);
                element = new br.inf.portalfiscal.xsd.nfe.nfe.ObjectFactory().createNFe((TNFe) obj);
                break;
            case "TNfeProc":
                context = JAXBContext.newInstance(TNfeProc.class);
                element = new br.inf.portalfiscal.xsd.nfe.procNFe.ObjectFactory().createNfeProc((TNfeProc) obj);
                break;
            case "TProtNFe":
                context = JAXBContext.newInstance(TProtNFe.class);
                element = new br.inf.portalfiscal.xsd.nfe.retConsReciNFe.ObjectFactory().createRetConsReciNFe((TRetConsReciNFe) obj);
                break;
        }

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty("jaxb.encoding", "Unicode");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();

        marshaller.marshal(element, sw);

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(sw.toString());

        return replacesNfe(xml.toString());

    }

    @SuppressWarnings("Duplicates")
    private static String replacesNfe(String xml) {
        xml = xml.replaceAll("ns2:", "");
        xml = xml.replaceAll("<!\\[CDATA\\[<!\\[CDATA\\[", "<!\\[CDATA\\[");
        xml = xml.replaceAll("\\]\\]>\\]\\]>", "\\]\\]>");
        xml = xml.replaceAll("ns3:", "");
        xml = xml.replaceAll("&lt;", "<");
        xml = xml.replaceAll("&amp;", "&");
        xml = xml.replaceAll("&gt;", ">");
        xml = xml.replaceAll("<Signature>", "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">");
        xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replaceAll(" xmlns=\"\" xmlns:ns3=\"http://www.portalfiscal.inf.br/nfe\"", "");
        return xml;

    }


}
