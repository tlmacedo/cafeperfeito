package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.vo.FiscalCestNcm;
import br.com.tlmacedo.cafeperfeito.model.vo.FiscalCstOrigem;
import br.com.tlmacedo.cafeperfeito.model.vo.Usuario;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-05-15
 * Time: 11:38
 */

public class ServiceAutoCompleteComboBox<T> implements EventHandler<KeyEvent> {
    private final Class<T> classe;
    private JFXComboBox<T> jfxComboBox;
    private ObservableList<T> observableList;
    private FilteredList<T> filteredList;
    //    private boolean moveCaretToPos = false;
//    private int caretPos;
    private ServiceMascara editorTexto;
    private StringProperty mascara = new SimpleStringProperty("");

    public ServiceAutoCompleteComboBox(final JFXComboBox<T> jfxComboBox, Class<T> classe) {
        this.classe = classe;
        setJfxComboBox(jfxComboBox);
        setObservableList(getJfxComboBox().getItems());
        setFilteredList(new FilteredList<T>(getObservableList()));
        getJfxComboBox().setItems(getFilteredList());

        getJfxComboBox().setEditable(true);
        getJfxComboBox().setOnKeyPressed(t -> getJfxComboBox().hide());
        getJfxComboBox().setOnKeyReleased(ServiceAutoCompleteComboBox.this);

        setEditorTexto(new ServiceMascara());
        getEditorTexto().fieldMask(getJfxComboBox().getEditor(), "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");

        if (!Arrays.asList("usuario", "fiscalcestncm", "fiscalcstorigem").contains(getClasse().getSimpleName().toLowerCase())) {
            ServiceAlertMensagem alertMensagem = new ServiceAlertMensagem();
            alertMensagem.setCabecalho(String.format("classe [%s] não habilitada para autocomplete", getClasse().getSimpleName()));
            alertMensagem.getRetornoAlert_OK();
            //return;
        }

        getJfxComboBox().setConverter(new StringConverter<T>() {
            @Override
            public String toString(Object object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public T fromString(String string) {
                switch (getClasse().getSimpleName()) {
                    case "Usuario":
                        for (T obj : getJfxComboBox().getItems())
                            if (((Usuario) obj).getEmail().toLowerCase().equals(string.toLowerCase()))
                                return obj;
                        break;
                    case "FiscalCestNcm":
                        for (T obj : getJfxComboBox().getItems())
                            if (((FiscalCestNcm) obj).toString().toLowerCase().equals(string.toLowerCase()))
                                return obj;
                        break;
                    case "FiscalCstOrigem":
                        for (T obj : getJfxComboBox().getItems())
                            if (((FiscalCstOrigem) obj).toString().toLowerCase().equals(string.toLowerCase()))
                                return obj;
                        break;
                    default:
                        for (T obj : getJfxComboBox().getItems())
                            if (obj.toString().toLowerCase().equals(string.toLowerCase()))
                                return obj;
                        break;
                }
                return null;
            }
        });
    }

    @Override
    public void handle(KeyEvent event) {
        switch (getClasse().getSimpleName()) {
            case "Usuario":
                getFilteredList().setPredicate(flist -> {
                    if (((Usuario) flist).getNome().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    else if (((Usuario) flist).getApelido().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    else if (((Usuario) flist).getEmail().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    else
                        return false;
                });
                break;
            case "FiscalCestNcm":
                getFilteredList().setPredicate(flist -> {
                    if (((FiscalCestNcm) flist).getDescricao().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    else if (((FiscalCestNcm) flist).getNcm().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    else if (((FiscalCestNcm) flist).getCest().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    else if (((FiscalCestNcm) flist).getSegmento().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    else
                        return false;
                });
                break;
            case "FiscalCstOrigem":
                getFilteredList().setPredicate(flist -> {
                    if (((FiscalCstOrigem) flist).getDescricao().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    return false;
                });
            default:
                getFilteredList().setPredicate(flist -> {
                    if (flist.toString().toLowerCase().contains(
                            ServiceAutoCompleteComboBox.this.getJfxComboBox()
                                    .getEditor().getText().toLowerCase()))
                        return true;
                    return false;
                });
                break;
        }
        if (!getFilteredList().isEmpty()) {
            getJfxComboBox().show();
        }
    }

    public Class<T> getClasse() {
        return classe;
    }

    public JFXComboBox<T> getJfxComboBox() {
        return jfxComboBox;
    }

    public void setJfxComboBox(JFXComboBox<T> jfxComboBox) {
        this.jfxComboBox = jfxComboBox;
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

    public ServiceMascara getEditorTexto() {
        return editorTexto;
    }

    public void setEditorTexto(ServiceMascara editorTexto) {
        this.editorTexto = editorTexto;
    }

    public String getMascara() {
        return mascara.get();
    }

    public void setMascara(String mascara) {
        this.mascara.set(mascara);
    }

    public StringProperty mascaraProperty() {
        return mascara;
    }
}