package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum TipoContaBanco {

    NULL(0, ""),
    CONTA_CORRENTE(1, "Conta corrente"),
    POUPANCA(2, "Poupança"),
    INVESTIMENTOS(3, "Investimentos"),
    OUTROS(4, "Outros");


    private int cod;
    private String descricao;

    private TipoContaBanco(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static TipoContaBanco toEnum(Integer cod) {
        if (cod == null) return null;
        for (TipoContaBanco tipo : TipoContaBanco.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<TipoContaBanco> getList() {
        List list = Arrays.asList(TipoContaBanco.values());
        Collections.sort(list, new Comparator<TipoContaBanco>() {
            @Override
            public int compare(TipoContaBanco e1, TipoContaBanco e2) {
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
