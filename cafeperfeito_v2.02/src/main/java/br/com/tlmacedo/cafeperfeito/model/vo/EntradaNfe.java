package br.com.tlmacedo.cafeperfeito.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "EntradaCte")
@Table(name = "entrada_nfe")
public class EntradaNfe implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private ObjectProperty<EntradaProduto> entradaProduto = new SimpleObjectProperty<>();
    private StringProperty chave = new SimpleStringProperty();
    private StringProperty numero = new SimpleStringProperty();
    private StringProperty serie = new SimpleStringProperty();
    private IntegerProperty modelo = new SimpleIntegerProperty();
    private ObjectProperty<Empresa> fornecedor = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dtEmissao = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dtEntrada = new SimpleObjectProperty<>();
    private ObjectProperty<EntradaFiscal> entradaFiscal = new SimpleObjectProperty<>();

    public EntradaNfe() {
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

    @Column(length = 1, nullable = false)
    public int getModelo() {
        return modelo.get();
    }

    public IntegerProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo.set(modelo);
    }

    @ManyToOne
    public Empresa getFornecedor() {
        return fornecedor.get();
    }

    public ObjectProperty<Empresa> fornecedorProperty() {
        return fornecedor;
    }

    public void setFornecedor(Empresa fornecedor) {
        this.fornecedor.set(fornecedor);
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

    public LocalDate getDtEntrada() {
        return dtEntrada.get();
    }

    public ObjectProperty<LocalDate> dtEntradaProperty() {
        return dtEntrada;
    }

    public void setDtEntrada(LocalDate dtEntrada) {
        this.dtEntrada.set(dtEntrada);
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
        return "EntradaNfe{" +
                "id=" + id +
                ", chave=" + chave +
                ", numero=" + numero +
                ", serie=" + serie +
                ", modelo=" + modelo +
                ", fornecedor=" + fornecedor +
                ", dtEmissao=" + dtEmissao +
                ", dtEntrada=" + dtEntrada +
                ", entradaFiscal=" + entradaFiscal +
                '}';
    }
}
