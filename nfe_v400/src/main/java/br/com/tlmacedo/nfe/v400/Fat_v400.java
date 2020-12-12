package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.FatVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Cobr.Fat;

public class Fat_v400 {

    Fat fat;
    FatVO fatVO = EnviNfe_v400.getNfeVO().getInfNfe().getCobr().getFat();

    public Fat_v400() {
        setFat(new Fat());

        /**Número da Fatura
         */
        if (!getFatVO().getnFat().equals(""))
            getFat().setNFat(getFatVO().getnFat());

        /**Valor Original da Fatura
         */
        getFat().setVOrig(getFatVO().getvOrig().setScale(2).toString());

        /**Valor do desconto
         */
        getFat().setVDesc(getFatVO().getvDesc().setScale(2).toString());

        /**Valor Líquido da Fatura
         */
        getFat().setVLiq(getFatVO().getvLiq().setScale(2).toString());
    }

    /**
     * Begin Getters and Setters
     */

    public Fat getFat() {
        return fat;
    }

    public void setFat(Fat fat) {
        this.fat = fat;
    }

    public FatVO getFatVO() {
        return fatVO;
    }

    public void setFatVO(FatVO fatVO) {
        this.fatVO = fatVO;
    }

    /**
     * Begin Getters and Setters
     */
}
