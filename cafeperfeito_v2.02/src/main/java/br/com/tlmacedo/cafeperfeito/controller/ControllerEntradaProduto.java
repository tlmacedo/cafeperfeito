package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.*;
import br.com.tlmacedo.cafeperfeito.model.enums.*;
import br.com.tlmacedo.cafeperfeito.model.tm.TmodelEntradaProduto;
import br.com.tlmacedo.cafeperfeito.model.tm.TmodelProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.service.alert.Alert_Ok;
import br.com.tlmacedo.cafeperfeito.service.autoComplete.ServiceAutoCompleteComboBox;
import br.com.tlmacedo.cafeperfeito.service.format.ServiceFormatDataPicker;
import br.com.tlmacedo.cafeperfeito.view.ViewEntradaProduto;
import br.com.tlmacedo.nfe.service.ServiceUtilXml;
import br.inf.portalfiscal.xsd.cte.procCTe.CteProc;
import br.inf.portalfiscal.xsd.cte.procCTe.TCTe;
import br.inf.portalfiscal.xsd.nfe.procNFe.TNfeProc;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_NFE_TO_LOCAL_DATE;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-02-22
 * Time: 12:33
 */

public class ControllerEntradaProduto implements Initializable, ModeloCafePerfeito {

    public AnchorPane painelViewEntradaProduto;

    public TitledPane tpnEntradaNfe;
    public TitledPane tpnNfeDetalhe;
    public ComboBox<Empresa> cboNfeLojaDestino;
    public TextField txtNfeChave;
    public TextField txtNfeNumero;
    public TextField txtNfeSerie;
    public ComboBox<NfeCteModelo> cboNfeModelo;
    public ComboBox<Empresa> cboNfeFornecedor;
    public DatePicker dtpNfeEmissao;
    public DatePicker dtpNfeEntrada;

    public TitledPane tpnNfeDetalheFiscal;
    public TextField txtNfeFiscalControle;
    public TextField txtNfeFiscalOrigem;
    public ComboBox<FiscalTributosSefazAm> cboNfeFiscalTributo;
    public TextField txtNfeFiscalVlrNFe;
    public TextField txtNfeFiscalVlrTributo;
    public TextField txtNfeFiscalVlrMulta;
    public TextField txtNfeFiscalVlrJuros;
    public TextField txtNfeFiscalVlrTaxa;
    public Label lblNfeFiscalVlrTotal;
    public Label lblNfeFiscalVlrPercentual;

    public TitledPane tpnCteDetalhe;
    public TextField txtCteChave;
    public ComboBox<CteTomadorServico> cboCteTomadorServico;
    public TextField txtCteNumero;
    public TextField txtCteSerie;
    public ComboBox<NfeCteModelo> cboCteModelo;
    public DatePicker dtpCteEmissao;
    public ComboBox<Empresa> cboCteTransportadora;
    public ComboBox<FiscalFreteSituacaoTributaria> cboCteSistuacaoTributaria;
    public TextField txtCteVlrCte;
    public TextField txtCteQtdVolume;
    public TextField txtCtePesoBruto;
    public TextField txtCteVlrBruto;
    public TextField txtCteVlrTaxa;
    public TextField txtCteVlrColeta;
    public TextField txtCteVlrImposto;
    public Label lblCteVlrLiquido;

    public TitledPane tpnCteDetalheFiscal;
    public TextField txtCteFiscalControle;
    public TextField txtCteFiscalOrigem;
    public ComboBox<FiscalTributosSefazAm> cboCteFiscalTributo;
    public Label lblCteFiscalVlrCte;
    public TextField txtCteFiscalVlrTributo;
    public TextField txtCteFiscalVlrMulta;
    public TextField txtCteFiscalVlrJuros;
    public TextField txtCteFiscalVlrTaxa;
    public Label lblCteFiscalVlrTotal;
    public Label lblCteFiscalVlrPercentual;

    public TitledPane tpnItensTotaisDetalhe;
    public TextField txtPesquisaProduto;
    public Label lblStatus;
    public Label lblRegistrosLocalizados;
    public TreeTableView<Object> ttvProdutoEstoque;
    public VBox vBoxItensNfeDetalhe;
    public TableView<EntradaProdutoProduto> tvItensNfe;
    public VBox vBoxTotalQtdItem;
    public Label lblQtdItem;
    public VBox vBoxTotalQtdTotal;
    public Label lblQtdTotal;
    public VBox vBoxTotalQtdVolume;
    public Label lblQtdVolume;
    public VBox vBoxTotalBruto;
    public Label lblTotalBruto;
    public VBox vBoxTotalImposto;
    public Label lblTotalImposto;
    public VBox vBoxTotalFrete;
    public Label lblTotalFrete;
    public VBox vBoxTotalDesconto;
    public Label lblTotalDesconto;
    public VBox vBoxTotalLiquido;
    public Label lblTotalLiquido;

    private boolean tabCarregada = false;
    private List<EnumsTasks> enumsTasksList = new ArrayList<>();

    private String nomeTab = ViewEntradaProduto.getTitulo();
    private String nomeController = "entradaProduto";
    private ObjectProperty<StatusBarEntradaProduto> statusBar = new SimpleObjectProperty<>(StatusBarEntradaProduto.DIGITACAO);
    private EventHandler eventHandlerEntradaProduto;

    private TmodelProduto tmodelProduto;
    private ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(new ProdutoDAO().getAll(Produto.class, null, "descricao"));
    private FilteredList<Produto> produtoFilteredList;

    private TmodelEntradaProduto tmodelEntradaProduto;
    private EntradaProduto entradaProduto;
    private EntradaProdutoDAO entradaProdutoDAO;
    private ObservableList<EntradaProdutoProduto> entradaProdutoProdutoObservableList = FXCollections.observableArrayList();
    private List<FichaKardex> fichaKardexList;

    private StringProperty nfeFiscalVlrTotal = new SimpleStringProperty();

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
            Platform.runLater(() -> limpaCampos(getPainelViewEntradaProduto()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fieldsFormat() throws Exception {
        ServiceCampoPersonalizado.fieldTextFormat(getPainelViewEntradaProduto());
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
                    .removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerEntradaProduto());
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

        setTabCarregada(new ServiceSegundoPlano().executaListaTarefas(newTaskEntradaProduto(), String.format("Abrindo %s!", getNomeTab())));
    }

    @Override
    public void fatorarObjetos() {
        getTpnNfeDetalheFiscal().setExpanded(false);
        getTpnCteDetalheFiscal().setExpanded(false);
        getTpnCteDetalhe().setExpanded(false);
    }

    @Override
    public void escutarTecla() {
        escutaTitledTab();
        statusBarProperty().addListener((ov, o, n) -> {
            if (n == null)
                statusBarProperty().setValue(StatusBarEntradaProduto.DIGITACAO);
            showStatusBar();
        });

        getTtvProdutoEstoque().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            try {
                if (event.getCode() != KeyCode.ENTER
                        || getTtvProdutoEstoque().getSelectionModel().getSelectedItem() == null)
                    return;
                getEntradaProdutoProdutoObservableList().add(
                        new EntradaProdutoProduto(getProdutoSelecionado(), TipoCodigoCFOP.COMERCIALIZACAO)
                );
                ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F8));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setEventHandlerEntradaProduto(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                try {
                    if (ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getSelectionModel().getSelectedIndex() < 0)
                        return;
                    if (!ControllerPrincipal.getCtrlPrincipal().getTabPaneViewPrincipal().getSelectionModel().getSelectedItem().getText().equals(getNomeTab()))
                        return;
                    if (!ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().teclaValida(keyEvent)) return;
                    switch (keyEvent.getCode()) {
                        case F1:
                            limpaCampos(getPainelViewEntradaProduto());
                            break;
                        case F2:
                            if (getEntradaProdutoProdutoObservableList().size() > 0
                                    && validarEntrada()) {
                                getEnumsTasksList().clear();
                                getEnumsTasksList().add(EnumsTasks.SALVAR_ENT_SAIDA);
                                if (new ServiceSegundoPlano().executaListaTarefas(newTaskEntradaProduto(), String.format("Salvando %s!", getNomeTab()))) {
                                    limpaCampos(getPainelViewEntradaProduto());
                                }
                            } else {
                                new Alert_Ok("Entrada invalida",
                                        "Verifique a entrada de produtos pois está invalida",
                                        null);
                            }
                            break;
                        case F5:
                            getTxtNfeChave().requestFocus();
                            break;
                        case F6:
                            if (!getTpnCteDetalhe().isExpanded()) return;
                            getTxtCteChave().requestFocus();
                            break;
                        case F7:
                            getTxtPesquisaProduto().requestFocus();
                            break;
                        case F8:
                            getTvItensNfe().requestFocus();
                            getTvItensNfe().getSelectionModel().select(getEntradaProdutoProdutoObservableList().size() - 1,
                                    getTmodelEntradaProduto().getColProdLote());
                            break;
                        case F9:
                            getTpnNfeDetalheFiscal().setExpanded(!getTpnNfeDetalheFiscal().isExpanded());
                            break;
                        case F10:
                            getTpnCteDetalhe().setExpanded(!getTpnCteDetalhe().isExpanded());
                            break;
                        case F11:
                            if (getTpnCteDetalhe().isExpanded())
                                getTpnCteDetalheFiscal().setExpanded(!getTpnCteDetalheFiscal().isExpanded());
                            break;
                        case F12:
                            fechar();
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
                ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerEntradaProduto());
                showStatusBar();
            } else {
                ControllerPrincipal.getCtrlPrincipal().getPainelViewPrincipal().removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerEntradaProduto());
            }
        });

        new ServiceAutoCompleteComboBox(Empresa.class, getCboNfeFornecedor());

        new ServiceAutoCompleteComboBox(FiscalTributosSefazAm.class, getCboNfeFiscalTributo());

        new ServiceAutoCompleteComboBox(Empresa.class, getCboCteTransportadora());

        new ServiceAutoCompleteComboBox(FiscalFreteSituacaoTributaria.class, getCboCteSistuacaoTributaria());

        new ServiceAutoCompleteComboBox(FiscalTributosSefazAm.class, getCboCteFiscalTributo());

        getTxtPesquisaProduto().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            getTtvProdutoEstoque().requestFocus();
            getTtvProdutoEstoque().getSelectionModel().selectFirst();
        });

        getTxtNfeChave().setOnDragOver(event -> {
            if (getTxtNfeChave().isDisable()) return;
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasFiles())
                if (Pattern.compile(".xml").matcher(dragboard.getFiles().get(0).toPath().toString()).find())
                    event.acceptTransferModes(TransferMode.ANY);
        });

        getTxtNfeChave().setOnDragDropped(event -> {
            if (getTxtNfeChave().isDisable()) return;
            Dragboard dragboard = event.getDragboard();
            addXmlNfe(dragboard.getFiles().get(0));
        });

        getTxtCteChave().setOnDragOver(event -> {
            if (getTxtCteChave().isDisable()) return;
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasFiles())
                if (Pattern.compile(".xml").matcher(dragboard.getFiles().get(0).toPath().toString()).find())
                    event.acceptTransferModes(TransferMode.ANY);
        });

        getTxtCteChave().setOnDragDropped(event -> {
            if (getTxtCteChave().isDisable()) return;
            Dragboard dragboard = event.getDragboard();
            addXmlCte(dragboard.getFiles().get(0));
        });

        getTpnNfeDetalhe().textProperty().bind(Bindings.createStringBinding(() -> {
                    return String.format("Detalhe da nf-e%s",
                            getTxtNfeNumero().getText().length() == 0
                                    ? ""
                                    : String.format(": [%s]", getTxtNfeNumero().getText().trim()));
                }, getTxtNfeNumero().textProperty()
        ));

        getTpnCteDetalhe().textProperty().bind(Bindings.createStringBinding(() -> {
                    if (getTpnCteDetalhe().isExpanded()) {
                        return String.format("Detalhe frete do ct-e%s",
                                getTxtCteNumero().getText().length() == 0
                                        ? ""
                                        : String.format(": [%s]", getTxtCteNumero().getText().trim()));
                    } else {
                        return "Nf-e sem frete";
                    }
                }, getTxtCteNumero().textProperty()
        ));

        getLblNfeFiscalVlrTotal().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrTributo = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTributo().getText(), 2);
                    BigDecimal vlrMulta = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrMulta().getText(), 2);
                    BigDecimal vlrJuros = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrJuros().getText(), 2);
                    BigDecimal vlrTaxa = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTaxa().getText(), 2);

                    return ServiceMascara.getMoeda(vlrTributo.add(vlrMulta).add(vlrJuros).add(vlrTaxa), 2);
                }, getTxtNfeFiscalVlrTributo().textProperty(), getTxtNfeFiscalVlrMulta().textProperty(),
                getTxtNfeFiscalVlrJuros().textProperty(), getTxtNfeFiscalVlrTaxa().textProperty()
        ));

        getLblNfeFiscalVlrPercentual().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrNfe = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrNFe().getText(), 2);
                    BigDecimal vlrTotal = ServiceMascara.getBigDecimalFromTextField(getLblNfeFiscalVlrTotal().getText(), 2);
                    try {
                        return ServiceMascara.getMoeda(vlrTotal
                                .divide(vlrNfe, 4, RoundingMode.HALF_UP)
                                .multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP), 2);
                    } catch (ArithmeticException ae) {
                        return "0,00";
                    }
                }, getTxtNfeFiscalVlrNFe().textProperty(), getLblNfeFiscalVlrTotal().textProperty()
        ));

        getLblCteVlrLiquido().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrBruto = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrBruto().getText(), 2);
                    BigDecimal vlrTaxa = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrTaxa().getText(), 2);
                    BigDecimal vlrColeta = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrColeta().getText(), 2);
                    BigDecimal vlrImposto = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrImposto().getText(), 2);
                    return ServiceMascara.getMoeda(vlrBruto.add(vlrTaxa).add(vlrColeta).add(vlrImposto), 2);
                }, getTxtCteVlrBruto().textProperty(), getTxtCteVlrTaxa().textProperty(),
                getTxtCteVlrColeta().textProperty(), getTxtCteVlrImposto().textProperty()
        ));

        getLblCteFiscalVlrCte().textProperty().bind(getLblCteVlrLiquido().textProperty());

        getLblCteFiscalVlrTotal().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrTributo = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTributo().getText(), 2);
                    BigDecimal vlrMulta = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrMulta().getText(), 2);
                    BigDecimal vlrJuros = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrJuros().getText(), 2);
                    BigDecimal vlrTaxa = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTaxa().getText(), 2);

                    return ServiceMascara.getMoeda(vlrTributo.add(vlrMulta).add(vlrJuros).add(vlrTaxa), 2);
                }, getTxtCteFiscalVlrTributo().textProperty(), getTxtCteFiscalVlrMulta().textProperty(),
                getTxtCteFiscalVlrJuros().textProperty(), getTxtCteFiscalVlrTaxa().textProperty()
        ));

        getLblCteFiscalVlrPercentual().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrCte = ServiceMascara.getBigDecimalFromTextField(getLblCteFiscalVlrCte().getText(), 2);
                    BigDecimal vlrTotal = ServiceMascara.getBigDecimalFromTextField(getLblCteFiscalVlrTotal().getText(), 2);
                    try {
                        return ServiceMascara.getMoeda(vlrTotal
                                .divide(vlrCte, 5, RoundingMode.HALF_UP)
                                .multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP), 2);
                    } catch (ArithmeticException ae) {
                        return "0,00";
                    }
                }, getLblCteFiscalVlrCte().textProperty(), getLblCteFiscalVlrTotal().textProperty()
        ));

        getLblQtdItem().textProperty().bind(getTmodelEntradaProduto().totalQtdItemProperty().asString());
        getLblQtdTotal().textProperty().bind(getTmodelEntradaProduto().totalQtdProdutoProperty().asString());
        getLblQtdVolume().textProperty().bind(getTmodelEntradaProduto().totalQtdVolumeProperty().asString());
        getLblTotalBruto().textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(getTmodelEntradaProduto().totalBrutoProperty().getValue(), 2),
                getTmodelEntradaProduto().totalBrutoProperty()
        ));
        getLblNfeFiscalVlrTotal().textProperty().addListener((ov, o, n) -> {
            getTmodelEntradaProduto().totalImpEntradaProperty().setValue(
                    ServiceMascara.getBigDecimalFromTextField(n, 2)
                            .add(ServiceMascara.getBigDecimalFromTextField(getLblCteFiscalVlrTotal().getText(), 2))
            );
        });
        getLblCteFiscalVlrTotal().textProperty().addListener((ov, o, n) -> {
            getTmodelEntradaProduto().totalImpEntradaProperty().setValue(
                    ServiceMascara.getBigDecimalFromTextField(n, 2)
                            .add(ServiceMascara.getBigDecimalFromTextField(getLblNfeFiscalVlrTotal().getText(), 2))
            );
        });
        getLblTotalImposto().textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(getTmodelEntradaProduto().totalImpostoProperty().getValue(), 2),
                getTmodelEntradaProduto().totalImpostoProperty()
        ));

        getLblCteVlrLiquido().textProperty().addListener((ov, o, n) -> {
            getTmodelEntradaProduto().setTotalFrete(ServiceMascara.getBigDecimalFromTextField(n, 2));
        });

        getLblTotalFrete().textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(getTmodelEntradaProduto().totalFreteProperty().getValue(), 2),
                getTmodelEntradaProduto().totalFreteProperty()
        ));

        getLblTotalDesconto().textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(getTmodelEntradaProduto().totalDescontoProperty().getValue(), 2),
                getTmodelEntradaProduto().totalDescontoProperty()
        ));
        getLblTotalLiquido().textProperty().bind(Bindings.createStringBinding(() ->
                        ServiceMascara.getMoeda(getTmodelEntradaProduto().totalLiquidoProperty().getValue(), 2),
                getTmodelEntradaProduto().totalLiquidoProperty()
        ));

        getDtpNfeEmissao().focusedProperty().addListener((ov, o, n) -> {
            ServiceFormatDataPicker.formatDataPicker(getDtpNfeEmissao(), n);
        });

        getDtpNfeEntrada().focusedProperty().addListener((ov, o, n) -> {
            ServiceFormatDataPicker.formatDataPicker(getDtpNfeEntrada(), n);
        });

        getDtpCteEmissao().focusedProperty().addListener((ov, o, n) -> {
            ServiceFormatDataPicker.formatDataPicker(getDtpCteEmissao(), n);
        });

    }

    /**
     * Begin Tasks
     */

    private Task newTaskEntradaProduto() {
        try {
            int qtdTasks = getEnumsTasksList().size();
            final int[] cont = {0};
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
                                setTmodelProduto(new TmodelProduto(TModelTipo.PROD_COMPRA));
                                getTmodelProduto().tabela_criar();

                                setTmodelEntradaProduto(new TmodelEntradaProduto());
                                getTmodelEntradaProduto().criaTabela();
                                break;

                            case TABELA_VINCULAR:
                                getTmodelProduto().setLblRegistrosLocalizados(getLblRegistrosLocalizados());
                                getTmodelProduto().setTtvProdutoEstoque(getTtvProdutoEstoque());
                                getTmodelProduto().setTxtPesquisa(getTxtPesquisaProduto());
                                getTmodelProduto().setProdutoFilteredList(new FilteredList<>(getProdutoObservableList()));
                                setProdutoFilteredList(getTmodelProduto().getProdutoFilteredList());
                                getTmodelProduto().escutaLista();

                                getTmodelEntradaProduto().setTvItensNfe(getTvItensNfe());
                                getTmodelEntradaProduto().setTxtPesquisaProduto(getTxtPesquisaProduto());
                                getTmodelEntradaProduto().setEntradaProdutoProdutoObservableList(getEntradaProdutoProdutoObservableList());
                                getTmodelEntradaProduto().setFichaKardexList(getFichaKardexList());
                                getTmodelEntradaProduto().escutaLista();
                                break;

                            case COMBOS_PREENCHER:
                                loadListaEmpresas();

                                getCboNfeModelo().setItems(
                                        Arrays.stream(NfeCteModelo.values()).collect(Collectors.toCollection(FXCollections::observableArrayList))
                                );

                                getCboCteModelo().setItems(
                                        Arrays.stream(NfeCteModelo.values()).collect(Collectors.toCollection(FXCollections::observableArrayList))
                                );

                                getCboCteSistuacaoTributaria().setItems(new FiscalFreteSituacaoTributariaDAO()
                                        .getAll(FiscalFreteSituacaoTributaria.class, null, "id").stream()
                                        .collect(Collectors.toCollection(FXCollections::observableArrayList))
                                );

                                ObservableList<FiscalTributosSefazAm> tributosSefazAmObservableList = new FiscalTributosSefazAmDAO()
                                        .getAll(FiscalTributosSefazAm.class, null, "id").stream()
                                        .collect(Collectors.toCollection(FXCollections::observableArrayList));

                                getCboNfeFiscalTributo().setItems(tributosSefazAmObservableList);
                                getCboCteFiscalTributo().setItems(tributosSefazAmObservableList);

                                getCboCteTomadorServico().setItems(
                                        Arrays.stream(CteTomadorServico.values()).collect(Collectors.toCollection(FXCollections::observableArrayList))
                                );
                                break;

                            case TABELA_PREENCHER:
                                getTmodelProduto().tabela_preencher();

                                getTmodelEntradaProduto().preencheTabela();
                                break;

                            case SALVAR_ENT_SAIDA:
                                if (guardarEntradaProduto()) {
                                    if (salvarEntradaProduto()) {
                                        getProdutoObservableList().setAll(new ProdutoDAO().getAll(Produto.class, null, "descricao"));
                                        getTmodelProduto().atualizarProdutos();
                                    } else {
                                        Thread.currentThread().interrupt();
                                    }
                                } else {
                                    Thread.currentThread().interrupt();
                                }
                                break;
//                            case NFE_GERAR:
////                                gerarXmlNFe();
//                                break;
//                            case NFE_ASSINAR:
////                                if (xmlNFeProperty().getValue() == null)
////                                    Thread.currentThread().interrupt();
////                                assinarXmlNFe();
//                                break;
//                            case NFE_TRANSMITIR:
////                                if (xmlNFeAssinadoProperty().getValue() == null)
////                                    Thread.currentThread().interrupt();
////                                transmitirXmlNFe();
//                                break;
//                            case NFE_RETORNO:
////                                if (xmlNFeAutorizacaoProperty().getValue() == null)
////                                    Thread.currentThread().interrupt();
////                                retornoXmlNFe();
////                                if (xmlNFeRetAutorizacaoProperty().getValue() == null)
////                                    Thread.currentThread().interrupt();
////                                retornoProcNFe();
//                                break;
                        }
                    }
                    updateMessage("tarefa concluída!!!");
                    updateProgress(qtdTasks, qtdTasks);
                    return null;
                }
            };
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
//        return null;
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
        if (anchorPane.equals(getPainelViewEntradaProduto())) {
            getCboNfeLojaDestino().getSelectionModel().select(0);
            getCboNfeLojaDestino().requestFocus();
        }
    }

    private void escutaTitledTab() {
        getTpnNfeDetalheFiscal().expandedProperty().addListener((ov, o, n) -> {
            int diff = (getTpnNfeDetalheFiscal().getHeight() == 0) ? 44 : (int) getTpnNfeDetalheFiscal().getHeight() - 23;
            if (!n) diff = (diff * -1);
            setHeightTpnNfeDetalhe(diff);
            getTpnNfeDetalheFiscal().setText(n ? "Informações de imposto" : "Nf-e sem imposto");
        });

        getTpnCteDetalheFiscal().expandedProperty().addListener((ov, o, n) -> {
            int diff = (getTpnCteDetalheFiscal().getHeight() == 0) ? 44 : (int) getTpnCteDetalheFiscal().getHeight() - 23;
            if (!n) diff = (diff * -1);
            setHeightTpnCteDetalhe(diff);
            getTpnCteDetalheFiscal().setText(n ? "Informações de imposto no frete" : "Frete sem imposto");
        });

        getTpnCteDetalhe().expandedProperty().addListener((ov, o, n) -> {
            int diff = (getTpnCteDetalhe().getHeight() == 0) ? 111 : (int) getTpnCteDetalhe().getHeight() - 23;
            if (!n) diff = (diff * -1);
            setHeightTpnEntradaNfe(diff);
        });
    }

    private void setHeightTpnNfeDetalhe(int diff) {
        getTpnNfeDetalhe().setPrefHeight(getTpnNfeDetalhe().getPrefHeight() + diff);
        setLayoutTpnCteDetalhe(diff);
    }

    private void setHeightTpnCteDetalhe(int diff) {
        getTpnCteDetalhe().setPrefHeight(getTpnCteDetalhe().getPrefHeight() + diff);
        setHeightTpnEntradaNfe(diff);
    }

    private void setLayoutTpnCteDetalhe(int diff) {
        getTpnCteDetalhe().setLayoutY(getTpnCteDetalhe().getLayoutY() + diff);
        setHeightTpnEntradaNfe(diff);
    }

    private void setHeightTpnEntradaNfe(int diff) {
        getTpnEntradaNfe().setPrefHeight(getTpnEntradaNfe().getPrefHeight() + diff);
        ajustaTpnItensNota(diff);
    }

    private void ajustaTpnItensNota(int diff) {
        getTpnItensTotaisDetalhe().setLayoutY(getTpnItensTotaisDetalhe().getLayoutY() + diff);
        getTpnItensTotaisDetalhe().setPrefHeight(getTpnItensTotaisDetalhe().getPrefHeight() + (diff * -1));
        getvBoxItensNfeDetalhe().setPrefHeight(getvBoxItensNfeDetalhe().getPrefHeight() + (diff * -1));
    }

    private void showStatusBar() {
        try {
            if (getEntradaProdutoProdutoObservableList().size() <= 0)
                ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(statusBarProperty().getValue().getDescricao().replace("  [F2-Finalizar entrada]", ""));
            else
                ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(statusBarProperty().getValue().getDescricao());
        } catch (Exception ex) {
            ControllerPrincipal.getCtrlPrincipal().getServiceStatusBar().atualizaStatusBar(statusBarProperty().getValue().getDescricao());
        }
    }

    private void loadListaEmpresas() {
        ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList(
                new EmpresaDAO().getAll(Empresa.class, null, "razao, fantasia"));

        getCboNfeLojaDestino().setItems(
                empresaObservableList.stream()
                        .filter(Empresa::isLoja)
                        .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );

        getCboNfeFornecedor().setItems(
                empresaObservableList.stream()
                        .filter(Empresa::isFornecedor)
                        .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );

        getCboCteTransportadora().setItems(
                empresaObservableList.stream()
                        .filter(Empresa::isTransportadora)
                        .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
    }

    private void addXmlNfe(File file) {
        TNfeProc nfeProc = null;
        try {
            nfeProc = ServiceUtilXml.xmlToObject(ServiceUtilXml.FileXml4String(new FileReader(file)), TNfeProc.class);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if (nfeProc.getNFe() == null) return;
        getTxtNfeChave().setText(nfeProc.getNFe().getInfNFe().getId().replaceAll("\\D", ""));
        getTxtNfeNumero().setText(nfeProc.getNFe().getInfNFe().getIde().getNNF());
        getTxtNfeSerie().setText(nfeProc.getNFe().getInfNFe().getIde().getSerie());

        getTxtNfeFiscalVlrNFe().setText(nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF());

        TNfeProc finalNfeProc = nfeProc;
        getCboNfeModelo().getSelectionModel().select(
                getCboNfeModelo().getItems().stream()
                        .filter(modeloNfeCte -> modeloNfeCte.getDescricao().equals(finalNfeProc.getNFe().getInfNFe().getIde().getMod()))
                        .findFirst().orElse(null)
        );
        getCboNfeLojaDestino().getSelectionModel().select(
                getCboNfeLojaDestino().getItems().stream()
                        .filter(loja -> loja.getCnpj().equals(finalNfeProc.getNFe().getInfNFe().getDest().getCNPJ()))
                        .findFirst().orElse(null)
        );
        getCboNfeFornecedor().getSelectionModel().select(
                getCboNfeFornecedor().getItems().stream()
                        .filter(fornecedor -> fornecedor.getCnpj().equals(finalNfeProc.getNFe().getInfNFe().getEmit().getCNPJ()))
                        .findFirst().orElse(null)
        );
        getDtpNfeEmissao().setValue(LocalDate.parse(nfeProc.getNFe().getInfNFe().getIde().getDhEmi(), DTF_NFE_TO_LOCAL_DATE));
        getDtpNfeEntrada().setValue(LocalDate.now());
    }

    private void addXmlCte(File file) {
        CteProc cteProc = null;
        try {
            cteProc = ServiceUtilXml.xmlToObject(ServiceUtilXml.FileXml4String(new FileReader(file)), CteProc.class);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if (cteProc.getCTe() == null) return;

        getTxtCteChave().setText(cteProc.getCTe().getInfCte().getId().replaceAll("\\D", ""));
        getTxtCteNumero().setText(cteProc.getCTe().getInfCte().getIde().getNCT());
        getTxtCteSerie().setText(cteProc.getCTe().getInfCte().getIde().getSerie());

        getTxtCteVlrCte().setText(cteProc.getCTe().getInfCte().getVPrest().getVTPrest());
        getTxtCteVlrImposto().setText(cteProc.getCTe().getInfCte().getImp().getICMS().getICMS00().getVICMS());

        for (TCTe.InfCte.InfCTeNorm.InfCarga.InfQ infQ : cteProc.getCTe().getInfCte().getInfCTeNorm().getInfCarga().getInfQ())
            switch (infQ.getTpMed().toLowerCase()) {
                case "volume":
                case "volumes":
                    getTxtCteQtdVolume().setText(infQ.getQCarga());
                    break;
                case "peso bruto":
                    getTxtCtePesoBruto().setText(BigDecimal.valueOf(Double.parseDouble(infQ.getQCarga())).setScale(4).toString());
            }

        double tmpTaxas = 0.;
        for (TCTe.InfCte.VPrest.Comp comp : cteProc.getCTe().getInfCte().getVPrest().getComp())
            if (comp.getXNome().toLowerCase().contains("peso"))
                getTxtCteVlrBruto().setText(comp.getVComp());
            else if (comp.getXNome().toLowerCase().contains("coleta"))
                getTxtCteVlrColeta().setText(comp.getVComp());
            else
                tmpTaxas += Double.parseDouble(comp.getVComp());
        getTxtCteVlrTaxa().setText(BigDecimal.valueOf(tmpTaxas).setScale(2).toString());

        CteProc finalCteProc = cteProc;
        getCboCteTomadorServico().getSelectionModel().select(
                getCboCteTomadorServico().getItems().stream()
                        .filter(tomadorServico -> tomadorServico.getCod() == Integer.valueOf(finalCteProc.getCTe().getInfCte().getIde().getToma3().getToma()))
                        .findFirst().orElse(null)
        );
        getCboCteModelo().getSelectionModel().select(
                getCboCteModelo().getItems().stream()
                        .filter(modeloNfeCte -> modeloNfeCte.getDescricao().equals(finalCteProc.getCTe().getInfCte().getIde().getMod()))
                        .findFirst().orElse(null)
        );
        getCboCteTransportadora().getSelectionModel().select(
                getCboCteTransportadora().getItems().stream()
                        .filter(transportadora -> transportadora.getCnpj().equals(finalCteProc.getCTe().getInfCte().getEmit().getCNPJ()))
                        .findFirst().orElse(null)
        );
        getCboCteSistuacaoTributaria().getSelectionModel().select(
                getCboCteSistuacaoTributaria().getItems().stream()
                        .filter(situacaoTributaria -> situacaoTributaria.getId() == Integer.valueOf(finalCteProc.getCTe().getInfCte().getImp().getICMS().getICMS00().getCST()))
                        .findFirst().orElse(null)
        );
        getDtpCteEmissao().setValue(LocalDate.parse(cteProc.getCTe().getInfCte().getIde().getDhEmi(), DTF_NFE_TO_LOCAL_DATE));
        if (cteProc.getCTe().getInfCte().getInfCTeNorm().getInfDoc().getInfNFe() != null)
            for (TCTe.InfCte.InfCTeNorm.InfDoc.InfNFe infNFe : cteProc.getCTe().getInfCte().getInfCTeNorm().getInfDoc().getInfNFe()) {
                File fileTmp;
                if ((fileTmp = ServiceFileFinder.finder(file.getParent(), infNFe.getChave(), "xml")).exists())
                    addXmlNfe(fileTmp);
            }
    }

    private void guardarEntradaProdutoProduto() {
        BigDecimal kgPedido = getEntradaProdutoProdutoObservableList().stream()
                .map(entradaProdutoProduto -> entradaProdutoProduto.produtoProperty().getValue()
                        .pesoProperty().getValue().multiply(new BigDecimal(entradaProdutoProduto.qtdProperty().getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal freteKg = ServiceMascara.getBigDecimalFromTextField(getLblCteVlrLiquido().getText(), 2)
                .divide(kgPedido, 4, RoundingMode.HALF_UP);
        BigDecimal impSefazNfe = (ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrNFe().getText(), 2)
                .compareTo(ServiceMascara.getBigDecimalFromTextField(getLblTotalBruto().getText(), 2)) == 0)
                ? ServiceMascara.getBigDecimalFromTextField(getLblNfeFiscalVlrPercentual().getText(), 4)
                : (ServiceMascara.getBigDecimalFromTextField(getLblNfeFiscalVlrTotal().getText(), 4)
                .multiply(new BigDecimal("100.")))
                .divide(ServiceMascara.getBigDecimalFromTextField(getLblTotalBruto().getText(), 4),
                        4, RoundingMode.HALF_UP);
        BigDecimal impSefazFrete = ServiceMascara.getBigDecimalFromTextField(getLblCteFiscalVlrTotal().getText(), 2)
                .divide(kgPedido, 4, RoundingMode.HALF_UP);

        getEntradaProdutoProdutoObservableList().stream()
                .forEach(entradaProdutoProduto -> {
                    entradaProdutoProduto.entradaProdutoProperty().setValue(getEntradaProduto());
                    BigDecimal kgItem = entradaProdutoProduto.produtoProperty().getValue().pesoProperty().getValue()
                            .multiply(new BigDecimal(entradaProdutoProduto.qtdProperty().getValue()));
                    entradaProdutoProduto.vlrFreteProperty().setValue(
                            freteKg.multiply(kgItem)
                    );
                    entradaProdutoProduto.vlrImpostoProperty().setValue(
                            ((impSefazNfe.divide(new BigDecimal("100."), 4, RoundingMode.HALF_UP)).multiply(
                                    entradaProdutoProduto.vlrBrutoProperty().getValue()
                                            .subtract(entradaProdutoProduto.vlrDescontoProperty().getValue()))
                            )
                                    .add(impSefazFrete.multiply(kgItem))
                    );
                });
    }

    private boolean salvarFichaKardexList() {
        FichaKardexDAO fichaKardexDAO = new FichaKardexDAO();
        try {
            fichaKardexDAO.transactionBegin();
            for (FichaKardex ficha : getFichaKardexList())
                fichaKardexDAO.setTransactionPersist(ficha);
            fichaKardexDAO.transactionCommit();
        } catch (Exception ex) {
            fichaKardexDAO.transactionRollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * END voids
     */


    /**
     * Begin returns
     */

    private boolean validarEntrada() {
        return (validarNfeDetalhe() && validarCteDetalhe());
    }

    private boolean validarNfeDetalhe() {
        if (getCboNfeLojaDestino().getValue() == null) {
            getCboNfeLojaDestino().requestFocus();
            return false;
        }
        if (getTxtNfeChave().getText().length() < 44) {
            getTxtNfeChave().requestFocus();
            return false;
        }
        if (getTxtNfeNumero().getText().length() == 0) {
            getTxtNfeNumero().requestFocus();
            return false;
        }
        if (getTxtNfeSerie().getText().length() == 0) {
            getTxtNfeSerie().requestFocus();
            return false;
        }
        if (getCboNfeModelo().getValue() == null) {
            getCboNfeModelo().requestFocus();
            return false;
        }
        if (getCboNfeFornecedor().getValue() == null) {
            getCboNfeFornecedor().requestFocus();
            return false;
        }
        if (getDtpNfeEmissao().getValue().isAfter(LocalDate.now())) {
            getDtpNfeEmissao().requestFocus();
            return false;
        }
        if (getDtpNfeEntrada().getValue().isAfter(LocalDate.now())) {
            getDtpNfeEntrada().requestFocus();
            return false;
        }
        if (getDtpNfeEmissao().getValue().isAfter(getDtpNfeEntrada().getValue())) {
            getDtpNfeEmissao().requestFocus();
            return false;
        }
        return (true && validarNfeFiscal());
    }

    private boolean validarNfeFiscal() {
        if (!getTpnNfeDetalheFiscal().isExpanded())
            return true;
        if (getTxtNfeFiscalControle().getText().length() == 0) {
            getTxtNfeFiscalControle().requestFocus();
            return false;
        }
        if (getTxtNfeFiscalOrigem().getText().length() == 0) {
            getTxtNfeFiscalOrigem().requestFocus();
            return false;
        }
        if (getCboNfeFiscalTributo().getValue() == null) {
            getCboNfeFiscalTributo().requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarCteDetalhe() {
        if (!getTpnCteDetalhe().isExpanded())
            return true;
        if (getTxtCteChave().getText().length() < 44) {
            getTxtCteChave().requestFocus();
            return false;
        }
        if (getCboCteTomadorServico().getValue() == null) {
            getCboCteTomadorServico().requestFocus();
            return false;
        }
        if (getTxtCteNumero().getText().length() == 0) {
            getTxtCteNumero().requestFocus();
            return false;
        }
        if (getTxtCteSerie().getText().length() == 0) {
            getTxtCteSerie().requestFocus();
            return false;
        }
        if (getCboNfeModelo().getValue() == null) {
            getCboCteModelo().requestFocus();
            return false;
        }
        if (getDtpCteEmissao().getValue().isAfter(LocalDate.now())) {
            getDtpCteEmissao().requestFocus();
            return false;
        }
        if (getCboCteTransportadora().getValue() == null) {
            getCboCteTransportadora().requestFocus();
            return false;
        }
        if (getCboCteSistuacaoTributaria().getValue() == null) {
            getCboCteSistuacaoTributaria().requestFocus();
            return false;
        }
        if (ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrCte().getText(), 2)
                .compareTo(BigDecimal.ZERO) <= 0) {
            getTxtCteVlrCte().requestFocus();
            return false;
        }
        if (Integer.parseInt(getTxtCteQtdVolume().getText()) <= 0) {
            getTxtCteQtdVolume().requestFocus();
            return false;
        }
        if (ServiceMascara.getBigDecimalFromTextField(getTxtCtePesoBruto().getText(), 3)
                .compareTo(BigDecimal.ZERO) <= 0) {
            getTxtCtePesoBruto().requestFocus();
            return false;
        }
        if (ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrBruto().getText(), 2)
                .compareTo(BigDecimal.ZERO) <= 0) {
            getTxtCteVlrBruto().requestFocus();
            return false;
        }
        if (ServiceMascara.getBigDecimalFromTextField(getLblCteVlrLiquido().getText(), 2)
                .compareTo(BigDecimal.ZERO) <= 0) {
            getTxtCteVlrCte().requestFocus();
            return false;
        }
        return (true && validarCteFiscal());
    }

    private boolean validarCteFiscal() {
        if (!getTpnCteDetalheFiscal().isExpanded())
            return true;
        if (getTxtCteFiscalControle().getText().length() == 0) {
            getTxtCteFiscalControle().requestFocus();
            return false;
        }
        if (getTxtCteFiscalOrigem().getText().length() == 0) {
            getTxtCteFiscalOrigem().requestFocus();
            return false;
        }
        if (getCboCteFiscalTributo().getValue() == null) {
            getCboCteFiscalTributo().requestFocus();
            return false;
        }
        return true;
    }

    private boolean guardarEntradaProduto() {
        try {
            setEntradaProduto(new EntradaProduto());

            getEntradaProduto().setEntradaProdutoProdutoList(getEntradaProdutoProdutoObservableList());
            getEntradaProduto().setSituacao(SituacaoEntrada.DIGITACAO.getCod());
            getEntradaProduto().setLoja(getCboNfeLojaDestino().getValue());
            getEntradaProduto().setUsuarioCadastro(UsuarioLogado.getUsuario());

            EntradaNfe nfe = new EntradaNfe();
            getEntradaProduto().entradaNfeProperty().setValue(nfe);
            nfe.entradaProdutoProperty().setValue(getEntradaProduto());
            nfe.chaveProperty().setValue(getTxtNfeChave().getText().trim().replaceAll("\\D", ""));
            nfe.numeroProperty().setValue(getTxtNfeNumero().getText().trim().replaceAll("\\D", ""));
            nfe.serieProperty().setValue(getTxtNfeSerie().getText().trim().replaceAll("\\D", ""));
            nfe.modeloProperty().setValue(getCboNfeModelo().getSelectionModel().getSelectedItem().getCod());
            nfe.fornecedorProperty().setValue(getCboNfeFornecedor().getSelectionModel().getSelectedItem());
            nfe.setDtEmissao(getDtpNfeEmissao().getValue());
            nfe.setDtEntrada(getDtpNfeEntrada().getValue());

            if (getTpnNfeDetalheFiscal().isExpanded()) {
                EntradaFiscal nfeFiscal = new EntradaFiscal();
                nfe.entradaFiscalProperty().setValue(nfeFiscal);
                nfeFiscal.controleProperty().setValue(getTxtNfeFiscalControle().getText().trim().replaceAll("\\D", ""));
                nfeFiscal.origemProperty().setValue(getTxtNfeFiscalOrigem().getText().trim().replaceAll("\\D", ""));
                nfeFiscal.tributosSefazAmProperty().setValue(getCboNfeFiscalTributo().getSelectionModel().getSelectedItem());
                nfeFiscal.vlrDocumentoProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrNFe().getText(), 4));
                nfeFiscal.vlrTributoProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTributo().getText(), 4));
                nfeFiscal.vlrMultaProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrMulta().getText(), 4));
                nfeFiscal.vlrJurosProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrJuros().getText(), 4));
                nfeFiscal.vlrTaxaProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTaxa().getText(), 4));
            }

            if (getTpnCteDetalhe().isExpanded()) {
                EntradaCte cte = new EntradaCte();
                getEntradaProduto().entradaCteProperty().setValue(cte);
                cte.entradaProdutoProperty().setValue(getEntradaProduto());
                cte.chaveProperty().setValue(getTxtCteChave().getText().trim().replaceAll("\\D", ""));
                cte.tomadorServicoProperty().setValue(getCboCteTomadorServico().getSelectionModel().getSelectedItem().getCod());
                cte.numeroProperty().setValue(getTxtCteNumero().getText().trim().replaceAll("\\D", ""));
                cte.serieProperty().setValue(getTxtCteSerie().getText().trim().replaceAll("\\D", ""));
                cte.modeloProperty().setValue(getCboCteModelo().getSelectionModel().getSelectedItem().getCod());
                cte.dtEmissaoProperty().setValue(getDtpCteEmissao().getValue());
                cte.transportadoraProperty().setValue(getCboCteTransportadora().getSelectionModel().getSelectedItem());
                cte.situacaoTributariaProperty().setValue(getCboCteSistuacaoTributaria().getSelectionModel().getSelectedItem());
                cte.vlrCteProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrCte().getText(), 4));
                cte.qtdVolumeProperty().setValue(Integer.parseInt(getTxtCteQtdVolume().getText().trim().replaceAll("\\D", "")));
                cte.pesoBrutoProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCtePesoBruto().getText(), 4));
                cte.vlrFreteBrutoProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrBruto().getText(), 4));
                cte.vlrTaxasProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrTaxa().getText(), 4));
                cte.vlrColetaProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrColeta().getText(), 4));
                cte.vlrImpostoFreteProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrImposto().getText(), 4));

                if (getTpnCteDetalheFiscal().isExpanded()) {
                    EntradaFiscal cteFiscal = new EntradaFiscal();
                    cte.entradaFiscalProperty().setValue(cteFiscal);
                    cteFiscal.controleProperty().setValue(getTxtCteFiscalControle().getText().trim().replaceAll("\\D", ""));
                    cteFiscal.origemProperty().setValue(getTxtCteFiscalOrigem().getText().trim().replaceAll("\\D", ""));
                    cteFiscal.tributosSefazAmProperty().setValue(getCboCteFiscalTributo().getSelectionModel().getSelectedItem());
                    cteFiscal.vlrDocumentoProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getLblCteFiscalVlrCte().getText(), 4));
                    cteFiscal.vlrTributoProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTributo().getText(), 4));
                    cteFiscal.vlrMultaProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrMulta().getText(), 4));
                    cteFiscal.vlrJurosProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrJuros().getText(), 4));
                    cteFiscal.vlrTaxaProperty().setValue(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTaxa().getText(), 4));
                }
            }

            guardarEntradaProdutoProduto();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean salvarEntradaProduto() {
        boolean retorno;
        try {
            setFichaKardexList(new ArrayList<>());
            getTmodelEntradaProduto().setFichaKardexList(getFichaKardexList());
            setEntradaProdutoDAO(new EntradaProdutoDAO());
            getEntradaProdutoDAO().transactionBegin();
            retorno = getTmodelEntradaProduto().incluirEstoque();
            setEntradaProduto(getEntradaProdutoDAO().setTransactionPersist(getEntradaProduto()));
            getEntradaProdutoDAO().transactionCommit();
            salvarFichaKardexList();
        } catch (Exception ex) {
            ex.printStackTrace();
            getEntradaProdutoDAO().transactionRollback();
            retorno = false;
        }
        return retorno;
    }

    private Produto getProdutoSelecionado() {
        Produto produtoSelecionado = null;
        if (getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue() instanceof Produto)
            produtoSelecionado = (Produto) getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue();
//        if (getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue().idProperty().getValue() != 0)
//            produtoSelecionado = getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getValue();
//        else
//            produtoSelecionado = getTtvProdutoEstoque().getSelectionModel().getSelectedItem().getParent().getValue();
        return produtoSelecionado;
    }


    /**
     * END returns
     */

    /**
     * Begin Getters e Setters
     */

    public AnchorPane getPainelViewEntradaProduto() {
        return painelViewEntradaProduto;
    }

    public void setPainelViewEntradaProduto(AnchorPane painelViewEntradaProduto) {
        this.painelViewEntradaProduto = painelViewEntradaProduto;
    }

    public TitledPane getTpnEntradaNfe() {
        return tpnEntradaNfe;
    }

    public void setTpnEntradaNfe(TitledPane tpnEntradaNfe) {
        this.tpnEntradaNfe = tpnEntradaNfe;
    }

    public TitledPane getTpnNfeDetalhe() {
        return tpnNfeDetalhe;
    }

    public void setTpnNfeDetalhe(TitledPane tpnNfeDetalhe) {
        this.tpnNfeDetalhe = tpnNfeDetalhe;
    }

    public ComboBox<Empresa> getCboNfeLojaDestino() {
        return cboNfeLojaDestino;
    }

    public void setCboNfeLojaDestino(ComboBox<Empresa> cboNfeLojaDestino) {
        this.cboNfeLojaDestino = cboNfeLojaDestino;
    }

    public TextField getTxtNfeChave() {
        return txtNfeChave;
    }

    public void setTxtNfeChave(TextField txtNfeChave) {
        this.txtNfeChave = txtNfeChave;
    }

    public TextField getTxtNfeNumero() {
        return txtNfeNumero;
    }

    public void setTxtNfeNumero(TextField txtNfeNumero) {
        this.txtNfeNumero = txtNfeNumero;
    }

    public TextField getTxtNfeSerie() {
        return txtNfeSerie;
    }

    public void setTxtNfeSerie(TextField txtNfeSerie) {
        this.txtNfeSerie = txtNfeSerie;
    }

    public ComboBox<NfeCteModelo> getCboNfeModelo() {
        return cboNfeModelo;
    }

    public void setCboNfeModelo(ComboBox<NfeCteModelo> cboNfeModelo) {
        this.cboNfeModelo = cboNfeModelo;
    }

    public ComboBox<Empresa> getCboNfeFornecedor() {
        return cboNfeFornecedor;
    }

    public void setCboNfeFornecedor(ComboBox<Empresa> cboNfeFornecedor) {
        this.cboNfeFornecedor = cboNfeFornecedor;
    }

    public DatePicker getDtpNfeEmissao() {
        return dtpNfeEmissao;
    }

    public void setDtpNfeEmissao(DatePicker dtpNfeEmissao) {
        this.dtpNfeEmissao = dtpNfeEmissao;
    }

    public DatePicker getDtpNfeEntrada() {
        return dtpNfeEntrada;
    }

    public void setDtpNfeEntrada(DatePicker dtpNfeEntrada) {
        this.dtpNfeEntrada = dtpNfeEntrada;
    }

    public TitledPane getTpnNfeDetalheFiscal() {
        return tpnNfeDetalheFiscal;
    }

    public void setTpnNfeDetalheFiscal(TitledPane tpnNfeDetalheFiscal) {
        this.tpnNfeDetalheFiscal = tpnNfeDetalheFiscal;
    }

    public TextField getTxtNfeFiscalControle() {
        return txtNfeFiscalControle;
    }

    public void setTxtNfeFiscalControle(TextField txtNfeFiscalControle) {
        this.txtNfeFiscalControle = txtNfeFiscalControle;
    }

    public TextField getTxtNfeFiscalOrigem() {
        return txtNfeFiscalOrigem;
    }

    public void setTxtNfeFiscalOrigem(TextField txtNfeFiscalOrigem) {
        this.txtNfeFiscalOrigem = txtNfeFiscalOrigem;
    }

    public ComboBox<FiscalTributosSefazAm> getCboNfeFiscalTributo() {
        return cboNfeFiscalTributo;
    }

    public void setCboNfeFiscalTributo(ComboBox<FiscalTributosSefazAm> cboNfeFiscalTributo) {
        this.cboNfeFiscalTributo = cboNfeFiscalTributo;
    }

    public TextField getTxtNfeFiscalVlrNFe() {
        return txtNfeFiscalVlrNFe;
    }

    public void setTxtNfeFiscalVlrNFe(TextField txtNfeFiscalVlrNFe) {
        this.txtNfeFiscalVlrNFe = txtNfeFiscalVlrNFe;
    }

    public TextField getTxtNfeFiscalVlrTributo() {
        return txtNfeFiscalVlrTributo;
    }

    public void setTxtNfeFiscalVlrTributo(TextField txtNfeFiscalVlrTributo) {
        this.txtNfeFiscalVlrTributo = txtNfeFiscalVlrTributo;
    }

    public TextField getTxtNfeFiscalVlrMulta() {
        return txtNfeFiscalVlrMulta;
    }

    public void setTxtNfeFiscalVlrMulta(TextField txtNfeFiscalVlrMulta) {
        this.txtNfeFiscalVlrMulta = txtNfeFiscalVlrMulta;
    }

    public TextField getTxtNfeFiscalVlrJuros() {
        return txtNfeFiscalVlrJuros;
    }

    public void setTxtNfeFiscalVlrJuros(TextField txtNfeFiscalVlrJuros) {
        this.txtNfeFiscalVlrJuros = txtNfeFiscalVlrJuros;
    }

    public TextField getTxtNfeFiscalVlrTaxa() {
        return txtNfeFiscalVlrTaxa;
    }

    public void setTxtNfeFiscalVlrTaxa(TextField txtNfeFiscalVlrTaxa) {
        this.txtNfeFiscalVlrTaxa = txtNfeFiscalVlrTaxa;
    }

    public Label getLblNfeFiscalVlrTotal() {
        return lblNfeFiscalVlrTotal;
    }

    public void setLblNfeFiscalVlrTotal(Label lblNfeFiscalVlrTotal) {
        this.lblNfeFiscalVlrTotal = lblNfeFiscalVlrTotal;
    }

    public Label getLblNfeFiscalVlrPercentual() {
        return lblNfeFiscalVlrPercentual;
    }

    public void setLblNfeFiscalVlrPercentual(Label lblNfeFiscalVlrPercentual) {
        this.lblNfeFiscalVlrPercentual = lblNfeFiscalVlrPercentual;
    }

    public TitledPane getTpnCteDetalhe() {
        return tpnCteDetalhe;
    }

    public void setTpnCteDetalhe(TitledPane tpnCteDetalhe) {
        this.tpnCteDetalhe = tpnCteDetalhe;
    }

    public TextField getTxtCteChave() {
        return txtCteChave;
    }

    public void setTxtCteChave(TextField txtCteChave) {
        this.txtCteChave = txtCteChave;
    }

    public ComboBox<CteTomadorServico> getCboCteTomadorServico() {
        return cboCteTomadorServico;
    }

    public void setCboCteTomadorServico(ComboBox<CteTomadorServico> cboCteTomadorServico) {
        this.cboCteTomadorServico = cboCteTomadorServico;
    }

    public TextField getTxtCteNumero() {
        return txtCteNumero;
    }

    public void setTxtCteNumero(TextField txtCteNumero) {
        this.txtCteNumero = txtCteNumero;
    }

    public TextField getTxtCteSerie() {
        return txtCteSerie;
    }

    public void setTxtCteSerie(TextField txtCteSerie) {
        this.txtCteSerie = txtCteSerie;
    }

    public ComboBox<NfeCteModelo> getCboCteModelo() {
        return cboCteModelo;
    }

    public void setCboCteModelo(ComboBox<NfeCteModelo> cboCteModelo) {
        this.cboCteModelo = cboCteModelo;
    }

    public DatePicker getDtpCteEmissao() {
        return dtpCteEmissao;
    }

    public void setDtpCteEmissao(DatePicker dtpCteEmissao) {
        this.dtpCteEmissao = dtpCteEmissao;
    }

    public ComboBox<Empresa> getCboCteTransportadora() {
        return cboCteTransportadora;
    }

    public void setCboCteTransportadora(ComboBox<Empresa> cboCteTransportadora) {
        this.cboCteTransportadora = cboCteTransportadora;
    }

    public ComboBox<FiscalFreteSituacaoTributaria> getCboCteSistuacaoTributaria() {
        return cboCteSistuacaoTributaria;
    }

    public void setCboCteSistuacaoTributaria(ComboBox<FiscalFreteSituacaoTributaria> cboCteSistuacaoTributaria) {
        this.cboCteSistuacaoTributaria = cboCteSistuacaoTributaria;
    }

    public TextField getTxtCteVlrCte() {
        return txtCteVlrCte;
    }

    public void setTxtCteVlrCte(TextField txtCteVlrCte) {
        this.txtCteVlrCte = txtCteVlrCte;
    }

    public TextField getTxtCteQtdVolume() {
        return txtCteQtdVolume;
    }

    public void setTxtCteQtdVolume(TextField txtCteQtdVolume) {
        this.txtCteQtdVolume = txtCteQtdVolume;
    }

    public TextField getTxtCtePesoBruto() {
        return txtCtePesoBruto;
    }

    public void setTxtCtePesoBruto(TextField txtCtePesoBruto) {
        this.txtCtePesoBruto = txtCtePesoBruto;
    }

    public TextField getTxtCteVlrBruto() {
        return txtCteVlrBruto;
    }

    public void setTxtCteVlrBruto(TextField txtCteVlrBruto) {
        this.txtCteVlrBruto = txtCteVlrBruto;
    }

    public TextField getTxtCteVlrTaxa() {
        return txtCteVlrTaxa;
    }

    public void setTxtCteVlrTaxa(TextField txtCteVlrTaxa) {
        this.txtCteVlrTaxa = txtCteVlrTaxa;
    }

    public TextField getTxtCteVlrColeta() {
        return txtCteVlrColeta;
    }

    public void setTxtCteVlrColeta(TextField txtCteVlrColeta) {
        this.txtCteVlrColeta = txtCteVlrColeta;
    }

    public TextField getTxtCteVlrImposto() {
        return txtCteVlrImposto;
    }

    public void setTxtCteVlrImposto(TextField txtCteVlrImposto) {
        this.txtCteVlrImposto = txtCteVlrImposto;
    }

    public Label getLblCteVlrLiquido() {
        return lblCteVlrLiquido;
    }

    public void setLblCteVlrLiquido(Label lblCteVlrLiquido) {
        this.lblCteVlrLiquido = lblCteVlrLiquido;
    }

    public TitledPane getTpnCteDetalheFiscal() {
        return tpnCteDetalheFiscal;
    }

    public void setTpnCteDetalheFiscal(TitledPane tpnCteDetalheFiscal) {
        this.tpnCteDetalheFiscal = tpnCteDetalheFiscal;
    }

    public TextField getTxtCteFiscalControle() {
        return txtCteFiscalControle;
    }

    public void setTxtCteFiscalControle(TextField txtCteFiscalControle) {
        this.txtCteFiscalControle = txtCteFiscalControle;
    }

    public TextField getTxtCteFiscalOrigem() {
        return txtCteFiscalOrigem;
    }

    public void setTxtCteFiscalOrigem(TextField txtCteFiscalOrigem) {
        this.txtCteFiscalOrigem = txtCteFiscalOrigem;
    }

    public ComboBox<FiscalTributosSefazAm> getCboCteFiscalTributo() {
        return cboCteFiscalTributo;
    }

    public void setCboCteFiscalTributo(ComboBox<FiscalTributosSefazAm> cboCteFiscalTributo) {
        this.cboCteFiscalTributo = cboCteFiscalTributo;
    }

    public Label getLblCteFiscalVlrCte() {
        return lblCteFiscalVlrCte;
    }

    public void setLblCteFiscalVlrCte(Label lblCteFiscalVlrCte) {
        this.lblCteFiscalVlrCte = lblCteFiscalVlrCte;
    }

    public TextField getTxtCteFiscalVlrTributo() {
        return txtCteFiscalVlrTributo;
    }

    public void setTxtCteFiscalVlrTributo(TextField txtCteFiscalVlrTributo) {
        this.txtCteFiscalVlrTributo = txtCteFiscalVlrTributo;
    }

    public TextField getTxtCteFiscalVlrMulta() {
        return txtCteFiscalVlrMulta;
    }

    public void setTxtCteFiscalVlrMulta(TextField txtCteFiscalVlrMulta) {
        this.txtCteFiscalVlrMulta = txtCteFiscalVlrMulta;
    }

    public TextField getTxtCteFiscalVlrJuros() {
        return txtCteFiscalVlrJuros;
    }

    public void setTxtCteFiscalVlrJuros(TextField txtCteFiscalVlrJuros) {
        this.txtCteFiscalVlrJuros = txtCteFiscalVlrJuros;
    }

    public TextField getTxtCteFiscalVlrTaxa() {
        return txtCteFiscalVlrTaxa;
    }

    public void setTxtCteFiscalVlrTaxa(TextField txtCteFiscalVlrTaxa) {
        this.txtCteFiscalVlrTaxa = txtCteFiscalVlrTaxa;
    }

    public Label getLblCteFiscalVlrTotal() {
        return lblCteFiscalVlrTotal;
    }

    public void setLblCteFiscalVlrTotal(Label lblCteFiscalVlrTotal) {
        this.lblCteFiscalVlrTotal = lblCteFiscalVlrTotal;
    }

    public Label getLblCteFiscalVlrPercentual() {
        return lblCteFiscalVlrPercentual;
    }

    public void setLblCteFiscalVlrPercentual(Label lblCteFiscalVlrPercentual) {
        this.lblCteFiscalVlrPercentual = lblCteFiscalVlrPercentual;
    }

    public TitledPane getTpnItensTotaisDetalhe() {
        return tpnItensTotaisDetalhe;
    }

    public void setTpnItensTotaisDetalhe(TitledPane tpnItensTotaisDetalhe) {
        this.tpnItensTotaisDetalhe = tpnItensTotaisDetalhe;
    }

    public TextField getTxtPesquisaProduto() {
        return txtPesquisaProduto;
    }

    public void setTxtPesquisaProduto(TextField txtPesquisaProduto) {
        this.txtPesquisaProduto = txtPesquisaProduto;
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

    public TableView<EntradaProdutoProduto> getTvItensNfe() {
        return tvItensNfe;
    }

    public void setTvItensNfe(TableView<EntradaProdutoProduto> tvItensNfe) {
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

    public VBox getvBoxTotalImposto() {
        return vBoxTotalImposto;
    }

    public void setvBoxTotalImposto(VBox vBoxTotalImposto) {
        this.vBoxTotalImposto = vBoxTotalImposto;
    }

    public Label getLblTotalImposto() {
        return lblTotalImposto;
    }

    public void setLblTotalImposto(Label lblTotalImposto) {
        this.lblTotalImposto = lblTotalImposto;
    }

    public VBox getvBoxTotalFrete() {
        return vBoxTotalFrete;
    }

    public void setvBoxTotalFrete(VBox vBoxTotalFrete) {
        this.vBoxTotalFrete = vBoxTotalFrete;
    }

    public Label getLblTotalFrete() {
        return lblTotalFrete;
    }

    public void setLblTotalFrete(Label lblTotalFrete) {
        this.lblTotalFrete = lblTotalFrete;
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

    public StatusBarEntradaProduto getStatusBar() {
        return statusBar.get();
    }

    public ObjectProperty<StatusBarEntradaProduto> statusBarProperty() {
        return statusBar;
    }

    public void setStatusBar(StatusBarEntradaProduto statusBar) {
        this.statusBar.set(statusBar);
    }

    public EventHandler getEventHandlerEntradaProduto() {
        return eventHandlerEntradaProduto;
    }

    public void setEventHandlerEntradaProduto(EventHandler eventHandlerEntradaProduto) {
        this.eventHandlerEntradaProduto = eventHandlerEntradaProduto;
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

    public TmodelEntradaProduto getTmodelEntradaProduto() {
        return tmodelEntradaProduto;
    }

    public void setTmodelEntradaProduto(TmodelEntradaProduto tmodelEntradaProduto) {
        this.tmodelEntradaProduto = tmodelEntradaProduto;
    }

    public ObservableList<EntradaProdutoProduto> getEntradaProdutoProdutoObservableList() {
        return entradaProdutoProdutoObservableList;
    }

    public void setEntradaProdutoProdutoObservableList(ObservableList<EntradaProdutoProduto> entradaProdutoProdutoObservableList) {
        this.entradaProdutoProdutoObservableList = entradaProdutoProdutoObservableList;
    }

    public String getNfeFiscalVlrTotal() {
        return nfeFiscalVlrTotal.get();
    }

    public StringProperty nfeFiscalVlrTotalProperty() {
        return nfeFiscalVlrTotal;
    }

    public void setNfeFiscalVlrTotal(String nfeFiscalVlrTotal) {
        this.nfeFiscalVlrTotal.set(nfeFiscalVlrTotal);
    }

    public EntradaProduto getEntradaProduto() {
        return entradaProduto;
    }

    public void setEntradaProduto(EntradaProduto entradaProduto) {
        this.entradaProduto = entradaProduto;
    }

    public EntradaProdutoDAO getEntradaProdutoDAO() {
        return entradaProdutoDAO;
    }

    public void setEntradaProdutoDAO(EntradaProdutoDAO entradaProdutoDAO) {
        this.entradaProdutoDAO = entradaProdutoDAO;
    }

    public List<FichaKardex> getFichaKardexList() {
        return fichaKardexList;
    }

    public void setFichaKardexList(List<FichaKardex> fichaKardexList) {
        this.fichaKardexList = fichaKardexList;
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
