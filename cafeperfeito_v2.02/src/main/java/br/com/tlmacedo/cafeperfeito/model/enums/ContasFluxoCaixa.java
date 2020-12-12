package br.com.tlmacedo.cafeperfeito.model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum ContasFluxoCaixa {


    DINHEIRO(0, "Dinheiro"),
    CHEQUE(1, "Cheque"),
    BOLETO(2, "Boleto"),
    TRANSFERENCIA(3, "Transferência"),
    ORDEM_BANCARIA(4, "Ordem bancária"),
    CARTAO_DEBITO(5, "Cartão de Débito"),
    CARTAO_CREDITO(6, "Cartão de Crédito"),
    CLIENTE(7, "Clientes"),
    OUTRO_RECEBIMENTO_1(8, "Outros Recebimentos 1"),
    OUTRO_RECEBIMENTO_2(9, "Outros Recebimentos 2"),
    OUTRO_RECEBIMENTO_3(10, "Outros Recebimentos 3"),
    OUTRO_RECEBIMENTO_4(11, "Outros Recebimentos 4"),
    OUTRO_RECEBIMENTO_5(12, "Outros Recebimentos 5"),
    OUTRO_RECEBIMENTO_6(13, "Outros Recebimentos 6"),
    OUTRO_RECEBIMENTO_7(14, "Outros Recebimentos 7"),
    OUTRO_RECEBIMENTO_8(15, "Outros Recebimentos 8"),
    OUTRO_RECEBIMENTO_9(16, "Outros Recebimentos 9"),
    OUTRO_RECEBIMENTO_10(17, "Outros Recebimentos 10"),
    OUTRO_RECEBIMENTO_11(18, "Outros Recebimentos 11"),
    OUTRO_RECEBIMENTO_12(19, "Outros Recebimentos 12"),
    OUTRO_RECEBIMENTO_13(20, "Outros Recebimentos 13"),
    OUTRO_RECEBIMENTO_14(21, "Outros Recebimentos 14"),
    OUTRO_RECEBIMENTO_15(22, "Outros Recebimentos 15"),
    RETIRADA_SOCIO(23, "Retirada de Sócio"),
    TRANSFERENCIA_BANCARIA(24, "Transferências Bancárias"),
    FORNECEDOR(25, "Fornecedores"),
    DESPESA_FINANCEIRA(26, "Despesas Financeiras"),
    IMPOSTO(27, "Impostos"),
    FOLHA_DE_PAGAMENTO(28, "Despesas com Pessoal"),
    SAQUE(29, "Saque"),
    DESPESA_ADMINISTRATIVA(30, "Despesas Administrativas"),
    CONTADOR(31, "Contador"),
    OUTRA_SAIDA_1(32, "Outras Saídas 1"),
    OUTRA_SAIDA_2(33, "Outras Saídas 2"),
    OUTRA_SAIDA_3(34, "Outras Saídas 3"),
    OUTRA_SAIDA_4(35, "Outras Saídas 4"),
    OUTRA_SAIDA_5(36, "Outras Saídas 5"),
    OUTRA_SAIDA_6(37, "Outras Saídas 6"),
    OUTRA_SAIDA_7(38, "Outras Saídas 7"),
    OUTRA_SAIDA_8(39, "Outras Saídas 8"),
    OUTRA_SAIDA_9(40, "Outras Saídas 9"),
    OUTRA_SAIDA_10(41, "Outras Saídas 10"),
    OUTRA_SAIDA_11(42, "Outras Saídas 11");


    private Integer cod;
    private String descricao;

    private ContasFluxoCaixa(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static List<ContasFluxoCaixa> getList() {
        List list = Arrays.asList(ContasFluxoCaixa.values());
        Collections.sort(list, new Comparator<ContasFluxoCaixa>() {
            @Override
            public int compare(ContasFluxoCaixa e1, ContasFluxoCaixa e2) {
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
