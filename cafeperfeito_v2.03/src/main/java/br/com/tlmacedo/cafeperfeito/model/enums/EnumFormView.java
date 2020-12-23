package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum EnumFormView {

    LOGIN(0, "Login"),
    PRINCIPAL(1, "Principal"),
    EMPRESA(2, "Cadastro de Empresa");

    private Integer cod;
    private String descricao;

    EnumFormView(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EnumFormView toEnum(Integer codigo) {
        if (codigo == null) return null;
        for (EnumFormView view : EnumFormView.values())
            if (view.getCod().equals(codigo))
                return view;
        throw new IllegalArgumentException("Id inválido!");
    }

    public static EnumFormView toEnum(String descricao) {
        if (descricao == null) return null;
        for (EnumFormView view : EnumFormView.values())
            if (view.toString().equals(descricao))
                return view;
        throw new IllegalArgumentException("Descricao inválida!");
    }

    public static List<EnumFormView> getList() {
        List<EnumFormView> list = Arrays.asList(EnumFormView.values());
        list.sort(Comparator.comparing(EnumFormView::getDescricao));
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
