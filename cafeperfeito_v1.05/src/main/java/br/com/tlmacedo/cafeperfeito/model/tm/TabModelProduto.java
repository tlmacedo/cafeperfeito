package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.model.dao.ProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.Produto;
import br.com.tlmacedo.cafeperfeito.service.ServiceAlertMensagem;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATA;

public class TabModelProduto {

    private String tipoForm = "";
    private Label lblRegistrosLocalizados;
    private Label lblStatus;
    private JFXTextField txtPesquisaProduto;
    private TreeTableView<Produto> ttvProduto;
    private ObservableList<Produto> produtoObservableList;
    private FilteredList<Produto> produtoFilteredList;
    private ServiceAlertMensagem alertMensagem;

    private TreeItem<Produto> root;
    private TreeTableColumn<Produto, String> colunaId;
    private TreeTableColumn<Produto, String> colunaCodigo;
    private TreeTableColumn<Produto, String> colunaDescricao;
    private TreeTableColumn<Produto, String> colunaUndCom;
    private TreeTableColumn<Produto, String> colunaPrecoFabrica;
    private TreeTableColumn<Produto, String> colunaPrecoConsumidor;
    private TreeTableColumn<Produto, Integer> colunaQtdEstoque;
    private TreeTableColumn<Produto, String> colunaSituacaoSistema;
    private TreeTableColumn<Produto, String> colunaVarejo;
    private TreeTableColumn<Produto, String> colunaLote;
    private TreeTableColumn<Produto, String> colunaValidade;
    private TreeTableColumn<Produto, String> colunaNotaEntrada;

    public TabModelProduto() {
    }

    public String getTipoForm() {
        return tipoForm;
    }

    public void setTipoForm(String tipoForm) {
        this.tipoForm = tipoForm;
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

    public ServiceAlertMensagem getAlertMensagem() {
        return alertMensagem;
    }

    public void setAlertMensagem(ServiceAlertMensagem alertMensagem) {
        this.alertMensagem = alertMensagem;
    }

    public TreeItem<Produto> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<Produto> root) {
        this.root = root;
    }

    public TreeTableColumn<Produto, String> getColunaId() {
        return colunaId;
    }

    public void setColunaId(TreeTableColumn<Produto, String> colunaId) {
        this.colunaId = colunaId;
    }

    public TreeTableColumn<Produto, String> getColunaCodigo() {
        return colunaCodigo;
    }

    public void setColunaCodigo(TreeTableColumn<Produto, String> colunaCodigo) {
        this.colunaCodigo = colunaCodigo;
    }

    public TreeTableColumn<Produto, String> getColunaDescricao() {
        return colunaDescricao;
    }

    public void setColunaDescricao(TreeTableColumn<Produto, String> colunaDescricao) {
        this.colunaDescricao = colunaDescricao;
    }

    public TreeTableColumn<Produto, String> getColunaUndCom() {
        return colunaUndCom;
    }

    public void setColunaUndCom(TreeTableColumn<Produto, String> colunaUndCom) {
        this.colunaUndCom = colunaUndCom;
    }

    public TreeTableColumn<Produto, String> getColunaPrecoFabrica() {
        return colunaPrecoFabrica;
    }

    public void setColunaPrecoFabrica(TreeTableColumn<Produto, String> colunaPrecoFabrica) {
        this.colunaPrecoFabrica = colunaPrecoFabrica;
    }

    public TreeTableColumn<Produto, String> getColunaPrecoConsumidor() {
        return colunaPrecoConsumidor;
    }

    public void setColunaPrecoConsumidor(TreeTableColumn<Produto, String> colunaPrecoConsumidor) {
        this.colunaPrecoConsumidor = colunaPrecoConsumidor;
    }

    public TreeTableColumn<Produto, Integer> getColunaQtdEstoque() {
        return colunaQtdEstoque;
    }

    public void setColunaQtdEstoque(TreeTableColumn<Produto, Integer> colunaQtdEstoque) {
        this.colunaQtdEstoque = colunaQtdEstoque;
    }

    public TreeTableColumn<Produto, String> getColunaSituacaoSistema() {
        return colunaSituacaoSistema;
    }

    public void setColunaSituacaoSistema(TreeTableColumn<Produto, String> colunaSituacaoSistema) {
        this.colunaSituacaoSistema = colunaSituacaoSistema;
    }

    public TreeTableColumn<Produto, String> getColunaVarejo() {
        return colunaVarejo;
    }

    public void setColunaVarejo(TreeTableColumn<Produto, String> colunaVarejo) {
        this.colunaVarejo = colunaVarejo;
    }

    public TreeTableColumn<Produto, String> getColunaLote() {
        return colunaLote;
    }

    public void setColunaLote(TreeTableColumn<Produto, String> colunaLote) {
        this.colunaLote = colunaLote;
    }

    public TreeTableColumn<Produto, String> getColunaValidade() {
        return colunaValidade;
    }

    public void setColunaValidade(TreeTableColumn<Produto, String> colunaValidade) {
        this.colunaValidade = colunaValidade;
    }

    public TreeTableColumn<Produto, String> getColunaNotaEntrada() {
        return colunaNotaEntrada;
    }

    public void setColunaNotaEntrada(TreeTableColumn<Produto, String> colunaNotaEntrada) {
        this.colunaNotaEntrada = colunaNotaEntrada;
    }

    public void atualizaProdutos() {
        getProdutoObservableList().setAll(new ProdutoDAO().getAll(Produto.class, "descricao", null, null, null));
        getTtvProduto().refresh();
    }

    public void setProdutoObservableList(Produto produto) {
        this.produtoObservableList.set(produtoObservableList.indexOf(ttvProduto.getSelectionModel().getSelectedItem().getValue()),
                produto);
    }

    public void tabela() {
        try {
            Label lblId = new Label("id");
            lblId.setPrefWidth(48);
            colunaId = new TreeTableColumn<Produto, String>();
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(48);
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> {
                if (param.getValue().getValue().idProperty().get() == 0)
                    return new SimpleStringProperty("");
                return param.getValue().getValue().idProperty().asString();
            });

            Label lblCodigo = new Label("Código");
            lblCodigo.setPrefWidth(60);
            colunaCodigo = new TreeTableColumn<Produto, String>();
            colunaCodigo.setGraphic(lblCodigo);
            colunaCodigo.setPrefWidth(60);
            colunaCodigo.setStyle("-fx-alignment: center-right;");
            colunaCodigo.setCellValueFactory(param -> param.getValue().getValue().codigoProperty());

            Label lblDescricao = new Label("Descrição");
            lblDescricao.setPrefWidth(350);
            colunaDescricao = new TreeTableColumn<Produto, String>();
            colunaDescricao.setGraphic(lblDescricao);
            colunaDescricao.setPrefWidth(350);
            colunaDescricao.setCellValueFactory(param -> param.getValue().getValue().descricaoProperty());

            Label lblUndComercial = new Label("Und Com");
            lblUndComercial.setPrefWidth(70);
            colunaUndCom = new TreeTableColumn<Produto, String>();
            colunaUndCom.setGraphic(lblUndComercial);
            colunaUndCom.setPrefWidth(70);
            colunaUndCom.setCellValueFactory(
                    param -> new SimpleStringProperty(param.getValue().getValue().getUnidadeComercial().getDescricao()));

            Label lblVarejo = new Label("Varejo");
            lblVarejo.setPrefWidth(50);
            colunaVarejo = new TreeTableColumn<Produto, String>();
            colunaVarejo.setGraphic(lblVarejo);
            colunaVarejo.setPrefWidth(50);
            colunaVarejo.setStyle("-fx-alignment: center-right;");
            colunaVarejo.setCellValueFactory(param -> {
                if (param.getValue().getValue().varejoProperty().get() == 0)
                    return new SimpleStringProperty("");
                return param.getValue().getValue().varejoProperty().asString();
            });

            Label lblPrecoFab = new Label("Preço Fab.");
            lblPrecoFab.setPrefWidth(90);
            colunaPrecoFabrica = new TreeTableColumn<Produto, String>();
            colunaPrecoFabrica.setGraphic(lblPrecoFab);
            colunaPrecoFabrica.setPrefWidth(90);
            colunaPrecoFabrica.setStyle("-fx-alignment: center-right;");
            colunaPrecoFabrica.setCellValueFactory(
                    param -> {
                        if (param.getValue().getValue().precoFabricaProperty().get() == null)
                            return new SimpleStringProperty("");
                        return new SimpleStringProperty(ServiceMascara.getMoeda(param.getValue().getValue().precoFabricaProperty().toString(), 2));
                    });

            Label lblPrecoCons = new Label("Preço Cons.");
            lblPrecoCons.setPrefWidth(90);
            colunaPrecoConsumidor = new TreeTableColumn<Produto, String>();
            colunaPrecoConsumidor.setPrefWidth(90);
            colunaPrecoConsumidor.setGraphic(lblPrecoCons);
            colunaPrecoConsumidor.setStyle("-fx-alignment: center-right;");
            colunaPrecoConsumidor.setCellValueFactory(
                    param -> {
                        if (param.getValue().getValue().precoConsumidorProperty().get() == null)
                            return new SimpleStringProperty("");
                        return new SimpleStringProperty(ServiceMascara.getMoeda(param.getValue().getValue().precoConsumidorProperty().toString(), 2));
                    });

            Label lblSituacaoSistema = new Label("Situação");
            lblSituacaoSistema.setPrefWidth(100);
            colunaSituacaoSistema = new TreeTableColumn<Produto, String>();
            colunaSituacaoSistema.setGraphic(lblSituacaoSistema);
            colunaSituacaoSistema.setPrefWidth(100);
            colunaSituacaoSistema.setCellValueFactory(
                    param -> new SimpleStringProperty(param.getValue().getValue().getSituacao().getDescricao()));

            Label lblEstoque = new Label("Estoque");
            lblEstoque.setPrefWidth(65);
            colunaQtdEstoque = new TreeTableColumn<Produto, Integer>();
            colunaQtdEstoque.setGraphic(lblEstoque);
            colunaQtdEstoque.setPrefWidth(65);
            colunaQtdEstoque.setStyle("-fx-alignment: center-right;");
            colunaQtdEstoque.setCellValueFactory(param -> param.getValue().getValue().estoqueProperty().asObject());

            Label lblLote = new Label("Lote");
            lblLote.setPrefWidth(105);
            colunaLote = new TreeTableColumn<Produto, String>();
            colunaLote.setGraphic(lblLote);
            colunaLote.setPrefWidth(105);
            colunaLote.setStyle("-fx-alignment: center;");
            colunaLote.setCellValueFactory(param -> {
                if (param.getValue().getValue().loteProperty() == null)
                    return new SimpleStringProperty("");
                return param.getValue().getValue().loteProperty();
            });

            Label lblValidade = new Label("Validade");
            lblValidade.setPrefWidth(105);
            colunaValidade = new TreeTableColumn<Produto, String>();
            colunaValidade.setGraphic(lblValidade);
            colunaValidade.setPrefWidth(105);
            colunaValidade.setStyle("-fx-alignment: center-right;");
            colunaValidade.setCellValueFactory(param -> {
                if (param.getValue().getValue().getValidade() == null)
                    return new SimpleStringProperty("");
                return new SimpleStringProperty(DTF_DATA.format(param.getValue().getValue().getValidade()));
            });


            Label lblNotaEntrada = new Label("nf Ent.");
            lblNotaEntrada.setPrefWidth(100);
            colunaNotaEntrada = new TreeTableColumn<Produto, String>();
            colunaNotaEntrada.setPrefWidth(100);
            colunaNotaEntrada.setGraphic(lblNotaEntrada);
            colunaNotaEntrada.setStyle("-fx-alignment: center-right;");
            colunaNotaEntrada.setCellValueFactory(param -> {
                if (param.getValue().getValue().getNotaEntrada() == null)
                    return new SimpleStringProperty("");
                return param.getValue().getValue().notaEntradaProperty();
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void escutaLista() {
        getTtvProduto().setRowFactory(param -> {
            TreeTableRow<Produto> row = new TreeTableRow<Produto>() {
                @Override
                protected void updateItem(Produto item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        getStyleClass().remove("produto-estoque");
                    } else if (item.getDescricao() == null) {
                        if (!getStyleClass().contains("produto-estoque"))
                            getStyleClass().add("produto-estoque");
                    } else {
                        getStyleClass().remove("produto-estoque");
                    }
                }
            };
            return row;
        });

        getTtvProduto().addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER)
                    event.consume();
            }
        });

        getTxtPesquisaProduto().textProperty().addListener((ov, o, n) -> {
            String strBusca = n.toLowerCase().trim();
            getProdutoFilteredList().setPredicate(produto -> {
                if (produto.getCodigo().toLowerCase().contains(strBusca)) return true;
                if (produto.getDescricao().toLowerCase().contains(strBusca)) return true;
                if (produto.getNcm().toLowerCase().contains(strBusca)) return true;
                if (produto.getCest().toLowerCase().contains(strBusca)) return true;
                if (produto.getProdutoCodigoBarraList().stream()
                        .filter(codBarra -> codBarra.getCodigoBarra().toLowerCase().contains(strBusca))
                        .findFirst().orElse(null) != null) return true;
                return false;
            });
        });

        getLblRegistrosLocalizados().textProperty().bind(Bindings.createStringBinding(() ->
                String.format("%4d registro%s localizado%s.",
                        getProdutoFilteredList().size(),
                        getProdutoFilteredList().size() > 1
                                ? "s" : "",
                        getProdutoFilteredList().size() > 1
                                ? "s" : ""
                ), getProdutoFilteredList()
        ));

        getProdutoFilteredList().addListener((ListChangeListener<? super Produto>) c -> {
            preencherTabela();
            getTtvProduto().refresh();
        });
    }

    public void inicializaRoot() {
    }

    public void preencherTabela() {
        setRoot(new RecursiveTreeItem<Produto>(getProdutoFilteredList(), RecursiveTreeObject::getChildren));
        getRoot().getChildren().stream()
                .forEach(produtoTreeItem -> {
                    final int[] estoqTotal = {0};
                    produtoTreeItem.getValue().getProdutoEstoqueList().stream()
                            .forEach(produtoEstoque -> {
                                if (produtoEstoque.getQtd() > 0) {
                                    produtoTreeItem.getChildren().add(new TreeItem<Produto>(new Produto(produtoEstoque)));
                                    estoqTotal[0] += produtoEstoque.getQtd();
                                }
                            });
                    produtoTreeItem.getValue().setEstoque(estoqTotal[0]);
                });
        if (getTipoForm() == null)
            getTtvProduto().getColumns().setAll(getColunaId(), getColunaCodigo(), getColunaDescricao(),
                    getColunaUndCom(), getColunaVarejo(), getColunaPrecoFabrica(), getColunaPrecoConsumidor(),
                    getColunaSituacaoSistema(), getColunaQtdEstoque(), getColunaLote(), getColunaValidade(), getColunaNotaEntrada()
            );
        else if (getTipoForm() == "saidaProduto")
            getTtvProduto().getColumns().setAll(getColunaId(), getColunaCodigo(), getColunaDescricao(),
                    getColunaUndCom(), getColunaVarejo(), getColunaPrecoConsumidor(),
                    getColunaSituacaoSistema(), getColunaQtdEstoque(), getColunaLote(), getColunaValidade(), getColunaNotaEntrada()
            );
        getTtvProduto().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getTtvProduto().setRoot(getRoot());
        getTtvProduto().setShowRoot(false);
    }

    public boolean verificaExistenteCodigo(String busca, long idProd) {
        String strBusca = busca.toLowerCase().trim().replaceAll("\\D", "");
        Produto produto;
        if ((produto = produtoObservableList.stream()
                .filter(prod -> prod.getCodigo().equals(strBusca))
                .findFirst().orElse(null)) == null)
            return false;
        if (idProd > 0 && idProd == produto.getId())
            return false;
        alertMensagem = new ServiceAlertMensagem();
        alertMensagem.setCabecalho("Código duplicado");
        alertMensagem.setPromptText(String.format("%s, o código: [%s] já está cadastrado no sistema para o produto:\n[%s]!",
                LogadoInf.getUserLog().getApelido(),
                busca,
                produto.getDescricao()));
        alertMensagem.setStrIco("ic_atencao_triangulo_24dp");
        alertMensagem.getRetornoAlert_OK();
        return true;
    }

    public boolean verificaExistenteCodigoBarra(String busca, long idProd) {
        String strBusca = busca.toLowerCase().trim();
        Produto produto;
        if ((produto = produtoObservableList.stream()
                .filter(prod -> prod.getProdutoCodigoBarraList().stream()
                        .filter(barra -> barra.getCodigoBarra().equals(strBusca))
                        .count() > 0)
                .findFirst().orElse(null)) == null)
            return false;
        if (idProd > 0 && idProd == produto.getId())
            return false;
        alertMensagem = new ServiceAlertMensagem();
        alertMensagem.setCabecalho("Código de barras duplicado");
        alertMensagem.setPromptText(String.format("%s, o código de barras: [%s] já está cadastrado no sistema para o produto:\n[%s]!",
                LogadoInf.getUserLog().getApelido(),
                busca,
                produto.getDescricao()));
        alertMensagem.setStrIco("ic_atencao_triangulo_24dp");
        alertMensagem.getRetornoAlert_OK();
        return true;
    }

}
