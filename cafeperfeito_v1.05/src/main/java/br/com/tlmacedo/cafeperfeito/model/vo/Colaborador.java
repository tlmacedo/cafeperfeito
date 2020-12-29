package br.com.tlmacedo.cafeperfeito.model.vo;


import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoNoSistema;
import br.com.tlmacedo.cafeperfeito.service.ServiceImage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Entity(name = "Colaborador")
@Table(name = "colaborador")
@Inheritance(strategy = InheritanceType.JOINED)
public class Colaborador implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty apelido = new SimpleStringProperty();
    private StringProperty ctps = new SimpleStringProperty();
    private ObjectProperty<LocalDateTime> dataAdmisao = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> salario = new SimpleObjectProperty<>();
    private IntegerProperty ativo = new SimpleIntegerProperty();
    private Empresa empresa;
    private Cargo cargo = new Cargo();

    private Blob imgColaborador, imgColaboradorBack;

    public Colaborador() {
    }


    public Colaborador(String nome, String apelido, String ctps, LocalDateTime dataAdmisao, BigDecimal salario, SituacaoNoSistema ativo, Empresa empresa, Cargo cargo) {
        this.nome = new SimpleStringProperty(nome);
        this.apelido = new SimpleStringProperty(apelido);
        this.ctps = new SimpleStringProperty(ctps);
        this.dataAdmisao = new SimpleObjectProperty<>(dataAdmisao);
        this.salario = new SimpleObjectProperty<>(salario);
        this.ativo = new SimpleIntegerProperty(ativo.getCod());
        this.empresa = empresa;
        this.cargo = cargo;
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

    @Column(length = 80, nullable = false, unique = true)
    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    @Column(length = 30, nullable = false, unique = true)
    public String getApelido() {
        return StringUtils.capitalize(apelido.get());
    }

    public StringProperty apelidoProperty() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido.set(apelido);
    }

    @Column(length = 30, nullable = false, unique = true)
    public String getCtps() {
        return ctps.get();
    }

    public StringProperty ctpsProperty() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps.set(ctps);
    }

    @Column(nullable = false)
    public LocalDateTime getDataAdmisao() {
        return dataAdmisao.get();
    }

    public void setDataAdmisao(LocalDateTime dataAdmisao) {
        this.dataAdmisao.set(dataAdmisao);
    }

    public ObjectProperty<LocalDateTime> dataAdmisaoProperty() {
        return dataAdmisao;
    }

    @Column(nullable = false, length = 19, scale = 2)
    public BigDecimal getSalario() {
        return salario.get();
    }

    public void setSalario(BigDecimal salario) {
        this.salario.set(salario);
    }

    public ObjectProperty<BigDecimal> salarioProperty() {
        return salario;
    }

    @Column(length = 2, nullable = false)
    public SituacaoNoSistema getAtivo() {
        return SituacaoNoSistema.toEnum(ativo.get());
    }

    public IntegerProperty ativoProperty() {
        return ativo;
    }

    public void setAtivo(SituacaoNoSistema ativo) {
        this.ativo.set(ativo.getCod());
    }

    @ManyToOne
    @JoinColumn(name = "empresa_id", foreignKey = @ForeignKey(name = "fk_colaborador_empresa"), nullable = false)
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @ManyToOne
    @JoinColumn(name = "cargo_id", foreignKey = @ForeignKey(name = "fk_colaborador_cargo"), nullable = false)
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @JsonIgnore
    @SuppressWarnings("JpaAttributeTypeInspection")
    public Blob getImgColaborador() {
        return imgColaborador;
    }

    public void setImgColaborador(Blob imgColaborador) {
        this.imgColaborador = imgColaborador;
    }

    @Transient
    public Image getImageColaborador() {
        try {
            return ServiceImage.getImageFromInputStream(getImgColaborador().getBinaryStream());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setImageColaborador(Image imageColaborador) {
        try {
            this.imgColaborador = new SerialBlob(ServiceImage.getInputStreamFromImage(imageColaborador).readAllBytes());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transient
    public Image getImageColaboradorBack() {
        return ServiceImage.getImageFromInputStream((InputStream) imgColaboradorBack);
    }

    public void setImgColaboradorBack(Image imageColaboradorBack) {
        try {
            this.imgColaboradorBack = new SerialBlob(ServiceImage.getInputStreamFromImage(imageColaboradorBack).readAllBytes());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "id=" + id +
                ", nome=" + nome +
                ", apelido=" + apelido +
                ", ctps=" + ctps +
                ", dataAdmisao=" + dataAdmisao +
                ", salario=" + salario +
                ", ativo=" + ativo +
                ", empresa=" + empresa +
                ", cargo=" + cargo +
                ", imgColaborador=" + imgColaborador +
                ", imgColaboradorBack=" + imgColaboradorBack +
                '}';
    }
}
