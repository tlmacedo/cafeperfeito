package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum SituacaoEntrada {

    DIGITACAO(0, "Digitação"),
    LANCADO(1, "Lançado"),
    INCLUIDO(2, "Incluido"),
    FATURADO(3, "Faturado");

    private Integer cod;
    private String descricao;

    private SituacaoEntrada(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

//    public static SituacaoEntrada toEnum(Integer cod) {
//        if (cod == null) return null;
//        for (SituacaoEntrada tipo : SituacaoEntrada.values())
//            if (cod.equals(tipo.getCod()))
//                return tipo;
//        throw new IllegalArgumentException("Id inválido");
//    }

    public static List<SituacaoEntrada> getList() {
        List list = Arrays.asList(SituacaoEntrada.values());
        Collections.sort(list, new Comparator<SituacaoEntrada>() {
            @Override
            public int compare(SituacaoEntrada e1, SituacaoEntrada e2) {
                return e1.getDescricao().compareTo(e2.getDescricao());
            }
        });
        return list;
    }

    public Integer getCod() {
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
