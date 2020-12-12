package br.com.tlmacedo.cafeperfeito.model.enums;

public enum StatusSaidaProduto {

    DIGITACAO(0, "[F1-Novo]  [F2-Finalizar venda]  [F6-Cliente]  [F7-Pesquisa produto]  [F8-Itens venda]  [F9-nfe]  [F10-Print Lote NFe]  [F12-Sair]"),
    FINALIZADA(1, "[F12-Sair]");

    private Integer cod;
    private String descricao;

    StatusSaidaProduto(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
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
