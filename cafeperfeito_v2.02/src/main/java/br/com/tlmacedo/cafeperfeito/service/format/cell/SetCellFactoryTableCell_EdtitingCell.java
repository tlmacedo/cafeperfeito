package br.com.tlmacedo.cafeperfeito.service.format.cell;

import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;

public class SetCellFactoryTableCell_EdtitingCell<S, T> extends TableCell<S, String> {

    private TextField textField;
    private String tipMascara;
    private ObjectProperty<StringConverter<T>> converter = new SimpleObjectProperty<>(this, "converter");

    public SetCellFactoryTableCell_EdtitingCell(String tipMascara) {
        this.tipMascara = tipMascara;
    }

    private <T> void createTextField() {
        setTextField(new TextField(getItem()));
        new ServiceMascara().fieldMask(getTextField(), getTipMascara());

        getTextField().setOnAction(actionEvent -> {
            if (converter == null) {
                throw new IllegalStateException("eroou");
            }
            commitEdit(textField.getText());
            actionEvent.consume();
        });
        getTextField().setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
                keyEvent.consume();
            }
        });
    }


//    private static <T> String getItemText(Cell<T> cell, StringConverter<T> converter) {
//        return converter == null ?
//                cell.getItem() == null ? "" : cell.getItem().toString() :
//                converter.toString(cell.getItem());
//    }

    @Override
    public void startEdit() {
        if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable())
            return;

        super.startEdit();

        if (isEditing()) {
            if (getTextField() == null)
                createTextField();

            if (getTextField() != null)
                getTextField().setText(getItem());

            setText(null);
            setGraphic(getTextField());

            //getTextField().selectAll();
            getTextField().requestFocus();
            if (ControllerPrincipal.getLastKey() != null) {
                getTextField().setText(ControllerPrincipal.getLastKey());
                Platform.runLater(() -> {
                    getTextField().deselect();
                    getTextField().end();
                });
            }
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem());
        setGraphic(null);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (isEmpty()) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (getTextField() != null)
                    getTextField().setText(getItem());
                setText(null);
                setGraphic(getTextField());
            } else {
                setText(getItem());
                setGraphic(null);
            }
        }
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public String getTipMascara() {
        return tipMascara;
    }

    public void setTipMascara(String tipMascara) {
        this.tipMascara = tipMascara;
    }

    public final StringConverter<T> getConverter() {
        return converterProperty().get();
    }

    public final ObjectProperty<StringConverter<T>> converterProperty() {
        return converter;
    }

    public final void setConverter(StringConverter<T> value) {
        converterProperty().set(value);
    }
}
