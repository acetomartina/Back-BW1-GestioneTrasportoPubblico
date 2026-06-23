package acetomartina;

import acetomartina.DAO.*;
import acetomartina.enom.TipoUtente;
import acetomartina.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestione-trasporto-pubblico-pu");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //DAO

        UtenteDao utenteDAO = new UtenteDao(entityManager);
        MezzoDao mezzoDao = new MezzoDao(entityManager);
        PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(entityManager);
        TesseraDao TesseraDao = new TesseraDao(entityManager);
        TitoloViaggioDao TitoloViaggioDao = new TitoloViaggioDao(entityManager);
        TrattaDao TrattaDao = new TrattaDao(entityManager);

        //TEST UTENTE


        Utente alessia = new Utente(TipoUtente.UTENTE, "Alessia","Cotini", LocalDate.of(1997,12,29), "Roma");
        utenteDAO.save(alessia);






        System.out.println("Siamo connessi!");
    }
}




