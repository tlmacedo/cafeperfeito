package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class PagVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private DetPagVO detPag;


    public PagVO() {
    }

    public DetPagVO getDetPag() {
        return detPag;
    }

    public void setDetPag(DetPagVO detPag) {
        this.detPag = detPag;
    }

    @Override
    public String toString() {
        return "PagVO{" +
                "detPag=" + detPag +
                '}';
    }
}
