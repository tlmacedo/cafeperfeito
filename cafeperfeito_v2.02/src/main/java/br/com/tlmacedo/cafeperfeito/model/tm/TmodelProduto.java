package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.model.enums.TModelTipo;
import br.com.tlmacedo.cafeperfeito.model.vo.Produto;
import br.com.tlmacedo.cafeperfeito.model.vo.ProdutoEstoque;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;

public class TmodelProduto {

    private final TModelTipo tModelTipo;
    private Label lblRegistrosLocalizados;
    private TextField txtPesquisa;
    private TreeTableView<Object> ttvProdutoEstoque;
    private FilteredList<Produto> produtoFilteredList;

    private TreeItem<Object> produtoEstoqueTreeItem;
    private TreeTableColumn<Object, String> colId;
    private TreeTableColumn<Object, String> colCodigo;
    private TreeTableColumn<Object, String> colDescricao;
    private TreeTableColumn<Object, String> colVarejo;
    private TreeTableColumn<Object, String> colUndCom;
    private TreeTableColumn<Object, String> colPrecoCompra;
    private TreeTableColumn<Object, String> colPrecoVenda;
    private TreeTableColumn<Object, Integer> colEstoque;
    private TreeTableColumn<Object, String> colLote;
    private TreeTableColumn<Object, String> colValidade;

    public TmodelProduto(TModelTipo tModelTipo) {
        this.tModelTipo = tModelTipo;
    }

    /**
     * Begin voids
     */

    public void tabela_criar() {
        setColId(new TreeTableColumn<>("id"));
        getColId().setPrefWidth(48);
        getColId().setStyle("-fx-alignment: center-right;");
        getColId().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return ((Produto) cellData.getValue().getValue()).idProperty().asString();
            return new SimpleStringProperty("");
        });

        setColCodigo(new TreeTableColumn<>("código"));
        getColCodigo().setPrefWidth(60);
        getColCodigo().setStyle("-fx-alignment: center-right;");
        getColCodigo().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return ((Produto) cellData.getValue().getValue()).codigoProperty();
            return new SimpleStringProperty("");
        });

        setColDescricao(new TreeTableColumn<>("descrição"));
        getColDescricao().setPrefWidth(350);
        getColDescricao().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return ((Produto) cellData.getValue().getValue()).descricaoProperty();
            return new SimpleStringProperty("");
        });

        setColVarejo(new TreeTableColumn<>("varejo"));
        getColVarejo().setPrefWidth(50);
        getColVarejo().setStyle("-fx-alignment: center-right;");
        getColVarejo().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return ((Produto) cellData.getValue().getValue()).varejoProperty().asString();
            return new SimpleStringProperty("");
        });

        setColUndCom(new TreeTableColumn<>("und com"));
        getColUndCom().setPrefWidth(70);
        getColUndCom().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return ((Produto) cellData.getValue().getValue()).unidadeComercialProperty().asString();
            return new SimpleStringProperty("");
        });

        setColPrecoCompra(new TreeTableColumn<>("preço compra"));
        getColPrecoCompra().setPrefWidth(90);
        getColPrecoCompra().setStyle("-fx-alignment: center-right;");
        getColPrecoCompra().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return new SimpleStringProperty(ServiceMascara.getMoeda(((Produto) cellData.getValue().getValue()).precoCompraProperty().getValue(), 2));
            return new SimpleStringProperty("0,00");
        });

        setColPrecoVenda(new TreeTableColumn<>("preço venda"));
        getColPrecoVenda().setPrefWidth(90);
        getColPrecoVenda().setStyle("-fx-alignment: center-right;");
        getColPrecoVenda().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return new SimpleStringProperty(ServiceMascara.getMoeda(((Produto) cellData.getValue().getValue()).precoVendaProperty().getValue(), 2));
            return new SimpleStringProperty("");
        });

        setColEstoque(new TreeTableColumn<>("estoque"));
        getColEstoque().setPrefWidth(65);
        getColEstoque().setStyle("-fx-alignment: center-right;");
        getColEstoque().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return ((Produto) cellData.getValue().getValue()).tblEstoqueProperty().asObject();
            if (cellData.getValue().getValue() instanceof ProdutoEstoque)
                return ((ProdutoEstoque) cellData.getValue().getValue()).qtdProperty().asObject();
            return new SimpleObjectProperty<>(0);
        });

        setColLote(new TreeTableColumn<>("lote"));
        getColLote().setPrefWidth(105);
        getColLote().setStyle("-fx-alignment: center;");
        getColLote().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Produto)
                return ((Produto) cellData.getValue().getValue()).tblLoteProperty();
            if (cellData.getValue().getValue() instanceof ProdutoEstoque)
                return ((ProdutoEstoque) cellData.getValue().getValue()).loteProperty();
            return new SimpleStringProperty("");
        });

        setColValidade(new TreeTableColumn<>("validade"));
        getColValidade().setPrefWidth(105);
        getColValidade().setStyle("-fx-alignment: center-right;");
        getColValidade().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ProdutoEstoque)
                return new SimpleStringProperty(((ProdutoEstoque) cellData.getValue().getValue()).dtValidadeProperty().getValue().format(DTF_DATA));
            return new SimpleStringProperty("");
        });

    }

    public void tabela_preencher() {
        setProdutoEstoqueTreeItem(new TreeItem<>());
        getProdutoFilteredList()
                .forEach(produto -> {
                            final int[] estq = {0};
                            TreeItem paiItem = new TreeItem(produto);
                            getProdutoEstoqueTreeItem().getChildren().add(paiItem);
                            if (gettModelTipo().equals(TModelTipo.PROD_VENDA))
                                produto.getProdutoEstoqueList().stream()
                                        .filter(produtoEstoque -> produtoEstoque.qtdProperty().getValue().compareTo(0) > 0)
                                        .sorted(Comparator.comparing(ProdutoEstoque::getDtValidade))
                                        .collect(Collectors.groupingBy(ProdutoEstoque::getLote,
                                                LinkedHashMap::new,
                                                Collectors.toList()))
                                        .forEach((s, produtoEstoques) -> {
                                            paiItem.getChildren().add(new TreeItem<>(new ProdutoEstoque(produtoEstoques)));
                                            estq[0] += produtoEstoques.stream().mapToInt(ProdutoEstoque::getQtd).sum();
                                        });
                            else
                                estq[0] = produto.getProdutoEstoqueList().stream().mapToInt(ProdutoEstoque::getQtd).sum();

                            ((Produto) paiItem.getValue()).tblEstoqueProperty().setValue(estq[0]);
                        }
                );

        getTtvProdutoEstoque().getColumns().setAll(
                getColId(), getColCodigo(), getColDescricao(), getColVarejo(), getColUndCom(), getColPrecoCompra(),
                getColPrecoVenda(), getColEstoque(), getColLote(), getColValidade()
        );

        if (gettModelTipo().equals(TModelTipo.PROD_VENDA))
            getTtvProdutoEstoque().getColumns().remove(getColPrecoCompra());
        else {
            getTtvProdutoEstoque().getColumns().remove(getColLote());
            getTtvProdutoEstoque().getColumns().remove(getColValidade());
        }

        getTtvProdutoEstoque().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        getTtvProdutoEstoque().setRoot(getProdutoEstoqueTreeItem());
        getTtvProdutoEstoque().setShowRoot(false);
    }

    public void escutaLista() {
        getTtvProdutoEstoque().setRowFactory(objectTreeTableView -> new TreeTableRow<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().removeAll(getStyleClass().stream().filter(s -> s.contains("produto-")).collect(Collectors.toList()));
                if (!empty)
                    if (item instanceof ProdutoEstoque)
                        getStyleClass().add("produto-estoque");
            }
        });

        getTxtPesquisa().textProperty().addListener((ov, o, n) -> {
            String strFind = n.toLowerCase().trim();
            getProdutoFilteredList().setPredicate(produto -> {
                if (produto.idProperty().toString().contains(strFind))
                    return true;
                if (produto.codigoProperty().get().toLowerCase().contains(strFind))
                    return true;
                if (produto.descricaoProperty().get().toLowerCase().contains(strFind))
                    return true;
                if (produto.ncmProperty().get().contains(strFind))
                    return true;
                if (produto.cestProperty().get().contains(strFind))
                    return true;
                return produto.getProdutoCodigoBarraList().stream()
                        .anyMatch(codBarra -> codBarra.getCodigoBarra().contains(strFind));
            });
        });

        getProdutoFilteredList().addListener((ListChangeListener<? super Produto>) change ->
                Platform.runLater(() -> {
                    tabela_preencher();
                    getTtvProdutoEstoque().refresh();
                }));

        getLblRegistrosLocalizados().textProperty().bind(Bindings.createStringBinding(() ->
                String.format("%5d", getProdutoFilteredList().size()), getProdutoFilteredList()
        ));
    }

    public void atualizarProdutos() {
//        getProdutoFilteredList().setAll(new ProdutoDAO().getAll(Produto.class, null, "descricao"));
//        getProdutoFilteredList().setAll(new ProdutoDAO().getAll(Produto.class, null, "descricao").stream()
//                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        getTtvProdutoEstoque().refresh();
    }

    /**
     * END Voids
     */

    /**
     * Begin Gets and Setters
     */

    public TModelTipo gettModelTipo() {
        return tModelTipo;
    }

    public Label getLblRegistrosLocalizados() {
        return lblRegistrosLocalizados;
    }

    public void setLblRegistrosLocalizados(Label lblRegistrosLocalizados) {
        this.lblRegistrosLocalizados = lblRegistrosLocalizados;
    }

    public TextField getTxtPesquisa() {
        return txtPesquisa;
    }

    public void setTxtPesquisa(TextField txtPesquisa) {
        this.txtPesquisa = txtPesquisa;
    }

    public TreeTableView<Object> getTtvProdutoEstoque() {
        return ttvProdutoEstoque;
    }

    public void setTtvProdutoEstoque(TreeTableView<Object> ttvProdutoEstoque) {
        this.ttvProdutoEstoque = ttvProdutoEstoque;
    }

    public FilteredList<Produto> getProdutoFilteredList() {
        return produtoFilteredList;
    }

    public void setProdutoFilteredList(FilteredList<Produto> produtoFilteredList) {
        this.produtoFilteredList = produtoFilteredList;
    }

    public TreeItem<Object> getProdutoEstoqueTreeItem() {
        return produtoEstoqueTreeItem;
    }

    public void setProdutoEstoqueTreeItem(TreeItem<Object> produtoEstoqueTreeItem) {
        this.produtoEstoqueTreeItem = produtoEstoqueTreeItem;
    }

    public TreeTableColumn<Object, String> getColId() {
        return colId;
    }

    public void setColId(TreeTableColumn<Object, String> colId) {
        this.colId = colId;
    }

    public TreeTableColumn<Object, String> getColCodigo() {
        return colCodigo;
    }

    public void setColCodigo(TreeTableColumn<Object, String> colCodigo) {
        this.colCodigo = colCodigo;
    }

    public TreeTableColumn<Object, String> getColDescricao() {
        return colDescricao;
    }

    public void setColDescricao(TreeTableColumn<Object, String> colDescricao) {
        this.colDescricao = colDescricao;
    }

    public TreeTableColumn<Object, String> getColVarejo() {
        return colVarejo;
    }

    public void setColVarejo(TreeTableColumn<Object, String> colVarejo) {
        this.colVarejo = colVarejo;
    }

    public TreeTableColumn<Object, String> getColUndCom() {
        return colUndCom;
    }

    public void setColUndCom(TreeTableColumn<Object, String> colUndCom) {
        this.colUndCom = colUndCom;
    }

    public TreeTableColumn<Object, String> getColPrecoCompra() {
        return colPrecoCompra;
    }

    public void setColPrecoCompra(TreeTableColumn<Object, String> colPrecoCompra) {
        this.colPrecoCompra = colPrecoCompra;
    }

    public TreeTableColumn<Object, String> getColPrecoVenda() {
        return colPrecoVenda;
    }

    public void setColPrecoVenda(TreeTableColumn<Object, String> colPrecoVenda) {
        this.colPrecoVenda = colPrecoVenda;
    }

    public TreeTableColumn<Object, Integer> getColEstoque() {
        return colEstoque;
    }

    public void setColEstoque(TreeTableColumn<Object, Integer> colEstoque) {
        this.colEstoque = colEstoque;
    }

    public TreeTableColumn<Object, String> getColLote() {
        return colLote;
    }

    public void setColLote(TreeTableColumn<Object, String> colLote) {
        this.colLote = colLote;
    }

    public TreeTableColumn<Object, String> getColValidade() {
        return colValidade;
    }

    public void setColValidade(TreeTableColumn<Object, String> colValidade) {
        this.colValidade = colValidade;
    }

    /**
     * END Gets and Setters
     */
}
