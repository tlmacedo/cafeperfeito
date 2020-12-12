package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.TranspVO;
import br.com.tlmacedo.nfe.model.vo.VolVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Transp;

public class Transp_v400 {

    Transp transp;
    TranspVO transpVO = EnviNfe_v400.getNfeVO().getInfNfe().getTransp();

    /**
     * Grupo X. Informações do Transporte da NF-e
     */
    public Transp_v400() {
        setTransp(new Transp());

        /**Modalidade do frete
         * 0=Contratação do Frete por conta do Remetente (CIF);
         * 1=Contratação do Frete por conta do Destinatário (FOB);
         * 2=Contratação do Frete por conta de Terceiros;
         * 3=Transporte Próprio por conta do Remetente;
         * 4=Transporte Próprio por conta do Destinatário;
         * 9=Sem Ocorrência de Transporte.
         */
        getTransp().setModFrete(getTranspVO().getModFrete());

        if (getTranspVO().getTransporta() != null)
            getTransp().setTransporta(new Transporta_v400().getTransporta());

        if (getTranspVO().getRetTransp() != null)
            getTransp().setRetTransp(new RetTransp_v400().getRetTransp());

        if (getTranspVO().getVolVOList().size() > 0)
            for (VolVO vol : getTranspVO().getVolVOList())
                getTransp().getVol().add(new Vol_v400(vol).getVol());
    }


    /**
     * Begin Getters and Setters
     */

    public Transp getTransp() {
        return transp;
    }

    public void setTransp(Transp transp) {
        this.transp = transp;
    }

    public TranspVO getTranspVO() {
        return transpVO;
    }

    public void setTranspVO(TranspVO transpVO) {
        this.transpVO = transpVO;
    }

    /**
     * END Getters and Setters
     */
}
