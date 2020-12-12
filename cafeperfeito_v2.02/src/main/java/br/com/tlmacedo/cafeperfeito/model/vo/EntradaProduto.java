package br.com.tlmacedo.cafeperfeito.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//@JsonSerialize(using = EntradaProdutoSerializer.class)
//@JsonDeserialize(using = EntradaProdutoDeserializer.class)
public class EntradaProduto implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private IntegerProperty situacao = new SimpleIntegerProperty();

    private ObjectProperty<Empresa> loja = new SimpleObjectProperty<>();

    private ObjectProperty<Usuario> usuarioCadastro = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDateTime> dtCadastro = new SimpleObjectProperty<>(LocalDateTime.now());

    private ObjectProperty<EntradaNfe> entradaNfe = new SimpleObjectProperty<>();
    private ObjectProperty<EntradaCte> entradaCte = new SimpleObjectProperty<>();

    private List<EntradaProdutoProduto> entradaProdutoProdutoList = new ArrayList<>();

    public EntradaProduto() {
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

    @Column(length = 1, nullable = false)
    public int getSituacao() {
        return situacao.get();
    }

    public IntegerProperty situacaoProperty() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao.set(situacao);
    }

    @ManyToOne
    public Empresa getLoja() {
        return loja.get();
    }

    public ObjectProperty<Empresa> lojaProperty() {
        return loja;
    }

    public void setLoja(Empresa loja) {
        this.loja.set(loja);
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
    public LocalDateTime getDtCadastro() {
        return dtCadastro.get();
    }

    public ObjectProperty<LocalDateTime> dtCadastroProperty() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDateTime dtCadastro) {
        this.dtCadastro.set(dtCadastro);
    }

    @OneToOne(mappedBy = "entradaProduto", cascade = CascadeType.ALL, orphanRemoval = true)
    public EntradaNfe getEntradaNfe() {
        return entradaNfe.get();
    }

    public ObjectProperty<EntradaNfe> entradaNfeProperty() {
        return entradaNfe;
    }

    public void setEntradaNfe(EntradaNfe entradaNfe) {
        this.entradaNfe.set(entradaNfe);
    }

    @OneToOne(mappedBy = "entradaProduto", cascade = CascadeType.ALL, orphanRemoval = true)
    public EntradaCte getEntradaCte() {
        return entradaCte.get();
    }

    public ObjectProperty<EntradaCte> entradaCteProperty() {
        return entradaCte;
    }

    public void setEntradaCte(EntradaCte entradaCte) {
        this.entradaCte.set(entradaCte);
    }

    @OneToMany(mappedBy = "entradaProduto", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<EntradaProdutoProduto> getEntradaProdutoProdutoList() {
        return entradaProdutoProdutoList;
    }

    public void setEntradaProdutoProdutoList(List<EntradaProdutoProduto> entradaProdutoProdutoList) {
        this.entradaProdutoProdutoList = entradaProdutoProdutoList;
    }

//    @Override
//    public String toString() {
//        return "EntradaProduto{" +
//                "id=" + id +
//                ", situacao=" + situacao +
//                ", loja=" + loja +
//                ", usuarioCadastro=" + usuarioCadastro +
//                ", dtCadastro=" + dtCadastro +
//                ", entradaProdutoProdutoList=" + entradaProdutoProdutoList +
//                '}';
//    }


    @Override
    public String toString() {
        return "EntradaProduto{" +
                "id=" + id +
                ", situacao=" + situacao +
                ", loja=" + loja +
                ", usuarioCadastro=" + usuarioCadastro +
                ", dtCadastro=" + dtCadastro +
                ", entradaNfe=" + entradaNfe +
                ", entradaCte=" + entradaCte +
                ", entradaProdutoProdutoList=" + entradaProdutoProdutoList +
                '}';
    }
}
