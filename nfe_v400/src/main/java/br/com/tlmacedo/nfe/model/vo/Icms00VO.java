package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class Icms00VO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orig = "";
    private String CST = "";
    private String modBC = "";
    private String vBC = "";
    private String pICMS = "";
    private String vICMS = "";
    private String pFCP = "";
    private String vFCP = "";

    public Icms00VO() {
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getCST() {
        return CST;
    }

    public void setCST(String CST) {
        this.CST = CST;
    }

    public String getModBC() {
        return modBC;
    }

    public void setModBC(String modBC) {
        this.modBC = modBC;
    }

    public String getvBC() {
        return vBC;
    }

    public void setvBC(String vBC) {
        this.vBC = vBC;
    }

    public String getpICMS() {
        return pICMS;
    }

    public void setpICMS(String pICMS) {
        this.pICMS = pICMS;
    }

    public String getvICMS() {
        return vICMS;
    }

    public void setvICMS(String vICMS) {
        this.vICMS = vICMS;
    }

    public String getpFCP() {
        return pFCP;
    }

    public void setpFCP(String pFCP) {
        this.pFCP = pFCP;
    }

    public String getvFCP() {
        return vFCP;
    }

    public void setvFCP(String vFCP) {
        this.vFCP = vFCP;
    }

    @Override
    public String toString() {
        return "Icms00VO{" +
                "orig='" + orig + '\'' +
                ", CST='" + CST + '\'' +
                ", modBC='" + modBC + '\'' +
                ", vBC='" + vBC + '\'' +
                ", pICMS='" + pICMS + '\'' +
                ", vICMS='" + vICMS + '\'' +
                ", pFCP='" + pFCP + '\'' +
                ", vFCP='" + vFCP + '\'' +
                '}';
    }
}
