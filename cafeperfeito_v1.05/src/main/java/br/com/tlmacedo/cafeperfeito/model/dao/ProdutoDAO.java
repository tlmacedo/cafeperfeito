package br.com.tlmacedo.cafeperfeito.model.dao;

import br.com.tlmacedo.cafeperfeito.interfaces.jpa_Database.DAO;
import br.com.tlmacedo.cafeperfeito.interfaces.jpa_Database.DAOImpl;
import br.com.tlmacedo.cafeperfeito.model.vo.Produto;

public class ProdutoDAO extends DAOImpl<Produto, Long> implements DAO<Produto, Long> {
}
