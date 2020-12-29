package br.com.tlmacedo.cafeperfeito.service.FormatCell;

import br.com.tlmacedo.cafeperfeito.controller.ControllerPrincipal;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-22
 * Time: 10:26
 */

public class SetCellFactoryTableCell_EditingCell<T, S> extends TableCell<T, String> {

    private JFXTextField textField;
    private String tipMascara;

    public SetCellFactoryTableCell_EditingCell(String tipMascara) {
        this.tipMascara = tipMascara;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            //setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            Platform.runLater(() -> {//without this space erases text, f2 doesn't
                textField.requestFocus();//also selects
            });
            if (ControllerPrincipal.getLastKey() != null) {
                textField.setText(ControllerPrincipal.getLastKey());
                Platform.runLater(() -> {
                    textField.deselect();
                    textField.end();
                });
            }
        }
    }

    public void commit() {
        commitEdit(textField.getText());
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        try {
            setText(getItem());
            ControllerPrincipal.setLastKey(null);
        } catch (Exception e) {
        }
        setGraphic(null);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else if (isEditing()) {
            if (textField != null) {
                textField.setText(getString());
            }
            setText(null);
            setGraphic(textField);
        } else {
            setText(getString());
            setGraphic(null);
            if (tipMascara.contains("#0."))
                setAlignment(Pos.CENTER_RIGHT);
        }
    }

    private void createTextField() {
        textField = new JFXTextField(getString());
        new ServiceMascara().fieldMask((JFXTextField) textField, tipMascara);

        //doesn't work if clicking a different cell, only focusing out of table
        textField.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
                    if (!arg2) commitEdit(textField.getText());
                });

        textField.setOnKeyReleased((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                commitEdit(textField.getText());
                SetCellFactoryTableCell_EditingCell.this.getTableView().getSelectionModel().selectBelowCell();
            }
            if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

        textField.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent t) -> {
            if (t.getCode() == KeyCode.DELETE) {
                t.consume();//stop from deleting line in table keyevent
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}