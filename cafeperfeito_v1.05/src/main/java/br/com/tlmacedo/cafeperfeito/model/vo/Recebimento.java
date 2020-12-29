package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoSituacao;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoTipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-16
 * Time: 19:26
 */

@Entity(name = "Recebimento")
@Table(name = "recebimento")
@Inheritance(strategy = InheritanceType.JOINED)
public class Recebimento implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private Usuario usuarioCadastro = new Usuario();
    private ObjectProperty<LocalDateTime> dtCadastro = new SimpleObjectProperty<>();
    private IntegerProperty pagamentoTipo = new SimpleIntegerProperty();
    private StringProperty documento = new SimpleStringProperty();
    private ObjectProperty<BigDecimal> valor = new SimpleObjectProperty<>();
    private IntegerProperty pagamentoSituacao = new SimpleIntegerProperty();
    private IntegerProperty parcela = new SimpleIntegerProperty();
    private IntegerProperty parcelas = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> dtVencimento = new SimpleObjectProperty<>();
    private Usuario usuarioPagamento = new Usuario();
    private ObjectProperty<LocalDate> dtPagamento = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> valorRecebido = new SimpleObjectProperty<>();
    private Usuario usuarioAtualizacao = new Usuario();
    private ObjectProperty<LocalDateTime> dtAtualizacao = new SimpleObjectProperty<>();
    private ContasAReceber contasAReceber;
    private Empresa emissorRecibo;

    public Recebimento() {
    }

    public Recebimento(Usuario usuarioCadastro, LocalDateTime dtCadastro, PagamentoTipo pagamentoTipo, String documento, BigDecimal valor, PagamentoSituacao pagamentoSituacao, Integer parcela, Integer parcelas, LocalDate dtVencimento, Usuario usuarioPagamento, LocalDate dtPagamento, BigDecimal valorRecebido, Usuario usuarioAtualizacao, LocalDateTime dtAtualizacao) {
        this.usuarioCadastro = usuarioCadastro;
        this.dtCadastro = new SimpleObjectProperty<>(dtCadastro);
        this.pagamentoTipo = new SimpleIntegerProperty(pagamentoTipo.getCod());
        this.documento = new SimpleStringProperty(documento);
        this.valor = new SimpleObjectProperty<>(valor);
        this.pagamentoSituacao = new SimpleIntegerProperty(pagamentoSituacao.getCod());
        this.parcela = new SimpleIntegerProperty(parcela);
        this.parcelas = new SimpleIntegerProperty(parcelas);
        this.dtVencimento = new SimpleObjectProperty<>(dtVencimento);
        this.usuarioPagamento = usuarioPagamento;
        this.dtPagamento = new SimpleObjectProperty<>(dtPagamento);
        this.valorRecebido = new SimpleObjectProperty<>(valorRecebido);
        this.usuarioAtualizacao = usuarioAtualizacao;
        this.dtAtualizacao = new SimpleObjectProperty<>(dtAtualizacao);
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

    @ManyToOne
    @JoinColumn(name = "usuarioCadastro_id", foreignKey = @ForeignKey(name = "fk_Recebimento_usuario_cadastro"))
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    //@CreationTimestamp
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

    @Column(length = 2, nullable = false)
    public PagamentoTipo getPagamentoTipo() {
        return PagamentoTipo.toEnum(pagamentoTipo.get());
    }

    public void setPagamentoTipo(PagamentoTipo pagamentoTipo) {
        this.pagamentoTipo.set(pagamentoTipo.getCod());
    }

    public IntegerProperty pagamentoTipoProperty() {
        return pagamentoTipo;
    }

    @Column(length = 15, nullable = false)
    public String getDocumento() {
        return documento.get();
    }

    public StringProperty documentoProperty() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento.set(documento);
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getValor() {
        return valor.get();
    }

    public ObjectProperty<BigDecimal> valorProperty() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor.set(valor);
    }

    @Column(length = 2, nullable = false)
    public PagamentoSituacao getPagamentoSituacao() {
        return PagamentoSituacao.toEnum(pagamentoSituacao.get());
    }

    public IntegerProperty pagamentoSituacaoProperty() {
        return pagamentoSituacao;
    }

    public void setPagamentoSituacao(PagamentoSituacao pagamentoSituacao) {
        this.pagamentoSituacao.set(pagamentoSituacao.getCod());
    }

    @Column(length = 2, nullable = false)
    public int getParcela() {
        return parcela.get();
    }

    public IntegerProperty parcelaProperty() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela.set(parcela);
    }

    @Column(length = 2, nullable = false)
    public int getParcelas() {
        return parcelas.get();
    }

    public IntegerProperty parcelasProperty() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas.set(parcelas);
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

    @ManyToOne()
    @JoinColumn(name = "usuarioPagamento_id", foreignKey = @ForeignKey(name = "fk_recebimento_usuario_pagamento"))
    public Usuario getUsuarioPagamento() {
        return usuarioPagamento;
    }

    public void setUsuarioPagamento(Usuario usuarioPagamento) {
        this.usuarioPagamento = usuarioPagamento;
    }

    public LocalDate getDtPagamento() {
        return dtPagamento.get();
    }

    public ObjectProperty<LocalDate> dtPagamentoProperty() {
        return dtPagamento;
    }

    public void setDtPagamento(LocalDate dtPagamento) {
        this.dtPagamento.set(dtPagamento);
    }

    @Column(length = 19, scale = 2)
    public BigDecimal getValorRecebido() {
        return valorRecebido.get();
    }

    public void setValorRecebido(BigDecimal valorRecebido) {
        this.valorRecebido.set(valorRecebido);
    }

    public ObjectProperty<BigDecimal> valorRecebidoProperty() {
        return valorRecebido;
    }

    @ManyToOne
    @JoinColumn(name = "usuarioAtualizacao_id", foreignKey = @ForeignKey(name = "fk_recebimento_usuario_atualizacao"))
    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

    @UpdateTimestamp
    @Column(nullable = false)
    public LocalDateTime getDtAtualizacao() {
        return dtAtualizacao.get();
    }

    public ObjectProperty<LocalDateTime> dtAtualizacaoProperty() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(LocalDateTime dtAtualizacao) {
        this.dtAtualizacao.set(dtAtualizacao);
    }

    @JsonIgnore
//    @JsonBackReference
    @ManyToOne
    public ContasAReceber getContasAReceber() {
        return contasAReceber;
    }

    public void setContasAReceber(ContasAReceber contasAReceber) {
        this.contasAReceber = contasAReceber;
    }

    @Transient
    public Empresa getEmissorRecibo() {
        return emissorRecibo;
    }

    public void setEmissorRecibo(Empresa emissorRecibo) {
        this.emissorRecibo = emissorRecibo;
    }

    @Override
    public String toString() {
        return "Recebimento{" +
                "id=" + id +
                ", usuarioCadastro=" + usuarioCadastro +
                ", dtCadastro=" + dtCadastro +
                ", pagamentoTipo=" + pagamentoTipo +
                ", documento=" + documento +
                ", valor=" + valor +
                ", pagamentoSituacao=" + pagamentoSituacao +
                ", parcela=" + parcela +
                ", parcelas=" + parcelas +
                ", dtVencimento=" + dtVencimento +
                ", usuarioPagamento=" + usuarioPagamento +
                ", dtPagamento=" + dtPagamento +
                ", valorRecebido=" + valorRecebido +
                ", usuarioAtualizacao=" + usuarioAtualizacao +
                ", dtAtualizacao=" + dtAtualizacao +
//                ", contasAReceber=" + contasAReceber +
                '}';
    }
}
