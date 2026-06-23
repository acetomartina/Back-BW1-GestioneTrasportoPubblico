package acetomartina.entities;

import acetomartina.enom.PeriodicitàAbbonamento;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento extends TitoloViaggio {

    private static int counter = 0;

    @Column(name = "numero_abbonamento")
    private String numero_abbonamento;

    @OneToOne
    @JoinColumn(name = "numero_tessera")
    private Tessera tessera_id;

    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;

    @Column(nullable = false)
    private PeriodicitàAbbonamento periodicita;

    @Column(name = "validità", nullable = false)
    private boolean validità;


    protected Abbonamento(){}

    public Abbonamento(Tessera tessera_id,LocalDate dataScadenza,PeriodicitàAbbonamento periodicita,boolean validità){
        this.tessera_id= tessera_id;
        this.dataScadenza= dataScadenza;
        this.periodicita = periodicita;
        this.validità = validità;
        counter++;
    }
    @PrePersist
    public void generaNumeroAbbonamento() {
        if (this.numero_abbonamento == null) {
            SecureRandom random = new SecureRandom();
            long numeroCasuale = random.nextLong(900_000_000_000L);
            long ISBN = 100_000_000_000L + numeroCasuale;
            this.numero_abbonamento = ISBN + "AB";
        }
    }

    public String getNumero_abbonamento() {
        return numero_abbonamento;
    }

    public void setNumero_abbonamento(String numero_abbonamento) {
        this.numero_abbonamento = numero_abbonamento;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Abbonamento.counter = counter;
    }

    public Tessera getTessera_id() {
        return tessera_id;
    }

    public void setTessera_id(Tessera tessera_id) {
        this.tessera_id = tessera_id;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public PeriodicitàAbbonamento getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(PeriodicitàAbbonamento periodicita) {
        this.periodicita = periodicita;
    }

    public boolean isValidità() {
        return validità;
    }

    public void setValidità(boolean validità) {
        this.validità = validità;
    }
}
