package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum NfeCobrancaDuplicataNumero {

    N001(1, "1"),
    N002(2, "2"),
    N003(3, "3"),
    N004(4, "4");

    private Integer cod;
    private String descricao;

    private NfeCobrancaDuplicataNumero(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<NfeCobrancaDuplicataNumero> getList() {
        List list = Arrays.asList(NfeCobrancaDuplicataNumero.values());
        Collections.sort(list, new Comparator<NfeCobrancaDuplicataNumero>() {
            @Override
            public int compare(NfeCobrancaDuplicataNumero e1, NfeCobrancaDuplicataNumero e2) {
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
