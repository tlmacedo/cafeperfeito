package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class InfRespTecVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cnpj = "";
    private String xContato = "";
    private String email = "";
    private String fone = "";

    public InfRespTecVO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getxContato() {
        return xContato;
    }

    public void setxContato(String xContato) {
        this.xContato = xContato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @Override
    public String toString() {
        return "InfRespTecVO{" +
                "cnpj='" + cnpj + '\'' +
                ", xContato='" + xContato + '\'' +
                ", email='" + email + '\'' +
                ", fone='" + fone + '\'' +
                '}';
    }
}
