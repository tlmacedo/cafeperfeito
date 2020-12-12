package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class CofinsSTVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String vBC = "";
    private String pCOFINS = "";
    private String qBCProd = "";
    private String vAliqProd = "";
    private String vCOFINS = "";

    public CofinsSTVO() {
    }

    /**
     * Begin Getters and Setters
     */
    public String getvBC() {
        return vBC;
    }

    public void setvBC(String vBC) {
        this.vBC = vBC;
    }

    public String getpCOFINS() {
        return pCOFINS;
    }

    public void setpCOFINS(String pCOFINS) {
        this.pCOFINS = pCOFINS;
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

    /**
     * Begin Getters and Setters
     */

    @Override
    public String toString() {
        return "CofinsSTVO{" +
                "vBC='" + vBC + '\'' +
                ", pCOFINS='" + pCOFINS + '\'' +
                ", qBCProd='" + qBCProd + '\'' +
                ", vAliqProd='" + vAliqProd + '\'' +
                ", vCOFINS='" + vCOFINS + '\'' +
                '}';
    }
}
