package br.com.tlmacedo.cafeperfeito.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-01-17
 * Time: 19:35
 */

public class WsEanCosmo extends RecursiveTreeObject<WsEanCosmo> implements Serializable {

    private static final long serialVersionUID = 1L;

    private StringProperty message, descricao, ncm;

    public WsEanCosmo() {
    }

    public WsEanCosmo(String message, String descricao, String ncm) {
        this.message = new SimpleStringProperty(message);
        this.descricao = new SimpleStringProperty(descricao);
        this.ncm = new SimpleStringProperty(ncm);
    }

    public String getMessage() {
        return message.get();
    }

    public void setMessage(String message) {
        this.message.set(message);
    }

    public StringProperty messageProperty() {
        return message;
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public String getNcm() {
        return ncm.get();
    }

    public void setNcm(String ncm) {
        this.ncm.set(ncm);
    }

    public StringProperty ncmProperty() {
        return ncm;
    }

    @Override
    public String toString() {
        return "WsEanCosmo{" +
                "message=" + message +
                ", descricao=" + descricao +
                ", ncm=" + ncm +
                "} " + super.toString();
    }
}
