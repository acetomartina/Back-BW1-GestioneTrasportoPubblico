package acetomartina.DAO;

import acetomartina.entities.PuntoEmissione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PuntoEmissioneDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public PuntoEmissioneDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // SALVO
    public void save(PuntoEmissione puntoEmissione){
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(puntoEmissione);
            transazione.commit();
            System.out.println("Il punto di emissione : "+ puntoEmissione.getPunto_emissione() + " è stato aggiunto al DATABASE");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio del punto di emissione : " + e.getMessage());
        }
    }
}
