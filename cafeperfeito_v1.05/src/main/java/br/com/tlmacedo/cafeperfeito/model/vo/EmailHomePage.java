package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "EmailHomePage")
@Table(name = "email_home_page")
public class EmailHomePage implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();

    private BooleanProperty mail = new SimpleBooleanProperty();
    private BooleanProperty principal = new SimpleBooleanProperty();
    private BooleanProperty nfe = new SimpleBooleanProperty();

    public EmailHomePage() {
    }

    public EmailHomePage(String descricao, Boolean mail, Boolean principal, Boolean nfe) {
        this.descricao = new SimpleStringProperty(descricao);
        this.mail = new SimpleBooleanProperty(mail);
        this.principal = new SimpleBooleanProperty(principal);
        this.nfe = new SimpleBooleanProperty(nfe);
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

    @Column(length = 120, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    @Column(length = 1, nullable = false)
    public boolean isMail() {
        return mail.get();
    }

    public BooleanProperty mailProperty() {
        return mail;
    }

    public void setMail(boolean mail) {
        this.mail.set(mail);
    }

    @Column(length = 1, nullable = false)
    public boolean isPrincipal() {
        return principal.get();
    }

    public BooleanProperty principalProperty() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal.set(principal);
    }

    @Column(length = 1, nullable = false)
    public boolean isNfe() {
        return nfe.get();
    }

    public BooleanProperty nfeProperty() {
        return nfe;
    }

    public void setNfe(boolean nfe) {
        this.nfe.set(nfe);
    }

    @Override
    public String toString() {
        return descricaoProperty().get();
    }
}
