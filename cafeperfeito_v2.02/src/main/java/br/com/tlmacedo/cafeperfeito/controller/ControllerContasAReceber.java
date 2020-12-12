package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.ContasAReceberDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.*;
import br.com.tlmacedo.cafeperfeito.model.tm.TmodelContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.vo.ContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.vo.Empresa;
import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.service.autoComplete.ServiceAutoCompleteComboBox;
import br.com.tlmacedo.cafeperfeito.service.format.FormatDataPicker;
import br.com.tlmacedo.cafeperfeito.view.ViewContasAReceber;
import br.com.tlmacedo.cafeperfeito.view.ViewRecebimento;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControllerContasAReceber implements Initializable, ModeloCafePerfeito {

    public AnchorPane painelViewContasAReceber;
    public TitledPane tpnContasAReceber;
    public DatePicker dtpData1;
    public DatePicker dtpData2;
    public CheckBox chkDtVenda;
    public ComboBox<Empresa> cboEmpresa;
    public TextField txtPesquisa;
    public ComboBox<PagamentoSituacao> cboPagamentoSituacao;
    public ComboBox<PagamentoModalidade> cboPagamentoModalidade;
    public Label lblRegistrosLocalizados;
    public TreeTableView<Object> ttvContasAReceber;
    public CheckBox chkLucroContaPaga;
    public Label lblTotQtdClientes;
    public Label lblTotalQtdClientes;
    public Label lblTotQtdContas;
    public Label lblTotalContas;
    public Label lblTotQtdRetiradas;
    public Label lblTotalRetiradas;
    public Label lblTotQtdDescontos;
    public Label lblTotalDescontos;
    public Label lblTotQtdLucroBruto;
    public Label lblTotalLucroBruto;
    public Label lblTotQtdContasAReceber;
    public Label lblTotalContasAReceber;
    public Label lblTotQtdContasVencidas;
    public Label lblTotalContasVencidas;
    public Label lblTotQtdContasPendentes;
    public Label lblTotalContasPendentes;
    public Label lblTotQtdContasPagas;
    public Label lblTotalContasPagas;
    public Label lblTotQtdContasSaldoClientes;
    public Label lblTotalContasSaldoClientes;
    public Label lblTotQtdLucroLiquido;
    public Label lblTotalLucroLiquido;

    private boolean tabCarregada = false;
    private List<EnumsTasks> enumsTasksList = new ArrayList<>();

    private String nomeTab = ViewContasAReceber.getTitulo();
    private String nomeController = "contasAReceber";
    private ObjectProperty<StatusBarContasAReceber> statusBar = new SimpleObjectProperty<>(StatusBarContasAReceber.DIGITACAO);
    private EventHandler eventHandlerContasAReceber;
//    private AlertMensagem alertMensagem;

    private TmodelContasAReceber tmodelContasAReceber;
    private ObjectProperty<ContasAReceber> contasAReceber = new SimpleObjectProperty<>();
    private ContasAReceberDAO contasAReceberDAO;
    private ObjectProperty<Object> objectSelect = new SimpleObjectProperty<>();
    private ObjectProperty<Empresa> empresa = new SimpleObjectProperty<>();

    private FilteredList<ContasAReceber> contasAReceberFilteredList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            criarObjetos();
            preencherObjetos();
            if (!isTabCarregada()) {
                Platform.runLater(() -> fechar());
                return;
            }
            escutarTecla();
            fatorarObjetos();
            fieldsFormat();
            Platform.runLater(() -> {
                limpaCampos(getPainelViewContasAReceber());
                getDtpData1().setValue(LocalDate.of(LocalDate.now().getYear(), 1, 1));
                getDtpData2().setValue(LocalDate.now().plusMonths(1).withDayOfMonth(1));
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fieldsFormat() throws Exception {
        ServiceCampoPersonalizado.fieldTextFormat(getPainelViewContasAReceber());
    }

    @Override
    public void fechar() {
        ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getTabs().remove(
                ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getTabs().stream()
                        .filter(tab -> tab.textProperty().get().equals(getNomeTab()))
                        .findFirst().orElse(null)
        );
        if (isTabCarregada())
            ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal()
                    .removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerContasAReceber());
    }

    @Override
    public void criarObjetos() throws Exception {
        getEnumsTasksList().add(EnumsTasks.TABELA_CRIAR);
    }

    @Override
    public void preencherObjetos() throws Exception {
        getEnumsTasksList().add(EnumsTasks.TABELA_VINCULAR);

        getEnumsTasksList().add(EnumsTasks.TABELA_PREENCHER);

        getEnumsTasksList().add(EnumsTasks.COMBOS_PREENCHER);

        setTabCarregada(new ServiceSegundoPlano().executaListaTarefas(newTaskContasAReceber(), String.format("Abrindo %s!", getNomeTab())));
    }

    @Override
    public void fatorarObjetos() {
        getDtpData2().valueProperty().addListener((ov, o, n) -> {
            if (n == null && getDtpData1().getValue() == null)
                getDtpData1().setValue(LocalDate.now());
        });

        getDtpData1().valueProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            getDtpData2().setDayCellFactory(param -> new FormatDataPicker(getDtpData1().getValue()));

            if (getDtpData2().getValue() == null
                    || getDtpData1().getValue().compareTo(getDtpData2().getValue()) > 0)
                getDtpData2().setValue(n);
        });
    }

    @Override
    public void escutarTecla() {
        statusBarProperty().addListener((ov, o, n) -> {
            if (n == null)
                statusBarProperty().setValue(StatusBarContasAReceber.DIGITACAO);
            showStatusBar();
        });

        setEventHandlerContasAReceber(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                try {
                    if (ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getSelectionModel().getSelectedIndex() < 0)
                        return;
                    if (!ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getSelectionModel().getSelectedItem().getText().equals(getNomeTab()))
                        return;
                    if (!ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().teclaValida(keyEvent)) return;
                    Object object;
                    Recebimento recebimento = null;
                    switch (keyEvent.getCode()) {
                        case F1:
                            limpaCampos(getPainelViewContasAReceber());
                            break;
                        case F2:
                            break;
                        case F4:
                            if (getTtvContasAReceber().getSelectionModel().getSelectedItem() == null) return;
                            object = getTtvContasAReceber().getSelectionModel().getSelectedItem().getValue();
                            if (object instanceof ContasAReceber)
                                recebimento = ((Recebimento) getTtvContasAReceber().getSelectionModel().getSelectedItem().getChildren().get(0).getValue());
                            else if (object instanceof Recebimento)
                                recebimento = (Recebimento) object;
                            if (recebimento == null) return;
                            new ViewRecebimento().openViewRecebimento(recebimento);
                            getTmodelContasAReceber().preencherTabela();
                            break;
                        case F6:
                            getCboEmpresa().requestFocus();
                            getCboEmpresa().setValue(null);
                            break;
                        case F7:
                            getTxtPesquisa().requestFocus();
                            break;
                        case F8:
                            break;
                        case F9:
                            break;
                        case F12:
                            fechar();
                            break;
                        case P:
                            if (!keyEvent.isControlDown() || getTtvContasAReceber().getSelectionModel().getSelectedItem() == null)
                                return;
                            object = getTtvContasAReceber().getSelectionModel().getSelectedItem().getValue();
                            if (object instanceof ContasAReceber)
                                recebimento = ((Recebimento) getTtvContasAReceber().getSelectionModel().getSelectedItem().getChildren().get(0).getValue());
                            else if (object instanceof Recebimento)
                                recebimento = (Recebimento) object;
                            if (recebimento == null) return;

                            new ServiceRelatorio_Recibo().imprimeRecibo(recebimento);
                            break;
                        case Y:
                            System.out.printf("apertou:%s...\n", keyEvent.getCode());
                            if (!keyEvent.isControlDown() || getTtvContasAReceber().getSelectionModel().getSelectedItem() == null)
                                return;
                            System.out.printf("tentando...\n");
                            imprimindoNFe((ContasAReceber) getTtvContasAReceber().getSelectionModel().getSelectedItem().getValue());
//                            object = getTtvContasAReceber().getSelectionModel().getSelectedItem().getValue();
//                           if( ((ContasAReceber) object).getSaidaProduto().getSaidaProdutoNfeList().size()>0){
//                                new ServiceRelatorio().gerar(RelatorioTipo.NFE, ContasAReceber) object).getSaidaProduto().getSaidaProdutoNfeList());
//                            }
//                            if (object instanceof ContasAReceber) {
//                                recebimento = ((Recebimento) getTtvContasAReceber().getSelectionModel().getSelectedItem().getChildren().get(0).getValue());
//                            } else if (object instanceof Recebimento)
//                                recebimento = (Recebimento) object;
//                            if (recebimento == null) return;
//                            new ServiceRelatorio_Recibo().imprimeRecibo(recebimento);
                            //new ServiceRelatorio().gerar(RelatorioTipo.NFE, getSaidaProdutoNfe().getXmlProtNfe());
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            if (n.getText().equals(getNomeTab())) {
                ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerContasAReceber());
                showStatusBar();
            } else {
                ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerContasAReceber());
            }
        });

        getTtvContasAReceber().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            objectSelectProperty().setValue(n);
            String stb = statusBarProperty().getValue().getDescricao();
            try {
                if (objectSelectProperty().getValue() instanceof ContasAReceber) {
                    if (((ContasAReceber) objectSelectProperty().getValue()).getRecebimentoList().size() == 0) {
                        stb = stb.replace("  [Ctrl+P-Imprimir recibo]  [F4-Editar recebimento]  ", "");
                    } else {
                        if (((ContasAReceber) objectSelectProperty().getValue()).getRecebimentoList().stream().map(Recebimento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add)
                                .compareTo(((ContasAReceber) objectSelectProperty().getValue()).valorProperty().getValue()) >= 0)
                            stb = stb.replace("[Insert-Novo recebimento]  ", "");
                    }
                } else if (objectSelectProperty().getValue() instanceof Recebimento) {
                    stb = stb.replace("[Insert-Novo recebimento]  ", "");
                }
            } catch (Exception ex) {
                stb = statusBarProperty().getValue().getDescricao();
            }
            ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(stb);
        });

        new ServiceAutoCompleteComboBox(Empresa.class, getCboEmpresa());

        empresaProperty().bind(Bindings.createObjectBinding(() -> {
            if (getCboEmpresa().getSelectionModel().getSelectedItem() == null)
                return new Empresa();
            return getCboEmpresa().getSelectionModel().getSelectedItem();
        }, getCboEmpresa().valueProperty()));

        getCboEmpresa().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER && getCboEmpresa().getValue() != null)
                getTxtPesquisa().requestFocus();
        });


        getLblTotalQtdClientes().textProperty().bind(
                getTmodelContasAReceber().qtdClientesProperty().asString()
        );

        getLblTotQtdContas().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Contas: [%d]", getTmodelContasAReceber().qtdContasProperty().getValue()),
                getTmodelContasAReceber().qtdContasProperty()));
        getLblTotalContas().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalContasProperty().getValue(), 2)),
                getTmodelContasAReceber().totalContasProperty()));


        getLblTotQtdRetiradas().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Retiradas: [%d]", getTmodelContasAReceber().qtdContasRetiradasProperty().getValue()),
                getTmodelContasAReceber().qtdContasRetiradasProperty()));
        getLblTotalRetiradas().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalContasRetiradasProperty().getValue(), 2)),
                getTmodelContasAReceber().totalContasRetiradasProperty()));


        getLblTotQtdDescontos().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Desc / bonif: [%d]", getTmodelContasAReceber().qtdContasDescontosProperty().getValue()),
                getTmodelContasAReceber().qtdContasDescontosProperty()));
        getLblTotalDescontos().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalContasDescontosProperty().getValue(), 2)),
                getTmodelContasAReceber().totalContasDescontosProperty()));


        getLblTotQtdLucroBruto().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Lucro bruto: [%s%%]", ServiceMascara.getMoeda(getTmodelContasAReceber().percLucroBrutoProperty().getValue(), 4)),
                getTmodelContasAReceber().percLucroBrutoProperty()));
        getLblTotalLucroBruto().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalLucroBrutoProperty().getValue(), 2)),
                getTmodelContasAReceber().totalLucroBrutoProperty()));


        getLblTotQtdContasAReceber().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Contas a receber: [%d]", getTmodelContasAReceber().qtdContasAReceberProperty().getValue()),
                getTmodelContasAReceber().qtdContasAReceberProperty()));
        getLblTotalContasAReceber().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalContasAReceberProperty().getValue(), 2)),
                getTmodelContasAReceber().totalContasAReceberProperty()));


        getLblTotQtdContasVencidas().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Contas vencidas: [%d]", getTmodelContasAReceber().qtdContasVencidasProperty().getValue()),
                getTmodelContasAReceber().qtdContasVencidasProperty()));
        getLblTotalContasVencidas().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalContasVencidasProperty().getValue(), 2)),
                getTmodelContasAReceber().totalContasVencidasProperty()));


        getLblTotQtdContasPendentes().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Contas a receber: [%d]", getTmodelContasAReceber().qtdContasPendentesProperty().getValue()),
                getTmodelContasAReceber().qtdContasPendentesProperty()));
        getLblTotalContasPendentes().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalContasPendentesProperty().getValue(), 2)),
                getTmodelContasAReceber().totalContasPendentesProperty()));


        getLblTotQtdContasPagas().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Contas pagas: [%d]", getTmodelContasAReceber().qtdContasPagasProperty().getValue()),
                getTmodelContasAReceber().qtdContasPagasProperty()));
        getLblTotalContasPagas().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalContasPagasProperty().getValue(), 2)),
                getTmodelContasAReceber().totalContasPagasProperty()));


        getLblTotQtdContasSaldoClientes().textProperty().bind(Bindings.createStringBinding(() ->
                String.format("Saldo clientes: [%d]", getTmodelContasAReceber().qtdContasSaldoClientesProperty().getValue(),
                        getTmodelContasAReceber().qtdContasSaldoClientesProperty())));
        getLblTotalContasSaldoClientes().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalContasSaldoClientesProperty().getValue(), 2)),
                getTmodelContasAReceber().totalContasSaldoClientesProperty()));


        getLblTotQtdLucroLiquido().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("Lucro líquido: [%s%%]", ServiceMascara.getMoeda(getTmodelContasAReceber().percLucroLiquidoProperty().getValue(), 4)),
                getTmodelContasAReceber().percLucroLiquidoProperty()));
        getLblTotalLucroLiquido().textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("R$ %s", ServiceMascara.getMoeda(getTmodelContasAReceber().totalLucroLiquidoProperty().getValue(), 2)),
                getTmodelContasAReceber().totalLucroLiquidoProperty()));

    }

    private void imprimindoNFe(ContasAReceber contasAReceber) {
        if (contasAReceber.getSaidaProduto().getSaidaProdutoNfeList().size() > 0) {
            try {
                new ServiceRelatorio().gerar(RelatorioTipo.NFE, contasAReceber.getSaidaProduto().getSaidaProdutoNfeList().get(0).getXmlProtNfe());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (JRException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Begin Tasks
     */

    private Task newTaskContasAReceber() {
        try {
            int qtdTasks = getEnumsTasksList().size();
            final int[] cont = {1};
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    updateMessage("Loading...");
                    for (EnumsTasks tasks : getEnumsTasksList()) {
                        updateProgress(cont[0]++, qtdTasks);
                        Thread.sleep(200);
                        updateMessage(String.format("%s%s", tasks.getDescricao(),
                                tasks.getDescricao().endsWith(" de ") ? getNomeController() : ""));
                        switch (tasks) {
                            case TABELA_CRIAR:
                                setTmodelContasAReceber(new TmodelContasAReceber());
                                getTmodelContasAReceber().criarTabela();
                                break;

                            case TABELA_VINCULAR:
                                getTmodelContasAReceber().dtpData1Property().bind(getDtpData1().valueProperty());
                                getTmodelContasAReceber().dtpData2Property().bind(getDtpData2().valueProperty());
                                getTmodelContasAReceber().chkDtVendaProperty().bind(getChkDtVenda().selectedProperty());
                                getTmodelContasAReceber().chkLucroContaPagaProperty().bind(getChkLucroContaPaga().selectedProperty());
                                getTmodelContasAReceber().txtPesquisaProperty().bind(getTxtPesquisa().textProperty());
                                getTmodelContasAReceber().empresaProperty().bind(getCboEmpresa().valueProperty());
                                getTmodelContasAReceber().pagamentoSituacaoProperty().bind(getCboPagamentoSituacao().getSelectionModel().selectedItemProperty());
                                getTmodelContasAReceber().pagamentoModalidadeProperty().bind(getCboPagamentoModalidade().getSelectionModel().selectedItemProperty());
                                getLblRegistrosLocalizados().textProperty().bind(getTmodelContasAReceber().lblRegistrosLocalizadosProperty().asString());
                                getTmodelContasAReceber().setTtvContasAReceber(getTtvContasAReceber());
                                setContasAReceberFilteredList(getTmodelContasAReceber().getContasAReceberFilteredList());
                                getTmodelContasAReceber().escutaLista();
                                break;

                            case COMBOS_PREENCHER:
                                getCboEmpresa().setItems(
                                        new EmpresaDAO().getAll(Empresa.class, null, "razao")
                                                .stream().filter(Empresa::isCliente)
                                                .collect(Collectors.toCollection(FXCollections::observableArrayList))
                                );
                                getCboEmpresa().getItems().add(0, new Empresa(null));

                                getCboPagamentoSituacao().setItems(Arrays.stream(PagamentoSituacao.values()).collect(Collectors.toCollection(FXCollections::observableArrayList)));
                                getCboPagamentoSituacao().getItems().add(0, null);

                                getCboPagamentoModalidade().setItems(Arrays.stream(PagamentoModalidade.values()).collect(Collectors.toCollection(FXCollections::observableArrayList)));
                                getCboPagamentoModalidade().getItems().add(0, null);
                                break;

                            case TABELA_PREENCHER:
                                getTmodelContasAReceber().preencherTabela();
                                break;

                            case SALVAR_ENT_SAIDA:
//                                if (gettmod().guardarSaidaProduto()) {
//                                    if (gettmod().salvarSaidaProduto()) {
//                                        getProdutoObservableList().setAll(new ProdutoDAO().getAll(Produto.class, null, "descricao"));
//                                        getTtvProdutos().refresh();
//                                    } else {
//                                        Thread.currentThread().interrupt();
//                                    }
//                                } else {
//                                    Thread.currentThread().interrupt();
//                                }
                                break;
//                            case RELATORIO_IMPRIME_RECIBO:
//                                Platform.runLater(() -> {
//                                    try {
//                                        new ServiceRecibo().imprimeRecibo(recebimentoProperty().getValue());
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                });
//                                break;
                        }
                    }
                    updateMessage("tarefa concluída!!!");
                    updateProgress(qtdTasks, qtdTasks);
                    getEnumsTasksList().clear();
                    return null;
                }
            };
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * END Tasks
     */

    /**
     * Begin voids
     */

    private void limpaCampos(AnchorPane anchorPane) {
        ServiceCampoPersonalizado.fieldClear(anchorPane);
    }


    private void showStatusBar() {
        String stb = statusBarProperty().getValue().getDescricao();
        ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(stb);
    }

    /**
     * END voids
     */

    /**
     * Begin Getters e Setters
     */

    public AnchorPane getPainelViewContasAReceber() {
        return painelViewContasAReceber;
    }

    public void setPainelViewContasAReceber(AnchorPane painelViewContasAReceber) {
        this.painelViewContasAReceber = painelViewContasAReceber;
    }

    public TitledPane getTpnContasAReceber() {
        return tpnContasAReceber;
    }

    public void setTpnContasAReceber(TitledPane tpnContasAReceber) {
        this.tpnContasAReceber = tpnContasAReceber;
    }

    public DatePicker getDtpData1() {
        return dtpData1;
    }

    public void setDtpData1(DatePicker dtpData1) {
        this.dtpData1 = dtpData1;
    }

    public DatePicker getDtpData2() {
        return dtpData2;
    }

    public void setDtpData2(DatePicker dtpData2) {
        this.dtpData2 = dtpData2;
    }

    public CheckBox getChkDtVenda() {
        return chkDtVenda;
    }

    public void setChkDtVenda(CheckBox chkDtVenda) {
        this.chkDtVenda = chkDtVenda;
    }

    public ComboBox<Empresa> getCboEmpresa() {
        return cboEmpresa;
    }

    public void setCboEmpresa(ComboBox<Empresa> cboEmpresa) {
        this.cboEmpresa = cboEmpresa;
    }

    public TextField getTxtPesquisa() {
        return txtPesquisa;
    }

    public void setTxtPesquisa(TextField txtPesquisa) {
        this.txtPesquisa = txtPesquisa;
    }

    public ComboBox<PagamentoSituacao> getCboPagamentoSituacao() {
        return cboPagamentoSituacao;
    }

    public void setCboPagamentoSituacao(ComboBox<PagamentoSituacao> cboPagamentoSituacao) {
        this.cboPagamentoSituacao = cboPagamentoSituacao;
    }

    public ComboBox<PagamentoModalidade> getCboPagamentoModalidade() {
        return cboPagamentoModalidade;
    }

    public void setCboPagamentoModalidade(ComboBox<PagamentoModalidade> cboPagamentoModalidade) {
        this.cboPagamentoModalidade = cboPagamentoModalidade;
    }

    public Label getLblRegistrosLocalizados() {
        return lblRegistrosLocalizados;
    }

    public void setLblRegistrosLocalizados(Label lblRegistrosLocalizados) {
        this.lblRegistrosLocalizados = lblRegistrosLocalizados;
    }

    public TreeTableView<Object> getTtvContasAReceber() {
        return ttvContasAReceber;
    }

    public void setTtvContasAReceber(TreeTableView<Object> ttvContasAReceber) {
        this.ttvContasAReceber = ttvContasAReceber;
    }

    public Label getLblTotQtdClientes() {
        return lblTotQtdClientes;
    }

    public void setLblTotQtdClientes(Label lblTotQtdClientes) {
        this.lblTotQtdClientes = lblTotQtdClientes;
    }

    public Label getLblTotalQtdClientes() {
        return lblTotalQtdClientes;
    }

    public void setLblTotalQtdClientes(Label lblTotalQtdClientes) {
        this.lblTotalQtdClientes = lblTotalQtdClientes;
    }

    public Label getLblTotQtdContas() {
        return lblTotQtdContas;
    }

    public void setLblTotQtdContas(Label lblTotQtdContas) {
        this.lblTotQtdContas = lblTotQtdContas;
    }

    public Label getLblTotalContas() {
        return lblTotalContas;
    }

    public void setLblTotalContas(Label lblTotalContas) {
        this.lblTotalContas = lblTotalContas;
    }

    public Label getLblTotQtdRetiradas() {
        return lblTotQtdRetiradas;
    }

    public void setLblTotQtdRetiradas(Label lblTotQtdRetiradas) {
        this.lblTotQtdRetiradas = lblTotQtdRetiradas;
    }

    public Label getLblTotalRetiradas() {
        return lblTotalRetiradas;
    }

    public void setLblTotalRetiradas(Label lblTotalRetiradas) {
        this.lblTotalRetiradas = lblTotalRetiradas;
    }

    public Label getLblTotQtdDescontos() {
        return lblTotQtdDescontos;
    }

    public void setLblTotQtdDescontos(Label lblTotQtdDescontos) {
        this.lblTotQtdDescontos = lblTotQtdDescontos;
    }

    public Label getLblTotalDescontos() {
        return lblTotalDescontos;
    }

    public void setLblTotalDescontos(Label lblTotalDescontos) {
        this.lblTotalDescontos = lblTotalDescontos;
    }

    public Label getLblTotQtdLucroBruto() {
        return lblTotQtdLucroBruto;
    }

    public void setLblTotQtdLucroBruto(Label lblTotQtdLucroBruto) {
        this.lblTotQtdLucroBruto = lblTotQtdLucroBruto;
    }

    public Label getLblTotalLucroBruto() {
        return lblTotalLucroBruto;
    }

    public void setLblTotalLucroBruto(Label lblTotalLucroBruto) {
        this.lblTotalLucroBruto = lblTotalLucroBruto;
    }

    public Label getLblTotQtdContasAReceber() {
        return lblTotQtdContasAReceber;
    }

    public void setLblTotQtdContasAReceber(Label lblTotQtdContasAReceber) {
        this.lblTotQtdContasAReceber = lblTotQtdContasAReceber;
    }

    public Label getLblTotalContasAReceber() {
        return lblTotalContasAReceber;
    }

    public void setLblTotalContasAReceber(Label lblTotalContasAReceber) {
        this.lblTotalContasAReceber = lblTotalContasAReceber;
    }

    public Label getLblTotQtdContasVencidas() {
        return lblTotQtdContasVencidas;
    }

    public void setLblTotQtdContasVencidas(Label lblTotQtdContasVencidas) {
        this.lblTotQtdContasVencidas = lblTotQtdContasVencidas;
    }

    public Label getLblTotalContasVencidas() {
        return lblTotalContasVencidas;
    }

    public void setLblTotalContasVencidas(Label lblTotalContasVencidas) {
        this.lblTotalContasVencidas = lblTotalContasVencidas;
    }

    public Label getLblTotQtdContasPendentes() {
        return lblTotQtdContasPendentes;
    }

    public void setLblTotQtdContasPendentes(Label lblTotQtdContasPendentes) {
        this.lblTotQtdContasPendentes = lblTotQtdContasPendentes;
    }

    public Label getLblTotalContasPendentes() {
        return lblTotalContasPendentes;
    }

    public void setLblTotalContasPendentes(Label lblTotalContasPendentes) {
        this.lblTotalContasPendentes = lblTotalContasPendentes;
    }

    public Label getLblTotQtdContasPagas() {
        return lblTotQtdContasPagas;
    }

    public void setLblTotQtdContasPagas(Label lblTotQtdContasPagas) {
        this.lblTotQtdContasPagas = lblTotQtdContasPagas;
    }

    public Label getLblTotalContasPagas() {
        return lblTotalContasPagas;
    }

    public void setLblTotalContasPagas(Label lblTotalContasPagas) {
        this.lblTotalContasPagas = lblTotalContasPagas;
    }

    public Label getLblTotQtdContasSaldoClientes() {
        return lblTotQtdContasSaldoClientes;
    }

    public void setLblTotQtdContasSaldoClientes(Label lblTotQtdContasSaldoClientes) {
        this.lblTotQtdContasSaldoClientes = lblTotQtdContasSaldoClientes;
    }

    public Label getLblTotalContasSaldoClientes() {
        return lblTotalContasSaldoClientes;
    }

    public void setLblTotalContasSaldoClientes(Label lblTotalContasSaldoClientes) {
        this.lblTotalContasSaldoClientes = lblTotalContasSaldoClientes;
    }

    public Label getLblTotQtdLucroLiquido() {
        return lblTotQtdLucroLiquido;
    }

    public void setLblTotQtdLucroLiquido(Label lblTotQtdLucroLiquido) {
        this.lblTotQtdLucroLiquido = lblTotQtdLucroLiquido;
    }

    public Label getLblTotalLucroLiquido() {
        return lblTotalLucroLiquido;
    }

    public void setLblTotalLucroLiquido(Label lblTotalLucroLiquido) {
        this.lblTotalLucroLiquido = lblTotalLucroLiquido;
    }

    public boolean isTabCarregada() {
        return tabCarregada;
    }

    public void setTabCarregada(boolean tabCarregada) {
        this.tabCarregada = tabCarregada;
    }

    public List<EnumsTasks> getEnumsTasksList() {
        return enumsTasksList;
    }

    public void setEnumsTasksList(List<EnumsTasks> enumsTasksList) {
        this.enumsTasksList = enumsTasksList;
    }

    public String getNomeTab() {
        return nomeTab;
    }

    public void setNomeTab(String nomeTab) {
        this.nomeTab = nomeTab;
    }

    public String getNomeController() {
        return nomeController;
    }

    public void setNomeController(String nomeController) {
        this.nomeController = nomeController;
    }

    public StatusBarContasAReceber getStatusBar() {
        return statusBar.get();
    }

    public ObjectProperty<StatusBarContasAReceber> statusBarProperty() {
        return statusBar;
    }

    public void setStatusBar(StatusBarContasAReceber statusBar) {
        this.statusBar.set(statusBar);
    }

    public EventHandler getEventHandlerContasAReceber() {
        return eventHandlerContasAReceber;
    }

    public void setEventHandlerContasAReceber(EventHandler eventHandlerContasAReceber) {
        this.eventHandlerContasAReceber = eventHandlerContasAReceber;
    }

    public TmodelContasAReceber getTmodelContasAReceber() {
        return tmodelContasAReceber;
    }

    public void setTmodelContasAReceber(TmodelContasAReceber tmodelContasAReceber) {
        this.tmodelContasAReceber = tmodelContasAReceber;
    }

    public ContasAReceber getContasAReceber() {
        return contasAReceber.get();
    }

    public ObjectProperty<ContasAReceber> contasAReceberProperty() {
        return contasAReceber;
    }

    public void setContasAReceber(ContasAReceber contasAReceber) {
        this.contasAReceber.set(contasAReceber);
    }

    public ContasAReceberDAO getContasAReceberDAO() {
        return contasAReceberDAO;
    }

    public void setContasAReceberDAO(ContasAReceberDAO contasAReceberDAO) {
        this.contasAReceberDAO = contasAReceberDAO;
    }

    public Object getObjectSelect() {
        return objectSelect.get();
    }

    public ObjectProperty<Object> objectSelectProperty() {
        return objectSelect;
    }

    public void setObjectSelect(Object objectSelect) {
        this.objectSelect.set(objectSelect);
    }

    public Empresa getEmpresa() {
        return empresa.get();
    }

    public ObjectProperty<Empresa> empresaProperty() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa.set(empresa);
    }

    public FilteredList<ContasAReceber> getContasAReceberFilteredList() {
        return contasAReceberFilteredList;
    }

    public void setContasAReceberFilteredList(FilteredList<ContasAReceber> contasAReceberFilteredList) {
        this.contasAReceberFilteredList = contasAReceberFilteredList;
    }

    public CheckBox getChkLucroContaPaga() {
        return chkLucroContaPaga;
    }

    public void setChkLucroContaPaga(CheckBox chkLucroContaPaga) {
        this.chkLucroContaPaga = chkLucroContaPaga;
    }

    /**
     * END Getters e Setters
     */

}
