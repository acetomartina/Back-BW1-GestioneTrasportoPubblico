package acetomartina.DAO;

import acetomartina.entities.TitoloViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TitoloViaggioDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public TitoloViaggioDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // SALVO
    public void save(TitoloViaggio titoloViaggio){
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(titoloViaggio);
            transazione.commit();
            System.out.println("Il titolo di viaggio numero "+ titoloViaggio.getCodiceUnivoco() + " è stato aggiunto al DATABASE");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio del titolo di viaggio : " + e.getMessage());
        }
    }


}
