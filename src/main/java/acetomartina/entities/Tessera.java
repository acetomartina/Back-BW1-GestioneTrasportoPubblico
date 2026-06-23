package acetomartina.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tessere")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_tessera",unique = true)
    private int numeroTessera;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza",nullable = false)
    private LocalDate dataScadenza;

    @OneToOne
    @JoinColumn(name = "utente_id", unique = true)
    private Utente utente;

    public Tessera(){

    }

    public Tessera(LocalDate dataEmissione, LocalDate dataScadenza, Utente utente){
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataEmissione.plusYears(1);
        this.utente = utente;
    }

    public Long getId() {
        return id;
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
        this.id = id;
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

    @Override
    public String toString(){
        return "Tessera{" +
                "id= " + id +
                ", dataEmissione= " + dataEmissione +
                ", dataScadenza= " + dataScadenza +
                "}" ;
    }
}
