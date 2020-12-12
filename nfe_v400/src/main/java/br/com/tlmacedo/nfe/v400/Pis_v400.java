package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.*;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISQtde;

public class Pis_v400 {

    PIS pis;
    PisVO pisVO = Imposto_v400.getImpostoVO().getPis();

    PISAliq pisAliq;
    PISQtde pisQtde;
    PISNT pisnt;
    PISOutr pisOutr;


    /**
     * Grupo Q. PIS
     */
    public Pis_v400() {
        setPis(new PIS());

        if (getPisVO().getPisAliq() != null) {
            setPisAliq(new PISAliq());
            PisAliqVO pisAliqVO = getPisVO().getPisAliq();

            if (!pisAliqVO.getCST().equals(""))
                getPisAliq().setCST(pisAliqVO.getCST());
            if (!pisAliqVO.getvBC().equals(""))
                getPisAliq().setVBC(pisAliqVO.getvBC());
            if (!pisAliqVO.getpPIS().equals(""))
                getPisAliq().setPPIS(pisAliqVO.getpPIS());
            if (!pisAliqVO.getvPIS().equals(""))
                getPisAliq().setVPIS(pisAliqVO.getvPIS());

            getPis().setPISAliq(getPisAliq());
        }

        if (getPisVO().getPisQtde() != null) {
            setPisQtde(new PISQtde());
            PisQtdeVO pisQtdeVO = getPisVO().getPisQtde();

            if (!pisQtdeVO.getCST().equals(""))
                getPisQtde().setCST(pisQtdeVO.getCST());
            if (!pisQtdeVO.getqBCProd().equals(""))
                getPisQtde().setQBCProd(pisQtdeVO.getqBCProd());
            if (!pisQtdeVO.getvAliqProd().equals(""))
                getPisQtde().setVAliqProd(pisQtdeVO.getvAliqProd());
            if (!pisQtdeVO.getvPIS().equals(""))
                getPisQtde().setVPIS(pisQtdeVO.getvPIS());


            getPis().setPISQtde(getPisQtde());
        }

        if (getPisVO().getPisNT() != null) {
            setPisnt(new PISNT());
            PisNTVO pisNTVO = getPisVO().getPisNT();

            if (!pisNTVO.getCST().equals(""))
                getPisnt().setCST(pisNTVO.getCST());

            getPis().setPISNT(getPisnt());
        }

        if (getPisVO().getPisOutr() != null) {
            setPisOutr(new PISOutr());
            PisOutrVO pisOutrVO = getPisVO().getPisOutr();

            if (!pisOutrVO.getCST().equals(""))
                getPisOutr().setCST(pisOutrVO.getCST());
            if (!pisOutrVO.getvBC().equals(""))
                getPisOutr().setVBC(pisOutrVO.getvBC());
            if (!pisOutrVO.getpPIS().equals(""))
                getPisOutr().setPPIS(pisOutrVO.getpPIS());
            if (!pisOutrVO.getqBCProd().equals(""))
                getPisOutr().setQBCProd(pisOutrVO.getqBCProd());
            if (!pisOutrVO.getvAliqProd().equals(""))
                getPisOutr().setVAliqProd(pisOutrVO.getvAliqProd());
            if (!pisOutrVO.getvPIS().equals(""))
                getPisOutr().setVPIS(pisOutrVO.getvPIS());

            getPis().setPISOutr(getPisOutr());
        }


    }


    /**
     * Begin Getters and Setters
     */
    public PIS getPis() {
        return pis;
    }

    public void setPis(PIS pis) {
        this.pis = pis;
    }

    public PisVO getPisVO() {
        return pisVO;
    }

    public void setPisVO(PisVO pisVO) {
        this.pisVO = pisVO;
    }

    public PISAliq getPisAliq() {
        return pisAliq;
    }

    public void setPisAliq(PISAliq pisAliq) {
        this.pisAliq = pisAliq;
    }

    public PISNT getPisnt() {
        return pisnt;
    }

    public void setPisnt(PISNT pisnt) {
        this.pisnt = pisnt;
    }

    public PISOutr getPisOutr() {
        return pisOutr;
    }

    public void setPisOutr(PISOutr pisOutr) {
        this.pisOutr = pisOutr;
    }

    public PISQtde getPisQtde() {
        return pisQtde;
    }

    public void setPisQtde(PISQtde pisQtde) {
        this.pisQtde = pisQtde;
    }

    /**
     * Begin Getters and Setters
     */
}
