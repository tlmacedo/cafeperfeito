package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.enums.EnumClassificacaoJuridica;
import br.com.tlmacedo.cafeperfeito.model.enums.EnumEnderecoTipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Empresa")
@Table(name = "empresa")
public class Empresa implements Serializable {
    public static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private EnumClassificacaoJuridica classificacaoJuridica;
    private ConfigSituacaoCadastro situacaoCadastro = new ConfigSituacaoCadastro();
    private StringProperty cnpj = new SimpleStringProperty();
    private StringProperty ie = new SimpleStringProperty();
    private StringProperty razao = new SimpleStringProperty();
    private StringProperty fantasia = new SimpleStringProperty();
    private BooleanProperty cliente = new SimpleBooleanProperty();
    private BooleanProperty fornecedor = new SimpleBooleanProperty();
    private BooleanProperty transportadora = new SimpleBooleanProperty();

    private ObjectProperty<Usuario> usuarioCadastro = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDateTime> dataCadastro = new SimpleObjectProperty<>();
    private ObjectProperty<Usuario> usuarioAtualizacao = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDateTime> dataAtualizacao = new SimpleObjectProperty<>();

    private StringProperty inscricaoSuframa = new SimpleStringProperty();
    private StringProperty inscricaoMunicipal = new SimpleStringProperty();
    private StringProperty observacoes = new SimpleStringProperty();

    private ObjectProperty<BigDecimal> limite = new SimpleObjectProperty<>();
    private IntegerProperty prazoPadrao = new SimpleIntegerProperty();
    private BooleanProperty diaUtilPrazo = new SimpleBooleanProperty();

    private List<Endereco> enderecoList = new ArrayList<>();

    public Empresa() {
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
    public EnumClassificacaoJuridica getClassificacaoJuridica() {
        return classificacaoJuridica;
    }

    public void setClassificacaoJuridica(EnumClassificacaoJuridica classificacaoJuridica) {
        this.classificacaoJuridica = classificacaoJuridica;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public ConfigSituacaoCadastro getSituacaoCadastro() {
        return situacaoCadastro;
    }

    public void setSituacaoCadastro(ConfigSituacaoCadastro situacaoCadastro) {
        this.situacaoCadastro = situacaoCadastro;
    }

    @Column(length = 14, nullable = false)
    public String getCnpj() {
        return cnpj.get();
    }

    public StringProperty cnpjProperty() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj.set(cnpj);
    }

    @Column(length = 14)
    public String getIe() {
        return ie.get();
    }

    public StringProperty ieProperty() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie.set(ie);
    }

    @Column(length = 120, nullable = false)
    public String getRazao() {
        return razao.get();
    }

    public StringProperty razaoProperty() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao.set(razao);
    }

    @Column(length = 80, nullable = false)
    public String getFantasia() {
        return fantasia.get();
    }

    public StringProperty fantasiaProperty() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia.set(fantasia);
    }

    @Column(length = 1, nullable = false)
    public boolean isCliente() {
        return cliente.get();
    }

    public BooleanProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente.set(cliente);
    }

    @Column(length = 1, nullable = false)
    public boolean isFornecedor() {
        return fornecedor.get();
    }

    public BooleanProperty fornecedorProperty() {
        return fornecedor;
    }

    public void setFornecedor(boolean fornecedor) {
        this.fornecedor.set(fornecedor);
    }

    @Column(length = 1, nullable = false)
    public boolean isTransportadora() {
        return transportadora.get();
    }

    public BooleanProperty transportadoraProperty() {
        return transportadora;
    }

    public void setTransportadora(boolean transportadora) {
        this.transportadora.set(transportadora);
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro.get();
    }

    public ObjectProperty<Usuario> usuarioCadastroProperty() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro.set(usuarioCadastro);
    }

    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime getDataCadastro() {
        return dataCadastro.get();
    }

    public ObjectProperty<LocalDateTime> dataCadastroProperty() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao.get();
    }

    public ObjectProperty<Usuario> usuarioAtualizacaoProperty() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao.set(usuarioAtualizacao);
    }

    @UpdateTimestamp
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao.get();
    }

    public ObjectProperty<LocalDateTime> dataAtualizacaoProperty() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao.set(dataAtualizacao);
    }

    @Column(length = 9)
    public String getInscricaoSuframa() {
        return inscricaoSuframa.get();
    }

    public StringProperty inscricaoSuframaProperty() {
        return inscricaoSuframa;
    }

    public void setInscricaoSuframa(String inscricaoSuframa) {
        this.inscricaoSuframa.set(inscricaoSuframa);
    }

    @Column(length = 15)
    public String getInscricaoMunicipal() {
        return inscricaoMunicipal.get();
    }

    public StringProperty inscricaoMunicipalProperty() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal.set(inscricaoMunicipal);
    }

    @Column(length = 1500)
    public String getObservacoes() {
        return observacoes.get();
    }

    public StringProperty observacoesProperty() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes.set(observacoes);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getLimite() {
        return limite.get();
    }

    public ObjectProperty<BigDecimal> limiteProperty() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite.set(limite);
    }

    @Column(length = 3, nullable = false)
    public int getPrazoPadrao() {
        return prazoPadrao.get();
    }

    public IntegerProperty prazoPadraoProperty() {
        return prazoPadrao;
    }

    public void setPrazoPadrao(int prazoPadrao) {
        this.prazoPadrao.set(prazoPadrao);
    }

    @Column(length = 1, nullable = false)
    public boolean isDiaUtilPrazo() {
        return diaUtilPrazo.get();
    }

    public BooleanProperty diaUtilPrazoProperty() {
        return diaUtilPrazo;
    }

    public void setDiaUtilPrazo(boolean diaUtilPrazo) {
        this.diaUtilPrazo.set(diaUtilPrazo);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    @Transient
    public Endereco getEndereco(EnumEnderecoTipo enderecoTipo) {

        if (enderecoTipo == null)
            enderecoTipo = EnumEnderecoTipo.PRINCIPAL;
        EnumEnderecoTipo finalEnderecoTipo = enderecoTipo;
        return getEnderecoList().stream()
                .filter(endereco -> endereco.getEnderecoTipo().equals(finalEnderecoTipo))
                .findFirst().orElse(null);

    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", classificacaoJuridica=" + classificacaoJuridica +
                ", situacaoCadastro=" + situacaoCadastro +
                ", cnpj=" + cnpj +
                ", ie=" + ie +
                ", razao=" + razao +
                ", fantasia=" + fantasia +
                ", cliente=" + cliente +
                ", fornecedor=" + fornecedor +
                ", transportadora=" + transportadora +
                ", usuarioCadastro=" + usuarioCadastro +
                ", dataCadastro=" + dataCadastro +
                ", usuarioAtualizacao=" + usuarioAtualizacao +
                ", dataAtualizacao=" + dataAtualizacao +
                ", inscricaoSuframa=" + inscricaoSuframa +
                ", inscricaoMunicipal=" + inscricaoMunicipal +
                ", observacoes=" + observacoes +
                ", limite=" + limite +
                ", prazoPadrao=" + prazoPadrao +
                ", diaUtilPrazo=" + diaUtilPrazo +
                '}';
    }
}
