package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class ImpostoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private IcmsVO icms;
    private PisVO pis;
    private CofinsVO cofins;
    private CofinsSTVO cofinsST;


    public ImpostoVO() {
    }

    public IcmsVO getIcms() {
        return icms;
    }

    public void setIcms(IcmsVO icms) {
        this.icms = icms;
    }

    public PisVO getPis() {
        return pis;
    }

    public void setPis(PisVO pis) {
        this.pis = pis;
    }

    public CofinsVO getCofins() {
        return cofins;
    }

    public void setCofins(CofinsVO cofins) {
        this.cofins = cofins;
    }

    public CofinsSTVO getCofinsST() {
        return cofinsST;
    }

    public void setCofinsST(CofinsSTVO cofinsST) {
        this.cofinsST = cofinsST;
    }

    @Override
    public String toString() {
        return "ImpostoVO{" +
                "icms=" + icms +
                ", pis=" + pis +
                ", cofins=" + cofins +
                ", cofinsST=" + cofinsST +
                '}';
    }
}
