package entities;

import enom.TipoBiglietto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "biaglietti")
public class Biglietto extends TitoloViaggio {

    private static int counter = 0;

    @Column(name = "tipo_biglietto")
    private TipoBiglietto tipoBiglietto;

    @Column(name = "data_partenza")
    private LocalDate dataPartenza;

    @Column(name = "orario-Partenza")
    private LocalTime orarioPartenza;

    @Column(name = "Validità", nullable = false)
    private boolean validita;


    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, TipoBiglietto tipoBiglietto , LocalDate dataPartenza , LocalTime orarioPartenza) {
        super(dataEmissione);
        this.tipoBiglietto = tipoBiglietto;
        this.dataPartenza = dataPartenza;
        this.orarioPartenza = orarioPartenza;
        if (LocalDate.now().isBefore(dataPartenza)){

        }
    }


}
