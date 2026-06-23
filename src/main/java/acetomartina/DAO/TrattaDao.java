package acetomartina.DAO;

import acetomartina.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TrattaDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public TrattaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // SALVO
    public void save(Tratta tratta){
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(tratta);
            transazione.commit();
            System.out.println("La tratta con capolinea : "+ tratta.getCapolinea() + " è stata aggiunta al DATABASE");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio della tratta : " + e.getMessage());
        }
    }
}
