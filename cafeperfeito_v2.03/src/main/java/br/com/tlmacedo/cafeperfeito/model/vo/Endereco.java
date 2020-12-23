package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.enums.EnumEnderecoTipo;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Endereco")
@Table(name = "endereco")
public class Endereco implements Serializable {
    public static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private EnumEnderecoTipo enderecoTipo;
    private StringProperty cep = new SimpleStringProperty();
    private StringProperty logradouro = new SimpleStringProperty();
    private StringProperty numero = new SimpleStringProperty();
    private StringProperty complemento = new SimpleStringProperty();
    private StringProperty bairro = new SimpleStringProperty();
    private StringProperty referencia = new SimpleStringProperty();

    private ObjectProperty<Municipio> municipio = new SimpleObjectProperty<>();

    public Endereco() {
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

    @Enumerated(EnumType.ORDINAL)
    public EnumEnderecoTipo getEnderecoTipo() {
        return enderecoTipo;
    }

    public void setEnderecoTipo(EnumEnderecoTipo enderecoTipo) {
        this.enderecoTipo = enderecoTipo;
    }

    @Column(length = 8, nullable = false)
    public String getCep() {
        return cep.get();
    }

    public StringProperty cepProperty() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep.set(cep);
    }

    @Column(length = 120, nullable = false)
    public String getLogradouro() {
        return logradouro.get();
    }

    public StringProperty logradouroProperty() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro.set(logradouro);
    }

    @Column(length = 10, nullable = false)
    public String getNumero() {
        return numero.get();
    }

    public StringProperty numeroProperty() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    @Column(length = 80)
    public String getComplemento() {
        return complemento.get();
    }

    public StringProperty complementoProperty() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento.set(complemento);
    }

    @Column(length = 50, nullable = false)
    public String getBairro() {
        return bairro.get();
    }

    public StringProperty bairroProperty() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro.set(bairro);
    }

    @Column(length = 80)
    public String getReferencia() {
        return referencia.get();
    }

    public StringProperty referenciaProperty() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia.set(referencia);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Municipio getMunicipio() {
        return municipio.get();
    }

    public ObjectProperty<Municipio> municipioProperty() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio.set(municipio);
    }

    @Override
    public String toString() {
        return getEnderecoTipo().getDescricao();
    }
}
