package br.com.tlmacedo.cafeperfeito.service.FormatCell;

import br.com.tlmacedo.cafeperfeito.model.vo.ProdutoCodigoBarra;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-06-19
 * Time: 22:04
 */

public class FormatListCell_ProdutoCodigoBarra extends ListCell<ProdutoCodigoBarra> {

    @Override
    protected void updateItem(ProdutoCodigoBarra item, boolean empty) {
        super.updateItem(item, empty);
        if (empty)
            setGraphic(null);
        else
            try {
                setGraphic(new ImageView(item.getImageCodigoBarra()));
            } catch (Exception ex) {
                ex.printStackTrace();
                setGraphic(null);
            }
    }
}
