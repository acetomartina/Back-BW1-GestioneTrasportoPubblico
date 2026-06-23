package acetomartina.entities;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Corse")
public class Corsa {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tratta_id", nullable = false)
    private Tratta tratta;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    @Column(nullable = false)
    private LocalDateTime partenza;

    @Column(nullable = false)
    private LocalDateTime arrivoPrev;

    @Column
    private Duration ritardo;

    @Column(nullable = false)
    private LocalDateTime arrivoEffettivo;

    @OneToMany(mappedBy = "corsa")
    private List<Biglietto> listaBiglietti;



    public Corsa() {
    }

    public Corsa(Tratta tratta, Mezzo mezzo, LocalDateTime partenza) {
        this.tratta = tratta;
        this.mezzo = mezzo;
        this.partenza = partenza;
        this.arrivoPrev = partenza.plus(tratta.getDurata());
        this.ritardo = Duration.ZERO;
        this.arrivoEffettivo = arrivoPrev.plus(this.ritardo);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public LocalDateTime getPartenza() {
        return partenza;
    }

    public void setPartenza(LocalDateTime partenza) {
        this.partenza = partenza;
    }

    public LocalDateTime getArrivoPrev() {
        return arrivoPrev;
    }

    public void setArrivoPrev(LocalDateTime arrivoPrev) {
        this.arrivoPrev = arrivoPrev;
    }

    public Duration getRitardo() {
        return ritardo;
    }

    public void setRitardo(int ritardo) {
        this.ritardo = Duration.ofMinutes(ritardo);
        this.arrivoEffettivo = this.arrivoEffettivo.plus(this.ritardo);
    }

    public LocalDateTime getArrivoEffettivo() {
        return arrivoEffettivo;
    }

    public void setArrivoEffettivo(LocalDateTime arrivoEffettivo) {
        this.arrivoEffettivo = arrivoEffettivo;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public void setRitardo(Duration ritardo) {
        this.ritardo = ritardo;
    }

    @Override
    public String toString() {
        return "Corsa{" +
                "id=" + id +
                ", tratta=" + tratta +
                ", mezzo=" + mezzo +
                ", partenza=" + partenza +
                ", arrivoPrev=" + arrivoPrev +
                ", ritardo=" + ritardo +
                ", arrivoEffettivo=" + arrivoEffettivo +
                '}';
    }
}
