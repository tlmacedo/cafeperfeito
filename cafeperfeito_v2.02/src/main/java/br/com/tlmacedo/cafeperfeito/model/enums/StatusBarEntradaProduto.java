package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-02-26
 * Time: 12:42
 */

public enum StatusBarEntradaProduto {

    DIGITACAO(0, "[F1-Novo]  [F2-Incluir nota e estoque]  [F3-Excluir entrada]  [F4-Salvar entrada e limpar]  [F5-Chave nfe]  [F6-Chave cte]  [F7-Pesquisa produto]  [F8-Itens nota]  [F9-Guia imposto nfe]  [F10-Guia frete]  [F11-Guia imposto frete]  [F12-Sair]"),
    LANCADO(2, "[F1-Novo]  [F2-Incluir estoque]  [F12-Sair]"),
    INCLUIDO(3, "[F1-Novo]  [F2-Faturar entrada]  [F12-Sair]"),
    FATURADO(4, "[F1-Novo  [F12-Sair]");

    private Integer cod;
    private String descricao;

    private StatusBarEntradaProduto(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

//    public static StatusBarEntradaProduto toEnum(Integer cod) {
//        if (cod == null) return null;
//        for (StatusBarEntradaProduto tipo : StatusBarEntradaProduto.values())
//            if (cod.equals(tipo.getCod()))
//                return tipo;
//        throw new IllegalArgumentException("Id inválido");
//    }

    public static List<StatusBarEntradaProduto> getList() {
        List list = Arrays.asList(StatusBarEntradaProduto.values());
        Collections.sort(list, new Comparator<StatusBarEntradaProduto>() {
            @Override
            public int compare(StatusBarEntradaProduto e1, StatusBarEntradaProduto e2) {
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

//    @Override
//    public String toString() {
//        return getDescricao();
//    }

}
