package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum EnderecoTipo {

    NULL(0, ""),
    PRINCIPAL(1, "Principal"),
    ENTREGA(2, "Entrega"),
    COBRANCA(3, "Cobrança"),
    CORRESPONDENCIA(4, "Correspondência"),
    RESIDENCIAL(5, "Residencial"),
    RECADO(6, "Recado");

    private int cod;
    private String descricao;

    private EnderecoTipo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EnderecoTipo toEnum(Integer cod) {
        if (cod == null) return null;
        for (EnderecoTipo tipo : EnderecoTipo.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<EnderecoTipo> getList() {
        List list = Arrays.asList(EnderecoTipo.values());
        Collections.sort(list, new Comparator<EnderecoTipo>() {
            @Override
            public int compare(EnderecoTipo e1, EnderecoTipo e2) {
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
