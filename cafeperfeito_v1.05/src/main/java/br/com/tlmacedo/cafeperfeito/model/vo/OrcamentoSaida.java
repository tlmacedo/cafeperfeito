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
 * Date: 2019-04-06
 * Time: 17:35
 */

@Entity(name = "OrcamentoSaida")
@Table(name = "orcamento_saida")
public class OrcamentoSaida implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty orcamento = new SimpleStringProperty("");

    public OrcamentoSaida() {
    }

    public OrcamentoSaida(String orcamento) {
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

    public void setOrcamento(String orcamento) {
        this.orcamento.set(orcamento);
    }

    public StringProperty orcamentoProperty() {
        return orcamento;
    }
}
