package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class PisVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private PisAliqVO pisAliq;
    private PisQtdeVO pisQtde;
    private PisNTVO pisNT;
    private PisOutrVO pisOutr;

    public PisVO() {
    }

    public PisAliqVO getPisAliq() {
        return pisAliq;
    }

    public void setPisAliq(PisAliqVO pisAliq) {
        this.pisAliq = pisAliq;
    }

    public PisQtdeVO getPisQtde() {
        return pisQtde;
    }

    public void setPisQtde(PisQtdeVO pisQtde) {
        this.pisQtde = pisQtde;
    }

    public PisNTVO getPisNT() {
        return pisNT;
    }

    public void setPisNT(PisNTVO pisNT) {
        this.pisNT = pisNT;
    }

    public PisOutrVO getPisOutr() {
        return pisOutr;
    }

    public void setPisOutr(PisOutrVO pisOutr) {
        this.pisOutr = pisOutr;
    }
}
