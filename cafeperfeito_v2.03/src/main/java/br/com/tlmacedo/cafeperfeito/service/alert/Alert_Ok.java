package br.com.tlmacedo.cafeperfeito.service.alert;

import javafx.scene.control.Button;

import java.io.Serializable;

public class Alert_Ok extends AlertMensagem implements Serializable {

    private static final long serialVersionUID = 1L;

    public Alert_Ok(String cabecalho, String contextText, String icone) {
        setCabecalho(cabecalho);
        setContentText(contextText);
        if (icone != null)
            addImage(icone);

        loadDialog();
        loadDialogPane();

        setBtnOk(new Button());
        getBtns().add(getBtnOk());
        addButton();

        getBtnOk().setDisable(false);

        getDialog().showAndWait();

    }

}
