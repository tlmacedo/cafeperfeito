package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert;
import br.com.tlmacedo.cafeperfeito.model.dao.RecebimentoDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.EnumsTasks;
import br.com.tlmacedo.cafeperfeito.model.enums.PagamentoModalidade;
import br.com.tlmacedo.cafeperfeito.model.enums.PagamentoSituacao;
import br.com.tlmacedo.cafeperfeito.model.vo.ContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.model.vo.UsuarioLogado;
import br.com.tlmacedo.cafeperfeito.service.*;
import br.com.tlmacedo.cafeperfeito.view.ViewRecebimento;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATA;

public class ControllerRecebimento implements Initializable, ModeloCafePerfeito {

    public AnchorPane painelViewRecebimento;
    public TitledPane tpnRecebimento;
    public ComboBox<PagamentoModalidade> cboPagamentoModalidade;
    public ImageView imgNewDocumento;
    public TextField txtDocumento;
    public DatePicker dtpDtPagamento;
    public ComboBox<PagamentoSituacao> cboSituacao;
    public TextField txtValor;
    public Button btnPrintOK;
    public Button btnOK;
    public Button btnCancel;

    private Stage stageRecebimento = ViewRecebimento.getStage();
    private List<EnumsTasks> enumsTasksList = new ArrayList<>();

    private String nomeController = "recebimento";

    private ObjectProperty<Recebimento> recebimento = new SimpleObjectProperty<>();
    private ObjectProperty<Object> object = ViewRecebimento.objectProperty();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            criarObjetos();
            preencherObjetos();
            escutarTecla();
            fatorarObjetos();
            fieldsFormat();
            Platform.runLater(() -> {
                getCboPagamentoModalidade().requestFocus();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fieldsFormat() throws Exception {
        ServiceCampoPersonalizado.fieldTextFormat(getPainelViewRecebimento());
    }

    @Override
    public void fechar() {
        getEnumsTasksList().clear();
        getStageRecebimento().close();
    }

    @Override
    public void criarObjetos() throws Exception {
        getEnumsTasksList().add(EnumsTasks.TABELA_CRIAR);
    }

    @Override
    public void preencherObjetos() throws Exception {
        getCboPagamentoModalidade().setItems(
                Arrays.stream(PagamentoModalidade.values()).collect(Collectors.toCollection(FXCollections::observableArrayList))
        );

        getCboSituacao().setItems(
                Arrays.stream(PagamentoSituacao.values()).collect(Collectors.toCollection(FXCollections::observableArrayList))
        );

        getTxtDocumento().textProperty().addListener((ov, o, n) -> {
            if (n.trim().length() > 0) {
                getBtnOK().setDefaultButton(false);
                getBtnPrintOK().setDefaultButton(true);
            } else {
                getBtnPrintOK().setDefaultButton(false);
                getBtnOK().setDefaultButton(true);
            }
        });

        if (objectProperty().getValue() instanceof ContasAReceber) {

            recebimentoProperty().setValue(new Recebimento(((ContasAReceber) objectProperty().getValue())));

            recebimentoProperty().getValue().valorProperty().setValue(
                    recebimentoProperty().getValue().valorProperty().getValue()
                            .add(ViewRecebimento.credDebProperty().getValue())
            );

            recebimentoProperty().getValue().contasAReceberProperty().setValue((ContasAReceber) objectProperty().getValue());

            ((ContasAReceber) objectProperty().getValue()).getRecebimentoList().add(recebimentoProperty().getValue());
            getEnumsTasksList().add(EnumsTasks.ADD_RECEBIMENTO);

        } else {
            recebimentoProperty().setValue(((Recebimento) objectProperty().getValue()));

            getEnumsTasksList().add(EnumsTasks.UPDATE_RECEBIMENTO);
        }

        getTxtDocumento().setText(recebimentoProperty().getValue().documentoProperty().getValue());
        getCboPagamentoModalidade().getItems().stream()
                .filter(pagamentoModalidade -> pagamentoModalidade
                        .equals(recebimentoProperty().getValue().pagamentoModalidadeProperty().getValue()))
                .findFirst().ifPresent(pagamentoModalidade -> {
            getCboPagamentoModalidade().setValue(pagamentoModalidade);
            getCboPagamentoModalidade().getSelectionModel().select(pagamentoModalidade);
        });
        getCboSituacao().setValue(recebimentoProperty().getValue().pagamentoSituacaoProperty().getValue());
        getTxtValor().setText(ServiceMascara.getMoeda(recebimentoProperty().getValue().valorProperty().getValue(), 2));

        getDtpDtPagamento().setValue(recebimentoProperty().getValue().dtPagamentoProperty().getValue() != null
                ? recebimentoProperty().getValue().dtPagamentoProperty().getValue()
                : (recebimentoProperty().getValue().contasAReceberProperty().getValue().dtVencimentoProperty().getValue() != null
                ? recebimentoProperty().getValue().contasAReceberProperty().getValue().dtVencimentoProperty().getValue()
                : LocalDate.now())
        );
    }

    @Override
    public void fatorarObjetos() throws Exception {

    }

    @Override
    public void escutarTecla() throws Exception {

        getPainelViewRecebimento().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (Regex_Convert.CODE_KEY_SHIFT_CTRL_N.match(event) || Regex_Convert.CHAR_KEY_SHIFT_CTRL_N.match(event))
                getNewDocumento();
        });

        getBtnCancel().setOnAction(event -> fechar());

        getBtnOK().setOnAction(event -> {
            try {
                if (new ServiceSegundoPlano().executaListaTarefas(newTaskRecebimento(), "recebimento"))
                    fechar();
                else
                    getCboPagamentoModalidade().requestFocus();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        getBtnOK().disableProperty().bind(Bindings.createBooleanBinding(() -> {
            if (recebimentoProperty().getValue() != null)
                getCboSituacao().setValue(recebimentoProperty().getValue().pagamentoSituacaoProperty().getValue());
            if (getCboPagamentoModalidade().getSelectionModel().getSelectedItem().equals(PagamentoModalidade.AMOSTRA)
                    || getCboPagamentoModalidade().getSelectionModel().getSelectedItem().equals(PagamentoModalidade.BONIFICACAO)
                    || getCboPagamentoModalidade().getSelectionModel().getSelectedItem().equals(PagamentoModalidade.RETIRADA)) {
                getCboSituacao().setValue(PagamentoSituacao.QUITADO);
                return false;
            }
            return (
                    ServiceMascara.getBigDecimalFromTextField(getTxtValor().getText(), 2)
                            .compareTo(BigDecimal.ZERO) <= 0
                            && getDtpDtPagamento().getValue().compareTo(LocalDate.now().minusDays(7)) < 0
            );
        }, getCboPagamentoModalidade().valueProperty(), getDtpDtPagamento().valueProperty(), getTxtValor().textProperty()));

        getBtnPrintOK().setOnAction(event -> {
            try {
                getEnumsTasksList().add(EnumsTasks.RELATORIO_IMPRIME_RECIBO);
                if (new ServiceSegundoPlano().executaListaTarefas(newTaskRecebimento(), "imprimir recibo")) ;
                fechar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        getImgNewDocumento().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> getNewDocumento());
    }


    /**
     * Begin Booleans
     */

    private Task newTaskRecebimento() {
        try {
            int qtdTasks = getEnumsTasksList().size();
            final int[] cont = {0};
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    updateMessage("Loading...");
                    for (EnumsTasks tasks : getEnumsTasksList()) {
                        updateProgress(cont[0]++, qtdTasks);
                        Thread.sleep(200);
                        updateMessage(String.format("%s%s", tasks.getDescricao(),
                                tasks.getDescricao().endsWith(" de ") ? getNomeController() : ""));
                        switch (tasks) {
                            case ADD_RECEBIMENTO:
                            case UPDATE_RECEBIMENTO:
                            case RELATORIO_IMPRIME_RECIBO:
                                if (guardarRecebimento()) {
                                    if (!salvarRecebimento())
                                        Thread.currentThread().interrupt();
                                } else {
                                    Thread.currentThread().interrupt();
                                }
                                if (tasks.getDescricao().equals(EnumsTasks.RELATORIO_IMPRIME_RECIBO.getDescricao()))
                                    new ServiceRelatorio_Recibo().imprimeRecibo(recebimentoProperty().getValue());
                                break;
                        }
                    }
                    updateMessage("tarefa concluída!!!");
                    updateProgress(qtdTasks, qtdTasks);
                    return null;
                }
            };
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private boolean guardarRecebimento() {
        try {
            recebimentoProperty().getValue().pagamentoSituacaoProperty().setValue(getCboSituacao().getSelectionModel().getSelectedItem());
            recebimentoProperty().getValue().documentoProperty().setValue(
                    getTxtDocumento().getText() == null
                            ? "" : getTxtDocumento().getText().trim());
            recebimentoProperty().getValue().pagamentoModalidadeProperty().setValue(getCboPagamentoModalidade().getSelectionModel().getSelectedItem());
            recebimentoProperty().getValue().valorProperty().setValue(
                    ServiceMascara.getBigDecimalFromTextField(getTxtValor().getText(), 2)
            );
            recebimentoProperty().getValue().usuarioPagamentoProperty().setValue(null);
            recebimentoProperty().getValue().dtPagamentoProperty().setValue(null);
            if (recebimentoProperty().getValue().pagamentoSituacaoProperty().getValue().equals(PagamentoSituacao.QUITADO)) {
                recebimentoProperty().getValue().usuarioPagamentoProperty().setValue(UsuarioLogado.getUsuario());
                try {
                    recebimentoProperty().getValue().dtPagamentoProperty().setValue(
                            LocalDate.parse(getDtpDtPagamento().getEditor().getText(), DTF_DATA)
                    );
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (objectProperty().getValue() instanceof ContasAReceber)
                recebimentoProperty().getValue().usuarioCadastroProperty().setValue(UsuarioLogado.getUsuario());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean salvarRecebimento() {
        RecebimentoDAO recebimentoDAO = new RecebimentoDAO();
        try {
            if (objectProperty().getValue() instanceof ContasAReceber)
                recebimentoProperty().getValue().setContasAReceber((ContasAReceber) objectProperty().getValue());
            recebimentoDAO.transactionBegin();
            recebimentoProperty().setValue(
                    recebimentoDAO.setTransactionPersist(recebimentoProperty().getValue()));
            recebimentoDAO.transactionCommit();
        } catch (Exception ex) {
            recebimentoDAO.transactionRollback();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * END Booleans
     */


    /**
     * Begin Voids
     */

    private void getNewDocumento() {
        getTxtDocumento().setText(ServiceValidarDado
                .gerarCodigoCafePerfeito(Recebimento.class,
                        recebimentoProperty().getValue().contasAReceberProperty().getValue().dtCadastroProperty().getValue().toLocalDate())
        );
    }

    /**
     * END Voids
     */

    /**
     * Begin Getters and Setters
     */

    public AnchorPane getPainelViewRecebimento() {
        return painelViewRecebimento;
    }

    public void setPainelViewRecebimento(AnchorPane painelViewRecebimento) {
        this.painelViewRecebimento = painelViewRecebimento;
    }

    public TitledPane getTpnRecebimento() {
        return tpnRecebimento;
    }

    public void setTpnRecebimento(TitledPane tpnRecebimento) {
        this.tpnRecebimento = tpnRecebimento;
    }

    public ComboBox<PagamentoModalidade> getCboPagamentoModalidade() {
        return cboPagamentoModalidade;
    }

    public void setCboPagamentoModalidade(ComboBox<PagamentoModalidade> cboPagamentoModalidade) {
        this.cboPagamentoModalidade = cboPagamentoModalidade;
    }

    public ImageView getImgNewDocumento() {
        return imgNewDocumento;
    }

    public void setImgNewDocumento(ImageView imgNewDocumento) {
        this.imgNewDocumento = imgNewDocumento;
    }

    public TextField getTxtDocumento() {
        return txtDocumento;
    }

    public void setTxtDocumento(TextField txtDocumento) {
        this.txtDocumento = txtDocumento;
    }

    public DatePicker getDtpDtPagamento() {
        return dtpDtPagamento;
    }

    public void setDtpDtPagamento(DatePicker dtpDtPagamento) {
        this.dtpDtPagamento = dtpDtPagamento;
    }

    public ComboBox<PagamentoSituacao> getCboSituacao() {
        return cboSituacao;
    }

    public void setCboSituacao(ComboBox<PagamentoSituacao> cboSituacao) {
        this.cboSituacao = cboSituacao;
    }

    public TextField getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(TextField txtValor) {
        this.txtValor = txtValor;
    }

    public Button getBtnPrintOK() {
        return btnPrintOK;
    }

    public void setBtnPrintOK(Button btnPrintOK) {
        this.btnPrintOK = btnPrintOK;
    }

    public Button getBtnOK() {
        return btnOK;
    }

    public void setBtnOK(Button btnOK) {
        this.btnOK = btnOK;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Stage getStageRecebimento() {
        return stageRecebimento;
    }

    public void setStageRecebimento(Stage stageRecebimento) {
        this.stageRecebimento = stageRecebimento;
    }

    public List<EnumsTasks> getEnumsTasksList() {
        return enumsTasksList;
    }

    public void setEnumsTasksList(List<EnumsTasks> enumsTasksList) {
        this.enumsTasksList = enumsTasksList;
    }

    public String getNomeController() {
        return nomeController;
    }

    public void setNomeController(String nomeController) {
        this.nomeController = nomeController;
    }

    public Recebimento getRecebimento() {
        return recebimento.get();
    }

    public ObjectProperty<Recebimento> recebimentoProperty() {
        return recebimento;
    }

    public void setRecebimento(Recebimento recebimento) {
        this.recebimento.set(recebimento);
    }

    public Object getObject() {
        return object.get();
    }

    public ObjectProperty<Object> objectProperty() {
        return object;
    }

    public void setObject(Object object) {
        this.object.set(object);
    }

    /**
     * END Getters and Setters
     */
}
