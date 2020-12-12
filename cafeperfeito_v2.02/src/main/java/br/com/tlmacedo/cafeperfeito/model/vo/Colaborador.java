package br.com.tlmacedo.cafeperfeito.model.vo;


import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.enums.SituacaoColaborador;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity(name = "Colaborador")
@Table(name = "colaborador")
@Inheritance(strategy = InheritanceType.JOINED)
public class Colaborador implements Serializable {


    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty apelido = new SimpleStringProperty();
    private StringProperty ctps = new SimpleStringProperty();
    private ObjectProperty<LocalDateTime> dtAdmisao = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> salario = new SimpleObjectProperty<>();
    private SituacaoColaborador situacao;

    private ObjectProperty<Empresa> lojaAtivo = new SimpleObjectProperty<>();

    private Blob imagem, imagemBack;


    public Colaborador() {
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

    @Column(length = 120, nullable = false, unique = true)
    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    @Column(length = 30, nullable = false, unique = true)
    public String getApelido() {
        return apelido.get();
    }

    public StringProperty apelidoProperty() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido.set(apelido);
    }

    @Column(length = 30, nullable = false, unique = true)
    public String getCtps() {
        return ctps.get();
    }

    public StringProperty ctpsProperty() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps.set(ctps);
    }

    @Column(nullable = false)
    public LocalDateTime getDtAdmisao() {
        return dtAdmisao.get();
    }

    public ObjectProperty<LocalDateTime> dtAdmisaoProperty() {
        return dtAdmisao;
    }

    public void setDtAdmisao(LocalDateTime dtAdmisao) {
        this.dtAdmisao.set(dtAdmisao);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getSalario() {
        return salario.get();
    }

    public ObjectProperty<BigDecimal> salarioProperty() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario.set(salario);
    }

    @Enumerated(EnumType.ORDINAL)
    public SituacaoColaborador getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoColaborador situacao) {
        this.situacao = situacao;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Empresa getLojaAtivo() {
        return lojaAtivoProperty().get();
    }

    public ObjectProperty<Empresa> lojaAtivoProperty() {
        if (lojaAtivo == null) lojaAtivo = new SimpleObjectProperty<>(new EmpresaDAO().getById(Empresa.class, 0L));
        return lojaAtivo;
    }

    public void setLojaAtivo(Empresa lojaAtivo) {
        lojaAtivoProperty().set(lojaAtivo);
    }

    @JsonIgnore
    @SuppressWarnings("JpaAttributeTypeInspection")
    public Blob getImagem() {
        return imagem;
    }

    public void setImagem(Blob imagem) {
        this.imagem = imagem;
    }

    @JsonIgnore
    @Transient
    public Blob getImagemBack() {
        return imagemBack;
    }

    public void setImagemBack(Blob imagemBack) {
        this.imagemBack = imagemBack;
    }


}
