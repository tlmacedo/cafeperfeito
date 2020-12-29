package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.enums.EnumCargo;
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
    public static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty apelido = new SimpleStringProperty();
    private EnumCargo cargo;
    private StringProperty ctps = new SimpleStringProperty();
    private ObjectProperty<LocalDateTime> dtAdmisao = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> salario = new SimpleObjectProperty<>();
    private BooleanProperty situacao = new SimpleBooleanProperty();

    private ObjectProperty<Empresa> lojaLocado = new SimpleObjectProperty<>();

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

    @Enumerated(EnumType.ORDINAL)
    public EnumCargo getCargo() {
        return cargo;
    }

    public void setCargo(EnumCargo cargo) {
        this.cargo = cargo;
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

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getSalario() {
        return salario.get();
    }

    public ObjectProperty<BigDecimal> salarioProperty() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario.set(salario);
    }

    @Column(length = 1, nullable = false)
    public boolean isSituacao() {
        return situacao.get();
    }

    public BooleanProperty situacaoProperty() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao.set(situacao);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Empresa getLojaLocado() {
        return lojaLocado.get();
    }

    public ObjectProperty<Empresa> lojaLocadoProperty() {
        return lojaLocado;
    }

    public void setLojaLocado(Empresa lojaLocado) {
        this.lojaLocado.set(lojaLocado);
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

    @Override
    public String toString() {
        return "Colaborador{" +
                "id=" + id +
                ", nome=" + nome +
                ", apelido=" + apelido +
                ", cargo=" + cargo +
                ", ctps=" + ctps +
                ", dtAdmisao=" + dtAdmisao +
                ", salario=" + salario +
                ", situacao=" + situacao +
                ", imagem=" + imagem +
                ", imagemBack=" + imagemBack +
                '}';
    }
}
