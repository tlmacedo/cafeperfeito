package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum FinalidadeEmailTelefone {

    NULL(0, "Padrão"),
    PRINCIPAL(1, "Principal"),
    NFE(2, "Nfe");


    private int cod;
    private String descricao;

    private FinalidadeEmailTelefone(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static FinalidadeEmailTelefone toEnum(Integer cod) {
        if (cod == null) return null;
        for (FinalidadeEmailTelefone tipo : FinalidadeEmailTelefone.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<FinalidadeEmailTelefone> getList() {
        List list = Arrays.asList(FinalidadeEmailTelefone.values());
        Collections.sort(list, new Comparator<FinalidadeEmailTelefone>() {
            @Override
            public int compare(FinalidadeEmailTelefone e1, FinalidadeEmailTelefone e2) {
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
