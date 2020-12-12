package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class DetPagVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer indPag;
    private Integer tPag;
    private BigDecimal vPag = BigDecimal.ZERO;

    public DetPagVO() {
    }

    public Integer getIndPag() {
        return indPag;
    }

    public void setIndPag(Integer indPag) {
        this.indPag = indPag;
    }

    public Integer gettPag() {
        return tPag;
    }

    public void settPag(Integer tPag) {
        this.tPag = tPag;
    }

    public BigDecimal getvPag() {
        return vPag;
    }

    public void setvPag(BigDecimal vPag) {
        this.vPag = vPag;
    }

    @Override
    public String toString() {
        return "DetPagVO{" +
                "indPag=" + indPag +
                ", tPag=" + tPag +
                ", vPag=" + vPag +
                '}';
    }
}
