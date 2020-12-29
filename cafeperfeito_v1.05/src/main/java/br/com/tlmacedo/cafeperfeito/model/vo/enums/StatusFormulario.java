package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum StatusFormulario {
    PESQUISA(0, "Pesquisa"),
    EDITAR(1, "Editar"),
    INCLUIR(2, "Incluir");


    private int cod;
    private String descricao;

    private StatusFormulario(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static StatusFormulario toEnum(Integer cod) {
        if (cod == null) return null;
        for (StatusFormulario tipo : StatusFormulario.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<StatusFormulario> getList() {
        List list = Arrays.asList(StatusFormulario.values());
        Collections.sort(list, new Comparator<StatusFormulario>() {
            @Override
            public int compare(StatusFormulario e1, StatusFormulario e2) {
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
