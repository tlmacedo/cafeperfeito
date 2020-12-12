package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import br.com.tlmacedo.cafeperfeito.model.enums.TipoCodigoCFOP;
import br.com.tlmacedo.cafeperfeito.model.vo.EntradaProdutoProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.FichaKardex;
import br.com.tlmacedo.cafeperfeito.model.vo.ProdutoEstoque;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import br.com.tlmacedo.cafeperfeito.service.format.cell.SetCellFactoryTableCell_ComboBox;
import br.com.tlmacedo.cafeperfeito.service.format.cell.SetCellFactoryTableCell_EdtitingCell;
import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;

public class TmodelEntradaProduto {

    private TablePosition tp;
    private TableView<EntradaProdutoProduto> tvItensNfe;
    private ObservableList<EntradaProdutoProduto> entradaProdutoProdutoObservableList;
    private TextField txtPesquisaProduto;
    private List<FichaKardex> fichaKardexList;

    private TableColumn<EntradaProdutoProduto, String> colId;
    private TableColumn<EntradaProdutoProduto, String> colProdId;
    private TableColumn<EntradaProdutoProduto, String> colProdCod;
    private TableColumn<EntradaProdutoProduto, String> colProdDescricao;
    private TableColumn<EntradaProdutoProduto, TipoCodigoCFOP> colCFOP;
    private TableColumn<EntradaProdutoProduto, String> colProdLote;
    private TableColumn<EntradaProdutoProduto, String> colProdValidade;
    private TableColumn<EntradaProdutoProduto, String> colQtd;
    private TableColumn<EntradaProdutoProduto, String> colVlrUnitario;
    private TableColumn<EntradaProdutoProduto, String> colVlrBruto;
    private TableColumn<EntradaProdutoProduto, String> colVlrDesconto;
    private TableColumn<EntradaProdutoProduto, String> colVlrImposto;
    private TableColumn<EntradaProdutoProduto, String> colVlrLiquido;

    private TableColumn<EntradaProdutoProduto, Integer> colEstoque;
    private TableColumn<EntradaProdutoProduto, Integer> colVarejo;
    private TableColumn<EntradaProdutoProduto, Integer> colVolume;

    private IntegerProperty totalQtdItem = new SimpleIntegerProperty(0);
    private IntegerProperty totalQtdProduto = new SimpleIntegerProperty(0);
    private IntegerProperty totalQtdVolume = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalDesconto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalFrete = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalImpEntrada = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalImposto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO);


    public TmodelEntradaProduto() {
    }

    /**
     * Begin voids
     */

    public void criaTabela() {
        setColId(new TableColumn<>("id"));
        getColId().setPrefWidth(48);
        getColId().setStyle("-fx-alignment: center-right;");
        getColId().setCellValueFactory(param -> param.getValue().idProperty().asString());

        setColProdId(new TableColumn<>("idP"));
        getColProdId().setPrefWidth(48);
        getColProdId().setStyle("-fx-alignment: center-right;");
        getColProdId().setCellValueFactory(param -> param.getValue().produtoProperty().getValue().idProperty().asString());

        setColProdCod(new TableColumn<>("cód"));
        getColProdCod().setPrefWidth(60);
        getColProdCod().setStyle("-fx-alignment: center-right;");
        getColProdCod().setCellValueFactory(param -> param.getValue().codigoProperty());

        setColProdDescricao(new TableColumn<>("descrição"));
        getColProdDescricao().setPrefWidth(450);
        getColProdDescricao().setCellValueFactory(param -> param.getValue().descricaoProperty());

        setColCFOP(new TableColumn<>("CFOP"));
        getColCFOP().setPrefWidth(100);
        getColCFOP().setCellValueFactory(param ->
                new SimpleObjectProperty<>(param.getValue().getCodigoCFOP()));
        getColCFOP().setCellFactory(param -> new SetCellFactoryTableCell_ComboBox<>(TipoCodigoCFOP.getList()));
        getColCFOP().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().setCodigoCFOP(editEvent.getNewValue());
            getTvItensNfe().getSelectionModel().selectNext();
            totalizaLinha(editEvent.getRowValue());
        });

        setColProdLote(new TableColumn<>("lote"));
        getColProdLote().setPrefWidth(105);
        getColProdLote().setStyle("-fx-alignment: center;");
        getColProdLote().setCellValueFactory(param -> param.getValue().loteProperty());
        getColProdLote().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<EntradaProdutoProduto, String>(
                ServiceMascara.getTextoMask(15, "*")));
        getColProdLote().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().loteProperty().setValue(editEvent.getNewValue());
            getTvItensNfe().getSelectionModel().selectNext();
        });

        setColProdValidade(new TableColumn<>("validade"));
        getColProdValidade().setPrefWidth(90);
        getColProdValidade().setStyle("-fx-alignment: center-right;");
        getColProdValidade().setCellValueFactory(param -> new SimpleStringProperty(param.getValue().dtValidadeProperty().get().format(DTF_DATA)));
        getColProdValidade().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<EntradaProdutoProduto, String>(
                "##/##/####"));
        getColProdValidade().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().dtValidadeProperty().setValue(LocalDate.parse(editEvent.getNewValue(), DTF_DATA));
            getTvItensNfe().getSelectionModel().selectNext();
        });

        setColQtd(new TableColumn<>("qtd"));
        getColQtd().setPrefWidth(70);
        getColQtd().setStyle("-fx-alignment: center-right;");
        getColQtd().setCellValueFactory(param -> new SimpleStringProperty(
                ServiceMascara.getMoeda(param.getValue().qtdProperty().toString(), 0)
        ));
        getColQtd().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<EntradaProdutoProduto, String>(
                ServiceMascara.getNumeroMask(12, 0)
        ));
        getColQtd().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().setQtd(Integer.parseInt(editEvent.getNewValue()));
            getTvItensNfe().getSelectionModel().selectNext();
            totalizaLinha(editEvent.getRowValue());
        });

        setColVlrUnitario(new TableColumn<>("vlr. unit"));
        getColVlrUnitario().setPrefWidth(90);
        getColVlrUnitario().setStyle("-fx-alignment: center-right;");
        getColVlrUnitario().setCellValueFactory(param -> new SimpleStringProperty(
                ServiceMascara.getMoeda(param.getValue().vlrUnitarioProperty().get(), 2)
        ));
        getColVlrUnitario().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<EntradaProdutoProduto, String>(
                ServiceMascara.getNumeroMask(12, 2)
        ));
        getColVlrUnitario().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().setVlrUnitario(ServiceMascara.getBigDecimalFromTextField(editEvent.getNewValue(), 2));
            getTvItensNfe().getSelectionModel().selectNext();
            totalizaLinha(editEvent.getRowValue());
        });

        setColVlrBruto(new TableColumn<>("vlr bruto"));
        getColVlrBruto().setPrefWidth(90);
        getColVlrBruto().setStyle("-fx-alignment: center-right;");
        getColVlrBruto().setCellValueFactory(param ->
                new SimpleStringProperty(
                        ServiceMascara.getMoeda(param.getValue().vlrBrutoProperty().get(), 2)
                ));

        setColVlrDesconto(new TableColumn<>("desc R$"));
        getColVlrDesconto().setPrefWidth(90);
        getColVlrDesconto().setStyle("-fx-alignment: center-right;");
        getColVlrDesconto().setCellValueFactory(param -> new SimpleStringProperty(
                ServiceMascara.getMoeda(param.getValue().vlrDescontoProperty().get(), 2)
        ));
        getColVlrDesconto().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<EntradaProdutoProduto, String>(
                ServiceMascara.getNumeroMask(12, 2)
        ));
        getColVlrDesconto().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().setVlrDesconto(ServiceMascara.getBigDecimalFromTextField(editEvent.getNewValue(), 2));
            getTvItensNfe().getSelectionModel().selectNext();
            totalizaLinha(editEvent.getRowValue());
        });

        setColVlrImposto(new TableColumn<>("imp R$"));
        getColVlrImposto().setPrefWidth(90);
        getColVlrImposto().setStyle("-fx-alignment: center-right;");
        getColVlrImposto().setCellValueFactory(param -> new SimpleStringProperty(
                ServiceMascara.getMoeda(param.getValue().vlrImpostoProperty().get(), 2)
        ));
        getColVlrImposto().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<EntradaProdutoProduto, String>(
                ServiceMascara.getNumeroMask(12, 2)
        ));
        getColVlrImposto().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().setVlrImposto(ServiceMascara.getBigDecimalFromTextField(editEvent.getNewValue(), 2));
            getTxtPesquisaProduto().requestFocus();
            totalizaLinha(editEvent.getRowValue());
        });

        setColVlrLiquido(new TableColumn<>("total"));
        getColVlrLiquido().setPrefWidth(90);
        getColVlrLiquido().setStyle("-fx-alignment: center-right;");
        getColVlrLiquido().setCellValueFactory(param -> new SimpleStringProperty(
                ServiceMascara.getMoeda(param.getValue().vlrLiquidoProperty().get(), 2)
        ));

        setColEstoque(new TableColumn<>("estoque"));
        getColEstoque().setPrefWidth(70);
        getColEstoque().setStyle("-fx-alignment: center-right;");
        getColEstoque().setCellValueFactory(param -> param.getValue().estoqueProperty().asObject());

    }

    public void preencheTabela() {
        getTvItensNfe().getColumns().setAll(
                //getColId(), getColProdId(),
                getColProdCod(), getColProdDescricao(), getColCFOP(), getColProdLote(),
                getColProdValidade(), getColQtd(), getColVlrUnitario(), getColVlrBruto(), getColVlrDesconto(),
                getColVlrImposto(), getColVlrLiquido()
                //, getColEstoque()
        );

        getTvItensNfe().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getTvItensNfe().getSelectionModel().setCellSelectionEnabled(true);
        getTvItensNfe().setEditable(true);
        getTvItensNfe().setItems(getEntradaProdutoProdutoObservableList());
    }

    public void escutaLista() {
        try {
            getTvItensNfe().getFocusModel().focusedCellProperty().addListener((ov, o, n) -> setTp(n));
            getTvItensNfe().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (getTvItensNfe().getEditingCell() == null && keyEvent.getCode() == KeyCode.ENTER) {
                    getTvItensNfe().getSelectionModel().selectNext();
                    //setTp(getTvSaidaProdutoProduto().getFocusModel().getFocusedCell());
                    keyEvent.consume();
                }
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (getTp().getTableColumn() == getColVlrLiquido())
                        getTxtPesquisaProduto().requestFocus();
                    if (getTvItensNfe().getEditingCell() != null)
                        getTvItensNfe().getSelectionModel().selectNext();
                }

                if (keyEvent.getCode() == KeyCode.DELETE)
                    if (getTvItensNfe().getEditingCell() == null)
                        getEntradaProdutoProdutoObservableList().remove(getTvItensNfe().getSelectionModel().getSelectedItem());
            });

            getTvItensNfe().setOnKeyPressed(keyEvent -> {
                if (getTvItensNfe().getSelectionModel().getSelectedItem() == null) return;
                if (getTp().getTableColumn() == getColCFOP()) {
                    if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.UP) {
                        getTvItensNfe().edit(getTp().getRow(), getColCFOP());
                    }
                }
                if (keyEvent.getCode().isLetterKey()
                        || keyEvent.getCode().isDigitKey()) {
                    ControllerPrincipal.setLastKey(keyEvent.getText());
                    getTvItensNfe().edit(getTp().getRow(), getTp().getTableColumn());
                }
            });

            getEntradaProdutoProdutoObservableList().addListener((ListChangeListener<? super EntradaProdutoProduto>) change -> {
                totalizaTabela();
            });

            totalImpEntradaProperty().addListener(observable -> atualizaTotalImposto());

            totalFreteProperty().addListener(observable -> atualizaTotalImposto());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void totalizaLinha(EntradaProdutoProduto entradaProdutoProduto) {
        switch (entradaProdutoProduto.getCodigoCFOP()) {
            case AMOSTRA:
            case CORTESIA:
            case TESTE:
                entradaProdutoProduto.qtdProperty().setValue(1);
            case BONIFICACAO:
            case CONSUMO:
                entradaProdutoProduto.vlrBrutoProperty().setValue(entradaProdutoProduto.vlrUnitarioProperty().getValue()
                        .multiply(BigDecimal.valueOf(entradaProdutoProduto.qtdProperty().getValue())).setScale(2));
                entradaProdutoProduto.vlrDescontoProperty().setValue(entradaProdutoProduto.vlrBrutoProperty().getValue());
                break;
            case COMERCIALIZACAO:
                entradaProdutoProduto.vlrBrutoProperty().setValue(entradaProdutoProduto.vlrUnitarioProperty().getValue()
                        .multiply(BigDecimal.valueOf(entradaProdutoProduto.qtdProperty().getValue())).setScale(2, RoundingMode.HALF_UP));
                if (entradaProdutoProduto.vlrDescontoProperty().getValue()
                        .compareTo(entradaProdutoProduto.vlrBrutoProperty().getValue().multiply(new BigDecimal("0.15"))) > 0) {
                    entradaProdutoProduto.vlrDescontoProperty()
                            .setValue(entradaProdutoProduto.vlrBrutoProperty().getValue().multiply(new BigDecimal("0.15")));
                }
                break;
        }


        entradaProdutoProduto.vlrLiquidoProperty().setValue((entradaProdutoProduto.vlrBrutoProperty().getValue()
                .add(entradaProdutoProduto.vlrImpostoProperty().getValue()))
                .subtract(entradaProdutoProduto.vlrDescontoProperty().getValue()).setScale(2, RoundingMode.HALF_UP));

        getTvItensNfe().refresh();

        totalizaTabela();
    }

    private void totalizaTabela() {
        final Integer[] qtdVol = {0};
        getEntradaProdutoProdutoObservableList().stream()
                .forEach(entradaProdutoProduto -> {
                    Double div = entradaProdutoProduto.qtdProperty().getValue().doubleValue() / entradaProdutoProduto.getProduto().varejoProperty().getValue().doubleValue();
                    if ((div - div.intValue()) > 0)
                        qtdVol[0] += (div.intValue() + 1);
                    else
                        qtdVol[0] += div.intValue();
                });
        totalQtdVolumeProperty().setValue(qtdVol[0]);

        totalQtdItemProperty().setValue(getEntradaProdutoProdutoObservableList().stream()
                .collect(Collectors.groupingBy(EntradaProdutoProduto::getProduto)).size());
        totalQtdProdutoProperty().setValue(getEntradaProdutoProdutoObservableList().stream()
                .collect(Collectors.summingInt(EntradaProdutoProduto::getQtd)));
        totalBrutoProperty().setValue(getEntradaProdutoProdutoObservableList().stream()
                .map(EntradaProdutoProduto::getVlrBruto)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        totalDescontoProperty().setValue(getEntradaProdutoProdutoObservableList().stream()
                .map(EntradaProdutoProduto::getVlrDesconto)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        atualizaTotalImposto();
//        totalImpostoProperty().setValue(getEntradaProdutoProdutoObservableList().stream()
//                .map(EntradaProdutoProduto::getVlrImposto)
//                .reduce(BigDecimal.ZERO, BigDecimal::add));
//        totalLiquidoProperty().setValue(getEntradaProdutoProdutoObservableList().stream()
//                .map(EntradaProdutoProduto::getVlrLiquido)
//                .reduce(BigDecimal.ZERO, BigDecimal::add)
//                .add(totalFreteProperty().getValue()));
    }

    private void atualizaTotalImposto() {
        totalImpostoProperty().setValue(getEntradaProdutoProdutoObservableList().stream()
                .map(EntradaProdutoProduto::getVlrImposto)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(totalImpEntradaProperty().getValue()));
        atualizaTotal();
    }

    private void atualizaTotal() {
        totalLiquidoProperty().setValue(
                (totalBrutoProperty().getValue()
                        .add(totalImpostoProperty().getValue())
                        .add(totalFreteProperty().getValue()))
                        .subtract(totalDescontoProperty().getValue()));
    }

    /**
     * END Voids
     */

    /**
     * Begin Returns
     */


    /**
     * END Returns
     */

    /**
     * Begin booleans
     */

    public boolean incluirEstoque() {
        try {
            getEntradaProdutoProdutoObservableList().stream()
                    .forEach(entradaProdutoProduto -> {
                        ProdutoEstoque produtoEstoque = new ProdutoEstoque(entradaProdutoProduto);
                        entradaProdutoProduto.produtoProperty().getValue()
                                .getProdutoEstoqueList().add(produtoEstoque);
                        getFichaKardexList().add(new FichaKardex(entradaProdutoProduto.qtdProperty().getValue(),
                                produtoEstoque, true));
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * END booleans
     */


    /**
     * Begin Gets and Setters
     */

    public TablePosition getTp() {
        return tp;
    }

    public void setTp(TablePosition tp) {
        this.tp = tp;
    }

    public TableView<EntradaProdutoProduto> getTvItensNfe() {
        return tvItensNfe;
    }

    public void setTvItensNfe(TableView<EntradaProdutoProduto> tvItensNfe) {
        this.tvItensNfe = tvItensNfe;
    }

    public ObservableList<EntradaProdutoProduto> getEntradaProdutoProdutoObservableList() {
        return entradaProdutoProdutoObservableList;
    }

    public void setEntradaProdutoProdutoObservableList(ObservableList<EntradaProdutoProduto> entradaProdutoProdutoObservableList) {
        this.entradaProdutoProdutoObservableList = entradaProdutoProdutoObservableList;
    }

    public TextField getTxtPesquisaProduto() {
        return txtPesquisaProduto;
    }

    public void setTxtPesquisaProduto(TextField txtPesquisaProduto) {
        this.txtPesquisaProduto = txtPesquisaProduto;
    }

    public TableColumn<EntradaProdutoProduto, String> getColId() {
        return colId;
    }

    public void setColId(TableColumn<EntradaProdutoProduto, String> colId) {
        this.colId = colId;
    }

    public TableColumn<EntradaProdutoProduto, String> getColProdId() {
        return colProdId;
    }

    public void setColProdId(TableColumn<EntradaProdutoProduto, String> colProdId) {
        this.colProdId = colProdId;
    }

    public TableColumn<EntradaProdutoProduto, String> getColProdCod() {
        return colProdCod;
    }

    public void setColProdCod(TableColumn<EntradaProdutoProduto, String> colProdCod) {
        this.colProdCod = colProdCod;
    }

    public TableColumn<EntradaProdutoProduto, String> getColProdDescricao() {
        return colProdDescricao;
    }

    public void setColProdDescricao(TableColumn<EntradaProdutoProduto, String> colProdDescricao) {
        this.colProdDescricao = colProdDescricao;
    }

    public TableColumn<EntradaProdutoProduto, TipoCodigoCFOP> getColCFOP() {
        return colCFOP;
    }

    public void setColCFOP(TableColumn<EntradaProdutoProduto, TipoCodigoCFOP> colCFOP) {
        this.colCFOP = colCFOP;
    }

    public TableColumn<EntradaProdutoProduto, String> getColProdLote() {
        return colProdLote;
    }

    public void setColProdLote(TableColumn<EntradaProdutoProduto, String> colProdLote) {
        this.colProdLote = colProdLote;
    }

    public TableColumn<EntradaProdutoProduto, String> getColProdValidade() {
        return colProdValidade;
    }

    public void setColProdValidade(TableColumn<EntradaProdutoProduto, String> colProdValidade) {
        this.colProdValidade = colProdValidade;
    }

    public TableColumn<EntradaProdutoProduto, String> getColQtd() {
        return colQtd;
    }

    public void setColQtd(TableColumn<EntradaProdutoProduto, String> colQtd) {
        this.colQtd = colQtd;
    }

    public TableColumn<EntradaProdutoProduto, String> getColVlrUnitario() {
        return colVlrUnitario;
    }

    public void setColVlrUnitario(TableColumn<EntradaProdutoProduto, String> colVlrUnitario) {
        this.colVlrUnitario = colVlrUnitario;
    }

    public TableColumn<EntradaProdutoProduto, String> getColVlrBruto() {
        return colVlrBruto;
    }

    public void setColVlrBruto(TableColumn<EntradaProdutoProduto, String> colVlrBruto) {
        this.colVlrBruto = colVlrBruto;
    }

    public TableColumn<EntradaProdutoProduto, String> getColVlrDesconto() {
        return colVlrDesconto;
    }

    public void setColVlrDesconto(TableColumn<EntradaProdutoProduto, String> colVlrDesconto) {
        this.colVlrDesconto = colVlrDesconto;
    }

    public TableColumn<EntradaProdutoProduto, String> getColVlrImposto() {
        return colVlrImposto;
    }

    public void setColVlrImposto(TableColumn<EntradaProdutoProduto, String> colVlrImposto) {
        this.colVlrImposto = colVlrImposto;
    }

    public TableColumn<EntradaProdutoProduto, String> getColVlrLiquido() {
        return colVlrLiquido;
    }

    public void setColVlrLiquido(TableColumn<EntradaProdutoProduto, String> colVlrLiquido) {
        this.colVlrLiquido = colVlrLiquido;
    }

    public TableColumn<EntradaProdutoProduto, Integer> getColEstoque() {
        return colEstoque;
    }

    public void setColEstoque(TableColumn<EntradaProdutoProduto, Integer> colEstoque) {
        this.colEstoque = colEstoque;
    }

    public TableColumn<EntradaProdutoProduto, Integer> getColVarejo() {
        return colVarejo;
    }

    public void setColVarejo(TableColumn<EntradaProdutoProduto, Integer> colVarejo) {
        this.colVarejo = colVarejo;
    }

    public TableColumn<EntradaProdutoProduto, Integer> getColVolume() {
        return colVolume;
    }

    public void setColVolume(TableColumn<EntradaProdutoProduto, Integer> colVolume) {
        this.colVolume = colVolume;
    }

    public int getTotalQtdItem() {
        return totalQtdItem.get();
    }

    public IntegerProperty totalQtdItemProperty() {
        return totalQtdItem;
    }

    public void setTotalQtdItem(int totalQtdItem) {
        this.totalQtdItem.set(totalQtdItem);
    }

    public int getTotalQtdProduto() {
        return totalQtdProduto.get();
    }

    public IntegerProperty totalQtdProdutoProperty() {
        return totalQtdProduto;
    }

    public void setTotalQtdProduto(int totalQtdProduto) {
        this.totalQtdProduto.set(totalQtdProduto);
    }

    public int getTotalQtdVolume() {
        return totalQtdVolume.get();
    }

    public IntegerProperty totalQtdVolumeProperty() {
        return totalQtdVolume;
    }

    public void setTotalQtdVolume(int totalQtdVolume) {
        this.totalQtdVolume.set(totalQtdVolume);
    }

    public BigDecimal getTotalBruto() {
        return totalBruto.get();
    }

    public ObjectProperty<BigDecimal> totalBrutoProperty() {
        return totalBruto;
    }

    public void setTotalBruto(BigDecimal totalBruto) {
        this.totalBruto.set(totalBruto);
    }

    public BigDecimal getTotalDesconto() {
        return totalDesconto.get();
    }

    public ObjectProperty<BigDecimal> totalDescontoProperty() {
        return totalDesconto;
    }

    public void setTotalDesconto(BigDecimal totalDesconto) {
        this.totalDesconto.set(totalDesconto);
    }

    public BigDecimal getTotalFrete() {
        return totalFrete.get();
    }

    public ObjectProperty<BigDecimal> totalFreteProperty() {
        return totalFrete;
    }

    public void setTotalFrete(BigDecimal totalFrete) {
        this.totalFrete.set(totalFrete);
    }

    public BigDecimal getTotalImposto() {
        return totalImposto.get();
    }

    public ObjectProperty<BigDecimal> totalImpostoProperty() {
        return totalImposto;
    }

    public void setTotalImposto(BigDecimal totalImposto) {
        this.totalImposto.set(totalImposto);
    }

    public BigDecimal getTotalImpEntrada() {
        return totalImpEntrada.get();
    }

    public ObjectProperty<BigDecimal> totalImpEntradaProperty() {
        return totalImpEntrada;
    }

    public void setTotalImpEntrada(BigDecimal totalImpEntrada) {
        this.totalImpEntrada.set(totalImpEntrada);
    }

    public BigDecimal getTotalLiquido() {
        return totalLiquido.get();
    }

    public ObjectProperty<BigDecimal> totalLiquidoProperty() {
        return totalLiquido;
    }

    public void setTotalLiquido(BigDecimal totalLiquido) {
        this.totalLiquido.set(totalLiquido);
    }

    public List<FichaKardex> getFichaKardexList() {
        return fichaKardexList;
    }

    public void setFichaKardexList(List<FichaKardex> fichaKardexList) {
        this.fichaKardexList = fichaKardexList;
    }

    /**
     * END Gets and Setters
     */
}
