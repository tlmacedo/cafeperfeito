package br.com.tlmacedo.cafeperfeito.service.format.cell;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;

import java.util.List;

public class SetCellFactoryTableCell_ComboBox<S, T> extends TableCell<S, T> {

    private final ObservableList<T> items;
    private ComboBox<T> comboBox;

    public SetCellFactoryTableCell_ComboBox(List<T> list) {
        items = FXCollections.observableArrayList(list);
    }

    private void createComboBox() {
        setComboBox(new ComboBox<>(items));


        getComboBox().setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                commit();
            } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

        getComboBox().getEditor().focusedProperty().addListener(o -> {
            if (!getComboBox().isFocused()) {
                commit();
            }
        });
    }

    private void commit() {
        StringConverter<T> sc = getComboBox().getConverter();
        if (getComboBox().isEditable() && sc != null) {
            T value = sc.fromString(getComboBox().getEditor().getText());
            commitEdit(value);
        } else {
            commitEdit(getComboBox().getValue());
        }
    }


    @Override
    public void startEdit() {
        if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable())
            return;

        if (getComboBox() == null)
            createComboBox();
        getComboBox().getSelectionModel().select(getItem());

        super.startEdit();
        setText(null);
        setGraphic(getComboBox());
        Platform.runLater(() -> getComboBox().show());
        getComboBox().requestFocus();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem().toString());
        setGraphic(null);
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (isEmpty()) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (getComboBox() != null)
                    getComboBox().getSelectionModel().select(getItem());
                setText(null);
                setGraphic(getComboBox());
            } else {
                setText(getItem().toString());
                setGraphic(null);
            }
        }
    }

    public ObservableList<T> getItems() {
        return items;
    }

    public ComboBox<T> getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }


}
