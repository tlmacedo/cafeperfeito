package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import com.jfoenix.controls.JFXToolbar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalTime;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATAHORA;
import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_HORA;

public class ServiceStatusBar implements Serializable {
    private static final long serialVersionUID = 1L;

    Timeline timeline;
    private JFXToolbar statusBar;
    //private Label lblUsuario = new Label(), lblTeclas = new Label(), lblHorario = new Label();
    private Label lblUsuario, lblTeclas, lblHorario;

    public ServiceStatusBar() {
    }

    public JFXToolbar getStatusBar() {
        return statusBar;
    }

    public void setStatusBar(JFXToolbar statusBar) {
        this.statusBar = statusBar;
        this.statusBar.setLeft(lblUsuario);
        this.statusBar.setCenter(lblTeclas);
        this.statusBar.setRight(lblHorario);
    }

    public Label getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(Label lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public Label getLblTeclas() {
        return lblTeclas;
    }

    public void setLblTeclas(Label lblTeclas) {
        this.lblTeclas = lblTeclas;
    }

    public Label getLblHorario() {
        return lblHorario;
    }

    public void setLblHorario(Label lblHorario) {
        this.lblHorario = lblHorario;
    }

    public void atualizaStatusBar() {
        String toolDataBase = String.format("%s:%s/%s",
                ServiceVariaveisSistema.TCONFIG.getSis().getConnectDB().getDbHost(),
                ServiceVariaveisSistema.TCONFIG.getSis().getConnectDB().getDbPorta(),
                ServiceVariaveisSistema.TCONFIG.getSis().getConnectDB().getDbDatabase()
        );
        String toolHorarioLog = DTF_DATAHORA.format(LogadoInf.getUserLogDateTime());
        lblHorario.setTooltip(new Tooltip(String.format("banco de dados: [%s]    horario_log: %s",
                toolDataBase, toolHorarioLog)));

        timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> lblHorario.setText(LocalTime.now().format(DTF_HORA))));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        lblUsuario.setText(String.format("Usuário [%02d]: %s", LogadoInf.getUserLog().idProperty().intValue(),
                StringUtils.capitalize(LogadoInf.getUserLog().getApelido())));
    }

    public void atualizaStatusBar(String teclas) {
        lblTeclas.setText(teclas);
    }
}
