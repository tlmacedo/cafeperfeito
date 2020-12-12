package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class CofinsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private CofinsAliqVO cofinsAliq;
    private CofinsQtdeVO cofinsQtde;
    private CofinsNTVO cofinsNT;
    private CofinsOutrVO cofinsOutr;

    public CofinsVO() {
    }

    public CofinsAliqVO getCofinsAliq() {
        return cofinsAliq;
    }

    public void setCofinsAliq(CofinsAliqVO cofinsAliq) {
        this.cofinsAliq = cofinsAliq;
    }

    public CofinsQtdeVO getCofinsQtde() {
        return cofinsQtde;
    }

    public void setCofinsQtde(CofinsQtdeVO cofinsQtde) {
        this.cofinsQtde = cofinsQtde;
    }

    public CofinsNTVO getCofinsNT() {
        return cofinsNT;
    }

    public void setCofinsNT(CofinsNTVO cofinsNT) {
        this.cofinsNT = cofinsNT;
    }

    public CofinsOutrVO getCofinsOutr() {
        return cofinsOutr;
    }

    public void setCofinsOutr(CofinsOutrVO cofinsOutr) {
        this.cofinsOutr = cofinsOutr;
    }
}
