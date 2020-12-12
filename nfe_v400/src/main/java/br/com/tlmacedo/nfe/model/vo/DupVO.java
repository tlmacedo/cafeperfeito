package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DupVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nDup = "";
    private LocalDate dVenc;
    private BigDecimal vDup = BigDecimal.ZERO;

    public DupVO() {
    }

    public String getnDup() {
        return nDup;
    }

    public void setnDup(String nDup) {
        this.nDup = nDup;
    }

    public LocalDate getdVenc() {
        return dVenc;
    }

    public void setdVenc(LocalDate dVenc) {
        this.dVenc = dVenc;
    }

    public BigDecimal getvDup() {
        return vDup;
    }

    public void setvDup(BigDecimal vDup) {
        this.vDup = vDup;
    }

    @Override
    public String toString() {
        return "DupVO{" +
                "nDup='" + nDup + '\'' +
                ", dVenc=" + dVenc +
                ", vDup=" + vDup +
                '}';
    }
}
