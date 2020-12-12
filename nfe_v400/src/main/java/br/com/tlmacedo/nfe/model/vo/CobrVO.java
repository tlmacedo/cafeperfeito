package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CobrVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private br.com.tlmacedo.nfe.model.vo.FatVO fat;
    private List<DupVO> dupVOList = new ArrayList<>();

    public CobrVO() {
    }

    public FatVO getFat() {
        return fat;
    }

    public void setFat(FatVO fat) {
        this.fat = fat;
    }

    public List<DupVO> getDupVOList() {
        return dupVOList;
    }

    public void setDupVOList(List<DupVO> dupVOList) {
        this.dupVOList = dupVOList;
    }

    @Override
    public String toString() {
        return "CobrVO{" +
                "fat=" + fat +
                ", dupVOList=" + dupVOList +
                '}';
    }
}
