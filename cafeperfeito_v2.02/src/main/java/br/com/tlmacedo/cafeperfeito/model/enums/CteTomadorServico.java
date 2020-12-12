package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum CteTomadorServico {

    //    NULL(0, ""),
    REMETENTE(0, "Remetente"),
    EXPEDIDOR(1, "Expedidor"),
    RECEBEDOR(2, "Recebedor"),
    DESTINATARIO(3, "Destinatário");

    private Integer cod;
    private String descricao;

    private CteTomadorServico(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

//    public static CteTomadorServico toEnum(Integer cod) {
//        if (cod == null) return null;
//        for (CteTomadorServico tipo : CteTomadorServico.values())
//            if (cod.equals(tipo.getCod()))
//                return tipo;
//        throw new IllegalArgumentException("Id inválido");
//    }

    public static List<CteTomadorServico> getList() {
        List list = Arrays.asList(CteTomadorServico.values());
        Collections.sort(list, new Comparator<CteTomadorServico>() {
            @Override
            public int compare(CteTomadorServico e1, CteTomadorServico e2) {
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
