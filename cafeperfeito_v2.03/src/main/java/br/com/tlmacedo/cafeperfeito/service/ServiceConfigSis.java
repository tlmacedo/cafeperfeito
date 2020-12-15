package br.com.tlmacedo.cafeperfeito.service;

import br.com.cafeperfeito.xsd.config_sis.config.TConfig;
import br.com.tlmacedo.nfe.service.ServiceUtilXml;
import javafx.scene.image.Image;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Locale;

public class ServiceConfigSis {
    public static TConfig TCONFIG;
    public static Locale MY_LOCALE;
    public static String PATHICONE, PATHFXML;
    public static List SPLASH_IMAGENS;
    public static Image IMG_DEFAULT_PRODUTO;

    private FileReader arqConfgSistema = null;

    public ServiceConfigSis() throws FileNotFoundException {
        arqConfgSistema = new FileReader(getClass().getClassLoader().getResource("configSis.xml").getFile());
//        if (UsuarioLogado.getUsuario() == null) {
//            UsuarioDAO usuarioDAO = new UsuarioDAO();
//            UsuarioLogado.setUsuario(usuarioDAO.getById(Usuario.class, 1L));
//        }
        new ServiceConfigNFe().getVariaveisNFe();
    }

    public void getVariaveisSistema() {
        try {
            String xml = ServiceUtilXml.FileXml4String(getArqConfgSistema());
            TCONFIG = ServiceUtilXml.xmlToObject(xml, TConfig.class);
            MY_LOCALE = new Locale(TCONFIG.getMyLocale().substring(0, 2), TCONFIG.getMyLocale().substring(3));
            Locale.setDefault(MY_LOCALE);
            PATHICONE = TCONFIG.getPaths().getPathIconeSistema();
            PATHFXML = TCONFIG.getPaths().getPathFXML();
            SPLASH_IMAGENS = TCONFIG.getPersonalizacao().getSplashImagens().getImage();
            //IMG_DEFAULT_PRODUTO = new Image(getClass().getResource("image/default_produto.png").toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void getVariaveisSistemaSimples() {
        try {
            String xml = ServiceUtilXml.FileXml4String(getArqConfgSistema());
            TCONFIG = ServiceUtilXml.xmlToObject(xml, TConfig.class);
            MY_LOCALE = new Locale(TCONFIG.getMyLocale().substring(0, 2), TCONFIG.getMyLocale().substring(3));
            Locale.setDefault(MY_LOCALE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void getVariaveisSistemaBasica() {
        try {
            String xml = ServiceUtilXml.FileXml4String(new FileReader(ServiceConfigSis.class.getClassLoader().getResource("configSis.xml").getFile()));
            TCONFIG = ServiceUtilXml.xmlToObject(xml, TConfig.class);
            MY_LOCALE = new Locale(TCONFIG.getMyLocale().substring(0, 2), TCONFIG.getMyLocale().substring(3));
            Locale.setDefault(MY_LOCALE);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public FileReader getArqConfgSistema() {
        try {
            if (arqConfgSistema == null)
                arqConfgSistema = new FileReader(getClass().getClassLoader().getResource("configSis.xml").getFile());
        } catch (FileNotFoundException ex) {
            arqConfgSistema = null;
            ex.printStackTrace();
        }
        return arqConfgSistema;
    }

}
