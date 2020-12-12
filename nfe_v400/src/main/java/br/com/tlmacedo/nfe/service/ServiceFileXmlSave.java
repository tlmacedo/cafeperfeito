package br.com.tlmacedo.nfe.service;

import br.inf.portalfiscal.xsd.nfe.enviNFe.TEnviNFe;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe;
import br.inf.portalfiscal.xsd.nfe.procNFe.TNfeProc;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ServiceFileXmlSave {

    private static FileWriter arqXml;
    private static String conteudoXml;
    private static File newFileXml;

    public static boolean saveXml(String nameFile, String xml) throws IOException {
        setConteudoXml(xml);

        if (!nameFile.contains("/"))
            nameFile = getPathFile(nameFile, null);

        setNewFileXml(new File(nameFile));

        return salvaArquivo();
    }

    public static boolean saveTEnviNFeToFile(TEnviNFe tEnviNFe) throws IOException, JAXBException {
        TNFe tnFe = tEnviNFe.getNFe().get(0);

        setConteudoXml(ServiceUtilXml.objectToXml(tnFe));

        setNewFileXml(new File(getPathFile(tnFe.getInfNFe().getId(),
                tnFe.getSignature() == null ? null : "-assinada")));
        return salvaArquivo();
    }

    public static boolean saveTNfeProcToFile(TNfeProc tNfeProc) throws IOException, JAXBException {

        setConteudoXml(ServiceUtilXml.objectToXml(tNfeProc));

        setNewFileXml(new File(getPathFile(tNfeProc.getNFe().getInfNFe().getId(),
                !tNfeProc.getProtNFe().getInfProt().getCStat().equals("100") ? null : "-proc")));

        if (salvaArquivo()) {
            System.out.printf("caminhoArquivo: [%s]\n", getNewFileXml());
            System.out.printf("caminhoArquivo.exists: [%s]\n", getNewFileXml().exists());

            System.out.printf("getArqXml.getName: [%s]\n", getArqXml().getClass().getName());
            System.out.printf("getArqXml.getSimpleName: [%s]\n", getArqXml().getClass().getSimpleName());

//            try {
//                ServiceRelatorio nfeDANFE = new ServiceRelatorio();
//                nfeDANFE.gerar(RelatorioTipo.NFE, getNewFileXml());
//            } catch (JRException ex) {
//                ex.printStackTrace();
//                return false;
//            }
        }
        return true;
    }

    private static String getPathFile(String nameFile, String posName) {
        return String.format("%s/%s%s%s%s.xml",
                System.getProperty("user.dir"),
                System.getProperty("moduloApp"),
                System.getProperty("pathNFeSaveXmlOut"),
                nameFile,
                posName == null ? "" : posName);

    }

    private static boolean salvaArquivo() {
        try {
//            if (getArqXml() == null)
            setArqXml(new FileWriter(getNewFileXml()));
            getArqXml().write(getConteudoXml());
            getArqXml().close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Begin Getters and Setters
     */
    private static FileWriter getArqXml() {
        return arqXml;
    }

    private static void setArqXml(FileWriter arqXml) {
        ServiceFileXmlSave.arqXml = arqXml;
    }

    private static String getConteudoXml() {
        return conteudoXml;
    }

    private static void setConteudoXml(String conteudoXml) {
        ServiceFileXmlSave.conteudoXml = conteudoXml;
    }

    public static File getNewFileXml() {
        return newFileXml;
    }

    public static void setNewFileXml(File newFileXml) {
        ServiceFileXmlSave.newFileXml = newFileXml;
    }

    /**
     * END Getters and Setters
     */
}
