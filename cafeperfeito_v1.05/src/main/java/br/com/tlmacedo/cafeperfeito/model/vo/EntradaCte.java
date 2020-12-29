package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.CteTomadorServico;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.NfeCteModelo;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-02-25
 * Time: 23:12
 */

@Entity(name = "EntradaCte")
@Table(name = "entrada_cte")
public class EntradaCte implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty chave = new SimpleStringProperty("");
    private IntegerProperty tomadorServico = new SimpleIntegerProperty();
    private StringProperty numero = new SimpleStringProperty("");
    private StringProperty serie = new SimpleStringProperty("");
    private IntegerProperty modelo = new SimpleIntegerProperty(0);
    private ObjectProperty<LocalDate> dataEmissao = new SimpleObjectProperty<>(LocalDate.now());
    private Empresa emissor = new Empresa();
    private FiscalFreteSituacaoTributaria situacaoTributaria;
    private ObjectProperty<BigDecimal> vlrCte = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private IntegerProperty qtdVolume = new SimpleIntegerProperty(0);
    private ObjectProperty<BigDecimal> pesoBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrFreteBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrTaxas = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrColeta = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrImpostoFrete = new SimpleObjectProperty<>(BigDecimal.ZERO);

    private EntradaFiscal entradaFiscal = new EntradaFiscal();

    public EntradaCte() {
    }

    public EntradaCte(String chave, String numero, String serie, Integer qtdVolume, CteTomadorServico tomadorServico, NfeCteModelo modelo, BigDecimal vlrCte, BigDecimal pesoBruto, BigDecimal vlrFreteBruto, BigDecimal vlrTaxas, BigDecimal vlrColeta, BigDecimal vlrImpostoFrete, LocalDate dataEmissao, FiscalFreteSituacaoTributaria situacaoTributaria, Empresa emissor, EntradaFiscal entradaFiscal) {
        this.chave = new SimpleStringProperty(chave);
        this.numero = new SimpleStringProperty(numero);
        this.serie = new SimpleStringProperty(serie);
        this.qtdVolume = new SimpleIntegerProperty(qtdVolume);
        this.tomadorServico = new SimpleIntegerProperty(tomadorServico.getCod());
        this.modelo = new SimpleIntegerProperty(modelo.getCod());
        this.vlrCte = new SimpleObjectProperty<>(vlrCte);
        this.pesoBruto = new SimpleObjectProperty<>(pesoBruto);
        this.vlrFreteBruto = new SimpleObjectProperty<>(vlrFreteBruto);
        this.vlrTaxas = new SimpleObjectProperty<>(vlrTaxas);
        this.vlrColeta = new SimpleObjectProperty<>(vlrColeta);
        this.vlrImpostoFrete = new SimpleObjectProperty<>(vlrImpostoFrete);
        this.dataEmissao = new SimpleObjectProperty<>(dataEmissao);
        this.situacaoTributaria = situacaoTributaria;
        this.emissor = emissor;
        this.entradaFiscal = entradaFiscal;
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

    @Column(length = 44, nullable = false)
    public String getChave() {
        return chave.get();
    }

    public void setChave(String chave) {
        this.chave.set(chave.replaceAll("\\D", ""));
    }

    public StringProperty chaveProperty() {
        return chave;
    }

    @Column(length = 1, nullable = false)
    public CteTomadorServico getTomadorServico() {
        return CteTomadorServico.toEnum(tomadorServico.get());
    }

    public void setTomadorServico(CteTomadorServico tomadorServico) {
        this.tomadorServico.set(tomadorServico.getCod());
    }

    public IntegerProperty tomadorServicoProperty() {
        return tomadorServico;
    }

    @Column(length = 9, nullable = false)
    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        this.numero.set(numero.replaceAll("\\D", ""));
    }

    public StringProperty numeroProperty() {
        return numero;
    }

    @Column(length = 3, nullable = false)
    public String getSerie() {
        return serie.get();
    }

    public void setSerie(String serie) {
        this.serie.set(serie.replaceAll("\\D", ""));
    }

    public StringProperty serieProperty() {
        return serie;
    }

    @Column(length = 1, nullable = false)
    public NfeCteModelo getModelo() {
        return NfeCteModelo.toEnum(modelo.get());
    }

    public void setModelo(NfeCteModelo modelo) {
        this.modelo.set(modelo.getCod());
    }

    public IntegerProperty modeloProperty() {
        return modelo;
    }

    @Column(nullable = false)
    public LocalDate getDataEmissao() {
        return dataEmissao.get();
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao.set(dataEmissao);
    }

    public ObjectProperty<LocalDate> dataEmissaoProperty() {
        return dataEmissao;
    }

    @ManyToOne
    @JoinColumn(name = "emissor_id", foreignKey = @ForeignKey(name = "fk_entrada_cte_empresa"), nullable = false)
    public Empresa getEmissor() {
        return emissor;
    }

    public void setEmissor(Empresa emissor) {
        this.emissor = emissor;
    }

    @ManyToOne
    @JoinColumn(name = "situacaoTributaria_id", foreignKey = @ForeignKey(name = "fk_entrada_cte_fiscal_frete_situacao_tributaria"), nullable = false)
    public FiscalFreteSituacaoTributaria getSituacaoTributaria() {
        return situacaoTributaria;
    }

    public void setSituacaoTributaria(FiscalFreteSituacaoTributaria situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrCte() {
        return vlrCte.get();
    }

    public void setVlrCte(BigDecimal vlrCte) {
        this.vlrCte.set(vlrCte);
    }

    public ObjectProperty<BigDecimal> vlrCteProperty() {
        return vlrCte;
    }

    @Column(length = 4, nullable = false)
    public int getQtdVolume() {
        return qtdVolume.get();
    }

    public IntegerProperty qtdVolumeProperty() {
        return qtdVolume;
    }

    public void setQtdVolume(int qtdVolume) {
        this.qtdVolume.set(qtdVolume);
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getPesoBruto() {
        return pesoBruto.get();
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto.set(pesoBruto);
    }

    public ObjectProperty<BigDecimal> pesoBrutoProperty() {
        return pesoBruto;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrFreteBruto() {
        return vlrFreteBruto.get();
    }

    public void setVlrFreteBruto(BigDecimal vlrFreteBruto) {
        this.vlrFreteBruto.set(vlrFreteBruto);
    }

    public ObjectProperty<BigDecimal> vlrFreteBrutoProperty() {
        return vlrFreteBruto;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrTaxas() {
        return vlrTaxas.get();
    }

    public void setVlrTaxas(BigDecimal vlrTaxas) {
        this.vlrTaxas.set(vlrTaxas);
    }

    public ObjectProperty<BigDecimal> vlrTaxasProperty() {
        return vlrTaxas;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrColeta() {
        return vlrColeta.get();
    }

    public void setVlrColeta(BigDecimal vlrColeta) {
        this.vlrColeta.set(vlrColeta);
    }

    public ObjectProperty<BigDecimal> vlrColetaProperty() {
        return vlrColeta;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getVlrImpostoFrete() {
        return vlrImpostoFrete.get();
    }

    public void setVlrImpostoFrete(BigDecimal vlrImpostoFrete) {
        this.vlrImpostoFrete.set(vlrImpostoFrete);
    }

    public ObjectProperty<BigDecimal> vlrImpostoFreteProperty() {
        return vlrImpostoFrete;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "entradaFiscal_id", foreignKey = @ForeignKey(name = "fk_entrada_cte_entrada_fiscal"))
    public EntradaFiscal getEntradaFiscal() {
        return entradaFiscal;
    }

    public void setEntradaFiscal(EntradaFiscal entradaFiscal) {
        this.entradaFiscal = entradaFiscal;
    }

    @Override
    public String toString() {
        return "EntradaCte{" +
                "id=" + id +
                ", chave=" + chave +
                ", numero=" + numero +
                ", serie=" + serie +
                ", vlrCte=" + vlrCte +
                ", qtdVolume=" + qtdVolume +
                ", pesoBruto=" + pesoBruto +
                ", vlrFreteBruto=" + vlrFreteBruto +
                ", vlrTaxas=" + vlrTaxas +
                ", vlrColeta=" + vlrColeta +
                ", vlrImpostoFrete=" + vlrImpostoFrete +
                ", dataEmissao=" + dataEmissao +
                ", tomadorServico=" + tomadorServico +
                ", modelo=" + modelo +
                ", situacaoTributaria=" + situacaoTributaria +
                ", emissor=" + emissor +
                '}';
    }

}
