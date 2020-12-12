package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class DestVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cnpj = "";
    private String cpf = "";
    private String idEstrangeiro = "";
    private String xNome = "";
    private EnderVO ender;
    private String indIEDest = "";
    private String IE = "";
    private String ISUF = "";
    private String IM = "";
    private String email = "";

    public DestVO() {
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

    public String getIdEstrangeiro() {
        return idEstrangeiro;
    }

    public void setIdEstrangeiro(String idEstrangeiro) {
        this.idEstrangeiro = idEstrangeiro;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public EnderVO getEnder() {
        return ender;
    }

    public void setEnder(EnderVO ender) {
        this.ender = ender;
    }

    public String getIndIEDest() {
        return indIEDest;
    }

    public void setIndIEDest(String indIEDest) {
        this.indIEDest = indIEDest;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getISUF() {
        return ISUF;
    }

    public void setISUF(String ISUF) {
        this.ISUF = ISUF;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "DestVO{" +
                "cnpj='" + cnpj + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idEstrangeiro='" + idEstrangeiro + '\'' +
                ", xNome='" + xNome + '\'' +
                ", ender=" + ender +
                ", indIEDest='" + indIEDest + '\'' +
                ", IE='" + IE + '\'' +
                ", ISUF='" + ISUF + '\'' +
                ", IM='" + IM + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
