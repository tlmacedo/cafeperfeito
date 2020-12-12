package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.PagVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Pag;

public class Pag_v400 {

    Pag pag;
    PagVO pagVO = EnviNfe_v400.getNfeVO().getInfNfe().getPag();

    /**
     * Grupo YA. Informações de Pagamento
     */
    public Pag_v400() {
        setPag(new Pag());

        /**Grupo Detalhamento do Pagamento
         */
        if (getPagVO().getDetPag() != null)
            getPag().getDetPag().add(new DetPag_v400().getDetPag());
    }


    /**
     * Begin Getters and Setters
     */

    public Pag getPag() {
        return pag;
    }

    public void setPag(Pag pag) {
        this.pag = pag;
    }

    public PagVO getPagVO() {
        return pagVO;
    }

    public void setPagVO(PagVO pagVO) {
        this.pagVO = pagVO;
    }

    /**
     * Begin Getters and Setters
     */
}
