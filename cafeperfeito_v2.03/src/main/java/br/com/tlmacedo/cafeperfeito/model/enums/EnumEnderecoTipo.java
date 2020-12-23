package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum EnumEnderecoTipo {

    PRINCIPAL(0, "Principal"),
    ENTREGA(1, "Entrega"),
    COBRANCA(2, "Cobrança"),
    CORRESPONDENCIA(3, "Correspondência"),
    RESIDENCIAL(4, "Residencial"),
    RECADO(5, "Recado");

    private Integer codigo;
    private String descricao;

    EnumEnderecoTipo(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static EnumEnderecoTipo toEnum(Integer cod) {

        if (cod == null) return null;
        for (EnumEnderecoTipo enderecoTipo : EnumEnderecoTipo.values())
            if (enderecoTipo.getCodigo().equals(cod))
                return enderecoTipo;
        throw new IllegalArgumentException("Id inválido!");

    }

    public static List<EnumEnderecoTipo> getList() {

        List<EnumEnderecoTipo> list = Arrays.asList(EnumEnderecoTipo.values());
        list.sort(Comparator.comparing(EnumEnderecoTipo::getDescricao));
        return list;

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
