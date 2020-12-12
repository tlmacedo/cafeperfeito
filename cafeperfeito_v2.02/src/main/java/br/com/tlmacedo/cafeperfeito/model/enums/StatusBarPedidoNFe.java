package br.com.tlmacedo.cafeperfeito.model.enums;

public enum StatusBarPedidoNFe {

    DIGITACAO(0, "[Insert-Nova NFe]  [Ctrl+P-Imprimir recibo]  [Ctrl+N-Imprimir NFe]  [F4-Editar NFe]  [F6-Cliente]  [F7-Pesquisa]  [F8-Datas]  [F9-Pedidos]  [F12-Sair]");

    private Integer cod;
    private String descricao;

    private StatusBarPedidoNFe(Integer cod, String descricao) {
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
