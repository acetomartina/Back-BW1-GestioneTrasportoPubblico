package acetomartina.entities;

import acetomartina.enums.StatoManutenzione;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "Manutenzioni")
public class Manutenzione {
    @Id
    @GeneratedValue
    private UUID id_Manutenzione;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    @Column(name = "durata")
    private Integer durata;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_manutenzione")
    private StatoManutenzione statoManutenzione;

    public Manutenzione() {
    }

    public Manutenzione(LocalDate dataInizio, LocalDate dataFine, Mezzo mezzo, StatoManutenzione statoManutenzione) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.mezzo = mezzo;
        this.durata = (int) ChronoUnit.DAYS.between(dataInizio, dataFine);
        this.statoManutenzione = statoManutenzione;
    }

    public UUID getId_Manutenzione() {
        return id_Manutenzione;
    }

    public void setId_Manutenzione(UUID id_Manutenzione) {
        this.id_Manutenzione = id_Manutenzione;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public StatoManutenzione getStatoManutenzione() {
        return statoManutenzione;
    }

    public void setStatoManutenzione(StatoManutenzione statoManutenzione) {
        this.statoManutenzione = statoManutenzione;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "id_Manutenzione=" + id_Manutenzione +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", mezzo=" + mezzo +
                ", durata=" + durata +
                ", statoManutenzione=" + statoManutenzione +
                '}';
    }
}
