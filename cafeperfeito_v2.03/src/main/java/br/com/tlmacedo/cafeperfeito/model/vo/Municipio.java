package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Municipio")
@Table(name = "municipio")
public class Municipio implements Serializable {
    public static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();
    private BooleanProperty capital = new SimpleBooleanProperty();
    private StringProperty ibge_codigo = new SimpleStringProperty();
    private StringProperty ddd = new SimpleStringProperty();

    private ObjectProperty<Uf> uf = new SimpleObjectProperty<>();

    public Municipio() {
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

    @Column(length = 80, nullable = false)
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
    public boolean isCapital() {
        return capital.get();
    }

    public BooleanProperty capitalProperty() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital.set(capital);
    }

    @Column(length = 7, nullable = false, unique = true)
    public String getIbge_codigo() {
        return ibge_codigo.get();
    }

    public StringProperty ibge_codigoProperty() {
        return ibge_codigo;
    }

    public void setIbge_codigo(String ibge_codigo) {
        this.ibge_codigo.set(ibge_codigo);
    }

    @Column(length = 2, nullable = false)
    public String getDdd() {
        return ddd.get();
    }

    public StringProperty dddProperty() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd.set(ddd);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Uf getUf() {
        return uf.get();
    }

    public ObjectProperty<Uf> ufProperty() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf.set(uf);
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}
