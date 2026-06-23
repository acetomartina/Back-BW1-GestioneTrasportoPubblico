package acetomartina.DAO;

import acetomartina.enom.StatoMezzo;
import acetomartina.entities.Manutenzione;
import acetomartina.entities.Mezzo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.UUID;

public class MezzoDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public MezzoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // SALVO
    public void save(Mezzo mezzo) {
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(mezzo);
            transazione.commit();
            System.out.println("Il mezzo numero : " + mezzo.getNumero_mezzo() + " è stato aggiunto al DATABASE");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio del mezzo : " + e.getMessage(), e);
        }
    }

    // CERCA PER ID
    public Mezzo getById(UUID mezzoID) {
        Mezzo mezzoTrovato = entityManager.find(Mezzo.class, mezzoID);
        if (mezzoTrovato != null) return mezzoTrovato;
        else {
            System.out.println("Nessun mezzo trovato all'id corrispondente!");
            return mezzoTrovato;
        }
    }

    //PRENDE TUTTI I MEZZI IN SERVIZIO
    public List<Mezzo> getMezziInServizio() {
        try {
            return entityManager.createQuery("from Mezzo where stato_mezzo = :stato", Mezzo.class).setParameter("stato", StatoMezzo.ATTIVO).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante recupero mezzi in servizio", e);
        }
    }

    //PRENDE TUTTI I MEZZI IN MANUTEZIONE
    public List<Mezzo> getMezziInManutenzione() {
        try {
            return entityManager.createQuery("from Mezzo where stato_mezzo = :stato", Mezzo.class).setParameter("stato", StatoMezzo.IN_MANUTENZIONE).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante recupero mezzi in manutanzione", e);
        }
    }

    // RITORNA LA LISTA DELLE MANUTENZIONI PROGRAMMATE  E GIA ESEGUITE PER UN MEZZO
    public List<Manutenzione> getManutenzioniMezzo(UUID mezzoId) {
        try {
            return entityManager.createQuery("from Manutenzione where mezzo.id = :param", Manutenzione.class).setParameter("param", mezzoId).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante la ricerca delle manutenzioni", e);
        }
    }

//    // AGGIUNGE MANUTANZIONE
//    public void aggiungiManutenzione(Manutenzione manutenzione){
//
//
//    }

}
