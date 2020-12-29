package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.*;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelProduto;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelSaidaProdutoProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.*;
import br.com.tlmacedo.cafeperfeito.nfe.v400.Nfe;
import br.com.tlmacedo.cafeperfeito.nfe.ServiceGerarChaveNfe;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.view.ViewSaidaProduto;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TEnviNFe;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Pair;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-06
 * Time: 20:53
 */

public class ControllerSaidaProduto implements Initializable, ModeloCafePerfeito {

    public AnchorPane painelViewSaidaProduto;
    public TitledPane tpnDadosSaida;

    public TitledPane tpnCadastroProduto;
    public JFXTextField txtPesquisaProduto;
    public Label lblRegistrosLocalizados;
    public TreeTableView<Produto> ttvProduto;

    public TitledPane tpnInformacaoVenda;
    public JFXComboBox<Empresa> cboCliente;
    public JFXDatePicker dtpVencimento;
    public JFXDatePicker dtpSaida;
    public Label labelClientePrazo;
    public Label lblClientePrazo;
    public Label labelClienteLimite;
    public Label lblClienteLimite;
    public Label labelClienteAberto;
    public Label lblClienteAberto;
    public Label labelClienteDisponivel;
    public Label lblClienteDisponivel;

    public TitledPane tpnNfeVenda;
    public JFXTextField txtNfeNaturezaOperacao;
    public JFXTextField txtNfeNumero;
    public JFXTextField txtNfeSerie;
    public JFXComboBox<NfeCteModelo> cboNfeModelo;
    public JFXDatePicker dtpNfeEmissao;
    public JFXTimePicker tmpNfeEmissao;
    public JFXDatePicker dtpNfeSaida;
    public JFXTimePicker tmpNfeSaida;
    public JFXComboBox<NfeDestinoOperacao> cboNfeDestinoOperacao;
    public JFXComboBox cboNfeConsumidorFinal;
    public JFXComboBox<NfePresencaComprador> cboNfeTipoAtendimento;
    public JFXComboBox<NfeModalidadeFrete> cboNfeModalidadeFrete;
    public JFXComboBox<Empresa> cboNfeTransportador;
    public JFXTextField txtNfeCobrancaNumero;
    public JFXTextField txtNfeInformacaoAdicional;


    public TitledPane tpnItensSaida;
    public TableView<SaidaProdutoProduto> tvItensSaida;
    public Label labelQtdItem;
    public Label lblQtdItem;
    public Label labelQtdTotal;
    public Label lblQtdTotal;
    public Label labelQtdVolume;
    public Label lblQtdVolume;
    public Label labelTotalBruto;
    public Label lblTotalBruto;
    public Label labelTotalDesconto;
    public Label lblTotalDesconto;
    public Label labelTotalLiquido;
    public Label lblTotalLiquido;


    boolean tabCarregada = false;
    private List<Pair> listaTarefa = new ArrayList<>();

    private EventHandler eventHandlerSaidaProduto;
    private ServiceAlertMensagem alertMensagem;
    private ObjectProperty<StatusBarSaidaProduto> statusBar = new SimpleObjectProperty<>();

    private String nomeController = "saidaProduto";
    private String nomeTab = "";
    private TabModelProduto modelProduto;
    private TabModelSaidaProdutoProduto modelSaidaProdutoProduto;

    //    private ObservableList<EntradaProduto> entradaProdutoObservableList =
//            FXCollections.observableArrayList(new EntradaProdutoDAO().getAll(EntradaProduto.class, null));
//    private EntradaTempDAO entradaTempDAO = new EntradaTempDAO();
    private ObjectProperty<LocalDate> vencimento = new SimpleObjectProperty<>();
    private SaidaProdutoDAO saidaProdutoDAO;
    private ContasAReceberDAO contasAReceberDAO;

    private OrcamentoSaida orcamento = new OrcamentoSaida();
    private ObjectProperty<Empresa> cliente = new SimpleObjectProperty<>(new Empresa());
    private SaidaProduto saidaProduto;
    private ContasAReceber contasAReceber;
    private ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(new ProdutoDAO().getAll(Produto.class, "descricao", null, null, null));
    private FilteredList<Produto> produtoFilteredList = new FilteredList<>(produtoObservableList);


    @Override
    public void fechar() {
        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().remove(ViewSaidaProduto.getTab());
        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, eventHandlerSaidaProduto);
    }

    @Override
    public void criarObjetos() {
        nomeTab = ViewSaidaProduto.getTituloJanela();
        listaTarefa.add(new Pair("criarTabela", "criando tabela de " + nomeController));
    }

    @Override
    public void preencherObjetos() {
        listaTarefa.add(new Pair("vinculandoObjetosTabela", "vinculando objetos a tableModel"));
        listaTarefa.add(new Pair("preencherTabela", "preenchendo tabela " + nomeController));

        listaTarefa.add(new Pair("preencherCombos", "carregando informações de clientes"));

        tabCarregada = new ServiceSegundoPlano().tarefaAbreCadastro(taskSaidaProduto(), listaTarefa.size(), "Abrindo saida de produtos");
    }

    @Override
    public void fatorarObjetos() {
        dtpVencimento.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: rgba(232,86,0,0.29);");
                        }
                    }
                };
            }
        });
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void escutarTecla() {
        escutaTitledTab();

        tpnNfeVenda.setExpanded(false);

        if (statusBarProperty().get() == null)
            setStatusBar(StatusBarSaidaProduto.DIGITACAO);
        ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());

        ttvProduto.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (!ttvProduto.isFocused() || ttvProduto.getSelectionModel().getSelectedItem() == null) return;
            if (event.getCode() != KeyCode.ENTER) return;
            if (ttvProduto.getSelectionModel().getSelectedItem().getValue().getEstoque() <= 0) return;
            Produto produtoSelecionado = ttvProduto.getSelectionModel().getSelectedItem().getValue();
            Long estq_id = null;
            if (produtoSelecionado.getCodigo().equals("")) {
                produtoSelecionado = ttvProduto.getSelectionModel().getSelectedItem().getParent().getValue();
                estq_id = ttvProduto.getSelectionModel().getSelectedItem().getValue().getEstoque_id();
            }
            modelSaidaProdutoProduto.getSaidaProdutoProdutoObservableList().add(new SaidaProdutoProduto(produtoSelecionado, estq_id));
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F8));
        });

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size() == 0) return;
            if (ControllerPrincipal.ctrlPrincipal.getTabSelecionada().equals(nomeTab))
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());
        });

        eventHandlerSaidaProduto = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() < 0)
                    return;
                if (!ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().getText().equals(ViewSaidaProduto.getTituloJanela()))
                    return;
                switch (event.getCode()) {
                    case F1:
                        if (teclaDeshabilitada(event.getCode())) return;
                        limpaTodosCampos();
                        break;
                    case F2:
                        if (teclaDeshabilitada(event.getCode())) return;
                        listaTarefa.clear();
                        listaTarefa.add(new Pair("validarSaidaProduto", "Validando..."));

                        tabCarregada = false;
                        tabCarregada = new ServiceSegundoPlano().tarefaValidando(taskF2(), listaTarefa.size(), "Finalizando saida de produtos");
                        System.out.printf("segundoPlando: [%s]\n", tabCarregada);
                        if (tabCarregada) {
//                        if (!validarSaidaProduto()) break;
                            if (!salvarSaidaProduto()) break;
                            modelProduto.atualizaProdutos();
                            limpaTodosCampos();
                        }
                        break;
                    case F3:
                        if (teclaDeshabilitada(event.getCode())) return;
//                        alertMensagem = new ServiceAlertMensagem();
//                        alertMensagem.setCabecalho(String.format("Excluir entrada"));
//                        alertMensagem.setPromptText(String.format("%s, deseja excluir entrada",
//                                LogadoInf.getUserLog().getApelido()));
//                        if (alertMensagem.getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
//                        if (orcamento != null)
//                            entradaTempDAO.remove(orcamento);
//                        setSituacaoEntrada(SituacaoEntrada.DIGITACAO);
                        break;
                    case F4:
                        if (teclaDeshabilitada(event.getCode())) return;
//                        if (!validarSaidaProduto()) break;
//                        salvarEntradaTemp();
//                        setSituacaoEntrada(SituacaoEntrada.DIGITACAO);
                        break;
                    case F5:
                        if (teclaDeshabilitada(event.getCode())) return;
//                        txtNfeChave.requestFocus();
                        break;
                    case F6:
                        if (teclaDeshabilitada(event.getCode())) return;
                        cboCliente.requestFocus();
                        break;
                    case F7:
                        if (teclaDeshabilitada(event.getCode())) return;
                        txtPesquisaProduto.requestFocus();
                        break;
                    case F8:
                        if (teclaDeshabilitada(event.getCode())) return;
                        tvItensSaida.requestFocus();
                        tvItensSaida.getSelectionModel().select(modelSaidaProdutoProduto.getSaidaProdutoProdutoObservableList().size() - 1,
                                modelSaidaProdutoProduto.getColunaQtd());
                        tvItensSaida.getFocusModel().focus(modelSaidaProdutoProduto.getSaidaProdutoProdutoObservableList().size() - 1,
                                modelSaidaProdutoProduto.getColunaQtd());
                        break;
                    case F9:
                        if (teclaDeshabilitada(event.getCode())) return;
                        tpnNfeVenda.setExpanded(!tpnNfeVenda.isExpanded());
                        break;
                    case F10:
                        if (teclaDeshabilitada(event.getCode())) return;
//                        tpnCteDetalhe.setExpanded(!tpnCteDetalhe.isExpanded());
                        break;
                    case F11:
                        if (teclaDeshabilitada(event.getCode())) return;
//                        tpnCteDetalhe.setExpanded(true);
//                        tpnCteFiscal.setExpanded(!tpnCteFiscal.isExpanded());
                        break;
                    case F12:
                        if (teclaDeshabilitada(event.getCode())) return;
                        fechar();
                        break;
                    case HELP:
//                        if (getStatusFormulario().equals(StatusFormulario.PESQUISA)) return;
//                        keyInsert();
                        break;
                    case DELETE:
//                        if (getStatusFormulario().equals(StatusFormulario.PESQUISA)) return;
//                        keyDelete();
                        break;
                    case B:
//                        if (getStatusFormulario().equals(StatusFormulario.PESQUISA)) return;
//                        if (CODE_KEY_CTRL_ALT_B.match(event) || CHAR_KEY_CTRL_ALT_B.match(event))
//                            addCodeBar("");
                        break;
                    case Z:
//                        if (getStatusFormulario().equals(StatusFormulario.PESQUISA)) return;
//                        if (CODE_KEY_CTRL_Z.match(event) || CHAR_KEY_CTRL_Z.match(event))
//                            reverseImageProduto();
                        break;
                }
//                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() > 0)
//                    ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().setOnCloseRequest(event1 -> {
//                        if (!situacaoEntrada.get().equals(SituacaoEntrada.DIGITACAO)) {
//                            event1.consume();
//                        }
//                    });
            }
        };

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerSaidaProduto);

        txtPesquisaProduto.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            ttvProduto.requestFocus();
            ttvProduto.getSelectionModel().selectFirst();
        });

        cboCliente.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            setCliente(n);
            modelSaidaProdutoProduto.setClienteDescontos(n);
        });

        lblClienteLimite.textProperty().bind(
                Bindings.createStringBinding(() -> {
                    String limite = "";
                    try {
                        limite = clienteProperty().get().limiteProperty().get().toString();
                    } catch (Exception ex) {
                        limite = "0,00";
                    }
                    return ServiceMascara.getMoeda(limite, 2);
                }, clienteProperty())
        );

        lblClientePrazo.textProperty().bind(modelSaidaProdutoProduto.prazoProperty());

        vencimento.bind(modelSaidaProdutoProduto.vencimentoProperty());

        vencimento.addListener((ov, o, n) -> dtpVencimento.setValue(n));

        lblQtdItem.textProperty().bind(modelSaidaProdutoProduto.numItensProperty().asString());
        lblQtdTotal.textProperty().bind(modelSaidaProdutoProduto.qtdItensProperty().asString());
        lblQtdVolume.textProperty().bind(modelSaidaProdutoProduto.qtdVolumeProperty().asString());
        lblTotalBruto.textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(modelSaidaProdutoProduto.totalBrutoProperty().get().setScale(2).toString(), 2),
                modelSaidaProdutoProduto.totalBrutoProperty()
        ));
        lblTotalDesconto.textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(modelSaidaProdutoProduto.totalDescontoProperty().get().setScale(2).toString(), 2),
                modelSaidaProdutoProduto.totalDescontoProperty()
        ));
        lblTotalLiquido.textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(modelSaidaProdutoProduto.totalLiquidoProperty().get().setScale(2).toString(), 2),
                modelSaidaProdutoProduto.totalLiquidoProperty()
        ));

        cboNfeModalidadeFrete.getSelectionModel().selectedItemProperty().addListener((ov, o, n) ->
                cboNfeTransportador.setDisable(n.equals(NfeModalidadeFrete.REMETENTE))
        );

        cboNfeTransportador.setDisable(true);

        txtNfeNumero.textProperty().addListener((ov, o, n) -> txtNfeCobrancaNumero.setText(n));

        lblTotalLiquido.textProperty().addListener((ov, o, n) ->
                txtNfeInformacaoAdicional.setText(String.format("TOTAL R$ %s \tDADOS BANCARIOS PARA TRANSFERENCIA \tBANCO ITAU AG: 1677  C/C: 35.456-3",
                        lblTotalLiquido.getText())));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarObjetos();
        preencherObjetos();
        escutarTecla();
        fatorarObjetos();
        ServiceCampoPersonalizado.fieldMask(painelViewSaidaProduto);
        limpaTodosCampos();

        Platform.runLater(() -> {
            if (!tabCarregada)
                fechar();
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F7));
        });

    }

    Task taskF2() {
        int qtdTarefas = listaTarefa.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int contador = 0;
                updateMessage("Finalizando saida de produto");
//                while (contador <= listaTarefa.size()) {
//                    Pair tarefaAtual = listaTarefa.get(contador++);
                for (Pair tarefaAtual : listaTarefa) {
                    updateProgress(listaTarefa.indexOf(tarefaAtual), qtdTarefas);
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    switch (tarefaAtual.getKey().toString()) {
                        case "validarSaidaProduto":
                            if (!validarSaidaProduto()) {
                                Thread.currentThread().interrupt();
                                updateMessage("poxa vida!!!");
                                System.out.printf("deu zebra!!!");
                            } else {
                                ;
                            }
                    }
//                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        return voidTask;
    }

    @SuppressWarnings("Duplicates")
    Task taskSaidaProduto() {
        int qtdTarefas = listaTarefa.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Abrindo Entrada Produto");
                for (Pair tarefaAtual : listaTarefa) {
                    updateProgress(listaTarefa.indexOf(tarefaAtual), qtdTarefas);
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    switch (tarefaAtual.getKey().toString()) {
                        case "vinculandoObjetosTabela":
                            modelProduto.setLblRegistrosLocalizados(getLblRegistrosLocalizados());
                            modelProduto.setTtvProduto(getTtvProduto());
                            modelProduto.setTxtPesquisaProduto(getTxtPesquisaProduto());
                            modelProduto.setProdutoObservableList(getProdutoObservableList());
                            modelProduto.setProdutoFilteredList(getProdutoFilteredList());
                            modelProduto.escutaLista();

                            modelSaidaProdutoProduto.setTvSaidaProdutoProduto(tvItensSaida);
                            modelSaidaProdutoProduto.setTxtPesquisaProduto(txtPesquisaProduto);
                            modelSaidaProdutoProduto.escutaLista();
                            break;
                        case "criarTabela":
                            modelProduto = new TabModelProduto();
                            modelProduto.tabela();
                            modelProduto.setTipoForm(nomeController);

                            modelSaidaProdutoProduto = new TabModelSaidaProdutoProduto();
                            modelSaidaProdutoProduto.tabela();
                            break;
                        case "preencherCombos":
                            cboCliente.getItems().setAll(
                                    new EmpresaDAO().getAll(Empresa.class, "razao", null, null, null)
                                            .stream().collect(Collectors.toCollection(FXCollections::observableArrayList))
                                            .stream().filter(Empresa::isCliente)
                                            //.filter(cliente -> SituacaoNoSistema.toEnum(cliente.situacaoProperty().get()) == SituacaoNoSistema.ATIVO)
                                            .collect(Collectors.toCollection(FXCollections::observableArrayList))
                            );

                            cboNfeModelo.setItems(NfeCteModelo.getList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                            cboNfeDestinoOperacao.setItems(NfeDestinoOperacao.getList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                            cboNfeConsumidorFinal.getItems().setAll(Arrays.asList("Não", "Sim"));

                            cboNfeTipoAtendimento.setItems(NfePresencaComprador.getList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                            cboNfeModalidadeFrete.setItems(NfeModalidadeFrete.getList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                            cboNfeTransportador.setItems(new EmpresaDAO().getAll(Empresa.class, "razao", null, null, null)
                                    .stream().filter(empresa -> empresa.isTransportadora())
                                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));

                            break;
                        case "preencherTabela":
                            modelProduto.preencherTabela();

                            modelSaidaProdutoProduto.preencherTabela();
                            break;
                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        return voidTask;
    }

    public StatusBarSaidaProduto getStatusBar() {
        return statusBar.get();
    }

    public void setStatusBar(StatusBarSaidaProduto statusBar) {
        this.statusBar.set(statusBar);
    }

    public ObjectProperty<StatusBarSaidaProduto> statusBarProperty() {
        return statusBar;
    }

    public SaidaProduto getSaidaProduto() {
        return saidaProduto;
    }

    public void setSaidaProduto(SaidaProduto saidaProduto) {
        if (saidaProduto == null)
            saidaProduto = new SaidaProduto();
        this.saidaProduto = saidaProduto;
    }

    public ContasAReceber getContasAReceber() {
        return contasAReceber;
    }

    public void setContasAReceber(ContasAReceber contasAReceber) {
        if (contasAReceber == null)
            contasAReceber = new ContasAReceber();
        this.contasAReceber = contasAReceber;
    }

    public Empresa getCliente() {
        return cliente.get();
    }

    public void setCliente(Empresa cliente) {
        this.cliente.set(cliente);
    }

    public ObjectProperty<Empresa> clienteProperty() {
        return cliente;
    }


    public Label getLblRegistrosLocalizados() {
        return lblRegistrosLocalizados;
    }

    public void setLblRegistrosLocalizados(Label lblRegistrosLocalizados) {
        this.lblRegistrosLocalizados = lblRegistrosLocalizados;
    }

    public JFXTextField getTxtPesquisaProduto() {
        return txtPesquisaProduto;
    }

    public void setTxtPesquisaProduto(JFXTextField txtPesquisaProduto) {
        this.txtPesquisaProduto = txtPesquisaProduto;
    }

    public ObservableList<Produto> getProdutoObservableList() {
        return produtoObservableList;
    }

    public void setProdutoObservableList(ObservableList<Produto> produtoObservableList) {
        this.produtoObservableList = produtoObservableList;
    }

    public FilteredList<Produto> getProdutoFilteredList() {
        return produtoFilteredList;
    }

    public void setProdutoFilteredList(FilteredList<Produto> produtoFilteredList) {
        this.produtoFilteredList = produtoFilteredList;
    }

    public TreeTableView<Produto> getTtvProduto() {
        return ttvProduto;
    }

    public void setTtvProduto(TreeTableView<Produto> ttvProduto) {
        this.ttvProduto = ttvProduto;
    }

    /**
     * END
     * Getters e setters
     */


    /**
     * Start
     * Return values
     */


    /**
     * END
     * Return Values
     */

    /**
     * start
     * Booleans
     */

    private boolean teclaDeshabilitada(KeyCode keyCode) {
        return !ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().getLblTeclas().getText().contains(keyCode.toString());
    }

    private boolean validarSaidaProduto() {
        if (validarItensNota() && validarInformacaoVenda()) {
            guardarSaidaProduto();
            return true;
        }
        return false;
    }

    private boolean salvarSaidaProduto() {
        if (modelSaidaProdutoProduto.getSaidaProdutoProdutoObservableList().size() == 0) {
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F7));
            return false;
        }
        saidaProdutoDAO = new SaidaProdutoDAO();
        try {
            setSaidaProduto(saidaProdutoDAO.persiste(getSaidaProduto()));
            salvarSaidaEstoque();
        } catch (Exception ex) {
            ex.printStackTrace();
            saidaProdutoDAO.getEntityManager().getTransaction().rollback();
            return false;
        }
        if (salvarContasAReceber())
            return true;
        else
            return false;
    }

    private boolean salvarContasAReceber() {
        contasAReceberDAO = new ContasAReceberDAO();
        setContasAReceber(null);
        try {
            getContasAReceber().setDtVencimento(dtpVencimento.getValue());
            getContasAReceber().setSaidaProduto(getSaidaProduto());
            getContasAReceber().setPagamentoSituacao(PagamentoSituacao.PENDENTE);
            getContasAReceber().setValor(modelSaidaProdutoProduto.getTotalLiquido());
            getContasAReceber().setUsuarioCadastro(LogadoInf.getUserLog());
            setContasAReceber(contasAReceberDAO.persiste(getContasAReceber()));
        } catch (Exception ex) {
            ex.printStackTrace();
            contasAReceberDAO.getEntityManager().getTransaction().rollback();
            return false;
        }
        return true;
    }

    @SuppressWarnings("Duplicates")
    private boolean validarItensNota() {
        return (modelSaidaProdutoProduto.getSaidaProdutoProdutoObservableList().size() > 0);
    }

    @SuppressWarnings("Duplicates")
    private boolean validarInformacaoVenda() {
        boolean result = true;
//        String dado = "";
//        if (result)
//            if (!(result = (cboCliente.getSelectionModel().getSelectedItem() != null))) {
//                dado += "Cliente";
//                cboCliente.requestFocus();
//            }
//        if (result)
//            if (!(result = (dtpVencimento.getValue().compareTo(LocalDate.now()) >= 0))) {
//                dado += "data vencimento inválido";
//                dtpVencimento.requestFocus();
//            }
//        if (!result) {
//            alertMensagem = new ServiceAlertMensagem();
//            alertMensagem.setCabecalho(String.format("Dados inválido"));
//            alertMensagem.setPromptText(String.format("%s, '%s' incompleto(a) ou invalido(a)",
//                    LogadoInf.getUserLog().getApelido(), dado));
//            alertMensagem.setStrIco("ic_atencao_triangulo_24dp.png");
//            alertMensagem.getRetornoAlert_OK();
//        } // else result = guardarSaidaProduto();
        return result;
    }

    private boolean guardarSaidaProduto() {
        try {
            getSaidaProduto().setCliente(getCboCliente().getSelectionModel().getSelectedItem());
            getSaidaProduto().setVendedor(LogadoInf.getUserLog());

            getSaidaProduto().setSaidaProdutoProdutoList(getModelSaidaProdutoProduto().getSaidaProdutoProdutoObservableList().stream().collect(Collectors.toList()));

            if (getTpnNfeVenda().isExpanded()) {
                if (!guardarValidarNfe()) return false;
            } else {
                getSaidaProduto().setNfe(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean guardarValidarNfe() {
        SaidaProdutoNfe nfe = getSaidaProduto().getNfe();
        nfe.setSaidaProduto(getSaidaProduto());
        try {
            nfe.setNaturezaOperacao(getTxtNfeNaturezaOperacao().getText());
            nfe.setNumero(Integer.parseInt(getTxtNfeNumero().getText().replaceAll("\\D", "")));
            nfe.setSerie(Integer.parseInt(getTxtNfeSerie().getText().replaceAll("\\D", "")));
            nfe.setModelo(getCboNfeModelo().getSelectionModel().getSelectedItem());
            LocalDateTime dtHoraEmissao, dtHoraSaida;
            dtHoraEmissao = getDtpNfeEmissao().getValue().atTime(getTmpNfeEmissao().getValue());
            dtHoraSaida = getDtpNfeSaida().getValue().atTime(getTmpNfeSaida().getValue());
            nfe.setDataHoraEmissao(dtHoraEmissao);
            nfe.setDataHoraSaida(dtHoraSaida);
            nfe.setDestOperacao(getCboNfeDestinoOperacao().getSelectionModel().getSelectedItem());
            nfe.setConsumidorFinal(getCboNfeConsumidorFinal().getSelectionModel().getSelectedIndex() == 1);
            nfe.setPresencaComprador(getCboNfeTipoAtendimento().getSelectionModel().getSelectedItem());
            nfe.setModalidadeFrete(cboNfeModalidadeFrete.getSelectionModel().getSelectedItem());
            if (cboNfeTransportador.getSelectionModel().getSelectedIndex() >= 0)
                nfe.setTransportador(cboNfeTransportador.getSelectionModel().getSelectedItem());
            else
                nfe.setTransportador(null);
            nfe.setCobrancaNumero(txtNfeCobrancaNumero.getText());
            nfe.setInformacaoAdicional(txtNfeInformacaoAdicional.getText());

            nfe.setChave(ServiceGerarChaveNfe.Gerar(nfe));

            TEnviNFe tEnviNFe = new Nfe(getSaidaProduto()).gettEnviNFe();

            ServiceFileSave.saveNfeXmlOut(tEnviNFe);

            //ServiceFileSave.saveNfeXmlOut(ServiceXmlUtil.xmlToObject(new ServiceAssinarXml(tnFe).outputXML(), TNFe.class));

            getSaidaProduto().getNfe().setConsumidorFinal(Boolean.parseBoolean(tEnviNFe.getNFe().get(0).getInfNFe().getIde().getIndFinal()));
            getSaidaProduto().getNfe().setStatus(NfeStatusSEFAZ.DIGITACAO);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * END
     * Booleans
     */

    /**
     * start
     * void
     */

    private void limpaTodosCampos() {
        ServiceCampoPersonalizado.fieldClear(painelViewSaidaProduto);
        ServiceCampoPersonalizado.fieldDisable(painelViewSaidaProduto, false);
        modelSaidaProdutoProduto.getSaidaProdutoProdutoObservableList().clear();
        setSaidaProduto(null);
        orcamento = null;
        txtPesquisaProduto.requestFocus();
    }

    private void escutaTitledTab() {

        tpnNfeVenda.expandedProperty().addListener((ov, o, n) -> {
            int diff = 70;
            if (!n) diff = (diff * (-1));
            tpnDadosSaida.setPrefHeight(tpnDadosSaida.getPrefHeight() + (diff * 1));
            tpnInformacaoVenda.setPrefHeight(tpnInformacaoVenda.getPrefHeight() + (diff * 1));
            tpnItensSaida.setLayoutY(tpnItensSaida.getLayoutY() + (diff * 1));
            tpnItensSaida.setPrefHeight(tpnItensSaida.getPrefHeight() + (diff * -1));
            tvItensSaida.setPrefHeight(tvItensSaida.getPrefHeight() + (diff * -1));
        });
    }

    private boolean salvarSaidaEstoque() {
        ProdutoEstoqueDAO estoqueDAO = new ProdutoEstoqueDAO();
        try {
            getSaidaProduto().getSaidaProdutoProdutoList().stream()
                    .forEach(saidaProdutoProduto -> {
                        ProdutoEstoque estoque = estoqueDAO.getById(ProdutoEstoque.class, saidaProdutoProduto.getProdutoEstoque().getId());
                        if (saidaProdutoProduto.getQtd() == estoque.getQtd()) {
                            estoque.setQtd(0);
                        } else {
                            int estqQtd = estoque.getQtd();
                            int qtdSaida = saidaProdutoProduto.getQtd();
                            int newEstoque = estqQtd - qtdSaida;
                            estoque.setQtd(newEstoque);
                        }
                        estoqueDAO.persiste(estoque);
                    });
        } catch (RuntimeException ex) {
            estoqueDAO.getEntityManager().getTransaction().rollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * END
     * void
     */

    public AnchorPane getPainelViewSaidaProduto() {
        return painelViewSaidaProduto;
    }

    public void setPainelViewSaidaProduto(AnchorPane painelViewSaidaProduto) {
        this.painelViewSaidaProduto = painelViewSaidaProduto;
    }

    public TitledPane getTpnDadosSaida() {
        return tpnDadosSaida;
    }

    public void setTpnDadosSaida(TitledPane tpnDadosSaida) {
        this.tpnDadosSaida = tpnDadosSaida;
    }

    public TitledPane getTpnCadastroProduto() {
        return tpnCadastroProduto;
    }

    public void setTpnCadastroProduto(TitledPane tpnCadastroProduto) {
        this.tpnCadastroProduto = tpnCadastroProduto;
    }

    public TitledPane getTpnInformacaoVenda() {
        return tpnInformacaoVenda;
    }

    public void setTpnInformacaoVenda(TitledPane tpnInformacaoVenda) {
        this.tpnInformacaoVenda = tpnInformacaoVenda;
    }

    public JFXComboBox<Empresa> getCboCliente() {
        return cboCliente;
    }

    public void setCboCliente(JFXComboBox<Empresa> cboCliente) {
        this.cboCliente = cboCliente;
    }

    public JFXDatePicker getDtpVencimento() {
        return dtpVencimento;
    }

    public void setDtpVencimento(JFXDatePicker dtpVencimento) {
        this.dtpVencimento = dtpVencimento;
    }

    public Label getLabelClientePrazo() {
        return labelClientePrazo;
    }

    public void setLabelClientePrazo(Label labelClientePrazo) {
        this.labelClientePrazo = labelClientePrazo;
    }

    public Label getLblClientePrazo() {
        return lblClientePrazo;
    }

    public void setLblClientePrazo(Label lblClientePrazo) {
        this.lblClientePrazo = lblClientePrazo;
    }

    public Label getLabelClienteLimite() {
        return labelClienteLimite;
    }

    public void setLabelClienteLimite(Label labelClienteLimite) {
        this.labelClienteLimite = labelClienteLimite;
    }

    public Label getLblClienteLimite() {
        return lblClienteLimite;
    }

    public void setLblClienteLimite(Label lblClienteLimite) {
        this.lblClienteLimite = lblClienteLimite;
    }

    public Label getLabelClienteAberto() {
        return labelClienteAberto;
    }

    public void setLabelClienteAberto(Label labelClienteAberto) {
        this.labelClienteAberto = labelClienteAberto;
    }

    public Label getLblClienteAberto() {
        return lblClienteAberto;
    }

    public void setLblClienteAberto(Label lblClienteAberto) {
        this.lblClienteAberto = lblClienteAberto;
    }

    public Label getLabelClienteDisponivel() {
        return labelClienteDisponivel;
    }

    public void setLabelClienteDisponivel(Label labelClienteDisponivel) {
        this.labelClienteDisponivel = labelClienteDisponivel;
    }

    public Label getLblClienteDisponivel() {
        return lblClienteDisponivel;
    }

    public void setLblClienteDisponivel(Label lblClienteDisponivel) {
        this.lblClienteDisponivel = lblClienteDisponivel;
    }

    public TitledPane getTpnNfeVenda() {
        return tpnNfeVenda;
    }

    public void setTpnNfeVenda(TitledPane tpnNfeVenda) {
        this.tpnNfeVenda = tpnNfeVenda;
    }

    public JFXTextField getTxtNfeNaturezaOperacao() {
        return txtNfeNaturezaOperacao;
    }

    public void setTxtNfeNaturezaOperacao(JFXTextField txtNfeNaturezaOperacao) {
        this.txtNfeNaturezaOperacao = txtNfeNaturezaOperacao;
    }

    public JFXTextField getTxtNfeNumero() {
        return txtNfeNumero;
    }

    public void setTxtNfeNumero(JFXTextField txtNfeNumero) {
        this.txtNfeNumero = txtNfeNumero;
    }

    public JFXTextField getTxtNfeSerie() {
        return txtNfeSerie;
    }

    public void setTxtNfeSerie(JFXTextField txtNfeSerie) {
        this.txtNfeSerie = txtNfeSerie;
    }

    public JFXComboBox<NfeCteModelo> getCboNfeModelo() {
        return cboNfeModelo;
    }

    public void setCboNfeModelo(JFXComboBox<NfeCteModelo> cboNfeModelo) {
        this.cboNfeModelo = cboNfeModelo;
    }

    public JFXDatePicker getDtpNfeEmissao() {
        return dtpNfeEmissao;
    }

    public void setDtpNfeEmissao(JFXDatePicker dtpNfeEmissao) {
        this.dtpNfeEmissao = dtpNfeEmissao;
    }

    public JFXTimePicker getTmpNfeEmissao() {
        return tmpNfeEmissao;
    }

    public void setTmpNfeEmissao(JFXTimePicker tmpNfeEmissao) {
        this.tmpNfeEmissao = tmpNfeEmissao;
    }

    public JFXDatePicker getDtpNfeSaida() {
        return dtpNfeSaida;
    }

    public void setDtpNfeSaida(JFXDatePicker dtpNfeSaida) {
        this.dtpNfeSaida = dtpNfeSaida;
    }

    public JFXTimePicker getTmpNfeSaida() {
        return tmpNfeSaida;
    }

    public void setTmpNfeSaida(JFXTimePicker tmpNfeSaida) {
        this.tmpNfeSaida = tmpNfeSaida;
    }

    public JFXComboBox<NfeDestinoOperacao> getCboNfeDestinoOperacao() {
        return cboNfeDestinoOperacao;
    }

    public void setCboNfeDestinoOperacao(JFXComboBox<NfeDestinoOperacao> cboNfeDestinoOperacao) {
        this.cboNfeDestinoOperacao = cboNfeDestinoOperacao;
    }

    public JFXComboBox getCboNfeConsumidorFinal() {
        return cboNfeConsumidorFinal;
    }

    public void setCboNfeConsumidorFinal(JFXComboBox cboNfeConsumidorFinal) {
        this.cboNfeConsumidorFinal = cboNfeConsumidorFinal;
    }

    public JFXComboBox<NfePresencaComprador> getCboNfeTipoAtendimento() {
        return cboNfeTipoAtendimento;
    }

    public void setCboNfeTipoAtendimento(JFXComboBox<NfePresencaComprador> cboNfeTipoAtendimento) {
        this.cboNfeTipoAtendimento = cboNfeTipoAtendimento;
    }

    public TitledPane getTpnItensSaida() {
        return tpnItensSaida;
    }

    public void setTpnItensSaida(TitledPane tpnItensSaida) {
        this.tpnItensSaida = tpnItensSaida;
    }

    public TableView<SaidaProdutoProduto> getTvItensSaida() {
        return tvItensSaida;
    }

    public void setTvItensSaida(TableView<SaidaProdutoProduto> tvItensSaida) {
        this.tvItensSaida = tvItensSaida;
    }

    public Label getLabelQtdItem() {
        return labelQtdItem;
    }

    public void setLabelQtdItem(Label labelQtdItem) {
        this.labelQtdItem = labelQtdItem;
    }

    public Label getLblQtdItem() {
        return lblQtdItem;
    }

    public void setLblQtdItem(Label lblQtdItem) {
        this.lblQtdItem = lblQtdItem;
    }

    public Label getLabelQtdTotal() {
        return labelQtdTotal;
    }

    public void setLabelQtdTotal(Label labelQtdTotal) {
        this.labelQtdTotal = labelQtdTotal;
    }

    public Label getLblQtdTotal() {
        return lblQtdTotal;
    }

    public void setLblQtdTotal(Label lblQtdTotal) {
        this.lblQtdTotal = lblQtdTotal;
    }

    public Label getLabelQtdVolume() {
        return labelQtdVolume;
    }

    public void setLabelQtdVolume(Label labelQtdVolume) {
        this.labelQtdVolume = labelQtdVolume;
    }

    public Label getLblQtdVolume() {
        return lblQtdVolume;
    }

    public void setLblQtdVolume(Label lblQtdVolume) {
        this.lblQtdVolume = lblQtdVolume;
    }

    public Label getLabelTotalBruto() {
        return labelTotalBruto;
    }

    public void setLabelTotalBruto(Label labelTotalBruto) {
        this.labelTotalBruto = labelTotalBruto;
    }

    public Label getLblTotalBruto() {
        return lblTotalBruto;
    }

    public void setLblTotalBruto(Label lblTotalBruto) {
        this.lblTotalBruto = lblTotalBruto;
    }

    public Label getLabelTotalDesconto() {
        return labelTotalDesconto;
    }

    public void setLabelTotalDesconto(Label labelTotalDesconto) {
        this.labelTotalDesconto = labelTotalDesconto;
    }

    public Label getLblTotalDesconto() {
        return lblTotalDesconto;
    }

    public void setLblTotalDesconto(Label lblTotalDesconto) {
        this.lblTotalDesconto = lblTotalDesconto;
    }

    public Label getLabelTotalLiquido() {
        return labelTotalLiquido;
    }

    public void setLabelTotalLiquido(Label labelTotalLiquido) {
        this.labelTotalLiquido = labelTotalLiquido;
    }

    public Label getLblTotalLiquido() {
        return lblTotalLiquido;
    }

    public void setLblTotalLiquido(Label lblTotalLiquido) {
        this.lblTotalLiquido = lblTotalLiquido;
    }

    public boolean isTabCarregada() {
        return tabCarregada;
    }

    public void setTabCarregada(boolean tabCarregada) {
        this.tabCarregada = tabCarregada;
    }

    public List<Pair> getListaTarefa() {
        return listaTarefa;
    }

    public void setListaTarefa(List<Pair> listaTarefa) {
        this.listaTarefa = listaTarefa;
    }

    public EventHandler getEventHandlerSaidaProduto() {
        return eventHandlerSaidaProduto;
    }

    public void setEventHandlerSaidaProduto(EventHandler eventHandlerSaidaProduto) {
        this.eventHandlerSaidaProduto = eventHandlerSaidaProduto;
    }

    public ServiceAlertMensagem getAlertMensagem() {
        return alertMensagem;
    }

    public void setAlertMensagem(ServiceAlertMensagem alertMensagem) {
        this.alertMensagem = alertMensagem;
    }

    public String getNomeController() {
        return nomeController;
    }

    public void setNomeController(String nomeController) {
        this.nomeController = nomeController;
    }

    public String getNomeTab() {
        return nomeTab;
    }

    public void setNomeTab(String nomeTab) {
        this.nomeTab = nomeTab;
    }

    public TabModelProduto getModelProduto() {
        return modelProduto;
    }

    public void setModelProduto(TabModelProduto modelProduto) {
        this.modelProduto = modelProduto;
    }

    public TabModelSaidaProdutoProduto getModelSaidaProdutoProduto() {
        return modelSaidaProdutoProduto;
    }

    public void setModelSaidaProdutoProduto(TabModelSaidaProdutoProduto modelSaidaProdutoProduto) {
        this.modelSaidaProdutoProduto = modelSaidaProdutoProduto;
    }

    public LocalDate getVencimento() {
        return vencimento.get();
    }

    public ObjectProperty<LocalDate> vencimentoProperty() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento.set(vencimento);
    }

    public SaidaProdutoDAO getSaidaProdutoDAO() {
        return saidaProdutoDAO;
    }

    public void setSaidaProdutoDAO(SaidaProdutoDAO saidaProdutoDAO) {
        this.saidaProdutoDAO = saidaProdutoDAO;
    }

    public ContasAReceberDAO getContasAReceberDAO() {
        return contasAReceberDAO;
    }

    public void setContasAReceberDAO(ContasAReceberDAO contasAReceberDAO) {
        this.contasAReceberDAO = contasAReceberDAO;
    }

    public OrcamentoSaida getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(OrcamentoSaida orcamento) {
        this.orcamento = orcamento;
    }
}
