package acetomartina.DAO;

import acetomartina.entities.Corsa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class CorsaDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public CorsaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Corsa getById(UUID id) {
        return entityManager.find(Corsa.class, id);
    }

    // SALVO
    public void save(Corsa corsa) {
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(corsa);
            transazione.commit();
            System.out.println("La corsa con la tratta : " + corsa.getTratta() + " è stata aggiunta al DATABASE");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio della tratta : " + e.getMessage());
        }
    }



}
