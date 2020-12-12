package br.com.tlmacedo.cafeperfeito.service;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.*;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class ServiceCampoPersonalizado {

    public static void fieldClear(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            fieldClear(node);
        }
    }

    public static void fieldClear(Node node) {
        if (node.getAccessibleText() != null) {
            try {
                String value;
                HashMap<String, String> hashMap = ServiceMascara.getFieldFormatMap(node.getAccessibleText());
                if (!hashMap.containsKey("value")) return;
                if ((value = hashMap.get("value")) == null)
                    value = "";

                if (hashMap.containsKey("binding"))
                    if (hashMap.get("binding").equals("true"))
                        return;
                if (hashMap.containsKey("type")) {
                    if (value.equals("now"))
                        switch (hashMap.get("type")) {
                            case "timeHMS":
                                value = LocalTime.now().format(DTF_HORA_HMS);
                                break;
                            case "timeHM":
                                value = LocalTime.now().format(DTF_HORA_HM);
                                break;
                            case "date":
                                value = LocalDate.now().format(DTF_DATA);
                                break;
                            case "dateTimeHMS":
                                value = LocalDateTime.now().format(DTF_DATAHORA_HMS);
                                break;
                            case "dateTimeHM":
                                value = LocalDateTime.now().format(DTF_DATAHORA_HM);
                                break;
                        }
                }
                if (node instanceof Label)
                    ((Label) node).setText(value);
                else if (node instanceof TextField)
                    ((TextField) node).setText(value);
                else if (node instanceof TextArea)
                    ((TextArea) node).setText(value);
                else if (node instanceof CheckBox)
                    ((CheckBox) node).setSelected(value.equals("true") || value.equals("1"));
                else if (node instanceof ComboBox)
                    ((ComboBox) node).getSelectionModel().select(value.equals("") ? -1 : Integer.parseInt(value));
                else if (node instanceof ImageView)
                    ((ImageView) node).setImage(null);
                else if (node instanceof DatePicker) {
                    if (value.equals("") || value.equals("now"))
                        ((DatePicker) node).setValue(LocalDate.now());
                    else if (value.contains("-"))
                        ((DatePicker) node).setValue(LocalDate.now().minusDays(Integer.parseInt(value)));
                    else
                        ((DatePicker) node).setValue(LocalDate.now().plusDays(Integer.parseInt(value)));
                }
//                else if (node instanceof TreeTableView)
//                    ((TableView) node).getColumns().clear();
                else if (node instanceof TableView)
                    ((TableView) node).getItems().clear();
                else if (node instanceof Circle)
                    ((Circle) node).setFill(FUNDO_RADIAL_GRADIENT);
                else if (node instanceof ListView)
                    ((ListView) node).getItems().clear();
            } catch (Exception ex) {
                System.out.printf("node_Errrrrrrr: [%s]*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n" +
                        "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n" +
                        "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n", node.getId());
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
        else if (node instanceof VBox)
            for (Node nod : ((VBox) node).getChildren())
                fieldClear(nod);
    }

    public static void fieldDisable(AnchorPane anchorPane, boolean setDisable) {
        for (Node node : anchorPane.getChildren()) {
            fieldClear(node);
        }
    }

    public static void fieldDisable(Node node, boolean setDisable) {
        if (node instanceof TextField)
            ((TextField) node).setDisable(setDisable);
        else if (node instanceof CheckBox)
            ((CheckBox) node).setDisable(setDisable);
        else if (node instanceof ComboBox)
            ((ComboBox) node).setDisable(setDisable);
        else if (node instanceof ImageView)
            ((ImageView) node).setDisable(setDisable);
        else if (node instanceof DatePicker)
            ((DatePicker) node).setDisable(setDisable);
        else if (node instanceof TreeTableView)
            ((TableView) node).setDisable(setDisable);
        else if (node instanceof TableView)
            ((TableView) node).setDisable(setDisable);
        else if (node instanceof Circle)
            ((Circle) node).setDisable(setDisable);
        else if (node instanceof ListView)
            ((ListView) node).setDisable(setDisable);
        else if (node instanceof TextArea)
            ((TextArea) node).setDisable(setDisable);

        if (node instanceof AnchorPane)
            fieldDisable((AnchorPane) node, setDisable);
        else if (node instanceof TitledPane)
            fieldDisable((AnchorPane) ((TitledPane) node).getContent(), setDisable);
        else if (node instanceof TabPane)
            for (Tab tab : ((TabPane) node).getTabs())
                fieldDisable((AnchorPane) tab.getContent(), setDisable);
        else if (node instanceof VBox)
            for (Node nod : ((VBox) node).getChildren())
                fieldClear(nod);

    }

    public static void fieldTextFormat(AnchorPane anchorPane) {
//        System.out.printf("AnchorPane: [%s]\n", anchorPane.getId());
        for (Node node : anchorPane.getChildren()) {
//            System.out.printf("\t\tnode: [%s]\n", node.getId());
            fieldTextFormat(node);
        }
    }

    public static void fieldTextFormat(Node node) {
        //System.out.printf("\t\t\tnodee: [%s]\n\t\t\t\taccessibleText: [%s]\n", node.getId(), node.getAccessibleText());
        HashMap<String, String> hashMap = null;
        if (node.getAccessibleText() != null) {
            hashMap = ServiceMascara.getFieldFormatMap(node.getAccessibleText());
            if (hashMap.containsKey("type"))
                alinhamento(node, hashMap.get("type"));
        }
        if (node instanceof TextField) {
            if (hashMap == null) return;
            ((TextField) node).setEditable(true);
            if (hashMap.containsKey("seteditable"))
                ((TextField) node).setEditable(!hashMap.get("seteditable").equals("false"));
            if (hashMap.containsKey("binding"))
                if (hashMap.get("binding").equals("true"))
                    ((TextField) node).setEditable(false);

            int len = 0, decimal = 0;
            String type = "", mascara = null;


            if (hashMap.containsKey("len"))
                len = hashMap.get("len").equals("") ? 0 : Integer.parseInt(hashMap.get("len"));

            if (hashMap.containsKey("decimal"))
                decimal = hashMap.get("decimal").equals("") ? 0 : Integer.parseInt(hashMap.get("decimal"));

            if (hashMap.containsKey("type")) {
                if ((type = hashMap.get("type")).equals(""))
                    type = "TEXTO";
                switch (type) {
                    case "texto":
                        mascara = ServiceMascara.getTextoMask(len, TCONFIG.getSis().getMaskCaracter().getLower());
                        break;
                    case "Texto":
                        mascara = ServiceMascara.getTextoMask(len, TCONFIG.getSis().getMaskCaracter().getInterrogacao());
                        break;
                    case "TEXTO":
                        mascara = ServiceMascara.getTextoMask(len, TCONFIG.getSis().getMaskCaracter().getUpper());
                        break;
                    case "numero":
                    case "número":
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
                    case "timeHMS":
                        mascara = MASK_HORA_HMS;
                        break;
                    case "timeHM":
                        mascara = MASK_HORA_HM;
                        break;
                }
                if (mascara != null)
                    new ServiceMascara().fieldMask((TextField) node, mascara);
            }
        } else if (node instanceof TextArea) {
            if (hashMap.containsKey("seteditable"))
                ((TextArea) node).setEditable(!hashMap.get("seteditable").equals("false"));
        } else if (node instanceof DatePicker) {
            if (hashMap.containsKey("seteditable"))
                ((DatePicker) node).setEditable(!hashMap.get("seteditable").equals("false"));
            new ServiceMascara().fieldMask(((DatePicker) node).getEditor(), "##/##/####");
        } else if (node instanceof AnchorPane)
            fieldTextFormat((AnchorPane) node);
        else if (node instanceof TitledPane)
            fieldTextFormat((AnchorPane) ((TitledPane) node).getContent());
        else if (node instanceof TabPane)
            for (Tab tab : ((TabPane) node).getTabs())
                fieldTextFormat((AnchorPane) tab.getContent());
        else if (node instanceof VBox)
            for (Node nod : ((VBox) node).getChildren())
                fieldTextFormat(nod);
    }

    public static void alinhamento(Node node, String type) {
        switch (type) {
            case "":
            case "texto":
            case "Texto":
            case "TEXTO":
                node.setStyle("-fx-alignment: center-left;");
                break;
            case "numero":
            case "número":
            case "moeda":
            case "valor":
            case "peso":
            case "cnpj":
            case "cpf":
            case "rg":
            case "ncm":
            case "cest":
            case "cep":
            case "nfe_chave":
            case "cte_chave":
            case "nfe_numero":
            case "cte_numero":
            case "fiscal_doc_origem":
            case "timeHMS":
            case "timeHM":
                node.setStyle("-fx-alignment: center-right;");
                break;
        }
    }

}
