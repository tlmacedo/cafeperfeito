package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.vo.UsuarioLogado;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATAHORA_HMS;
import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_HORA_HMS;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

//import org.apache.maven.surefire.shade.booter.org.apache.commons.lang3.StringUtils;

public class ServiceStatusBar {

    private Timeline timeline;
    private final ToolBar statusBar;
    private Label stbLogadoInf, stbTeclas, stbRelogio;
    private final String toolDataBase;
    private final String tooHorarioLog;

    /**
     * Begin Voids
     */

    public void atualizaStatusBar(String teclas) {
        getStbTeclas().setText(teclas);
    }

    /**
     * END Voids
     */

    public boolean teclaValida(KeyEvent keyEvent) {
        String tecla = keyEvent.getCode().getName().toLowerCase();
        if (keyEvent.isControlDown())
            if (!tecla.equals("ctrl"))
                tecla = "ctrl+" + tecla;
        if (keyEvent.getCode().equals(KeyCode.HELP))
            tecla = tecla.replace("help", "insert");

        System.out.printf("apertou: %s", tecla);
        tecla = tecla + "-";
        if (getStbTeclas().getText().toLowerCase().contains(tecla))
            System.out.printf(" [valid]");
        System.out.printf("...\n");
        return getStbTeclas().getText().toLowerCase().contains(tecla);
//        KeyCode keyCode = keyEvent.getCode();
//        return getStbTeclas().getText().toLowerCase().contains(
//                String.format("%s-", keyCode.equals(KeyCode.HELP) ?
//                        "insert"
//                        : keyCode.toString().toLowerCase()));
    }

    public ServiceStatusBar(ToolBar statusBar, Label lblUsuario, Label lblTeclas, Label lblRelogio) {
        this.statusBar = statusBar;
        setStbLogadoInf(lblUsuario);
        setStbTeclas(lblTeclas);
        setStbRelogio(lblRelogio);
        toolDataBase = String.format(
                "%s:%s/%s",
                TCONFIG.getSis().getConnectDB().getDbHost(),
                TCONFIG.getSis().getConnectDB().getDbPorta(),
                TCONFIG.getSis().getConnectDB().getDbDatabase()
        );
        tooHorarioLog = DTF_DATAHORA_HMS.format(UsuarioLogado.getDataDeLog());
        getStbRelogio().setTooltip(new Tooltip(
                String.format("banco de dados: [%s]\thorário_log: %s",
                        toolDataBase, tooHorarioLog)
        ));
        setTimeline(new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> getStbRelogio().setText(LocalTime.now().format(DTF_HORA_HMS)))));
        getTimeline().setCycleCount(Animation.INDEFINITE);
        getTimeline().play();

        getStbLogadoInf().setText(
                String.format("Usuário [%02d]: %s",
                        UsuarioLogado.getUsuario().idProperty().intValue(),
                        StringUtils.capitalize(UsuarioLogado.getUsuario().apelidoProperty().get()))
        );

    }


    private Timeline getTimeline() {
        return timeline;
    }

    private void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    private ToolBar getStatusBar() {
        return statusBar;
    }

    private Label getStbLogadoInf() {
        return stbLogadoInf;
    }

    private void setStbLogadoInf(Label stbLogadoInf) {
        this.stbLogadoInf = stbLogadoInf;
    }

    private Label getStbTeclas() {
        return stbTeclas;
    }

    private void setStbTeclas(Label stbTeclas) {
        this.stbTeclas = stbTeclas;
    }

    private Label getStbRelogio() {
        return stbRelogio;
    }

    private void setStbRelogio(Label stbRelogio) {
        this.stbRelogio = stbRelogio;
    }
}
