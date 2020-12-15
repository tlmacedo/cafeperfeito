package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum TB_PRINCIPAL {

    PRINCIPAL(0, "");

    private Integer cod;
    private String descricao;

    TB_PRINCIPAL(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static TB_PRINCIPAL toEnum(Integer codigo) {

        if (codigo == null) return null;
        for (TB_PRINCIPAL toolBar : TB_PRINCIPAL.values())
            if (toolBar.getCod().equals(codigo))
                return toolBar;
        throw new IllegalArgumentException("Id inválido!");

    }

    public static List<TB_PRINCIPAL> getList() {
        List<TB_PRINCIPAL> list = Arrays.asList(TB_PRINCIPAL.values());
        list.sort(Comparator.comparing(TB_PRINCIPAL::getDescricao));
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
