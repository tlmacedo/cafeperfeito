package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.*;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoNoSistema;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.StatusBarProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.UnidadeComercial;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.WsCosmosBusca;
import br.com.tlmacedo.cafeperfeito.model.ws.WsEanCosmoDAO;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.FormatListCell_FiscalCestNcm;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.FormatListCell_ProdutoCodigoBarra;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.view.ViewCadastroProduto;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.*;
import static br.com.tlmacedo.cafeperfeito.model.vo.enums.CriteriosValidationFields.*;

public class ControllerCadastroProduto implements Initializable, ModeloCafePerfeito {

    public TreeTableView<Produto> tTvProduto;
    public JFXListView<ProdutoCodigoBarra> codigoBarraList;
    public AnchorPane painelViewCadastroProduto;
    public TitledPane tpnCadastroProduto;
    public JFXTextField txtPesquisaProduto;
    public Label lblStatus;
    public Label lblRegistrosLocalizados;
    public TitledPane tpnDadoCadastral;
    public JFXTextField txtCodigo;
    public JFXTextField txtDescricao;
    public JFXTextField txtPeso;
    public JFXComboBox<UnidadeComercial> cboUnidadeComercial;
    public JFXComboBox<SituacaoNoSistema> cboSituacaoSistema;
    public JFXTextField txtPrecoFabrica;
    public JFXTextField txtMargem;
    public JFXTextField txtPrecoConsumidor;
    public JFXTextField txtVarejo;
    public JFXTextField txtComissaoPorc;
    public JFXTextField txtLucroBruto;
    public JFXTextField txtPrecoUltimoImpostoSefaz;
    public JFXTextField txtPrecoUltimoFrete;
    public JFXTextField txtComissaoReal;
    public JFXTextField txtLucroLiquido;
    public JFXTextField txtLucratividade;
    public Label lblDataCadastro;
    public Label lblDataCadastroDiff;
    public Label lblDataAtualizacao;
    public Label lblDataAtualizacaoDiff;
    public JFXComboBox<FiscalCestNcm> cboFiscalCestNcm;
    public JFXTextField txtFiscalGenero;
    public JFXTextField txtFiscalNcm;
    public JFXTextField txtFiscalCest;
    public JFXComboBox<FiscalCstOrigem> cboFiscalCstOrigem;
    public JFXComboBox<FiscalIcms> cboFiscalIcms;
    public JFXComboBox<FiscalPisCofins> cboFiscalPis;
    public JFXComboBox<FiscalPisCofins> cboFiscalCofins;
    public Circle imgCirculo;

    boolean tabCarregada = false;
    private StringProperty stpDtCad = new SimpleStringProperty("");
    private StringProperty stpDtCadDiff = new SimpleStringProperty("");
    private StringProperty stpDtAtualiz = new SimpleStringProperty("");
    private StringProperty stpDtAtualizDiff = new SimpleStringProperty("");
    private ServiceAlertMensagem alertMensagem;
    private EventHandler eventHandlerCadastroProduto;
    private ObjectProperty<StatusBarProduto> statusBar = new SimpleObjectProperty<>(StatusBarProduto.PESQUISA);
    private String nomeController = "cadastroProduto";
    private String nomeTab = "";
    private List<Pair> listaTarefa = new ArrayList<>();
    private TabModelProduto modelProduto;
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private Produto produto;
    private ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(new ProdutoDAO().getAll(Produto.class, "descricao", null, null, null));
    private FilteredList<Produto> produtoFilteredList = new FilteredList<>(produtoObservableList);
    private ObservableList<ProdutoCodigoBarra> codigoBarraObservableList = FXCollections.observableArrayList();
    private BooleanProperty isValido = new SimpleBooleanProperty(false);

    @Override
    public void fechar() {
        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().remove(ViewCadastroProduto.getTab());
        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroProduto());
    }

    @Override
    public void criarObjetos() {
        setNomeTab(ViewCadastroProduto.getTituloJanela());
        getListaTarefa().add(new Pair("criarTabela", "criando tabela de " + getNomeController()));
    }

    @Override
    public void preencherObjetos() {
        getListaTarefa().add(new Pair("vinculandoObjetosTabela", "vinculando objetos a tableModel"));
        getListaTarefa().add(new Pair("preencherTabela", "preenchendo tabela " + getNomeController()));

        getListaTarefa().add(new Pair("preencherCombos", "carregando informações do formulario"));

        setTabCarregada(new ServiceSegundoPlano().tarefaAbreCadastro(taskCadastroProduto(), getListaTarefa().size(),
                String.format("Abrindo %s", getNomeTab())));
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void fatorarObjetos() {
        getCboFiscalCestNcm().setCellFactory(param -> new FormatListCell_FiscalCestNcm());
        getCodigoBarraList().setCellFactory(param -> new FormatListCell_ProdutoCodigoBarra());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void escutarTecla() {

        ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());

        getLblStatus().textProperty().bind(Bindings.createStringBinding(() -> {
            switch (statusBarProperty().get()) {
                case INCLUIR:
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnCadastroProduto().getContent(), true);
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnDadoCadastral().getContent(), false);
                    limparCampos();
                    setProduto(null);
                    getTxtCodigo().requestFocus();
                    break;
                case EDITAR:
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnCadastroProduto().getContent(), true);
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnDadoCadastral().getContent(), false);
                    try {
                        setProduto(getProduto().clone());
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                    getTxtCodigo().requestFocus();
                    break;
                case PESQUISA:
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnCadastroProduto().getContent(), false);
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnDadoCadastral().getContent(), true);
                    limparCampos();
                    getTxtPesquisaProduto().requestFocus();
                    break;
            }
            String tmpStatusBar = statusBarProperty().get().getDescricao();
            if (!isValidoProperty().get())
                tmpStatusBar = tmpStatusBar.replace("[F2-Incluir]", "").replace("[F5-Atualizar]", "").replaceAll("    ", "  ");
            ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(tmpStatusBar);
            return String.format("[%s]", statusBarProperty().get());
        }, statusBarProperty()));

        isValidoProperty().addListener((ov, o, n) -> {
            if (!n)
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao().replace("[F2-Incluir]", "").replace("[F5-Atualizar]", "").replaceAll("    ", "  "));
            else
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());
        });

        gettTvProduto().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            if (!n.getValue().getCodigo().equals(""))
                setProduto(n.getValue());
            else
                setProduto(n.getParent().getValue());
        });

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size() == 0) return;
            if (ControllerPrincipal.ctrlPrincipal.getTabSelecionada().equals(getNomeTab())) {
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(getStatusBar().getDescricao());
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroProduto());
            } else {
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroProduto());
            }
        });

        setEventHandlerCadastroProduto(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
//                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() < 0)
//                    return;
//                if (!ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().getText().equals(ViewCadastroProduto.getTituloJanela()))
//                    return;
                switch (event.getCode()) {
                    case F1:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        setStatusBar(StatusBarProduto.INCLUIR);
                        break;
                    case F2:
                    case F5:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        if (!guardarProduto()) break;
                        if (salvarProduto()) {
                            switch (getStatusBar()) {
                                case INCLUIR:
                                    getProdutoObservableList().add(getProduto());
                                    break;
                                case EDITAR:
                                    if (gettTvProduto().getSelectionModel().getSelectedItem().getValue() != getProduto())
                                        getModelProduto().setProdutoObservableList(getProduto());
                                    break;
                            }
                            setStatusBar(StatusBarProduto.PESQUISA);
                        }
                        break;
                    case F3:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        setAlertMensagem(new ServiceAlertMensagem());
                        getAlertMensagem().setStrIco("ic_cadastro_produto_cancel_24dp.png");
                        getAlertMensagem().setCabecalho(
                                String.format("Cancelar %s",
                                        (getStatusBar().equals(StatusBarProduto.INCLUIR) ? "inclusão" : "edição")
                                )
                        );
                        getAlertMensagem().setPromptText(
                                String.format("%s, deseja cancelar %s do cadastro de produto?",
                                        LogadoInf.getUserLog().getApelido(),
                                        (getStatusBar().equals(StatusBarProduto.INCLUIR) ? "inclusão" : "edição")
                                )
                        );
                        if (getAlertMensagem().getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
                        if (getStatusBar().equals(StatusBarProduto.EDITAR))
                            setProduto(gettTvProduto().getSelectionModel().getSelectedItem().getValue());
                        setStatusBar(StatusBarProduto.PESQUISA);
                        break;
                    case F4:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        if (!gettTvProduto().isFocused() || getProduto().getId() <= 0) break;
                        setStatusBar(StatusBarProduto.EDITAR);
                        break;
                    case F6:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        if (getStatusBar().equals(StatusBarProduto.PESQUISA) || !event.isShiftDown()) return;
                        keyShiftF6();
                        break;
                    case F7:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTxtPesquisaProduto().requestFocus();
                        break;
                    case F8:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        break;
                    case F12:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        fechar();
                        break;
                    case HELP:
                        if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
                        keyInsert();
                        break;
                    case DELETE:
                        if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
                        keyDelete();
                        break;
                    case B:
                        if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
                        if (CODE_KEY_CTRL_ALT_B.match(event) || CHAR_KEY_CTRL_ALT_B.match(event))
                            addCodeBar("");
                        break;
                    case N:
                        if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
                        if (CODE_KEY_CTRL_ALT_N.match(event) || CHAR_KEY_CTRL_ALT_N.match(event))
                            if (getTxtFiscalGenero().getText().equals("")
                                    || getTxtFiscalGenero().getText().equals("00")) {
                                setAlertMensagem(new ServiceAlertMensagem());
                                getAlertMensagem().setCabecalho("campo inválido");
                                getAlertMensagem().setPromptText("gênero do produto é inválido!");
                                getAlertMensagem().getRetornoAlert_OK();
                                getTxtFiscalGenero().requestFocus();
                                return;
                            } else {
                                geraNovoCodigo();
                            }
                        break;
                    case Z:
                        if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
                        if (CODE_KEY_CTRL_Z.match(event) || CHAR_KEY_CTRL_Z.match(event))
                            reverseImageProduto();
                        break;
                }
                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() > 0)
                    ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().setOnCloseRequest(event1 -> {
                        if (!getStatusBar().equals(StatusBarProduto.PESQUISA)) {
                            event1.consume();
                        }
                    });
            }
        });

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroProduto());

        getTxtPesquisaProduto().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            gettTvProduto().requestFocus();
            gettTvProduto().getSelectionModel().selectFirst();
        });

        getCboFiscalCestNcm().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null || getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
            getTxtFiscalNcm().setText(n.getNcm());
            getTxtFiscalCest().setText(n.getCest());
            getTxtFiscalGenero().setText(
                    String.format("%02d",
                            n.getNcm().length() < 2
                                    ? 0
                                    : Integer.valueOf(n.getNcm().substring(0, n.getNcm().length() - 2))
                    )
            );
        });

        getTxtPrecoFabrica().textProperty().addListener((observable, oldValue, newValue) -> {
            if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
            if (!getTxtPrecoFabrica().isFocused()) return;
            resultVlrConsumidor();
        });

        getTxtMargem().textProperty().addListener((observable, oldValue, newValue) -> {
            if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
            if (!getTxtMargem().isFocused()) return;
            resultVlrConsumidor();
        });

        getTxtPrecoConsumidor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
            if (!getTxtPrecoConsumidor().isFocused()) return;
            resultVlrMargem();
            resultVlrLucroBruto();
            resultVlrComissaoReal();
        });

        //noinspection Duplicates
        getTxtComissaoPorc().textProperty().addListener((observable, oldValue, newValue) -> {
            if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
            if (!getTxtComissaoPorc().isFocused()) return;
            resultVlrComissaoReal();
        });

        //noinspection Duplicates
        getTxtPrecoUltimoImpostoSefaz().textProperty().addListener((observable, oldValue, newValue) -> {
            if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
            if (!getTxtPrecoUltimoImpostoSefaz().isFocused()) return;
            resultVlrComissaoReal();
        });

        //noinspection Duplicates
        getTxtPrecoUltimoFrete().textProperty().addListener((observable, oldValue, newValue) -> {
            if (getStatusBar().equals(StatusBarProduto.PESQUISA)) return;
            if (!getTxtPrecoUltimoFrete().isFocused()) return;
            resultVlrComissaoReal();
        });

        getCodigoBarraObservableList().addListener((ListChangeListener<ProdutoCodigoBarra>) c -> {
            getCodigoBarraList().setItems(getCodigoBarraObservableList());
        });

        getImgCirculo().setOnDragOver(event -> {
            if (getImgCirculo().isDisabled()) return;
            Dragboard board = event.getDragboard();
            if (board.hasFiles())
                if (Pattern.compile(REGEX_EXTENSAO_IMAGENS).matcher(board.getFiles().get(0).toPath().toString()).find())
                    event.acceptTransferModes(TransferMode.ANY);
        });

        getImgCirculo().setOnDragDropped(event -> {
            if (getImgCirculo().isDisabled()) return;
            try {
                if (event.getDragboard().getFiles().size() == 0) return;
                Dragboard board = event.getDragboard();
                addImageProduto(new Image(new FileInputStream(board.getFiles().get(0))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        getLblDataCadastro().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtCadProperty().get(), stpDtCadProperty()));
        getLblDataCadastroDiff().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtCadDiffProperty().get(), stpDtCadDiffProperty()));
        getLblDataAtualizacao().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtAtualizProperty().get(), stpDtAtualizProperty()));
        getLblDataAtualizacaoDiff().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtAtualizDiffProperty().get(), stpDtAtualizDiffProperty()));

        camposValidos();

    }

    @SuppressWarnings("Duplicates")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarObjetos();
        preencherObjetos();
        escutarTecla();
        fatorarObjetos();
        setStatusBar(StatusBarProduto.PESQUISA);
        ServiceCampoPersonalizado.fieldMask(getPainelViewCadastroProduto());

        Platform.runLater(() -> {
            if (!isTabCarregada())
                fechar();
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F7));
        });
    }

    Task taskCadastroProduto() {
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
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
                                break;

                            case "vinculandoObjetosTabela":
                                getModelProduto().setLblRegistrosLocalizados(getLblRegistrosLocalizados());
                                getModelProduto().setTtvProduto(gettTvProduto());
                                getModelProduto().setTxtPesquisaProduto(getTxtPesquisaProduto());
                                getModelProduto().setProdutoObservableList(getProdutoObservableList());
                                getModelProduto().setProdutoFilteredList(getProdutoFilteredList());
                                getModelProduto().escutaLista();
                                break;

                            case "preencherTabela":
                                getModelProduto().preencherTabela();
                                break;

                            case "preencherCombos":
//                                new ServiceAutoCompleteTextField(getTxtDescricao(), getProdutoObservableList().stream()
//                                        .map(Produto::getDescricao).collect(Collectors.toCollection(FXCollections::observableArrayList)));

                                getCboUnidadeComercial().getItems().setAll(UnidadeComercial.getList());

                                getCboSituacaoSistema().getItems().setAll(SituacaoNoSistema.getList());

                                getCboFiscalCestNcm().getItems().setAll(
                                        FXCollections.observableArrayList(
                                                new FiscalCestNcmDAO().getAll(FiscalCestNcm.class, "ncm", null, null, null)
                                        )
                                );
                                getCboFiscalCestNcm().setEditable(true);
                                new ServiceAutoCompleteComboBox<FiscalCestNcm>(getCboFiscalCestNcm(), FiscalCestNcm.class);

                                getCboFiscalCstOrigem().getItems().setAll(
                                        FXCollections.observableArrayList(
                                                new FiscalCstOrigemDAO().getAll(FiscalCstOrigem.class, null, null, null, null)
                                        )
                                );

                                getCboFiscalIcms().getItems().setAll(
                                        FXCollections.observableArrayList(
                                                new FiscalIcmsDAO().getAll(FiscalIcms.class, null, null, null, null)
                                        )
                                );

                                List<FiscalPisCofins> fiscalPisCofinsList = new FiscalPisCofinsDAO().getAll(FiscalPisCofins.class, null, null, null, null);
                                getCboFiscalPis().getItems().setAll(
                                        fiscalPisCofinsList
                                );

                                getCboFiscalCofins().getItems().setAll(
                                        fiscalPisCofinsList
                                );
                                break;
                        }
                    }
                    updateProgress(getListaTarefa().size(), getListaTarefa().size());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

        };
        return voidTask;

    }

    /**
     * start
     * Booleans
     */

    private boolean teclaFuncaoDisponivel(KeyCode keyCode) {
        return ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().getLblTeclas().getText().contains(keyCode.toString());
    }

    @SuppressWarnings("Duplicates")
    private boolean guardarProduto() {
        try {
            getProduto().setCodigo(getTxtCodigo().getText());
            getProduto().setDescricao(getTxtDescricao().getText());
            getProduto().setPeso(ServiceMascara.getBigDecimalFromTextField(getTxtPeso().getText(), 3));

            getProduto().setUnidadeComercial(getCboUnidadeComercial().getSelectionModel().getSelectedItem());
            getProduto().setSituacao(getCboSituacaoSistema().getSelectionModel().getSelectedItem());
            getProduto().setPrecoFabrica(ServiceMascara.getBigDecimalFromTextField(getTxtPrecoFabrica().getText(), 2));
            getProduto().setPrecoConsumidor(ServiceMascara.getBigDecimalFromTextField(getTxtPrecoConsumidor().getText(), 2));
            getProduto().setVarejo(Integer.parseInt(getTxtVarejo().getText()));
            getProduto().setUltImpostoSefaz(ServiceMascara.getBigDecimalFromTextField(getTxtPrecoUltimoImpostoSefaz().getText(), 2));
            getProduto().setUltFrete(ServiceMascara.getBigDecimalFromTextField(getTxtPrecoUltimoFrete().getText(), 2));
            getProduto().setComissao(ServiceMascara.getBigDecimalFromTextField(getTxtComissaoPorc().getText(), 2));
            getProduto().setNcm(getTxtFiscalNcm().getText());
            getProduto().setCest(getTxtFiscalCest().getText());
            getProduto().setFiscalCstOrigem(getCboFiscalCstOrigem().getSelectionModel().getSelectedItem());
            getProduto().setFiscalIcms(getCboFiscalIcms().getSelectionModel().getSelectedItem());
            getProduto().setFiscalPis(getCboFiscalPis().getSelectionModel().getSelectedItem());
            getProduto().setFiscalCofins(getCboFiscalCofins().getSelectionModel().getSelectedItem());
            getProduto().setNfeGenero(getTxtFiscalGenero().getText().replaceAll("\\D", ""));
            getProduto().setProdutoCodigoBarraList(getCodigoBarraObservableList());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean salvarProduto() {
        if (getStatusBar().equals(StatusBarProduto.INCLUIR)) {
            getProduto().setUsuarioCadastro(LogadoInf.getUserLog());
            getProduto().setUsuarioAtualizacao(null);
            getProduto().setDataAtualizacao(null);
        } else {
            getProduto().setUsuarioAtualizacao(LogadoInf.getUserLog());
        }
        setProduto(getProdutoDAO().persiste(getProduto()));
        return (getProdutoDAO() != null);
    }

    /**
     * END
     * Boolean
     */

    /**
     * start
     * private voids
     */

    private void limparCampos() {
        getTxtPesquisaProduto().setText("");
        setStpDtCad("");
        setStpDtCadDiff("");
        setStpDtAtualiz("");
        setStpDtAtualizDiff("");
        ServiceCampoPersonalizado.fieldClear((AnchorPane) getTpnDadoCadastral().getContent());
    }

    @SuppressWarnings("Duplicates")
    private void exibirProduto() {
        getTxtCodigo().setText(getProduto().getCodigo());
        getTxtDescricao().setText(getProduto().getDescricao());
        getTxtPeso().setText(getProduto().getPeso().toString());
        getCboUnidadeComercial().getSelectionModel().select(getProduto().getUnidadeComercial());
        getCboSituacaoSistema().getSelectionModel().select(getProduto().getSituacao());
        getTxtPrecoFabrica().setText(getProduto().getPrecoFabrica().toString());
        getTxtPrecoConsumidor().setText(getProduto().getPrecoConsumidor().toString());
        getTxtVarejo().setText(String.valueOf(getProduto().getVarejo()));
        getTxtPrecoUltimoImpostoSefaz().setText(getProduto().getUltImpostoSefaz().toString());
        getTxtPrecoUltimoFrete().setText(getProduto().getUltFrete().toString());
        getTxtComissaoPorc().setText(getProduto().getComissao().toString());
        getTxtFiscalNcm().setText(getProduto().getNcm());
        getTxtFiscalCest().setText(getProduto().getCest());

        resultVlrMargem();
        resultVlrLucroBruto();
        resultVlrComissaoReal();

        getTxtFiscalGenero().setText(getProduto().getNfeGenero());
        getTxtFiscalNcm().setText(getProduto().getNcm());
        getTxtFiscalCest().setText(getProduto().getCest());

        getCboFiscalCstOrigem().getSelectionModel().select(
                getCboFiscalCstOrigem().getItems().stream()
                        .filter(fiscalCstOrigem -> fiscalCstOrigem.getId() == getProduto().getFiscalCstOrigem().getId())
                        .findFirst().orElse(null)
        );
        getCboFiscalIcms().getSelectionModel().select(
                getCboFiscalIcms().getItems().stream()
                        .filter(fiscalIcms -> fiscalIcms.getId() == getProduto().getFiscalIcms().getId())
                        .findFirst().orElse(null)
        );
        getCboFiscalPis().getSelectionModel().select(
                getCboFiscalPis().getItems().stream()
                        .filter(fiscalPis -> fiscalPis.getId() == getProduto().getFiscalPis().getId())
                        .findFirst().orElse(null)
        );
        getCboFiscalCofins().getSelectionModel().select(
                getCboFiscalCofins().getItems().stream()
                        .filter(fiscalCofins -> fiscalCofins.getId() == getProduto().getFiscalCofins().getId())
                        .findFirst().orElse(null)
        );

        getCodigoBarraObservableList().setAll(getProduto().getProdutoCodigoBarraList());

        if (getProduto().getDataCadastro() != null) {
            setStpDtCad(String.format("%s [%02d-%s]",
                    DTF_DATAHORA.format(getProduto().getDataCadastro()),
                    getProduto().getUsuarioCadastro().getId(),
                    StringUtils.capitalize(getProduto().getUsuarioCadastro().getApelido())
            ));
            setStpDtCadDiff(ServiceDataHora.getIntervaloData(getProduto().getDataCadastro().toLocalDate(), null));
        }
        if (getProduto().getUsuarioAtualizacao() != null) {
            setStpDtAtualiz(String.format("%s [%02d-%s]",
                    DTF_DATAHORA.format(getProduto().getDataAtualizacao()),
                    getProduto().getUsuarioAtualizacao().getId(),
                    StringUtils.capitalize(getProduto().getUsuarioAtualizacao().getApelido())
            ));
            setStpDtAtualizDiff(ServiceDataHora.getIntervaloData(getProduto().getDataAtualizacao().toLocalDate(), null));
        } else {
            setStpDtAtualiz("");
            setStpDtAtualizDiff("");
        }

        refreshImageProduto();
    }

    private void keyShiftF6() {
        if (getCodigoBarraList().isFocused() && getCodigoBarraList().getSelectionModel().getSelectedItem() != null) {
            editCodigoBarra(getCodigoBarraList().getSelectionModel().getSelectedItem().getCodigoBarra());
        }
    }

    private void keyInsert() {
        if (getCodigoBarraList().isFocused())
            addCodeBar("");
    }

    private void keyDelete() {
        if (getCodigoBarraList().isFocused())
            delCodBar();
    }

    private void reverseImageProduto() {
        if (getProduto().getImageProduto() != null && getProduto().getImageProdutoBack() != null)
            getProduto().setImageProduto(getProduto().getImageProdutoBack());
        refreshImageProduto();
    }

    private void addImageProduto(Image image) {
        if (getProduto().getImgProduto() != null)
            getProduto().setImgProdutoBack(getProduto().getImageProduto());
        getProduto().setImageProduto(ServiceImage.getImageResized(image,
                ServiceVariaveisSistema.TCONFIG.getInfLoja().getImageDefaultProduto().getWidth(),
                ServiceVariaveisSistema.TCONFIG.getInfLoja().getImageDefaultProduto().getHeight()));
        refreshImageProduto();
    }

    private void refreshImageProduto() {
        try {
            if (getProduto().getImgProduto() != null)
                getImgCirculo().setFill(new ImagePattern(getProduto().getImageProduto()));
            else
                getImgCirculo().setFill(FUNDO_RADIAL_GRADIENT);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    private void geraNovoCodigo() {
        if (getTxtFiscalGenero().getText().equals("")) return;
        int digitoCodigo = 0;
        try {
            String str;
            digitoCodigo = Integer.valueOf((str = getProdutoObservableList().stream()
                    .sorted(Comparator.comparing(Produto::getCodigo))
                    .filter(prod -> {
                        if (prod.getCodigo().substring(0, prod.getCodigo().length() - 2).equals(Integer.valueOf(getTxtFiscalGenero().getText()).toString()))
                            return true;
                        return false;
                    })
                    .reduce((first, second) -> second)
                    .get().getCodigo()).substring(str.length() - 2));
        } catch (Exception ex) {
            digitoCodigo = 0;
        }
        getTxtCodigo().setText(String.format("%d%02d",
                Integer.valueOf(getTxtFiscalGenero().getText()),
                digitoCodigo + 1));
    }

    private void addCodeBar(String temp) {
        alertMensagem = new ServiceAlertMensagem();
        alertMensagem.setStrIco("ic_barcode_add_24dp");
        alertMensagem.setCabecalho(String.format("Adicionar dados [código de barras]"));
        alertMensagem.setPromptText(String.format("%s, qual o código de barras a ser adicionado para o produto: [%s] ?",
                LogadoInf.getUserLog().getApelido(), getProduto().getDescricao()));
        String codBarra;
        if ((codBarra = alertMensagem.getRetornoAlert_TextField(
                ServiceMascara.getCodigoBarraMask(), temp, "Código de barras")
                .orElse(null)) == null) return;
        if (!ServiceValidarDado.isEan13Valido(codBarra)) {
            addCodeBar(codBarra);
            return;
        }
        String finalCodBarra = codBarra;
        if (codigoBarraObservableList.stream()
                .filter(barra -> barra.getCodigoBarra().equals(finalCodBarra))
                .findFirst().orElse(null) != null) {
            return;
        }
        if (modelProduto.verificaExistenteCodigoBarra(finalCodBarra, getProduto().getId())) return;
        codigoBarraObservableList.add(new ProdutoCodigoBarra(finalCodBarra,
                new ServiceEan13(finalCodBarra).createBarcodePNG()));
        getProduto().setProdutoCodigoBarraList(codigoBarraObservableList);

        String retorno = ServiceConsultaWebServices.getProdutoNcmCest_WsEanCosmos(finalCodBarra, WsCosmosBusca.GTINS);
        if (!retorno.equals("")) {
            alertMensagem = new ServiceAlertMensagem();
            alertMensagem.setStrIco("ic_webservice_24dp.png");
            alertMensagem.setCabecalho(String.format("Localizado informações do produto"));
            alertMensagem.setPromptText(String.format("%s, deseja atualizar informações do produto?",
                    LogadoInf.getUserLog().getApelido()));
            if (alertMensagem.getRetornoAlert_Yes_No().get() == ButtonType.YES) {
                new WsEanCosmoDAO().getInforcacoesProduto(getProduto(), finalCodBarra, retorno);
                txtDescricao.setText(getProduto().getDescricao());
                txtFiscalNcm.setText(getProduto().getNcm());
                txtFiscalGenero.setText(getProduto().getNfeGenero());
                txtFiscalCest.setText(getProduto().getCest());
                txtPeso.setText(getProduto().getPeso().toString());
                txtPrecoConsumidor.setText(getProduto().getPrecoConsumidor().toString());
            }
        }
        refreshImageProduto();
    }

    private void delCodBar() {
        ProdutoCodigoBarra codigoBarra;
        if ((codigoBarra = codigoBarraList.getSelectionModel().getSelectedItem()) == null) return;
        alertMensagem = new ServiceAlertMensagem();
        alertMensagem.setStrIco("ic_barcode_remove_24dp");
        alertMensagem.setCabecalho(String.format("Deletar dados [código de barras]"));
        alertMensagem.setPromptText(String.format("%s, deseja deletar o código de barras: [%s]\ndo produto: [%s] ?",
                LogadoInf.getUserLog().getApelido(), codigoBarra.getCodigoBarra(), txtDescricao.getText()));
        if (alertMensagem.getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
        List<ProdutoCodigoBarra> list = new ArrayList<>(getProduto().getProdutoCodigoBarraList());
        list.remove(codigoBarra);
        codigoBarraObservableList.remove(codigoBarra);
//        getProduto().setProdutoCodigoBarraList(list);
//        codigoBarraObservableList.setAll(getProduto().getProdutoCodigoBarraList());
    }

    @SuppressWarnings("Duplicates")
    private void editCodigoBarra(String codBarra) {
        ProdutoCodigoBarra codigoBarra = null;
        if ((codigoBarra = codigoBarraList.getSelectionModel().getSelectedItem()) == null) return;
        if (codBarra.equals(""))
            codBarra = codigoBarra.getCodigoBarra();
        alertMensagem = new ServiceAlertMensagem();
        alertMensagem.setStrIco("ic_barcode_update_24dp.png");
        alertMensagem.setCabecalho("Editar dados [código barra]");
        alertMensagem.setPromptText(String.format("%s, deseja editar código barra: [%s] do produto: [%s] ?",
                LogadoInf.getUserLog().getApelido(),
                codBarra,
                txtDescricao.getText()));
        if ((codBarra = alertMensagem.getRetornoAlert_TextField(
                ServiceMascara.getCodigoBarraMask(), codBarra, "Código de barras")
                .orElse(null)) == null) return;
        if (codBarra.length() < 13)
            codBarra = String.format("%013d", Long.parseLong(codBarra));
        if (!ServiceValidarDado.isEan13Valido(codBarra)) {
            editCodigoBarra(codBarra);
            return;
        }
        codigoBarra.setCodigoBarra(codBarra);
        codigoBarraObservableList.set(codigoBarraObservableList.indexOf(codigoBarraList.getSelectionModel().getSelectedItem()), codigoBarra);
//        codigoBarraList.setItems(getProduto().getProdutoCodigoBarraList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }

    /**
     * END
     * private voids
     */


    /**
     * Start
     * calculos
     */

    private void camposValidos() {
        ServiceValidationFields getTxtCodigoValid = new ServiceValidationFields();
        getTxtCodigoValid.checkFields(
                getTxtCodigo(),
                String.format("%s::%d;",
                        MIN_SIZE, 3)
        );
        ServiceValidationFields getTxtDescricaoValid = new ServiceValidationFields();
        getTxtDescricaoValid.checkFields(
                getTxtDescricao(),
                String.format("%s::%d;",
                        MIN_SIZE, 3)
        );

        ServiceValidationFields getCboSituacaoSistemaValid = new ServiceValidationFields();
        getCboSituacaoSistemaValid.checkFields(
                getCboSituacaoSistema(),
                String.format("%s::%d;",
                        MIN_CBO, 1)
        );

        ServiceValidationFields getTxtLucroLiquidoValid = new ServiceValidationFields();
        getTxtLucroLiquidoValid.checkFields(
                getTxtLucroLiquido(),
                String.format("%s::%s;",
                        MIN_BIG, "0.01")
        );

        ServiceValidationFields getTxtFiscalNcmValid = new ServiceValidationFields();
        getTxtFiscalNcmValid.checkFields(
                getTxtFiscalNcm(),
                String.format("%s::%d;",
                        MIN_SIZE, 4)
        );

        ServiceValidationFields getTxtFiscalCestValid = new ServiceValidationFields();
        getTxtFiscalCestValid.checkFields(
                getTxtFiscalCest(),
                String.format("%s::%d;",
                        MIN_SIZE, 4)
        );

        ServiceValidationFields getCboFiscalCstOrigemValid = new ServiceValidationFields();
        getCboFiscalCstOrigemValid.checkFields(
                getCboFiscalCstOrigem(),
                String.format("%s::%d;",
                        MIN_CBO, 0)
        );

        ServiceValidationFields getCboFiscalIcmsValid = new ServiceValidationFields();
        getCboFiscalIcmsValid.checkFields(
                getCboFiscalIcms(),
                String.format("%s::%d;",
                        MIN_CBO, 0)
        );

        ServiceValidationFields getCboFiscalPisValid = new ServiceValidationFields();
        getCboFiscalPisValid.checkFields(
                getCboFiscalPis(),
                String.format("%s::%d;",
                        MIN_CBO, 0)
        );

        ServiceValidationFields getCboFiscalCofinsValid = new ServiceValidationFields();
        getCboFiscalCofinsValid.checkFields(
                getCboFiscalCofins(),
                String.format("%s::%d;",
                        MIN_CBO, 0)
        );

        isValidoProperty().bind(Bindings.createBooleanBinding(() ->
                        (getTxtCodigoValid.isValidoProperty().get()
                                && getTxtDescricaoValid.isValidoProperty().get()
                                && getCboSituacaoSistemaValid.isValidoProperty().get()
                                && getTxtLucroLiquidoValid.isValidoProperty().get()
                                && getTxtFiscalNcmValid.isValidoProperty().get()
                                && getTxtFiscalCestValid.isValidoProperty().get()
                        ), getTxtCodigoValid.isValidoProperty(), getTxtDescricaoValid.isValidoProperty(),
                getCboSituacaoSistemaValid.isValidoProperty(), getTxtLucroLiquidoValid.isValidoProperty(),
                getTxtFiscalNcmValid.isValidoProperty(), getTxtFiscalCestValid.isValidoProperty(),
                getCboFiscalCstOrigemValid.isValidoProperty(), getCboFiscalIcmsValid.isValidoProperty(),
                getCboFiscalPisValid.isValidoProperty(), getCboFiscalCofinsValid.isValidoProperty()
        ));


    }

    @SuppressWarnings("Duplicates")
    private void resultVlrMargem() {
        BigDecimal prcFabrica = ServiceMascara.getBigDecimalFromTextField(txtPrecoFabrica.getText(), 2);
        BigDecimal prcConsumidor = ServiceMascara.getBigDecimalFromTextField(txtPrecoConsumidor.getText(), 2);
        BigDecimal margem = prcConsumidor.multiply(new BigDecimal("100")).divide(prcFabrica, 2, RoundingMode.HALF_UP)
                .subtract(new BigDecimal("100"));
        txtMargem.setText(margem.setScale(2).toString());
    }

    @SuppressWarnings("Duplicates")
    private void resultVlrConsumidor() {
        BigDecimal prcFabrica = ServiceMascara.getBigDecimalFromTextField(txtPrecoFabrica.getText(), 2);
        BigDecimal margem = ServiceMascara.getBigDecimalFromTextField(txtMargem.getText(), 2);
        BigDecimal prcConsumidor = prcFabrica.multiply(margem).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP)
                .add(prcFabrica);
        txtPrecoConsumidor.setText(prcConsumidor.setScale(2, RoundingMode.HALF_UP).toString());
        resultVlrLucroBruto();
        resultVlrComissaoReal();
    }


    @SuppressWarnings("Duplicates")
    private void resultVlrLucroBruto() {
        BigDecimal prcFabrica = ServiceMascara.getBigDecimalFromTextField(txtPrecoFabrica.getText(), 2);
        BigDecimal prcConsumidor = ServiceMascara.getBigDecimalFromTextField(txtPrecoConsumidor.getText(), 2);
        BigDecimal lucroBruto = prcConsumidor.subtract(prcFabrica);
        txtLucroBruto.setText(lucroBruto.setScale(2, RoundingMode.HALF_UP).toString());
        Platform.runLater(() -> resultVlrLucroLiq());
    }

    @SuppressWarnings("Duplicates")
    private void resultVlrLucroLiq() {
        BigDecimal lucroBruto = ServiceMascara.getBigDecimalFromTextField(txtLucroBruto.getText(), 2);
        BigDecimal ultimoImposto = ServiceMascara.getBigDecimalFromTextField(txtPrecoUltimoImpostoSefaz.getText(), 2);
        BigDecimal ultimoFrete = ServiceMascara.getBigDecimalFromTextField(txtPrecoUltimoFrete.getText(), 2);
        BigDecimal comissaoReal = ServiceMascara.getBigDecimalFromTextField(txtComissaoReal.getText(), 2);
        BigDecimal lucroLiquido = lucroBruto.subtract((ultimoFrete.add(comissaoReal).add(ultimoImposto)));
        txtLucroLiquido.setText(lucroLiquido.setScale(2, RoundingMode.HALF_UP).toString());
        Platform.runLater(() -> resultVlrLucratividade());
    }

    @SuppressWarnings("Duplicates")
    private void resultVlrLucratividade() {
        BigDecimal prcConsumidor = ServiceMascara.getBigDecimalFromTextField(txtPrecoConsumidor.getText(), 2);
        BigDecimal lucroLiquido = ServiceMascara.getBigDecimalFromTextField(txtLucroLiquido.getText(), 2);
        BigDecimal lucratividade = lucroLiquido.multiply(new BigDecimal("100")).divide(prcConsumidor, 2, RoundingMode.HALF_UP);
        txtLucratividade.setText(lucratividade.setScale(2).toString());
    }

    @SuppressWarnings("Duplicates")
    private void resultVlrComissaoReal() {
        BigDecimal prcConsumidor = ServiceMascara.getBigDecimalFromTextField(txtPrecoConsumidor.getText(), 2);
        BigDecimal comissaoPorc = ServiceMascara.getBigDecimalFromTextField(txtComissaoPorc.getText(), 2);
        BigDecimal comissao = prcConsumidor.multiply(comissaoPorc).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        txtComissaoReal.setText(comissao.setScale(2, RoundingMode.HALF_UP).toString());
        Platform.runLater(() -> resultVlrLucroLiq());
    }

    /**
     * END
     * calculos
     */

    /**
     * start
     * Getters e setters
     */


    public TreeTableView<Produto> gettTvProduto() {
        return tTvProduto;
    }

    public void settTvProduto(TreeTableView<Produto> tTvProduto) {
        this.tTvProduto = tTvProduto;
    }

    public JFXListView<ProdutoCodigoBarra> getCodigoBarraList() {
        return codigoBarraList;
    }

    public void setCodigoBarraList(JFXListView<ProdutoCodigoBarra> codigoBarraList) {
        this.codigoBarraList = codigoBarraList;
    }

    public AnchorPane getPainelViewCadastroProduto() {
        return painelViewCadastroProduto;
    }

    public void setPainelViewCadastroProduto(AnchorPane painelViewCadastroProduto) {
        this.painelViewCadastroProduto = painelViewCadastroProduto;
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

    public TitledPane getTpnDadoCadastral() {
        return tpnDadoCadastral;
    }

    public void setTpnDadoCadastral(TitledPane tpnDadoCadastral) {
        this.tpnDadoCadastral = tpnDadoCadastral;
    }

    public JFXTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JFXTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JFXTextField getTxtDescricao() {
        return txtDescricao;
    }

    public void setTxtDescricao(JFXTextField txtDescricao) {
        this.txtDescricao = txtDescricao;
    }

    public JFXTextField getTxtPeso() {
        return txtPeso;
    }

    public void setTxtPeso(JFXTextField txtPeso) {
        this.txtPeso = txtPeso;
    }

    public JFXComboBox<UnidadeComercial> getCboUnidadeComercial() {
        return cboUnidadeComercial;
    }

    public void setCboUnidadeComercial(JFXComboBox<UnidadeComercial> cboUnidadeComercial) {
        this.cboUnidadeComercial = cboUnidadeComercial;
    }

    public JFXComboBox<SituacaoNoSistema> getCboSituacaoSistema() {
        return cboSituacaoSistema;
    }

    public void setCboSituacaoSistema(JFXComboBox<SituacaoNoSistema> cboSituacaoSistema) {
        this.cboSituacaoSistema = cboSituacaoSistema;
    }

    public JFXTextField getTxtPrecoFabrica() {
        return txtPrecoFabrica;
    }

    public void setTxtPrecoFabrica(JFXTextField txtPrecoFabrica) {
        this.txtPrecoFabrica = txtPrecoFabrica;
    }

    public JFXTextField getTxtMargem() {
        return txtMargem;
    }

    public void setTxtMargem(JFXTextField txtMargem) {
        this.txtMargem = txtMargem;
    }

    public JFXTextField getTxtPrecoConsumidor() {
        return txtPrecoConsumidor;
    }

    public void setTxtPrecoConsumidor(JFXTextField txtPrecoConsumidor) {
        this.txtPrecoConsumidor = txtPrecoConsumidor;
    }

    public JFXTextField getTxtVarejo() {
        return txtVarejo;
    }

    public void setTxtVarejo(JFXTextField txtVarejo) {
        this.txtVarejo = txtVarejo;
    }

    public JFXTextField getTxtComissaoPorc() {
        return txtComissaoPorc;
    }

    public void setTxtComissaoPorc(JFXTextField txtComissaoPorc) {
        this.txtComissaoPorc = txtComissaoPorc;
    }

    public JFXTextField getTxtLucroBruto() {
        return txtLucroBruto;
    }

    public void setTxtLucroBruto(JFXTextField txtLucroBruto) {
        this.txtLucroBruto = txtLucroBruto;
    }

    public JFXTextField getTxtPrecoUltimoImpostoSefaz() {
        return txtPrecoUltimoImpostoSefaz;
    }

    public void setTxtPrecoUltimoImpostoSefaz(JFXTextField txtPrecoUltimoImpostoSefaz) {
        this.txtPrecoUltimoImpostoSefaz = txtPrecoUltimoImpostoSefaz;
    }

    public JFXTextField getTxtPrecoUltimoFrete() {
        return txtPrecoUltimoFrete;
    }

    public void setTxtPrecoUltimoFrete(JFXTextField txtPrecoUltimoFrete) {
        this.txtPrecoUltimoFrete = txtPrecoUltimoFrete;
    }

    public JFXTextField getTxtComissaoReal() {
        return txtComissaoReal;
    }

    public void setTxtComissaoReal(JFXTextField txtComissaoReal) {
        this.txtComissaoReal = txtComissaoReal;
    }

    public JFXTextField getTxtLucroLiquido() {
        return txtLucroLiquido;
    }

    public void setTxtLucroLiquido(JFXTextField txtLucroLiquido) {
        this.txtLucroLiquido = txtLucroLiquido;
    }

    public JFXTextField getTxtLucratividade() {
        return txtLucratividade;
    }

    public void setTxtLucratividade(JFXTextField txtLucratividade) {
        this.txtLucratividade = txtLucratividade;
    }

    public Label getLblDataCadastro() {
        return lblDataCadastro;
    }

    public void setLblDataCadastro(Label lblDataCadastro) {
        this.lblDataCadastro = lblDataCadastro;
    }

    public Label getLblDataCadastroDiff() {
        return lblDataCadastroDiff;
    }

    public void setLblDataCadastroDiff(Label lblDataCadastroDiff) {
        this.lblDataCadastroDiff = lblDataCadastroDiff;
    }

    public Label getLblDataAtualizacao() {
        return lblDataAtualizacao;
    }

    public void setLblDataAtualizacao(Label lblDataAtualizacao) {
        this.lblDataAtualizacao = lblDataAtualizacao;
    }

    public Label getLblDataAtualizacaoDiff() {
        return lblDataAtualizacaoDiff;
    }

    public void setLblDataAtualizacaoDiff(Label lblDataAtualizacaoDiff) {
        this.lblDataAtualizacaoDiff = lblDataAtualizacaoDiff;
    }

    public JFXComboBox<FiscalCestNcm> getCboFiscalCestNcm() {
        return cboFiscalCestNcm;
    }

    public void setCboFiscalCestNcm(JFXComboBox<FiscalCestNcm> cboFiscalCestNcm) {
        this.cboFiscalCestNcm = cboFiscalCestNcm;
    }

    public JFXTextField getTxtFiscalGenero() {
        return txtFiscalGenero;
    }

    public void setTxtFiscalGenero(JFXTextField txtFiscalGenero) {
        this.txtFiscalGenero = txtFiscalGenero;
    }

    public JFXTextField getTxtFiscalNcm() {
        return txtFiscalNcm;
    }

    public void setTxtFiscalNcm(JFXTextField txtFiscalNcm) {
        this.txtFiscalNcm = txtFiscalNcm;
    }

    public JFXTextField getTxtFiscalCest() {
        return txtFiscalCest;
    }

    public void setTxtFiscalCest(JFXTextField txtFiscalCest) {
        this.txtFiscalCest = txtFiscalCest;
    }

    public JFXComboBox<FiscalCstOrigem> getCboFiscalCstOrigem() {
        return cboFiscalCstOrigem;
    }

    public void setCboFiscalCstOrigem(JFXComboBox<FiscalCstOrigem> cboFiscalCstOrigem) {
        this.cboFiscalCstOrigem = cboFiscalCstOrigem;
    }

    public JFXComboBox<FiscalIcms> getCboFiscalIcms() {
        return cboFiscalIcms;
    }

    public void setCboFiscalIcms(JFXComboBox<FiscalIcms> cboFiscalIcms) {
        this.cboFiscalIcms = cboFiscalIcms;
    }

    public JFXComboBox<FiscalPisCofins> getCboFiscalPis() {
        return cboFiscalPis;
    }

    public void setCboFiscalPis(JFXComboBox<FiscalPisCofins> cboFiscalPis) {
        this.cboFiscalPis = cboFiscalPis;
    }

    public JFXComboBox<FiscalPisCofins> getCboFiscalCofins() {
        return cboFiscalCofins;
    }

    public void setCboFiscalCofins(JFXComboBox<FiscalPisCofins> cboFiscalCofins) {
        this.cboFiscalCofins = cboFiscalCofins;
    }

    public Circle getImgCirculo() {
        return imgCirculo;
    }

    public void setImgCirculo(Circle imgCirculo) {
        this.imgCirculo = imgCirculo;
    }

    public ServiceAlertMensagem getAlertMensagem() {
        return alertMensagem;
    }

    public void setAlertMensagem(ServiceAlertMensagem alertMensagem) {
        this.alertMensagem = alertMensagem;
    }

    public EventHandler getEventHandlerCadastroProduto() {
        return eventHandlerCadastroProduto;
    }

    public void setEventHandlerCadastroProduto(EventHandler eventHandlerCadastroProduto) {
        this.eventHandlerCadastroProduto = eventHandlerCadastroProduto;
    }

    public StatusBarProduto getStatusBar() {
        return statusBar.get();
    }

    public void setStatusBar(StatusBarProduto statusBar) {
        this.statusBar.set(statusBar);
    }

    public ObjectProperty<StatusBarProduto> statusBarProperty() {
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

    public ProdutoDAO getProdutoDAO() {
        return produtoDAO;
    }

    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        if (produto == null)
            produto = new Produto();
        this.produto = produto;
        if (getStatusBar().equals(StatusBarProduto.PESQUISA))
            exibirProduto();
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

    public ObservableList<ProdutoCodigoBarra> getCodigoBarraObservableList() {
        return codigoBarraObservableList;
    }

    public void setCodigoBarraObservableList(ObservableList<ProdutoCodigoBarra> codigoBarraObservableList) {
        this.codigoBarraObservableList = codigoBarraObservableList;
    }

    public String getStpDtCad() {
        return stpDtCad.get();
    }

    public void setStpDtCad(String stpDtCad) {
        this.stpDtCad.set(String.format("data cadastro: %s", stpDtCad));
    }

    public StringProperty stpDtCadProperty() {
        return stpDtCad;
    }

    public String getStpDtCadDiff() {
        return stpDtCadDiff.get();
    }

    public void setStpDtCadDiff(String stpDtCadDiff) {
        this.stpDtCadDiff.set(String.format("tempo cadastro: %s", stpDtCadDiff));
    }

    public StringProperty stpDtCadDiffProperty() {
        return stpDtCadDiff;
    }

    public String getStpDtAtualiz() {
        return stpDtAtualiz.get();
    }

    public void setStpDtAtualiz(String stpDtAtualiz) {
        this.stpDtAtualiz.set(String.format("data atualização: %s", stpDtAtualiz));
    }

    public StringProperty stpDtAtualizProperty() {
        return stpDtAtualiz;
    }

    public String getStpDtAtualizDiff() {
        return stpDtAtualizDiff.get();
    }

    public void setStpDtAtualizDiff(String stpDtAtualizDiff) {
        this.stpDtAtualizDiff.set(String.format("tempo cadastro: %s", stpDtAtualizDiff));
    }

    public StringProperty stpDtAtualizDiffProperty() {
        return stpDtAtualizDiff;
    }

    public boolean isIsValido() {
        return isValido.get();
    }

    public void setIsValido(boolean isValido) {
        this.isValido.set(isValido);
    }

    public BooleanProperty isValidoProperty() {
        return isValido;
    }


    /**
     * END
     * Getters e setters
     */


}