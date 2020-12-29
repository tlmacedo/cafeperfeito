package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.dao.EntradaProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoEntrada;
import br.com.tlmacedo.cafeperfeito.service.serialize_deserialize.EntradaProdutoDeserializer;
import br.com.tlmacedo.cafeperfeito.service.serialize_deserialize.EntradaProdutoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.beans.property.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-02-26
 * Time: 11:27
 */

@Entity(name = "EntradaProduto")
@Table(name = "entrada_produto")
@JsonSerialize(using = EntradaProdutoSerializer.class)
@JsonDeserialize(using = EntradaProdutoDeserializer.class)
public class EntradaProduto implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private IntegerProperty situacao = new SimpleIntegerProperty(0);

    private Empresa loja = new Empresa();
    private EntradaNfe entradaNfe = new EntradaNfe();
    private EntradaCte entradaCte = new EntradaCte();

    private Usuario usuarioCadastro = new Usuario();
    private ObjectProperty<LocalDateTime> dataCadastro = new SimpleObjectProperty<>(LocalDateTime.now());

    private List<EntradaProdutoProduto> entradaProdutoProdutoList = new ArrayList<>();

    public EntradaProduto() {
    }

    public EntradaProduto(SituacaoEntrada situacao, Empresa loja, EntradaNfe entradaNfe, EntradaCte entradaCte, Usuario usuarioCadastro, LocalDateTime dataCadastro) {
        this.situacao = new SimpleIntegerProperty(situacao.getCod());
        this.loja = loja;
        this.entradaNfe = entradaNfe;
        this.entradaCte = entradaCte;
        this.usuarioCadastro = usuarioCadastro;
        this.dataCadastro = new SimpleObjectProperty<>(dataCadastro);
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

    @Column(length = 2, nullable = false)
    public SituacaoEntrada getSituacao() {
        return SituacaoEntrada.toEnum(situacao.get());
    }

    public void setSituacao(SituacaoEntrada situacao) {
        this.situacao.set(situacao.getCod());
    }

    public IntegerProperty situacaoProperty() {
        return situacao;
    }

    @ManyToOne
    @JoinColumn(name = "loja_id", foreignKey = @ForeignKey(name = "fk_entrada_produto_empresa"))
    public Empresa getLoja() {
        return loja;
    }

    public void setLoja(Empresa loja) {
        this.loja = loja;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "entradaNfe_id", foreignKey = @ForeignKey(name = "fk_entrada_produto_entrada_nfe"))
    public EntradaNfe getEntradaNfe() {
        return entradaNfe;
    }

    public void setEntradaNfe(EntradaNfe entradaNfe) {
        this.entradaNfe = entradaNfe;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "entradaCte_id", foreignKey = @ForeignKey(name = "fk_entrada_produto_entrada_cte"))
    public EntradaCte getEntradaCte() {
        return entradaCte;
    }

    public void setEntradaCte(EntradaCte entradaCte) {
        this.entradaCte = entradaCte;
    }

    @ManyToOne
    @JoinColumn(name = "usuarioCadastro_id", foreignKey = @ForeignKey(name = "fk_entrada_produto_usuario_cadastro"), nullable = false)
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime getDataCadastro() {
        return dataCadastro.get();
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    public ObjectProperty<LocalDateTime> dataCadastroProperty() {
        return dataCadastro;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<EntradaProdutoProduto> getEntradaProdutoProdutoList() {
        return entradaProdutoProdutoList;
    }

    public void setEntradaProdutoProdutoList(List<EntradaProdutoProduto> entradaProdutoProdutoList) {
        this.entradaProdutoProdutoList = entradaProdutoProdutoList;
    }

    @Override
    public EntradaProduto clone() throws CloneNotSupportedException {
        EntradaProduto entradaProduto = new EntradaProduto();
        EntradaProdutoDAO entradaProdutoDAO = new EntradaProdutoDAO();
        entradaProduto = entradaProdutoDAO.getById(EntradaProduto.class, getId());
        return entradaProduto;
    }

    @Override
    public String toString() {
        return "EntradaProduto{" +
                "id=" + id +
                ", situacao=" + situacao +
                ", loja=" + loja +
                ", entradaNfe=" + entradaNfe +
                ", entradaCte=" + entradaCte +
                ", usuarioCadastro=" + usuarioCadastro +
                ", dataCadastro=" + dataCadastro +
                ", entradaProdutoProdutoList=" + entradaProdutoProdutoList +
                '}';
    }
}
