package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class EntregaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cnpj = "";
    private String cpf = "";
    private String xNome = "";
    private String xLgr = "";
    private String nro = "";
    private String xCpl = "";
    private String xBairro = "";
    private String cMun = "";
    private String xMun = "";
    private String UF = "";
    private String CEP = "";
    private String cPais = "";
    private String xPais = "";
    private String fone = "";
    private String email = "";
    private String IE = "";

    public EntregaVO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getxLgr() {
        return xLgr;
    }

    public void setxLgr(String xLgr) {
        this.xLgr = xLgr;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getxCpl() {
        return xCpl;
    }

    public void setxCpl(String xCpl) {
        this.xCpl = xCpl;
    }

    public String getxBairro() {
        return xBairro;
    }

    public void setxBairro(String xBairro) {
        this.xBairro = xBairro;
    }

    public String getcMun() {
        return cMun;
    }

    public void setcMun(String cMun) {
        this.cMun = cMun;
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

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getcPais() {
        return cPais;
    }

    public void setcPais(String cPais) {
        this.cPais = cPais;
    }

    public String getxPais() {
        return xPais;
    }

    public void setxPais(String xPais) {
        this.xPais = xPais;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    @Override
    public String toString() {
        return "EntregaVO{" +
                "cnpj='" + cnpj + '\'' +
                ", cpf='" + cpf + '\'' +
                ", xNome='" + xNome + '\'' +
                ", xLgr='" + xLgr + '\'' +
                ", nro='" + nro + '\'' +
                ", xCpl='" + xCpl + '\'' +
                ", xBairro='" + xBairro + '\'' +
                ", cMun='" + cMun + '\'' +
                ", UF='" + UF + '\'' +
                ", CEP='" + CEP + '\'' +
                ", cPais='" + cPais + '\'' +
                ", xPais='" + xPais + '\'' +
                ", fone='" + fone + '\'' +
                ", email='" + email + '\'' +
                ", IE='" + IE + '\'' +
                '}';
    }
}
