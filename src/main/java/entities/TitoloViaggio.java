package entities;

import acetomartina.isbnGeneretor.ISBNGenerator;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TitoloViaggio {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "codice_univoco", unique = true, nullable = false)
    private String codiceUnivoco;


    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @OneToOne
    @JoinColumn(name = "punto_emissione_id", nullable = false)
    private PuntoEmissione puntoEmissione;


    public TitoloViaggio() {
    }

    public TitoloViaggio(LocalDate dataEmissione) {
        this.codiceUnivoco = ISBNGenerator.generateISBN13();
        this.dataEmissione = dataEmissione;
    }

    public UUID getId() {
        return id;
    }


    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

}
