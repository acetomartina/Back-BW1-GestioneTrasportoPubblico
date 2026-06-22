package entities;

import enom.TipoBiglietto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "biglietti")
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

    public Biglietto(LocalDate dataEmissione, TipoBiglietto tipoBiglietto, LocalDate dataPartenza, LocalTime orarioPartenza) {
        super(dataEmissione);
        this.tipoBiglietto = tipoBiglietto;
        this.dataPartenza = dataPartenza;
        this.orarioPartenza = orarioPartenza;
    }

    public boolean isValido() {
        if ((LocalDate.now().equals(dataPartenza) && LocalTime.now().isAfter(orarioPartenza))
                || LocalDate.now().isAfter(dataPartenza)) {
            return false;
        } else return true;
    }


    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Biglietto.counter = counter;
    }

    public TipoBiglietto getTipoBiglietto() {
        return tipoBiglietto;
    }

    public void setTipoBiglietto(TipoBiglietto tipoBiglietto) {
        this.tipoBiglietto = tipoBiglietto;
    }

    public LocalDate getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(LocalDate dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public LocalTime getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(LocalTime orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public boolean isValidita() {
        return validita;
    }

}

