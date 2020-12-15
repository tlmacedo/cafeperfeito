package br.com.tlmacedo.cafeperfeito;


import br.com.tlmacedo.cafeperfeito.model.enums.FORM_VIEW;
import br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis;
import br.com.tlmacedo.cafeperfeito.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ServiceConfigSis().getVariaveisSistema();
//        new ServiceConfigSis().getVariaveisSistemaSimples();

//        new ViewLogin().openViewLogin(false);

        new View(FORM_VIEW.LOGIN).loadForm();

//        new ViewPrincipal().openView();

    }
}
