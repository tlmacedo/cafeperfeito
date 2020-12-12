package br.com.tlmacedo.cafeperfeito.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "EntrdaCte")
@Table(name = "entrada_cte")
public class EntradaCte implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private ObjectProperty<EntradaProduto> entradaProduto = new SimpleObjectProperty<>();
    private StringProperty chave = new SimpleStringProperty();
    private IntegerProperty tomadorServico = new SimpleIntegerProperty();
    private StringProperty numero = new SimpleStringProperty();
    private StringProperty serie = new SimpleStringProperty();
    private IntegerProperty modelo = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> dtEmissao = new SimpleObjectProperty<>();
    private ObjectProperty<Empresa> transportadora = new SimpleObjectProperty<>();
    private ObjectProperty<FiscalFreteSituacaoTributaria> situacaoTributaria = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> vlrCte = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private IntegerProperty qtdVolume = new SimpleIntegerProperty();
    private ObjectProperty<BigDecimal> pesoBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrFreteBruto = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrTaxas = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrColeta = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrImpostoFrete = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<EntradaFiscal> entradaFiscal = new SimpleObjectProperty<>();

    public EntradaCte() {
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
    @OneToOne(fetch = FetchType.LAZY)
    public EntradaProduto getEntradaProduto() {
        return entradaProduto.get();
    }

    public ObjectProperty<EntradaProduto> entradaProdutoProperty() {
        return entradaProduto;
    }

    public void setEntradaProduto(EntradaProduto entradaProduto) {
        this.entradaProduto.set(entradaProduto);
    }

    @Column(length = 44, nullable = false, unique = true)
    public String getChave() {
        return chave.get();
    }

    public StringProperty chaveProperty() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave.set(chave);
    }

    @Column(length = 1, nullable = false)
    public int getTomadorServico() {
        return tomadorServico.get();
    }

    public IntegerProperty tomadorServicoProperty() {
        return tomadorServico;
    }

    public void setTomadorServico(int tomadorServico) {
        this.tomadorServico.set(tomadorServico);
    }

    @Column(length = 9, nullable = false)
    public String getNumero() {
        return numero.get();
    }

    public StringProperty numeroProperty() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    @Column(length = 3, nullable = false)
    public String getSerie() {
        return serie.get();
    }

    public StringProperty serieProperty() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie.set(serie);
    }

    @Column(length = 2, nullable = false)
    public int getModelo() {
        return modelo.get();
    }

    public IntegerProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo.set(modelo);
    }

    public LocalDate getDtEmissao() {
        return dtEmissao.get();
    }

    public ObjectProperty<LocalDate> dtEmissaoProperty() {
        return dtEmissao;
    }

    public void setDtEmissao(LocalDate dtEmissao) {
        this.dtEmissao.set(dtEmissao);
    }

    @ManyToOne//(fetch = FetchType.LAZY)
    public Empresa getTransportadora() {
        return transportadora.get();
    }

    public ObjectProperty<Empresa> transportadoraProperty() {
        return transportadora;
    }

    public void setTransportadora(Empresa transportadora) {
        this.transportadora.set(transportadora);
    }

    @ManyToOne//(fetch = FetchType.LAZY)
    public FiscalFreteSituacaoTributaria getSituacaoTributaria() {
        return situacaoTributaria.get();
    }

    public ObjectProperty<FiscalFreteSituacaoTributaria> situacaoTributariaProperty() {
        return situacaoTributaria;
    }

    public void setSituacaoTributaria(FiscalFreteSituacaoTributaria situacaoTributaria) {
        this.situacaoTributaria.set(situacaoTributaria);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrCte() {
        return vlrCte.get();
    }

    public ObjectProperty<BigDecimal> vlrCteProperty() {
        return vlrCte;
    }

    public void setVlrCte(BigDecimal vlrCte) {
        this.vlrCte.set(vlrCte);
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

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getPesoBruto() {
        return pesoBruto.get();
    }

    public ObjectProperty<BigDecimal> pesoBrutoProperty() {
        return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto.set(pesoBruto);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrFreteBruto() {
        return vlrFreteBruto.get();
    }

    public ObjectProperty<BigDecimal> vlrFreteBrutoProperty() {
        return vlrFreteBruto;
    }

    public void setVlrFreteBruto(BigDecimal vlrFreteBruto) {
        this.vlrFreteBruto.set(vlrFreteBruto);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrTaxas() {
        return vlrTaxas.get();
    }

    public ObjectProperty<BigDecimal> vlrTaxasProperty() {
        return vlrTaxas;
    }

    public void setVlrTaxas(BigDecimal vlrTaxas) {
        this.vlrTaxas.set(vlrTaxas);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrColeta() {
        return vlrColeta.get();
    }

    public ObjectProperty<BigDecimal> vlrColetaProperty() {
        return vlrColeta;
    }

    public void setVlrColeta(BigDecimal vlrColeta) {
        this.vlrColeta.set(vlrColeta);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrImpostoFrete() {
        return vlrImpostoFrete.get();
    }

    public ObjectProperty<BigDecimal> vlrImpostoFreteProperty() {
        return vlrImpostoFrete;
    }

    public void setVlrImpostoFrete(BigDecimal vlrImpostoFrete) {
        this.vlrImpostoFrete.set(vlrImpostoFrete);
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public EntradaFiscal getEntradaFiscal() {
        return entradaFiscal.get();
    }

    public ObjectProperty<EntradaFiscal> entradaFiscalProperty() {
        return entradaFiscal;
    }

    public void setEntradaFiscal(EntradaFiscal entradaFiscal) {
        this.entradaFiscal.set(entradaFiscal);
    }

    @Override
    public String toString() {
        return "EntradaCte{" +
                "id=" + id +
                ", entradaProduto=" + entradaProduto +
                ", chave=" + chave +
                ", tomadorServico=" + tomadorServico +
                ", numero=" + numero +
                ", serie=" + serie +
                ", modelo=" + modelo +
                ", dtEmissao=" + dtEmissao +
                ", transportadora=" + transportadora +
                ", situacaoTributaria=" + situacaoTributaria +
                ", vlrCte=" + vlrCte +
                ", qtdVolume=" + qtdVolume +
                ", pesoBruto=" + pesoBruto +
                ", vlrFreteBruto=" + vlrFreteBruto +
                ", vlrTaxas=" + vlrTaxas +
                ", vlrColeta=" + vlrColeta +
                ", vlrImpostoFrete=" + vlrImpostoFrete +
                ", entradaFiscal=" + entradaFiscal +
                '}';
    }
}
