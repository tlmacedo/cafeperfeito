package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum RelatorioTipo {

    RECIBO(0, "/relatorio/recibo.jasper"),
    NFE(1, "/relatorio/danfe.jasper"),
    TESTE(2, "/relatorio/Blank_A4.jasper");

    private Integer cod;
    private String descricao;

    private RelatorioTipo(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<RelatorioTipo> getList() {
        List list = Arrays.asList(RelatorioTipo.values());
        Collections.sort(list, new Comparator<RelatorioTipo>() {
            @Override
            public int compare(RelatorioTipo e1, RelatorioTipo e2) {
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
