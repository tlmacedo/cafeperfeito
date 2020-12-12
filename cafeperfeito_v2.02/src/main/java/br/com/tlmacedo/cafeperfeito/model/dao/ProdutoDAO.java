package br.com.tlmacedo.cafeperfeito.model.dao;

import br.com.tlmacedo.cafeperfeito.interfaces.jpa.DAO;
import br.com.tlmacedo.cafeperfeito.interfaces.jpa.DAOImpl;
import br.com.tlmacedo.cafeperfeito.model.vo.Produto;

public class ProdutoDAO extends DAOImpl<Produto, Long> implements DAO<Produto, Long> {
}
