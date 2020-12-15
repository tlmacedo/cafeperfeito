package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import com.jfoenix.controls.JFXToolbar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_DATAHORA_HMS;
import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.DTF_HORA_HMS;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class ServiceToolBar {

    private Timeline timeline;
    private final JFXToolbar jfxToolbar;
    private final String toolDataBase, toolHorarioLog;
    private Label stbLblCenter;

    public ServiceToolBar(JFXToolbar jfxToolbar, Label stb_lblLeft, Label stb_lblCenter, Label stb_lblRight) {
        this.jfxToolbar = jfxToolbar;
        this.toolDataBase = String.format("%s:%s/%s",
                TCONFIG.getSis().getConnectDB().getDbHost(),
                TCONFIG.getSis().getConnectDB().getDbPorta(),
                TCONFIG.getSis().getConnectDB().getDbDatabase());

        this.toolHorarioLog = DTF_DATAHORA_HMS.format(LocalDateTime.now());

        stb_lblRight.setTooltip(
                new Tooltip(String.format("banco de dados: [%s]\thorário_log: %s",
                        getToolDataBase(), getToolHorarioLog())));
        setTimeline(new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> stb_lblRight.setText(LocalTime.now().format(DTF_HORA_HMS)))));
        getTimeline().setCycleCount(Animation.INDEFINITE);
        getTimeline().play();

        setStbLblCenter(stb_lblCenter);

        stb_lblLeft.setText(
                String.format("Usuário[%02d]: %s",
                        ControllerPrincipal.getUsuarioLogado().getId(),
                        StringUtils.capitalize(ControllerPrincipal.getUsuarioLogado().getApelido())));

    }

    public void teclas(String teclas) {
        getStbLblCenter().setText(teclas);
    }


    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public JFXToolbar getJfxToolbar() {
        return jfxToolbar;
    }

    public String getToolDataBase() {
        return toolDataBase;
    }

    public String getToolHorarioLog() {
        return toolHorarioLog;
    }

    public Label getStbLblCenter() {
        return stbLblCenter;
    }

    public void setStbLblCenter(Label stbLblCenter) {
        this.stbLblCenter = stbLblCenter;
    }
}
