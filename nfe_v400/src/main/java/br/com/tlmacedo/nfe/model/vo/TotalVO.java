package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class TotalVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private IcmsTotVO icmsTot;

    public TotalVO() {
    }

    public IcmsTotVO getIcmsTot() {
        return icmsTot;
    }

    public void setIcmsTot(IcmsTotVO icmsTot) {
        this.icmsTot = icmsTot;
    }


    @Override
    public String toString() {
        return "TotalVO{" +
                "icmsTot=" + icmsTot +
                '}';
    }
}
