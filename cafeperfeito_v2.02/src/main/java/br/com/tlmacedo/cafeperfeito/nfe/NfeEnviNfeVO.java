package br.com.tlmacedo.cafeperfeito.nfe;

import br.com.cafeperfeito.xsd.config_nfe.config.InfRespTec;
import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.TipoEndereco;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import br.com.tlmacedo.nfe.model.vo.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigNFe.MYINFNFE;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

//import br.com.tlmacedo.cafeperfeito.model.enums.NfeDadosNaturezaOperacao;


public class NfeEnviNfeVO {

    private SaidaProdutoNfe saidaProdutoNfe;
    private boolean imprimirLote;
    private int nItem = 0;
    private DetVO detVO;
    private EnviNfeVO enviNfeVO = new EnviNfeVO();
    private NfeVO nfeVO = new NfeVO();
    private InfNfeVO infNfeVO = new InfNfeVO();
    private IdeVO ideVO = new IdeVO();
    private EmitVO emitVO = new EmitVO();
    private DestVO destVO = new DestVO();
    private EntregaVO entregaVO;
    private List<DetVO> detVOList = new ArrayList<>();
    private TotalVO totalVO = new TotalVO();
    private IcmsTotVO icmsTotVO = new IcmsTotVO();
    private TranspVO transpVO = new TranspVO();
    private CobrVO cobrVO = new CobrVO();
    private FatVO fatVO = new FatVO();
    private PagVO pagVO = new PagVO();
    private DetPagVO detPagVO = new DetPagVO();
    private InfAdicVO infAdicVO = new InfAdicVO();
    private InfRespTecVO infRespTecVO = new InfRespTecVO();


    public NfeEnviNfeVO(SaidaProdutoNfe saidaProdutoNfe, boolean imprimirLote) {
        try {
            setSaidaProdutoNfe(saidaProdutoNfe);
            setImprimirLote(imprimirLote);

            getEnviNfeVO().setVersao(MYINFNFE.getMyConfig().getVersao());
            getEnviNfeVO().setIdLote(NfeService.factoryIdLote(getSaidaProdutoNfe().getSaidaProduto().idProperty().getValue()));
            getEnviNfeVO().setIndSinc(String.valueOf(MYINFNFE.getMyConfig().getIndSinc()));
            getEnviNfeVO().setNfe(getNfeVO());

            getNfeVO().setInfNfe(getInfNfeVO());
            getInfNfeVO().setId(getSaidaProdutoNfe().chaveProperty().getValue());
            getInfNfeVO().setVersao(MYINFNFE.getMyConfig().getVersao());

            getInfNfeVO().setIde(getIdeVO());
            ideVO_write();

            getInfNfeVO().setEmit(getEmitVO());
            emitVO_write();

            getInfNfeVO().setDest(getDestVO());
            destVO_write();

            getInfNfeVO().setDetList(getDetVOList());
            getTotalVO().setIcmsTot(getIcmsTotVO());
            getInfNfeVO().setTotal(getTotalVO());
            detVOList_write();

            getTranspVO().setModFrete(String.valueOf(getSaidaProdutoNfe().modFreteProperty().getValue()));
            getInfNfeVO().setTransp(getTranspVO());
            if (getSaidaProdutoNfe().modFreteProperty().getValue() > 0 && getSaidaProdutoNfe().modeloProperty().getValue() < 9)
                transpVO_write();

            if (getTotalVO().getIcmsTot().getvNF().compareTo(BigDecimal.ZERO) != 0)
                cobrVO_write();

            getPagVO().setDetPag(getDetPagVO());
            getInfNfeVO().setPag(getPagVO());
            detPagVO_write();

            getInfNfeVO().setInfAdic(getInfAdicVO());
            getInfAdicVO().setInfCpl(getSaidaProdutoNfe().informacaoAdicionalProperty().getValue());

            getInfNfeVO().setInfRespTec(getInfRespTecVO());
            infRespTecVO_write();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void ideVO_write() {
        getIdeVO().setcUF(String.valueOf(TCONFIG.getInfLoja().getCUF()));
        getIdeVO().setcNF(getSaidaProdutoNfe().getChave().substring(35, 43));
        getIdeVO().setNatOp(String.format("VENDA %s",
                MYINFNFE.getIde().getNatOps().getNatOp().stream()
                        .filter(natOp -> natOp.getId() == getSaidaProdutoNfe().naturezaOperacaoProperty().getValue())
                        .findFirst().get().getDescricao()
        ));

        getIdeVO().setMod(MYINFNFE.getIde().getMods().getMod().stream()
                .filter(mod -> mod.getId() == getSaidaProdutoNfe().modeloProperty().getValue().intValue())
                .findFirst().get().getDescricao());
        getIdeVO().setSerie(getSaidaProdutoNfe().serieProperty().getValue().toString());
        getIdeVO().setnNF(getSaidaProdutoNfe().numeroProperty().getValue().toString());
        getIdeVO().setDhEmi(getSaidaProdutoNfe().dtHoraEmissaoProperty().getValue());
        getIdeVO().setDhSaiEnt(getSaidaProdutoNfe().dtHoraSaidaProperty().getValue());
        getIdeVO().setTpNF(MYINFNFE.getMyConfig().getTpNF().toString());
        getIdeVO().setIdDest(String.valueOf(getSaidaProdutoNfe().destinoOperacaoProperty().getValue()));
        getIdeVO().setcMunFG(MYINFNFE.getMyConfig().getCMunFG().toString());
        getIdeVO().setTpImp(String.valueOf(getSaidaProdutoNfe().impressaoTpImpProperty().getValue()));
        getIdeVO().setTpEmis(getSaidaProdutoNfe().impressaoTpEmisProperty().getValue().toString());
        getIdeVO().setcDV(getSaidaProdutoNfe().getChave().substring(43, 44));
        getIdeVO().setTpAmb(MYINFNFE.getMyConfig().getTpAmb().toString());
        getIdeVO().setFinNFe(String.valueOf(getSaidaProdutoNfe().impressaoFinNFeProperty().getValue()));
        if (getSaidaProdutoNfe().consumidorFinalProperty().getValue().equals(0)
                && getSaidaProdutoNfe().saidaProdutoProperty().getValue().clienteProperty().getValue().ieProperty().getValue().equals("")) {
            getSaidaProdutoNfe().consumidorFinalProperty().setValue(1);
        }
        getIdeVO().setIndFinal(String.valueOf(getSaidaProdutoNfe().consumidorFinalProperty().getValue()));
        getIdeVO().setIndPres(String.valueOf(getSaidaProdutoNfe().indicadorPresencaProperty().getValue()));
        getIdeVO().setProcEmi(MYINFNFE.getMyConfig().getProcEmi().toString());
        getIdeVO().setVerProc(MYINFNFE.getMyConfig().getVerProc());

    }

    private void emitVO_write() {
        Empresa emissor = new EmpresaDAO().getById(Empresa.class, Long.valueOf(TCONFIG.getInfLoja().getId().intValue()));
        if (emissor.isPessoaJuridica())
            getEmitVO().setCnpj(emissor.cnpjProperty().getValue());
        else
            getEmitVO().setCpf(emissor.cnpjProperty().getValue());

        getEmitVO().setxNome(emissor.getRazao(60));
        getEmitVO().setxFant(emissor.getFantasia(60));
        getEmitVO().setIE(emissor.ieProperty().getValue());
        getEmitVO().setCRT(MYINFNFE.getMyConfig().getCRT().toString());

        EnderVO emitEnderVO = new EnderVO();
        getEmitVO().setEnder(emitEnderVO);
        Endereco emissorEndereco = emissor.getEndereco(TipoEndereco.PRINCIPAL);

        emitEnderVO.setxLgr(emissorEndereco.logradouroProperty().getValue());
        emitEnderVO.setNro(emissorEndereco.numeroProperty().getValue());
        emitEnderVO.setxCpl(emissorEndereco.complementoProperty().getValue());
        emitEnderVO.setxBairro(emissorEndereco.bairroProperty().getValue());
        emitEnderVO.setcMun(emissorEndereco.municipioProperty().getValue().ibge_codigoProperty().getValue());
        emitEnderVO.setxMun(emissorEndereco.municipioProperty().getValue().descricaoProperty().getValue().toUpperCase());
        emitEnderVO.setUF(emissorEndereco.municipioProperty().getValue().ufProperty().getValue().siglaProperty().getValue().toUpperCase());
        emitEnderVO.setCEP(emissorEndereco.cepProperty().getValue());
        emitEnderVO.setcPais(MYINFNFE.getMyConfig().getCPais().toString());
        emitEnderVO.setxPais(MYINFNFE.getMyConfig().getNPais().toUpperCase());
        emitEnderVO.setFone(emissor.getFonePrincipal());
    }

    private void destVO_write() {
        Empresa destinatario = getSaidaProdutoNfe().saidaProdutoProperty().getValue().clienteProperty().getValue();
        if (destinatario == null)
            destinatario = new EmpresaDAO().getById(Empresa.class, Long.valueOf(TCONFIG.getInfLoja().getId().intValue()));
        if (destinatario.isPessoaJuridica())
            getDestVO().setCnpj(destinatario.cnpjProperty().getValue());
        else
            getDestVO().setCpf(destinatario.cnpjProperty().getValue());
        getDestVO().setxNome(destinatario.getxNome(60));
        if (destinatario.ieProperty().getValue().length() > 0)
            getDestVO().setIndIEDest("1");
        else
            getDestVO().setIndIEDest("9");
        getDestVO().setIE(destinatario.ieProperty().getValue());
        if (destinatario.iSuframaProperty().getValue() != null)
            getDestVO().setISUF(destinatario.iSuframaProperty().getValue());
        if (destinatario.iMunicpipalProperty().getValue() != null)
            getDestVO().setIM(destinatario.iMunicpipalProperty().getValue());
        getDestVO().setEmail(destinatario.getEmailPrincipal());

        EnderVO destEnderVO = new EnderVO();
        getDestVO().setEnder(destEnderVO);

        Endereco destinatarioEndereco = destinatario.getEndereco(TipoEndereco.PRINCIPAL);
        destEnderVO.setxLgr(destinatarioEndereco.logradouroProperty().getValue());
        destEnderVO.setNro(destinatarioEndereco.numeroProperty().getValue());
        destEnderVO.setxCpl(destinatarioEndereco.complementoProperty().getValue());
        destEnderVO.setxBairro(destinatarioEndereco.bairroProperty().getValue());
        destEnderVO.setcMun(destinatarioEndereco.municipioProperty().getValue().ibge_codigoProperty().getValue());
        destEnderVO.setxMun(destinatarioEndereco.municipioProperty().getValue().descricaoProperty().getValue().toUpperCase());
        destEnderVO.setUF(destinatarioEndereco.municipioProperty().getValue().ufProperty().getValue().siglaProperty().getValue().toUpperCase());
        destEnderVO.setCEP(destinatarioEndereco.cepProperty().getValue());
        destEnderVO.setcPais(MYINFNFE.getMyConfig().getCPais().toString());
        destEnderVO.setxPais(MYINFNFE.getMyConfig().getNPais().toUpperCase());
        destEnderVO.setFone(destinatario.getFonePrincipal());

        Endereco destinatarioEntrega = destinatario.getEndereco(TipoEndereco.ENTREGA);
        if (destinatarioEntrega != null) {
            setEntregaVO(new EntregaVO());
            getInfNfeVO().setEntrega(getEntregaVO());
            if (destinatario.isPessoaJuridica())
                getEntregaVO().setCnpj(destinatario.cnpjProperty().getValue());
            else
                getEntregaVO().setCpf(destinatario.cnpjProperty().getValue());
            getEntregaVO().setxNome(destinatario.getxNome(60));
            getEntregaVO().setFone(destinatario.getFonePrincipal());

            entregaVO_write(destinatarioEntrega);
        }
    }

    private void entregaVO_write(Endereco destinatarioEntrega) {
        getEntregaVO().setxLgr(destinatarioEntrega.logradouroProperty().getValue());
        getEntregaVO().setNro(destinatarioEntrega.numeroProperty().getValue());
        getEntregaVO().setxCpl(destinatarioEntrega.complementoProperty().getValue());
        getEntregaVO().setxBairro(destinatarioEntrega.bairroProperty().getValue());
        getEntregaVO().setcMun(destinatarioEntrega.municipioProperty().getValue().ibge_codigoProperty().getValue());
        getEntregaVO().setxMun(destinatarioEntrega.municipioProperty().getValue().descricaoProperty().getValue().toUpperCase());
        getEntregaVO().setUF(destinatarioEntrega.municipioProperty().getValue().ufProperty().getValue().siglaProperty().getValue().toUpperCase());
        getEntregaVO().setCEP(destinatarioEntrega.cepProperty().getValue());
        getEntregaVO().setcPais(MYINFNFE.getMyConfig().getCPais().toString());
        getEntregaVO().setxPais(MYINFNFE.getMyConfig().getNPais());
    }

    private void detVOList_write() {
        SaidaProduto saidaProduto = getSaidaProdutoNfe().saidaProdutoProperty().getValue();
        if (saidaProduto.getSaidaProdutoProdutoList().size() <= 0) return;
        saidaProduto.getSaidaProdutoProdutoList().stream()
                .sorted(Comparator.comparing(SaidaProdutoProduto::getProdId)
                        .thenComparing(SaidaProdutoProduto::getLote))
                .collect(Collectors.groupingBy(SaidaProdutoProduto::getProdId))
                .forEach((aLong, saidaProdutoProdutos) -> {
                    System.out.printf("nItens: %s\n", saidaProdutoProdutos.size());
                    for (SaidaProdutoProduto saidProd : saidaProdutoProdutos) {
                        System.out.printf("saidProd: %s\n", saidProd);
                        setDetVO(null);
                        new_ProdVO(saidProd);
                    }
                });
        getIcmsTotVO().setvNF(getIcmsTotVO().getvProd().subtract(getIcmsTotVO().getvDesc()));

    }

    private void new_ProdVO(SaidaProdutoProduto saidProd) {
        ProdVO prodVO = null;
        Produto produto = saidProd.produtoProperty().getValue();
        if (isImprimirLote()) {
            setDetVO(null);
        } else {
            getDetVO();
        }

        if (getDetVO() == null) {
            setDetVO(new DetVO());
            setnItem(getnItem() + 1);
            getDetVO().setnItem(String.valueOf(getnItem()));
            prodVO = new ProdVO();
            getDetVO().setProd(prodVO);
            prodImposto_write(produto);
            getDetVOList().add(getDetVO());
        }
        if (prodVO == null) return;

        prodVO.setcProd(produto.codigoProperty().getValue());
        prodVO.setcEAN(produto.getCEAN());
        String prodString = produto.descricaoProperty().getValue();
        if (isImprimirLote()) {
            prodString = String.format("%s Lt[%s] val.:%s",
                    prodString,
                    saidProd.loteProperty().getValue(),
                    saidProd.dtValidadeProperty().getValue().format(DTF_DATA));
        }
        prodVO.setxProd(prodString);
        prodVO.setNCM(produto.ncmProperty().getValue());
        prodVO.setNVE("");
        prodVO.setCEST(produto.cestProperty().getValue());
        prodVO.setIndEscala("");
        prodVO.setCNPJFab("");
        prodVO.setcBenef("");
        prodVO.setEXTIPI("");
        prodVO.setCFOP("5" + saidProd.codigoCFOPProperty().getValue().getCod());
        prodVO.setuCom(produto.unidadeComercialProperty().getValue().getDescricao());
        BigDecimal qCom = new BigDecimal(saidProd.qtdProperty().getValue());
        prodVO.setqCom(qCom);
        prodVO.setvUnCom(saidProd.vlrUnitarioProperty().getValue());
        prodVO.setvProd(saidProd.vlrBrutoProperty().getValue());
        prodVO.setcEANTrib(produto.getCEAN());
        prodVO.setuTrib(produto.unidadeComercialProperty().getValue().getDescricao());
        prodVO.setqTrib(qCom);
        prodVO.setvUnTrib(saidProd.vlrUnitarioProperty().getValue());
        prodVO.setvFrete(null);
        prodVO.setvSeg(null);
        if (saidProd.vlrDescontoProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
            prodVO.setvDesc(saidProd.vlrDescontoProperty().getValue());
        prodVO.setvOutro(null);
        prodVO.setIndTot("1");

        if (getDetVO().getProd().getvProd() != null)
            getIcmsTotVO().setvProd(getIcmsTotVO().getvProd().add(getDetVO().getProd().getvProd()));
        if (getDetVO().getProd().getvFrete() != null)
            getIcmsTotVO().setvFrete(getIcmsTotVO().getvFrete().add(getDetVO().getProd().getvFrete()));
        if (getDetVO().getProd().getvSeg() != null)
            getIcmsTotVO().setvSeg(getIcmsTotVO().getvSeg().add(getDetVO().getProd().getvSeg()));
        if (getDetVO().getProd().getvDesc() != null)
            getIcmsTotVO().setvDesc(getIcmsTotVO().getvDesc().add(getDetVO().getProd().getvDesc()));

    }

    private void prodImposto_write(Produto produto) {
        ImpostoVO impostoVO = new ImpostoVO();
        getDetVO().setImposto(impostoVO);
        if (produto.fiscalIcmsProperty().getValue() != null) {
            IcmsVO icmsVO = new IcmsVO();
            impostoVO.setIcms(icmsVO);
            switch (produto.fiscalIcmsProperty().getValue().idProperty().getValue().intValue()) {
                case 0:
                    Icms00VO icms00VO = new Icms00VO();
                    icmsVO.setIcms00(icms00VO);
                    icms00VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms00VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
                case 10:
                    Icms10VO icms10VO = new Icms10VO();
                    icmsVO.setIcms10(icms10VO);
                    icms10VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms10VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
                case 20:
                    Icms20VO icms20VO = new Icms20VO();
                    icmsVO.setIcms20(icms20VO);
                    icms20VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms20VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
                case 30:
                    Icms30VO icms30VO = new Icms30VO();
                    icmsVO.setIcms30(icms30VO);
                    icms30VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms30VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
                case 40:
                    Icms40_41_50VO icms404150VO = new Icms40_41_50VO();
                    icmsVO.setIcms40_41_50(icms404150VO);
                    icms404150VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms404150VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
                case 50:
                    Icms51VO icms51VO = new Icms51VO();
                    icmsVO.setIcms51(icms51VO);
                    icms51VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms51VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
                case 60:
                    Icms60VO icms60VO = new Icms60VO();
                    icmsVO.setIcms60(icms60VO);
                    icms60VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms60VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
                case 70:
                    Icms70VO icms70VO = new Icms70VO();
                    icmsVO.setIcms70(icms70VO);
                    icms70VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms70VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
                case 90:
                    Icms90VO icms90VO = new Icms90VO();
                    icmsVO.setIcms90(icms90VO);
                    icms90VO.setOrig(produto.fiscalCstOrigemProperty().getValue().idProperty().getValue().toString());
                    icms90VO.setCST(String.format("%02d", produto.fiscalIcmsProperty().getValue().idProperty().getValue()));
                    break;
            }
        }

        if (produto.fiscalPisProperty().getValue() != null) {
            PisVO pisVO = new PisVO();
            impostoVO.setPis(pisVO);
            PisNTVO pisNTVO = new PisNTVO();
            pisVO.setPisNT(pisNTVO);
            pisNTVO.setCST(String.format("%02d", produto.fiscalPisProperty().getValue().idProperty().getValue()));
        }

        if (produto.fiscalCofinsProperty().getValue() != null) {
            CofinsVO cofinsVO = new CofinsVO();
            impostoVO.setCofins(cofinsVO);
            CofinsNTVO cofinsNTVO = new CofinsNTVO();
            cofinsVO.setCofinsNT(cofinsNTVO);
            cofinsNTVO.setCST(String.format("%02d", produto.fiscalCofinsProperty().getValue().idProperty().getValue()));
        }
    }

    private void transpVO_write() {
        TransportaVO transportaVO = new TransportaVO();
        getTranspVO().setTransporta(transportaVO);

        Empresa transportadora = getSaidaProdutoNfe().getTransportador();
        if (transportadora.isPessoaJuridica())
            transportaVO.setCNPJ(transportadora.getCnpj());
        else
            transportaVO.setCPF(transportadora.getCnpj());
        transportaVO.setxNome(transportadora.getxNome(60));

        if (!transportadora.ieProperty().getValue().equals(""))
            transportaVO.setIE(transportadora.ieProperty().getValue());

        Endereco end = transportadora.getEndereco(TipoEndereco.PRINCIPAL);
        transportaVO.setxEnder(transportadora.getEndereco(end));
        transportaVO.setxMun(end.municipioProperty().getValue().descricaoProperty().getValue().toUpperCase());
        transportaVO.setUF(end.municipioProperty().getValue().ufProperty().getValue().siglaProperty().getValue().toUpperCase());
    }

    private void cobrVO_write() {
        getInfNfeVO().setCobr(getCobrVO());
        getCobrVO().setFat(getFatVO());

        getFatVO().setnFat(!getSaidaProdutoNfe().cobrancaNumeroProperty().getValue().equals("")
                ? getSaidaProdutoNfe().cobrancaNumeroProperty().getValue()
                : getIdeVO().getnNF());
        getFatVO().setvOrig(getIcmsTotVO().getvProd());
        getFatVO().setvDesc(getIcmsTotVO().getvDesc());
        getFatVO().setvLiq(getIcmsTotVO().getvNF());
    }

    private void detPagVO_write() {
        getDetPagVO().setIndPag(getSaidaProdutoNfe().pagamentoIndicadorProperty().getValue());
        getDetPagVO().settPag(getSaidaProdutoNfe().pagamentoMeioProperty().getValue());
        getDetPagVO().setvPag(getIcmsTotVO().getvNF());
    }

    private void infRespTecVO_write() {
        InfRespTec respTec = MYINFNFE.getMyConfig().getInfRespTecs().getInfRespTec().stream()
                .filter(infRespTec -> infRespTec.getId() == UsuarioLogado.getUsuario().getId())
                .findFirst().orElse(MYINFNFE.getMyConfig().getInfRespTecs().getInfRespTec().get(0));
        getInfRespTecVO().setCnpj(respTec.getCnpj());
        getInfRespTecVO().setxContato(respTec.getXContato());
        getInfRespTecVO().setEmail(respTec.getEmail());
        getInfRespTecVO().setFone(respTec.getFone());
    }


    /**
     * Begin Getters and Setters
     */

    public SaidaProdutoNfe getSaidaProdutoNfe() {
        return saidaProdutoNfe;
    }

    public void setSaidaProdutoNfe(SaidaProdutoNfe saidaProdutoNfe) {
        this.saidaProdutoNfe = saidaProdutoNfe;
    }

    public boolean isImprimirLote() {
        return imprimirLote;
    }

    public void setImprimirLote(boolean imprimirLote) {
        this.imprimirLote = imprimirLote;
    }

    public int getnItem() {
        return nItem;
    }

    public void setnItem(int nItem) {
        this.nItem = nItem;
    }

    public DetVO getDetVO() {
        return detVO;
    }

    public void setDetVO(DetVO detVO) {
        this.detVO = detVO;
    }

    public EnviNfeVO getEnviNfeVO() {
        return enviNfeVO;
    }

    public void setEnviNfeVO(EnviNfeVO enviNfeVO) {
        this.enviNfeVO = enviNfeVO;
    }

    public NfeVO getNfeVO() {
        return nfeVO;
    }

    public void setNfeVO(NfeVO nfeVO) {
        this.nfeVO = nfeVO;
    }

    public InfNfeVO getInfNfeVO() {
        return infNfeVO;
    }

    public void setInfNfeVO(InfNfeVO infNfeVO) {
        this.infNfeVO = infNfeVO;
    }

    public IdeVO getIdeVO() {
        return ideVO;
    }

    public void setIdeVO(IdeVO ideVO) {
        this.ideVO = ideVO;
    }

    public EmitVO getEmitVO() {
        return emitVO;
    }

    public void setEmitVO(EmitVO emitVO) {
        this.emitVO = emitVO;
    }

    public DestVO getDestVO() {
        return destVO;
    }

    public void setDestVO(DestVO destVO) {
        this.destVO = destVO;
    }

    public EntregaVO getEntregaVO() {
        return entregaVO;
    }

    public void setEntregaVO(EntregaVO entregaVO) {
        this.entregaVO = entregaVO;
    }

    public List<DetVO> getDetVOList() {
        return detVOList;
    }

    public void setDetVOList(List<DetVO> detVOList) {
        this.detVOList = detVOList;
    }

    public TotalVO getTotalVO() {
        return totalVO;
    }

    public void setTotalVO(TotalVO totalVO) {
        this.totalVO = totalVO;
    }

    public IcmsTotVO getIcmsTotVO() {
        return icmsTotVO;
    }

    public void setIcmsTotVO(IcmsTotVO icmsTotVO) {
        this.icmsTotVO = icmsTotVO;
    }

    public TranspVO getTranspVO() {
        return transpVO;
    }

    public void setTranspVO(TranspVO transpVO) {
        this.transpVO = transpVO;
    }

    public CobrVO getCobrVO() {
        return cobrVO;
    }

    public void setCobrVO(CobrVO cobrVO) {
        this.cobrVO = cobrVO;
    }

    public FatVO getFatVO() {
        return fatVO;
    }

    public void setFatVO(FatVO fatVO) {
        this.fatVO = fatVO;
    }

    public PagVO getPagVO() {
        return pagVO;
    }

    public void setPagVO(PagVO pagVO) {
        this.pagVO = pagVO;
    }

    public DetPagVO getDetPagVO() {
        return detPagVO;
    }

    public void setDetPagVO(DetPagVO detPagVO) {
        this.detPagVO = detPagVO;
    }

    public InfAdicVO getInfAdicVO() {
        return infAdicVO;
    }

    public void setInfAdicVO(InfAdicVO infAdicVO) {
        this.infAdicVO = infAdicVO;
    }

    public InfRespTecVO getInfRespTecVO() {
        return infRespTecVO;
    }

    public void setInfRespTecVO(InfRespTecVO infRespTecVO) {
        this.infRespTecVO = infRespTecVO;
    }

    /**
     * END Getters and Setters
     */


}
