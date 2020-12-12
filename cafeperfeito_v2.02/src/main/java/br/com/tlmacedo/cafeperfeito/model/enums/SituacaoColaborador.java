package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum SituacaoColaborador {

    DESATIVADO(0, "Desativado"),
    ATIVO(1, "Ativo");

    private Integer cod;
    private String descricao;

    private SituacaoColaborador(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<SituacaoColaborador> getList() {
        List list = Arrays.asList(SituacaoColaborador.values());
        Collections.sort(list, new Comparator<SituacaoColaborador>() {
            @Override
            public int compare(SituacaoColaborador e1, SituacaoColaborador e2) {
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
