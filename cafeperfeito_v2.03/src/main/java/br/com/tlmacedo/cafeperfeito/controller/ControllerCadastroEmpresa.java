package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.view.View;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCadastroEmpresa implements Initializable, ModeloCafePerfeito {

    public TitledPane tpnCadastroEmpresa;
    public JFXTextField jfxTxtPesquisa;
    public TitledPane tpnInformacao;

    private EventHandler<KeyEvent> eventHandlerCadastroEmpresa;


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

        ControllerPrincipal.fecharTab(View.getTab(), getEventHandlerCadastroEmpresa());

    }

    @Override
    public void criarObjetos() throws Exception {

    }

    @Override
    public void preencherObjetos() throws Exception {

    }

    @Override
    public void fatorarObjetos() throws Exception {

    }

    @Override
    public void escutarTecla() throws Exception {

        setEventHandlerCadastroEmpresa(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case F7 -> {
                        getJfxTxtPesquisa().requestFocus();
                    }
                    case F12 -> {
                        fechar();
                    }
                }
            }
        });
        ControllerPrincipal.getControllerPrincipal().getPanePrincipal().addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerCadastroEmpresa());

    }

    /**
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     */

    public TitledPane getTpnCadastroEmpresa() {
        return tpnCadastroEmpresa;
    }

    public void setTpnCadastroEmpresa(TitledPane tpnCadastroEmpresa) {
        this.tpnCadastroEmpresa = tpnCadastroEmpresa;
    }

    public JFXTextField getJfxTxtPesquisa() {
        return jfxTxtPesquisa;
    }

    public void setJfxTxtPesquisa(JFXTextField jfxTxtPesquisa) {
        this.jfxTxtPesquisa = jfxTxtPesquisa;
    }

    public TitledPane getTpnInformacao() {
        return tpnInformacao;
    }

    public void setTpnInformacao(TitledPane tpnInformacao) {
        this.tpnInformacao = tpnInformacao;
    }

    public EventHandler<KeyEvent> getEventHandlerCadastroEmpresa() {
        return eventHandlerCadastroEmpresa;
    }

    public void setEventHandlerCadastroEmpresa(EventHandler<KeyEvent> eventHandlerCadastroEmpresa) {
        this.eventHandlerCadastroEmpresa = eventHandlerCadastroEmpresa;
    }

    /**
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     */

}
