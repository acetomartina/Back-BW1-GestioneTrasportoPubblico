package acetomartina.entities;

import acetomartina.enums.TipoPuntoEmissione;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "punto_emissione")
public class PuntoEmissione {

    @Id
    @Column(name= "punto_emissione")
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_punto_emissione")
    private TipoPuntoEmissione tipoPuntoEmissione;

    @Column(name = "in_attività")
    private boolean inAttivita;

    @OneToMany(mappedBy = "puntoEmissione")
    private List<TitoloViaggio> listaVendite = new ArrayList<>();

    protected PuntoEmissione(){}
    public PuntoEmissione(TipoPuntoEmissione tipo_punto_emissione,boolean in_attività){
        this.tipoPuntoEmissione = tipo_punto_emissione;
        this.inAttivita= in_attività;
    }

    public List<TitoloViaggio> getLista_vendite() {
        return listaVendite;
    }

    public UUID getPunto_emissione() {
        return id;
    }

    public TipoPuntoEmissione getTipo_punto_emissione() {
        return tipoPuntoEmissione;
    }

    public boolean isIn_attività() {
        return inAttivita;
    }

    public void setTipo_punto_emissione(TipoPuntoEmissione tipo_punto_emissione) {
        this.tipoPuntoEmissione = tipo_punto_emissione;
    }

    public void setIn_attività(boolean in_attività) {
        this.inAttivita = in_attività;
    }

    @Override
    public String toString() {
        return "PuntoEmissione{" +
                "punto_emissione=" + id +
                ", tipo_punto_emissione=" + tipoPuntoEmissione +
                ", in_attività=" + inAttivita +
                ", lista_vendite=" + listaVendite +
                '}';
    }
}


