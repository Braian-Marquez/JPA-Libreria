package Libreria.persistencia;

import Libreria.entidades.Editorial;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EditorialDAO {

    private final EntityManager em = Persistence
            .createEntityManagerFactory("LibreriaPU")
            .createEntityManager();

    public void insert(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar libros");
        }
    }

    public void update(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar editorial");
        }
    }

    public Editorial getByCode(Integer code) throws Exception {
        try {
            Editorial editorial = em.find(Editorial.class, code);
            return editorial;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al buscar editorial por código");
        }
    }
}
