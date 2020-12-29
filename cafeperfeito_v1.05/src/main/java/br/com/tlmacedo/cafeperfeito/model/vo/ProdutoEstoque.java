package br.com.tlmacedo.cafeperfeito.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-03-27
 * Time: 11:13
 */

@Entity(name = "ProdutoEstoque")
@Table(name = "produto_estoque")
public class ProdutoEstoque implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private IntegerProperty qtd = new SimpleIntegerProperty();
    private StringProperty lote = new SimpleStringProperty();
    private ObjectProperty<LocalDate> validade = new SimpleObjectProperty<LocalDate>();
    private ObjectProperty<BigDecimal> vlrBruto = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> vlrImpostoEntrada = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> vlrImpostoProduto = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> vlrFreteImpostoEntrada = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> vlrFreteImposto = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> vlrFreteLiquido = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> vlrLiquido = new SimpleObjectProperty<>();
    private Usuario usuarioCadastro = new Usuario();
    private ObjectProperty<LocalDateTime> dataCadastro = new SimpleObjectProperty<>();
    private StringProperty documentoEntrada = new SimpleStringProperty();
    private StringProperty documentoEntradaChaveNfe = new SimpleStringProperty();

    public ProdutoEstoque() {
    }

    public ProdutoEstoque(Integer qtd, String lote, LocalDate validade, BigDecimal vlrBruto, BigDecimal vlrImpostoEntrada, BigDecimal vlrImpostoProduto, BigDecimal vlrFreteImpostoEntrada, BigDecimal vlrFreteImposto, BigDecimal vlrFreteLiquido, BigDecimal vlrLiquido, Usuario usuarioCadastro, LocalDateTime dataCadastro, String documentoEntrada, String documentoEntradaChaveNfe) {
        this.qtd = new SimpleIntegerProperty(qtd);
        this.lote = new SimpleStringProperty(lote);
        this.validade = new SimpleObjectProperty<>(validade);
        this.vlrBruto = new SimpleObjectProperty<>(vlrBruto);
        this.vlrImpostoEntrada = new SimpleObjectProperty<>(vlrImpostoEntrada);
        this.vlrImpostoProduto = new SimpleObjectProperty<>(vlrImpostoProduto);
        this.vlrFreteImpostoEntrada = new SimpleObjectProperty<>(vlrFreteImpostoEntrada);
        this.vlrFreteImposto = new SimpleObjectProperty<>(vlrFreteImposto);
        this.vlrFreteLiquido = new SimpleObjectProperty<>(vlrFreteLiquido);
        this.vlrLiquido = new SimpleObjectProperty<>(vlrLiquido);
        this.usuarioCadastro = usuarioCadastro;
        this.dataCadastro = new SimpleObjectProperty<>(dataCadastro);
        this.documentoEntrada = new SimpleStringProperty(documentoEntrada);
        this.documentoEntradaChaveNfe = new SimpleStringProperty(documentoEntradaChaveNfe);
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
        return validade.get();
    }

    public void setValidade(LocalDate validade) {
        this.validade.set(validade);
    }

    public ObjectProperty<LocalDate> validadeProperty() {
        return validade;
    }

    @Column(length = 19, scale = 2, nullable = false)
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
    public BigDecimal getVlrImpostoEntrada() {
        return vlrImpostoEntrada.get();
    }

    public void setVlrImpostoEntrada(BigDecimal vlrImpostoEntrada) {
        this.vlrImpostoEntrada.set(vlrImpostoEntrada);
    }

    public ObjectProperty<BigDecimal> vlrImpostoEntradaProperty() {
        return vlrImpostoEntrada;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrImpostoProduto() {
        return vlrImpostoProduto.get();
    }

    public void setVlrImpostoProduto(BigDecimal vlrImpostoProduto) {
        this.vlrImpostoProduto.set(vlrImpostoProduto);
    }

    public ObjectProperty<BigDecimal> vlrImpostoProdutoProperty() {
        return vlrImpostoProduto;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrFreteImpostoEntrada() {
        return vlrFreteImpostoEntrada.get();
    }

    public void setVlrFreteImpostoEntrada(BigDecimal vlrFreteImpostoEntrada) {
        this.vlrFreteImpostoEntrada.set(vlrFreteImpostoEntrada);
    }

    public ObjectProperty<BigDecimal> vlrFreteImpostoEntradaProperty() {
        return vlrFreteImpostoEntrada;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrFreteImposto() {
        return vlrFreteImposto.get();
    }

    public void setVlrFreteImposto(BigDecimal vlrFreteImposto) {
        this.vlrFreteImposto.set(vlrFreteImposto);
    }

    public ObjectProperty<BigDecimal> vlrFreteImpostoProperty() {
        return vlrFreteImposto;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrFreteLiquido() {
        return vlrFreteLiquido.get();
    }

    public void setVlrFreteLiquido(BigDecimal vlrFreteLiquido) {
        this.vlrFreteLiquido.set(vlrFreteLiquido);
    }

    public ObjectProperty<BigDecimal> vlrFreteLiquidoProperty() {
        return vlrFreteLiquido;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrLiquido() {
        return vlrLiquido.get();
    }

    public void setVlrLiquido(BigDecimal vlrLiquido) {
        this.vlrLiquido.set(vlrLiquido);
    }

    public ObjectProperty<BigDecimal> vlrLiquidoProperty() {
        return vlrLiquido;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuarioCadastro_id", foreignKey = @ForeignKey(name = "fk_produto_estoque_usuario_cadastro"))
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime getDataCadastro() {
        return dataCadastro.get();
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    public ObjectProperty<LocalDateTime> dataCadastroProperty() {
        return dataCadastro;
    }

    @Column(length = 15, nullable = false)
    public String getDocumentoEntrada() {
        return documentoEntrada.get();
    }

    public void setDocumentoEntrada(String documentoEntrada) {
        this.documentoEntrada.set(documentoEntrada);
    }

    public StringProperty documentoEntradaProperty() {
        return documentoEntrada;
    }

    @Column(length = 44, nullable = false)
    public String getDocumentoEntradaChaveNfe() {
        return documentoEntradaChaveNfe.get();
    }

    public void setDocumentoEntradaChaveNfe(String documentoEntradaChaveNfe) {
        this.documentoEntradaChaveNfe.set(documentoEntradaChaveNfe);
    }

    public StringProperty documentoEntradaChaveNfeProperty() {
        return documentoEntradaChaveNfe;
    }

    @Override
    public String toString() {
        return "ProdutoEstoque{" +
                "id=" + id +
                ", qtd=" + qtd +
                ", lote=" + lote +
                ", validade=" + validade +
                ", vlrBruto=" + vlrBruto +
                ", vlrImpostoEntrada=" + vlrImpostoEntrada +
                ", vlrImpostoProduto=" + vlrImpostoProduto +
                ", vlrFreteImpostoEntrada=" + vlrFreteImpostoEntrada +
                ", vlrFreteImposto=" + vlrFreteImposto +
                ", vlrFreteLiquido=" + vlrFreteLiquido +
                ", vlrLiquido=" + vlrLiquido +
                ", usuarioCadastro=" + usuarioCadastro +
                ", dataCadastro=" + dataCadastro +
                ", documentoEntrada=" + documentoEntrada +
                ", documentoEntradaChaveNfe=" + documentoEntradaChaveNfe +
                '}';
    }
}
