package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum PagamentoSituacao {

    //    NULL(-1, ""),
    PENDENTE(0, "Pendente"),
    QUITADO(1, "Quitado"),
    CANCELADO(2, "Cancelado");

    private Integer cod;
    private String descricao;

    private PagamentoSituacao(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<PagamentoSituacao> getList() {
        List list = Arrays.asList(PagamentoSituacao.values());
        Collections.sort(list, new Comparator<PagamentoSituacao>() {
            @Override
            public int compare(PagamentoSituacao e1, PagamentoSituacao e2) {
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
