import br.com.cafeperfeito.xsd.config_nfe.config.MyInfNfe;
import br.com.cafeperfeito.xsd.config_nfe.config.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class ServiceUtilXml {

    @SuppressWarnings("Duplicates")
    public static <T> String objectToXml(Object obj) throws JAXBException {

        JAXBContext context = null;
        JAXBElement<?> element = null;
        System.out.println(obj.getClass().getSimpleName());
        switch (obj.getClass().getSimpleName()) {
            case "MyInfNfe":
                context = JAXBContext.newInstance(MyInfNfe.class);
                //element = new ObjectFactory().createInfMyNfe((MyInfNfe) obj);
                element = new ObjectFactory().createInfMyNfe((MyInfNfe) obj);
//                element = new br.com.cafeperfeito.xsd.configNFe.config.ObjectFactory().createXsdNFe((TMyInfNFe) obj);
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
