package acetomartina.entities;

import acetomartina.enom.TipoMezzo;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDate;
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

    @Column(name = "in_servizio")
    private boolean in_servizio;

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

    public Mezzo(TipoMezzo tipo_mezzo, boolean in_servizio) {
        this.tipo_mezzo = tipo_mezzo;
        this.passeggeri_max = tipo_mezzo.getCapienzaMassima();
        this.in_servizio = in_servizio;
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

    public boolean isIn_servizio() {
        return in_servizio;
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

    public void setIn_servizio(boolean in_servizio) {
        this.in_servizio = in_servizio;
    }

    public void setMezzo_di_trasporto(UUID mezzo_di_trasporto) {
        this.mezzo_di_trasporto = mezzo_di_trasporto;
    }
}
