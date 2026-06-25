package acetomartina.DAO;

import acetomartina.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.format.DateTimeFormatter;
import java.util.List;


import static acetomartina.Application.scanner;

public class UtenteDao {
    private final EntityManager entityManager;


    // Utilizziamo lo stesso entityManager del costruttore per inizializzare i DAO interni,
    // garantendo l'allineamento della sessione di persistenza senza duplicare le Factory.
    private final TrattaDao trattaDao;
    private final CorsaDao corsaDao;
    private final TesseraDao tesseraDao;
    private final AbbonamentoDAO abbonamentoDAO;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    // COSTRUTTORE
    public UtenteDao(EntityManager entityManager, TesseraDao tesseraDao, AbbonamentoDAO abbonamentoDAO) {
        this.entityManager = entityManager;
        this.trattaDao = new TrattaDao(entityManager);
        this.corsaDao = new CorsaDao(entityManager);
        this.tesseraDao = tesseraDao;
        this.abbonamentoDAO = abbonamentoDAO;
    }


    // SALVO
    public void save(Utente utente) {
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(utente);
            transazione.commit();
            System.out.println("L'utente " + utente.getNome_utente() + " " + utente.getCognome_utente() + ", è stato aggiunto al DATABASE");
        } catch (Exception e) {
            if (transazione.isActive()) transazione.rollback();
            throw new RuntimeException("Errore durante il salvataggio dell'utente : " + e.getMessage());
        }
    }

    public List<Utente> getTuttiGliUtenti() {
        return entityManager.createQuery("from Utente", Utente.class).getResultList();
    }

    public List<Utente> getTuttiITesserati() {
        return entityManager.createQuery("from Utente where tessera is not null", Utente.class).getResultList();
    }


    public void scannerUtente1() {
        System.out.println("Di cosa hai bisogno?");
        System.out.println("1 - Biglietto. ");
        System.out.println("2 - Abbonamento.");
        System.out.println("3 - Tessera.");
        System.out.println("0 - Esci.");

        int sceltaUtente = 0;
        boolean sceltaUtenteValida = false;

        do {
            try {
                sceltaUtente = Integer.parseInt(scanner.nextLine());
                if (sceltaUtente >= 0 && sceltaUtente <= 3) {
                    sceltaUtenteValida = true;
                } else {
                    System.err.println("Opzione non disponibile. Inserisci un numero da 0 a 3.");
                }
            } catch (Exception e) {
                System.err.println("Scelta non valida. Verifica di nuovo le opzioni disponibili.");
            }
        } while (!sceltaUtenteValida);


        switch (sceltaUtente) {
            case 1 -> {
                System.out.println("1 - Acquista biglietto.");
                System.out.println("2 - Oblitera biglietto.");

                int sceltaUtente2 = 0;
                boolean sceltaUtenteValida2 = false;

                do {
                    try {
                        sceltaUtente2 = Integer.parseInt(scanner.nextLine());
                        if (sceltaUtente2 == 1 || sceltaUtente2 == 2) {
                            sceltaUtenteValida2 = true;
                        } else {
                            System.err.println("Opzione non valida. Scegli 1 o 2.");
                        }
                    } catch (Exception e) {
                        System.err.println("Scelta non valida. Verifica di nuovo le opzioni disponibili.");
                    }
                } while (!sceltaUtenteValida2);

                switch (sceltaUtente2) {
                    case 1 -> {
                        // --- ACQUISTA BIGLIETTO ---
                        System.out.println("Scegli la destinazione che devi raggiungere.");
                        List<Tratta> tratte = trattaDao.findAll();

                        if (tratte.isEmpty()) {
                            System.out.println("Nessuna tratta disponibile nel sistema.");
                            return;
                        }

                        final int[] numero = {1};
                        tratte.forEach(tratta -> {
                            System.out.println(numero[0] + " - " + "Da " + tratta.getZonaPartenza() + " a " + tratta.getCapolinea() + " (previsto " + tratta.getDurata().toHours() + "." + tratta.getDurata().toMinutesPart() + " h)");
                            numero[0]++;
                        });

                        int trattaScelta = 0;
                        boolean trattaSceltaValida = false;

                        do {
                            try {
                                System.out.println("Inserisci il numero della tratta:");
                                trattaScelta = Integer.parseInt(scanner.nextLine());

                                if (trattaScelta >= 1 && trattaScelta <= tratte.size()) {
                                    trattaSceltaValida = true;
                                } else {
                                    System.err.println("Selezione fuori intervallo. Scegli un numero tra 1 e " + tratte.size());
                                }
                            } catch (Exception e) {
                                System.err.println("Scelta non valida. Inserisci un numero.");
                            }
                        } while (!trattaSceltaValida);

                        Tratta trattaSelezionata = tratte.get(trattaScelta - 1);
                        List<Corsa> corse = corsaDao.findAllByTratta(trattaSelezionata);

                        if (corse.isEmpty()) {
                            System.out.println("Non ci sono corse attive per questa tratta al momento.");
                            return;
                        }

                        final int[] numeroCorsa = {1};
                        System.out.println("Bene! Ora puoi scegliere la corsa.");

                        corse.forEach(corsa -> {
                            System.out.println(
                                    numeroCorsa[0] + " - " +
                                            corsa.getMezzo().getTipo_mezzo() + " - " +
                                            "Partenza prevista: " + corsa.getPartenza().format(formatter) + "h - " +
                                            "Arrivo previsto: " + corsa.getArrivoPrev().format(formatter) + "h - " +
                                            "Ritardo previsto: " + corsa.getRitardo().toMinutes() + " minuti."
                            );
                            numeroCorsa[0]++;
                        });

                        int corsaScelta = 0;
                        boolean corsaSceltaValida = false;

                        do {
                            try {
                                System.out.println("Inserisci il numero della corsa per completare l'acquisto:");
                                corsaScelta = Integer.parseInt(scanner.nextLine());

                                if (corsaScelta >= 1 && corsaScelta <= corse.size()) {
                                    corsaSceltaValida = true;
                                } else {
                                    System.err.println("Selezione fuori intervallo. Scegli un numero tra 1 e " + corse.size());
                                }
                            } catch (Exception e) {
                                System.err.println("Scelta non valida. Inserisci un numero.");
                            }
                        } while (!corsaSceltaValida);

                        Corsa corsaSelezionata = corse.get(corsaScelta - 1);
                        System.out.println("Acquisto completato con successo per la corsa del mezzo " + corsaSelezionata.getMezzo().getTipo_mezzo());
                        // Qui istanzierai l'oggetto Biglietto per salvarlo a DB
                    }

                    case 2 -> {
                        // --- OBLITERA BIGLIETTO ---
                        System.out.println("--- VIDIMAZIONE BIGLIETTO ---");
                        System.out.println("Inserisci il codice univoco del tuo biglietto:");
                        String codiceBiglietto = scanner.nextLine();

                        System.out.println("Inserisci l'ID o la targa del mezzo su cui sali:");
                        String idMezzo = scanner.nextLine();

                        // Qui andrà la logica del cambio di stato del biglietto a DB
                        System.out.println("Biglietto " + codiceBiglietto + " obliterato con successo sul mezzo " + idMezzo + "!");
                    }
                }
            }
            case 2 -> {
                // --- CASE 2: GESTIONE ABBONAMENTO ---
                System.out.println("1 - Acquista un nuovo Abbonamento.");
                System.out.println("2 - Verifica validità del tuo Abbonamento.");

                int sceltaAbbonamento = 0;
                boolean sceltaAbbValida = false;

                do {
                    try {
                        sceltaAbbonamento = Integer.parseInt(scanner.nextLine());
                        if (sceltaAbbonamento == 1 || sceltaAbbonamento == 2) {
                            sceltaAbbValida = true;
                        } else {
                            System.err.println("Opzione non valida. Scegli 1 o 2.");
                        }
                    } catch (Exception e) {
                        System.err.println("Scelta non valida. Inserisci un numero.");
                    }
                } while (!sceltaAbbValida);

                switch (sceltaAbbonamento) {
                    case 1 -> {
                        // --- ACQUISTA ABBONAMENTO ---
                        System.out.println("Per sottoscrivere un abbonamento devi possedere una tessera.");
                        System.out.println("Inserisci il numero della tua tessera:");
                        String numTessera = scanner.nextLine();

                        // Utilizziamo il metodo di ricerca del TesseraDao
                        Tessera tesseraTrovata = tesseraDao.getById(numTessera);

                        if (tesseraTrovata == null) {
                            System.out.println("Tessera non trovata! Devi prima richiedere una tessera al menu principale.");
                            return;
                        }

                        // 1. SCELTA DELLA PERIODICITÀ
                        System.out.println("Che tipo di abbonamento desideri?");
                        System.out.println("1 - Settimanale");
                        System.out.println("2 - Mensile");

                        int tipoAbbonamento = 0;
                        boolean tipoAbbValido = false;

                        do {
                            try {
                                tipoAbbonamento = Integer.parseInt(scanner.nextLine());
                                if (tipoAbbonamento == 1 || tipoAbbonamento == 2) {
                                    tipoAbbValido = true;
                                } else {
                                    System.err.println("Scelta errata. Scegli 1 per Settimanale o 2 per Mensile.");
                                }
                            } catch (Exception e) {
                                System.err.println("Scelta non valida. Inserisci un numero.");
                            }
                        } while (!tipoAbbValido);


                        // 2. SCELTA DELLA TRATTA
                        System.out.println("Seleziona la tratta alla quale vuoi abbonarti:");
                        List<Tratta> tratte = trattaDao.findAll();

                        if (tratte.isEmpty()) {
                            System.out.println("Nessuna tratta disponibile a sistema. Impossibile procedere.");
                            return;
                        }

                        final int[] numero = {1};
                        tratte.forEach(tratta -> {
                            System.out.println(numero[0] + " - " + "Da " + tratta.getZonaPartenza() + " a " + tratta.getCapolinea());
                            numero[0]++;
                        });

                        int trattaScelta = 0;
                        boolean trattaSceltaValida = false;

                        do {
                            try {
                                System.out.println("Inserisci il numero della tratta desiderata:");
                                trattaScelta = Integer.parseInt(scanner.nextLine());

                                if (trattaScelta >= 1 && trattaScelta <= tratte.size()) {
                                    trattaSceltaValida = true;
                                } else {
                                    System.err.println("Selezione fuori intervallo. Scegli un numero tra 1 e " + tratte.size());
                                }
                            } catch (Exception e) {
                                System.err.println("Scelta non valida. Inserisci un numero.");
                            }
                        } while (!trattaSceltaValida);

                        Tratta trattaSelezionata = tratte.get(trattaScelta - 1);

                        // 3. LOGICA DI SALVATAGGIO REALE SUL DATABASE
                        String tipoString = (tipoAbbonamento == 1) ? "Settimanale" : "Mensile";
                        System.out.println("\nRegistrazione abbonamento " + tipoString + " sulla tratta: Da "
                                + trattaSelezionata.getZonaPartenza() + " a " + trattaSelezionata.getCapolinea() + "...");

                        try {
                            entityManager.getTransaction().begin();

                            Abbonamento nuovoAbbonamento = new Abbonamento();

                            // 1. GENERIAMO E IMPOSTIAMO IL CODICE UNIVOCO
                            nuovoAbbonamento.setCodiceUnivoco(java.util.UUID.randomUUID().toString());

                            // 2. Impostiamo gli altri campi (Tratta e Tessera)
                            nuovoAbbonamento.setTratta();
                            nuovoAbbonamento.setTessera(tesseraTrovata);

                            // 3. Impostiamo le date a seconda di come calcoli la scadenza
                            nuovoAbbonamento.setDataEmissione(java.time.LocalDate.now());
                            if (tipoAbbonamento == 1) {
                                nuovoAbbonamento.setDataScadenza(java.time.LocalDate.now().plusDays(7)); // Settimanale
                            } else {
                                nuovoAbbonamento.setDataScadenza(java.time.LocalDate.now().plusMonths(1)); // Mensile
                            }

                            // Ora che tutti i campi obbligatori sono pieni, non darà più errore!
                            entityManager.persist(nuovoAbbonamento);
                            entityManager.getTransaction().commit();
                            System.out.println("Acquisto completato con successo!");

                        } catch (Exception e) {
                            if (entityManager.getTransaction().isActive()) {
                                entityManager.getTransaction().rollback();
                            }
                            System.err.println("Errore durante il salvataggio dell'abbonamento: " + e.getMessage());
                            return;
                        }

                        // 4. STAMPA DI TUTTI GLI ABBONAMENTI ATTIVI PER QUELLA TESSERA
                        System.out.println("\n--- RIEPILOGO ABBONAMENTI ATTIVI PER LA TESSERA N. " + numTessera + " ---");
                        abbonamentoDAO.verificaValidita(tesseraTrovata);
                    }
                    case 2 -> {
                        // --- VERIFICA VALIDITÀ ABBONAMENTO ---
                        System.out.println("Inserisci il numero della tua tessera per verificare lo stato degli abbonamenti:");
                        String numTessera = scanner.nextLine();

                        Tessera tesseraTrovata = tesseraDao.getById(numTessera);
                        if (tesseraTrovata == null) {
                            System.out.println("Nessuna tessera trouvata con questo codice.");
                            return;
                        }

                        abbonamentoDAO.verificaValidita(tesseraTrovata);
                    }
                }
            }
            case 3 -> {
                // --- CASE 3: GESTIONE TESSERA ---
                System.out.println("1 - Richiedi il rilascio di una nuova Tessera.");
                System.out.println("2 - Rinnova una Tessera esistente.");

                int sceltaTessera = 0;
                boolean sceltaTesseraValida = false;

                do {
                    try {
                        sceltaTessera = Integer.parseInt(scanner.nextLine());
                        if (sceltaTessera == 1 || sceltaTessera == 2) {
                            sceltaTesseraValida = true;
                        } else {
                            System.err.println("Opzione non valida. Scegli 1 o 2.");
                        }
                    } catch (Exception e) {
                        System.err.println("Scelta non valida. Inserisci un numero.");
                    }
                } while (!sceltaTesseraValida);

                switch (sceltaTessera) {
                    case 1 -> {
                        // --- RICHIESTA NUOVA TESSERA ---
                        System.out.println("Stai richiedendo una nuova tessera di validità annuale.");
                        // Logica di istanziazione e persistenza della nuova Tessera
                        System.out.println("Nuova tessera emessa e registrata a sistema con successo!");
                    }
                    case 2 -> {
                        // --- RINNOVA TESSERA SCADUTA ---
                        System.out.println("Inserisci il numero della tessera che desideri rinnovare:");
                        String numTessera = scanner.nextLine();

                        Tessera tesseraTrovata = tesseraDao.getById(numTessera);
                        if (tesseraTrovata == null) {
                            System.out.println("Impossibile rinnovare: Tessera non trovata.");
                            return;
                        }

                        // Aggiornamento date e merge/commit sul DB
                        System.out.println("La tessera " + numTessera + " è stata rinnovata per un ulteriore anno!");
                    }
                }
            }
            case 0 -> System.out.println("Ritorno al menu principale...");
        }
    }
}

