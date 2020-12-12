package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "EntradaFiscal")
@Table(name = "entrada_fiscal")
public class EntradaFiscal implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty controle = new SimpleStringProperty();
    private StringProperty origem = new SimpleStringProperty();
    private ObjectProperty<FiscalTributosSefazAm> tributosSefazAm = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> vlrDocumento = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrTributo = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrMulta = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrJuros = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrTaxa = new SimpleObjectProperty<>(BigDecimal.ZERO);

    public EntradaFiscal() {
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

    @Column(length = 13, nullable = false)
    public String getControle() {
        return controle.get();
    }

    public StringProperty controleProperty() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle.set(controle);
    }

    @Column(length = 12, nullable = false)
    public String getOrigem() {
        return origem.get();
    }

    public StringProperty origemProperty() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem.set(origem);
    }

    //    @JsonIgnore
    @ManyToOne//(fetch = FetchType.LAZY)
    public FiscalTributosSefazAm getTributosSefazAm() {
        return tributosSefazAm.get();
    }

    public ObjectProperty<FiscalTributosSefazAm> tributosSefazAmProperty() {
        return tributosSefazAm;
    }

    public void setTributosSefazAm(FiscalTributosSefazAm tributosSefazAm) {
        this.tributosSefazAm.set(tributosSefazAm);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrDocumento() {
        return vlrDocumento.get();
    }

    public ObjectProperty<BigDecimal> vlrDocumentoProperty() {
        return vlrDocumento;
    }

    public void setVlrDocumento(BigDecimal vlrDocumento) {
        this.vlrDocumento.set(vlrDocumento);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrTributo() {
        return vlrTributo.get();
    }

    public ObjectProperty<BigDecimal> vlrTributoProperty() {
        return vlrTributo;
    }

    public void setVlrTributo(BigDecimal vlrTributo) {
        this.vlrTributo.set(vlrTributo);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrMulta() {
        return vlrMulta.get();
    }

    public ObjectProperty<BigDecimal> vlrMultaProperty() {
        return vlrMulta;
    }

    public void setVlrMulta(BigDecimal vlrMulta) {
        this.vlrMulta.set(vlrMulta);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrJuros() {
        return vlrJuros.get();
    }

    public ObjectProperty<BigDecimal> vlrJurosProperty() {
        return vlrJuros;
    }

    public void setVlrJuros(BigDecimal vlrJuros) {
        this.vlrJuros.set(vlrJuros);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getVlrTaxa() {
        return vlrTaxa.get();
    }

    public ObjectProperty<BigDecimal> vlrTaxaProperty() {
        return vlrTaxa;
    }

    public void setVlrTaxa(BigDecimal vlrTaxa) {
        this.vlrTaxa.set(vlrTaxa);
    }

    @Override
    public String toString() {
        return "EntradaFiscal{" +
                "id=" + id +
                ", controle=" + controle +
                ", origem=" + origem +
                ", tributosSefazAm=" + tributosSefazAm +
                ", vlrDocumento=" + vlrDocumento +
                ", vlrTributo=" + vlrTributo +
                ", vlrMulta=" + vlrMulta +
                ", vlrJuros=" + vlrJuros +
                ", vlrTaxa=" + vlrTaxa +
                '}';
    }
}
