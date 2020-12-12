package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class DetVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nItem = "";
    private ProdVO prod;
    private ImpostoVO imposto;


    public DetVO() {
    }

    public String getnItem() {
        return nItem;
    }

    public void setnItem(String nItem) {
        this.nItem = nItem;
    }

    public ProdVO getProd() {
        return prod;
    }

    public void setProd(ProdVO prod) {
        this.prod = prod;
    }

    public ImpostoVO getImposto() {
        return imposto;
    }

    public void setImposto(ImpostoVO imposto) {
        this.imposto = imposto;
    }

    @Override
    public String toString() {
        return "DetVO{" +
                "nItem='" + nItem + '\'' +
                ", prod=" + prod +
                ", imposto=" + imposto +
                '}';
    }
}
