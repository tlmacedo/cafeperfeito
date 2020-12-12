package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.util.List;

public class VolVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String qVol = "";
    private String esp = "";
    private String marca = "";
    private String nVol = "";
    private String pesoL = "";
    private String pesoB = "";
    private List<LacresVO> lacresVOList;

    public VolVO() {
    }

    public String getqVol() {
        return qVol;
    }

    public void setqVol(String qVol) {
        this.qVol = qVol;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getnVol() {
        return nVol;
    }

    public void setnVol(String nVol) {
        this.nVol = nVol;
    }

    public String getPesoL() {
        return pesoL;
    }

    public void setPesoL(String pesoL) {
        this.pesoL = pesoL;
    }

    public String getPesoB() {
        return pesoB;
    }

    public void setPesoB(String pesoB) {
        this.pesoB = pesoB;
    }

    public List<LacresVO> getLacresVOList() {
        return lacresVOList;
    }

    public void setLacresVOList(List<LacresVO> lacresVOList) {
        this.lacresVOList = lacresVOList;
    }

    @Override
    public String toString() {
        return "VolVO{" +
                "qVol='" + qVol + '\'' +
                ", esp='" + esp + '\'' +
                ", marca='" + marca + '\'' +
                ", nVol='" + nVol + '\'' +
                ", pesoL='" + pesoL + '\'' +
                ", pesoB='" + pesoB + '\'' +
                ", lacresVOList=" + lacresVOList +
                '}';
    }
}
