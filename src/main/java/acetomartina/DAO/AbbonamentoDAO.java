package acetomartina.DAO;

import acetomartina.entities.Abbonamento;
import acetomartina.entities.Tessera;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class AbbonamentoDAO {
    private EntityManager entityManager;

    public AbbonamentoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //VERIFICA VALIDITA
    public boolean verificaValidita(Abbonamento abbonamento) {
        if (abbonamento == null) return false;
        return abbonamento.isValido();
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
