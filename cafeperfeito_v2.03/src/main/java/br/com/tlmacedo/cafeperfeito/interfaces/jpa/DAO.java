package br.com.tlmacedo.cafeperfeito.interfaces.jpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public interface DAO<T, I extends Serializable> {

    public T merger(T entity) throws Exception;

    public void remove(T entity);

    public void transactionBegin();

    public T setTransactionPersist(T entity) throws Exception;

    public void transactionCommit() throws Exception;

    public void transactionRollback();

    public T getById(Class<T> classe, I pk);

    public List<T> getAll(Class<T> classe, String personalizaBusca, String orderBy);

    public T getLast(Class<T> classe, String campo);

    public EntityManager getEntityManager();

    public EntityTransaction getTransaction();

    public void closeTransaction();
}