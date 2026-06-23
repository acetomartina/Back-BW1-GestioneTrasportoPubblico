package acetomartina.entities;

import acetomartina.enom.TipoBiglietto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "biglietti")
public class Biglietto extends TitoloViaggio {

    private static int counter = 0;

    @Column(name = "tipo_biglietto")
    private TipoBiglietto tipoBiglietto;

    @ManyToOne
    @JoinColumn(name = "corsa_id")
    private Corsa corsa;

    @Column(name = "Validità", nullable = false)
    private boolean validita;

    @Column(name = "obliterato")
    private boolean obliterato;


    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, Corsa corsa, PuntoEmissione puntoEmissione) {
        super(dataEmissione, puntoEmissione);
        this.tipoBiglietto = corsa.getMezzo().getTipo_mezzo().getTipoBiglietto();
        this.corsa = corsa;
        counter++;
    }

    public boolean isObliterato() {
        return obliterato;
    }

    public void setObliterato(boolean obliterato) {
        this.obliterato = obliterato;
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

    @Override
    public String toString() {
        return "Biglietto{" +
                "tipoBiglietto=" + tipoBiglietto +
                ", corsa=" + corsa +
                ", validita=" + validita +
                ", obliterato=" + obliterato +
                '}';
    }
}

