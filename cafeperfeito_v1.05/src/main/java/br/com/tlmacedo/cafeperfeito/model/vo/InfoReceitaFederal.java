package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.AtividadeReceitaFederalTipo;
import br.com.tlmacedo.cafeperfeito.service.ServiceSplitString;
import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-01-23
 * Time: 12:40
 */

@Entity(name = "InfoReceitaFederal")
@Table(name = "info_receita_federal")
public class InfoReceitaFederal implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty code = new SimpleStringProperty();
    private StringProperty text = new SimpleStringProperty();

    private IntegerProperty tipo = new SimpleIntegerProperty();

    public InfoReceitaFederal() {
    }

    public InfoReceitaFederal(String code, String text, AtividadeReceitaFederalTipo tipo) {
        this.code = new SimpleStringProperty(code);
        this.text = new SimpleStringProperty(text);
        this.tipo = new SimpleIntegerProperty(tipo.getCod());
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

    @Column(length = 70, nullable = false)
    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public StringProperty codeProperty() {
        return code;
    }

    @Column(length = 500, nullable = false)
    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    @Column(length = 2, nullable = false)
    public AtividadeReceitaFederalTipo getTipo() {
        return AtividadeReceitaFederalTipo.toEnum(tipo.get());
    }

    public void setTipo(AtividadeReceitaFederalTipo tipo) {
        this.tipo.set(tipo.getCod());
    }

    public IntegerProperty tipoProperty() {
        return tipo;
    }

    @Transient
    public String getDetalheInfoReceitaFederal() {
        StringBuilder retorno = new StringBuilder("");

        if (codeProperty().get() != null)
            retorno.append(String.format("[item]: %s", codeProperty().get()));

        if (!retorno.toString().equals("")) retorno.append("\r\n");
        String descricao = "";
        if (textProperty().get().length() < 70) {
            descricao += String.format("    [Descrição]: %s", textProperty().get());
        } else {
            descricao += String.format("    [Descrição]: %s", textProperty().get().substring(0, 70));
            for (String split : ServiceSplitString.split(textProperty().get().substring(70), 90))
                descricao += String.format("\r\n%s", split);
        }
        retorno.append(descricao);
        return retorno.toString();
    }

    @Override
    public String toString() {
        return String.format(" [%11s] %s",
                codeProperty().get(),
                textProperty().get());
    }
}
