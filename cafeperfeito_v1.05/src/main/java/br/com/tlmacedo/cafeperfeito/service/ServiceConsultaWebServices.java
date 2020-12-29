package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.dao.TelefoneOperadoraDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.Empresa;
import br.com.tlmacedo.cafeperfeito.model.vo.Endereco;
import br.com.tlmacedo.cafeperfeito.model.vo.TelefoneOperadora;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.WsCosmosBusca;
import br.com.tlmacedo.cafeperfeito.model.ws.WsEanCosmoDAO;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;

import static br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema.TCONFIG;

public class ServiceConsultaWebServices {

    public static String getProdutoNcmCest_WsEanCosmos(String busca, WsCosmosBusca cosmosBusca) {
        final String[] retorno = new String[1];
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage(String.format("Pesquisando código de barra: [%s]", busca));
                Thread.sleep(300);
                retorno[0] = new WsEanCosmoDAO().getWsEanCosmos(busca, cosmosBusca);
                return null;
            }
        };
        new ServiceAlertMensagem("Aguarde pesquisando código de barras...", "",
                "ic_aguarde_sentado_orange_32dp.png").getProgressBar(voidTask, Color.TRANSPARENT, false, 1);
        return retorno[0];
    }

    public boolean getEnderecoCep_postmon(Endereco endereco, String busca) {
        final boolean[] result = new boolean[1];
        Task<Void> buscaCep = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage(String.format("Pesquisando C.E.P.: [%s]", busca));
                Thread.sleep(300);
                result[0] = new Endereco().setWsEndereco_CepPostmon(endereco, ServiceBuscaWebService.getJsonObjectHttpUrlConnection(
                        TCONFIG.getWs().getPostmon().getUrl() + busca.replaceAll("\\D", ""),
                        "", ""
                ));
                return null;
            }
        };
        new ServiceAlertMensagem("Aguarde pesquisando cep nos correios...", "",
                "ic_aguarde_sentado_orange_32dp.png").getProgressBar(buscaCep, Color.TRANSPARENT, false, 1);
        return result[0];
    }

    public TelefoneOperadora getTelefone_WsPortabilidadeCelular(String busca) {
        final TelefoneOperadora[] operadora = new TelefoneOperadora[1];
        Task<Void> buscaTelefone = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage(String.format("Pesq. operadora para: [%s]", ServiceMascara.getTelefone(busca)));
                Thread.sleep(300);
                operadora[0] = getOperadoraTelefone(busca);
                return null;
            }
        };
        new ServiceAlertMensagem("Aguarde pesquisando operadoras de telefonia...", "",
                "ic_aguarde_sentado_orange_32dp.png").getProgressBar(buscaTelefone, Color.TRANSPARENT, false, 1);
        return operadora[0];
    }

    public boolean getSistuacaoCNPJ_receitaWs(Empresa empresa, String busca) {
        final boolean[] result = new boolean[1];
        Task<Void> buscaCNPJ = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage(String.format("Pesquisando C.N.P.J: [%s]", busca));
                Thread.sleep(300);
                result[0] = Empresa.setWsEmpresa_ReceitaWs(empresa, ServiceBuscaWebService.getJsonObjectHttpUrlConnection(
                        TCONFIG.getWs().getReceitaws().getUrl() + busca.replaceAll("\\D", ""),
                        TCONFIG.getWs().getReceitaws().getToken(),
                        "/days/0"
                ));
                return null;
            }
        };
        new ServiceAlertMensagem("Aguarde pesquisando cnpj na receita federal...", "",
                "ic_aguarde_sentado_orange_32dp.png").getProgressBar(buscaCNPJ, Color.TRANSPARENT, false, 1);
        return result[0];
    }

    public TelefoneOperadora getOperadoraTelefone(String telefone) {
        String retURL;
        try {
            if ((retURL = new ServiceBuscaWebService().getObjectWebService(
                    String.format("%s?pass=%s&user=%s&search_number=%s",
                            TCONFIG.getWs().getPortabilidadecelular().getUrl(),
                            TCONFIG.getWs().getPortabilidadecelular().getPass(),
                            TCONFIG.getWs().getPortabilidadecelular().getUser(),
                            telefone.replaceAll("\\D", ""))
            )) == null) retURL = "55321";
            String finalRetURL = retURL;
            return new TelefoneOperadoraDAO().getAll(TelefoneOperadora.class, null, null, null, null)
                    .stream().filter(operadora -> operadora.getCodWsPortabilidade().contains(finalRetURL))
                    .findFirst()
                    .orElse(new TelefoneOperadoraDAO().getById(TelefoneOperadora.class, 0L));
        } catch (Exception ex) {
            return new TelefoneOperadoraDAO().getById(TelefoneOperadora.class, 0L);
        }

    }
}
