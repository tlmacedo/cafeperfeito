package br.com.tlmacedo.cafeperfeito.service.FormatCell;

import br.com.tlmacedo.cafeperfeito.model.vo.FiscalCestNcm;
import javafx.scene.control.ListCell;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-06-19
 * Time: 21:59
 */

public class FormatListCell_FiscalCestNcm extends ListCell<FiscalCestNcm> {


    @Override
    protected void updateItem(FiscalCestNcm item, boolean empty) {
        super.updateItem(item, empty);
        if (empty)
            setText("");
        else
            setText(item.getDetalheCestNcm());
    }
}
