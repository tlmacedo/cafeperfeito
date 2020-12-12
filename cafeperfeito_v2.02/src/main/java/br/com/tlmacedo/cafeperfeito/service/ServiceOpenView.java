package br.com.tlmacedo.cafeperfeito.service;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class ServiceOpenView {

    public ServiceOpenView(Stage stage, boolean showAndWait) {
        if (showAndWait) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } else {
            stage.show();
        }
    }
}
