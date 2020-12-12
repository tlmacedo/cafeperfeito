package br.com.tlmacedo.cafeperfeito.service;

import br.com.cafeperfeito.xsd.config_nfe.config.MyInfNfe;
import br.com.tlmacedo.nfe.service.ServiceUtilXml;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ServiceConfigNFe {

    public static MyInfNfe MYINFNFE;

    private FileReader arqConfgSistema = null;

    public ServiceConfigNFe() throws FileNotFoundException {
        arqConfgSistema = new FileReader(getClass().getClassLoader().getResource("configNFe.xml").getFile());
    }

    public void getVariaveisNFe() {
        try {
            String xml = ServiceUtilXml.FileXml4String(arqConfgSistema);
            MYINFNFE = ServiceUtilXml.xmlToObject(xml, MyInfNfe.class);
            System.setProperty("senhaDoCertificado", "4879");
            System.setProperty("nfeTokenUtilizado", "tokenSafeNet5100.cfg");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}