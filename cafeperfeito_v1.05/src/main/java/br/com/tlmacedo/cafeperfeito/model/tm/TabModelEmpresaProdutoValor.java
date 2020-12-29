package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import br.com.tlmacedo.cafeperfeito.model.dao.ProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.EmpresaProdutoValor;
import br.com.tlmacedo.cafeperfeito.model.vo.Produto;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.SetCellFactoryTableCell_ComboBox;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.SetCellFactoryTableCell_DatePicker;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.SetCellFactoryTableCell_EditingCell;
import br.com.tlmacedo.cafeperfeito.service.ServiceComandoTecladoMouse;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TabModelEmpresaProdutoValor {

    private TablePosition tp;
    private TableView<EmpresaProdutoValor> tvEmpresaProdutoValor;
    private ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(new ProdutoDAO().getAll(Produto.class, "descricao", null, null, null));
    private ObservableList<EmpresaProdutoValor> empresaProdutoValorObservableList;

    private TableColumn<EmpresaProdutoValor, String> colunaId;
    private TableColumn<EmpresaProdutoValor, String> colunaProdutoId;
    private TableColumn<EmpresaProdutoValor, String> colunaProdutoCodigo;
    private TableColumn<EmpresaProdutoValor, Produto> colunaProdutoDescricao;
    private TableColumn<EmpresaProdutoValor, String> colunaPrecoConsumidor;
    private TableColumn<EmpresaProdutoValor, String> colunaQtdMinima;
    private TableColumn<EmpresaProdutoValor, String> colunaDias;
    private TableColumn<EmpresaProdutoValor, String> colunaBonificacao;
    private TableColumn<EmpresaProdutoValor, String> colunaDesconto;
    private TableColumn<EmpresaProdutoValor, LocalDate> colunaValidade;

    public TabModelEmpresaProdutoValor() {
    }

    public TablePosition getTp() {
        return tp;
    }

    public void setTp(TablePosition tp) {
        this.tp = tp;
    }

    public TableView<EmpresaProdutoValor> getTvEmpresaProdutoValor() {
        return tvEmpresaProdutoValor;
    }

    public void setTvEmpresaProdutoValor(TableView<EmpresaProdutoValor> tvEmpresaProdutoValor) {
        this.tvEmpresaProdutoValor = tvEmpresaProdutoValor;
    }

    public ObservableList<Produto> getProdutoObservableList() {
        return produtoObservableList;
    }

    public void setProdutoObservableList(ObservableList<Produto> produtoObservableList) {
        this.produtoObservableList = produtoObservableList;
    }

    public ObservableList<EmpresaProdutoValor> getEmpresaProdutoValorObservableList() {
        return empresaProdutoValorObservableList;
    }

    public void setEmpresaProdutoValorObservableList(ObservableList<EmpresaProdutoValor> empresaProdutoValorObservableList) {
        this.empresaProdutoValorObservableList = empresaProdutoValorObservableList;
    }

    public TableColumn<EmpresaProdutoValor, String> getColunaId() {
        return colunaId;
    }

    public void setColunaId(TableColumn<EmpresaProdutoValor, String> colunaId) {
        this.colunaId = colunaId;
    }

    public TableColumn<EmpresaProdutoValor, String> getColunaProdutoId() {
        return colunaProdutoId;
    }

    public void setColunaProdutoId(TableColumn<EmpresaProdutoValor, String> colunaProdutoId) {
        this.colunaProdutoId = colunaProdutoId;
    }

    public TableColumn<EmpresaProdutoValor, String> getColunaProdutoCodigo() {
        return colunaProdutoCodigo;
    }

    public void setColunaProdutoCodigo(TableColumn<EmpresaProdutoValor, String> colunaProdutoCodigo) {
        this.colunaProdutoCodigo = colunaProdutoCodigo;
    }

    public TableColumn<EmpresaProdutoValor, Produto> getColunaProdutoDescricao() {
        return colunaProdutoDescricao;
    }

    public void setColunaProdutoDescricao(TableColumn<EmpresaProdutoValor, Produto> colunaProdutoDescricao) {
        this.colunaProdutoDescricao = colunaProdutoDescricao;
    }

    public TableColumn<EmpresaProdutoValor, String> getColunaPrecoConsumidor() {
        return colunaPrecoConsumidor;
    }

    public void setColunaPrecoConsumidor(TableColumn<EmpresaProdutoValor, String> colunaPrecoConsumidor) {
        this.colunaPrecoConsumidor = colunaPrecoConsumidor;
    }

    public TableColumn<EmpresaProdutoValor, String> getColunaQtdMinima() {
        return colunaQtdMinima;
    }

    public void setColunaQtdMinima(TableColumn<EmpresaProdutoValor, String> colunaQtdMinima) {
        this.colunaQtdMinima = colunaQtdMinima;
    }

    public TableColumn<EmpresaProdutoValor, String> getColunaDias() {
        return colunaDias;
    }

    public void setColunaDias(TableColumn<EmpresaProdutoValor, String> colunaDias) {
        this.colunaDias = colunaDias;
    }

    public TableColumn<EmpresaProdutoValor, String> getColunaBonificacao() {
        return colunaBonificacao;
    }

    public void setColunaBonificacao(TableColumn<EmpresaProdutoValor, String> colunaBonificacao) {
        this.colunaBonificacao = colunaBonificacao;
    }

    public TableColumn<EmpresaProdutoValor, String> getColunaDesconto() {
        return colunaDesconto;
    }

    public void setColunaDesconto(TableColumn<EmpresaProdutoValor, String> colunaDesconto) {
        this.colunaDesconto = colunaDesconto;
    }

    public TableColumn<EmpresaProdutoValor, LocalDate> getColunaValidade() {
        return colunaValidade;
    }

    public void setColunaValidade(TableColumn<EmpresaProdutoValor, LocalDate> colunaValidade) {
        this.colunaValidade = colunaValidade;
    }

    @SuppressWarnings("Duplicates")
    public void tabela() {
        try {
            Label lblId = new Label("id");
            lblId.setPrefWidth(35);
            colunaId = new TableColumn<EmpresaProdutoValor, String>();
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(lblId.getPrefWidth());
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> {
                if (param.getValue().idProperty().get() == 0)
                    return new SimpleStringProperty("");
                return param.getValue().idProperty().asString();
            });

            Label lblProdutoId = new Label("id");
            lblProdutoId.setPrefWidth(30);
            colunaProdutoId = new TableColumn<EmpresaProdutoValor, String>();
            colunaProdutoId.setGraphic(lblProdutoId);
            colunaProdutoId.setPrefWidth(lblProdutoId.getPrefWidth());
            colunaProdutoId.setStyle("-fx-alignment: center-right;");
            colunaProdutoId.setCellValueFactory(param -> {
                EmpresaProdutoValor data = param.getValue();
                return Bindings.createObjectBinding(() -> {
                            String value = "";
                            if (data.produtoProperty().get() != null)
                                value = String.valueOf(data.produtoProperty().get().idProperty().get());
                            param.getValue().setProdutoId(Integer.parseInt(value));
                            return value;
                        }, data.produtoProperty()
                );
            });

            Label lblProdutoCodigo = new Label("Cód");
            lblProdutoCodigo.setPrefWidth(50);
            colunaProdutoCodigo = new TableColumn<EmpresaProdutoValor, String>();
            colunaProdutoCodigo.setGraphic(lblProdutoCodigo);
            colunaProdutoCodigo.setPrefWidth(lblProdutoCodigo.getPrefWidth());
            colunaProdutoCodigo.setStyle("-fx-alignment: center-right;");
            colunaProdutoCodigo.setCellValueFactory(param -> {
                EmpresaProdutoValor data = param.getValue();
                return Bindings.createObjectBinding(() -> {
                            String value = "";
                            if (data.produtoProperty().get() != null)
                                value = data.produtoProperty().get().codigoProperty().get();
                            param.getValue().setProdutoCodigo(value);
                            return value;
                        }, data.produtoProperty()
                );
            });

            Label lblProdutoDescricao = new Label("Descrição");
            lblProdutoDescricao.setPrefWidth(320);
            colunaProdutoDescricao = new TableColumn<EmpresaProdutoValor, Produto>();
            colunaProdutoDescricao.setGraphic(lblProdutoDescricao);
            colunaProdutoDescricao.setPrefWidth(lblProdutoDescricao.getPrefWidth());
            colunaProdutoDescricao.setCellValueFactory(param ->
                    new SimpleObjectProperty<>(param.getValue().getProduto()));
            colunaProdutoDescricao.setCellFactory(param ->
                    new SetCellFactoryTableCell_ComboBox<EmpresaProdutoValor, Produto>(getProdutoObservableList()));
            colunaProdutoDescricao.setOnEditCommit(event -> {
                event.getRowValue().setProduto(event.getNewValue());
                tvEmpresaProdutoValor.getSelectionModel().selectNext();
            });

            Label lblPrecoCons = new Label("vlr.");
            lblPrecoCons.setPrefWidth(65);
            colunaPrecoConsumidor = new TableColumn<EmpresaProdutoValor, String>();
            colunaPrecoConsumidor.setPrefWidth(lblPrecoCons.getPrefWidth());
            colunaPrecoConsumidor.setGraphic(lblPrecoCons);
            colunaPrecoConsumidor.setStyle("-fx-alignment: center-right;");
            colunaPrecoConsumidor.setCellValueFactory(param -> {
                EmpresaProdutoValor data = param.getValue();
                return Bindings.createObjectBinding(() -> {
                            if (data.valorProperty().get() != BigDecimal.ZERO)
                                return ServiceMascara.getMoeda(data.valorProperty().get().setScale(2).toString(), 2);
                            String value = BigDecimal.ZERO.setScale(2).toString();
                            if (!data.produtoProperty().get().precoConsumidorProperty().get().equals(BigDecimal.ZERO))
                                value = ServiceMascara.getMoeda(data.produtoProperty().get().precoConsumidorProperty().get().setScale(2).toString(), 2);
                            param.getValue().setValor(new BigDecimal(value));
                            return value;
                        }, data.produtoProperty()
                );
            });
            colunaPrecoConsumidor.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EmpresaProdutoValor, String>(
                    ServiceMascara.getNumeroMask(12, 2)));
            colunaPrecoConsumidor.setOnEditCommit(event -> {
                event.getRowValue().setValor(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
                tvEmpresaProdutoValor.getSelectionModel().selectNext();
            });

            Label lblQtdMinima = new Label("qtd.");
            lblQtdMinima.setPrefWidth(45);
            colunaQtdMinima = new TableColumn<EmpresaProdutoValor, String>();
            colunaQtdMinima.setPrefWidth(lblQtdMinima.getPrefWidth());
            colunaQtdMinima.setGraphic(lblQtdMinima);
            colunaQtdMinima.setStyle("-fx-alignment: center-right;");
            colunaQtdMinima.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().qtdMinimaProperty()), 0)));
            colunaQtdMinima.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EmpresaProdutoValor, String>(
                    ServiceMascara.getNumeroMask(12, 0)));
            colunaQtdMinima.setOnEditCommit(event -> {
                event.getRowValue().setQtdMinima(Integer.parseInt(event.getNewValue()));
                tvEmpresaProdutoValor.getSelectionModel().selectNext();
            });

            Label lblDias = new Label("dias");
            lblDias.setPrefWidth(45);
            colunaDias = new TableColumn<EmpresaProdutoValor, String>();
            colunaDias.setPrefWidth(lblDias.getPrefWidth());
            colunaDias.setGraphic(lblDias);
            colunaDias.setStyle("-fx-alignment: center-right;");
            colunaDias.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().diasProperty()), 0)));
            colunaDias.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EmpresaProdutoValor, String>(
                    ServiceMascara.getNumeroMask(12, 0)));
            colunaDias.setOnEditCommit(event -> {
                event.getRowValue().setDias(Integer.parseInt(event.getNewValue()));
                tvEmpresaProdutoValor.getSelectionModel().selectNext();
            });

            Label lblBonificacao = new Label("bonif");
            lblBonificacao.setPrefWidth(45);
            colunaBonificacao = new TableColumn<EmpresaProdutoValor, String>();
            colunaBonificacao.setPrefWidth(lblBonificacao.getPrefWidth());
            colunaBonificacao.setGraphic(lblBonificacao);
            colunaBonificacao.setStyle("-fx-alignment: center-right;");
            colunaBonificacao.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().bonificacaoProperty()), 0)));
            colunaBonificacao.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EmpresaProdutoValor, String>(
                    ServiceMascara.getNumeroMask(12, 0)));
            colunaBonificacao.setOnEditCommit(event -> {
                event.getRowValue().setBonificacao(Integer.parseInt(event.getNewValue()));
                tvEmpresaProdutoValor.getSelectionModel().selectNext();
            });


            Label lblDesconto = new Label("Desconto");
            lblDesconto.setPrefWidth(65);
            colunaDesconto = new TableColumn<EmpresaProdutoValor, String>();
            colunaDesconto.setPrefWidth(lblDesconto.getPrefWidth());
            colunaDesconto.setGraphic(lblDesconto);
            colunaDesconto.setStyle("-fx-alignment: center-right;");
            colunaDesconto.setCellValueFactory(
                    param -> {
                        if (param.getValue().descontoProperty().get() == null)
                            return new SimpleStringProperty(ServiceMascara.getMoeda(new BigDecimal("0").setScale(2).toString(), 2));
                        return new SimpleStringProperty(ServiceMascara.getMoeda(param.getValue().descontoProperty().get().setScale(2).toString(), 2));
                    });
            colunaDesconto.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EmpresaProdutoValor, String>(
                    ServiceMascara.getNumeroMask(12, 2)));
            colunaDesconto.setOnEditCommit(event -> {
                event.getRowValue().setDesconto(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
                tvEmpresaProdutoValor.getSelectionModel().selectNext();
            });

            Label lblValidade = new Label("Validade");
            lblValidade.setPrefWidth(90);
            colunaValidade = new TableColumn<EmpresaProdutoValor, LocalDate>();
            colunaValidade.setGraphic(lblValidade);
            colunaValidade.setPrefWidth(lblValidade.getPrefWidth());
            colunaValidade.setStyle("-fx-alignment: center-right;");
            colunaValidade.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().validadeDescontoProperty().get()));
            colunaValidade.setCellFactory(param -> new SetCellFactoryTableCell_DatePicker<>());
            colunaValidade.setOnEditCommit(event -> {
                event.getRowValue().setValidadeDesconto(event.getNewValue());
                tvEmpresaProdutoValor.getSelectionModel().selectNext();
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void escutaLista() {
        getEmpresaProdutoValorObservableList().addListener((ListChangeListener<? super EmpresaProdutoValor>) c -> {
            getTvEmpresaProdutoValor().refresh();
        });

        getTvEmpresaProdutoValor().getFocusModel().focusedCellProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            setTp(n);
        });

        getTvEmpresaProdutoValor().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (getTvEmpresaProdutoValor().getEditingCell() == null && event.getCode() == KeyCode.ENTER) {
                getTvEmpresaProdutoValor().getSelectionModel().selectNext();
                setTp(getTvEmpresaProdutoValor().getFocusModel().getFocusedCell());
                event.consume();
            }
            if (event.getCode() == KeyCode.ENTER && getTp().getTableColumn() == getColunaValidade())
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ServiceComandoTecladoMouse.pressTecla(KeyCode.F6));

            if (event.getCode() == KeyCode.DELETE)
                if (getTvEmpresaProdutoValor().getEditingCell() == null)
                    getEmpresaProdutoValorObservableList().remove(getTvEmpresaProdutoValor().getSelectionModel().getSelectedItem());
        });

        getTvEmpresaProdutoValor().setOnKeyPressed(event -> {
            if (!event.isControlDown() &&
                    (event.getCode().isLetterKey() || event.getCode().isDigitKey())) {
                ControllerPrincipal.setLastKey(event.getText());
                getTvEmpresaProdutoValor().edit(getTp().getRow(), getTp().getTableColumn());
            }
        });
    }


    public void preencherTabela() {
        getTvEmpresaProdutoValor().getColumns().setAll(getColunaProdutoId(), getColunaProdutoCodigo(),
                getColunaProdutoDescricao(), getColunaPrecoConsumidor(), getColunaQtdMinima(),
                getColunaDias(), getColunaBonificacao(), getColunaDesconto(), getColunaValidade()
        );
        getTvEmpresaProdutoValor().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getTvEmpresaProdutoValor().getSelectionModel().setCellSelectionEnabled(true);
        getTvEmpresaProdutoValor().setEditable(true);

        getTvEmpresaProdutoValor().setItems(getEmpresaProdutoValorObservableList());
    }
}
