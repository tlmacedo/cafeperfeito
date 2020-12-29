package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum SituacaoNoSistema {

    NULL(0, ""),
    ATIVO(1, "Ativo"),
    DESATIVADO(2, "Desativado"),
    NEGOCIACAO(3, "Negociação"),
    DESCONTINUADO(4, "Descontinuado"),
    INCONSISTENTE(5, "Inconsistente"),
    TERCEIROS(6, "Terceiros"),
    SUSPENSA(7, "Suspensa"),
    INAPTA(8, "Inapta"),
    BAIXADA(9, "Baixada");


    private int cod;
    private String descricao;

    private SituacaoNoSistema(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static SituacaoNoSistema toEnum(Integer cod) {
        if (cod == null) return null;
        for (SituacaoNoSistema tipo : SituacaoNoSistema.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<SituacaoNoSistema> getList() {
        List list = Arrays.asList(SituacaoNoSistema.values());
        Collections.sort(list, new Comparator<SituacaoNoSistema>() {
            @Override
            public int compare(SituacaoNoSistema e1, SituacaoNoSistema e2) {
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
