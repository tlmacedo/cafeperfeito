package br.com.tlmacedo.nfe.model.enums;

//import javafx.beans.property.IntegerProperty;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum TASK_NFE {

    NFE_GERAR(0, "gerando NF-e"),
    NFE_ASSINAR(1, "assinando NF-e"),
    NFE_TRANSMITIR(2, "transmitindo NF-e"),
    NFE_RETORNO(3, "consultando retorno NF-e"),
    NFE_PROC(4, "validando retorno NF-e da SEFAZ"),
    NFE_DANFE(5, "gerando DANFE da NF-e");

    private Integer cod;
    private String descricao;

    TASK_NFE(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static TASK_NFE toEnum(Integer cod) {
        if (cod == null) return null;
        for (TASK_NFE task : TASK_NFE.values())
            if (cod.equals(task.getCod()))
                return task;
        throw new IllegalArgumentException("Id inválido!!!");
    }

    public static List<TASK_NFE> getList() {
        List<TASK_NFE> list = Arrays.asList(TASK_NFE.values());
        list.sort(Comparator.comparing(TASK_NFE::getDescricao));
        return list;
    }


    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
