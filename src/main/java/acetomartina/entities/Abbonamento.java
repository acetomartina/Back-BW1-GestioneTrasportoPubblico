package acetomartina.entities;

import acetomartina.enums.PeriodicitaAbbonamento;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento extends TitoloViaggio {

    private static int counter = 0;

    @Column(name = "numero_abbonamento")
    private String numero_abbonamento;

    @ManyToOne
    @JoinColumn(name = "numero_tessera")
    private Tessera tessera;

    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodicitaAbbonamento periodicita;

    @ManyToOne
    @JoinColumn(name = "tratta")
    private Tratta tratta;


    public Abbonamento() {
    }

    public Abbonamento(LocalDate dataEmissione, PuntoEmissione puntoEmissione, Tessera tessera_id, PeriodicitaAbbonamento periodicita) {
        super(dataEmissione, puntoEmissione);
        this.tessera = tessera_id;
        this.periodicita = periodicita;
        switch (periodicita) {
            case SETTIMANALE -> this.dataScadenza = dataEmissione.plusWeeks(1);
            case MENSILE -> this.dataScadenza = dataEmissione.plusMonths(1);
            default -> throw new IllegalArgumentException("Periodicità non gestita: " + periodicita);
        }
        counter++;
    }

    public boolean isValido() {
        return !LocalDate.now().isAfter(this.dataScadenza);
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

    public void setTratta(Tratta trattaSelezionata) {
        this.tratta = trattaSelezionata;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Abbonamento.counter = counter;
    }

    public String getNumero_abbonamento() {
        return numero_abbonamento;
    }

    public void setNumero_abbonamento(String numero_abbonamento) {
        this.numero_abbonamento = numero_abbonamento;
    }

    public Tessera getTessera_id() {
        return tessera;
    }

    public void setTessera_id(Tessera tessera_id) {
        this.tessera = tessera_id;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public PeriodicitaAbbonamento getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(PeriodicitaAbbonamento periodicita) {
        this.periodicita = periodicita;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }


    @Override
    public String toString() {
        return "Abbonamento{" +
                "numero_abbonamento='" + numero_abbonamento + '\'' +
                ", tessera_id=" + tessera +
                ", dataScadenza=" + dataScadenza +
                ", periodicita=" + periodicita +
                "} " + super.toString();
    }
}
