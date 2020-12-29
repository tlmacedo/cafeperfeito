package br.com.tlmacedo.cafeperfeito.service.FormatCell;

import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-16
 * Time: 22:37
 */

public class SetCellFactoryTreeTableCell_ComboBox<S, T> extends TreeTableCell<S, T> {

    private ObservableList<T> observableList;
    private JFXComboBox comboBox;

    public SetCellFactoryTreeTableCell_ComboBox(List list) {
        this.observableList = FXCollections.observableArrayList(list);
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createComboBox();
            setText(null);
            setGraphic(comboBox);
            //setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            Platform.runLater(() -> {//without this space erases text, f2 doesn't
                comboBox.show();//also selects
            });
//            if (ControllerPrincipal.getLastKey() != null) {
//                textField.setText(ControllerPrincipal.getLastKey());
//                Platform.runLater(() -> {
//                    textField.deselect();
//                    textField.end();
//                });
//            }
        }
    }

    public void commit() {
        commitEdit((T) comboBox.getSelectionModel().getSelectedItem());
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        try {
            setText(getItem().toString());
//            ControllerPrincipal.setLastKey(null);
        } catch (Exception e) {
        }
        setGraphic(null);
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else if (isEditing()) {
//            if (textField != null) {
//                textField.setText(getString());
//            }
            setText(null);
            setGraphic(comboBox);
        } else {
            setText(getString());
            setGraphic(null);
//            if (tipMascara.contains("#0."))
//                setAlignment(Pos.CENTER_RIGHT);
        }
    }

    private void createComboBox() {
        comboBox = new JFXComboBox(observableList);
//        new ServiceMascara().fieldMask((JFXTextField) textField, tipMascara);

        //doesn't work if clicking a different cell, only focusing out of table
        comboBox.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
                    if (!arg2) commitEdit((T) comboBox.getSelectionModel().getSelectedItem());
                });

        comboBox.setOnKeyReleased((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                commitEdit((T) comboBox.getSelectionModel().getSelectedItem());
                SetCellFactoryTreeTableCell_ComboBox.this.getTreeTableView().getSelectionModel().selectBelowCell();
            }
            if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

        comboBox.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent t) -> {
            if (t.getCode() == KeyCode.DELETE) {
                t.consume();//stop from deleting line in table keyevent
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
