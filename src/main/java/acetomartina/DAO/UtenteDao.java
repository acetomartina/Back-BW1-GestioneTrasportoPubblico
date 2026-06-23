package acetomartina.DAO;

import acetomartina.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public UtenteDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // SALVO
    public void save(Utente utente){
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(utente);
            transazione.commit();
            System.out.println("L'utente "+ utente.getNome_utente() + " "+ utente.getCognome_utente()+ ", è stato aggiunto al DATABASE");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio dell'utente : " + e.getMessage());
        }
    }
}
