package acetomartina.DAO;

import acetomartina.Exceptions.ErroreSalvataggio;
import acetomartina.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TesseraDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public TesseraDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // SALVO
    public void save(Tessera tessera){
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(tessera);
            transazione.commit();
            System.out.println("La tessera numero : "+ tessera.getNumeroTessera() + " è stata aggiunta al DATABASE");
        } catch (ErroreSalvataggio e){
            System.out.println("Errore nel salvataggio. Ti chiediamo di riprovare più tardi.");
        }

        catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio della tessera : " + e.getMessage());
        }
    }

    //TROVO CON ID
    public Tessera getById(String numeroTessera) {
        try {
            return entityManager.find(Tessera.class, numeroTessera);
        } catch (Exception e) {
            return null;
        }
    }

}
