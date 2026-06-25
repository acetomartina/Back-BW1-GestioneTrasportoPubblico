package acetomartina.DAO;

import acetomartina.entities.Corsa;
import acetomartina.entities.Mezzo;
import jakarta.persistence.EntityManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AmminstratoreDAO {
    private EntityManager entityManager;

    public AmminstratoreDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    // METODO PER CALCOLARE TEMPO EFFETTIVO DI PERCORRENZA DATA UNA TRATTA , DA PARTE DI UN MEZZO

    public String getDurataCorsa (Corsa corsa){
        Duration durataCorsa = Duration.between( corsa.getPartenza(),corsa.getArrivoEffettivo());
        long ore = durataCorsa.toHours();
        long minuti = durataCorsa.toMinutesPart();
        return "%02d:%02d".formatted(ore, minuti);
    }

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
}
