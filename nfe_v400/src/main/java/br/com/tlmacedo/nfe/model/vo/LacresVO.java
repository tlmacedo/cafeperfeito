package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class LacresVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nLacre = "";

    public LacresVO() {
    }

    public String getnLacre() {
        return nLacre;
    }

    public void setnLacre(String nLacre) {
        this.nLacre = nLacre;
    }

    @Override
    public String toString() {
        return "LacresVO{" +
                "nLacre='" + nLacre + '\'' +
                '}';
    }
}
