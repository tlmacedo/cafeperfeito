package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import br.com.tlmacedo.cafeperfeito.model.vo.Empresa;
import br.com.tlmacedo.cafeperfeito.model.vo.EmpresaProdutoValor;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProdutoProduto;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.SetCellFactoryTableCell_EditingCell;
import br.com.tlmacedo.cafeperfeito.service.ServiceDataHora;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATA;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-08
 * Time: 14:37
 */

public class TabModelSaidaProdutoProduto {

    private Empresa clienteDescontos;

    private ObjectProperty<LocalDate> vencimento = new SimpleObjectProperty<>();
    private StringProperty prazo = new SimpleStringProperty("");
    private IntegerProperty numItens = new SimpleIntegerProperty();
    private IntegerProperty qtdItens = new SimpleIntegerProperty();
    private IntegerProperty qtdVolume = new SimpleIntegerProperty();
    private ObjectProperty<BigDecimal> totalBruto = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalDesconto = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));
    private ObjectProperty<BigDecimal> totalLiquido = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO.setScale(2));

    private TablePosition tp;
    private JFXTextField txtPesquisaProduto;
    private TableView<SaidaProdutoProduto> tvSaidaProdutoProduto;
    private ObservableList<SaidaProdutoProduto> saidaProdutoProdutoObservableList = FXCollections.observableArrayList();
    private ObservableList<EmpresaProdutoValor> empresaProdutoValorObservableList = FXCollections.observableArrayList();

    private TableColumn<SaidaProdutoProduto, String> colunaId;
    private TableColumn<SaidaProdutoProduto, String> colunaProdutoId;
    private TableColumn<SaidaProdutoProduto, String> colunaProdutoCodigo;
    private TableColumn<SaidaProdutoProduto, String> colunaProdutoDescricao;
    private TableColumn<SaidaProdutoProduto, String> colunaProdutoLote;
    private TableColumn<SaidaProdutoProduto, String> colunaProdutoValidade;
    private TableColumn<SaidaProdutoProduto, String> colunaQtd;
    //    private TableColumn<SaidaProdutoProduto, String> colunaVlrDescontoUnitario;
    private TableColumn<SaidaProdutoProduto, String> colunaVlrConsumidor;
    private TableColumn<SaidaProdutoProduto, String> colunaVlrBruto;
    private TableColumn<SaidaProdutoProduto, String> colunaVlrDescontoLiquido;
    private TableColumn<SaidaProdutoProduto, String> colunaVlrLiquido;

    private TableColumn<SaidaProdutoProduto, String> colunaEstoque;
    private TableColumn<SaidaProdutoProduto, String> colunaVarejo;
    private TableColumn<SaidaProdutoProduto, String> colunaVolume;

    public TabModelSaidaProdutoProduto() {
    }

    public LocalDate getVencimento() {
        return vencimento.get();
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento.set(vencimento);
    }

    public ObjectProperty<LocalDate> vencimentoProperty() {
        return vencimento;
    }

    public String getPrazo() {
        return prazo.get();
    }

    public void setPrazo(String prazo) {
        this.prazo.set(prazo);
    }

    public StringProperty prazoProperty() {
        return prazo;
    }

    public Empresa getClienteDescontos() {
        return clienteDescontos;
    }

    public void setClienteDescontos(Empresa clienteDescontos) {
        this.clienteDescontos = clienteDescontos;
        calculaDescontosCliente();
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

    public TablePosition getTp() {
        return tp;
    }

    public void setTp(TablePosition tp) {
        this.tp = tp;
    }

    public JFXTextField getTxtPesquisaProduto() {
        return txtPesquisaProduto;
    }

    public void setTxtPesquisaProduto(JFXTextField txtPesquisaProduto) {
        this.txtPesquisaProduto = txtPesquisaProduto;
    }

    public TableView<SaidaProdutoProduto> getTvSaidaProdutoProduto() {
        return tvSaidaProdutoProduto;
    }

    public void setTvSaidaProdutoProduto(TableView<SaidaProdutoProduto> tvSaidaProdutoProduto) {
        this.tvSaidaProdutoProduto = tvSaidaProdutoProduto;
    }

    public ObservableList<SaidaProdutoProduto> getSaidaProdutoProdutoObservableList() {
        return saidaProdutoProdutoObservableList;
    }

    public void setSaidaProdutoProdutoObservableList(ObservableList<SaidaProdutoProduto> saidaProdutoProdutoObservableList) {
        this.saidaProdutoProdutoObservableList = saidaProdutoProdutoObservableList;
    }

    public ObservableList<EmpresaProdutoValor> getEmpresaProdutoValorObservableList() {
        return empresaProdutoValorObservableList;
    }

    public void setEmpresaProdutoValorObservableList(ObservableList<EmpresaProdutoValor> empresaProdutoValorObservableList) {
        this.empresaProdutoValorObservableList = empresaProdutoValorObservableList;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaId() {
        return colunaId;
    }

    public void setColunaId(TableColumn<SaidaProdutoProduto, String> colunaId) {
        this.colunaId = colunaId;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaProdutoId() {
        return colunaProdutoId;
    }

    public void setColunaProdutoId(TableColumn<SaidaProdutoProduto, String> colunaProdutoId) {
        this.colunaProdutoId = colunaProdutoId;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaProdutoCodigo() {
        return colunaProdutoCodigo;
    }

    public void setColunaProdutoCodigo(TableColumn<SaidaProdutoProduto, String> colunaProdutoCodigo) {
        this.colunaProdutoCodigo = colunaProdutoCodigo;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaProdutoDescricao() {
        return colunaProdutoDescricao;
    }

    public void setColunaProdutoDescricao(TableColumn<SaidaProdutoProduto, String> colunaProdutoDescricao) {
        this.colunaProdutoDescricao = colunaProdutoDescricao;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaProdutoLote() {
        return colunaProdutoLote;
    }

    public void setColunaProdutoLote(TableColumn<SaidaProdutoProduto, String> colunaProdutoLote) {
        this.colunaProdutoLote = colunaProdutoLote;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaProdutoValidade() {
        return colunaProdutoValidade;
    }

    public void setColunaProdutoValidade(TableColumn<SaidaProdutoProduto, String> colunaProdutoValidade) {
        this.colunaProdutoValidade = colunaProdutoValidade;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaQtd() {
        return colunaQtd;
    }

    public void setColunaQtd(TableColumn<SaidaProdutoProduto, String> colunaQtd) {
        this.colunaQtd = colunaQtd;
    }

//    public TableColumn<SaidaProdutoProduto, String> getColunaVlrDescontoUnitario() {
//        return colunaVlrDescontoUnitario;
//    }
//
//    public void setColunaVlrDescontoUnitario(TableColumn<SaidaProdutoProduto, String> colunaVlrDescontoUnitario) {
//        this.colunaVlrDescontoUnitario = colunaVlrDescontoUnitario;
//    }

    public TableColumn<SaidaProdutoProduto, String> getColunaVlrConsumidor() {
        return colunaVlrConsumidor;
    }

    public void setColunaVlrConsumidor(TableColumn<SaidaProdutoProduto, String> colunaVlrConsumidor) {
        this.colunaVlrConsumidor = colunaVlrConsumidor;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaVlrBruto() {
        return colunaVlrBruto;
    }

    public void setColunaVlrBruto(TableColumn<SaidaProdutoProduto, String> colunaVlrBruto) {
        this.colunaVlrBruto = colunaVlrBruto;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaVlrDescontoLiquido() {
        return colunaVlrDescontoLiquido;
    }

    public void setColunaVlrDescontoLiquido(TableColumn<SaidaProdutoProduto, String> colunaVlrDescontoLiquido) {
        this.colunaVlrDescontoLiquido = colunaVlrDescontoLiquido;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaVlrLiquido() {
        return colunaVlrLiquido;
    }

    public void setColunaVlrLiquido(TableColumn<SaidaProdutoProduto, String> colunaVlrLiquido) {
        this.colunaVlrLiquido = colunaVlrLiquido;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaEstoque() {
        return colunaEstoque;
    }

    public void setColunaEstoque(TableColumn<SaidaProdutoProduto, String> colunaEstoque) {
        this.colunaEstoque = colunaEstoque;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaVarejo() {
        return colunaVarejo;
    }

    public void setColunaVarejo(TableColumn<SaidaProdutoProduto, String> colunaVarejo) {
        this.colunaVarejo = colunaVarejo;
    }

    public TableColumn<SaidaProdutoProduto, String> getColunaVolume() {
        return colunaVolume;
    }

    public void setColunaVolume(TableColumn<SaidaProdutoProduto, String> colunaVolume) {
        this.colunaVolume = colunaVolume;
    }

    @SuppressWarnings("Duplicates")
    public void tabela() {
        try {

            Label lblId = new Label("id");
            lblId.setPrefWidth(48);
            colunaId = new TableColumn<SaidaProdutoProduto, String>();
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(lblId.getPrefWidth());
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> param.getValue().idProperty().asString());

            Label lblProdutoId = new Label("prod id");
            lblProdutoId.setPrefWidth(48);
            colunaProdutoId = new TableColumn<SaidaProdutoProduto, String>();
            colunaProdutoId.setGraphic(lblProdutoId);
            colunaProdutoId.setPrefWidth(lblProdutoId.getPrefWidth());
            colunaProdutoId.setStyle("-fx-alignment: center-right;");
            colunaProdutoId.setCellValueFactory(param -> param.getValue().codigoProperty());

            Label lblProdutoCodigo = new Label("Código");
            lblProdutoCodigo.setPrefWidth(60);
            colunaProdutoCodigo = new TableColumn<SaidaProdutoProduto, String>();
            colunaProdutoCodigo.setGraphic(lblProdutoCodigo);
            colunaProdutoCodigo.setPrefWidth(60);
            colunaProdutoCodigo.setStyle("-fx-alignment: center-right;");
            colunaProdutoCodigo.setCellValueFactory(param -> param.getValue().codigoProperty());

            Label lblProdutoDescricao = new Label("Descrição");
            lblProdutoDescricao.setPrefWidth(350);
            colunaProdutoDescricao = new TableColumn<SaidaProdutoProduto, String>();
            colunaProdutoDescricao.setGraphic(lblProdutoDescricao);
            colunaProdutoDescricao.setPrefWidth(lblProdutoDescricao.getPrefWidth());
            colunaProdutoDescricao.setCellValueFactory(param -> param.getValue().descricaoProperty());

            Label lblProdutoLote = new Label("Lote");
            lblProdutoLote.setPrefWidth(105);
            colunaProdutoLote = new TableColumn<SaidaProdutoProduto, String>();
            colunaProdutoLote.setGraphic(lblProdutoLote);
            colunaProdutoLote.setPrefWidth(lblProdutoLote.getPrefWidth());
            colunaProdutoLote.setCellValueFactory(param -> param.getValue().getProdutoEstoque().loteProperty());

            Label lblProdutoValidade = new Label("Validade");
            lblProdutoValidade.setPrefWidth(100);
            colunaProdutoValidade = new TableColumn<SaidaProdutoProduto, String>();
            colunaProdutoValidade.setPrefWidth(lblProdutoValidade.getPrefWidth());
            colunaProdutoValidade.setGraphic(lblProdutoValidade);
            colunaProdutoValidade.setStyle("-fx-alignment: center-right;");
            colunaProdutoValidade.setCellValueFactory(param ->
                    new SimpleStringProperty(param.getValue().getProdutoEstoque().validadeProperty().get().format(DTF_DATA)));

            Label lblQtd = new Label("Qtd");
            lblQtd.setPrefWidth(70);
            colunaQtd = new TableColumn<SaidaProdutoProduto, String>();
            colunaQtd.setGraphic(lblQtd);
            colunaQtd.setPrefWidth(lblQtd.getPrefWidth());
            colunaQtd.setStyle("-fx-alignment: center-right;");
            colunaQtd.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getQtd()), 0)));
            colunaQtd.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<SaidaProdutoProduto, String>(
                    ServiceMascara.getNumeroMask(12, 0)));
            colunaQtd.setOnEditCommit(event -> {
                if (Integer.parseInt(event.getNewValue()) > event.getRowValue().getProdutoEstoque().qtdProperty().get())
                    event.getRowValue().setQtd(event.getRowValue().getProdutoEstoque().qtdProperty().get());
                else
                    event.getRowValue().setQtd(Integer.parseInt(event.getNewValue()));
                getTvSaidaProdutoProduto().getSelectionModel().selectNext();
                calculaDescontosCliente();
                totalizaLinha(event.getRowValue());
                if (getClienteDescontos() != null)
                    calculaDescontosCliente();
            });

//            Label lblVlrDescontoUnitario = new Label("Desc R$[unt]");
//            lblVlrDescontoUnitario.setPrefWidth(90);
//            colunaVlrDescontoUnitario = new TableColumn<SaidaProdutoProduto, String>();
//            colunaVlrDescontoUnitario.setPrefWidth(lblVlrDescontoUnitario.getPrefWidth());
//            colunaVlrDescontoUnitario.setGraphic(lblVlrDescontoUnitario);
//            colunaVlrDescontoUnitario.setStyle("-fx-alignment: center-right;");
//            colunaVlrDescontoUnitario.setCellValueFactory(param -> new SimpleStringProperty(
//                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getVlrDescontoUnitario()), 2)));
//            colunaVlrDescontoUnitario.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<SaidaProdutoProduto, String>(
//                    ServiceMascara.getNumeroMask(12, 2)));
//            colunaVlrDescontoUnitario.setOnEditCommit(event -> {
//                event.getRowValue().setVlrDescontoUnitario(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
//                getTvSaidaProdutoProduto().getSelectionModel().selectNext();
//                totalizaLinha(event.getRowValue());
//            });


            Label lblVlrConsumidor = new Label("Preço Cons.");
            lblVlrConsumidor.setPrefWidth(90);
            colunaVlrConsumidor = new TableColumn<SaidaProdutoProduto, String>();
            colunaVlrConsumidor.setPrefWidth(lblVlrConsumidor.getPrefWidth());
            colunaVlrConsumidor.setGraphic(lblVlrConsumidor);
            colunaVlrConsumidor.setStyle("-fx-alignment: center-right;");
            colunaVlrConsumidor.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getVlrConsumidor()), 2)));
//            colunaVlrConsumidor.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<SaidaProdutoProduto, String>(
//                    ServiceMascara.getNumeroMask(12, 2)));
//            colunaVlrConsumidor.setOnEditCommit(event -> {
//                event.getRowValue().setVlrConsumidor(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
//                getTvSaidaProdutoProduto().getSelectionModel().selectNext();
//                totalizaLinha(event.getRowValue());
//            });

            Label lblVlrBruto = new Label("Tot. Bruto");
            lblVlrBruto.setPrefWidth(100);
            colunaVlrBruto = new TableColumn<SaidaProdutoProduto, String>();
            colunaVlrBruto.setPrefWidth(lblVlrBruto.getPrefWidth());
            colunaVlrBruto.setGraphic(lblVlrBruto);
            colunaVlrBruto.setStyle("-fx-alignment: center-right;");
            colunaVlrBruto.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(param.getValue().vlrBrutoProperty().get().toString(), 2)));

            Label lblVlrDescontoLiquido = new Label("vlr Desc R$ liq");
            lblVlrDescontoLiquido.setPrefWidth(90);
            colunaVlrDescontoLiquido = new TableColumn<SaidaProdutoProduto, String>();
            colunaVlrDescontoLiquido.setPrefWidth(lblVlrDescontoLiquido.getPrefWidth());
            colunaVlrDescontoLiquido.setGraphic(lblVlrDescontoLiquido);
            colunaVlrDescontoLiquido.setStyle("-fx-alignment: center-right;");
            colunaVlrDescontoLiquido.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getVlrDescontoLiquido()), 2)));
            colunaVlrDescontoLiquido.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<SaidaProdutoProduto, String>(
                    ServiceMascara.getNumeroMask(12, 2)));
            colunaVlrDescontoLiquido.setOnEditCommit(event -> {
                event.getRowValue().setVlrDescontoLiquido(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
                getTvSaidaProdutoProduto().getSelectionModel().selectNext();
                totalizaLinha(event.getRowValue());
            });

            Label lblVlrLiquido = new Label("vlr Liq R$");
            lblVlrLiquido.setPrefWidth(90);
            colunaVlrLiquido = new TableColumn<SaidaProdutoProduto, String>();
            colunaVlrLiquido.setPrefWidth(lblVlrLiquido.getPrefWidth());
            colunaVlrLiquido.setGraphic(lblVlrLiquido);
            colunaVlrLiquido.setStyle("-fx-alignment: center-right;");
            colunaVlrLiquido.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getVlrLiquido()), 2)));
//            colunaVlrLiquido.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EntradaProdutoProduto, String>(
//                    ServiceMascara.getNumeroMask(12, 2)));
//            colunaVlrLiquido.setOnEditCommit(event -> {
//                event.getRowValue().setVlrLiquido(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
//                ttvEntradaProdutoProduto.getSelectionModel().selectNext();
//            });

//            Label lblEstoque = new Label("Estoque");
//            lblEstoque.setPrefWidth(100);
//            colunaEstoque = new TableColumn<EntradaProdutoProduto, String>();
//            colunaEstoque.setGraphic(lblEstoque);
//            colunaEstoque.setPrefWidth(100);
//            colunaEstoque.setCellValueFactory(param -> param.getValue().estoqueProperty().asString());
//
//            Label lblVarejo = new Label("Varejo");
//            lblVarejo.setPrefWidth(00);
//            colunaVarejo = new TableColumn<EntradaProdutoProduto, String>();
//            colunaVarejo.setGraphic(lblVarejo);
//            colunaVarejo.setPrefWidth(50);
//            colunaVarejo.setStyle("-fx-alignment: center-right;");
//            colunaVarejo.setCellValueFactory(param -> param.getValue().varejoProperty().asString());

//            Label lblVolume = new Label("volume");
//            lblVolume.setPrefWidth(90);
//            colunaVolume = new TableColumn<EntradaProdutoProduto, String>();
//            colunaVolume.setPrefWidth(90);
//            colunaVolume.setGraphic(lblVolume);
//            colunaVolume.setStyle("-fx-alignment: center-right;");
//            colunaVolume.setCellValueFactory(param -> {
//                EntradaProdutoProduto data = param.getValue();
//                return Bindings.createObjectBinding(() -> {
//                            return BigInteger.valueOf(data.qtdProperty().get() * data.varejoProperty().get()).toString();
//                        }, data.qtdProperty(), data.varejoProperty()
//                );
//            });
//            colunaVolume.setCellValueFactory(param -> new SimpleStringProperty(
//                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getVolume()), 2)));
//            colunaVolume.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EntradaProdutoProduto, String>(
//                    ServiceMascara.getNumeroMask(12, 2)));
//            colunaVolume.setOnEditCommit(event -> {
//                event.getRowValue().setVolume(Integer.parseInt(event.getNewValue()));
//                totalizaLinha(event.getRowValue());
//                ttvEntradaProdutoProduto.getSelectionModel().selectNext();
//            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void escutaLista() {
        getTvSaidaProdutoProduto().getFocusModel().focusedCellProperty().addListener((ov, o, n) ->
                setTp(n)
        );

        getSaidaProdutoProdutoObservableList().addListener((ListChangeListener<? super SaidaProdutoProduto>) c -> {
            calculaDescontosCliente();
            getTvSaidaProdutoProduto().refresh();
            //totalizaTabela();
        });

        getTvSaidaProdutoProduto().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            if (getTvSaidaProdutoProduto().getEditingCell() == null && event.getCode() == KeyCode.ENTER) {
                getTvSaidaProdutoProduto().getSelectionModel().selectNext();
                setTp(getTvSaidaProdutoProduto().getFocusModel().getFocusedCell());
                event.consume();
            }
            if (event.getCode() == KeyCode.ENTER && getTp().getTableColumn() == getColunaVlrLiquido())
                txtPesquisaProduto.requestFocus();

            if (event.getCode() == KeyCode.DELETE) {
                if (tvSaidaProdutoProduto.getEditingCell() == null)
                    getSaidaProdutoProdutoObservableList().remove(getTvSaidaProdutoProduto().getSelectionModel().getSelectedItem());
            }
        });

        getTvSaidaProdutoProduto().setOnKeyPressed(event -> {
            if (!event.isControlDown() &&
                    (event.getCode().isLetterKey() || event.getCode().isDigitKey())) {
                ControllerPrincipal.setLastKey(event.getText());
                getTvSaidaProdutoProduto().edit(getTp().getRow(), getTp().getTableColumn());
            }
        });

        saidaProdutoProdutoObservableList.addListener((ListChangeListener<? super SaidaProdutoProduto>) c -> totalizaTabela());

//        getEmpresaProdutoValorObservableList().addListener((ListChangeListener<? super EmpresaProdutoValor>) c -> {
//            getSaidaProdutoProdutoObservableList().stream()
//                    .collect(Collectors.groupingBy(SaidaProdutoProduto::getDescricao, Collectors.counting()))
//                    .forEach(
//                            produto->
//                    );
//        });


//        ttvEntradaProdutoProduto.setOnKeyReleased((KeyEvent keyEvent) -> {
//            TablePosition tp;
//            switch (keyEvent.getCode()) {
////                case INSERT:
////                    items.add(new LineItem("",0d,0));//maybe try adding at position
////                    break;
//                case DELETE:
//                    tp = ttvEntradaProdutoProduto.getFocusModel().getFocusedCell();
//                    if (tp.getTableColumn() == colunaQtd) {
//                        deletedLines.push(items.remove(tp.getRow()));
//                    } else { //maybe delete cell value
//                    }
//                    break;
//                case Z:
//                    if (keyEvent.isControlDown()) {
//                        if (!deletedLines.isEmpty()) {
//                            items.add(deletedLines.pop());
//                        }
//                    }
//            }
//        });

    }

    private void calculaDescontosCliente() {
        if (getClienteDescontos() == null) return;
        final BigDecimal[] descTotal = {BigDecimal.ZERO};
        final BigDecimal[] descResidual = {BigDecimal.ZERO};
        final int[] diasPrazo = {0};
        boolean prazoUtil = clienteDescontos.diaUtilProperty().get();
        saidaProdutoProdutoObservableList.stream()
                .forEach(produto -> {
                    int qtdItens = (int) getSaidaProdutoProdutoObservableList().stream().filter(produto1 -> produto1.getProduto().getId() == produto.getProduto().getId()).count();
                    final BigDecimal[] vlrProd = {produto.vlrConsumidorProperty().get()};
                    produto.vlrDescontoLiquidoProperty().setValue(BigDecimal.ZERO);
                    produto.vlrConsumidorProperty().setValue(produto.getProduto().precoConsumidorProperty().get());
                    getClienteDescontos().getEmpresaProdutoValorList().stream()
                            .filter(empresaProdutoValor -> empresaProdutoValor.getProduto().getId() == produto.getProduto().getId())
                            .sorted(Comparator.comparing(EmpresaProdutoValor::getValidadeDesconto))
                            .forEach(empresaProdutoValor -> {
                                if (empresaProdutoValor.getValidadeDesconto().compareTo(LocalDate.now()) >= 0) {
                                    int qtdSaida = getSaidaProdutoProdutoObservableList().stream()
                                            .filter(produto1 -> produto1.getProduto().getId() == produto.getProduto().getId())
                                            .collect(Collectors.summingInt(SaidaProdutoProduto::getQtd));
                                    int fator = empresaProdutoValor.getQtdMinima() == 0
                                            ? qtdSaida : qtdSaida / empresaProdutoValor.getQtdMinima();
                                    if (empresaProdutoValor.valorProperty().get().compareTo(BigDecimal.ZERO) > 0) {
                                        vlrProd[0] = empresaProdutoValor.valorProperty().get();
                                        produto.setVlrConsumidor(empresaProdutoValor.valorProperty().get());
                                    }
                                    if (descTotal[0].compareTo(BigDecimal.ZERO) == 0 || descResidual[0].compareTo(BigDecimal.ZERO) == 0) {
                                        descResidual[0] = BigDecimal.ZERO;
                                        descTotal[0] = BigDecimal.ZERO;
                                        if (empresaProdutoValor.qtdMinimaProperty().get() <= qtdSaida) {
                                            if (empresaProdutoValor.bonificacaoProperty().get() == 0) {
                                                descTotal[0] = empresaProdutoValor.descontoProperty().get()
                                                        .multiply(new BigDecimal(String.valueOf(fator)));
                                            } else {
                                                descTotal[0] = vlrProd[0]
                                                        .multiply(new BigDecimal(String.valueOf(empresaProdutoValor.bonificacaoProperty().get())))
                                                        .multiply(new BigDecimal(String.valueOf(fator)));
                                            }
                                            descResidual[0] = descTotal[0];

                                            if (empresaProdutoValor.diasProperty().get() > 0)
                                                if (diasPrazo[0] < empresaProdutoValor.diasProperty().get())
                                                    diasPrazo[0] = empresaProdutoValor.diasProperty().get();
                                        }
                                    }
                                    BigDecimal descParcial = BigDecimal.ZERO;
                                    if (qtdItens == 1) {
                                        descParcial = descTotal[0];
                                    } else {
                                        BigDecimal proporcao = new BigDecimal(produto.qtdProperty().get())
                                                .multiply(new BigDecimal("100"))
                                                .divide(new BigDecimal(String.valueOf(qtdSaida)), 2, RoundingMode.HALF_UP);
                                        descParcial = proporcao.divide(new BigDecimal("100")).multiply(descTotal[0]);
                                        if (descParcial.compareTo(descResidual[0]) >= 0)
                                            descParcial = descResidual[0];
                                    }
                                    produto.vlrDescontoLiquidoProperty().setValue(descParcial.setScale(2, RoundingMode.HALF_UP));

                                    descResidual[0] = descResidual[0].subtract(produto.vlrDescontoLiquidoProperty().get());


                                }
                            });
                    totalizaLinha(produto);
                });
        if (diasPrazo[0] == 0)
            diasPrazo[0] = clienteDescontos.prazoProperty().get();
        setVencimento(ServiceDataHora.getDataVencimento(
                null,
                diasPrazo[0],
                clienteDescontos.diaUtilProperty().get()));
        setPrazo(String.format("%d%s",
                diasPrazo[0],
                diasPrazo[0] > 1 ?
                        String.format(" dias%s", clienteDescontos.diaUtilProperty().get() ? " uteis" : "") :
                        String.format(" dia%s", clienteDescontos.diaUtilProperty().get() ? " util" : "")));


    }

    public void preencherTabela() {
    /*
    colunaId;
    colunaCodigo;
    colunaDescricao;
    colunaLote;
    colunaValidade;
    colunaQtd;
    colunaVlrFabrica;
    colunaVlrBruto;
    colunaVlrDescontoLiquido;
    colunaVlrImposto;
    colunaVlrLiquido;
    colunaEstoque;
    colunaVarejo;
    colunaVolume;
     */

        getTvSaidaProdutoProduto().getColumns().setAll(getColunaProdutoCodigo(), getColunaProdutoDescricao(),
                getColunaProdutoLote(), getColunaProdutoValidade(), getColunaQtd(), getColunaVlrConsumidor(),
                getColunaVlrBruto(), getColunaVlrDescontoLiquido(), getColunaVlrLiquido()
        );
        getTvSaidaProdutoProduto().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getTvSaidaProdutoProduto().getSelectionModel().setCellSelectionEnabled(true);
        getTvSaidaProdutoProduto().setEditable(true);
        getTvSaidaProdutoProduto().setItems(saidaProdutoProdutoObservableList);

    }

    @SuppressWarnings("Duplicates")
    private void totalizaTabela() {
        setNumItens(getSaidaProdutoProdutoObservableList().stream()
                .collect(Collectors.groupingBy(SaidaProdutoProduto::getDescricao, Collectors.counting()))
                .size());
        setQtdItens(getSaidaProdutoProdutoObservableList().stream().collect(Collectors.summingInt(SaidaProdutoProduto::getQtd)));
        setQtdVolume(getSaidaProdutoProdutoObservableList().stream().collect(Collectors.summingInt(SaidaProdutoProduto::getVolume)));

        BigDecimal totBruto = getSaidaProdutoProdutoObservableList().stream().map(SaidaProdutoProduto::getVlrBruto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalBruto(totBruto.setScale(2));

        BigDecimal totDesconto = getSaidaProdutoProdutoObservableList().stream().map(SaidaProdutoProduto::getVlrDescontoLiquido)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalDesconto(totDesconto.setScale(2, RoundingMode.HALF_UP));

        BigDecimal totLiquido = totBruto.subtract(totDesconto);
        setTotalLiquido(totLiquido.setScale(2));

//        BigDecimal totBruto = BigDecimal.ZERO;
//        BigDecimal totImpostoItens = BigDecimal.ZERO;
//        BigDecimal totDesconto = BigDecimal.ZERO;
//        BigDecimal finalTotBruto = totBruto;
//        BigDecimal finalTotImpostoItens = totImpostoItens;
//        BigDecimal finalTotDesconto = totDesconto;
//        getEntradaProdutoProdutoObservableList().stream()
//                .forEach(entradaProdutoProduto -> {
//                    finalTotBruto.add(entradaProdutoProduto.getVlrBruto() == null ? BigDecimal.ZERO : entradaProdutoProduto.getVlrBruto());
//                    finalTotImpostoItens.add(entradaProdutoProduto.getVlrImposto() == null ? BigDecimal.ZERO : entradaProdutoProduto.getVlrImposto());
//                    finalTotDesconto.add(entradaProdutoProduto.getVlrDesconto() == null ? BigDecimal.ZERO : entradaProdutoProduto.getVlrDesconto());
//                });
//
//        setTotalBruto(finalTotBruto.setScale(2));
//
//        BigDecimal totImpostoEntrada = getTotalImpostoEntrada();
//        BigDecimal totImposto = totImpostoEntrada.add(finalTotImpostoItens);
//
//        setTotalImposto(totImposto.setScale(2));
//
//        BigDecimal totFrete = getTotalFrete();
//
//        setTotalDesconto(finalTotDesconto.setScale(2));
//
//        BigDecimal totLiquido = finalTotBruto.add(totImposto).add(totFrete).subtract(finalTotDesconto);
//
//        setTotalLiquido(totLiquido.setScale(2));
    }

    @SuppressWarnings("Duplicates")
    private void totalizaLinha(SaidaProdutoProduto saidaProdutoProduto) {
        int qtd = saidaProdutoProduto.qtdProperty().get();
        int varejo = saidaProdutoProduto.getProduto().varejoProperty().get();
        int div = qtd / varejo;
        double resto = qtd % varejo;
        int volume = (resto == 0) ? div : div + 1;
        BigDecimal bruto = saidaProdutoProduto.vlrConsumidorProperty().get();
        BigDecimal vlrBruto = bruto.multiply(BigDecimal.valueOf(qtd));

        BigDecimal desconto = saidaProdutoProduto.vlrDescontoLiquidoProperty().get();

        BigDecimal vlrLiquido = vlrBruto.subtract(desconto);
        saidaProdutoProduto.setVlrBruto(vlrBruto);
        saidaProdutoProduto.setVlrLiquido(vlrLiquido);
        saidaProdutoProduto.setVolume(volume);

        getTvSaidaProdutoProduto().refresh();

        totalizaTabela();

    }


}
