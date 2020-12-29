package br.com.tlmacedo.cafeperfeito.interfaces.jpa_Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class ConnectionFactory {
    private static final String UNIT_NAME = "cafeperfeitoPU";
    //private static final String UNIT_NAME = "ftpcafeperfeito";

    private EntityManagerFactory emf;
    private EntityManager em;

    public EntityManager getEntityManager() {
        Map<String, String> properties = new HashMap<String, String>();

        if (emf == null)
            emf = Persistence.createEntityManagerFactory(UNIT_NAME, properties);
        if (em == null)
            em = emf.createEntityManager();
        return em;
    }

    public void closeEntityManeger() {
        try {
            if (em != null) {
                em.close();
                if (emf != null)
                    emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
