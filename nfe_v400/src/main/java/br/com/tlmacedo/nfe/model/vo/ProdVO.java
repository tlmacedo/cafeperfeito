package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cProd = "";
    private String cEAN = "";
    private String xProd = "";
    private String NCM = "";
    private String NVE = "";
    private String CEST = "";
    private String indEscala = "";
    private String CNPJFab = "";
    private String cBenef = "";
    private String EXTIPI = "";
    private String CFOP = "";
    private String uCom = "";
    private BigDecimal qCom = null;
    private BigDecimal vUnCom = null;
    private BigDecimal vProd = null;
    private String cEANTrib = "";
    private String uTrib = "";
    private BigDecimal qTrib = null;
    private BigDecimal vUnTrib = null;
    private BigDecimal vFrete = null;
    private BigDecimal vSeg = null;
    private BigDecimal vDesc = null;
    private BigDecimal vOutro = null;
    private String indTot = "";
    private ImpostoVO imposto;

    public ProdVO() {
    }

    public String getcProd() {
        return cProd;
    }

    public void setcProd(String cProd) {
        this.cProd = cProd;
    }

    public String getcEAN() {
        return cEAN;
    }

    public void setcEAN(String cEAN) {
        this.cEAN = cEAN;
    }

    public String getxProd() {
        return xProd;
    }

    public void setxProd(String xProd) {
        this.xProd = xProd;
    }

    public String getNCM() {
        return NCM;
    }

    public void setNCM(String NCM) {
        this.NCM = NCM;
    }

    public String getNVE() {
        return NVE;
    }

    public void setNVE(String NVE) {
        this.NVE = NVE;
    }

    public String getCEST() {
        return CEST;
    }

    public void setCEST(String CEST) {
        this.CEST = CEST;
    }

    public String getIndEscala() {
        return indEscala;
    }

    public void setIndEscala(String indEscala) {
        this.indEscala = indEscala;
    }

    public String getCNPJFab() {
        return CNPJFab;
    }

    public void setCNPJFab(String CNPJFab) {
        this.CNPJFab = CNPJFab;
    }

    public String getcBenef() {
        return cBenef;
    }

    public void setcBenef(String cBenef) {
        this.cBenef = cBenef;
    }

    public String getEXTIPI() {
        return EXTIPI;
    }

    public void setEXTIPI(String EXTIPI) {
        this.EXTIPI = EXTIPI;
    }

    public String getCFOP() {
        return CFOP;
    }

    public void setCFOP(String CFOP) {
        this.CFOP = CFOP;
    }

    public String getuCom() {
        return uCom;
    }

    public void setuCom(String uCom) {
        this.uCom = uCom;
    }

    public BigDecimal getqCom() {
        return qCom;
    }

    public void setqCom(BigDecimal qCom) {
        this.qCom = qCom;
    }

    public BigDecimal getvUnCom() {
        return vUnCom;
    }

    public void setvUnCom(BigDecimal vUnCom) {
        this.vUnCom = vUnCom;
    }

    public BigDecimal getvProd() {
        return vProd;
    }

    public void setvProd(BigDecimal vProd) {
        this.vProd = vProd;
    }

    public String getcEANTrib() {
        return cEANTrib;
    }

    public void setcEANTrib(String cEANTrib) {
        this.cEANTrib = cEANTrib;
    }

    public String getuTrib() {
        return uTrib;
    }

    public void setuTrib(String uTrib) {
        this.uTrib = uTrib;
    }

    public BigDecimal getqTrib() {
        return qTrib;
    }

    public void setqTrib(BigDecimal qTrib) {
        this.qTrib = qTrib;
    }

    public BigDecimal getvUnTrib() {
        return vUnTrib;
    }

    public void setvUnTrib(BigDecimal vUnTrib) {
        this.vUnTrib = vUnTrib;
    }

    public BigDecimal getvFrete() {
        return vFrete;
    }

    public void setvFrete(BigDecimal vFrete) {
        this.vFrete = vFrete;
    }

    public BigDecimal getvSeg() {
        return vSeg;
    }

    public void setvSeg(BigDecimal vSeg) {
        this.vSeg = vSeg;
    }

    public BigDecimal getvDesc() {
        return vDesc;
    }

    public void setvDesc(BigDecimal vDesc) {
        this.vDesc = vDesc;
    }

    public BigDecimal getvOutro() {
        return vOutro;
    }

    public void setvOutro(BigDecimal vOutro) {
        this.vOutro = vOutro;
    }

    public String getIndTot() {
        return indTot;
    }

    public void setIndTot(String indTot) {
        this.indTot = indTot;
    }

    public ImpostoVO getImposto() {
        return imposto;
    }

    public void setImposto(ImpostoVO imposto) {
        this.imposto = imposto;
    }

    @Override
    public String toString() {
        return "ProdVO{" +
                "cProd='" + cProd + '\'' +
                ", cEAN='" + cEAN + '\'' +
                ", xProd='" + xProd + '\'' +
                ", NCM='" + NCM + '\'' +
                ", NVE='" + NVE + '\'' +
                ", CEST='" + CEST + '\'' +
                ", indEscala='" + indEscala + '\'' +
                ", CNPJFab='" + CNPJFab + '\'' +
                ", cBenef='" + cBenef + '\'' +
                ", EXTIPI='" + EXTIPI + '\'' +
                ", CFOP='" + CFOP + '\'' +
                ", uCom='" + uCom + '\'' +
                ", qCom=" + qCom +
                ", vUnCom=" + vUnCom +
                ", vProd=" + vProd +
                ", cEANTrib='" + cEANTrib + '\'' +
                ", uTrib='" + uTrib + '\'' +
                ", qTrib=" + qTrib +
                ", vUnTrib=" + vUnTrib +
                ", vFrete=" + vFrete +
                ", vSeg=" + vSeg +
                ", vDesc=" + vDesc +
                ", vOutro='" + vOutro + '\'' +
                ", indTot='" + indTot + '\'' +
                ", imposto=" + imposto +
                '}';
    }
}
