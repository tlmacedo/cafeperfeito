package br.com.tlmacedo.cafeperfeito.service.format;

import javafx.scene.control.DateCell;

import java.time.LocalDate;

public class FormatDataPicker extends DateCell {


    private LocalDate date;

    public FormatDataPicker(LocalDate date) {
        setDate(date);
        if (getDate() == null)
            setDate(LocalDate.now());
    }

    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);
        if (item.isBefore(getDate())) {
            setDisable(true);
            setStyle("-fx-background-color: rgba(255,102,0,0.59);");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
