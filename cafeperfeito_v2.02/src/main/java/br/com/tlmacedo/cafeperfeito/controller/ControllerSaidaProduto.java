package br.com.tlmacedo.cafeperfeito.controller;

import br.com.cafeperfeito.xsd.config_nfe.config.*;
import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.*;
import br.com.tlmacedo.cafeperfeito.model.enums.*;
import br.com.tlmacedo.cafeperfeito.model.tm.TmodelProduto;
import br.com.tlmacedo.cafeperfeito.model.tm.TmodelSaidaProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import br.com.tlmacedo.cafeperfeito.nfe.Nfe;
import br.com.tlmacedo.cafeperfeito.nfe.NfeService;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.service.alert.Alert_Ok;
import br.com.tlmacedo.cafeperfeito.service.alert.Alert_YesNoCancel;
import br.com.tlmacedo.cafeperfeito.service.autoComplete.ServiceAutoCompleteComboBox;
import br.com.tlmacedo.cafeperfeito.service.format.FormatDataPicker;
import br.com.tlmacedo.cafeperfeito.service.format.ServiceFormatDataPicker;
import br.com.tlmacedo.cafeperfeito.view.ViewRecebimento;
import br.com.tlmacedo.cafeperfeito.view.ViewSaidaProduto;
import br.com.tlmacedo.nfe.service.NFev400;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TEnviNFe;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;
import static br.com.tlmacedo.cafeperfeito.nfe.NfeService.getChaveNfe;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigNFe.MYINFNFE;
import static java.time.temporal.ChronoUnit.DAYS;

public class ControllerSaidaProduto implements Initializable, ModeloCafePerfeito {

    public AnchorPane painelViewSaidaProduto;

    public TitledPane tpnCliente;
    public DatePicker dtpDtSaida;
    public DatePicker dtpDtVencimento;
    public ComboBox<Empresa> cboEmpresa;
    public Label lblLimite;
    public Label lblLimiteUtilizado;
    public Label lblLimiteDisponivel;
    public Label lblPrazo;
    public Label labelUltimoPedidoDt;
    public Label lblUltimoPedidoDt;
    public Label lblUltimoPedidoDias;
    public Label lblUltimoPedidoVlr;
    public Label lblQtdPedidos;
    public Label lblTicketMedioVlr;
    public ComboBox<Endereco> cboEndereco;
    public Label lblLogradoruro;
    public Label lblNumero;
    public Label lblBairro;
    public Label lblComplemento;
    public ComboBox<Telefone> cboTelefone;

    public TitledPane tpnNfe;
    public Tab tabNfeDados;
    public ComboBox<NatOp> cboNfeDadosNaturezaOperacao;
    public TextField txtNfeDadosNumero;
    public TextField txtNfeDadosSerie;
    public ComboBox<Mod> cboNfeDadosModelo;
    public DatePicker dtpNfeDadosDtEmissao;
    public TextField txtNfeDadosHoraEmissao;
    public DatePicker dtpNfeDadosDtSaida;
    public TextField txtNfeDadosHoraSaida;
    public ComboBox<IdDest> cboNfeDadosDestinoOperacao;
    public ComboBox<IndFinal> cboNfeDadosIndicadorConsumidorFinal;
    public ComboBox<IndPres> cboNfeDadosIndicadorPresenca;
    public CheckBox chkPrintLoteProdutos;


    public Tab tabNfeImpressao;
    public ComboBox<TpImp> cboNfeImpressaoTpImp;
    public ComboBox<TpEmis> cboNfeImpressaoTpEmis;
    public ComboBox<FinNFe> cboNfeImpressaoFinNFe;

    public Tab tabNfeTransporta;
    public ComboBox<ModFrete> cboNfeTransporteModFrete;
    public ComboBox<Empresa> cboNfeTransporteTransportadora;

    public Tab tabNfeCobranca;
    public ComboBox<NfeCobrancaDuplicataNumero> cboNfeCobrancaDuplicataNumeros;
    public TextField txtNfeCobrancaDuplicataValor;
    public ComboBox<IndPag> cboNfeCobrancaPagamentoIndicador;
    public ComboBox<NfeCobrancaDuplicataPagamentoMeio> cboNfeCobrancaPagamentoMeio;
    public TextField txtNfeCobrancaPagamentoValor;

    public Tab tabNfeInformacoes;
    public TextArea txaNfeInformacoesAdicionais;

    public TitledPane tpnItensTotaisDetalhe;
    public TextField txtPesquisa;
    public Label lblStatus;
    public Label lblRegistrosLocalizados;
    public TreeTableView<Object> ttvProdutoEstoque;
    public VBox vBoxItensNfeDetalhe;
    public TableView<SaidaProdutoProduto> tvItensNfe;
    public VBox vBoxTotalQtdItem;
    public Label lblQtdItem;
    public VBox vBoxTotalQtdTotal;
    public Label lblQtdTotal;
    public VBox vBoxTotalQtdVolume;
    public Label lblQtdVolume;
    public VBox vBoxTotalBruto;
    public Label lblTotalBruto;
    public VBox vBoxTotalDesconto;
    public Label lblTotalDesconto;
    public VBox vBoxTotalLiquido;
    public Label lblTotalLiquido;

    private boolean tabCarregada = false;
    private List<EnumsTasks> enumsTasksList = new ArrayList<>();

    private String nomeTab = ViewSaidaProduto.getTitulo();
    private String nomeController = "saidaProduto";
    private ObjectProperty<StatusSaidaProduto> statusBar = new SimpleObjectProperty<>(StatusSaidaProduto.DIGITACAO);
    private EventHandler eventHandlerSaidaProduto;

    private TmodelProduto tmodelProduto;
    private ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(new ProdutoDAO().getAll(Produto.class, null, "descricao"));
    private FilteredList<Produto> produtoFilteredList;

    private TmodelSaidaProduto tmodelSaidaProduto;
    private ObjectProperty<SaidaProduto> saidaProduto = new SimpleObjectProperty<>();
    private SaidaProdutoDAO saidaProdutoDAO;
    private ObservableList<SaidaProdutoProduto> saidaProdutoProdutoObservableList = FXCollections.observableArrayList();
    private ObservableList<ContasAReceber> contasAReceberObservableList =
            FXCollections.observableArrayList(new ContasAReceberDAO()
                    .getAll(ContasAReceber.class, null, "dtCadastro DESC"));
    private ContasAReceberDAO contasAReceberDAO;

    private ObjectProperty<Empresa> empresa = new SimpleObjectProperty<>();
    private IntegerProperty prazo = new SimpleIntegerProperty(0);
    private List<FichaKardex> fichaKardexList;

    private IntegerProperty nfeLastNumber = new SimpleIntegerProperty(0);
    private ObjectProperty<SaidaProdutoNfe> saidaProdutoNfe = new SimpleObjectProperty<>();
    private StringProperty informacoesAdicionasNFe = new SimpleStringProperty();
    private ObjectProperty<NFev400> nFev400 = new SimpleObjectProperty<>();
    private ObjectProperty<TEnviNFe> tEnviNFe = new SimpleObjectProperty<>();
    private StringProperty xmlNFe = new SimpleStringProperty();
    private StringProperty xmlNFeAssinado = new SimpleStringProperty();
    private StringProperty xmlNFeAutorizacao = new SimpleStringProperty();
    private StringProperty xmlNFeRetAutorizacao = new SimpleStringProperty();
    private StringProperty xmlNFeProc = new SimpleStringProperty();

    private ServiceAutoCompleteComboBox comboEmpresa;

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
            Platform.runLater(() -> limpaCampos(getPainelViewSaidaProduto()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fieldsFormat() throws Exception {
        ServiceCampoPersonalizado.fieldTextFormat(getPainelViewSaidaProduto());
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
                    .removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerSaidaProduto());
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

        setTabCarregada(new ServiceSegundoPlano().executaListaTarefas(newTaskSaidaProduto(), String.format("Abrindo %s!", getNomeTab())));
    }

    @Override
    public void fatorarObjetos() {
        getTpnNfe().setExpanded(false);

        getDtpDtSaida().setDayCellFactory(param -> new FormatDataPicker(null));
        getDtpDtVencimento().setDayCellFactory(param -> new FormatDataPicker(null));
        getDtpNfeDadosDtEmissao().setDayCellFactory(param -> new FormatDataPicker(null));
        getDtpNfeDadosDtSaida().setDayCellFactory(param -> new FormatDataPicker(null));

        getDtpDtSaida().valueProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            int prazo = (empresaProperty().getValue() == null
                    ? 0 : empresaProperty().getValue().prazoProperty().getValue());
            getDtpDtVencimento().setValue(n.plusDays(prazo));
            getDtpDtVencimento().setDayCellFactory(param -> new FormatDataPicker(n));
        });

        getDtpNfeDadosDtEmissao().valueProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            getDtpNfeDadosDtSaida().setValue(n);
            getDtpNfeDadosDtSaida().setDayCellFactory(param -> new FormatDataPicker(n));
        });
    }

    @Override
    public void escutarTecla() {
        escutaTitledTab();
        statusBarProperty().addListener((ov, o, n) -> {
            if (n == null)
                statusBarProperty().setValue(StatusSaidaProduto.DIGITACAO);
            showStatusBar();
        });

        getTtvProdutoEstoque().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() != KeyCode.ENTER
                    || getTtvProdutoEstoque().getSelectionModel().getSelectedItem() == null
                    || (getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue() instanceof Produto
                    && ((Produto) getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue()).tblEstoqueProperty().getValue() <= 0)
                    || (getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue() instanceof ProdutoEstoque
                    && ((ProdutoEstoque) getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue()).qtdProperty().getValue() <= 0))
                return;
            ProdutoEstoque estoqueEscolhido = getEstoqueSelecionado();
            SaidaProdutoProduto saida;
            if ((saida = getSaidaProdutoProdutoObservableList().stream()
                    .filter(saidaProdutoProduto -> saidaProdutoProduto.loteProperty().getValue().equals(estoqueEscolhido.loteProperty().getValue())
                            && saidaProdutoProduto.produtoProperty().getValue().idProperty().getValue().intValue()
                            == estoqueEscolhido.produtoProperty().getValue().idProperty().getValue().intValue())
                    .findFirst().orElse(null)) == null) {
//                System.out.printf("Empresa[%s]: %s\n", getEmpresa().getId(), getEmpresa());
//                BigDecimal vlrUnt = null, vlrDesc = null;
//                System.out.printf("vlrUnt: %s\tvlrDesc: %s\n", vlrUnt, vlrDesc);
//                List<EmpresaCondicoes> empresaCondicoesList =
//                        getEmpresa().getEmpresaCondicoes().stream().filter(empresaCondicoes ->
//                                empresaCondicoes.produtoProperty().getValue().idProperty().getValue().intValue()
//                                        == getEstoqueSelecionado().produtoProperty().getValue().idProperty().getValue().intValue()
//                                        && empresaCondicoes.validadeProperty().getValue().compareTo(LocalDate.now()) <= 0)
//                                .collect(Collectors.toList());
//
//                System.out.printf("empresaCondicoesList: %s\n", empresaCondicoesList);
//                if (empresaCondicoesList.size() > 0) {
//                    vlrUnt = empresaCondicoesList.stream().filter(empresaCondicoes ->
//                            empresaCondicoes.valorProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
//                            .findFirst().get().valorProperty().getValue();
//                    vlrDesc = empresaCondicoesList.stream().filter(empresaCondicoes ->
//                            empresaCondicoes.descontoProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
//                            .findFirst().get().descontoProperty().getValue();
//                }
//                System.out.printf("vlrUnt: %s\tvlrDesc: %s\n", vlrUnt, vlrDesc);
                getSaidaProdutoProdutoObservableList().add(new SaidaProdutoProduto(estoqueEscolhido, TipoCodigoCFOP.COMERCIALIZACAO, 1));
                ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F8));
            } else {
                getTvItensNfe().requestFocus();
                getTvItensNfe().getSelectionModel().select(getSaidaProdutoProdutoObservableList().indexOf(saida),
                        getTmodelSaidaProduto().getColQtd());
            }
        });

        setEventHandlerSaidaProduto(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                try {
                    if (ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getSelectionModel().getSelectedIndex() < 0)
                        return;
                    if (!ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getSelectionModel().getSelectedItem().getText().equals(getNomeTab()))
                        return;
                    if (keyEvent.getCode().equals(KeyCode.HELP)
                            && getTvItensNfe().isFocused()) {
                        ProdutoEstoque estoqueEscolhido = getEstoqueSelecionado();

                        getSaidaProdutoProdutoObservableList().add(new SaidaProdutoProduto(estoqueEscolhido, TipoCodigoCFOP.BONIFICACAO, 1));
                        ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F8));
                    }
                    if (!ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().teclaValida(keyEvent)) return;
                    switch (keyEvent.getCode()) {
                        case F1:
                            limpaCampos(getPainelViewSaidaProduto());
                            break;
                        case F2:
                            if (validarSaida()) {
                                BigDecimal vlrCredDeb;
                                if ((vlrCredDeb = utilizacaoDeCreditoDebito()) == null) return;
                                getEnumsTasksList().clear();
                                getEnumsTasksList().add(EnumsTasks.SALVAR_ENT_SAIDA);
                                if (new ServiceSegundoPlano().executaListaTarefas(newTaskSaidaProduto(), String.format("Salvando %s!", getNomeTab()))) {
                                    if (vlrCredDeb.compareTo(BigDecimal.ZERO) != 0) {
                                        try {
                                            setContasAReceberDAO(new ContasAReceberDAO());
                                            baixarCreditoDebito(vlrCredDeb);
                                            getContasAReceberDAO().transactionBegin();
                                            PagamentoModalidade modRecebimento = null;
                                            if (vlrCredDeb.compareTo(BigDecimal.ZERO) < 0)
                                                modRecebimento = PagamentoModalidade.CREDITO;
                                            else
                                                modRecebimento = PagamentoModalidade.DEBITO;
                                            getSaidaProduto().contasAReceberProperty().getValue().getRecebimentoList()
                                                    .add(addRecebimento(getSaidaProduto().contasAReceberProperty().getValue(),
                                                            modRecebimento, vlrCredDeb));
                                            getSaidaProduto().contasAReceberProperty().setValue(
                                                    getContasAReceberDAO().setTransactionPersist(getSaidaProduto().contasAReceberProperty().getValue())
                                            );
                                            getContasAReceberDAO().transactionCommit();

                                        } catch (Exception ex) {
                                            getContasAReceberDAO().transactionRollback();
                                            ex.printStackTrace();
                                        }
                                    }

                                    new ViewRecebimento().openViewRecebimento(getSaidaProduto().contasAReceberProperty().getValue());

                                    if (saidaProdutoNfeProperty().getValue() != null) {
                                        new Nfe(saidaProdutoNfeProperty().getValue(), getChkPrintLoteProdutos().isSelected());
//                                        getSaidaProdutoDAO().merger(getSaidaProduto());
                                    }

                                    atualizaTotaisCliente();

                                    limpaCampos(getPainelViewSaidaProduto());
                                } else {
                                    //if (getSaidaProdutoDAO() != null)
                                    getSaidaProdutoDAO().transactionRollback();
                                }
                            } else {
                                new Alert_Ok("Saida invalida",
                                        "Verifique a saida de produtos pois está invalida",
                                        null);
                            }
                            break;
                        case F6:
                            getCboEmpresa().requestFocus();
                            //getCboEmpresa().setValue(null);
                            break;
                        case F7:
                            getTxtPesquisa().requestFocus();
                            break;
                        case F8:
                            getTvItensNfe().requestFocus();
                            getTvItensNfe().getSelectionModel().select(getSaidaProdutoProdutoObservableList().size() - 1,
                                    getTmodelSaidaProduto().getColQtd());
                            break;
                        case F9:
                            getTpnNfe().setExpanded(!getTpnNfe().isExpanded());
                            showStatusBar();
                            if (getTpnNfe().isExpanded())
                                getTxtNfeDadosNumero().requestFocus();
                            break;
                        case F10:
                            if (getTpnNfe().isExpanded())
                                getChkPrintLoteProdutos().setSelected(!getChkPrintLoteProdutos().isSelected());
                            break;
                        case F12:
                            fechar();
                            break;
                        case HELP:
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
                ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerSaidaProduto());
                showStatusBar();
            } else {
                ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerSaidaProduto());
            }
        });

        getSaidaProdutoProdutoObservableList().addListener((ListChangeListener<? super SaidaProdutoProduto>) c -> {
            showStatusBar();
        });

        comboEmpresa = new ServiceAutoCompleteComboBox(Empresa.class, getCboEmpresa());

        new ServiceAutoCompleteComboBox(Empresa.class, getCboNfeTransporteTransportadora());

        empresaProperty().bind(Bindings.createObjectBinding(() -> {
            if (getCboEmpresa().getSelectionModel().getSelectedItem() == null)
                return new Empresa();
            return getCboEmpresa().getSelectionModel().getSelectedItem();
        }, getCboEmpresa().valueProperty()));

        empresaProperty().addListener((ov, o, n) -> {
            exibirEmpresaDetalhe();
            showStatusBar();
        });

        getCboEmpresa().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER && getCboEmpresa().getValue() != null)
                getTxtPesquisa().requestFocus();
        });

        getCboEndereco().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) {
                limpaEndereco();
                return;
            }
            getLblLogradoruro().setText(n.logradouroProperty().getValue());
            getLblNumero().setText(n.numeroProperty().getValue());
            getLblBairro().setText(n.bairroProperty().getValue());
            getLblComplemento().setText(n.complementoProperty().getValue());
        });

        prazoProperty().addListener((ov, o, n) -> {
            if (n == null)
                getDtpDtVencimento().setValue(getDtpDtSaida().getValue());
            else
                getDtpDtVencimento().setValue(getDtpDtSaida().getValue().plusDays(n.intValue()));
            getLblPrazo().setText(n.toString());
        });

        getTxtPesquisa().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            getTtvProdutoEstoque().requestFocus();
            getTtvProdutoEstoque().getSelectionModel().selectFirst();
        });

        getLblQtdItem().textProperty().bind(getTmodelSaidaProduto().totalQtdItemProperty().asString());
        getLblQtdTotal().textProperty().bind(getTmodelSaidaProduto().totalQtdProdutoProperty().asString());
        getLblQtdVolume().textProperty().bind(getTmodelSaidaProduto().totalQtdVolumeProperty().asString());
        getLblTotalBruto().textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(getTmodelSaidaProduto().totalBrutoProperty().getValue(), 2),
                getTmodelSaidaProduto().totalBrutoProperty()
        ));
        getLblTotalDesconto().textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(getTmodelSaidaProduto().totalDescontoProperty().getValue(), 2),
                getTmodelSaidaProduto().totalDescontoProperty()
        ));
        getLblTotalLiquido().textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(getTmodelSaidaProduto().totalLiquidoProperty().getValue(), 2),
                getTmodelSaidaProduto().totalLiquidoProperty()
        ));

        getTpnNfe().expandedProperty().addListener((ov, o, n) -> {
            if (n) {
                getTxtNfeDadosNumero().setText(String.valueOf(nfeLastNumberProperty().getValue() + 1));
                getTxtNfeDadosSerie().setText(String.valueOf(MYINFNFE.getMyConfig().getSerie()));
                getCboNfeDadosNaturezaOperacao().requestFocus();
            } else {
                limpaCampos(getTpnNfe());
                getTxtPesquisa().requestFocus();
            }
        });

        getCboNfeTransporteTransportadora().disableProperty().bind(getCboNfeTransporteModFrete().valueProperty().isEqualTo(MYINFNFE.getTransp().getModFretes().getModFrete().get(0)));

        getCboNfeTransporteTransportadora().disableProperty().addListener((ov, o, n) -> {
            if (n)
                getCboNfeTransporteTransportadora().getSelectionModel().select(-1);
        });

        informacoesAdicionasNFeProperty().bind(
                Bindings.createStringBinding(() -> refreshNFeInfAdicionas(),
                        getLblTotalLiquido().textProperty(), getDtpDtVencimento().valueProperty()));

        informacoesAdicionasNFeProperty().addListener((ov, o, n) -> {
            if (n == null)
                n = "";
            getTxaNfeInformacoesAdicionais().setText(n);
        });

        getDtpDtSaida().focusedProperty().addListener((ov, o, n) -> {
            ServiceFormatDataPicker.formatDataPicker(getDtpDtSaida(), n);
        });

        getDtpDtVencimento().focusedProperty().addListener((ov, o, n) -> {
            ServiceFormatDataPicker.formatDataPicker(getDtpDtVencimento(), n);
        });

        getDtpNfeDadosDtSaida().focusedProperty().addListener((ov, o, n) -> {
            ServiceFormatDataPicker.formatDataPicker(getDtpNfeDadosDtSaida(), n);
        });

        getDtpNfeDadosDtEmissao().focusedProperty().addListener((ov, o, n) -> {
            ServiceFormatDataPicker.formatDataPicker(getDtpNfeDadosDtEmissao(), n);
        });

        //getCboNfeTransporteModFrete().disableProperty().bind(getCboNfeTransporteModFrete().selectionModelProperty().isEqualTo(NfeTransporteModFrete.REMETENTE));
    }

    /**
     * Begin Tasks
     */

    private Task newTaskSaidaProduto() {
//        try {
        int qtdTasks = getEnumsTasksList().size();
        final int[] cont = {0};
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Loading...");
                try {
                    for (EnumsTasks tasks : getEnumsTasksList()) {
                        updateProgress(cont[0]++, qtdTasks);
                        Thread.sleep(200);
                        updateMessage(String.format("%s%s", tasks.getDescricao(),
                                tasks.getDescricao().endsWith(" de ") ? getNomeController() : ""));
                        switch (tasks) {
                            case TABELA_CRIAR:
                                setTmodelProduto(new TmodelProduto(TModelTipo.PROD_VENDA));
                                getTmodelProduto().tabela_criar();

                                setTmodelSaidaProduto(new TmodelSaidaProduto());
                                getTmodelSaidaProduto().criaTabela();
                                break;

                            case TABELA_VINCULAR:
                                getTmodelProduto().setLblRegistrosLocalizados(getLblRegistrosLocalizados());
                                getTmodelProduto().setTtvProdutoEstoque(getTtvProdutoEstoque());
                                getTmodelProduto().setTxtPesquisa(getTxtPesquisa());
                                getTmodelProduto().setProdutoFilteredList(new FilteredList<>(getProdutoObservableList()));
                                setProdutoFilteredList(getTmodelProduto().getProdutoFilteredList());
                                getTmodelProduto().escutaLista();

                                setFichaKardexList(new ArrayList<>());
                                getTmodelSaidaProduto().setFichaKardexList(getFichaKardexList());
                                getTmodelSaidaProduto().setTvItensNfe(getTvItensNfe());
                                getTmodelSaidaProduto().setTxtPesquisaProduto(getTxtPesquisa());
                                getTmodelSaidaProduto().setSaidaProdutoProdutoObservableList(getSaidaProdutoProdutoObservableList());
                                prazoProperty().bind(getTmodelSaidaProduto().prazoProperty());
                                getTmodelSaidaProduto().escutaLista();
                                break;

                            case COMBOS_PREENCHER:
                                loadListaEmpresas();
                                getCboNfeDadosNaturezaOperacao().setItems(
                                        MYINFNFE.getIde().getNatOps().getNatOp().stream().collect(Collectors.toCollection(FXCollections::observableArrayList))
                                );
//                                getCboNfeDadosNaturezaOperacao().setItems(
//                                        Arrays.stream(NfeDadosNaturezaOperacao.values()).collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeDadosModelo().setItems(
                                        MYINFNFE.getIde().getMods().getMod().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeDadosDestinoOperacao().setItems(
                                        MYINFNFE.getIde().getIdDests().getIdDest().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeDadosIndicadorConsumidorFinal().setItems(
                                        MYINFNFE.getIde().getIndFinals().getIndFinal().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeDadosIndicadorPresenca().setItems(
                                        MYINFNFE.getIde().getIndPress().getIndPres().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeImpressaoTpImp().setItems(
                                        MYINFNFE.getIde().getTpImps().getTpImp().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeImpressaoTpEmis().setItems(
                                        MYINFNFE.getIde().getTpEmiss().getTpEmis().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeImpressaoFinNFe().setItems(
                                        MYINFNFE.getIde().getFinNFes().getFinNFe().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeTransporteModFrete().setItems(
                                        MYINFNFE.getTransp().getModFretes().getModFrete().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeCobrancaDuplicataNumeros().setItems(
                                        Arrays.stream(NfeCobrancaDuplicataNumero.values()).collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeCobrancaPagamentoIndicador().setItems(
                                        MYINFNFE.getIde().getIndPags().getIndPag().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboNfeCobrancaPagamentoMeio().setItems(
                                        Arrays.stream(NfeCobrancaDuplicataPagamentoMeio.values()).collect(Collectors.toCollection(FXCollections::observableArrayList)));
                                break;

                            case TABELA_PREENCHER:
                                getTmodelProduto().tabela_preencher();

                                getTmodelSaidaProduto().preencheTabela();
                                break;

                            case SALVAR_ENT_SAIDA:
                                if (guardarSaidaProduto())
                                    if (salvarSaidaProduto()) {
                                        getProdutoObservableList().setAll(new ProdutoDAO().getAll(Produto.class, null, "descricao"));
                                        getTmodelProduto().atualizarProdutos();
                                    } else {
                                        Thread.currentThread().interrupt();
                                    }
                                else
                                    Thread.currentThread().interrupt();
                                break;
//                            case NFE_GERAR:
//
//                                break;
//                            case NFE_ASSINAR:
//                                break;
//                            case NFE_TRANSMITIR:
//                                break;
//                            case NFE_RETORNO:
//                                break;
                            case RELATORIO_IMPRIME_NFE:
//                                if (xmlNFeProcProperty().getValue() == null)
//                                    Thread.currentThread().interrupt();
//                                ControllerPrincipal.getCtrlPrincipal().getPrincipalStage().getScene().setCursor(Cursor.CROSSHAIR);
//                                //**ServiceFileXmlSave.saveTNfeProcToFile(nFev400Property().getValue().getProcNFe().gettNfeProc());
//                                ControllerPrincipal.getCtrlPrincipal().getPrincipalStage().getScene().setCursor(Cursor.DEFAULT);
                                break;
                        }
                    }
                } catch (Exception ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }
                updateMessage("tarefa concluída!!!");
                updateProgress(qtdTasks, qtdTasks);
                return null;
            }
        };
    }


    /**
     * END Tasks
     */

    /**
     * Begin voids
     */

    private void limpaCampos(TitledPane titledPane) {
        limpaCampos((AnchorPane) titledPane.getContent());
    }

    private void limpaCampos(AnchorPane anchorPane) {
        ServiceCampoPersonalizado.fieldClear(anchorPane);
        if (anchorPane.equals(getPainelViewSaidaProduto())) {
            comboEmpresa.clearCombo();
            getCboEmpresa().requestFocus();
//            ServiceComandoTecladoMouse.pressTecla(KeyCode.BACK_SPACE);
//            getCboEmpresa().getEditor().setText("");
//            getCboEmpresa().getSelectionModel().select(-1);
            getTpnNfe().setExpanded(false);
            getSaidaProdutoProdutoObservableList();
            getTmodelSaidaProduto().getSaidaProdutoProdutoObservableList().clear();
        }
    }

    private void limpaEndereco() {
        getLblLogradoruro().setText("");
        getLblNumero().setText("");
        getLblBairro().setText("");
        getLblComplemento().setText("");
    }

    private void escutaTitledTab() {
        getTpnNfe().expandedProperty().addListener((ov, o, n) -> {
            int diff = (getTpnNfe().getHeight() == 0) ? 85 : (int) getTpnNfe().getHeight() - 23;
            if (!n) diff = (diff * -1);
            ajustaTpnItensNota(diff);
//            getTpnNfe().setText(n ? "Informações de imposto" : "Nf-e sem imposto");
        });
    }

    private void ajustaTpnItensNota(int diff) {
        getTpnItensTotaisDetalhe().setLayoutY(getTpnItensTotaisDetalhe().getLayoutY() + diff);
        getTpnItensTotaisDetalhe().setPrefHeight(getTpnItensTotaisDetalhe().getPrefHeight() + (diff * -1));
        getvBoxItensNfeDetalhe().setPrefHeight(getvBoxItensNfeDetalhe().getPrefHeight() + (diff * -1));
    }

    private void showStatusBar() {
        try {
            if (getSaidaProdutoProdutoObservableList().size() <= 0)
                ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(statusBarProperty().getValue().getDescricao().replace("  [F2-Finalizar venda]", ""));
            else
                ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(statusBarProperty().getValue().getDescricao());
            if (!getTpnNfe().isExpanded())
                ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(statusBarProperty().getValue().getDescricao().replace("[F10-Print Lote NFe]  ", ""));
        } catch (Exception ex) {
            ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(statusBarProperty().getValue().getDescricao());
        }
    }

    private void loadListaEmpresas() throws Exception {
        ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList(
                new EmpresaDAO().getAll(Empresa.class, null, "razao, fantasia"));
        Empresa empTemp = new Empresa();
        empTemp.setRazao("");
        empTemp.setFantasia("");
        empresaObservableList.add(0, empTemp);

        getCboEmpresa().setItems(empresaObservableList);

        getCboNfeTransporteTransportadora().setItems(
                empresaObservableList.stream()
                        .filter(tranportadoras -> tranportadoras.isTransportadora())
                        .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        final BigDecimal[] vlrTotalUtilizado = {BigDecimal.ZERO};
        empresaObservableList.stream()
                .filter(empresa -> getContasAReceberObservableList().stream()
                        .filter(contasAReceber -> contasAReceber.saidaProdutoProperty().getValue()
                                .clienteProperty().getValue().idProperty().getValue().equals(empresa.idProperty().getValue())).count() > 0)
                .forEach(empresa -> {
//                    System.out.printf("emresa: [%s]", empresa.getRazaoFantasia());
                    vlrTotalUtilizado[0] = BigDecimal.ZERO;
                    getContasAReceberObservableList().stream()
                            .filter(aReceber -> aReceber.getSaidaProduto().getCliente().idProperty().getValue() == empresa.idProperty().getValue())
                            .sorted(Comparator.comparing(ContasAReceber::getDtCadastro).reversed())
                            .forEach(aReceber -> {
                                if (empresa.dtUltimoPedidoProperty().getValue() == null) {
                                    empresa.dtUltimoPedidoProperty().setValue(aReceber.dtCadastroProperty().getValue().toLocalDate());
                                    empresa.vlrUltimoPedidoProperty().setValue(aReceber.valorProperty().getValue());
//                                    System.out.printf("\tdtUltPedido: [%10s]\tvlrUltPedido: [R$%10s]\n",
//                                            empresa.dtUltimoPedidoProperty().getValue(),
//                                            empresa.vlrUltimoPedidoProperty().getValue().toString());
                                }
                                empresa.qtdPedidosProperty().setValue(empresa.qtdPedidosProperty().getValue() + 1);
                                BigDecimal totalAcm = empresa.vlrTotalPedidosProperty().getValue();
                                empresa.vlrTotalPedidosProperty().setValue(empresa.vlrTotalPedidosProperty().getValue()
                                        .add(aReceber.valorProperty().getValue()));
                                vlrTotalUtilizado[0] = vlrTotalUtilizado[0]
                                        .add(aReceber.valorProperty().getValue()
                                                .subtract(aReceber.getRecebimentoList().stream()
                                                        .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO))
                                                        .map(Recebimento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add)));
//                                System.out.printf("dtPedido: [%10s]\tvlrPedido: [R$%10s]\tacumulado: [R$%10s]\n" +
//                                                "qtdPed: [%03d]\tvlrtotalPedido: [R$%10s]\n" +
//                                                "limiteUtili: [R$%-10s]\n\n",
//                                        aReceber.dtCadastroProperty().getValue().format(DTF_DATA),
//                                        aReceber.valorProperty().getValue().toString(),
//                                        totalAcm,
//                                        empresa.qtdPedidosProperty().getValue(),
//                                        empresa.vlrTotalPedidosProperty().getValue().toString(),
//                                        vlrTotalUtilizado[0].toString());
                            });
                    empresa.limiteUtilizadoProperty().setValue(vlrTotalUtilizado[0]);
//                    empresa.limiteUtilizadoProperty().setValue(
//                            contasAReceberObservableList.stream()
//                                    .filter(aReceber -> aReceber.getSaidaProduto().getCliente().idProperty().getValue() == empresa.idProperty().getValue())
//                                    .map(ContasAReceber::getValor)
//                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
//                                    .subtract(
//                                            contasAReceberObservableList.stream()
//                                                    .filter(aReceber -> aReceber.getSaidaProduto().getCliente().idProperty().getValue() ==
//                                                            empresa.idProperty().getValue())
//                                                    .map(ContasAReceber::getRecebimentoList)
//                                                    .map(recebimentos -> recebimentos.stream()
//                                                            .filter(recebimento -> recebimento.getPagamentoSituacao().equals(PagamentoSituacao.QUITADO))
//                                                            .map(Recebimento::getValor)
//                                                            .reduce(BigDecimal.ZERO, BigDecimal::add))
//                                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
//                                    ));
                });
        nfeLastNumberProperty().setValue(NfeService.getLastNumeroNFe());
    }

    private void exibirEmpresaDetalhe() {
        //limpaCampos(getTpnCliente());
        getTmodelSaidaProduto().empresaProperty().setValue(empresaProperty().getValue());
        showStatusBar();

        //if (empresaProperty().getValue() == null) return;

        getDtpDtSaida().setValue(LocalDate.now());
        getLblPrazo().setText(prazoProperty().getValue().toString());
        getDtpDtVencimento().setValue(getDtpDtSaida().getValue().plusDays(prazoProperty().getValue()));
        getLblLimite().setText(ServiceMascara.getMoeda(empresaProperty().getValue().limiteProperty().getValue(), 2));
        getLblLimiteUtilizado().setText(ServiceMascara.getMoeda(empresaProperty().getValue().limiteUtilizadoProperty().getValue(), 2));
        getLblLimiteDisponivel().setText(ServiceMascara.getMoeda(
                empresaProperty().getValue().limiteProperty().getValue()
                        .subtract(empresaProperty().getValue().limiteUtilizadoProperty().getValue()), 2));

        getLblUltimoPedidoDt().setText(empresaProperty().getValue().dtUltimoPedidoProperty().getValue() != null
                ? empresaProperty().getValue().dtUltimoPedidoProperty().getValue().format(DTF_DATA)
                : "");

        getLblUltimoPedidoDias().setText(empresaProperty().getValue() == null
                ? ""
                : (empresaProperty().getValue().dtUltimoPedidoProperty().getValue() != null
                ? String.valueOf(DAYS.between(empresaProperty().getValue().dtUltimoPedidoProperty().getValue(), LocalDate.now()))
                : (empresaProperty().getValue().dtCadastroProperty().getValue() == null
                ? ""
                : String.valueOf(DAYS.between(empresaProperty().getValue().dtCadastroProperty().getValue().toLocalDate(), LocalDate.now())))));

        getLblUltimoPedidoVlr().setText(ServiceMascara.getMoeda(empresaProperty().getValue().vlrUltimoPedidoProperty().getValue(), 2));
        getLblQtdPedidos().setText(empresaProperty().getValue().qtdPedidosProperty().getValue().toString());

        BigDecimal vlrTicket = BigDecimal.ZERO;
        if (empresaProperty().getValue().qtdPedidosProperty().getValue().compareTo(0) > 0)
            vlrTicket = empresaProperty().getValue().vlrTotalPedidosProperty().getValue()
                    .divide(BigDecimal.valueOf(empresaProperty().getValue().qtdPedidosProperty().getValue()), 4, RoundingMode.HALF_UP);
        getLblTicketMedioVlr().setText(ServiceMascara.getMoeda(vlrTicket, 2));

        if (empresaProperty().getValue().getEnderecoList() != null)
            getCboEndereco().setItems(empresaProperty().getValue().getEnderecoList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
        else
            getCboEndereco().getItems().clear();

        if (empresaProperty().getValue().getTelefoneList() != null)
            getCboTelefone().setItems(empresaProperty().getValue().getTelefoneList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
        else
            getCboTelefone().getItems().clear();

        getCboEndereco().getSelectionModel().select(0);
        getCboTelefone().getSelectionModel().select(0);

        getCboEndereco().getItems().stream()
                .filter(endereco -> endereco.getTipo().equals(TipoEndereco.ENTREGA))
                .findFirst().ifPresent(endereco -> getCboEndereco().getSelectionModel().select(endereco));

    }

    private void guardarSaidaProdutoProduto() {
        getSaidaProdutoProdutoObservableList().stream()
                .forEach(saidaProdutoProduto -> {
                    saidaProdutoProduto.saidaProdutoProperty().setValue(getSaidaProduto());
                });
    }

    private void atualizaTotaisCliente() {
        empresaProperty().getValue().vlrUltimoPedidoProperty()
                .setValue(ServiceMascara.getBigDecimalFromTextField(getLblTotalLiquido().getText(), 4));

        empresaProperty().getValue().limiteUtilizadoProperty().setValue(
                empresaProperty().getValue().limiteUtilizadoProperty().getValue()
                        .add(empresaProperty().getValue().vlrUltimoPedidoProperty().getValue()));

        empresaProperty().getValue().dtUltimoPedidoProperty().setValue(
                getSaidaProduto().dtCadastroProperty().getValue().toLocalDate());

        empresaProperty().getValue().vlrTotalPedidosProperty().setValue(
                empresaProperty().getValue().vlrTotalPedidosProperty().getValue()
                        .add(ServiceMascara.getBigDecimalFromTextField(getLblTotalLiquido().getText(), 4))
        );

        empresaProperty().getValue().qtdPedidosProperty()
                .setValue(empresaProperty().getValue().qtdPedidosProperty().getValue() + 1);

        if (saidaProdutoNfeProperty().getValue() != null)
            nfeLastNumberProperty().setValue(saidaProdutoNfeProperty().getValue().numeroProperty().getValue());

    }

    private void salvarContasAReceber() {
        ContasAReceber contasAReceber = new ContasAReceber();
        contasAReceber.saidaProdutoProperty().setValue(getSaidaProduto());
        getSaidaProduto().contasAReceberProperty().setValue(contasAReceber);

        contasAReceber.dtVencimentoProperty().setValue(getDtpDtVencimento().getValue());
        contasAReceber.valorProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getLblTotalLiquido().getText(), 2));
        contasAReceber.usuarioCadastroProperty().setValue(UsuarioLogado.getUsuario());
    }

    private void baixarCreditoDebito(BigDecimal valorDebito) throws Exception {
        final BigDecimal[] vlrDebito = {valorDebito};
        final boolean[] alterou = {false};
        getContasAReceberDAO().transactionBegin();
        try {
            getContasAReceberObservableList().stream()
                    .sorted(Comparator.comparing(ContasAReceber::getDtCadastro))
                    .filter(contasAReceber -> contasAReceber.getSaidaProduto().getCliente().idProperty().getValue() == empresaProperty().getValue().idProperty().getValue()
                            && contasAReceber.getRecebimentoList().stream()
                            .filter(recebimento -> recebimento.getPagamentoSituacao().equals(PagamentoSituacao.QUITADO))
                            .map(Recebimento::getValor)
                            .reduce(BigDecimal.ZERO, BigDecimal::add).compareTo(contasAReceber.valorProperty().getValue()) != 0)
                    .forEach(contasAReceber -> {
                        try {
                            if (vlrDebito[0].compareTo(BigDecimal.ZERO) < 0) {
                                vlrDebito[0] = vlrDebito[0].negate();
                                BigDecimal saldoConta = contasAReceber.valorProperty().getValue().subtract(contasAReceber.getRecebimentoList().stream()
                                        .filter(recebimento -> recebimento.getPagamentoSituacao().equals(PagamentoSituacao.QUITADO))
                                        .map(Recebimento::getValor)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add));
                                if (vlrDebito[0].compareTo(saldoConta) < 0)
                                    saldoConta = vlrDebito[0];
                                contasAReceber.getRecebimentoList().add(addRecebimento(contasAReceber,
                                        PagamentoModalidade.BAIXA_CREDITO, saldoConta));
                                contasAReceber = getContasAReceberDAO().setTransactionPersist(contasAReceber);
                                alterou[0] = true;
                                vlrDebito[0] = vlrDebito[0].add(saldoConta);
                            } else if (vlrDebito[0].compareTo(BigDecimal.ZERO) > 0) {
                                BigDecimal saldoConta = contasAReceber.valorProperty().getValue().subtract(contasAReceber.getRecebimentoList().stream()
                                        .filter(recebimento -> recebimento.getPagamentoSituacao().equals(PagamentoSituacao.QUITADO))
                                        .map(Recebimento::getValor)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add));
                                if (contasAReceber.valorProperty().getValue().compareTo(saldoConta) != 0) {
                                    contasAReceber.getRecebimentoList().add(addRecebimento(contasAReceber,
                                            PagamentoModalidade.BAIXA_DEBITO, saldoConta));
                                } else {
                                    contasAReceber.getRecebimentoList().stream()
                                            .filter(recebimento -> !recebimento.getPagamentoSituacao().equals(PagamentoSituacao.QUITADO))
                                            .forEach(recebimento -> {
                                                recebimento.setPagamentoSituacao(PagamentoSituacao.QUITADO);
                                                recebimento.setPagamentoModalidade(PagamentoModalidade.BAIXA_DEBITO);
                                                recebimento.setValor(saldoConta);
                                            });
                                }
                                contasAReceber = getContasAReceberDAO().setTransactionPersist(contasAReceber);
                                alterou[0] = true;
                                vlrDebito[0] = vlrDebito[0].subtract(saldoConta);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    });
            if (alterou[0])
                getContasAReceberDAO().transactionCommit();
        } catch (Exception ex) {
            getContasAReceberDAO().transactionRollback();
            ex.printStackTrace();
        }
    }

    /**
     * END voids
     */


    /**
     * Begin returns
     */

    private ProdutoEstoque getEstoqueSelecionado() {
        ProdutoEstoque estoqueSelecionado = null;
        if (getTtvProdutoEstoque().isFocused()) {
            if (getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue() instanceof ProdutoEstoque)
                estoqueSelecionado = ((ProdutoEstoque) getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue());
            else
                estoqueSelecionado = ((ProdutoEstoque) getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getChildren().get(0).getValue());
        } else if (getTvItensNfe().isFocused()) {
            estoqueSelecionado = getTvItensNfe().getSelectionModel().getSelectedItem().produtoProperty().getValue().getProdutoEstoqueList().stream()
                    .filter(produtoEstoque -> produtoEstoque.loteProperty().getValue().equals(getTvItensNfe().getSelectionModel().getSelectedItem().loteProperty().getValue()))
                    .findFirst().orElse(null);
        }
        return estoqueSelecionado;
    }

    private boolean guardarSaidaProduto() {
        try {
            setSaidaProduto(new SaidaProduto());

            getSaidaProduto().setCliente(getCboEmpresa().getSelectionModel().getSelectedItem());
            getSaidaProduto().setVendedor(UsuarioLogado.usuarioProperty().getValue());
            getSaidaProduto().setDtSaida(getDtpDtSaida().getValue());

            guardarSaidaProdutoProduto();
            getSaidaProduto().setSaidaProdutoProdutoList(getSaidaProdutoProdutoObservableList());

            if (getTpnNfe().isExpanded()) {
                saidaProdutoNfeProperty().setValue(new SaidaProdutoNfe());
                getSaidaProduto().getSaidaProdutoNfeList().add(saidaProdutoNfeProperty().getValue());
                saidaProdutoNfeProperty().getValue().saidaProdutoProperty().setValue(getSaidaProduto());

                saidaProdutoNfeProperty().getValue().canceladaProperty().setValue(false);
                saidaProdutoNfeProperty().getValue().statusSefazProperty().setValue(MYINFNFE.getSefaz().getStatuss().getStatus().stream()
                        .filter(status -> status.getSimplificada().toLowerCase().equals("digitacao")).findFirst().get().getId());
                saidaProdutoNfeProperty().getValue().naturezaOperacaoProperty().setValue(getCboNfeDadosNaturezaOperacao().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().modeloProperty().setValue(getCboNfeDadosModelo().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().serieProperty().setValue(Integer.valueOf(getTxtNfeDadosSerie().getText()));
                saidaProdutoNfeProperty().getValue().numeroProperty().setValue(Integer.valueOf(getTxtNfeDadosNumero().getText()));
                saidaProdutoNfeProperty().getValue().dtHoraEmissaoProperty().setValue(getDtpNfeDadosDtEmissao().getValue()
                        .atTime(LocalTime.parse(getTxtNfeDadosHoraEmissao().getText())));
                saidaProdutoNfeProperty().getValue().dtHoraSaidaProperty().setValue(getDtpNfeDadosDtSaida().getValue()
                        .atTime(LocalTime.parse(getTxtNfeDadosHoraSaida().getText())));
                saidaProdutoNfeProperty().getValue().destinoOperacaoProperty().setValue(getCboNfeDadosDestinoOperacao().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().impressaoTpImpProperty().setValue(getCboNfeImpressaoTpImp().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().impressaoTpEmisProperty().setValue(getCboNfeImpressaoTpEmis().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().impressaoFinNFeProperty().setValue(getCboNfeImpressaoFinNFe().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().impressaoLtProdutoProperty().setValue(getChkPrintLoteProdutos().isSelected());
                saidaProdutoNfeProperty().getValue().consumidorFinalProperty().setValue(getCboNfeDadosIndicadorConsumidorFinal().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().indicadorPresencaProperty().setValue(getCboNfeDadosIndicadorPresenca().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().modFreteProperty().setValue(getCboNfeTransporteModFrete().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().transportadorProperty().setValue(getCboNfeTransporteTransportadora().getSelectionModel().getSelectedItem());
                saidaProdutoNfeProperty().getValue().cobrancaNumeroProperty().setValue(getCboNfeCobrancaDuplicataNumeros().getSelectionModel().getSelectedItem().getDescricao());
                saidaProdutoNfeProperty().getValue().pagamentoIndicadorProperty().setValue(getCboNfeCobrancaPagamentoIndicador().getSelectionModel().getSelectedItem().getId());
                saidaProdutoNfeProperty().getValue().pagamentoMeioProperty().setValue(getCboNfeCobrancaPagamentoMeio().getSelectionModel().getSelectedItem().getCod());
                saidaProdutoNfeProperty().getValue().informacaoAdicionalProperty().setValue(getTxaNfeInformacoesAdicionais().getText().trim());

                saidaProdutoNfeProperty().getValue().chaveProperty().setValue(getChaveNfe(saidaProdutoNfeProperty().getValue()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private Recebimento addRecebimento(ContasAReceber contasAReceber, PagamentoModalidade pagamentoModalidade, BigDecimal vlr) {
        Recebimento recebimento = new Recebimento();
        recebimento.contasAReceberProperty().setValue(contasAReceber);
        if (pagamentoModalidade.getDescricao().toLowerCase().contains("baixa"))
            recebimento.pagamentoSituacaoProperty().setValue(PagamentoSituacao.QUITADO);
        else
            recebimento.pagamentoSituacaoProperty().setValue(PagamentoSituacao.PENDENTE);
        recebimento.pagamentoModalidadeProperty().setValue(pagamentoModalidade);
        recebimento.valorProperty().setValue(vlr);

        recebimento.setUsuarioPagamento(UsuarioLogado.getUsuario());
        recebimento.dtPagamentoProperty().setValue(LocalDate.now());
        recebimento.setUsuarioCadastro(UsuarioLogado.getUsuario());

        String codDocRecebimento =
                ServiceValidarDado.gerarCodigoCafePerfeito(Recebimento.class, getDtpDtSaida().getValue());


        switch (pagamentoModalidade) {
            case CREDITO:
                recebimento.valorProperty().setValue(vlr.multiply(new BigDecimal("-1.0")));
            case BAIXA_CREDITO:
                recebimento.setPagamentoSituacao(PagamentoSituacao.QUITADO);
                recebimento.documentoProperty().setValue(String.format("UC%s", codDocRecebimento));
                break;

            case DEBITO:
                recebimento.valorProperty().setValue(vlr.multiply(new BigDecimal("-1.0")));
            case BAIXA_DEBITO:
                recebimento.documentoProperty().setValue(String.format("UD%s", codDocRecebimento));
            default:
                recebimento.documentoProperty().setValue(codDocRecebimento);
                break;
        }

        return recebimento;
    }

    private boolean validarSaida() {
        System.out.printf("validarCliente:[%s]", validarCliente());
        System.out.printf("\tvalidarNFe:[%s]", validarNFe());
        System.out.printf("\tlimiteDisponivel:[%s]", ServiceMascara.getBigDecimalFromTextField(getLblLimiteDisponivel().getText(), 2));
        System.out.printf("\ttotalLiquido:[%s]", ServiceMascara.getBigDecimalFromTextField(getLblTotalLiquido().getText(), 2));
        System.out.printf("\tvlrValido:[%s]\n", (ServiceMascara.getBigDecimalFromTextField(getLblLimiteDisponivel().getText(), 2)
                .compareTo(ServiceMascara.getBigDecimalFromTextField(getLblTotalLiquido().getText(), 2)) >= 0));
        boolean result = ((validarCliente() && validarNFe()
                && getSaidaProdutoProdutoObservableList().size() > 0)
                && (ServiceMascara.getBigDecimalFromTextField(getLblLimiteDisponivel().getText(), 2)
                .compareTo(ServiceMascara.getBigDecimalFromTextField(getLblTotalLiquido().getText(), 2)) >= 0)
        );
        return result;
    }

    private boolean validarCliente() {
        if (getDtpDtSaida().getValue() == null) {
            getDtpDtSaida().requestFocus();
            return false;
        }
        if (getDtpDtVencimento().getValue() == null) {
            getDtpDtVencimento().requestFocus();
            return false;
        }
        if (getCboEmpresa().getSelectionModel().getSelectedItem() == null
                && getCboEmpresa().getValue() == null) {
            getCboEmpresa().requestFocus();
            return false;
        }
        if (getCboEndereco().getSelectionModel().getSelectedItem() == null) {
            getCboEndereco().getSelectionModel().select(0);
        }
        return true;
    }

    private boolean validarNFe() {
        if (!getTpnNfe().isExpanded())
            return true;
        if (getCboNfeDadosNaturezaOperacao().getSelectionModel().getSelectedItem() == null) {
            getCboNfeDadosNaturezaOperacao().requestFocus();
            return false;
        }
        if (getTxtNfeDadosNumero().getText().trim().replaceAll("\\D", "").length() == 0) {
            getTxtNfeDadosNumero().requestFocus();
            return false;
        }
        if (getTxtNfeDadosSerie().getText().trim().replaceAll("\\D", "").length() == 0) {
            getTxtNfeDadosSerie().requestFocus();
            return false;
        }
        if (getCboNfeDadosModelo().getSelectionModel().getSelectedItem() == null) {
            getCboNfeDadosModelo().requestFocus();
            return false;
        }
        if (getDtpNfeDadosDtEmissao().getValue() == null || getDtpNfeDadosDtEmissao().getValue().compareTo(LocalDate.now()) > 0) {
            getDtpNfeDadosDtEmissao().requestFocus();
            return false;
        }
        if (getTxtNfeDadosHoraEmissao().getText().trim().replaceAll("\\D", "").length() == 0) {
            getTxtNfeDadosHoraEmissao().requestFocus();
            return false;
        }
        if (getDtpNfeDadosDtSaida().getValue() == null || getDtpNfeDadosDtSaida().getValue().compareTo(LocalDate.now()) > 0) {
            getDtpNfeDadosDtSaida().requestFocus();
            return false;
        }
        if (getTxtNfeDadosHoraSaida().getText().trim().replaceAll("\\D", "").length() == 0) {
            getTxtNfeDadosHoraSaida().requestFocus();
            return false;
        }
        if (getCboNfeDadosDestinoOperacao().getSelectionModel().getSelectedItem() == null) {
            getCboNfeDadosDestinoOperacao().requestFocus();
            return false;
        }
        return true;
    }

    private boolean salvarSaidaProduto() {
        try {
            setSaidaProdutoDAO(new SaidaProdutoDAO());
            if (!getTmodelSaidaProduto().baixarEstoque()) return false;
            salvarContasAReceber();
            saidaProdutoProperty().setValue(getSaidaProdutoDAO().merger(saidaProdutoProperty().getValue()));
            if (getSaidaProdutoNfe() != null)
                setSaidaProdutoNfe(saidaProduto.getValue().getSaidaProdutoNfeList().get(saidaProduto.getValue().getSaidaProdutoNfeList().size() - 1));
            salvarFichaKardexList();

        } catch (Exception ex) {
            ex.printStackTrace();
            getSaidaProdutoDAO().transactionRollback();
            return false;
        }
        return true;
    }

    private boolean salvarFichaKardexList() {
        FichaKardexDAO fichaKardexDAO = new FichaKardexDAO();
        try {
            fichaKardexDAO.transactionBegin();
            for (FichaKardex ficha : getFichaKardexList()) {
                ficha.setDocumento(getSaidaProduto().idProperty().getValue().toString());
                fichaKardexDAO.setTransactionPersist(ficha);
            }
            fichaKardexDAO.transactionCommit();
        } catch (Exception ex) {
            fichaKardexDAO.transactionRollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean gerarDanfe() throws Exception {
        getEnumsTasksList().clear();
//        getEnumsTasksList().add(EnumsTasks.NFE_GERAR);
//        getEnumsTasksList().add(EnumsTasks.NFE_ASSINAR);
//        getEnumsTasksList().add(EnumsTasks.NFE_TRANSMITIR);
//        getEnumsTasksList().add(EnumsTasks.NFE_RETORNO);
        getEnumsTasksList().add(EnumsTasks.RELATORIO_IMPRIME_NFE);
        if (new ServiceSegundoPlano().executaListaTarefas(newTaskSaidaProduto(), String.format("Gerando NFe [%d]!", Integer.valueOf(getTxtNfeDadosNumero().getText())))) {
            new Alert_Ok("Sucesso NFe",
                    "NFe foi gerada com sucesso!!!",
                    null);
            return true;
        }
        new Alert_Ok("Retorno inválido",
                "alguma coisa de errado aconteceu com a nota!!!",
                null);
        return false;

    }

    private String refreshNFeInfAdicionas() {
        Banco bancoPadrao = MYINFNFE.getMeusBancos().getBancos().getBanco().stream()
                .filter(banco1 -> banco1.isPadrão()).findFirst().get();
        String strInf = String.format(MYINFNFE.getMyConfig().getInfAdic(),
                getLblTotalLiquido().getText(),
                (getDtpDtVencimento().getValue() != null)
                        ? String.format("dt. Venc.: %s",
                        getDtpDtVencimento().getValue().format(DTF_DATA))
                        : "",
                bancoPadrao.getNome(),
                bancoPadrao.getAgencia(),
                bancoPadrao.getConta())
                .toUpperCase();

        int start = 0, end = 0;
        String retorno = strInf;
        if (getTxaNfeInformacoesAdicionais().getText().length() > 0) {
            start = getTxaNfeInformacoesAdicionais().getText().indexOf("-** ");
            end = getTxaNfeInformacoesAdicionais().getText().indexOf(" **-");
            if (end > 0)
                retorno = getTxaNfeInformacoesAdicionais().getText().replace(
                        getTxaNfeInformacoesAdicionais().getText().substring(start, end + 4),
                        strInf
                );
        }
        return retorno;
    }

    private BigDecimal utilizacaoDeCreditoDebito() {
        BigDecimal vlrCredDeb = ServiceMascara.getBigDecimalFromTextField(getLblLimiteUtilizado().getText(), 2);

        if (vlrCredDeb.compareTo(BigDecimal.ZERO) != 0) {
            String strCabecalho = null, strContextText = null, strIcone = null;
            if (vlrCredDeb.compareTo(BigDecimal.ZERO) < 0) {
                strCabecalho = "Crédito disponível";
                strContextText = String.format("o cliente tem um crédito de R$ %s\ndeseja utilizar esse valor para abater no pedido?",
                        ServiceMascara.getMoeda((vlrCredDeb.multiply(new BigDecimal("-1."))), 2));
                strIcone = null;
            } else if (vlrCredDeb.compareTo(BigDecimal.ZERO) > 0) {
                strCabecalho = "Débito detectado";
                strContextText = String.format("o cliente tem um dédito de R$ %s\ndeseja acrescentar esse valor no pedido atual?",
                        ServiceMascara.getMoeda((vlrCredDeb.multiply(new BigDecimal("-1."))), 2));
                strIcone = null;
            }
            ButtonType retorno = new Alert_YesNoCancel(strCabecalho, strContextText, strIcone).retorno().get();
            if (retorno.equals(ButtonType.NO))
                return BigDecimal.ZERO;
            if (retorno.equals(ButtonType.CANCEL))
                return null;
        }
        return vlrCredDeb;
    }


    /**
     * END returns
     */

    /**
     * Begin Getters e Setters
     */

    public AnchorPane getPainelViewSaidaProduto() {
        return painelViewSaidaProduto;
    }

    public void setPainelViewSaidaProduto(AnchorPane painelViewSaidaProduto) {
        this.painelViewSaidaProduto = painelViewSaidaProduto;
    }

    public TitledPane getTpnCliente() {
        return tpnCliente;
    }

    public void setTpnCliente(TitledPane tpnCliente) {
        this.tpnCliente = tpnCliente;
    }

    public DatePicker getDtpDtSaida() {
        return dtpDtSaida;
    }

    public void setDtpDtSaida(DatePicker dtpDtSaida) {
        this.dtpDtSaida = dtpDtSaida;
    }

    public DatePicker getDtpDtVencimento() {
        return dtpDtVencimento;
    }

    public void setDtpDtVencimento(DatePicker dtpDtVencimento) {
        this.dtpDtVencimento = dtpDtVencimento;
    }

    public ComboBox<Empresa> getCboEmpresa() {
        return cboEmpresa;
    }

    public void setCboEmpresa(ComboBox<Empresa> cboEmpresa) {
        this.cboEmpresa = cboEmpresa;
    }

    public Label getLblLimite() {
        return lblLimite;
    }

    public void setLblLimite(Label lblLimite) {
        this.lblLimite = lblLimite;
    }

    public Label getLblLimiteUtilizado() {
        return lblLimiteUtilizado;
    }

    public void setLblLimiteUtilizado(Label lblLimiteUtilizado) {
        this.lblLimiteUtilizado = lblLimiteUtilizado;
    }

    public Label getLblLimiteDisponivel() {
        return lblLimiteDisponivel;
    }

    public void setLblLimiteDisponivel(Label lblLimiteDisponivel) {
        this.lblLimiteDisponivel = lblLimiteDisponivel;
    }

    public Label getLblPrazo() {
        return lblPrazo;
    }

    public void setLblPrazo(Label lblPrazo) {
        this.lblPrazo = lblPrazo;
    }

    public Label getLabelUltimoPedidoDt() {
        return labelUltimoPedidoDt;
    }

    public void setLabelUltimoPedidoDt(Label labelUltimoPedidoDt) {
        this.labelUltimoPedidoDt = labelUltimoPedidoDt;
    }

    public Label getLblUltimoPedidoDt() {
        return lblUltimoPedidoDt;
    }

    public void setLblUltimoPedidoDt(Label lblUltimoPedidoDt) {
        this.lblUltimoPedidoDt = lblUltimoPedidoDt;
    }

    public Label getLblUltimoPedidoDias() {
        return lblUltimoPedidoDias;
    }

    public void setLblUltimoPedidoDias(Label lblUltimoPedidoDias) {
        this.lblUltimoPedidoDias = lblUltimoPedidoDias;
    }

    public Label getLblUltimoPedidoVlr() {
        return lblUltimoPedidoVlr;
    }

    public void setLblUltimoPedidoVlr(Label lblUltimoPedidoVlr) {
        this.lblUltimoPedidoVlr = lblUltimoPedidoVlr;
    }

    public Label getLblQtdPedidos() {
        return lblQtdPedidos;
    }

    public void setLblQtdPedidos(Label lblQtdPedidos) {
        this.lblQtdPedidos = lblQtdPedidos;
    }

    public Label getLblTicketMedioVlr() {
        return lblTicketMedioVlr;
    }

    public void setLblTicketMedioVlr(Label lblTicketMedioVlr) {
        this.lblTicketMedioVlr = lblTicketMedioVlr;
    }

    public ComboBox<Endereco> getCboEndereco() {
        return cboEndereco;
    }

    public void setCboEndereco(ComboBox<Endereco> cboEndereco) {
        this.cboEndereco = cboEndereco;
    }

    public Label getLblLogradoruro() {
        return lblLogradoruro;
    }

    public void setLblLogradoruro(Label lblLogradoruro) {
        this.lblLogradoruro = lblLogradoruro;
    }

    public Label getLblNumero() {
        return lblNumero;
    }

    public void setLblNumero(Label lblNumero) {
        this.lblNumero = lblNumero;
    }

    public Label getLblBairro() {
        return lblBairro;
    }

    public void setLblBairro(Label lblBairro) {
        this.lblBairro = lblBairro;
    }

    public Label getLblComplemento() {
        return lblComplemento;
    }

    public void setLblComplemento(Label lblComplemento) {
        this.lblComplemento = lblComplemento;
    }

    public ComboBox<Telefone> getCboTelefone() {
        return cboTelefone;
    }

    public void setCboTelefone(ComboBox<Telefone> cboTelefone) {
        this.cboTelefone = cboTelefone;
    }

    public TitledPane getTpnNfe() {
        return tpnNfe;
    }

    public void setTpnNfe(TitledPane tpnNfe) {
        this.tpnNfe = tpnNfe;
    }

    public Tab getTabNfeDados() {
        return tabNfeDados;
    }

    public void setTabNfeDados(Tab tabNfeDados) {
        this.tabNfeDados = tabNfeDados;
    }

    public ComboBox<NatOp> getCboNfeDadosNaturezaOperacao() {
        return cboNfeDadosNaturezaOperacao;
    }

    public void setCboNfeDadosNaturezaOperacao(ComboBox<NatOp> cboNfeDadosNaturezaOperacao) {
        this.cboNfeDadosNaturezaOperacao = cboNfeDadosNaturezaOperacao;
    }

    public TextField getTxtNfeDadosNumero() {
        return txtNfeDadosNumero;
    }

    public void setTxtNfeDadosNumero(TextField txtNfeDadosNumero) {
        this.txtNfeDadosNumero = txtNfeDadosNumero;
    }

    public TextField getTxtNfeDadosSerie() {
        return txtNfeDadosSerie;
    }

    public void setTxtNfeDadosSerie(TextField txtNfeDadosSerie) {
        this.txtNfeDadosSerie = txtNfeDadosSerie;
    }

    public ComboBox<Mod> getCboNfeDadosModelo() {
        return cboNfeDadosModelo;
    }

    public void setCboNfeDadosModelo(ComboBox<Mod> cboNfeDadosModelo) {
        this.cboNfeDadosModelo = cboNfeDadosModelo;
    }

    public DatePicker getDtpNfeDadosDtEmissao() {
        return dtpNfeDadosDtEmissao;
    }

    public void setDtpNfeDadosDtEmissao(DatePicker dtpNfeDadosDtEmissao) {
        this.dtpNfeDadosDtEmissao = dtpNfeDadosDtEmissao;
    }

    public TextField getTxtNfeDadosHoraEmissao() {
        return txtNfeDadosHoraEmissao;
    }

    public void setTxtNfeDadosHoraEmissao(TextField txtNfeDadosHoraEmissao) {
        this.txtNfeDadosHoraEmissao = txtNfeDadosHoraEmissao;
    }

    public DatePicker getDtpNfeDadosDtSaida() {
        return dtpNfeDadosDtSaida;
    }

    public void setDtpNfeDadosDtSaida(DatePicker dtpNfeDadosDtSaida) {
        this.dtpNfeDadosDtSaida = dtpNfeDadosDtSaida;
    }

    public TextField getTxtNfeDadosHoraSaida() {
        return txtNfeDadosHoraSaida;
    }

    public void setTxtNfeDadosHoraSaida(TextField txtNfeDadosHoraSaida) {
        this.txtNfeDadosHoraSaida = txtNfeDadosHoraSaida;
    }

    public ComboBox<IdDest> getCboNfeDadosDestinoOperacao() {
        return cboNfeDadosDestinoOperacao;
    }

    public void setCboNfeDadosDestinoOperacao(ComboBox<IdDest> cboNfeDadosDestinoOperacao) {
        this.cboNfeDadosDestinoOperacao = cboNfeDadosDestinoOperacao;
    }

    public ComboBox<IndFinal> getCboNfeDadosIndicadorConsumidorFinal() {
        return cboNfeDadosIndicadorConsumidorFinal;
    }

    public void setCboNfeDadosIndicadorConsumidorFinal(ComboBox<IndFinal> cboNfeDadosIndicadorConsumidorFinal) {
        this.cboNfeDadosIndicadorConsumidorFinal = cboNfeDadosIndicadorConsumidorFinal;
    }

    public ComboBox<IndPres> getCboNfeDadosIndicadorPresenca() {
        return cboNfeDadosIndicadorPresenca;
    }

    public void setCboNfeDadosIndicadorPresenca(ComboBox<IndPres> cboNfeDadosIndicadorPresenca) {
        this.cboNfeDadosIndicadorPresenca = cboNfeDadosIndicadorPresenca;
    }

    public Tab getTabNfeImpressao() {
        return tabNfeImpressao;
    }

    public void setTabNfeImpressao(Tab tabNfeImpressao) {
        this.tabNfeImpressao = tabNfeImpressao;
    }

    public ComboBox<TpImp> getCboNfeImpressaoTpImp() {
        return cboNfeImpressaoTpImp;
    }

    public void setCboNfeImpressaoTpImp(ComboBox<TpImp> cboNfeImpressaoTpImp) {
        this.cboNfeImpressaoTpImp = cboNfeImpressaoTpImp;
    }

    public ComboBox<TpEmis> getCboNfeImpressaoTpEmis() {
        return cboNfeImpressaoTpEmis;
    }

    public void setCboNfeImpressaoTpEmis(ComboBox<TpEmis> cboNfeImpressaoTpEmis) {
        this.cboNfeImpressaoTpEmis = cboNfeImpressaoTpEmis;
    }

    public ComboBox<FinNFe> getCboNfeImpressaoFinNFe() {
        return cboNfeImpressaoFinNFe;
    }

    public void setCboNfeImpressaoFinNFe(ComboBox<FinNFe> cboNfeImpressaoFinNFe) {
        this.cboNfeImpressaoFinNFe = cboNfeImpressaoFinNFe;
    }

    public Tab getTabNfeTransporta() {
        return tabNfeTransporta;
    }

    public void setTabNfeTransporta(Tab tabNfeTransporta) {
        this.tabNfeTransporta = tabNfeTransporta;
    }

    public ComboBox<ModFrete> getCboNfeTransporteModFrete() {
        return cboNfeTransporteModFrete;
    }

    public void setCboNfeTransporteModFrete(ComboBox<ModFrete> cboNfeTransporteModFrete) {
        this.cboNfeTransporteModFrete = cboNfeTransporteModFrete;
    }

    public ComboBox<Empresa> getCboNfeTransporteTransportadora() {
        return cboNfeTransporteTransportadora;
    }

    public void setCboNfeTransporteTransportadora(ComboBox<Empresa> cboNfeTransporteTransportadora) {
        this.cboNfeTransporteTransportadora = cboNfeTransporteTransportadora;
    }

    public Tab getTabNfeCobranca() {
        return tabNfeCobranca;
    }

    public void setTabNfeCobranca(Tab tabNfeCobranca) {
        this.tabNfeCobranca = tabNfeCobranca;
    }

    public ComboBox<NfeCobrancaDuplicataNumero> getCboNfeCobrancaDuplicataNumeros() {
        return cboNfeCobrancaDuplicataNumeros;
    }

    public void setCboNfeCobrancaDuplicataNumeros(ComboBox<NfeCobrancaDuplicataNumero> cboNfeCobrancaDuplicataNumeros) {
        this.cboNfeCobrancaDuplicataNumeros = cboNfeCobrancaDuplicataNumeros;
    }

    public TextField getTxtNfeCobrancaDuplicataValor() {
        return txtNfeCobrancaDuplicataValor;
    }

    public void setTxtNfeCobrancaDuplicataValor(TextField txtNfeCobrancaDuplicataValor) {
        this.txtNfeCobrancaDuplicataValor = txtNfeCobrancaDuplicataValor;
    }

    public ComboBox<IndPag> getCboNfeCobrancaPagamentoIndicador() {
        return cboNfeCobrancaPagamentoIndicador;
    }

    public void setCboNfeCobrancaPagamentoIndicador(ComboBox<IndPag> cboNfeCobrancaPagamentoIndicador) {
        this.cboNfeCobrancaPagamentoIndicador = cboNfeCobrancaPagamentoIndicador;
    }

    public ComboBox<NfeCobrancaDuplicataPagamentoMeio> getCboNfeCobrancaPagamentoMeio() {
        return cboNfeCobrancaPagamentoMeio;
    }

    public void setCboNfeCobrancaPagamentoMeio(ComboBox<NfeCobrancaDuplicataPagamentoMeio> cboNfeCobrancaPagamentoMeio) {
        this.cboNfeCobrancaPagamentoMeio = cboNfeCobrancaPagamentoMeio;
    }

    public TextField getTxtNfeCobrancaPagamentoValor() {
        return txtNfeCobrancaPagamentoValor;
    }

    public void setTxtNfeCobrancaPagamentoValor(TextField txtNfeCobrancaPagamentoValor) {
        this.txtNfeCobrancaPagamentoValor = txtNfeCobrancaPagamentoValor;
    }

    public Tab getTabNfeInformacoes() {
        return tabNfeInformacoes;
    }

    public void setTabNfeInformacoes(Tab tabNfeInformacoes) {
        this.tabNfeInformacoes = tabNfeInformacoes;
    }

    public TextArea getTxaNfeInformacoesAdicionais() {
        return txaNfeInformacoesAdicionais;
    }

    public void setTxaNfeInformacoesAdicionais(TextArea txaNfeInformacoesAdicionais) {
        this.txaNfeInformacoesAdicionais = txaNfeInformacoesAdicionais;
    }

    public TitledPane getTpnItensTotaisDetalhe() {
        return tpnItensTotaisDetalhe;
    }

    public void setTpnItensTotaisDetalhe(TitledPane tpnItensTotaisDetalhe) {
        this.tpnItensTotaisDetalhe = tpnItensTotaisDetalhe;
    }

    public TextField getTxtPesquisa() {
        return txtPesquisa;
    }

    public void setTxtPesquisa(TextField txtPesquisa) {
        this.txtPesquisa = txtPesquisa;
    }

    public Label getLblStatus() {
        return lblStatus;
    }

    public void setLblStatus(Label lblStatus) {
        this.lblStatus = lblStatus;
    }

    public Label getLblRegistrosLocalizados() {
        return lblRegistrosLocalizados;
    }

    public void setLblRegistrosLocalizados(Label lblRegistrosLocalizados) {
        this.lblRegistrosLocalizados = lblRegistrosLocalizados;
    }

    public TreeTableView<Object> getTtvProdutoEstoque() {
        return ttvProdutoEstoque;
    }

    public void setTtvProdutoEstoque(TreeTableView<Object> ttvProdutoEstoque) {
        this.ttvProdutoEstoque = ttvProdutoEstoque;
    }

    public VBox getvBoxItensNfeDetalhe() {
        return vBoxItensNfeDetalhe;
    }

    public void setvBoxItensNfeDetalhe(VBox vBoxItensNfeDetalhe) {
        this.vBoxItensNfeDetalhe = vBoxItensNfeDetalhe;
    }

    public TableView<SaidaProdutoProduto> getTvItensNfe() {
        return tvItensNfe;
    }

    public void setTvItensNfe(TableView<SaidaProdutoProduto> tvItensNfe) {
        this.tvItensNfe = tvItensNfe;
    }

    public VBox getvBoxTotalQtdItem() {
        return vBoxTotalQtdItem;
    }

    public void setvBoxTotalQtdItem(VBox vBoxTotalQtdItem) {
        this.vBoxTotalQtdItem = vBoxTotalQtdItem;
    }

    public Label getLblQtdItem() {
        return lblQtdItem;
    }

    public void setLblQtdItem(Label lblQtdItem) {
        this.lblQtdItem = lblQtdItem;
    }

    public VBox getvBoxTotalQtdTotal() {
        return vBoxTotalQtdTotal;
    }

    public void setvBoxTotalQtdTotal(VBox vBoxTotalQtdTotal) {
        this.vBoxTotalQtdTotal = vBoxTotalQtdTotal;
    }

    public Label getLblQtdTotal() {
        return lblQtdTotal;
    }

    public void setLblQtdTotal(Label lblQtdTotal) {
        this.lblQtdTotal = lblQtdTotal;
    }

    public VBox getvBoxTotalQtdVolume() {
        return vBoxTotalQtdVolume;
    }

    public void setvBoxTotalQtdVolume(VBox vBoxTotalQtdVolume) {
        this.vBoxTotalQtdVolume = vBoxTotalQtdVolume;
    }

    public Label getLblQtdVolume() {
        return lblQtdVolume;
    }

    public void setLblQtdVolume(Label lblQtdVolume) {
        this.lblQtdVolume = lblQtdVolume;
    }

    public VBox getvBoxTotalBruto() {
        return vBoxTotalBruto;
    }

    public void setvBoxTotalBruto(VBox vBoxTotalBruto) {
        this.vBoxTotalBruto = vBoxTotalBruto;
    }

    public Label getLblTotalBruto() {
        return lblTotalBruto;
    }

    public void setLblTotalBruto(Label lblTotalBruto) {
        this.lblTotalBruto = lblTotalBruto;
    }

    public VBox getvBoxTotalDesconto() {
        return vBoxTotalDesconto;
    }

    public void setvBoxTotalDesconto(VBox vBoxTotalDesconto) {
        this.vBoxTotalDesconto = vBoxTotalDesconto;
    }

    public Label getLblTotalDesconto() {
        return lblTotalDesconto;
    }

    public void setLblTotalDesconto(Label lblTotalDesconto) {
        this.lblTotalDesconto = lblTotalDesconto;
    }

    public VBox getvBoxTotalLiquido() {
        return vBoxTotalLiquido;
    }

    public void setvBoxTotalLiquido(VBox vBoxTotalLiquido) {
        this.vBoxTotalLiquido = vBoxTotalLiquido;
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

    public StatusSaidaProduto getStatusBar() {
        return statusBar.get();
    }

    public ObjectProperty<StatusSaidaProduto> statusBarProperty() {
        return statusBar;
    }

    public void setStatusBar(StatusSaidaProduto statusBar) {
        this.statusBar.set(statusBar);
    }

    public EventHandler getEventHandlerSaidaProduto() {
        return eventHandlerSaidaProduto;
    }

    public void setEventHandlerSaidaProduto(EventHandler eventHandlerSaidaProduto) {
        this.eventHandlerSaidaProduto = eventHandlerSaidaProduto;
    }

    public TmodelProduto getTmodelProduto() {
        return tmodelProduto;
    }

    public void setTmodelProduto(TmodelProduto tmodelProduto) {
        this.tmodelProduto = tmodelProduto;
    }

    public FilteredList<Produto> getProdutoFilteredList() {
        return produtoFilteredList;
    }

    public void setProdutoFilteredList(FilteredList<Produto> produtoFilteredList) {
        this.produtoFilteredList = produtoFilteredList;
    }

    public TmodelSaidaProduto getTmodelSaidaProduto() {
        return tmodelSaidaProduto;
    }

    public void setTmodelSaidaProduto(TmodelSaidaProduto tmodelSaidaProduto) {
        this.tmodelSaidaProduto = tmodelSaidaProduto;
    }

    public SaidaProduto getSaidaProduto() {
        return saidaProduto.get();
    }

    public ObjectProperty<SaidaProduto> saidaProdutoProperty() {
        return saidaProduto;
    }

    public void setSaidaProduto(SaidaProduto saidaProduto) {
        this.saidaProduto.set(saidaProduto);
    }

    public SaidaProdutoDAO getSaidaProdutoDAO() {
        return saidaProdutoDAO;
    }

    public void setSaidaProdutoDAO(SaidaProdutoDAO saidaProdutoDAO) {
        this.saidaProdutoDAO = saidaProdutoDAO;
    }

    public ObservableList<SaidaProdutoProduto> getSaidaProdutoProdutoObservableList() {
        return saidaProdutoProdutoObservableList;
    }

    public void setSaidaProdutoProdutoObservableList(ObservableList<SaidaProdutoProduto> saidaProdutoProdutoObservableList) {
        this.saidaProdutoProdutoObservableList = saidaProdutoProdutoObservableList;
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

    public List<FichaKardex> getFichaKardexList() {
        return fichaKardexList;
    }

    public void setFichaKardexList(List<FichaKardex> fichaKardexList) {
        this.fichaKardexList = fichaKardexList;
    }

    public int getPrazo() {
        return prazo.get();
    }

    public IntegerProperty prazoProperty() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo.set(prazo);
    }

    public String getXmlNFe() {
        return xmlNFe.get();
    }

    public StringProperty xmlNFeProperty() {
        return xmlNFe;
    }

    public void setXmlNFe(String xmlNFe) {
        this.xmlNFe.set(xmlNFe);
    }

    public String getXmlNFeAssinado() {
        return xmlNFeAssinado.get();
    }

    public StringProperty xmlNFeAssinadoProperty() {
        return xmlNFeAssinado;
    }

    public void setXmlNFeAssinado(String xmlNFeAssinado) {
        this.xmlNFeAssinado.set(xmlNFeAssinado);
    }

    public String getXmlNFeAutorizacao() {
        return xmlNFeAutorizacao.get();
    }

    public StringProperty xmlNFeAutorizacaoProperty() {
        return xmlNFeAutorizacao;
    }

    public void setXmlNFeAutorizacao(String xmlNFeAutorizacao) {
        this.xmlNFeAutorizacao.set(xmlNFeAutorizacao);
    }

    public String getXmlNFeRetAutorizacao() {
        return xmlNFeRetAutorizacao.get();
    }

    public StringProperty xmlNFeRetAutorizacaoProperty() {
        return xmlNFeRetAutorizacao;
    }

    public void setXmlNFeRetAutorizacao(String xmlNFeRetAutorizacao) {
        this.xmlNFeRetAutorizacao.set(xmlNFeRetAutorizacao);
    }

    public String getXmlNFeProc() {
        return xmlNFeProc.get();
    }

    public StringProperty xmlNFeProcProperty() {
        return xmlNFeProc;
    }

    public void setXmlNFeProc(String xmlNFeProc) {
        this.xmlNFeProc.set(xmlNFeProc);
    }

    public int getNfeLastNumber() {
        return nfeLastNumber.get();
    }

    public IntegerProperty nfeLastNumberProperty() {
        return nfeLastNumber;
    }

    public void setNfeLastNumber(int nfeLastNumber) {
        this.nfeLastNumber.set(nfeLastNumber);
    }

    public SaidaProdutoNfe getSaidaProdutoNfe() {
        return saidaProdutoNfe.get();
    }

    public ObjectProperty<SaidaProdutoNfe> saidaProdutoNfeProperty() {
        return saidaProdutoNfe;
    }

    public void setSaidaProdutoNfe(SaidaProdutoNfe saidaProdutoNfe) {
        this.saidaProdutoNfe.set(saidaProdutoNfe);
    }

    public String getInformacoesAdicionasNFe() {
        return informacoesAdicionasNFe.get();
    }

    public StringProperty informacoesAdicionasNFeProperty() {
        return informacoesAdicionasNFe;
    }

    public void setInformacoesAdicionasNFe(String informacoesAdicionasNFe) {
        this.informacoesAdicionasNFe.set(informacoesAdicionasNFe);
    }

    public NFev400 getnFev400() {
        return nFev400.get();
    }

    public ObjectProperty<NFev400> nFev400Property() {
        return nFev400;
    }

    public void setnFev400(NFev400 nFev400) {
        this.nFev400.set(nFev400);
    }

    public TEnviNFe gettEnviNFe() {
        return tEnviNFe.get();
    }

    public ObjectProperty<TEnviNFe> tEnviNFeProperty() {
        return tEnviNFe;
    }

    public void settEnviNFe(TEnviNFe tEnviNFe) {
        this.tEnviNFe.set(tEnviNFe);
    }

    public ObservableList<ContasAReceber> getContasAReceberObservableList() {
        return contasAReceberObservableList;
    }

    public void setContasAReceberObservableList(ObservableList<ContasAReceber> contasAReceberObservableList) {
        this.contasAReceberObservableList = contasAReceberObservableList;
    }

    public ContasAReceberDAO getContasAReceberDAO() {
        return contasAReceberDAO;
    }

    public void setContasAReceberDAO(ContasAReceberDAO contasAReceberDAO) {
        this.contasAReceberDAO = contasAReceberDAO;
    }

    public CheckBox getChkPrintLoteProdutos() {
        return chkPrintLoteProdutos;
    }

    public void setChkPrintLoteProdutos(CheckBox chkPrintLoteProdutos) {
        this.chkPrintLoteProdutos = chkPrintLoteProdutos;
    }

    public ServiceAutoCompleteComboBox getComboEmpresa() {
        return comboEmpresa;
    }

    public void setComboEmpresa(ServiceAutoCompleteComboBox comboEmpresa) {
        this.comboEmpresa = comboEmpresa;
    }

    public ObservableList<Produto> getProdutoObservableList() {
        return produtoObservableList;
    }

    public void setProdutoObservableList(ObservableList<Produto> produtoObservableList) {
        this.produtoObservableList = produtoObservableList;
    }


    /**
     * END Getters e Setters
     */

}
