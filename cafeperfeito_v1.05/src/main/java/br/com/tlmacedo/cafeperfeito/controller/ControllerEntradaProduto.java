package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.*;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelEntradaProdutoProduto;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.CteTomadorServico;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.NfeCteModelo;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoEntrada;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.StatusBarEntradaProduto;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.view.ViewEntradaProduto;
import br.inf.portalfiscal.xsd.cte.procCTe.CteProc;
import br.inf.portalfiscal.xsd.cte.procCTe.TCTe;
import br.inf.portalfiscal.xsd.nfe.procNFe.TNfeProc;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_NFE_TO_LOCAL_DATE;

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
    public JFXComboBox<Empresa> cboNfeLojaDestino;
    public JFXTextField txtNfeChave;
    public JFXTextField txtNfeNumero;
    public JFXTextField txtNfeSerie;
    public JFXComboBox<NfeCteModelo> cboNfeModelo;
    public JFXComboBox<Empresa> cboNfeFornecedor;
    public DatePicker dtpNfeEmissao;
    public DatePicker dtpNfeEntrada;

    public TitledPane tpnNfeFiscal;
    public JFXTextField txtNfeFiscalControle;
    public JFXTextField txtNfeFiscalOrigem;
    public JFXComboBox<FiscalTributosSefazAm> cboNfeFiscalTributo;
    public JFXTextField txtNfeFiscalVlrNFe;
    public JFXTextField txtNfeFiscalVlrTributo;
    public JFXTextField txtNfeFiscalVlrMulta;
    public JFXTextField txtNfeFiscalVlrJuros;
    public JFXTextField txtNfeFiscalVlrTaxa;
    public JFXTextField txtNfeFiscalVlrTotal;
    public JFXTextField txtNfeFiscalVlrPercentual;

    public TitledPane tpnCteDetalhe;
    public JFXTextField txtCteChave;
    public JFXComboBox<CteTomadorServico> cboCteTomadorServico;
    public JFXTextField txtCteNumero;
    public JFXTextField txtCteSerie;
    public JFXComboBox<NfeCteModelo> cboCteModelo;
    public JFXComboBox<FiscalFreteSituacaoTributaria> cboCteSistuacaoTributaria;
    public JFXComboBox<Empresa> cboCteTransportadora;
    public DatePicker dtpCteEmissao;
    public JFXTextField txtCteVlrCte;
    public JFXTextField txtCteQtdVolume;
    public JFXTextField txtCtePesoBruto;
    public JFXTextField txtCteVlrBruto;
    public JFXTextField txtCteVlrTaxa;
    public JFXTextField txtCteVlrColeta;
    public JFXTextField txtCteVlrImposto;
    public JFXTextField txtCteVlrLiquido;

    public TitledPane tpnCteFiscal;
    public JFXTextField txtCteFiscalControle;
    public JFXTextField txtCteFiscalOrigem;
    public JFXComboBox<FiscalTributosSefazAm> cboCteFiscalTributo;
    public JFXTextField txtCteFiscalVlrCte;
    public JFXTextField txtCteFiscalVlrTributo;
    public JFXTextField txtCteFiscalVlrMulta;
    public JFXTextField txtCteFiscalVlrJuros;
    public JFXTextField txtCteFiscalVlrTaxa;
    public JFXTextField txtCteFiscalVlrTotal;
    public JFXTextField txtCteFiscalVlrPercentual;

    public TitledPane tpnItensTotaisNfe;

    public TitledPane tpnCadastroProduto;
    public JFXTextField txtPesquisaProduto;
    public TreeTableView<Produto> ttvProduto;
    public Label lblStatus;
    public Label lblRegistrosLocalizados;

    public TitledPane tpnItensNfe;
    public TableView<EntradaProdutoProduto> ttvItensNfe;
    public Label labelQtdItem;
    public Label lblQtdItem;
    public Label labelQtdTotal;
    public Label lblQtdTotal;
    public Label labelQtdVolume;
    public Label lblQtdVolume;
    public Label labelTotalBruto;
    public Label lblTotalBruto;
    public Label labelTotalImposto;
    public Label lblTotalImposto;
    public Label labelTotalFrete;
    public Label lblTotalFrete;
    public Label lblTotalDesconto;
    public Label labelTotalLiquido;
    public Label lblTotalLiquido;

    boolean tabCarregada = false;

    private EventHandler eventHandlerEntradaProduto;
    private ObjectProperty<StatusBarEntradaProduto> statusBar = new SimpleObjectProperty<>();

    private String nomeController = "entradaProduto";
    private String nomeTab = "";
    private ServiceAlertMensagem alertMensagem;
    private List<Pair> listaTarefa = new ArrayList<>();

    private TabModelProduto modelProduto;
    private TablePosition tp;
    private TabModelEntradaProdutoProduto modelEntradaProdutoProduto;
    private EntradaProduto entradaProduto = new EntradaProduto();
    private ObservableList<EntradaProduto> entradaProdutoObservableList =
            FXCollections.observableArrayList(new EntradaProdutoDAO().getAll(EntradaProduto.class, null, null, null, null));
    private ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(new ProdutoDAO().getAll(Produto.class, "descricao", null, null, null));
    private FilteredList<Produto> produtoFilteredList = new FilteredList<>(produtoObservableList);
    private ObservableList<EntradaProdutoProduto> entradaProdutoProdutoObservableList = FXCollections.observableArrayList();
    private EntradaProdutoDAO entradaProdutoDAO = new EntradaProdutoDAO();

    private EntradaProdutoProduto entradaProdutoProduto = new EntradaProdutoProduto();
    private IntegerProperty numItens = new SimpleIntegerProperty();
    private IntegerProperty qtdItens = new SimpleIntegerProperty();
    private IntegerProperty qtdVolume = new SimpleIntegerProperty();
    private ObjectProperty<BigDecimal> totalBruto = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalFrete = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalImpostoEntrada = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalImpostoItens = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalImposto = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalDesconto = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalLiquido = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));

    private ObjectProperty<BigDecimal> nfeVlr = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> nfeVlrTributo = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> nfeVlrMulta = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> nfeVlrJuros = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> nfeVlrTaxa = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> nfeVlrTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));

    @Override
    public void fechar() {
        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().remove(ViewEntradaProduto.getTab());
        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerEntradaProduto());
    }

    @Override
    public void criarObjetos() {
        setNomeTab(ViewEntradaProduto.getTituloJanela());
        getListaTarefa().add(new Pair("criarTabela", "criando tabela de " + getNomeController()));
    }

    @Override
    public void preencherObjetos() {
        getListaTarefa().add(new Pair("vinculandoObjetosTabela", "vinculando objetos a tableModel"));
        getListaTarefa().add(new Pair("preencherTabela", "preenchendo tabela " + getNomeController()));

        getListaTarefa().add(new Pair("preencherCombos", "carregando informações do formulario"));

        setTabCarregada(new ServiceSegundoPlano().tarefaAbreCadastro(taskEntradaProduto(), getListaTarefa().size(),
                String.format("Abrindo %s", getNomeTab())));
    }

    @Override
    public void fatorarObjetos() {
        getTpnNfeFiscal().setExpanded(false);
        getTpnCteFiscal().setExpanded(false);
        getTpnCteDetalhe().setExpanded(false);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void escutarTecla() {
        escutaTitledTab();

        if (statusBarProperty().get() == null)
            setStatusBar(StatusBarEntradaProduto.DIGITACAO);
        ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());

        getLblStatus().textProperty().bind(Bindings.createStringBinding(() -> {
            switch (statusBarProperty().get()) {
                case DIGITACAO:
                    ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F1));
                    break;
                case LANCADO:
                case INCLUIDO:
                case FATURADO:
                    ServiceCampoPersonalizado.fieldDisable(getPainelViewEntradaProduto(), true);
                    break;
            }
            ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());
            return String.format("[%s]", statusBarProperty().get());
        }, statusBarProperty()));

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size() == 0) return;
            if (ControllerPrincipal.ctrlPrincipal.getTabSelecionada().equals(getNomeTab()))
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(getStatusBar().getDescricao());
        });

        getLblStatus().widthProperty().addListener((ov, o, n) -> {
            getLblRegistrosLocalizados().setLayoutX(getLblStatus().getLayoutX() + n.doubleValue() + 20.);
        });

        getTtvProduto().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER || getTtvProduto().getSelectionModel().getSelectedItem() == null)
                return;
            Produto produtoSelecionado;
            if ((produtoSelecionado = getTtvProduto().getSelectionModel().getSelectedItem().getValue()).getCodigo().equals(""))
                produtoSelecionado = getTtvProduto().getSelectionModel().getSelectedItem().getParent().getValue();
            getEntradaProdutoProdutoObservableList().add(new EntradaProdutoProduto(produtoSelecionado));
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F8));
        });

        setEventHandlerEntradaProduto(new EventHandler<KeyEvent>() {
            /**
             * Invoked when a specific event of the type for which this handler is
             * registered happens.
             *
             * @param event the event which occurred
             */
            @Override
            public void handle(KeyEvent event) {
                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() < 0)
                    return;
                if (!ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().getText().equals(ViewEntradaProduto.getTituloJanela()))
                    return;
                switch (event.getCode()) {
                    case F1:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        limparCampos();
                        getTxtNfeChave().requestFocus();
                        break;
                    case F2:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        if (!guardarEntradaProduto()) break;
                        if (!salvarEntradaProduto()) break;
                        getModelProduto().atualizaProdutos();
                        break;
                    case F3:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        setAlertMensagem(new ServiceAlertMensagem());
                        getAlertMensagem().setCabecalho(String.format("Excluir entrada"));
                        getAlertMensagem().setPromptText(String.format("%s, deseja excluir entrada",
                                LogadoInf.getUserLog().getApelido()));
                        if (getAlertMensagem().getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
//                        if (orcamento != null)
//                            entradaTempDAO.remove(orcamento);
                        setStatusBar(StatusBarEntradaProduto.DIGITACAO);
                        break;
                    case F4:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
//                        if (!validarEntradaProduto()) break;
//                        salvarEntradaTemp();
                        setStatusBar(StatusBarEntradaProduto.DIGITACAO);
                        break;
                    case F5:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTxtNfeChave().requestFocus();
                        break;
                    case F6:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTxtCteChave().requestFocus();
                        break;
                    case F7:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTxtPesquisaProduto().requestFocus();
                        break;
                    case F8:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTtvItensNfe().requestFocus();
                        getTtvItensNfe().getSelectionModel().select(getEntradaProdutoProdutoObservableList().size() - 1,
                                getModelEntradaProdutoProduto().getColunaLote());
                        getTtvItensNfe().getFocusModel().focus(getEntradaProdutoProdutoObservableList().size() - 1,
                                getModelEntradaProdutoProduto().getColunaLote());
                        break;
                    case F9:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTpnNfeFiscal().setExpanded(!getTpnNfeFiscal().isExpanded());
                        break;
                    case F10:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTpnCteDetalhe().setExpanded(!getTpnCteDetalhe().isExpanded());
                        break;
                    case F11:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTpnCteDetalhe().setExpanded(true);
                        getTpnCteFiscal().setExpanded(!getTpnCteFiscal().isExpanded());
                        break;
                    case F12:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
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
                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() > 0)
                    ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().setOnCloseRequest(event1 -> {
                        if (!getStatusBar().equals(StatusBarEntradaProduto.DIGITACAO)) {
                            event1.consume();
                        }
                    });
            }
        });

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerEntradaProduto());

        getTxtPesquisaProduto().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            getTtvProduto().requestFocus();
            getTtvProduto().getSelectionModel().selectFirst();
        });

        getTxtNfeChave().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (!getStatusBar().equals(StatusBarEntradaProduto.DIGITACAO)) return;
            if (event.getCode() != KeyCode.ENTER || getTxtNfeChave().getText().replaceAll("\\D", "").length() != 44)
                return;
            buscaEntrada(getTxtNfeChave().getText());
        });

        getTxtCteChave().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (!getStatusBar().equals(StatusBarEntradaProduto.DIGITACAO)) return;
            if (event.getCode() != KeyCode.ENTER || getTxtCteChave().getText().replaceAll("\\D", "").length() != 44)
                return;
            buscaEntrada(getTxtCteChave().getText());
        });

        getTxtNfeChave().setOnDragOver(event -> {
            if (getTxtNfeChave().isDisabled()) return;
            Dragboard board = event.getDragboard();
            if (board.hasFiles())
                if (Pattern.compile(".xml").matcher(board.getFiles().get(0).toPath().toString()).find())
                    event.acceptTransferModes(TransferMode.ANY);
        });

        getTxtNfeChave().setOnDragDropped(event -> {
            if (getTxtNfeChave().isDisabled()) return;
            Dragboard board = event.getDragboard();
            addXmlNfe(board.getFiles().get(0));
        });

        getTxtCteChave().setOnDragOver(event -> {
            if (getTxtCteChave().isDisabled()) return;
            Dragboard board = event.getDragboard();
            if (board.hasFiles())
                if (Pattern.compile(".xml").matcher(board.getFiles().get(0).toPath().toString()).find())
                    event.acceptTransferModes(TransferMode.ANY);
        });

        getTxtCteChave().setOnDragDropped(event -> {
            if (getTxtCteChave().isDisabled()) return;
            Dragboard board = event.getDragboard();
            addXmlCte(board.getFiles().get(0));
        });

        getTpnNfeDetalhe().textProperty().bind(Bindings.createStringBinding(() ->
                String.format("Detalhe da nf-e %s",
                        getTxtNfeNumero().getText().length() == 0
                                ? ""
                                : "[" + getTxtNfeNumero().getText().trim() + "]"
                ), getTxtNfeNumero().textProperty()
        ));

        getTpnNfeFiscal().textProperty().bind(Bindings.createStringBinding(() ->
                String.format("%s",
                        getTpnNfeFiscal().isExpanded()
                                ? "Informações de imposto"
                                : "Nf-e sem imposto"
                ), getTpnNfeFiscal().expandedProperty()
        ));

        getTpnCteDetalhe().textProperty().bind(Bindings.createStringBinding(() -> {
                    if (getTpnCteDetalhe().isExpanded()) {
                        return String.format("Detalhe frete do ct-e %s",
                                getTxtCteNumero().getText().length() == 0
                                        ? ""
                                        : "[" + getTxtCteNumero().getText().trim() + "]"
                        );
                    } else {
                        return "Nf-e sem frete";
                    }
                }, getTxtCteNumero().textProperty()
        ));

        getTpnCteFiscal().textProperty().bind(Bindings.createStringBinding(() ->
                String.format("%s",
                        getTpnCteFiscal().isExpanded()
                                ? "Informações de imposto no frete"
                                : "Frete sem imposto"
                ), getTpnCteFiscal().expandedProperty()
        ));

        getLblQtdItem().textProperty().bind(numItensProperty().asString());
        getLblQtdTotal().textProperty().bind(qtdItensProperty().asString());
        getLblQtdVolume().textProperty().bind(qtdVolumeProperty().asString());
        getLblTotalBruto().textProperty().bind(Bindings.createStringBinding(() ->
                ServiceMascara.getMoeda2(totalBrutoProperty().get(), 2), totalBrutoProperty()
        ));
        getLblTotalImposto().textProperty().bind(Bindings.createStringBinding(() ->
                ServiceMascara.getMoeda2(totalImpostoProperty().get(), 2), totalImpostoProperty()
        ));
        getLblTotalFrete().textProperty().bind(Bindings.createStringBinding(() ->
                ServiceMascara.getMoeda2(totalFreteProperty().get(), 2), totalFreteProperty()
        ));
        getLblTotalDesconto().textProperty().bind(Bindings.createStringBinding(() ->
                ServiceMascara.getMoeda2(totalDescontoProperty().get(), 2), totalDescontoProperty()
        ));
        getLblTotalLiquido().textProperty().bind(Bindings.createStringBinding(() ->
                ServiceMascara.getMoeda2(totalLiquidoProperty().get(), 2), totalLiquidoProperty()
        ));

        getEntradaProdutoProdutoObservableList().addListener((ListChangeListener<? super EntradaProdutoProduto>) c -> {
            totalizaTabela();
        });

        getTxtNfeFiscalVlrTotal().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrTributo = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTributo().getText(), 2);
                    BigDecimal vlrMulta = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrMulta().getText(), 2);
                    BigDecimal vlrJuros = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrJuros().getText(), 2);
                    BigDecimal vlrTaxa = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTaxa().getText(), 2);

                    return ServiceMascara.getMoeda2(vlrTributo.add(vlrMulta).add(vlrJuros).add(vlrTaxa), 2);
                }, getTxtNfeFiscalVlrTributo().textProperty(), getTxtNfeFiscalVlrMulta().textProperty(),
                getTxtNfeFiscalVlrJuros().textProperty(), getTxtNfeFiscalVlrTaxa().textProperty()
        ));

        getTxtNfeFiscalVlrPercentual().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrNfe = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrNFe().getText(), 2);
                    BigDecimal vlrTotal = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTotal().getText(), 2);
                    try {
                        return ServiceMascara.getMoeda2(vlrTotal
                                .divide(vlrNfe, 5, RoundingMode.HALF_UP)
                                .multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP), 2);
                    } catch (ArithmeticException ae) {
                        return "0,00";
                    }
                }, getTxtNfeFiscalVlrNFe().textProperty(), getTxtNfeFiscalVlrTotal().textProperty()
        ));

        getTxtCteFiscalVlrTotal().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrTributo = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTributo().getText(), 2);
                    BigDecimal vlrMulta = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrMulta().getText(), 2);
                    BigDecimal vlrJuros = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrJuros().getText(), 2);
                    BigDecimal vlrTaxa = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTaxa().getText(), 2);

                    return ServiceMascara.getMoeda2(vlrTributo.add(vlrMulta).add(vlrJuros).add(vlrTaxa), 2);
                }, getTxtCteFiscalVlrTributo().textProperty(), getTxtCteFiscalVlrMulta().textProperty(),
                getTxtCteFiscalVlrJuros().textProperty(), getTxtCteFiscalVlrTaxa().textProperty()
        ));

        getTxtCteFiscalVlrPercentual().textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal vlrCte = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrCte().getText(), 2);
                    BigDecimal vlrTotal = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTotal().getText(), 2);
                    try {
                        return ServiceMascara.getMoeda2(vlrTotal
                                .divide(vlrCte, 5, RoundingMode.HALF_UP)
                                .multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP), 2);
                    } catch (ArithmeticException ae) {
                        return "0,00";
                    }
                }, getTxtCteFiscalVlrCte().textProperty(), getTxtCteFiscalVlrTotal().textProperty()
        ));

        totalFreteProperty().bind(Bindings.createObjectBinding(() -> {
                    BigDecimal vlrBruto = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrBruto().getText(), 2);
                    BigDecimal vlrTaxa = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrTaxa().getText(), 2);
                    BigDecimal vlrColeta = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrColeta().getText(), 2);
                    BigDecimal vlrImposto = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrImposto().getText(), 2);
                    BigDecimal vlrTotal = vlrBruto.add(vlrTaxa).add(vlrColeta).add(vlrImposto);
                    getTxtCteVlrLiquido().setText(ServiceMascara.getMoeda2(vlrTotal, 2));
                    getTxtCteVlrCte().setText(ServiceMascara.getMoeda2(vlrTotal, 2));
                    if (getTpnCteFiscal().isExpanded())
                        getTxtCteFiscalVlrCte().setText(ServiceMascara.getMoeda2(vlrTotal, 2));

                    return vlrTotal;
                }, getTxtCteVlrBruto().textProperty(), getTxtCteVlrTaxa().textProperty(),
                getTxtCteVlrColeta().textProperty(), getTxtCteVlrImposto().textProperty()
        ));

        totalImpostoEntradaProperty().bind(Bindings.createObjectBinding(() -> {
                    BigDecimal vlrImpostoNfe = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTotal().getText(), 2);
                    BigDecimal vlrImpostoCte = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTotal().getText(), 2);

                    return vlrImpostoNfe.add(vlrImpostoCte);

                }, getTxtNfeFiscalVlrTotal().textProperty(), getTxtCteFiscalVlrTotal().textProperty()
        ));

        totalImpostoProperty().bind(Bindings.createObjectBinding(() ->
                        totalImpostoEntradaProperty().get().add(totalImpostoItensProperty().get())
                , totalImpostoEntradaProperty(), totalImpostoItensProperty()
        ));

        totalLiquidoProperty().bind(Bindings.createObjectBinding(() ->
                        (totalBrutoProperty().get().add(totalImpostoProperty().get()).add(totalFreteProperty().get()))
                                .subtract(totalDescontoProperty().get())
                , totalBrutoProperty(), totalImpostoProperty(), totalFreteProperty(), totalDescontoProperty()
        ));

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
    @SuppressWarnings("Duplicates")
    public void initialize(URL location, ResourceBundle resources) {
        criarObjetos();
        preencherObjetos();
        escutarTecla();
        fatorarObjetos();
        setStatusBar(StatusBarEntradaProduto.DIGITACAO);
        ServiceCampoPersonalizado.fieldMask(getPainelViewEntradaProduto());

        Platform.runLater(() -> {
            if (!isTabCarregada())
                fechar();
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F5));
        });
    }

    @SuppressWarnings("Duplicates")
    Task taskEntradaProduto() {
        ControllerEntradaProduto tmpEntrada = this;
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando");
                for (Pair tarefaAtual : getListaTarefa()) {
                    updateProgress(getListaTarefa().indexOf(tarefaAtual), getListaTarefa().size());
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    switch (tarefaAtual.getKey().toString()) {
                        case "criarTabela":
                            setModelProduto(new TabModelProduto());
                            getModelProduto().tabela();
                            getModelProduto().setTipoForm(null);

                            setModelEntradaProdutoProduto(new TabModelEntradaProdutoProduto(tmpEntrada));
                            getModelEntradaProdutoProduto().tabela();
                            break;

                        case "vinculandoObjetosTabela":
                            getModelProduto().setLblRegistrosLocalizados(getLblRegistrosLocalizados());
                            getModelProduto().setTtvProduto(getTtvProduto());
                            getModelProduto().setTxtPesquisaProduto(getTxtPesquisaProduto());
                            getModelProduto().setProdutoObservableList(getProdutoObservableList());
                            getModelProduto().setProdutoFilteredList(getProdutoFilteredList());
                            getModelProduto().escutaLista();

                            getModelEntradaProdutoProduto().setTp(getTp());
                            getModelEntradaProdutoProduto().setTxtPesquisaProduto(getTxtPesquisaProduto());
                            getModelEntradaProdutoProduto().setTtvItensNfe(getTtvItensNfe());
                            getModelEntradaProdutoProduto().setEntradaProdutoProduto(getEntradaProdutoProduto());
                            getModelEntradaProdutoProduto().setEntradaProdutoProdutoObservableList(getEntradaProdutoProdutoObservableList());
                            getModelEntradaProdutoProduto().escutaLista();
                            break;

                        case "preencherTabela":
                            getModelProduto().preencherTabela();

                            getModelEntradaProdutoProduto().preencherTabela();
                            break;

                        case "preencherCombos":
                            ObservableList<Empresa> tmpEmpresas = FXCollections.observableArrayList(new EmpresaDAO().getAll(Empresa.class, null, null, null, null))
                                    .sorted(Comparator.comparing(Empresa::getRazao));
                            getCboNfeLojaDestino().setItems(
                                    tmpEmpresas.stream()
                                            .filter(loja -> (loja.isLojaSistema()))
                                            .collect(Collectors.toCollection(FXCollections::observableArrayList))
                            );
                            getCboNfeFornecedor().setItems(
                                    tmpEmpresas.stream()
                                            .filter(fornecedor -> (fornecedor.isFornecedor()))
                                            .collect(Collectors.toCollection(FXCollections::observableArrayList))
                            );
                            getCboCteTransportadora().setItems(
                                    tmpEmpresas.stream()
                                            .filter(transportadora -> (transportadora.isTransportadora()))
                                            .collect(Collectors.toCollection(FXCollections::observableArrayList))
                            );
                            getCboNfeModelo().setItems(NfeCteModelo.getList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
                            getCboCteModelo().setItems(NfeCteModelo.getList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
                            getCboCteSistuacaoTributaria().setItems(
                                    new FiscalFreteSituacaoTributariaDAO().getAll(FiscalFreteSituacaoTributaria.class, null, null, null, null)
                                            .stream().collect(Collectors.toCollection(FXCollections::observableArrayList))
                            );
                            ObservableList<FiscalTributosSefazAm> tmpImposto = new FiscalTributosSefazAmDAO().getAll(FiscalTributosSefazAm.class, null, null, null, null)
                                    .stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
                            getCboNfeFiscalTributo().setItems(tmpImposto);
                            getCboCteFiscalTributo().setItems(tmpImposto);
                            getCboCteTomadorServico().setItems(CteTomadorServico.getList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
                            break;
                    }
                }
                updateProgress(getListaTarefa().size(), getListaTarefa().size());
                return null;
            }
        };
        return voidTask;
    }


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

    private boolean teclaFuncaoDisponivel(KeyCode keyCode) {
        return getStatusBar().getDescricao().contains(keyCode.toString());
    }

//    private boolean validarEntradaProduto() {
//        if (validarEntradaNfe() && validarEntradaFiscalNfe() && validarEntradaCte() && validarEntradaFiscalCte()) {
//            guardarEntradaProduto();
//            return true;
//        }
//        return false;
//    }

    private boolean salvarEntradaProduto() {
        if (getEntradaProdutoProdutoObservableList().size() == 0) {
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F7));
            return false;
        }
        try {
            getEntradaProduto().setUsuarioCadastro(LogadoInf.getUserLog());
            getEntradaProduto().setSituacao(SituacaoEntrada.LANCADO);
            setEntradaProduto(getEntradaProdutoDAO().persiste(getEntradaProduto()));
            salvarEntradaEstoque();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

//    @SuppressWarnings("Duplicates")
//    private boolean validarEntradaNfe() {
//        boolean result = true;
//        String dado = "";
//        if (result)
//            if (!(result = (cboNfeLojaDestino.getSelectionModel().getSelectedItem() != null))) {
//                dado += "Loja de destino";
//                cboNfeLojaDestino.requestFocus();
//            }
//        if (result)
//            if (result = (txtNfeChave.getText().replaceAll("\\D", "").length() == 44)) {
//                EntradaProduto ent;
//                if ((ent = verificaExistente(txtNfeChave.getText(), getEntradaProduto().getId())) != null) {
//                    carregarEntradaExistente(ent, txtNfeChave.getText());
//                    txtNfeChave.requestFocus();
//                    return false;
//                }
//            }
//        if (result)
//            if (!(result = txtNfeNumero.getText().length() >= 1)) {
//                dado = "nfe número";
//                txtNfeNumero.requestFocus();
//            }
//        if (result)
//            if (!(result = txtNfeSerie.getText().length() >= 1)) {
//                dado = "nfe série";
//                txtNfeSerie.requestFocus();
//            }
//        if (result)
//            if (!(result = (cboNfeModelo.getSelectionModel().getSelectedItem() != null))) {
//                dado += "nfe modelo nfe";
//                cboNfeModelo.requestFocus();
//            }
//        if (result)
//            if (!(result = (cboNfeFornecedor.getSelectionModel().getSelectedItem() != null))) {
//                dado += "nfe fornecedor";
//                cboNfeFornecedor.requestFocus();
//            }
//        if (result)
//            if (Period.between(dtpNfeEmissao.getValue(), dtpNfeEntrada.getValue()).isNegative()
//                    || Period.between(dtpNfeEmissao.getValue(), LocalDate.now()).isNegative()) {
//                dado += "nfe data emissão";
//                dtpNfeEmissao.requestFocus();
//            }
//        if (result)
//            if (Period.between(dtpNfeEntrada.getValue(), LocalDate.now()).isNegative()) {
//                dado += "nfe data entrada";
//                dtpNfeEntrada.requestFocus();
//            }
//        if (!result) {
//            alertMensagem = new ServiceAlertMensagem();
//            alertMensagem.setCabecalho(String.format("Dados inválido"));
//            alertMensagem.setPromptText(String.format("%s, '%s' incompleto(a) ou invalido(a)",
//                    LogadoInf.getUserLog().getApelido(), dado));
//            alertMensagem.setStrIco("ic_atencao_triangulo_24dp.png");
//            alertMensagem.getRetornoAlert_OK();
//        } // else result = guardarEntradaProduto();
//        return result;
//    }
//
//    @SuppressWarnings("Duplicates")
//    private boolean validarEntradaFiscalNfe() {
//        boolean result = true;
//        String dado = "";
//        if (!tpnNfeFiscal.isExpanded()) {
//            ServiceCampoPersonalizado.fieldClear((AnchorPane) tpnNfeFiscal.getContent());
//            return true;
//        }
//        if (result)
//            if (!(result = txtNfeFiscalControle.getText().length() >= 1)) {
//                dado = "fiscal nfe número controle";
//                txtNfeFiscalControle.requestFocus();
//            }
//        if (result)
//            if (!(result = txtNfeFiscalOrigem.getText().length() >= 1)) {
//                dado = "fiscal nfe documento origem";
//                txtNfeFiscalOrigem.requestFocus();
//            }
//        if (result)
//            if (!(result = (cboNfeFiscalTributo.getSelectionModel().getSelectedItem() != null))) {
//                dado += "fiscal nfe tributo";
//                cboNfeFiscalTributo.requestFocus();
//            }
//        if (result)
//            if (!(result = Double.parseDouble(txtNfeFiscalVlrNFe.getText().replace(".", "").replace(",", ".")) > 0)) {
//                dado += "fiscal nfe vlr nfe";
//                txtNfeFiscalVlrNFe.requestFocus();
//            }
//        if (!result) {
//            alertMensagem = new ServiceAlertMensagem();
//            alertMensagem.setCabecalho(String.format("Dados inválido"));
//            alertMensagem.setPromptText(String.format("%s, '%s' incompleto(a) ou invalido(a)",
//                    LogadoInf.getUserLog().getApelido(), dado));
//            alertMensagem.setStrIco("ic_atencao_triangulo_24dp.png");
//            alertMensagem.getRetornoAlert_OK();
//        }// else result = guardarEntradaProduto();
//        return result;
//    }
//
//    @SuppressWarnings("Duplicates")
//    private boolean validarEntradaCte() {
//        boolean result = true;
//        String dado = "";
//        if (!tpnCteDetalhe.isExpanded()) {
//            ServiceCampoPersonalizado.fieldClear((AnchorPane) tpnCteDetalhe.getContent());
//            return true;
//        }
//        if (result)
//            if (result = (txtCteChave.getText().replaceAll("\\D", "").length() == 44)) {
//                EntradaProduto ent;
//                if ((ent = verificaExistente(txtCteChave.getText(), getEntradaProduto().getId())) != null) {
//                    carregarEntradaExistente(ent, txtCteChave.getText());
//                    txtCteChave.requestFocus();
//                    return false;
//                }
//            }
//        if (result)
//            if (!(result = (cboCteTomadorServico.getSelectionModel().getSelectedItem() != null))) {
//                dado += "Cte tomador serviço";
//                cboCteTomadorServico.requestFocus();
//            }
//        if (result)
//            if (!(result = txtCteNumero.getText().length() >= 1)) {
//                dado = "Cte número";
//                txtCteNumero.requestFocus();
//            }
//        if (result)
//            if (!(result = txtCteSerie.getText().length() >= 1)) {
//                dado = "Cte série";
//                txtCteSerie.requestFocus();
//            }
//        if (result)
//            if (!(result = (cboCteModelo.getSelectionModel().getSelectedItem() != null))) {
//                dado += "Cte modelo cte";
//                cboCteModelo.requestFocus();
//            }
//        if (result)
//            if (!(result = (cboCteSistuacaoTributaria.getSelectionModel().getSelectedItem() != null))) {
//                dado += "Cte situação tributária";
//                cboCteSistuacaoTributaria.requestFocus();
//            }
//        if (result)
//            if (!(result = (cboCteTransportadora.getSelectionModel().getSelectedItem() != null))) {
//                dado += "Cte transportadora";
//                cboCteTransportadora.requestFocus();
//            }
//        if (result)
//            if (Period.between(dtpCteEmissao.getValue(), LocalDate.now()).isNegative()) {
//                dado += "Cte data emissão";
//                dtpCteEmissao.requestFocus();
//            }
//        if (result)
//            if (!(result = Double.parseDouble(txtCteVlrCte.getText().replace(".", "").replace(",", ".")) > 0)) {
//                dado += "Cte vlr cte";
//                txtCteVlrCte.requestFocus();
//            }
//        if (result)
//            if (!(result = Integer.parseInt(txtCteQtdVolume.getText()) > 0)) {
//                dado += "Cte qtd volume";
//                txtCteQtdVolume.requestFocus();
//            }
//        if (result)
//            if (!(result = Double.parseDouble(txtCtePesoBruto.getText().replace(".", "").replace(",", ".")) > 0)) {
//                dado += "Cte peso bruto";
//                txtCtePesoBruto.requestFocus();
//            }
//        if (result)
//            if (!(result = Double.parseDouble(txtCteVlrBruto.getText().replace(".", "").replace(",", ".")) > 0)) {
//                dado += "Cte vlr bruto";
//                txtCteVlrBruto.requestFocus();
//            }
//        if (result)
//            if (!(result = Double.parseDouble(txtCteVlrImposto.getText().replace(".", "").replace(",", ".")) > 0)) {
//                dado += "Cte vlr imposto";
//                txtCteVlrImposto.requestFocus();
//            }
//        if (result)
//            if (!(result = Double.parseDouble(txtCteVlrLiquido.getText().replace(".", "").replace(",", ".")) > 0)) {
//                dado += "Cte vlr frete liquído";
//                txtCteVlrLiquido.requestFocus();
//            }
//
//        if (!result) {
//            alertMensagem = new ServiceAlertMensagem();
//            alertMensagem.setCabecalho(String.format("Dados inválido"));
//            alertMensagem.setPromptText(String.format("%s, '%s' incompleto(a) ou invalido(a)",
//                    LogadoInf.getUserLog().getApelido(), dado));
//            alertMensagem.setStrIco("ic_atencao_triangulo_24dp.png");
//            alertMensagem.getRetornoAlert_OK();
//        } // else result = guardarEntradaProduto();
//        return result;
//    }
//
//    @SuppressWarnings("Duplicates")
//    private boolean validarEntradaFiscalCte() {
//        boolean result = true;
//        String dado = "";
//        if (!tpnCteFiscal.isExpanded()) {
//            ServiceCampoPersonalizado.fieldClear((AnchorPane) tpnCteFiscal.getContent());
//            return true;
//        }
//        if (result)
//            if (!(result = txtCteFiscalControle.getText().length() >= 1)) {
//                dado = "fiscal cte número controle";
//                txtCteFiscalControle.requestFocus();
//            }
//        if (result)
//            if (!(result = txtCteFiscalOrigem.getText().length() >= 1)) {
//                dado = "fiscal cte documento origem";
//                txtCteFiscalOrigem.requestFocus();
//            }
//        if (result)
//            if (!(result = (cboCteFiscalTributo.getSelectionModel().getSelectedItem() != null))) {
//                dado += "fiscal cte tributo";
//                cboCteFiscalTributo.requestFocus();
//            }
//        if (result)
//            if (!(result = Double.parseDouble(txtCteFiscalVlrCte.getText().replace(".", "").replace(",", ".")) > 0)) {
//                dado += "fiscal cte vlr cte";
//                txtCteFiscalVlrCte.requestFocus();
//            }
//        if (!result) {
//            alertMensagem = new ServiceAlertMensagem();
//            alertMensagem.setCabecalho(String.format("Dados inválido"));
//            alertMensagem.setPromptText(String.format("%s, '%s' incompleto(a) ou invalido(a)",
//                    LogadoInf.getUserLog().getApelido(), dado));
//            alertMensagem.setStrIco("ic_atencao_triangulo_24dp.png");
//            alertMensagem.getRetornoAlert_OK();
//        }// else result = guardarEntradaProduto();
//        return result;
//    }

    private boolean jaExiste(String busca) {
        String strBusca = busca.toLowerCase().trim().replaceAll("\\D", "");
        EntradaProduto entradaBusca;
        if ((entradaBusca = getEntradaProdutoObservableList().stream()
                .filter(entrada ->
                        (entrada.getEntradaNfe() != null && entrada.getEntradaNfe().getChave().equals(strBusca)) ||
                                (entrada.getEntradaCte() != null && entrada.getEntradaCte().getChave().equals(strBusca))
                ).findFirst().orElse(null)) == null)
            return false;
        return carregarEntradaExistente(entradaBusca, busca);
    }

    private boolean carregarEntradaExistente(EntradaProduto entradaProduto, String chave) {
        setAlertMensagem(new ServiceAlertMensagem());
        getAlertMensagem().setCabecalho("Informação duplicada");
        getAlertMensagem().setPromptText(String.format("%s, a chave: [%s]\njá está cadastrado no sistema!\nDeseja carregar ela?",
                LogadoInf.getUserLog().getApelido(),
                chave));
        getAlertMensagem().setStrIco("ic_atencao_triangulo_24dp");
        if (getAlertMensagem().getRetornoAlert_Yes_No().get() == ButtonType.NO) return false;
        setEntradaProduto(entradaProduto);
        return true;
    }

    private boolean guardarEntradaProduto() {
        try {
            getEntradaProduto().setLoja(getCboNfeLojaDestino().getSelectionModel().getSelectedItem());

            getEntradaProduto().getEntradaNfe().setChave(getTxtNfeChave().getText());
            getEntradaProduto().getEntradaNfe().setNumero(getTxtNfeNumero().getText());
            getEntradaProduto().getEntradaNfe().setSerie(getTxtNfeSerie().getText());
            getEntradaProduto().getEntradaNfe().setModelo(getCboNfeModelo().getSelectionModel().getSelectedItem());
            getEntradaProduto().getEntradaNfe().setEmissor(getCboNfeFornecedor().getSelectionModel().getSelectedItem());
            getEntradaProduto().getEntradaNfe().setDataEmissao(getDtpNfeEmissao().getValue());
            getEntradaProduto().getEntradaNfe().setDataEntrada(getDtpNfeEntrada().getValue());

            if (getTpnNfeFiscal().isExpanded()) {
                if (getEntradaProduto().getEntradaNfe().getEntradaFiscal() == null)
                    getEntradaProduto().getEntradaNfe().setEntradaFiscal(new EntradaFiscal());
                getEntradaProduto().getEntradaNfe().getEntradaFiscal().setControle(getTxtNfeFiscalControle().getText());
                getEntradaProduto().getEntradaNfe().getEntradaFiscal().setDocOrigem(getTxtNfeFiscalOrigem().getText());
                getEntradaProduto().getEntradaNfe().getEntradaFiscal().setTributosSefazAm(getCboNfeFiscalTributo().getSelectionModel().getSelectedItem());
                getEntradaProduto().getEntradaNfe().getEntradaFiscal().setVlrDocumento(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrNFe().getText(), 2));
                getEntradaProduto().getEntradaNfe().getEntradaFiscal().setVlrTributo(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTributo().getText(), 2));
                getEntradaProduto().getEntradaNfe().getEntradaFiscal().setVlrMulta(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrMulta().getText(), 2));
                getEntradaProduto().getEntradaNfe().getEntradaFiscal().setVlrJuros(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrJuros().getText(), 2));
                getEntradaProduto().getEntradaNfe().getEntradaFiscal().setVlrTaxa(ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTaxa().getText(), 2));
            } else {
                getEntradaProduto().getEntradaNfe().setEntradaFiscal(null);
            }

            if (getTpnCteDetalhe().isExpanded()) {
                if (getEntradaProduto().getEntradaCte() == null)
                    getEntradaProduto().setEntradaCte(new EntradaCte());
                getEntradaProduto().getEntradaCte().setChave(getTxtCteChave().getText());
                getEntradaProduto().getEntradaCte().setTomadorServico(getCboCteTomadorServico().getSelectionModel().getSelectedItem());
                getEntradaProduto().getEntradaCte().setNumero(getTxtCteNumero().getText());
                getEntradaProduto().getEntradaCte().setSerie(getTxtCteSerie().getText());
                getEntradaProduto().getEntradaCte().setModelo(getCboCteModelo().getSelectionModel().getSelectedItem());
                getEntradaProduto().getEntradaCte().setSituacaoTributaria(getCboCteSistuacaoTributaria().getSelectionModel().getSelectedItem());
                getEntradaProduto().getEntradaCte().setEmissor(getCboCteTransportadora().getSelectionModel().getSelectedItem());
                getEntradaProduto().getEntradaCte().setDataEmissao(getDtpCteEmissao().getValue());
                getEntradaProduto().getEntradaCte().setVlrCte(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrCte().getText(), 2));
                getEntradaProduto().getEntradaCte().setQtdVolume(Integer.parseInt(getTxtCteQtdVolume().getText()));
                getEntradaProduto().getEntradaCte().setPesoBruto(ServiceMascara.getBigDecimalFromTextField(getTxtCtePesoBruto().getText(), 2));
                getEntradaProduto().getEntradaCte().setVlrFreteBruto(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrBruto().getText(), 2));
                getEntradaProduto().getEntradaCte().setVlrTaxas(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrTaxa().getText(), 2));
                getEntradaProduto().getEntradaCte().setVlrColeta(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrColeta().getText(), 2));
                getEntradaProduto().getEntradaCte().setVlrImpostoFrete(ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrImposto().getText(), 2));

                if (getTpnCteFiscal().isExpanded()) {
                    if (getEntradaProduto().getEntradaCte().getEntradaFiscal() == null)
                        getEntradaProduto().getEntradaCte().setEntradaFiscal(new EntradaFiscal());
                    getEntradaProduto().getEntradaCte().getEntradaFiscal().setControle(getTxtCteFiscalControle().getText());
                    getEntradaProduto().getEntradaCte().getEntradaFiscal().setDocOrigem(getTxtCteFiscalOrigem().getText());
                    getEntradaProduto().getEntradaCte().getEntradaFiscal().setTributosSefazAm(getCboCteFiscalTributo().getSelectionModel().getSelectedItem());
                    getEntradaProduto().getEntradaCte().getEntradaFiscal().setVlrDocumento(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrCte().getText(), 2));
                    getEntradaProduto().getEntradaCte().getEntradaFiscal().setVlrTributo(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTributo().getText(), 2));
                    getEntradaProduto().getEntradaCte().getEntradaFiscal().setVlrMulta(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrMulta().getText(), 2));
                    getEntradaProduto().getEntradaCte().getEntradaFiscal().setVlrJuros(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrJuros().getText(), 2));
                    getEntradaProduto().getEntradaCte().getEntradaFiscal().setVlrTaxa(ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTaxa().getText(), 2));
                } else {
                    getEntradaProduto().getEntradaCte().setEntradaFiscal(null);
                }
            } else {
                getEntradaProduto().setEntradaCte(null);
            }
            getEntradaProduto().setEntradaProdutoProdutoList(getEntradaProdutoProdutoObservableList());
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
     * voids
     */
    @SuppressWarnings("Duplicates")

    private void closeTabColapsed() {
        getTpnNfeFiscal().setExpanded(false);
        getTpnCteFiscal().setExpanded(false);
        getTpnCteDetalhe().setExpanded(false);
    }

    private void limparCampos() {
        getTxtPesquisaProduto().setText("");
        ServiceCampoPersonalizado.fieldClear(getPainelViewEntradaProduto());
        closeTabColapsed();
    }

//    private void atualizaTotaisFreteImposto() {
//        BigDecimal impostoNfe = ServiceMascara.getBigDecimalFromTextField(txtNfeFiscalVlrTotal.getText(), 2);
//        BigDecimal impostoCte = ServiceMascara.getBigDecimalFromTextField(txtCteFiscalVlrTotal.getText(), 2);
//        modelEntradaProdutoProduto.setTotalImpostoEntrada(impostoNfe.add(impostoCte));
//        BigDecimal frete = ServiceMascara.getBigDecimalFromTextField(txtCteVlrLiquido.getText(), 2);
//        modelEntradaProdutoProduto.setTotalFrete(frete);
//    }

//    private void salvarEntradaTemp() {
//        try {
//            if (orcamento == null)
//                orcamento = new OrcamentoEntrada();
//            orcamento.setOrcamento(new ObjectMapper().writeValueAsString(getEntradaProduto()));
//            orcamento = entradaTempDAO.persiste(orcamento);
//            setSituacaoEntrada(SituacaoEntrada.DIGITACAO);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            entradaTempDAO.getEntityManager().getTransaction().rollback();
//        }
//    }

    private void salvarEntradaEstoque() {
        BigDecimal qtdTotal = ServiceMascara.getBigDecimalFromTextField(getLblQtdTotal().getText(), 0);

        BigDecimal freteBruto = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrBruto().getText(), 2);
        BigDecimal freteColeta = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrColeta().getText(), 2);
        BigDecimal freteLiquido = ServiceMascara.getBigDecimalFromTextField(getTxtCteVlrLiquido().getText(), 2);
        BigDecimal freteLiquidoUnd = freteLiquido.divide(qtdTotal, 2, RoundingMode.HALF_UP);

        BigDecimal impostoFrete = freteLiquido.subtract(freteBruto.add(freteColeta));
        BigDecimal impostoFreteUnd = impostoFrete.divide(qtdTotal, 2, RoundingMode.HALF_UP);

        BigDecimal impostoFreteEntrada = ServiceMascara.getBigDecimalFromTextField(getTxtCteFiscalVlrTotal().getText(), 2);
        BigDecimal impostoFreteEntradaUnd = impostoFreteEntrada.divide(qtdTotal, 2, RoundingMode.HALF_UP);

        BigDecimal impostoEntrada = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTotal().getText(), 2);
        BigDecimal impostoEntradaUnd = impostoEntrada.divide(qtdTotal, 2, RoundingMode.HALF_UP);

        BigDecimal totImposto = ServiceMascara.getBigDecimalFromTextField(getLblTotalImposto().getText(), 2);
        BigDecimal imposto = totImposto.subtract(impostoEntrada.add(impostoFreteEntrada));
        BigDecimal impostoUnd = imposto.divide(qtdTotal, 2, RoundingMode.HALF_UP);


        try {
            getEntradaProduto().getEntradaProdutoProdutoList().stream()
                    .forEach(entradaProdutoProduto -> {
                        BigDecimal vlrDesc = entradaProdutoProduto.getVlrDesconto().divide(BigDecimal.valueOf(entradaProdutoProduto.getQtd()), 2, RoundingMode.HALF_UP);
                        BigDecimal vlrLiquido = entradaProdutoProduto.getVlrFabrica().add(freteLiquidoUnd)
                                .add(impostoFreteEntradaUnd).add(impostoEntradaUnd).add(impostoUnd)
                                .subtract(vlrDesc);
                        ProdutoEstoque estoque = new ProdutoEstoque();
                        estoque.setQtd(entradaProdutoProduto.getQtd());
                        estoque.setLote(entradaProdutoProduto.getLote());
                        estoque.setValidade(entradaProdutoProduto.getValidade());
                        estoque.setVlrBruto(entradaProdutoProduto.getVlrFabrica());
                        estoque.setVlrFreteImposto(impostoFreteUnd.setScale(2, RoundingMode.HALF_UP));
                        estoque.setVlrFreteImpostoEntrada(impostoFreteEntradaUnd.setScale(2, RoundingMode.HALF_UP));
                        estoque.setVlrFreteLiquido(freteLiquidoUnd.setScale(2, RoundingMode.HALF_UP));
                        estoque.setVlrImpostoEntrada(impostoEntradaUnd.setScale(2, RoundingMode.HALF_UP));
                        estoque.setVlrImpostoProduto(impostoUnd.setScale(2, RoundingMode.HALF_UP));
                        estoque.setVlrLiquido(vlrLiquido.setScale(2, RoundingMode.HALF_UP));
                        estoque.setDocumentoEntrada(txtNfeNumero.getText().replaceAll("\\D", ""));
                        estoque.setDocumentoEntradaChaveNfe(txtNfeChave.getText().replaceAll("\\D", ""));
                        estoque.setUsuarioCadastro(LogadoInf.getUserLog());

                        Produto produto = entradaProdutoProduto.getProduto();

                        if (estoque.vlrBrutoProperty().get().setScale(2).compareTo(produto.precoFabricaProperty().get().setScale(2)) != 0) {
                            BigDecimal margem = produto.getPrecoConsumidor().divide(produto.getPrecoFabrica(), 2, RoundingMode.HALF_UP).subtract(BigDecimal.ONE).multiply(new BigDecimal("100"));
                            produto.setPrecoFabrica(estoque.vlrBrutoProperty().get().setScale(2, RoundingMode.HALF_UP));
                            BigDecimal newConsumidor = margem.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP).add(BigDecimal.ONE).multiply(produto.getPrecoFabrica());
                            produto.setPrecoConsumidor(newConsumidor.setScale(2, RoundingMode.HALF_UP));
                        }
                        produto.setUltFrete(estoque.vlrFreteLiquidoProperty().get().setScale(2, RoundingMode.HALF_UP));
                        BigDecimal ultImposto = estoque.vlrFreteImpostoEntradaProperty().get()
                                .add(estoque.vlrImpostoEntradaProperty().get())
                                .add(estoque.vlrImpostoProdutoProperty().get());
                        produto.setUltImpostoSefaz(ultImposto.setScale(2, RoundingMode.HALF_UP));

                        produto.getProdutoEstoqueList().add(estoque);

                        //produtoDAO.persiste(produto);
                    });
            getEntradaProduto().setSituacao(SituacaoEntrada.INCLUIDO);
            setEntradaProduto(getEntradaProdutoDAO().persiste(getEntradaProduto()));
            setStatusBar(StatusBarEntradaProduto.DIGITACAO);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            getEntradaProdutoDAO().getEntityManager().getTransaction().rollback();
        }

    }

    private void buscaEntrada(String chave) {
        if (!jaExiste(chave)) return;
        setStatusBar(StatusBarEntradaProduto.toEnum(getEntradaProduto().getSituacao().getCod()));
        exibirEntrada();
    }

    @SuppressWarnings("Duplicates")
    private void exibirEntrada() {
        cboNfeLojaDestino.getSelectionModel().select(
                cboNfeLojaDestino.getItems().stream()
                        .filter(loja -> loja.getId() == getEntradaProduto().getLoja().getId())
                        .findFirst().orElse(null)
        );

        if (getEntradaProduto().getEntradaNfe() != null) {
            txtNfeChave.setText(getEntradaProduto().getEntradaNfe().getChave());
            cboNfeFornecedor.getSelectionModel().select(
                    cboNfeFornecedor.getItems().stream()
                            .filter(loja -> loja.getId() == entradaProduto.getLoja().getId())
                            .findFirst().orElse(null)
            );
            txtNfeNumero.setText(getEntradaProduto().getEntradaNfe().getNumero());
            txtNfeSerie.setText(getEntradaProduto().getEntradaNfe().getSerie());
            cboNfeModelo.getSelectionModel().select(
                    cboNfeModelo.getItems().stream()
                            .filter(modelNfe -> modelNfe.getCod() == getEntradaProduto().getEntradaNfe().getModelo().getCod())
                            .findFirst().orElse(null)
            );
            cboNfeFornecedor.getSelectionModel().select(
                    cboNfeFornecedor.getItems().stream()
                            .filter(fornecedor -> fornecedor.getId() == getEntradaProduto().getEntradaNfe().getEmissor().getId())
                            .findFirst().orElse(null)
            );
            dtpNfeEmissao.setValue(getEntradaProduto().getEntradaNfe().getDataEmissao());
            dtpNfeEntrada.setValue(getEntradaProduto().getEntradaNfe().getDataEntrada());

            if (getEntradaProduto().getEntradaNfe().getEntradaFiscal() != null) {
                tpnNfeFiscal.setExpanded(true);
                txtNfeFiscalControle.setText(getEntradaProduto().getEntradaNfe().getEntradaFiscal().getControle());
                txtNfeFiscalOrigem.setText(getEntradaProduto().getEntradaNfe().getEntradaFiscal().getDocOrigem());
                cboNfeFiscalTributo.getSelectionModel().select(
                        cboNfeFiscalTributo.getItems().stream()
                                .filter(fiscalNfe -> fiscalNfe.getId() == getEntradaProduto().getEntradaNfe().getEntradaFiscal().getTributosSefazAm().getId())
                                .findFirst().orElse(null)
                );
                txtNfeFiscalVlrNFe.setText(getEntradaProduto().getEntradaNfe().getEntradaFiscal().getVlrDocumento().toString());
                txtNfeFiscalVlrTributo.setText(getEntradaProduto().getEntradaNfe().getEntradaFiscal().getVlrTributo().toString());
                txtNfeFiscalVlrMulta.setText(getEntradaProduto().getEntradaNfe().getEntradaFiscal().getVlrMulta().toString());
                txtNfeFiscalVlrJuros.setText(getEntradaProduto().getEntradaNfe().getEntradaFiscal().getVlrJuros().toString());
                txtNfeFiscalVlrTaxa.setText(getEntradaProduto().getEntradaNfe().getEntradaFiscal().getVlrTaxa().toString());
            }
        }
        if (getEntradaProduto().getEntradaCte() != null) {
            tpnCteDetalhe.setExpanded(true);

            txtCteChave.setText(getEntradaProduto().getEntradaCte().getChave());
            cboCteTomadorServico.getSelectionModel().select(
                    cboCteTomadorServico.getItems().stream()
                            .filter(tomadorServico -> tomadorServico.getCod() == getEntradaProduto().getEntradaCte().getTomadorServico().getCod())
                            .findFirst().orElse(null)
            );
            txtCteNumero.setText(getEntradaProduto().getEntradaCte().getNumero());
            txtCteSerie.setText(getEntradaProduto().getEntradaCte().getSerie());
            cboCteModelo.getSelectionModel().select(
                    cboCteModelo.getItems().stream()
                            .filter(modelCte -> modelCte.getCod() == getEntradaProduto().getEntradaCte().getModelo().getCod())
                            .findFirst().orElse(null)
            );
            dtpCteEmissao.setValue(getEntradaProduto().getEntradaCte().getDataEmissao());
            cboCteTransportadora.getSelectionModel().select(
                    cboCteTransportadora.getItems().stream()
                            .filter(transportadora -> transportadora.getId() == getEntradaProduto().getEntradaCte().getEmissor().getId())
                            .findFirst().orElse(null)
            );
            if (getEntradaProduto().getEntradaCte().getSituacaoTributaria() == null)
                cboCteSistuacaoTributaria.getSelectionModel().select(-1);
            else
                cboCteSistuacaoTributaria.getSelectionModel().select(
                        cboCteSistuacaoTributaria.getItems().stream()
                                .filter(situacaoTributaria -> situacaoTributaria.getId() == getEntradaProduto().getEntradaCte().getSituacaoTributaria().getId())
                                .findFirst().orElse(null)
                );

            txtCteVlrCte.setText(getEntradaProduto().getEntradaCte().getVlrCte().toString());
            txtCteQtdVolume.setText(String.valueOf(getEntradaProduto().getEntradaCte().getQtdVolume()));
            txtCtePesoBruto.setText(getEntradaProduto().getEntradaCte().getPesoBruto().toString());
            txtCteVlrBruto.setText(getEntradaProduto().getEntradaCte().getVlrFreteBruto().toString());
            txtCteVlrTaxa.setText(getEntradaProduto().getEntradaCte().getVlrTaxas().toString());
            txtCteVlrColeta.setText(getEntradaProduto().getEntradaCte().getVlrColeta().toString());
            txtCteVlrImposto.setText(getEntradaProduto().getEntradaCte().getVlrImpostoFrete().toString());

            if (getEntradaProduto().getEntradaCte().getEntradaFiscal() != null) {
                tpnCteFiscal.setExpanded(true);

                txtCteFiscalControle.setText(getEntradaProduto().getEntradaCte().getEntradaFiscal().getControle());
                txtCteFiscalOrigem.setText(getEntradaProduto().getEntradaCte().getEntradaFiscal().getDocOrigem());
                cboCteFiscalTributo.getSelectionModel().select(
                        cboCteFiscalTributo.getItems().stream()
                                .filter(fiscalCte -> fiscalCte.getId() == getEntradaProduto().getEntradaCte().getEntradaFiscal().getTributosSefazAm().getId())
                                .findFirst().orElse(null)
                );
                txtCteFiscalVlrCte.setText(getEntradaProduto().getEntradaCte().getEntradaFiscal().getVlrDocumento().toString());
                txtCteFiscalVlrTributo.setText(getEntradaProduto().getEntradaCte().getEntradaFiscal().getVlrTributo().toString());
                txtCteFiscalVlrMulta.setText(getEntradaProduto().getEntradaCte().getEntradaFiscal().getVlrMulta().toString());
                txtCteFiscalVlrJuros.setText(getEntradaProduto().getEntradaCte().getEntradaFiscal().getVlrJuros().toString());
                txtCteFiscalVlrTaxa.setText(getEntradaProduto().getEntradaCte().getEntradaFiscal().getVlrTaxa().toString());
            }
        }

        modelEntradaProdutoProduto.getEntradaProdutoProdutoObservableList().clear();
        entradaProduto.getEntradaProdutoProdutoList().stream()
                .forEach(entradaProdutoProduto ->
                        modelEntradaProdutoProduto.getEntradaProdutoProdutoObservableList().add(entradaProdutoProduto)
                );
    }


    private void addXmlNfe(File file) {
        if (!file.getName().toLowerCase().contains("nfe")) return;
        TNfeProc nfeProc = null;
        try {
            nfeProc = ServiceXmlUtil.xmlToObject(ServiceXmlUtil.leXml(new FileInputStream(file)), TNfeProc.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (nfeProc == null) return;
        txtNfeChave.setText(nfeProc.getNFe().getInfNFe().getId().replaceAll("\\D", ""));
        txtNfeNumero.setText(nfeProc.getNFe().getInfNFe().getIde().getNNF());
        txtNfeSerie.setText(nfeProc.getNFe().getInfNFe().getIde().getSerie());

        txtNfeFiscalVlrNFe.setText(nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF());

        TNfeProc finalNfeProc = nfeProc;
        cboNfeModelo.getSelectionModel().select(
                cboNfeModelo.getItems().stream()
                        .filter(modeloNfeCte -> modeloNfeCte.getDescricao().equals(finalNfeProc.getNFe().getInfNFe().getIde().getMod()))
                        .findFirst().orElse(null)
        );
        cboNfeLojaDestino.getSelectionModel().select(
                cboNfeLojaDestino.getItems().stream()
                        .filter(loja -> loja.getCnpj().equals(finalNfeProc.getNFe().getInfNFe().getDest().getCNPJ()))
                        .findFirst().orElse(null)
        );
        cboNfeFornecedor.getSelectionModel().select(
                cboNfeFornecedor.getItems().stream()
                        .filter(fornecedor -> fornecedor.getCnpj().equals(finalNfeProc.getNFe().getInfNFe().getEmit().getCNPJ()))
                        .findFirst().orElse(null)
        );
        dtpNfeEmissao.setValue(LocalDate.parse(nfeProc.getNFe().getInfNFe().getIde().getDhEmi(), DTF_NFE_TO_LOCAL_DATE));
        dtpNfeEntrada.setValue(LocalDate.now());
    }

    private void addXmlCte(File file) {
        if (!file.getName().toLowerCase().contains("cte")) return;
        CteProc cteProc = null;
        try {
            cteProc = ServiceXmlUtil.xmlToObject(ServiceXmlUtil.leXml(new FileInputStream(file)), CteProc.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (cteProc == null) return;

        txtCteChave.setText(cteProc.getCTe().getInfCte().getId().replaceAll("\\D", ""));
        txtCteNumero.setText(cteProc.getCTe().getInfCte().getIde().getNCT());
        txtCteSerie.setText(cteProc.getCTe().getInfCte().getIde().getSerie());

        txtCteVlrCte.setText(cteProc.getCTe().getInfCte().getVPrest().getVTPrest());
        txtCteVlrImposto.setText(cteProc.getCTe().getInfCte().getImp().getICMS().getICMS00().getVICMS());

        for (br.inf.portalfiscal.xsd.cte.procCTe.TCTe.InfCte.InfCTeNorm.InfCarga.InfQ infQ : cteProc.getCTe().getInfCte().getInfCTeNorm().getInfCarga().getInfQ())
            switch (infQ.getTpMed().toLowerCase()) {
                case "volume":
                case "volumes":
                    txtCteQtdVolume.setText(infQ.getQCarga());
                    break;
                case "peso bruto":
                    txtCtePesoBruto.setText(BigDecimal.valueOf(Double.parseDouble(infQ.getQCarga())).setScale(2).toString());
            }

        double tmpTaxas = 0.;
        for (br.inf.portalfiscal.xsd.cte.procCTe.TCTe.InfCte.VPrest.Comp comp : cteProc.getCTe().getInfCte().getVPrest().getComp())
            if (comp.getXNome().toLowerCase().contains("peso"))
                txtCteVlrBruto.setText(comp.getVComp());
            else if (comp.getXNome().toLowerCase().contains("coleta"))
                txtCteVlrColeta.setText(comp.getVComp());
            else
                tmpTaxas += Double.parseDouble(comp.getVComp());
        txtCteVlrTaxa.setText(BigDecimal.valueOf(tmpTaxas).setScale(2).toString());

        txtCteVlrLiquido.setText(cteProc.getCTe().getInfCte().getVPrest().getVTPrest());

        txtCteFiscalVlrCte.setText(cteProc.getCTe().getInfCte().getVPrest().getVTPrest());

        CteProc finalCteProc = cteProc;
        cboCteTomadorServico.getSelectionModel().select(
                cboCteTomadorServico.getItems().stream()
                        .filter(tomadorServico -> tomadorServico.getCod() == Integer.valueOf(finalCteProc.getCTe().getInfCte().getIde().getToma3().getToma()))
                        .findFirst().orElse(null)
        );
        cboCteModelo.getSelectionModel().select(
                cboCteModelo.getItems().stream()
                        .filter(modeloNfeCte -> modeloNfeCte.getDescricao().equals(finalCteProc.getCTe().getInfCte().getIde().getMod()))
                        .findFirst().orElse(null)
        );
        cboCteTransportadora.getSelectionModel().select(
                cboCteTransportadora.getItems().stream()
                        .filter(transportadora -> transportadora.getCnpj().equals(finalCteProc.getCTe().getInfCte().getEmit().getCNPJ()))
                        .findFirst().orElse(null)
        );
        cboCteSistuacaoTributaria.getSelectionModel().select(
                cboCteSistuacaoTributaria.getItems().stream()
                        .filter(situacaoTributaria -> situacaoTributaria.getId() == Integer.valueOf(finalCteProc.getCTe().getInfCte().getImp().getICMS().getICMS00().getCST()))
                        .findFirst().orElse(null)
        );
        dtpCteEmissao.setValue(LocalDate.parse(cteProc.getCTe().getInfCte().getIde().getDhEmi(), DTF_NFE_TO_LOCAL_DATE));

        if (txtNfeChave.getText().equals("")) {
            File filetmp = null;
            for (TCTe.InfCte.InfCTeNorm.InfDoc.InfNFe infNFe : cteProc.getCTe().getInfCte().getInfCTeNorm().getInfDoc().getInfNFe())
                if ((filetmp = new File(file.getParent() + "/" + infNFe.getChave() + "-nfe.xml")).exists())
                    addXmlNfe(filetmp);
        }
    }

    @SuppressWarnings("Duplicates")

    private void escutaTitledTab() {
        getTpnNfeFiscal().expandedProperty().addListener((ov, o, n) -> {
            int diff = 40;
            if (!n) diff = (diff * (-1));
            getTpnEntradaNfe().setPrefHeight(getTpnEntradaNfe().getPrefHeight() + (diff * 1));
            getTpnNfeDetalhe().setPrefHeight(getTpnNfeDetalhe().getPrefHeight() + (diff * 1));
            getTpnCteDetalhe().setLayoutY(getTpnCteDetalhe().getLayoutY() + (diff * 1));
            getTpnItensTotaisNfe().setLayoutY(getTpnItensTotaisNfe().getLayoutY() + (diff * 1));
            getTpnItensTotaisNfe().setPrefHeight(getTpnItensTotaisNfe().getPrefHeight() + (diff * -1));
            getTpnItensNfe().setPrefHeight(getTpnItensNfe().getPrefHeight() + (diff * -1));
            getTtvItensNfe().setPrefHeight(getTtvItensNfe().getPrefHeight() + (diff * -1));
            if (n) {
                getTxtNfeFiscalControle().requestFocus();
            } else {
                getTxtNfeChave().requestFocus();
                ServiceCampoPersonalizado.fieldClear((AnchorPane) getTpnNfeFiscal().getContent());
            }
        });

        getTpnCteDetalhe().expandedProperty().addListener((ov, o, n) -> {
            int diff = 100;
            if (!n) {
                getTpnCteFiscal().setExpanded(false);
                diff = (diff * (-1));
            }
            getTpnEntradaNfe().setPrefHeight(getTpnEntradaNfe().getPrefHeight() + (diff * 1));
            getTpnItensTotaisNfe().setLayoutY(getTpnItensTotaisNfe().getLayoutY() + (diff * 1));
            getTpnItensTotaisNfe().setPrefHeight(getTpnItensTotaisNfe().getPrefHeight() + (diff * -1));
            getTpnItensNfe().setPrefHeight(getTpnItensNfe().getPrefHeight() + (diff * -1));
            getTtvItensNfe().setPrefHeight(getTtvItensNfe().getPrefHeight() + (diff * -1));
            if (n) {
                getTxtCteChave().requestFocus();
            } else {
                getTxtNfeChave().requestFocus();
                ServiceCampoPersonalizado.fieldClear((AnchorPane) getTpnCteDetalhe().getContent());
            }
        });

        getTpnCteFiscal().expandedProperty().addListener((ov, o, n) -> {
            int diff = 40;
            if (!n) diff = (diff * (-1));
            getTpnEntradaNfe().setPrefHeight(getTpnEntradaNfe().getPrefHeight() + (diff * 1));
            getTpnCteDetalhe().setPrefHeight(getTpnCteDetalhe().getPrefHeight() + (diff * 1));
            getTpnItensTotaisNfe().setLayoutY(getTpnItensTotaisNfe().getLayoutY() + (diff * 1));
            getTpnItensTotaisNfe().setPrefHeight(getTpnItensTotaisNfe().getPrefHeight() + (diff * -1));
            getTpnItensNfe().setPrefHeight(getTpnItensNfe().getPrefHeight() + (diff * -1));
            getTtvItensNfe().setPrefHeight(getTtvItensNfe().getPrefHeight() + (diff * -1));
            if (n) {
                getTxtCteFiscalVlrCte().setText(getTxtCteVlrLiquido().getText());
                getTxtCteFiscalControle().requestFocus();
            } else {
                getTxtCteChave().requestFocus();
                ServiceCampoPersonalizado.fieldClear((AnchorPane) getTpnCteFiscal().getContent());
            }
        });
    }

    @SuppressWarnings("Duplicates")
    private void resultVlrTributoNfe() {
        BigDecimal vlrNfe = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrNFe().getText(), 2);
        BigDecimal vlrTributo = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTributo().getText(), 2);
        BigDecimal vlrMulta = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrMulta().getText(), 2);
        BigDecimal vlrJuros = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrJuros().getText(), 2);
        BigDecimal vlrTaxa = ServiceMascara.getBigDecimalFromTextField(getTxtNfeFiscalVlrTaxa().getText(), 2);

        BigDecimal vlrTotal = vlrTributo.add(vlrMulta).add(vlrJuros).add(vlrTaxa);
        BigDecimal vlrPercentual = BigDecimal.valueOf((vlrTotal.doubleValue() / (vlrNfe.doubleValue() == 0. ? 0.00000000001 : vlrNfe.doubleValue())) * 100);

        getTxtNfeFiscalVlrTotal().setText(vlrTotal.setScale(2).toString());
        getTxtNfeFiscalVlrPercentual().setText(vlrPercentual.setScale(2, RoundingMode.HALF_UP).toString());
    }

    @SuppressWarnings("Duplicates")
    private void resultVlrTributoCte() {
        BigDecimal vlrCte = ServiceMascara.getBigDecimalFromTextField(txtCteFiscalVlrCte.getText(), 2);
        BigDecimal vlrTributo = ServiceMascara.getBigDecimalFromTextField(txtCteFiscalVlrTributo.getText(), 2);
        BigDecimal vlrMulta = ServiceMascara.getBigDecimalFromTextField(txtCteFiscalVlrMulta.getText(), 2);
        BigDecimal vlrJuros = ServiceMascara.getBigDecimalFromTextField(txtCteFiscalVlrJuros.getText(), 2);
        BigDecimal vlrTaxa = ServiceMascara.getBigDecimalFromTextField(txtCteFiscalVlrTaxa.getText(), 2);

        BigDecimal vlrTotal = vlrTributo.add(vlrMulta).add(vlrJuros).add(vlrTaxa);
        BigDecimal vlrPercentual = BigDecimal.valueOf((vlrTotal.doubleValue() / (vlrCte.doubleValue() == 0. ? 0.00000000001 : vlrCte.doubleValue())) * 100);

        txtCteFiscalVlrTotal.setText(vlrTotal.setScale(2).toString());
        txtCteFiscalVlrPercentual.setText(vlrPercentual.setScale(2, RoundingMode.HALF_UP).toString());
//        modelEntradaProdutoProduto.setTotalImpostoEntrada(
//                vlrTotal.add(ServiceMascara.getBigDecimalFromTextField(txtNfeFiscalVlrTotal.getText(), 2))
//        );
    }

    public void totalizaTabela() {
        setNumItens(getEntradaProdutoProdutoObservableList().stream()
                .collect(Collectors.groupingBy(EntradaProdutoProduto::getDescricao, Collectors.counting()))
                .size());
        setQtdItens(getEntradaProdutoProdutoObservableList().stream().collect(Collectors.summingInt(EntradaProdutoProduto::getQtd)));
        setQtdVolume(getEntradaProdutoProdutoObservableList().stream().collect(Collectors.summingInt(EntradaProdutoProduto::getVolume)));

        BigDecimal totBruto = getEntradaProdutoProdutoObservableList().stream().map(EntradaProdutoProduto::getVlrBruto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalBruto(totBruto.setScale(2));

        BigDecimal totImpostoItens = getEntradaProdutoProdutoObservableList().stream().map(EntradaProdutoProduto::getVlrImposto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalImpostoItens(totImpostoItens);

        BigDecimal totFrete = getTotalFrete();

        BigDecimal totDesconto = getEntradaProdutoProdutoObservableList().stream().map(EntradaProdutoProduto::getVlrDesconto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalDesconto(totDesconto.setScale(2));
    }

    /**
     * END
     * voids
     */

    /**
     * start
     * Getters e setters
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

    public JFXComboBox<Empresa> getCboNfeLojaDestino() {
        return cboNfeLojaDestino;
    }

    public void setCboNfeLojaDestino(JFXComboBox<Empresa> cboNfeLojaDestino) {
        this.cboNfeLojaDestino = cboNfeLojaDestino;
    }

    public JFXTextField getTxtNfeChave() {
        return txtNfeChave;
    }

    public void setTxtNfeChave(JFXTextField txtNfeChave) {
        this.txtNfeChave = txtNfeChave;
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

    public JFXComboBox<Empresa> getCboNfeFornecedor() {
        return cboNfeFornecedor;
    }

    public void setCboNfeFornecedor(JFXComboBox<Empresa> cboNfeFornecedor) {
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

    public TitledPane getTpnNfeFiscal() {
        return tpnNfeFiscal;
    }

    public void setTpnNfeFiscal(TitledPane tpnNfeFiscal) {
        this.tpnNfeFiscal = tpnNfeFiscal;
    }

    public JFXTextField getTxtNfeFiscalControle() {
        return txtNfeFiscalControle;
    }

    public void setTxtNfeFiscalControle(JFXTextField txtNfeFiscalControle) {
        this.txtNfeFiscalControle = txtNfeFiscalControle;
    }

    public JFXTextField getTxtNfeFiscalOrigem() {
        return txtNfeFiscalOrigem;
    }

    public void setTxtNfeFiscalOrigem(JFXTextField txtNfeFiscalOrigem) {
        this.txtNfeFiscalOrigem = txtNfeFiscalOrigem;
    }

    public JFXComboBox<FiscalTributosSefazAm> getCboNfeFiscalTributo() {
        return cboNfeFiscalTributo;
    }

    public void setCboNfeFiscalTributo(JFXComboBox<FiscalTributosSefazAm> cboNfeFiscalTributo) {
        this.cboNfeFiscalTributo = cboNfeFiscalTributo;
    }

    public JFXTextField getTxtNfeFiscalVlrNFe() {
        return txtNfeFiscalVlrNFe;
    }

    public void setTxtNfeFiscalVlrNFe(JFXTextField txtNfeFiscalVlrNFe) {
        this.txtNfeFiscalVlrNFe = txtNfeFiscalVlrNFe;
    }

    public JFXTextField getTxtNfeFiscalVlrTributo() {
        return txtNfeFiscalVlrTributo;
    }

    public void setTxtNfeFiscalVlrTributo(JFXTextField txtNfeFiscalVlrTributo) {
        this.txtNfeFiscalVlrTributo = txtNfeFiscalVlrTributo;
    }

    public JFXTextField getTxtNfeFiscalVlrMulta() {
        return txtNfeFiscalVlrMulta;
    }

    public void setTxtNfeFiscalVlrMulta(JFXTextField txtNfeFiscalVlrMulta) {
        this.txtNfeFiscalVlrMulta = txtNfeFiscalVlrMulta;
    }

    public JFXTextField getTxtNfeFiscalVlrJuros() {
        return txtNfeFiscalVlrJuros;
    }

    public void setTxtNfeFiscalVlrJuros(JFXTextField txtNfeFiscalVlrJuros) {
        this.txtNfeFiscalVlrJuros = txtNfeFiscalVlrJuros;
    }

    public JFXTextField getTxtNfeFiscalVlrTaxa() {
        return txtNfeFiscalVlrTaxa;
    }

    public void setTxtNfeFiscalVlrTaxa(JFXTextField txtNfeFiscalVlrTaxa) {
        this.txtNfeFiscalVlrTaxa = txtNfeFiscalVlrTaxa;
    }

    public JFXTextField getTxtNfeFiscalVlrTotal() {
        return txtNfeFiscalVlrTotal;
    }

    public void setTxtNfeFiscalVlrTotal(JFXTextField txtNfeFiscalVlrTotal) {
        this.txtNfeFiscalVlrTotal = txtNfeFiscalVlrTotal;
    }

    public JFXTextField getTxtNfeFiscalVlrPercentual() {
        return txtNfeFiscalVlrPercentual;
    }

    public void setTxtNfeFiscalVlrPercentual(JFXTextField txtNfeFiscalVlrPercentual) {
        this.txtNfeFiscalVlrPercentual = txtNfeFiscalVlrPercentual;
    }

    public TitledPane getTpnCteDetalhe() {
        return tpnCteDetalhe;
    }

    public void setTpnCteDetalhe(TitledPane tpnCteDetalhe) {
        this.tpnCteDetalhe = tpnCteDetalhe;
    }

    public JFXTextField getTxtCteChave() {
        return txtCteChave;
    }

    public void setTxtCteChave(JFXTextField txtCteChave) {
        this.txtCteChave = txtCteChave;
    }

    public JFXComboBox<CteTomadorServico> getCboCteTomadorServico() {
        return cboCteTomadorServico;
    }

    public void setCboCteTomadorServico(JFXComboBox<CteTomadorServico> cboCteTomadorServico) {
        this.cboCteTomadorServico = cboCteTomadorServico;
    }

    public JFXTextField getTxtCteNumero() {
        return txtCteNumero;
    }

    public void setTxtCteNumero(JFXTextField txtCteNumero) {
        this.txtCteNumero = txtCteNumero;
    }

    public JFXTextField getTxtCteSerie() {
        return txtCteSerie;
    }

    public void setTxtCteSerie(JFXTextField txtCteSerie) {
        this.txtCteSerie = txtCteSerie;
    }

    public JFXComboBox<NfeCteModelo> getCboCteModelo() {
        return cboCteModelo;
    }

    public void setCboCteModelo(JFXComboBox<NfeCteModelo> cboCteModelo) {
        this.cboCteModelo = cboCteModelo;
    }

    public JFXComboBox<FiscalFreteSituacaoTributaria> getCboCteSistuacaoTributaria() {
        return cboCteSistuacaoTributaria;
    }

    public void setCboCteSistuacaoTributaria(JFXComboBox<FiscalFreteSituacaoTributaria> cboCteSistuacaoTributaria) {
        this.cboCteSistuacaoTributaria = cboCteSistuacaoTributaria;
    }

    public JFXComboBox<Empresa> getCboCteTransportadora() {
        return cboCteTransportadora;
    }

    public void setCboCteTransportadora(JFXComboBox<Empresa> cboCteTransportadora) {
        this.cboCteTransportadora = cboCteTransportadora;
    }

    public DatePicker getDtpCteEmissao() {
        return dtpCteEmissao;
    }

    public void setDtpCteEmissao(DatePicker dtpCteEmissao) {
        this.dtpCteEmissao = dtpCteEmissao;
    }

    public JFXTextField getTxtCteVlrCte() {
        return txtCteVlrCte;
    }

    public void setTxtCteVlrCte(JFXTextField txtCteVlrCte) {
        this.txtCteVlrCte = txtCteVlrCte;
    }

    public JFXTextField getTxtCteQtdVolume() {
        return txtCteQtdVolume;
    }

    public void setTxtCteQtdVolume(JFXTextField txtCteQtdVolume) {
        this.txtCteQtdVolume = txtCteQtdVolume;
    }

    public JFXTextField getTxtCtePesoBruto() {
        return txtCtePesoBruto;
    }

    public void setTxtCtePesoBruto(JFXTextField txtCtePesoBruto) {
        this.txtCtePesoBruto = txtCtePesoBruto;
    }

    public JFXTextField getTxtCteVlrBruto() {
        return txtCteVlrBruto;
    }

    public void setTxtCteVlrBruto(JFXTextField txtCteVlrBruto) {
        this.txtCteVlrBruto = txtCteVlrBruto;
    }

    public JFXTextField getTxtCteVlrTaxa() {
        return txtCteVlrTaxa;
    }

    public void setTxtCteVlrTaxa(JFXTextField txtCteVlrTaxa) {
        this.txtCteVlrTaxa = txtCteVlrTaxa;
    }

    public JFXTextField getTxtCteVlrColeta() {
        return txtCteVlrColeta;
    }

    public void setTxtCteVlrColeta(JFXTextField txtCteVlrColeta) {
        this.txtCteVlrColeta = txtCteVlrColeta;
    }

    public JFXTextField getTxtCteVlrImposto() {
        return txtCteVlrImposto;
    }

    public void setTxtCteVlrImposto(JFXTextField txtCteVlrImposto) {
        this.txtCteVlrImposto = txtCteVlrImposto;
    }

    public JFXTextField getTxtCteVlrLiquido() {
        return txtCteVlrLiquido;
    }

    public void setTxtCteVlrLiquido(JFXTextField txtCteVlrLiquido) {
        this.txtCteVlrLiquido = txtCteVlrLiquido;
    }

    public TitledPane getTpnCteFiscal() {
        return tpnCteFiscal;
    }

    public void setTpnCteFiscal(TitledPane tpnCteFiscal) {
        this.tpnCteFiscal = tpnCteFiscal;
    }

    public JFXTextField getTxtCteFiscalControle() {
        return txtCteFiscalControle;
    }

    public void setTxtCteFiscalControle(JFXTextField txtCteFiscalControle) {
        this.txtCteFiscalControle = txtCteFiscalControle;
    }

    public JFXTextField getTxtCteFiscalOrigem() {
        return txtCteFiscalOrigem;
    }

    public void setTxtCteFiscalOrigem(JFXTextField txtCteFiscalOrigem) {
        this.txtCteFiscalOrigem = txtCteFiscalOrigem;
    }

    public JFXComboBox<FiscalTributosSefazAm> getCboCteFiscalTributo() {
        return cboCteFiscalTributo;
    }

    public void setCboCteFiscalTributo(JFXComboBox<FiscalTributosSefazAm> cboCteFiscalTributo) {
        this.cboCteFiscalTributo = cboCteFiscalTributo;
    }

    public JFXTextField getTxtCteFiscalVlrCte() {
        return txtCteFiscalVlrCte;
    }

    public void setTxtCteFiscalVlrCte(JFXTextField txtCteFiscalVlrCte) {
        this.txtCteFiscalVlrCte = txtCteFiscalVlrCte;
    }

    public JFXTextField getTxtCteFiscalVlrTributo() {
        return txtCteFiscalVlrTributo;
    }

    public void setTxtCteFiscalVlrTributo(JFXTextField txtCteFiscalVlrTributo) {
        this.txtCteFiscalVlrTributo = txtCteFiscalVlrTributo;
    }

    public JFXTextField getTxtCteFiscalVlrMulta() {
        return txtCteFiscalVlrMulta;
    }

    public void setTxtCteFiscalVlrMulta(JFXTextField txtCteFiscalVlrMulta) {
        this.txtCteFiscalVlrMulta = txtCteFiscalVlrMulta;
    }

    public JFXTextField getTxtCteFiscalVlrJuros() {
        return txtCteFiscalVlrJuros;
    }

    public void setTxtCteFiscalVlrJuros(JFXTextField txtCteFiscalVlrJuros) {
        this.txtCteFiscalVlrJuros = txtCteFiscalVlrJuros;
    }

    public JFXTextField getTxtCteFiscalVlrTaxa() {
        return txtCteFiscalVlrTaxa;
    }

    public void setTxtCteFiscalVlrTaxa(JFXTextField txtCteFiscalVlrTaxa) {
        this.txtCteFiscalVlrTaxa = txtCteFiscalVlrTaxa;
    }

    public JFXTextField getTxtCteFiscalVlrTotal() {
        return txtCteFiscalVlrTotal;
    }

    public void setTxtCteFiscalVlrTotal(JFXTextField txtCteFiscalVlrTotal) {
        this.txtCteFiscalVlrTotal = txtCteFiscalVlrTotal;
    }

    public JFXTextField getTxtCteFiscalVlrPercentual() {
        return txtCteFiscalVlrPercentual;
    }

    public void setTxtCteFiscalVlrPercentual(JFXTextField txtCteFiscalVlrPercentual) {
        this.txtCteFiscalVlrPercentual = txtCteFiscalVlrPercentual;
    }

    public TitledPane getTpnItensTotaisNfe() {
        return tpnItensTotaisNfe;
    }

    public void setTpnItensTotaisNfe(TitledPane tpnItensTotaisNfe) {
        this.tpnItensTotaisNfe = tpnItensTotaisNfe;
    }

    public TitledPane getTpnCadastroProduto() {
        return tpnCadastroProduto;
    }

    public void setTpnCadastroProduto(TitledPane tpnCadastroProduto) {
        this.tpnCadastroProduto = tpnCadastroProduto;
    }

    public JFXTextField getTxtPesquisaProduto() {
        return txtPesquisaProduto;
    }

    public void setTxtPesquisaProduto(JFXTextField txtPesquisaProduto) {
        this.txtPesquisaProduto = txtPesquisaProduto;
    }

    public TreeTableView<Produto> getTtvProduto() {
        return ttvProduto;
    }

    public void setTtvProduto(TreeTableView<Produto> ttvProduto) {
        this.ttvProduto = ttvProduto;
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

    public TitledPane getTpnItensNfe() {
        return tpnItensNfe;
    }

    public void setTpnItensNfe(TitledPane tpnItensNfe) {
        this.tpnItensNfe = tpnItensNfe;
    }

    public TableView<EntradaProdutoProduto> getTtvItensNfe() {
        return ttvItensNfe;
    }

    public void setTtvItensNfe(TableView<EntradaProdutoProduto> ttvItensNfe) {
        this.ttvItensNfe = ttvItensNfe;
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

    public Label getLabelTotalImposto() {
        return labelTotalImposto;
    }

    public void setLabelTotalImposto(Label labelTotalImposto) {
        this.labelTotalImposto = labelTotalImposto;
    }

    public Label getLblTotalImposto() {
        return lblTotalImposto;
    }

    public void setLblTotalImposto(Label lblTotalImposto) {
        this.lblTotalImposto = lblTotalImposto;
    }

    public Label getLabelTotalFrete() {
        return labelTotalFrete;
    }

    public void setLabelTotalFrete(Label labelTotalFrete) {
        this.labelTotalFrete = labelTotalFrete;
    }

    public Label getLblTotalFrete() {
        return lblTotalFrete;
    }

    public void setLblTotalFrete(Label lblTotalFrete) {
        this.lblTotalFrete = lblTotalFrete;
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

    public ServiceAlertMensagem getAlertMensagem() {
        return alertMensagem;
    }

    public void setAlertMensagem(ServiceAlertMensagem alertMensagem) {
        this.alertMensagem = alertMensagem;
    }

    public EventHandler getEventHandlerEntradaProduto() {
        return eventHandlerEntradaProduto;
    }

    public void setEventHandlerEntradaProduto(EventHandler eventHandlerEntradaProduto) {
        this.eventHandlerEntradaProduto = eventHandlerEntradaProduto;
    }

    public StatusBarEntradaProduto getStatusBar() {
        return statusBar.get();
    }

    public void setStatusBar(StatusBarEntradaProduto statusBar) {
        this.statusBar.set(statusBar);
    }

    public ObjectProperty<StatusBarEntradaProduto> statusBarProperty() {
        return statusBar;
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

    public TabModelProduto getModelProduto() {
        return modelProduto;
    }

    public void setModelProduto(TabModelProduto modelProduto) {
        this.modelProduto = modelProduto;
    }

    public TablePosition getTp() {
        return tp;
    }

    public void setTp(TablePosition tp) {
        this.tp = tp;
    }

    public TabModelEntradaProdutoProduto getModelEntradaProdutoProduto() {
        return modelEntradaProdutoProduto;
    }

    public void setModelEntradaProdutoProduto(TabModelEntradaProdutoProduto modelEntradaProdutoProduto) {
        this.modelEntradaProdutoProduto = modelEntradaProdutoProduto;
    }

    public EntradaProduto getEntradaProduto() {
        return entradaProduto;
    }

    public void setEntradaProduto(EntradaProduto entradaProduto) {
        this.entradaProduto = entradaProduto;
    }

    public ObservableList<EntradaProduto> getEntradaProdutoObservableList() {
        return entradaProdutoObservableList;
    }

    public void setEntradaProdutoObservableList(ObservableList<EntradaProduto> entradaProdutoObservableList) {
        this.entradaProdutoObservableList = entradaProdutoObservableList;
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

    public ObservableList<EntradaProdutoProduto> getEntradaProdutoProdutoObservableList() {
        return entradaProdutoProdutoObservableList;
    }

    public void setEntradaProdutoProdutoObservableList(ObservableList<EntradaProdutoProduto> entradaProdutoProdutoObservableList) {
        this.entradaProdutoProdutoObservableList = entradaProdutoProdutoObservableList;
    }

    public EntradaProdutoDAO getEntradaProdutoDAO() {
        return entradaProdutoDAO;
    }

    public void setEntradaProdutoDAO(EntradaProdutoDAO entradaProdutoDAO) {
        this.entradaProdutoDAO = entradaProdutoDAO;
    }

    public int getNumItens() {
        return numItens.get();
    }

    public void setNumItens(int numItens) {
        this.numItens.set(numItens);
    }

    public IntegerProperty numItensProperty() {
        return numItens;
    }

    public int getQtdItens() {
        return qtdItens.get();
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens.set(qtdItens);
    }

    public IntegerProperty qtdItensProperty() {
        return qtdItens;
    }

    public int getQtdVolume() {
        return qtdVolume.get();
    }

    public void setQtdVolume(int qtdVolume) {
        this.qtdVolume.set(qtdVolume);
    }

    public IntegerProperty qtdVolumeProperty() {
        return qtdVolume;
    }

    public BigDecimal getTotalBruto() {
        return totalBruto.get();
    }

    public void setTotalBruto(BigDecimal totalBruto) {
        this.totalBruto.set(totalBruto);
    }

    public ObjectProperty<BigDecimal> totalBrutoProperty() {
        return totalBruto;
    }

    public BigDecimal getTotalFrete() {
        return totalFrete.get();
    }

    public void setTotalFrete(BigDecimal totalFrete) {
        this.totalFrete.set(totalFrete);
    }

    public ObjectProperty<BigDecimal> totalFreteProperty() {
        return totalFrete;
    }

    public BigDecimal getTotalImpostoEntrada() {
        return totalImpostoEntrada.get();
    }

    public void setTotalImpostoEntrada(BigDecimal totalImpostoEntrada) {
        this.totalImpostoEntrada.set(totalImpostoEntrada);
    }

    public ObjectProperty<BigDecimal> totalImpostoEntradaProperty() {
        return totalImpostoEntrada;
    }

    public BigDecimal getTotalImpostoItens() {
        return totalImpostoItens.get();
    }

    public ObjectProperty<BigDecimal> totalImpostoItensProperty() {
        return totalImpostoItens;
    }

    public void setTotalImpostoItens(BigDecimal totalImpostoItens) {
        this.totalImpostoItens.set(totalImpostoItens);
    }

    public BigDecimal getTotalImposto() {
        return totalImposto.get();
    }

    public void setTotalImposto(BigDecimal totalImposto) {
        this.totalImposto.set(totalImposto);
    }

    public ObjectProperty<BigDecimal> totalImpostoProperty() {
        return totalImposto;
    }

    public BigDecimal getTotalDesconto() {
        return totalDesconto.get();
    }

    public void setTotalDesconto(BigDecimal totalDesconto) {
        this.totalDesconto.set(totalDesconto);
    }

    public ObjectProperty<BigDecimal> totalDescontoProperty() {
        return totalDesconto;
    }

    public BigDecimal getTotalLiquido() {
        return totalLiquido.get();
    }

    public void setTotalLiquido(BigDecimal totalLiquido) {
        this.totalLiquido.set(totalLiquido);
    }

    public ObjectProperty<BigDecimal> totalLiquidoProperty() {
        return totalLiquido;
    }

    public EntradaProdutoProduto getEntradaProdutoProduto() {
        return entradaProdutoProduto;
    }

    public void setEntradaProdutoProduto(EntradaProdutoProduto entradaProdutoProduto) {
        this.entradaProdutoProduto = entradaProdutoProduto;
    }

    public BigDecimal getNfeVlr() {
        return nfeVlr.get();
    }

    public void setNfeVlr(BigDecimal nfeVlr) {
        this.nfeVlr.set(nfeVlr);
    }

    public ObjectProperty<BigDecimal> nfeVlrProperty() {
        return nfeVlr;
    }

    public BigDecimal getNfeVlrTributo() {
        return nfeVlrTributo.get();
    }

    public void setNfeVlrTributo(BigDecimal nfeVlrTributo) {
        this.nfeVlrTributo.set(nfeVlrTributo);
    }

    public ObjectProperty<BigDecimal> nfeVlrTributoProperty() {
        return nfeVlrTributo;
    }

    public BigDecimal getNfeVlrMulta() {
        return nfeVlrMulta.get();
    }

    public void setNfeVlrMulta(BigDecimal nfeVlrMulta) {
        this.nfeVlrMulta.set(nfeVlrMulta);
    }

    public ObjectProperty<BigDecimal> nfeVlrMultaProperty() {
        return nfeVlrMulta;
    }

    public BigDecimal getNfeVlrJuros() {
        return nfeVlrJuros.get();
    }

    public void setNfeVlrJuros(BigDecimal nfeVlrJuros) {
        this.nfeVlrJuros.set(nfeVlrJuros);
    }

    public ObjectProperty<BigDecimal> nfeVlrJurosProperty() {
        return nfeVlrJuros;
    }

    public BigDecimal getNfeVlrTaxa() {
        return nfeVlrTaxa.get();
    }

    public void setNfeVlrTaxa(BigDecimal nfeVlrTaxa) {
        this.nfeVlrTaxa.set(nfeVlrTaxa);
    }

    public ObjectProperty<BigDecimal> nfeVlrTaxaProperty() {
        return nfeVlrTaxa;
    }

    public BigDecimal getNfeVlrTotal() {
        return nfeVlrTotal.get();
    }

    public void setNfeVlrTotal(BigDecimal nfeVlrTotal) {
        this.nfeVlrTotal.set(nfeVlrTotal);
    }

    public ObjectProperty<BigDecimal> nfeVlrTotalProperty() {
        return nfeVlrTotal;
    }

    /**
     * END
     * Getters e setters
     */

}
