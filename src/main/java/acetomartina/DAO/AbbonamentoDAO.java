package acetomartina.DAO;

import acetomartina.entities.Abbonamento;
import acetomartina.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

public class AbbonamentoDAO {
    private EntityManager entityManager;

    public AbbonamentoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Abbonamento abbonamento){
        {
            EntityTransaction transazione = this.entityManager.getTransaction();
            try {
                transazione.begin();
                this.entityManager.persist(abbonamento);
                transazione.commit();
                System.out.println("Abbonamento salvato.");
            } catch (Exception e) {
                if (transazione.isActive()) transazione.rollback();
                throw new RuntimeException("Errore durante il salvataggio dell'abbonamento.");
            }
        }
    }
    //VERIFICA VALIDITA
    public List<Abbonamento> verificaValidita(Tessera tessera) {
        List<Abbonamento> abbonamentiTrovati = entityManager.createQuery("from Abbonamento where tessera.tessera_id = :tesseraID", Abbonamento.class).setParameter("tesseraID", tessera.getNumeroTessera()).getResultList();
        abbonamentiTrovati.forEach(abbonamento -> {
            if (abbonamento.isValido())
                System.out.println("L'abbonamento " + abbonamento.getNumero_abbonamento() + " è ancora valido !");
            else
                System.out.println("L'abbonamento " + abbonamento.getNumero_abbonamento() + " è scaduto! Deve essere rinnovato !");
        });
        return abbonamentiTrovati;
    }

    public List<Abbonamento> getByTessera(Tessera tessera) {
        List<Abbonamento> abbonamentiTrovati = entityManager.createQuery("from Abbonamento where tessera.numeroTessera = :numeroTessera", Abbonamento.class).setParameter("numeroTessera", tessera.getNumeroTessera()).getResultList();
        if (abbonamentiTrovati.isEmpty()) {
            System.out.println("Nessun abbonamento trovato!");
        }
        return abbonamentiTrovati;
    }

    // TROVA TUTTI GLI ABBONAMENTI EMESSI PRIMA DI QUESTA DATA
    public List<Abbonamento> getByData(LocalDate dataMax) {
        List<Abbonamento> abbonamentiTrovati = entityManager.createQuery("from Abbonamento where dataEmissione < :dataMax", Abbonamento.class).setParameter("dataMax", dataMax).getResultList();
        if (abbonamentiTrovati.isEmpty()) {
            System.out.println("Nessun abbonamneto trovato");
        }
        return abbonamentiTrovati;
    }
}
