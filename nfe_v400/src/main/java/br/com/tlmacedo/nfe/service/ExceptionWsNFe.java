package br.com.tlmacedo.nfe.service;

public class ExceptionWsNFe extends Exception {

    private int tpAmb, cStat;
    private String xMotivo;

    public ExceptionWsNFe(Throwable cause) {
        super(cause);
    }

    public ExceptionWsNFe(int tpAmb, int cStat, String xMotivo) {
        super(String.format("\n<tpAmb>%d</tpAmb>\n<cStat>%d</cStat>\n<xMotivo>%s</xMotivo>\n",
                tpAmb,
                cStat,
                xMotivo));
        this.tpAmb = tpAmb;
        this.cStat = cStat;
        this.xMotivo = xMotivo;
    }

    public int getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(int tpAmb) {
        this.tpAmb = tpAmb;
    }

    public int getcStat() {
        return cStat;
    }

    public void setcStat(int cStat) {
        this.cStat = cStat;
    }

    public String getxMotivo() {
        return xMotivo;
    }

    public void setxMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }
}


