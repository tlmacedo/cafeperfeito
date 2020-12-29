package br.com.tlmacedo.cafeperfeito.service;

import javafx.concurrent.Task;
import javafx.scene.paint.Color;

public class ServiceSegundoPlano {

//    URL url;
//    BigDecimal getSaldo;
    //    WsCnpjReceitaWsVO wsCnpjReceitaWsVO;
//    TabEmpresaVO tabEmpresaVO;
//    TabProdutoVO tabProdutoVO;
//    TabEnderecoVO tabEnderecoVO;
//    WsCepPostmonVO wsCepPostmonVO;
//    WsEanCosmosVO wsEanCosmosVO;

    public boolean tarefaAbreCadastro(Task task, int qtdTarefas, String titulo) {
        return new ServiceAlertMensagem(titulo, "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(task, Color.TRANSPARENT, false, qtdTarefas);
    }


    public boolean tarefaValidando(Task task, int qtdTarefas, String titulo) {
        return new ServiceAlertMensagem(titulo, "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(task, Color.TRANSPARENT, false, qtdTarefas);
    }

//    public void tarefaAbreCadastroProduto(Task voidTask, int qtdTarefas) {
//        new ServiceAlertMensagem("Aguarde carregando dados do sistema...", "",
//                "ic_aguarde_sentado_orange_32dp.png")
//                .getProgressBar(voidTask, true, false, qtdTarefas);
//    }


}
