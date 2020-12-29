package br.com.tlmacedo.cafeperfeito.service.FormatCell;
/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-22
 * Time: 12:25
 */

import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import com.jfoenix.controls.JFXDatePicker;
import javafx.scene.control.TableCell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.DTF_DATA;


public class SetCellFactoryTableCell_DatePicker<S> extends TableCell<S, LocalDate> {

    private JFXDatePicker picker;
    private DateTimeFormatter formatter;

    public SetCellFactoryTableCell_DatePicker() {
        this(null);
        this.formatter = DTF_DATA;
    }

    /**
     * Creates a new table cell with date picker
     *
     * @param formatter A {@link DateTimeFormatter} that can convert the selected
     *                  LocalDate (from what the user typed in) into an string with the given format.
     */
    public SetCellFactoryTableCell_DatePicker(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void startEdit() {
        if (!isEditable()
                || !getTableView().isEditable()
                || !getTableColumn().isEditable()) {
            return;
        }
        super.startEdit();
        if (isEditing()) {
            if (picker == null) {
                picker = new JFXDatePicker();

                // ATTENTION: Value MUST be set before the change listener(!)
                picker.setValue(this.getItem());


                // stop editing when loosing focus
                // (e.g. when another cell is clicked)
                picker.focusedProperty().addListener(
                        (observable, oldValue, newValue) -> {
                            if (!newValue)
                                cancelEdit();
                        }
                );

                // make sure events propagate back through to the data model
                picker.valueProperty().addListener(
                        (observable, oldValue, newValue) -> commitEdit(newValue));
            }


            this.setText(null);
            this.setGraphic(picker);
            new ServiceMascara().fieldMask(picker.getEditor(), "##/##/####");
            picker.requestFocus();
            picker.getEditor().selectAll();
        }
    }

    @Override
    protected void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        if (isEmpty()) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                setText(null);
                setGraphic(picker);
            } else {
                setGraphic(null);
                setText(formatText(item));
            }
        }
    }

    private String formatText(LocalDate item) {
        if (item == null)
            return null;
        else if (formatter != null)
            return formatter.format(item);
        else
            return item.toString();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(formatText(getItem()));
        setGraphic(null);
    }
}