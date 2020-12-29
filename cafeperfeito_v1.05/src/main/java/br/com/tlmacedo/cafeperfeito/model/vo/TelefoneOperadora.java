package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "TelefoneOperadora")
@Table(name = "telefone_operadora")
public class TelefoneOperadora implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();
    private StringProperty codWsPortabilidade = new SimpleStringProperty();

    public TelefoneOperadora() {
    }

    public TelefoneOperadora(String descricao, String codWsPortabilidade) {
        this.descricao = new SimpleStringProperty(descricao);
        this.codWsPortabilidade = new SimpleStringProperty(codWsPortabilidade);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Column(length = 30, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    @Column(length = 5)
    public String getCodWsPortabilidade() {
        return codWsPortabilidade.get();
    }

    public void setCodWsPortabilidade(String codWsPortabilidade) {
        this.codWsPortabilidade.set(codWsPortabilidade);
    }

    public StringProperty codWsPortabilidadeProperty() {
        return codWsPortabilidade;
    }

    @Override
    public String toString() {
        return descricaoProperty().get();
//        return "TelefoneOperadora{" +
//                "id=" + id +
//                ", descricao=" + descricao +
//                ", codWsPortabilidade=" + codWsPortabilidade +
//                '}';
    }
}
