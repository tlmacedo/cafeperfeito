package br.com.tlmacedo.cafeperfeito.interfaces.jpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class ConnectionFactory {

    public static final String UNIT_NAME = "cafeperfeitoPU";


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    public EntityManager getEntityManager() {
        Map<String, String> properties = new HashMap<String, String>();

        if (entityManagerFactory == null)
            entityManagerFactory = Persistence.createEntityManagerFactory(UNIT_NAME, properties);
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();

        return entityManager;
    }

    public void closeEntityManager() {
        if (entityManager != null) {
            entityManager.close();
            if (entityManagerFactory != null)
                entityManagerFactory.close();
        }
    }
}
