package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum GuestAccess {

    NULL(0, ""),
    ADMINISTRADOR(1, "Administradr"),
    SUPERVISOR(2, "Supervicor"),
    GERENTE(3, "Gerente"),
    CONTADOR(4, "Contador"),
    ASSISTENTE(5, "Assistente"),
    VENDEDOR(6, "Vendedor"),
    USUARIO(88, "Usuário"),
    ENTREGADOR(99, "Entregador");

    private int cod;
    private String descricao;

    private GuestAccess(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static GuestAccess toEnum(Integer cod) {
        if (cod == null) return null;
        for (GuestAccess tipo : GuestAccess.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<GuestAccess> getList() {
        List list = Arrays.asList(GuestAccess.values());
        Collections.sort(list, new Comparator<GuestAccess>() {
            @Override
            public int compare(GuestAccess e1, GuestAccess e2) {
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
