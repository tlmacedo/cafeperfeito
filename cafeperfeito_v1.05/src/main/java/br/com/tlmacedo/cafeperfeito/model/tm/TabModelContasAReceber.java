package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.model.vo.ContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoSituacao;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATA;
import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATAHORA;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-08
 * Time: 14:37
 */

public class TabModelContasAReceber {

    private Label lblRegistrosLocalizados;
    private JFXDatePicker dtpPesq1, dtpPesq2;
    private JFXCheckBox chkDtVenda;
    private JFXTextField txtPesquisaContasAReceber;
    private TreeTableView<ContasAReceber> ttvContasAReceber;
    private ObservableList<ContasAReceber> contasAReceberObservableList;
    private FilteredList<ContasAReceber> contasAReceberFilteredList;

    private TreeTableColumn<ContasAReceber, Long> colunaId;
    private TreeTableColumn<ContasAReceber, String> colunaCliente;
    private TreeTableColumn<ContasAReceber, LocalDateTime> colunaDataVenda;
    private TreeTableColumn<ContasAReceber, LocalDate> colunaDataVencimento;
    private TreeTableColumn<ContasAReceber, PagamentoSituacao> colunaPagamentoSituacao;
    private TreeTableColumn<ContasAReceber, String> colunaSaidaProduto_id;
    private TreeTableColumn<ContasAReceber, BigDecimal> colunaValor;

    public TabModelContasAReceber() {
    }

    public Label getLblRegistrosLocalizados() {
        return lblRegistrosLocalizados;
    }

    public void setLblRegistrosLocalizados(Label lblRegistrosLocalizados) {
        this.lblRegistrosLocalizados = lblRegistrosLocalizados;
    }

    public JFXDatePicker getDtpPesq1() {
        return dtpPesq1;
    }

    public void setDtpPesq1(JFXDatePicker dtpPesq1) {
        this.dtpPesq1 = dtpPesq1;
    }

    public JFXDatePicker getDtpPesq2() {
        return dtpPesq2;
    }

    public void setDtpPesq2(JFXDatePicker dtpPesq2) {
        this.dtpPesq2 = dtpPesq2;
    }

    public JFXCheckBox getChkDtVenda() {
        return chkDtVenda;
    }

    public void setChkDtVenda(JFXCheckBox chkDtVenda) {
        this.chkDtVenda = chkDtVenda;
    }

    public JFXTextField getTxtPesquisaContasAReceber() {
        return txtPesquisaContasAReceber;
    }

    public void setTxtPesquisaContasAReceber(JFXTextField txtPesquisaContasAReceber) {
        this.txtPesquisaContasAReceber = txtPesquisaContasAReceber;
    }

    public TreeTableView<ContasAReceber> getTtvContasAReceber() {
        return ttvContasAReceber;
    }

    public void setTtvContasAReceber(TreeTableView<ContasAReceber> ttvContasAReceber) {
        this.ttvContasAReceber = ttvContasAReceber;
    }

    public ObservableList<ContasAReceber> getContasAReceberObservableList() {
        return contasAReceberObservableList;
    }

    public void setContasAReceberObservableList(ObservableList<ContasAReceber> contasAReceberObservableList) {
        this.contasAReceberObservableList = contasAReceberObservableList;
    }

    public void setContasAReceberObservableList(ContasAReceber oldValue, ContasAReceber newValue) {
        contasAReceberObservableList.set(
                contasAReceberObservableList.indexOf(oldValue),
                newValue
        );
    }

    public FilteredList<ContasAReceber> getContasAReceberFilteredList() {
        return contasAReceberFilteredList;
    }

    public void setContasAReceberFilteredList(FilteredList<ContasAReceber> contasAReceberFilteredList) {
        this.contasAReceberFilteredList = contasAReceberFilteredList;
    }

    public TreeTableColumn<ContasAReceber, Long> getColunaId() {
        return colunaId;
    }

    public void setColunaId(TreeTableColumn<ContasAReceber, Long> colunaId) {
        this.colunaId = colunaId;
    }

    public TreeTableColumn<ContasAReceber, String> getColunaCliente() {
        return colunaCliente;
    }

    public void setColunaCliente(TreeTableColumn<ContasAReceber, String> colunaCliente) {
        this.colunaCliente = colunaCliente;
    }

    public TreeTableColumn<ContasAReceber, LocalDateTime> getColunaDataVenda() {
        return colunaDataVenda;
    }

    public void setColunaDataVenda(TreeTableColumn<ContasAReceber, LocalDateTime> colunaDataVenda) {
        this.colunaDataVenda = colunaDataVenda;
    }

    public TreeTableColumn<ContasAReceber, LocalDate> getColunaDataVencimento() {
        return colunaDataVencimento;
    }

    public void setColunaDataVencimento(TreeTableColumn<ContasAReceber, LocalDate> colunaDataVencimento) {
        this.colunaDataVencimento = colunaDataVencimento;
    }

    public TreeTableColumn<ContasAReceber, PagamentoSituacao> getColunaPagamentoSituacao() {
        return colunaPagamentoSituacao;
    }

    public void setColunaPagamentoSituacao(TreeTableColumn<ContasAReceber, PagamentoSituacao> colunaPagamentoSituacao) {
        this.colunaPagamentoSituacao = colunaPagamentoSituacao;
    }

    public TreeTableColumn<ContasAReceber, String> getColunaSaidaProduto_id() {
        return colunaSaidaProduto_id;
    }

    public void setColunaSaidaProduto_id(TreeTableColumn<ContasAReceber, String> colunaSaidaProduto_id) {
        this.colunaSaidaProduto_id = colunaSaidaProduto_id;
    }

    public TreeTableColumn<ContasAReceber, BigDecimal> getColunaValor() {
        return colunaValor;
    }

    public void setColunaValor(TreeTableColumn<ContasAReceber, BigDecimal> colunaValor) {
        this.colunaValor = colunaValor;
    }

    @SuppressWarnings("Duplicates")
    public void tabela() {
        try {

            Label lblId = new Label("id");
            lblId.setPrefWidth(48);
            colunaId = new TreeTableColumn<ContasAReceber, Long>();
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(lblId.getPrefWidth());
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> param.getValue().getValue().idProperty().asObject());

            Label lblCliente = new Label("Cliente");
            lblCliente.setPrefWidth(250);
            colunaCliente = new TreeTableColumn<ContasAReceber, String>();
            colunaCliente.setGraphic(lblCliente);
            colunaCliente.setPrefWidth(lblCliente.getPrefWidth());
            colunaCliente.setStyle("-fx-alignment: center-left;");
            colunaCliente.setCellValueFactory(param ->
                    new SimpleStringProperty(String.format("%s (%s)",
                            param.getValue().getValue().getSaidaProduto().getCliente().razaoProperty().get(),
                            param.getValue().getValue().getSaidaProduto().getCliente().fantasiaProperty().get())
                    ));

            Label lblDtVenda = new Label("Dt. Venda");
            lblDtVenda.setPrefWidth(120);
            colunaDataVenda = new TreeTableColumn<ContasAReceber, LocalDateTime>();
            colunaDataVenda.setPrefWidth(lblDtVenda.getPrefWidth());
            colunaDataVenda.setGraphic(lblDtVenda);
            colunaDataVenda.setStyle("-fx-alignment: center-right;");
            colunaDataVenda.setCellValueFactory(param -> param.getValue().getValue().dtCadastroProperty());
            colunaDataVenda.setCellFactory(param -> new TreeTableCell<>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty)
                        setText(null);
                    else
                        setText(String.format(item.format(DTF_DATAHORA)));
                }
            });

            Label lblDtVencimento = new Label("Dt. Vencimento");
            lblDtVencimento.setPrefWidth(100);
            colunaDataVencimento = new TreeTableColumn<ContasAReceber, LocalDate>();
            colunaDataVencimento.setPrefWidth(lblDtVencimento.getPrefWidth());
            colunaDataVencimento.setGraphic(lblDtVencimento);
            colunaDataVencimento.setStyle("-fx-alignment: center-right;");
            colunaDataVencimento.setCellValueFactory(param -> param.getValue().getValue().dtVencimentoProperty());
            colunaDataVencimento.setCellFactory(param -> new TreeTableCell<>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty)
                        setText(null);
                    else
                        setText(String.format(item.format(DTF_DATA)));
                }
            });

            Label lblPagamentoSituacao = new Label("Situação");
            lblPagamentoSituacao.setPrefWidth(60);
            colunaPagamentoSituacao = new TreeTableColumn<ContasAReceber, PagamentoSituacao>();
            colunaPagamentoSituacao.setGraphic(lblPagamentoSituacao);
            colunaPagamentoSituacao.setPrefWidth(lblPagamentoSituacao.getPrefWidth());
            colunaPagamentoSituacao.setStyle("-fx-alignment: center-left;");
            colunaPagamentoSituacao.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getValue().getPagamentoSituacao()));

            Label lblValor = new Label("Valor");
            lblValor.setPrefWidth(100);
            colunaValor = new TreeTableColumn<ContasAReceber, BigDecimal>();
            colunaValor.setPrefWidth(lblValor.getPrefWidth());
            colunaValor.setGraphic(lblValor);
            colunaValor.setStyle("-fx-alignment: center-right;");
            colunaValor.setCellValueFactory(param -> param.getValue().getValue().valorProperty());
//            colunaValor.setCellValueFactory(param ->
//                    new SimpleObjectProperty<>(param.getValue().getValue().getSaidaProduto().getSaidaProdutoProdutoList().stream()
//                            .map(SaidaProdutoProduto::getVlrLiquido).reduce(BigDecimal.ZERO, BigDecimal::add)));
            colunaValor.setCellFactory(param -> new TreeTableCell<>() {
                @Override
                protected void updateItem(BigDecimal item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty)
                        setText("");
                    else
                        setText(ServiceMascara.getMoeda(item.setScale(2).toString(), 2));
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void escutaLista() {

        ttvContasAReceber.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER)
                    event.consume();
            }
        });

        contasAReceberFilteredList.addListener((ListChangeListener<? super ContasAReceber>) c -> {
            int qtd = getContasAReceberFilteredList().size();

            lblRegistrosLocalizados.setText(String.format("%-4d registro%2$s localizado%2$s.",
                    qtd,
                    qtd > 1 ? "s" : ""
            ));
        });

        dtpPesq1.valueProperty().addListener((ov, o, n) -> {
            if (dtpPesq2 == null)
                if (dtpPesq2.getValue().compareTo(n) < 0)
                    dtpPesq2.setValue(n);
            pesquisa();
        });
        dtpPesq2.valueProperty().addListener((ov, o, n) -> {
            if (dtpPesq1.getValue().compareTo(n) > 0)
                dtpPesq1.setValue(n);
            pesquisa();
        });
        txtPesquisaContasAReceber.textProperty().addListener((ov, o, n) -> pesquisa());
        chkDtVenda.selectedProperty().addListener((ov, o, n) -> pesquisa());
    }

    public void preencherTabela() {
        final TreeItem<ContasAReceber> root = new RecursiveTreeItem<ContasAReceber>(contasAReceberFilteredList, RecursiveTreeObject::getChildren);
        ttvContasAReceber
                .getColumns()
                .setAll(
                        getColunaId(),
                        getColunaCliente(),
                        getColunaDataVenda(),
                        getColunaDataVencimento(),
                        getColunaPagamentoSituacao(),
                        getColunaValor()
                );
        ttvContasAReceber.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ttvContasAReceber.setRoot(root);
        ttvContasAReceber.setShowRoot(false);

        int qtd = contasAReceberFilteredList.size();
        lblRegistrosLocalizados.setText(String.format("%-4d registro%2$s localizado%2$s.",
                qtd,
                qtd > 1 ? "s" : ""
        ));
    }

    public void pesquisa() {
        try {
            String strBusca = getTxtPesquisaContasAReceber().getText().toLowerCase().trim();
            contasAReceberFilteredList.setPredicate(contasAReceber -> {
                if (dtpPesq1.getValue() == null || dtpPesq2.getValue() == null)
                    return true;
                if (chkDtVenda.isSelected()) {
                    if (contasAReceber.getSaidaProduto().dtCadastroProperty().get().toLocalDate().compareTo(dtpPesq1.getValue()) < 0
                            || contasAReceber.getSaidaProduto().dtCadastroProperty().get().toLocalDate().compareTo(dtpPesq2.getValue()) > 0)
                        return false;
                } else {
                    if (contasAReceber.dtVencimentoProperty().get().compareTo(dtpPesq1.getValue()) < 0
                            || contasAReceber.dtVencimentoProperty().get().compareTo(dtpPesq2.getValue()) > 0)
                        return false;
                }
                if (contasAReceber.getSaidaProduto().getCliente().cnpjProperty().get().contains(strBusca)) return true;
                if (contasAReceber.getSaidaProduto().getCliente().razaoProperty().get().toLowerCase().contains(strBusca))
                    return true;
                if (contasAReceber.getSaidaProduto().getCliente().fantasiaProperty().get().toLowerCase().contains(strBusca))
                    return true;
                return false;
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}