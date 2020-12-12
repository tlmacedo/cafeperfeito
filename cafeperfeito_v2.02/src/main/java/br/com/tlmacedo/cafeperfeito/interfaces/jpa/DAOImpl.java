package br.com.tlmacedo.cafeperfeito.interfaces.jpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class DAOImpl<T, I extends Serializable> implements DAO<T, I> {
    private ConnectionFactory conexao;

    public DAOImpl() {
        if (getConexao() == null)
            setConexao(new ConnectionFactory());
    }

    @Override
    public T merger(T entity) throws Exception {
        try {
            transactionBegin();
            T saved = getConexao().getEntityManager().merge(entity);
            transactionCommit();
            return saved;
        } catch (Exception ex) {
            ex.printStackTrace();
            transactionRollback();
            return null;
        }
    }

    @Override
    public void transactionBegin() {
        getTransaction().begin();
    }

    @Override
    public T setTransactionPersist(T entity) throws Exception {
        try {
            T saved = getConexao().getEntityManager().merge(entity);
            return saved;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void transactionCommit() throws Exception {
        try {
            getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void transactionRollback() {
        try {
            if (getTransaction() == null)
                getTransaction().rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(T entity) {
        try {
            getConexao().getEntityManager().getTransaction().begin();
            getConexao().getEntityManager().remove(entity);
            getConexao().getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public T getById(Class<T> classe, I pk) {
        try {
            return getConexao().getEntityManager().find(classe, pk);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> getAll(Class<T> classe, String personalizaBusca, String orderBy) {
        try {
            Query select;
            String sql = String.format("from %s%s%s",
                    classe.getSimpleName(),
                    (personalizaBusca != null)
                            ? String.format(" where %s", personalizaBusca) : "",
                    orderBy != null
                            ? String.format(" order by %s", orderBy) : ""
            );
            select = getConexao().getEntityManager().createQuery(sql);
            return select.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public T getLast(Class<T> classe, String campo) {
        try {
            Query select;
            String sql = String.format("from %s ORDER BY %s DESC",
                    classe.getSimpleName(), campo);
            return (T) getConexao().getEntityManager().createQuery(sql).setMaxResults(1).getSingleResult();
        } catch (Exception ex) {
            if (!(ex instanceof NoResultException))
                ex.printStackTrace();
            return null;
        }
    }

    @Override
    public EntityManager getEntityManager() {
        if (getConexao() == null)
            setConexao(new ConnectionFactory());
        return getConexao().getEntityManager();
    }

    @Override
    public EntityTransaction getTransaction() {
        return getConexao().getEntityManager().getTransaction();
    }

    @Override
    public void closeTransaction() {
        getConexao().getEntityManager().close();
    }

    public ConnectionFactory getConexao() {
        return conexao;
    }

    public void setConexao(ConnectionFactory conexao) {
        this.conexao = conexao;
    }
}
