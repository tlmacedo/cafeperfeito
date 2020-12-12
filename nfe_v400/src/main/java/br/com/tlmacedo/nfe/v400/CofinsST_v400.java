package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.CofinsSTVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.COFINSST;

public class CofinsST_v400 {

    COFINSST cofinsst;
    CofinsSTVO cofinsSTVO = Imposto_v400.getImpostoVO().getCofinsST();


    public CofinsST_v400() {
        setCofinsst(new COFINSST());

        if (!getCofinsSTVO().getvBC().equals(""))
            getCofinsst().setVBC(getCofinsSTVO().getvBC());
        if (!getCofinsSTVO().getpCOFINS().equals(""))
            getCofinsst().setPCOFINS(getCofinsSTVO().getpCOFINS());
        if (!getCofinsSTVO().getqBCProd().equals(""))
            getCofinsst().setQBCProd(getCofinsSTVO().getqBCProd());
        if (!getCofinsSTVO().getvAliqProd().equals(""))
            getCofinsst().setVAliqProd(getCofinsSTVO().getvAliqProd());
        if (!getCofinsSTVO().getvCOFINS().equals(""))
            getCofinsst().setVCOFINS(getCofinsSTVO().getvCOFINS());

    }

    /**
     * Begin Getters and Setters
     */

    public COFINSST getCofinsst() {
        return cofinsst;
    }

    public void setCofinsst(COFINSST cofinsst) {
        this.cofinsst = cofinsst;
    }

    public CofinsSTVO getCofinsSTVO() {
        return cofinsSTVO;
    }

    public void setCofinsSTVO(CofinsSTVO cofinsSTVO) {
        this.cofinsSTVO = cofinsSTVO;
    }

    /**
     * END Getters and Setters
     */
}
