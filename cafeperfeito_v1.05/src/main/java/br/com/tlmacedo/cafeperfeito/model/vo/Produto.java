package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.dao.ProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoNoSistema;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.UnidadeComercial;
import br.com.tlmacedo.cafeperfeito.service.ServiceImage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Produto")
@Table(name = "produto")
public class Produto extends RecursiveTreeObject<Produto> implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty codigo = new SimpleStringProperty("");
    private StringProperty descricao = new SimpleStringProperty("");
    private ObjectProperty<BigDecimal> peso = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private IntegerProperty unidadeComercial = new SimpleIntegerProperty(UnidadeComercial.NULL.getCod());
    private IntegerProperty situacao = new SimpleIntegerProperty(SituacaoNoSistema.NULL.getCod());
    private ObjectProperty<BigDecimal> precoFabrica = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> precoConsumidor = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private IntegerProperty varejo = new SimpleIntegerProperty(1);
    private ObjectProperty<BigDecimal> ultImpostoSefaz = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> ultFrete = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> comissao = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private StringProperty ncm = new SimpleStringProperty("");
    private StringProperty cest = new SimpleStringProperty("");
    private StringProperty nfeGenero = new SimpleStringProperty("");
    private ObjectProperty<LocalDateTime> dataCadastro = new SimpleObjectProperty<>(null);
    private ObjectProperty<LocalDateTime> dataAtualizacao = new SimpleObjectProperty<>(null);
    private Blob imgProduto, imgProdutoBack;
    private FiscalCstOrigem fiscalCstOrigem = new FiscalCstOrigem();
    private FiscalIcms fiscalIcms = new FiscalIcms();
    private FiscalPisCofins fiscalPis = new FiscalPisCofins();
    private FiscalPisCofins fiscalCofins = new FiscalPisCofins();
    private Usuario usuarioCadastro = new Usuario();
    private Usuario usuarioAtualizacao = new Usuario();

    private LongProperty estoque_id = new SimpleLongProperty();
    private IntegerProperty estoque = new SimpleIntegerProperty(0);
    private StringProperty lote = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> validade = new SimpleObjectProperty<>(null);
    private StringProperty notaEntrada = new SimpleStringProperty("");


    private List<ProdutoCodigoBarra> produtoCodigoBarraList = new ArrayList<>();

    private List<ProdutoEstoque> produtoEstoqueList = new ArrayList<>();


    public Produto() {
    }

    public Produto(ProdutoEstoque produtoEstoque) {
        this.estoque_id = new SimpleLongProperty(produtoEstoque.getId());
        this.estoque = new SimpleIntegerProperty(produtoEstoque.getQtd());
        this.lote = new SimpleStringProperty(produtoEstoque.getLote());
        this.validade = new SimpleObjectProperty<>(produtoEstoque.getValidade());
        this.notaEntrada = new SimpleStringProperty(produtoEstoque.getDocumentoEntrada());
    }

    public Produto(String codigo, String descricao, BigDecimal peso, UnidadeComercial unidadeComercial, SituacaoNoSistema situacao, BigDecimal precoFabrica, BigDecimal precoConsumidor, Integer varejo, BigDecimal ultImpostoSefaz, BigDecimal ultFrete, BigDecimal comissao, String ncm, String cest, String nfeGenero, LocalDateTime dataCadastro, LocalDateTime dataAtualizacao, Blob imgProduto, FiscalCstOrigem fiscalCstOrigem, FiscalIcms fiscalIcms, FiscalPisCofins fiscalPis, FiscalPisCofins fiscalCofins, Usuario usuarioCadastro, Usuario usuarioAtualizacao) {
        this.codigo = new SimpleStringProperty(codigo);
        this.descricao = new SimpleStringProperty(descricao);
        this.peso = new SimpleObjectProperty<>(peso);
        this.unidadeComercial = new SimpleIntegerProperty(unidadeComercial.getCod());
        this.situacao = new SimpleIntegerProperty(situacao.getCod());
        this.precoFabrica = new SimpleObjectProperty<>(precoFabrica);
        this.precoConsumidor = new SimpleObjectProperty<>(precoConsumidor);
        this.varejo = new SimpleIntegerProperty(varejo);
        this.ultImpostoSefaz = new SimpleObjectProperty<>(ultImpostoSefaz);
        this.ultFrete = new SimpleObjectProperty<>(ultFrete);
        this.comissao = new SimpleObjectProperty<>(comissao);
        this.ncm = new SimpleStringProperty(ncm);
        this.cest = new SimpleStringProperty(cest);
        this.nfeGenero = new SimpleStringProperty(nfeGenero);
        this.dataCadastro = new SimpleObjectProperty<>(dataCadastro);
        this.dataAtualizacao = new SimpleObjectProperty<>(dataAtualizacao);
        this.imgProduto = imgProduto;
        this.imgProdutoBack = imgProdutoBack;
        this.fiscalCstOrigem = fiscalCstOrigem;
        this.fiscalIcms = fiscalIcms;
        this.fiscalPis = fiscalPis;
        this.fiscalCofins = fiscalCofins;
        this.usuarioCadastro = usuarioCadastro;
        this.usuarioAtualizacao = usuarioAtualizacao;
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

    @Size(max = 15, min = 3, message = "código é inválido")
    @NotEmpty(message = "informe um código")
    @Column(length = 15, nullable = false, unique = true)
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

    @Column(length = 19, scale = 3, nullable = false)
    public BigDecimal getPeso() {
        return peso.get();
    }

    public void setPeso(BigDecimal peso) {
        this.peso.set(peso);
    }

    public ObjectProperty<BigDecimal> pesoProperty() {
        return peso;
    }

    @Column(length = 2, nullable = false)
    public UnidadeComercial getUnidadeComercial() {
        return UnidadeComercial.toEnum(unidadeComercial.get());
    }

    public IntegerProperty unidadeComercialProperty() {
        return unidadeComercial;
    }

    public void setUnidadeComercial(UnidadeComercial unidadeComercial) {
        this.unidadeComercial.set(unidadeComercial.getCod());
    }

    @Column(length = 2, nullable = false)
    public SituacaoNoSistema getSituacao() {
        return SituacaoNoSistema.toEnum(situacao.get());
    }

    public IntegerProperty situacaoProperty() {
        return situacao;
    }

    public void setSituacao(SituacaoNoSistema situacao) {
        this.situacao.set(situacao.getCod());
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getPrecoFabrica() {
        return precoFabrica.get();
    }

    public void setPrecoFabrica(BigDecimal precoFabrica) {
        this.precoFabrica.set(precoFabrica);
    }

    public ObjectProperty<BigDecimal> precoFabricaProperty() {
        return precoFabrica;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getPrecoConsumidor() {
        return precoConsumidor.get();
    }

    public void setPrecoConsumidor(BigDecimal precoConsumidor) {
        this.precoConsumidor.set(precoConsumidor);
    }

    public ObjectProperty<BigDecimal> precoConsumidorProperty() {
        return precoConsumidor;
    }

    @Column(length = 2, nullable = false)
    public int getVarejo() {
        return varejo.get();
    }

    public IntegerProperty varejoProperty() {
        return varejo;
    }

    public void setVarejo(int varejo) {
        this.varejo.set(varejo);
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getUltImpostoSefaz() {
        return ultImpostoSefaz.get();
    }

    public void setUltImpostoSefaz(BigDecimal ultImpostoSefaz) {
        this.ultImpostoSefaz.set(ultImpostoSefaz);
    }

    public ObjectProperty<BigDecimal> ultImpostoSefazProperty() {
        return ultImpostoSefaz;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getUltFrete() {
        return ultFrete.get();
    }

    public void setUltFrete(BigDecimal ultFrete) {
        this.ultFrete.set(ultFrete);
    }

    public ObjectProperty<BigDecimal> ultFreteProperty() {
        return ultFrete;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getComissao() {
        return comissao.get();
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao.set(comissao);
    }

    public ObjectProperty<BigDecimal> comissaoProperty() {
        return comissao;
    }

    @Column(length = 8)
    public String getNcm() {
        return ncm.get();
    }

    public StringProperty ncmProperty() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm.set(ncm.replaceAll("\\D", ""));
    }

    @Column(length = 7)
    public String getCest() {
        return cest.get();
    }

    public StringProperty cestProperty() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest.set(cest.replaceAll("\\D", ""));
    }

    @Column(length = 2, nullable = false)
    public String getNfeGenero() {
        return nfeGenero.get();
    }

    public void setNfeGenero(String nfeGenero) {
        this.nfeGenero.set(nfeGenero.replaceAll("\\D", ""));
    }

    public StringProperty nfeGeneroProperty() {
        return nfeGenero;
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

    @UpdateTimestamp
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao.get();
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao.set(dataAtualizacao);
    }

    public ObjectProperty<LocalDateTime> dataAtualizacaoProperty() {
        return dataAtualizacao;
    }

    @ManyToOne
    @JoinColumn(name = "fiscalCstOrigem_id", foreignKey = @ForeignKey(name = "fk_produto_fiscal_cst_origem"), nullable = false)
    public FiscalCstOrigem getFiscalCstOrigem() {
        return fiscalCstOrigem;
    }

    public void setFiscalCstOrigem(FiscalCstOrigem fiscalCstOrigem) {
        this.fiscalCstOrigem = fiscalCstOrigem;
    }

    @ManyToOne
    @JoinColumn(name = "fiscalIcms_id", foreignKey = @ForeignKey(name = "fk_produto_fiscal_icms"), nullable = false)
    public FiscalIcms getFiscalIcms() {
        return fiscalIcms;
    }

    public void setFiscalIcms(FiscalIcms fiscalIcms) {
        this.fiscalIcms = fiscalIcms;
    }

    @ManyToOne
    @JoinColumn(name = "fiscalPis_id", foreignKey = @ForeignKey(name = "fk_produto_fiscal_pis_cofins_pis"), nullable = false)
    public FiscalPisCofins getFiscalPis() {
        return fiscalPis;
    }

    public void setFiscalPis(FiscalPisCofins fiscalPis) {
        this.fiscalPis = fiscalPis;
    }

    @ManyToOne
    @JoinColumn(name = "fiscalCofins_id", foreignKey = @ForeignKey(name = "fk_produto_fiscal_pis_cofins_cofins"), nullable = false)
    public FiscalPisCofins getFiscalCofins() {
        return fiscalCofins;
    }

    public void setFiscalCofins(FiscalPisCofins fiscalCofins) {
        this.fiscalCofins = fiscalCofins;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuarioCadastro_id", foreignKey = @ForeignKey(name = "fk_produto_usuario_cadastro"), nullable = false)
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuarioAtualizacao_id", foreignKey = @ForeignKey(name = "fk_produto_usuario_atualizacao"))
    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ProdutoCodigoBarra> getProdutoCodigoBarraList() {
        return produtoCodigoBarraList;
    }

    public void setProdutoCodigoBarraList(List<ProdutoCodigoBarra> produtoCodigoBarraList) {
        this.produtoCodigoBarraList = produtoCodigoBarraList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ProdutoEstoque> getProdutoEstoqueList() {
        return produtoEstoqueList;
    }

    public void setProdutoEstoqueList(List<ProdutoEstoque> produtoEstoqueList) {
        this.produtoEstoqueList = produtoEstoqueList;
    }


    @JsonIgnore
    @SuppressWarnings("JpaAttributeTypeInspection")
    public Blob getImgProduto() {
        return imgProduto;
    }

    public void setImgProduto(Blob imgProduto) {
        this.imgProduto = imgProduto;
    }

    public void setImageProduto(Image imageProduto) {
        try {
            this.imgProduto = new SerialBlob(ServiceImage.getInputStreamFromImage(imageProduto).readAllBytes());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @JsonIgnore
    @Transient
    public Image getImageProduto() {
        try {
            return ServiceImage.getImageFromInputStream(getImgProduto().getBinaryStream());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @JsonIgnore
    @Transient
    public Image getImageProdutoBack() {
        try {
            return ServiceImage.getImageFromInputStream(imgProdutoBack.getBinaryStream());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setImgProdutoBack(Image imageProdutoBack) {
        try {
            this.imgProdutoBack = new SerialBlob(ServiceImage.getInputStreamFromImage(imageProdutoBack).readAllBytes());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transient
    public long getEstoque_id() {
        return estoque_id.get();
    }

    public void setEstoque_id(long estoque_id) {
        this.estoque_id.set(estoque_id);
    }

    public LongProperty estoque_idProperty() {
        return estoque_id;
    }

    @Transient
    public int getEstoque() {
        return estoque.get();
    }

    public void setEstoque(int estoque) {
        this.estoque.set(estoque);
    }

    public IntegerProperty estoqueProperty() {
        return estoque;
    }

    @Transient
    public String getLote() {
        return lote.get();
    }

    public void setLote(String lote) {
        this.lote.set(lote);
    }

    public StringProperty loteProperty() {
        return lote;
    }

    @Transient
    public LocalDate getValidade() {
        return validade.get();
    }

    public void setValidade(LocalDate validade) {
        this.validade.set(validade);
    }

    public ObjectProperty<LocalDate> validadeProperty() {
        return validade;
    }

    @Transient
    public String getNotaEntrada() {
        return notaEntrada.get();
    }

    public void setNotaEntrada(String notaEntrada) {
        this.notaEntrada.set(notaEntrada);
    }

    public StringProperty notaEntradaProperty() {
        return notaEntrada;
    }

    @Override
    public Produto clone() throws CloneNotSupportedException {
        Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produto = produtoDAO.getById(Produto.class, getId());
        return produto;
    }

    @Override
    public String toString() {
        return descricaoProperty().get();
    }
}
