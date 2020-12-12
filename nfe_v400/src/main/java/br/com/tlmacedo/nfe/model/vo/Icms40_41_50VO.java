package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class Icms40_41_50VO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orig = "";
    private String CST = "";
    private String vICMSDeson = "";
    private String motDesICMS = "";

    public Icms40_41_50VO() {
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

    public String getvICMSDeson() {
        return vICMSDeson;
    }

    public void setvICMSDeson(String vICMSDeson) {
        this.vICMSDeson = vICMSDeson;
    }

    public String getMotDesICMS() {
        return motDesICMS;
    }

    public void setMotDesICMS(String motDesICMS) {
        this.motDesICMS = motDesICMS;
    }

    @Override
    public String toString() {
        return "Icms40_41_50VO{" +
                "orig='" + orig + '\'' +
                ", CST='" + CST + '\'' +
                ", vICMSDeson='" + vICMSDeson + '\'' +
                ", motDesICMS='" + motDesICMS + '\'' +
                '}';
    }
}
