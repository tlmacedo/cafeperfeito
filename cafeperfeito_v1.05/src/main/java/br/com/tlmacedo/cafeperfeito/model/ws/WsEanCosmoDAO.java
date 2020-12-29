package br.com.tlmacedo.cafeperfeito.model.ws;

import br.com.tlmacedo.cafeperfeito.model.vo.Produto;
import br.com.tlmacedo.cafeperfeito.model.vo.ProdutoCodigoBarra;
import br.com.tlmacedo.cafeperfeito.model.vo.WsEanCosmo;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.WsCosmosBusca;
import br.com.tlmacedo.cafeperfeito.service.*;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-01-17
 * Time: 19:34
 */

public class WsEanCosmoDAO extends ServiceBuscaWebService {

    JSONObject jsonObject;
    JSONArray jsonArray;

    WsEanCosmo wsEanCosmo;
    String retorno = "";
    WsCosmosBusca cosmosBusca;

    public WsCosmosBusca getCosmosBusca() {
        return cosmosBusca;
    }

    public void setCosmosBusca(WsCosmosBusca cosmosBusca) {
        this.cosmosBusca = cosmosBusca;
    }

    JSONObject getRetWs(String busca) {
        String url = "";
        switch (getCosmosBusca()) {
            case NCM:
                url = ServiceVariaveisSistema.TCONFIG.getWs().getCosmos().getConsultaNcm();
                break;
            case GPCS:
                url = ServiceVariaveisSistema.TCONFIG.getWs().getCosmos().getConsultaGpcs();
                break;
            case GTINS:
            default:
                url = ServiceVariaveisSistema.TCONFIG.getWs().getCosmos().getConsultaGtins();
                break;
        }
        url = String.format("%s%s%s.json",
                url, busca, getCosmosBusca() == WsCosmosBusca.NCM ? "/products" : "");
        return (jsonObject =
                getJsonObjectHttpUrlConnection(
                        url,
                        ServiceVariaveisSistema.TCONFIG.getWs().getCosmos().getToken(),
                        "")
        );
    }

    public String getWsEanCosmos(String busca, WsCosmosBusca cosmosBusca) {
        setCosmosBusca(cosmosBusca);
        if (getRetWs(busca) != null) {
            if (jsonObject.has("description") && !jsonObject.get("description").equals(null))
                retorno += "descricao::" + jsonObject.getString("description") + ";";
            if (jsonObject.has("ncm") && !jsonObject.get("ncm").equals(null))
                retorno += "ncm::" + jsonObject.getJSONObject("ncm").getString("code") + ";";
            if (jsonObject.has("avg_price") && !jsonObject.get("avg_price").equals(null))
                retorno += "preco::" + ServiceMascara.getMoeda2(jsonObject.getBigDecimal("avg_price"), 2) + ";";
            if (jsonObject.has("net_weight") && !jsonObject.get("net_weight").equals(null))
                retorno += "peso::" + ServiceMascara.getMoeda2(jsonObject.getBigDecimal("net_weight"), 3) + ";";
            if (jsonObject.has("cest") && !jsonObject.get("cest").equals(null))
                retorno += "cest::" + jsonObject.getJSONObject("cest").getString("code") + ";";
            if (jsonObject.has("thumbnail") && !jsonObject.get("thumbnail").equals(null))
                retorno += "imgProduto::" + jsonObject.getString("thumbnail") + ";";
        }
        return retorno;
    }

    public void getInforcacoesProduto(Produto produto, String busca, String retorno) {
        Image imageTmp[] = new Image[2];
        if (produto.getImgProduto() != null)
            produto.setImgProdutoBack(produto.getImageProduto());
        imageTmp[1] = new ServiceEan13(busca.replaceAll("\\D", "")).createBarcodePNG();
        if (!retorno.equals("")) {
            HashMap<String, String> hashMap = ServiceMascara.getFieldFormatMap(retorno);
            if (hashMap.containsKey("descricao"))
                produto.setDescricao(hashMap.get("descricao"));
            if (hashMap.containsKey("ncm")) {
                produto.setNcm(hashMap.get("ncm"));
                produto.setNfeGenero(produto.getNcm().substring(0, 2));
            }
            if (hashMap.containsKey("preco"))
                produto.setPrecoConsumidor(ServiceMascara.getBigDecimalFromTextField(hashMap.get("preco"), 2));
            if (hashMap.containsKey("peso"))
                produto.setPeso(ServiceMascara.getBigDecimalFromTextField(hashMap.get("peso"), 3));
            if (hashMap.containsKey("cest"))
                produto.setCest(hashMap.get("cest"));
            if (hashMap.containsKey("imgProduto"))
                if ((imageTmp[0] = ServiceImage.getImagemFromUrl(hashMap.get("imgProduto"))) == null)
                    imageTmp[0] = ServiceVariaveisSistema.IMG_DEFAULT_PRODUTO;
        }
        if (!produto.getNcm().equals("") && (produto.getCest() == null || produto.getCest().equals(""))) {
            setCosmosBusca(WsCosmosBusca.NCM);
            if (getRetWs(produto.getNcm()) != null) {
                jsonArray = jsonObject.getJSONArray("products");
                outerloop:
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj.has("cest")) {
                        produto.setCest(obj.getJSONObject("cest").getString("code"));
                        break outerloop;
                    }
                }
            }
        }
        produto.setImageProduto(imageTmp[0]);
        if (produto.getProdutoCodigoBarraList().stream()
                .filter(cod -> cod.getCodigoBarra().equals(busca))
                .count() == 0)
            produto.getProdutoCodigoBarraList().add(new ProdutoCodigoBarra(busca, imageTmp[1]));
    }
}
