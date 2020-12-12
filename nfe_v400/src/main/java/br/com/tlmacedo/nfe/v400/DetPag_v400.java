package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.DetPagVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Pag.DetPag;

import java.math.RoundingMode;

public class DetPag_v400 {

    DetPag detPag;
    DetPagVO detPagVO = EnviNfe_v400.getNfeVO().getInfNfe().getPag().getDetPag();


    /**
     * Grupo Detalhamento do Pagamento
     */
    public DetPag_v400() {
        setDetPag(new DetPag());

//        if (EnviNfe_v400.getNfeVO().getInfNfe().getTotal().getIcmsTot().getvNF().compareTo(BigDecimal.ZERO) == 0)
//            return;

        /**Indicador da Forma de Pagamento
         */
        if (getDetPagVO().getIndPag() != null)
            getDetPag().setIndPag(getDetPagVO().getIndPag().toString());

        /**Meio de pagamento
         */
        if (getDetPagVO().gettPag() != null)
            getDetPag().setTPag(String.format("%02d", getDetPagVO().gettPag()));

        /**Valor do Pagamento
         */
        getDetPag().setVPag(getDetPagVO().getvPag().setScale(2, RoundingMode.HALF_UP).toString());
    }


    /**
     * Begin Getters and Setters
     */

    public DetPag getDetPag() {
        return detPag;
    }

    public void setDetPag(DetPag detPag) {
        this.detPag = detPag;
    }

    public DetPagVO getDetPagVO() {
        return detPagVO;
    }

    public void setDetPagVO(DetPagVO detPagVO) {
        this.detPagVO = detPagVO;
    }


    /**
     * END Getters and Setters
     */
}
