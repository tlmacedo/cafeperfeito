package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum TipoCodigoCFOP {

    COMERCIALIZACAO(102, "comercialização"),
    BONIFICACAO(910, "bonif"),
    CONSUMO(557, "retirada"),
    CORTESIA(910, "cortesia"),
    AMOSTRA(911, "amostra"),
    TESTE(912, "teste"),
    AVARIA(927, "avaria");

    private Integer cod;
    private String descricao;

    private TipoCodigoCFOP(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<TipoCodigoCFOP> getList() {
        List list = Arrays.asList(TipoCodigoCFOP.values());
        Collections.sort(list, new Comparator<TipoCodigoCFOP>() {
            @Override
            public int compare(TipoCodigoCFOP e1, TipoCodigoCFOP e2) {
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
