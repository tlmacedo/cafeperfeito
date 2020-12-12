package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.TotalVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Total;

public class Total_v400 {

    Total total;
    TotalVO totalVO = EnviNfe_v400.getNfeVO().getInfNfe().getTotal();


    /**
     * Grupo W. Total da NF-e
     */
    public Total_v400() {
        setTotal(new Total());

        /**Grupo Totais referentes ao ICMS
         */
        if (getTotalVO().getIcmsTot() != null)
            getTotal().setICMSTot(new IcmsTot_v400().getIcmsTot());
    }


    /**
     * Begin Getters and Setters
     */

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public TotalVO getTotalVO() {
        return totalVO;
    }

    public void setTotalVO(TotalVO totalVO) {
        this.totalVO = totalVO;
    }

    /**
     * Begin Getters and Setters
     */
}
