package acetomartina.DAO;

import acetomartina.Exceptions.NonTrovatoEccezzione;
import acetomartina.entities.*;
import acetomartina.enums.StatoMezzo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    //CERCO PER ID

    public Mezzo findMezzoById(UUID id) {
        Mezzo fromDB = this.entityManager.find(Mezzo.class, id);
        if (fromDB == null) throw new RuntimeException(" mezzo non trovato nel database.");
        return fromDB;
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

    // AGGIUNGE MANUTANZIONE
    public void aggiungiManutenzione(Manutenzione manutenzione, Mezzo mezzo) {
        try {
            mezzo.setManutenzioni(manutenzione);
            System.out.println("Manutenzione aggiunta!");
        } catch (NonTrovatoEccezzione e) {
            throw new RuntimeException("Errore nella programazione della manutenzione!");
        } catch (Exception e) {
            throw new RuntimeException("Errore eccezzionale!", e);
        }
    }

    //AGGIUNGE UNA CORSA AL MEZZO
    public void aggiungiCorsa(Corsa corsa, Mezzo mezzo) {
        try {
            mezzo.setCorse(corsa);
            System.out.println("Corsa aggiunta!");
        } catch (NonTrovatoEccezzione e) {
            throw new RuntimeException("Errore nell'aggiunta della corsa!");
        } catch (Exception e) {
            throw new RuntimeException("Errore eccezzionale!", e);
        }
    }

    // DATO UN MEZZO DEVE TORNARE I PERIODI DI ATTIVITA O MANTENZIONE
    public List<IntervalloDate> getPeriodiAttivita(Mezzo mezzo) {
        List<Manutenzione> listaManutenzioni = mezzo.getManutenzioni();

        if (listaManutenzioni == null || listaManutenzioni.isEmpty()) {
            System.out.println("Il mezzo non ha ancora alcuna effettuato nessuna manutanzione");
            return List.of();
        }

        List<Manutenzione> manutenzioniOrdinate = listaManutenzioni.stream().sorted(Comparator.comparing(Manutenzione::getDataInizio)).toList();

        List<IntervalloDate> periodiOperativi = new ArrayList<>();

        LocalDate inizioPrimo = mezzo.getDataEmissione();
        LocalDate finePrimo = manutenzioniOrdinate.get(0).getDataInizio().minusDays(1);
        if (!inizioPrimo.isAfter(finePrimo)) {
            periodiOperativi.add(new IntervalloDate(inizioPrimo, finePrimo));
        }


        for (int i = 0; i < manutenzioniOrdinate.size() - 1; i++) {
            LocalDate fineManutenzioneCorrente = manutenzioniOrdinate.get(i).getDataFine();
            LocalDate inizioManutenzioneSuccessiva = manutenzioniOrdinate.get(i + 1).getDataInizio();

            LocalDate inizioOperativo = fineManutenzioneCorrente.plusDays(1);
            LocalDate fineOperativo = inizioManutenzioneSuccessiva.minusDays(1);

            if (!inizioOperativo.isAfter(fineOperativo)) {
                periodiOperativi.add(new IntervalloDate(inizioOperativo, fineOperativo));
            }
        }

        LocalDate ultimaManutenzione = manutenzioniOrdinate.getLast().getDataFine().plusDays(1);
        if (!ultimaManutenzione.isAfter(LocalDate.now())) {
            periodiOperativi.add(new IntervalloDate(ultimaManutenzione, LocalDate.now()));
        }
        return periodiOperativi;
    }


    // METODO PER TROVARE TUTTI I BIGLIETTI OBLITERATI SU UN MEZZO
    public int getBigliettiObliterati(Mezzo mezzo) {
        List<Biglietto> bigliettiTrovati = entityManager.createQuery("from Biglietto where corsa.mezzo.mezzo_di_trasporto = :mezzoID and obliterato is not null", Biglietto.class).setParameter("mezzoID", mezzo.getMezzo_di_trasporto()).getResultList();
        return bigliettiTrovati.size();
    }

    // NUMERO DI VOLTE CHE UN MEZZO PERCORRE UNA TRATTA
    // STAMPA PER OGNI TRATTA IL TEMPO DI PERCORRENZA EFFETTIVO

    public String getDurataCorsa (Corsa corsa){
        Duration durataCorsa = Duration.between( corsa.getPartenza(),corsa.getArrivoEffettivo());
        long ore = durataCorsa.toHours();
        long minuti = durataCorsa.toMinutesPart();
        return "%02d:%02d".formatted(ore, minuti);
    }
    public void getNumeroCorsePercorse(Mezzo mezzo) {
        List<Corsa> corseTrovate = entityManager.createQuery(
                        "SELECT c FROM Corsa c WHERE c.arrivoEffettivo < :params AND c.mezzo = :mezzo",
                        Corsa.class)
                .setParameter("params", LocalDateTime.now())
                .setParameter("mezzo", mezzo)
                .getResultList();

        System.out.println("Le corse trovate sono : " + corseTrovate.size());
        corseTrovate.forEach(corsa -> System.out.println("La durata della corsa è stata : " + getDurataCorsa(corsa)));
    }











}
