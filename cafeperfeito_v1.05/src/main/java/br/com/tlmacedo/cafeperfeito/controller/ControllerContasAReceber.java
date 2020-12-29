package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.ContasAReceberDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.RecebimentoDAO;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelRecebimento;
import br.com.tlmacedo.cafeperfeito.model.vo.ContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.GuestAccess;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoSituacao;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoTipo;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.StatusBarContasAReceber;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.view.ViewContasAReceber;
import br.com.tlmacedo.cafeperfeito.view.ViewRecebimento;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-16
 * Time: 14:17
 */

public class ControllerContasAReceber implements Initializable, ModeloCafePerfeito {

    public AnchorPane painelViewContasAReceber;
    public TitledPane tpnContasAReceber;
    public JFXDatePicker dtpPesq1;
    public JFXDatePicker dtpPesq2;
    public JFXCheckBox chkDtVenda;
    public JFXTextField txtPesquisaContasAReceber;
    public Label lblRegistrosLocalizados;
    public TreeTableView<ContasAReceber> ttvContasAReceber;

    public TitledPane tpnRecebimento;
    public TableView<Recebimento> tvRecebimento;
    public Label labelTotalRecebimentoPago;
    public Label lblTotalRecebimentoPago;
    public Label labelTotalRecebimentoAberto;
    public Label lblTotalRecebimentoAberto;

    public Label labelQtdClientes;
    public Label lblQtdClientes;
    public Label labelQtdContas;
    public Label lblQtdContas;
    public Label labelQtdContasPaga;
    public Label lblQtdContasPaga;
    public Label labelQtdContasAberta;
    public Label lblQtdContasAberta;
    public Label labelTotalContas;
    public Label lblTotalContas;
    public Label labelTotalContasPaga;
    public Label lblTotalContasPaga;
    public Label labelTotalContasAberta;
    public Label lblTotalContasAberta;

    ContextMenu contextMenuPagamentoSituacao = null, contextMenuRecibo = null, contextMenuPagamentoTipo = null, contextSimNao = null;
    MenuItem[] menuItensSituacao, menuItensRecibo, menuItensTipo, menuItensSimNao;

    private ObjectProperty<BigDecimal> totalContaPago = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalContaAberto = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private IntegerProperty qtdClientes = new SimpleIntegerProperty();
    private IntegerProperty qtdContas = new SimpleIntegerProperty();
    private IntegerProperty qtdContasPaga = new SimpleIntegerProperty();
    private IntegerProperty qtdContasAberta = new SimpleIntegerProperty();
    private ObjectProperty<BigDecimal> totalContas = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));

    boolean tabCarregada = false;
    private List<Pair> listaTarefa = new ArrayList<>();

    private EventHandler eventHandlerContasAReceber;
    private ObjectProperty<StatusBarContasAReceber> statusBar = new SimpleObjectProperty<>(StatusBarContasAReceber.DIGITACAO);

    private String nomeController = "contasAReceber";
    private String nomeTab = "";
    private ObjectProperty<BigDecimal> totalContasPaga = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalContasAberta = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private TablePosition tp;
    Integer indexObservable = null;
    private ObservableList<ContasAReceber> contasAReceberObservableList = FXCollections.observableArrayList(new ContasAReceberDAO().getAll(ContasAReceber.class, "dtVencimento", null, null, null));
    private FilteredList<ContasAReceber> contasAReceberFilteredList = new FilteredList<>(contasAReceberObservableList);

    private TabModelContasAReceber modelContasAReceber;
    private TabModelRecebimento modelRecebimento;
    private ObservableList<Recebimento> recebimentoObservableList = FXCollections.observableArrayList();
    private ContasAReceber contasAReceber;

    @Override
    public void fechar() {
        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().remove(ViewContasAReceber.getTab());
        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, eventHandlerContasAReceber);
    }

    @Override
    public void criarObjetos() {
        nomeTab = ViewContasAReceber.getTituloJanela();
        listaTarefa.add(new Pair("criarTabela", "criando tabela de " + nomeController));
        criarMenus();
    }

    @Override
    public void preencherObjetos() {
        listaTarefa.add(new Pair("vinculandoObjetosTabela", "vinculando objetos a tableModel"));
        listaTarefa.add(new Pair("preencherTabela", "preenchendo tabela " + nomeController));
        listaTarefa.add(new Pair("datasPesquisa", "gerando datas para pesquisa"));

        tabCarregada = new ServiceSegundoPlano().tarefaAbreCadastro(taskContasAReceber(), listaTarefa.size(), "Abrindo contas a receber");
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTecla() {

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size() == 0) return;
            if (ControllerPrincipal.ctrlPrincipal.getTabSelecionada().equals(nomeTab))
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());
        });

        ttvContasAReceber.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            if ((indexObservable = contasAReceberObservableList.indexOf(n.getValue())) != null)
                setContasAReceber(n.getValue());
        });

        ttvContasAReceber.focusedProperty().addListener((ov, o, n) ->
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().getLblTeclas().setText(
                        (n) ?
                                statusBarProperty().get().getDescricao() :
                                statusBarProperty().get().getDescricao().replace("[Insert-Novo recebimento]  ", "")
                )
        );

        eventHandlerContasAReceber = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() < 0)
                    return;
                if (!ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().getText().equals(ViewContasAReceber.getTituloJanela()))
                    return;
                switch (event.getCode()) {
                    case F12:
                        if (teclaDeshabilitada(event.getCode())) return;
                        fechar();
                        break;
                    case HELP:
                        if (getContasAReceber() == null) return;
                        if (getContasAReceber().pagamentoSituacaoProperty().get() != PagamentoSituacao.PENDENTE.getCod())
                            return;
                        Recebimento recebimento = new Recebimento();
                        new ViewRecebimento().openViewRecebimento(getContasAReceber(), recebimento);
                        if (recebimento.getDtVencimento() != null) {
                            getContasAReceber().addRecebimento(recebimento);
                            setContasAReceber(new ContasAReceberDAO().persiste(getContasAReceber()));
                            contasAReceberObservableList.get(indexObservable).setRecebimentoList(getContasAReceber().getRecebimentoList());
                            exibirRecebimento();
                        }
                        break;
                }
            }
        };

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerContasAReceber);

        lblQtdClientes.textProperty().bind(qtdClientesProperty().asString());
        lblQtdContas.textProperty().bind(qtdContasProperty().asString());
        lblQtdContasPaga.textProperty().bind(qtdContasPagaProperty().asString());
        lblQtdContasAberta.textProperty().bind(qtdContasAbertaProperty().asString());
        lblTotalContas.textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(totalContasProperty().get().setScale(2).toString(), 2),
                totalContasProperty()
        ));
        lblTotalContasPaga.textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(totalContasPagaProperty().get().setScale(2).toString(), 2),
                totalContasPagaProperty()
        ));
        lblTotalContasAberta.textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(totalContasAbertaProperty().get().setScale(2).toString(), 2),
                totalContasAbertaProperty()
        ));

        lblTotalRecebimentoPago.textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(totalContaPagoProperty().get().setScale(2).toString(), 2),
                totalContaPagoProperty()
        ));

        lblTotalRecebimentoAberto.textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(totalContaAbertoProperty().get().setScale(2).toString(), 2),
                totalContaAbertoProperty()
        ));

        tvRecebimento.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            contextMenuPagamentoSituacao.hide();
            contextMenuPagamentoTipo.hide();
            contextSimNao.hide();
            tvRecebimento.setContextMenu(null);
        });

        tvRecebimento.getFocusModel().focusedCellProperty().addListener((ov, o, n) -> setTp(n));

        tvRecebimento.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
            if (tp.getTableColumn().equals(modelRecebimento.getColunaDocumento())) {
                if (tvRecebimento.getSelectionModel().getSelectedItem().getDocumento().equals("")) {
                    if (tvRecebimento.getSelectionModel().getSelectedItem().getPagamentoSituacao() == PagamentoSituacao.PENDENTE) {
                        menuItensRecibo[0].setText("Gerar recibo");
                        menuItensRecibo[1].setText("Gerar e quitar recibo");
                        menuItensRecibo[2].setText("Gerar e imprimir recibo");
                        menuItensRecibo[3].setText("Gerar e quitar imprimir recibo");
                        contextMenuRecibo.getItems().get(1).setVisible(true);
                        contextMenuRecibo.getItems().get(2).setVisible(true);
                        contextMenuRecibo.getItems().get(3).setVisible(true);
                    } else {
                        menuItensRecibo[0].setText("Gerar recibo");
                        menuItensRecibo[1].setText("Gerar e imprimir recibo");
                        contextMenuRecibo.getItems().get(1).setVisible(true);
                        contextMenuRecibo.getItems().get(2).setVisible(false);
                        contextMenuRecibo.getItems().get(3).setVisible(false);
                    }
                } else {
                    if (tvRecebimento.getSelectionModel().getSelectedItem().getPagamentoSituacao() == PagamentoSituacao.PENDENTE) {
                        menuItensRecibo[0].setText("Quitar recibo");
                        menuItensRecibo[1].setText("Quitar e imprimir recibo");
                        menuItensRecibo[2].setText("Imprimir recibo");
                        contextMenuRecibo.getItems().get(1).setVisible(true);
                        contextMenuRecibo.getItems().get(2).setVisible(true);
                        contextMenuRecibo.getItems().get(3).setVisible(false);
                    } else {
                        menuItensRecibo[0].setText("Imprimir recibo");
                        contextMenuRecibo.getItems().get(1).setVisible(false);
                        contextMenuRecibo.getItems().get(2).setVisible(false);
                        contextMenuRecibo.getItems().get(3).setVisible(false);
                    }
                }
                tvRecebimento.setContextMenu(contextMenuRecibo);
                contextMenuRecibo.show(tvRecebimento, event.getScreenX(), event.getScreenY());
                event.consume();
            }
            if (tp.getTableColumn().equals(modelRecebimento.getColunaPagamentoSituacao())) {
                tvRecebimento.setContextMenu(contextMenuPagamentoSituacao);
                contextMenuPagamentoSituacao.show(tvRecebimento, event.getScreenX(), event.getScreenY());
                event.consume();
            }
            if (tp.getTableColumn().equals(modelRecebimento.getColunaPagamentoTipo())) {
                tvRecebimento.setContextMenu(contextMenuPagamentoTipo);
                contextMenuPagamentoTipo.show(tvRecebimento, event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });

//        contasAReceberObservableList.addListener(c -> );

        contasAReceberFilteredList.addListener((ListChangeListener<? super ContasAReceber>) c ->
                totalizaTabela()
        );

        recebimentoObservableList.addListener((ListChangeListener<? super Recebimento>) c -> totalizaConta());

        for (int i = 0; i < menuItensRecibo.length; i++) {
            int finalI = i;
            menuItensRecibo[i].setOnAction(event -> {
                int index = tvRecebimento.getSelectionModel().getSelectedIndex();
                if (menuItensRecibo[finalI].getText().toLowerCase().contains("gerar")) {
                    new ServiceRecibo().setNumeroRecibo(recebimentoObservableList.get(index));
                    ttvContasAReceber.getSelectionModel().getSelectedItem().setValue(new ContasAReceberDAO().getById(ContasAReceber.class, getContasAReceber().getId()));
                }
                if (menuItensRecibo[finalI].getText().toLowerCase().contains("quitar")) {
                    quitarRecibo(recebimentoObservableList.get(index), PagamentoSituacao.QUITADO);
                }
                if (menuItensRecibo[finalI].getText().toLowerCase().contains("imprimir")) {
                    new ServiceRecibo().imprimeRecibo(recebimentoObservableList.get(index));
                }
            });
        }

        for (int i = 1; i < menuItensSituacao.length; i++) {
            int finalI = i;
            menuItensSituacao[i].setOnAction(event -> {
                try {
                    if (PagamentoSituacao.toEnum(finalI).equals(PagamentoSituacao.PENDENTE)
                            || PagamentoSituacao.toEnum(finalI).equals(PagamentoSituacao.CANCELADO))
                        if (LogadoInf.getUserLog().guestAcessProperty().get() > GuestAccess.GERENTE.getCod())
                            return;
                    quitarRecibo(tvRecebimento.getSelectionModel().getSelectedItem(), PagamentoSituacao.toEnum(finalI));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        for (int i = 1; i < menuItensTipo.length; i++) {
            int finalI = i;
            menuItensTipo[i].setOnAction(event -> {
                try {
                    Recebimento recebimento = getTvRecebimento().getSelectionModel().getSelectedItem();
                    recebimento.setPagamentoTipo(PagamentoTipo.toEnum(finalI));
                    recebimento = new RecebimentoDAO().persiste(recebimento);
                    setContasAReceber(new ContasAReceberDAO().persiste(getContasAReceber()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        dtpPesq2.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(dtpPesq1.getValue().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: rgba(232,86,0,0.29);");
                        }
                    }
                };
            }
        });

        totalizaTabela();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarObjetos();
        preencherObjetos();
        escutarTecla();
        fatorarObjetos();
        ServiceCampoPersonalizado.fieldMask(painelViewContasAReceber);
        ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().getLblTeclas().setText(
                statusBarProperty().get().getDescricao().replace("[Insert-Novo recebimento]  ", "")
        );

        Platform.runLater(() -> {
            if (!tabCarregada)
                fechar();
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F7));
        });

    }

    @SuppressWarnings("Duplicates")
    Task taskContasAReceber() {
        int qtdTarefas = listaTarefa.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Abrindo Contas Receber");
                for (Pair tarefaAtual : listaTarefa) {
                    updateProgress(listaTarefa.indexOf(tarefaAtual), qtdTarefas);
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    switch (tarefaAtual.getKey().toString()) {
                        case "vinculandoObjetosTabela":
                            modelContasAReceber.setLblRegistrosLocalizados(getLblRegistrosLocalizados());
                            modelContasAReceber.setTtvContasAReceber(getTtvContasAReceber());
                            modelContasAReceber.setTxtPesquisaContasAReceber(getTxtPesquisaContasAReceber());
                            modelContasAReceber.setContasAReceberObservableList(getContasAReceberObservableList());
                            modelContasAReceber.setContasAReceberFilteredList(getContasAReceberFilteredList());
                            modelContasAReceber.setChkDtVenda(getChkDtVenda());
                            modelContasAReceber.setDtpPesq1(getDtpPesq1());
                            modelContasAReceber.setDtpPesq2(getDtpPesq2());
                            modelContasAReceber.escutaLista();

                            modelRecebimento.setTvRecebimento(getTvRecebimento());
                            modelRecebimento.setRecebimentoObservableList(getRecebimentoObservableList());
                            modelRecebimento.escutaLista();
                            break;
                        case "criarTabela":
                            modelContasAReceber = new TabModelContasAReceber();
                            modelContasAReceber.tabela();

                            modelRecebimento = new TabModelRecebimento();
                            modelRecebimento.tabela();
                            break;
                        case "datasPesquisa":
                            dtpPesq1.setValue(LocalDate.of(LocalDate.now().getYear(), 1, 1));
                            dtpPesq2.setValue(LocalDate.now().plusMonths(2).withDayOfMonth(1).minusDays(1));
                            break;
                        case "preencherTabela":
                            modelContasAReceber.preencherTabela();

                            modelRecebimento.preencherTabela();
                            break;
                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        return voidTask;
    }

    /**
     * Start
     * Getters e setters
     */

    public ContasAReceber getContasAReceber() {
        return contasAReceber;
    }

    public void setContasAReceber(ContasAReceber contasAReceber) {
//        if (contasAReceber == null)
//            contasAReceber = new ContasAReceber();
        if ((this.contasAReceber = contasAReceber) == null)
            return;
        exibirRecebimento();
    }

    public JFXDatePicker getDtpPesq1() {
        return dtpPesq1;
    }

    public void setDtpPesq1(JFXDatePicker dtpPesq1) {
        this.dtpPesq1 = dtpPesq1;
    }

    public JFXDatePicker getDtpPesq2() {
        return dtpPesq2;
    }

    public void setDtpPesq2(JFXDatePicker dtpPesq2) {
        this.dtpPesq2 = dtpPesq2;
    }

    public JFXCheckBox getChkDtVenda() {
        return chkDtVenda;
    }

    public void setChkDtVenda(JFXCheckBox chkDtVenda) {
        this.chkDtVenda = chkDtVenda;
    }

    public JFXTextField getTxtPesquisaContasAReceber() {
        return txtPesquisaContasAReceber;
    }

    public void setTxtPesquisaContasAReceber(JFXTextField txtPesquisaContasAReceber) {
        this.txtPesquisaContasAReceber = txtPesquisaContasAReceber;
    }

    public Label getLblRegistrosLocalizados() {
        return lblRegistrosLocalizados;
    }

    public void setLblRegistrosLocalizados(Label lblRegistrosLocalizados) {
        this.lblRegistrosLocalizados = lblRegistrosLocalizados;
    }

    public TreeTableView<ContasAReceber> getTtvContasAReceber() {
        return ttvContasAReceber;
    }

    public void setTtvContasAReceber(TreeTableView<ContasAReceber> ttvContasAReceber) {
        this.ttvContasAReceber = ttvContasAReceber;
    }

    public TableView<Recebimento> getTvRecebimento() {
        return tvRecebimento;
    }

    public void setTvRecebimento(TableView<Recebimento> tvRecebimento) {
        this.tvRecebimento = tvRecebimento;
    }

    public BigDecimal getTotalContaPago() {
        return totalContaPago.get();
    }

    public ObjectProperty<BigDecimal> totalContaPagoProperty() {
        return totalContaPago;
    }

    public void setTotalContaPago(BigDecimal totalContaPago) {
        this.totalContaPago.set(totalContaPago);
    }

    public BigDecimal getTotalContaAberto() {
        return totalContaAberto.get();
    }

    public ObjectProperty<BigDecimal> totalContaAbertoProperty() {
        return totalContaAberto;
    }

    public void setTotalContaAberto(BigDecimal totalContaAberto) {
        this.totalContaAberto.set(totalContaAberto);
    }

    public int getQtdClientes() {
        return qtdClientes.get();
    }

    public IntegerProperty qtdClientesProperty() {
        return qtdClientes;
    }

    public void setQtdClientes(int qtdClientes) {
        this.qtdClientes.set(qtdClientes);
    }

    public int getQtdContas() {
        return qtdContas.get();
    }

    public IntegerProperty qtdContasProperty() {
        return qtdContas;
    }

    public void setQtdContas(int qtdContas) {
        this.qtdContas.set(qtdContas);
    }

    public int getQtdContasPaga() {
        return qtdContasPaga.get();
    }

    public IntegerProperty qtdContasPagaProperty() {
        return qtdContasPaga;
    }

    public void setQtdContasPaga(int qtdContasPaga) {
        this.qtdContasPaga.set(qtdContasPaga);
    }

    public int getQtdContasAberta() {
        return qtdContasAberta.get();
    }

    public IntegerProperty qtdContasAbertaProperty() {
        return qtdContasAberta;
    }

    public void setQtdContasAberta(int qtdContasAberta) {
        this.qtdContasAberta.set(qtdContasAberta);
    }

    public BigDecimal getTotalContas() {
        return totalContas.get();
    }

    public ObjectProperty<BigDecimal> totalContasProperty() {
        return totalContas;
    }

    public void setTotalContas(BigDecimal totalContas) {
        this.totalContas.set(totalContas);
    }

    public StatusBarContasAReceber getStatusBar() {
        return statusBar.get();
    }

    public void setStatusBar(StatusBarContasAReceber statusBar) {
        this.statusBar.set(statusBar);
    }

    public ObjectProperty<StatusBarContasAReceber> statusBarProperty() {
        return statusBar;
    }

    public BigDecimal getTotalContasPaga() {
        return totalContasPaga.get();
    }

    public ObjectProperty<BigDecimal> totalContasPagaProperty() {
        return totalContasPaga;
    }

    public void setTotalContasPaga(BigDecimal totalContasPaga) {
        this.totalContasPaga.set(totalContasPaga);
    }

    public BigDecimal getTotalContasAberta() {
        return totalContasAberta.get();
    }

    public ObjectProperty<BigDecimal> totalContasAbertaProperty() {
        return totalContasAberta;
    }

    public void setTotalContasAberta(BigDecimal totalContasAberta) {
        this.totalContasAberta.set(totalContasAberta);
    }

    public TablePosition getTp() {
        return tp;
    }

    public void setTp(TablePosition tp) {
        this.tp = tp;
    }

    public ObservableList<ContasAReceber> getContasAReceberObservableList() {
        return contasAReceberObservableList;
    }

    public void setContasAReceberObservableList(ObservableList<ContasAReceber> contasAReceberObservableList) {
        this.contasAReceberObservableList = contasAReceberObservableList;
    }

    public FilteredList<ContasAReceber> getContasAReceberFilteredList() {
        return contasAReceberFilteredList;
    }

    public void setContasAReceberFilteredList(FilteredList<ContasAReceber> contasAReceberFilteredList) {
        this.contasAReceberFilteredList = contasAReceberFilteredList;
    }

    public TabModelContasAReceber getModelContasAReceber() {
        return modelContasAReceber;
    }

    public void setModelContasAReceber(TabModelContasAReceber modelContasAReceber) {
        this.modelContasAReceber = modelContasAReceber;
    }

    public TabModelRecebimento getModelRecebimento() {
        return modelRecebimento;
    }

    public void setModelRecebimento(TabModelRecebimento modelRecebimento) {
        this.modelRecebimento = modelRecebimento;
    }

    public ObservableList<Recebimento> getRecebimentoObservableList() {
        return recebimentoObservableList;
    }

    public void setRecebimentoObservableList(ObservableList<Recebimento> recebimentoObservableList) {
        this.recebimentoObservableList = recebimentoObservableList;
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

    /**
     * start
     * private voids
     */

    private void changePagamentoSituacao(PagamentoSituacao pagamentoSituacao) {
        getContasAReceber().setPagamentoSituacao(pagamentoSituacao);
        setContasAReceber(new ContasAReceberDAO().persiste(getContasAReceber()));
        modelContasAReceber.getTtvContasAReceber().refresh();
    }

    private void exibirRecebimento() {
        recebimentoObservableList.setAll(getContasAReceber().getRecebimentoList());
        //totalizaConta();
    }

    private void criarMenus() {
        contextMenuPagamentoSituacao = new ContextMenu();
        menuItensSituacao = new MenuItem[PagamentoSituacao.getList().size()];
        PagamentoSituacao.getList().stream()
                .forEach(pagamentoSituacao -> {
                    menuItensSituacao[pagamentoSituacao.getCod()] = new MenuItem(pagamentoSituacao.getDescricao());
                    contextMenuPagamentoSituacao.getItems().add(menuItensSituacao[pagamentoSituacao.getCod()]);
                });

        contextMenuPagamentoTipo = new ContextMenu();
        menuItensTipo = new MenuItem[PagamentoTipo.getList().size()];
        PagamentoTipo.getList().stream()
                .forEach(pagamentoTipo -> {
                    menuItensTipo[pagamentoTipo.getCod()] = new MenuItem(pagamentoTipo.getDescricao());
                    contextMenuPagamentoTipo.getItems().add(menuItensTipo[pagamentoTipo.getCod()]);
                });

        contextSimNao = new ContextMenu();
        menuItensSimNao = new MenuItem[2];
        menuItensSimNao[0] = new MenuItem("Sim");
        menuItensSimNao[1] = new MenuItem("Não");
        contextSimNao.getItems().addAll(menuItensSimNao[0], menuItensSimNao[1]);

        contextMenuRecibo = new ContextMenu();
        menuItensRecibo = new MenuItem[4];
        menuItensRecibo[0] = new MenuItem("");
        menuItensRecibo[1] = new MenuItem("");
        menuItensRecibo[2] = new MenuItem("");
        menuItensRecibo[3] = new MenuItem("");
        contextMenuRecibo.getItems().addAll(menuItensRecibo[0], menuItensRecibo[1], menuItensRecibo[2], menuItensRecibo[3]);

    }

    /**
     * END
     * private voids
     */

    private void quitarRecibo(Recebimento recebimento, PagamentoSituacao formaPagto) {
        recebimento.setPagamentoSituacao(formaPagto);
        switch (formaPagto) {
            case QUITADO:
                recebimento.setValorRecebido(recebimento.getValor());
                recebimento.setUsuarioPagamento(LogadoInf.getUserLog());
                recebimento.setDtPagamento(LocalDate.now());
                recebimento.setUsuarioAtualizacao(LogadoInf.getUserLog());
                recebimento = new RecebimentoDAO().persiste(recebimento);
                if (recebimento.valorRecebidoProperty().get().compareTo(getTotalContaAberto()) >= 0)
                    getContasAReceber().setPagamentoSituacao(formaPagto);
                break;
            case PENDENTE:
                if (recebimento.valorRecebidoProperty().get().compareTo(getTotalContaPago()) >= 0)
                    getContasAReceber().setPagamentoSituacao(formaPagto);
            case CANCELADO:
                recebimento.setValorRecebido(BigDecimal.ZERO);
                recebimento.setUsuarioPagamento(null);
                recebimento.setDtPagamento(null);
                recebimento.setUsuarioAtualizacao(LogadoInf.getUserLog());
                recebimento = new RecebimentoDAO().persiste(recebimento);
                if (formaPagto.equals(PagamentoSituacao.CANCELADO))
                    getContasAReceber().setPagamentoSituacao(formaPagto);
                break;
        }
        setContasAReceber(new ContasAReceberDAO().persiste(getContasAReceber()));
        ttvContasAReceber.getSelectionModel().getSelectedItem().setValue(getContasAReceber());
        ttvContasAReceber.refresh();
        totalizaTabela();
    }

    private void totalizaConta() {
        setTotalContaPago(BigDecimal.ZERO.setScale(2));
        setTotalContaAberto(BigDecimal.ZERO.setScale(2));
        if (getContasAReceber() == null) return;
        BigDecimal valorConta = getContasAReceber().valorProperty().get();
        BigDecimal valorRecebido = getContasAReceber().getRecebimentoList().stream()
                .map(Recebimento::getValorRecebido).reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalContaPago(valorRecebido);
        setTotalContaAberto(valorConta.subtract(valorRecebido));
    }

    private void totalizaTabela() {
        getTtvContasAReceber().refresh();
        setQtdClientes(0);
        setQtdContas(0);
        setQtdContasPaga(0);
        setQtdContasAberta(0);
        setTotalContas(BigDecimal.ZERO.setScale(2));
        setTotalContasPaga(BigDecimal.ZERO.setScale(2));
        setTotalContasAberta(BigDecimal.ZERO.setScale(2));
        List<Long> clientesList = new ArrayList<>();
        getContasAReceberFilteredList().stream()
                .forEach(
                        contasAReceber -> {
                            try {
                                BigDecimal valorConta = contasAReceber.valorProperty().get();
                                setQtdClientes(clientesList.size());
                                setQtdContas(getQtdContas() + 1);
                                setTotalContas(getTotalContas().add(valorConta));
                                if (!clientesList.contains(contasAReceber.getSaidaProduto().getCliente().getId()))
                                    clientesList.add(contasAReceber.getSaidaProduto().getCliente().getId());

                                if (contasAReceber.pagamentoSituacaoProperty().get() != PagamentoSituacao.PENDENTE.getCod()) {
                                    setQtdContasPaga(getQtdContasPaga() + 1);
                                } else {
                                    setQtdContasAberta(getQtdContasAberta() + 1);
                                }

                                final BigDecimal[] valorRecebido = new BigDecimal[1];
                                valorRecebido[0] = BigDecimal.ZERO;
                                contasAReceber.getRecebimentoList().stream()
                                        .forEach(recebimento -> {
                                            try {
                                                if (recebimento.pagamentoSituacaoProperty().get() != PagamentoSituacao.PENDENTE.getCod())
                                                    valorRecebido[0] = valorRecebido[0].add(recebimento.getValor());
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        });
                                setTotalContasPaga(getTotalContasPaga().add(valorRecebido[0]));
                                setTotalContasAberta(getTotalContasAberta().add(valorConta.subtract(valorRecebido[0])));


                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                );
    }

}
