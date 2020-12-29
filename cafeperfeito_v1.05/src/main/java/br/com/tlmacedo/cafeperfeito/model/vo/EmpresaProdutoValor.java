package br.com.tlmacedo.cafeperfeito.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-04
 * Time: 15:52
 */


@Entity(name = "EmpresaProdutoValor")
@Table(name = "empresa_produto_valor")
public class EmpresaProdutoValor extends RecursiveTreeObject<EmpresaProdutoValor> implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private ObjectProperty<Produto> produto = new SimpleObjectProperty<>(new Produto());
    private ObjectProperty<BigDecimal> valor = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private IntegerProperty qtdMinima = new SimpleIntegerProperty(0);
    private IntegerProperty dias = new SimpleIntegerProperty(0);
    private IntegerProperty bonificacao = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> desconto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<LocalDate> validadeDesconto = new SimpleObjectProperty<>(LocalDate.now());

    private IntegerProperty produtoId = new SimpleIntegerProperty(0);
    private StringProperty produtoCodigo = new SimpleStringProperty("");
//    private StringProperty produtoDescricao = new SimpleStringProperty("");

    public EmpresaProdutoValor() {
    }

    public EmpresaProdutoValor(Produto produto, BigDecimal valor, Integer qtdMinima, Integer dias, Integer bonificacao, BigDecimal desconto, LocalDate validadeDesconto) {
        this.produto = new SimpleObjectProperty<>(produto);
        this.valor = new SimpleObjectProperty<>(valor);
        this.qtdMinima = new SimpleIntegerProperty(qtdMinima);
        this.dias = new SimpleIntegerProperty(dias);
        this.bonificacao = new SimpleIntegerProperty(bonificacao);
        this.desconto = new SimpleObjectProperty<>(desconto);
        this.validadeDesconto = new SimpleObjectProperty<>(validadeDesconto);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_empresa_produto_valor_produto"))
    public Produto getProduto() {
        return produto.get();
    }

    public void setProduto(Produto produto) {
        this.produto.set(produto);
    }

    public ObjectProperty<Produto> produtoProperty() {
        return produto;
    }

    @Column(nullable = false)
    public BigDecimal getValor() {
        return valor.get();
    }

    public void setValor(BigDecimal valor) {
        this.valor.set(valor);
    }

    public ObjectProperty<BigDecimal> valorProperty() {
        return valor;
    }

    @Column(length = 3, nullable = false)
    public int getQtdMinima() {
        return qtdMinima.get();
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima.set(qtdMinima);
    }

    public IntegerProperty qtdMinimaProperty() {
        return qtdMinima;
    }

    @Column(length = 3, nullable = false)
    public int getDias() {
        return dias.get();
    }

    public void setDias(int dias) {
        this.dias.set(dias);
    }

    public IntegerProperty diasProperty() {
        return dias;
    }

    @Column(length = 3, nullable = false)
    public int getBonificacao() {
        return bonificacao.get();
    }

    public void setBonificacao(int bonificacao) {
        this.bonificacao.set(bonificacao);
    }

    public IntegerProperty bonificacaoProperty() {
        return bonificacao;
    }

    @Column(nullable = false)
    public BigDecimal getDesconto() {
        return desconto.get();
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto.set(desconto);
    }

    public ObjectProperty<BigDecimal> descontoProperty() {
        return desconto;
    }

    @Column(nullable = false)
    public LocalDate getValidadeDesconto() {
        return validadeDesconto.get();
    }

    public void setValidadeDesconto(LocalDate validadeDesconto) {
        this.validadeDesconto.set(validadeDesconto);
    }

    public ObjectProperty<LocalDate> validadeDescontoProperty() {
        return validadeDesconto;
    }

    @Transient
    public int getProdutoId() {
        return produtoId.get();
    }

    public void setProdutoId(int produtoId) {
        this.produtoId.set(produtoId);
    }

    public IntegerProperty produtoIdProperty() {
        return produtoId;
    }

    @Transient
    public String getProdutoCodigo() {
        return produtoCodigo.get();
    }

    public void setProdutoCodigo(String produtoCodigo) {
        this.produtoCodigo.set(produtoCodigo);
    }

    public StringProperty produtoCodigoProperty() {
        return produtoCodigo;
    }

//    @Transient
//    public String getProdutoDescricao() {
//        return produtoDescricao.get();
//    }
//
//    public void setProdutoDescricao(String produtoDescricao) {
//        this.produtoDescricao.set(produtoDescricao);
//    }
//
//    public StringProperty produtoDescricaoProperty() {
//        return produtoDescricao;
//    }

    @Override
    public String toString() {
        return "EmpresaProdutoValor{" +
                "id=" + id +
                ", produto=" + produto +
                ", valor=" + valor +
                ", qtdMinima=" + qtdMinima +
                ", dias=" + dias +
                ", bonificacao=" + bonificacao +
                ", desconto=" + desconto +
                ", validadeDesconto=" + validadeDesconto +
                ", produtoId=" + produtoId +
                ", produtoCodigo=" + produtoCodigo +
                "} " + super.toString();
    }
}
