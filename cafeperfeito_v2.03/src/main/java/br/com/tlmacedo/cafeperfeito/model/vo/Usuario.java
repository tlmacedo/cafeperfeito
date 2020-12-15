package br.com.tlmacedo.cafeperfeito.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Optional;

@Entity(name = "Usuario")
@Table(name = "usuario")
public class Usuario extends Colaborador implements Serializable {
    public static final long serialVersionUID = 1L;

    private StringProperty email = new SimpleStringProperty();
    private StringProperty senha = new SimpleStringProperty();

    public Usuario() {
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

    public StringProperty senhaProperty() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    @JsonIgnore
    @Transient
    public String getUsuarioDetalhe() {
        StringBuilder detalhe = new StringBuilder();
        Optional.of(getNome()).ifPresent(s ->
                detalhe.append(String.format("Usuário: %s", getNome())));
        Optional.of(getApelido()).ifPresent(s ->
                detalhe.append(String.format("%sApelido: %s", !detalhe.equals(null) ? "\n" : "",
                        getApelido())));
        Optional.of(getCargo()).ifPresent(enumCargo ->
                detalhe.append(String.format("%sCargo: %s", !detalhe.equals(null) ? "\n" : "",
                        getCargo().getDescricao())));
        return detalhe.toString();
    }

    @Override
    public String toString() {
        return getNome();
    }
}
