package br.com.tlmacedo.cafeperfeito.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ContasAReceber")
@Table(name = "contas_a_receber")
public class ContasAReceber implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private ObjectProperty<SaidaProduto> saidaProduto = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dtVencimento = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> valor = new SimpleObjectProperty<>();
    private ObjectProperty<Usuario> usuarioCadastro = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDateTime> dtCadastro = new SimpleObjectProperty<>();

    private List<Recebimento> recebimentoList = new ArrayList<>();

    private ObjectProperty<BigDecimal> vlrOriginalSaida = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrCredDeb = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrLiquido = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrPago = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private ObjectProperty<BigDecimal> vlrSaldo = new SimpleObjectProperty<>(BigDecimal.ZERO);

    public ContasAReceber() {
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
    @OneToOne//(fetch = FetchType.LAZY)
    public SaidaProduto getSaidaProduto() {
        return saidaProduto.get();
    }

    public ObjectProperty<SaidaProduto> saidaProdutoProperty() {
        return saidaProduto;
    }

    public void setSaidaProduto(SaidaProduto saidaProduto) {
        this.saidaProduto.set(saidaProduto);
    }

    @Column(nullable = false)
    public LocalDate getDtVencimento() {
        return dtVencimento.get();
    }

    public ObjectProperty<LocalDate> dtVencimentoProperty() {
        return dtVencimento;
    }

    public void setDtVencimento(LocalDate dtVencimento) {
        this.dtVencimento.set(dtVencimento);
    }

    @Column(length = 19, scale = 4, nullable = false)
    public BigDecimal getValor() {
        return valor.get();
    }

    public ObjectProperty<BigDecimal> valorProperty() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor.set(valor);
    }

    @JsonIgnore
    @ManyToOne
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro.get();
    }

    public ObjectProperty<Usuario> usuarioCadastroProperty() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro.set(usuarioCadastro);
    }

    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime getDtCadastro() {
        return dtCadastro.get();
    }

    public ObjectProperty<LocalDateTime> dtCadastroProperty() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDateTime dtCadastro) {
        this.dtCadastro.set(dtCadastro);
    }

    @OneToMany(mappedBy = "contasAReceber", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Recebimento> getRecebimentoList() {
        return recebimentoList;
    }

    public void setRecebimentoList(List<Recebimento> recebimentoList) {
        this.recebimentoList = recebimentoList;
    }

    @JsonIgnore
    @Transient
    public BigDecimal getVlrOriginalSaida() {
        return vlrOriginalSaida.get();
    }

    public ObjectProperty<BigDecimal> vlrOriginalSaidaProperty() {
        return vlrOriginalSaida;
    }

    public void setVlrOriginalSaida(BigDecimal vlrOriginalSaida) {
        this.vlrOriginalSaida.set(vlrOriginalSaida);
    }

    @JsonIgnore
    @Transient
    public BigDecimal getVlrCredDeb() {
        return vlrCredDeb.get();
    }

    public ObjectProperty<BigDecimal> vlrCredDebProperty() {
        return vlrCredDeb;
    }

    public void setVlrCredDeb(BigDecimal vlrCredDeb) {
        this.vlrCredDeb.set(vlrCredDeb);
    }

    @JsonIgnore
    @Transient
    public BigDecimal getVlrLiquido() {
        return vlrLiquido.get();
    }

    public ObjectProperty<BigDecimal> vlrLiquidoProperty() {
        return vlrLiquido;
    }

    public void setVlrLiquido(BigDecimal vlrLiquido) {
        this.vlrLiquido.set(vlrLiquido);
    }

    @JsonIgnore
    @Transient
    public BigDecimal getVlrPago() {
        return vlrPago.get();
    }

    public ObjectProperty<BigDecimal> vlrPagoProperty() {
        return vlrPago;
    }

    public void setVlrPago(BigDecimal vlrPago) {
        this.vlrPago.set(vlrPago);
    }

    @JsonIgnore
    @Transient
    public BigDecimal getVlrSaldo() {
        return vlrSaldo.get();
    }

    public ObjectProperty<BigDecimal> vlrSaldoProperty() {
        return vlrSaldo;
    }

    public void setVlrSaldo(BigDecimal vlrSaldo) {
        this.vlrSaldo.set(vlrSaldo);
    }


    @Override
    public String toString() {
        return "ContasAReceber{" +
                "id=" + id +
//                ", saidaProduto=" + saidaProduto +
                ", dtVencimento=" + dtVencimento +
                ", valor=" + valor +
//                ", usuarioCadastro=" + usuarioCadastro +
                ", dtCadastro=" + dtCadastro +
//                ", recebimentoList=" + recebimentoList +
                ", vlrOriginalSaida=" + vlrOriginalSaida +
                ", vlrCredDeb=" + vlrCredDeb +
                ", vlrLiquido=" + vlrLiquido +
                ", vlrPago=" + vlrPago +
                ", vlrSaldo=" + vlrSaldo +
                '}';
    }
}
