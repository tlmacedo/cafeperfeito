package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.service.ServiceOpenView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;
import static javafx.stage.StageStyle.UNDECORATED;

public class ViewRecebimento {

    private static Stage stage;
    private static ObjectProperty<Object> object = new SimpleObjectProperty<>();
    private static ObjectProperty<BigDecimal> credDeb = new SimpleObjectProperty<>(BigDecimal.ZERO);

    public ViewRecebimento() {
    }

    public void openViewRecebimento(Object object) {
        objectProperty().setValue(object);
        openViewRecebimento();
    }

//    public void openViewRecebimento(Object object, BigDecimal credDeb){
//        credDebProperty().setValue(credDeb);
//        openViewRecebimento(object);
//    }

    public void openViewRecebimento() {
        setStage(new Stage());
        Parent parent;
        Scene scene = null;

        try {
            parent = FXMLLoader.load(getClass().getResource(TCONFIG.getFxml().getRecebimento().getFxml()));
            scene = new Scene(parent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        getStage().setResizable(false);
        getStage().setScene(scene);
        getStage().initStyle(UNDECORATED);
        getStage().setTitle(TCONFIG.getFxml().getLogin().getTitulo());
        getStage().getIcons().setAll(new Image(getClass().getResource(TCONFIG.getFxml().getLogin().getIcone()).toString()));
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().setAll(getClass().getResource(TCONFIG.getPersonalizacao().getStyleSheets()).toString());

        new ServiceOpenView(getStage(), true);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ViewRecebimento.stage = stage;
    }

    public static Object getObject() {
        return object.get();
    }

    public static ObjectProperty<Object> objectProperty() {
        return object;
    }

    public static void setObject(Object object) {
        ViewRecebimento.object.set(object);
    }

    public static BigDecimal getCredDeb() {
        return credDeb.get();
    }

    public static ObjectProperty<BigDecimal> credDebProperty() {
        return credDeb;
    }

    public static void setCredDeb(BigDecimal credDeb) {
        ViewRecebimento.credDeb.set(credDeb);
    }
}
