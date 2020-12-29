package br.com.tlmacedo.cafeperfeito.model.vo;


import javafx.beans.property.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "Uf")
@Table(name = "uf")
public class Uf implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();
    private StringProperty sigla = new SimpleStringProperty();
    private IntegerProperty ddd = new SimpleIntegerProperty();

    public Uf() {
    }

    public Uf(String descricao, String sigla, Integer ddd) {
        this.descricao = new SimpleStringProperty(descricao);
        this.sigla = new SimpleStringProperty(sigla);
        this.ddd = new SimpleIntegerProperty(ddd);
    }

    @Id
    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Column(length = 50, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    @Column(length = 2, nullable = false)
    public String getSigla() {
        return sigla.get();
    }

    public StringProperty siglaProperty() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla.set(sigla);
    }

    @Column(length = 2, nullable = false)
    public int getDdd() {
        return ddd.get();
    }

    public void setDdd(int ddd) {
        this.ddd.set(ddd);
    }

    public IntegerProperty dddProperty() {
        return ddd;
    }

    @Override
    public String toString() {
        return siglaProperty().get();
    }
}
