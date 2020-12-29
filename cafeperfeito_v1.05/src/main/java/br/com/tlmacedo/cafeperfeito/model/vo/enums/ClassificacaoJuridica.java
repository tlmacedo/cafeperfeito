package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum ClassificacaoJuridica {

    NULL(0, ""),
    PESSOAFISICA(1, "Física"),
    PESSOAJURIDICA(2, "Jurídica");

    private int cod;
    private String descricao;

    private ClassificacaoJuridica(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static ClassificacaoJuridica toEnum(Integer cod) {
        if (cod == null) return null;
        for (ClassificacaoJuridica tipo : ClassificacaoJuridica.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<ClassificacaoJuridica> getList() {
        List list = Arrays.asList(ClassificacaoJuridica.values());
        Collections.sort(list, new Comparator<ClassificacaoJuridica>() {
            @Override
            public int compare(ClassificacaoJuridica e1, ClassificacaoJuridica e2) {
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
