package acetomartina.DAO;

import acetomartina.entities.PuntoEmissione;
import acetomartina.enums.TipoPuntoEmissione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.UUID;

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
            System.out.println("Il punto di emissione : "+ puntoEmissione.getPunto_emissione() + " è stato aggiunto al DATABASE.");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio del punto di emissione : " + e.getMessage());
        }
    }

    public PuntoEmissione findById(UUID punto_emissione){
        return entityManager.find(PuntoEmissione.class, punto_emissione);
    }

    public void delete (UUID punto_emissione){
        PuntoEmissione puntoEmissione = findById(punto_emissione);

        if(puntoEmissione != null){
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(puntoEmissione);
            transaction.commit();

            System.out.println("Punto emissione eliminato correttamente.");
        } else {
            System.out.println("Punto emissione non trovato.");
        }
    }

    public List<PuntoEmissione> findByTipo(TipoPuntoEmissione tipoPuntoEmissione) {
        return entityManager.createQuery(
                "SELECT p FROM PuntoEmissione p WHERE p.tipoPuntoEmissione = :tipo",
                PuntoEmissione.class
        )
                .setParameter("tipo",tipoPuntoEmissione)
                .getResultList();
    }

    public List<PuntoEmissione> findByStato(boolean inAttivita){
        return entityManager.createQuery(
                "SELECT p FROM PuntoEmissione p WHERE p.inAttivita = :stato",
                PuntoEmissione.class
        ) .setParameter("stato", inAttivita)
                .getResultList();
    }

    public List<PuntoEmissione> findAll(){
        return entityManager.createQuery(
                "SELECT p FROM PuntoEmissione p",
                PuntoEmissione.class
        ) .getResultList();
    }
}
