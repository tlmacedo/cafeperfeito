package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.Empresa;
import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.EmpresaTipo;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoNoSistema;
import br.com.tlmacedo.cafeperfeito.service.ServiceAlertMensagem;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class TabModelEmpresa {

    private Label lblRegistrosLocalizados;
    private Label lblStatus;
    private JFXTextField txtPesquisaEmpresa;
    private TreeTableView<Empresa> ttvEmpresa;
    private ObservableList<Empresa> empresaObservableList;
    private FilteredList<Empresa> empresaFilteredList;

    static TreeTableColumn<Empresa, Long> colunaId;
    static TreeTableColumn<Empresa, String> colunaCnpj;
    static TreeTableColumn<Empresa, String> colunaIe;
    static TreeTableColumn<Empresa, String> colunaRazao;
    static TreeTableColumn<Empresa, String> colunaFantasia;
    static TreeTableColumn<Empresa, String> colunaEndereco;
    static TreeTableColumn<Empresa, String> colunaEndLogradouro;
    static TreeTableColumn<Empresa, String> colunaEndNumero;
    static TreeTableColumn<Empresa, String> colunaEndComplemento;
    static TreeTableColumn<Empresa, String> colunaEndBairro;
    static TreeTableColumn<Empresa, String> colunaEndUFMunicipio;
    static TreeTableColumn<Empresa, Boolean> colunaIsCliente;
    static TreeTableColumn<Empresa, Boolean> colunaIsFornecedor;
    static TreeTableColumn<Empresa, Boolean> colunaIsTransportadora;

    private ServiceAlertMensagem alertMensagem;
    private JFXComboBox<EmpresaTipo> cboFiltroTipo;
    private JFXComboBox<SituacaoNoSistema> cboFiltroSituacao;

    public TabModelEmpresa() {
    }

    @SuppressWarnings("Duplicates")
    public static void tabela() {
        try {
            Label lblId = new Label("id");
            lblId.setPrefWidth(28);
            colunaId = new TreeTableColumn<Empresa, Long>();
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(28);
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> param.getValue().getValue().idProperty().asObject());

            Label lblCnpj = new Label("C.N.P.J / C.P.F.");
            lblCnpj.setPrefWidth(110);
            colunaCnpj = new TreeTableColumn<Empresa, String>();
            colunaCnpj.setGraphic(lblCnpj);
            colunaCnpj.setPrefWidth(110);
            colunaCnpj.setStyle("-fx-alignment: center-right;");
            colunaCnpj.setCellValueFactory(param -> {
                String cnpjCpf = param.getValue().getValue().cnpjProperty().get();
                return new SimpleStringProperty(cnpjCpf.length() == 14 ? ServiceMascara.getCnpj(cnpjCpf) : ServiceMascara.getCpf(cnpjCpf));
            });

            Label lblIe = new Label(("IE / RG"));
            lblIe.setPrefWidth(90);
            colunaIe = new TreeTableColumn<Empresa, String>();
            colunaIe.setGraphic(lblIe);
            colunaIe.setPrefWidth(90);
            colunaIe.setStyle("-fx-alignment: center-right;");
            colunaIe.setCellValueFactory(param -> {
                if (param.getValue().getValue().ieIsentoProperty().get())
                    return new SimpleStringProperty("Isento");
                if (param.getValue().getValue().getEnderecoList().size() > 0)
                    return new SimpleStringProperty(
                            ServiceMascara.getIe(
                                    param.getValue().getValue().ieProperty().get(),
                                    param.getValue().getValue().getEnderecoList().get(0).getMunicipio().getUf().siglaProperty().get()
                            ));
                return param.getValue().getValue().ieProperty();
            });

            Label lblRazao = new Label("Razão / Nome");
            lblRazao.setPrefWidth(250);
            colunaRazao = new TreeTableColumn<Empresa, String>();
            colunaRazao.setGraphic(lblRazao);
            colunaRazao.setPrefWidth(250);
            colunaRazao.setCellValueFactory(param -> param.getValue().getValue().razaoProperty());

            Label lblFantasia = new Label("Fantasia / Apelido");
            lblFantasia.setPrefWidth(150);
            colunaFantasia = new TreeTableColumn<Empresa, String>();
            colunaFantasia.setGraphic(lblFantasia);
            colunaFantasia.setPrefWidth(150);
            colunaFantasia.setCellValueFactory(param -> param.getValue().getValue().fantasiaProperty());

            colunaEndereco = new TreeTableColumn<Empresa, String>("Endereço");
            colunaEndereco.setStyle("-fx-alignment: center;");

            Label lblEndLogradouro = new Label("Logradouro");
            lblEndLogradouro.setPrefWidth(170);
            colunaEndLogradouro = new TreeTableColumn<Empresa, String>();
            colunaEndLogradouro.setGraphic(lblEndLogradouro);
            colunaEndLogradouro.setPrefWidth(170);
            colunaEndLogradouro.setCellValueFactory(param -> {
                String endereco = "";
                if (param.getValue().getValue().getEnderecoList().size() > 0)
                    endereco = param.getValue().getValue().getEnderecoList().get(0).logradouroProperty().get();
                return new SimpleStringProperty(endereco);
            });

            Label lblEndNumero = new Label("Número");
            lblEndNumero.setPrefWidth(40);
            colunaEndNumero = new TreeTableColumn<Empresa, String>();
            colunaEndNumero.setGraphic(lblEndNumero);
            colunaEndNumero.setPrefWidth(40);
            colunaEndNumero.setStyle("-fx-alignment: center-right;");
            colunaEndNumero.setCellValueFactory(param -> {
                String numero = "";
                if (param.getValue().getValue().getEnderecoList().size() > 0)
                    numero = param.getValue().getValue().getEnderecoList().get(0).numeroProperty().get();
                return new SimpleStringProperty(numero);
            });

            Label lblEndComplemento = new Label("Complemento");
            lblEndComplemento.setPrefWidth(150);
            colunaEndComplemento = new TreeTableColumn<Empresa, String>();
            colunaEndComplemento.setGraphic(lblEndComplemento);
            colunaEndComplemento.setPrefWidth(150);
            colunaEndComplemento.setCellValueFactory(param -> {
                String complemento = "";
                if (param.getValue().getValue().getEnderecoList().size() > 0)
                    complemento = param.getValue().getValue().getEnderecoList().get(0).complementoProperty().get();
                return new SimpleStringProperty(complemento);
            });

            Label lblEndBairro = new Label("Bairro");
            lblEndBairro.setPrefWidth(95);
            colunaEndBairro = new TreeTableColumn<Empresa, String>();
            colunaEndBairro.setGraphic(lblEndBairro);
            colunaEndBairro.setPrefWidth(95);
            colunaEndBairro.setCellValueFactory(param -> {
                String bairro = "";
                if (param.getValue().getValue().getEnderecoList().size() > 0)
                    bairro = param.getValue().getValue().getEnderecoList().get(0).bairroProperty().get();
                return new SimpleStringProperty(bairro);
            });

            Label lblEndUFMunicipio = new Label("UF - Cidade");
            lblEndUFMunicipio.setPrefWidth(75);
            colunaEndUFMunicipio = new TreeTableColumn<Empresa, String>();
            colunaEndUFMunicipio.setGraphic(lblEndUFMunicipio);
            colunaEndUFMunicipio.setPrefWidth(75);
            colunaEndUFMunicipio.setCellValueFactory(param -> {
                String ufMunicipio = "";
                if (param.getValue().getValue().getEnderecoList().size() > 0)
                    ufMunicipio =
                            param.getValue().getValue().getEnderecoList().get(0).getMunicipio().descricaoProperty().get() + " - " +
                                    param.getValue().getValue().getEnderecoList().get(0).getMunicipio().getUf().siglaProperty().get();
                return new SimpleStringProperty(ufMunicipio);
            });

            colunaEndereco.getColumns().addAll(colunaEndLogradouro, colunaEndNumero,
                    colunaEndComplemento, colunaEndBairro);

            VBox vBoxIsCliente = new VBox();
            Label lblImgIsCliente = new Label();
            lblImgIsCliente.getStyleClass().add("lbl_ico_cliente");
            lblImgIsCliente.setPrefSize(18, 18);
            Label lblIsCliente = new Label("Cliente");
            vBoxIsCliente.setAlignment(Pos.CENTER);
            vBoxIsCliente.getChildren().addAll(lblImgIsCliente, lblIsCliente);
            colunaIsCliente = new TreeTableColumn<Empresa, Boolean>();
            colunaIsCliente.setPrefWidth(55);
            colunaIsCliente.setGraphic(vBoxIsCliente);
            colunaIsCliente.setCellValueFactory(param -> param.getValue().getValue().clienteProperty());

            VBox vBoxIsFornecedor = new VBox();
            Label lblImgIsFornecedor = new Label();
            lblImgIsFornecedor.getStyleClass().add("lbl_ico_fornecedor");
            lblImgIsFornecedor.setPrefSize(18, 18);
            Label lblIsFornecedor = new Label("Forn.");
            vBoxIsFornecedor.setAlignment(Pos.CENTER);
            vBoxIsFornecedor.getChildren().addAll(lblImgIsFornecedor, lblIsFornecedor);
            colunaIsFornecedor = new TreeTableColumn<Empresa, Boolean>();
            colunaIsFornecedor.setPrefWidth(55);
            colunaIsFornecedor.setGraphic(vBoxIsFornecedor);
            colunaIsFornecedor.setCellValueFactory(param -> param.getValue().getValue().fornecedorProperty());

            VBox vBoxIsTransportadora = new VBox();
            Label lblImgIsTransportadora = new Label();
            lblImgIsTransportadora.getStyleClass().add("lbl_ico_transportadora");
            lblImgIsTransportadora.setPrefSize(18, 18);
            Label lblIsTransportadora = new Label("Transp.");
            vBoxIsTransportadora.setAlignment(Pos.CENTER);
            vBoxIsTransportadora.getChildren().addAll(lblImgIsTransportadora, lblIsTransportadora);
            colunaIsTransportadora = new TreeTableColumn<Empresa, Boolean>();
            colunaIsTransportadora.setPrefWidth(55);
            colunaIsTransportadora.setGraphic(vBoxIsTransportadora);
            colunaIsTransportadora.setCellValueFactory(param -> param.getValue().getValue().transportadoraProperty());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Label getLblRegistrosLocalizados() {
        return lblRegistrosLocalizados;
    }

    public void setLblRegistrosLocalizados(Label lblRegistrosLocalizados) {
        this.lblRegistrosLocalizados = lblRegistrosLocalizados;
    }

    public Label getLblStatus() {
        return lblStatus;
    }

    public void setLblStatus(Label lblStatus) {
        this.lblStatus = lblStatus;
    }

    public JFXTextField getTxtPesquisaEmpresa() {
        return txtPesquisaEmpresa;
    }

    public void setTxtPesquisaEmpresa(JFXTextField txtPesquisaEmpresa) {
        this.txtPesquisaEmpresa = txtPesquisaEmpresa;
    }

    public TreeTableView<Empresa> getTtvEmpresa() {
        return ttvEmpresa;
    }

    public void setTtvEmpresa(TreeTableView<Empresa> ttvEmpresa) {
        this.ttvEmpresa = ttvEmpresa;
    }

    public ObservableList<Empresa> getEmpresaObservableList() {
        return empresaObservableList;
    }

    public void setEmpresaObservableList(ObservableList<Empresa> empresaObservableList) {
        this.empresaObservableList = empresaObservableList;
    }

    public void setEmpresaObservableList(Empresa empresa) {
        this.empresaObservableList.set(empresaObservableList.indexOf(ttvEmpresa.getSelectionModel().getSelectedItem().getValue()),
                empresa);
    }

    public static TreeTableColumn<Empresa, Long> getColunaId() {
        return colunaId;
    }

    public static void setColunaId(TreeTableColumn<Empresa, Long> colunaId) {
        TabModelEmpresa.colunaId = colunaId;
    }

    public static TreeTableColumn<Empresa, String> getColunaCnpj() {
        return colunaCnpj;
    }

    public static void setColunaCnpj(TreeTableColumn<Empresa, String> colunaCnpj) {
        TabModelEmpresa.colunaCnpj = colunaCnpj;
    }

    public static TreeTableColumn<Empresa, String> getColunaIe() {
        return colunaIe;
    }

    public static void setColunaIe(TreeTableColumn<Empresa, String> colunaIe) {
        TabModelEmpresa.colunaIe = colunaIe;
    }

    public static TreeTableColumn<Empresa, String> getColunaRazao() {
        return colunaRazao;
    }

    public static void setColunaRazao(TreeTableColumn<Empresa, String> colunaRazao) {
        TabModelEmpresa.colunaRazao = colunaRazao;
    }

    public static TreeTableColumn<Empresa, String> getColunaFantasia() {
        return colunaFantasia;
    }

    public static void setColunaFantasia(TreeTableColumn<Empresa, String> colunaFantasia) {
        TabModelEmpresa.colunaFantasia = colunaFantasia;
    }

    public static TreeTableColumn<Empresa, String> getColunaEndereco() {
        return colunaEndereco;
    }

    public static void setColunaEndereco(TreeTableColumn<Empresa, String> colunaEndereco) {
        TabModelEmpresa.colunaEndereco = colunaEndereco;
    }

    public static TreeTableColumn<Empresa, String> getColunaEndLogradouro() {
        return colunaEndLogradouro;
    }

    public static void setColunaEndLogradouro(TreeTableColumn<Empresa, String> colunaEndLogradouro) {
        TabModelEmpresa.colunaEndLogradouro = colunaEndLogradouro;
    }

    public static TreeTableColumn<Empresa, String> getColunaEndNumero() {
        return colunaEndNumero;
    }

    public static void setColunaEndNumero(TreeTableColumn<Empresa, String> colunaEndNumero) {
        TabModelEmpresa.colunaEndNumero = colunaEndNumero;
    }

    public static TreeTableColumn<Empresa, String> getColunaEndComplemento() {
        return colunaEndComplemento;
    }

    public static void setColunaEndComplemento(TreeTableColumn<Empresa, String> colunaEndComplemento) {
        TabModelEmpresa.colunaEndComplemento = colunaEndComplemento;
    }

    public static TreeTableColumn<Empresa, String> getColunaEndBairro() {
        return colunaEndBairro;
    }

    public static void setColunaEndBairro(TreeTableColumn<Empresa, String> colunaEndBairro) {
        TabModelEmpresa.colunaEndBairro = colunaEndBairro;
    }

    public static TreeTableColumn<Empresa, String> getColunaEndUFMunicipio() {
        return colunaEndUFMunicipio;
    }

    public static void setColunaEndUFMunicipio(TreeTableColumn<Empresa, String> colunaEndUFMunicipio) {
        TabModelEmpresa.colunaEndUFMunicipio = colunaEndUFMunicipio;
    }

    public static TreeTableColumn<Empresa, Boolean> getColunaIsCliente() {
        return colunaIsCliente;
    }

    public static void setColunaIsCliente(TreeTableColumn<Empresa, Boolean> colunaIsCliente) {
        TabModelEmpresa.colunaIsCliente = colunaIsCliente;
    }

    public static TreeTableColumn<Empresa, Boolean> getColunaIsFornecedor() {
        return colunaIsFornecedor;
    }

    public static void setColunaIsFornecedor(TreeTableColumn<Empresa, Boolean> colunaIsFornecedor) {
        TabModelEmpresa.colunaIsFornecedor = colunaIsFornecedor;
    }

    public static TreeTableColumn<Empresa, Boolean> getColunaIsTransportadora() {
        return colunaIsTransportadora;
    }

    public static void setColunaIsTransportadora(TreeTableColumn<Empresa, Boolean> colunaIsTransportadora) {
        TabModelEmpresa.colunaIsTransportadora = colunaIsTransportadora;
    }

    public FilteredList<Empresa> getEmpresaFilteredList() {
        return empresaFilteredList;
    }

    public void setEmpresaFilteredList(FilteredList<Empresa> empresaFilteredList) {
        this.empresaFilteredList = empresaFilteredList;
    }

    public void atualizaEmpresas() {
        empresaObservableList.setAll(new EmpresaDAO().getAll(Empresa.class, "razao", null, null, null));
        ttvEmpresa.refresh();
    }

    public ServiceAlertMensagem getAlertMensagem() {
        return alertMensagem;
    }

    public void setAlertMensagem(ServiceAlertMensagem alertMensagem) {
        this.alertMensagem = alertMensagem;
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

    public void escutaLista() {
        getTtvEmpresa().addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER)
                    event.consume();
            }
        });

        getTxtPesquisaEmpresa().textProperty().addListener((ov, o, n) -> pesquisa());

        getCboFiltroTipo().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> pesquisa());

        getCboFiltroSituacao().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> pesquisa());

        getLblRegistrosLocalizados().textProperty().bind(Bindings.createStringBinding(() ->
                String.format("%4d registro%s localizado%s.",
                        getEmpresaFilteredList().size(),
                        getEmpresaFilteredList().size() > 1
                                ? "s" : "",
                        getEmpresaFilteredList().size() > 1
                                ? "s" : ""
                ), getEmpresaFilteredList()
        ));

        getEmpresaFilteredList().addListener((ListChangeListener<? super Empresa>) c -> {
            getTtvEmpresa().refresh();
        });

    }

    public void preencherTabela() {
        final TreeItem<Empresa> root = new RecursiveTreeItem<Empresa>(getEmpresaFilteredList(), RecursiveTreeObject::getChildren);
        getTtvEmpresa().getColumns().setAll(getColunaId(), getColunaCnpj(), getColunaIe(),
                getColunaRazao(), getColunaFantasia(), getColunaEndereco(),
                getColunaIsCliente(), getColunaIsFornecedor(), getColunaIsTransportadora());

        getTtvEmpresa().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getTtvEmpresa().setRoot(root);
        getTtvEmpresa().setShowRoot(false);
    }

    public void pesquisa() {
        String strBusca = getTxtPesquisaEmpresa().getText().toLowerCase().trim();
        if (strBusca.length() == 0
                && getCboFiltroTipo().getValue() == null
                && getCboFiltroSituacao().getValue() == null
        )
            getEmpresaFilteredList().setPredicate(empresa -> true);
        else
            getEmpresaFilteredList().setPredicate(empresa -> {
                if (getCboFiltroTipo().getValue() != null)
                    switch (getCboFiltroTipo().getValue()) {
                        case CLIENTE:
                            if (!empresa.clienteProperty().get())
                                return false;
                            break;
                        case FORNECEDOR:
                            if (!empresa.fornecedorProperty().get())
                                return false;
                            break;
                        case TRANSPORTADORA:
                            if (!empresa.transportadoraProperty().get())
                                return false;
                            break;
                    }
                if (getCboFiltroSituacao().getValue() != null)
                    switch (getCboFiltroSituacao().getValue()) {
                        case NULL:
                            break;
                        default:
                            if (SituacaoNoSistema.toEnum(empresa.situacaoProperty().get()) != getCboFiltroSituacao().getValue())
                                return false;
                            break;
                    }
                if (empresa.getCnpj().contains(strBusca)) return true;
                if (empresa.getIe().contains(strBusca)) return true;
                if (empresa.getRazao().toLowerCase().contains(strBusca)) return true;
                if (empresa.getFantasia().toLowerCase().contains(strBusca)) return true;
                if (empresa.getEnderecoList() != null) {
                    if (empresa.getEnderecoList().stream()
                            .filter(end -> end.getCep().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                    if (empresa.getEnderecoList().stream()
                            .filter(end -> end.getLogradouro().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                    if (empresa.getEnderecoList().stream()
                            .filter(end -> end.getNumero().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                    if (empresa.getEnderecoList().stream()
                            .filter(end -> end.getComplemento().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                    if (empresa.getEnderecoList().stream()
                            .filter(end -> end.getBairro().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                    if (empresa.getEnderecoList().stream()
                            .filter(end -> end.getMunicipio().getDescricao().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                    if (empresa.getEnderecoList().stream()
                            .filter(end -> end.getMunicipio().getUf().getSigla().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                }
                if (empresa.getEmailHomePageList() != null) {
                    if (empresa.getEmailHomePageList().stream()
                            .filter(mail -> mail.getDescricao().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                }
                if (empresa.getTelefoneList() != null) {
                    if (empresa.getTelefoneList().stream()
                            .filter(tel -> tel.getDescricao().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                    if (empresa.getTelefoneList().stream()
                            .filter(tel -> tel.getTelefoneOperadora().getDescricao().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                }
                if (empresa.getContatoList() != null) {
                    if (empresa.getContatoList().stream()
                            .filter(cont -> cont.getDescricao().toLowerCase().contains(strBusca))
                            .findFirst().orElse(null) != null) return true;
                    if (empresa.getContatoList().stream()
                            .filter(cont -> cont.getEmailHomePageList().stream()
                                    .filter(contMail -> contMail.getDescricao().toLowerCase().contains(strBusca))
                                    .count() > 0).findFirst().orElse(null) != null) return true;
                    if (empresa.getContatoList().stream()
                            .filter(cont -> cont.getTelefoneList().stream()
                                    .filter(contTel -> contTel.getDescricao().toLowerCase().contains(strBusca))
                                    .count() > 0).findFirst().orElse(null) != null) return true;
                    if (empresa.getContatoList().stream()
                            .filter(cont -> cont.getTelefoneList().stream()
                                    .filter(contTel -> contTel.getTelefoneOperadora().getDescricao().toLowerCase().contains(strBusca))
                                    .count() > 0).findFirst().orElse(null) != null) return true;
                }
                return false;
            });
    }

    @SuppressWarnings("Duplicates")
    public boolean jaExiste(String busca, long idEmp, boolean getAlertMsg) {
        if (busca.equals("000.000.000-00")) return false;
        String strBusca = busca.toLowerCase().trim().replaceAll("\\D", "");
        Empresa empTemp;
        if ((empTemp = getEmpresaObservableList().stream()
                .filter(emp -> emp.getCnpj().equals(strBusca)
                        || emp.getIe().equals(strBusca))
                .findFirst().orElse(null)) == null)
            return false;
        if (idEmp > 0 && idEmp == empTemp.getId())
            return false;
        if (!getAlertMsg) return true;
        setAlertMensagem(new ServiceAlertMensagem());
        getAlertMensagem().setCabecalho("Informação duplicada");
        getAlertMensagem().setPromptText(String.format("%s, %s: [%s] já está cadastrado no sistema para a empresa:\n%s!",
                LogadoInf.getUserLog().getApelido(),
                empTemp.getCnpj().replaceAll("\\D", "").equals(strBusca) ? "o cnpj:" : "a IE:",
                busca,
                String.format("%s (%s)", empTemp.getRazao(), empTemp.getFantasia())));
        getAlertMensagem().setStrIco("ic_atencao_triangulo_24dp");
        getAlertMensagem().getRetornoAlert_OK();
        return true;
    }

    @SuppressWarnings("Duplicates")
    public Empresa jaExiste(String busca, Empresa empresa, boolean getAlertMsg) {
        if (busca.equals("000.000.000-00")) return null;
        String strBusca = busca.toLowerCase().trim().replaceAll("\\D", "");
        Empresa empTemp;
        if ((empTemp = getEmpresaObservableList().stream()
                .filter(emp -> emp.getCnpj().equals(strBusca)
                        || emp.getIe().equals(strBusca))
                .findFirst().orElse(null)) == null)
            return null;
        if (empresa.getId() > 0 && empresa.getId() == empTemp.getId())
            return null;
        if (!getAlertMsg) return empTemp;
        setAlertMensagem(new ServiceAlertMensagem());
        getAlertMensagem().setCabecalho("Informação duplicada");
        getAlertMensagem().setPromptText(String.format("%s, %s: [%s] já está cadastrado no sistema para a empresa:\n%s!",
                LogadoInf.getUserLog().getApelido(),
                empTemp.getCnpj().replaceAll("\\D", "").equals(strBusca) ? "o cnpj:" : "a IE:",
                busca,
                String.format("%s (%s)", empTemp.getRazao(), empTemp.getFantasia())));
        getAlertMensagem().setStrIco("ic_atencao_triangulo_24dp");
        getAlertMensagem().getRetornoAlert_OK();
        return empTemp;
    }

}
