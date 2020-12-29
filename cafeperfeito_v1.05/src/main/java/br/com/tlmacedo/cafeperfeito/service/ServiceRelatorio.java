package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.RelatorioTipo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-05-02
 * Time: 13:24
 */

public class ServiceRelatorio {

    public ServiceRelatorio() {
    }

    public void gerar(RelatorioTipo tipo, File pathXml) throws JRException {

        JRDataSource ds = new JRXmlDataSource(pathXml);

        InputStream relJasper = getClass().getResourceAsStream(tipo.getDescricao());

        JasperPrint impressao = null;

        try {
            impressao = JasperFillManager.fillReport(relJasper, new HashMap<>(), ds);
            JasperViewer viewer = new JasperViewer(impressao, false);
            viewer.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void gerar(RelatorioTipo tipo, Map parametros, List list) throws JRException {

        InputStream relJasper = getClass().getResourceAsStream(tipo.getDescricao());

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);

        JasperPrint impressao = null;

        try {
            impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
            JasperViewer viewer = new JasperViewer(impressao, false);
            viewer.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
