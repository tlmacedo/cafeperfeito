package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.*;
import javafx.beans.property.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-05-28
 * Time: 10:53
 */

@Entity(name = "SaidaProdutoNfe")
@Table(name = "saida_produto_nfe")
public class SaidaProdutoNfe implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private SaidaProduto saidaProduto;
    private StringProperty chave = new SimpleStringProperty("");
    private IntegerProperty status = new SimpleIntegerProperty(0);

    private StringProperty naturezaOperacao = new SimpleStringProperty("");
    private IntegerProperty modelo = new SimpleIntegerProperty(0);
    private IntegerProperty serie = new SimpleIntegerProperty(0);
    private IntegerProperty numero = new SimpleIntegerProperty(0);
    private ObjectProperty<LocalDateTime> dataHoraEmissao = new SimpleObjectProperty<>(LocalDateTime.now());
    private ObjectProperty<LocalDateTime> dataHoraSaida = new SimpleObjectProperty<>(LocalDateTime.now());
    private IntegerProperty destOperacao = new SimpleIntegerProperty(0);
    private BooleanProperty consumidorFinal = new SimpleBooleanProperty(false);
    private IntegerProperty presencaComprador = new SimpleIntegerProperty(3);
    private IntegerProperty modalidadeFrete = new SimpleIntegerProperty(3);
    private Empresa transportador;
    private StringProperty cobrancaNumero = new SimpleStringProperty("");
    private StringProperty informacaoAdicional = new SimpleStringProperty("");
    private StringProperty xmlAssinatura = new SimpleStringProperty("");
    private StringProperty xmlProtNFe = new SimpleStringProperty("");

    public SaidaProdutoNfe() {
    }

    public SaidaProdutoNfe(SaidaProduto saidaProduto, String chave, NfeStatusSEFAZ status, String naturezaOperacao, NfeCteModelo modelo, Integer serie, Integer numero, LocalDateTime dataHoraEmissao, LocalDateTime dataHoraSaida, NfeDestinoOperacao destOperacao, Boolean consumidorFinal, NfePresencaComprador presencaComprador, NfeModalidadeFrete modalidadeFrete, Empresa transportador, String cobrancaNumero, String informacaoAdicional, String xmlAssinatura, String xmlProtNFe) {
        this.saidaProduto = saidaProduto;
        this.chave = new SimpleStringProperty(chave);
        this.status = new SimpleIntegerProperty(status.getCod());
        this.naturezaOperacao = new SimpleStringProperty(naturezaOperacao);
        this.modelo = new SimpleIntegerProperty(modelo.getCod());
        this.serie = new SimpleIntegerProperty(serie);
        this.numero = new SimpleIntegerProperty(numero);
        this.dataHoraEmissao = new SimpleObjectProperty<>(dataHoraEmissao);
        this.dataHoraSaida = new SimpleObjectProperty<>(dataHoraSaida);
        this.destOperacao = new SimpleIntegerProperty(destOperacao.getCod());
        this.consumidorFinal = new SimpleBooleanProperty(consumidorFinal);
        this.presencaComprador = new SimpleIntegerProperty(presencaComprador.getCod());
        this.modalidadeFrete = new SimpleIntegerProperty(modalidadeFrete.getCod());
        this.transportador = transportador;
        this.cobrancaNumero = new SimpleStringProperty(cobrancaNumero);
        this.informacaoAdicional = new SimpleStringProperty(informacaoAdicional);
        this.xmlAssinatura = new SimpleStringProperty(xmlAssinatura);
        this.xmlProtNFe = new SimpleStringProperty(xmlProtNFe);
    }

    @OneToOne
    @JoinColumn(name = "saidaProduto_id", foreignKey = @ForeignKey(name = "fk_saida_produto_nfe_saida_produto"))
    public SaidaProduto getSaidaProduto() {
        return saidaProduto;
    }

    public void setSaidaProduto(SaidaProduto saidaProduto) {
        this.saidaProduto = saidaProduto;
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

    @Column(length = 47, nullable = false, unique = true)
    public String getChave() {
        return chave.get();
    }

    public void setChave(String chave) {
        this.chave.set(chave.replaceAll("\\D", ""));
    }

    public StringProperty chaveProperty() {
        return chave;
    }

    @Column(length = 3, nullable = false)
    public NfeStatusSEFAZ getStatus() {
        return NfeStatusSEFAZ.toEnum(status.get());
    }

    public void setStatus(NfeStatusSEFAZ status) {
        this.status.set(status.getCod());
    }

    public IntegerProperty statusProperty() {
        return status;
    }

    @Column(length = 60, nullable = false)
    public String getNaturezaOperacao() {
        return naturezaOperacao.get();
    }

    public void setNaturezaOperacao(String naturezaOperacao) {
        this.naturezaOperacao.set(naturezaOperacao);
    }

    public StringProperty naturezaOperacaoProperty() {
        return naturezaOperacao;
    }

    @Column(length = 2, nullable = false)
    public NfeCteModelo getModelo() {
        return NfeCteModelo.toEnum(modelo.get());
    }

    public void setModelo(NfeCteModelo modelo) {
        this.modelo.set(modelo.getCod());
    }

    public IntegerProperty modeloProperty() {
        return modelo;
    }

    @Column(length = 3, nullable = false)
    public int getSerie() {
        return serie.get();
    }

    public void setSerie(int serie) {
        this.serie.set(serie);
    }

    public IntegerProperty serieProperty() {
        return serie;
    }

    @Column(length = 9, nullable = false)
    public int getNumero() {
        return numero.get();
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public IntegerProperty numeroProperty() {
        return numero;
    }

    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime getDataHoraEmissao() {
        return dataHoraEmissao.get();
    }

    public void setDataHoraEmissao(LocalDateTime dataHoraEmissao) {
        this.dataHoraEmissao.set(dataHoraEmissao);
    }

    public ObjectProperty<LocalDateTime> dataHoraEmissaoProperty() {
        return dataHoraEmissao;
    }

    @Column(nullable = false)
    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida.get();
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida.set(dataHoraSaida);
    }

    public ObjectProperty<LocalDateTime> dataHoraSaidaProperty() {
        return dataHoraSaida;
    }

    @Column(length = 1, nullable = false)
    public NfeDestinoOperacao getDestOperacao() {
        return NfeDestinoOperacao.toEnum(destOperacao.get());
    }

    public void setDestOperacao(NfeDestinoOperacao destOperacao) {
        this.destOperacao.set(destOperacao.getCod());
    }

    public IntegerProperty destOperacaoProperty() {
        return destOperacao;
    }

    @Column(length = 1, nullable = false)
    public boolean isConsumidorFinal() {
        return consumidorFinal.get();
    }

    public void setConsumidorFinal(boolean consumidorFinal) {
        this.consumidorFinal.set(consumidorFinal);
    }

    public BooleanProperty consumidorFinalProperty() {
        return consumidorFinal;
    }

    @Column(length = 1, nullable = false)
    public NfePresencaComprador getPresencaComprador() {
        return NfePresencaComprador.toEnum(presencaComprador.get());
    }

    public void setPresencaComprador(NfePresencaComprador presencaComprador) {
        this.presencaComprador.set(presencaComprador.getCod());
    }

    public IntegerProperty presencaCompradorProperty() {
        return presencaComprador;
    }

    @Column(length = 1, nullable = false)
    public NfeModalidadeFrete getModalidadeFrete() {
        return NfeModalidadeFrete.toEnum(modalidadeFrete.get());
    }

    public IntegerProperty modalidadeFreteProperty() {
        return modalidadeFrete;
    }

    public void setModalidadeFrete(NfeModalidadeFrete modalidadeFrete) {
        this.modalidadeFrete.set(modalidadeFrete.getCod());
    }

    @ManyToOne
    @JoinColumn(name = "transportador_id", foreignKey = @ForeignKey(name = "fk_saida_produto_nfe_transportador"))
    public Empresa getTransportador() {
        return transportador;
    }

    public void setTransportador(Empresa transportador) {
        this.transportador = transportador;
    }

    @Column(length = 60, nullable = false)
    public String getCobrancaNumero() {
        return cobrancaNumero.get();
    }

    public StringProperty cobrancaNumeroProperty() {
        return cobrancaNumero;
    }

    public void setCobrancaNumero(String cobrancaNumero) {
        this.cobrancaNumero.set(cobrancaNumero);
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

    @Column(length = 5000, nullable = false)
    public String getXmlAssinatura() {
        return xmlAssinatura.get();
    }

    public StringProperty xmlAssinaturaProperty() {
        return xmlAssinatura;
    }

    public void setXmlAssinatura(String xmlAssinatura) {
        this.xmlAssinatura.set(xmlAssinatura);
    }

    @Column(length = 5000, nullable = false)
    public String getXmlProtNFe() {
        return xmlProtNFe.get();
    }

    public StringProperty xmlProtNFeProperty() {
        return xmlProtNFe;
    }

    public void setXmlProtNFe(String xmlProtNFe) {
        this.xmlProtNFe.set(xmlProtNFe);
    }


    @Override
    public String toString() {
        return "SaidaProdutoNfe{" +
                "id=" + id +
                ", saidaProduto=" + saidaProduto +
                ", chave=" + chave +
                ", status=" + status +
                ", naturezaOperacao=" + naturezaOperacao +
                ", modelo=" + modelo +
                ", serie=" + serie +
                ", numero=" + numero +
                ", dataHoraEmissao=" + dataHoraEmissao +
                ", dataHoraSaida=" + dataHoraSaida +
                ", destOperacao=" + destOperacao +
                ", consumidorFinal=" + consumidorFinal +
                ", presencaComprador=" + presencaComprador +
                ", modalidadeFrete=" + modalidadeFrete +
                ", transportador=" + transportador +
                ", cobrancaNumero=" + cobrancaNumero +
                ", informacaoAdicional=" + informacaoAdicional +
                ", xmlAssinatura=" + xmlAssinatura +
                ", xmlProtNFe=" + xmlProtNFe +
                '}';
    }
}
