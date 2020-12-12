package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class IcmsSTVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orig = "";
    private String CST = "";
    private String vBCSTRet = "";
    private String pST = "";
    private String vICMSSubstituto = "";
    private String vICMSSTRet = "";
    private String vBCFCPSTRet = "";
    private String pFCPSTRet = "";
    private String vFCPSTRet = "";
    private String vBCSTDest = "";
    private String vICMSSTDest = "";
    private String pRedBCEfet = "";
    private String vBCEfet = "";
    private String pICMSEfet = "";
    private String vICMSEfet = "";

    public IcmsSTVO() {
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

    public String getvBCSTRet() {
        return vBCSTRet;
    }

    public void setvBCSTRet(String vBCSTRet) {
        this.vBCSTRet = vBCSTRet;
    }

    public String getpST() {
        return pST;
    }

    public void setpST(String pST) {
        this.pST = pST;
    }

    public String getvICMSSubstituto() {
        return vICMSSubstituto;
    }

    public void setvICMSSubstituto(String vICMSSubstituto) {
        this.vICMSSubstituto = vICMSSubstituto;
    }

    public String getvICMSSTRet() {
        return vICMSSTRet;
    }

    public void setvICMSSTRet(String vICMSSTRet) {
        this.vICMSSTRet = vICMSSTRet;
    }

    public String getvBCFCPSTRet() {
        return vBCFCPSTRet;
    }

    public void setvBCFCPSTRet(String vBCFCPSTRet) {
        this.vBCFCPSTRet = vBCFCPSTRet;
    }

    public String getpFCPSTRet() {
        return pFCPSTRet;
    }

    public void setpFCPSTRet(String pFCPSTRet) {
        this.pFCPSTRet = pFCPSTRet;
    }

    public String getvFCPSTRet() {
        return vFCPSTRet;
    }

    public void setvFCPSTRet(String vFCPSTRet) {
        this.vFCPSTRet = vFCPSTRet;
    }

    public String getvBCSTDest() {
        return vBCSTDest;
    }

    public void setvBCSTDest(String vBCSTDest) {
        this.vBCSTDest = vBCSTDest;
    }

    public String getvICMSSTDest() {
        return vICMSSTDest;
    }

    public void setvICMSSTDest(String vICMSSTDest) {
        this.vICMSSTDest = vICMSSTDest;
    }

    public String getpRedBCEfet() {
        return pRedBCEfet;
    }

    public void setpRedBCEfet(String pRedBCEfet) {
        this.pRedBCEfet = pRedBCEfet;
    }

    public String getvBCEfet() {
        return vBCEfet;
    }

    public void setvBCEfet(String vBCEfet) {
        this.vBCEfet = vBCEfet;
    }

    public String getpICMSEfet() {
        return pICMSEfet;
    }

    public void setpICMSEfet(String pICMSEfet) {
        this.pICMSEfet = pICMSEfet;
    }

    public String getvICMSEfet() {
        return vICMSEfet;
    }

    public void setvICMSEfet(String vICMSEfet) {
        this.vICMSEfet = vICMSEfet;
    }

    @Override
    public String toString() {
        return "IcmsSTVO{" +
                "orig='" + orig + '\'' +
                ", CST='" + CST + '\'' +
                ", vBCSTRet='" + vBCSTRet + '\'' +
                ", pST='" + pST + '\'' +
                ", vICMSSubstituto='" + vICMSSubstituto + '\'' +
                ", vICMSSTRet='" + vICMSSTRet + '\'' +
                ", vBCFCPSTRet='" + vBCFCPSTRet + '\'' +
                ", pFCPSTRet='" + pFCPSTRet + '\'' +
                ", vFCPSTRet='" + vFCPSTRet + '\'' +
                ", vBCSTDest='" + vBCSTDest + '\'' +
                ", vICMSSTDest='" + vICMSSTDest + '\'' +
                ", pRedBCEfet='" + pRedBCEfet + '\'' +
                ", vBCEfet='" + vBCEfet + '\'' +
                ", pICMSEfet='" + pICMSEfet + '\'' +
                ", vICMSEfet='" + vICMSEfet + '\'' +
                '}';
    }
}
