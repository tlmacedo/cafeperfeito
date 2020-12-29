package br.com.tlmacedo.cafeperfeito.model.dao;

import br.com.tlmacedo.cafeperfeito.interfaces.jpa_Database.DAO;
import br.com.tlmacedo.cafeperfeito.interfaces.jpa_Database.DAOImpl;
import br.com.tlmacedo.cafeperfeito.model.vo.Contato;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-01-22
 * Time: 20:32
 */

public class ContatoDAO extends DAOImpl<Contato, Long> implements DAO<Contato, Long> {
}
