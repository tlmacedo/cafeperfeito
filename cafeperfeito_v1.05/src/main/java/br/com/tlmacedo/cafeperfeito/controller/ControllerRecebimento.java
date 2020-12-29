package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.vo.ContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProdutoProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoSituacao;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoTipo;
import br.com.tlmacedo.cafeperfeito.service.ServiceCampoPersonalizado;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import br.com.tlmacedo.cafeperfeito.view.ViewRecebimento;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-22
 * Time: 16:00
 */

public class ControllerRecebimento implements Initializable, ModeloCafePerfeito {

    public TitledPane tpnInformacaoRecebimento;
    public JFXComboBox<PagamentoTipo> cboTipoPagamento;
    public JFXTextField txtParcelas;
    public JFXTextField txtDocumento;
    public JFXDatePicker dtpVencimento;
    public JFXTextField txtValor;

    ObservableList<Recebimento> recebimentoObservableList = FXCollections.observableArrayList();
    EventHandler eventHandlerRecebimento;
    ContasAReceber contasAReceber;
    Recebimento recebimento;

    private List<Pair> listaTarefa = new ArrayList<>();
    private String nomeTab = "";
    private String nomeController = "Recebimento";

    public ControllerRecebimento() {
        setContasAReceber(ViewRecebimento.getaReceber());
        setRecebimento(ViewRecebimento.getRecebimento());
    }

    @Override
    public void fechar() {
        ViewRecebimento.getStage().close();
    }

    @Override
    public void criarObjetos() {
    }

    @Override
    public void preencherObjetos() {
        txtParcelas.setVisible(false);
        cboTipoPagamento.setItems(PagamentoTipo.getList().stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }

    @Override
    public void fatorarObjetos() {
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void escutarTecla() {

        eventHandlerRecebimento = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESCAPE:
                        event.consume();
                        getRecebimento().setDtVencimento(null);
                        fechar();
                        break;
                    case F2:
                        if (!validarRecebimento()) return;
                        if (!guardarRecebimento()) return;
                        fechar();
                        break;
                }
            }
        };
        tpnInformacaoRecebimento.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerRecebimento);

        cboTipoPagamento.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            txtDocumento.requestFocus();
        });
        txtDocumento.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            dtpVencimento.requestFocus();
        });

        dtpVencimento.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() != KeyCode.ENTER) return;
            txtValor.requestFocus();
        });
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarObjetos();
        preencherObjetos();
        escutarTecla();
        fatorarObjetos();
        ServiceCampoPersonalizado.fieldMask((AnchorPane) tpnInformacaoRecebimento.getContent());
        exibirRecebimento();
    }

    public ContasAReceber getContasAReceber() {
        return contasAReceber;
    }

    public void setContasAReceber(ContasAReceber contasAReceber) {
        this.contasAReceber = contasAReceber;
    }

    public Recebimento getRecebimento() {
        return recebimento;
    }

    public void setRecebimento(Recebimento recebimento) {
        this.recebimento = recebimento;
    }

    private void exibirRecebimento() {
        txtDocumento.setText("");
        txtValor.setText(
                getContasAReceber().getSaidaProduto().getSaidaProdutoProdutoList().stream().map(SaidaProdutoProduto::getVlrLiquido)
                        .reduce(BigDecimal.ZERO, BigDecimal::add).subtract(getContasAReceber().getRecebimentoList().stream().map(Recebimento::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)).setScale(2).toString()
        );
        cboTipoPagamento.getSelectionModel().select(PagamentoTipo.DINHEIRO);
        dtpVencimento.setValue(getContasAReceber().dtVencimentoProperty().get());
    }

    @SuppressWarnings("Duplicates")
    private boolean validarRecebimento() {
        boolean result = true;
        String dado = "";
//        if (result)
//            if (!(result = (cboTipoPagamento.getSelectionModel().getSelectedItem() != null))) {
//                dado += "tipo pagamento";
//                cboTipoPagamento.requestFocus();
//            }
//        if (!(LocalDate.now().compareTo(LocalDate.of(2019, 05, 22)) == 0
//                || LocalDate.now().compareTo(LocalDate.of(2019, 05, 23)) == 0
//                || LocalDate.now().compareTo(LocalDate.of(2019, 05, 24)) == 0))
//            if (result)
//                if (!(result = (dtpVencimento.getValue().compareTo(LocalDate.now()) >= 0))) {
//                    dado += "data vencimento inválido";
//                    dtpVencimento.requestFocus();
//                }
//        if (result) {
//            BigDecimal vlr = ServiceMascara.getBigDecimalFromTextField(txtValor.getText(), 2);
//            if (!(result = (vlr.compareTo(BigDecimal.ZERO) > 0))) {
//                dado += "valor do recebimento";
//                txtValor.requestFocus();
//            }
//        }
        return result;
    }

    private boolean guardarRecebimento() {
        try {
            getRecebimento().setUsuarioCadastro(LogadoInf.getUserLog());
            getRecebimento().setPagamentoTipo(cboTipoPagamento.getSelectionModel().getSelectedItem());
            getRecebimento().setDocumento(txtDocumento.getText());
            getRecebimento().setValor(ServiceMascara.getBigDecimalFromTextField(txtValor.getText(), 2));
            getRecebimento().setPagamentoSituacao(PagamentoSituacao.PENDENTE);
            if (cboTipoPagamento.getSelectionModel().getSelectedItem() != PagamentoTipo.CARTAO) {
                getRecebimento().setParcela(1);
                getRecebimento().setParcelas(1);
            }
            getRecebimento().setDtVencimento(dtpVencimento.getValue());
            getRecebimento().setDtCadastro(LocalDateTime.now());
            if (LocalDate.now().compareTo(LocalDate.of(2019, 05, 8)) == 0) {
                getRecebimento().setDtCadastro(getContasAReceber().getDtCadastro());
            }

            getRecebimento().setUsuarioPagamento(null);
            getRecebimento().setDtPagamento(null);
            getRecebimento().setValorRecebido(BigDecimal.ZERO.setScale(2));
            getRecebimento().setUsuarioAtualizacao(LogadoInf.getUserLog());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


}
