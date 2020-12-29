package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum UnidadeComercial {

    NULL(0, ""),
    UNIDADE(1, "UND"),
    PACOTE(2, "PCT"),
    PESO(3, "KG"),
    FARDO(4, "FD"),
    CAIXA(5, "CX"),
    VIDRO(6, "VD"),
    DUZIA(7, "DZ"),
    LATA(8, "LT");

    private int cod;
    private String descricao;

    private UnidadeComercial(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static UnidadeComercial toEnum(Integer cod) {
        if (cod == null) return null;
        for (UnidadeComercial tipo : UnidadeComercial.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<UnidadeComercial> getList() {
        List list = Arrays.asList(UnidadeComercial.values());
        Collections.sort(list, new Comparator<UnidadeComercial>() {
            @Override
            public int compare(UnidadeComercial e1, UnidadeComercial e2) {
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
