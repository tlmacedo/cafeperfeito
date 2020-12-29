package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum StatusBarProduto {

    PESQUISA(0, "[F1-Novo]  [F4-Editar]  [F7-Pesquisar]  [F12-Sair]"),
    EDITAR(1, "[F3-Cancelar edição]  [F5-Atualizar]"),
    INCLUIR(2, "[F2-Incluir]  [F3-Cancelar inclusão]"),
    EDITAR_ERR(51, "[F3-Cancelar edição]"),
    INCLUIR_ERR(52, "[F3-Cancelar inclusão]");

    private int cod;
    private String descricao;

    private StatusBarProduto(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static StatusBarProduto toEnum(Integer cod) {
        if (cod == null) return null;
        for (StatusBarProduto tipo : StatusBarProduto.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<StatusBarProduto> getList() {
        List list = Arrays.asList(StatusBarProduto.values());
        Collections.sort(list, new Comparator<StatusBarProduto>() {
            @Override
            public int compare(StatusBarProduto e1, StatusBarProduto e2) {
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

//    @Override
//    public String toString() {
//        return getDescricao();
//    }

}
