package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.*;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde;


public class Cofins_v400 {

    COFINS cofins;
    CofinsVO cofinsVO = Imposto_v400.getImpostoVO().getCofins();

    COFINSAliq cofinsAliq;
    COFINSNT cofinsnt;
    COFINSOutr cofinsOutr;
    COFINSQtde cofinsQtde;


    /**
     * Grupo S. COFINS
     */
    public Cofins_v400() {
        setCofins(new COFINS());

        if (getCofinsVO().getCofinsAliq() != null) {
            setCofinsAliq(new COFINSAliq());
            CofinsAliqVO cofinsAliqVO = getCofinsVO().getCofinsAliq();

            if (!cofinsAliqVO.getCST().equals(""))
                getCofinsAliq().setCST(cofinsAliqVO.getCST());
            if (!cofinsAliqVO.getCST().equals(""))
                getCofinsAliq().setVBC(cofinsAliqVO.getvBC());
            if (!cofinsAliqVO.getCST().equals(""))
                getCofinsAliq().setPCOFINS(cofinsAliqVO.getpCOFINS());
            if (!cofinsAliqVO.getCST().equals(""))
                getCofinsAliq().setVCOFINS(cofinsAliqVO.getvCOFINS());

            getCofins().setCOFINSAliq(getCofinsAliq());
        }

        if (getCofinsVO().getCofinsQtde() != null) {
            setCofinsQtde(new COFINSQtde());
            CofinsQtdeVO cofinsQtdeVO = getCofinsVO().getCofinsQtde();

            if (!cofinsQtdeVO.getCST().equals(""))
                getCofinsQtde().setCST(cofinsQtdeVO.getCST());
            if (!cofinsQtdeVO.getqBCProd().equals(""))
                getCofinsQtde().setQBCProd(cofinsQtdeVO.getqBCProd());
            if (!cofinsQtdeVO.getvAliqProd().equals(""))
                getCofinsQtde().setVAliqProd(cofinsQtdeVO.getvAliqProd());
            if (!cofinsQtdeVO.getvCOFINS().equals(""))
                getCofinsQtde().setVCOFINS(cofinsQtdeVO.getvCOFINS());

            getCofins().setCOFINSQtde(getCofinsQtde());
        }

        if (getCofinsVO().getCofinsNT() != null) {
            setCofinsnt(new COFINSNT());
            CofinsNTVO cofinsNTVO = getCofinsVO().getCofinsNT();

            if (!cofinsNTVO.getCST().equals(""))
                getCofinsnt().setCST(cofinsNTVO.getCST());

            getCofins().setCOFINSNT(getCofinsnt());
        }

        if (getCofinsVO().getCofinsOutr() != null) {
            setCofinsOutr(new COFINSOutr());
            CofinsOutrVO cofinsOutrVO = getCofinsVO().getCofinsOutr();

            if (!cofinsOutrVO.getCST().equals(""))
                getCofinsOutr().setCST(cofinsOutrVO.getCST());
            if (!cofinsOutrVO.getvBC().equals(""))
                getCofinsOutr().setVBC(cofinsOutrVO.getvBC());
            if (!cofinsOutrVO.getpCOFINS().equals(""))
                getCofinsOutr().setPCOFINS(cofinsOutrVO.getpCOFINS());
            if (!cofinsOutrVO.getqBCProd().equals(""))
                getCofinsOutr().setQBCProd(cofinsOutrVO.getqBCProd());
            if (!cofinsOutrVO.getvAliqProd().equals(""))
                getCofinsOutr().setVAliqProd(cofinsOutrVO.getvAliqProd());
            if (!cofinsOutrVO.getvCOFINS().equals(""))
                getCofinsOutr().setVCOFINS(cofinsOutrVO.getvCOFINS());

            getCofins().setCOFINSOutr(getCofinsOutr());
        }


    }


    /**
     * Begin Getters and Setters
     */

    public COFINS getCofins() {
        return cofins;
    }

    public void setCofins(COFINS cofins) {
        this.cofins = cofins;
    }

    public CofinsVO getCofinsVO() {
        return cofinsVO;
    }

    public void setCofinsVO(CofinsVO cofinsVO) {
        this.cofinsVO = cofinsVO;
    }

    public COFINSAliq getCofinsAliq() {
        return cofinsAliq;
    }

    public void setCofinsAliq(COFINSAliq cofinsAliq) {
        this.cofinsAliq = cofinsAliq;
    }

    public COFINSNT getCofinsnt() {
        return cofinsnt;
    }

    public void setCofinsnt(COFINSNT cofinsnt) {
        this.cofinsnt = cofinsnt;
    }

    public COFINSOutr getCofinsOutr() {
        return cofinsOutr;
    }

    public void setCofinsOutr(COFINSOutr cofinsOutr) {
        this.cofinsOutr = cofinsOutr;
    }

    public COFINSQtde getCofinsQtde() {
        return cofinsQtde;
    }

    public void setCofinsQtde(COFINSQtde cofinsQtde) {
        this.cofinsQtde = cofinsQtde;
    }

    /**
     * END Getters and Setters
     */
}
