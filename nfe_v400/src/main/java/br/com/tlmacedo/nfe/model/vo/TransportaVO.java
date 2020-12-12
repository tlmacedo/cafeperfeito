package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class TransportaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CNPJ = "";
    private String CPF = "";
    private String xNome = "";
    private String IE = "";
    private String xEnder = "";
    private String xMun = "";
    private String UF = "";


    public TransportaVO() {
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getxEnder() {
        return xEnder;
    }

    public void setxEnder(String xEnder) {
        this.xEnder = xEnder;
    }

    public String getxMun() {
        return xMun;
    }

    public void setxMun(String xMun) {
        this.xMun = xMun;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    @Override
    public String toString() {
        return "TransportaVO{" +
                "CNPJ='" + CNPJ + '\'' +
                ", CPF='" + CPF + '\'' +
                ", xNome='" + xNome + '\'' +
                ", IE='" + IE + '\'' +
                ", xEnder='" + xEnder + '\'' +
                ", xMun='" + xMun + '\'' +
                ", UF='" + UF + '\'' +
                '}';
    }
}
