package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum WsCosmosBusca {

    NCM(0, "https://api.cosmos.bluesoft.com.br/ncms/"),
    GTINS(1, "/gtins/"),
    GPCS(2, "/gpcs/");

    private int cod;
    private String descricao;

    private WsCosmosBusca(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static WsCosmosBusca toEnum(Integer cod) {
        if (cod == null) return null;
        for (WsCosmosBusca tipo : WsCosmosBusca.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<WsCosmosBusca> getList() {
        List list = Arrays.asList(WsCosmosBusca.values());
        Collections.sort(list, new Comparator<WsCosmosBusca>() {
            @Override
            public int compare(WsCosmosBusca e1, WsCosmosBusca e2) {
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
