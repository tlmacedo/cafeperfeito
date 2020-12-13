package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.model.enums.FORM_VIEW;
import br.com.tlmacedo.cafeperfeito.service.ServiceOpenView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class View {

    private static Stage stage;
    private Parent parent;
    private Scene scene = null;
    private String fxmlPath, fxmlTitle, fxmlIcon, fxmlStyle;

    public View(FORM_VIEW formView) {

        setStage(new Stage());
        setFxmlIcon(TCONFIG.getFxml().getLogin().getIcone());

        switch (formView) {
            case LOGIN -> {
                setFxmlPath(TCONFIG.getFxml().getLogin().getFxml());
                setFxmlTitle(TCONFIG.getFxml().getLogin().getTitulo());
                setFxmlIcon(TCONFIG.getFxml().getLogin().getIcone());
            }
            case PRINCIPAL -> {

            }
        }
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
//        getStage().getIcons().setAll(new Image(getClass().getResource(TCONFIG.getFxml().getPrincipal().getIconeDesativo()).toString()));
        getScene().getStylesheets().setAll(getClass().getResource(TCONFIG.getPersonalizacao().getStyleSheetsMin()).toString());

        new ServiceOpenView(stage, false);

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        View.stage = stage;
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

    public String getFxmlIcon() {
        return fxmlIcon;
    }

    public void setFxmlIcon(String fxmlIcon) {
        this.fxmlIcon = fxmlIcon;
    }

    public String getFxmlStyle() {
        return fxmlStyle;
    }

    public void setFxmlStyle(String fxmlStyle) {
        this.fxmlStyle = fxmlStyle;
    }
}
