package acetomartina.DAO;

import acetomartina.entities.Mezzo;

import acetomartina.entities.TitoloViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class MezzoDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public MezzoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // SALVO
    public void save(Mezzo mezzo){
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(mezzo);
            transazione.commit();
            System.out.println("Il mezzo numero : "+ mezzo.getNumero_mezzo() + " è stato aggiunto al DATABASE");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio del mezzo : " + e.getMessage());
        }


    }
    //CERCO PER ID

    public Mezzo findMezzoById(UUID id){
        Mezzo fromDB = this.entityManager.find(Mezzo.class, id);
        if (fromDB == null) throw new RuntimeException(" mezzo non trovato nel database.");
        return fromDB;
    }
}
