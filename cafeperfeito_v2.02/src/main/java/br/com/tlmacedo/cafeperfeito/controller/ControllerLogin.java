package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.UsuarioDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.Usuario;
import br.com.tlmacedo.cafeperfeito.model.vo.UsuarioLogado;
import br.com.tlmacedo.cafeperfeito.service.ServiceCryptografia;
import br.com.tlmacedo.cafeperfeito.service.ServiceTremeView;
import br.com.tlmacedo.cafeperfeito.view.ViewLogin;
import br.com.tlmacedo.cafeperfeito.view.ViewPrincipal;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class ControllerLogin implements Initializable, ModeloCafePerfeito {

    public Label lblTitulo;
    public ComboBox<Usuario> cboUsuario;
    public PasswordField pswSenha;
    public Button btnOK;
    public Button btnCancel;

    private Stage loginStage;
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarioList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            criarObjetos();
            preencherObjetos();
            fatorarObjetos();
            escutarTecla();
            fieldsFormat();
            Platform.runLater(() -> {
                setLoginStage(ViewLogin.getStage());
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fieldsFormat() throws Exception {

    }

    @Override
    public void fechar() {
        getLoginStage().close();
    }

    @Override
    public void criarObjetos() throws Exception {

    }

    @Override
    public void preencherObjetos() throws Exception {
        getLblTitulo().setText(TCONFIG.getFxml().getLogin().getTitulo());
        getCboUsuario().setItems(
                new UsuarioDAO().getAll(Usuario.class, "situacao>=1", null)
                        .stream().collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
    }

    @Override
    public void fatorarObjetos() throws Exception {
        getCboUsuario().setCellFactory(param -> new ListCell<Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null)
                    setText("");
                else
                    setText(item.getDetalhe());
            }
        });
    }

    @Override
    public void escutarTecla() throws Exception {

        getBtnCancel().setOnAction(event -> fechar());

        getBtnOK().disableProperty().bind(Bindings.createBooleanBinding(() ->
                        (getCboUsuario().getSelectionModel().getSelectedItem() == null
                                || getPswSenha().getText().trim().length() == 0),
                getCboUsuario().getSelectionModel().selectedItemProperty(),
                getPswSenha().textProperty()
        ));

        getBtnOK().setOnAction(event -> {
            if (getCboUsuario().getSelectionModel().getSelectedItem() == null) return;
            if (!ServiceCryptografia.senhaValida(getPswSenha().getText(), getCboUsuario().getSelectionModel().getSelectedItem().getSenha())) {
                new ServiceTremeView(getLoginStage());
                return;
            }
            UsuarioLogado.setUsuario(getCboUsuario().getSelectionModel().getSelectedItem());
            fechar();
            new ViewPrincipal().openViewPrincipal();
        });

    }

    public Label getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(Label lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public ComboBox<Usuario> getCboUsuario() {
        return cboUsuario;
    }

    public void setCboUsuario(ComboBox<Usuario> cboUsuario) {
        this.cboUsuario = cboUsuario;
    }

    public PasswordField getPswSenha() {
        return pswSenha;
    }

    public void setPswSenha(PasswordField pswSenha) {
        this.pswSenha = pswSenha;
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

    public Stage getLoginStage() {
        return loginStage;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
}
