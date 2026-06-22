package entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Utente {
    @Id
    private long id;
    private int nome;
}
