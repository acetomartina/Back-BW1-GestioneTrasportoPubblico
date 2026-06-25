package acetomartina;

import acetomartina.DAO.*;
import acetomartina.enums.*;
import acetomartina.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestione-trasporto-pubblico-pu");

    static EntityManager entityManager = entityManagerFactory.createEntityManager();



    public static <T> void salvaLista(List<T> lista) {
        entityManager.getTransaction().begin();
         lista.forEach(entity -> entityManager.persist(entity));
         entityManager.getTransaction().commit();
    }

    public static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        //DAO

        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(entityManager);
        AmminstratoreDAO amminstratoreDAO = new AmminstratoreDAO(entityManager);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(entityManager);
        CorsaDao corsaDao = new CorsaDao(entityManager);
        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(entityManager);
        MezzoDao mezzoDao = new MezzoDao(entityManager);
        PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(entityManager);
        TesseraDao tesseraDao = new TesseraDao(entityManager);
        TitoloViaggioDao TitoloViaggioDao = new TitoloViaggioDao(entityManager);
        TrattaDao trattaDao = new TrattaDao(entityManager);
        UtenteDao utenteDAO = new UtenteDao(entityManager, tesseraDao,abbonamentoDAO);

        System.out.println("Benvenuto nella gestione del trosporto pubblico");
        System.out.println("\n--- MENU PRINCIPALE ---");
        System.out.println("1. Accedi come UTENTE SEMPLICE");
        System.out.println("2. Accedi come AMMINISTRATORE");
        System.out.println("0. Esci dall'applicazione");

        int scelta = 0;
        boolean sceltaValida= false;

       do{
           try {
               System.out.println("Scegli un numero.");
               scelta = Integer.parseInt(scanner.nextLine());
               sceltaValida = true;
           } catch (Exception e) {
               System.err.println("Scelta non valida. Verifica di nuovo il menù.");
           }
       }
       while(!sceltaValida);

       switch (scelta){
           case 1 -> utenteDAO.scannerUtente1();
       }



        PuntoEmissione pe1 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, true);
        PuntoEmissione pe2 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, false);
        PuntoEmissione pe3 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, true);
        PuntoEmissione pe4 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, false); // Fuori servizio
        PuntoEmissione pe5 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        PuntoEmissione pe6 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, true);
        PuntoEmissione pe7 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, false);
        PuntoEmissione pe8 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        PuntoEmissione pe9 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, false); // Chiusa
        PuntoEmissione pe10 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        PuntoEmissione pe11 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, false);
        PuntoEmissione pe12 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        PuntoEmissione pe13 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        PuntoEmissione pe14 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, false);
        PuntoEmissione pe15 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, false);
        PuntoEmissione pe16 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        PuntoEmissione pe17 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, true);
        PuntoEmissione pe18 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        PuntoEmissione pe19 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, false);
        PuntoEmissione pe20 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, true);
//
//        puntoEmissioneDao.save(pe1);
//        puntoEmissioneDao.save(pe2);
//        puntoEmissioneDao.save(pe3);
//        puntoEmissioneDao.save(pe4);
//        puntoEmissioneDao.save(pe5);
//        puntoEmissioneDao.save(pe6);
//        puntoEmissioneDao.save(pe7);
//        puntoEmissioneDao.save(pe8);
//        puntoEmissioneDao.save(pe9);
//        puntoEmissioneDao.save(pe10);
//        puntoEmissioneDao.save(pe11);
//        puntoEmissioneDao.save(pe12);
//        puntoEmissioneDao.save(pe13);
//        puntoEmissioneDao.save(pe14);
//        puntoEmissioneDao.save(pe15);
//        puntoEmissioneDao.save(pe16);
//        puntoEmissioneDao.save(pe17);
//        puntoEmissioneDao.save(pe18);
//        puntoEmissioneDao.save(pe19);
//        puntoEmissioneDao.save(pe20);

        System.out.println("Punti emissione caricati!");


        Mezzo m1 = new Mezzo(LocalDate.of(2025, 1, 10), TipoMezzo.TRAM);
        Mezzo m2 = new Mezzo(LocalDate.of(2025, 1, 15), TipoMezzo.AUTOBUS);
        Mezzo m3 = new Mezzo(LocalDate.of(2025, 2, 20), TipoMezzo.TRAM);
        Mezzo m4 = new Mezzo(LocalDate.of(2025, 3, 5), TipoMezzo.AUTOBUS);
        Mezzo m5 = new Mezzo(LocalDate.of(2025, 3, 12), TipoMezzo.AUTOBUS);
        Mezzo m6 = new Mezzo(LocalDate.of(2025, 4, 18), TipoMezzo.TRAM);
        Mezzo m7 = new Mezzo(LocalDate.of(2025, 5, 22), TipoMezzo.TRAM);
        Mezzo m8 = new Mezzo(LocalDate.of(2025, 6, 30), TipoMezzo.AUTOBUS);
        Mezzo m9 = new Mezzo(LocalDate.of(2025, 7, 14), TipoMezzo.AUTOBUS);
        Mezzo m10 = new Mezzo(LocalDate.of(2025, 8, 5), TipoMezzo.TRAM);
        Mezzo m11 = new Mezzo(LocalDate.of(2025, 9, 20), TipoMezzo.AUTOBUS);
        Mezzo m12 = new Mezzo(LocalDate.of(2025, 10, 1), TipoMezzo.AUTOBUS);
        Mezzo m13 = new Mezzo(LocalDate.of(2025, 10, 15), TipoMezzo.TRAM);
        Mezzo m14 = new Mezzo(LocalDate.of(2025, 11, 2), TipoMezzo.TRAM);
        Mezzo m15 = new Mezzo(LocalDate.of(2025, 11, 28), TipoMezzo.AUTOBUS);
        Mezzo m16 = new Mezzo(LocalDate.of(2025, 12, 12), TipoMezzo.AUTOBUS);
        Mezzo m17 = new Mezzo(LocalDate.of(2026, 1, 5), TipoMezzo.TRAM);
        Mezzo m18 = new Mezzo(LocalDate.of(2026, 2, 10), TipoMezzo.TRAM);
        Mezzo m19 = new Mezzo(LocalDate.of(2026, 3, 22), TipoMezzo.AUTOBUS);
        Mezzo m20 = new Mezzo(LocalDate.of(2026, 4, 18), TipoMezzo.AUTOBUS);

//        mezzoDao.save(m1);
//        mezzoDao.save(m2);
//        mezzoDao.save(m3);
//        mezzoDao.save(m4);
//        mezzoDao.save(m5);
//        mezzoDao.save(m6);
//        mezzoDao.save(m7);
//        mezzoDao.save(m8);
//        mezzoDao.save(m9);
//        mezzoDao.save(m10);
//        mezzoDao.save(m11);
//        mezzoDao.save(m12);
//        mezzoDao.save(m13);
//        mezzoDao.save(m14);
//        mezzoDao.save(m15);
//        mezzoDao.save(m16);
//        mezzoDao.save(m17);
//        mezzoDao.save(m18);
//        mezzoDao.save(m19);
//      mezzoDao.save(m20);

        Tratta t1 = new Tratta("Stazione Termini", "Anagnina", Duration.ofMinutes(40));
        Tratta t2 = new Tratta("Laurentina", "Rebibbia", Duration.ofMinutes(50));
        Tratta t3 = new Tratta("Piazza Venezia", "Viterbo", Duration.ofHours(2));
        Tratta t4 = new Tratta("Flaminio", "Piazza del Popolo", Duration.ofMinutes(15));
        Tratta t5 = new Tratta("Milano Centrale", "Duomo", Duration.ofMinutes(10));
        Tratta t6 = new Tratta("Napoli Centrale", "Sorrento", Duration.ofMinutes(65));
        Tratta t7 = new Tratta("Venezia S. Lucia", "Mestre", Duration.ofMinutes(12));
        Tratta t8 = new Tratta("Firenze SMN", "Pisa Centrale", Duration.ofMinutes(50));
        Tratta t9 = new Tratta("Bologna Centrale", "San Lazzaro", Duration.ofMinutes(25));
        Tratta t10 = new Tratta("Torino Porta Nuova", "Lingotto", Duration.ofMinutes(15));
        Tratta t11 = new Tratta("Londra King's Cross", "Camden Town", Duration.ofMinutes(18));
        Tratta t12 = new Tratta("Parigi Gare du Nord", "Chatelet", Duration.ofMinutes(22));
        Tratta t13 = new Tratta("Berlino Alexanderplatz", "Spandau", Duration.ofMinutes(35));
        Tratta t14 = new Tratta("Vienna Hauptbahnhof", "Prater", Duration.ofMinutes(14));
        Tratta t15 = new Tratta("Barcellona Sants", "Sagrada Familia", Duration.ofMinutes(20));
        Tratta t16 = new Tratta("Madrid Atocha", "Sol", Duration.ofMinutes(12));
        Tratta t17 = new Tratta("Amsterdam Centraal", "Schiphol", Duration.ofMinutes(18));
        Tratta t18 = new Tratta("Bruxelles Midi", "Atomium", Duration.ofMinutes(30));
        Tratta t19 = new Tratta("Ginevra Cornavin", "Aeroporto", Duration.ofMinutes(10));
        Tratta t20 = new Tratta("Monaco Hauptbahnhof", "Marienplatz", Duration.ofMinutes(8));

//        trattaDao.save(t1);
//        trattaDao.save(t2);
//        trattaDao.save(t3);
//        trattaDao.save(t4);
//        trattaDao.save(t5);
//        trattaDao.save(t6);
//        trattaDao.save(t7);
//        trattaDao.save(t8);
//        trattaDao.save(t9);
//        trattaDao.save(t10);
//        trattaDao.save(t11);
//        trattaDao.save(t12);
//        trattaDao.save(t13);
//        trattaDao.save(t14);
//        trattaDao.save(t15);
//        trattaDao.save(t16);
//        trattaDao.save(t17);
//        trattaDao.save(t18);
//        trattaDao.save(t19);
//        trattaDao.save(t20);

        Corsa c1 = new Corsa(t1, m1, LocalDateTime.of(2026, 6, 24, 8, 0));
        Corsa c2 = new Corsa(t2, m2, LocalDateTime.of(2026, 6, 24, 8, 30));
        Corsa c3 = new Corsa(t3, m5, LocalDateTime.of(2026, 6, 24, 9, 0));
        Corsa c4 = new Corsa(t4, m3, LocalDateTime.of(2026, 6, 24, 9, 45));
        Corsa c5 = new Corsa(t5, m4, LocalDateTime.of(2026, 6, 24, 10, 15));
        Corsa c6 = new Corsa(t6, m8, LocalDateTime.of(2026, 6, 24, 11, 0));
        Corsa c7 = new Corsa(t7, m6, LocalDateTime.of(2026, 6, 24, 11, 30));
        Corsa c8 = new Corsa(t8, m2, LocalDateTime.of(2026, 6, 24, 12, 0));
        Corsa c9 = new Corsa(t9, m9, LocalDateTime.of(2026, 6, 24, 13, 15));
        Corsa c10 = new Corsa(t10, m10, LocalDateTime.of(2026, 6, 24, 14, 0));
        Corsa c11 = new Corsa(t11, m11, LocalDateTime.of(2026, 6, 24, 14, 45));
        Corsa c12 = new Corsa(t12, m12, LocalDateTime.of(2026, 6, 24, 15, 30));
        Corsa c13 = new Corsa(t13, m14, LocalDateTime.of(2026, 6, 24, 16, 0));
        Corsa c14 = new Corsa(t14, m13, LocalDateTime.of(2026, 6, 24, 16, 45));
        Corsa c15 = new Corsa(t15, m17, LocalDateTime.of(2026, 6, 24, 17, 15));
        Corsa c16 = new Corsa(t16, m15, LocalDateTime.of(2026, 6, 24, 18, 0));
        Corsa c17 = new Corsa(t17, m16, LocalDateTime.of(2026, 6, 24, 18, 30));
        Corsa c18 = new Corsa(t18, m19, LocalDateTime.of(2026, 6, 24, 19, 0));
        Corsa c19 = new Corsa(t19, m18, LocalDateTime.of(2026, 6, 24, 19, 45));
        Corsa c20 = new Corsa(t20, m20, LocalDateTime.of(2026, 6, 24, 20, 30));

//        corsaDao.save(c1);
//        corsaDao.save(c2);
//        corsaDao.save(c3);
//        corsaDao.save(c4);
//        corsaDao.save(c5);
//        corsaDao.save(c6);
//        corsaDao.save(c7);
//        corsaDao.save(c8);
//        corsaDao.save(c9);
//        corsaDao.save(c10);
//        corsaDao.save(c11);
//        corsaDao.save(c12);
//        corsaDao.save(c13);
//        corsaDao.save(c14);
//        corsaDao.save(c15);
//        corsaDao.save(c16);
//        corsaDao.save(c17);
//        corsaDao.save(c18);
//        corsaDao.save(c19);
//       corsaDao.save(c20);

        Utente u1 = new Utente(TipoUtente.AMMINISTRATORE, "Martina", "Aceto", LocalDate.of(1998, 4, 16), "Calabria");
        Utente u2 = new Utente(TipoUtente.UTENTE, "Naruto", "Uzumaki", LocalDate.of(1999, 10, 10), "Villaggio della Foglia");
        Utente u3 = new Utente(TipoUtente.UTENTE, "Luffy", "Monkey D.", LocalDate.of(1997, 5, 5), "Isola di Dawn");
        Utente u4 = new Utente(TipoUtente.UTENTE, "Light", "Yagami", LocalDate.of(1986, 2, 28), "Tokyo");
        Utente u5 = new Utente(TipoUtente.UTENTE, "L", "Lawliet", LocalDate.of(1979, 10, 3), "Winchester");
        Utente u6 = new Utente(TipoUtente.UTENTE, "Zoro", "Roronoa", LocalDate.of(1997, 11, 11), "Villaggio di Shimotsuki");
        Utente u7 = new Utente(TipoUtente.UTENTE, "Sasuke", "Uchiha", LocalDate.of(1999, 7, 23), "Villaggio della Foglia");
        Utente u8 = new Utente(TipoUtente.UTENTE, "Vegeta", "Principe", LocalDate.of(1984, 8, 14), "Pianeta Vegeta");
        Utente u9 = new Utente(TipoUtente.UTENTE, "Kakashi", "Hatake", LocalDate.of(1985, 9, 15), "Villaggio della Foglia");
        Utente u10 = new Utente(TipoUtente.UTENTE, "Killua", "Zoldyck", LocalDate.of(1999, 7, 7), "Montagna Kukuroo");
        Utente u11 = new Utente(TipoUtente.UTENTE, "Gon", "Freecss", LocalDate.of(1999, 5, 5), "Isola Balena");
        Utente u12 = new Utente(TipoUtente.UTENTE, "Saitama", "OnePunch", LocalDate.of(2015, 6, 12), "Città Z");
        Utente u13 = new Utente(TipoUtente.UTENTE, "Eren", "Jaeger", LocalDate.of(1993, 3, 30), "Distretto di Shiganshina");
        Utente u14 = new Utente(TipoUtente.UTENTE, "Mikasa", "Ackerman", LocalDate.of(1993, 2, 10), "Distretto di Shiganshina");
        Utente u15 = new Utente(TipoUtente.UTENTE, "Tanjiro", "Kamado", LocalDate.of(2016, 7, 14), "Montagna Kumotori");
        Utente u16 = new Utente(TipoUtente.UTENTE, "Nezuko", "Kamado", LocalDate.of(2016, 12, 28), "Montagna Kumotori");
        Utente u17 = new Utente(TipoUtente.UTENTE, "Satoru", "Gojo", LocalDate.of(1989, 12, 7), "Kyoto");
        Utente u18 = new Utente(TipoUtente.UTENTE, "Yuji", "Itadori", LocalDate.of(2003, 3, 20), "Sendai");
        Utente u19 = new Utente(TipoUtente.UTENTE, "Ken", "Kaneki", LocalDate.of(1995, 12, 20), "Tokyo");
        Utente u20 = new Utente(TipoUtente.UTENTE, "Izuku", "Midoriya", LocalDate.of(2014, 7, 15), "Shizuoka");

//        utenteDAO.save(u1);
//        utenteDAO.save(u2);
//        utenteDAO.save(u3);
//        utenteDAO.save(u4);
//        utenteDAO.save(u5);
//        utenteDAO.save(u6);
//        utenteDAO.save(u7);
//        utenteDAO.save(u8);
//        utenteDAO.save(u9);
//        utenteDAO.save(u10);
//        utenteDAO.save(u11);
//        utenteDAO.save(u12);
//        utenteDAO.save(u13);
//        utenteDAO.save(u14);
//        utenteDAO.save(u15);
//        utenteDAO.save(u16);
//        utenteDAO.save(u17);
//        utenteDAO.save(u18);
//        utenteDAO.save(u19);
//        utenteDAO.save(u20);



        Tessera ts1 = new Tessera(LocalDate.of(2026, 1, 5), u2, true);
        Tessera ts2 = new Tessera(LocalDate.of(2026, 1, 12), u3, true);
        Tessera ts3 = new Tessera(LocalDate.of(2026, 1, 20), u4, true);
        Tessera ts4 = new Tessera(LocalDate.of(2026, 2, 2), u5, true);
        Tessera ts5 = new Tessera(LocalDate.of(2026, 2, 15), u6, true);
        Tessera ts6 = new Tessera(LocalDate.of(2026, 2, 28), u7, true);
        Tessera ts7 = new Tessera(LocalDate.of(2026, 3, 5), u8, true);
        Tessera ts8 = new Tessera(LocalDate.of(2026, 3, 19), u9, true);
        Tessera ts9 = new Tessera(LocalDate.of(2026, 3, 25), u10, true);
        Tessera ts10 = new Tessera(LocalDate.of(2026, 4, 1), u11, true);
        Tessera ts11 = new Tessera(LocalDate.of(2026, 4, 10), u12, true);
        Tessera ts12 = new Tessera(LocalDate.of(2026, 4, 15), u13, true);
        Tessera ts13 = new Tessera(LocalDate.of(2026, 4, 22), u14, true);
        Tessera ts14 = new Tessera(LocalDate.of(2026, 5, 2), u15, true);
        Tessera ts15 = new Tessera(LocalDate.of(2026, 5, 12), u16, true);
        Tessera ts16 = new Tessera(LocalDate.of(2026, 5, 20), u17, true);
        Tessera ts17 = new Tessera(LocalDate.of(2026, 6, 1), u18, true);
        Tessera ts18 = new Tessera(LocalDate.of(2026, 6, 10), u19, true);
        Tessera ts19 = new Tessera(LocalDate.of(2026, 6, 18), u20, true);

//        tesseraDao.save(ts1);
//        tesseraDao.save(ts2);
//        tesseraDao.save(ts3);
//        tesseraDao.save(ts4);
//        tesseraDao.save(ts5);
//        tesseraDao.save(ts6);
//        tesseraDao.save(ts7);
//        tesseraDao.save(ts8);
//        tesseraDao.save(ts9);
//        tesseraDao.save(ts10);
//        tesseraDao.save(ts11);
//        tesseraDao.save(ts12);
//        tesseraDao.save(ts13);
//        tesseraDao.save(ts14);
//        tesseraDao.save(ts15);
//        tesseraDao.save(ts16);
//        tesseraDao.save(ts17);
//        tesseraDao.save(ts18);
//        tesseraDao.save(ts19);

        Biglietto b1 = new Biglietto(LocalDate.of(2026, 6, 24), c1, pe1);
        Biglietto b2 = new Biglietto(LocalDate.of(2026, 6, 24), c2, pe2);
        Biglietto b3 = new Biglietto(LocalDate.of(2026, 6, 24), c3, pe3);
        Biglietto b4 = new Biglietto(LocalDate.of(2026, 6, 24), c4, pe5);
        Biglietto b5 = new Biglietto(LocalDate.of(2026, 6, 24), c5, pe7);
        Biglietto b6 = new Biglietto(LocalDate.of(2026, 6, 24), c6, pe8);
        Biglietto b7 = new Biglietto(LocalDate.of(2026, 6, 24), c7, pe10);
        Biglietto b8 = new Biglietto(LocalDate.of(2026, 6, 24), c8, pe11);
        Biglietto b9 = new Biglietto(LocalDate.of(2026, 6, 24), c9, pe12);
        Biglietto b10 = new Biglietto(LocalDate.of(2026, 6, 24), c10, pe13);
        Biglietto b11 = new Biglietto(LocalDate.of(2026, 6, 24), c11, pe14);
        Biglietto b12 = new Biglietto(LocalDate.of(2026, 6, 24), c12, pe16);
        Biglietto b13 = new Biglietto(LocalDate.of(2026, 6, 24), c13, pe17);
        Biglietto b14 = new Biglietto(LocalDate.of(2026, 6, 24), c14, pe18);
        Biglietto b15 = new Biglietto(LocalDate.of(2026, 6, 24), c15, pe19);
        Biglietto b16 = new Biglietto(LocalDate.of(2026, 6, 24), c16, pe20);
        Biglietto b17 = new Biglietto(LocalDate.of(2026, 6, 24), c17, pe1);
        Biglietto b18 = new Biglietto(LocalDate.of(2026, 6, 24), c18, pe2);
        Biglietto b19 = new Biglietto(LocalDate.of(2026, 6, 24), c19, pe5);
        Biglietto b20 = new Biglietto(LocalDate.of(2026, 6, 24), c20, pe7);

//        bigliettoDAO.save(b1);
//        bigliettoDAO.save(b2);
//        bigliettoDAO.save(b3);
//        bigliettoDAO.save(b4);
//        bigliettoDAO.save(b5);
//        bigliettoDAO.save(b6);
//        bigliettoDAO.save(b7);
//        bigliettoDAO.save(b8);
//        bigliettoDAO.save(b9);
//        bigliettoDAO.save(b10);
//        bigliettoDAO.save(b11);
//        bigliettoDAO.save(b12);
//        bigliettoDAO.save(b13);
//        bigliettoDAO.save(b14);
//        bigliettoDAO.save(b15);
//        bigliettoDAO.save(b16);
//        bigliettoDAO.save(b17);
//        bigliettoDAO.save(b18);
//        bigliettoDAO.save(b19);
//        bigliettoDAO.save(b20);

        Abbonamento abb1 = new Abbonamento(LocalDate.of(2026, 6, 1), pe1, ts1, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb2 = new Abbonamento(LocalDate.of(2026, 6, 2), pe2, ts2, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb3 = new Abbonamento(LocalDate.of(2026, 6, 3), pe3, ts3, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb4 = new Abbonamento(LocalDate.of(2026, 6, 4), pe5, ts4, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb5 = new Abbonamento(LocalDate.of(2026, 6, 5), pe7, ts5, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb6 = new Abbonamento(LocalDate.of(2026, 6, 6), pe8, ts6, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb7 = new Abbonamento(LocalDate.of(2026, 6, 7), pe10, ts7, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb8 = new Abbonamento(LocalDate.of(2026, 6, 8), pe11, ts8, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb9 = new Abbonamento(LocalDate.of(2026, 6, 9), pe12, ts9, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb10 = new Abbonamento(LocalDate.of(2026, 6, 10), pe13, ts10, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb11 = new Abbonamento(LocalDate.of(2026, 6, 11), pe14, ts11, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb12 = new Abbonamento(LocalDate.of(2026, 6, 12), pe16, ts12, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb13 = new Abbonamento(LocalDate.of(2026, 6, 13), pe17, ts13, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb14 = new Abbonamento(LocalDate.of(2026, 6, 14), pe18, ts14, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb15 = new Abbonamento(LocalDate.of(2026, 6, 15), pe19, ts15, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb16 = new Abbonamento(LocalDate.of(2026, 6, 16), pe20, ts16, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb17 = new Abbonamento(LocalDate.of(2026, 6, 17), pe1, ts17, PeriodicitaAbbonamento.MENSILE);
        Abbonamento abb18 = new Abbonamento(LocalDate.of(2026, 6, 18), pe2, ts18, PeriodicitaAbbonamento.SETTIMANALE);
        Abbonamento abb19 = new Abbonamento(LocalDate.of(2026, 6, 19), pe5, ts19, PeriodicitaAbbonamento.MENSILE);

//        abbonamentoDAO.save(abb1);
//        abbonamentoDAO.save(abb2);
//        abbonamentoDAO.save(abb3);
//        abbonamentoDAO.save(abb4);
//        abbonamentoDAO.save(abb5);
//        abbonamentoDAO.save(abb6);
//        abbonamentoDAO.save(abb7);
//        abbonamentoDAO.save(abb8);
//        abbonamentoDAO.save(abb9);
//        abbonamentoDAO.save(abb10);
//        abbonamentoDAO.save(abb11);
//        abbonamentoDAO.save(abb12);
//        abbonamentoDAO.save(abb13);
//        abbonamentoDAO.save(abb14);
//        abbonamentoDAO.save(abb15);
//        abbonamentoDAO.save(abb16);
//        abbonamentoDAO.save(abb17);
//        abbonamentoDAO.save(abb18);
//        abbonamentoDAO.save(abb19);

        Manutenzione mn1 = new Manutenzione(LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 15), m1, StatoManutenzione.CONCLUSA);
        Manutenzione mn2 = new Manutenzione(LocalDate.of(2026, 1, 20), LocalDate.of(2026, 1, 22), m2, StatoManutenzione.CONCLUSA);
        Manutenzione mn3 = new Manutenzione(LocalDate.of(2026, 2, 5), LocalDate.of(2026, 2, 10), m3, StatoManutenzione.CONCLUSA);
        Manutenzione mn4 = new Manutenzione(LocalDate.of(2026, 2, 18), LocalDate.of(2026, 2, 19), m4, StatoManutenzione.PROGRAMMATA);
        Manutenzione mn5 = new Manutenzione(LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 5), m5, StatoManutenzione.CONCLUSA);
        Manutenzione mn6 = new Manutenzione(LocalDate.of(2026, 3, 12), LocalDate.of(2026, 3, 15), m6, StatoManutenzione.CONCLUSA);
        Manutenzione mn7 = new Manutenzione(LocalDate.of(2026, 3, 20), LocalDate.of(2026, 3, 25), m7, StatoManutenzione.CONCLUSA);
        Manutenzione mn8 = new Manutenzione(LocalDate.of(2026, 4, 2), LocalDate.of(2026, 4, 4), m8, StatoManutenzione.PROGRAMMATA);
        Manutenzione mn9 = new Manutenzione(LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 12), m9, StatoManutenzione.CONCLUSA);
        Manutenzione mn10 = new Manutenzione(LocalDate.of(2026, 4, 20), LocalDate.of(2026, 4, 26), m10, StatoManutenzione.CONCLUSA);
        Manutenzione mn11 = new Manutenzione(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 3), m11, StatoManutenzione.CONCLUSA);
        Manutenzione mn12 = new Manutenzione(LocalDate.of(2026, 5, 10), LocalDate.of(2026, 5, 15), m12, StatoManutenzione.CONCLUSA);
        Manutenzione mn13 = new Manutenzione(LocalDate.of(2026, 5, 18), LocalDate.of(2026, 5, 20), m13, StatoManutenzione.PROGRAMMATA);
        Manutenzione mn14 = new Manutenzione(LocalDate.of(2026, 5, 25), LocalDate.of(2026, 5, 30), m14, StatoManutenzione.CONCLUSA);
        Manutenzione mn15 = new Manutenzione(LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 5), m15, StatoManutenzione.CONCLUSA);
        Manutenzione mn16 = new Manutenzione(LocalDate.of(2026, 6, 8), LocalDate.of(2026, 6, 10), m16, StatoManutenzione.CONCLUSA);
        Manutenzione mn17 = new Manutenzione(LocalDate.of(2026, 6, 12), LocalDate.of(2026, 6, 14), m17, StatoManutenzione.PROGRAMMATA);
        Manutenzione mn18 = new Manutenzione(LocalDate.of(2026, 6, 15), LocalDate.of(2026, 6, 18), m18, StatoManutenzione.CONCLUSA);
        Manutenzione mn19 = new Manutenzione(LocalDate.of(2026, 6, 22), LocalDate.of(2026, 6, 28), m19, StatoManutenzione.IN_CORSO);
        Manutenzione mn20 = new Manutenzione(LocalDate.of(2026, 6, 24), LocalDate.of(2026, 6, 30), m20, StatoManutenzione.IN_CORSO);

//        manutenzioneDAO.save(mn1);
//        manutenzioneDAO.save(mn2);
//        manutenzioneDAO.save(mn3);
//        manutenzioneDAO.save(mn4);
//        manutenzioneDAO.save(mn5);
//        manutenzioneDAO.save(mn6);
//        manutenzioneDAO.save(mn7);
//        manutenzioneDAO.save(mn8);
//        manutenzioneDAO.save(mn9);
//        manutenzioneDAO.save(mn10);
//        manutenzioneDAO.save(mn11);
//        manutenzioneDAO.save(mn12);
//        manutenzioneDAO.save(mn13);
//        manutenzioneDAO.save(mn14);
//        manutenzioneDAO.save(mn15);
//        manutenzioneDAO.save(mn16);
//        manutenzioneDAO.save(mn17);
//        manutenzioneDAO.save(mn18);
//        manutenzioneDAO.save(mn19);
//        manutenzioneDAO.save(mn20);








        System.out.println("Siamo connessi!");
    }


}




