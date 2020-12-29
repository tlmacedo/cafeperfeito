package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum WebTipo {

    HOMEPAGE(0, "home page"),
    EMAIL(1, "email");

    private int cod;
    private String descricao;

    private WebTipo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static WebTipo toEnum(Integer cod) {
        if (cod == null) return null;
        for (WebTipo tipo : WebTipo.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<WebTipo> getList() {
        List list = Arrays.asList(WebTipo.values());
        Collections.sort(list, new Comparator<WebTipo>() {
            @Override
            public int compare(WebTipo e1, WebTipo e2) {
                return e1.getDescricao().compareTo(e2.getDescricao());
            }
        });
        return list;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}
