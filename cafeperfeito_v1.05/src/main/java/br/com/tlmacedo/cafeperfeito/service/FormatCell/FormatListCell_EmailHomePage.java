package br.com.tlmacedo.cafeperfeito.service.FormatCell;

import br.com.tlmacedo.cafeperfeito.model.vo.EmailHomePage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-20
 * Time: 15:30
 */

public class FormatListCell_EmailHomePage extends ListCell<EmailHomePage> {
    private ImageView[] imageView = new ImageView[4];
    private CheckBox chkPrincipal = new CheckBox();
    private CheckBox chkNfe = new CheckBox();

    public FormatListCell_EmailHomePage() {
        imageView[0] = new ImageView(new Image("image/ico/ic_home_page_dp16.png"));
        imageView[1] = new ImageView(new Image("image/ico/ic_email_dp16.png"));
        imageView[2] = new ImageView(new Image("image/ico/ic_email_principal_dp16.png"));
        imageView[3] = new ImageView(new Image("image/ico/ic_email_nfe_dp16.png"));
    }

    @Override
    protected void updateItem(EmailHomePage item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            HBox hBoxSub, hBox = new HBox();
            hBox.setSpacing(6);
            if (item.isMail()) {
                chkPrincipal.setSelected(item.isPrincipal());
                item.principalProperty().bind(chkPrincipal.selectedProperty());
                chkNfe.setSelected(item.isNfe());
                item.nfeProperty().bind(chkNfe.selectedProperty());

                hBox.getChildren().add(imageView[1]);

                hBoxSub = new HBox();
                hBoxSub.setSpacing(4);
                hBoxSub.getChildren().add(chkPrincipal);
                hBoxSub.getChildren().add(imageView[2]);
                hBox.getChildren().add(hBoxSub);

                hBoxSub = new HBox();
                hBoxSub.setSpacing(4);
                hBoxSub.getChildren().add(chkNfe);
                hBoxSub.getChildren().add(imageView[3]);
                hBox.getChildren().add(hBoxSub);
            } else {
                hBox.getChildren().add(imageView[0]);

            }
//            try {
//                if (item.getTipo().equals(WebTipo.EMAIL))
//                    imageView.setImage(new Image(ServiceVariaveisSistema.PATHICONE + "ic_email@_dp14.png"));
//                else
//                    imageView.setImage(new Image(ServiceVariaveisSistema.PATHICONE + "ic_webhome_dp14.png"));
//                hBox.getChildren().add(imageView);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
            setGraphic(hBox);
            setText(item.toString());
        }
    }

}
