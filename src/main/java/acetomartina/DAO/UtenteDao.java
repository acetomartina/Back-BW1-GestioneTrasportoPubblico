package acetomartina.DAO;

import acetomartina.entities.Corsa;
import acetomartina.entities.Tratta;
import acetomartina.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


import java.time.format.DateTimeFormatter;
import java.util.List;

import static acetomartina.Application.scanner;

public class UtenteDao {
    private final EntityManager entityManager;

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestione-trasporto-pubblico-pu");

    static EntityManager em2 = entityManagerFactory.createEntityManager();

    TrattaDao trattaDao = new TrattaDao(em2);
    CorsaDao corsaDao = new CorsaDao(em2);

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    //COSTRUTTORE
    public UtenteDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    // SALVO
    public void save(Utente utente) {
        EntityTransaction transazione = this.entityManager.getTransaction();
        try {
            transazione.begin();
            this.entityManager.persist(utente);
            transazione.commit();
            System.out.println("L'utente " + utente.getNome_utente() + " " + utente.getCognome_utente() + ", Ã¨ stato aggiunto al DATABASE");
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
                sceltaUtenteValida = true;
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
                        sceltaUtenteValida2 = true;
                    } catch (Exception e) {
                        System.err.println("Scelta non valida. Verifica di nuovo le opzioni disponibili.");
                    }
                } while (!sceltaUtenteValida2);

                switch (sceltaUtente2) {
                    case 1 -> {
                        System.out.println("Scegli la destinazione che devi raggiungere.");
                        List<Tratta> tratte = trattaDao.findAll();
                        final int[] numero = {1};

                        tratte.forEach(tratta -> {
                            System.out.println(numero[0] + " - " + "Da " + tratta.getZonaPartenza() + " a " + tratta.getCapolinea() + " (previsto " + tratta.getDurata().toHours() + "." + tratta.getDurata().toMinutesPart() + " h)");
                            numero[0]++;

                        });

                        int trattaScelta;
                        boolean trattaSceltaValida = false;

                        do {
                            try {
                                trattaScelta = Integer.parseInt(scanner.nextLine());
                                trattaSceltaValida = true;

                                List<Corsa> corse = corsaDao.findAllByTratta(tratte.get(trattaScelta));

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

                            } catch (Exception e) {
                                System.err.println("Scelta non valida.");
                            }
                        } while (!trattaSceltaValida);


                    }


                }
            }


        }
    }


}