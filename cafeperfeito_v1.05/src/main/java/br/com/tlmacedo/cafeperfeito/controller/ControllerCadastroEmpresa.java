package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.CargoDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.MunicipioDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.UfDAO;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelEmpresa;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelEmpresaProdutoValor;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.*;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.FormatListCell_EmailHomePage;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.FormatListCell_Endereco;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.FormatListCell_InfoReceitaFederal;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.FormatListCell_Telefone;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.view.ViewCadastroEmpresa;
import com.jfoenix.controls.*;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.*;
import static br.com.tlmacedo.cafeperfeito.model.vo.enums.CriteriosValidationFields.*;
import static br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema.TCONFIG;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-01-22
 * Time: 10:32
 */

@SuppressWarnings("Duplicates")
public class ControllerCadastroEmpresa implements Initializable, ModeloCafePerfeito {

    public AnchorPane painelViewCadastroEmpresa;
    public TitledPane tpnCadastroEmpresa;
    public JFXTextField txtPesquisaEmpresa;
    public JFXComboBox<EmpresaTipo> cboFiltroTipo;
    public JFXComboBox<SituacaoNoSistema> cboFiltroSituacao;
    public TreeTableView<Empresa> ttvEmpresa;
    public Label lblStatus;
    public Label lblRegistrosLocalizados;
    public TitledPane tpnDadoCadastral;
    public JFXComboBox<ClassificacaoJuridica> cboClassificacaoJuridica;
    public JFXTextField txtCNPJ;
    public JFXCheckBox chkIeIsento;
    public JFXTextField txtIE;
    public JFXTextField txtRazao;
    public JFXTextField txtFantasia;
    public JFXComboBox<SituacaoNoSistema> cboSituacaoSistema;
    public JFXCheckBox chkIsCliente;
    public JFXCheckBox chkIsFornecedor;
    public JFXCheckBox chkIsTransportadora;
    public Label lblDataCadastro;
    public Label lblDataCadastroDiff;
    public Label lblDataAtualizacao;
    public Label lblDataAtualizacaoDiff;
    public TitledPane tpnEndereco;
    public JFXListView<Endereco> listEndereco;
    public JFXTextField txtEndCEP;
    public JFXTextField txtEndLogradouro;
    public JFXTextField txtEndNumero;
    public JFXTextField txtEndComplemento;
    public JFXTextField txtEndBairro;
    public JFXComboBox<Uf> cboEndUF;
    public JFXComboBox<Municipio> cboEndMunicipio;
    public JFXTextField txtEndPontoReferencia;
    public JFXListView<EmailHomePage> listEmailHomePage;
    public JFXListView<Telefone> listTelefone;
    public TitledPane tpnPessoaContato;
    public JFXListView<Contato> listContatoNome;
    public JFXListView<EmailHomePage> listContatoEmailHomePage;
    public JFXListView<Telefone> listContatoTelefone;
    public JFXTextField txtPrazo;
    public JFXCheckBox chkDiaUtil;
    public JFXTextField txtLimite;
    public TableView<EmpresaProdutoValor> tvEmpresaProdutoValor;
    public TitledPane tpnObservacoes;
    public JFXTextArea txaObservacoes;
    public Label lblDataAbertura;
    public Label lblDataAberturaDiff;
    public Label lblNaturezaJuridica;
    public TitledPane tpnReceitaAtividadePrincipal;
    public JFXListView<InfoReceitaFederal> listAtividadePrincipal;
    public TitledPane tpnReceitaAtividadeSecundaria;
    public JFXListView<InfoReceitaFederal> listAtividadeSecundaria;
    public TitledPane tpnReceitaQsa;
    public JFXListView<InfoReceitaFederal> listQsaReceitaFederal;
    boolean tabCarregada = false;

    private StringProperty stpDtCad = new SimpleStringProperty("");
    private StringProperty stpDtCadDiff = new SimpleStringProperty("");
    private StringProperty stpDtAtualiz = new SimpleStringProperty("");
    private String nomeTab = "";
    private StringProperty stpDtAtualizDiff = new SimpleStringProperty("");
    private StringProperty stpNatJuridica = new SimpleStringProperty("");
    private BooleanProperty isValido = new SimpleBooleanProperty(false);

    private TabModelEmpresa modelEmpresa;
    private TabModelEmpresaProdutoValor modelEmpresaProdutoValor;
    private StringProperty stpDtAbert = new SimpleStringProperty("");
    private StringProperty stpDtAbertDiff = new SimpleStringProperty("");
    private ServiceAlertMensagem alertMensagem;
    private EventHandler eventHandlerCadastroEmpresa;
    private ObjectProperty<StatusBarEmpresa> statusBar = new SimpleObjectProperty<>(StatusBarEmpresa.PESQUISA);
    private String nomeController = "cadastroEmpresa";
    private List<Pair> listaTarefa = new ArrayList<>();
    private EmpresaDAO empresaDAO = new EmpresaDAO();
    private Empresa empresa;
    private ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList(new EmpresaDAO().getAll(Empresa.class, "razao", null, null, null));
    private FilteredList<Empresa> empresaFilteredList = new FilteredList<>(empresaObservableList);
    private ObservableList<EmpresaProdutoValor> empresaProdutoValorObservableList = FXCollections.observableArrayList();
    private FilteredList<Municipio> municipioFilteredList;
    private Endereco endereco;
    private Contato contato;
    private ObservableList<Endereco> enderecoObservableList = FXCollections.observableArrayList();
    private ObservableList<EmailHomePage> emailHomePageObservableList = FXCollections.observableArrayList();
    private ObservableList<Telefone> telefoneObservableList = FXCollections.observableArrayList();
    private ObservableList<Contato> contatoObservableList = FXCollections.observableArrayList();
    private ObservableList<EmailHomePage> contatoEmailHomePageObservableList = FXCollections.observableArrayList();
    private ObservableList<Telefone> contatoTelefoneObservableList = FXCollections.observableArrayList();
    private ObservableList<InfoReceitaFederal> infoReceitaFederalObservableList = FXCollections.observableArrayList();
    private ObjectProperty<Uf> objUfPrincipal = new SimpleObjectProperty();
    private ObjectProperty<ClassificacaoJuridica> objClassificacaoJuridica = new SimpleObjectProperty();
    private StringProperty maskIe = new SimpleStringProperty(ServiceMascara.getMascaraIE(TCONFIG.getInfLoja().getUf()));
    private ServiceMascara formatCnpj, formatIe;
    private Pattern pattern;
    private Matcher matcher;
    private Telefone telAntigo, telNovo, telContAntigo, telContNovo;
    private EmailHomePage emailHomePageAntigo, emailHomePageNovo, emailHomePageContatoAntigo, emailHomePageContatoNovo;
    private Contato contatoAntigo, contatoNovo;

    ContextMenu contextMenuEmailFinalidade = null;
    MenuItem[] menuItensEmailFinalidade;


    @Override
    public void fechar() {
        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().remove(ViewCadastroEmpresa.getTab());
        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroEmpresa());
    }

    @Override
    public void criarObjetos() {
        setNomeTab(ViewCadastroEmpresa.getTituloJanela());
        getListaTarefa().add(new Pair("criarTabela", "criando tabela de " + getNomeController()));
        criarMenus();
    }

    @Override
    public void preencherObjetos() {
        getListaTarefa().add(new Pair("vinculandoObjetosTabela", "vinculando objetos a tableModel"));
        getListaTarefa().add(new Pair("preencherTabela", "preenchendo tabela " + getNomeController()));

        getListaTarefa().add(new Pair("preencherCombos", "carregando informações do formulario"));

        setTabCarregada(new ServiceSegundoPlano().tarefaAbreCadastro(taskCadastroEmpresa(), getListaTarefa().size(),
                String.format("Abrindo %s", getNomeTab())));

        setFormatCnpj(new ServiceMascara());
        getFormatCnpj().fieldMask(getTxtCNPJ(), MASK_CNPJ);
        setFormatIe(new ServiceMascara());
        getFormatIe().fieldMask(getTxtIE(), ServiceMascara.getMascaraIE(TCONFIG.getInfLoja().getUf()));
    }


    @Override
    public void fatorarObjetos() {
        ServiceMascara.fatorarColunaCheckBox(TabModelEmpresa.getColunaIsCliente());
        ServiceMascara.fatorarColunaCheckBox(TabModelEmpresa.getColunaIsFornecedor());
        ServiceMascara.fatorarColunaCheckBox(TabModelEmpresa.getColunaIsTransportadora());
        getListEndereco().setCellFactory(param -> new FormatListCell_Endereco());
        getListEmailHomePage().setCellFactory(param -> new FormatListCell_EmailHomePage());
        getListTelefone().setCellFactory(param -> new FormatListCell_Telefone());
        getListAtividadePrincipal().setCellFactory(param -> new FormatListCell_InfoReceitaFederal());
        getListAtividadeSecundaria().setCellFactory(param -> new FormatListCell_InfoReceitaFederal());
        getListQsaReceitaFederal().setCellFactory(param -> new FormatListCell_InfoReceitaFederal());
        getListContatoTelefone().setCellFactory(param -> new FormatListCell_Telefone());
        getListContatoEmailHomePage().setCellFactory(param -> new FormatListCell_EmailHomePage());
    }


    @Override
    public void escutarTecla() {

//        if (statusBarProperty().get() == null)
//            setStatusBar(StatusBarEmpresa.PESQUISA);
        ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());

        getLblStatus().textProperty().bind(Bindings.createStringBinding(() -> {
            switch (statusBarProperty().get()) {
                case INCLUIR:
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnCadastroEmpresa().getContent(), true);
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnDadoCadastral().getContent(), false);
                    limparCampos();
                    setEmpresa(new Empresa());
                    getCboClassificacaoJuridica().requestFocus();
                    getCboClassificacaoJuridica().getSelectionModel().selectLast();
                    break;
                case EDITAR:
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnCadastroEmpresa().getContent(), true);
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnDadoCadastral().getContent(), false);
                    try {
                        setEmpresa(getEmpresa().clone());
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                    getTxtCNPJ().requestFocus();
                    break;
                case PESQUISA:
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnCadastroEmpresa().getContent(), false);
                    ServiceCampoPersonalizado.fieldDisable((AnchorPane) getTpnDadoCadastral().getContent(), true);
                    limparCampos();
                    getTxtPesquisaEmpresa().requestFocus();
                    break;
            }
            String tmpStatusBar = statusBarProperty().get().getDescricao();
            if (!isValidoProperty().get())
                tmpStatusBar = tmpStatusBar.replace("[F2-Incluir]", "").replace("[F5-Atualizar]", "").replaceAll("    ", "  ");
            ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(tmpStatusBar);
//            ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());
            return String.format("[%s]", statusBarProperty().get());
        }, statusBarProperty()));

        isValidoProperty().addListener((ov, o, n) -> {
            if (!n)
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao().replace("[F2-Incluir]", "").replace("[F5-Atualizar]", "").replaceAll("    ", "  "));
            else
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(statusBarProperty().get().getDescricao());
        });

        getTtvEmpresa().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            if (n.getValue().getRazao() != null)
                setEmpresa(n.getValue());
            else
                setEmpresa(n.getParent().getValue());
        });

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size() == 0) return;
            if (ControllerPrincipal.ctrlPrincipal.getTabSelecionada().equals(getNomeTab())) {
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroEmpresa());
                ControllerPrincipal.ctrlPrincipal.getServiceStatusBar().atualizaStatusBar(getStatusBar().getDescricao());
            } else {
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroEmpresa());
            }
        });

        getListEndereco().setItems(getEnderecoObservableList());
        getListEmailHomePage().setItems(getEmailHomePageObservableList());
        getListEmailHomePage().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

        });
        getListTelefone().setItems(getTelefoneObservableList());
        getListContatoNome().setItems(getContatoObservableList());
        getListContatoEmailHomePage().setItems(getContatoEmailHomePageObservableList());
        getListContatoTelefone().setItems(getContatoTelefoneObservableList());
        getListAtividadePrincipal().setItems(getInfoReceitaFederalObservableList().stream()
                .filter(principal -> principal.getTipo() == AtividadeReceitaFederalTipo.PRINCIPAL)
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        getListAtividadeSecundaria().setItems(getInfoReceitaFederalObservableList().stream()
                .filter(secundaria -> secundaria.getTipo() == AtividadeReceitaFederalTipo.SECUNDARIA)
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        getListQsaReceitaFederal().setItems(getInfoReceitaFederalObservableList().stream()
                .filter(qsa -> qsa.getTipo() == AtividadeReceitaFederalTipo.QSA)
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));


        getListEmailHomePage().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            contextMenuEmailFinalidade.hide();
//            getListContatoEmailHomePage().setContextMenu(null);
        });

        getListEmailHomePage().addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
            if (getListEmailHomePage().getSelectionModel().getSelectedItem() == null) return;
            if (getListEmailHomePage().getSelectionModel().getSelectedItem().isMail()) {
//                String tipo = getListEmailHomePage().getSelectionModel().getSelectedItem().getFinalidade();
//
//                contextMenuEmailFinalidade.getItems().stream()
//                        .forEach(menuItem -> {
//                            if (tipo.contains(String.valueOf(FinalidadeEmailTelefone.PRINCIPAL.getCod())))
//                        });
//                getListEmailHomePage().setContextMenu(contextMenuEmailFinalidade);
                contextMenuEmailFinalidade.show(getListEmailHomePage(), event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });

        setEventHandlerCadastroEmpresa(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
//                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() < 0)
//                    return;
//                if (!ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().getText().equals(ViewCadastroEmpresa.getTituloJanela()))
//                    return;
                switch (event.getCode()) {
                    case F1:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        setStatusBar(StatusBarEmpresa.INCLUIR);
                        break;
                    case F2:
                    case F5:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        if (!guardarEmpresa()) break;
                        if (!validarDados()) break;
                        if (salvarEmpresa()) {
                            switch (getStatusBar()) {
                                case INCLUIR:
                                    getEmpresaObservableList().add(getEmpresa());
                                    break;
                                case EDITAR:
                                    if (getTtvEmpresa().getSelectionModel().getSelectedItem().getValue() != getEmpresa())
                                        getModelEmpresa().setEmpresaObservableList(getEmpresa());
                                    break;
                            }
                            setStatusBar(StatusBarEmpresa.PESQUISA);
                        }
                        break;
                    case F3:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        setAlertMensagem(new ServiceAlertMensagem());
                        getAlertMensagem().setStrIco("ic_cadastro_empresa_cancel_24dp.png");
                        getAlertMensagem().setCabecalho(
                                String.format("Cancelar %s",
                                        (getStatusBar().equals(StatusBarEmpresa.INCLUIR) ? "inclusão" : "edição")
                                )
                        );
                        getAlertMensagem().setPromptText(
                                String.format("%s, deseja cancelar %s do cadastro de empresa?",
                                        LogadoInf.getUserLog().getApelido(),
                                        (getStatusBar().equals(StatusBarEmpresa.INCLUIR) ? "inclusão" : "edição")
                                )
                        );
                        if (getAlertMensagem().getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
                        if (getStatusBar().equals(StatusBarEmpresa.EDITAR))
                            setEmpresa(getTtvEmpresa().getSelectionModel().getSelectedItem().getValue());
                        setStatusBar(StatusBarEmpresa.PESQUISA);
                        break;
                    case F4:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        if (!getTtvEmpresa().isFocused() || getEmpresa().getId() <= 0) break;
                        setStatusBar(StatusBarEmpresa.EDITAR);
                        break;
                    case F6:
                        if (!event.isShiftDown()) {
                            if (!teclaFuncaoDisponivel(event.getCode())) return;
                            getTxtCNPJ().requestFocus();
                        } else {
                            if (getStatusBar().equals(StatusFormulario.PESQUISA)) return;
                            keyShiftF6();
                        }
                        break;
                    case F7:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getTxtPesquisaEmpresa().requestFocus();
                        break;
                    case F8:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getCboFiltroTipo().requestFocus();
                        break;
                    case F9:
                        if (!getStatusBar().equals(StatusFormulario.PESQUISA)) return;
                        getCboFiltroSituacao().requestFocus();
                        break;
                    case F10:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getListEndereco().requestFocus();
                        break;
                    case F11:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        getListContatoNome().requestFocus();
                        break;
                    case F12:
                        if (!teclaFuncaoDisponivel(event.getCode())) return;
                        fechar();
                        break;
                    case Z:
                        if (getStatusBar().equals(StatusFormulario.PESQUISA) || !event.isControlDown()) return;
                        keyCtrlZ();
                        break;
                    case HELP:
                        if (getStatusBar().equals(StatusFormulario.PESQUISA)) return;
                        keyInsert();
                        break;
                    case DELETE:
                        if (getStatusBar().equals(StatusFormulario.PESQUISA)) return;
                        keyDelete();
                        break;
                }
                if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() > 0)
                    ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem().setOnCloseRequest(event1 -> {
                        if (!getStatusBar().equals(StatusBarEmpresa.PESQUISA)) {
                            event1.consume();
                        }
                    });
            }
        });

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroEmpresa());

        getTxtPesquisaEmpresa().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            getTtvEmpresa().requestFocus();
            getTtvEmpresa().getSelectionModel().selectFirst();
        });

        getLblDataCadastro().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtCadProperty().get(), stpDtCadProperty()));
        getLblDataCadastroDiff().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtCadDiffProperty().get(), stpDtCadDiffProperty()));
        getLblDataAtualizacao().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtAtualizProperty().get(), stpDtAtualizProperty()));
        getLblDataAtualizacaoDiff().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtAtualizDiffProperty().get(), stpDtAtualizDiffProperty()));


        getChkIeIsento().selectedProperty().addListener((ov, o, n) -> {
            if (getStatusBar().equals(StatusBarEmpresa.PESQUISA)) return;
            getTxtIE().setDisable(n);
            getTxtIE().setText(n ? "" : getEmpresa().getIe());
        });

        getCboFiltroTipo().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (!getCboFiltroTipo().isDisable() && event.getCode() == KeyCode.ENTER)
                getTxtPesquisaEmpresa().requestFocus();
        });

        getCboFiltroSituacao().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (!getCboFiltroSituacao().isDisable() && event.getCode() == KeyCode.ENTER)
                getTxtPesquisaEmpresa().requestFocus();
        });

        getCboEndUF().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            if (getListEndereco().getSelectionModel().getSelectedItem() != null)
                if (getListEndereco().getSelectionModel().getSelectedItem().getTipo().equals(EnderecoTipo.PRINCIPAL))
                    setObjUfPrincipal(n);
            getMunicipioFilteredList().setPredicate(municipio -> {
                if (municipio.getUf().getId() == n.getId()) return true;
                return false;
            });
        });

        getCboClassificacaoJuridica().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            setObjClassificacaoJuridica(n);
            if (n.equals(ClassificacaoJuridica.PESSOAFISICA)) {
                getTxtCNPJ().setPromptText("C.P.F.");
                getTxtIE().setPromptText("RG");
                getTxtRazao().setPromptText("Nome");
                getTxtFantasia().setPromptText("Apelido");
                getFormatCnpj().setMascara(MASK_CPF);
            }
            if (n.equals(ClassificacaoJuridica.PESSOAJURIDICA)) {
                getTxtCNPJ().setPromptText("C.N.P.J.");
                getTxtIE().setPromptText("IE");
                getTxtRazao().setPromptText("Razão");
                getTxtFantasia().setPromptText("Fantasia");
                getFormatCnpj().setMascara(MASK_CNPJ);
            }
            getTxtCNPJ().setText(getTxtCNPJ().getText().replaceAll("\\d", ""));
        });

        maskIeProperty().bind(Bindings.createStringBinding(() -> {
                    if (getObjClassificacaoJuridica() == null)
                        return null;
                    if (getObjClassificacaoJuridica().equals(ClassificacaoJuridica.PESSOAFISICA)) {
                        return ServiceMascara.getRgMask(14);
                    } else {
                        return ServiceMascara.getMascaraIE(
                                getObjUfPrincipal() != null
                                        ? getObjUfPrincipal().getSigla()
                                        : TCONFIG.getInfLoja().getUf());
                    }
                }, objClassificacaoJuridicaProperty(), objUfPrincipalProperty()
        ));

        maskIeProperty().addListener((ov, o, n) -> {
            getFormatIe().setMascara(n);
            if (getTxtIE().getText().equals("") || getChkIeIsento().isSelected()) return;
            getTxtIE().setText(getTxtIE().getText().replaceAll("\\D", ""));
        });

        getMunicipioFilteredList().addListener((ListChangeListener<Municipio>) c -> {
            getCboEndMunicipio().getItems().setAll(getMunicipioFilteredList());
            getCboEndMunicipio().getSelectionModel().selectFirst();
        });


        getTxtCNPJ().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setPattern(Pattern.compile(REGEX_CNPJ_CPF, Pattern.CASE_INSENSITIVE));
                setMatcher(getPattern().matcher(getTxtCNPJ().getText()));
                if (getMatcher().find()) {
                    if (!ServiceValidarDado.isCnpjCpfValido(getMatcher().group())) {
                        setAlertMensagem(new ServiceAlertMensagem());
                        getAlertMensagem().setCabecalho("Dado inválido!");
                        getAlertMensagem().setPromptText(String.format("%s, o %s: [%s] é inválido!",
                                StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                                getTxtCNPJ().getPromptText(), getMatcher().group()));
                        getAlertMensagem().setStrIco("ic_webservice_24dp.png");
                        getAlertMensagem().getRetornoAlert_OK();
                        getTxtCNPJ().requestFocus();
                        return;
                    } else if (getModelEmpresa().jaExiste(getMatcher().group(), getEmpresa().getId(), true)) {
                        getTxtCNPJ().requestFocus();
                        return;
                    } else {
                        if (getCboClassificacaoJuridica().getSelectionModel().getSelectedIndex() == ClassificacaoJuridica.PESSOAJURIDICA.getCod()
                                && new ServiceConsultaWebServices().getSistuacaoCNPJ_receitaWs(getEmpresa(), getMatcher().group())) {
                            exibirEmpresa();
                        }
                        getTxtIE().requestFocus();
                    }
                }
            }
        });

        getTxtEndCEP().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setPattern(Pattern.compile(REGEX_CEP, Pattern.CASE_INSENSITIVE));
                setMatcher(getPattern().matcher(getTxtEndCEP().getText()));
                if (getMatcher().find())
                    if (new ServiceConsultaWebServices().getEnderecoCep_postmon(getListEndereco().getSelectionModel().getSelectedItem(), getMatcher().group())) {
                        exibirEndereco();
                        getTxtEndNumero().requestFocus();
                    } else {
                        getTxtEndCEP().requestFocus();
                    }
            }
        });

        getListEndereco().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            if (!getStatusBar().equals(StatusFormulario.PESQUISA))
                if (o != null && n != o)
                    guardarEndereco(o);
            if (n != null && n != o)
                setEndereco(n);
        });

        getEnderecoObservableList().addListener((ListChangeListener<Endereco>) c -> {
            getTpnEndereco().setText(
                    String.format(" '%d' endereço%2$s cadastrado%2$s.",
                            getEnderecoObservableList().size(),
                            getEnderecoObservableList().size() <= 1 ? "" : "s"
                    )
            );
            if (getEnderecoObservableList().size() == 0)
                limparEndereco();
        });

        getContatoObservableList().addListener((ListChangeListener<Contato>) c -> {
            getTpnPessoaContato().setText(
                    String.format(" '%d' contato%2$s cadastrado%2$s.",
                            getContatoObservableList().size(),
                            getContatoObservableList().size() <= 1 ? "" : "s"
                    )
            );
            setTelContNovo(null);
            setTelContAntigo(null);
            setEmailHomePageContatoNovo(null);
            setEmailHomePageContatoAntigo(null);
        });

        getListContatoNome().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) {
                getContatoEmailHomePageObservableList().clear();
                getContatoTelefoneObservableList().clear();
            } else {
                getTpnPessoaContato().setText(
                        String.format("%s [%s]",
                                getTpnPessoaContato().getText().substring(0, getTpnPessoaContato().getText().indexOf(".") + 1),
                                n)
                );
                getContatoEmailHomePageObservableList().setAll(n.getEmailHomePageList());
                getContatoTelefoneObservableList().setAll(n.getTelefoneList());
            }
        });

//        getEmailHomePageObservableList().addListener((ListChangeListener<? super EmailHomePage>) c -> {
//
//        });

        getLblNaturezaJuridica().textProperty().bind(Bindings.createStringBinding(() ->
                stpNatJuridicaProperty().get(), stpNatJuridicaProperty()));
        getLblDataAbertura().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtAbertProperty().get(), stpDtAbertProperty()));
        getLblDataAberturaDiff().textProperty().bind(Bindings.createStringBinding(() ->
                stpDtAbertDiffProperty().get(), stpDtAbertDiffProperty()));
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarObjetos();
        preencherObjetos();
        escutarTecla();
        fatorarObjetos();
        setStatusBar(StatusBarEmpresa.PESQUISA);
        ServiceCampoPersonalizado.fieldMask(getPainelViewCadastroEmpresa());

        Platform.runLater(() -> {
            if (!isTabCarregada())
                fechar();
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F7));
        });
    }

    Task taskCadastroEmpresa() {
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
                            setModelEmpresa(new TabModelEmpresa());
                            getModelEmpresa().tabela();

                            setModelEmpresaProdutoValor(new TabModelEmpresaProdutoValor());
                            getModelEmpresaProdutoValor().tabela();
                            break;

                        case "vinculandoObjetosTabela":
                            getModelEmpresa().setLblRegistrosLocalizados(getLblRegistrosLocalizados());
                            getModelEmpresa().setCboFiltroTipo(getCboFiltroTipo());
                            getModelEmpresa().setCboFiltroSituacao(getCboFiltroSituacao());
                            getModelEmpresa().setTtvEmpresa(getTtvEmpresa());
                            getModelEmpresa().setTxtPesquisaEmpresa(getTxtPesquisaEmpresa());
                            getModelEmpresa().setEmpresaObservableList(getEmpresaObservableList());
                            getModelEmpresa().setEmpresaFilteredList(getEmpresaFilteredList());
                            getModelEmpresa().escutaLista();

                            getModelEmpresaProdutoValor().setTvEmpresaProdutoValor(getTvEmpresaProdutoValor());
                            getModelEmpresaProdutoValor().setEmpresaProdutoValorObservableList(getEmpresaProdutoValorObservableList());
                            getModelEmpresaProdutoValor().escutaLista();

                            break;

                        case "preencherTabela":
                            getModelEmpresa().preencherTabela();

                            getModelEmpresaProdutoValor().preencherTabela();
                            break;

                        case "preencherCombos":
                            getCboFiltroTipo().getItems().setAll(EmpresaTipo.getList());
                            getCboFiltroSituacao().getItems().setAll(SituacaoNoSistema.getList());

                            getCboClassificacaoJuridica().getItems().setAll(ClassificacaoJuridica.getList());

                            getCboSituacaoSistema().getItems().setAll(SituacaoNoSistema.getList());

                            setMunicipioFilteredList(new FilteredList<>(FXCollections.observableArrayList(new MunicipioDAO().getAll(Municipio.class, "capital DESC, descricao", null, null, null))));
                            getCboEndMunicipio().getItems().setAll(getMunicipioFilteredList());

                            getCboEndUF().getItems().setAll(new UfDAO().getAll(Uf.class, "sigla", null, null, null));
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
     * start
     * Booleans
     */

    private boolean teclaFuncaoDisponivel(KeyCode keyCode) {
        return getStatusBar().getDescricao().contains(keyCode.toString());
    }

//    private boolean validarInformacao() {
//        return (validarEnderecoPrincipal() && validarEmpresa());
//    }

    private boolean guardarEmpresa() {
        try {
            getEmpresa().setClassifJuridica(getCboClassificacaoJuridica().getSelectionModel().getSelectedItem());
            getEmpresa().setCnpj(getTxtCNPJ().getText());
            getEmpresa().setIe(getTxtIE().getText());
            getEmpresa().setIeIsento(getChkIeIsento().isSelected());
            getEmpresa().setRazao(getTxtRazao().getText());
            getEmpresa().setFantasia(getTxtFantasia().getText());
            getEmpresa().setCliente(getChkIsCliente().isSelected());
            getEmpresa().setFornecedor(getChkIsFornecedor().isSelected());
            getEmpresa().setTransportadora(getChkIsTransportadora().isSelected());
            getEmpresa().setSituacao(getCboSituacaoSistema().getSelectionModel().getSelectedItem());

            getEmpresa().setEnderecoList(getEnderecoObservableList());

            getEmpresa().setEmailHomePageList(getEmailHomePageObservableList());

            getEmpresa().setTelefoneList(getTelefoneObservableList());

            getEmpresa().setContatoList(getContatoObservableList());

            setPattern(Pattern.compile(REGEX_DTF_DATA));
            setMatcher(getPattern().matcher(getLblDataAbertura().getText()));
            if (getMatcher().find())
                getEmpresa().setDataAbetura(LocalDate.parse(getMatcher().group(), DTF_DATA).atTime(0, 0, 0));

            if (getLblNaturezaJuridica().getText().length() > 19)
                getEmpresa().setNaturezaJuridica(getLblNaturezaJuridica().getText().substring(19));
            else
                getEmpresa().setNaturezaJuridica("");
            getEmpresa().setObservacoes(getTxaObservacoes().getText());
            getEmpresa().setLimite(ServiceMascara.getBigDecimalFromTextField(getTxtLimite().getText(), 2));
            getEmpresa().setPrazo(Integer.parseInt(getTxtPrazo().getText()));
            getEmpresa().setDiaUtil(getChkDiaUtil().isSelected());
            getEmpresa().setEmpresaProdutoValorList(getEmpresaProdutoValorObservableList());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean guardarEndereco(Endereco endAntigo) {
        try {
            endAntigo.setCep(getTxtEndCEP().getText());
            endAntigo.setLogradouro(getTxtEndLogradouro().getText());
            endAntigo.setNumero(getTxtEndNumero().getText());
            endAntigo.setComplemento(getTxtEndComplemento().getText());
            endAntigo.setBairro(getTxtEndBairro().getText());
            endAntigo.setProx(getTxtEndPontoReferencia().getText());
            endAntigo.setMunicipio(getCboEndMunicipio().getSelectionModel().getSelectedItem());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private void limparEndereco() {
        getTxtEndCEP().setText("");
        getTxtEndLogradouro().setText("");
        getTxtEndNumero().setText("");
        getTxtEndComplemento().setText("");
        getTxtEndBairro().setText("");
        getTxtEndPontoReferencia().setText("");
        getCboEndUF().getSelectionModel().select(0);
    }

    private boolean salvarEmpresa() {
        if (getEmpresa().getId() == 0) {
            getEmpresa().setUsuarioCadastro(LogadoInf.getUserLog());
            getEmpresa().setUsuarioAtualizacao(null);
            getEmpresa().setDataAtualizacao(null);
        } else {
            if (getEmpresa() != getEmpresaObservableList().stream().filter(empresa1 -> empresa1.getId() == getEmpresa().getId()).findFirst().orElse(null)) {
                getEmpresa().setUsuarioAtualizacao(LogadoInf.getUserLog());
                getEmpresa().setDataAtualizacao(LocalDateTime.now());
            }
        }
        setEmpresa(getEmpresaDAO().persiste(getEmpresa()));
        return (getEmpresaDAO() != null);
    }

    /**
     * END
     * Boolean
     */

    /**
     * start
     * List<>
     */

    private List<EnderecoTipo> getTipoEnderecoDisponivel() {
        return new ArrayList<>(EnderecoTipo.getList().stream()
                .filter(tipo -> getEnderecoObservableList().stream()
                        .filter(end -> tipo.equals(end.getTipo()))
                        .count() == 0)
                .collect(Collectors.toList()));
    }

    /**
     * END
     * List<>
     */

    /**
     * start
     * private voids
     */

    private void limparCampos() {
        getTxtPesquisaEmpresa().setText("");
        setStpDtCad("");
        setStpDtCadDiff("");
        setStpDtAtualiz("");
        setStpDtAtualizDiff("");
        ServiceCampoPersonalizado.fieldClear((AnchorPane) getTpnDadoCadastral().getContent());
        setObjUfPrincipal(new UfDAO().getById(Uf.class, Long.valueOf(TCONFIG.getInfLoja().getCUF())));
    }

    private void exibirEmpresa() {
        setTelNovo(null);
        setTelAntigo(null);
        setEmailHomePageNovo(null);
        setEmailHomePageAntigo(null);

        getEnderecoObservableList().setAll(getEmpresa().getEnderecoList());

        getCboClassificacaoJuridica().getSelectionModel().select(getEmpresa().getClassifJuridica());
        getTxtCNPJ().setText(getEmpresa().getCnpj());
        getChkIeIsento().setSelected(getEmpresa().isIeIsento());
        getTxtIE().setText("");
        if (!getEmpresa().isIeIsento() || getEmpresa().getEnderecoList() == null)
            getTxtIE().setText(getEmpresa().getIe());
        getCboSituacaoSistema().getSelectionModel().select(getEmpresa().getSituacao());
        getTxtRazao().setText(getEmpresa().getRazao());
        getTxtFantasia().setText(getEmpresa().getFantasia());
        getChkIsCliente().setSelected(getEmpresa().isCliente());
        getChkIsFornecedor().setSelected(getEmpresa().isFornecedor());
        getChkIsTransportadora().setSelected(getEmpresa().isTransportadora());

        getEmailHomePageObservableList().setAll(getEmpresa().getEmailHomePageList());
        getTelefoneObservableList().setAll(getEmpresa().getTelefoneList());

        getContatoObservableList().setAll(getEmpresa().getContatoList());

        if (getEmpresa().getNaturezaJuridica() == null)
            setStpNatJuridica("");
        else
            setStpNatJuridica(getEmpresa().getNaturezaJuridica());

        if (getEmpresa().getDataAbetura() == null) {
            setStpDtAbert("");
            setStpDtAbertDiff("");
        } else {
            setStpDtAbert(DTF_DATA.format(getEmpresa().getDataAbetura()));
            setStpDtAbertDiff(ServiceDataHora.getIntervaloData(getEmpresa().getDataAbetura().toLocalDate(), LocalDate.now()));
        }

        if (getEmpresa().getUsuarioCadastro() == null) {
            setStpDtCad("");
            setStpDtCadDiff("");
        } else {
            setStpDtCad(String.format("%s [%02d-%s]",
                    DTF_DATAHORA.format(getEmpresa().getDataCadastro()),
                    getEmpresa().getUsuarioCadastro().getId(),
                    StringUtils.capitalize(getEmpresa().getUsuarioCadastro().getApelido())
            ));
            setStpDtCadDiff(ServiceDataHora.getIntervaloData(getEmpresa().getDataCadastro().toLocalDate(), LocalDate.now()));
        }

        if (getEmpresa().getUsuarioAtualizacao() == null) {
            setStpDtAtualiz("");
            setStpDtAtualizDiff("");
        } else {
            setStpDtAtualiz(String.format("%s [%02d-%s]",
                    DTF_DATAHORA.format(getEmpresa().getDataAtualizacao()),
                    getEmpresa().getUsuarioAtualizacao().getId(),
                    StringUtils.capitalize(getEmpresa().getUsuarioAtualizacao().getApelido())
            ));
            setStpDtAtualizDiff(ServiceDataHora.getIntervaloData(getEmpresa().getDataAtualizacao().toLocalDate(), null));
        }

        getInfoReceitaFederalObservableList().setAll(getEmpresa().getInfoReceitaFederalList());

        getTxaObservacoes().setText(getEmpresa().getObservacoes() != null ? getEmpresa().getObservacoes() : "");

        getTxtLimite().setText(getEmpresa().limiteProperty().get().toString());
        getTxtPrazo().setText(getEmpresa().prazoProperty().getValue().toString());
        getChkDiaUtil().setSelected(getEmpresa().diaUtilProperty().get());

        getEmpresaProdutoValorObservableList().setAll(getEmpresa().getEmpresaProdutoValorList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));

        getListEndereco().getSelectionModel().selectFirst();

        getListContatoNome().getSelectionModel().selectFirst();
    }

    private void exibirEndereco() {
        getCboEndUF().getSelectionModel().select(getCboEndUF().getItems().stream()
                .filter(endUf -> endUf.getSigla().equals(getEndereco().getMunicipio().getUf().getSigla()))
                .findFirst().orElse(null));

        getTpnEndereco().setText(
                String.format("%s [%s]",
                        getTpnEndereco().getText().substring(0, getTpnEndereco().getText().indexOf(".") + 1),
                        getEndereco())
        );

        getTxtIE().setText(getEmpresa().getIe());
        getTxtEndCEP().setText(getEndereco().getCep());
        getTxtEndLogradouro().setText(getEndereco().getLogradouro());
        getTxtEndNumero().setText(getEndereco().getNumero());
        getTxtEndComplemento().setText(getEndereco().getComplemento());
        getTxtEndBairro().setText(getEndereco().getBairro());
        getTxtEndPontoReferencia().setText(getEndereco().getProx());

        getCboEndMunicipio().getSelectionModel().select(getCboEndMunicipio().getItems().stream()
                .filter(municipio -> municipio.getDescricao().equals(getEndereco().getMunicipio().getDescricao()))
                .findFirst().orElse(null));
    }

    private void keyCtrlZ() {
        if (getListEmailHomePage().isFocused()) {
            if (getEmailHomePageAntigo() == null || getEmailHomePageNovo() == null) return;
            changeEmailHomePage(getEmailHomePageNovo(), getEmailHomePageAntigo());
            getListEmailHomePage();
            return;
        }
        if (getListContatoEmailHomePage().isFocused()) {
            if (getEmailHomePageContatoAntigo() == null || getEmailHomePageContatoNovo() == null) return;
            changeEmailHomePage(getEmailHomePageContatoNovo(), getEmailHomePageContatoAntigo());
            getListContatoEmailHomePage().refresh();
            return;
        }
        if (getListTelefone().isFocused()) {
            if (getTelAntigo() == null || getTelNovo() == null) return;
            changeTelefone(getTelNovo(), getTelAntigo());
            getListTelefone().refresh();
            return;
        }
        if (getListContatoTelefone().isFocused()) {
            if (getTelContAntigo() == null || getTelContNovo() == null) return;
            changeTelefone(getTelContNovo(), getTelContAntigo());
            getListContatoTelefone().refresh();
            return;
        }
        if (getListContatoNome().isFocused()) {
            if (getContatoAntigo() == null || getContatoNovo() == null) return;
            changeContato(getContatoNovo(), getContatoAntigo());
            getListContatoNome().refresh();
            return;
        }
    }

    private void changeTelefone(Telefone tel1, Telefone tel2) {
        tel1.setDescricao(tel2.getDescricao());
        tel1.setTelefoneOperadora(tel2.getTelefoneOperadora());
    }

    private void changeEmailHomePage(EmailHomePage email1, EmailHomePage email2) {
        email1.setDescricao(email2.getDescricao());
        email1.setMail(email2.isMail());
    }

    private void changeContato(Contato contato1, Contato contato2) {
        contato1.setDescricao(contato2.getDescricao());
        contato1.setCargo(contato2.getCargo());
    }

    private void keyShiftF6() {
        if (getListEmailHomePage().isFocused()) {
            setEmailHomePageAntigo(new EmailHomePage());
            changeEmailHomePage(getEmailHomePageAntigo(), getListEmailHomePage().getSelectionModel().getSelectedItem());
            editEmailHomePage(getListEmailHomePage().getSelectionModel().getSelectedItem());
            return;
        }
        if (getListContatoEmailHomePage().isFocused()) {
            setEmailHomePageContatoAntigo(new EmailHomePage());
            changeEmailHomePage(getEmailHomePageContatoAntigo(), getListContatoEmailHomePage().getSelectionModel().getSelectedItem());
            editEmailHomePage(getListContatoEmailHomePage().getSelectionModel().getSelectedItem());
            return;
        }
        if (getListTelefone().isFocused()) {
            setTelAntigo(new Telefone());
            changeTelefone(getTelAntigo(), getListTelefone().getSelectionModel().getSelectedItem());
            editTelefone(getListTelefone().getSelectionModel().getSelectedItem());
            return;
        }
        if (getListContatoTelefone().isFocused()) {
            setTelContAntigo(new Telefone());
            changeTelefone(getTelContAntigo(), getListContatoTelefone().getSelectionModel().getSelectedItem());
            editTelefone(getListContatoTelefone().getSelectionModel().getSelectedItem());
            return;
        }
        if (getListContatoNome().isFocused()) {
            setContatoAntigo(new Contato());
            changeContato(getContatoAntigo(), getListContatoNome().getSelectionModel().getSelectedItem());
            Contato contTmp;
            if ((contTmp = getListContatoNome().getSelectionModel().getSelectedItem()) == null) return;
            setAlertMensagem(new ServiceAlertMensagem());
            getAlertMensagem().setStrIco("ic_user_add_24dp.png");
            getAlertMensagem().setCabecalho("Editar dados [contato]");
            getAlertMensagem().setPromptText(String.format("%s, qual novo nome e cargo para substituir: [%s]?",
                    StringUtils.capitalize(LogadoInf.getUserLog().getApelido()), contTmp));
            Pair<String, ?> pair;
            if ((pair = getAlertMensagem().getRetornoAlert_textField_comboBox(
                    ServiceMascara.getTextoMask(40, null), contTmp.getDescricao(), contTmp.getCargo(),
                    "Contato", new CargoDAO().getAll(Cargo.class, "descricao", null, null, null))
                    .orElse(null)) == null) return;
            contTmp.setDescricao(pair.getKey());
            contTmp.setCargo((Cargo) pair.getValue());
            getListContatoNome().refresh();
            setContatoNovo(contTmp);
            return;
        }
    }

    private void keyInsert() {
        if (getListEndereco().isFocused()) {
            Endereco endTmp = new Endereco();
            if (getListEndereco().getItems().size() == 0) {
                endTmp.setTipo(EnderecoTipo.PRINCIPAL);
            } else {
                setAlertMensagem(new ServiceAlertMensagem());
                getAlertMensagem().setStrIco("ic_endereco_add_24dp.png");
                if (getTipoEnderecoDisponivel().size() <= 0) {
                    getAlertMensagem().setCabecalho("Endereço não disponivél");
                    getAlertMensagem().setPromptText(String.format("%s, o sistema não tem endereço disponível para a empresa: [%s]\nAtualize algum endereço já existente!",
                            StringUtils.capitalize(LogadoInf.getUserLog().getApelido()), getTxtRazao().getText()));
                    getAlertMensagem().getRetornoAlert_OK();
                    return;
                }
                getAlertMensagem().setCabecalho("Adicionar dados [endereço]");
                getAlertMensagem().setPromptText(String.format("%s, selecione o tipo endereço que vai ser adicionado\na empresa: [%s]",
                        LogadoInf.getUserLog().toString(), getTxtRazao().getText()));
                Object obj;
                if ((obj = getAlertMensagem().getRetornoAlert_comboBox(getTipoEnderecoDisponivel()).orElse(null)) == null)
                    return;
                endTmp.setTipo(((EnderecoTipo) obj));
            }
            endTmp.setMunicipio(new MunicipioDAO().getById(Municipio.class, 112L));
            getEnderecoObservableList().add(endTmp);
            getListEndereco().getSelectionModel().selectLast();
            getTxtEndCEP().requestFocus();
            return;
        }
        if (getListEmailHomePage().isFocused() || getListContatoEmailHomePage().isFocused()) {
            addEmailHomePage("");
            return;
        }
        if (getListTelefone().isFocused() || getListContatoTelefone().isFocused()) {
            addTelefone("");
            return;
        }
        if (getListContatoNome().isFocused()) {
            setAlertMensagem(new ServiceAlertMensagem());
            getAlertMensagem().setStrIco("ic_user_add_24dp.png");
            getAlertMensagem().setCabecalho("Adicionar dados [contato]");
            getAlertMensagem().setPromptText(
                    String.format("%s, qual contato a ser adicionado para empresa: [%s] ?",
                            StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                            getTxtRazao().getText()
                    ));
            Pair<String, ?> pair;
            if ((pair = getAlertMensagem().getRetornoAlert_textField_comboBox(
                    ServiceMascara.getTextoMask(40, null), "", null, "Contato",
                    new CargoDAO().getAll(Cargo.class, "descricao", null, null, null))
                    .orElse(null)) == null) return;
            getContatoObservableList().add(new Contato(pair.getKey(), (Cargo) pair.getValue()));
            getListContatoNome().getSelectionModel().selectLast();
            return;
        }
        if (getTvEmpresaProdutoValor().isFocused())
            if (getEmpresaProdutoValorObservableList().stream()
                    .filter(empresaProdutoValor -> empresaProdutoValor.getProduto().getId() == 0)
                    .count() == 0)
                getEmpresaProdutoValorObservableList().add(new EmpresaProdutoValor());
    }

    private void keyDelete() {
        if (getListEndereco().isFocused()) {
            if (getEndereco() == null) return;
            setAlertMensagem(new ServiceAlertMensagem());
            if (getEndereco().getTipo().equals(EnderecoTipo.PRINCIPAL)) {
                getAlertMensagem().setCabecalho("Proteção de dados!");
                getAlertMensagem().setPromptText(String.format("%s, o endereço principal não pode ser deletado!",
                        StringUtils.capitalize(LogadoInf.getUserLog().getApelido())));
                getAlertMensagem().setStrIco("ic_endereco_remove_24dp.png");
                guardarEndereco(getEndereco());
                getAlertMensagem().getRetornoAlert_OK();
            } else {
                getAlertMensagem().setCabecalho("Deletar dados [endereço]");
                getAlertMensagem().setPromptText(String.format("%s, deseja deletar o endereço: [%s]\nda empresa: [%s] ?",
                        StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                        getEndereco(),
                        getTxtRazao().getText()));
                getAlertMensagem().setStrIco("ic_endereco_remove_24dp.png");
                if (getAlertMensagem().getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
                getEnderecoObservableList().remove(getEndereco());
                getListEndereco().getSelectionModel().selectFirst();
            }
            return;
        }
        if (getListEmailHomePage().isFocused() || getListContatoEmailHomePage().isFocused()) {
            Contato contTmp = null;
            EmailHomePage emailHomeTmp = null;
            if (getListEmailHomePage().isFocused())
                emailHomeTmp = getListEmailHomePage().getSelectionModel().getSelectedItem();
            if (getListContatoEmailHomePage().isFocused()) {
                contTmp = getListContatoNome().getSelectionModel().getSelectedItem();
                emailHomeTmp = getListContatoEmailHomePage().getSelectionModel().getSelectedItem();
            }
            if (emailHomeTmp == null || (getListContatoEmailHomePage().isFocused() && contTmp == null)) return;
            setAlertMensagem(new ServiceAlertMensagem());
            getAlertMensagem().setStrIco(emailHomeTmp.isMail() ? "ic_email_remove_24dp.png" : "ic_web_remove_24dp.png");
            getAlertMensagem().setCabecalho(String.format("Deletar dados [%s%s]",
                    StringUtils.capitalize(emailHomeTmp.isMail() ? "Email" : "Home Page"),
                    contTmp == null ? "" : " contato"
            ));
            getAlertMensagem().setPromptText(String.format("%s, deseja deletar %s: [%s]%s\nda empresa: [%s]?",
                    StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                    emailHomeTmp.isMail() ? "\no email" : "\na home page",
                    emailHomeTmp,
                    contTmp == null ? "" : String.format(" para o contato: [%s]", contTmp),
                    getTxtRazao().getText()));
            if (getAlertMensagem().getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
            if (contTmp == null) {
                getEmailHomePageObservableList().remove(emailHomeTmp);
            } else {
                getListContatoEmailHomePage().getItems().remove(emailHomeTmp);
                getContatoObservableList().get(getContatoObservableList().indexOf(contTmp)).getEmailHomePageList().remove(emailHomeTmp);
            }
            return;
        }
        if (getListTelefone().isFocused() || getListContatoTelefone().isFocused()) {
            Contato contTmp = null;
            Telefone telTmp = null;
            if (getListTelefone().isFocused()) {
                telTmp = getListTelefone().getSelectionModel().getSelectedItem();
            } else {
                contTmp = getListContatoNome().getSelectionModel().getSelectedItem();
                telTmp = getListContatoTelefone().getSelectionModel().getSelectedItem();
            }
            if (telTmp == null || (getListContatoTelefone().isFocused() && contTmp == null)) return;
            setAlertMensagem(new ServiceAlertMensagem());
            getAlertMensagem().setStrIco("ic_telefone_24dp.png");
            getAlertMensagem().setCabecalho(String.format("Deletar dados [telefone%s]", contTmp == null ? "" : " contato"));
            getAlertMensagem().setPromptText(String.format("%s, deseja deletar o telefone: [%s]%s\nda empresa: [%s]?",
                    StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                    telTmp,
                    contTmp == null ? "" : String.format("\n para o contato: [%s]", contTmp),
                    getTxtRazao().getText()));
            if (getAlertMensagem().getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
            if (contTmp == null) {
                getTelefoneObservableList().remove(telTmp);
            } else {
                getListContatoTelefone().getItems().remove(telTmp);
                getContatoObservableList().get(getContatoObservableList().indexOf(contTmp)).getTelefoneList().remove(telTmp);
            }
            return;
        }
        if (getListContatoNome().isFocused()) {
            Contato contTmp;
            if ((contTmp = getListContatoNome().getSelectionModel().getSelectedItem()) == null) return;
            setAlertMensagem(new ServiceAlertMensagem());
            getAlertMensagem().setStrIco("ic_user_remove_24dp.png");
            getAlertMensagem().setCabecalho("Deletar dados [contato]");
            getAlertMensagem().setPromptText(String.format("%s, deseja deletar o contato: [%s]\nda empresa: [%s]?",
                    StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                    contTmp,
                    getTxtRazao().getText()));
            if (getAlertMensagem().getRetornoAlert_Yes_No().get() == ButtonType.NO) return;
            getContatoObservableList().remove(contTmp);
            return;
        }
    }

    private void addEmailHomePage(String temp) {
        Contato contTmp = null;
        if (getListContatoEmailHomePage().isFocused())
            contTmp = getListContatoNome().getSelectionModel().getSelectedItem();
        if (getListContatoEmailHomePage().isFocused() && contTmp == null) return;
        setAlertMensagem(new ServiceAlertMensagem());
        getAlertMensagem().setStrIco("ic_web_add_24dp.png");
        getAlertMensagem().setCabecalho(String.format("Adicionar dados [%s%s]",
                "EMAIL/HOME_PAGE",
                contTmp == null ? "" : " contato"
        ));
        getAlertMensagem().setPromptText(String.format("%s, qual o email/home page a ser adiconado %sa empresa: [%s]?",
                StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                contTmp == null ? "\npar" : String.format(" para o contato: [%s]\nd", contTmp),
                getTxtRazao().getText()));
        if ((temp = getAlertMensagem().getRetornoAlert_TextField(
                ServiceMascara.getEmailHomeMask(120), temp, "email/home_page")
                .orElse(null)) == null) return;
        WebTipo webTipTmp;
        if ((webTipTmp = ServiceValidarDado.isEmailHomePageValido(temp, true)) == null) {
            addEmailHomePage(temp);
            return;
        }
        EmailHomePage emailHomePage = new EmailHomePage(temp, (webTipTmp == WebTipo.EMAIL), false, false);
        if (contTmp == null) {
            getEmailHomePageObservableList().add(emailHomePage);
            getListEmailHomePage().getSelectionModel().selectLast();
        } else {
            contTmp.getEmailHomePageList().add(emailHomePage);
            getContatoEmailHomePageObservableList().add(emailHomePage);
            getListContatoEmailHomePage().getSelectionModel().selectLast();
        }
    }

    private void editEmailHomePage(EmailHomePage emailHomeTmp) {
        if (emailHomeTmp == null) return;
//        WebTipo webTipTmp = emailHomeTmp.getDescricao().contains("@") ? WebTipo.EMAIL : WebTipo.HOMEPAGE;
        setAlertMensagem(new ServiceAlertMensagem());
        getAlertMensagem().setStrIco("ic_web_add_24dp.png");
        getAlertMensagem().setCabecalho(String.format("Editar dados [email/home page]"));
        getAlertMensagem().setPromptText(String.format("%s, qual o novo email/home page para substituir [%s]?",
                StringUtils.capitalize(LogadoInf.getUserLog().getApelido()), emailHomeTmp));
        String temp = emailHomeTmp.getDescricao();
        if ((temp = getAlertMensagem().getRetornoAlert_TextField(
                ServiceMascara.getEmailHomeMask(120), temp, "email/home_page")
                .orElse(null)) == null) return;
        WebTipo webTipTmp;
        if ((webTipTmp = ServiceValidarDado.isEmailHomePageValido(temp, true)) == null) {
            editEmailHomePage(emailHomeTmp);
            return;
        }
        emailHomeTmp.setDescricao(temp);
        emailHomeTmp.setMail(webTipTmp == WebTipo.EMAIL);
        if (getListEmailHomePage().isFocused()) {
            getListEmailHomePage().refresh();
            setEmailHomePageNovo(emailHomeTmp);
        }
        if (getListContatoEmailHomePage().isFocused()) {
            getListContatoEmailHomePage().refresh();
            setEmailHomePageContatoNovo(emailHomeTmp);
        }
    }

    private void addTelefone(String temp) {
        Contato contTmp = null;
        if (getListContatoTelefone().isFocused())
            contTmp = getListContatoNome().getSelectionModel().getSelectedItem();
        if (getListContatoTelefone().isFocused() && contTmp == null) return;

        setAlertMensagem(new ServiceAlertMensagem());
        getAlertMensagem().setStrIco("ic_telefone_24dp.png");
        getAlertMensagem().setCabecalho(String.format("Adicionar dados [telefone%s]",
                contTmp == null ? "" : " contato"
        ));
        getAlertMensagem().setPromptText(String.format("%s, qual telefone a ser adicionado %sa empresa: [%s]?",
                StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                contTmp == null ? "\npar" : String.format(" para o contato: [%s]\nd", contTmp),
                getTxtRazao().getText()));
        if (getEndereco() != null)
            if (!String.valueOf(getEndereco().getMunicipio().getDdd()).equals(TCONFIG.getInfLoja().getDdd())
                    && temp == "")
                temp = String.valueOf(getEndereco().getMunicipio().getDdd());
        if ((temp = getAlertMensagem().getRetornoAlert_TextField(MASK_TELEFONE, temp, "Telefone:")
                .orElse(null)) == null) return;
        temp = temp.replaceAll("\\D", "");
        if (!ServiceValidarDado.isTelefoneValido(temp, true)) {
            addTelefone(temp);
            return;
        }
        setPattern(Pattern.compile(REGEX_TELEFONE));
        setMatcher(getPattern().matcher(temp));
        if (getMatcher().find())
            if (getMatcher().group(1) == null)
                temp = TCONFIG.getInfLoja().getDdd() + temp;
        Telefone telTmp = new Telefone(temp,
                new ServiceConsultaWebServices().getTelefone_WsPortabilidadeCelular(temp));
        if (contTmp == null) {
            getTelefoneObservableList().add(telTmp);
            getListTelefone().getSelectionModel().selectLast();
        } else {
            getContato().getTelefoneList().add(telTmp);
            getContatoTelefoneObservableList().add(telTmp);
            getListTelefone().getSelectionModel().selectLast();
        }
    }

    private void editTelefone(Telefone telTmp) {
        if (telTmp == null) return;

        setAlertMensagem(new ServiceAlertMensagem());
        getAlertMensagem().setStrIco("ic_telefone_24dp.png");
        getAlertMensagem().setCabecalho(String.format("Editar dados [telefone]"));
        getAlertMensagem().setPromptText(String.format("%s, qual o novo telefone para substituir [%s]?",
                StringUtils.capitalize(LogadoInf.getUserLog().getApelido()), telTmp));
        String temp = "";
        if (temp.equals("")) temp = telTmp.getDescricao().replaceAll("\\D", "");
        if ((temp = getAlertMensagem().getRetornoAlert_TextField(MASK_TELEFONE, temp, "Telefone:")
                .orElse(null)) == null) return;
        temp = temp.replaceAll("\\D", "");
        if (!ServiceValidarDado.isTelefoneValido(temp, true)) {
            editTelefone(telTmp);
            return;
        }
        setPattern(Pattern.compile(REGEX_TELEFONE));
        setMatcher(getPattern().matcher(temp));
        if (getMatcher().find())
            if (getMatcher().group(1) == null)
                temp = String.valueOf(TCONFIG.getInfLoja().getDdd()) + temp;
        telTmp.setDescricao(temp);
        telTmp.setTelefoneOperadora(new ServiceConsultaWebServices().getTelefone_WsPortabilidadeCelular(temp));
        if (getListTelefone().isFocused()) {
            getListTelefone().refresh();
            setTelNovo(telTmp);
        }
        if (getListContatoTelefone().isFocused()) {
            getListContatoTelefone().refresh();
            setTelContNovo(telTmp);
        }
    }

    private void criarMenus() {
        contextMenuEmailFinalidade = new ContextMenu();
        int qtdMen = 0, qtd = FinalidadeEmailTelefone.getList().size();
        menuItensEmailFinalidade = new MenuItem[qtd * 2];
        for (int i = 0; i < qtd; i++) {
            menuItensEmailFinalidade[qtdMen++] = new MenuItem("✘ " + FinalidadeEmailTelefone.toEnum(i).getDescricao());
            contextMenuEmailFinalidade.getItems().add(menuItensEmailFinalidade[qtdMen - 1]);
            menuItensEmailFinalidade[qtdMen++] = new MenuItem("✔︎ " + FinalidadeEmailTelefone.toEnum(i).getDescricao());
            contextMenuEmailFinalidade.getItems().add(menuItensEmailFinalidade[qtdMen - 1]);
        }
        getListEmailHomePage().setContextMenu(contextMenuEmailFinalidade);

//        FinalidadeEmailTelefone.getList().stream()
//                .forEach(finalidadeEmailTelefone -> {
//                    menuItensEmailFinalidade[finalidadeEmailTelefone.getCod()] = new MenuItem(finalidadeEmailTelefone.getDescricao());
//                    contextMenuEmailFinalidade.getItems().add(menuItensEmailFinalidade[finalidadeEmailTelefone.getCod()]);
//                });
//
//        for (int i = 1; i < menuItensEmailFinalidade.length; i++) {
//            int finalI = i;//✘ ✔
//            menuItensEmailFinalidade[i].setOnAction(event -> {
//                EmailHomePage mail = getListEmailHomePage().getSelectionModel().getSelectedItem();
//                if (menuItensEmailFinalidade[finalI].getText().toLowerCase().contains("✔︎")) {
//                    switch (FinalidadeEmailTelefone.toEnum(finalI)) {
//                        case NFE:
//                            getEmailHomePageObservableList().stream()
//                                    .filter(emailHomePage -> emailHomePage.getFinalidade()
//                                            .contains(String.valueOf(FinalidadeEmailTelefone.NFE.getCod())))
//                                    .forEach(emailHomePage -> emailHomePage.setFinalidade(emailHomePage.getFinalidade()
//                                            .replace(String.valueOf(FinalidadeEmailTelefone.NFE.getCod()), "")));
//                            mail.setFinalidade(mail.getFinalidade() + FinalidadeEmailTelefone.NFE.getCod());
//                            break;
//                        case PRINCIPAL:
//                            getEmailHomePageObservableList().stream()
//                                    .filter(emailHomePage -> emailHomePage.getFinalidade()
//                                            .contains(String.valueOf(FinalidadeEmailTelefone.PRINCIPAL.getCod())))
//                                    .forEach(emailHomePage -> emailHomePage.setFinalidade(emailHomePage.getFinalidade()
//                                            .replace(String.valueOf(FinalidadeEmailTelefone.PRINCIPAL.getCod()),
//                                                    String.valueOf(FinalidadeEmailTelefone.NULL.getCod()))));
//                            mail.setFinalidade(mail.getFinalidade().replace(String.valueOf(FinalidadeEmailTelefone.NULL.getCod()),
//                                    String.valueOf(FinalidadeEmailTelefone.PRINCIPAL.getCod())));
//                            break;
//                    }
//                } else {
//                    switch (FinalidadeEmailTelefone.toEnum(finalI)) {
//                        case NFE:
//                            mail.setFinalidade(mail.getFinalidade().replace("2", ""));
//                            break;
//                        case PRINCIPAL:
//                            mail.setFinalidade(mail.getFinalidade().replace("1", "0"));
//                            break;
//                    }
//                }
//                contextMenuEmailFinalidade.hide();
//                getListEmailHomePage().refresh();
//            });
//        }
    }

    /**
     * END
     * private voids
     */

    /**
     * start
     * Validações
     */

    private boolean validarDados() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Empresa>> constraintViolation = validator.validate(getEmpresa());
        if (constraintViolation.size() > 0) {
            for (ConstraintViolation<Empresa> violation : constraintViolation)
                if (violation.getMessage().contains("CNPJ") && getEmpresa().getClassifJuridica().equals(ClassificacaoJuridica.PESSOAFISICA)) {
                    if (constraintViolation.size() == 1)
                        return true;
                } else {
                    System.out.printf("violation.getMessage(): [%s]\n", violation.getMessage());
                }
            return false;
        } else {
            System.out.printf("Valido!!!");
        }
        return true;
    }

    private void camposValidos() {
        ServiceValidationFields getTxtCNPJValid = new ServiceValidationFields();
        getTxtCNPJValid.checkFields(
                getTxtCNPJ(),
                String.format("%s::%s;",
                        VAL_CNPJ, 1)
        );

        ServiceValidationFields iEValid = new ServiceValidationFields();
        getTxtIE().disableProperty().addListener((ov, o, n) -> {
            if (n || getTxtIE().getText().length() >= 3)
                iEValid.removeToolTipAndBorderColor(getTxtIE());
            else {
                iEValid.addToolTipAndBorderColor(getTxtIE());
                getTxtIE().lengthProperty().addListener((ov1, o1, n1) -> {
                    if (n1.intValue() >= 3)
                        iEValid.removeToolTipAndBorderColor(getTxtIE());
                    else {
                        if (!getTxtIE().isDisabled())
                            iEValid.addToolTipAndBorderColor(getTxtIE());
                    }
                });
            }
        });

        ServiceValidationFields getCboSituacaoSistemaValid = new ServiceValidationFields();
        getCboSituacaoSistemaValid.checkFields(
                getCboSituacaoSistema(),
                String.format("%s::%d;",
                        MIN_CBO, 1)
        );

        ServiceValidationFields getTxtLimiteValid = new ServiceValidationFields();
        getTxtLimiteValid.checkFields(
                getTxtLimite(),
                String.format("%s::%s;",
                        MIN_BIG, "0.01")
        );


        isValidoProperty().bind(Bindings.createBooleanBinding(() ->
                        (getTxtCNPJValid.isValidoProperty().get()
                                && iEValid.isValidoProperty().get()
                                && getCboSituacaoSistemaValid.isValidoProperty().get()
                                && getTxtLimiteValid.isValidoProperty().get()
                        ), getTxtCNPJValid.isValidoProperty(), iEValid.isValidoProperty(),
                getCboSituacaoSistemaValid.isValidoProperty(), getTxtLimiteValid.isValidoProperty()
        ));
    }

    /**
     * END
     * Validações
     */


    /**
     * start
     * Getters e setters
     */

    public AnchorPane getPainelViewCadastroEmpresa() {
        return painelViewCadastroEmpresa;
    }

    public void setPainelViewCadastroEmpresa(AnchorPane painelViewCadastroEmpresa) {
        this.painelViewCadastroEmpresa = painelViewCadastroEmpresa;
    }

    public TitledPane getTpnCadastroEmpresa() {
        return tpnCadastroEmpresa;
    }

    public void setTpnCadastroEmpresa(TitledPane tpnCadastroEmpresa) {
        this.tpnCadastroEmpresa = tpnCadastroEmpresa;
    }

    public JFXTextField getTxtPesquisaEmpresa() {
        return txtPesquisaEmpresa;
    }

    public void setTxtPesquisaEmpresa(JFXTextField txtPesquisaEmpresa) {
        this.txtPesquisaEmpresa = txtPesquisaEmpresa;
    }

    public JFXComboBox<EmpresaTipo> getCboFiltroTipo() {
        return cboFiltroTipo;
    }

    public void setCboFiltroTipo(JFXComboBox<EmpresaTipo> cboFiltroTipo) {
        this.cboFiltroTipo = cboFiltroTipo;
    }

    public JFXComboBox<SituacaoNoSistema> getCboFiltroSituacao() {
        return cboFiltroSituacao;
    }

    public void setCboFiltroSituacao(JFXComboBox<SituacaoNoSistema> cboFiltroSituacao) {
        this.cboFiltroSituacao = cboFiltroSituacao;
    }

    public TreeTableView<Empresa> getTtvEmpresa() {
        return ttvEmpresa;
    }

    public void setTtvEmpresa(TreeTableView<Empresa> ttvEmpresa) {
        this.ttvEmpresa = ttvEmpresa;
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

    public JFXComboBox<ClassificacaoJuridica> getCboClassificacaoJuridica() {
        return cboClassificacaoJuridica;
    }

    public void setCboClassificacaoJuridica(JFXComboBox<ClassificacaoJuridica> cboClassificacaoJuridica) {
        this.cboClassificacaoJuridica = cboClassificacaoJuridica;
    }

    public JFXTextField getTxtCNPJ() {
        return txtCNPJ;
    }

    public void setTxtCNPJ(JFXTextField txtCNPJ) {
        this.txtCNPJ = txtCNPJ;
    }

    public JFXCheckBox getChkIeIsento() {
        return chkIeIsento;
    }

    public void setChkIeIsento(JFXCheckBox chkIeIsento) {
        this.chkIeIsento = chkIeIsento;
    }

    public JFXTextField getTxtIE() {
        return txtIE;
    }

    public void setTxtIE(JFXTextField txtIE) {
        this.txtIE = txtIE;
    }

    public JFXTextField getTxtRazao() {
        return txtRazao;
    }

    public void setTxtRazao(JFXTextField txtRazao) {
        this.txtRazao = txtRazao;
    }

    public JFXTextField getTxtFantasia() {
        return txtFantasia;
    }

    public void setTxtFantasia(JFXTextField txtFantasia) {
        this.txtFantasia = txtFantasia;
    }

    public JFXComboBox<SituacaoNoSistema> getCboSituacaoSistema() {
        return cboSituacaoSistema;
    }

    public void setCboSituacaoSistema(JFXComboBox<SituacaoNoSistema> cboSituacaoSistema) {
        this.cboSituacaoSistema = cboSituacaoSistema;
    }

    public JFXCheckBox getChkIsCliente() {
        return chkIsCliente;
    }

    public void setChkIsCliente(JFXCheckBox chkIsCliente) {
        this.chkIsCliente = chkIsCliente;
    }

    public JFXCheckBox getChkIsFornecedor() {
        return chkIsFornecedor;
    }

    public void setChkIsFornecedor(JFXCheckBox chkIsFornecedor) {
        this.chkIsFornecedor = chkIsFornecedor;
    }

    public JFXCheckBox getChkIsTransportadora() {
        return chkIsTransportadora;
    }

    public void setChkIsTransportadora(JFXCheckBox chkIsTransportadora) {
        this.chkIsTransportadora = chkIsTransportadora;
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

    public TitledPane getTpnEndereco() {
        return tpnEndereco;
    }

    public void setTpnEndereco(TitledPane tpnEndereco) {
        this.tpnEndereco = tpnEndereco;
    }

    public JFXListView<Endereco> getListEndereco() {
        return listEndereco;
    }

    public void setListEndereco(JFXListView<Endereco> listEndereco) {
        this.listEndereco = listEndereco;
    }

    public JFXTextField getTxtEndCEP() {
        return txtEndCEP;
    }

    public void setTxtEndCEP(JFXTextField txtEndCEP) {
        this.txtEndCEP = txtEndCEP;
    }

    public JFXTextField getTxtEndLogradouro() {
        return txtEndLogradouro;
    }

    public void setTxtEndLogradouro(JFXTextField txtEndLogradouro) {
        this.txtEndLogradouro = txtEndLogradouro;
    }

    public JFXTextField getTxtEndNumero() {
        return txtEndNumero;
    }

    public void setTxtEndNumero(JFXTextField txtEndNumero) {
        this.txtEndNumero = txtEndNumero;
    }

    public JFXTextField getTxtEndComplemento() {
        return txtEndComplemento;
    }

    public void setTxtEndComplemento(JFXTextField txtEndComplemento) {
        this.txtEndComplemento = txtEndComplemento;
    }

    public JFXTextField getTxtEndBairro() {
        return txtEndBairro;
    }

    public void setTxtEndBairro(JFXTextField txtEndBairro) {
        this.txtEndBairro = txtEndBairro;
    }

    public JFXComboBox<Uf> getCboEndUF() {
        return cboEndUF;
    }

    public void setCboEndUF(JFXComboBox<Uf> cboEndUF) {
        this.cboEndUF = cboEndUF;
    }

    public JFXComboBox<Municipio> getCboEndMunicipio() {
        return cboEndMunicipio;
    }

    public void setCboEndMunicipio(JFXComboBox<Municipio> cboEndMunicipio) {
        this.cboEndMunicipio = cboEndMunicipio;
    }

    public JFXTextField getTxtEndPontoReferencia() {
        return txtEndPontoReferencia;
    }

    public void setTxtEndPontoReferencia(JFXTextField txtEndPontoReferencia) {
        this.txtEndPontoReferencia = txtEndPontoReferencia;
    }

    public JFXListView<EmailHomePage> getListEmailHomePage() {
        return listEmailHomePage;
    }

    public void setListEmailHomePage(JFXListView<EmailHomePage> listEmailHomePage) {
        this.listEmailHomePage = listEmailHomePage;
    }

    public JFXListView<Telefone> getListTelefone() {
        return listTelefone;
    }

    public void setListTelefone(JFXListView<Telefone> listTelefone) {
        this.listTelefone = listTelefone;
    }

    public TitledPane getTpnPessoaContato() {
        return tpnPessoaContato;
    }

    public void setTpnPessoaContato(TitledPane tpnPessoaContato) {
        this.tpnPessoaContato = tpnPessoaContato;
    }

    public JFXListView<Contato> getListContatoNome() {
        return listContatoNome;
    }

    public void setListContatoNome(JFXListView<Contato> listContatoNome) {
        this.listContatoNome = listContatoNome;
    }

    public JFXListView<EmailHomePage> getListContatoEmailHomePage() {
        return listContatoEmailHomePage;
    }

    public void setListContatoEmailHomePage(JFXListView<EmailHomePage> listContatoEmailHomePage) {
        this.listContatoEmailHomePage = listContatoEmailHomePage;
    }

    public JFXListView<Telefone> getListContatoTelefone() {
        return listContatoTelefone;
    }

    public void setListContatoTelefone(JFXListView<Telefone> listContatoTelefone) {
        this.listContatoTelefone = listContatoTelefone;
    }

    public JFXTextField getTxtPrazo() {
        return txtPrazo;
    }

    public void setTxtPrazo(JFXTextField txtPrazo) {
        this.txtPrazo = txtPrazo;
    }

    public JFXCheckBox getChkDiaUtil() {
        return chkDiaUtil;
    }

    public void setChkDiaUtil(JFXCheckBox chkDiaUtil) {
        this.chkDiaUtil = chkDiaUtil;
    }

    public JFXTextField getTxtLimite() {
        return txtLimite;
    }

    public void setTxtLimite(JFXTextField txtLimite) {
        this.txtLimite = txtLimite;
    }

    public TableView<EmpresaProdutoValor> getTvEmpresaProdutoValor() {
        return tvEmpresaProdutoValor;
    }

    public void setTvEmpresaProdutoValor(TableView<EmpresaProdutoValor> tvEmpresaProdutoValor) {
        this.tvEmpresaProdutoValor = tvEmpresaProdutoValor;
    }

    public TitledPane getTpnObservacoes() {
        return tpnObservacoes;
    }

    public void setTpnObservacoes(TitledPane tpnObservacoes) {
        this.tpnObservacoes = tpnObservacoes;
    }

    public JFXTextArea getTxaObservacoes() {
        return txaObservacoes;
    }

    public void setTxaObservacoes(JFXTextArea txaObservacoes) {
        this.txaObservacoes = txaObservacoes;
    }

    public Label getLblDataAbertura() {
        return lblDataAbertura;
    }

    public void setLblDataAbertura(Label lblDataAbertura) {
        this.lblDataAbertura = lblDataAbertura;
    }

    public Label getLblDataAberturaDiff() {
        return lblDataAberturaDiff;
    }

    public void setLblDataAberturaDiff(Label lblDataAberturaDiff) {
        this.lblDataAberturaDiff = lblDataAberturaDiff;
    }

    public Label getLblNaturezaJuridica() {
        return lblNaturezaJuridica;
    }

    public void setLblNaturezaJuridica(Label lblNaturezaJuridica) {
        this.lblNaturezaJuridica = lblNaturezaJuridica;
    }

    public TitledPane getTpnReceitaAtividadePrincipal() {
        return tpnReceitaAtividadePrincipal;
    }

    public void setTpnReceitaAtividadePrincipal(TitledPane tpnReceitaAtividadePrincipal) {
        this.tpnReceitaAtividadePrincipal = tpnReceitaAtividadePrincipal;
    }

    public JFXListView<InfoReceitaFederal> getListAtividadePrincipal() {
        return listAtividadePrincipal;
    }

    public void setListAtividadePrincipal(JFXListView<InfoReceitaFederal> listAtividadePrincipal) {
        this.listAtividadePrincipal = listAtividadePrincipal;
    }

    public TitledPane getTpnReceitaAtividadeSecundaria() {
        return tpnReceitaAtividadeSecundaria;
    }

    public void setTpnReceitaAtividadeSecundaria(TitledPane tpnReceitaAtividadeSecundaria) {
        this.tpnReceitaAtividadeSecundaria = tpnReceitaAtividadeSecundaria;
    }

    public JFXListView<InfoReceitaFederal> getListAtividadeSecundaria() {
        return listAtividadeSecundaria;
    }

    public void setListAtividadeSecundaria(JFXListView<InfoReceitaFederal> listAtividadeSecundaria) {
        this.listAtividadeSecundaria = listAtividadeSecundaria;
    }

    public TitledPane getTpnReceitaQsa() {
        return tpnReceitaQsa;
    }

    public void setTpnReceitaQsa(TitledPane tpnReceitaQsa) {
        this.tpnReceitaQsa = tpnReceitaQsa;
    }

    public JFXListView<InfoReceitaFederal> getListQsaReceitaFederal() {
        return listQsaReceitaFederal;
    }

    public void setListQsaReceitaFederal(JFXListView<InfoReceitaFederal> listQsaReceitaFederal) {
        this.listQsaReceitaFederal = listQsaReceitaFederal;
    }

    public ServiceAlertMensagem getAlertMensagem() {
        return alertMensagem;
    }

    public void setAlertMensagem(ServiceAlertMensagem alertMensagem) {
        this.alertMensagem = alertMensagem;
    }

    public EventHandler getEventHandlerCadastroEmpresa() {
        return eventHandlerCadastroEmpresa;
    }

    public void setEventHandlerCadastroEmpresa(EventHandler eventHandlerCadastroEmpresa) {
        this.eventHandlerCadastroEmpresa = eventHandlerCadastroEmpresa;
    }

    public StatusBarEmpresa getStatusBar() {
        return statusBar.get();
    }

    public void setStatusBar(StatusBarEmpresa statusBar) {
        this.statusBar.set(statusBar);
    }

    public ObjectProperty<StatusBarEmpresa> statusBarProperty() {
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

    public TabModelEmpresa getModelEmpresa() {
        return modelEmpresa;
    }

    public void setModelEmpresa(TabModelEmpresa modelEmpresa) {
        this.modelEmpresa = modelEmpresa;
    }

    public TabModelEmpresaProdutoValor getModelEmpresaProdutoValor() {
        return modelEmpresaProdutoValor;
    }

    public void setModelEmpresaProdutoValor(TabModelEmpresaProdutoValor modelEmpresaProdutoValor) {
        this.modelEmpresaProdutoValor = modelEmpresaProdutoValor;
    }

    public EmpresaDAO getEmpresaDAO() {
        return empresaDAO;
    }

    public void setEmpresaDAO(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        if (getStatusBar().equals(StatusBarEmpresa.PESQUISA))
            exibirEmpresa();
    }

    public ObservableList<Empresa> getEmpresaObservableList() {
        return empresaObservableList;
    }

    public void setEmpresaObservableList(ObservableList<Empresa> empresaObservableList) {
        this.empresaObservableList = empresaObservableList;
    }

    public FilteredList<Empresa> getEmpresaFilteredList() {
        return empresaFilteredList;
    }

    public void setEmpresaFilteredList(FilteredList<Empresa> empresaFilteredList) {
        this.empresaFilteredList = empresaFilteredList;
    }

    public ObservableList<EmpresaProdutoValor> getEmpresaProdutoValorObservableList() {
        return empresaProdutoValorObservableList;
    }

    public void setEmpresaProdutoValorObservableList(ObservableList<EmpresaProdutoValor> empresaProdutoValorObservableList) {
        this.empresaProdutoValorObservableList = empresaProdutoValorObservableList;
    }

    public FilteredList<Municipio> getMunicipioFilteredList() {
        return municipioFilteredList;
    }

    public void setMunicipioFilteredList(FilteredList<Municipio> municipioFilteredList) {
        this.municipioFilteredList = municipioFilteredList;
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

    public String getStpNatJuridica() {
        return stpNatJuridica.get();
    }

    public void setStpNatJuridica(String stpNatJuridica) {
        this.stpNatJuridica.set(String.format("natureza júridica: %s", stpNatJuridica));
    }

    public StringProperty stpNatJuridicaProperty() {
        return stpNatJuridica;
    }

    public String getStpDtAbert() {
        return stpDtAbert.get();
    }

    public void setStpDtAbert(String stpDtAbert) {
        this.stpDtAbert.set(String.format("data abertura: %s", stpDtAbert));
    }

    public StringProperty stpDtAbertProperty() {
        return stpDtAbert;
    }

    public String getStpDtAbertDiff() {
        return stpDtAbertDiff.get();
    }

    public void setStpDtAbertDiff(String stpDtAbertDiff) {
        this.stpDtAbertDiff.set(String.format("tempo de abertura: %s", stpDtAbertDiff));
    }

    public StringProperty stpDtAbertDiffProperty() {
        return stpDtAbertDiff;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        exibirEndereco();
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ObservableList<Endereco> getEnderecoObservableList() {
        return enderecoObservableList;
    }

    public void setEnderecoObservableList(ObservableList<Endereco> enderecoObservableList) {
        this.enderecoObservableList = enderecoObservableList;
    }

    public ObservableList<EmailHomePage> getEmailHomePageObservableList() {
        return emailHomePageObservableList;
    }

    public void setEmailHomePageObservableList(ObservableList<EmailHomePage> emailHomePageObservableList) {
        this.emailHomePageObservableList = emailHomePageObservableList;
    }

    public ObservableList<Telefone> getTelefoneObservableList() {
        return telefoneObservableList;
    }

    public void setTelefoneObservableList(ObservableList<Telefone> telefoneObservableList) {
        this.telefoneObservableList = telefoneObservableList;
    }

    public ObservableList<Contato> getContatoObservableList() {
        return contatoObservableList;
    }

    public void setContatoObservableList(ObservableList<Contato> contatoObservableList) {
        this.contatoObservableList = contatoObservableList;
    }

    public ObservableList<EmailHomePage> getContatoEmailHomePageObservableList() {
        return contatoEmailHomePageObservableList;
    }

    public void setContatoEmailHomePageObservableList(ObservableList<EmailHomePage> contatoEmailHomePageObservableList) {
        this.contatoEmailHomePageObservableList = contatoEmailHomePageObservableList;
    }

    public ObservableList<Telefone> getContatoTelefoneObservableList() {
        return contatoTelefoneObservableList;
    }

    public void setContatoTelefoneObservableList(ObservableList<Telefone> contatoTelefoneObservableList) {
        this.contatoTelefoneObservableList = contatoTelefoneObservableList;
    }

    public ObservableList<InfoReceitaFederal> getInfoReceitaFederalObservableList() {
        return infoReceitaFederalObservableList;
    }

    public void setInfoReceitaFederalObservableList(ObservableList<InfoReceitaFederal> infoReceitaFederalObservableList) {
        this.infoReceitaFederalObservableList = infoReceitaFederalObservableList;
    }

    public Uf getObjUfPrincipal() {
        return objUfPrincipal.get();
    }

    public void setObjUfPrincipal(Uf objUfPrincipal) {
        this.objUfPrincipal.set(objUfPrincipal);
    }

    public ObjectProperty<Uf> objUfPrincipalProperty() {
        return objUfPrincipal;
    }

    public ClassificacaoJuridica getObjClassificacaoJuridica() {
        return objClassificacaoJuridica.get();
    }

    public void setObjClassificacaoJuridica(ClassificacaoJuridica objClassificacaoJuridica) {
        this.objClassificacaoJuridica.set(objClassificacaoJuridica);
    }

    public ObjectProperty<ClassificacaoJuridica> objClassificacaoJuridicaProperty() {
        return objClassificacaoJuridica;
    }

    public String getMaskIe() {
        return maskIe.get();
    }

    public void setMaskIe(String maskIe) {
        this.maskIe.set(maskIe);
    }

    public StringProperty maskIeProperty() {
        return maskIe;
    }

    public ServiceMascara getFormatCnpj() {
        return formatCnpj;
    }

    public void setFormatCnpj(ServiceMascara formatCnpj) {
        this.formatCnpj = formatCnpj;
    }

    public ServiceMascara getFormatIe() {
        return formatIe;
    }

    public void setFormatIe(ServiceMascara formatIe) {
        this.formatIe = formatIe;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public Telefone getTelAntigo() {
        return telAntigo;
    }

    public void setTelAntigo(Telefone telAntigo) {
        this.telAntigo = telAntigo;
    }

    public Telefone getTelNovo() {
        return telNovo;
    }

    public void setTelNovo(Telefone telNovo) {
        this.telNovo = telNovo;
    }

    public Telefone getTelContAntigo() {
        return telContAntigo;
    }

    public void setTelContAntigo(Telefone telContAntigo) {
        this.telContAntigo = telContAntigo;
    }

    public Telefone getTelContNovo() {
        return telContNovo;
    }

    public void setTelContNovo(Telefone telContNovo) {
        this.telContNovo = telContNovo;
    }

    public EmailHomePage getEmailHomePageAntigo() {
        return emailHomePageAntigo;
    }

    public void setEmailHomePageAntigo(EmailHomePage emailHomePageAntigo) {
        this.emailHomePageAntigo = emailHomePageAntigo;
    }

    public EmailHomePage getEmailHomePageNovo() {
        return emailHomePageNovo;
    }

    public void setEmailHomePageNovo(EmailHomePage emailHomePageNovo) {
        this.emailHomePageNovo = emailHomePageNovo;
    }

    public EmailHomePage getEmailHomePageContatoAntigo() {
        return emailHomePageContatoAntigo;
    }

    public void setEmailHomePageContatoAntigo(EmailHomePage emailHomePageContatoAntigo) {
        this.emailHomePageContatoAntigo = emailHomePageContatoAntigo;
    }

    public EmailHomePage getEmailHomePageContatoNovo() {
        return emailHomePageContatoNovo;
    }

    public void setEmailHomePageContatoNovo(EmailHomePage emailHomePageContatoNovo) {
        this.emailHomePageContatoNovo = emailHomePageContatoNovo;
    }

    public Contato getContatoAntigo() {
        return contatoAntigo;
    }

    public void setContatoAntigo(Contato contatoAntigo) {
        this.contatoAntigo = contatoAntigo;
    }

    public Contato getContatoNovo() {
        return contatoNovo;
    }

    public void setContatoNovo(Contato contatoNovo) {
        this.contatoNovo = contatoNovo;
    }

    public boolean isIsValido() {
        return isValido.get();
    }

    public BooleanProperty isValidoProperty() {
        return isValido;
    }

    public void setIsValido(boolean isValido) {
        this.isValido.set(isValido);
    }

    /**
     * END
     * Getters e setters
     */


}
