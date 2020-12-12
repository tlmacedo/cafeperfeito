package br.com.tlmacedo.cafeperfeito.nfe;

import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.SaidaProdutoNfeDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.NfeCobrancaDuplicataPagamentoMeio;
import br.com.tlmacedo.cafeperfeito.model.enums.RelatorioTipo;
import br.com.tlmacedo.cafeperfeito.model.vo.Empresa;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProdutoNfe;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import br.com.tlmacedo.cafeperfeito.service.ServiceRelatorio;
import br.com.tlmacedo.cafeperfeito.service.ServiceSegundoPlano;
import br.com.tlmacedo.cafeperfeito.service.ServiceUtilJSon;
import br.com.tlmacedo.cafeperfeito.service.alert.Alert_Ok;
import br.com.tlmacedo.cafeperfeito.service.alert.Alert_YesNo;
import br.com.tlmacedo.nfe.service.NFev400;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;
import static br.com.tlmacedo.cafeperfeito.nfe.NfeService.getChaveNfe;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigNFe.MYINFNFE;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class Nfe {

    private NFev400 nFev400;
    private SaidaProdutoNfe saidaProdutoNfe;
    private String xml;

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

    private void variaveisSystemaNFe() {
        System.setProperty("nfeTokenUtilizado", "tokenSafeNet5100.cfg");
        System.setProperty("pathNFeSaveXmlOut", TCONFIG.getPaths().getPathNFeSaveXmlOut());
    }

    public Nfe(SaidaProdutoNfe saidaProdutoNfe, boolean imprimeLote) {
        try {
            variaveisSystemaNFe();
            setSaidaProdutoNfe(saidaProdutoNfe);

            System.out.printf("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
            ServiceUtilJSon.printJsonFromObject(getSaidaProdutoNfe(),
                    String.format("emitir NFe do pedido[%s]", getSaidaProdutoNfe().getSaidaProduto().getId()));
            System.out.printf("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
            System.out.printf("tpAmb: %s\tprintPrompt: %s\n", (MYINFNFE.getMyConfig().getTpAmb().intValue() == 1), true);


            setnFev400(new NFev400((MYINFNFE.getMyConfig().getTpAmb().intValue() == 1), true));
            if (errCertificado())
                return;


            if (getSaidaProdutoNfe().getXmlProtNfe() != null)
                getnFev400().newNFev400_xmlProtNfe(getSaidaProdutoNfe().getXmlProtNfe().toString());
            else if (getSaidaProdutoNfe().getXmlConsRecibo() != null) {
                getnFev400().setXmlAssinado(getSaidaProdutoNfe().getXmlAssinatura().toString());
                getnFev400().newNFev400_xmlConsRecibo(getSaidaProdutoNfe().getXmlConsRecibo().toString());
            } else if (getSaidaProdutoNfe().getXmlAssinatura() != null)
                getnFev400().newNFev400_xmlAssinado(getSaidaProdutoNfe().getXmlAssinatura().toString());
            else
                getnFev400().setNewNFe(new NfeEnviNfeVO(getSaidaProdutoNfe(), imprimeLote).getEnviNfeVO());

            new ServiceSegundoPlano().executaListaTarefas(getnFev400().newTaskNFe(), "NF-e");
            update_MyNfe();
            new ServiceRelatorio().gerar(RelatorioTipo.NFE, getSaidaProdutoNfe().getXmlProtNfe());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void update_MyNfe() {
        try {
            if (getnFev400().getXmlAssinado() != null)
                getSaidaProdutoNfe().setXmlAssinatura(new SerialBlob(getnFev400().getXmlAssinado().getBytes()));

            if (NFev400.getXmlConsRecibo() != null)
                getSaidaProdutoNfe().setXmlConsRecibo(new SerialBlob(NFev400.getXmlConsRecibo().getBytes()));

            if (getnFev400().getXmlProcNfe() != null) {
                getSaidaProdutoNfe().setXmlProtNfe(new SerialBlob(getnFev400().getXmlProcNfe().getBytes()));
                getSaidaProdutoNfe().setDigVal(NFev400.getDigVal());
            }
            setSaidaProdutoNfe(new SaidaProdutoNfeDAO().merger(getSaidaProdutoNfe()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void newSaidaProdutoNfe(boolean imprimeLote) {
        Empresa emissor = new EmpresaDAO().getById(Empresa.class, (long) TCONFIG.getInfLoja().getId().intValue());
        SaidaProduto saidaProduto = getSaidaProdutoNfe().saidaProdutoProperty().getValue();

        getSaidaProdutoNfe().canceladaProperty().setValue(false);
        getSaidaProdutoNfe().statusSefazProperty().setValue(1);
        getSaidaProdutoNfe().naturezaOperacaoProperty().setValue(MYINFNFE.getMyConfig().getNatOp());
        getSaidaProdutoNfe().modeloProperty().setValue(MYINFNFE.getMyConfig().getMod());

        if (getSaidaProdutoNfe().dtHoraSaidaProperty().getValue().toLocalDate()
                .compareTo(getSaidaProdutoNfe().dtHoraEmissaoProperty().getValue().toLocalDate()) <= 0)
            getSaidaProdutoNfe().dtHoraSaidaProperty().setValue(getSaidaProdutoNfe().dtHoraEmissaoProperty()
                    .getValue());

        getSaidaProdutoNfe().destinoOperacaoProperty().setValue(MYINFNFE.getMyConfig().getIdDest());
        getSaidaProdutoNfe().impressaoTpImpProperty().setValue(MYINFNFE.getMyConfig().getTpImp());
        getSaidaProdutoNfe().impressaoTpEmisProperty().setValue(MYINFNFE.getMyConfig().getTpImp());
        getSaidaProdutoNfe().impressaoFinNFeProperty().setValue(MYINFNFE.getMyConfig().getFinNFe());
        getSaidaProdutoNfe().impressaoLtProdutoProperty().setValue(imprimeLote);
        if (saidaProduto.clienteProperty().getValue().ieProperty().getValue().equals(""))
            getSaidaProdutoNfe().consumidorFinalProperty().setValue(1);
        else
            getSaidaProdutoNfe().consumidorFinalProperty().setValue(0);
        getSaidaProdutoNfe().indicadorPresencaProperty().setValue(MYINFNFE.getMyConfig().getIndPres());
        getSaidaProdutoNfe().modFreteProperty().setValue(0);
        getSaidaProdutoNfe().transportadorProperty().setValue(emissor);
        getSaidaProdutoNfe().cobrancaNumeroProperty().setValue(getSaidaProdutoNfe().numeroProperty().getValue().toString());
        getSaidaProdutoNfe().pagamentoIndicadorProperty().setValue(MYINFNFE.getMyConfig().getIndPag());
        getSaidaProdutoNfe().pagamentoMeioProperty().setValue(NfeCobrancaDuplicataPagamentoMeio.OUTROS.getCod());
        getSaidaProdutoNfe().informacaoAdicionalProperty().setValue(
                String.format(MYINFNFE.getMyConfig().getInfAdic(),
                        ServiceMascara.getMoeda(saidaProduto.getSaidaProdutoProdutoList().stream()
                                .map(saidaProdutoProduto -> saidaProdutoProduto.vlrBrutoProperty().getValue()
                                        .subtract(saidaProdutoProduto.vlrDescontoProperty().getValue()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add), 2),
                        (saidaProduto.contasAReceberProperty().getValue().dtVencimentoProperty().getValue() != null)
                                ? String.format(" dt. Venc.: %s",
                                saidaProduto.contasAReceberProperty().getValue()
                                        .dtVencimentoProperty().getValue().format(DTF_DATA))
                                : "",
                        TCONFIG.getInfLoja().getBanco(),
                        TCONFIG.getInfLoja().getAgencia(), TCONFIG.getInfLoja().getContaCorrente())
                        .toUpperCase());
        getSaidaProdutoNfe().digValProperty().setValue(null);
        getSaidaProdutoNfe().xmlAssinaturaProperty().setValue(null);
        getSaidaProdutoNfe().xmlConsReciboProperty().setValue(null);
        getSaidaProdutoNfe().xmlProtNfeProperty().setValue(null);

        getSaidaProdutoNfe().chaveProperty().setValue(getChaveNfe(getSaidaProdutoNfe()));
        try {
            setSaidaProdutoNfe(new SaidaProdutoNfeDAO().merger(getSaidaProdutoNfe()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void addNumeroSerieUltimaNfe() {
        SaidaProdutoNfe nfeTemp;
        int num = 626, serie = 1;
        if ((nfeTemp = new SaidaProdutoNfeDAO().getAll(SaidaProdutoNfe.class, null, "numero DESC")
                .stream().findFirst().orElse(null)) != null) {
            num = nfeTemp.numeroProperty().getValue() + 1;
            serie = nfeTemp.serieProperty().getValue();
        }
        getSaidaProdutoNfe().serieProperty().setValue(serie);
        getSaidaProdutoNfe().numeroProperty().setValue(num);
    }


    /**
     * Begin Getters and Setters
     */


    public NFev400 getnFev400() {
        return nFev400;
    }

    public void setnFev400(NFev400 nFev400) {
        this.nFev400 = nFev400;
    }

    public SaidaProdutoNfe getSaidaProdutoNfe() {
        return saidaProdutoNfe;
    }

    public void setSaidaProdutoNfe(SaidaProdutoNfe saidaProdutoNfe) {
        this.saidaProdutoNfe = saidaProdutoNfe;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }


}
