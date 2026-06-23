package acetomartina.DAO;

import acetomartina.entities.Biglietto;
import acetomartina.entities.TitoloViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.UUID;

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

    //CERCO PER ID

    public TitoloViaggio findById(UUID id){
        TitoloViaggio fromDB = this.entityManager.find(TitoloViaggio.class, id);
        if (fromDB == null) throw new RuntimeException(" utente non trovato nel database.");
        return fromDB;
    }

    //RIMUOVO TRAMITE ID

    public void deleteFromDb (UUID id){
        TitoloViaggio fromDB = this.findById(id);
        if (fromDB == null) throw new RuntimeException(" id non trovato nel database.");
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.remove(fromDB);
        transaction.commit();
        System.out.println("Il titolo di viaggio "+ fromDB.getCodiceUnivoco()+" è stato eliminato dal database");

    }

    // VALIDITA' BIGLIETTO
    public boolean checkValidity(Biglietto biglietto){
        Biglietto fromDB = (Biglietto) this.findById(biglietto.getId());
        if (!fromDB.isObliterato()) {
            return true;
        }
        if (fromDB.getScadenza() != null && LocalDateTime.now().isBefore(fromDB.getScadenza())) {
            return true;
        }
        return false;
    }

    // CONVALIDO IL BIGLIETTO
    public Biglietto convalidateFromDb (Biglietto biglietto){
        Biglietto fromDB = (Biglietto) this.findById(biglietto.getId());

        if (fromDB.isObliterato()) {
            System.out.println("Biglietto già utilizzato! Scadeva il: " + fromDB.getScadenza());
            return fromDB;
        }

        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            fromDB.setObliterato(true);
            fromDB.setScadenza(LocalDateTime.now().plusMinutes(90));
            this.entityManager.merge(fromDB);
            transaction.commit();
            System.out.println("Biglietto convalidato con successo. Scadenza: " + fromDB.getScadenza());
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Errore durante la convalida: " + e.getMessage());
        }

        return fromDB;
    }

    // CERCO PER VALIDITA'
    public Biglietto findByValidity (Biglietto biglietto){
        Biglietto fromDB = (Biglietto) this.findById(biglietto.getId());
        if (fromDB.isObliterato()) {
            System.out.println("Biglietto timbrato. Data emissione: " + fromDB.getDataEmissione());
        } else {
            System.out.println("Biglietto non timbrato");
        }
        return fromDB;
    }
}



