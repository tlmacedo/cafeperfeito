import br.com.tlmacedo.cafeperfeito.model.dao.SaidaProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProdutoNfe;
import br.com.tlmacedo.cafeperfeito.nfe.Nfe;
import br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis;
import br.com.tlmacedo.cafeperfeito.service.alert.Alert_Ok;
import br.com.tlmacedo.cafeperfeito.service.alert.Alert_YesNo;
import br.com.tlmacedo.nfe.service.NFev400;
import br.com.tlmacedo.nfe.service.ServiceLoadCertificates;

import java.time.LocalDateTime;
import java.util.Scanner;

import static br.com.tlmacedo.cafeperfeito.nfe.NfeService.getChaveNfe;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigNFe.MYINFNFE;

public class Testes {

    Scanner scan;
    private ServiceLoadCertificates loadCertificates;
    private NFev400 nFev400;
    private SaidaProdutoNfe saidaProdutoNfe;
    private String xml;

    public NFev400 getnFev400() {
        return nFev400;
    }

    public void setnFev400(NFev400 nFev400) {
        this.nFev400 = nFev400;
    }

    public static void main(String... args) throws Exception {
        Testes testes = new Testes();
        new ServiceConfigSis().getVariaveisSistemaBasica();

        String strEnviNFe = testes.gerarNovaNFe();

//        testes.loadCertificates = new ServiceLoadCertificates();
//        testes.loadCertificates.loadToken();
//        testes.loadCertificates.loadSocketDinamico();
//
//        String strEnviNFeAssinado = testes.assinarXmlNFe(strEnviNFe);
//
//        String strAutorizacaoNFe = testes.sefazAtutorizacaoNFe(strEnviNFeAssinado);
//
//        String strRetAutorizacaoNFe = testes.sefazRetAutorizacaoNFe(strAutorizacaoNFe);
////        String strRetAutorizacaoNFe = testes.sefazRetAutorizacaoNFe("130000147476880");
//
//        testes.sefazProcNFe(strEnviNFeAssinado, strRetAutorizacaoNFe);
////        testes.sefazProcNFe("", strRetAutorizacaoNFe);

    }


    private String gerarNovaNFe() throws Exception {
        setnFev400(new NFev400((MYINFNFE.getMyConfig().getTpAmb().intValue() == 1), true));
        System.out.printf("\nqual saida de produto vc que gerar NF? ");
        scan = new Scanner(System.in);

        SaidaProduto saidaProduto = new SaidaProdutoDAO().getById(SaidaProduto.class, Long.valueOf(scan.nextLine().replaceAll("\\D", "")));
        //ServiceUtilJSon.printJsonFromObject(saidaProduto, "SaidaProduto:");

//        EnviNfeVO nfeVO;
        if (saidaProduto.getSaidaProdutoNfeList().size() > 0) {
            new Nfe(saidaProduto.getSaidaProdutoNfeList().get(0), false);
//            nfeVO = new NfeEnviNfeVO(saidaProduto.getSaidaProdutoNfeList().get(0), false).getEnviNfeVO();
        } else {
            new Nfe(getNewNFe(saidaProduto), false);
            //nfeVO = new NfeEnviNfeVO(getNewNFe(saidaProduto), false).getEnviNfeVO();
        }
//        new Nfe(nfeVO, false);
//        getnFev400().setNewNFe(nfeVO);
//        for (TASK_NFE taskNfe : getnFev400().getTaskList()) {
//            switch (taskNfe) {
//                case NFE_GERAR -> {
//                    if (nfeVO == null) return null;
//                    getnFev400().setXml(new EnviNfe_v400(nfeVO).getXml());
//                }
//                case NFE_ASSINAR -> {
//                    if (getnFev400().getXml() == null) return null;
//                    if (errCertificado()) return null;
//                    getnFev400().setXmlAssinado(new NFeAssinar(getnFev400().getXml()).getXmlAssinado());
//                }
//                case NFE_TRANSMITIR -> {
//                    if (getnFev400().getXmlAssinado() == null) return null;
//                    getnFev400().setXmlAutorizado(new NFeAutorizacao(getnFev400().getXmlAssinado()).getXmlAutorizado());
//                }
//                case NFE_RETORNO -> {
//                    setXmlConsRecibo(new NFeConsRecibo(getnFev400().getXmlAutorizado()).getXmlConsRecibo());
//                    if ((getXmlConsRecibo()) == null) return null;
//                    getnFev400().setXmlRetAutorizacao(new NFeRetAutorizacao(getXmlConsRecibo()).getXmlRetornoAutorizacao());
//                }
//            }
//        }

//            for (TASK_NFE taskNfe : getTaskList()) {
//                updateProgress(contadorTarefa++, qtdTarefas);
//                updateMessage(taskNfe.getDescricao());
//                switch (taskNfe) {
//                    case NFE_GERAR:
//                        if (getEnviNfeVO() == null)
//                            Thread.currentThread().interrupt();
//                        setXml(new EnviNfe_v400(getEnviNfeVO()).getXml());
//                        break;
//                    case NFE_ASSINAR:
//                        if (getXml() == null)
//                            Thread.currentThread().interrupt();
//                        setXmlAssinado(new NFeAssinar(getXml()).getXmlAssinado());
//                        break;
//                    case NFE_TRANSMITIR:
//                        if (getXmlAssinado() == null)
//                            Thread.currentThread().interrupt();
//                        setXmlAutorizado(new NFeAutorizacao(getXmlAssinado()).getXmlAutorizado());
//                        break;
//                    case NFE_RETORNO:
//                        if ((XML_CONS_RECIBO = new NFeConsRecibo(getXmlAutorizado()).getXmlConsRecibo()) == null)
//                            Thread.currentThread().interrupt();
//                        setXmlRetAutorizacao(new NFeRetAutorizacao(XML_CONS_RECIBO).getXmlRetornoAutorizacao());
//                        break;
//                    case NFE_PROC:
//                        if (getXmlRetAutorizacao() == null)
//                            Thread.currentThread().interrupt();
//                        setXmlProcNfe(new NFeProc(getXmlAssinado(), getXmlRetAutorizacao()).getXmlNFeProc());
//                        break;
//                    case NFE_DANFE:
//                        if (getXmlProcNfe() == null)
//                            Thread.currentThread().interrupt();
//                        NFePrintPrompt.print("imprimirNFeProc", getXmlProcNfe());
//                        break;
//                }
//            }

//            new Nfe(saidaProduto.getSaidaProdutoNfeList().get(0), false);
        return "poxapoxapoxa";

    }

    private boolean errCertificado() {
        boolean err = true, repete = false;
        do {
            try {
                err = getnFev400().errNoCertificado();
            } catch (Exception e) {
                repete = new Alert_YesNo("Certificado digital",
                        "erro no certificado, deseja tentar novamente?",
                        null).retorno();
            }
        } while (err && repete);
        if (err)
            new Alert_Ok("Erro", "Operação cancelada pelo usuário!", null);

        return (err);

    }

    private SaidaProdutoNfe getNewNFe(SaidaProduto saidaProduto) {
        SaidaProdutoNfe nfe = new SaidaProdutoNfe();

        scan = new Scanner(System.in);
        nfe.setSaidaProduto(saidaProduto);
        nfe.setNaturezaOperacao(MYINFNFE.getMyConfig().getNatOp().intValue());
        System.out.printf("\nqual o numero da NFe ? ");
        nfe.setNumero(Integer.parseInt(scan.nextLine().replaceAll("\\D", "")));
//        nfe.setNumero(NfeService.getLastNumeroNFe() + 1);
        nfe.setSerie(001);
        nfe.setModelo(MYINFNFE.getMyConfig().getMod().intValue());
        nfe.setImpressaoTpEmis(MYINFNFE.getMyConfig().getTpEmis().intValue());
        nfe.setImpressaoFinNFe(MYINFNFE.getMyConfig().getFinNFe().intValue());
        nfe.setPagamentoIndicador(MYINFNFE.getMyConfig().getIndPag().intValue());
        nfe.setPagamentoMeio(99);
        LocalDateTime dtHoraEmissao, dtHoraSaida;
        dtHoraEmissao = LocalDateTime.now();
        dtHoraSaida = LocalDateTime.now().plusDays(1);
        nfe.setDtHoraEmissao(dtHoraEmissao);
        nfe.setDtHoraSaida(dtHoraSaida);
        nfe.setDestinoOperacao(MYINFNFE.getMyConfig().getIdDest().intValue());
        if (saidaProduto.getCliente().getIe().equals(""))
            nfe.setConsumidorFinal(1);
        else
            nfe.setConsumidorFinal(0);
        nfe.setIndicadorPresenca(MYINFNFE.getMyConfig().getIndPres().intValue());
        nfe.setModFrete(0);
//            if (cboNfeTransportador.getSelectionModel().getSelectedIndex() >= 0)
//                nfe.setTransportador(cboNfeTransportador.getSelectionModel().getSelectedItem());
//            else
//                nfe.setTransportador(null);
        nfe.setCobrancaNumero(String.valueOf(nfe.getNumero()));
        nfe.setInformacaoAdicional("");

        nfe.setChave(getChaveNfe(nfe));

        saidaProduto.getSaidaProdutoNfeList().add(nfe);

        return nfe;
    }

//    private String sefazAtutorizacaoNFe(String strEnviNFeAssinado) {
//        String retorno = null;
//        OMElement ome = null;
//        try {
//            ome = AXIOMUtil.stringToOM(strEnviNFeAssinado);
//        } catch (XMLStreamException e) {
//            e.printStackTrace();
//        }
//
//        NfeAutorizacao4Stub.NfeDadosMsg dadosMsg = new NfeAutorizacao4Stub.NfeDadosMsg();
//        dadosMsg.setExtraElement(ome);
//
//        NfeAutorizacao4Stub stub = null;
//        try {
//            stub = new NfeAutorizacao4Stub();
//        } catch (AxisFault axisFault) {
//            axisFault.printStackTrace();
//        }
//        NfeAutorizacao4Stub.NfeResultMsg result = null;
//        try {
//            result = stub.nfeAutorizacaoLote(dadosMsg);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//
//        retorno = result.getExtraElement().toString();
//
//        System.out.printf("strAutorizacaoNFe:\n%s\n\n\n", retorno);
//        return retorno;
//    }
//
//    private String sefazRetAutorizacaoNFe(String strAutorizacaoNFe) {
//        String retorno = null;
//        boolean retornoValido = true;
//        String xmlConsReciNFe = null;
//
//        TRetEnviNFe tRetEnviNFe = null;
//        try {
//            tRetEnviNFe = ServiceXmlUtil.xmlToObject(strAutorizacaoNFe, TRetEnviNFe.class);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        TRetConsReciNFe tRetConsReciNFe = null;
//        while (retornoValido) {
//            try {
//                if (strAutorizacaoNFe.length() != 15)
//                    tRetEnviNFe = ServiceXmlUtil.xmlToObject(strAutorizacaoNFe, TRetEnviNFe.class);
//
//                TConsReciNFe tConsReciNFe = new TConsReciNFe();
//                tConsReciNFe.setVersao(TCONFIG.getNfe().getVersao());
//                tConsReciNFe.setTpAmb(String.valueOf(TCONFIG.getNfe().getTpAmb()));
//                if (strAutorizacaoNFe.length() != 15)
//                    tConsReciNFe.setNRec(tRetEnviNFe.getInfRec().getNRec());
//                else
//                    tConsReciNFe.setNRec(strAutorizacaoNFe);
//
//                xmlConsReciNFe = ServiceXmlUtil.objectToXml(tConsReciNFe);
//            } catch (JAXBException e) {
//                e.printStackTrace();
//            }
//
//            System.out.printf("xmlConsReciNFe:\n%s\n\n\n", xmlConsReciNFe);
//
//            OMElement ome = null;
//            try {
//                ome = AXIOMUtil.stringToOM(xmlConsReciNFe);
//            } catch (XMLStreamException e) {
//                e.printStackTrace();
//            }
//
//            NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg = new NfeRetAutorizacao4Stub.NfeDadosMsg();
//            dadosMsg.setExtraElement(ome);
//
//
//            NfeRetAutorizacao4Stub retAutorizacao4Stub = null;
//            try {
//                retAutorizacao4Stub = new NfeRetAutorizacao4Stub();
//            } catch (AxisFault axisFault) {
//                axisFault.printStackTrace();
//            }
//            NfeRetAutorizacao4Stub.NfeResultMsg result = null;
//            try {
//                result = retAutorizacao4Stub.nfeRetAutorizacaoLote(dadosMsg);
//                retorno = result.getExtraElement().toString();
//                tRetConsReciNFe = ServiceXmlUtil.xmlToObject(retorno, TRetConsReciNFe.class);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            } catch (JAXBException e) {
//                e.printStackTrace();
//            }
//            if (tRetConsReciNFe.getCStat().equals("105")) {
//                System.out.printf("\ncod(%s)Lote em processamento...\n%s\n\n\n", tRetConsReciNFe.getCStat(), retorno);
//            } else {
//                System.out.printf("\ncod(%s)Lote processado...\n%s\n\n\n", tRetConsReciNFe.getCStat(), retorno);
//                retornoValido = false;
//            }
//        }
//
//        System.out.printf("strRetAutorizacaoNFe:\n%s\n\n\n", retorno);
//        return retorno;
//    }
//
//    private String assinarXmlNFe(String strEnviNFe) {
//        ServiceAssinarXml serviceAssinarXml = new ServiceAssinarXml(strEnviNFe, loadCertificates);
//        String strEnviNFeAssinado = ServiceOutputXML.outputXML(serviceAssinarXml.getDocument());
//
//        TEnviNFe tEnviNFe = null;
//        try {
//            tEnviNFe = ServiceXmlUtil.xmlToObject(strEnviNFeAssinado, TEnviNFe.class);
//            ServiceFileSave.saveNfeXmlOut(tEnviNFe);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        System.out.printf("strEnviNFeAssinado:\n%s\n\n\n", strEnviNFeAssinado);
//        return strEnviNFeAssinado;
//    }
//
//    private String sefazProcNFe(String strEnviNFeAssinado, String strRetAutorizacaoNFe) {
//        String retorno = null;
//        TRetConsReciNFe tRetConsReciNFe = null;
//        TEnviNFe tEnviNFe = null;
//        try {
//            tRetConsReciNFe = ServiceXmlUtil.xmlToObject(strRetAutorizacaoNFe, TRetConsReciNFe.class);
//            tEnviNFe = ServiceXmlUtil.xmlToObject(strEnviNFeAssinado, TEnviNFe.class);
//            retorno = ServiceXmlUtil.objectToXml(tRetConsReciNFe);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        System.out.printf("tRetConsReciNFe:\n%s\n\n\n", retorno);
//        if (!tRetConsReciNFe.getProtNFe().get(0).getInfProt().getCStat().equals("100")) {
//            retorno = String.format("retorno do processamento foi inválido! cód(%s) - motivo: [%s]\nmsg: [%s-%s]\n",
//                    tRetConsReciNFe.getProtNFe().get(0).getInfProt().getCStat(),
//                    tRetConsReciNFe.getProtNFe().get(0).getInfProt().getXMotivo(),
//                    tRetConsReciNFe.getProtNFe().get(0).getInfProt().getCMsg(),
//                    tRetConsReciNFe.getProtNFe().get(0).getInfProt().getXMsg()
//            );
//            System.out.printf("deu erro:\n%s\n\n\n", retorno);
//            return retorno;
//        } else {
//            System.out.printf("tamo mais ou menos!!!\n%s\n\n\n", retorno);
//        }
//        try {
//            Pair<br.inf.portalfiscal.xsd.nfe.procNFe.TNFe, br.inf.portalfiscal.xsd.nfe.procNFe.TProtNFe> pair = getTNFeProc(strEnviNFeAssinado, strRetAutorizacaoNFe);
//            TNfeProc tNfeProc = new TNfeProc();
//            tNfeProc.setVersao(TCONFIG.getNfe().getVersao());
//            tNfeProc.setNFe(pair.getKey());
//            tNfeProc.setProtNFe(pair.getValue());
//
//            ServiceFileSave.saveNfeProcXmlOut(tNfeProc);
//
//            retorno = ServiceXmlUtil.objectToXml(tNfeProc);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        System.out.printf("xmlNFeProc:\n%s\n\n\n", retorno);
//        return retorno;
//    }
//
//    private Pair<br.inf.portalfiscal.xsd.nfe.procNFe.TNFe, br.inf.portalfiscal.xsd.nfe.procNFe.TProtNFe> getTNFeProc(String strEnviNFeAssinado, String strRetAutorizacaoNFe) {
//        Document document = ServiceDocumentFactory.documentFactory(strEnviNFeAssinado);
//        NodeList nodeListNfe = document.getDocumentElement().getElementsByTagName("NFe");
//        NodeList nodeListInfNfe = document.getElementsByTagName("infNFe");
//        br.inf.portalfiscal.xsd.nfe.procNFe.TNFe tnFe = null;
//        br.inf.portalfiscal.xsd.nfe.procNFe.TProtNFe tProtNFe = null;
//        try {
//            for (int i = 0; i < nodeListNfe.getLength(); i++) {
//                Element el = (Element) nodeListInfNfe.item(i);
//                tnFe = ServiceXmlUtil.xmlToObject(ServiceOutputXML.outputXML(nodeListNfe.item(i)), br.inf.portalfiscal.xsd.nfe.procNFe.TNFe.class);
//                tProtNFe = getTProtNFe(strRetAutorizacaoNFe, el.getAttribute("Id"));
//            }
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return new Pair<br.inf.portalfiscal.xsd.nfe.procNFe.TNFe, br.inf.portalfiscal.xsd.nfe.procNFe.TProtNFe>(tnFe, tProtNFe);
//    }
//
//    private br.inf.portalfiscal.xsd.nfe.procNFe.TProtNFe getTProtNFe(String xml, String chaveNFe) {
//        br.inf.portalfiscal.xsd.nfe.procNFe.TProtNFe tProtNFe = null;
//        Document document = ServiceDocumentFactory.documentFactory(xml);
//        NodeList nodeListProtNFe = document.getDocumentElement().getElementsByTagName("protNFe");
//        NodeList nodeListChNFe = document.getElementsByTagName("chNFe");
//        for (int i = 0; i < nodeListProtNFe.getLength(); i++) {
//            Element el = (Element) nodeListChNFe.item(i);
//            if (chaveNFe.contains(el.getTextContent())) {
//                try {
//                    tProtNFe = ServiceXmlUtil.xmlToObject(ServiceOutputXML.outputXML(nodeListProtNFe.item(i)), br.inf.portalfiscal.xsd.nfe.procNFe.TProtNFe.class);
//                } catch (JAXBException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return tProtNFe;
//    }
//
//    private static TelefoneOperadora getOperadoraTelefone(String telefone) {
//        String retURL;
//        if ((retURL = new ServiceBuscaWebService().getObjectWebService(
//                String.format("%s?pass=%s&user=%s&search_number=%s",
//                        "http://consultas.portabilidadecelular.com/painel/consulta_numero.php",
//                        "Tlm487901",
//                        "tlmacedo",
//                        telefone.replaceAll("\\D", ""))
//        )) == null) retURL = "55321";
//        String finalRetURL = retURL;
//        return new TelefoneOperadoraDAO().getAll(TelefoneOperadora.class, null, null, null, null)
//                .stream().filter(operadora -> operadora.getCodWsPortabilidade().contains(finalRetURL))
//                .findFirst()
//                .orElse(new TelefoneOperadoraDAO().getById(TelefoneOperadora.class, 355L));
//    }

}
