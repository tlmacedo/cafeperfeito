package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class EnviNfeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String versao = "";
    private String idLote = "";
    private String indSinc = "";

    private NfeVO nfe;

    public EnviNfeVO() {

    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public String getIndSinc() {
        return indSinc;
    }

    public void setIndSinc(String indSinc) {
        this.indSinc = indSinc;
    }

    public NfeVO getNfe() {
        return nfe;
    }

    public void setNfe(NfeVO nfe) {
        this.nfe = nfe;
    }

    @Override
    public String toString() {
        return "EnviNfeVO{" +
                "versao='" + versao + '\'' +
                ", idLote='" + idLote + '\'' +
                ", indSinc='" + indSinc + '\'' +
                ", nfe=" + nfe +
                '}';
    }
}
