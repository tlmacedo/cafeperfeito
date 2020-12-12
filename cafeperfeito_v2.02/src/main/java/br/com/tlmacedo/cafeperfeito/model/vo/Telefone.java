package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

@Entity(name = "Telefone")
@Table(name = "telefone")
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty descricao = new SimpleStringProperty();
    private BooleanProperty principal = new SimpleBooleanProperty();

    private ObjectProperty<TelefoneOperadora> telefoneOperadora = new SimpleObjectProperty<>();

    public Telefone() {
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

    @ManyToOne(fetch = FetchType.LAZY)
    public TelefoneOperadora getTelefoneOperadora() {
        return telefoneOperadora.get();
    }

    public ObjectProperty<TelefoneOperadora> telefoneOperadoraProperty() {
        return telefoneOperadora;
    }

    public void setTelefoneOperadora(TelefoneOperadora telefoneOperadora) {
        this.telefoneOperadora.set(telefoneOperadora);
    }

    @Override
    public String toString() {
        try {
            return String.format("%14s\t(%s)",
                    ServiceMascara.getTelefone(
                            Integer.valueOf(descricaoProperty().get().substring(0, 2)) == TCONFIG.getInfLoja().getDdd().intValue()
                                    ? descricaoProperty().get().substring(2)
                                    : descricaoProperty().get()
                    ),
                    getTelefoneOperadora());
        } catch (Exception ex) {
            return null;
        }
    }
}
