package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum RelatorioTipo {

    RECIBO(1, "/relatorio/recibo.jasper"),
    NFE(2, "/relatorio/SIDTMDanfe.jasper");

    private int cod;
    private String descricao;

    private RelatorioTipo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static RelatorioTipo toEnum(Integer cod) {
        if (cod == null) return null;
        for (RelatorioTipo tipo : RelatorioTipo.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
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
