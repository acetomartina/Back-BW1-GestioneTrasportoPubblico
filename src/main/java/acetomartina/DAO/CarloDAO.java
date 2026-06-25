package acetomartina.DAO;

import jakarta.persistence.EntityManager;

import java.util.List;

public class CarloDAO {
    private final EntityManager entityManager;

    public CarloDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> boolean isTableEmpty(Class<T> entityClass) {
        List<T> result = entityManager.createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).setMaxResults(1).getResultList();

        return result.isEmpty();
    }

}
