package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum SituacaoCadastroEmpresa {

    INATIVO(0, "Inativo"),
    ATIVO(1, "Ativo"),
    NEGOCIACAO(2, "Negociação"),
    TERCEIROS(3, "Terceiros"),
    SUSPENSA(4, "Suspensa"),
    INAPTA(5, "Inapta"),
    BAIXADA(6, "Baixada");

    private Integer cod;
    private String descricao;

    private SituacaoCadastroEmpresa(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<SituacaoCadastroEmpresa> getList() {
        List list = Arrays.asList(SituacaoCadastroEmpresa.values());
        Collections.sort(list, new Comparator<SituacaoCadastroEmpresa>() {
            @Override
            public int compare(SituacaoCadastroEmpresa e1, SituacaoCadastroEmpresa e2) {
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
