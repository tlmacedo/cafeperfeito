package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum PagamentoTipo {

    NULL(0, ""),
    DINHEIRO(1, "Dinheiro"),
    CARTAO(2, "Cartão"),
    BOLETO(3, "Boleto"),
    TRANSFERENCIA(4, "Transferência"),
    ORDEM_BANCARIA(5, "Ordem bancária"),
    RETIRADA(6, "Retirada"),
    BONIFICACAO(7, "Bonificação"),
    AMOSTRA(8, "Amostra");

    private int cod;
    private String descricao;

    private PagamentoTipo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static PagamentoTipo toEnum(Integer cod) {
        if (cod == null) return null;
        for (PagamentoTipo tipo : PagamentoTipo.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<PagamentoTipo> getList() {
        List list = Arrays.asList(PagamentoTipo.values());
        Collections.sort(list, new Comparator<PagamentoTipo>() {
            @Override
            public int compare(PagamentoTipo e1, PagamentoTipo e2) {
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
