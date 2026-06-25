//package acetomartina;
//
//import acetomartina.entities.*;
//import acetomartina.enums.*;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// * Popola il database con un set di dati realistico per i test.
// *
// * ORDINE DI PERSISTENZA (rispetta le foreign key per evitare l'errore
// * HHH90032003 "unsaved transient entity"):
// *   1. PuntoEmissione   (nessuna dipendenza)
// *   2. Tratta           (nessuna dipendenza)
// *   3. Mezzo            (nessuna dipendenza)
// *   4. Utente           (nessuna dipendenza)
// *   5. Corsa            -> Tratta + Mezzo
// *   6. Manutenzione     -> Mezzo
// *   7. Tessera          -> Utente
// *   8. Biglietto        -> Corsa + PuntoEmissione (+ Mezzo, derivato dalla Corsa)
// *   9. Abbonamento      -> PuntoEmissione + Tessera (+ Tratta)
// *
// * Tutto avviene in UNA singola transazione: ogni entità "padre" è già
// * gestita (managed) quando viene referenziata da un'entità "figlia",
// * quindi non esistono riferimenti transienti.
// */
//public class DataSeeder {
//
//    private static final Random random = new Random();
//
//    // --- Quantità ---
//    private static final int NUM_PUNTI_EMISSIONE = 10;
//    private static final int NUM_TRATTE          = 20;
//    private static final int NUM_MEZZI           = 30;
//    private static final int NUM_UTENTI          = 60;
//    private static final int NUM_TESSERATI       = 45;   // utenti che ricevono una tessera
//    private static final int NUM_CORSE           = 120;
//    private static final int NUM_MANUTENZIONI    = 35;
//    private static final int NUM_BIGLIETTI       = 500;
//
//    // --- Dati di base per generare valori plausibili ---
//    private static final String[] NOMI = {
//            "Mario", "Giulia", "Luca", "Francesca", "Marco", "Sofia", "Alessandro",
//            "Martina", "Giuseppe", "Anna", "Davide", "Chiara", "Matteo", "Sara",
//            "Andrea", "Elena", "Simone", "Valentina", "Federico", "Federica"
//    };
//    private static final String[] COGNOMI = {
//            "Rossi", "Bianchi", "Ferrari", "Esposito", "Russo", "Romano", "Colombo",
//            "Ricci", "Marino", "Greco", "Bruno", "Gallo", "Conti", "De Luca",
//            "Mancini", "Costa", "Giordano", "Rizzo", "Lombardi", "Moretti"
//    };
//    private static final String[] CITTA = {
//            "Roma", "Milano", "Napoli", "Torino", "Bologna", "Firenze",
//            "Bari", "Palermo", "Genova", "Venezia", "Verona", "Catania"
//    };
//    private static final String[] CAPOLINEA = {
//            "Centro", "Stazione Centrale", "Aeroporto", "Porto", "Università",
//            "Ospedale", "Fiera", "Stadio", "Periferia Nord", "Periferia Sud"
//    };
//
//    public static void main(String[] args) {
//        EntityManagerFactory emf =
//                Persistence.createEntityManagerFactory("gestione-trasporto-pubblico-pu");
//        EntityManager em = emf.createEntityManager();
//
//        try {
//            em.getTransaction().begin();
//            System.out.println("Inizio popolamento del database...");
//
//            // 1. PUNTI EMISSIONE
//            List<PuntoEmissione> puntiEmissione = new ArrayList<>();
//            for (int i = 0; i < NUM_PUNTI_EMISSIONE; i++) {
//                PuntoEmissione pe = new PuntoEmissione(
//                        randomEnum(TipoPuntoEmissione.values()),
//                        random.nextBoolean());
//                em.persist(pe);
//                puntiEmissione.add(pe);
//            }
//            System.out.println(puntiEmissione.size() + " punti emissione creati.");
//
//            // 2. TRATTE
//            List<Tratta> tratte = new ArrayList<>();
//            for (int i = 0; i < NUM_TRATTE; i++) {
//                String partenza = CITTA[random.nextInt(CITTA.length)];
//                String arrivo   = CAPOLINEA[random.nextInt(CAPOLINEA.length)];
//                Duration durata = Duration.ofMinutes(10 + random.nextInt(171)); // 10–180 min
//                Tratta t = new Tratta(partenza, arrivo, durata);
//                em.persist(t);
//                tratte.add(t);
//            }
//            System.out.println(tratte.size() + " tratte create.");
//
//            // 3. MEZZI
//            List<Mezzo> mezzi = new ArrayList<>();
//            for (int i = 0; i < NUM_MEZZI; i++) {
//                LocalDate dataImmatricolazione = LocalDate.now()
//                        .minusYears(random.nextInt(15))
//                        .minusDays(random.nextInt(365));
//                Mezzo m = new Mezzo(dataImmatricolazione, randomEnum(TipoMezzo.values()));
//                em.persist(m);
//                mezzi.add(m);
//            }
//            System.out.println(mezzi.size() + " mezzi creati.");
//
//            // 4. UTENTI
//            List<Utente> utenti = new ArrayList<>();
//            for (int i = 0; i < NUM_UTENTI; i++) {
//                Utente u = new Utente(
//                        randomEnum(TipoUtente.values()),
//                        NOMI[random.nextInt(NOMI.length)],
//                        COGNOMI[random.nextInt(COGNOMI.length)],
//                        LocalDate.now().minusYears(18 + random.nextInt(60)).minusDays(random.nextInt(365)),
//                        CITTA[random.nextInt(CITTA.length)]);
//                em.persist(u);
//                utenti.add(u);
//            }
//            System.out.println(utenti.size() + " utenti creati.");
//
//            // 5. CORSE
//            List<Corsa> corse = new ArrayList<>();
//            for (int i = 0; i < NUM_CORSE; i++) {
//                Tratta tratta = tratte.get(random.nextInt(tratte.size()));
//                Mezzo mezzo   = mezzi.get(random.nextInt(mezzi.size()));
//
//                // 80% corse passate (servono per la media di percorrenza), 20% future
//                LocalDateTime partenza;
//                if (random.nextInt(100) < 80) {
//                    partenza = LocalDateTime.now()
//                            .minusDays(1 + random.nextInt(90))
//                            .minusHours(random.nextInt(12));
//                } else {
//                    partenza = LocalDateTime.now()
//                            .plusDays(1 + random.nextInt(30))
//                            .plusHours(random.nextInt(12));
//                }
//
//                Corsa c = new Corsa(tratta, mezzo, partenza);
//                // ~30% delle corse hanno un ritardo (così arrivoEffettivo != arrivoPrev)
//                if (random.nextInt(100) < 30) {
//                    c.setRitardo(5 + random.nextInt(30)); // minuti
//                }
//                em.persist(c);
//                corse.add(c);
//            }
//            System.out.println(corse.size() + " corse create.");
//
//            // 6. MANUTENZIONI
//            for (int i = 0; i < NUM_MANUTENZIONI; i++) {
//                Mezzo mezzo = mezzi.get(random.nextInt(mezzi.size()));
//                LocalDate inizio = LocalDate.now().minusDays(random.nextInt(365));
//                LocalDate fine   = inizio.plusDays(1 + random.nextInt(14));
//                Manutenzione man = new Manutenzione(
//                        inizio, fine, mezzo, randomEnum(StatoManutenzione.values()));
//                em.persist(man);
//            }
//            System.out.println(NUM_MANUTENZIONI + " manutenzioni create.");
//
//            // 7. TESSERE (un sottoinsieme di utenti diventa tesserato)
//            List<Tessera> tessere = new ArrayList<>();
//            int tesserati = Math.min(NUM_TESSERATI, utenti.size());
//            for (int i = 0; i < tesserati; i++) {
//                Utente utente = utenti.get(i);
//                Tessera tessera = new Tessera(
//                        LocalDate.now().minusMonths(random.nextInt(11)),
//                        utente,
//                        true);
//                em.persist(tessera);
//                utente.setTessera(tessera); // coerenza del grafo in memoria
//                tessere.add(tessera);
//            }
//            System.out.println(tessere.size() + " tessere create.");
//
//            // 8. BIGLIETTI (500) con mix di validi e scaduti
//            int biglValidi = 0, biglScaduti = 0;
//            for (int i = 0; i < NUM_BIGLIETTI; i++) {
//                Corsa corsa       = corse.get(random.nextInt(corse.size()));
//                PuntoEmissione pe = puntiEmissione.get(random.nextInt(puntiEmissione.size()));
//                LocalDate dataEmissione = LocalDate.now().minusDays(random.nextInt(90));
//
//                Biglietto b = new Biglietto(dataEmissione, corsa, pe);
//
//                int caso = random.nextInt(3);
//                if (caso == 0) {
//                    // non obliterato -> valido (isValido() == true)
//                    biglValidi++;
//                } else if (caso == 1) {
//                    // obliterato da meno di 90 min -> ancora valido
//                    b.setObliterato(LocalDateTime.now().minusMinutes(random.nextInt(89)));
//                    biglValidi++;
//                } else {
//                    // obliterato da più di 90 min -> scaduto
//                    b.setObliterato(LocalDateTime.now().minusMinutes(91 + random.nextInt(5000)));
//                    biglScaduti++;
//                }
//                em.persist(b);
//            }
//            System.out.println(NUM_BIGLIETTI + " biglietti creati ("
//                    + biglValidi + " validi, " + biglScaduti + " scaduti).");
//
//            // 9. ABBONAMENTI (uno per tessera)
//            int abbonamentiCreati = 0;
//            for (int i = 0; i < tessere.size(); i++) {
//                Tessera tessera   = tessere.get(i);
//                PuntoEmissione pe = puntiEmissione.get(random.nextInt(puntiEmissione.size()));
//                PeriodicitaAbbonamento periodicita = random.nextBoolean()
//                        ? PeriodicitaAbbonamento.SETTIMANALE
//                        : PeriodicitaAbbonamento.MENSILE;
//
//                // 1 su 4 emesso oggi (utile se getByData filtra per la data odierna),
//                // gli altri distribuiti negli ultimi 40 giorni -> mix di validi/scaduti
//                LocalDate dataEmissione = (i % 4 == 0)
//                        ? LocalDate.now()
//                        : LocalDate.now().minusDays(1 + random.nextInt(40));
//
//                Abbonamento a = new Abbonamento(dataEmissione, pe, tessera, periodicita);
//                a.setTratta(tratte.get(random.nextInt(tratte.size())));
//                em.persist(a);
//                abbonamentiCreati++;
//            }
//            System.out.println(abbonamentiCreati + " abbonamenti creati.");
//
//            em.getTransaction().commit();
//            System.out.println("Popolamento completato con successo!");
//
//        } catch (Exception e) {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            System.err.println("Errore durante il popolamento, rollback eseguito.");
//            e.printStackTrace();
//        } finally {
//            em.close();
//            emf.close();
//        }
//    }
//
//    /** Restituisce un valore casuale tra le costanti di un enum, senza doverne conoscere i nomi. */
//    private static <T> T randomEnum(T[] values) {
//        return values[random.nextInt(values.length)];
//    }
//}

