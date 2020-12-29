package br.com.tlmacedo.cafeperfeito.model.tm;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-14
 * Time: 11:08
 */

public class TabModelMovimentacaoProduto {


//    private ProdutoDAO produtoDAO = new ProdutoDAO();
//    private TreeTableView<Produto> ttvProduto;
//    private Label lblRegistrosLocalizados;
//    private Label lblStatus;
//    private ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList();
//    private FilteredList<Produto> produtoFilteredList;
//    private ServiceAlertMensagem alertMensagem;
//
//    private TreeTableColumn<Produto, String> colunaId;
//    private TreeTableColumn<Produto, String> colunaCodigo;
//    private TreeTableColumn<Produto, String> colunaDescricao;
//    private TreeTableColumn<Produto, String> colunaPeso;
//    private TreeTableColumn<Produto, Integer> colunaQtd;
//    private TreeTableColumn<Produto, BigDecimal> colunaVlrUnitario;
//    private TreeTableColumn<Produto, BigDecimal> colunaVlrDesconto;
//    private TreeTableColumn<Produto, Integer> colunaVarejo;
//    private TreeTableColumn<Produto, Integer> colunaQtdEstoque;
//    private TreeTableColumn<Produto, String> colunaSituacaoSistema;
//    private TreeTableColumn<Produto, String> colunaLote;
//    private TreeTableColumn<Produto, String> colunaValidade;
//
//    public TabModelMovimentacaoProduto() {
//    }
//
//    public TreeTableView<Produto> getTtvProduto() {
//        return ttvProduto;
//    }
//
//    public void setTtvProduto(TreeTableView<Produto> ttvProduto) {
//        this.ttvProduto = ttvProduto;
//    }
//
//    public Label getLblStatus() {
//        return lblStatus;
//    }
//
//    public void setLblStatus(Label lblStatus) {
//        this.lblStatus = lblStatus;
//    }
//
//    public Label getLblRegistrosLocalizados() {
//        return lblRegistrosLocalizados;
//    }
//
//    public void setLblRegistrosLocalizados(Label lblRegistrosLocalizados) {
//        this.lblRegistrosLocalizados = lblRegistrosLocalizados;
//    }
//
//    public ObservableList<Produto> getProdutoObservableList() {
//        return produtoObservableList;
//    }
//
//    public void setProdutoObservableList(Produto produto) {
//        this.produtoObservableList.set(produtoObservableList.indexOf(ttvProduto.getSelectionModel().getSelectedItem().getValue()),
//                produto);
//        this.produtoFilteredList = new FilteredList<>(this.produtoObservableList);
//    }
//
//    public void setProdutoObservableList(ObservableList<Produto> produtoObservableList) {
//        this.produtoObservableList = produtoObservableList;
//    }
//
//    public FilteredList<Produto> getProdutoFilteredList() {
//        return produtoFilteredList;
//    }
//
//    public void setProdutoFilteredList(FilteredList<Produto> produtoFilteredList) {
//        this.produtoFilteredList = produtoFilteredList;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaId() {
//        return colunaId;
//    }
//
//    public void setColunaId(TreeTableColumn<Produto, String> colunaId) {
//        this.colunaId = colunaId;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaProdutoCodigo() {
//        return colunaCodigo;
//    }
//
//    public void setColunaProdutoCodigo(TreeTableColumn<Produto, String> colunaCodigo) {
//        this.colunaCodigo = colunaCodigo;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaProdutoDescricao() {
//        return colunaDescricao;
//    }
//
//    public void setColunaProdutoDescricao(TreeTableColumn<Produto, String> colunaDescricao) {
//        this.colunaDescricao = colunaDescricao;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaPeso() {
//        return colunaPeso;
//    }
//
//    public void setColunaPeso(TreeTableColumn<Produto, String> colunaPeso) {
//        this.colunaPeso = colunaPeso;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaQtd() {
//        return colunaQtd;
//    }
//
//    public void setColunaQtd(TreeTableColumn<Produto, String> colunaQtd) {
//        this.colunaQtd = colunaQtd;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaVlrDescontoLiquido() {
//        return colunaVlrDesconto;
//    }
//
//    public void setColunaVlrDescontoLiquido(TreeTableColumn<Produto, String> colunaVlrDesconto) {
//        this.colunaVlrDesconto = colunaVlrDesconto;
//    }
//
//    public TreeTableColumn<Produto, Integer> getColunaQtdEstoque() {
//        return colunaQtdEstoque;
//    }
//
//    public void setColunaQtdEstoque(TreeTableColumn<Produto, Integer> colunaQtdEstoque) {
//        this.colunaQtdEstoque = colunaQtdEstoque;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaSituacaoSistema() {
//        return colunaSituacaoSistema;
//    }
//
//    public void setColunaSituacaoSistema(TreeTableColumn<Produto, String> colunaSituacaoSistema) {
//        this.colunaSituacaoSistema = colunaSituacaoSistema;
//    }
//
//    public TreeTableColumn<Produto, Integer> getColunaVarejo() {
//        return colunaVarejo;
//    }
//
//    public void setColunaVarejo(TreeTableColumn<Produto, Integer> colunaVarejo) {
//        this.colunaVarejo = colunaVarejo;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaLote() {
//        return colunaLote;
//    }
//
//    public void setColunaLote(TreeTableColumn<Produto, String> colunaLote) {
//        this.colunaLote = colunaLote;
//    }
//
//    public TreeTableColumn<Produto, String> getColunaValidade() {
//        return colunaValidade;
//    }
//
//    public void setColunaValidade(TreeTableColumn<Produto, String> colunaValidade) {
//        this.colunaValidade = colunaValidade;
//    }
//
//    public void carregarLista() {
//        produtoFilteredList = new FilteredList<Produto>
//                (produtoObservableList = FXCollections.observableArrayList(produtoDAO.getAll(Produto.class, "descricao")));
//    }
//
//    public void tabela() {
//        try {
//            Label lblId = new Label("id");
//            lblId.setPrefWidth(48);
//            colunaId = new TreeTableColumn<Produto, String>();
//            colunaId.setGraphic(lblId);
//            colunaId.setPrefWidth(48);
//            colunaId.setStyle("-fx-alignment: center-right;");
//            colunaId.setCellValueFactory(param -> param.getValue().getValue().idProperty().asString());
//
//            Label lblCodigo = new Label("Código");
//            lblCodigo.setPrefWidth(60);
//            colunaCodigo = new TreeTableColumn<Produto, String>();
//            colunaCodigo.setGraphic(lblCodigo);
//            colunaCodigo.setPrefWidth(60);
//            colunaCodigo.setStyle("-fx-alignment: center-right;");
//            colunaCodigo.setCellValueFactory(param -> param.getValue().getValue().codigoProperty());
//
//            Label lblDescricao = new Label("Descrição");
//            lblDescricao.setPrefWidth(350);
//            colunaDescricao = new TreeTableColumn<Produto, String>();
//            colunaDescricao.setGraphic(lblDescricao);
//            colunaDescricao.setPrefWidth(350);
//            colunaDescricao.setCellValueFactory(param -> param.getValue().getValue().descricaoProperty());
//
//            Label lblUndComercial = new Label("Und Com");
//            lblUndComercial.setPrefWidth(70);
//            colunaPeso = new TreeTableColumn<Produto, String>();
//            colunaPeso.setGraphic(lblUndComercial);
//            colunaPeso.setPrefWidth(70);
//            colunaPeso.setCellValueFactory(
//                    param -> new SimpleStringProperty(param.getValue().getValue().getUnidadeComercial().getDescricao()));
//
//            Label lblVarejo = new Label("Varejo");
//            lblVarejo.setPrefWidth(50);
//            colunaVarejo = new TreeTableColumn<Produto, Integer>();
//            colunaVarejo.setGraphic(lblVarejo);
//            colunaVarejo.setPrefWidth(50);
//            colunaVarejo.setStyle("-fx-alignment: center-right;");
//            colunaVarejo.setCellValueFactory(param -> param.getValue().getValue().varejoProperty().asObject());
//
//            Label lblPrecoFab = new Label("Preço Fab.");
//            lblPrecoFab.setPrefWidth(90);
//            colunaQtd = new TreeTableColumn<Produto, String>();
//            colunaQtd.setGraphic(lblPrecoFab);
//            colunaQtd.setPrefWidth(90);
//            colunaQtd.setStyle("-fx-alignment: center-right;");
//            colunaQtd.setCellValueFactory(
//                    param -> new SimpleStringProperty(param.getValue().getValue().getPrecoFabrica().setScale(2).toString()));
//
//            Label lblPrecoCons = new Label("Preço Cons.");
//            lblPrecoCons.setPrefWidth(90);
//            colunaVlrDesconto = new TreeTableColumn<Produto, String>();
//            colunaVlrDesconto.setPrefWidth(90);
//            colunaVlrDesconto.setGraphic(lblPrecoCons);
//            colunaVlrDesconto.setStyle("-fx-alignment: center-right;");
//            colunaVlrDesconto.setCellValueFactory(
//                    param -> new SimpleStringProperty(param.getValue().getValue().getPrecoConsumidor().setScale(2).toString()));
//
//            Label lblSituacaoSistema = new Label("Situação");
//            lblSituacaoSistema.setPrefWidth(100);
//            colunaSituacaoSistema = new TreeTableColumn<Produto, String>();
//            colunaSituacaoSistema.setGraphic(lblSituacaoSistema);
//            colunaSituacaoSistema.setPrefWidth(100);
//            colunaSituacaoSistema.setCellValueFactory(
//                    param -> new SimpleStringProperty(param.getValue().getValue().getSituacao().getDescricao()));
//
////            Label lblEstoque = new Label("Estoque");
////            lblEstoque.setPrefWidth(65);
////            colunaQtdEstoque = new TreeTableColumn<Produto, Integer>();
////            colunaQtdEstoque.setGraphic(lblEstoque);
////            colunaQtdEstoque.setPrefWidth(65);
////            colunaQtdEstoque.setStyle("-fx-alignment: center-right;");
////            colunaQtdEstoque.setCellValueFactory(param -> param.getValue().getValue().estoqueProperty().asObject());
//
////            Label lblLote = new Label("Lote");
////            lblLote.setPrefWidth(105);
////            colunaLote = new TreeTableColumn<Produto, String>();
////            colunaLote.setGraphic(lblLote);
////            colunaLote.setPrefWidth(105);
////            colunaLote.setStyle("-fx-alignment: center;");
////            colunaLote.setCellValueFactory(param -> {
////                if (param.getValue().getValue().loteProperty() == null)
////                    return new SimpleStringProperty("");
////                return param.getValue().getValue().loteProperty();
////            });
//
////            Label lblValidade = new Label("Validade");
////            lblValidade.setPrefWidth(105);
////            colunaValidade = new TreeTableColumn<Produto, String>();
////            colunaValidade.setGraphic(lblValidade);
////            colunaValidade.setPrefWidth(105);
////            colunaValidade.setStyle("-fx-alignment: center-right;");
////            colunaValidade.setCellValueFactory(param -> {
////                if (param.getValue().getValue().getValidade() == null)
////                    return new SimpleStringProperty("");
////                return new SimpleStringProperty(Constants.DTF_DATA.format(param.getValue().getValue().getValidade().toLocalDateTime().toLocalDate()));
////            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void escutaLista() {
//        ttvProduto.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.ENTER)
//                    event.consume();
//            }
//        });
//
//    }
//
//    public void preencherTabela() {
//        final TreeItem<Produto> root = new RecursiveTreeItem<Produto>(produtoFilteredList, RecursiveTreeObject::getChildren);
//        ttvProduto.getColumns().setAll(getColunaId(), getColunaProdutoCodigo(),
//                getColunaProdutoDescricao(), getColunaPeso(), getColunaVarejo(),
//                getColunaQtd(), getColunaVlrDescontoLiquido(),
//                getColunaSituacaoSistema()//,
////                getColunaQtdEstoque(), getColunaLote(), getColunaValidade()
//        );
//        ttvProduto.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        ttvProduto.setRoot(root);
//        ttvProduto.setShowRoot(false);
//    }
//
//    public void pesquisa(String busca) {
//        String strBusca = busca.toLowerCase().trim();
//        produtoFilteredList.setPredicate(produto -> {
//            if (produto.getCodigo().toLowerCase().contains(strBusca)) return true;
//            if (produto.getDescricao().toLowerCase().contains(strBusca)) return true;
//            if (produto.getNcm().toLowerCase().contains(strBusca)) return true;
//            if (produto.getCest().toLowerCase().contains(strBusca)) return true;
//            if (produto.getProdutoCodigoBarraList().stream()
//                    .filter(codBarra -> codBarra.getCodigoBarra().toLowerCase().contains(busca))
//                    .findFirst().orElse(null) != null) return true;
//            return false;
//        });
//        atualizaRegistrosLocalizados();
//        preencherTabela();
//    }
//
//    public boolean verificaExistenteCodigo(String busca, long idProd) {
//        String strBusca = busca.toLowerCase().trim().replaceAll("\\D", "");
//        Produto produto;
//        if ((produto = produtoObservableList.stream()
//                .filter(prod -> prod.getCodigo().equals(strBusca))
//                .findFirst().orElse(null)) == null)
//            return false;
//        if (idProd > 0 && idProd == produto.getId())
//            return false;
//        alertMensagem = new ServiceAlertMensagem();
//        alertMensagem.setCabecalho("Código duplicado");
//        alertMensagem.setPromptText(String.format("%s, o código: [%s] já está cadastrado no sistema para o produto:\n[%s]!",
//                LogadoInf.getUserLog().getApelido(),
//                busca,
//                produto.getDescricao()));
//        alertMensagem.setStrIco("ic_atencao_triangulo_24dp");
//        alertMensagem.getRetornoAlert_OK();
//        return true;
//    }
//
//    public boolean verificaExistenteCodigoBarra(String busca, long idProd) {
//        String strBusca = busca.toLowerCase().trim();
//        Produto produto;
//        if ((produto = produtoObservableList.stream()
//                .filter(prod -> prod.getProdutoCodigoBarraList().stream()
//                        .filter(barra -> barra.getCodigoBarra().equals(strBusca))
//                        .count() > 0)
//                .findFirst().orElse(null)) == null)
//            return false;
//        if (idProd > 0 && idProd == produto.getId())
//            return false;
//        alertMensagem = new ServiceAlertMensagem();
//        alertMensagem.setCabecalho("Código de barras duplicado");
//        alertMensagem.setPromptText(String.format("%s, o código de barras: [%s] já está cadastrado no sistema para o produto:\n[%s]!",
//                LogadoInf.getUserLog().getApelido(),
//                busca,
//                produto.getDescricao()));
//        alertMensagem.setStrIco("ic_atencao_triangulo_24dp");
//        alertMensagem.getRetornoAlert_OK();
//        return true;
//    }
//
////    public boolean verificaExistente(String busca, Produto produto) {
////        String strBusca = busca.toLowerCase().trim();
////        FilteredList<Produto> filteredList = new FilteredList<>(produtoObservableList);
////        filteredList.setPredicate(prod -> {
////            if (prod.getId() == Long.valueOf(strBusca)) return true;
////            if (prod.getCodigo().toLowerCase().contains(strBusca)) return true;
////            if (prod.getProdutoCodigoBarraList().stream()
////                    .filter(codBarra -> codBarra.getCodigoBarra().toLowerCase().contains(strBusca))
////                    .findFirst().orElse(null) != null) return true;
////            return false;
////        });
////
////        if ((filteredList.size() > 0)
////                || (produto.getProdutoCodigoBarraList().stream()
////                .filter(codBarra -> codBarra.getCodigoBarra().toLowerCase().contains(strBusca))
////                .findFirst().orElse(null) != null)) {
////            alertMensagem = new ServiceAlertMensagem();
////            alertMensagem.setCabecalho(String.format("Código%s duplicado", (busca.replaceAll("\\D", "").length() == 13) ? " de barras" : ""));
////            alertMensagem.setPromptText(String.format("%s, o código%s: [%s] já está cadastrado no sistema para o produto: [%s]!",
////                    LogadoInf.getUserLog().getApelido(),
////                    (busca.replaceAll("\\D", "").length() == 13) ? " de barras" : "",
////                    busca, (filteredList.size() > 0) ? filteredList.get(0).getDescricao() : produto.getDescricao()));
////            alertMensagem.setStrIco("ic_atencao_triangulo_24dp");
////            alertMensagem.getRetornoAlert_OK();
////            return true;
////        }
////        return false;
////    }
//
//    public void atualizaRegistrosLocalizados() {
//        int qtd = produtoFilteredList.size();
//        lblRegistrosLocalizados.setText(String.format("%-4d registro%2$s localizado%2$s.",
//                qtd,
//                qtd > 1 ? "s" : ""
//        ));
//    }
//
}
