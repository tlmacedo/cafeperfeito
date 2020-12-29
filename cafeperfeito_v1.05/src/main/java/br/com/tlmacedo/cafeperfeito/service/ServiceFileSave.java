package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.nfe.ServiceImprimeDanfe;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TEnviNFe;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe;
import br.inf.portalfiscal.xsd.nfe.procNFe.TNfeProc;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema.TCONFIG;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-06-05
 * Time: 11:54
 */

public class ServiceFileSave {

    public static boolean saveNfeProcXmlOut(TNfeProc tNfeProc) {
        try {
            System.out.printf(String.format("001[%s]\n",
                    TCONFIG.getPaths().getPathNFeSaveXmlOut().trim()));

            System.out.printf(String.format("002[%s]\n",
                    (tNfeProc.getNFe().getInfNFe().getId())));

            System.out.printf(String.format("003[%s]\n",
                    (tNfeProc.getProtNFe().getInfProt().getCStat().equals("100")) ? "-nfe" : "-naoAutorizado"));


            String diretorio = String.format("%s%s%s%s.xml",
                    System.getProperty("user.dir"),
                    TCONFIG.getPaths().getPathNFeSaveXmlOut().trim(),
                    tNfeProc.getNFe().getInfNFe().getId(),
                    (tNfeProc.getProtNFe().getInfProt().getCStat().equals("100")) ? "-nfe" : "-naoAutorizado");
            FileWriter arqXml = new FileWriter(new File(diretorio));
//            FileWriter arqXml = new FileWriter(new File(
//                    String.format("%s%s%s%s.xml",
//                            System.getProperty("user.dir"),
//                            TCONFIG.getPaths().getPathNFeSaveXmlOut().trim(),
//                            tNfeProc.getNFe().getInfNFe().getId(),
//                            (tNfeProc.getProtNFe().getInfProt().getCStat().equals("100")) ? "-nfe" : "-naoAutorizado")
//            ));
            System.out.printf("diretorio que foi achado: [%s]\n", arqXml.getClass().getName());
            arqXml.write(ServiceXmlUtil.objectToXml(tNfeProc));
            arqXml.close();
            new ServiceImprimeDanfe().imprimeDanfe(new File(diretorio));
            //new ServiceRelatorio().gerar(RelatorioTipo.NFE, diretorio);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean saveNfeXmlOut(TEnviNFe tEnviNFe) {
        try {
            TNFe tnFe = tEnviNFe.getNFe().get(0);
            System.out.printf(String.format("001[%s]\n",
                    TCONFIG.getPaths().getPathNFeSaveXmlOut().trim()));

            System.out.printf(String.format("002[%s]\n",
                    (tnFe.getInfNFe().getId())));

            System.out.printf(String.format("003[%s]\n",
                    (tnFe.getSignature() != null) ? "-assinada" : ""));


            FileWriter arqXml = new FileWriter(new File(
                    String.format("%s%s%s%s.xml",
                            System.getProperty("user.dir"),
                            TCONFIG.getPaths().getPathNFeSaveXmlOut().trim(),
                            tnFe.getInfNFe().getId(),
                            (tnFe.getSignature() != null) ? "-assinada" : "")
            ));
            arqXml.write(ServiceXmlUtil.objectToXml(tEnviNFe));
            arqXml.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
