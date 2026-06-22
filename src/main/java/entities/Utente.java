package entities;

import enom.TipoUtente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "utenti")
public class Utente {
  @Id
  @GeneratedValue
  private UUID utente_id;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_utente")
    private TipoUtente tipo_utente;

  @Column(name = "nome_utente")
  private String nome_utente;

  @Column(name = "cognome_utente")
  private String cognome_utente;

  @Column(name = "data_nascita")
  private LocalDate data_nascita;

  @Column(name = "luogo_nascita")
    private String luogo_nascita;

  protected Utente(){}
    public Utente(TipoUtente tipo_utente,String nome_utente,String cognome_utente,LocalDate data_nascita, String luogo_nascita){
      this.tipo_utente= tipo_utente;
      this.nome_utente= nome_utente;
      this.cognome_utente = cognome_utente;
      this.data_nascita= data_nascita;
      this.luogo_nascita= luogo_nascita;
    }

    public TipoUtente getTipo_utente() {
        return tipo_utente;
    }

    public void setTipo_utente(TipoUtente tipo_utente) {
        this.tipo_utente = tipo_utente;
    }

    public UUID getUtente_id() {
        return utente_id;
    }

    public String getNome_utente() {
        return nome_utente;
    }

    public void setNome_utente(String nome_utente) {
        this.nome_utente = nome_utente;
    }

    public String getCognome_utente() {
        return cognome_utente;
    }

    public void setCognome_utente(String cognome_utente) {
        this.cognome_utente = cognome_utente;
    }

    public LocalDate getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(LocalDate data_nascita) {
        this.data_nascita = data_nascita;
    }

    public String getLuogo_nascita() {
        return luogo_nascita;
    }

    public void setLuogo_nascita(String luogo_nascita) {
        this.luogo_nascita = luogo_nascita;
    }
}
