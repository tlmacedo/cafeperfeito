package br.com.tlmacedo.cafeperfeito.service.serialize_deserialize;

import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.FiscalFreteSituacaoTributariaDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.FiscalTributosSefazAmDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.ProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;

//import br.com.tlmacedo.cafeperfeito.model.vo.enums.CteTomadorServico;
//import br.com.tlmacedo.cafeperfeito.model.vo.enums.NfeCteModelo;
//import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoEntrada;

//import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATA;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-17
 * Time: 17:52
 */

public class EntradaProdutoDeserializer extends StdDeserializer<EntradaProduto> {

    public EntradaProdutoDeserializer() {
        this(null);
    }

    public EntradaProdutoDeserializer(Class<?> vc) {
        super(vc);
    }


    @SuppressWarnings("Duplicates")
    @Override
    public EntradaProduto deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = node.get("id").intValue();
        int situacao = node.get("situacao").intValue();
        Empresa loja = new EmpresaDAO().getById(Empresa.class, node.get("loja").get("id").longValue());

        JsonNode nodeNfe = node.get("entradaNfe");

        EntradaNfe entradaNfe = null;
        EntradaFiscal nfeEntradaFiscal = null;
        if (!nodeNfe.get("entradaFiscal").asText().equals("null")) {
            JsonNode nodeNfeEntradaFiscal = nodeNfe.get("entradaFiscal");
            nfeEntradaFiscal = new EntradaFiscal();
            nfeEntradaFiscal.setId(nodeNfeEntradaFiscal.get("id").longValue());
            nfeEntradaFiscal.setControle(nodeNfeEntradaFiscal.get("controle").asText());
            nfeEntradaFiscal.setOrigem(nodeNfeEntradaFiscal.get("docOrigem").asText());
            nfeEntradaFiscal.setVlrJuros(nodeNfeEntradaFiscal.get("vlrJuros").decimalValue().setScale(2));
            nfeEntradaFiscal.setVlrMulta(nodeNfeEntradaFiscal.get("vlrMulta").decimalValue().setScale(2));
            nfeEntradaFiscal.setVlrDocumento(nodeNfeEntradaFiscal.get("vlrNfe").decimalValue().setScale(2));
            nfeEntradaFiscal.setVlrTaxa(nodeNfeEntradaFiscal.get("vlrTaxa").decimalValue().setScale(2));
            nfeEntradaFiscal.setVlrTributo(nodeNfeEntradaFiscal.get("vlrTributo").decimalValue().setScale(2));
            nfeEntradaFiscal.setTributosSefazAm(new FiscalTributosSefazAmDAO().getById(FiscalTributosSefazAm.class,
                    nodeNfeEntradaFiscal.get("tributoSefazAm").get("id").longValue()));
        }
        if (nodeNfe != null) {
            entradaNfe = new EntradaNfe();
            entradaNfe.setId(nodeNfe.get("id").longValue());
            entradaNfe.setChave(nodeNfe.get("chave").asText());
            entradaNfe.setNumero(nodeNfe.get("numero").asText());
            entradaNfe.setSerie(nodeNfe.get("serie").asText());
            entradaNfe.setModelo(nodeNfe.get("modelo").intValue());
            entradaNfe.setDtEmissao(LocalDate.parse(nodeNfe.get("dataEmissao").asText(), DTF_DATA));
            entradaNfe.setDtEntrada(LocalDate.parse(nodeNfe.get("dataEntrada").asText(), DTF_DATA));
            entradaNfe.setFornecedor(new EmpresaDAO().getById(Empresa.class, nodeNfe.get("emissor").get("id").longValue()));
            entradaNfe.setEntradaFiscal(nfeEntradaFiscal);
        }

        EntradaCte entradaCte = null;
        EntradaFiscal cteEntradaFiscal = null;
        if (!node.get("entradaCte").asText().equals("null")) {
            JsonNode nodeCte = node.get("entradaCte");
            if (!nodeCte.get("entradaFiscal").asText().equals("null")) {
                JsonNode nodeCteEntradaFiscal = nodeCte.get("entradaFiscal");
                cteEntradaFiscal = new EntradaFiscal();
                cteEntradaFiscal.setId(nodeCteEntradaFiscal.get("id").longValue());
                cteEntradaFiscal.setControle(nodeCteEntradaFiscal.get("controle").asText());
                cteEntradaFiscal.setOrigem(nodeCteEntradaFiscal.get("docOrigem").asText());
                cteEntradaFiscal.setVlrJuros(nodeCteEntradaFiscal.get("vlrJuros").decimalValue().setScale(2));
                cteEntradaFiscal.setVlrMulta(nodeCteEntradaFiscal.get("vlrMulta").decimalValue().setScale(2));
                cteEntradaFiscal.setVlrDocumento(nodeCteEntradaFiscal.get("vlrNfe").decimalValue().setScale(2));
                cteEntradaFiscal.setVlrTaxa(nodeCteEntradaFiscal.get("vlrTaxa").decimalValue().setScale(2));
                cteEntradaFiscal.setVlrTributo(nodeCteEntradaFiscal.get("vlrTributo").decimalValue().setScale(2));
                cteEntradaFiscal.setTributosSefazAm(new FiscalTributosSefazAmDAO().getById(FiscalTributosSefazAm.class,
                        nodeCteEntradaFiscal.get("tributoSefazAm").get("id").longValue()));
            }

            if (nodeCte.asText() != null) {
                entradaCte = new EntradaCte();
                entradaCte.setId(nodeCte.get("id").longValue());
                entradaCte.setChave(nodeCte.get("chave").asText());
                entradaCte.setNumero(nodeCte.get("numero").asText());
                entradaCte.setSerie(nodeCte.get("serie").asText());
                entradaCte.setQtdVolume(nodeCte.get("qtdVolume").intValue());
                entradaCte.setTomadorServico(nodeCte.get("tomadorServico").intValue());
                entradaCte.setModelo(nodeCte.get("modelo").intValue());
                entradaCte.setVlrCte(nodeCte.get("vlrCte").decimalValue().setScale(2));
                entradaCte.setPesoBruto(nodeCte.get("pesoBruto").decimalValue().setScale(2));
                entradaCte.setVlrFreteBruto(nodeCte.get("vlrFreteBruto").decimalValue().setScale(2));
                entradaCte.setVlrTaxas(nodeCte.get("vlrTaxas").decimalValue().setScale(2));
                entradaCte.setVlrColeta(nodeCte.get("vlrColeta").decimalValue().setScale(2));
                entradaCte.setVlrImpostoFrete(nodeCte.get("vlrImpostoFrete").decimalValue().setScale(2));
                entradaCte.setDtEmissao(LocalDate.parse(nodeCte.get("dataEmissao").asText(), DTF_DATA));
                entradaCte.setSituacaoTributaria(new FiscalFreteSituacaoTributariaDAO().getById(FiscalFreteSituacaoTributaria.class,
                        nodeCte.get("situacaoTributaria").get("id").longValue()));
                entradaCte.setTransportadora(new EmpresaDAO().getById(Empresa.class, nodeCte.get("emissor").get("id").longValue()));
                entradaCte.setEntradaFiscal(cteEntradaFiscal);

            }
        }

        JsonNode nodeProdutos = node.get("produtos");
        List<EntradaProdutoProduto> produtoList = new ArrayList<>();
        if (nodeProdutos.size() != 0)
            for (JsonNode getProduto : nodeProdutos) {
                EntradaProdutoProduto produtoEntrada = new EntradaProdutoProduto();
                produtoEntrada.setId(getProduto.get("id").longValue());
                produtoEntrada.setCodigo(getProduto.get("codigo").asText());
                produtoEntrada.setDescricao(getProduto.get("descricao").asText());
                produtoEntrada.setLote(getProduto.get("lote").asText());
                produtoEntrada.setDtValidade(LocalDate.parse(getProduto.get("validade").asText(), DTF_DATA));
                produtoEntrada.setQtd(getProduto.get("qtd").intValue());
                produtoEntrada.setVlrUnitario(getProduto.get("vlrFabrica").decimalValue().setScale(2));
                produtoEntrada.setVlrBruto(getProduto.get("vlrBruto").decimalValue().setScale(2));
                produtoEntrada.setVlrImposto(getProduto.get("vlrImposto").decimalValue().setScale(2));
                produtoEntrada.setVlrDesconto(getProduto.get("vlrDesconto").decimalValue().setScale(2));
                produtoEntrada.setVlrLiquido(getProduto.get("vlrLiquido").decimalValue().setScale(2));
                produtoEntrada.setEstoque(getProduto.get("estoque").intValue());
                produtoEntrada.setVarejo(getProduto.get("varejo").intValue());
                produtoEntrada.setVolume(getProduto.get("volume").intValue());
                produtoEntrada.setProduto(new ProdutoDAO().getById(Produto.class, getProduto.get("produto_id").longValue()));
                produtoList.add(produtoEntrada);
            }

        EntradaProduto entradaProduto = new EntradaProduto();
        entradaProduto.situacaoProperty().setValue(situacao);
        entradaProduto.lojaProperty().setValue(loja);
        entradaProduto.entradaNfeProperty().setValue(entradaNfe);
        entradaProduto.entradaCteProperty().setValue(entradaCte);
        entradaProduto.setEntradaProdutoProdutoList(produtoList);
        return entradaProduto;
    }
}
