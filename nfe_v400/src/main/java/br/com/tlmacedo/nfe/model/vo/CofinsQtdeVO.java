package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class CofinsQtdeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CST = "";
    private String qBCProd = "";
    private String vAliqProd = "";
    private String vCOFINS = "";

    public CofinsQtdeVO() {
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

    public String getvCOFINS() {
        return vCOFINS;
    }

    public void setvCOFINS(String vCOFINS) {
        this.vCOFINS = vCOFINS;
    }

    @Override
    public String toString() {
        return "COFINSQtdeVO{" +
                "CST='" + CST + '\'' +
                ", qBCProd='" + qBCProd + '\'' +
                ", vAliqProd='" + vAliqProd + '\'' +
                ", vCOFINS='" + vCOFINS + '\'' +
                '}';
    }
}
