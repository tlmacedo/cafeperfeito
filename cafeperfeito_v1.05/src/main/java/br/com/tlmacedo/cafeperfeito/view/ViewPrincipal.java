package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.service.ServiceOpenView;
import br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewPrincipal extends Application {

    static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void openViewPrincipal() {
        this.stage = new Stage();
        Parent parent;
        Scene scene = null;

        try {
            parent = FXMLLoader.load(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getFxml().getPrincipal().getFxml()));
            scene = new Scene(parent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle(ServiceVariaveisSistema.TCONFIG.getFxml().getPrincipal().getTitulo());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().setAll(new Image(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getFxml().getPrincipal().getIconeDesativo()).toString()));
        scene.getStylesheets().setAll(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getPersonalizacao().getStyleSheetsMin()).toString());

        new ServiceOpenView(stage, false);
    }


//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        TabColaboradorVO colaboradorVO = new TabColaboradorDAO().getTabColaboradorVO(1);
//        ServiceVariavelSistema.USUARIO_LOGADO_ID = String.valueOf(colaboradorVO.getId());
//        ServiceVariavelSistema.USUARIO_LOGADO_NOME = colaboradorVO.getNome();
//        ServiceVariavelSistema.USUARIO_LOGADO_APELIDO = colaboradorVO.getApelido();
//        ServiceVariavelSistema.DATA_HORA = LocalDateTime.now();
//        ServiceVariavelSistema.DATA_HORA_STR = ServiceVariavelSistema.DATA_HORA.format(DTF_DATAHORA);
//        ServiceVariavelSistema.USUARIO_LOGADO_DATA = LocalDate.now();
//        ServiceVariavelSistema.USUARIO_LOGADO_DATA_STR = ServiceVariavelSistema.USUARIO_LOGADO_DATA.format(DTF_DATA);
//        ServiceVariavelSistema.USUARIO_LOGADO_HORA = LocalTime.now();
//        ServiceVariavelSistema.USUARIO_LOGADO_HORA_STR = ServiceVariavelSistema.USUARIO_LOGADO_HORA.format(DTF_HORA);
//
//        openViewPrincipal();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        ServiceVariavelSistema.newServiceVariavelSistema(null);
//        openViewPrincipal();
    }

}
