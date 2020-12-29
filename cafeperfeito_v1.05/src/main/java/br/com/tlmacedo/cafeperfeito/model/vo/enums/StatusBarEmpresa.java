package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum StatusBarEmpresa {
    PESQUISA(0, "[F1-Novo]  [F4-Editar]  [F7-Pesquisar]  [F8-Filtro tipo]  [F9-Filtro situação]  [F12-Sair]"),
    EDITAR(1, "[F3-Cancelar edição]  [F5-Atualizar]  [F6-Cnpj]  [F10-Endereços]  [F11-Contatos]"),
    INCLUIR(2, "[F2-Incluir]  [F3-Cancelar inclusão]  [F6-Cnpj]  [F10-Endereços]  [F11-Contatos]");

    private int cod;
    private String descricao;

    private StatusBarEmpresa(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static StatusBarEmpresa toEnum(Integer cod) {
        if (cod == null) return null;
        for (StatusBarEmpresa tipo : StatusBarEmpresa.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<StatusBarEmpresa> getList() {
        List list = Arrays.asList(StatusBarEmpresa.values());
        Collections.sort(list, new Comparator<StatusBarEmpresa>() {
            @Override
            public int compare(StatusBarEmpresa e1, StatusBarEmpresa e2) {
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

//    @Override
//    public String toString() {
//        return getDescricao();
//    }

}
