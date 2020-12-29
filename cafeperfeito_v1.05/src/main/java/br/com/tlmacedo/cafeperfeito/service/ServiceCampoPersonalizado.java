package br.com.tlmacedo.cafeperfeito.service;

import com.jfoenix.controls.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.*;

public class ServiceCampoPersonalizado {

    //999@_CNPJ
    //(0, 3) qtdMax
    //(3, 4) Tipo [Numero] [AlfaNumerico]
    //(4, 5) Vlr campo Limpo
    //(5, 6) Deshabilitado
    //(6   ) Mascara
    // # -> numero
    // $ -> moeda
    // @ -> alfanum Maiusculo
    // ? -> alfanum Minusculo

    public static void fieldClear(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            String vlrInicial = "";
            HashMap<String, String> hashMap = null;
            if (node.getAccessibleText() != null) {
                try {
                    hashMap = ServiceMascara.getFieldFormatMap(node.getAccessibleText());
                    if (!hashMap.containsKey("value")) continue;
                    if ((vlrInicial = hashMap.get("value")) == null)
                        vlrInicial = "";

                    if (hashMap.containsKey("binding"))
                        if (hashMap.get("binding").equals("true"))
                            continue;
                    if (node instanceof Label)
                        ((Label) node).setText(vlrInicial);
                    else if (node instanceof JFXTextField)
                        ((JFXTextField) node).setText(vlrInicial);
                    else if (node instanceof JFXCheckBox)
                        ((JFXCheckBox) node).setSelected(vlrInicial.equals("true") || vlrInicial.equals("verdadeiro"));
                    else if (node instanceof JFXComboBox)
                        if (vlrInicial.equals("")) {
                            ((JFXComboBox) node).getSelectionModel().select(-1);
                        } else {
                            ((JFXComboBox) node).getSelectionModel().select(Integer.parseInt(vlrInicial));
                        }
                    else if (node instanceof ImageView)
                        ((ImageView) node).setImage(null);

                    else if (node instanceof DatePicker || node instanceof JFXDatePicker) {
                        LocalDate localDate = null;
                        if (vlrInicial.equals("now"))
                            localDate = LocalDate.now();
                        else //if (vlrInicial.equals(""))
                            localDate = LocalDate.parse(vlrInicial);
                        if (node instanceof DatePicker)
                            ((DatePicker) node).setValue(localDate);
                        else
                            ((JFXDatePicker) node).setValue(localDate);
                    } else if (node instanceof JFXTimePicker) {
                        LocalTime localTime = null;
                        if (vlrInicial.equals("now"))
                            ((JFXTimePicker) node).setValue(LocalTime.now());
                        else
                            ((JFXTimePicker) node).setValue(LocalTime.parse(vlrInicial));
                    } else if (node instanceof JFXTreeTableView)
                        ((JFXTreeTableView) node).getColumns().clear();
                    else if (node instanceof TableView)
                        ((TableView) node).getItems().clear();
                    else if (node instanceof Circle)
                        ((Circle) node).setFill(FUNDO_RADIAL_GRADIENT);
                    else if (node instanceof JFXListView)
                        ((JFXListView) node).getItems().clear();
                } catch (Exception ex) {
                    if (ex instanceof RuntimeException) {
                        new ServiceAlertMensagem("Erro", String.format("tente colocar no FXML do campo: [%s] a informação: [%s]\n",
                                node.getId(), "binding::true;"), null).getRetornoAlert_OK();
                    } else {
                        ex.printStackTrace();
                    }
                    System.out.printf("node_Errrrrrrr: [%s]\n", node.getId());
                    ex.printStackTrace();
                }
            }
            if (node instanceof AnchorPane)
                fieldClear((AnchorPane) node);
            else if (node instanceof TitledPane)
                fieldClear((AnchorPane) ((TitledPane) node).getContent());
            else if (node instanceof TabPane)
                for (Tab tab : ((TabPane) node).getTabs())
                    fieldClear((AnchorPane) tab.getContent());
        }
    }

    public static void fieldDisable(AnchorPane anchorPane, boolean setDisable) {
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof DatePicker)
                ((DatePicker) node).setDisable(setDisable);
            else if (node instanceof JFXDatePicker)
                ((JFXDatePicker) node).setDisable(setDisable);
            else if (node instanceof JFXTimePicker)
                ((JFXTimePicker) node).setDisable(setDisable);
            else if (node instanceof JFXTextField)
                ((JFXTextField) node).setDisable(setDisable);
            else if (node instanceof JFXTextArea)
                ((JFXTextArea) node).setDisable(setDisable);
            else if (node instanceof TreeTableView)
                ((TreeTableView) node).setDisable(setDisable);
            else if (node instanceof TableView)
                ((TableView) node).setDisable(setDisable);
            else if (node instanceof JFXComboBox)
                ((JFXComboBox) node).setDisable(setDisable);
            else if (node instanceof ComboBox)
                ((ComboBox) node).setDisable(setDisable);
            else if (node instanceof ImageView)
                ((ImageView) node).setDisable(setDisable);
            else if (node instanceof Circle)
                ((Circle) node).setDisable(setDisable);
            else if (node instanceof JFXCheckBox)
                ((JFXCheckBox) node).setDisable(setDisable);
            else if (node instanceof AnchorPane)
                fieldDisable((AnchorPane) node, setDisable);
            else if (node instanceof TitledPane)
                fieldDisable((AnchorPane) ((TitledPane) node).getContent(), setDisable);
            else if (node instanceof TabPane)
                for (Tab tab : ((TabPane) node).getTabs())
                    fieldDisable((AnchorPane) tab.getContent(), setDisable);
            else if (node instanceof TabPane)
                for (Tab tab : ((TabPane) node).getTabs())
                    fieldDisable((AnchorPane) tab.getContent(), setDisable);
        }
    }


    public static void fieldMask(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            boolean editOK = true;
            HashMap<String, String> hashMap = null;
            if (node.getAccessibleText() != null) {
                hashMap = ServiceMascara.getFieldFormatMap(node.getAccessibleText());
                if (hashMap.containsKey("seteditable"))
                    editOK = !(hashMap.get("seteditable").equals("false"));
            }
            if (node instanceof JFXTextField && hashMap != null) {
                ((JFXTextField) node).setEditable(editOK);
                int len = 0, decimal = 0;
                String mascara = null;
                String type = "";
                if (hashMap.containsKey("len"))
                    len = hashMap.get("len").equals("") ? 0 : Integer.parseInt(hashMap.get("len"));
                if (hashMap.containsKey("decimal"))
                    decimal = hashMap.get("decimal").equals("") ? 0 : Integer.parseInt(hashMap.get("decimal"));
                if (hashMap.containsKey("type")) {
                    if ((type = hashMap.get("type")).equals(""))
                        type = "TEXTO";
                    switch (type) {
                        case "texto":
                            mascara = ServiceMascara.getTextoMask(len, ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getLower());
                            break;
                        case "Texto":
                            mascara = ServiceMascara.getTextoMask(len, ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getInterrogacao());
                            break;
                        case "TEXTO":
                            mascara = ServiceMascara.getTextoMask(len, ServiceVariaveisSistema.TCONFIG.getSis().getMaskCaracter().getUpper());
                            break;
                        case "numero":
                        case "moeda":
                        case "valor":
                        case "peso":
                            mascara = ServiceMascara.getNumeroMask(len, decimal);
                            break;
                        case "cnpj":
                            mascara = MASK_CNPJ;
                            break;
                        case "cpf":
                            mascara = MASK_CPF;
                            break;
                        case "rg":
                            mascara = ServiceMascara.getRgMask(len);
                            break;
                        case "ncm":
                            mascara = MASK_NCM;
                            break;
                        case "cest":
                            mascara = MASK_CEST;
                            break;
                        case "cep":
                            mascara = MASK_CEP;
                            break;
                        case "nfe_chave":
                        case "cte_chave":
                            mascara = MASK_NFE_CHAVE;
                            break;
                        case "nfe_numero":
                        case "cte_numero":
                            mascara = ServiceMascara.getNumeroMilMask(10);
                            break;
                        case "fiscal_doc_origem":
                            mascara = MASK_FISCAL_DOC_ORIGEM;
                            break;
                    }
                    if (mascara != null)
                        new ServiceMascara().fieldMask((JFXTextField) node, mascara);
                }
            } else if (node instanceof JFXTextArea) {
                ((JFXTextArea) node).setEditable(editOK);
            } else if (node instanceof JFXDatePicker) {
                ((JFXDatePicker) node).setEditable(editOK);
                new ServiceMascara().fieldMask(((JFXDatePicker) node).getEditor(), "##/##/####");
            } else if (node instanceof AnchorPane) {
                fieldMask((AnchorPane) node);
            } else if (node instanceof TitledPane) {
                fieldMask((AnchorPane) ((TitledPane) node).getContent());
            } else if (node instanceof TabPane) {
                for (Tab tab : ((TabPane) node).getTabs())
                    fieldMask((AnchorPane) tab.getContent());
            } else if (node instanceof TabPane) {
                for (Tab tab : ((TabPane) node).getTabs())
                    fieldMask((AnchorPane) tab.getContent());
            }
        }
    }

}

