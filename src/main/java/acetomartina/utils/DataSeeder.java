package acetomartina.utils;

import acetomartina.DAO.*;
import acetomartina.entities.*;
import acetomartina.enums.TipoUtente;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class DataSeeder {


    private EntityManager entityManager;

    public static void riempiUtenti (UtenteDao utenteDao, CarloDAO carloDAO){
        List<Utente> utenti = new ArrayList<>();
        if (!carloDAO.isTableEmpty(Utente.class)) {
            return;
        }

        //UTENTI

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


        for (Utente utente : utenti){
            utenteDao.save(utente);
        }
        System.out.println("Riempimento UTENTI avvenuto con successo");

    }
}



