package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.TipoContaBanco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-07-18
 * Time: 10:41
 */

@Entity(name = "InformacaoBancaria")
@Table(name = "informacao_bancaria")
public class InformacaoBancaria implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private Bancos banco = new Bancos();
    private StringProperty agencia = new SimpleStringProperty("");
    private StringProperty agenciaDV = new SimpleStringProperty("");
    private IntegerProperty tipoConta = new SimpleIntegerProperty(0);
    private StringProperty conta = new SimpleStringProperty("");
    private StringProperty contaDV = new SimpleStringProperty("");
    private StringProperty titularNome = new SimpleStringProperty("");
    private StringProperty titularCnpjCpf = new SimpleStringProperty("");

    public InformacaoBancaria() {
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
    @ManyToOne
    @JoinColumn(name = "bancos_id", foreignKey = @ForeignKey(name = "fk_informacao_bancaria_bancos_id"))
    public Bancos getBanco() {
        return banco;
    }

    public void setBanco(Bancos banco) {
        this.banco = banco;
    }

    @Column(length = 4, nullable = false)
    public String getAgencia() {
        return agencia.get();
    }

    public StringProperty agenciaProperty() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia.set(agencia);
    }

    @Column(length = 2)
    public String getAgenciaDV() {
        return agenciaDV.get();
    }

    public StringProperty agenciaDVProperty() {
        return agenciaDV;
    }

    public void setAgenciaDV(String agenciaDV) {
        this.agenciaDV.set(agenciaDV);
    }

    @Column(length = 1, nullable = false)
    public TipoContaBanco getTipoConta() {
        return TipoContaBanco.toEnum(tipoConta.get());
    }

    public IntegerProperty tipoContaProperty() {
        return tipoConta;
    }

    public void setTipoConta(TipoContaBanco tipoConta) {
        this.tipoConta.set(tipoConta.getCod());
    }

    @Column(length = 13, nullable = false)
    public String getConta() {
        return conta.get();
    }

    public StringProperty contaProperty() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta.set(conta);
    }

    @Column(length = 2)
    public String getContaDV() {
        return contaDV.get();
    }

    public StringProperty contaDVProperty() {
        return contaDV;
    }

    public void setContaDV(String contaDV) {
        this.contaDV.set(contaDV);
    }

    @Column(length = 80, nullable = false)
    public String getTitularNome() {
        return titularNome.get();
    }

    public StringProperty titularNomeProperty() {
        return titularNome;
    }

    public void setTitularNome(String titularNome) {
        this.titularNome.set(titularNome);
    }

    @Column(length = 14, nullable = false)
    public String getTitularCnpjCpf() {
        return titularCnpjCpf.get();
    }

    public StringProperty titularCnpjCpfProperty() {
        return titularCnpjCpf;
    }

    public void setTitularCnpjCpf(String titularCnpjCpf) {
        this.titularCnpjCpf.set(titularCnpjCpf);
    }

    @Override
    public String toString() {
        return "InformacaoBancaria{" +
                "id=" + id +
                ", banco=" + banco +
                ", agencia=" + agencia +
                ", agenciaDV=" + agenciaDV +
                ", tipoConta=" + tipoConta +
                ", conta=" + conta +
                ", contaDV=" + contaDV +
                ", titularNome=" + titularNome +
                ", titularCnpjCpf=" + titularCnpjCpf +
                '}';
    }
}
