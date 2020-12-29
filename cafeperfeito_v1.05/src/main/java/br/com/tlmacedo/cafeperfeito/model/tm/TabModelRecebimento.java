package br.com.tlmacedo.cafeperfeito.model.tm;

import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoSituacao;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoTipo;
import br.com.tlmacedo.cafeperfeito.service.FormatCell.SetCellFactoryTableCell_DatePicker;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATA;
import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATAHORA;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-20
 * Time: 13:17
 */

public class TabModelRecebimento {

    private TableView<Recebimento> tvRecebimento;
    private ObservableList<Recebimento> recebimentoObservableList = FXCollections.observableArrayList();

    private TableColumn<Recebimento, String> colunaId;
    private TableColumn<Recebimento, String> colunaUsuarioCadastro;
    private TableColumn<Recebimento, String> colunaDtCadastro;
    private TableColumn<Recebimento, PagamentoTipo> colunaPagamentoTipo;
    private TableColumn<Recebimento, String> colunaDocumento;
    private TableColumn<Recebimento, String> colunaValor;
    private TableColumn<Recebimento, PagamentoSituacao> colunaPagamentoSituacao;
    private TableColumn<Recebimento, String> colunaParcela;
    private TableColumn<Recebimento, String> colunaParcelas;
    private TableColumn<Recebimento, String> colunaDtVencimento;
    private TableColumn<Recebimento, String> colunaUsuarioPagamento;
    private TableColumn<Recebimento, LocalDate> colunaDtPagamento;
    private TableColumn<Recebimento, String> colunaValorRecebido;
    private TableColumn<Recebimento, String> colunaUsuarioAtualizacao;
    private TableColumn<Recebimento, String> colunaDtAtualizacao;

    public TabModelRecebimento() {
    }

    public TableView<Recebimento> getTvRecebimento() {
        return tvRecebimento;
    }

    public void setTvRecebimento(TableView<Recebimento> tvRecebimento) {
        this.tvRecebimento = tvRecebimento;
    }

    public ObservableList<Recebimento> getRecebimentoObservableList() {
        return recebimentoObservableList;
    }

    public void setRecebimentoObservableList(ObservableList<Recebimento> recebimentoObservableList) {
        this.recebimentoObservableList = recebimentoObservableList;
    }

    public TableColumn<Recebimento, String> getColunaId() {
        return colunaId;
    }

    public void setColunaId(TableColumn<Recebimento, String> colunaId) {
        this.colunaId = colunaId;
    }

    public TableColumn<Recebimento, String> getColunaUsuarioCadastro() {
        return colunaUsuarioCadastro;
    }

    public void setColunaUsuarioCadastro(TableColumn<Recebimento, String> colunaUsuarioCadastro) {
        this.colunaUsuarioCadastro = colunaUsuarioCadastro;
    }

    public TableColumn<Recebimento, String> getColunaDtCadastro() {
        return colunaDtCadastro;
    }

    public void setColunaDtCadastro(TableColumn<Recebimento, String> colunaDtCadastro) {
        this.colunaDtCadastro = colunaDtCadastro;
    }

    public TableColumn<Recebimento, PagamentoTipo> getColunaPagamentoTipo() {
        return colunaPagamentoTipo;
    }

    public void setColunaPagamentoTipo(TableColumn<Recebimento, PagamentoTipo> colunaPagamentoTipo) {
        this.colunaPagamentoTipo = colunaPagamentoTipo;
    }

    public TableColumn<Recebimento, String> getColunaDocumento() {
        return colunaDocumento;
    }

    public void setColunaDocumento(TableColumn<Recebimento, String> colunaDocumento) {
        this.colunaDocumento = colunaDocumento;
    }

    public TableColumn<Recebimento, String> getColunaValor() {
        return colunaValor;
    }

    public void setColunaValor(TableColumn<Recebimento, String> colunaValor) {
        this.colunaValor = colunaValor;
    }

    public TableColumn<Recebimento, PagamentoSituacao> getColunaPagamentoSituacao() {
        return colunaPagamentoSituacao;
    }

    public void setColunaPagamentoSituacao(TableColumn<Recebimento, PagamentoSituacao> colunaPagamentoSituacao) {
        this.colunaPagamentoSituacao = colunaPagamentoSituacao;
    }

    public TableColumn<Recebimento, String> getColunaParcela() {
        return colunaParcela;
    }

    public void setColunaParcela(TableColumn<Recebimento, String> colunaParcela) {
        this.colunaParcela = colunaParcela;
    }

    public TableColumn<Recebimento, String> getColunaParcelas() {
        return colunaParcelas;
    }

    public void setColunaParcelas(TableColumn<Recebimento, String> colunaParcelas) {
        this.colunaParcelas = colunaParcelas;
    }

    public TableColumn<Recebimento, String> getColunaDtVencimento() {
        return colunaDtVencimento;
    }

    public void setColunaDtVencimento(TableColumn<Recebimento, String> colunaDtVencimento) {
        this.colunaDtVencimento = colunaDtVencimento;
    }

    public TableColumn<Recebimento, String> getColunaUsuarioPagamento() {
        return colunaUsuarioPagamento;
    }

    public void setColunaUsuarioPagamento(TableColumn<Recebimento, String> colunaUsuarioPagamento) {
        this.colunaUsuarioPagamento = colunaUsuarioPagamento;
    }

    public TableColumn<Recebimento, LocalDate> getColunaDtPagamento() {
        return colunaDtPagamento;
    }

    public void setColunaDtPagamento(TableColumn<Recebimento, LocalDate> colunaDtPagamento) {
        this.colunaDtPagamento = colunaDtPagamento;
    }

    public TableColumn<Recebimento, String> getColunaValorRecebido() {
        return colunaValorRecebido;
    }

    public void setColunaValorRecebido(TableColumn<Recebimento, String> colunaValorRecebido) {
        this.colunaValorRecebido = colunaValorRecebido;
    }

    public TableColumn<Recebimento, String> getColunaUsuarioAtualizacao() {
        return colunaUsuarioAtualizacao;
    }

    public void setColunaUsuarioAtualizacao(TableColumn<Recebimento, String> colunaUsuarioAtualizacao) {
        this.colunaUsuarioAtualizacao = colunaUsuarioAtualizacao;
    }

    public TableColumn<Recebimento, String> getColunaDtAtualizacao() {
        return colunaDtAtualizacao;
    }

    public void setColunaDtAtualizacao(TableColumn<Recebimento, String> colunaDtAtualizacao) {
        this.colunaDtAtualizacao = colunaDtAtualizacao;
    }

    @SuppressWarnings("Duplicates")
    public void tabela() {
        try {
            Label lblId = new Label("id");
            lblId.setPrefWidth(40);
            colunaId = new TableColumn<Recebimento, String>();
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(lblId.getPrefWidth());
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> param.getValue().idProperty().asString());

            Label lblUsuarioCadastro = new Label("Usuário");
            lblUsuarioCadastro.setPrefWidth(70);
            colunaUsuarioCadastro = new TableColumn<Recebimento, String>();
            colunaUsuarioCadastro.setGraphic(lblUsuarioCadastro);
            colunaUsuarioCadastro.setPrefWidth(lblUsuarioCadastro.getPrefWidth());
            colunaUsuarioCadastro.setStyle("-fx-alignment: center-left;");
            colunaUsuarioCadastro.setCellValueFactory(param -> param.getValue().getUsuarioCadastro().apelidoProperty());

            Label lblDtCadastro = new Label("Dt. cadastro");
            lblDtCadastro.setPrefWidth(140);
            colunaDtCadastro = new TableColumn<Recebimento, String>();
            colunaDtCadastro.setPrefWidth(lblDtCadastro.getPrefWidth());
            colunaDtCadastro.setGraphic(lblDtCadastro);
            colunaDtCadastro.setStyle("-fx-alignment: center-right;");
            colunaDtCadastro.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().dtCadastroProperty().get().format(DTF_DATAHORA)));

            Label lblPagamentoTipo = new Label("Tipo");
            lblPagamentoTipo.setPrefWidth(85);
            colunaPagamentoTipo = new TableColumn<Recebimento, PagamentoTipo>();
            colunaPagamentoTipo.setGraphic(lblPagamentoTipo);
            colunaPagamentoTipo.setPrefWidth(lblPagamentoTipo.getPrefWidth());
            colunaPagamentoTipo.setStyle("-fx-alignment: center-left;");
            colunaPagamentoTipo.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPagamentoTipo()));

            Label lblDocumento = new Label("Documento");
            lblDocumento.setPrefWidth(120);
            colunaDocumento = new TableColumn<Recebimento, String>();
            colunaDocumento.setGraphic(lblDocumento);
            colunaDocumento.setPrefWidth(lblDocumento.getPrefWidth());
            colunaDocumento.setStyle("-fx-alignment: center-right;");
            colunaDocumento.setCellValueFactory(param -> param.getValue().documentoProperty());

            Label lblValor = new Label("Valor");
            lblValor.setPrefWidth(75);
            colunaValor = new TableColumn<Recebimento, String>();
            colunaValor.setPrefWidth(lblValor.getPrefWidth());
            colunaValor.setGraphic(lblValor);
            colunaValor.setStyle("-fx-alignment: center-right;");
            colunaValor.setCellValueFactory(param -> new SimpleStringProperty(ServiceMascara.getMoeda(param.getValue().valorProperty().get().toString(), 2)));

            Label lblPagamentoSituacao = new Label("Situação");
            lblPagamentoSituacao.setPrefWidth(85);
            colunaPagamentoSituacao = new TableColumn<Recebimento, PagamentoSituacao>();
            colunaPagamentoSituacao.setGraphic(lblPagamentoSituacao);
            colunaPagamentoSituacao.setPrefWidth(lblPagamentoSituacao.getPrefWidth());
            colunaPagamentoSituacao.setStyle("-fx-alignment: center-left;");
            colunaPagamentoSituacao.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPagamentoSituacao()));

//            Label lblParcela = new Label("Parcela");
//            lblParcela.setPrefWidth(45);
//            colunaParcela = new TableColumn<Recebimento, String>();
//            colunaParcela.setGraphic(lblParcela);
//            colunaParcela.setPrefWidth(lblParcela.getPrefWidth());
//            colunaParcela.setStyle("-fx-alignment: center-right;");
//            colunaParcela.setCellValueFactory(param ->
//                    new SimpleStringProperty(String.format("%02d/%02d", param.getValue().parcelaProperty(), param.getValue().parcelasProperty())));

            Label lblParcelas = new Label("Parcelas");
            lblParcelas.setPrefWidth(60);
            colunaParcelas = new TableColumn<Recebimento, String>();
            colunaParcelas.setGraphic(lblParcelas);
            colunaParcelas.setPrefWidth(lblParcelas.getPrefWidth());
            colunaParcelas.setStyle("-fx-alignment: center-right;");
            colunaParcelas.setCellValueFactory(param ->
                    new SimpleStringProperty(String.format("%02d/%02d",
                            param.getValue().parcelaProperty().get(),
                            param.getValue().parcelasProperty().get())
                    ));

            Label lblDtVencimento = new Label("Dt. vencimento");
            lblDtVencimento.setPrefWidth(80);
            colunaDtVencimento = new TableColumn<Recebimento, String>();
            colunaDtVencimento.setPrefWidth(lblDtVencimento.getPrefWidth());
            colunaDtVencimento.setGraphic(lblDtVencimento);
            colunaDtVencimento.setStyle("-fx-alignment: center-right;");
            colunaDtVencimento.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().dtVencimentoProperty().get().format(DTF_DATA)));

            Label lblUsuarioPagamento = new Label("Usuário pag");
            lblUsuarioPagamento.setPrefWidth(70);
            colunaUsuarioPagamento = new TableColumn<Recebimento, String>();
            colunaUsuarioPagamento.setGraphic(lblUsuarioPagamento);
            colunaUsuarioPagamento.setPrefWidth(lblUsuarioPagamento.getPrefWidth());
            colunaUsuarioPagamento.setStyle("-fx-alignment: center-left;");
            colunaUsuarioPagamento.setCellValueFactory(param -> {
                String apelido;
                if (((apelido = param.getValue().getUsuarioAtualizacao().apelidoProperty().get()) == null)
                        || (param.getValue().pagamentoSituacaoProperty().get() == PagamentoSituacao.PENDENTE.getCod()))
                    apelido = "";
                return new SimpleStringProperty(apelido);
            });

            Label lblDtPagamento = new Label("Dt. pag");
            lblDtPagamento.setPrefWidth(80);
            colunaDtPagamento = new TableColumn<Recebimento, LocalDate>();
            colunaDtPagamento.setPrefWidth(lblDtPagamento.getPrefWidth());
            colunaDtPagamento.setGraphic(lblDtPagamento);
            colunaDtPagamento.setStyle("-fx-alignment: center-right;");
            colunaDtPagamento.setCellValueFactory(param -> {
                try {
                    if (param.getValue().pagamentoSituacaoProperty().get() != PagamentoSituacao.PENDENTE.getCod())
                        return new SimpleObjectProperty<>(param.getValue().dtPagamentoProperty().get());
                } catch (Exception ex) {
                }
                return new SimpleObjectProperty<>();
            });
            colunaDtPagamento.setCellFactory(param -> new SetCellFactoryTableCell_DatePicker<>());
            colunaDtPagamento.setOnEditCommit(event -> {
                event.getRowValue().dtPagamentoProperty().setValue(event.getNewValue());
            });
            colunaDtPagamento.setEditable(false);

            Label lblValorRecebido = new Label("Valor rec");
            lblValorRecebido.setPrefWidth(75);
            colunaValorRecebido = new TableColumn<Recebimento, String>();
            colunaValorRecebido.setPrefWidth(lblValorRecebido.getPrefWidth());
            colunaValorRecebido.setGraphic(lblValorRecebido);
            colunaValorRecebido.setStyle("-fx-alignment: center-right;");
            colunaValorRecebido.setCellValueFactory(param -> {
                BigDecimal vlr;
                if (((vlr = param.getValue().valorRecebidoProperty().get()) == null)
                        || (param.getValue().pagamentoSituacaoProperty().get() == PagamentoSituacao.PENDENTE.getCod()))
                    vlr = BigDecimal.ZERO;
                return new SimpleStringProperty(ServiceMascara.getMoeda(vlr.setScale(2).toString(), 2));
            });

            Label lblUsuarioAtualizacao = new Label("Usuário atualiz.");
            lblUsuarioAtualizacao.setPrefWidth(70);
            colunaUsuarioAtualizacao = new TableColumn<Recebimento, String>();
            colunaUsuarioAtualizacao.setGraphic(lblUsuarioAtualizacao);
            colunaUsuarioAtualizacao.setPrefWidth(lblUsuarioAtualizacao.getPrefWidth());
            colunaUsuarioAtualizacao.setStyle("-fx-alignment: center-left;");
            colunaUsuarioAtualizacao.setCellValueFactory(param -> param.getValue().getUsuarioAtualizacao().apelidoProperty());

            Label lblDtAtualizacao = new Label("Dt. atualiz.");
            lblDtAtualizacao.setPrefWidth(140);
            colunaDtAtualizacao = new TableColumn<Recebimento, String>();
            colunaDtAtualizacao.setPrefWidth(lblDtAtualizacao.getPrefWidth());
            colunaDtAtualizacao.setGraphic(lblDtAtualizacao);
            colunaDtAtualizacao.setStyle("-fx-alignment: center-right;");
            colunaDtAtualizacao.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().dtAtualizacaoProperty().get().format(DTF_DATAHORA)));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void escutaLista() {

    }

    public void preencherTabela() {
        getTvRecebimento().getColumns().setAll(
                getColunaId(), getColunaUsuarioCadastro(), getColunaDtCadastro(), getColunaPagamentoTipo(),
                getColunaDocumento(), getColunaValor(), getColunaPagamentoSituacao(), getColunaParcelas(),
                getColunaDtVencimento(), getColunaUsuarioPagamento(), getColunaDtPagamento(),
                getColunaValorRecebido(), getColunaUsuarioAtualizacao(), getColunaDtAtualizacao()
        );
        getTvRecebimento().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getTvRecebimento().getSelectionModel().setCellSelectionEnabled(true);
        getTvRecebimento().setEditable(true);
        getTvRecebimento().setItems(recebimentoObservableList);
    }

}
