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
 * Date: 2019-07-18
 * Time: 10:22
 */

@Entity(name = "Bancos")
@Table(name = "bancos")
public class Bancos implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty codigo = new SimpleStringProperty("");
    private StringProperty banco = new SimpleStringProperty("");
    private StringProperty site = new SimpleStringProperty("");

    public Bancos() {
    }

    public Bancos(StringProperty codigo, StringProperty banco, StringProperty site) {
        this.codigo = codigo;
        this.banco = banco;
        this.site = site;
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

    @Column(length = 3, nullable = false, unique = true)
    public String getCodigo() {
        return codigo.get();
    }

    public StringProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    @Column(length = 80, nullable = false)
    public String getBanco() {
        return banco.get();
    }

    public StringProperty bancoProperty() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco.set(banco);
    }

    @Column(length = 120)
    public String getSite() {
        return site.get();
    }

    public StringProperty siteProperty() {
        return site;
    }

    public void setSite(String site) {
        this.site.set(site);
    }

    @Override
    public String toString() {
        return "Bancos{" +
                "id=" + id +
                ", codigo=" + codigo +
                ", banco=" + banco +
                ", site=" + site +
                '}';
    }
}
