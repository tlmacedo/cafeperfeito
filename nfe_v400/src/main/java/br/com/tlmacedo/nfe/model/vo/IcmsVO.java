package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;

public class IcmsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Icms00VO icms00;
    private Icms10VO icms10;
    private Icms20VO icms20;
    private Icms30VO icms30;
    private Icms40_41_50VO icms40_41_50;
    private Icms51VO icms51;
    private Icms60VO icms60;
    private Icms70VO icms70;
    private Icms90VO icms90;
    private IcmsSTVO icmsST;

    public IcmsVO() {
    }

    public Icms00VO getIcms00() {
        return icms00;
    }

    public void setIcms00(Icms00VO icms00) {
        this.icms00 = icms00;
    }

    public Icms10VO getIcms10() {
        return icms10;
    }

    public void setIcms10(Icms10VO icms10) {
        this.icms10 = icms10;
    }

    public Icms20VO getIcms20() {
        return icms20;
    }

    public void setIcms20(Icms20VO icms20) {
        this.icms20 = icms20;
    }

    public Icms30VO getIcms30() {
        return icms30;
    }

    public void setIcms30(Icms30VO icms30) {
        this.icms30 = icms30;
    }

    public Icms40_41_50VO getIcms40_41_50() {
        return icms40_41_50;
    }

    public void setIcms40_41_50(Icms40_41_50VO icms40_41_50) {
        this.icms40_41_50 = icms40_41_50;
    }

    public Icms51VO getIcms51() {
        return icms51;
    }

    public void setIcms51(Icms51VO icms51) {
        this.icms51 = icms51;
    }

    public Icms60VO getIcms60() {
        return icms60;
    }

    public void setIcms60(Icms60VO icms60) {
        this.icms60 = icms60;
    }

    public Icms70VO getIcms70() {
        return icms70;
    }

    public void setIcms70(Icms70VO icms70) {
        this.icms70 = icms70;
    }

    public Icms90VO getIcms90() {
        return icms90;
    }

    public void setIcms90(Icms90VO icms90) {
        this.icms90 = icms90;
    }

    public IcmsSTVO getIcmsST() {
        return icmsST;
    }

    public void setIcmsST(IcmsSTVO icmsST) {
        this.icmsST = icmsST;
    }

    @Override
    public String toString() {
        return "IcmsVO{" +
                "icms00=" + icms00 +
                ", icms10=" + icms10 +
                ", icms20=" + icms20 +
                ", icms30=" + icms30 +
                ", icms40_41_50=" + icms40_41_50 +
                ", icms51=" + icms51 +
                ", icms60=" + icms60 +
                ", icms70=" + icms70 +
                ", icms90=" + icms90 +
                ", icmsST=" + icmsST +
                '}';
    }
}
