package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.model.dao.ContasAReceberDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.PagamentoModalidade;
import br.com.tlmacedo.cafeperfeito.model.enums.PagamentoSituacao;
import br.com.tlmacedo.cafeperfeito.model.enums.TipoCodigoCFOP;
import br.com.tlmacedo.cafeperfeito.model.vo.*;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;

public class TmodelContasAReceber {

    private TablePosition tp;
    private StringProperty txtPesquisa = new SimpleStringProperty();
    private IntegerProperty lblRegistrosLocalizados = new SimpleIntegerProperty();
    private TreeTableView<Object> ttvContasAReceber;
    private FilteredList<ContasAReceber> contasAReceberFilteredList = new FilteredList<>(
            FXCollections.observableArrayList(new ContasAReceberDAO().getAll(ContasAReceber.class, null, null))
    );
    private ObjectProperty<LocalDate> dtpData1 = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dtpData2 = new SimpleObjectProperty<>();
    private BooleanProperty chkDtVenda = new SimpleBooleanProperty();
    private BooleanProperty chkLucroContaPaga = new SimpleBooleanProperty();
    private ObjectProperty<Empresa> empresa = new SimpleObjectProperty<>();
    private ObjectProperty<PagamentoSituacao> pagamentoSituacao = new SimpleObjectProperty<>();
    private ObjectProperty<PagamentoModalidade> pagamentoModalidade = new SimpleObjectProperty<>();

    private TreeItem<Object> pedidoTreeItem;
    private TreeTableColumn<Object, Long> colId;
    private TreeTableColumn<Object, String> colCliente_Documento;
    private TreeTableColumn<Object, LocalDate> colDtVenda;
    private TreeTableColumn<Object, String> colModalidade;
    private TreeTableColumn<Object, String> colSituacao;
    private TreeTableColumn<Object, LocalDate> colDtVencimento_DtPagamento;
    private TreeTableColumn<Object, String> colVlrPedido;
    private TreeTableColumn<Object, String> colVlrDescontos;
    private TreeTableColumn<Object, String> colVlrBruto;
    private TreeTableColumn<Object, String> colVlrCredDeb;
    private TreeTableColumn<Object, String> colVlrLiquido;
    private TreeTableColumn<Object, String> colValorPago;
    private TreeTableColumn<Object, String> colValorSaldo;
    private TreeTableColumn<Object, String> colUsuario;

    private IntegerProperty qtdClientes = new SimpleIntegerProperty(0);

    private IntegerProperty qtdContas = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalContas = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private IntegerProperty qtdContasRetiradas = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalContasRetiradas = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private IntegerProperty qtdContasDescontos = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalContasDescontos = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private IntegerProperty qtdContasAReceber = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalContasAReceber = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private IntegerProperty qtdContasVencidas = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalContasVencidas = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private IntegerProperty qtdContasPendentes = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalContasPendentes = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private IntegerProperty qtdContasPagas = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalContasPagas = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private IntegerProperty qtdContasSaldoClientes = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> totalContasSaldoClientes = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private ObjectProperty<BigDecimal> percLucroBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalLucroBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private ObjectProperty<BigDecimal> percLucroLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> totalLucroLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO);

    public TmodelContasAReceber() {
    }

    /**
     * Begin voids
     */


    public void criarTabela() {
        setColId(new TreeTableColumn("id"));
        getColId().setPrefWidth(50);
        getColId().setStyle("-fx-alignment: center-right;");
        getColId().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return ((ContasAReceber) cellData.getValue().getValue()).idProperty().asObject();
            return new SimpleObjectProperty<>(null);
        });

        setColCliente_Documento(new TreeTableColumn("cliente / documento pag"));
        getColCliente_Documento().setPrefWidth(280);
        getColCliente_Documento().setCellFactory(cellFactory -> new TreeTableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : getItem());
                if (getTreeTableRow().getItem() instanceof ContasAReceber)
                    setAlignment(Pos.CENTER_LEFT);
                else
                    setAlignment(Pos.CENTER_RIGHT);
            }
        });
        getColCliente_Documento().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleStringProperty(((ContasAReceber) cellData.getValue().getValue()).getSaidaProduto().getCliente().getRazaoFantasia());
            if (cellData.getValue().getValue() instanceof Recebimento)
                return ((Recebimento) cellData.getValue().getValue()).documentoProperty();
            return new SimpleStringProperty("");
        });

        setColDtVenda(new TreeTableColumn("dt. venda"));
        getColDtVenda().setPrefWidth(75);
        getColDtVenda().setStyle("-fx-alignment: center-right;");
        getColDtVenda().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleObjectProperty<>(((ContasAReceber) cellData.getValue().getValue()).dtCadastroProperty().getValue().toLocalDate());
            return new SimpleObjectProperty<>();
        });
        getColDtVenda().setCellFactory(cellFactory -> new TreeTableCell<>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setText(getItem() == null ? "" : getItem().format(DTF_DATA));
            }
        });

        setColModalidade(new TreeTableColumn("/ mod pag"));
        getColModalidade().setPrefWidth(70);
        getColModalidade().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Recebimento)
                return new SimpleStringProperty(((Recebimento) cellData.getValue().getValue()).getPagamentoModalidade().getDescricao());
            return new SimpleStringProperty("");
        });

        setColSituacao(new TreeTableColumn("/ situação"));
        getColSituacao().setPrefWidth(75);
        getColSituacao().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof Recebimento)
                return new SimpleStringProperty(((Recebimento) cellData.getValue().getValue()).getPagamentoSituacao().getDescricao());
            return new SimpleStringProperty("");
        });

        setColDtVencimento_DtPagamento(new TreeTableColumn("dt. venc / dt. pag"));
        getColDtVencimento_DtPagamento().setPrefWidth(90);
        getColDtVencimento_DtPagamento().setStyle("-fx-alignment: center-right;");
        getColDtVencimento_DtPagamento().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleObjectProperty<>(((ContasAReceber) cellData.getValue().getValue()).dtVencimentoProperty().getValue());
            if (cellData.getValue().getValue() instanceof Recebimento
                    && ((Recebimento) cellData.getValue().getValue()).dtPagamentoProperty().getValue() != null)
                return new SimpleObjectProperty<>(((Recebimento) cellData.getValue().getValue()).dtPagamentoProperty().getValue());
            return new SimpleObjectProperty<>();
        });
        getColDtVencimento_DtPagamento().setCellFactory(cellFactory -> new TreeTableCell<>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setText(getItem() == null ? "" : getItem().format(DTF_DATA));
            }
        });

        setColVlrPedido(new TreeTableColumn<>("vlr. Pedido"));
        getColVlrPedido().setPrefWidth(80);
        getColVlrPedido().setStyle("-fx-alignment: center-right;");
        getColVlrPedido().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleStringProperty(
                        ServiceMascara.getMoeda(((ContasAReceber) cellData.getValue().getValue())
                                .saidaProdutoProperty().getValue()
                                .getSaidaProdutoProdutoList().stream()
                                .map(SaidaProdutoProduto::getVlrBruto)
                                .reduce(BigDecimal.ZERO, BigDecimal::add), 2));
            return new SimpleStringProperty("");
        });

        setColVlrDescontos(new TreeTableColumn<>("vlr. Desc"));
        getColVlrDescontos().setPrefWidth(80);
        getColVlrDescontos().setStyle("-fx-alignment: center-right;");
        getColVlrDescontos().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleStringProperty(
                        ServiceMascara.getMoeda(((ContasAReceber) cellData.getValue().getValue())
                                .saidaProdutoProperty().getValue()
                                .getSaidaProdutoProdutoList().stream()
                                .map(SaidaProdutoProduto::getVlrDesconto)
                                .reduce(BigDecimal.ZERO, BigDecimal::add), 2));
            return new SimpleStringProperty("");
        });

        setColVlrBruto(new TreeTableColumn<>("vlr. Bruto"));
        getColVlrBruto().setPrefWidth(80);
        getColVlrBruto().setStyle("-fx-alignment: center-right;");
        getColVlrBruto().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleStringProperty(
                        ServiceMascara.getMoeda(((ContasAReceber) cellData.getValue().getValue()).valorProperty().getValue(), 2));
            return new SimpleStringProperty("");
        });

        setColVlrCredDeb(new TreeTableColumn<>("Cred/Déb"));
        getColVlrCredDeb().setPrefWidth(60);
        getColVlrCredDeb().setStyle("-fx-alignment: center-right;");
        getColVlrCredDeb().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleStringProperty(
                        ServiceMascara.getMoeda(((ContasAReceber) cellData.getValue().getValue()).getVlrCredDeb()
                                .setScale(2, RoundingMode.HALF_UP), 2));
            if (cellData.getValue().getValue() instanceof Recebimento)
                if (((Recebimento) cellData.getValue().getValue()).getPagamentoModalidade().getDescricao().toLowerCase().contains("crédito")
                        || ((Recebimento) cellData.getValue().getValue()).getPagamentoModalidade().getDescricao().toLowerCase().contains("débito"))
                    return new SimpleStringProperty(
                            ServiceMascara.getMoeda(((Recebimento) cellData.getValue().getValue()).valorProperty().getValue(), 2));
            return new SimpleStringProperty("");
        });

        setColVlrLiquido(new TreeTableColumn("vlr Liq"));
        getColVlrLiquido().setPrefWidth(80);
        getColVlrLiquido().setStyle("-fx-alignment: center-right;");
        getColVlrLiquido().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleStringProperty(
                        ServiceMascara.getMoeda(((ContasAReceber) cellData.getValue().getValue()).vlrLiquidoProperty().getValue(), 2));
            if (cellData.getValue().getValue() instanceof Recebimento)
                if (!((Recebimento) cellData.getValue().getValue()).pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO))
                    return new SimpleStringProperty(
                            ServiceMascara.getMoeda(((Recebimento) cellData.getValue().getValue()).valorProperty().getValue(), 2));
            return new SimpleStringProperty("");
        });

        setColValorPago(new TreeTableColumn("vlr pago R$"));
        getColValorPago().setPrefWidth(80);
        getColValorPago().setStyle("-fx-alignment: center-right;");
        getColValorPago().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleStringProperty(
                        ServiceMascara.getMoeda(((ContasAReceber) cellData.getValue().getValue()).vlrPagoProperty().getValue(), 2));
            if (cellData.getValue().getValue() instanceof Recebimento)
                if (((Recebimento) cellData.getValue().getValue()).pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO))
                    if (!(((Recebimento) cellData.getValue().getValue()).pagamentoModalidadeProperty().getValue().getDescricao().toLowerCase().contains("crédito")
                            || ((Recebimento) cellData.getValue().getValue()).pagamentoModalidadeProperty().getValue().getDescricao().toLowerCase().contains("débito")))
                        return new SimpleStringProperty(
                                ServiceMascara.getMoeda(((Recebimento) cellData.getValue().getValue()).valorProperty().getValue(), 2));
            return new SimpleStringProperty("");
        });

        setColValorSaldo(new TreeTableColumn<>("saldo R$"));
        getColValorSaldo().setPrefWidth(80);
        getColValorSaldo().setStyle("-fx-alignment: center-right;");
        getColValorSaldo().setCellValueFactory(cellData -> {
            if (cellData.getValue().getValue() instanceof ContasAReceber)
                return new SimpleStringProperty(
                        ServiceMascara.getMoeda(((ContasAReceber) cellData.getValue().getValue()).vlrSaldoProperty().getValue(), 2));
            return new SimpleStringProperty("");
        });


    }

    public void preencherTabela() {
        try {
            setPedidoTreeItem(new TreeItem());
            getContasAReceberFilteredList().stream()
                    .forEach(contasAReceber -> {
                        final BigDecimal[] vlrCredDeb = {BigDecimal.ZERO};
                        final BigDecimal[] vlrPago = {BigDecimal.ZERO};

                        TreeItem<Object> paiItem = new TreeItem(contasAReceber);
                        getPedidoTreeItem().getChildren().add(paiItem);
                        contasAReceber.getRecebimentoList().stream()
                                .forEach(recebimento -> {
                                    TreeItem<Object> filhoItem = new TreeItem(recebimento);
                                    paiItem.getChildren().add(filhoItem);
                                    if (recebimento.getPagamentoModalidade().getDescricao().toLowerCase().contains("crédito")
                                            || recebimento.getPagamentoModalidade().getDescricao().toLowerCase().contains("débito")) {
                                        vlrCredDeb[0] = vlrCredDeb[0].add(recebimento.valorProperty().getValue());
                                    } else {
                                        if (recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO))
                                            vlrPago[0] = vlrPago[0].add(recebimento.valorProperty().getValue());
                                    }
                                });

                        BigDecimal vlrLiquido = contasAReceber.valorProperty().getValue().subtract(vlrCredDeb[0]);

                        contasAReceber.vlrCredDebProperty().setValue(vlrCredDeb[0]);

                        contasAReceber.vlrLiquidoProperty().setValue(vlrLiquido);

                        contasAReceber.vlrPagoProperty().setValue(vlrPago[0]);

                        contasAReceber.vlrSaldoProperty().setValue(vlrLiquido.subtract(vlrPago[0]));

                    });


            getTtvContasAReceber().getColumns().setAll(getColId(), getColCliente_Documento(), getColDtVenda(),
                    getColModalidade(), getColSituacao(), getColDtVencimento_DtPagamento(), getColVlrPedido(),
                    getColVlrDescontos(), getColVlrBruto(), getColVlrCredDeb(), getColVlrLiquido(), getColValorPago(),
                    getColValorSaldo());

            getTtvContasAReceber().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            getTtvContasAReceber().setRoot(getPedidoTreeItem());
            getTtvContasAReceber().setShowRoot(false);
            totalizaTabela();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void escutaLista() {
        getTtvContasAReceber().setRowFactory(objectTreeTableView -> new TreeTableRow<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().removeAll(getStyleClass().stream().filter(s -> s.contains("recebimento-")).collect(Collectors.toList()));
                if (getItem() != null) {
                    String stilo = "";
                    if (item instanceof ContasAReceber) {
                        PagamentoModalidade modalidade = null;
                        BigDecimal vlrPg;
                        if (LocalDate.now().compareTo(((ContasAReceber) item).dtVencimentoProperty().getValue()) <= 0) {
                            stilo = "recebimento-pendente";
                        } else {
                            stilo = "recebimento-vencido";
                        }
                        modalidade = ((ContasAReceber) item).getRecebimentoList().stream().sorted(Comparator.comparing(Recebimento::getValor).reversed())
                                .findFirst().orElse(null).getPagamentoModalidade();
                        vlrPg = ((ContasAReceber) item).getRecebimentoList().stream()
                                .filter(recebimento -> recebimento.getPagamentoSituacao().equals(PagamentoSituacao.QUITADO))
                                .map(Recebimento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
                        if (vlrPg.compareTo(((ContasAReceber) item).valorProperty().getValue()) >= 0
                                && vlrPg.compareTo(BigDecimal.ZERO) != 0) {
                            stilo = "recebimento-pago";
                        }
                        if (modalidade != null && (modalidade.equals(PagamentoModalidade.RETIRADA)
                                || modalidade.equals(PagamentoModalidade.BONIFICACAO)
                                || modalidade.equals(PagamentoModalidade.AMOSTRA))) {
                            stilo = "recebimento-retirada";
                        } else {
                        }
                    } else if (item instanceof Recebimento) {
                        stilo = "recebimento-recebimento";
                    }
                    if (!stilo.equals(""))
                        getStyleClass().add(stilo);
                }
            }
        });

        getContasAReceberFilteredList().addListener((ListChangeListener<? super ContasAReceber>) change -> {
            preencherTabela();
        });

        chkLucroContaPagaProperty().addListener((observable -> totalizaTabela()));

        lblRegistrosLocalizadosProperty().bind(Bindings.size(getContasAReceberFilteredList()));

        dtpData1Property().addListener(observable -> aplicaFiltro());
        dtpData2Property().addListener(observable -> aplicaFiltro());
        chkDtVendaProperty().addListener(observable -> aplicaFiltro());
        empresaProperty().addListener(observable -> aplicaFiltro());
        txtPesquisaProperty().addListener(observable -> aplicaFiltro());
        pagamentoSituacaoProperty().addListener(observable -> aplicaFiltro());
        pagamentoModalidadeProperty().addListener(observable -> aplicaFiltro());

    }

    private void aplicaFiltro() {
        getContasAReceberFilteredList().setPredicate(contasAReceber -> {
            if (dtpData1Property().getValue() == null || dtpData2Property().getValue() == null)
                return true;
            if (chkDtVendaProperty().getValue()) {
                if (contasAReceber.dtCadastroProperty().getValue().toLocalDate().isBefore(dtpData1Property().getValue())
                        || contasAReceber.dtCadastroProperty().getValue().toLocalDate().isAfter(dtpData2Property().getValue()))
                    return false;
            } else {
                if (contasAReceber.dtVencimentoProperty().getValue().isBefore(dtpData1Property().getValue())
                        || contasAReceber.dtVencimentoProperty().getValue().isAfter(dtpData2Property().getValue()))
                    return false;
            }
            if (empresaProperty().getValue() != null)
                if (contasAReceber.getSaidaProduto().getCliente().idProperty().getValue() != empresaProperty().getValue().idProperty().getValue())
                    return false;

            if (pagamentoSituacaoProperty().getValue() != null)
                if (contasAReceber.getRecebimentoList().stream()
                        .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(pagamentoSituacaoProperty().getValue()))
                        .count() == 0)
                    return false;

            if (pagamentoModalidadeProperty().getValue() != null)
                if (contasAReceber.getRecebimentoList().stream()
                        .filter(recebimento -> recebimento.pagamentoModalidadeProperty().getValue().equals(pagamentoModalidade.getValue()))
                        .count() == 0)
                    return false;

            if (contasAReceber.getRecebimentoList().stream()
                    .filter(recebimento -> recebimento.documentoProperty().getValue().toLowerCase()
                            .contains(txtPesquisaProperty().getValue().toLowerCase())).count() == 0)
                return false;
            return true;
        });
    }

    public void totalizaTabela() {

        qtdClientesProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .map(ContasAReceber::getSaidaProduto)
                        .map(SaidaProduto::getCliente)
                        .distinct().count()
        );

        qtdContasProperty().setValue(
                getContasAReceberFilteredList().size()
        );
        totalContasProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .map(ContasAReceber::getSaidaProduto)
                        .map(SaidaProduto::getSaidaProdutoProdutoList)
                        .flatMap(Collection::stream)
                        .map(SaidaProdutoProduto::getVlrBruto)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        qtdContasRetiradasProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .map(ContasAReceber::getSaidaProduto)
                        .map(SaidaProduto::getSaidaProdutoProdutoList)
                        .flatMap(Collection::stream)
                        .filter(saidaProdutoProduto -> saidaProdutoProduto.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.CONSUMO))
                        .count()
        );
        totalContasRetiradasProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .map(ContasAReceber::getSaidaProduto)
                        .map(SaidaProduto::getSaidaProdutoProdutoList)
                        .flatMap(Collection::stream)
                        .filter(saidaProdutoProduto -> saidaProdutoProduto.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.CONSUMO))
                        .map(SaidaProdutoProduto::getVlrBruto)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        qtdContasDescontosProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .map(ContasAReceber::getSaidaProduto)
                        .map(SaidaProduto::getSaidaProdutoProdutoList)
                        .flatMap(Collection::stream)
                        .filter(saidaProdutoProduto -> !saidaProdutoProduto.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.CONSUMO)
                                && saidaProdutoProduto.vlrDescontoProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
                        .count()
        );
        totalContasDescontosProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .map(ContasAReceber::getSaidaProduto)
                        .map(SaidaProduto::getSaidaProdutoProdutoList)
                        .flatMap(Collection::stream)
                        .filter(saidaProdutoProduto -> !saidaProdutoProduto.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.CONSUMO)
                                && saidaProdutoProduto.vlrDescontoProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
                        .map(SaidaProdutoProduto::getVlrDesconto)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        qtdContasAReceberProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .filter(contasAReceber -> contasAReceber.valorProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
                        .count()
        );
        totalContasAReceberProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .map(ContasAReceber::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        qtdContasVencidasProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .filter(contasAReceber -> contasAReceber.dtVencimentoProperty().getValue().isBefore(LocalDate.now())
                                && (contasAReceber.valorProperty().getValue().subtract(
                                contasAReceber.getRecebimentoList().stream()
                                        .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO))
                                        .map(Recebimento::getValor)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        )).compareTo(BigDecimal.ZERO) != 0)
                        .count()
        );
        totalContasVencidasProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .filter(contasAReceber -> contasAReceber.dtVencimentoProperty().getValue().isBefore(LocalDate.now()))
                        .map(ContasAReceber::getRecebimentoList)
                        .flatMap(Collection::stream)
                        .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.PENDENTE))
                        .map(Recebimento::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        qtdContasPendentesProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .filter(contasAReceber -> contasAReceber.dtVencimentoProperty().getValue().isAfter(LocalDate.now()))
                        .map(ContasAReceber::getRecebimentoList)
                        .flatMap(Collection::stream)
                        .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.PENDENTE))
                        .count()
        );
        totalContasPendentesProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .filter(contasAReceber -> contasAReceber.dtVencimentoProperty().getValue().isAfter(LocalDate.now()))
                        .map(ContasAReceber::getRecebimentoList)
                        .flatMap(Collection::stream)
                        .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.PENDENTE))
                        .map(Recebimento::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        qtdContasPagasProperty().setValue(
                getContasAReceberFilteredList().stream().filter(
                        contasAReceber -> contasAReceber.valorProperty().getValue().compareTo(BigDecimal.ZERO) > 0
                                && contasAReceber.valorProperty().getValue()
                                .compareTo(contasAReceber.getRecebimentoList().stream()
                                        .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO))
                                        .map(Recebimento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add)
                                )
                                <= 0).count()
        );
        totalContasPagasProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .map(ContasAReceber::getRecebimentoList)
                        .flatMap(Collection::stream)
                        .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO))
                        .map(Recebimento::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        qtdContasSaldoClientesProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .filter(contasAReceber -> contasAReceber.vlrSaldoProperty().getValue().compareTo(BigDecimal.ZERO) < 0)
                        .count()
        );
        totalContasSaldoClientesProperty().setValue(
                getContasAReceberFilteredList().stream()
                        .filter(contasAReceber -> contasAReceber.vlrSaldoProperty().getValue().compareTo(BigDecimal.ZERO) < 0)
                        .map(ContasAReceber::getVlrSaldo)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        List<ContasAReceber> contasAReceberList = new ArrayList<>();
        BigDecimal totalConta, totalDesc, totalReti;
        if (chkLucroContaPagaProperty().getValue()) {
            contasAReceberList = getContasAReceberFilteredList().stream()
                    .filter(contasAReceber -> contasAReceber.getRecebimentoList().stream()
                            .filter(recebimento -> recebimento.pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO))
                            .count() > 0).collect(Collectors.toList());
            totalConta = totalContasPagasProperty().getValue();
            totalDesc = contasAReceberList.stream()
                    .map(ContasAReceber::getSaidaProduto)
                    .map(SaidaProduto::getSaidaProdutoProdutoList)
                    .flatMap(Collection::stream)
                    .filter(saidaProdutoProduto -> !saidaProdutoProduto.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.CONSUMO)
                            && saidaProdutoProduto.vlrDescontoProperty().getValue().compareTo(BigDecimal.ZERO) > 0)
                    .map(SaidaProdutoProduto::getVlrDesconto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            totalReti = contasAReceberList.stream()
                    .map(ContasAReceber::getSaidaProduto)
                    .map(SaidaProduto::getSaidaProdutoProdutoList)
                    .flatMap(Collection::stream)
                    .filter(saidaProdutoProduto -> saidaProdutoProduto.codigoCFOPProperty().getValue().equals(TipoCodigoCFOP.CONSUMO))
                    .map(SaidaProdutoProduto::getVlrBruto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            contasAReceberList = getContasAReceberFilteredList();
            totalConta = totalContasProperty().getValue();
            totalDesc = totalContasDescontosProperty().getValue();
            totalReti = totalContasRetiradasProperty().getValue();
        }

        totalLucroBrutoProperty().setValue(
                contasAReceberList.stream()
                        .map(ContasAReceber::getSaidaProduto)
                        .map(SaidaProduto::getSaidaProdutoProdutoList)
                        .flatMap(Collection::stream)
                        .map(saidaProdutoProduto -> saidaProdutoProduto.vlrBrutoProperty().getValue()
                                .subtract(saidaProdutoProduto.vlrEntradaBrutoProperty().getValue()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        try {
            percLucroBrutoProperty().setValue(
                    totalLucroBrutoProperty().getValue().scaleByPowerOfTen(2)
                            .divide(totalConta, 4, RoundingMode.HALF_UP)
            );
        } catch (Exception ex) {
            if (ex instanceof ArithmeticException)
                percLucroBrutoProperty().setValue(BigDecimal.ZERO);
        }


        totalLucroLiquidoProperty().setValue(
                totalLucroBrutoProperty().getValue().subtract(
                        (totalDesc.add(totalReti))
                ));
        try {
            percLucroLiquidoProperty().setValue(
                    totalLucroLiquidoProperty().getValue().scaleByPowerOfTen(2)
                            .divide(totalConta, 4, RoundingMode.HALF_UP)
            );
        } catch (Exception ex) {
            if (ex instanceof ArithmeticException)
                percLucroLiquidoProperty().setValue(BigDecimal.ZERO);
        }

    }

    /**
     * END voids
     */


    /**
     * Begin Getters e Setters
     */

    public TablePosition getTp() {
        return tp;
    }

    public void setTp(TablePosition tp) {
        this.tp = tp;
    }

    public String getTxtPesquisa() {
        return txtPesquisa.get();
    }

    public StringProperty txtPesquisaProperty() {
        return txtPesquisa;
    }

    public void setTxtPesquisa(String txtPesquisa) {
        this.txtPesquisa.set(txtPesquisa);
    }

    public int getLblRegistrosLocalizados() {
        return lblRegistrosLocalizados.get();
    }

    public IntegerProperty lblRegistrosLocalizadosProperty() {
        return lblRegistrosLocalizados;
    }

    public void setLblRegistrosLocalizados(int lblRegistrosLocalizados) {
        this.lblRegistrosLocalizados.set(lblRegistrosLocalizados);
    }

    public TreeTableView<Object> getTtvContasAReceber() {
        return ttvContasAReceber;
    }

    public void setTtvContasAReceber(TreeTableView<Object> ttvContasAReceber) {
        this.ttvContasAReceber = ttvContasAReceber;
    }

    public FilteredList<ContasAReceber> getContasAReceberFilteredList() {
        return contasAReceberFilteredList;
    }

    public void setContasAReceberFilteredList(FilteredList<ContasAReceber> contasAReceberFilteredList) {
        this.contasAReceberFilteredList = contasAReceberFilteredList;
    }

    public LocalDate getDtpData1() {
        return dtpData1.get();
    }

    public ObjectProperty<LocalDate> dtpData1Property() {
        return dtpData1;
    }

    public void setDtpData1(LocalDate dtpData1) {
        this.dtpData1.set(dtpData1);
    }

    public LocalDate getDtpData2() {
        return dtpData2.get();
    }

    public ObjectProperty<LocalDate> dtpData2Property() {
        return dtpData2;
    }

    public void setDtpData2(LocalDate dtpData2) {
        this.dtpData2.set(dtpData2);
    }

    public boolean isChkDtVenda() {
        return chkDtVenda.get();
    }

    public BooleanProperty chkDtVendaProperty() {
        return chkDtVenda;
    }

    public void setChkDtVenda(boolean chkDtVenda) {
        this.chkDtVenda.set(chkDtVenda);
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

    public PagamentoSituacao getPagamentoSituacao() {
        return pagamentoSituacao.get();
    }

    public ObjectProperty<PagamentoSituacao> pagamentoSituacaoProperty() {
        return pagamentoSituacao;
    }

    public void setPagamentoSituacao(PagamentoSituacao pagamentoSituacao) {
        this.pagamentoSituacao.set(pagamentoSituacao);
    }

    public PagamentoModalidade getPagamentoModalidade() {
        return pagamentoModalidade.get();
    }

    public ObjectProperty<PagamentoModalidade> pagamentoModalidadeProperty() {
        return pagamentoModalidade;
    }

    public void setPagamentoModalidade(PagamentoModalidade pagamentoModalidade) {
        this.pagamentoModalidade.set(pagamentoModalidade);
    }

    public TreeItem<Object> getPedidoTreeItem() {
        return pedidoTreeItem;
    }

    public void setPedidoTreeItem(TreeItem<Object> pedidoTreeItem) {
        this.pedidoTreeItem = pedidoTreeItem;
    }

    public TreeTableColumn<Object, Long> getColId() {
        return colId;
    }

    public void setColId(TreeTableColumn<Object, Long> colId) {
        this.colId = colId;
    }

    public TreeTableColumn<Object, String> getColCliente_Documento() {
        return colCliente_Documento;
    }

    public void setColCliente_Documento(TreeTableColumn<Object, String> colCliente_Documento) {
        this.colCliente_Documento = colCliente_Documento;
    }

    public TreeTableColumn<Object, LocalDate> getColDtVenda() {
        return colDtVenda;
    }

    public void setColDtVenda(TreeTableColumn<Object, LocalDate> colDtVenda) {
        this.colDtVenda = colDtVenda;
    }

    public TreeTableColumn<Object, String> getColModalidade() {
        return colModalidade;
    }

    public void setColModalidade(TreeTableColumn<Object, String> colModalidade) {
        this.colModalidade = colModalidade;
    }

    public TreeTableColumn<Object, String> getColSituacao() {
        return colSituacao;
    }

    public void setColSituacao(TreeTableColumn<Object, String> colSituacao) {
        this.colSituacao = colSituacao;
    }

    public TreeTableColumn<Object, LocalDate> getColDtVencimento_DtPagamento() {
        return colDtVencimento_DtPagamento;
    }

    public void setColDtVencimento_DtPagamento(TreeTableColumn<Object, LocalDate> colDtVencimento_DtPagamento) {
        this.colDtVencimento_DtPagamento = colDtVencimento_DtPagamento;
    }

    public TreeTableColumn<Object, String> getColVlrBruto() {
        return colVlrBruto;
    }

    public void setColVlrBruto(TreeTableColumn<Object, String> colVlrBruto) {
        this.colVlrBruto = colVlrBruto;
    }

    public TreeTableColumn<Object, String> getColVlrCredDeb() {
        return colVlrCredDeb;
    }

    public void setColVlrCredDeb(TreeTableColumn<Object, String> colVlrCredDeb) {
        this.colVlrCredDeb = colVlrCredDeb;
    }

    public TreeTableColumn<Object, String> getColVlrLiquido() {
        return colVlrLiquido;
    }

    public void setColVlrLiquido(TreeTableColumn<Object, String> colVlrLiquido) {
        this.colVlrLiquido = colVlrLiquido;
    }

    public TreeTableColumn<Object, String> getColValorPago() {
        return colValorPago;
    }

    public void setColValorPago(TreeTableColumn<Object, String> colValorPago) {
        this.colValorPago = colValorPago;
    }

    public TreeTableColumn<Object, String> getColValorSaldo() {
        return colValorSaldo;
    }

    public void setColValorSaldo(TreeTableColumn<Object, String> colValorSaldo) {
        this.colValorSaldo = colValorSaldo;
    }

    public TreeTableColumn<Object, String> getColUsuario() {
        return colUsuario;
    }

    public void setColUsuario(TreeTableColumn<Object, String> colUsuario) {
        this.colUsuario = colUsuario;
    }

    public int getQtdClientes() {
        return qtdClientes.get();
    }

    public IntegerProperty qtdClientesProperty() {
        return qtdClientes;
    }

    public void setQtdClientes(int qtdClientes) {
        this.qtdClientes.set(qtdClientes);
    }

    public int getQtdContas() {
        return qtdContas.get();
    }

    public IntegerProperty qtdContasProperty() {
        return qtdContas;
    }

    public void setQtdContas(int qtdContas) {
        this.qtdContas.set(qtdContas);
    }

    public BigDecimal getTotalContas() {
        return totalContas.get();
    }

    public ObjectProperty<BigDecimal> totalContasProperty() {
        return totalContas;
    }

    public void setTotalContas(BigDecimal totalContas) {
        this.totalContas.set(totalContas);
    }

    public int getQtdContasRetiradas() {
        return qtdContasRetiradas.get();
    }

    public IntegerProperty qtdContasRetiradasProperty() {
        return qtdContasRetiradas;
    }

    public void setQtdContasRetiradas(int qtdContasRetiradas) {
        this.qtdContasRetiradas.set(qtdContasRetiradas);
    }

    public BigDecimal getTotalContasRetiradas() {
        return totalContasRetiradas.get();
    }

    public ObjectProperty<BigDecimal> totalContasRetiradasProperty() {
        return totalContasRetiradas;
    }

    public void setTotalContasRetiradas(BigDecimal totalContasRetiradas) {
        this.totalContasRetiradas.set(totalContasRetiradas);
    }

    public int getQtdContasDescontos() {
        return qtdContasDescontos.get();
    }

    public IntegerProperty qtdContasDescontosProperty() {
        return qtdContasDescontos;
    }

    public void setQtdContasDescontos(int qtdContasDescontos) {
        this.qtdContasDescontos.set(qtdContasDescontos);
    }

    public BigDecimal getTotalContasDescontos() {
        return totalContasDescontos.get();
    }

    public ObjectProperty<BigDecimal> totalContasDescontosProperty() {
        return totalContasDescontos;
    }

    public void setTotalContasDescontos(BigDecimal totalContasDescontos) {
        this.totalContasDescontos.set(totalContasDescontos);
    }

    public int getQtdContasAReceber() {
        return qtdContasAReceber.get();
    }

    public IntegerProperty qtdContasAReceberProperty() {
        return qtdContasAReceber;
    }

    public void setQtdContasAReceber(int qtdContasAReceber) {
        this.qtdContasAReceber.set(qtdContasAReceber);
    }

    public BigDecimal getTotalContasAReceber() {
        return totalContasAReceber.get();
    }

    public ObjectProperty<BigDecimal> totalContasAReceberProperty() {
        return totalContasAReceber;
    }

    public void setTotalContasAReceber(BigDecimal totalContasAReceber) {
        this.totalContasAReceber.set(totalContasAReceber);
    }

    public int getQtdContasVencidas() {
        return qtdContasVencidas.get();
    }

    public IntegerProperty qtdContasVencidasProperty() {
        return qtdContasVencidas;
    }

    public void setQtdContasVencidas(int qtdContasVencidas) {
        this.qtdContasVencidas.set(qtdContasVencidas);
    }

    public BigDecimal getTotalContasVencidas() {
        return totalContasVencidas.get();
    }

    public ObjectProperty<BigDecimal> totalContasVencidasProperty() {
        return totalContasVencidas;
    }

    public void setTotalContasVencidas(BigDecimal totalContasVencidas) {
        this.totalContasVencidas.set(totalContasVencidas);
    }

    public int getQtdContasPendentes() {
        return qtdContasPendentes.get();
    }

    public IntegerProperty qtdContasPendentesProperty() {
        return qtdContasPendentes;
    }

    public void setQtdContasPendentes(int qtdContasPendentes) {
        this.qtdContasPendentes.set(qtdContasPendentes);
    }

    public BigDecimal getTotalContasPendentes() {
        return totalContasPendentes.get();
    }

    public ObjectProperty<BigDecimal> totalContasPendentesProperty() {
        return totalContasPendentes;
    }

    public void setTotalContasPendentes(BigDecimal totalContasPendentes) {
        this.totalContasPendentes.set(totalContasPendentes);
    }

    public int getQtdContasPagas() {
        return qtdContasPagas.get();
    }

    public IntegerProperty qtdContasPagasProperty() {
        return qtdContasPagas;
    }

    public void setQtdContasPagas(int qtdContasPagas) {
        this.qtdContasPagas.set(qtdContasPagas);
    }

    public BigDecimal getTotalContasPagas() {
        return totalContasPagas.get();
    }

    public ObjectProperty<BigDecimal> totalContasPagasProperty() {
        return totalContasPagas;
    }

    public void setTotalContasPagas(BigDecimal totalContasPagas) {
        this.totalContasPagas.set(totalContasPagas);
    }

    public int getQtdContasSaldoClientes() {
        return qtdContasSaldoClientes.get();
    }

    public IntegerProperty qtdContasSaldoClientesProperty() {
        return qtdContasSaldoClientes;
    }

    public void setQtdContasSaldoClientes(int qtdContasSaldoClientes) {
        this.qtdContasSaldoClientes.set(qtdContasSaldoClientes);
    }

    public BigDecimal getTotalContasSaldoClientes() {
        return totalContasSaldoClientes.get();
    }

    public ObjectProperty<BigDecimal> totalContasSaldoClientesProperty() {
        return totalContasSaldoClientes;
    }

    public void setTotalContasSaldoClientes(BigDecimal totalContasSaldoClientes) {
        this.totalContasSaldoClientes.set(totalContasSaldoClientes);
    }

    public BigDecimal getPercLucroBruto() {
        return percLucroBruto.get();
    }

    public ObjectProperty<BigDecimal> percLucroBrutoProperty() {
        return percLucroBruto;
    }

    public void setPercLucroBruto(BigDecimal percLucroBruto) {
        this.percLucroBruto.set(percLucroBruto);
    }

    public BigDecimal getTotalLucroBruto() {
        return totalLucroBruto.get();
    }

    public ObjectProperty<BigDecimal> totalLucroBrutoProperty() {
        return totalLucroBruto;
    }

    public void setTotalLucroBruto(BigDecimal totalLucroBruto) {
        this.totalLucroBruto.set(totalLucroBruto);
    }

    public BigDecimal getPercLucroLiquido() {
        return percLucroLiquido.get();
    }

    public ObjectProperty<BigDecimal> percLucroLiquidoProperty() {
        return percLucroLiquido;
    }

    public void setPercLucroLiquido(BigDecimal percLucroLiquido) {
        this.percLucroLiquido.set(percLucroLiquido);
    }

    public BigDecimal getTotalLucroLiquido() {
        return totalLucroLiquido.get();
    }

    public ObjectProperty<BigDecimal> totalLucroLiquidoProperty() {
        return totalLucroLiquido;
    }

    public void setTotalLucroLiquido(BigDecimal totalLucroLiquido) {
        this.totalLucroLiquido.set(totalLucroLiquido);
    }

    public TreeTableColumn<Object, String> getColVlrPedido() {
        return colVlrPedido;
    }

    public void setColVlrPedido(TreeTableColumn<Object, String> colVlrPedido) {
        this.colVlrPedido = colVlrPedido;
    }

    public TreeTableColumn<Object, String> getColVlrDescontos() {
        return colVlrDescontos;
    }

    public void setColVlrDescontos(TreeTableColumn<Object, String> colVlrDescontos) {
        this.colVlrDescontos = colVlrDescontos;
    }

    public boolean isChkLucroContaPaga() {
        return chkLucroContaPaga.get();
    }

    public BooleanProperty chkLucroContaPagaProperty() {
        return chkLucroContaPaga;
    }

    public void setChkLucroContaPaga(boolean chkLucroContaPaga) {
        this.chkLucroContaPaga.set(chkLucroContaPaga);
    }

    /**
     * END Getters e Setters
     */
}
