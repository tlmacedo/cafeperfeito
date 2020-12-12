package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class PisQtdeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CST = "";
    private String qBCProd = "";
    private String vAliqProd = "";
    private String vPIS = "";

    public PisQtdeVO() {
    }

    public String getCST() {
        return CST;
    }

    public void setCST(String CST) {
        this.CST = CST;
    }

    public String getqBCProd() {
        return qBCProd;
    }

    public void setqBCProd(String qBCProd) {
        this.qBCProd = qBCProd;
    }

    public String getvAliqProd() {
        return vAliqProd;
    }

    public void setvAliqProd(String vAliqProd) {
        this.vAliqProd = vAliqProd;
    }

    public String getvPIS() {
        return vPIS;
    }

    public void setvPIS(String vPIS) {
        this.vPIS = vPIS;
    }

    @Override
    public String toString() {
        return "PisQtdeVO{" +
                "CST='" + CST + '\'' +
                ", qBCProd='" + qBCProd + '\'' +
                ", vAliqProd='" + vAliqProd + '\'' +
                ", vPIS='" + vPIS + '\'' +
                '}';
    }
}
