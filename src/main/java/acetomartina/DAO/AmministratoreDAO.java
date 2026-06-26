package acetomartina.DAO;

import acetomartina.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AmministratoreDAO {
    private EntityManager entityManager;

    public AmministratoreDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestione-trasporto-pubblico-pu");
    static EntityManager em2 = entityManagerFactory.createEntityManager();

    BigliettoDAO bigliettoDAO = new BigliettoDAO(em2);
    TesseraDao tesseraDAO = new TesseraDao(em2);
    AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em2);
    UtenteDao utenteDao = new UtenteDao(em2, tesseraDAO, abbonamentoDAO, new PuntoEmissioneDao(em2));
    MezzoDao mezzoDao = new MezzoDao(em2);

    Scanner scanner = new Scanner(System.in);


    // METODO PER CALCOLARE TEMPO EFFETTIVO DI PERCORRENZA DATA UNA TRATTA , DA PARTE DI UN MEZZO
    public String getDurataCorsa(Corsa corsa) {
        Duration durataCorsa = Duration.between(corsa.getPartenza(), corsa.getArrivoEffettivo());
        long ore = durataCorsa.toHours();
        long minuti = durataCorsa.toMinutesPart();
        return "%02d:%02d".formatted(ore, minuti);
    }

    // METODO PER CALCOLARE IL NUMERO DELLE CORSE PERCORSE DA UN MEZZO PIU LA DURATA MEDIA DI ESSE
    public void getNumeroCorsePercorsePiuMedia(Mezzo mezzo) {
        List<Corsa> corseTrovate = entityManager.createQuery(
                        "SELECT c FROM Corsa c WHERE c.arrivoEffettivo < :params AND c.mezzo = :mezzo",
                        Corsa.class)
                .setParameter("params", LocalDateTime.now())
                .setParameter("mezzo", mezzo)
                .getResultList();
        System.out.println("Le corse trovate sono : " + corseTrovate.size());
        corseTrovate.forEach(corsa -> System.out.println("La durata della corsa è stata : " + getDurataCorsa(corsa)));
        if (!corseTrovate.isEmpty()) {
            double mediaMinuti = corseTrovate.stream()
                    .collect(Collectors.averagingLong(corsa -> {
                        return Duration.between(corsa.getPartenza(), corsa.getArrivoEffettivo()).toMinutes();
                    }));
            System.out.println("La media di percorrenza è: " + mediaMinuti + " minuti");
        } else {
            System.out.println("Nessuna corsa trovata, impossibile calcolare la media.");
        }
    }

    // PERMETTE DI SCEGLIERE UN MEZZO TRA TUTTI QUELLI DISPONIBILI
    public Mezzo sceltaMezzo() {
        List<Mezzo> tuttiImezzi = mezzoDao.findAll();
        if (tuttiImezzi.isEmpty()) {                       // <-- mancava
            System.out.println("Nessun mezzo disponibile a sistema.");
            return null;
        }
        System.out.println("Inserisci il numero del mezzo per ottenere l'informazione");
        for (int i = 0; i < tuttiImezzi.size(); i++) {
            Mezzo mezzo = tuttiImezzi.get(i);
            System.out.println(i + 1 + ")" + mezzo.getTipo_mezzo() + " " + mezzo.getNumero_mezzo());
        }
        int sceltaMezzo = 0;
        boolean sceltaMezzoValida = false;
        do {
            try {
                sceltaMezzo = Integer.parseInt(scanner.nextLine());
                if (sceltaMezzo <= tuttiImezzi.size() && sceltaMezzo >= 1) {
                    sceltaMezzoValida = true;
                } else {
                    System.err.println("Qualcosa è andato storto! Riprova");
                }
            } catch (Exception e) {
                System.err.println("Qualcosa è andato storto! Riprova");
            }
        } while (!sceltaMezzoValida);
        return tuttiImezzi.get(sceltaMezzo - 1);
    }

    // PERETTERE DI FAR INSERIRE ALL'UTENTE UNA DATA E NE RITORNA IL VALORE IN LOCALDATE 
    private LocalDate leggiData(String messaggio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;
        boolean valida = false;
        do {
            System.out.println(messaggio);
            try {
                data = LocalDate.parse(scanner.nextLine(), formatter);
                valida = true;
            } catch (DateTimeParseException e) {
                System.err.println("Data non valida! Usa il formato gg/mm/aaaa.");
            }
        } while (!valida);
        return data;
    }

    public void scannerAmministratore() {
        int scelta = -1;
        boolean sceltaValida = false;
        do {
            System.out.println("-------------------------------------------------");
            System.out.println("Cosa vuoi fare ? ");
            System.out.println("1) Vedi tutti i biglietti emessi ");
            System.out.println("2) Vedi tutti gli abbonamenti emessi ");
            System.out.println("3) Vedi tutti gli utenti");
            System.out.println("4) Vedi tutti gli utenti tesserati ");
            System.out.println("5) Vedi tempo di percorrenza effettivo di una tratta da un mezzo ");
            System.out.println("6) Vedi manutenzioni di un mezzo");
            System.out.println("7) Vedi i periodi di attività di un mezzo");
            System.out.println("8) Vedi tutti i biglietti obliterati su un mezzo");
            System.out.println("9) Vedi biglietti obliterati dato un periodo");
            System.out.println("0) Esci");

            do {
                try {
                    scelta = Integer.parseInt(scanner.nextLine());
                    if (scelta == 0) break;
                    if (scelta <= 9 && scelta >= 1) {
                        sceltaValida = true;
                    } else {
                        System.out.println("Inserisci un numedo da 1  a 5 !");
                    }
                } catch (Exception e) {
                    System.err.println("Opzione non valida ! Riprova !");
                }
            } while (!sceltaValida);

            switch (scelta) {
                case 1 -> {
                    List<Biglietto> bigliettiValidi = bigliettoDAO.getBigliettiValidi();
                    List<Biglietto> bigliettiScaduti = bigliettoDAO.getBigliettiScaduti();
                    bigliettiValidi.forEach(biglietto -> System.out.println(biglietto.getId() + " " + biglietto.getCorsa().getTratta().getZonaPartenza() + " - " + biglietto.getCorsa().getTratta().getCapolinea() + " Valido"));
                    bigliettiScaduti.forEach(biglietto -> System.out.println(biglietto.getId() + " " + biglietto.getCorsa().getTratta().getZonaPartenza() + " - " + biglietto.getCorsa().getTratta().getCapolinea() + " Scaduto"));
                }
                case 2 -> {
                    List<Abbonamento> abbonamentiEmessi = abbonamentoDAO.getByData(LocalDate.now());
                    abbonamentiEmessi.forEach(abbonamento -> System.out.println(abbonamento.getNumero_abbonamento() + " " + abbonamento.getPeriodicita() + (abbonamento.isValido() ? " Valido " : " Scaduto ")));
                }
                case 3 -> {
                    List<Utente> tuttiGliUtenti = utenteDao.getTuttiGliUtenti();
                    tuttiGliUtenti.forEach(utente -> System.out.println(utente.getNome_utente() + " " + utente.getCognome_utente() + " " + utente.getTipo_utente()));
                }
                case 4 -> {
                    List<Utente> tuttiITesserati = utenteDao.getTuttiITesserati();
                    tuttiITesserati.forEach(utente -> System.out.println(utente.getNome_utente() + " " + utente.getCognome_utente() + " " + utente.getTessera().getNumeroTessera()));
                }
                case 5 -> {
                    Mezzo mezzoScelto = sceltaMezzo();
                    getNumeroCorsePercorsePiuMedia(mezzoScelto);
                }
                case 6 -> {
                    Mezzo mezzoScelto = sceltaMezzo();
                    List<Manutenzione> manutenzioni = mezzoDao.getManutenzioniMezzo(mezzoScelto.getMezzo_di_trasporto());
                    manutenzioni.forEach(manutenzione -> System.out.println("Manutenzione :" + " Inizio : " + manutenzione.getDataInizio() + " - Fine : " + manutenzione.getDataFine() + " Tempo di inattività : " + manutenzione.getDurata() + " giorni"));
                }
                case 7 -> {
                    Mezzo mezzoScelto = sceltaMezzo();
                    List<IntervalloDate> periodiAttivita = mezzoDao.getPeriodiAttivita(mezzoScelto);
                    periodiAttivita.forEach(intervallo -> System.out.println("Il mezzo : " + mezzoScelto.getTipo_mezzo() + " " + mezzoScelto.getNumero_mezzo() + " ha circolato dal " + intervallo.inizio() + " al " + intervallo.fine()));
                }
                case 8 -> {
                    Mezzo mezzoScelto = sceltaMezzo();
                    List<Biglietto> bigliettiOblit = mezzoDao.getListaBigliettiOblitSuMezzo(mezzoScelto);
                    System.out.println("Sul mezz o" + mezzoScelto.getTipo_mezzo() + " " + mezzoScelto.getNumero_mezzo() + " sono stati obliterati : ");
                    bigliettiOblit.forEach(biglietto -> System.out.println(biglietto.getCodiceUnivoco() + "Data" + biglietto.getObliterato()));
                }
                case 9 -> {
                    LocalDate dataInizio = leggiData("Inserisci la prima data (gg/mm/aaaa):");
                    LocalDate dataFine = leggiData("Inserisci la data finale (gg/mm/aaaa):");
                    List<Biglietto> listaBiglietti = bigliettoDAO.getBigliettiObliteratiNelPeriodo(dataInizio, dataFine);
                    listaBiglietti.forEach(biglietto -> System.out.println(biglietto.getCodiceUnivoco() + "Data" + biglietto.getObliterato()));
                }
            }
        } while (scelta != 0);
    }
}
