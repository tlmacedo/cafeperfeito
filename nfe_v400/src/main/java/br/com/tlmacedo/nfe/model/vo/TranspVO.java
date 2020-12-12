package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TranspVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String modFrete = "";
    private TransportaVO transporta;
    private RetTranspVO retTransp;
    private List<VolVO> volVOList = new ArrayList<>();


    public TranspVO() {
    }

    public String getModFrete() {
        return modFrete;
    }

    public void setModFrete(String modFrete) {
        this.modFrete = modFrete;
    }

    public TransportaVO getTransporta() {
        return transporta;
    }

    public void setTransporta(TransportaVO transporta) {
        this.transporta = transporta;
    }

    public RetTranspVO getRetTransp() {
        return retTransp;
    }

    public void setRetTransp(RetTranspVO retTransp) {
        this.retTransp = retTransp;
    }

    public List<VolVO> getVolVOList() {
        return volVOList;
    }

    public void setVolVOList(List<VolVO> volVOList) {
        this.volVOList = volVOList;
    }

    @Override
    public String toString() {
        return "TranspVO{" +
                "modFrete='" + modFrete + '\'' +
                ", transporta=" + transporta +
                ", retTransp=" + retTransp +
                ", volVOList=" + volVOList +
                '}';
    }
}
