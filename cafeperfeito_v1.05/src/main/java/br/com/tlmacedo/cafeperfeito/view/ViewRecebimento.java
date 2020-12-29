package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.model.vo.ContasAReceber;
import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.service.ServiceOpenView;
import br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

@SuppressWarnings("Duplicates")
public class ViewRecebimento { // extends Application {

    static Stage stage;
    static ContasAReceber aReceber;
    static Recebimento recebimento;

    public static Stage getStage() {
        return stage;
    }

    public static ContasAReceber getaReceber() {
        return aReceber;
    }

    public static void setaReceber(ContasAReceber aReceber) {
        ViewRecebimento.aReceber = aReceber;
    }

    public static Recebimento getRecebimento() {
        return recebimento;
    }

    public static void setRecebimento(Recebimento recebimento) {
        ViewRecebimento.recebimento = recebimento;
    }

    public void openViewRecebimento(ContasAReceber aReceber, Recebimento recebimento) {
        if (aReceber != null)
            setaReceber(aReceber);
        if (recebimento != null)
            setRecebimento(recebimento);
        this.stage = new Stage();
        Parent parent;
        Scene scene = null;

        try {
            parent = FXMLLoader.load(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getFxml().getRecebimento().getFxml()));
            scene = new Scene(parent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle(ServiceVariaveisSistema.TCONFIG.getFxml().getRecebimento().getTitulo());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().setAll(new Image(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getFxml().getRecebimento().getIcone()).toString()));
        stage.initStyle(StageStyle.UNDECORATED);
        //scene.getStylesheets().setAll(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getPersonalizacao().getStyleSheetsMin()).toString());

        new ServiceOpenView(stage, true);
    }
}
