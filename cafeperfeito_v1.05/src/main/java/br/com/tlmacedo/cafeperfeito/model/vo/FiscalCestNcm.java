package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import br.com.tlmacedo.cafeperfeito.service.ServiceSplitString;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "FiscalCestNcm")
@Table(name = "fiscal_cest_ncm")
public class FiscalCestNcm implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty segmento = new SimpleStringProperty();
    private StringProperty descricao = new SimpleStringProperty();
    private StringProperty cest = new SimpleStringProperty();
    private StringProperty ncm = new SimpleStringProperty();

    public FiscalCestNcm() {
    }

    public FiscalCestNcm(String segmento, String descricao, String cest, String ncm) {
        this.segmento = new SimpleStringProperty(segmento);
        this.descricao = new SimpleStringProperty(descricao);
        this.cest = new SimpleStringProperty(cest);
        this.ncm = new SimpleStringProperty(ncm);
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

    @Column(length = 100, nullable = false)
    public String getSegmento() {
        return segmento.get();
    }

    public StringProperty segmentoProperty() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento.set(segmento);
    }

    @Column(length = 600, nullable = false)
    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    @Column(length = 7, nullable = false)
    public String getCest() {
        return cest.get();
    }

    public StringProperty cestProperty() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest.set(cest);
    }

    @Column(length = 8, nullable = false)
    public String getNcm() {
        return ncm.get();
    }

    public StringProperty ncmProperty() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm.set(ncm);
    }

    @Transient
    public String getDetalheCestNcm() {
        StringBuilder retorno = new StringBuilder("");

        retorno.append(String.format("[Segmento]: %s", segmentoProperty().get()));

        if (!retorno.toString().equals("")) retorno.append("\r\n");
        retorno.append(String.format("[Ncm]: %10s %10s [Cest]: %9s",
                ServiceMascara.getNcm(ncmProperty().get()), "",
                ServiceMascara.getCest(cestProperty().get())
        ));

        if (!retorno.toString().equals("")) retorno.append("\r\n");
        String descricao = "";
        if (descricaoProperty().get().length() < 77) {
            descricao += String.format("[Descrição]: %s", descricaoProperty().get());
        } else {
            descricao += String.format("[Descrição]: %s", descricaoProperty().get().substring(0, 77));
            for (String split : ServiceSplitString.split(descricaoProperty().get().substring(77), 90))
                descricao += String.format("\r\n%s", split);
        }
        retorno.append(descricao);
        return retorno.toString();
    }

    @Override
    public String toString() {
        if (descricaoProperty().get().equals(""))
            return "";
        else
            return String.format("[%d] %s",
                    idProperty().get(),
                    descricaoProperty().get()
            );
    }
}
