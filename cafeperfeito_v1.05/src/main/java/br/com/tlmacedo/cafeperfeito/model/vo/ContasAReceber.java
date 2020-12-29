package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.dao.ContasAReceberDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.PagamentoSituacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-04-15
 * Time: 14:27
 */

@Entity(name = "ContasAReceber")
@Table(name = "contas_a_receber")
public class ContasAReceber extends RecursiveTreeObject<ContasAReceber> implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private SaidaProduto saidaProduto = new SaidaProduto();
    private ObjectProperty<LocalDate> dtVencimento = new SimpleObjectProperty<>(LocalDate.now());
    private IntegerProperty pagamentoSituacao = new SimpleIntegerProperty();
    private ObjectProperty<BigDecimal> valor = new SimpleObjectProperty<>();
    private Usuario usuarioCadastro = new Usuario();
    private ObjectProperty<LocalDateTime> dtCadastro = new SimpleObjectProperty<>(LocalDateTime.now());

    private List<Recebimento> recebimentoList = new ArrayList<>();

    public ContasAReceber() {
    }

    public ContasAReceber(SaidaProduto saidaProduto, LocalDate dtVencimento, PagamentoSituacao pagamentoSituacao, BigDecimal valor, Usuario usuarioCadastro, LocalDateTime dtCadastro) {
        this.saidaProduto = saidaProduto;
        this.dtVencimento = new SimpleObjectProperty<>(dtVencimento);
        this.pagamentoSituacao = new SimpleIntegerProperty(pagamentoSituacao.getCod());
        this.valor = new SimpleObjectProperty<>(valor);
        this.usuarioCadastro = usuarioCadastro;
        this.dtCadastro = new SimpleObjectProperty<>(dtCadastro);
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
    @OneToOne
    public SaidaProduto getSaidaProduto() {
        return saidaProduto;
    }

    public void setSaidaProduto(SaidaProduto saidaProduto) {
        this.saidaProduto = saidaProduto;
    }

    @Column(nullable = false)
    public LocalDate getDtVencimento() {
        return dtVencimento.get();
    }

    public void setDtVencimento(LocalDate dtVencimento) {
        this.dtVencimento.set(dtVencimento);
    }

    public ObjectProperty<LocalDate> dtVencimentoProperty() {
        return dtVencimento;
    }

    @Column(length = 2, nullable = false)
    public PagamentoSituacao getPagamentoSituacao() {
        return PagamentoSituacao.toEnum(pagamentoSituacao.get());
    }

    public void setPagamentoSituacao(PagamentoSituacao pagamentoSituacao) {
        this.pagamentoSituacao.set(pagamentoSituacao.getCod());
    }

    public IntegerProperty pagamentoSituacaoProperty() {
        return pagamentoSituacao;
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getValor() {
        return valor.get();
    }

    public void setValor(BigDecimal valor) {
        this.valor.set(valor);
    }

    public ObjectProperty<BigDecimal> valorProperty() {
        return valor;
    }

    @ManyToOne
    @JoinColumn(name = "usuarioCadastro_id", foreignKey = @ForeignKey(name = "fk_contas_a_receber_usuario_cadastro"))
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime getDtCadastro() {
        return dtCadastro.get();
    }

    public void setDtCadastro(LocalDateTime dtCadastro) {
        this.dtCadastro.set(dtCadastro);
    }

    public ObjectProperty<LocalDateTime> dtCadastroProperty() {
        return dtCadastro;
    }

    //    @Transient
//    public BigDecimal getValor() {
//        return valor.get();
//    }
//
//    public void setValor(BigDecimal valor) {
//        this.valor.set(valor);
//    }
//
//    public ObjectProperty<BigDecimal> valorProperty() {
//        return valor;
//    }

    @JsonIgnore
//    @JsonManagedReference
    @OneToMany(mappedBy = "contasAReceber", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Recebimento> getRecebimentoList() {
        return recebimentoList;
    }

    public void setRecebimentoList(List<Recebimento> recebimentoList) {
        this.recebimentoList = recebimentoList;
    }

    @Override
    public ContasAReceber clone() throws CloneNotSupportedException {
        ContasAReceber contasAReceber = new ContasAReceber();
        ContasAReceberDAO contasAReceberDAO = new ContasAReceberDAO();
        contasAReceber = contasAReceberDAO.getById(ContasAReceber.class, getId());
        return contasAReceber;
    }

    public void addRecebimento(Recebimento recebimento) {
        recebimentoList.add(recebimento);
        recebimento.setContasAReceber(this);
    }

    public void removeRecebimento(Recebimento recebimento) {
        recebimentoList.remove(recebimento);
        recebimento.setContasAReceber(null);
    }


    @Override
    public String toString() {
        return "ContasAReceber{" +
                "id=" + id +
                ", saidaProduto=" + saidaProduto +
                ", dtVencimento=" + dtVencimento +
                ", pagamentoSituacao=" + pagamentoSituacao +
                ", valor=" + valor +
                ", usuarioCadastro=" + usuarioCadastro +
                ", dtCadastro=" + dtCadastro +
                ", recebimentoList=" + recebimentoList +
                '}';
    }
}
