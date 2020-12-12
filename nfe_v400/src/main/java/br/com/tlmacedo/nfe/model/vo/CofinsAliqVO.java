package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class CofinsAliqVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CST = "";
    private String vBC = "";
    private String pCOFINS = "";
    private String vCOFINS = "";

    public CofinsAliqVO() {
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

    public String getpCOFINS() {
        return pCOFINS;
    }

    public void setpCOFINS(String pCOFINS) {
        this.pCOFINS = pCOFINS;
    }

    public String getvCOFINS() {
        return vCOFINS;
    }

    public void setvCOFINS(String vCOFINS) {
        this.vCOFINS = vCOFINS;
    }

    @Override
    public String toString() {
        return "COFINSAliqVO{" +
                "CST='" + CST + '\'' +
                ", vBC='" + vBC + '\'' +
                ", pCOFINS='" + pCOFINS + '\'' +
                ", vCOFINS='" + vCOFINS + '\'' +
                '}';
    }
}
