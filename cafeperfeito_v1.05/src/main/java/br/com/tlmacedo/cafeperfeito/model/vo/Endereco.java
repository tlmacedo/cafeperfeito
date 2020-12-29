package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.EnderecoTipo;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.*;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.io.StringReader;

@Entity(name = "Endereco")
@Table(name = "endereco")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private IntegerProperty tipo = new SimpleIntegerProperty();
    private StringProperty cep = new SimpleStringProperty();
    private StringProperty logradouro = new SimpleStringProperty();
    private StringProperty numero = new SimpleStringProperty();
    private StringProperty complemento = new SimpleStringProperty();
    private StringProperty bairro = new SimpleStringProperty();
    private StringProperty prox = new SimpleStringProperty();

    private Municipio municipio = new Municipio();


    public Endereco() {
    }

    public Endereco(EnderecoTipo tipo) {
        this.tipo = new SimpleIntegerProperty(tipo.getCod());
    }

    public Endereco(EnderecoTipo tipo, String cep, String logradouro, String numero, String complemento, String bairro, String prox, Municipio municipio) {
        this.tipo = new SimpleIntegerProperty(tipo.getCod());
        this.cep = new SimpleStringProperty(cep);
        this.logradouro = new SimpleStringProperty(logradouro);
        this.numero = new SimpleStringProperty(numero);
        this.complemento = new SimpleStringProperty(complemento);
        this.bairro = new SimpleStringProperty(bairro);
        this.prox = new SimpleStringProperty(prox);
        this.municipio = municipio;
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

    @Column(length = 2, nullable = false)
    public EnderecoTipo getTipo() {
        return EnderecoTipo.toEnum(tipo.get());
    }

    public void setTipo(EnderecoTipo tipo) {
        this.tipo.set(tipo.getCod());
    }

    public IntegerProperty tipoProperty() {
        return tipo;
    }

    @Column(length = 8, nullable = false)
    public String getCep() {
        return cep.get();
    }

    public StringProperty cepProperty() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep.set(cep.replaceAll("\\D", ""));
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
    public String getProx() {
        return prox.get();
    }

    public StringProperty proxProperty() {
        return prox;
    }

    public void setProx(String prox) {
        this.prox.set(prox);
    }

    @ManyToOne
    @JoinColumn(name = "municipio_id", foreignKey = @ForeignKey(name = "fk_endereco_municipio"), nullable = false)
    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public boolean setWsEndereco_CepPostmon(Endereco endereco, JSONObject retWs) {
        ObjectMapper objectMapper = new ObjectMapper();
        WsEnderecoCepPostmon wsEnderecoCepPostmon = null;
        if (retWs == null)
            return false;
        try {
            wsEnderecoCepPostmon = objectMapper.readValue(new StringReader(retWs.toString()), WsEnderecoCepPostmon.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }

        endereco.setCep(wsEnderecoCepPostmon.getCep());
        endereco.setLogradouro(wsEnderecoCepPostmon.getLogradouro());
        endereco.setNumero("");
        endereco.setComplemento(wsEnderecoCepPostmon.getComplemento());
        endereco.setBairro(wsEnderecoCepPostmon.getBairro());
        endereco.setMunicipio(wsEnderecoCepPostmon.getCidade_DB());
        endereco.setProx("");
        return true;
    }

    @Override
    public String toString() {
        return EnderecoTipo.toEnum(tipoProperty().get()).getDescricao();
    }
}
