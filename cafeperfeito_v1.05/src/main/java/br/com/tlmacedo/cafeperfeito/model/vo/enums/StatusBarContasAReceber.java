package br.com.tlmacedo.cafeperfeito.model.vo.enums;

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

public enum StatusBarContasAReceber {

    DIGITACAO(0, "[Insert-Novo recebimento]  [F12-Sair]");

    private int cod;
    private String descricao;

    private StatusBarContasAReceber(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static StatusBarContasAReceber toEnum(Integer cod) {
        if (cod == null) return null;
        for (StatusBarContasAReceber tipo : StatusBarContasAReceber.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<StatusBarContasAReceber> getList() {
        List list = Arrays.asList(StatusBarContasAReceber.values());
        Collections.sort(list, new Comparator<StatusBarContasAReceber>() {
            @Override
            public int compare(StatusBarContasAReceber e1, StatusBarContasAReceber e2) {
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
