package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.dao.RecebimentoDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.RelatorioTipo;
import br.com.tlmacedo.cafeperfeito.model.vo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATAHORA_HM;

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

        String paramReferenteA = null;
        try {
            SaidaProdutoNfe nfe = getRecebimento().getContasAReceber().getSaidaProduto().getSaidaProdutoNfeList().get(0);
            paramReferenteA = String.format("NF:(%d) série:(%d) emissão: %s", nfe.getNumero(), nfe.getSerie(),
                    nfe.getDtHoraEmissao().format(DTF_DATAHORA_HM));
        } catch (Exception ex) {
            if (!(ex instanceof IndexOutOfBoundsException)) {
                ex.printStackTrace();
            } else {
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

                paramReferenteA = String.format("%02d KG de %s", qtd, descricaoProduto[0]);
            }
        }
        Map paramentros = new HashMap();
        paramentros.put("valorExtenso", new ServiceNumeroExtenso(getRecebimento().valorProperty().get()).toString().toUpperCase());
        paramentros.put("referenteA", paramReferenteA.toUpperCase());
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
