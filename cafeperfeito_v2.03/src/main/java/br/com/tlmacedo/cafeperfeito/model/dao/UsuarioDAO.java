package br.com.tlmacedo.cafeperfeito.model.dao;

import br.com.tlmacedo.cafeperfeito.interfaces.jpa.DAO;
import br.com.tlmacedo.cafeperfeito.interfaces.jpa.DAOImpl;
import br.com.tlmacedo.cafeperfeito.model.vo.Usuario;

public class UsuarioDAO extends DAOImpl<Usuario, Long> implements DAO<Usuario, Long> {
}
