package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ConfigSituacaoCadastro")
@Table(name = "config_situacao_cadastro")
public class ConfigSituacaoCadastro implements Serializable {
    public static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();

    public ConfigSituacaoCadastro() {
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

    @Column(length = 30, nullable = false, unique = true)
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
        return "ConfigSituacaoCadastro{" +
                "id=" + id +
                ", descricao=" + descricao +
                '}';
    }
}
