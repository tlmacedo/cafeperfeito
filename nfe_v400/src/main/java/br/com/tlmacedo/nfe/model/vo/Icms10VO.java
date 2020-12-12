package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class Icms10VO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orig = "";
    private String CST = "";
    private String modBC = "";
    private String vBC = "";
    private String pICMS = "";
    private String vICMS = "";

    private String vBCFCP = "";
    private String pFCP = "";
    private String vFCP = "";
    private String modBCST = "";
    private String pMVAST = "";
    private String pRedBCST = "";
    private String vBCST = "";
    private String pICMSST = "";
    private String vICMSST = "";
    private String vBCFCPST = "";
    private String pFCPST = "";
    private String vFCPST = "";

    public Icms10VO() {
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

    public String getModBCST() {
        return modBCST;
    }

    public void setModBCST(String modBCST) {
        this.modBCST = modBCST;
    }

    public String getpMVAST() {
        return pMVAST;
    }

    public void setpMVAST(String pMVAST) {
        this.pMVAST = pMVAST;
    }

    public String getpRedBCST() {
        return pRedBCST;
    }

    public void setpRedBCST(String pRedBCST) {
        this.pRedBCST = pRedBCST;
    }

    public String getvBCST() {
        return vBCST;
    }

    public void setvBCST(String vBCST) {
        this.vBCST = vBCST;
    }

    public String getpICMSST() {
        return pICMSST;
    }

    public void setpICMSST(String pICMSST) {
        this.pICMSST = pICMSST;
    }

    public String getvICMSST() {
        return vICMSST;
    }

    public void setvICMSST(String vICMSST) {
        this.vICMSST = vICMSST;
    }

    public String getvBCFCPST() {
        return vBCFCPST;
    }

    public void setvBCFCPST(String vBCFCPST) {
        this.vBCFCPST = vBCFCPST;
    }

    public String getpFCPST() {
        return pFCPST;
    }

    public void setpFCPST(String pFCPST) {
        this.pFCPST = pFCPST;
    }

    public String getvFCPST() {
        return vFCPST;
    }

    public void setvFCPST(String vFCPST) {
        this.vFCPST = vFCPST;
    }

    @Override
    public String toString() {
        return "Icms10VO{" +
                "orig='" + orig + '\'' +
                ", CST='" + CST + '\'' +
                ", modBC='" + modBC + '\'' +
                ", vBC='" + vBC + '\'' +
                ", pICMS='" + pICMS + '\'' +
                ", vICMS='" + vICMS + '\'' +
                ", vBCFCP='" + vBCFCP + '\'' +
                ", pFCP='" + pFCP + '\'' +
                ", vFCP='" + vFCP + '\'' +
                ", modBCST='" + modBCST + '\'' +
                ", pMVAST='" + pMVAST + '\'' +
                ", pRedBCST='" + pRedBCST + '\'' +
                ", vBCST='" + vBCST + '\'' +
                ", pICMSST='" + pICMSST + '\'' +
                ", vICMSST='" + vICMSST + '\'' +
                ", vBCFCPST='" + vBCFCPST + '\'' +
                ", pFCPST='" + pFCPST + '\'' +
                ", vFCPST='" + vFCPST + '\'' +
                '}';
    }
}
