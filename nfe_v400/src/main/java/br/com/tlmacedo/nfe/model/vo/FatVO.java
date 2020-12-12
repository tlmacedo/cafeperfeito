package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class FatVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nFat = "";
    private BigDecimal vOrig = BigDecimal.ZERO;
    private BigDecimal vDesc = BigDecimal.ZERO;
    private BigDecimal vLiq = BigDecimal.ZERO;

    public FatVO() {
    }

    public String getnFat() {
        return nFat;
    }

    public void setnFat(String nFat) {
        this.nFat = nFat;
    }

    public BigDecimal getvOrig() {
        return vOrig;
    }

    public void setvOrig(BigDecimal vOrig) {
        this.vOrig = vOrig;
    }

    public BigDecimal getvDesc() {
        return vDesc;
    }

    public void setvDesc(BigDecimal vDesc) {
        this.vDesc = vDesc;
    }

    public BigDecimal getvLiq() {
        return vLiq;
    }

    public void setvLiq(BigDecimal vLiq) {
        this.vLiq = vLiq;
    }

    @Override
    public String toString() {
        return "FatVO{" +
                "nFat='" + nFat + '\'' +
                ", vOrig=" + vOrig +
                ", vDesc=" + vDesc +
                ", vLiq=" + vLiq +
                '}';
    }
}
