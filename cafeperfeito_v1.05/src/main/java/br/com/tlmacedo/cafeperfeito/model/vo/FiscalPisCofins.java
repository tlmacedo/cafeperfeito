package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "FiscalPisCofins")
@Table(name = "fiscal_pis_cofins")
public class FiscalPisCofins implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();

    public FiscalPisCofins() {
    }

    public FiscalPisCofins(Long id, String descricao) {
        this.id = new SimpleLongProperty(id);
        this.descricao = new SimpleStringProperty(descricao);
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

    @Column(length = 150, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    @Override
    public String toString() {
        return String.format("[%02d] %s",
                idProperty().get(),
                descricaoProperty().get()
        );
    }
}
