package br.com.tlmacedo.cafeperfeito.model.vo.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum NfeStatusSEFAZ {

    NULL(0, ""),
    DIGITACAO(1, "Digitação"),
    ASSINADA(2, "Assinada"),
    AUTORIZADA(100, "Autorizado o uso da NF-e"),
    INUTILIZADA(102, "Inutilização de número homologado"),
    LOTE_RECEBIDO(103, "Lote recebido com sucesso"),
    LOTE_PROCESSADO(104, "Lote processado"),
    LOTE_PROCESSAMENTO(105, "Lote em processamento"),
    LOTE_NAO_LOCALIZADO(106, "Lote não localizado"),
    PARALIZACAO_MANUTENCAO(108, "Paralização para manutenção"),
    PARALIZACAO_PROBLEMAS(109, "Paralização por problemas tecnicos"),
    DENEGADA(110, "Uso Denegado"),
    CONSULTA_CAD_UMA_OCORRENCIA(111, "Consulta cadastro com uma ocorrência"),
    CONSULTA_CAD_VAIRAS_OCORRENCIAS(112, "Consulta cadastro com mais de uma ocorrência");

    private int cod;
    private String descricao;

    private NfeStatusSEFAZ(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static NfeStatusSEFAZ toEnum(Integer cod) {
        if (cod == null) return null;
        for (NfeStatusSEFAZ tipo : NfeStatusSEFAZ.values())
            if (cod.equals(tipo.getCod()))
                return tipo;
        throw new IllegalArgumentException("Id inválido");
    }

    public static List<NfeStatusSEFAZ> getList() {
        List list = Arrays.asList(NfeStatusSEFAZ.values());
        Collections.sort(list, new Comparator<NfeStatusSEFAZ>() {
            @Override
            public int compare(NfeStatusSEFAZ e1, NfeStatusSEFAZ e2) {
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
