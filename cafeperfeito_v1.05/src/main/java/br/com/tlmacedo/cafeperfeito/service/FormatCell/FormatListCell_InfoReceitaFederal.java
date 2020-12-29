package br.com.tlmacedo.cafeperfeito.service.FormatCell;

import br.com.tlmacedo.cafeperfeito.model.vo.InfoReceitaFederal;
import javafx.scene.control.ListCell;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-20
 * Time: 15:28
 */

public class FormatListCell_InfoReceitaFederal extends ListCell<InfoReceitaFederal> {

    public FormatListCell_InfoReceitaFederal() {
    }

    @Override
    protected void updateItem(InfoReceitaFederal item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("");
        } else {
            setText(item.getDetalheInfoReceitaFederal());
        }
    }
}
