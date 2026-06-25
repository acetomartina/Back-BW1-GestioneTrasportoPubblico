package acetomartina.utils;

import acetomartina.DAO.*;
import acetomartina.entities.*;
import acetomartina.enums.TipoMezzo;
import acetomartina.enums.TipoPuntoEmissione;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class DataSeeder {

    public  void popolaDatabase(
            UtenteDao utenteDao,
            MezzoDao mezzoDao,
            PuntoEmissioneDao puntoEmissioneDao,
            TesseraDao tesseraDao,
            TitoloViaggioDao titoloViaggioDao,
            TrattaDao trattaDao,
            CorsaDao corsaDao,
            AbbonamentoDAO abbonamentoDAO,
            AmminstratoreDAO amminstratoreDAO,
            BigliettoDAO bigliettoDAO,
            ManutenzioneDAO manutenzioneDAO) {

        popolaPuntiEmissione(puntoEmissioneDao);
        popolaMezzi(mezzoDao);
        popolaTratte(trattaDao);
        popolaCorse(corsaDao, trattaDao, mezzoDao);
    }


    public void popolaPuntiEmissione(PuntoEmissioneDao puntoEmissioneDao) {

        if (!puntoEmissioneDao.findAll().isEmpty()) {
            System.out.println("Punti emissioni già presenti.");
            return;
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

        puntoEmissioneDao.save(pe1);
        puntoEmissioneDao.save(pe2);
        puntoEmissioneDao.save(pe3);
        puntoEmissioneDao.save(pe4);
        puntoEmissioneDao.save(pe5);
        puntoEmissioneDao.save(pe6);
        puntoEmissioneDao.save(pe7);
        puntoEmissioneDao.save(pe8);
        puntoEmissioneDao.save(pe9);
        puntoEmissioneDao.save(pe10);
        puntoEmissioneDao.save(pe11);
        puntoEmissioneDao.save(pe12);
        puntoEmissioneDao.save(pe13);
        puntoEmissioneDao.save(pe14);
        puntoEmissioneDao.save(pe15);
        puntoEmissioneDao.save(pe16);
        puntoEmissioneDao.save(pe17);
        puntoEmissioneDao.save(pe18);
        puntoEmissioneDao.save(pe19);
        puntoEmissioneDao.save(pe20);


        System.out.println("Punti emissione caricati!");


    }

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

    public void popolaMezzi(MezzoDao mezzoDao) {

        if (!mezzoDao.findAll().isEmpty()) {
            System.out.println("Mezzi già presenti.");
            return;
        }



        mezzoDao.save(m1);
        mezzoDao.save(m2);
        mezzoDao.save(m3);
        mezzoDao.save(m4);
        mezzoDao.save(m5);
        mezzoDao.save(m6);
        mezzoDao.save(m7);
        mezzoDao.save(m8);
        mezzoDao.save(m9);
        mezzoDao.save(m10);
        mezzoDao.save(m11);
        mezzoDao.save(m12);
        mezzoDao.save(m13);
        mezzoDao.save(m14);
        mezzoDao.save(m15);
        mezzoDao.save(m16);
        mezzoDao.save(m17);
        mezzoDao.save(m18);
        mezzoDao.save(m19);
        mezzoDao.save(m20);

        System.out.println("Mezzi caricati!");
    }

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

    public void popolaTratte(TrattaDao trattaDao) {

        if (!trattaDao.findAll().isEmpty()) {
            System.out.println("Tratte già presenti.");
            return;
        }
        trattaDao.save(t1);
        trattaDao.save(t2);
        trattaDao.save(t3);
        trattaDao.save(t4);
        trattaDao.save(t5);
        trattaDao.save(t6);
        trattaDao.save(t7);
        trattaDao.save(t8);
        trattaDao.save(t9);
        trattaDao.save(t10);
        trattaDao.save(t11);
        trattaDao.save(t12);
        trattaDao.save(t13);
        trattaDao.save(t14);
        trattaDao.save(t15);
        trattaDao.save(t16);
        trattaDao.save(t17);
        trattaDao.save(t18);
        trattaDao.save(t19);
        trattaDao.save(t20);


        System.out.println("Tratte caricate!");
    }

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

    public void popolaCorse(CorsaDao corsaDao, TrattaDao trattaDao, MezzoDao mezzoDao) {

        if(!corsaDao.findAll().isEmpty()){
            System.out.println("Corse già presenti.");
            return;
        }


        corsaDao.save(c1);
        corsaDao.save(c2);
        corsaDao.save(c3);
        corsaDao.save(c4);
        corsaDao.save(c5);
        corsaDao.save(c6);
        corsaDao.save(c7);
        corsaDao.save(c8);
        corsaDao.save(c9);
        corsaDao.save(c10);
        corsaDao.save(c11);
        corsaDao.save(c12);
        corsaDao.save(c13);
        corsaDao.save(c14);
        corsaDao.save(c15);
        corsaDao.save(c16);
        corsaDao.save(c17);
        corsaDao.save(c18);
        corsaDao.save(c19);
        corsaDao.save(c20);

        System.out.println("Corse salvate correttamente.");

    }




}



