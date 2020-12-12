package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class PisNTVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CST = "";

    public PisNTVO() {
    }

    public String getCST() {
        return CST;
    }

    public void setCST(String CST) {
        this.CST = CST;
    }

    @Override
    public String toString() {
        return "PisNTVO{" +
                "CST='" + CST + '\'' +
                '}';
    }
}
