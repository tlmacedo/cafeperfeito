package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum AtividadeReceitaFederalTipo {
    NULL(0, ""),
    PRINCIPAL(1, "Principal"),
    SECUNDARIA(2, "Secundária"),
    QSA(3, "Qsa");

    private int cod;
    private String descricao;

    private AtividadeReceitaFederalTipo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static AtividadeReceitaFederalTipo toEnum(Integer cod) {
        if (cod == null) return null;
        for (AtividadeReceitaFederalTipo tipo : AtividadeReceitaFederalTipo.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<AtividadeReceitaFederalTipo> getList() {
        List list = Arrays.asList(AtividadeReceitaFederalTipo.values());
        Collections.sort(list, new Comparator<AtividadeReceitaFederalTipo>() {
            @Override
            public int compare(AtividadeReceitaFederalTipo e1, AtividadeReceitaFederalTipo e2) {
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
