package br.com.tlmacedo.cafeperfeito.model.enums;

public enum StatusBarContasAReceber {

    DIGITACAO(0, "[Insert-Novo recebimento]  [Ctrl+P-Imprimir recibo]  [Ctrl+O-Imprimir nfe]  [F4-Editar recebimento]  [F6-Cliente]  [F7-Pesquisa]  [F8-Datas]  [F9-Contas]  [F12-Sair]");

    private Integer cod;
    private String descricao;

    private StatusBarContasAReceber(Integer cod, String descricao) {
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
