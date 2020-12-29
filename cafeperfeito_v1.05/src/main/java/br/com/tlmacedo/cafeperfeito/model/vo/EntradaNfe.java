package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.NfeCteModelo;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-02-25
 * Time: 20:51
 */

@Entity(name = "EntradaNfe")
@Table(name = "entrada_nfe")
public class EntradaNfe implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty chave = new SimpleStringProperty("");
    private StringProperty numero = new SimpleStringProperty("");
    private StringProperty serie = new SimpleStringProperty("");
    private IntegerProperty modelo = new SimpleIntegerProperty(0);
    private Empresa emissor = new Empresa();
    private ObjectProperty<LocalDate> dataEmissao = new SimpleObjectProperty<>(LocalDate.now());
    private ObjectProperty<LocalDate> dataEntrada = new SimpleObjectProperty<>(LocalDate.now());

    private EntradaFiscal entradaFiscal = new EntradaFiscal();

    public EntradaNfe() {
    }

    public EntradaNfe(String chave, String numero, String serie, NfeCteModelo modelo, Empresa emissor, LocalDate dataEmissao, LocalDate dataEntrada, EntradaFiscal entradaFiscal) {
        this.chave = new SimpleStringProperty(chave);
        this.numero = new SimpleStringProperty(numero);
        this.serie = new SimpleStringProperty(serie);
        this.modelo = new SimpleIntegerProperty(modelo.getCod());
        this.emissor = emissor;
        this.dataEmissao = new SimpleObjectProperty<>(dataEmissao);
        this.dataEntrada = new SimpleObjectProperty<>(dataEntrada);
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

    @Column(length = 2, nullable = false)
    public NfeCteModelo getModelo() {
        return NfeCteModelo.toEnum(modelo.get());
    }

    public IntegerProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(NfeCteModelo modelo) {
        this.modelo.set(modelo.getCod());
    }

    @ManyToOne
    @JoinColumn(name = "emissor_id", foreignKey = @ForeignKey(name = "fk_entrada_nfe_empresa"), nullable = false)
    public Empresa getEmissor() {
        return emissor;
    }

    public void setEmissor(Empresa emissor) {
        this.emissor = emissor;
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

    @Column(nullable = false)
    public LocalDate getDataEntrada() {
        return dataEntrada.get();
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada.set(dataEntrada);
    }

    public ObjectProperty<LocalDate> dataEntradaProperty() {
        return dataEntrada;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "entradaFiscal_id", foreignKey = @ForeignKey(name = "fk_entrada_nfe_entrada_fiscal"))
    public EntradaFiscal getEntradaFiscal() {
        return entradaFiscal;
    }

    public void setEntradaFiscal(EntradaFiscal entradaFiscal) {
        this.entradaFiscal = entradaFiscal;
    }

    @Override
    public String toString() {
        return "EntradaNfe{" +
                "id=" + id +
                ", chave=" + chave +
                ", numero=" + numero +
                ", serie=" + serie +
                ", modelo=" + modelo +
                ", emissor=" + emissor +
                ", dataEmissao=" + dataEmissao +
                ", dataEntrada=" + dataEntrada +
                ", entradaFiscal=" + entradaFiscal +
                '}';
    }
}
