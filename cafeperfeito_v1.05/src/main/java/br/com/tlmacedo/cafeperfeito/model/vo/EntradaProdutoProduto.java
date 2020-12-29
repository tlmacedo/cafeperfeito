package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.dao.ProdutoDAO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-02
 * Time: 11:47
 */

@Entity(name = "EntradaProdutoProduto")
@Table(name = "entrada_produto_produto")
public class EntradaProdutoProduto extends RecursiveTreeObject<EntradaProdutoProduto> implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty codigo = new SimpleStringProperty();
    private StringProperty descricao = new SimpleStringProperty();
    private StringProperty lote = new SimpleStringProperty();
    private ObjectProperty<LocalDate> validade = new SimpleObjectProperty<>();
    private IntegerProperty qtd = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> vlrFabrica = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrDesconto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrImposto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private IntegerProperty estoque = new SimpleIntegerProperty(0);
    private IntegerProperty varejo = new SimpleIntegerProperty(0);
    private IntegerProperty volume = new SimpleIntegerProperty(0);

    private Produto produto;

    public EntradaProdutoProduto() {
    }

    public EntradaProdutoProduto(String codigo, String descricao, String lote, LocalDate validade, Integer qtd, BigDecimal vlrFabrica, BigDecimal vlrBruto, BigDecimal vlrDesconto, BigDecimal vlrImposto, BigDecimal vlrLiquido, Integer estoque, Integer varejo, Integer volume, Produto produto) {
        this.codigo = new SimpleStringProperty(codigo);
        this.descricao = new SimpleStringProperty(descricao);
        this.lote = new SimpleStringProperty(lote);
        this.validade = new SimpleObjectProperty<>(validade);
        this.qtd = new SimpleIntegerProperty(qtd);
        this.vlrFabrica = new SimpleObjectProperty<>(vlrFabrica);
        this.vlrBruto = new SimpleObjectProperty<>(vlrBruto);
        this.vlrDesconto = new SimpleObjectProperty<>(vlrDesconto);
        this.vlrImposto = new SimpleObjectProperty<>(vlrImposto);
        this.vlrLiquido = new SimpleObjectProperty<>(vlrLiquido);
        this.estoque = new SimpleIntegerProperty(estoque);
        this.varejo = new SimpleIntegerProperty(varejo);
        this.volume = new SimpleIntegerProperty(volume);
        this.produto = produto;
    }

    public EntradaProdutoProduto(Produto produto) {
        this.codigo = new SimpleStringProperty(produto.getCodigo());
        this.descricao = new SimpleStringProperty(produto.getDescricao());
        this.lote = new SimpleStringProperty("");
        this.validade = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.qtd = new SimpleIntegerProperty(1);
        this.vlrFabrica = new SimpleObjectProperty<>(produto.getPrecoFabrica().setScale(2));
        this.vlrBruto = new SimpleObjectProperty<>(produto.getPrecoFabrica().setScale(2));
        this.vlrDesconto = new SimpleObjectProperty<>(BigDecimal.ZERO.setScale(2));
        this.vlrImposto = new SimpleObjectProperty<>(BigDecimal.ZERO.setScale(2));
        this.vlrLiquido = new SimpleObjectProperty<>(produto.getPrecoFabrica().setScale(2));
        this.estoque = new SimpleIntegerProperty(produto.getEstoque());
        this.varejo = new SimpleIntegerProperty(produto.getVarejo());
        this.volume = new SimpleIntegerProperty(1);
        this.produto = new ProdutoDAO().getById(Produto.class, produto.getId());
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

    @Column(length = 15, nullable = false)
    public String getCodigo() {
        return codigo.get();
    }

    public StringProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    @Column(length = 120, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    @Column(length = 15, nullable = false)
    public String getLote() {
        return lote.get();
    }

    public void setLote(String lote) {
        this.lote.set(lote);
    }

    public StringProperty loteProperty() {
        return lote;
    }

    @Column(nullable = false)
    public LocalDate getValidade() {
        return validade.get() != null ? validade.get() : LocalDate.now();
    }

    public void setValidade(LocalDate validade) {
        this.validade.set(validade);
    }

    public ObjectProperty<LocalDate> validadeProperty() {
        return validade;
    }

    @Column(length = 4, nullable = false)
    public int getQtd() {
        return qtd.get();
    }

    public void setQtd(int qtd) {
        this.qtd.set(qtd);
    }

    public IntegerProperty qtdProperty() {
        return qtd;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrFabrica() {
        return vlrFabrica.get();
    }

    public void setVlrFabrica(BigDecimal vlrFabrica) {
        this.vlrFabrica.set(vlrFabrica);
    }

    public ObjectProperty<BigDecimal> vlrFabricaProperty() {
        return vlrFabrica;
    }

    @Transient
    public BigDecimal getVlrBruto() {
        return vlrBruto.get();
    }

    public void setVlrBruto(BigDecimal vlrBruto) {
        this.vlrBruto.set(vlrBruto);
    }

    public ObjectProperty<BigDecimal> vlrBrutoProperty() {
        return vlrBruto;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrDesconto() {
        return vlrDesconto.get();
    }

    public void setVlrDesconto(BigDecimal vlrDesconto) {
        this.vlrDesconto.set(vlrDesconto);
    }

    public ObjectProperty<BigDecimal> vlrDescontoProperty() {
        return vlrDesconto;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrImposto() {
        return vlrImposto.get();
    }

    public void setVlrImposto(BigDecimal vlrImposto) {
        this.vlrImposto.set(vlrImposto);
    }

    public ObjectProperty<BigDecimal> vlrImpostoProperty() {
        return vlrImposto;
    }

    @Transient
    public BigDecimal getVlrLiquido() {
        return vlrLiquido.get();
    }

    public void setVlrLiquido(BigDecimal vlrLiquido) {
        this.vlrLiquido.set(vlrLiquido);
    }

    public ObjectProperty<BigDecimal> vlrLiquidoProperty() {
        return vlrLiquido;
    }

    @Column(length = 4, nullable = false)
    public int getEstoque() {
        return estoque.get();
    }

    public void setEstoque(int estoque) {
        this.estoque.set(estoque);
    }

    public IntegerProperty estoqueProperty() {
        return estoque;
    }

    @Column(length = 2, nullable = false)
    public int getVarejo() {
        return varejo.get();
    }

    public void setVarejo(int varejo) {
        this.varejo.set(varejo);
    }

    public IntegerProperty varejoProperty() {
        return varejo;
    }

    @Transient
    public int getVolume() {
        return volume.get();
    }

    public void setVolume(int volume) {
        this.volume.set(volume);
    }

    public IntegerProperty volumeProperty() {
        return volume;
    }

    public ObservableValue changeProperty() {
        return Bindings.concat(qtd);
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_entrada_produto_produto_produto"))
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "EntradaProdutoProduto{" +
                "id=" + id +
                ", codigo=" + codigo +
                ", descricao=" + descricao +
                ", lote=" + lote +
                ", validade=" + validade +
                ", qtd=" + qtd +
                ", vlrFabrica=" + vlrFabrica +
                ", vlrBruto=" + vlrBruto +
                ", vlrDesconto=" + vlrDesconto +
                ", vlrImposto=" + vlrImposto +
                ", vlrLiquido=" + vlrLiquido +
                ", estoque=" + estoque +
                ", varejo=" + varejo +
                ", volume=" + volume +
                ", produto=" + produto +
                "} " + super.toString();
    }
}
