package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum EnumCargo {

    NULL(0, ""),
    ADMINISTRADOR(1, "Administradr"),
    SUPERVISOR(2, "Supervicor"),
    GERENTE(3, "Gerente"),
    CONTADOR(4, "Contador"),
    ASSISTENTE(5, "Assistente"),
    VENDEDOR(6, "Vendedor"),
    USUARIO(88, "Usuário"),
    ENTREGADOR(99, "Entregador");

    private Integer cod;
    private String descricao;

    EnumCargo(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EnumCargo toEnum(Integer codigo) {

        if (codigo == null) return null;
        for (EnumCargo guest : EnumCargo.values())
            if (guest.getCod().equals(codigo))
                return guest;
        throw new IllegalArgumentException("Id inválido!");

    }

    public static List<EnumCargo> getList() {

        List<EnumCargo> list = Arrays.asList(EnumCargo.values());
        list.sort(Comparator.comparing(EnumCargo::getDescricao));
        return list;

    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
