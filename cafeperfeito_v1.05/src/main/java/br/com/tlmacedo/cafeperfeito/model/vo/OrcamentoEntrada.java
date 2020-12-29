package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-14
 * Time: 11:44
 */

@Entity(name = "OrcamentoEntrada")
@Table(name = "orcamento_entrada")
public class OrcamentoEntrada implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty orcamento = new SimpleStringProperty("");

    public OrcamentoEntrada() {
    }

    public OrcamentoEntrada(String orcamento) {
        this.orcamento = new SimpleStringProperty(orcamento);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    @Lob
    @Column(nullable = false)
    public String getOrcamento() {
        return orcamento.get();
    }

    public void setOrcamento(String temp) {
        this.orcamento.set(temp);
    }

    public StringProperty tempProperty() {
        return orcamento;
    }

    @Override
    public String toString() {
        return "OrcamentoEntrada{" +
                "id=" + id +
                ", orcamento=" + orcamento +
                '}';
    }
}
