package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum BinarioS_N {

    FALSE(0, "Não"),
    TRUE(1, "Sim");

    private int cod;
    private String descricao;

    private BinarioS_N(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static BinarioS_N toEnum(Integer cod) {
        if (cod == null) return null;
        for (BinarioS_N tipo : BinarioS_N.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<BinarioS_N> getList() {
        List list = Arrays.asList(BinarioS_N.values());
        Collections.sort(list, new Comparator<BinarioS_N>() {
            @Override
            public int compare(BinarioS_N e1, BinarioS_N e2) {
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
