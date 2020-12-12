package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.RetTranspVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Transp.RetTransp;

public class RetTransp_v400 {

    RetTransp retTransp;
    RetTranspVO retTranspVO = EnviNfe_v400.getNfeVO().getInfNfe().getTransp().getRetTransp();


    public RetTransp_v400() {
        setRetTransp(new RetTransp());

        /**Valor do Serviço
         */
        if (!getRetTranspVO().getvServ().equals(""))
            getRetTransp().setVServ(getRetTranspVO().getvServ());

        /**BC da Retenção do ICMS
         */
        if (!getRetTranspVO().getvBCRet().equals(""))
            getRetTransp().setVBCRet(getRetTranspVO().getvBCRet());

        /**Alíquota da Retenção
         */
        if (!getRetTranspVO().getpICMSRet().equals(""))
            getRetTransp().setPICMSRet(getRetTranspVO().getpICMSRet());

        /**Valor do ICMS Retido
         */
        if (!getRetTranspVO().getvICMSRet().equals(""))
            getRetTransp().setVICMSRet(getRetTranspVO().getvICMSRet());

        /**CFOP
         */
        if (!getRetTranspVO().getCFOP().equals(""))
            getRetTransp().setCFOP(getRetTranspVO().getCFOP());

        /**Código do município de ocorrência do fato gerador do ICMS do transporte
         */
        if (!getRetTranspVO().getcMunFg().equals(""))
            getRetTransp().setCMunFG(getRetTranspVO().getcMunFg());

    }

    /**
     * Begin Getters and Setters
     */

    public RetTransp getRetTransp() {
        return retTransp;
    }

    public void setRetTransp(RetTransp retTransp) {
        this.retTransp = retTransp;
    }

    public RetTranspVO getRetTranspVO() {
        return retTranspVO;
    }

    public void setRetTranspVO(RetTranspVO retTranspVO) {
        this.retTranspVO = retTranspVO;
    }

    /**
     * END Getters and Setters
     */
}
