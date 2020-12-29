package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;

import static br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema.TCONFIG;

@Entity(name = "Telefone")
@Table(name = "telefone")
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();

    private TelefoneOperadora telefoneOperadora = new TelefoneOperadora();

    public Telefone() {
    }

    public Telefone(String descricao, TelefoneOperadora telefoneOperadora) {
        this.descricao = new SimpleStringProperty(descricao);
        this.telefoneOperadora = telefoneOperadora;
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

    @Column(length = 11, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    @OneToOne
    @JoinColumn(name = "telefoneOperadora_id", foreignKey = @ForeignKey(name = "fk_telefone_telefone_operadora"))
    public TelefoneOperadora getTelefoneOperadora() {
        return telefoneOperadora;
    }

    public void setTelefoneOperadora(TelefoneOperadora telefoneOperadora) {
        this.telefoneOperadora = telefoneOperadora;
    }

    @Override
    public String toString() {
        try {
            return String.format("%14s\t(%s)",
                    ServiceMascara.getTelefone(
                            Integer.valueOf(descricaoProperty().get().substring(0, 2)) == Integer.valueOf(TCONFIG.getInfLoja().getDdd())
                                    ? descricaoProperty().get().substring(2)
                                    : descricaoProperty().get()
                    ),
                    getTelefoneOperadora());
        } catch (Exception ex) {
            return null;
        }
    }
}
