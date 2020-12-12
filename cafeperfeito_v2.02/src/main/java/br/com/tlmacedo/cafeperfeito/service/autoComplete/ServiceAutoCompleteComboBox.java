package br.com.tlmacedo.cafeperfeito.service.autoComplete;

import br.com.tlmacedo.cafeperfeito.model.vo.Empresa;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;

public class ServiceAutoCompleteComboBox<T> {

    private final Class<T> classe;
    private ComboBox<T> comboBox;
    private ObservableList<T> observableList;
    private FilteredList<T> filteredList;

    private boolean moveCaretToPos = false;
    private int caretPos;

    public ServiceAutoCompleteComboBox(Class<T> classe, ComboBox<T> comboBox) {
        this.classe = classe;
        setComboBox(comboBox);
        new ServiceMascara().fieldMask(getComboBox().getEditor(), "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");

        setFilteredList(new FilteredList<>(getComboBox().getItems()));
        getComboBox().setItems(getFilteredList());

        getComboBox().focusedProperty().addListener((ov, o, n) -> {
            if (n) {
                getComboBox().setEditable(true);
                Platform.runLater(() -> getComboBox().getEditor().selectAll());
            } else {
                getComboBox().setEditable(false);
                try {
//                    if (getObservableList().contains(getComboBox().getSelectionModel().getSelectedItem()))
                    getComboBox().setValue(getComboBox().getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                    getComboBox().setValue(null);
                }
            }
        });
        getComboBox().setOnKeyPressed(t -> getComboBox().hide());
        getComboBox().setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ESCAPE)
                return;
            if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.RIGHT
                    || event.getCode() == KeyCode.LEFT || event.getCode().equals(KeyCode.SHIFT)
                    || event.getCode().equals(KeyCode.CONTROL) || event.isControlDown()
                    || event.getCode() == KeyCode.HOME || event.getCode() == KeyCode.END
                    || event.getCode() == KeyCode.TAB) {
                return;
            }
            if (event.getCode() == KeyCode.ENTER) {
                if (getComboBox().getSelectionModel().getSelectedItem() != null) {
                    getComboBox().setValue(getComboBox().getSelectionModel().getSelectedItem());
                    getComboBox().hide();
                    getComboBox().setEditable(false);
                }
                return;
            }

            switch (getClasse().getSimpleName().toLowerCase()) {
                case "empresa":
                    getFilteredList().setPredicate(flist -> {
                        if (((Empresa) flist).getRazao() == null)
                            return false;
                        else if (((Empresa) flist).getRazao().toLowerCase().contains(
                                ServiceAutoCompleteComboBox.this.
                                        getComboBox().getEditor().getText().toLowerCase()))
                            return true;
                        else if (((Empresa) flist).getFantasia().toLowerCase().contains(
                                ServiceAutoCompleteComboBox.this.
                                        getComboBox().getEditor().getText().toLowerCase()))
                            return true;
                        return false;
                    });
                    break;
                default:
                    getFilteredList().setPredicate(flist -> {
                        if (flist.toString().toLowerCase().contains(
                                ServiceAutoCompleteComboBox.this.
                                        getComboBox().getEditor().getText().toLowerCase()))
                            return true;
                        return false;
                    });
                    break;
            }
            if (!getFilteredList().isEmpty())
                getComboBox().show();

        });

        getComboBox().setConverter(new StringConverter<T>() {
            @Override
            public String toString(T object) {
                if (object != null)
                    return object.toString();
                return null;
            }

            @Override
            public T fromString(String string) {
                for (T obj : getFilteredList())
                    if (obj.toString().toLowerCase().contains(string.toLowerCase()))
                        return obj;
                return null;
            }
        });
    }

    public void clearCombo() {
        getFilteredList().setPredicate(t -> true);
        getComboBox().getEditor().clear();
        getComboBox().setValue(null);
    }

    public Class<T> getClasse() {
        return classe;
    }

    public ComboBox<T> getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }

    public ObservableList<T> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<T> observableList) {
        this.observableList = observableList;
    }

    public FilteredList<T> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(FilteredList<T> filteredList) {
        this.filteredList = filteredList;
    }

}
