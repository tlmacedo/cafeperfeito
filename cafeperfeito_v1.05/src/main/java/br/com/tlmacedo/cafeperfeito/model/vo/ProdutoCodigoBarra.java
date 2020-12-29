package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.service.ServiceImage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

@Entity(name = "ProdutoCodigoBarra")
@Table(name = "produto_codigo_barra")
public class ProdutoCodigoBarra implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty codigoBarra = new SimpleStringProperty();
    private Blob imgCodigoBarra;

    public ProdutoCodigoBarra() {
    }

    public ProdutoCodigoBarra(String codigoBarra, Image imageCodigoBarra) {
        this.codigoBarra = new SimpleStringProperty(codigoBarra);
        try {
            this.imgCodigoBarra = new SerialBlob(ServiceImage.getInputStreamFromImage(imageCodigoBarra).readAllBytes());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Column(length = 18, nullable = false, unique = true)
    public String getCodigoBarra() {
        return codigoBarra.get();
    }

    public StringProperty codigoBarraProperty() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra.set(codigoBarra);
    }

    @JsonIgnore
    @SuppressWarnings("JpaAttributeTypeInspection")
    public Blob getImgCodigoBarra() {
        return imgCodigoBarra;
    }

    public void setImgCodigoBarra(Blob imgCodigoBarra) {
        this.imgCodigoBarra = imgCodigoBarra;
    }

    @JsonIgnore
    @Transient
    public Image getImageCodigoBarra() {
        try {
            if (getImgCodigoBarra() != null)
                return ServiceImage.getImageFromInputStream(getImgCodigoBarra().getBinaryStream());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Transient
    public void setImageCodigoBarra(Image imageCodigoBarra) {
        setImgCodigoBarra((Blob) ServiceImage.getInputStreamFromImage(imageCodigoBarra));
    }

    @Override
    public String toString() {
        return "ProdutoCodigoBarra{" +
                "id=" + id +
                ", codigoBarra=" + codigoBarra +
                '}';
    }
}
