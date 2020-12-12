package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class EmitVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cnpj = "";
    private String cpf = "";
    private String xNome = "";
    private String xFant = "";
    private EnderVO ender;
    private String IE = "";
    private String IEST = "";
    private String CRT = "";

    public EmitVO() {
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

    public String getxFant() {
        return xFant;
    }

    public void setxFant(String xFant) {
        this.xFant = xFant;
    }

    public EnderVO getEnder() {
        return ender;
    }

    public void setEnder(EnderVO ender) {
        this.ender = ender;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getIEST() {
        return IEST;
    }

    public void setIEST(String IEST) {
        this.IEST = IEST;
    }

    public String getCRT() {
        return CRT;
    }

    public void setCRT(String CRT) {
        this.CRT = CRT;
    }

    @Override
    public String toString() {
        return "EmitVO{" +
                "cnpj='" + cnpj + '\'' +
                ", cpf='" + cpf + '\'' +
                ", xNome='" + xNome + '\'' +
                ", xFant='" + xFant + '\'' +
                ", ender=" + ender +
                ", IE='" + IE + '\'' +
                ", IEST='" + IEST + '\'' +
                ", CRT='" + CRT + '\'' +
                '}';
    }
}
