package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.enums.RelatorioTipo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceRelatorio extends JFrame {
    public void gerar(RelatorioTipo tipo, File pathXml) throws JRException {
        JRDataSource ds = new JRXmlDataSource(pathXml, "/nfeProc/NFe/infNFe/det");
//        JRXmlDataSource xml = new JRXmlDataSource(pathXml, "/NFe/infNFe/det");

        InputStream relJasper = getClass().getResourceAsStream(tipo.getDescricao());

        JasperPrint impressao = JasperFillManager.fillReport(relJasper, new HashMap<>(), ds);
//        impressao = JasperFillManager.fillReport(relJasper, new HashMap<>(), xml);
        JasperViewer viewer = new JasperViewer(impressao, false);
        viewer.setVisible(true);
    }

    public void gerar(RelatorioTipo tipo, String strXml) throws IOException, SQLException, JRException {
        gerar(tipo, new ByteArrayInputStream(strXml.getBytes()));
    }

    public void gerar(RelatorioTipo tipo, Blob blobXml) throws SQLException, IOException, JRException {
        gerar(tipo, blobXml.getBinaryStream());
    }

    public void gerar(RelatorioTipo tipo, InputStream streamXml) throws JRException, IOException, SQLException {
        InputStream streamRel = getClass().getResourceAsStream(tipo.getDescricao());
        JRXmlDataSource xml = new JRXmlDataSource(streamXml, "/nfeProc/NFe/infNFe/det");
        JasperPrint jp = JasperFillManager.fillReport(streamRel, new HashMap<>(), xml);

        JasperViewer jv = new JasperViewer(jp, false);
        jv.setTitle("[DANFE] - Documento Auxiliar da Nota Fiscal Eletrônica");
        //jv.setIconImage(ImageIO.read(getClass().getResourceAsStream("/image/recibo/Logo.png")));
        jv.setVisible(true);

    }


    public void gerar(RelatorioTipo tipo, Map parametros, List list) throws JRException {

        InputStream relJasper = getClass().getResourceAsStream(tipo.getDescricao());

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);

        JasperPrint impressao = null;

        impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
        JasperViewer viewer = new JasperViewer(impressao, false);
        viewer.setVisible(true);
//        JRViewer viewer = new JRViewer(impressao);
//        this.add(viewer);
//        this.setSize(700, 500);
//        this.setVisible(true);
//        System.out.printf("Done!");
//        ServiceAlertMensagem alertMensagem;
    }
}
