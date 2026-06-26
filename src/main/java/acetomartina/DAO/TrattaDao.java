package acetomartina.DAO;

import acetomartina.Exceptions.ErroreSalvataggio;
import acetomartina.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

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
        } catch (ErroreSalvataggio e){
            System.out.println("Errore nel salvataggio. Ti chiediamo di riprovare più tardi.");
        }
        catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio della tratta : " + e.getMessage());
        }
    }

    public List<Tratta> findAll() {
        TypedQuery<Tratta> query = entityManager.createQuery(
                "SELECT t FROM Tratta t",
                Tratta.class
        );
        return query.getResultList();
    }
}
