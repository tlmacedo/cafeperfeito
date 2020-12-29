package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum TelefoneTipo {

    NULL(0, ""),
    FIXO(1, "Fixo"),
    CELULAR(2, "Celular"),
    FIXO_CELULAR(3, "Fixo/Celular");

    private int cod;
    private String descricao;

    private TelefoneTipo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static TelefoneTipo toEnum(Integer cod) {
        if (cod == null) return null;
        for (TelefoneTipo tipo : TelefoneTipo.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<TelefoneTipo> getList() {
        List list = Arrays.asList(TelefoneTipo.values());
        Collections.sort(list, new Comparator<TelefoneTipo>() {
            @Override
            public int compare(TelefoneTipo e1, TelefoneTipo e2) {
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
