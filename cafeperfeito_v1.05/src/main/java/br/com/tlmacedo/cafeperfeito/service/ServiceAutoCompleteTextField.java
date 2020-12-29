package br.com.tlmacedo.cafeperfeito.service;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXTextField;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-05-15
 * Time: 11:38
 */

public class ServiceAutoCompleteTextField {

    public ServiceAutoCompleteTextField(final JFXTextField jfxTextField, List<String> listItens) {

        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        autoCompletePopup.setCellLimit(10);
        autoCompletePopup.setPrefSize(jfxTextField.getPrefWidth(), jfxTextField.getPrefHeight());
        autoCompletePopup.getSuggestions().setAll(listItens);
        autoCompletePopup.setSelectionHandler(event -> {
            jfxTextField.setText(event.getObject());
            jfxTextField.positionCaret(jfxTextField.getLength());
        });

//        jfxTextField.focusedProperty().addListener((ov, o, n) -> {
//            if (n) {
        jfxTextField.textProperty().addListener((ov1, o1, n1) -> {
            if (n1.length() > 0 && jfxTextField.isFocused() && !jfxTextField.isDisabled()) {
                autoCompletePopup.filter(item -> item.toLowerCase().contains(jfxTextField.getText().toLowerCase()));
                if (autoCompletePopup.getFilteredSuggestions().isEmpty())
                    autoCompletePopup.hide();
                else
                    autoCompletePopup.show(jfxTextField);
            } else {
                autoCompletePopup.hide();
            }
        });
//            } else {
//                autoCompletePopup.hide();
//            }
//        });
    }
}