package br.com.tlmacedo.cafeperfeito.service;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ServiceComandoTecladoMouse {

    public static KeyEvent pressTecla(KeyCode keyCode) {
        return new KeyEvent(KeyEvent.KEY_PRESSED, "", "", keyCode, false, false, false, false);
    }

    public static MouseEvent clickMouse(int qtdClicks) {
        if (qtdClicks <= 0) qtdClicks = 1;
        return new MouseEvent(MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, MouseButton.PRIMARY, qtdClicks,
                false, false, false, false,
                false, false, false,
                false, false, false, null);
    }
}
