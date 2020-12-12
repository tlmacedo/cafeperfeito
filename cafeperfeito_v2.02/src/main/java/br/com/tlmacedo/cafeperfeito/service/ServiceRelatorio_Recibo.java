package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.dao.RecebimentoDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.RelatorioTipo;
import br.com.tlmacedo.cafeperfeito.model.vo.ContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProdutoProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.UsuarioLogado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceRelatorio_Recibo {

    ContasAReceber aReceber;
    Recebimento recebimento;

    public void imprimeRecibo(Recebimento recebimento) throws Exception {
        setRecebimento(recebimento);
        if (getRecebimento().getDocumento() == null || getRecebimento().getDocumento().equals("")) {
            getRecebimento().setDocumento(ServiceValidarDado.gerarCodigoCafePerfeito(Recebimento.class, getRecebimento().dtCadastroProperty().getValue().toLocalDate()));
            setRecebimento(new RecebimentoDAO().merger(getRecebimento()));
        }
        getRecebimento().setEmissorRecibo(UsuarioLogado.getUsuario().getLojaAtivo());

        List list = new ArrayList();
        list.add(getRecebimento());

        final String[] descricaoProduto = new String[1];
        int qtd = getRecebimento().contasAReceberProperty().getValue().saidaProdutoProperty().getValue().getSaidaProdutoProdutoList().stream()
                .filter(produto -> {
                    if (produto.descricaoProperty().getValue().toLowerCase().contains("supremo")) {
                        descricaoProduto[0] = produto.getDescricao();
                        return true;
                    }
                    return false;
                })
                .mapToInt(SaidaProdutoProduto::getQtd).sum();

        Map paramentros = new HashMap();
        paramentros.put("valorExtenso", new ServiceNumeroExtenso(getRecebimento().valorProperty().get()).toString().toUpperCase());
        paramentros.put("referenteA", String.format("%02d KG de %s", qtd, descricaoProduto[0]).toUpperCase());
        new ServiceRelatorio().gerar(RelatorioTipo.RECIBO, paramentros, list);
    }


    /**
     * Begin Getters and Setters
     */

    public ContasAReceber getaReceber() {
        return aReceber;
    }

    public void setaReceber(ContasAReceber aReceber) {
        this.aReceber = aReceber;
    }

    public Recebimento getRecebimento() {
        return recebimento;
    }

    public void setRecebimento(Recebimento recebimento) {
        this.recebimento = recebimento;
    }

    /**
     * END Getters and Setters
     */
}
