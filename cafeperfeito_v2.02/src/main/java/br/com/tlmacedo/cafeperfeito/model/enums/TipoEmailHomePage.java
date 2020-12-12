package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum TipoEmailHomePage {

    HOME_PAGE(0, "Home page"),
    EMAIL(1, "email");

    private Integer cod;
    private String descricao;

    private TipoEmailHomePage(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<TipoEmailHomePage> getList() {
        List list = Arrays.asList(TipoEmailHomePage.values());
        Collections.sort(list, new Comparator<TipoEmailHomePage>() {
            @Override
            public int compare(TipoEmailHomePage e1, TipoEmailHomePage e2) {
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
