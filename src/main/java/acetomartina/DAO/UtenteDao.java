package acetomartina.DAO;

import acetomartina.entities.*;
import acetomartina.enums.PeriodicitaAbbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static acetomartina.Application.scanner;

public class UtenteDao {
    private final EntityManager entityManager;

    private final TrattaDao trattaDao;
    private final CorsaDao corsaDao;
    private final TesseraDao tesseraDao;
    private final AbbonamentoDAO abbonamentoDAO;
    private final PuntoEmissioneDao puntoEmissioneDao;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    // COSTRUTTORE
    public UtenteDao(EntityManager entityManager, TesseraDao tesseraDao, AbbonamentoDAO abbonamentoDAO, PuntoEmissioneDao puntoEmissioneDao) {
        this.entityManager = entityManager;
        this.trattaDao = new TrattaDao(entityManager);
        this.corsaDao = new CorsaDao(entityManager);
        this.tesseraDao = tesseraDao;
        this.abbonamentoDAO = abbonamentoDAO;
        this.puntoEmissioneDao = puntoEmissioneDao;
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

    // recupero tutti gli utenti
    public List<Utente> getTuttiGliUtenti() {
        return entityManager.createQuery("from Utente", Utente.class).getResultList();
    }

    // recupero chi ha la tessera
    public List<Utente> getTuttiITesserati() {
        return entityManager.createQuery("from Utente where tessera is not null", Utente.class).getResultList();
    }


    public void scannerUtente1() {

        System.out.println("Di cosa hai bisogno?");
        System.out.println("1 - Macchinetta digitale.");
        System.out.println("2 - Rivenditore autorizzato più vicino.");

        int sceltaPuntoEmissione = 0;
        boolean sceltaPeValida = false;

        do{
            try {
                sceltaPuntoEmissione = Integer.parseInt(scanner.nextLine());
                if (sceltaPuntoEmissione == 1 || sceltaPuntoEmissione == 2) {
                    sceltaPeValida = true;
                } else System.out.println("Devi inserire un opzione valida.");
            } catch (Exception e) {
                System.err.println("Opzione non valida. Verifica di nuovo il menù.");
            }
        } while (!sceltaPeValida);

        switch (sceltaPuntoEmissione){

            case 1 -> {
                System.out.println("Bene! Benvenuto. Di cosa hai bisogno?");
                System.out.println("1 - Biglietto. ");
                System.out.println("2 - Abbonamento.");
                System.out.println("3 - Tessera.");
                System.out.println("0 - Esci.");
            }
            case 2 -> System.out.println("Bene! Puoi recarti dal rivenditore in Via Appia Nuova n.27.");

        }


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

                        System.out.println("Scegli la destinazione che devi raggiungere.");
                        List<Tratta> tratte = trattaDao.findAll();

                        if (tratte.isEmpty()) {
                            System.out.println("Nessuna tratta disponibile nel sistema.");
                            return;
                        }

                        final int[] numero = {1};
                        tratte.forEach(tratta -> {
                            System.out.println(numero[0] + " - " +
                                    "Da " + tratta.getZonaPartenza() +
                                    " a " + tratta.getCapolinea() +
                                    " (previsto " + tratta.getDurata().toHours() +
                                    "." + tratta.getDurata().toMinutesPart() + " h)");
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
                    }

                    case 2 -> {
                        // obliteriamo biglietto
                        System.out.println("Inserisci il codice univoco del tuo biglietto:");
                        String codiceBiglietto = scanner.nextLine();

                        System.out.println("Inserisci l'ID o la targa del mezzo su cui sali:");
                        String idMezzo = scanner.nextLine();

                        System.out.println("Biglietto " + codiceBiglietto + " obliterato con successo sul mezzo " + idMezzo + "!");
                    }
                }
            }
            case 2 -> {
                // gestiamo abbonamento
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
                        // acquista abbonamento
                        System.out.println("Per sottoscrivere un abbonamento devi possedere una tessera.");
                        System.out.println("Inserisci il numero della tua tessera:");
                        String numTessera = scanner.nextLine();

                        Tessera tesseraTrovata = tesseraDao.getById(numTessera);

                        if (tesseraTrovata == null) {
                            System.out.println("Tessera non trovata! Devi prima richiedere una tessera al menu principale.");
                            return;
                        }

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


                        // con tratte.get prendo tutte le tratte dal DB
                        // parto da -1 perché la lista parte da 0
                        Tratta trattaSelezionata = tratte.get(trattaScelta - 1);

                        String tipoString = (tipoAbbonamento == 1) ? "Settimanale" : "Mensile";
                        System.out.println("\nRegistrazione abbonamento " + tipoString + " sulla tratta: Da "
                                + trattaSelezionata.getZonaPartenza() + " a " + trattaSelezionata.getCapolinea() + ".");

                        try { entityManager.getTransaction().begin();

                            // salvo la scelta dell'utente dentro l'abbonamento che sto creando
                            // altrimenti l'abbonamento rimane senza tratta, la colonna sul DB prende null
                            // e va nel catch = errore
                            Abbonamento nuovoAbbonamento = new Abbonamento();

                            nuovoAbbonamento.setCodiceUnivoco(java.util.UUID.randomUUID().toString());

                            nuovoAbbonamento.setDataEmissione(java.time.LocalDate.now());

                            nuovoAbbonamento.setTessera(tesseraTrovata);

                            nuovoAbbonamento.setTratta(trattaSelezionata);

                            PuntoEmissione puntoEmissione = puntoEmissioneDao.getPunto_emissione();
                            nuovoAbbonamento.setPuntoEmissione(puntoEmissione);


                            if (tipoAbbonamento == 1) {
                                nuovoAbbonamento.setPeriodicita(PeriodicitaAbbonamento.SETTIMANALE);
                                nuovoAbbonamento.setDataScadenza(java.time.LocalDate.now().plusWeeks(1));
                            } else {
                                nuovoAbbonamento.setPeriodicita(PeriodicitaAbbonamento.MENSILE);

                                nuovoAbbonamento.setDataScadenza(java.time.LocalDate.now().plusMonths(1));
                            }

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

                        System.out.println("\nAbbonamenti attivi per la tessera: " + numTessera);
                        abbonamentoDAO.verificaValidita(tesseraTrovata);
                    }
                    case 2 -> {
                        System.out.println("Inserisci il numero della tua tessera per verificare lo stato degli abbonamenti:");
                        String numTessera = scanner.nextLine();

                        Tessera tesseraTrovata = tesseraDao.getById(numTessera);
                        if (tesseraTrovata == null) {
                            System.out.println("Nessuna tessera trovata con questo codice.");
                            return;
                        }

                        abbonamentoDAO.verificaValidita(tesseraTrovata);
                    }
                }
            }
            case 3 -> {
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
                        System.out.println("Inserisci nome utente:");
                        String nome = scanner.nextLine();

                        System.out.println("Inserisci cognome utente:");
                        String cognome = scanner.nextLine();

                        Utente nuovoUtente = new Utente();
                        nuovoUtente.setNome_utente(nome);
                        nuovoUtente.setCognome_utente(cognome);

                        Tessera nuovaTessera = new Tessera(java.time.LocalDate.now(), nuovoUtente, true);

                        try {
                            entityManager.getTransaction().begin();

                            entityManager.persist(nuovoUtente);
                            entityManager.persist(nuovaTessera);

                            entityManager.getTransaction().commit();

                            System.out.println("Tessera creata correttamente!");
                            System.out.println("Numero tessera: " + nuovaTessera.getNumeroTessera());
                            System.out.println("Valida fino al: " + nuovaTessera.getDataScadenza());

                        } catch (Exception e) {
                            if (entityManager.getTransaction().isActive()) {
                                entityManager.getTransaction().rollback();
                            } System.err.println("Errore durante la creazione della tessera: " + e.getMessage());
                        }
                    }
                    case 2 -> {

                        System.out.println("Inserisci il numero della tessera che desideri rinnovare:");
                        String numTessera = scanner.nextLine();

                        Tessera tesseraTrovata = tesseraDao.getById(numTessera);
                        if (tesseraTrovata == null) {
                            System.out.println("Impossibile rinnovare: Tessera non trovata.");
                            return;
                        }
                        System.out.println("La tessera " + numTessera + " è stata rinnovata per un ulteriore anno!");

                    }
                }
            }
            case 0 -> System.out.println("Ritorno al menu principale...");
        }
    }
}

