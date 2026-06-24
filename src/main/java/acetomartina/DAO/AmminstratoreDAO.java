package acetomartina.DAO;

import jakarta.persistence.EntityManager;

public class AmminstratoreDAO {
    private EntityManager entityManager;

    public AmminstratoreDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    // METODO PER CALCOLARE TEMPO EFFETTIVO DI PERCORRENZA DATA UNA TRATTA , DA PARTE DI UN MEZZO
    // QUI.....
}
