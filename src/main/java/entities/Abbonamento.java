package entities;

import enom.PeriodicitàAbbonamento;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;

public class Abbonamento extends TitoloViaggio {

    private static int counter = 0;

    @OneToMany
    @JoinColumn(name = "")
    @Column(name = "numero_tessera_associata", nullable = false)
    private Tessera tessera_id;

    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;

    @Column(nullable = false)
    private PeriodicitàAbbonamento periodicita;

    @Column(name = "validità", nullable = false)
    private boolean valido;


    protected Abbonamento(){}

    public Abbonamento(){

    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Abbonamento.counter = counter;
    }
}
