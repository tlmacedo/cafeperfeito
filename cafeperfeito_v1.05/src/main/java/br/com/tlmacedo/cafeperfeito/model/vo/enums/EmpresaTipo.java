package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum EmpresaTipo {
    NULL(0, ""),
    CLIENTE(1, "Cliente"),
    FORNECEDOR(2, "Fornecedor"),
    TRANSPORTADORA(3, "Transportadora");

    private int cod;
    private String descricao;

    private EmpresaTipo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EmpresaTipo toEnum(Integer cod) {
        if (cod == null) return null;
        for (EmpresaTipo tipo : EmpresaTipo.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<EmpresaTipo> getList() {
        List list = Arrays.asList(EmpresaTipo.values());
        Collections.sort(list, new Comparator<EmpresaTipo>() {
            @Override
            public int compare(EmpresaTipo e1, EmpresaTipo e2) {
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
