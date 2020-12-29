package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum NfePresencaComprador {

    NULL(0, ""),
    PRESENCIAL(1, "Operação presencial"),
    NAOPRESENCIAL_INTERNET(2, "Operação não presencial, pela Internet"),
    NAOPRESENCIAL_TELEATENDIMENTO(3, "Operação não presencial, Teleatendimento"),
    NFCE_ENTREGA(4, "NFC-e em operação com entrega a domicílio"),
    NAOPRESENCIAL_OUTROS(9, "Operação não presencial, outros");

    private int cod;
    private String descricao;

    private NfePresencaComprador(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static NfePresencaComprador toEnum(Integer cod) {
        if (cod == null) return null;
        for (NfePresencaComprador tipo : NfePresencaComprador.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<NfePresencaComprador> getList() {
        List list = Arrays.asList(NfePresencaComprador.values());
        Collections.sort(list, new Comparator<NfePresencaComprador>() {
            @Override
            public int compare(NfePresencaComprador e1, NfePresencaComprador e2) {
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
