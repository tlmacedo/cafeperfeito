package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class PisAliqVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CST = "";
    private String vBC = "";
    private String pPIS = "";
    private String vPIS = "";

    public PisAliqVO() {
    }

    public String getCST() {
        return CST;
    }

    public void setCST(String CST) {
        this.CST = CST;
    }

    public String getvBC() {
        return vBC;
    }

    public void setvBC(String vBC) {
        this.vBC = vBC;
    }

    public String getpPIS() {
        return pPIS;
    }

    public void setpPIS(String pPIS) {
        this.pPIS = pPIS;
    }

    public String getvPIS() {
        return vPIS;
    }

    public void setvPIS(String vPIS) {
        this.vPIS = vPIS;
    }

    @Override
    public String toString() {
        return "PisAliqVO{" +
                "CST='" + CST + '\'' +
                ", vBC='" + vBC + '\'' +
                ", pPIS='" + pPIS + '\'' +
                ", vPIS='" + vPIS + '\'' +
                '}';
    }
}
