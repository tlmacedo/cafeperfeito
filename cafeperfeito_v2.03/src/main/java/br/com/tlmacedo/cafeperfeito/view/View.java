package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.model.enums.FORM_VIEW;
import br.com.tlmacedo.cafeperfeito.service.ServiceOpenView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class View {

    private static Stage stage;
    private static Tab tab;
    private Parent parent;
    private Scene scene = null;
    private static Image Icon0, Icon1;
    private String fxmlPath, fxmlTitle, fxmlStyle, fxmlIcon0, fxmlIcon1;

    public View(FORM_VIEW formView) {

        setStage(new Stage());

        switch (formView) {
            case LOGIN -> {
                setFxmlPath(TCONFIG.getFxml().getLogin().getFxml());
                setFxmlTitle(TCONFIG.getFxml().getLogin().getTitulo());
                setFxmlIcon0(TCONFIG.getFxml().getLogin().getIcone());
            }
            case PRINCIPAL -> {
                setFxmlPath(TCONFIG.getFxml().getPrincipal().getFxml());
                setFxmlTitle(TCONFIG.getFxml().getPrincipal().getTitulo());
                setFxmlIcon0(TCONFIG.getFxml().getPrincipal().getIconeInativo());
                setFxmlIcon1(TCONFIG.getFxml().getPrincipal().getIconeAtivo());
            }
            case EMPRESA -> {
                setFxmlPath(TCONFIG.getFxml().getCadastroEmpresa().getFxml());
                setFxmlTitle(TCONFIG.getFxml().getCadastroEmpresa().getTitulo());
            }
        }
    }

    public void loadForm() {
        if (getFxmlPath() != null) {
            try {
                setParent(FXMLLoader.load(getClass().getResource(getFxmlPath())));
                setScene(new Scene(getParent()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getStage().setTitle(getFxmlTitle());
        getStage().setResizable(false);
        getStage().setScene(getScene());
        getScene().getStylesheets().setAll(getClass().getResource(TCONFIG.getPersonalizacao().getStyleSheetsMin()).toString());

        new ServiceOpenView(stage, false);

    }

    public Tab loadTab(String titulo) {
        if (getFxmlPath() != null) {
            try {
                setParent(FXMLLoader.load(getClass().getResource(getFxmlPath())));
                setTab(new Tab(titulo));
                getTab().setContent(getParent());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return getTab();
    }


    /**
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     */

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        View.stage = stage;
    }

    public static Tab getTab() {
        return tab;
    }

    public static void setTab(Tab tab) {
        View.tab = tab;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static Image getIcon0() {
        return Icon0;
    }

    public static void setIcon0(Image icon0) {
        Icon0 = icon0;
    }

    public static Image getIcon1() {
        return Icon1;
    }

    public static void setIcon1(Image icon1) {
        Icon1 = icon1;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlTitle() {
        return fxmlTitle;
    }

    public void setFxmlTitle(String fxmlTitle) {
        this.fxmlTitle = fxmlTitle;
    }

    public String getFxmlStyle() {
        return fxmlStyle;
    }

    public void setFxmlStyle(String fxmlStyle) {
        this.fxmlStyle = fxmlStyle;
    }

    public String getFxmlIcon0() {
        return fxmlIcon0;
    }

    public void setFxmlIcon0(String fxmlIcon0) {
        setIcon0(new Image(getClass().getResource(fxmlIcon0).toString()));
        this.fxmlIcon0 = fxmlIcon0;
    }

    public String getFxmlIcon1() {
        return fxmlIcon1;
    }

    public void setFxmlIcon1(String fxmlIcon1) {
        setIcon1(new Image(getClass().getResource(fxmlIcon1).toString()));
        this.fxmlIcon1 = fxmlIcon1;
    }
}
