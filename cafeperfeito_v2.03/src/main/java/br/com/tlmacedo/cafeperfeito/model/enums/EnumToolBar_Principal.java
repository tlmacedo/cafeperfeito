package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum EnumToolBar_Principal {

    PRINCIPAL(0, "");

    private Integer cod;
    private String descricao;

    EnumToolBar_Principal(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EnumToolBar_Principal toEnum(Integer codigo) {

        if (codigo == null) return null;
        for (EnumToolBar_Principal toolBar : EnumToolBar_Principal.values())
            if (toolBar.getCod().equals(codigo))
                return toolBar;
        throw new IllegalArgumentException("Id inválido!");

    }

    public static List<EnumToolBar_Principal> getList() {
        List<EnumToolBar_Principal> list = Arrays.asList(EnumToolBar_Principal.values());
        list.sort(Comparator.comparing(EnumToolBar_Principal::getDescricao));
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
