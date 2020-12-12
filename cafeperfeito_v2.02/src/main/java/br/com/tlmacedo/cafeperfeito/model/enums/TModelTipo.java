package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum TModelTipo {

    PROD_VENDA(0, "Produto venda"),
    PROD_CADASTRO(1, "Produto cadastro"),
    PROD_COMPRA(2, "Produto compra"),
    PEDIDO_RECIBO(3, "Pedido recibo"),
    PEDIDO_NFE(4, "Pedido nfe");

    private Integer cod;
    private String descricao;

    private TModelTipo(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<TModelTipo> getList() {
        List list = Arrays.asList(TModelTipo.values());
        Collections.sort(list, new Comparator<TModelTipo>() {
            @Override
            public int compare(TModelTipo e1, TModelTipo e2) {
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
