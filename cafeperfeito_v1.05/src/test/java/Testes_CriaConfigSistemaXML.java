package test;//import br.com.tlmacedo.cafeperfeito.xsd.configSistema.informacaoBasica.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class Testes_CriaConfigSistemaXML {


    public static void main(String... args) throws Exception {

//        TInformacaoBasica inf = new TInformacaoBasica();
//        inf.setIdLoja(1);
//        inf.setTitulo("Café Perfeito");
//        inf.setCopyright("Café Perfeito " + "\u00a9 ");
//        inf.setTheme("principal");
//        inf.setDdd(92);
//        inf.setPortaSSl(443);
//        inf.setMyLocale(new Locale("pt", "br").toString());
//
//        String xml = ServiceXmlUtil.objectToXml(inf);
//        String diretorio = "/Volumes/150GB-Development/Java/Intellij/sidtmcafe10/src/sidtmcafe/resources/xml/configSistema.xml";
//        FileWriter arquivo = new FileWriter(new File(diretorio));
//        arquivo.write(xml);
//        arquivo.close();
//
//        xml = ServiceXmlUtil.leXml(new FileInputStream("/Volumes/150GB-Development/Java/Intellij/sidtmcafe10/src/sidtmcafe/resources/xml/configSistema.xml"));
//        inf = ServiceXmlUtil.xmlToObject(xml, TInformacaoBasica.class);
//        configSistema = new ConfigSistemaVO(inf);
//
//        System.out.println(String.format("%s: [%s]", "getIdLoja", configSistema.getIdLoja()));
//        System.out.println(String.format("%s: [%s]", "getTituloLoja", configSistema.getTituloLoja()));
//        System.out.println(String.format("%s: [%s]", "getCopyright", configSistema.getCopyright()));
//        System.out.println(String.format("%s: [%s]", "getTheme", configSistema.getTheme()));
//        System.out.println(String.format("%s: [%s]", "getDdd", configSistema.getDdd()));
//        System.out.println(String.format("%s: [%s]", "getPortaSSL", configSistema.getPortaSSL()));
//        System.out.println(String.format("%s: [%s]", "getMyLocale", configSistema.getMyLocale()));
//        System.out.println(String.format("%s: [%s]", "Locale.getDefault()", Locale.getDefault()));
//        Locale.setDefault(configSistema.getMyLocale());
//        System.out.println(String.format("%s_1: [%s]", "Locale.getDefault()", Locale.getDefault()));
////        System.out.println(String.format("%s: [%s]","", configSistema));
////        System.out.println(String.format("%s: [%s]","", configSistema));
////        System.out.println(String.format("%s: [%s]","", configSistema));
    }

    public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(classe);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();
    }
}
