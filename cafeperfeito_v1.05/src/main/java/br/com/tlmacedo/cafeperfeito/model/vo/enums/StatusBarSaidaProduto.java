package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-10
 * Time: 14:31
 */

public enum StatusBarSaidaProduto {
    DIGITACAO(0, "[F1-Novo]  [F2-Finalizar venda]  [F3-]  [F4-]  [F5-]  [F6-Cliente]  [F7-Pesquisa produto]  [F8-Itens venda]  [F9-nfe]  [F10-]  [F11-]  [F12-Sair]"),
    FINALIZADA(1, "[F12-Sair]");

    private int cod;
    private String descricao;

    private StatusBarSaidaProduto(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static StatusBarSaidaProduto toEnum(Integer cod) {
        if (cod == null) return null;
        for (StatusBarSaidaProduto tipo : StatusBarSaidaProduto.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<StatusBarSaidaProduto> getList() {
        List list = Arrays.asList(StatusBarSaidaProduto.values());
        Collections.sort(list, new Comparator<StatusBarSaidaProduto>() {
            @Override
            public int compare(StatusBarSaidaProduto e1, StatusBarSaidaProduto e2) {
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
