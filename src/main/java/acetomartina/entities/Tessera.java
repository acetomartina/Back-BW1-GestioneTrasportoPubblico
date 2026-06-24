package acetomartina.entities;

import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tessere")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tessera_id;

    @Column(name = "numero_tessera",unique = true)
    private int numeroTessera;

    @PrePersist
    public void generaNumeroTessera(){
        if(this.numeroTessera == 0){
            SecureRandom random = new SecureRandom();
            this.numeroTessera = random.nextInt(900000) + 100000;
        }
    }

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza",nullable = false)
    private LocalDate dataScadenza;

    @OneToOne
    @JoinColumn(name = "utente_id", unique = true)
    private Utente utente;

    @OneToMany(mappedBy = "tessera_id")
    private List<Abbonamento> abbonamenti;

    @Column(name = "tessera_attiva")
    private boolean tessera_attiva;

    public Tessera(){

    }

    public Tessera(LocalDate dataEmissione, Utente utente, boolean tessera_attiva){
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataEmissione.plusYears(1);
        this.utente = utente;
        this.tessera_attiva = tessera_attiva;
    }

    public Long getId() {
        return tessera_id;
    }

    public int getNumeroTessera() {
        return numeroTessera;
    }

    public List<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setId(Long id) {
        this.tessera_id = id;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public boolean isTessera_attiva() {
        return tessera_attiva;
    }

    public void setTessera_attiva(boolean tessera_attiva) {
        this.tessera_attiva = tessera_attiva;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "tessera_id=" + tessera_id +
                ", numeroTessera=" + numeroTessera +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", utente=" + utente +
                ", abbonamenti=" + abbonamenti +
                ", tessera_attiva=" + tessera_attiva +
                '}';
    }
}
