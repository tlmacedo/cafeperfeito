package br.com.tlmacedo.cafeperfeito.interfaces.jpa_Database;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class DAOImpl<T, I extends Serializable> implements DAO<T, I> {
    private ConnectionFactory conexao;

    public DAOImpl() {
        if (conexao == null)
            conexao = new ConnectionFactory();
    }

    @Override
    public T persiste(T entity) {
        conexao.getEntityManager().getTransaction().begin();
        T saved = conexao.getEntityManager().merge(entity);
        conexao.getEntityManager().getTransaction().commit();
        return saved;
    }

    @Override
    public void remove(T entity) {
        conexao.getEntityManager().getTransaction().begin();
        conexao.getEntityManager().remove(entity);
        conexao.getEntityManager().getTransaction().commit();
    }

    @Override
    public T getById(Class<T> classe, I pk) {
        return conexao.getEntityManager().find(classe, pk);
    }

    @Override
    public List<T> getAll(Class<T> classe, String orderBy, String campo, String operador, String busca) {
        Query select;
        String sql = String.format("from %s%s%s",
                classe.getSimpleName(),
                (campo != null && operador != null && busca != null)
                        ? String.format(" where %s %s %s", campo, operador, busca) : "",
                orderBy != null
                        ? String.format(" order by %s", orderBy) : ""
        );
        select = conexao.getEntityManager().createQuery(sql);
//        List<T> list = select.getResultList();
//        return list;
        return select.getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        if (conexao == null)
            conexao = new ConnectionFactory();
        return conexao.getEntityManager();
    }
}
