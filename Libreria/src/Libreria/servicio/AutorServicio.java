package Libreria.servicio;

import Libreria.entidades.Autor;

import Libreria.persistencia.AutorDAO;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AutorServicio {

    private AutorDAO autorDAO;

    public AutorServicio() {
        this.autorDAO = new AutorDAO();
    }

    Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);

    public Autor crearAutor() throws Exception {
        Autor autor = new Autor();
        System.out.println("Ingresar nombre del autor");
        autor.setNombre(read.next());
        autor.setAlta(Boolean.FALSE);
        autorDAO.insert(autor);

        return autor;
    }

    public void darAlta() throws Exception {

        System.out.println("Ingresar ID del autor");
        int alta = read.nextInt();
        Autor autor = autorDAO.getByCode(alta);
        if (autor == null) {
            System.out.println("No existe un autor con este ID");
        } else {
            autor.setAlta(Boolean.TRUE);
            autorDAO.update(autor);
        }
    }

    public void buscarAutor() throws Exception {
        System.out.println("Ingresar el nombre del autor que desea buscar");
        List<Autor> lista = autorDAO.getByName(read.next());
        if (lista == null) {
            System.out.println("El autor no existe");
        } else {
            for (Autor autor : lista) {
                System.out.println(autor.getId() + " " + autor.getNombre());
            }
        }

    }

    public void editarAutor() throws Exception {
        System.out.println("Ingrese ID del autor");
        Autor autor = autorDAO.getByCode(read.nextInt());

        if (autor == null) {
            System.out.println("El autor no existe");
        } else {
            System.out.println("Ingrese el nombre nuevo");
            autor.setNombre(read.next());
            autorDAO.update(autor);
        }

    }

}
