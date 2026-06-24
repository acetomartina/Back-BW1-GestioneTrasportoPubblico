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


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(entityManager);
        AmminstratoreDAO amminstratoreDAO = new AmminstratoreDAO(entityManager);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(entityManager);
        CorsaDao corsaDao = new CorsaDao(entityManager);
        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(entityManager);
        MezzoDao mezzoDao = new MezzoDao(entityManager);
        PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(entityManager);
        TesseraDao TesseraDao = new TesseraDao(entityManager);
        TitoloViaggioDao TitoloViaggioDao = new TitoloViaggioDao(entityManager);
        TrattaDao TrattaDao = new TrattaDao(entityManager);
        UtenteDao utenteDAO = new UtenteDao(entityManager);

        
        System.out.println("Siamo connessi!");
    }
}




