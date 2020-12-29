package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.controller.ControllerEntradaProduto;
import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import br.com.tlmacedo.cafeperfeito.model.vo.EntradaProdutoProduto;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.SetCellFactoryTableCell_DatePicker;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.SetCellFactoryTableCell_EditingCell;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-18
 * Time: 19:30
 */

public class TabModelEntradaProdutoProduto {

    private TablePosition tp;
    private JFXTextField txtPesquisaProduto;
    private TableView<EntradaProdutoProduto> ttvItensNfe;
    private ObservableList<EntradaProdutoProduto> entradaProdutoProdutoObservableList;
    private EntradaProdutoProduto entradaProdutoProduto;
    private ControllerEntradaProduto controllerEntradaProduto;

    private TableColumn<EntradaProdutoProduto, Long> colunaId;
    private TableColumn<EntradaProdutoProduto, String> colunaCodigo;
    private TableColumn<EntradaProdutoProduto, String> colunaDescricao;
    private TableColumn<EntradaProdutoProduto, String> colunaLote;
    private TableColumn<EntradaProdutoProduto, LocalDate> colunaValidade;
    private TableColumn<EntradaProdutoProduto, String> colunaQtd;
    private TableColumn<EntradaProdutoProduto, String> colunaVlrFabrica;
    private TableColumn<EntradaProdutoProduto, String> colunaVlrBruto;
    private TableColumn<EntradaProdutoProduto, String> colunaVlrDesconto;
    private TableColumn<EntradaProdutoProduto, String> colunaVlrImposto;
    private TableColumn<EntradaProdutoProduto, String> colunaVlrLiquido;

    private TableColumn<EntradaProdutoProduto, String> colunaEstoque;
    private TableColumn<EntradaProdutoProduto, String> colunaVarejo;
    private TableColumn<EntradaProdutoProduto, String> colunaVolume;

    public TabModelEntradaProdutoProduto(ControllerEntradaProduto controllerEntradaProduto) {
        setControllerEntradaProduto(controllerEntradaProduto);
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

    public TableView<EntradaProdutoProduto> getTtvItensNfe() {
        return ttvItensNfe;
    }

    public void setTtvItensNfe(TableView<EntradaProdutoProduto> ttvItensNfe) {
        this.ttvItensNfe = ttvItensNfe;
    }

    public ObservableList<EntradaProdutoProduto> getEntradaProdutoProdutoObservableList() {
        return entradaProdutoProdutoObservableList;
    }

    public void setEntradaProdutoProdutoObservableList(ObservableList<EntradaProdutoProduto> entradaProdutoProdutoObservableList) {
        this.entradaProdutoProdutoObservableList = entradaProdutoProdutoObservableList;
    }

    public EntradaProdutoProduto getEntradaProdutoProduto() {
        return entradaProdutoProduto;
    }

    public void setEntradaProdutoProduto(EntradaProdutoProduto entradaProdutoProduto) {
        this.entradaProdutoProduto = entradaProdutoProduto;
    }

    public ControllerEntradaProduto getControllerEntradaProduto() {
        return controllerEntradaProduto;
    }

    public void setControllerEntradaProduto(ControllerEntradaProduto controllerEntradaProduto) {
        this.controllerEntradaProduto = controllerEntradaProduto;
    }

    public TableColumn<EntradaProdutoProduto, Long> getColunaId() {
        return colunaId;
    }

    public void setColunaId(TableColumn<EntradaProdutoProduto, Long> colunaId) {
        this.colunaId = colunaId;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaCodigo() {
        return colunaCodigo;
    }

    public void setColunaCodigo(TableColumn<EntradaProdutoProduto, String> colunaCodigo) {
        this.colunaCodigo = colunaCodigo;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaDescricao() {
        return colunaDescricao;
    }

    public void setColunaDescricao(TableColumn<EntradaProdutoProduto, String> colunaDescricao) {
        this.colunaDescricao = colunaDescricao;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaLote() {
        return colunaLote;
    }

    public void setColunaLote(TableColumn<EntradaProdutoProduto, String> colunaLote) {
        this.colunaLote = colunaLote;
    }

    public TableColumn<EntradaProdutoProduto, LocalDate> getColunaValidade() {
        return colunaValidade;
    }

    public void setColunaValidade(TableColumn<EntradaProdutoProduto, LocalDate> colunaValidade) {
        this.colunaValidade = colunaValidade;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaQtd() {
        return colunaQtd;
    }

    public void setColunaQtd(TableColumn<EntradaProdutoProduto, String> colunaQtd) {
        this.colunaQtd = colunaQtd;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaVlrFabrica() {
        return colunaVlrFabrica;
    }

    public void setColunaVlrFabrica(TableColumn<EntradaProdutoProduto, String> colunaVlrFabrica) {
        this.colunaVlrFabrica = colunaVlrFabrica;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaVlrBruto() {
        return colunaVlrBruto;
    }

    public void setColunaVlrBruto(TableColumn<EntradaProdutoProduto, String> colunaVlrBruto) {
        this.colunaVlrBruto = colunaVlrBruto;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaVlrDesconto() {
        return colunaVlrDesconto;
    }

    public void setColunaVlrDesconto(TableColumn<EntradaProdutoProduto, String> colunaVlrDesconto) {
        this.colunaVlrDesconto = colunaVlrDesconto;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaVlrImposto() {
        return colunaVlrImposto;
    }

    public void setColunaVlrImposto(TableColumn<EntradaProdutoProduto, String> colunaVlrImposto) {
        this.colunaVlrImposto = colunaVlrImposto;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaVlrLiquido() {
        return colunaVlrLiquido;
    }

    public void setColunaVlrLiquido(TableColumn<EntradaProdutoProduto, String> colunaVlrLiquido) {
        this.colunaVlrLiquido = colunaVlrLiquido;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaEstoque() {
        return colunaEstoque;
    }

    public void setColunaEstoque(TableColumn<EntradaProdutoProduto, String> colunaEstoque) {
        this.colunaEstoque = colunaEstoque;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaVarejo() {
        return colunaVarejo;
    }

    public void setColunaVarejo(TableColumn<EntradaProdutoProduto, String> colunaVarejo) {
        this.colunaVarejo = colunaVarejo;
    }

    public TableColumn<EntradaProdutoProduto, String> getColunaVolume() {
        return colunaVolume;
    }

    public void setColunaVolume(TableColumn<EntradaProdutoProduto, String> colunaVolume) {
        this.colunaVolume = colunaVolume;
    }

    @SuppressWarnings("Duplicates")
    public void tabela() {
        try {

            Label lblId = new Label("id");
            lblId.setPrefWidth(48);
            setColunaId(new TableColumn<EntradaProdutoProduto, Long>());
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(48);
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> param.getValue().idProperty().asObject());

            Label lblCodigo = new Label("Código");
            lblCodigo.setPrefWidth(60);
            colunaCodigo = new TableColumn<EntradaProdutoProduto, String>();
            colunaCodigo.setGraphic(lblCodigo);
            colunaCodigo.setPrefWidth(60);
            colunaCodigo.setStyle("-fx-alignment: center-right;");
            colunaCodigo.setCellValueFactory(param -> param.getValue().codigoProperty());

            Label lblDescricao = new Label("Descrição");
            lblDescricao.setPrefWidth(350);
            colunaDescricao = new TableColumn<EntradaProdutoProduto, String>();
            colunaDescricao.setGraphic(lblDescricao);
            colunaDescricao.setPrefWidth(350);
            colunaDescricao.setCellValueFactory(param -> param.getValue().descricaoProperty());

            Label lblLote = new Label("Lote");
            lblLote.setPrefWidth(105);
            colunaLote = new TableColumn<EntradaProdutoProduto, String>();
            colunaLote.setGraphic(lblLote);
            colunaLote.setPrefWidth(105);
            colunaLote.setCellValueFactory(param -> param.getValue().loteProperty());
            colunaLote.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EntradaProdutoProduto, String>(
                    ServiceMascara.getTextoMask(15, "*")));
            colunaLote.setOnEditCommit(event -> {
                event.getRowValue().setLote(event.getNewValue());
                getTtvItensNfe().getSelectionModel().selectNext();
            });

            Label lblValidade = new Label("Validade");
            lblValidade.setPrefWidth(100);
            colunaValidade = new TableColumn<EntradaProdutoProduto, LocalDate>();
            colunaValidade.setPrefWidth(lblValidade.getPrefWidth());
            colunaValidade.setGraphic(lblValidade);
            colunaValidade.setStyle("-fx-alignment: center-right;");
            colunaValidade.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().validadeProperty().get()));
            colunaValidade.setCellFactory(param -> new SetCellFactoryTableCell_DatePicker<>());
//            colunaValidade.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EntradaProdutoProduto, String>(
//                    "##/##/####"));
            colunaValidade.setOnEditCommit(event -> {
                event.getRowValue().setValidade(event.getNewValue());
                getTtvItensNfe().getSelectionModel().selectNext();
            });

            Label lblQtd = new Label("Qtd");
            lblQtd.setPrefWidth(70);
            colunaQtd = new TableColumn<EntradaProdutoProduto, String>();
            colunaQtd.setGraphic(lblQtd);
            colunaQtd.setPrefWidth(lblQtd.getPrefWidth());
            colunaQtd.setStyle("-fx-alignment: center-right;");
            colunaQtd.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().qtdProperty()), 0)));
            colunaQtd.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EntradaProdutoProduto, String>(
                    ServiceMascara.getNumeroMask(12, 0)));
            colunaQtd.setOnEditCommit(event -> {
                event.getRowValue().setQtd(Integer.parseInt(event.getNewValue()));
                getTtvItensNfe().getSelectionModel().selectNext();
                totalizaLinha();
            });

            Label lblVlrFabrica = new Label("Preço Fab.");
            lblVlrFabrica.setPrefWidth(90);
            colunaVlrFabrica = new TableColumn<EntradaProdutoProduto, String>();
            colunaVlrFabrica.setPrefWidth(90);
            colunaVlrFabrica.setGraphic(lblVlrFabrica);
            colunaVlrFabrica.setStyle("-fx-alignment: center-right;");
            colunaVlrFabrica.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getVlrFabrica()), 2)));
            colunaVlrFabrica.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EntradaProdutoProduto, String>(
                    ServiceMascara.getNumeroMask(12, 2)));
            colunaVlrFabrica.setOnEditCommit(event -> {
                event.getRowValue().setVlrFabrica(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
                getTtvItensNfe().getSelectionModel().selectNext();
                totalizaLinha();
            });

            Label lblVlrBruto = new Label("Tot. Bruto");
            lblVlrBruto.setPrefWidth(100);
            colunaVlrBruto = new TableColumn<EntradaProdutoProduto, String>();
            colunaVlrBruto.setPrefWidth(90);
            colunaVlrBruto.setGraphic(lblVlrBruto);
            colunaVlrBruto.setStyle("-fx-alignment: center-right;");
            colunaVlrBruto.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(param.getValue().vlrBrutoProperty().get().toString(), 2)));
//            colunaVlrBruto.setCellValueFactory(param -> {
//                EntradaProdutoProduto data = param.getValue();
//                return Bindings.createObjectBinding(() -> {
//                            return ServiceMascara.getMoeda(new BigDecimal(String.valueOf(data.getVlrFabrica().multiply(BigDecimal.valueOf(data.getQtd())))).setScale(2).toString(), 2);
//                        }, data.vlrFabricaProperty(), data.qtdProperty()
//                );
//            });

            Label lblVlrDesconto = new Label("vlr Desc R$");
            lblVlrDesconto.setPrefWidth(90);
            colunaVlrDesconto = new TableColumn<EntradaProdutoProduto, String>();
            colunaVlrDesconto.setPrefWidth(90);
            colunaVlrDesconto.setGraphic(lblVlrDesconto);
            colunaVlrDesconto.setStyle("-fx-alignment: center-right;");
            colunaVlrDesconto.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getVlrDesconto()), 2)));
            colunaVlrDesconto.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EntradaProdutoProduto, String>(
                    ServiceMascara.getNumeroMask(12, 2)));
            colunaVlrDesconto.setOnEditCommit(event -> {
                event.getRowValue().setVlrDesconto(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
                getTtvItensNfe().getSelectionModel().selectNext();
                totalizaLinha();
            });

            Label lblVlrImposto = new Label("Imposto R$");
            lblVlrImposto.setPrefWidth(90);
            colunaVlrImposto = new TableColumn<EntradaProdutoProduto, String>();
            colunaVlrImposto.setPrefWidth(90);
            colunaVlrImposto.setGraphic(lblVlrImposto);
            colunaVlrImposto.setStyle("-fx-alignment: center-right;");
            colunaVlrImposto.setCellValueFactory(param -> new SimpleStringProperty(
                    ServiceMascara.getMoeda(String.valueOf(param.getValue().getVlrImposto()), 2)));
            colunaVlrImposto.setCellFactory(param -> new SetCellFactoryTableCell_EditingCell<EntradaProdutoProduto, String>(
                    ServiceMascara.getNumeroMask(12, 2)));
            colunaVlrImposto.setOnEditCommit(event -> {
                event.getRowValue().setVlrImposto(ServiceMascara.getBigDecimalFromTextField(event.getNewValue(), 2));
                getTtvItensNfe().getSelectionModel().selectNext();
                totalizaLinha();
            });

            Label lblVlrLiquido = new Label("vlr Liq R$");
            lblVlrLiquido.setPrefWidth(90);
            colunaVlrLiquido = new TableColumn<EntradaProdutoProduto, String>();
            colunaVlrLiquido.setPrefWidth(90);
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

            Label lblEstoque = new Label("Estoque");
            lblEstoque.setPrefWidth(100);
            colunaEstoque = new TableColumn<EntradaProdutoProduto, String>();
            colunaEstoque.setGraphic(lblEstoque);
            colunaEstoque.setPrefWidth(100);
            colunaEstoque.setCellValueFactory(param -> param.getValue().estoqueProperty().asString());

            Label lblVarejo = new Label("Varejo");
            lblVarejo.setPrefWidth(00);
            colunaVarejo = new TableColumn<EntradaProdutoProduto, String>();
            colunaVarejo.setGraphic(lblVarejo);
            colunaVarejo.setPrefWidth(50);
            colunaVarejo.setStyle("-fx-alignment: center-right;");
            colunaVarejo.setCellValueFactory(param -> param.getValue().varejoProperty().asString());

            Label lblVolume = new Label("volume");
            lblVolume.setPrefWidth(90);
            colunaVolume = new TableColumn<EntradaProdutoProduto, String>();
            colunaVolume.setPrefWidth(90);
            colunaVolume.setGraphic(lblVolume);
            colunaVolume.setStyle("-fx-alignment: center-right;");
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

    public void escutaLista() {

        getEntradaProdutoProdutoObservableList().addListener((ListChangeListener<? super EntradaProdutoProduto>) c -> {
            getTtvItensNfe().refresh();
        });

        getTtvItensNfe().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            setEntradaProdutoProduto(n);
        });

        getTtvItensNfe().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (getTtvItensNfe().getEditingCell() == null && event.getCode() == KeyCode.ENTER) {
                getTtvItensNfe().getSelectionModel().selectNext();
                setTp(getTtvItensNfe().getFocusModel().getFocusedCell());
                event.consume();
            }
            if (event.getCode() == KeyCode.ENTER && getTp().getTableColumn() == getColunaVlrLiquido())
                getTxtPesquisaProduto().requestFocus();

            if (event.getCode() == KeyCode.DELETE) {
                if (getTtvItensNfe().getEditingCell() == null)
                    getEntradaProdutoProdutoObservableList().remove(getTtvItensNfe().getSelectionModel().getSelectedItem());
            }
        });

        getTtvItensNfe().setOnKeyPressed(event -> {
            setTp(getTtvItensNfe().getFocusModel().getFocusedCell());
            if (!event.isControlDown() &&
                    (event.getCode().isLetterKey() || event.getCode().isDigitKey())) {
                ControllerPrincipal.setLastKey(event.getText());
                getTtvItensNfe().edit(getTp().getRow(), getTp().getTableColumn());
            }
        });
    }

    public void preencherTabela() {
        getTtvItensNfe().getColumns().setAll(getColunaCodigo(), getColunaDescricao(), getColunaLote(),
                getColunaValidade(), getColunaQtd(), getColunaVlrFabrica(), getColunaVlrBruto(), getColunaVlrDesconto(),
                getColunaVlrImposto(), getColunaVlrLiquido()
        );
        getTtvItensNfe().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getTtvItensNfe().getSelectionModel().setCellSelectionEnabled(true);
        getTtvItensNfe().setEditable(true);
        getTtvItensNfe().setItems(getEntradaProdutoProdutoObservableList());

    }

//    private void totalizaTabela() {
//        setNumItens(getEntradaProdutoProdutoObservableList()
//                .stream().collect(Collectors.groupingBy(EntradaProdutoProduto::getDescricao, Collectors.counting()))
//                .size());
//        setQtdItens(getEntradaProdutoProdutoObservableList().stream().collect(Collectors.summingInt(EntradaProdutoProduto::getQtd)));
//        setQtdVolume(getEntradaProdutoProdutoObservableList().stream().collect(Collectors.summingInt(EntradaProdutoProduto::getVolume)));
//
//        BigDecimal totBruto = getEntradaProdutoProdutoObservableList().stream().map(EntradaProdutoProduto::getVlrBruto)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        setTotalBruto(totBruto.setScale(2));
//
//        BigDecimal totImpostoEntrada = getTotalImpostoEntrada();
//        BigDecimal totImpostoItens = getEntradaProdutoProdutoObservableList().stream().map(EntradaProdutoProduto::getVlrImposto)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        BigDecimal totImposto = totImpostoEntrada.add(totImpostoItens);
//
//        setTotalImposto(totImposto.setScale(2));
//
//        BigDecimal totFrete = getTotalFrete();
//
//        BigDecimal totDesconto = getEntradaProdutoProdutoObservableList().stream().map(EntradaProdutoProduto::getVlrDesconto)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        setTotalDesconto(totDesconto.setScale(2));
//
//        BigDecimal totLiquido = totBruto.add(totImposto).add(totFrete).subtract(totDesconto);
//        setTotalLiquido(totLiquido.setScale(2));
//    }

    private void totalizaLinha() {
        if (getEntradaProdutoProduto() == null) return;
        int qtd = getEntradaProdutoProduto().qtdProperty().get();
        int varejo = getEntradaProdutoProduto().varejoProperty().get();
        int div = qtd / varejo;
        double resto = qtd % varejo;
        int volume = (resto == 0) ? div : div + 1;
        BigDecimal bruto = getEntradaProdutoProduto().vlrFabricaProperty().get();
        BigDecimal vlrBruto = bruto.multiply(BigDecimal.valueOf(qtd));

        BigDecimal desconto = getEntradaProdutoProduto().vlrDescontoProperty().get();
        BigDecimal imposto = getEntradaProdutoProduto().vlrImpostoProperty().get();

        BigDecimal vlrLiquido = vlrBruto.subtract(desconto).add(imposto);
        getEntradaProdutoProduto().setVlrBruto(vlrBruto);
        getEntradaProdutoProduto().setVlrLiquido(vlrLiquido);
        getEntradaProdutoProduto().setVolume(volume);

        getTtvItensNfe().refresh();
        getControllerEntradaProduto().totalizaTabela();
    }

}
