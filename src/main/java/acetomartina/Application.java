package acetomartina;

import acetomartina.DAO.*;
import acetomartina.enom.TipoMezzo;
import acetomartina.enom.TipoPuntoEmissione;
import acetomartina.enom.TipoUtente;
import acetomartina.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestione-trasporto-pubblico-pu");

    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    //public static <T> void salvaLista(List<T> lista) {
    //    entityManager.getTransaction().begin();
    //   lista.forEach(entity -> entityManager.persist(entity));
    //    entityManager.getTransaction().commit();
    //}



    public static void main(String[] args) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        //DAO

        UtenteDao utenteDAO = new UtenteDao(entityManager);
        MezzoDao mezzoDao = new MezzoDao(entityManager);
        PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(entityManager);
        TesseraDao TesseraDao = new TesseraDao(entityManager);
        TitoloViaggioDao TitoloViaggioDao = new TitoloViaggioDao(entityManager);
        TrattaDao TrattaDao = new TrattaDao(entityManager);

        //TEST UTENTE!!!! OK
        //Utente alessia = new Utente(TipoUtente.UTENTE, "Alessia","Cotini", LocalDate.of(1997,12,29), "Roma");
        //utenteDAO.save(alessia);


        //TEST CORSA!!! OK

        //Mezzo autobus1 = new Mezzo(LocalDate.now().minusYears(1), TipoMezzo.AUTOBUS, true);
        //Mezzo autobus2 = new Mezzo(LocalDate.now().minusYears(3), TipoMezzo.AUTOBUS, true);
        //Mezzo autobus3 = new Mezzo(LocalDate.now().minusMonths(6), TipoMezzo.AUTOBUS, true);

        // 3 tram
        //Mezzo tram1 = new Mezzo(LocalDate.now().minusYears(2), TipoMezzo.TRAM, true);
        //Mezzo tram2 = new Mezzo(LocalDate.now().minusYears(5), TipoMezzo.TRAM, true);
        //Mezzo tram3 = new Mezzo(LocalDate.now().minusMonths(8), TipoMezzo.TRAM, true);

        //List<Mezzo> mezzi = new ArrayList<>(List.of(autobus1, autobus2, autobus3, tram1, tram2, tram3));
        //salvaLista(mezzi);

        // 5 tratte intercity (esempio bus/treno tra cittÃ  italiane)
        //Tratta tratta1 = new Tratta("Roma", "Napoli", Duration.ofHours(2));
        //Tratta tratta2 = new Tratta("Milano", "Torino", Duration.ofHours(1).plusMinutes(30));
        //Tratta tratta3 = new Tratta("Firenze", "Bologna", Duration.ofHours(1));
        //Tratta tratta4 = new Tratta("Bari", "Lecce", Duration.ofHours(1).plusMinutes(30));
        //Tratta tratta5 = new Tratta("Venezia", "Verona", Duration.ofHours(1).plusMinutes(15));

        // 5 tratte tram urbane (esempio rete tram di Roma)
        //Tratta trattaTram1 = new Tratta("Termini", "Trastevere", Duration.ofMinutes(20));
        //Tratta trattaTram2 = new Tratta("Piazza Risorgimento", "Piazza Mancini", Duration.ofMinutes(15));
        //Tratta trattaTram3 = new Tratta("Porta Maggiore", "Centocelle", Duration.ofMinutes(25));
        //Tratta trattaTram4 = new Tratta("Piazzale Flaminio", "Valle Giulia", Duration.ofMinutes(10));
        //Tratta trattaTram5 = new Tratta("Largo Argentina", "Casaletto", Duration.ofMinutes(30));

        //List<Tratta> lista = new ArrayList<>(List.of(tratta1, tratta2, tratta3, tratta4, tratta5, trattaTram1, trattaTram2, trattaTram3, trattaTram4, trattaTram5));
        //salvaLista(lista);

        // 10 corse nazionali, distribuite sui 3 autobus
        //Corsa corsa1 = new Corsa(tratta1, autobus1, LocalDateTime.now().plusDays(2));
        //Corsa corsa2 = new Corsa(tratta2, autobus2, LocalDateTime.now().plusDays(2).withHour(9).withMinute(30));
        //Corsa corsa3 = new Corsa(tratta3, autobus3, LocalDateTime.now().plusDays(2).withHour(11).withMinute(0));
        //Corsa corsa4 = new Corsa(tratta4, autobus1, LocalDateTime.now().plusDays(2).withHour(13).withMinute(15));
        //Corsa corsa5 = new Corsa(tratta5, autobus2, LocalDateTime.now().plusDays(2).withHour(15).withMinute(45));
        //Corsa corsa6 = new Corsa(tratta1, autobus3, LocalDateTime.now().plusDays(3).withHour(8).withMinute(0));
        //Corsa corsa7 = new Corsa(tratta2, autobus1, LocalDateTime.now().plusDays(3).withHour(10).withMinute(0));
        //Corsa corsa8 = new Corsa(tratta3, autobus2, LocalDateTime.now().plusDays(3).withHour(12).withMinute(30));
        //Corsa corsa9 = new Corsa(tratta4, autobus3, LocalDateTime.now().plusDays(4).withHour(7).withMinute(45));
        //Corsa corsa10 = new Corsa(tratta5, autobus1, LocalDateTime.now().plusDays(4).withHour(16).withMinute(0));

// 5 corse tram, distribuite sui 3 tram
        //Corsa corsaTram1 = new Corsa(trattaTram1, tram1, LocalDateTime.now().plusHours(1));
        //Corsa corsaTram2 = new Corsa(trattaTram2, tram2, LocalDateTime.now().plusHours(1).plusMinutes(20));
        //Corsa corsaTram3 = new Corsa(trattaTram3, tram3, LocalDateTime.now().plusHours(2));
        //Corsa corsaTram4 = new Corsa(trattaTram4, tram1, LocalDateTime.now().plusHours(2).plusMinutes(15));
        //Corsa corsaTram5 = new Corsa(trattaTram5, tram2, LocalDateTime.now().plusHours(3));

        //List<Corsa> listaCorse = new ArrayList<>(List.of(corsa1, corsa2, corsa3, corsa4, corsa5, corsa6, corsa7, corsa8, corsa9, corsa10, corsaTram1, corsaTram2, corsaTram3, corsaTram4, corsaTram5));
        //salvaLista(listaCorse);

        //PuntoEmissione puntoEmissione = new PuntoEmissione(TipoPuntoEmissione.DISTRIBUTORE, true);
        //puntoEmissioneDao.save(puntoEmissione);



        //Biglietto biglietto1 = new Biglietto(LocalDate.now(), corsa3, puntoEmissione);
        //Biglietto biglietto2 = new Biglietto(LocalDate.now().minusDays(2), corsa3,puntoEmissione);

        //List<Biglietto> listaCorsa3 = entityManager.createQuery("from Biglietto where corsa.id = :param", Biglietto.class).setParameter("param", corsa3.getId()).getResultList();

        //listaCorsa3.forEach(biglietto -> System.out.println(biglietto));






        System.out.println("Siamo connessi!");
    }
}




