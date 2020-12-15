package br.com.tlmacedo.cafeperfeito.controller;


import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.UsuarioDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.FORM_VIEW;
import br.com.tlmacedo.cafeperfeito.model.vo.Usuario;
import br.com.tlmacedo.cafeperfeito.service.ServiceCryptografia;
import br.com.tlmacedo.cafeperfeito.service.ServiceTremeView;
import br.com.tlmacedo.cafeperfeito.view.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class ControllerLogin implements Initializable, ModeloCafePerfeito {

    public Label lblTitulo;
    public JFXComboBox<Usuario> jfxCboUsuario;
    public JFXPasswordField jfxPswSenha;
    public JFXButton jfxBtnOk;
    public JFXButton jfxBtnCancela;

    private Stage stageLogin;
    private static Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            criarObjetos();
            preencherObjetos();
            fatorarObjetos();
            escutarTecla();
            fieldsFormat();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void fieldsFormat() throws Exception {

    }

    @Override
    public void fechar() {
        getStageLogin().close();
    }

    @Override
    public void criarObjetos() throws Exception {

    }

    @Override
    public void preencherObjetos() throws Exception {

        setStageLogin(View.getStage());
        getLblTitulo().setText(TCONFIG.getFxml().getLogin().getTitulo());
        getJfxCboUsuario().setItems(getUsuarioDAO().getAll(Usuario.class, "situacao>=1", null).stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));

    }

    @Override
    public void fatorarObjetos() throws Exception {

        getJfxCboUsuario().setCellFactory(listView -> new ListCell<Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null)
                    setText("");
                else
                    setText(item.getUsuarioDetalhe());
            }
        });

    }

    @Override
    public void escutarTecla() throws Exception {

        getJfxCboUsuario().setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                getJfxPswSenha().requestFocus();
        });

        getJfxPswSenha().setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                getJfxCboUsuario().requestFocus();
        });

        getJfxBtnCancela().setOnAction(actionEvent -> fechar());

        getJfxBtnOk().disableProperty().bind(Bindings.createBooleanBinding(() -> {
                    setUsuario(getJfxCboUsuario().getSelectionModel().getSelectedItem());
                    return (getUsuario() == null || getJfxPswSenha().getText().trim().length() == 0);
                },
                getJfxCboUsuario().getSelectionModel().selectedItemProperty(),
                getJfxPswSenha().textProperty()));

        getJfxBtnOk().setOnAction(actionEvent -> {
            if (!ServiceCryptografia.senhaValida(getJfxPswSenha().getText(), getUsuario().getSenha())) {
                new ServiceTremeView(getStageLogin());
                return;
            }
//            System.out.printf("000: %s\n", getClass().getResource("image/ico/ic_coffee_ativo_dp24.png"));
            new View(FORM_VIEW.PRINCIPAL).loadForm();
            fechar();
        });

    }

    /**
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     */

    public static Usuario getUsuarioLogado() {
        return getUsuario();
    }

    /**
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     */

    public Label getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(Label lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public JFXComboBox<Usuario> getJfxCboUsuario() {
        return jfxCboUsuario;
    }

    public void setJfxCboUsuario(JFXComboBox<Usuario> jfxCboUsuario) {
        this.jfxCboUsuario = jfxCboUsuario;
    }

    public JFXPasswordField getJfxPswSenha() {
        return jfxPswSenha;
    }

    public void setJfxPswSenha(JFXPasswordField jfxPswSenha) {
        this.jfxPswSenha = jfxPswSenha;
    }

    public JFXButton getJfxBtnOk() {
        return jfxBtnOk;
    }

    public void setJfxBtnOk(JFXButton jfxBtnOk) {
        this.jfxBtnOk = jfxBtnOk;
    }

    public JFXButton getJfxBtnCancela() {
        return jfxBtnCancela;
    }

    public void setJfxBtnCancela(JFXButton jfxBtnCancela) {
        this.jfxBtnCancela = jfxBtnCancela;
    }

    public Stage getStageLogin() {
        return stageLogin;
    }

    public void setStageLogin(Stage stageLogin) {
        this.stageLogin = stageLogin;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        ControllerLogin.usuario = usuario;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
}
