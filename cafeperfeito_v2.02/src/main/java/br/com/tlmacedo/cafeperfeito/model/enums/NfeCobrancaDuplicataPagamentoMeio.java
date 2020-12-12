package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum NfeCobrancaDuplicataPagamentoMeio {

    DINHEIRO(1, "Dinheiro"),
    CHEQUE(2, "Cheque"),
    CREDITO(3, "Cartão de Crédido"),
    DEBITO(4, "Cartão de Débito"),
    LOJA(5, "Crédido Loja"),
    ALIMENTACAO(10, "Vale Alimentação"),
    REFEICAO(11, "Vale Refeição"),
    PRESENTE(12, "Vale Presente"),
    COMBUSTIVEL(13, "Vale Combustível"),
    BOLETO(15, "Boleto Bancário"),
    SEM_PAGAMENTO(90, "Sem pagamento"),
    OUTROS(99, "Outros");

    private Integer cod;
    private String descricao;

    private NfeCobrancaDuplicataPagamentoMeio(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<NfeCobrancaDuplicataPagamentoMeio> getList() {
        List list = Arrays.asList(NfeCobrancaDuplicataPagamentoMeio.values());
        Collections.sort(list, new Comparator<NfeCobrancaDuplicataPagamentoMeio>() {
            @Override
            public int compare(NfeCobrancaDuplicataPagamentoMeio e1, NfeCobrancaDuplicataPagamentoMeio e2) {
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
