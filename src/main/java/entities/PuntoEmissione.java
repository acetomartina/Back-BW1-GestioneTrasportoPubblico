package entities;

import enom.TipoPuntoEmissione;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "punto_emissione")
public class PuntoEmissione {

    @Id
    @Column(name= "punto_emissione")
    @GeneratedValue
    private UUID punto_emissione;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_punto_emissione")
    private TipoPuntoEmissione tipo_punto_emissione;

    @Column(name = "in_attività")
    private boolean in_attività;

    protected PuntoEmissione(TipoPuntoEmissione tipo_punto_emissione,boolean in_attività){}
    public PuntoEmissione(){
        this.tipo_punto_emissione = tipo_punto_emissione;
        this.in_attività= in_attività;
    }

    public UUID getPunto_emissione() {
        return punto_emissione;
    }

    public TipoPuntoEmissione getTipo_punto_emissione() {
        return tipo_punto_emissione;
    }

    public boolean isIn_attività() {
        return in_attività;
    }

    public void setTipo_punto_emissione(TipoPuntoEmissione tipo_punto_emissione) {
        this.tipo_punto_emissione = tipo_punto_emissione;
    }

    public void setIn_attività(boolean in_attività) {
        this.in_attività = in_attività;
    }
}
