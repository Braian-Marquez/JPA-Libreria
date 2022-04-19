package Libreria.persistencia;

import Libreria.entidades.Libro;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class LibroDAO {

    private final EntityManager em = Persistence
            .createEntityManagerFactory("LibreriaPU")
            .createEntityManager();

    public void insert(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar libros");
        }
    }

    public void update(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar libros");
        }
    }

    public void delete(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar libro");
        }
    }

    public Libro getByCode(Long code) throws Exception {
        try {
            Libro libro = em.find(Libro.class, code);
            return libro;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al buscar libro por código");
        }
    }

    public List<Libro> getAll() throws Exception {
        try {
            List<Libro> libro = em.createQuery("SELECT l FROM Libro l", Libro.class)
                    .getResultList();
            return libro;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al buscar libros");
        }
    }

    public List<Libro> getByName(String nombre) throws Exception {
        try {
            List<Libro> libro = em.createQuery("SELECT l FROM Libro l WHERE l.nombre LIKE :name", Libro.class)
                    .setParameter("name", nombre)
                    .getResultList();
            return libro;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros por nombre");
        }
    }

    public List<Libro> buscarPorAutor(String nombre) throws Exception {
        try {
            List<Libro> libros;
            libros = em.createQuery("SELECT f from Libro f  WHERE f.autor.nombre LIKE :nombre1", Libro.class)
                    .setParameter("nombre1", nombre)
                    .getResultList();
            return libros;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("ERROR al buscar el autor por Nombre");
        }

    }

    public List<Libro> buscarPorEditorial(String nombre) throws Exception {
        try {
            List<Libro> libros;
            libros = em.createQuery("SELECT f from Libro f  WHERE f.editorial.nombre LIKE :nombre1", Libro.class)
                    .setParameter("nombre1", nombre)
                    .getResultList();
            return libros;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("ERROR al buscar el autor por Nombre");
        }

    }

    public List<Libro> buscarTodos() throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT f from Libro f", Libro.class
            ).getResultList();

            return libros;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("ERROR al buscar el autor por ID");
        }

    }

}
