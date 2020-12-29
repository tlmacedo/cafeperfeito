package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum GeneroSexo {

    NULL(0, ""),
    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino");


    private int cod;
    private String descricao;

    private GeneroSexo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static GeneroSexo toEnum(Integer cod) {
        if (cod == null) return null;
        for (GeneroSexo tipo : GeneroSexo.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<GeneroSexo> getList() {
        List list = Arrays.asList(GeneroSexo.values());
        Collections.sort(list, new Comparator<GeneroSexo>() {
            @Override
            public int compare(GeneroSexo e1, GeneroSexo e2) {
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
