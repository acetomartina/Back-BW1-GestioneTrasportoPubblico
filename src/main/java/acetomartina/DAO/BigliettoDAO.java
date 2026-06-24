package acetomartina.DAO;

import acetomartina.Exceptions.NonTrovatoEccezzione;
import acetomartina.entities.Biglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;

public class BigliettoDAO {
    private EntityManager entityManager;

    public BigliettoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    // OBLITERA IL BIGLIETTO
    public void obliteraBiglietto(Biglietto biglietto) {
        Biglietto fromDB = entityManager.find(Biglietto.class, biglietto.getId());
        if (fromDB.getObliterato() != null) {
            System.out.println("Biglietto gia convalidato! Compra un nuovo biglietto!");
            return;
        }
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            fromDB.setObliterato(LocalDateTime.now());
            transaction.commit();
            System.out.println("Biglietto obliterato con successo !");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Non è stato possibile obliterare il biglietto : " + e.getMessage());
        }

    }

    public boolean verificaValidita(Biglietto biglietto) {
        if (biglietto == null) return false;
        return biglietto.isValido();
    }

    // LISTA BIGLIETTI NON OBLITERATI
    public List<Biglietto> getBigliettiNonObliterati() {
        List<Biglietto> bigliettiTrovati = entityManager.createQuery("from Biglietto where obliterato is null", Biglietto.class).getResultList();
        if (bigliettiTrovati.isEmpty()) {
            System.out.println("Nessun biglietto non obliterato trovato");
        }
        return bigliettiTrovati;
    }

    // LISTA DI BIGLIETTI ANCORA VALIDI
    public List<Biglietto> getBigliettiValidi() {
        LocalDateTime limite = LocalDateTime.now().minusMinutes(90);
        List<Biglietto> bigliettiTrovati = entityManager.createQuery("from Biglietto where obliterato is null or obliterato > :limite", Biglietto.class).setParameter("limite", limite).getResultList();
        if (bigliettiTrovati.isEmpty()) {
            System.out.println("Nessun Biglietto valido trovato");
        }
        return bigliettiTrovati;
    }

    // LISTA DI BIGLIETTI SCADUTI
    public List<Biglietto> getBigliettiScaduti() {
        LocalDateTime limite = LocalDateTime.now().minusMinutes(90);
        List<Biglietto> bigliettiTrovati = entityManager.createQuery("from Biglietto where obliterato <= :limite", Biglietto.class).setParameter("limite", limite).getResultList();
        if (bigliettiTrovati.isEmpty()) {
            System.out.println("Nessun Biglietto scaduto trovato");
        }
        return bigliettiTrovati;
    }

    // METODO PER OTTENERE LISTA DI BIGLIETTI OBLITERATI IN UN PERIODO DI TEMPO


}
