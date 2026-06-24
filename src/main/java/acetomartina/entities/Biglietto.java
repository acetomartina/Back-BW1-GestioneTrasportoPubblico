package acetomartina.entities;

import acetomartina.enums.TipoBiglietto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "biglietti")
public class Biglietto extends TitoloViaggio {

    private static int counter = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_biglietto")
    private TipoBiglietto tipoBiglietto;

    @ManyToOne
    @JoinColumn(name = "corsa_id")
    private Corsa corsa;

    @Column(name = "obliterato")
    private LocalDateTime obliterato;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;


    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, Corsa corsa, PuntoEmissione puntoEmissione) {
        super(dataEmissione, puntoEmissione);
        this.tipoBiglietto = corsa.getMezzo().getTipo_mezzo().getTipoBiglietto();
        this.corsa = corsa;
        this.mezzo = corsa.getMezzo();
        counter++;
    }

    @Transient
    public boolean isValido() {
        if (this.obliterato == null) {
            return true;
        }
        LocalDateTime scadenza = this.obliterato.plusMinutes(90);
        return LocalDateTime.now().isBefore(scadenza);
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

    public Corsa getCorsa() {
        return corsa;
    }

    public void setCorsa(Corsa corsa) {
        this.corsa = corsa;
    }

    public LocalDateTime getObliterato() {
        return obliterato;
    }

    public void setObliterato(LocalDateTime obliterato) {
        this.obliterato = obliterato;
    }

    public Mezzo getMezzo_id() {
        return mezzo;
    }

    public void setMezzo_id(Mezzo mezzo_id) {
        this.mezzo = mezzo_id;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "tipoBiglietto=" + tipoBiglietto +
                ", corsa=" + corsa +
                ", obliterato=" + obliterato +
                ", mezzo_id=" + mezzo +
                "} " + super.toString();
    }
}

