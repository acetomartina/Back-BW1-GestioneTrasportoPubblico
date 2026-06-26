package acetomartina.DAO;

import acetomartina.Exceptions.ErroreSalvataggio;
import acetomartina.entities.Abbonamento;
import acetomartina.entities.Biglietto;

import acetomartina.entities.TitoloViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TitoloViaggioDao {
    private final EntityManager entityManager;

    //COSTRUTTORE
    public TitoloViaggioDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // SALVO
    public void save(TitoloViaggio titoloViaggio) {
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(titoloViaggio);
            transazione.commit();
            System.out.println("Il titolo di viaggio numero " + titoloViaggio.getCodiceUnivoco() + " è stato aggiunto al DATABASE");
        } catch (ErroreSalvataggio e){
            System.out.println("Errore nel salvataggio. Ti chiediamo di riprovare più tardi.");
        }

        catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio del titolo di viaggio : " + e.getMessage());
        }
    }

    //CERCO PER ID
    public TitoloViaggio findById(UUID id) {
        TitoloViaggio fromDB = this.entityManager.find(TitoloViaggio.class, id);
        if (fromDB == null) throw new RuntimeException(" utente non trovato nel database.");
        return fromDB;
    }

    //RIMUOVO TRAMITE ID
    public void deleteFromDb(UUID id) {
        TitoloViaggio fromDB = this.findById(id);
        if (fromDB == null) throw new RuntimeException(" id non trovato nel database.");
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.remove(fromDB);
        transaction.commit();
        System.out.println("Il titolo di viaggio " + fromDB.getCodiceUnivoco() + " è stato eliminato dal database");
    }

    // VERIFICA VALIDITA
    public boolean verificaValidita(TitoloViaggio titoloViaggio) {
        if (titoloViaggio instanceof Biglietto) {
            if (((Biglietto) titoloViaggio).isValido()) {
                System.out.println("Il biglietto è valido!");
                return true;
            } else {
                System.out.println("Il biglietto è gia scaduto!");
                return false;
            }
        }
        if (titoloViaggio instanceof Abbonamento) {
            if (((Abbonamento) titoloViaggio).isValido()) {
                System.out.println("L'abbonamento è valido!");
                return true;
            } else {
                System.out.println("L'abbonamento è scaduto!");
                return false;
            }
        } else {
            return false;
        }
    }
}



