package br.com.tlmacedo.cafeperfeito;


import br.com.tlmacedo.cafeperfeito.model.enums.FORM_VIEW;
import br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis;
import br.com.tlmacedo.cafeperfeito.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//        new ServiceConfigSis(false).getVariaveisSistema();
        new ServiceConfigSis(true).getVariaveisSistema();

//        new View(FORM_VIEW.LOGIN).loadForm();
        new View(FORM_VIEW.PRINCIPAL).loadForm();

    }
}
