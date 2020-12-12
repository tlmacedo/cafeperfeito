package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class RetTranspVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String vServ = "";
    private String vBCRet = "";
    private String pICMSRet = "";
    private String vICMSRet = "";
    private String CFOP = "";
    private String cMunFg = "";

    public RetTranspVO() {
    }

    public String getvServ() {
        return vServ;
    }

    public void setvServ(String vServ) {
        this.vServ = vServ;
    }

    public String getvBCRet() {
        return vBCRet;
    }

    public void setvBCRet(String vBCRet) {
        this.vBCRet = vBCRet;
    }

    public String getpICMSRet() {
        return pICMSRet;
    }

    public void setpICMSRet(String pICMSRet) {
        this.pICMSRet = pICMSRet;
    }

    public String getvICMSRet() {
        return vICMSRet;
    }

    public void setvICMSRet(String vICMSRet) {
        this.vICMSRet = vICMSRet;
    }

    public String getCFOP() {
        return CFOP;
    }

    public void setCFOP(String CFOP) {
        this.CFOP = CFOP;
    }

    public String getcMunFg() {
        return cMunFg;
    }

    public void setcMunFg(String cMunFg) {
        this.cMunFg = cMunFg;
    }

    @Override
    public String toString() {
        return "RetTranspVO{" +
                "vServ='" + vServ + '\'' +
                ", vBCRet='" + vBCRet + '\'' +
                ", pICMSRet='" + pICMSRet + '\'' +
                ", vICMSRet='" + vICMSRet + '\'' +
                ", CFOP='" + CFOP + '\'' +
                ", cMunFg='" + cMunFg + '\'' +
                '}';
    }
}
