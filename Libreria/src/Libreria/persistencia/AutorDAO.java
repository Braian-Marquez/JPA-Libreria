package Libreria.persistencia;

import Libreria.entidades.Autor;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class AutorDAO {

    private final EntityManager em = Persistence
            .createEntityManagerFactory("LibreriaPU")
            .createEntityManager();

    public void insert(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar libros");
        }
    }

    public void update(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar autor");
        }
    }

    public Autor getByCode(Integer code) throws Exception {
        try {
            Autor autor = em.find(Autor.class, code);
            return autor;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al buscar libro por código");
        }
    }

    public List<Autor> getByName(String name) throws Exception {
        try {
            List<Autor> autor = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :name", Autor.class)
                    .setParameter("name", name)
                    .getResultList();
            return autor;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros por nombre");
        }
    }
}
