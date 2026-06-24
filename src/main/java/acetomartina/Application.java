package acetomartina;

import acetomartina.DAO.*;
import acetomartina.enums.*;
import acetomartina.entities.*;
import acetomartina.entities.Tessera;
import acetomartina.entities.Utente;
import acetomartina.enums.TipoMezzo;
import acetomartina.enums.TipoPuntoEmissione;
import acetomartina.enums.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import acetomartina.utils.DataSeeder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestione-trasporto-pubblico-pu");

    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static <T> void salvaLista(List<T> lista) {
        entityManager.getTransaction().begin();
        lista.forEach(entity -> entityManager.persist(entity));
        entityManager.getTransaction().commit();
    }


    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //DAO

        UtenteDao utenteDAO = new UtenteDao(entityManager);
        MezzoDao mezzoDao = new MezzoDao(entityManager);
        PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(entityManager);
        TesseraDao TesseraDao = new TesseraDao(entityManager);
        TitoloViaggioDao TitoloViaggioDao = new TitoloViaggioDao(entityManager);
        TrattaDao TrattaDao = new TrattaDao(entityManager);


        System.out.println("Distributori");
        puntoEmissioneDao.findByTipo(TipoPuntoEmissione.RIVENDITORE).forEach(System.out::println);


        //TEST UTENTE!!!! OK
        Utente alessia = new Utente(TipoUtente.UTENTE, "Alessia", "Cotini", LocalDate.of(1997, 12, 29), "Roma");
        utenteDAO.save(alessia);

        Tessera tessera = new Tessera(LocalDate.now(),
                alessia,
                true);

        puntoEmissioneDao.emettiTessera(tessera);
        System.out.println("Tessera emessa: ");
        System.out.println(tessera);

        puntoEmissioneDao.rinnovaTessera(tessera.getId());
        System.out.println("Tessera rinnovata: ");
        System.out.println(tessera);


//        TEST CORSA!!! OK


        Mezzo autobus1 = new Mezzo(LocalDate.now().minusDays(13), TipoMezzo.AUTOBUS);
        Mezzo autobus2 = new Mezzo(LocalDate.now().minusDays(25), TipoMezzo.AUTOBUS);
        Mezzo autobus3 = new Mezzo(LocalDate.now().minusDays(5), TipoMezzo.AUTOBUS);

//         3 tram
        Mezzo tram1 = new Mezzo(LocalDate.now().minusDays(45), TipoMezzo.TRAM);
        Mezzo tram2 = new Mezzo(LocalDate.now().minusDays(120), TipoMezzo.TRAM);
        Mezzo tram3 = new Mezzo(LocalDate.now().minusDays(75), TipoMezzo.TRAM);

        List<Mezzo> mezzi = new ArrayList<>(List.of(autobus1, autobus2, autobus3, tram1, tram2, tram3));
        salvaLista(mezzi);

//         5 tratte intercity (esempio bus/treno tra cittÃ  italiane)
        Tratta tratta1 = new Tratta("Roma", "Napoli", Duration.ofHours(2));
        Tratta tratta2 = new Tratta("Milano", "Torino", Duration.ofHours(1).plusMinutes(30));
        Tratta tratta3 = new Tratta("Firenze", "Bologna", Duration.ofHours(1));
        Tratta tratta4 = new Tratta("Bari", "Lecce", Duration.ofHours(1).plusMinutes(30));
        Tratta tratta5 = new Tratta("Venezia", "Verona", Duration.ofHours(1).plusMinutes(15));

//         5 tratte tram urbane (esempio rete tram di Roma)
        Tratta trattaTram1 = new Tratta("Termini", "Trastevere", Duration.ofMinutes(20));
        Tratta trattaTram2 = new Tratta("Piazza Risorgimento", "Piazza Mancini", Duration.ofMinutes(15));
        Tratta trattaTram3 = new Tratta("Porta Maggiore", "Centocelle", Duration.ofMinutes(25));
        Tratta trattaTram4 = new Tratta("Piazzale Flaminio", "Valle Giulia", Duration.ofMinutes(10));
        Tratta trattaTram5 = new Tratta("Largo Argentina", "Casaletto", Duration.ofMinutes(30));

        List<Tratta> lista = new ArrayList<>(List.of(tratta1, tratta2, tratta3, tratta4, tratta5, trattaTram1, trattaTram2, trattaTram3, trattaTram4, trattaTram5));
        salvaLista(lista);

//         10 corse nazionali, distribuite sui 3 autobus
        Corsa corsa1 = new Corsa(tratta1, autobus1, LocalDateTime.now().plusDays(2));
        Corsa corsa2 = new Corsa(tratta2, autobus2, LocalDateTime.now().plusDays(2).withHour(9).withMinute(30));
        Corsa corsa3 = new Corsa(tratta3, autobus3, LocalDateTime.now().plusDays(2).withHour(11).withMinute(0));
        Corsa corsa4 = new Corsa(tratta4, autobus1, LocalDateTime.now().plusDays(2).withHour(13).withMinute(15));
        Corsa corsa5 = new Corsa(tratta5, autobus2, LocalDateTime.now().plusDays(2).withHour(15).withMinute(45));
        Corsa corsa6 = new Corsa(tratta1, autobus3, LocalDateTime.now().plusDays(3).withHour(8).withMinute(0));
        Corsa corsa7 = new Corsa(tratta2, autobus1, LocalDateTime.now().plusDays(3).withHour(10).withMinute(0));
        Corsa corsa8 = new Corsa(tratta3, autobus2, LocalDateTime.now().plusDays(3).withHour(12).withMinute(30));
        Corsa corsa9 = new Corsa(tratta4, autobus3, LocalDateTime.now().plusDays(4).withHour(7).withMinute(45));
        Corsa corsa10 = new Corsa(tratta5, autobus1, LocalDateTime.now().plusDays(4).withHour(16).withMinute(0));

// 5 corse tram, distribuite sui 3 tram
        Corsa corsaTram1 = new Corsa(trattaTram1, tram1, LocalDateTime.now().plusHours(1));
        Corsa corsaTram2 = new Corsa(trattaTram2, tram2, LocalDateTime.now().plusHours(1).plusMinutes(20));
        Corsa corsaTram3 = new Corsa(trattaTram3, tram3, LocalDateTime.now().plusHours(2));
        Corsa corsaTram4 = new Corsa(trattaTram4, tram1, LocalDateTime.now().plusHours(2).plusMinutes(15));
        Corsa corsaTram5 = new Corsa(trattaTram5, tram2, LocalDateTime.now().plusHours(3));

        List<Corsa> listaCorse = new ArrayList<>(List.of(corsa1, corsa2, corsa3, corsa4, corsa5, corsa6, corsa7, corsa8, corsa9, corsa10, corsaTram1, corsaTram2, corsaTram3, corsaTram4, corsaTram5));
        salvaLista(listaCorse);


        PuntoEmissione puntoEmissione1 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        PuntoEmissione puntoEmissione2 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, false);
        PuntoEmissione puntoEmissione3 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, true);
        PuntoEmissione puntoEmissione4 = new PuntoEmissione(TipoPuntoEmissione.RIVENDITORE, true);
        PuntoEmissione puntoEmissione5 = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, false);

        List<PuntoEmissione> puntiEmissione = new ArrayList<>();
        puntiEmissione.add(puntoEmissione1);
        puntiEmissione.add(puntoEmissione2);
        puntiEmissione.add(puntoEmissione3);
        puntiEmissione.add(puntoEmissione4);
        puntiEmissione.add(puntoEmissione5);

        salvaLista(puntiEmissione);

        Biglietto biglietto1 = new Biglietto(LocalDate.now(), corsa3, puntoEmissione1);
        Biglietto biglietto2 = new Biglietto(LocalDate.now().minusDays(2), corsa3, puntoEmissione2);
        Biglietto biglietto3 = new Biglietto(LocalDate.now().minusDays(1), corsa1, puntoEmissione3);
        Biglietto biglietto4 = new Biglietto(LocalDate.now().minusDays(5), corsa2, puntoEmissione1);
        Biglietto biglietto5 = new Biglietto(LocalDate.now().minusDays(3), corsa1, puntoEmissione4);
        Biglietto biglietto6 = new Biglietto(LocalDate.now().minusDays(7), corsa3, puntoEmissione5);
        Biglietto biglietto7 = new Biglietto(LocalDate.now().minusDays(10), corsa2, puntoEmissione2);
        Biglietto biglietto8 = new Biglietto(LocalDate.now().minusDays(4), corsa1, puntoEmissione1);
        Biglietto biglietto9 = new Biglietto(LocalDate.now().minusDays(6), corsa3, puntoEmissione3);
        Biglietto biglietto10 = new Biglietto(LocalDate.now().minusDays(8), corsa2, puntoEmissione4);
        Biglietto biglietto11 = new Biglietto(LocalDate.now().minusDays(12), corsa1, puntoEmissione5);
        Biglietto biglietto12 = new Biglietto(LocalDate.now().minusDays(9), corsa3, puntoEmissione1);
        Biglietto biglietto13 = new Biglietto(LocalDate.now().minusDays(15), corsa2, puntoEmissione2);
        Biglietto biglietto14 = new Biglietto(LocalDate.now().minusDays(11), corsa1, puntoEmissione3);
        Biglietto biglietto15 = new Biglietto(LocalDate.now().minusDays(14), corsa3, puntoEmissione4);
        Biglietto biglietto16 = new Biglietto(LocalDate.now().minusDays(20), corsa2, puntoEmissione5);
        Biglietto biglietto17 = new Biglietto(LocalDate.now().minusDays(18), corsa1, puntoEmissione1);
        Biglietto biglietto18 = new Biglietto(LocalDate.now().minusDays(13), corsa3, puntoEmissione2);
        Biglietto biglietto19 = new Biglietto(LocalDate.now().minusDays(16), corsa2, puntoEmissione3);
        Biglietto biglietto20 = new Biglietto(LocalDate.now().minusDays(25), corsa1, puntoEmissione4);

        List<Biglietto> biglietti = new ArrayList<>();
        biglietti.add(biglietto1);
        biglietti.add(biglietto2);
        biglietti.add(biglietto3);
        biglietti.add(biglietto4);
        biglietti.add(biglietto5);
        biglietti.add(biglietto6);
        biglietti.add(biglietto7);
        biglietti.add(biglietto8);
        biglietti.add(biglietto9);
        biglietti.add(biglietto10);
        biglietti.add(biglietto11);
        biglietti.add(biglietto12);
        biglietti.add(biglietto13);
        biglietti.add(biglietto14);
        biglietti.add(biglietto15);
        biglietti.add(biglietto16);
        biglietti.add(biglietto17);
        biglietti.add(biglietto18);
        biglietti.add(biglietto19);
        biglietti.add(biglietto20);

        salvaLista(biglietti);


        Manutenzione manutenzione1 = new Manutenzione(LocalDate.now().plusDays(20), LocalDate.now().plusDays(27), autobus1, acetomartina.enom.StatoManutenzione.PROGRAMMATA);
        Manutenzione manutenzione2 = new Manutenzione(LocalDate.now().minusDays(5), LocalDate.now().plusDays(2), autobus2, acetomartina.enom.StatoManutenzione.IN_CORSO);
        Manutenzione manutenzione3 = new Manutenzione(LocalDate.now().minusDays(30), LocalDate.now().minusDays(25), autobus3, acetomartina.enom.StatoManutenzione.ESEGUITA);
        Manutenzione manutenzione4 = new Manutenzione(LocalDate.now().plusDays(10), LocalDate.now().plusDays(15), autobus1, acetomartina.enom.StatoManutenzione.PROGRAMMATA);
        Manutenzione manutenzione5 = new Manutenzione(LocalDate.now().minusDays(60), LocalDate.now().minusDays(52), autobus3, acetomartina.enom.StatoManutenzione.ESEGUITA);
        Manutenzione manutenzione6 = new Manutenzione(LocalDate.now().minusDays(3), LocalDate.now().plusDays(4), tram2, acetomartina.enom.StatoManutenzione.IN_CORSO);
        Manutenzione manutenzione7 = new Manutenzione(LocalDate.now().plusDays(30), LocalDate.now().plusDays(38), tram1, acetomartina.enom.StatoManutenzione.PROGRAMMATA);
        Manutenzione manutenzione8 = new Manutenzione(LocalDate.now().minusDays(45), LocalDate.now().minusDays(40), tram3, acetomartina.enom.StatoManutenzione.ESEGUITA);
        Manutenzione manutenzione9 = new Manutenzione(LocalDate.now().plusDays(5), LocalDate.now().plusDays(9), tram1, acetomartina.enom.StatoManutenzione.PROGRAMMATA);
        Manutenzione manutenzione10 = new Manutenzione(LocalDate.now().minusDays(15), LocalDate.now().minusDays(10), tram3, acetomartina.enom.StatoManutenzione.ESEGUITA);

        List<Manutenzione> manutenzioni = new ArrayList<>();
        manutenzioni.add(manutenzione1);
        manutenzioni.add(manutenzione2);
        manutenzioni.add(manutenzione3);
        manutenzioni.add(manutenzione4);
        manutenzioni.add(manutenzione5);
        manutenzioni.add(manutenzione6);
        manutenzioni.add(manutenzione7);
        manutenzioni.add(manutenzione8);
        manutenzioni.add(manutenzione9);
        manutenzioni.add(manutenzione10);

        salvaLista(manutenzioni);

//        BigliettoDAO bigliettoDAO = new BigliettoDAO(entityManager);
//        bigliettoDAO.obliteraBiglietto(biglietto3);
//        bigliettoDAO.obliteraBiglietto(biglietto3);

        CorsaDao corsaDao = new CorsaDao(entityManager);
        Corsa corsaTrovata = corsaDao.getById(corsa6.getId());

//        Mezzo mezzoTrovato = mezzoDao.getById(UUID.fromString("d83c1fbb-ebae-4082-83bb-3095e9dfaba2"));

        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(entityManager);

        Manutenzione manutenzioneTrovata = manutenzioneDAO.getById(manutenzione3.getId_Manutenzione());

        //FUNZIONA
        //puntoEmissioneDao.emettiESalvaBiglietto(puntoEmissione2, corsaTrovata);

        //FUNZIONA
//        mezzoDao.aggiungiCorsa(corsaTrovata, mezzoTrovato);

        // FUNZIONA
//        mezzoDao.aggiungiManutenzione(manutenzioneTrovata, mezzoTrovato);

        BigliettoDAO bigliettoDAO = new BigliettoDAO(entityManager);

        bigliettoDAO.obliteraBiglietto(biglietto5);

        bigliettoDAO.obliteraBiglietto(biglietto4);
        bigliettoDAO.obliteraBiglietto(biglietto3);


        int obliterati = mezzoDao.getBigliettiObliterati(autobus1);

        System.out.println("biglietti obliterati " + obliterati);

        Corsa corsa11 = new Corsa(tratta1, autobus3, LocalDateTime.now().minusDays(2).withHour(8).withMinute(0));
        Corsa corsa12 = new Corsa(tratta2, autobus1, LocalDateTime.now().minusDays(5).withHour(11).withMinute(0));
        Corsa corsa13 = new Corsa(tratta3, autobus2, LocalDateTime.now().minusDays(6).withHour(17).withMinute(30));
        Corsa corsa14 = new Corsa(tratta4, autobus3, LocalDateTime.now().minusDays(9).withHour(7).withMinute(48));
        Corsa corsa15 = new Corsa(tratta5, autobus1, LocalDateTime.now().minusDays(8).withHour(19).withMinute(0));

        corsaDao.save(corsa11);
        corsaDao.save(corsa12);
        corsaDao.save(corsa13);
        corsaDao.save(corsa14);
        corsaDao.save(corsa15);


        mezzoDao.getNumeroCorsePercorse(autobus1);
        corsa15.setRitardo(30);
        mezzoDao.getNumeroCorsePercorse(autobus1);

        //DAJE*2

        AmminstratoreDAO amminstratoreDAO = new AmminstratoreDAO(entityManager);
        amminstratoreDAO.getNumeroCorsePercorsePiuMedia(autobus1);



        System.out.println("Siamo connessi!");
    }
}




