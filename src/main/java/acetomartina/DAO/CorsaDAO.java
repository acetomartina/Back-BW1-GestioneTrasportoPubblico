package acetomartina.DAO;

import acetomartina.Exceptions.ErroreSalvataggio;
import acetomartina.entities.Corsa;

import acetomartina.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

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
        } catch (ErroreSalvataggio e){
            System.out.println("Errore nel salvataggio. Ti chiediamo di riprovare più tardi.");
        }
        catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio della tratta : " + e.getMessage());
        }
    }

    public List<Corsa> findAll() {
        TypedQuery<Corsa> query = entityManager.createQuery(
                "SELECT c FROM Corsa c",
                Corsa.class
        );

        return query.getResultList();
    }

    public List<Corsa> findAllByTratta(Tratta tratta) {
        TypedQuery<Corsa> query = entityManager.createQuery(
                "SELECT c FROM Corsa c WHERE c.tratta.id = :trattaId",
                Corsa.class
        );

        query.setParameter("trattaId", tratta.getId());

        return query.getResultList();
    }


}
