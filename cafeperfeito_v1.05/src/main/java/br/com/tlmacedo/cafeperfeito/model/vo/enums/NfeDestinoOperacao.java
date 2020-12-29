package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum NfeDestinoOperacao {

    NULL(0, ""),
    INTERNA(1, "Operação interna"),
    INTERESTADUAL(2, "Operação interestadual"),
    EXTERIOR(3, "Operação com exterior");

    private int cod;
    private String descricao;

    private NfeDestinoOperacao(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static NfeDestinoOperacao toEnum(Integer cod) {
        if (cod == null) return null;
        for (NfeDestinoOperacao tipo : NfeDestinoOperacao.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<NfeDestinoOperacao> getList() {
        List list = Arrays.asList(NfeDestinoOperacao.values());
        Collections.sort(list, new Comparator<NfeDestinoOperacao>() {
            @Override
            public int compare(NfeDestinoOperacao e1, NfeDestinoOperacao e2) {
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
