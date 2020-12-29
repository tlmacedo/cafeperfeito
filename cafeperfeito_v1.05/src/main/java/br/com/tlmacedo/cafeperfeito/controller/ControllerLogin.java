package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.UsuarioDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.Usuario;
import br.com.tlmacedo.cafeperfeito.service.ServiceCryptografia;
import br.com.tlmacedo.cafeperfeito.service.ServiceTremeView;
import br.com.tlmacedo.cafeperfeito.view.ViewLogin;
import br.com.tlmacedo.cafeperfeito.view.ViewPrincipal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema.TCONFIG;

@SuppressWarnings("JavaFX Application Thread")
public class ControllerLogin implements Initializable, ModeloCafePerfeito {
    public AnchorPane painelViewLogin;
    public Label titulo;
    public JFXComboBox<Usuario> cboUsuarioLogin;
    public JFXPasswordField pswUsuarioSenha;
    public JFXButton btnOK;
    public JFXButton btnCancela;


    private Usuario usuarioVO = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private FilteredList<Usuario> usuarioFilteredList;
    private Stage stageLogin;

    @Override
    public void fechar() {
        ViewLogin.getStage().close();
    }

    @Override
    public void criarObjetos() {
    }

    @Override
    public void preencherObjetos() {
        getTitulo().setText(TCONFIG.getFxml().getLogin().getTitulo());
        getCboUsuarioLogin().setPromptText(String.format("%s:", "Selecione usuário"));
//        getCboUsuarioLogin().setItems(getUsuarioDAO().getAll(Usuario.class, null, null, null, null).stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
        getCboUsuarioLogin().setItems(new UsuarioDAO().getAll(Usuario.class, null, null, null, null).stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
//        getCboUsuarioLogin().setEditable(true);
//        new ServiceAutoCompleteComboBox<Usuario>(getCboUsuarioLogin(), Usuario.class);
    }

    @Override
    public void fatorarObjetos() {
        getCboUsuarioLogin().setCellFactory(new Callback<ListView<Usuario>, ListCell<Usuario>>() {
            @Override
            public ListCell<Usuario> call(ListView<Usuario> param) {
                final ListCell<Usuario> cell = new ListCell<Usuario>() {
                    @Override
                    protected void updateItem(Usuario item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null)
                            setText(null);
                        else
                            setText(item.getDetalhe());
                    }
                };
                return cell;
            }
        });
    }

    @Override
    public void escutarTecla() {
        getPainelViewLogin().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER && getBtnOK().isDisable())
                if (getCboUsuarioLogin().isFocused())
                    getPswUsuarioSenha().requestFocus();
                else
                    getCboUsuarioLogin().requestFocus();
            if (event.getCode() == KeyCode.F12)
                getBtnCancela().fire();
        });

        getCboUsuarioLogin().getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> setUsuarioVO(n));

        getBtnOK().disableProperty().bind(Bindings.createBooleanBinding(() ->
                        (getUsuarioVO() == null || getPswUsuarioSenha().getLength() == 0)
                , getCboUsuarioLogin().getSelectionModel().selectedItemProperty(), getPswUsuarioSenha().textProperty()
        ));

        getBtnCancela().setOnAction(event -> fechar());

        getBtnOK().setOnAction(event -> {
            if (getUsuarioVO() == null) return;
            if (!ServiceCryptografia.senhaValida(getPswUsuarioSenha().getText(), getUsuarioVO().getSenha())) {
                new ServiceTremeView(ViewLogin.getStage());
                return;
            }
            LogadoInf.setUserLog(getUsuarioVO());
            fechar();
            new ViewPrincipal().openViewPrincipal();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarObjetos();
        preencherObjetos();
        fatorarObjetos();
        escutarTecla();
        Platform.runLater(() -> {
            setStageLogin(ViewLogin.getStage());
        });
    }


    public AnchorPane getPainelViewLogin() {
        return painelViewLogin;
    }

    public void setPainelViewLogin(AnchorPane painelViewLogin) {
        this.painelViewLogin = painelViewLogin;
    }

    public Label getTitulo() {
        return titulo;
    }

    public void setTitulo(Label titulo) {
        this.titulo = titulo;
    }

    public JFXComboBox<Usuario> getCboUsuarioLogin() {
        return cboUsuarioLogin;
    }

    public void setCboUsuarioLogin(JFXComboBox<Usuario> cboUsuarioLogin) {
        this.cboUsuarioLogin = cboUsuarioLogin;
    }

    public JFXPasswordField getPswUsuarioSenha() {
        return pswUsuarioSenha;
    }

    public void setPswUsuarioSenha(JFXPasswordField pswUsuarioSenha) {
        this.pswUsuarioSenha = pswUsuarioSenha;
    }

    public JFXButton getBtnOK() {
        return btnOK;
    }

    public void setBtnOK(JFXButton btnOK) {
        this.btnOK = btnOK;
    }

    public JFXButton getBtnCancela() {
        return btnCancela;
    }

    public void setBtnCancela(JFXButton btnCancela) {
        this.btnCancela = btnCancela;
    }

    public Usuario getUsuarioVO() {
        return usuarioVO;
    }

    public void setUsuarioVO(Usuario usuarioVO) {
        this.usuarioVO = usuarioVO;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public FilteredList<Usuario> getUsuarioFilteredList() {
        return usuarioFilteredList;
    }

    public void setUsuarioFilteredList(FilteredList<Usuario> usuarioFilteredList) {
        this.usuarioFilteredList = usuarioFilteredList;
    }

    public Stage getStageLogin() {
        return stageLogin;
    }

    public void setStageLogin(Stage stageLogin) {
        this.stageLogin = stageLogin;
    }
}
