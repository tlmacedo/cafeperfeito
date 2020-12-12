package br.com.tlmacedo.cafeperfeito;


import br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis;
import br.com.tlmacedo.cafeperfeito.view.ViewPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //new ServiceConfigSis().getVariaveisSistema();
        new ServiceConfigSis().getVariaveisSistemaSimples();

//        new ViewLogin().openViewLogin(false);

        new ViewPrincipal().openViewPrincipal();

    }
}
