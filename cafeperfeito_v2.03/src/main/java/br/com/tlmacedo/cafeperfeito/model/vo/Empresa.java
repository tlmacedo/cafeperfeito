package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.enums.EnumClassificacaoJuridica;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

//@Entity(name = "Empresa")
//@Table(name = "empresa")
public class Empresa implements Serializable {
    public static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private EnumClassificacaoJuridica classificacaoJuridica;
    private ConfigSituacaoCadastro situacaoCadastro = new ConfigSituacaoCadastro();
    private StringProperty cnpj = new SimpleStringProperty();
    private StringProperty ie = new SimpleStringProperty();
    private StringProperty razao = new SimpleStringProperty();
    private StringProperty fantasia = new SimpleStringProperty();

}
