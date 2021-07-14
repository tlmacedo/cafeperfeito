package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import br.com.tlmacedo.cafeperfeito.model.dao.ProdutoEstoqueDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.TipoCodigoCFOP;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
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
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class TmodelSaidaProduto {

    private TablePosition tp;
    private TableView<SaidaProdutoProduto> tvItensNfe;
    private ObservableList<SaidaProdutoProduto> saidaProdutoProdutoObservableList;
    private TextField txtPesquisaProduto;
    private ObjectProperty<Empresa> empresa = new SimpleObjectProperty<>();
    private List<FichaKardex> fichaKardexList;

    private TableColumn<SaidaProdutoProduto, String> colId;
    private TableColumn<SaidaProdutoProduto, String> colProdId;
    private TableColumn<SaidaProdutoProduto, String> colProdCod;
    private TableColumn<SaidaProdutoProduto, String> colProdDescricao;
    private TableColumn<SaidaProdutoProduto, TipoCodigoCFOP> colCFOP;
    private TableColumn<SaidaProdutoProduto, String> colProdLote;
    private TableColumn<SaidaProdutoProduto, String> colProdValidade;
    private TableColumn<SaidaProdutoProduto, String> colQtd;
    private TableColumn<SaidaProdutoProduto, String> colVlrUnitario;
    private TableColumn<SaidaProdutoProduto, String> colVlrBruto;
    private TableColumn<SaidaProdutoProduto, String> colVlrDesconto;
    private TableColumn<SaidaProdutoProduto, String> colVlrLiquido;

    private TableColumn<SaidaProdutoProduto, Integer> colEstoque;
    private TableColumn<SaidaProdutoProduto, Integer> colVarejo;
    private TableColumn<SaidaProdutoProduto, Integer> colVolume;

    private IntegerProperty prazo = new SimpleIntegerProperty(0);
    private IntegerProperty totalQtdItem = new SimpleIntegerProperty(0);
    private IntegerProperty totalQtdProduto = new SimpleIntegerProperty(0);
    private IntegerProperty totalQtdVolume = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalDesconto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO);

//    private ObjectProperty<BigDecimal> lucroVlrSaida = new SimpleObjectProperty<>(BigDecimal.ZERO);
//    private IntegerProperty lucroQtdSaida = new SimpleIntegerProperty(0);


    public TmodelSaidaProduto() {
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
            editEvent.getRowValue().codigoCFOPProperty().setValue(editEvent.getNewValue());
            getTvItensNfe().getSelectionModel().selectNext();
            totalizaLinha(editEvent.getRowValue());
        });

        setColProdLote(new TableColumn<>("lote"));
        getColProdLote().setPrefWidth(105);
        getColProdLote().setStyle("-fx-alignment: center;");
        getColProdLote().setCellValueFactory(param -> param.getValue().loteProperty());

        setColProdValidade(new TableColumn<>("validade"));
        getColProdValidade().setPrefWidth(100);
        getColProdValidade().setStyle("-fx-alignment: center-right;");
        getColProdValidade().setCellValueFactory(param -> new SimpleStringProperty(param.getValue().dtValidadeProperty().get().format(DTF_DATA)));


        setColQtd(new TableColumn<>("qtd"));
        getColQtd().setPrefWidth(70);
        getColQtd().setStyle("-fx-alignment: center-right;");
        getColQtd().setCellValueFactory(param -> param.getValue().qtdProperty().asString());
        getColQtd().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<SaidaProdutoProduto, String>(
                ServiceMascara.getNumeroMask(12, 0)
        ));
        getColQtd().setOnEditCommit(editEvent -> {
            Integer newValue = Integer.valueOf(editEvent.getNewValue());
            if (newValue.compareTo(0) == 0)
                newValue = 1;
            Integer oldValue = Integer.valueOf(editEvent.getOldValue());
            editEvent.getRowValue().qtdProperty().setValue(validEstoque(newValue, oldValue));

            if (editEvent.getRowValue().codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.COMERCIALIZACAO)) {
                calculaDescontoCliente();
            } else {
                if (newValue > oldValue)
                    calculaDescontoCliente();
            }

            getTvItensNfe().getSelectionModel().selectNext();
            totalizaLinha(editEvent.getRowValue());
        });

        setColVlrUnitario(new TableColumn<>("vlr. venda"));
        getColVlrUnitario().setPrefWidth(90);
        getColVlrUnitario().setStyle("-fx-alignment: center-right;");
        getColVlrUnitario().setCellValueFactory(param -> new SimpleStringProperty(
                ServiceMascara.getMoeda(param.getValue().vlrUnitarioProperty().getValue(), 2)
        ));
        getColVlrUnitario().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<SaidaProdutoProduto, String>(
                ServiceMascara.getNumeroMask(12, 2)
        ));
        getColVlrUnitario().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().vlrUnitarioProperty().setValue(ServiceMascara.getBigDecimalFromTextField(editEvent.getNewValue(), 2));
            getTxtPesquisaProduto().requestFocus();
            totalizaLinha(editEvent.getRowValue());
        });

        setColVlrBruto(new TableColumn<>("vlr bruto"));
        getColVlrBruto().setPrefWidth(90);
        getColVlrBruto().setStyle("-fx-alignment: center-right;");
        getColVlrBruto().setCellValueFactory(param ->
                new SimpleStringProperty(
                        ServiceMascara.getMoeda(param.getValue().vlrBrutoProperty().getValue(), 2)
                ));

        setColVlrDesconto(new TableColumn<>("desc R$"));
        getColVlrDesconto().setPrefWidth(90);
        getColVlrDesconto().setStyle("-fx-alignment: center-right;");
        getColVlrDesconto().setCellValueFactory(param -> new SimpleStringProperty(
                ServiceMascara.getMoeda(param.getValue().vlrDescontoProperty().getValue(), 2)
        ));
        getColVlrDesconto().setCellFactory(param -> new SetCellFactoryTableCell_EdtitingCell<SaidaProdutoProduto, String>(
                ServiceMascara.getNumeroMask(12, 2)
        ));
        getColVlrDesconto().setOnEditCommit(editEvent -> {
            editEvent.getRowValue().vlrDescontoProperty().setValue(ServiceMascara.getBigDecimalFromTextField(editEvent.getNewValue(), 2));
            getTxtPesquisaProduto().requestFocus();
            totalizaLinha(editEvent.getRowValue());
        });

        setColVlrLiquido(new TableColumn<>("total"));
        getColVlrLiquido().setPrefWidth(90);
        getColVlrLiquido().setStyle("-fx-alignment: center-right;");
        getColVlrLiquido().setCellValueFactory(param -> new SimpleStringProperty(
                ServiceMascara.getMoeda(param.getValue().vlrLiquidoProperty().getValue(), 2)
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
                getColVlrLiquido()
                //, getColEstoque()
        );

        getTvItensNfe().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getTvItensNfe().getSelectionModel().setCellSelectionEnabled(true);
        getTvItensNfe().setEditable(true);
        getTvItensNfe().setItems(getSaidaProdutoProdutoObservableList());
    }

    public void escutaLista() {
        try {
            getTvItensNfe().getFocusModel().focusedCellProperty().addListener((ov, o, n) -> setTp(n));

            getTvItensNfe().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (getTvItensNfe().getEditingCell() == null && keyEvent.getCode() == KeyCode.ENTER) {
                    getTvItensNfe().getSelectionModel().selectNext();
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
                        getSaidaProdutoProdutoObservableList().remove(getTvItensNfe().getSelectionModel().getSelectedItem());
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

            getSaidaProdutoProdutoObservableList().addListener((ListChangeListener<? super SaidaProdutoProduto>) change -> {
                //calculaDescontoCliente();
                totalizaTabela();
            });

            empresaProperty().addListener((ov, o, n) -> {
                prazoProperty().setValue(0);
                if (n != null)
                    prazoProperty().setValue(empresaProperty().getValue().prazoProperty().getValue());
                calculaDescontoCliente();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void totalizaLinha(SaidaProdutoProduto saidaProdutoProduto) {
        switch (saidaProdutoProduto.getCodigoCFOP()) {
            case AMOSTRA:
            case CORTESIA:
            case TESTE:
                saidaProdutoProduto.qtdProperty().setValue(1);
            case AVARIA:
            case BONIFICACAO:
            case CONSUMO:
                saidaProdutoProduto.vlrBrutoProperty().setValue(saidaProdutoProduto.vlrUnitarioProperty().getValue()
                        .multiply(BigDecimal.valueOf(saidaProdutoProduto.qtdProperty().getValue())).setScale(2));
                saidaProdutoProduto.vlrDescontoProperty().setValue(saidaProdutoProduto.vlrBrutoProperty().getValue());
                break;
            case COMERCIALIZACAO:
                saidaProdutoProduto.vlrBrutoProperty().setValue(saidaProdutoProduto.vlrUnitarioProperty().getValue()
                        .multiply(BigDecimal.valueOf(saidaProdutoProduto.qtdProperty().getValue())).setScale(2, RoundingMode.HALF_UP));
                if (saidaProdutoProduto.vlrDescontoProperty().getValue()
                        .compareTo(saidaProdutoProduto.vlrBrutoProperty().getValue().multiply(TCONFIG.getInfLoja().getDescMax())) > 0) {
                    saidaProdutoProduto.vlrDescontoProperty()
                            .setValue(saidaProdutoProduto.vlrBrutoProperty().getValue().multiply(TCONFIG.getInfLoja().getDescMax()));
                }
                break;
        }
        saidaProdutoProduto.vlrEntradaBrutoProperty().setValue(saidaProdutoProduto.vlrEntradaProperty().getValue().multiply(new BigDecimal(saidaProdutoProduto.qtdProperty().getValue())));

        Double vol = saidaProdutoProduto.qtdProperty().getValue().doubleValue() / saidaProdutoProduto.produtoProperty().getValue().varejoProperty().getValue().doubleValue();
        saidaProdutoProduto.volumeProperty().setValue(((vol - vol.intValue()) > 0) ? vol.intValue() + 1. : vol.intValue());

        saidaProdutoProduto.vlrLiquidoProperty().setValue(saidaProdutoProduto.vlrBrutoProperty().getValue()
                .subtract(saidaProdutoProduto.vlrDescontoProperty().getValue()).setScale(2, RoundingMode.HALF_UP));

        getTvItensNfe().refresh();

        totalizaTabela();
    }

    private void totalizaTabela() {
        totalQtdVolumeProperty().setValue(getSaidaProdutoProdutoObservableList().stream()
                .collect(Collectors.summingInt(SaidaProdutoProduto::getVolume)));
        totalQtdItemProperty().setValue(getSaidaProdutoProdutoObservableList().stream()
                .collect(Collectors.groupingBy(SaidaProdutoProduto::getProdId)).size());
        totalQtdProdutoProperty().setValue(getSaidaProdutoProdutoObservableList().stream()
                .collect(Collectors.summingInt(SaidaProdutoProduto::getQtd)));
        totalBrutoProperty().setValue(getSaidaProdutoProdutoObservableList().stream()
                .map(SaidaProdutoProduto::getVlrBruto)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        totalDescontoProperty().setValue(getSaidaProdutoProdutoObservableList().stream()
                .map(SaidaProdutoProduto::getVlrDesconto)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        totalLiquidoProperty().setValue(getSaidaProdutoProdutoObservableList().stream()
                .map(SaidaProdutoProduto::getVlrLiquido)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public void calculaDescontoCliente() {
        if (empresaProperty().getValue() == null) return;
        getSaidaProdutoProdutoObservableList().stream()
                .collect(Collectors.groupingBy(SaidaProdutoProduto::getProdId))
                .forEach((aLong, saidaProdutosId) -> {
                    prazoProperty().setValue(empresaProperty().getValue().prazoProperty().getValue());
                    EmpresaCondicoes condicoes;
                    if ((condicoes = empresaProperty().getValue().getEmpresaCondicoes().stream()
                            .filter(empresaCondicoes -> empresaCondicoes.produtoProperty().getValue().idProperty().getValue() == aLong
                                    && empresaCondicoes.validadeProperty().getValue().compareTo(LocalDate.now()) >= 0)
                            .sorted(Comparator.comparing(EmpresaCondicoes::getValidade))
                            .findFirst().orElse(null)) != null) {
                        final Integer fator = (condicoes.qtdMinimaProperty().getValue().compareTo(0) <= 0)
                                ? 0
                                : saidaProdutosId.stream()
                                .filter(saidaProdId -> saidaProdId.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.COMERCIALIZACAO))
                                .collect(Collectors.summingInt(SaidaProdutoProduto::getQtd)) / condicoes.qtdMinimaProperty().getValue();


                        if (condicoes.valorProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
                            saidaProdutosId.stream()
                                    .forEach(saidaProdId -> {
                                        saidaProdId.vlrUnitarioProperty().setValue(condicoes.valorProperty().getValue());
                                        if (fator > 0)
                                            saidaProdId.vlrDescontoProperty().setValue(condicoes.descontoProperty().getValue()
                                                    .multiply(new BigDecimal(saidaProdId.qtdProperty().getValue())));
                                        else
                                            saidaProdId.vlrDescontoProperty().setValue(BigDecimal.ZERO);
                                    });

                        if (condicoes.descontoProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
                            saidaProdutosId.stream().forEach(saidaProdId -> {
                                if (fator > 0)
                                    saidaProdId.vlrDescontoProperty().setValue(condicoes.descontoProperty().getValue()
                                            .multiply(new BigDecimal(saidaProdId.qtdProperty().getValue())));
                                else
                                    saidaProdId.vlrDescontoProperty().setValue(BigDecimal.ZERO);
                            });

                        if (fator > 0
                                && condicoes.qtdMinimaProperty().getValue() > 0
                                && (condicoes.bonificacaoProperty().getValue().compareTo(0) > 0
                                || condicoes.retiradaProperty().getValue().compareTo(0) > 0)) {
                            prazoProperty().setValue(condicoes.prazoProperty().getValue());
                            System.out.printf("passando por aqui!\n");
                            if (condicoes.bonificacaoProperty().getValue() == condicoes.qtdMinimaProperty().getValue()) {
                                saidaProdutosId.stream()
                                        .forEach(saidaProdId -> saidaProdId.codigoCFOPProperty().setValue(TipoCodigoCFOP.BONIFICACAO));
                            } else if (condicoes.retiradaProperty().getValue() == condicoes.qtdMinimaProperty().getValue()) {
                                saidaProdutosId.stream()
                                        .forEach(saidaProdId -> saidaProdId.codigoCFOPProperty().setValue(TipoCodigoCFOP.CONSUMO));
                            } else {
                                TipoCodigoCFOP codigoCFOP = (condicoes.bonificacaoProperty().getValue() > 0)
                                        ? TipoCodigoCFOP.BONIFICACAO
                                        : ((condicoes.retiradaProperty().getValue() > 0)
                                        ? TipoCodigoCFOP.CONSUMO
                                        : TipoCodigoCFOP.COMERCIALIZACAO);
//                                System.out.printf("codigoCFOP(01): %s\n", codigoCFOP);
//                                if (codigoCFOP == null)
//                                    codigoCFOP = TipoCodigoCFOP.COMERCIALIZACAO;
//                                System.out.printf("codigoCFOP(02): %s\n", codigoCFOP);
                                switch (codigoCFOP) {
                                    case BONIFICACAO:
                                        saidaProdutosId.stream()
                                                .filter(saidaProdId -> saidaProdId.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.CONSUMO))
                                                .forEach(saidaProdId -> getSaidaProdutoProdutoObservableList().remove(saidaProdId));
                                        break;
                                    case CONSUMO:
                                        saidaProdutosId.stream()
                                                .filter(saidaProdId -> saidaProdId.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.BONIFICACAO))
                                                .forEach(saidaProdId -> getSaidaProdutoProdutoObservableList().remove(saidaProdId));
                                        break;
                                }
                                final Integer[] restBonif = {fator - saidaProdutosId.stream()
                                        .filter(saidaProdId -> saidaProdId.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.CONSUMO)
                                                || saidaProdId.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.BONIFICACAO))
                                        .collect(Collectors.summingInt(SaidaProdutoProduto::getQtd))};
                                if (restBonif[0] <= 0) {
                                    saidaProdutosId.stream()
                                            .filter(saidaProdId -> saidaProdId.codigoCFOPProperty().getValue().equals(codigoCFOP))
                                            .sorted(Comparator.comparing(SaidaProdutoProduto::getDtValidade).reversed())
                                            .forEach(saidaProdId -> {
                                                saidaProdId.qtdProperty().setValue(saidaProdId.qtdProperty().getValue() + restBonif[0]);
                                                if (saidaProdId.qtdProperty().getValue().compareTo(0) <= 0) {
                                                    restBonif[0] = saidaProdId.qtdProperty().getValue();
                                                    getSaidaProdutoProdutoObservableList().remove(saidaProdId);
                                                } else {
                                                    restBonif[0] = 0;
                                                }
                                            });
                                } else {
                                    new ProdutoEstoqueDAO().getAll(ProdutoEstoque.class,
                                            String.format("qtd > 0 AND produto_id = \'%d\'", aLong), "dtValidade, id").stream()
                                            .collect(Collectors.groupingBy(ProdutoEstoque::getLote, LinkedHashMap::new, Collectors.toList()))
                                            .forEach((s, produtoEstoques) -> {
                                                if (restBonif[0].compareTo(0) > 0) {
                                                    Integer qtdDisponivel = ((produtoEstoques.stream()
                                                            .collect(Collectors.summingInt(ProdutoEstoque::getQtd))) - (getSaidaProdutoProdutoObservableList().stream()
                                                            .filter(saidaProdutoProduto -> saidaProdutoProduto.produtoProperty().getValue().idProperty().getValue()
                                                                    .equals(aLong)
                                                                    && saidaProdutoProduto.loteProperty().getValue().equals(s))
                                                            .collect(Collectors.summingInt(SaidaProdutoProduto::getQtd))));
                                                    if (qtdDisponivel.compareTo(0) > 0) {
                                                        Integer qtdAdd = (restBonif[0] - qtdDisponivel > 0)
                                                                ? qtdDisponivel : restBonif[0];
                                                        restBonif[0] -= qtdAdd;
                                                        SaidaProdutoProduto newItem;
                                                        if ((newItem = saidaProdutosId.stream()
                                                                .filter(saidaProdId -> saidaProdId.loteProperty().getValue().equals(s)
                                                                        && saidaProdId.codigoCFOPProperty().getValue().equals(codigoCFOP))
                                                                .findFirst().orElse(null)) == null)
                                                            getSaidaProdutoProdutoObservableList().add(new SaidaProdutoProduto(produtoEstoques.get(0), codigoCFOP, qtdAdd));
                                                        else
                                                            newItem.qtdProperty().setValue(newItem.qtdProperty().getValue() + qtdAdd);
                                                    }
                                                }
                                            });
                                }
                            }
                        }
                    }
                    saidaProdutosId.stream().forEach(saidaProdId -> totalizaLinha(saidaProdId));
                });
    }

    /**
     * END Voids
     */

    /**
     * Begin Returns
     */

    public Integer validEstoque(Integer newQtd, Integer oldQtd) {
        if (getSaidaProdutoProdutoObservableList().stream()
                .filter(saidaProdutoProduto -> saidaProdutoProduto.produtoProperty().getValue().idProperty().getValue() == getTvItensNfe().getItems().get(getTp().getRow()).produtoProperty().getValue().idProperty().getValue()
                        && saidaProdutoProduto.loteProperty().getValue().equals(getTvItensNfe().getItems().get(getTp().getRow()).loteProperty().getValue())).count() == 1) {
            if (newQtd.compareTo(getTvItensNfe().getItems().get(getTp().getRow()).estoqueProperty().getValue()) > 0)
                return getTvItensNfe().getItems().get(getTp().getRow()).estoqueProperty().getValue();
            else
                return newQtd;
        } else {
            Integer qtdSaiu = getSaidaProdutoProdutoObservableList().stream()
                    .filter(saidaProdutoProduto -> saidaProdutoProduto.produtoProperty().getValue().idProperty().getValue() == getTvItensNfe().getItems().get(getTp().getRow()).produtoProperty().getValue().idProperty().getValue()
                            && saidaProdutoProduto.loteProperty().getValue().equals(getTvItensNfe().getItems().get(getTp().getRow()).loteProperty().getValue()))
                    .collect(Collectors.summingInt(SaidaProdutoProduto::getQtd)) - oldQtd;
            Integer qtdSaida = qtdSaiu + newQtd;
            if (qtdSaida.compareTo(getTvItensNfe().getItems().get(getTp().getRow()).estoqueProperty().getValue()) > 0)
                return getTvItensNfe().getItems().get(getTp().getRow()).estoqueProperty().getValue() - (qtdSaiu);
            else
                return newQtd;
        }
    }


    /**
     * END Returns
     */

    /**
     * Begin booleans
     */


    public boolean baixarEstoque() {
        ProdutoEstoqueDAO produtoEstoqueDAO = new ProdutoEstoqueDAO();
        try {
            getFichaKardexList().clear();
            produtoEstoqueDAO.transactionBegin();
            List<ProdutoEstoque> produtoEstoqueList = produtoEstoqueDAO.getAll(ProdutoEstoque.class,
                    String.format("qtd > 0"), "dtValidade, id");
            getSaidaProdutoProdutoObservableList().stream()
                    .sorted(Comparator.comparing(SaidaProdutoProduto::getProdId))
                    .collect(Collectors.groupingBy(SaidaProdutoProduto::getProdId, LinkedHashMap::new, Collectors.toList()))
                    .forEach((aLong, saidaProdutoProdutos) -> {
                        saidaProdutoProdutos.stream()
                                .collect(Collectors.groupingBy(SaidaProdutoProduto::getLote, LinkedHashMap::new, Collectors.toList()))
                                .forEach((s, saidaProdutoProdutos1) -> {
                                    final Integer[] saldoSaida = {saidaProdutoProdutos1.stream().collect(Collectors.summingInt(SaidaProdutoProduto::getQtd)), 0};
                                    produtoEstoqueList.stream()
                                            .filter(produtoEstoque -> produtoEstoque.produtoProperty().getValue().idProperty().getValue().equals(aLong)
                                                    && produtoEstoque.loteProperty().getValue().equals(s))
                                            .forEach(produtoEstoque -> {
                                                try {
                                                    if (saldoSaida[0].compareTo(0) > 0) {
                                                        produtoEstoque.qtdProperty().setValue(produtoEstoque.qtdProperty().getValue() - saldoSaida[0]);
                                                        if (produtoEstoque.qtdProperty().getValue().compareTo(0) < 0) {
                                                            getFichaKardexList().add(new FichaKardex(produtoEstoque.qtdProperty().getValue() - saldoSaida[0],
                                                                    produtoEstoque, false));
                                                            saldoSaida[0] = produtoEstoque.qtdProperty().getValue() * (-1);
                                                            produtoEstoque.qtdProperty().setValue(0);
                                                        } else {
                                                            getFichaKardexList().add(new FichaKardex(saldoSaida[0],
                                                                    produtoEstoque, false));
                                                            saldoSaida[0] = 0;
                                                        }
                                                        produtoEstoqueDAO.setTransactionPersist(produtoEstoque);
                                                    }
                                                } catch (Exception ex) {
                                                    ex.printStackTrace();
                                                }
                                            });
                                });
                    });
            produtoEstoqueDAO.transactionCommit();
        } catch (Exception ex) {
            ex.printStackTrace();
            produtoEstoqueDAO.transactionRollback();
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

    public TableView<SaidaProdutoProduto> getTvItensNfe() {
        return tvItensNfe;
    }

    public void setTvItensNfe(TableView<SaidaProdutoProduto> tvItensNfe) {
        this.tvItensNfe = tvItensNfe;
    }

    public ObservableList<SaidaProdutoProduto> getSaidaProdutoProdutoObservableList() {
        return saidaProdutoProdutoObservableList;
    }

    public void setSaidaProdutoProdutoObservableList(ObservableList<SaidaProdutoProduto> saidaProdutoProdutoObservableList) {
        this.saidaProdutoProdutoObservableList = saidaProdutoProdutoObservableList;
    }

    public TextField getTxtPesquisaProduto() {
        return txtPesquisaProduto;
    }

    public void setTxtPesquisaProduto(TextField txtPesquisaProduto) {
        this.txtPesquisaProduto = txtPesquisaProduto;
    }

    public Empresa getEmpresa() {
        return empresa.get();
    }

    public ObjectProperty<Empresa> empresaProperty() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa.set(empresa);
    }

    public List<FichaKardex> getFichaKardexList() {
        return fichaKardexList;
    }

    public void setFichaKardexList(List<FichaKardex> fichaKardexList) {
        this.fichaKardexList = fichaKardexList;
    }

    public TableColumn<SaidaProdutoProduto, String> getColId() {
        return colId;
    }

    public void setColId(TableColumn<SaidaProdutoProduto, String> colId) {
        this.colId = colId;
    }

    public TableColumn<SaidaProdutoProduto, String> getColProdId() {
        return colProdId;
    }

    public void setColProdId(TableColumn<SaidaProdutoProduto, String> colProdId) {
        this.colProdId = colProdId;
    }

    public TableColumn<SaidaProdutoProduto, String> getColProdCod() {
        return colProdCod;
    }

    public void setColProdCod(TableColumn<SaidaProdutoProduto, String> colProdCod) {
        this.colProdCod = colProdCod;
    }

    public TableColumn<SaidaProdutoProduto, String> getColProdDescricao() {
        return colProdDescricao;
    }

    public void setColProdDescricao(TableColumn<SaidaProdutoProduto, String> colProdDescricao) {
        this.colProdDescricao = colProdDescricao;
    }

    public TableColumn<SaidaProdutoProduto, TipoCodigoCFOP> getColCFOP() {
        return colCFOP;
    }

    public void setColCFOP(TableColumn<SaidaProdutoProduto, TipoCodigoCFOP> colCFOP) {
        this.colCFOP = colCFOP;
    }

    public TableColumn<SaidaProdutoProduto, String> getColProdLote() {
        return colProdLote;
    }

    public void setColProdLote(TableColumn<SaidaProdutoProduto, String> colProdLote) {
        this.colProdLote = colProdLote;
    }

    public TableColumn<SaidaProdutoProduto, String> getColProdValidade() {
        return colProdValidade;
    }

    public void setColProdValidade(TableColumn<SaidaProdutoProduto, String> colProdValidade) {
        this.colProdValidade = colProdValidade;
    }

    public TableColumn<SaidaProdutoProduto, String> getColQtd() {
        return colQtd;
    }

    public void setColQtd(TableColumn<SaidaProdutoProduto, String> colQtd) {
        this.colQtd = colQtd;
    }

    public TableColumn<SaidaProdutoProduto, String> getColVlrUnitario() {
        return colVlrUnitario;
    }

    public void setColVlrUnitario(TableColumn<SaidaProdutoProduto, String> colVlrUnitario) {
        this.colVlrUnitario = colVlrUnitario;
    }

    public TableColumn<SaidaProdutoProduto, String> getColVlrBruto() {
        return colVlrBruto;
    }

    public void setColVlrBruto(TableColumn<SaidaProdutoProduto, String> colVlrBruto) {
        this.colVlrBruto = colVlrBruto;
    }

    public TableColumn<SaidaProdutoProduto, String> getColVlrDesconto() {
        return colVlrDesconto;
    }

    public void setColVlrDesconto(TableColumn<SaidaProdutoProduto, String> colVlrDesconto) {
        this.colVlrDesconto = colVlrDesconto;
    }

    public TableColumn<SaidaProdutoProduto, String> getColVlrLiquido() {
        return colVlrLiquido;
    }

    public void setColVlrLiquido(TableColumn<SaidaProdutoProduto, String> colVlrLiquido) {
        this.colVlrLiquido = colVlrLiquido;
    }

    public TableColumn<SaidaProdutoProduto, Integer> getColEstoque() {
        return colEstoque;
    }

    public void setColEstoque(TableColumn<SaidaProdutoProduto, Integer> colEstoque) {
        this.colEstoque = colEstoque;
    }

    public TableColumn<SaidaProdutoProduto, Integer> getColVarejo() {
        return colVarejo;
    }

    public void setColVarejo(TableColumn<SaidaProdutoProduto, Integer> colVarejo) {
        this.colVarejo = colVarejo;
    }

    public TableColumn<SaidaProdutoProduto, Integer> getColVolume() {
        return colVolume;
    }

    public void setColVolume(TableColumn<SaidaProdutoProduto, Integer> colVolume) {
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

    public BigDecimal getTotalLiquido() {
        return totalLiquido.get();
    }

    public ObjectProperty<BigDecimal> totalLiquidoProperty() {
        return totalLiquido;
    }

    public void setTotalLiquido(BigDecimal totalLiquido) {
        this.totalLiquido.set(totalLiquido);
    }

    public int getPrazo() {
        return prazo.get();
    }

    public IntegerProperty prazoProperty() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo.set(prazo);
    }

    /**
     * END Gets and Setters
     */
}
