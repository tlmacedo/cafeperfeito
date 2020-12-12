package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.*;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.*;

public class Icms_v400 {

    private ICMS icms;
    private IcmsVO icmsVO = Imposto_v400.getImpostoVO().getIcms();

    private ICMS00 icms00;
    private ICMS10 icms10;
    private ICMS20 icms20;
    private ICMS30 icms30;
    private ICMS40 icms40;
    private ICMS51 icms51;
    private ICMS60 icms60;
    private ICMS70 icms70;
    private ICMS90 icms90;

    public Icms_v400() {
        setIcms(new ICMS());

        /**
         * Grupo N. Grupo Tributação do ICMS= 00
         */
        if (getIcmsVO().getIcms00() != null) {
            setIcms00(new ICMS00());
            Icms00VO icms00VO = getIcmsVO().getIcms00();
            if (!icms00VO.getOrig().equals(""))
                getIcms00().setOrig(icms00VO.getOrig());
            if (!icms00VO.getCST().equals(""))
                getIcms00().setCST(icms00VO.getCST());
            if (!icms00VO.getModBC().equals(""))
                getIcms00().setModBC(icms00VO.getModBC());
            if (!icms00VO.getvBC().equals(""))
                getIcms00().setVBC(icms00VO.getvBC());
            if (!icms00VO.getpICMS().equals(""))
                getIcms00().setPICMS(icms00VO.getpICMS());
            if (!icms00VO.getvICMS().equals(""))
                getIcms00().setVICMS(icms00VO.getvICMS());
            if (!icms00VO.getpFCP().equals(""))
                getIcms00().setPFCP(getIcms00().getPFCP());
            if (!icms00VO.getvFCP().equals(""))
                getIcms00().setVFCP(getIcms00().getVFCP());
            getIcms().setICMS00(getIcms00());
        }

        /**
         * Grupo N. Grupo Tributação do ICMS= 10
         */
        else if (getIcmsVO().getIcms10() != null) {
            setIcms10(new ICMS10());
            Icms10VO icms10VO = getIcmsVO().getIcms10();
            if (!icms10VO.getOrig().equals(""))
                getIcms10().setOrig(icms10VO.getOrig());
            if (!icms10VO.getCST().equals(""))
                getIcms10().setCST(icms10VO.getCST());
            if (!icms10VO.getModBC().equals(""))
                getIcms10().setModBC(icms10VO.getModBC());
            if (!icms10VO.getvBC().equals(""))
                getIcms10().setVBC(icms10VO.getvBC());
            if (!icms10VO.getpICMS().equals(""))
                getIcms10().setPICMS(icms10VO.getpICMS());
            if (!icms10VO.getvICMS().equals(""))
                getIcms10().setVICMS(icms10VO.getvICMS());

            if (!icms10VO.getvBCFCP().equals(""))
                getIcms10().setVBCFCP(icms10VO.getvBCFCP());
            if (!icms10VO.getpFCP().equals(""))
                getIcms10().setPFCP(icms10VO.getpFCP());
            if (!icms10VO.getvFCP().equals(""))
                getIcms10().setVFCP(icms10VO.getvFCP());
            if (!icms10VO.getModBCST().equals(""))
                getIcms10().setModBCST(icms10VO.getModBCST());
            if (!icms10VO.getpMVAST().equals(""))
                getIcms10().setPMVAST(icms10VO.getpMVAST());
            if (!icms10VO.getpRedBCST().equals(""))
                getIcms10().setPRedBCST(icms10VO.getpRedBCST());
            if (!icms10VO.getvBCST().equals(""))
                getIcms10().setVBCST(icms10VO.getvBCST());
            if (!icms10VO.getpICMSST().equals(""))
                getIcms10().setPICMSST(icms10VO.getpICMSST());
            if (!icms10VO.getvICMSST().equals(""))
                getIcms10().setVICMSST(icms10VO.getvICMSST());
            if (!icms10VO.getvBCFCPST().equals(""))
                getIcms10().setVBCFCPST(icms10VO.getvBCFCPST());
            if (!icms10VO.getpFCPST().equals(""))
                getIcms10().setPFCPST(icms10VO.getpFCPST());
            if (!icms10VO.getvFCPST().equals(""))
                getIcms10().setVFCPST(icms10VO.getvFCPST());
            getIcms().setICMS10(getIcms10());
        }

        /**
         * Grupo N. Grupo Tributação do ICMS= 20
         */
        else if (getIcmsVO().getIcms20() != null) {
            Icms20VO icms20VO = getIcmsVO().getIcms20();
            setIcms20(new ICMS20());

            if (!icms20VO.getOrig().equals(""))
                getIcms20().setOrig(icms20VO.getOrig());
            if (!icms20VO.getCST().equals(""))
                getIcms20().setCST(icms20VO.getCST());
            if (!icms20VO.getModBC().equals(""))
                getIcms20().setModBC(icms20VO.getModBC());
            if (!icms20VO.getpRedBC().equals(""))
                getIcms20().setPRedBC(icms20VO.getpRedBC());
            if (!icms20VO.getvBC().equals(""))
                getIcms20().setVBC(icms20VO.getvBC());
            if (!icms20VO.getpICMS().equals(""))
                getIcms20().setPICMS(icms20VO.getpICMS());
            if (!icms20VO.getvICMS().equals(""))
                getIcms20().setVICMS(icms20VO.getvICMS());

            if (!icms20VO.getvBCFCP().equals(""))
                getIcms20().setVBCFCP(icms20VO.getvBCFCP());
            if (!icms20VO.getpFCP().equals(""))
                getIcms20().setPFCP(icms20VO.getpFCP());
            if (!icms20VO.getvFCP().equals(""))
                getIcms20().setVFCP(icms20VO.getvFCP());
            if (!icms20VO.getvICMSDeson().equals(""))
                getIcms20().setVICMSDeson(icms20VO.getvICMSDeson());
            if (!icms20VO.getMotDesICMS().equals(""))
                getIcms20().setMotDesICMS(icms20VO.getMotDesICMS());

            getIcms().setICMS20(getIcms20());
        }

        /**
         * Grupo N. Grupo Tributação do ICMS= 30
         */
        else if (getIcmsVO().getIcms30() != null) {
            Icms30VO icms30VO = getIcmsVO().getIcms30();
            setIcms30(new ICMS30());

            if (!icms30VO.getOrig().equals(""))
                getIcms30().setOrig(icms30VO.getOrig());
            if (!icms30VO.getCST().equals(""))
                getIcms30().setCST(icms30VO.getCST());
            if (!icms30VO.getModBCST().equals(""))
                getIcms30().setModBCST(icms30VO.getModBCST());
            if (!icms30VO.getpMVAST().equals(""))
                getIcms30().setPMVAST(icms30VO.getpMVAST());
            if (!icms30VO.getpRedBCST().equals(""))
                getIcms30().setPRedBCST(icms30VO.getpRedBCST());
            if (!icms30VO.getvBCST().equals(""))
                getIcms30().setVBCST(icms30VO.getvBCST());
            if (!icms30VO.getpICMSST().equals(""))
                getIcms30().setPICMSST(icms30VO.getpICMSST());
            if (!icms30VO.getvICMSST().equals(""))
                getIcms30().setVICMSST(icms30VO.getvICMSST());
            if (!icms30VO.getvBCFCPST().equals(""))
                getIcms30().setVBCFCPST(icms30VO.getvBCFCPST());
            if (!icms30VO.getpFCPST().equals(""))
                getIcms30().setPFCPST(icms30VO.getpFCPST());
            if (!icms30VO.getvFCPST().equals(""))
                getIcms30().setVFCPST(icms30VO.getvFCPST());
            if (!icms30VO.getvICMSDeson().equals(""))
                getIcms30().setVICMSDeson(icms30VO.getvICMSDeson());
            if (!icms30VO.getMotDesICMS().equals(""))
                getIcms30().setMotDesICMS(icms30VO.getMotDesICMS());


            getIcms().setICMS30(getIcms30());
        }

        /**
         * Grupo N. Grupo Tributação do ICMS= 40
         */
        else if (getIcmsVO().getIcms40_41_50() != null) {
            Icms40_41_50VO icms404150VO = getIcmsVO().getIcms40_41_50();
            setIcms40(new ICMS40());

            if (!icms404150VO.getOrig().equals(""))
                getIcms40().setOrig(icms404150VO.getOrig());
            if (!icms404150VO.getCST().equals(""))
                getIcms40().setCST(icms404150VO.getCST());
            if (!icms404150VO.getvICMSDeson().equals(""))
                getIcms40().setVICMSDeson(icms404150VO.getvICMSDeson());
            if (!icms404150VO.getMotDesICMS().equals(""))
                getIcms40().setMotDesICMS(icms404150VO.getMotDesICMS());

            getIcms().setICMS40(getIcms40());
        }

        /**
         * Grupo N. Grupo Tributação do ICMS= 51
         */
        else if (getIcmsVO().getIcms51() != null) {
            Icms51VO icms51VO = getIcmsVO().getIcms51();
            setIcms51(new ICMS51());

            if (!icms51VO.getOrig().equals(""))
                getIcms51().setOrig(icms51VO.getOrig());
            if (!icms51VO.getCST().equals(""))
                getIcms51().setCST(icms51VO.getCST());
            if (!icms51VO.getModBC().equals(""))
                getIcms51().setModBC(icms51VO.getModBC());
            if (!icms51VO.getpRedBC().equals(""))
                getIcms51().setPRedBC(icms51VO.getpRedBC());
            if (!icms51VO.getvBC().equals(""))
                getIcms51().setVBC(icms51VO.getvBC());
            if (!icms51VO.getpICMS().equals(""))
                getIcms51().setPICMS(icms51VO.getpICMS());
            if (!icms51VO.getvICMSOp().equals(""))
                getIcms51().setVICMSOp(icms51VO.getvICMSOp());
            if (!icms51VO.getpDif().equals(""))
                getIcms51().setPDif(icms51VO.getpDif());
            if (!icms51VO.getvICMSDif().equals(""))
                getIcms51().setVICMSDif(icms51VO.getvICMSDif());
            if (!icms51VO.getvICMS().equals(""))
                getIcms51().setVICMS(icms51VO.getvICMS());
            if (!icms51VO.getvBCFCP().equals(""))
                getIcms51().setVBCFCP(icms51VO.getvBCFCP());
            if (!icms51VO.getpFCP().equals(""))
                getIcms51().setPFCP(icms51VO.getpFCP());
            if (!icms51VO.getvFCP().equals(""))
                getIcms51().setVFCP(icms51VO.getvFCP());

            getIcms().setICMS51(getIcms51());
        }

        /**
         * Grupo N. Grupo Tributação do ICMS= 60
         */
        else if (getIcmsVO().getIcms60() != null) {
            Icms60VO icms60VO = getIcmsVO().getIcms60();
            setIcms60(new ICMS60());
            if (!icms60VO.getOrig().equals(""))
                getIcms60().setOrig(icms60VO.getOrig());
            if (!icms60VO.getCST().equals(""))
                getIcms60().setCST(icms60VO.getCST());
            if (!icms60VO.getvBCSTRet().equals(""))
                getIcms60().setVBCSTRet(icms60VO.getvBCSTRet());
            if (!icms60VO.getpST().equals(""))
                getIcms60().setPST(icms60VO.getpST());
            if (!icms60VO.getvICMSSubstituto().equals(""))
                getIcms60().setVICMSSubstituto(icms60VO.getvICMSSubstituto());
            if (!icms60VO.getvICMSSTRet().equals(""))
                getIcms60().setVICMSSTRet(icms60VO.getvICMSSTRet());
            if (!icms60VO.getvBCFCPSTRet().equals(""))
                getIcms60().setVBCFCPSTRet(icms60VO.getvBCFCPSTRet());
            if (!icms60VO.getpFCPSTRet().equals(""))
                getIcms60().setPFCPSTRet(icms60VO.getpFCPSTRet());
            if (!icms60VO.getvFCPSTRet().equals(""))
                getIcms60().setVFCPSTRet(icms60VO.getvFCPSTRet());
            if (!icms60VO.getpRedBCEfet().equals(""))
                getIcms60().setPRedBCEfet(icms60VO.getpRedBCEfet());
            if (!icms60VO.getvBCEfet().equals(""))
                getIcms60().setVBCEfet(icms60VO.getvBCEfet());
            if (!icms60VO.getpICMSEfet().equals(""))
                getIcms60().setPICMSEfet(icms60VO.getpICMSEfet());
            if (!icms60VO.getvICMSEfet().equals(""))
                getIcms60().setVICMSEfet(icms60VO.getvICMSEfet());

            getIcms().setICMS60(getIcms60());
        }

        /**
         * Grupo N. Grupo Tributação do ICMS= 70
         */
        else if (getIcmsVO().getIcms70() != null) {
            Icms70VO icms70VO = getIcmsVO().getIcms70();
            setIcms70(new ICMS70());

            if (!icms70VO.getOrig().equals(""))
                getIcms70().setOrig(icms70VO.getOrig());
            if (!icms70VO.getCST().equals(""))
                getIcms70().setCST(icms70VO.getCST());
            if (!icms70VO.getModBC().equals(""))
                getIcms70().setModBC(icms70VO.getModBC());
            if (!icms70VO.getpRedBC().equals(""))
                getIcms70().setPRedBC(icms70VO.getpRedBC());
            if (!icms70VO.getvBC().equals(""))
                getIcms70().setVBC(icms70VO.getvBC());
            if (!icms70VO.getpICMS().equals(""))
                getIcms70().setPICMS(icms70VO.getpICMS());
            if (!icms70VO.getvICMS().equals(""))
                getIcms70().setVICMS(icms70VO.getvICMS());
            if (!icms70VO.getvBCFCP().equals(""))
                getIcms70().setVBCFCP(icms70VO.getvBCFCP());
            if (!icms70VO.getpFCP().equals(""))
                getIcms70().setPFCP(icms70VO.getpFCP());
            if (!icms70VO.getvFCP().equals(""))
                getIcms70().setVFCP(icms70VO.getvFCP());
            if (!icms70VO.getModBCST().equals(""))
                getIcms70().setModBCST(icms70VO.getModBCST());
            if (!icms70VO.getpMVAST().equals(""))
                getIcms70().setPMVAST(icms70VO.getpMVAST());
            if (!icms70VO.getpRedBCST().equals(""))
                getIcms70().setPRedBCST(icms70VO.getpRedBCST());
            if (!icms70VO.getvBCST().equals(""))
                getIcms70().setVBCST(icms70VO.getvBCST());
            if (!icms70VO.getpICMSST().equals(""))
                getIcms70().setPICMSST(icms70VO.getpICMSST());
            if (!icms70VO.getvICMSST().equals(""))
                getIcms70().setVICMSST(icms70VO.getvICMSST());
            if (!icms70VO.getvBCFCPST().equals(""))
                getIcms70().setVBCFCPST(icms70VO.getvBCFCPST());
            if (!icms70VO.getpFCPST().equals(""))
                getIcms70().setPFCPST(icms70VO.getpFCPST());
            if (!icms70VO.getvFCPST().equals(""))
                getIcms70().setVFCPST(icms70VO.getvFCPST());
            if (!icms70VO.getvICMSDeson().equals(""))
                getIcms70().setVICMSDeson(icms70VO.getvICMSDeson());
            if (!icms70VO.getMotDesICMS().equals(""))
                getIcms70().setMotDesICMS(icms70VO.getMotDesICMS());

            getIcms().setICMS70(getIcms70());
        }

        /**
         * Grupo N. Grupo Tributação do ICMS= 90
         */
        else if (getIcmsVO().getIcms90() != null) {
            Icms90VO icms90VO = getIcmsVO().getIcms90();
            setIcms90(new ICMS90());

            if (!icms90VO.getOrig().equals(""))
                getIcms90().setOrig(icms90VO.getOrig());
            if (!icms90VO.getCST().equals(""))
                getIcms90().setCST(icms90VO.getCST());
            if (!icms90VO.getModBC().equals(""))
                getIcms90().setModBC(icms90VO.getModBC());
            if (!icms90VO.getvBC().equals(""))
                getIcms90().setVBC(icms90VO.getvBC());
            if (!icms90VO.getpRedBC().equals(""))
                getIcms90().setPRedBC(icms90VO.getpRedBC());
            if (!icms90VO.getpICMS().equals(""))
                getIcms90().setPICMS(icms90VO.getpICMS());
            if (!icms90VO.getvICMS().equals(""))
                getIcms90().setVICMS(icms90VO.getvICMS());
            if (!icms90VO.getvBCFCP().equals(""))
                getIcms90().setVBCFCP(icms90VO.getvBCFCP());
            if (!icms90VO.getpFCP().equals(""))
                getIcms90().setPFCP(icms90VO.getpFCP());
            if (!icms90VO.getvFCP().equals(""))
                getIcms90().setVFCP(icms90VO.getvFCP());
            if (!icms90VO.getModBCST().equals(""))
                getIcms90().setModBCST(icms90VO.getModBCST());
            if (!icms90VO.getpMVAST().equals(""))
                getIcms90().setPMVAST(icms90VO.getpMVAST());
            if (!icms90VO.getpRedBCST().equals(""))
                getIcms90().setPRedBCST(icms90VO.getpRedBCST());
            if (!icms90VO.getvBCST().equals(""))
                getIcms90().setVBCST(icms90VO.getvBCST());
            if (!icms90VO.getpICMSST().equals(""))
                getIcms90().setPICMSST(icms90VO.getpICMSST());
            if (!icms90VO.getvICMSST().equals(""))
                getIcms90().setVICMSST(icms90VO.getvICMSST());
            if (!icms90VO.getvBCFCPST().equals(""))
                getIcms90().setVBCFCPST(icms90VO.getvBCFCPST());
            if (!icms90VO.getpFCPST().equals(""))
                getIcms90().setPFCPST(icms90VO.getpFCPST());
            if (!icms90VO.getvFCPST().equals(""))
                getIcms90().setVFCPST(icms90VO.getvFCPST());
            if (!icms90VO.getvICMSDeson().equals(""))
                getIcms90().setVICMSDeson(icms90VO.getvICMSDeson());
            if (!icms90VO.getMotDesICMS().equals(""))
                getIcms90().setMotDesICMS(icms90VO.getMotDesICMS());

            getIcms().setICMS90(getIcms90());
        }
    }

    /**
     * Begin Getters and Setters
     */

    public ICMS getIcms() {
        return icms;
    }

    public void setIcms(ICMS icms) {
        this.icms = icms;
    }

    public IcmsVO getIcmsVO() {
        return icmsVO;
    }

    public void setIcmsVO(IcmsVO icmsVO) {
        this.icmsVO = icmsVO;
    }

    public ICMS00 getIcms00() {
        return icms00;
    }

    public void setIcms00(ICMS00 icms00) {
        this.icms00 = icms00;
    }

    public ICMS10 getIcms10() {
        return icms10;
    }

    public void setIcms10(ICMS10 icms10) {
        this.icms10 = icms10;
    }

    public ICMS20 getIcms20() {
        return icms20;
    }

    public void setIcms20(ICMS20 icms20) {
        this.icms20 = icms20;
    }

    public ICMS30 getIcms30() {
        return icms30;
    }

    public void setIcms30(ICMS30 icms30) {
        this.icms30 = icms30;
    }

    public ICMS40 getIcms40() {
        return icms40;
    }

    public void setIcms40(ICMS40 icms40) {
        this.icms40 = icms40;
    }

    public ICMS51 getIcms51() {
        return icms51;
    }

    public void setIcms51(ICMS51 icms51) {
        this.icms51 = icms51;
    }

    public ICMS60 getIcms60() {
        return icms60;
    }

    public void setIcms60(ICMS60 icms60) {
        this.icms60 = icms60;
    }

    public ICMS70 getIcms70() {
        return icms70;
    }

    public void setIcms70(ICMS70 icms70) {
        this.icms70 = icms70;
    }

    public ICMS90 getIcms90() {
        return icms90;
    }

    public void setIcms90(ICMS90 icms90) {
        this.icms90 = icms90;
    }
    /**
     * END Getters and Setters
     */
}
