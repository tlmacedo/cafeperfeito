package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum AccessGuest {

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

    private AccessGuest(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<AccessGuest> getList() {
        List list = Arrays.asList(AccessGuest.values());
        Collections.sort(list, new Comparator<AccessGuest>() {
            @Override
            public int compare(AccessGuest e1, AccessGuest e2) {
                return e1.getDescricao().compareTo(e2.getDescricao());
            }
        });
        return list;
    }

    public Integer getCod() {
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
