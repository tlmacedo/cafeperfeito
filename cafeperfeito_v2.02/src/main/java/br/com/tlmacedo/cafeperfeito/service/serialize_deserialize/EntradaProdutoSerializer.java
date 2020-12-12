package br.com.tlmacedo.cafeperfeito.service.serialize_deserialize;

import br.com.tlmacedo.cafeperfeito.model.dao.FiscalFreteSituacaoTributariaDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.FiscalTributosSefazAmDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-18
 * Time: 12:54
 */

public class EntradaProdutoSerializer extends StdSerializer<EntradaProduto> {

    public EntradaProdutoSerializer() {
        this(null);
    }

    public EntradaProdutoSerializer(Class<EntradaProduto> t) {
        super(t);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void serialize(EntradaProduto entradaProduto, JsonGenerator jgen, SerializerProvider provider) {

        try {
            EntradaNfe entradaNfe;
            EntradaCte entradaCte;
            EntradaFiscal entradaFiscalNfe;
            EntradaFiscal entradaFiscalCte;
            List<EntradaProdutoProduto> entradaProdutoProdutoList;

            jgen.writeStartObject();
            jgen.writeNumberField("id", entradaProduto.getId());
            jgen.writeNumberField("situacao", entradaProduto.getSituacao());

            Empresa loja = entradaProduto.getLoja();
            jgen.writeObjectFieldStart("loja");
            jgen.writeNumberField("id", loja.getId());
            jgen.writeStringField("cnpj", loja.getCnpj());
            jgen.writeStringField("razao", loja.getRazao());
            jgen.writeStringField("fantasia", loja.getFantasia());
            jgen.writeEndObject();

            entradaNfe = entradaProduto.getEntradaNfe();
            jgen.writeObjectFieldStart("entradaNfe");
            jgen.writeNumberField("id", entradaNfe.getId());
            jgen.writeStringField("chave", entradaNfe.getChave());
            jgen.writeStringField("numero", entradaNfe.getNumero());
            jgen.writeStringField("serie", entradaNfe.getSerie());
            jgen.writeNumberField("modelo", entradaNfe.getModelo());
            jgen.writeStringField("dataEmissao", entradaNfe.getDtEmissao().format(DTF_DATA));
            jgen.writeStringField("dataEntrada", entradaNfe.getDtEntrada().format(DTF_DATA));

            Empresa emissorNfe = entradaNfe.getFornecedor();
            jgen.writeObjectFieldStart("emissor");
            jgen.writeNumberField("id", emissorNfe.getId());
            jgen.writeStringField("cnpj", emissorNfe.getCnpj());
            jgen.writeStringField("razao", emissorNfe.getRazao());
            jgen.writeStringField("fantasia", emissorNfe.getFantasia());
            jgen.writeEndObject();

            entradaFiscalNfe = entradaNfe.getEntradaFiscal();
            if (entradaFiscalNfe != null) {
                jgen.writeObjectFieldStart("entradaFiscal");
                jgen.writeNumberField("id", entradaFiscalNfe.getId());
                jgen.writeStringField("controle", entradaFiscalNfe.getControle());
                jgen.writeStringField("docOrigem", entradaFiscalNfe.getOrigem());
                jgen.writeNumberField("vlrJuros", entradaFiscalNfe.getVlrJuros());
                jgen.writeNumberField("vlrMulta", entradaFiscalNfe.getVlrMulta());
                jgen.writeNumberField("vlrNfe", entradaFiscalNfe.getVlrDocumento());
                jgen.writeNumberField("vlrTaxa", entradaFiscalNfe.getVlrTaxa());
                jgen.writeNumberField("vlrTributo", entradaFiscalNfe.getVlrTributo());
                jgen.writeObjectField("tributoSefazAm", new FiscalTributosSefazAmDAO().getById(FiscalTributosSefazAm.class,
                        entradaFiscalNfe.getTributosSefazAm().getId()));
                jgen.writeEndObject();
            } else {
                jgen.writeObjectField("entradaFiscal", null);
            }
            jgen.writeEndObject();

            entradaCte = entradaProduto.getEntradaCte();
            if (entradaCte != null) {
                jgen.writeObjectFieldStart("entradaCte");
                jgen.writeNumberField("id", entradaCte.getId());
                jgen.writeStringField("chave", entradaCte.getChave());
                jgen.writeStringField("numero", entradaCte.getNumero());
                jgen.writeStringField("serie", entradaCte.getSerie());
                jgen.writeNumberField("qtdVolume", entradaCte.getQtdVolume());
                jgen.writeNumberField("tomadorServico", entradaCte.getTomadorServico());
                jgen.writeNumberField("modelo", entradaCte.getModelo());
                jgen.writeNumberField("vlrCte", entradaCte.getVlrCte());
                jgen.writeNumberField("pesoBruto", entradaCte.getPesoBruto());
                jgen.writeNumberField("vlrFreteBruto", entradaCte.getVlrFreteBruto());
                jgen.writeNumberField("vlrTaxas", entradaCte.getVlrTaxas());
                jgen.writeNumberField("vlrColeta", entradaCte.getVlrColeta());
                jgen.writeNumberField("vlrImpostoFrete", entradaCte.getVlrImpostoFrete());
                jgen.writeStringField("dataEmissao", entradaCte.getDtEmissao().format(DTF_DATA));
                jgen.writeObjectField("situacaoTributaria", new FiscalFreteSituacaoTributariaDAO().getById(FiscalFreteSituacaoTributaria.class,
                        entradaCte.getSituacaoTributaria().getId()));

                Empresa emissorCte = entradaCte.getTransportadora();
                jgen.writeObjectFieldStart("emissor");
                jgen.writeNumberField("id", emissorCte.getId());
                jgen.writeStringField("cnpj", emissorCte.getCnpj());
                jgen.writeStringField("razao", emissorCte.getRazao());
                jgen.writeStringField("fantasia", emissorCte.getFantasia());
                jgen.writeEndObject();

                entradaFiscalCte = entradaCte.getEntradaFiscal();
                if (entradaFiscalCte != null) {
                    jgen.writeObjectFieldStart("entradaFiscal");
                    jgen.writeNumberField("id", entradaFiscalCte.getId());
                    jgen.writeStringField("controle", entradaFiscalCte.getControle());
                    jgen.writeStringField("docOrigem", entradaFiscalCte.getOrigem());
                    jgen.writeNumberField("vlrJuros", entradaFiscalCte.getVlrJuros());
                    jgen.writeNumberField("vlrMulta", entradaFiscalCte.getVlrMulta());
                    jgen.writeNumberField("vlrNfe", entradaFiscalCte.getVlrDocumento());
                    jgen.writeNumberField("vlrTaxa", entradaFiscalCte.getVlrTaxa());
                    jgen.writeNumberField("vlrTributo", entradaFiscalCte.getVlrTributo());
                    jgen.writeObjectField("tributoSefazAm", new FiscalTributosSefazAmDAO().getById(FiscalTributosSefazAm.class,
                            entradaFiscalCte.getTributosSefazAm().getId()));
                    jgen.writeEndObject();
                } else {
                    jgen.writeObjectField("entradaFiscal", null);
                }
                jgen.writeEndObject();
            } else {
                jgen.writeObjectField("entradaCte", null);
            }

            jgen.writeArrayFieldStart("produtos");
            entradaProduto.getEntradaProdutoProdutoList().stream()
                    .forEach(entradaProdutoProduto -> {
                        try {
                            jgen.writeStartObject();
                            jgen.writeNumberField("id", entradaProdutoProduto.getId());
                            jgen.writeStringField("codigo", entradaProdutoProduto.getCodigo());
                            jgen.writeStringField("descricao", entradaProdutoProduto.getDescricao());
                            jgen.writeStringField("lote", entradaProdutoProduto.getLote());
                            jgen.writeStringField("validade", entradaProdutoProduto.getDtValidade().format(DTF_DATA));
                            jgen.writeNumberField("qtd", entradaProdutoProduto.getQtd());
                            jgen.writeNumberField("vlrFabrica", entradaProdutoProduto.getVlrUnitario());
                            jgen.writeNumberField("vlrBruto", entradaProdutoProduto.getVlrBruto());
                            jgen.writeNumberField("vlrImposto", entradaProdutoProduto.getVlrImposto());
                            jgen.writeNumberField("vlrDesconto", entradaProdutoProduto.getVlrDesconto());
                            jgen.writeNumberField("vlrLiquido", entradaProdutoProduto.getVlrLiquido());
                            jgen.writeNumberField("estoque", entradaProdutoProduto.getEstoque());
                            jgen.writeNumberField("varejo", entradaProdutoProduto.getVarejo());
                            jgen.writeNumberField("volume", entradaProdutoProduto.getVolume());
                            jgen.writeNumberField("produto_id", entradaProdutoProduto.getProduto().getId());
                            jgen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            jgen.writeEndArray();

            jgen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
