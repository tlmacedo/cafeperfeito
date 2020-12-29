package br.com.tlmacedo.cafeperfeito;

import br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema;
import br.com.tlmacedo.cafeperfeito.view.ViewPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ServiceVariaveisSistema().getVariaveisSistema();

//        new ViewLogin().openViewLogin(false);

        new ViewPrincipal().openViewPrincipal();
    }
}
