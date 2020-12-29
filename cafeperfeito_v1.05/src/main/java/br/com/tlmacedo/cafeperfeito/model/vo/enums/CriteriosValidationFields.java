package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum CriteriosValidationFields {

    NOT_NULL(0, "notNull"),
    MIN_SIZE(1, "minSize"),
    MIN_BIG(2, "minBigDecimal"),
    MIN_DATA(3, "minData"),
    MIN_CBO(4, "minCbo"),
    VAL_CNPJ(5, "valCnpj");

    private int cod;
    private String descricao;

    private CriteriosValidationFields(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static CriteriosValidationFields toEnum(Integer cod) {
        if (cod == null) return null;
        for (CriteriosValidationFields tipo : CriteriosValidationFields.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<CriteriosValidationFields> getList() {
        List list = Arrays.asList(CriteriosValidationFields.values());
        Collections.sort(list, new Comparator<CriteriosValidationFields>() {
            @Override
            public int compare(CriteriosValidationFields e1, CriteriosValidationFields e2) {
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
