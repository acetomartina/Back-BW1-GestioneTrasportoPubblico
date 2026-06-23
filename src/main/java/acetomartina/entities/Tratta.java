package acetomartina.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tratte")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "zona_partenza",nullable = false)
    private String zonaPartenza;

    @Column (name = "capolinea",nullable = false)
    private String capolinea;

    @Column(name = "tempo_previsto", nullable = false)
    private int tempoPrevisto; //minuti

    @Column(name = "data_percorrenza")
    private LocalDate dataPercorrenza;

    @Column(name = "orario_partenza", nullable = false)
    private LocalTime orarioPartenza;

    @Column(name = "orario_arrivo")
    private LocalTime orarioArrivo;

    @Column (name = "tempo_effettivo")
    private int tempoEffettivo; //minuti

    @Column(name = "numero_biglietti_obliterati")
    private int numeroBigliettiObliterati;

    @OneToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    public Tratta(){

    }

    public Tratta(String zonaPartenza, String capolinea, int tempoPrevisto, LocalDate dataPercorrenza, LocalTime orarioPartenza, LocalTime orarioArrivo, int tempoEffettivo, int numeroBigliettiObliterati, Mezzo mezzo){
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;
        this.dataPercorrenza = dataPercorrenza;
        this.orarioPartenza = orarioPartenza;
        this.orarioArrivo = orarioArrivo;
        this.tempoEffettivo = tempoEffettivo;
        this.numeroBigliettiObliterati = numeroBigliettiObliterati;
        this.mezzo = mezzo;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public int getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(int tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public LocalDate getDataPercorrenza() {
        return dataPercorrenza;
    }

    public void setDataPercorrenza(LocalDate dataPercorrenza) {
        this.dataPercorrenza = dataPercorrenza;
    }

    public LocalTime getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(LocalTime orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public LocalTime getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(LocalTime orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public int getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(int tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    public int getNumeroBigliettiObliterati() {
        return numeroBigliettiObliterati;
    }

    public void setNumeroBigliettiObliterati(int numeroBigliettiObliterati) {
        this.numeroBigliettiObliterati = numeroBigliettiObliterati;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPrevisto=" + tempoPrevisto + " min" +
                ", mezzo=" + (mezzo != null ? mezzo.getMezzo_di_trasporto() : "nessuno") +
                '}';
    }
}
