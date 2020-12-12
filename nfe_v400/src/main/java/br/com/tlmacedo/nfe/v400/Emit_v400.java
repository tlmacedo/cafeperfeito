package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.EmitVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Emit;

public class Emit_v400 {


    Emit emit;
    EmitVO emitVO = EnviNfe_v400.getNfeVO().getInfNfe().getEmit();

    /**
     * Grupo C. Identificação do Emitente da Nota Fiscal eletrônica
     */
    public Emit_v400() {
        setEmit(new Emit());

        /**CNPJ do emitente
         * Informar o CNPJ do emitente.
         */
        if (!getEmitVO().getCnpj().equals(""))
            getEmit().setCNPJ(getEmitVO().getCnpj());
        /**
         * Razão Social ou Nome do emitente
         */
        if (!getEmitVO().getxNome().equals(""))
            getEmit().setXNome(getEmitVO().getxNome());
        /**
         * Nome fantasia
         */
        if (!getEmitVO().getxFant().equals(""))
            getEmit().setXFant(getEmitVO().getxFant());

//        if (NFev400.isAmbProducao()) {
//            if (!getEmitVO().getCnpj().equals(""))
//                getEmit().setCNPJ(getEmitVO().getCnpj());
//            if (!getEmitVO().getxNome().equals(""))
//                getEmit().setXNome(getEmitVO().getxNome());
//            if (!getEmitVO().getxFant().equals(""))
//                getEmit().setXFant(getEmitVO().getxFant());
//        } else {
//            getEmit().setCNPJ("99999999000191");
//            getEmit().setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
//        }

        getEmit().setEnderEmit(new Ender_v400(getEmitVO().getEnder()).gettEnderEmi());

        /**Inscrição Estadual do Emitente
         * Informar somente os algarismos, sem os caracteres de formatação (ponto, barra, hífen, etc.).
         */
        if (!getEmitVO().getIE().equals(""))
            getEmit().setIE(getEmitVO().getIE());

        /**IE do Substituto Tributário
         * IE do Substituto Tributário da UF de destino da mercadoria, quando houver a retenção do ICMS ST
         * para a UF de destino.
         */
        if (!getEmitVO().getIEST().equals(""))
            getEmit().setIEST(getEmitVO().getIEST());

        /**Código de Regime Tributário
         * 1=Simples Nacional;
         * 2=Simples Nacional, excesso sublimite de receita bruta;
         * 3=Regime Normal. (v2.0).
         */
        if (!getEmitVO().getCRT().equals(""))
            getEmit().setCRT(getEmitVO().getCRT());

    }


    /**
     * Begin Getters and Setters
     */
    public Emit getEmit() {
        return emit;
    }

    public void setEmit(Emit emit) {
        this.emit = emit;
    }

    public EmitVO getEmitVO() {
        return emitVO;
    }

    public void setEmitVO(EmitVO emitVO) {
        this.emitVO = emitVO;
    }
    /**
     * Begin Getters and Setters
     */
}


