package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity(name = "SaidaProdutoNfe")
@Table(name = "saida_produto_nfe")
public class SaidaProdutoNfe implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private BooleanProperty cancelada = new SimpleBooleanProperty(false);
    private ObjectProperty<SaidaProduto> saidaProduto = new SimpleObjectProperty<>();
    private StringProperty chave = new SimpleStringProperty();
    private IntegerProperty statusSefaz = new SimpleIntegerProperty();
    private IntegerProperty naturezaOperacao = new SimpleIntegerProperty();
    private IntegerProperty modelo = new SimpleIntegerProperty();
    private IntegerProperty serie = new SimpleIntegerProperty();
    private IntegerProperty numero = new SimpleIntegerProperty();
    private ObjectProperty<LocalDateTime> dtHoraEmissao = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDateTime> dtHoraSaida = new SimpleObjectProperty<>();
    private IntegerProperty destinoOperacao = new SimpleIntegerProperty();
    private IntegerProperty impressaoTpImp = new SimpleIntegerProperty();
    private IntegerProperty impressaoTpEmis = new SimpleIntegerProperty();
    private IntegerProperty impressaoFinNFe = new SimpleIntegerProperty();
    private BooleanProperty impressaoLtProduto = new SimpleBooleanProperty(false);
    private IntegerProperty consumidorFinal = new SimpleIntegerProperty();
    private IntegerProperty indicadorPresenca = new SimpleIntegerProperty();
    private IntegerProperty modFrete = new SimpleIntegerProperty();
    private ObjectProperty<Empresa> transportador;
    private StringProperty cobrancaNumero = new SimpleStringProperty();
    private IntegerProperty pagamentoIndicador = new SimpleIntegerProperty();
    private IntegerProperty pagamentoMeio = new SimpleIntegerProperty();
    private StringProperty informacaoAdicional = new SimpleStringProperty();
    private StringProperty digVal = new SimpleStringProperty();
    private ObjectProperty<Blob> xmlAssinatura = new SimpleObjectProperty<>();
    private ObjectProperty<Blob> xmlConsRecibo = new SimpleObjectProperty<>();
    private ObjectProperty<Blob> xmlProtNfe = new SimpleObjectProperty<>();

    public SaidaProdutoNfe() {
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


    public boolean isCancelada() {
        return cancelada.get();
    }

    public BooleanProperty canceladaProperty() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada.set(cancelada);
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public SaidaProduto getSaidaProduto() {
        return saidaProduto.get();
    }

    public ObjectProperty<SaidaProduto> saidaProdutoProperty() {
        return saidaProduto;
    }

    public void setSaidaProduto(SaidaProduto saidaProduto) {
        this.saidaProduto.set(saidaProduto);
    }

    @Column(length = 47, nullable = false, unique = true)
    public String getChave() {
        return chave.get();
    }

    public StringProperty chaveProperty() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave.set(chave);
    }

    @Column(length = 3, nullable = false)
    public int getStatusSefaz() {
        return statusSefaz.get();
    }

    public IntegerProperty statusSefazProperty() {
        return statusSefaz;
    }

    public void setStatusSefaz(int statusSefaz) {
        this.statusSefaz.set(statusSefaz);
    }

    @Column(length = 1, nullable = false)
    public int getNaturezaOperacao() {
        return naturezaOperacao.get();
    }

    public IntegerProperty naturezaOperacaoProperty() {
        return naturezaOperacao;
    }

    public void setNaturezaOperacao(int naturezaOperacao) {
        this.naturezaOperacao.set(naturezaOperacao);
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

    @Column(length = 3, nullable = false)
    public int getSerie() {
        return serie.get();
    }

    public IntegerProperty serieProperty() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie.set(serie);
    }

    @Column(length = 9, nullable = false)
    public int getNumero() {
        return numero.get();
    }

    public IntegerProperty numeroProperty() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    @Column(nullable = false)
    public LocalDateTime getDtHoraEmissao() {
        return dtHoraEmissao.get();
    }

    public ObjectProperty<LocalDateTime> dtHoraEmissaoProperty() {
        return dtHoraEmissao;
    }

    public void setDtHoraEmissao(LocalDateTime dtHoraEmissao) {
        this.dtHoraEmissao.set(dtHoraEmissao);
    }

    @Column(nullable = false)
    public LocalDateTime getDtHoraSaida() {
        return dtHoraSaida.get();
    }

    public ObjectProperty<LocalDateTime> dtHoraSaidaProperty() {
        return dtHoraSaida;
    }

    public void setDtHoraSaida(LocalDateTime dtHoraSaida) {
        this.dtHoraSaida.set(dtHoraSaida);
    }

    @Column(length = 1, nullable = false)
    public int getDestinoOperacao() {
        return destinoOperacao.get();
    }

    public IntegerProperty destinoOperacaoProperty() {
        return destinoOperacao;
    }

    public void setDestinoOperacao(int destinoOperacao) {
        this.destinoOperacao.set(destinoOperacao);
    }

    @Column(length = 1, nullable = false)
    public int getConsumidorFinal() {
        return consumidorFinal.get();
    }

    public IntegerProperty consumidorFinalProperty() {
        return consumidorFinal;
    }

    public void setConsumidorFinal(int consumidorFinal) {
        this.consumidorFinal.set(consumidorFinal);
    }

    @Column(length = 2, nullable = false)
    public int getIndicadorPresenca() {
        return indicadorPresenca.get();
    }

    public IntegerProperty indicadorPresencaProperty() {
        return indicadorPresenca;
    }

    public void setIndicadorPresenca(int indicadorPresenca) {
        this.indicadorPresenca.set(indicadorPresenca);
    }

    @Column(length = 2, nullable = false)
    public int getModFrete() {
        return modFrete.get();
    }

    public IntegerProperty modFreteProperty() {
        return modFrete;
    }

    public void setModFrete(int modFrete) {
        this.modFrete.set(modFrete);
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Empresa getTransportador() {
        return transportadorProperty().get();
    }

    public ObjectProperty<Empresa> transportadorProperty() {
        if (transportador == null)
            transportador = new SimpleObjectProperty<>(new EmpresaDAO().getById(Empresa.class, 0L));
        return transportador;
    }

    public void setTransportador(Empresa transportador) {
        transportadorProperty().set(transportador);
    }

    @Column(length = 10, nullable = false)
    public String getCobrancaNumero() {
        return cobrancaNumero.get();
    }

    public StringProperty cobrancaNumeroProperty() {
        return cobrancaNumero;
    }

    public void setCobrancaNumero(String cobrancaNumero) {
        this.cobrancaNumero.set(cobrancaNumero);
    }

    @Column(length = 1, nullable = false)
    public int getPagamentoIndicador() {
        return pagamentoIndicador.get();
    }

    public IntegerProperty pagamentoIndicadorProperty() {
        return pagamentoIndicador;
    }

    public void setPagamentoIndicador(int pagamentoIndicador) {
        this.pagamentoIndicador.set(pagamentoIndicador);
    }

    @Column(length = 2, nullable = false)
    public int getPagamentoMeio() {
        return pagamentoMeio.get();
    }

    public IntegerProperty pagamentoMeioProperty() {
        return pagamentoMeio;
    }

    public void setPagamentoMeio(int pagamentoMeio) {
        this.pagamentoMeio.set(pagamentoMeio);
    }

    @Column(length = 5000, nullable = false)
    public String getInformacaoAdicional() {
        return informacaoAdicional.get();
    }

    public StringProperty informacaoAdicionalProperty() {
        return informacaoAdicional;
    }

    public void setInformacaoAdicional(String informacaoAdicional) {
        this.informacaoAdicional.set(informacaoAdicional);
    }

    @Column(length = 28, unique = true)
    public String getDigVal() {
        return digVal.get();
    }

    public StringProperty digValProperty() {
        return digVal;
    }

    public void setDigVal(String digVal) {
        this.digVal.set(digVal);
    }

    @JsonIgnore
    @SuppressWarnings("JpaAttributeTypeInspection")
    public Blob getXmlAssinatura() {
        return xmlAssinatura.get();
    }

    public ObjectProperty<Blob> xmlAssinaturaProperty() {
        return xmlAssinatura;
    }

    public void setXmlAssinatura(Blob xmlAssinatura) {
        this.xmlAssinatura.set(xmlAssinatura);
    }

    @JsonIgnore
    @SuppressWarnings("JpaAttributeTypeInspection")
    public Blob getXmlConsRecibo() {
        return xmlConsRecibo.get();
    }

    public ObjectProperty<Blob> xmlConsReciboProperty() {
        return xmlConsRecibo;
    }

    public void setXmlConsRecibo(Blob xmlConsRecibo) {
        this.xmlConsRecibo.set(xmlConsRecibo);
    }

    @JsonIgnore
    @SuppressWarnings("JpaAttributeTypeInspection")
    public Blob getXmlProtNfe() {
        return xmlProtNfe.get();
    }

    public ObjectProperty<Blob> xmlProtNfeProperty() {
        return xmlProtNfe;
    }

    public void setXmlProtNfe(Blob xmlProtNfe) {
        this.xmlProtNfe.set(xmlProtNfe);
    }

    @Column(length = 1, nullable = false)
    public int getImpressaoTpImp() {
        return impressaoTpImp.get();
    }

    public IntegerProperty impressaoTpImpProperty() {
        return impressaoTpImp;
    }

    public void setImpressaoTpImp(int impressaoTpImp) {
        this.impressaoTpImp.set(impressaoTpImp);
    }

    @Column(length = 1, nullable = false)
    public int getImpressaoTpEmis() {
        return impressaoTpEmis.get();
    }

    public IntegerProperty impressaoTpEmisProperty() {
        return impressaoTpEmis;
    }

    public void setImpressaoTpEmis(int impressaoTpEmis) {
        this.impressaoTpEmis.set(impressaoTpEmis);
    }

    @Column(length = 1, nullable = false)
    public int getImpressaoFinNFe() {
        return impressaoFinNFe.get();
    }

    public IntegerProperty impressaoFinNFeProperty() {
        return impressaoFinNFe;
    }

    public void setImpressaoFinNFe(int impressaoFinNFe) {
        this.impressaoFinNFe.set(impressaoFinNFe);
    }

    @Column(length = 1, nullable = false)
    public boolean isImpressaoLtProduto() {
        return impressaoLtProduto.get();
    }

    public BooleanProperty impressaoLtProdutoProperty() {
        return impressaoLtProduto;
    }

    public void setImpressaoLtProduto(boolean impressaoLtProduto) {
        this.impressaoLtProduto.set(impressaoLtProduto);
    }

    @Override
    public String toString() {
        return "SaidaProdutoNfe{" +
                "id=" + id +
                ", saidaProduto=" + saidaProduto +
                ", chave=" + chave +
                ", statusSefaz=" + statusSefaz +
                ", naturezaOperacao=" + naturezaOperacao +
                ", modelo=" + modelo +
                ", serie=" + serie +
                ", numero=" + numero +
                ", dtHoraEmissao=" + dtHoraEmissao +
                ", dtHoraSaida=" + dtHoraSaida +
                ", destinoOperacao=" + destinoOperacao +
                ", impressaoTpImp=" + impressaoTpImp +
                ", impressaoTpEmis=" + impressaoTpEmis +
                ", impressaoFinNFe=" + impressaoFinNFe +
                ", impressaoLtProduto=" + impressaoLtProduto +
                ", consumidorFinal=" + consumidorFinal +
                ", indicadorPresenca=" + indicadorPresenca +
                ", modFrete=" + modFrete +
                ", transportador=" + transportador +
                ", cobrancaNumero=" + cobrancaNumero +
                ", pagamentoIndicador=" + pagamentoIndicador +
                ", pagamentoMeio=" + pagamentoMeio +
                ", informacaoAdicional=" + informacaoAdicional +
                ", xmlAssinatura=" + xmlAssinatura +
                ", xmlProtNfe=" + xmlProtNfe +
                '}';
    }
}
