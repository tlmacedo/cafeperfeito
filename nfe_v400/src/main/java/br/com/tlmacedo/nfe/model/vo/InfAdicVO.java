package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class InfAdicVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String infAdFisco = "";
    private String infCpl = "";

    public InfAdicVO() {
    }

    public String getInfAdFisco() {
        return infAdFisco;
    }

    public void setInfAdFisco(String infAdFisco) {
        this.infAdFisco = infAdFisco;
    }

    public String getInfCpl() {
        return infCpl;
    }

    public void setInfCpl(String infCpl) {
        this.infCpl = infCpl;
    }

    @Override
    public String toString() {
        return "InfAdicVO{" +
                "infAdFisco='" + infAdFisco + '\'' +
                ", infCpl='" + infCpl + '\'' +
                '}';
    }
}
