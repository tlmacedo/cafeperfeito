package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class Icms51VO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orig = "";
    private String CST = "";
    private String modBC = "";
    private String pRedBC = "";
    private String vBC = "";
    private String pICMS = "";
    private String vICMSOp = "";
    private String pDif = "";
    private String vICMSDif = "";
    private String vICMS = "";
    private String vBCFCP = "";
    private String pFCP = "";
    private String vFCP = "";

    public Icms51VO() {
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

    public String getpRedBC() {
        return pRedBC;
    }

    public void setpRedBC(String pRedBC) {
        this.pRedBC = pRedBC;
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

    public String getvICMSOp() {
        return vICMSOp;
    }

    public void setvICMSOp(String vICMSOp) {
        this.vICMSOp = vICMSOp;
    }

    public String getpDif() {
        return pDif;
    }

    public void setpDif(String pDif) {
        this.pDif = pDif;
    }

    public String getvICMSDif() {
        return vICMSDif;
    }

    public void setvICMSDif(String vICMSDif) {
        this.vICMSDif = vICMSDif;
    }

    public String getvICMS() {
        return vICMS;
    }

    public void setvICMS(String vICMS) {
        this.vICMS = vICMS;
    }

    public String getvBCFCP() {
        return vBCFCP;
    }

    public void setvBCFCP(String vBCFCP) {
        this.vBCFCP = vBCFCP;
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
        return "Icms51VO{" +
                "orig='" + orig + '\'' +
                ", CST='" + CST + '\'' +
                ", modBC='" + modBC + '\'' +
                ", pRedBC='" + pRedBC + '\'' +
                ", vBC='" + vBC + '\'' +
                ", pICMS='" + pICMS + '\'' +
                ", vICMSOp='" + vICMSOp + '\'' +
                ", pDif='" + pDif + '\'' +
                ", vICMSDif='" + vICMSDif + '\'' +
                ", vICMS='" + vICMS + '\'' +
                ", vBCFCP='" + vBCFCP + '\'' +
                ", pFCP='" + pFCP + '\'' +
                ", vFCP='" + vFCP + '\'' +
                '}';
    }
}
