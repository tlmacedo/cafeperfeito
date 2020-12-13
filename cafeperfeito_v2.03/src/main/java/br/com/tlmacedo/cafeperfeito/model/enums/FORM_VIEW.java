package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum FORM_VIEW {

    LOGIN(0, "Login"),
    PRINCIPAL(1, "Principal");

    private Integer cod;
    private String descricao;

    FORM_VIEW(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static FORM_VIEW toEnum(Integer codigo) {
        if (codigo == null) return null;
        for (FORM_VIEW view : FORM_VIEW.values())
            if (view.getCod().equals(codigo))
                return view;
        throw new IllegalArgumentException("Id inválido!");
    }

    public static List<FORM_VIEW> getList() {
        List<FORM_VIEW> list = Arrays.asList(FORM_VIEW.values());
        list.sort(Comparator.comparing(FORM_VIEW::getDescricao));
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
