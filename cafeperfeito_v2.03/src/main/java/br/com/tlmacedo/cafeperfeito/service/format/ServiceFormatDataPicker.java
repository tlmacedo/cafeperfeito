package br.com.tlmacedo.cafeperfeito.service.format;

import javafx.scene.control.DatePicker;

import java.time.format.DateTimeParseException;

public class ServiceFormatDataPicker {

    public static void formatDataPicker(DatePicker dtp, boolean isFocused) {
        if (!isFocused) {
            try {
                dtp.setValue(dtp.getConverter().fromString(dtp.getEditor().getText()));
            } catch (DateTimeParseException e) {
                dtp.getEditor().setText(dtp.getConverter().toString(dtp.getValue()));
            }
        } else {
            dtp.getEditor().selectAll();
        }
    }
}
