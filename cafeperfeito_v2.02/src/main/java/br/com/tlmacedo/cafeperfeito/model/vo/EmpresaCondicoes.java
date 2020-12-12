package br.com.tlmacedo.cafeperfeito.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "EmpresaCondicoes")
@Table(name = "empresa_condicoes")
public class EmpresaCondicoes implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private ObjectProperty<Empresa> empresa = new SimpleObjectProperty<>();
    private ObjectProperty<Produto> produto = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> valor = new SimpleObjectProperty<>();
    private IntegerProperty qtdMinima = new SimpleIntegerProperty();
    private IntegerProperty prazo = new SimpleIntegerProperty();
    private IntegerProperty bonificacao = new SimpleIntegerProperty();
    private IntegerProperty retirada = new SimpleIntegerProperty();
    private ObjectProperty<BigDecimal> desconto = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> validade = new SimpleObjectProperty<>();

    public EmpresaCondicoes() {
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Empresa getEmpresa() {
        return empresa.get();
    }

    public ObjectProperty<Empresa> empresaProperty() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa.set(empresa);
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Produto getProduto() {
        return produto.get();
    }

    public ObjectProperty<Produto> produtoProperty() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto.set(produto);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getValor() {
        return valor.get();
    }

    public ObjectProperty<BigDecimal> valorProperty() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor.set(valor);
    }

    @Column(length = 3, nullable = false)
    public int getQtdMinima() {
        return qtdMinima.get();
    }

    public IntegerProperty qtdMinimaProperty() {
        return qtdMinima;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima.set(qtdMinima);
    }

    @Column(length = 3, nullable = false)
    public int getPrazo() {
        return prazo.get();
    }

    public IntegerProperty prazoProperty() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo.set(prazo);
    }

    @Column(length = 3, nullable = false)
    public int getBonificacao() {
        return bonificacao.get();
    }

    public IntegerProperty bonificacaoProperty() {
        return bonificacao;
    }

    public void setBonificacao(int bonificacao) {
        this.bonificacao.set(bonificacao);
    }

    @Column(length = 3, nullable = false)
    public int getRetirada() {
        return retirada.get();
    }

    public IntegerProperty retiradaProperty() {
        return retirada;
    }

    public void setRetirada(int retirada) {
        this.retirada.set(retirada);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getDesconto() {
        return desconto.get();
    }

    public ObjectProperty<BigDecimal> descontoProperty() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto.set(desconto);
    }

    @Column(nullable = false)
    public LocalDate getValidade() {
        return validade.get();
    }

    public ObjectProperty<LocalDate> validadeProperty() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade.set(validade);
    }

    @Override
    public String toString() {
        return "EmpresaCondicoes{" +
                "id=" + id +
                ", empresa=" + empresa +
                ", produto=" + produto +
                ", valor=" + valor +
                ", qtdMinima=" + qtdMinima +
                ", prazo=" + prazo +
                ", bonificacao=" + bonificacao +
                ", desconto=" + desconto +
                ", validade=" + validade +
                '}';
    }
}
