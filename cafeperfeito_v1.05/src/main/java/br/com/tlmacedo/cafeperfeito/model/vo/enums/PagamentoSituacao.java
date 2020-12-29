package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum PagamentoSituacao {

    NULL(0, ""),
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int cod;
    private String descricao;

    private PagamentoSituacao(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static PagamentoSituacao toEnum(Integer cod) {
        if (cod == null) return null;
        for (PagamentoSituacao tipo : PagamentoSituacao.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
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

    public static ObservableList<PagamentoSituacao> getObservableList() {
        ObservableList<PagamentoSituacao> observableList = FXCollections.observableArrayList(PagamentoSituacao.values());
        Collections.sort(observableList, new Comparator<PagamentoSituacao>() {
            @Override
            public int compare(PagamentoSituacao o1, PagamentoSituacao o2) {
                return o1.getDescricao().compareTo(o2.getDescricao());
            }
        });
        return observableList;
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
