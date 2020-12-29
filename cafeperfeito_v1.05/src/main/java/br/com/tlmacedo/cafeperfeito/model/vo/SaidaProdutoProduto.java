package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.dao.ProdutoDAO;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-08
 * Time: 11:19
 */

@Entity(name = "SaidaProdutoProduto")
@Table(name = "saida_produto_produto")
public class SaidaProdutoProduto implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty codigo = new SimpleStringProperty("");
    private StringProperty descricao = new SimpleStringProperty("");

    private ProdutoEstoque produtoEstoque = new ProdutoEstoque();

    private IntegerProperty qtd = new SimpleIntegerProperty(0);
    //    private ObjectProperty<BigDecimal> vlrDescontoUnitario = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrConsumidor = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrDescontoLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private IntegerProperty volume = new SimpleIntegerProperty(0);

    private Produto produto = new Produto();

    public SaidaProdutoProduto() {
    }

    public SaidaProdutoProduto(String codigo, String descricao, ProdutoEstoque produtoEstoque, Integer qtd, BigDecimal vlrConsumidor, BigDecimal vlrBruto, BigDecimal vlrDescontoLiquido, BigDecimal vlrLiquido, Integer volume, Produto produto) {
        this.codigo = new SimpleStringProperty(codigo);
        this.descricao = new SimpleStringProperty(descricao);
        this.produtoEstoque = produtoEstoque;
        this.qtd = new SimpleIntegerProperty(qtd);
//        this.vlrDescontoUnitario = new SimpleObjectProperty<>(vlrDescontoUnitario);
        this.vlrConsumidor = new SimpleObjectProperty<>(vlrConsumidor);
        this.vlrBruto = new SimpleObjectProperty<>(vlrBruto);
        this.vlrDescontoLiquido = new SimpleObjectProperty<>(vlrDescontoLiquido);
        this.vlrLiquido = new SimpleObjectProperty<>(vlrLiquido);
        this.volume = new SimpleIntegerProperty(volume);
        this.produto = produto;
    }

    public SaidaProdutoProduto(Produto produto, Long estoque_id) {
        this.produto = new ProdutoDAO().getById(Produto.class, produto.getId());
        this.codigo = new SimpleStringProperty(produto.getCodigo());
        this.descricao = new SimpleStringProperty(produto.getDescricao());
        this.qtd = new SimpleIntegerProperty(1);
        if (estoque_id == null)
            this.produtoEstoque = getProduto().getProdutoEstoqueList().stream()
                    .filter(estq -> estq.getQtd() > 0)
                    .sorted(Comparator.comparing(ProdutoEstoque::getValidade)).findFirst().orElse(null);
        else
            this.produtoEstoque = getProduto().getProdutoEstoqueList()
                    .stream().filter(estq -> estq.getId() == estoque_id).findFirst().orElse(null);
//        this.vlrDescontoUnitario = new SimpleObjectProperty<>(BigDecimal.ZERO.setScale(2));
        this.vlrConsumidor = new SimpleObjectProperty<>(produto.getPrecoConsumidor().setScale(2));
        this.vlrBruto = new SimpleObjectProperty<>(produto.getPrecoConsumidor().setScale(2));
        this.vlrDescontoLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO.setScale(2));
        this.vlrLiquido = new SimpleObjectProperty<>(produto.getPrecoConsumidor().setScale(2));
        this.volume = new SimpleIntegerProperty(1);
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
    @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_saida_produto_produto_produto"))
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Column(length = 15, nullable = false)
    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public StringProperty codigoProperty() {
        return codigo;
    }

    @Column(length = 120, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public StringProperty descricaoProperty() {
        return descricao;
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

//    @Transient
//    public BigDecimal getVlrDescontoUnitario() {
//        return vlrDescontoUnitario.get();
//    }
//
//    public ObjectProperty<BigDecimal> vlrDescontoUnitarioProperty() {
//        return vlrDescontoUnitario;
//    }
//
//    public void setVlrDescontoUnitario(BigDecimal vlrDescontoUnitario) {
//        this.vlrDescontoUnitario.set(vlrDescontoUnitario);
//    }

    @Column(nullable = false)
    public BigDecimal getVlrConsumidor() {
        return vlrConsumidor.get();
    }

    public void setVlrConsumidor(BigDecimal vlrConsumidor) {
        this.vlrConsumidor.set(vlrConsumidor);
    }

    public ObjectProperty<BigDecimal> vlrConsumidorProperty() {
        return vlrConsumidor;
    }

    @Column(nullable = false)
    public BigDecimal getVlrBruto() {
        return vlrBruto.get();
    }

    public void setVlrBruto(BigDecimal vlrBruto) {
        this.vlrBruto.set(vlrBruto);
    }

    public ObjectProperty<BigDecimal> vlrBrutoProperty() {
        return vlrBruto;
    }

    @Column(nullable = false)
    public BigDecimal getVlrDescontoLiquido() {
        return vlrDescontoLiquido.get();
    }

    public void setVlrDescontoLiquido(BigDecimal vlrDesconto) {
        this.vlrDescontoLiquido.set(vlrDesconto);
    }

    public ObjectProperty<BigDecimal> vlrDescontoLiquidoProperty() {
        return vlrDescontoLiquido;
    }

    @Column(nullable = false)
    public BigDecimal getVlrLiquido() {
        return vlrLiquido.get();
    }

    public void setVlrLiquido(BigDecimal vlrLiquido) {
        this.vlrLiquido.set(vlrLiquido);
    }

    public ObjectProperty<BigDecimal> vlrLiquidoProperty() {
        return vlrLiquido;
    }

    @ManyToOne
    @JoinColumn(name = "estoque_id", foreignKey = @ForeignKey(name = "fk_saida_produto_produto_produto"))
    public ProdutoEstoque getProdutoEstoque() {
        return produtoEstoque;
    }

    public void setProdutoEstoque(ProdutoEstoque produtoEstoque) {
        this.produtoEstoque = produtoEstoque;
    }

    @Column(length = 3, nullable = false)
    public int getVolume() {
        return volume.get();
    }

    public void setVolume(int volume) {
        this.volume.set(volume);
    }

    public IntegerProperty volumeProperty() {
        return volume;
    }

    @Override
    public String toString() {
        return "SaidaProdutoProduto{" +
                "id=" + id +
                ", produto=" + produto +
                ", codigo=" + codigo +
                ", descricao=" + descricao +
                ", qtd=" + qtd +
                ", vlrConsumidor=" + vlrConsumidor +
                ", vlrBruto=" + vlrBruto +
                ", vlrDesconto=" + vlrDescontoLiquido +
                ", vlrLiquido=" + vlrLiquido +
                '}';
    }

}
