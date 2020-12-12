package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.DupVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Cobr.Dup;

import java.math.RoundingMode;

public class Dup_v400 {

    Dup dup;
    DupVO dupVO;

    public Dup_v400(DupVO dupVO) {
        setDup(new Dup());
        setDupVO(dupVO);

        /**Número da Parcela
         */
        if (!getDupVO().getnDup().equals(""))
            getDup().setNDup(getDupVO().getnDup());

        /**Data de vencimento
         */
        if (!getDupVO().getdVenc().equals(""))
            getDup().setDVenc(getDupVO().getdVenc().toString());

        /**Valor da Parcela
         */
        if (!getDupVO().getvDup().equals(""))
            getDup().setVDup(getDupVO().getvDup().setScale(2, RoundingMode.HALF_UP).toString());


    }

    /**
     * Begin Getters and Setters
     */

    public Dup getDup() {
        return dup;
    }

    public void setDup(Dup dup) {
        this.dup = dup;
    }

    public DupVO getDupVO() {
        return dupVO;
    }

    public void setDupVO(DupVO dupVO) {
        this.dupVO = dupVO;
    }

    /**
     * Begin Getters and Setters
     */
}
