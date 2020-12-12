package br.com.tlmacedo.cafeperfeito.service.alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.Serializable;

public class Alert_YesNo extends AlertMensagem implements Serializable {

    private static final long serialVersionUID = 1L;

    public Alert_YesNo(String cabecalho, String contextText, String icone) {
        setCabecalho(cabecalho);
        setContentText(contextText);
        if (icone != null)
            addImage(icone);

        loadDialog();
        loadDialogPane();

        setBtnYes(new Button());
        getBtns().add(getBtnYes());

        setBtnNo(new Button());
        getBtns().add(getBtnNo());

        addButton();

        getDialog().setResultConverter(o -> {
            if (o == ButtonType.YES)
                return true;
            return false;
        });

        getDialog().showAndWait();
    }

    public boolean retorno() {
//        getDialog().setResultConverter(o -> {
//            if (o == ButtonType.YES)
//                return true;
//            return false;
//        });
//
//        getDialog().showAndWait();

        return (boolean) getDialog().getResult();

    }

//    public Optional<ButtonType> retorno_ButtonType() {
//        return getDialog().showAndWait();
//    }


}
