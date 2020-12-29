package br.com.tlmacedo.cafeperfeito.service;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.CriteriosValidationFields;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static br.com.tlmacedo.cafeperfeito.model.vo.enums.CriteriosValidationFields.*;

//import javafx.scene.control.Tooltip;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-05-15
 * Time: 10:07
 */

public class ServiceValidationFields {

    private final String STILE_BORDER_VALIDATION = "-fx-background-color: rgba(255,102,0,0.4)";
    //    private final Tooltip toolTip = new Tooltip("Este campo está inválido!!!");
    private HashMap<CriteriosValidationFields, String> hashMap;
    private boolean fieldEditable = true;
    private int erros = 0;
    private Node campo;
    private JFXTextField jfxTextField;
    private BooleanProperty isValido = new SimpleBooleanProperty(false);

    public ServiceValidationFields() {
//        toolTip.setStyle("-fx-background-color: linear-gradient(#FF6B6B , #FFA6A6 );"
//                + " -fx-font-weight: bold;");
    }

    /**
     * ***********ARRAY TO LIST UTILITY************
     */
    public static List<Node> arrayToList(Node[] n) {
        List<Node> listItems = new ArrayList<>();
        for (Node n1 : n) {
            listItems.add(n1);
        }
        return listItems;
    }

    public void checkFields(Node campo, String criterios) {
        setCampo(campo);
        if (campo.getAccessibleText() != null) {
            HashMap<String, String> tmpHashMap = ServiceMascara.getFieldFormatMap(campo.getAccessibleText());
            try {
                setFieldEditable(!(tmpHashMap.get("seteditable").equals("false")));
            } catch (Exception ex) {
                setFieldEditable(true);
            }
        }

        setHashMap(ServiceMascara.getHasMapCriteriosValidationFields(criterios));
        getCampo().disableProperty().addListener((ov, o, n) -> {
            if (n || !isFieldEditable()) {
                removeToolTipAndBorderColor(getCampo());
            } else {
                analisaErrors();
                if (getCampo() instanceof JFXTextField)
                    ((JFXTextField) getCampo()).textProperty().addListener((ov1, o1, n1) -> analisaErrors());
                if (getCampo() instanceof JFXComboBox)
                    ((JFXComboBox) getCampo()).getSelectionModel().selectedIndexProperty().addListener((ov1, o1, n1) -> analisaErrors());
            }
        });
    }

    private void analisaErrors() {
        setErros(0);
        for (CriteriosValidationFields criteria : getHashMap().keySet()) {
            if (!getCampo().isDisabled()) {
                if (getCampo() instanceof JFXTextField)
                    switch (criteria) {
                        case VAL_CNPJ:
                            if (!ServiceValidarDado.isCnpjCpfValido(((JFXTextField) getCampo()).getText()))
                                setErros(getErros() + 1);
                            break;
                        case MIN_SIZE:
                            if (((JFXTextField) getCampo()).getLength() < Integer.valueOf(getHashMap().get(MIN_SIZE)))
                                setErros(getErros() + 1);
                            break;
                        case MIN_BIG:
                            if (ServiceMascara.getBigDecimalFromTextField(((JFXTextField) getCampo()).getText(),
                                    (((JFXTextField) getCampo()).getText().replaceAll("\\D", "").length()
                                            - ((JFXTextField) getCampo()).getText().lastIndexOf(","))
                            ).compareTo(new BigDecimal(getHashMap().get(MIN_BIG))) < 0)
                                setErros(getErros() + 1);
                            break;
                    }
                if (getCampo() instanceof JFXComboBox)
                    switch (criteria) {
                        case MIN_CBO:
                            if (((JFXComboBox) getCampo()).getSelectionModel().getSelectedIndex() < Integer.valueOf(getHashMap().get(MIN_CBO)))
                                setErros(getErros() + 1);
                            break;
                    }
            }
        }
        if (getErros() > 0)
            addToolTipAndBorderColor(getCampo());
        else
            removeToolTipAndBorderColor(getCampo());

    }

    /**
     * *******ADD AND REMOVE STYLES********
     */
    public void addToolTipAndBorderColor(Node n) {
//        Tooltip.install(n, t);
        n.setStyle(STILE_BORDER_VALIDATION);
        setIsValido(false);
    }

    public void removeToolTipAndBorderColor(Node n) {
        n.setStyle(null);
        setIsValido(true);
    }

    /**
     * ***********FORCE TOOL TIP TO BE DISPLAYED FASTER************
     */
//    private void hackTooltipStartTiming(Tooltip tooltip) {
//        try {
//            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
//            fieldBehavior.setAccessible(true);
//            Object objBehavior = fieldBehavior.get(tooltip);
//
//            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
//            fieldTimer.setAccessible(true);
//            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);
//
//            objTimer.getKeyFrames().clear();
//            objTimer.getKeyFrames().add(new KeyFrame(new Duration(5000)));
//        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
//            System.out.println(e);
//        }
//    }
    public HashMap<CriteriosValidationFields, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<CriteriosValidationFields, String> hashMap) {
        this.hashMap = hashMap;
    }

    public boolean isFieldEditable() {
        return fieldEditable;
    }

    public void setFieldEditable(boolean fieldEditable) {
        this.fieldEditable = fieldEditable;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public JFXTextField getJfxTextField() {
        return jfxTextField;
    }

    public void setJfxTextField(JFXTextField jfxTextField) {
        this.jfxTextField = jfxTextField;
    }

    public boolean isIsValido() {
        return isValido.get();
    }

    public void setIsValido(boolean isValido) {
        this.isValido.set(isValido);
    }

    public BooleanProperty isValidoProperty() {
        return isValido;
    }

    public Node getCampo() {
        return campo;
    }

    public void setCampo(Node campo) {
        this.campo = campo;
    }
}

