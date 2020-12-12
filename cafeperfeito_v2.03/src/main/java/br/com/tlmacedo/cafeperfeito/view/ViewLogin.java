package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.service.ServiceOpenView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;
import static javafx.stage.StageStyle.UNDECORATED;

public class ViewLogin {

    private static Stage stage;

    public void openViewLogin(boolean showAndWait) {
        setStage(new Stage());
        Parent parent;
        Scene scene = null;

        try {
            parent = FXMLLoader.load(getClass().getResource(TCONFIG.getFxml().getLogin().getFxml()));
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

        new ServiceOpenView(getStage(), showAndWait);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ViewLogin.stage = stage;
    }
}
