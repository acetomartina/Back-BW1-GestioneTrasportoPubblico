package acetomartina.entities;

import acetomartina.enums.StatoMezzo;
import acetomartina.enums.TipoMezzo;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mezzo_di_trasporto")
public class Mezzo {

    @Id
    @Column(name = "mezzo_di_trasporto")
    @GeneratedValue
    private UUID mezzo_di_trasporto;

    @Column(name = "numero_mezzo")
    private String numero_mezzo;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_mezzo")
    private TipoMezzo tipo_mezzo;

    @Column(name = "passeggeri_max")
    private int passeggeri_max;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_mezzo")
    private StatoMezzo stato_mezzo;

    @OneToMany(mappedBy = "mezzo")
    private List<Corsa> corse;

    @OneToMany(mappedBy = "mezzo")
    private List<Manutenzione> manutanzioni;

    @PrePersist
    public void generaNumeroMezzo() {
        if (this.numero_mezzo == null) {
            SecureRandom random = new SecureRandom();
            long numeroCasuale = random.nextLong(900_000_000_000L);
            long ISBN = 100_000_000_000L + numeroCasuale;
            this.numero_mezzo = ISBN + "NM";
        }
    }

    protected Mezzo() {
    }

    public Mezzo(TipoMezzo tipo_mezzo) {
        this.tipo_mezzo = tipo_mezzo;
        this.passeggeri_max = tipo_mezzo.getCapienzaMassima();
        this.stato_mezzo = StatoMezzo.ATTIVO;
    }

    public UUID getMezzo_di_trasporto() {
        return mezzo_di_trasporto;
    }

    public String getNumero_mezzo() {
        return numero_mezzo;
    }


    public TipoMezzo getTipo_mezzo() {
        return tipo_mezzo;
    }

    public int getPasseggeri_max() {
        return passeggeri_max;
    }

    public void setNumero_mezzo(String numero_mezzo) {
        this.numero_mezzo = numero_mezzo;
    }


    public void setTipo_mezzo(TipoMezzo tipo_mezzo) {
        this.tipo_mezzo = tipo_mezzo;
    }

    public void setPasseggeri_max(int passeggeri_max) {
        this.passeggeri_max = passeggeri_max;
    }

    public StatoMezzo getStato_mezzo() {
        return stato_mezzo;
    }

    public void setStato_mezzo(StatoMezzo stato_mezzo) {
        this.stato_mezzo = stato_mezzo;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "mezzo_di_trasporto=" + mezzo_di_trasporto +
                ", numero_mezzo='" + numero_mezzo + '\'' +
                ", tipo_mezzo=" + tipo_mezzo +
                ", passeggeri_max=" + passeggeri_max +
                ", stato_mezzo=" + stato_mezzo +
                ", corse=" + corse +
                '}';
    }
}
