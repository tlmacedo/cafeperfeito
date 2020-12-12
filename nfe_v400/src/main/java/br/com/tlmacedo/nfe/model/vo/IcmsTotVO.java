package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class IcmsTotVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal vBC = BigDecimal.ZERO;
    private BigDecimal vICMS = BigDecimal.ZERO;
    private BigDecimal vICMSDeson = BigDecimal.ZERO;
    private BigDecimal vFCPUFDest = BigDecimal.ZERO;
    private BigDecimal vICMSUFDest = BigDecimal.ZERO;
    private BigDecimal vICMSUFRemet = BigDecimal.ZERO;
    private BigDecimal vFCP = BigDecimal.ZERO;
    private BigDecimal vBCST = BigDecimal.ZERO;
    private BigDecimal vST = BigDecimal.ZERO;
    private BigDecimal vFCPST = BigDecimal.ZERO;
    private BigDecimal vFCPSTRet = BigDecimal.ZERO;
    private BigDecimal vProd = BigDecimal.ZERO;
    private BigDecimal vFrete = BigDecimal.ZERO;
    private BigDecimal vSeg = BigDecimal.ZERO;
    private BigDecimal vDesc = BigDecimal.ZERO;
    private BigDecimal vII = BigDecimal.ZERO;
    private BigDecimal vIPI = BigDecimal.ZERO;
    private BigDecimal vIPIDevol = BigDecimal.ZERO;
    private BigDecimal vPIS = BigDecimal.ZERO;
    private BigDecimal vCOFINS = BigDecimal.ZERO;
    private BigDecimal vOutro = BigDecimal.ZERO;
    private BigDecimal vNF = BigDecimal.ZERO;
    private BigDecimal vTotTrib = BigDecimal.ZERO;

    public IcmsTotVO() {
    }

    public BigDecimal getvBC() {
        return vBC;
    }

    public void setvBC(BigDecimal vBC) {
        this.vBC = vBC;
    }

    public BigDecimal getvICMS() {
        return vICMS;
    }

    public void setvICMS(BigDecimal vICMS) {
        this.vICMS = vICMS;
    }

    public BigDecimal getvICMSDeson() {
        return vICMSDeson;
    }

    public void setvICMSDeson(BigDecimal vICMSDeson) {
        this.vICMSDeson = vICMSDeson;
    }

    public BigDecimal getvFCPUFDest() {
        return vFCPUFDest;
    }

    public void setvFCPUFDest(BigDecimal vFCPUFDest) {
        this.vFCPUFDest = vFCPUFDest;
    }

    public BigDecimal getvICMSUFDest() {
        return vICMSUFDest;
    }

    public void setvICMSUFDest(BigDecimal vICMSUFDest) {
        this.vICMSUFDest = vICMSUFDest;
    }

    public BigDecimal getvICMSUFRemet() {
        return vICMSUFRemet;
    }

    public void setvICMSUFRemet(BigDecimal vICMSUFRemet) {
        this.vICMSUFRemet = vICMSUFRemet;
    }

    public BigDecimal getvFCP() {
        return vFCP;
    }

    public void setvFCP(BigDecimal vFCP) {
        this.vFCP = vFCP;
    }

    public BigDecimal getvBCST() {
        return vBCST;
    }

    public void setvBCST(BigDecimal vBCST) {
        this.vBCST = vBCST;
    }

    public BigDecimal getvST() {
        return vST;
    }

    public void setvST(BigDecimal vST) {
        this.vST = vST;
    }

    public BigDecimal getvFCPST() {
        return vFCPST;
    }

    public void setvFCPST(BigDecimal vFCPST) {
        this.vFCPST = vFCPST;
    }

    public BigDecimal getvFCPSTRet() {
        return vFCPSTRet;
    }

    public void setvFCPSTRet(BigDecimal vFCPSTRet) {
        this.vFCPSTRet = vFCPSTRet;
    }

    public BigDecimal getvProd() {
        return vProd;
    }

    public void setvProd(BigDecimal vProd) {
        this.vProd = vProd;
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

    public BigDecimal getvII() {
        return vII;
    }

    public void setvII(BigDecimal vII) {
        this.vII = vII;
    }

    public BigDecimal getvIPI() {
        return vIPI;
    }

    public void setvIPI(BigDecimal vIPI) {
        this.vIPI = vIPI;
    }

    public BigDecimal getvIPIDevol() {
        return vIPIDevol;
    }

    public void setvIPIDevol(BigDecimal vIPIDevol) {
        this.vIPIDevol = vIPIDevol;
    }

    public BigDecimal getvPIS() {
        return vPIS;
    }

    public void setvPIS(BigDecimal vPIS) {
        this.vPIS = vPIS;
    }

    public BigDecimal getvCOFINS() {
        return vCOFINS;
    }

    public void setvCOFINS(BigDecimal vCOFINS) {
        this.vCOFINS = vCOFINS;
    }

    public BigDecimal getvOutro() {
        return vOutro;
    }

    public void setvOutro(BigDecimal vOutro) {
        this.vOutro = vOutro;
    }

    public BigDecimal getvNF() {
        return vNF;
    }

    public void setvNF(BigDecimal vNF) {
        this.vNF = vNF;
    }

    public BigDecimal getvTotTrib() {
        return vTotTrib;
    }

    public void setvTotTrib(BigDecimal vTotTrib) {
        this.vTotTrib = vTotTrib;
    }

    @Override
    public String toString() {
        return "IcmsTotVO{" +
                "vBC=" + vBC +
                ", vICMS=" + vICMS +
                ", vICMSDeson=" + vICMSDeson +
                ", vFCPUFDest=" + vFCPUFDest +
                ", vICMSUFDest=" + vICMSUFDest +
                ", vICMSUFRemet=" + vICMSUFRemet +
                ", vFCP=" + vFCP +
                ", vBCST=" + vBCST +
                ", vST=" + vST +
                ", vFCPST=" + vFCPST +
                ", vFCPSTRet=" + vFCPSTRet +
                ", vProd=" + vProd +
                ", vFrete=" + vFrete +
                ", vSeg=" + vSeg +
                ", vDesc=" + vDesc +
                ", vII=" + vII +
                ", vIPI=" + vIPI +
                ", vIPIDevol=" + vIPIDevol +
                ", vPIS=" + vPIS +
                ", vCOFINS=" + vCOFINS +
                ", vOutro=" + vOutro +
                ", vNF=" + vNF +
                ", vTotTrib=" + vTotTrib +
                '}';
    }
}
