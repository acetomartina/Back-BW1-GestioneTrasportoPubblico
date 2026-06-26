package acetomartina;

import acetomartina.DAO.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


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
        TitoloViaggioDao titoloViaggioDao = new TitoloViaggioDao(entityManager);
        TrattaDao trattaDao = new TrattaDao(entityManager);
        UtenteDao utenteDAO = new UtenteDao(entityManager, tesseraDao, abbonamentoDAO, puntoEmissioneDao);

        System.out.println("Benvenuto nella gestione del trosporto pubblico");
        System.out.println("1. Accedi come Utente semplice.");
        System.out.println("2. Accedi come Amministratore.");
        System.out.println("0. Esci dall'applicazione");

        int scelta = 0;
        boolean sceltaValida = false;

        do {
            try {
                System.out.println("Scegli un numero.");
                scelta = Integer.parseInt(scanner.nextLine());
                if (scelta == 0) break;
                if (scelta == 1 || scelta == 2) {
                    sceltaValida = true;
                } else {
                    System.err.println("valore non valido! Inserisci un numero da 1 a 2 ");
                }
            } catch (Exception e) {
                System.err.println("Scelta non valida. Verifica di nuovo il menù.");
            }
        }
        while (!sceltaValida);

        switch (scelta) {
            case 1 -> utenteDAO.scannerUtente1();
            case 2 -> amminstratoreDAO.scannerAmministratore();
        }
        System.out.println("Grazie per aver utilizzato il nostro sistema! Arrivederci!");
    }


}




