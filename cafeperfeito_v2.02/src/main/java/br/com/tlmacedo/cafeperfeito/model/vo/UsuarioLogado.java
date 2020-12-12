package br.com.tlmacedo.cafeperfeito.model.vo;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

//import org.apache.maven.surefire.shade.booter.org.apache.commons.lang3.StringUtils;

public class UsuarioLogado implements Serializable {
    private static final long serialVersionUID = 1L;

    private static ObjectProperty<Usuario> usuario = new SimpleObjectProperty<>();
    private static ObjectProperty<LocalDateTime> dataDeLog = new SimpleObjectProperty<>(LocalDateTime.now());

    public UsuarioLogado() {
    }

    public static Usuario getUsuario() {
        return usuario.get();
    }

    public static ObjectProperty<Usuario> usuarioProperty() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        UsuarioLogado.usuario.set(usuario);
    }

    public static LocalDateTime getDataDeLog() {
        return dataDeLog.get();
    }

    public static ObjectProperty<LocalDateTime> dataDeLogProperty() {
        return dataDeLog;
    }

    public static void setDataDeLog(LocalDateTime dataDeLog) {
        UsuarioLogado.dataDeLog.set(dataDeLog);
    }

    @Override
    public String toString() {
        return StringUtils.capitalize(getUsuario().getApelido());
    }
}
