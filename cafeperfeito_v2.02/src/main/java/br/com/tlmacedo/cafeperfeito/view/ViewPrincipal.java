package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.service.ServiceOpenView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class ViewPrincipal {

    static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void openViewPrincipal() {
        stage = new Stage();
        Parent parent;
        Scene scene = null;

        try {
            parent = FXMLLoader.load(getClass().getResource(TCONFIG.getFxml().getPrincipal().getFxml()));
            scene = new Scene(parent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        stage.setTitle(TCONFIG.getFxml().getPrincipal().getTitulo());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().setAll(new Image(getClass().getResource(TCONFIG.getFxml().getPrincipal().getIconeInativo()).toString()));
        scene.getStylesheets().setAll(getClass().getResource(TCONFIG.getPersonalizacao().getStyleSheetsMin()).toString());

        new ServiceOpenView(stage, false);
    }
}
