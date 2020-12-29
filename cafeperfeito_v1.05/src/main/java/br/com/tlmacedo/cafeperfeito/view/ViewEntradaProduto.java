package br.com.tlmacedo.cafeperfeito.view;

import br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

import java.io.IOException;

public class ViewEntradaProduto {
    static String tituloJanela;
    static Tab tab;

    public static String getTituloJanela() {
        return tituloJanela;
    }

    public static void setTituloJanela(String tituloJanela) {
        ViewEntradaProduto.tituloJanela = tituloJanela;
    }

    public static Tab getTab() {
        return tab;
    }

    public static void setTab(Tab tab) {
        ViewEntradaProduto.tab = tab;
    }

    @SuppressWarnings("Duplicates")
    public Tab openTabEntradaProduto(String tituloJanela) {
        setTituloJanela(tituloJanela);
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getFxml().getEntradaProduto().getFxml()));
            parent.getStylesheets().setAll(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getPersonalizacao().getStyleSheets()).toString());

            setTab(new Tab(tituloJanela));
            getTab().setContent(parent);
            return getTab();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
