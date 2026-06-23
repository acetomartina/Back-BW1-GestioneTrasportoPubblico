package acetomartina.DAO;

import acetomartina.entities.Manutenzione;
import acetomartina.entities.Mezzo;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class ManutenzioneDAO {
    private EntityManager entityManager;

    public ManutenzioneDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Manutenzione manutenzione) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(manutenzione);
            entityManager.getTransaction().commit();
            System.out.println("La manutenzione " + manutenzione.getId_Manutenzione() + " è stata salvata!");
        } catch (Exception e) {
            throw new RuntimeException("Errore nel salvataggio della manutanzione!", e);
        }
    }

    public Manutenzione getById(UUID id) {
        Manutenzione manutenzioneTrovata = entityManager.find(Manutenzione.class, id);
        if (manutenzioneTrovata != null) return manutenzioneTrovata;
        else {
            System.out.println("Manutenzione non trovata all'id inserito");
            return manutenzioneTrovata;
        }
    }



}
