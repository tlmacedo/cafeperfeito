package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.service.ServiceOpenView;
import br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewLogin {

    static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void openViewLogin(boolean showAndWait) {
        this.stage = new Stage();
        Parent parent;
        Scene scene = null;

        try {
            parent = FXMLLoader.load(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getFxml().getLogin().getFxml()));
            scene = new Scene(parent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle(ServiceVariaveisSistema.TCONFIG.getFxml().getLogin().getTitulo());
        stage.getIcons().setAll(new Image(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getFxml().getLogin().getIcone()).toString()));
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().setAll(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getPersonalizacao().getStyleSheetsMin()).toString());

        new ServiceOpenView(stage, showAndWait);

    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        //ViewLogin.stage = primaryStage;
//        openViewLogin(false);
//    }
}
