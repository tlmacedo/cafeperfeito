package br.com.tlmacedo.cafeperfeito.service;

//import br.com.tlmacedo.cafeperfeito.xsd.sistema.config.TConfig;

import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.UsuarioDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.Empresa;
import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.Usuario;
import br.com.tlmacedo.cafeperfeito.xsd.sistema.config.TConfig;
import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

public class ServiceVariaveisSistema {
    public static TConfig TCONFIG;
    public static Locale MY_LOCALE;
    public static String PATHICONE, PATHFXML;
    public static List SPLASH_IMAGENS;
    public static Image IMG_DEFAULT_PRODUTO;

    public ServiceVariaveisSistema() {
        System.setProperty("senhaDoCertificado", "4879");
        if (LogadoInf.getUserLog() == null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            LogadoInf.setUserLog(usuarioDAO.getById(Usuario.class, 1L));
            LogadoInf.getUserLog().setApelido(StringUtils.capitalize(LogadoInf.getUserLog().getApelido()));
            LogadoInf.setLojaUser(new EmpresaDAO().getById(Empresa.class, Long.valueOf(1)));
        }

    }

    public void getVariaveisSistema() {
        try {
            FileInputStream arqConfiSistema = new FileInputStream(getClass().getClassLoader().getResource("xml/configSistema.xml").getFile());
            String xml = ServiceXmlUtil.leXml(arqConfiSistema);
            TCONFIG = ServiceXmlUtil.xmlToObject(xml, TConfig.class);
            MY_LOCALE = new Locale(ServiceVariaveisSistema.TCONFIG.getMyLocale().substring(0, 2), ServiceVariaveisSistema.TCONFIG.getMyLocale().substring(3));
            Locale.setDefault(MY_LOCALE);
            PATHICONE = TCONFIG.getPaths().getPathIconeSistema();
            PATHFXML = TCONFIG.getPaths().getPathFXML();
            SPLASH_IMAGENS = TCONFIG.getPersonalizacao().getSplashImagens().getImage();
            IMG_DEFAULT_PRODUTO = new Image("image/default_produto.png");
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getVariaveisSistemaSimples() {
        try {
            FileInputStream arqConfiSistema = new FileInputStream(getClass().getClassLoader().getResource("xml/configSistema.xml").getFile());
            String xml = ServiceXmlUtil.leXml(arqConfiSistema);
            TCONFIG = ServiceXmlUtil.xmlToObject(xml, TConfig.class);
            MY_LOCALE = new Locale(ServiceVariaveisSistema.TCONFIG.getMyLocale().substring(0, 2), ServiceVariaveisSistema.TCONFIG.getMyLocale().substring(3));
            Locale.setDefault(MY_LOCALE);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getVariaveisSistemaBasica() {
        try {
            FileInputStream arqConfiSistema = new FileInputStream(ServiceVariaveisSistema.class.getClassLoader().getResource("xml/configSistema.xml").getFile());
            String xml = ServiceXmlUtil.leXml(arqConfiSistema);
            TCONFIG = ServiceXmlUtil.xmlToObject(xml, TConfig.class);
            MY_LOCALE = new Locale(ServiceVariaveisSistema.TCONFIG.getMyLocale().substring(0, 2), ServiceVariaveisSistema.TCONFIG.getMyLocale().substring(3));
            Locale.setDefault(MY_LOCALE);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

//    public TConfig gettConfig() {
//        return TCONFIG;
//    }
//
//    public void settConfig(TConfig TCONFIG) {
//        this.TCONFIG = TCONFIG;
//    }
}
