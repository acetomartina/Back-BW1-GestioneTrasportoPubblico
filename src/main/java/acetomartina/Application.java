package acetomartina;

import acetomartina.isbnGeneretor.ISBNGenerator;
import enom.TipoBiglietto;
import entities.Biglietto;
import entities.Mezzo;
import entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestione-trasporto-pubblico-pu");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        System.out.println("Siamo connessi!");
    }
}




