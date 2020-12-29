package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum NfeModalidadeFrete {

    CIF(0, "Contratação do Frete por conta do Remetente (CIF)"),
    FOB(1, "Contratação do Frete por conta do Destinatário (FOB)"),
    TERCEIROS(2, "Contratação do Frete por conta de Terceiros"),
    REMETENTE(3, "Transporte Próprio por conta do Remetente"),
    DESTINATARIO(4, "Transporte Próprio por conta do Destinatário"),
    SEM_OCORRENCIA(9, "Sem Ocorrência de Transporte");

    private int cod;
    private String descricao;

    private NfeModalidadeFrete(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static NfeModalidadeFrete toEnum(Integer cod) {
        if (cod == null) return null;
        for (NfeModalidadeFrete tipo : NfeModalidadeFrete.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<NfeModalidadeFrete> getList() {
        List list = Arrays.asList(NfeModalidadeFrete.values());
        Collections.sort(list, new Comparator<NfeModalidadeFrete>() {
            @Override
            public int compare(NfeModalidadeFrete e1, NfeModalidadeFrete e2) {
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
