package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.vo.enums.GuestAccess;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoNoSistema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity(name = "Usuario")
@Table(name = "usuario")
public class Usuario extends Colaborador implements Serializable {
    private static final long serialVersionUID = 1L;

    private StringProperty email = new SimpleStringProperty();
    private StringProperty senha = new SimpleStringProperty();
    private IntegerProperty userAtivo = new SimpleIntegerProperty();
    private IntegerProperty guestAcess = new SimpleIntegerProperty();
    private Blob imagem;

    public Usuario() {
    }

    public Usuario(String nome, String apelido, String ctps, LocalDateTime dataAdmisao, BigDecimal salario, SituacaoNoSistema ativo, Empresa empresa, Cargo cargo, String senha, String email, SituacaoNoSistema userAtivo, GuestAccess guestAccess, Blob imagem) {
        super(nome, apelido, ctps, dataAdmisao, salario, ativo, empresa, cargo);
        this.senha = new SimpleStringProperty(senha);
        this.email = new SimpleStringProperty(email);
        this.userAtivo = new SimpleIntegerProperty(userAtivo.getCod());
        this.guestAcess = new SimpleIntegerProperty(guestAccess.getCod());
        this.imagem = imagem;
    }

    @Column(length = 150, nullable = false)
    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Column(length = 128, nullable = false)
    public String getSenha() {
        return senha.get();
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public StringProperty senhaProperty() {
        return senha;
    }

    @Column(length = 1, nullable = false)
    public SituacaoNoSistema getUserAtivo() {
        return SituacaoNoSistema.toEnum(userAtivo.get());
    }

    public IntegerProperty userAtivoProperty() {
        return userAtivo;
    }

    public void setUserAtivo(SituacaoNoSistema userAtivo) {
        this.userAtivo.set(userAtivo.getCod());
    }

    @Column(length = 3, nullable = false)
    public GuestAccess getGuestAcess() {
        return GuestAccess.toEnum(guestAcess.get());
    }

    public void setGuestAcess(GuestAccess guestAcess) {
        this.guestAcess.set(guestAcess.getCod());
    }

    public IntegerProperty guestAcessProperty() {
        return guestAcess;
    }

    @JsonIgnore
    @SuppressWarnings("JpaAttributeTypeInspection")
    public Blob getImagem() {
        return imagem;
    }

    public void setImagem(Blob imagem) {
        this.imagem = imagem;
    }

    @Transient
    public String getDetalhe() {
        StringBuilder usuarioDetalhe = new StringBuilder();
        Optional.of(getNome()).ifPresent(c -> {
                    if (usuarioDetalhe != null)
                        usuarioDetalhe.append("\n");
                    usuarioDetalhe.append(String.format("Usuário: %s;", getNome()));
                }
        );
        Optional.of(getApelido()).ifPresent(c -> {
                    if (usuarioDetalhe != null)
                        usuarioDetalhe.append("\n");
                    usuarioDetalhe.append(String.format("Apelido: %s;", getApelido()));
                }
        );
        Optional.of(getCargo()).ifPresent(c -> {
                    if (usuarioDetalhe != null)
                        usuarioDetalhe.append("\n");
                    usuarioDetalhe.append(String.format("Cargo: %s;", c.getDescricao()));
                }
        );
        return usuarioDetalhe.toString();
    }

    @Override
    public String toString() {
        return emailProperty().get();
    }
}