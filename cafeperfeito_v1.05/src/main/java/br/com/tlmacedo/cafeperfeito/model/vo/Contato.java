package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Contato")
@Table(name = "contato")
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();

    private Cargo cargo = new Cargo();

    private List<EmailHomePage> emailHomePageList = new ArrayList<>();
    private List<Telefone> telefoneList = new ArrayList<>();

    public Contato() {
    }

    public Contato(String descricao, Cargo cargo) {
        this.descricao = new SimpleStringProperty(descricao);
        this.cargo = cargo;
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

    @Column(length = 40, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    @ManyToOne
    @JoinColumn(name = "cargo_id", foreignKey = @ForeignKey(name = "fk_contato_cargo"), nullable = false)
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<EmailHomePage> getEmailHomePageList() {
        return emailHomePageList;
    }

    public void setEmailHomePageList(List<EmailHomePage> emailHomePageList) {
        this.emailHomePageList = emailHomePageList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", descricaoProperty().get(), getCargo().descricaoProperty().get());
    }
}
