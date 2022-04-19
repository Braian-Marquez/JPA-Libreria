package Libreria.servicio;

import Libreria.entidades.Libro;
import Libreria.persistencia.LibroDAO;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class LibroServicio {

    private LibroDAO libroDAO;

    public LibroServicio() {
        this.libroDAO = new LibroDAO();
    }
    Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);

    public void crearLibro() throws Exception {

        Libro libro = new Libro();
        AutorServicio autor = new AutorServicio();
        EditorialServicio editorial = new EditorialServicio();
        try {
            System.out.println("Ingrese ISBN del Libro");
            libro.setIsbn(read.nextLong());
            System.out.println("Ingrese el titulo del libro");
            libro.setNombre(read.next());
            System.out.println("Ingrese el año");
            libro.setAnio(read.nextInt());
            System.out.println("Ingrese cantidad de ejemplares");
            libro.setEjemplares(read.nextInt());
            System.out.println("Ingrese ejemplares prestados");
            libro.setEjemplaresPrestados(read.nextInt());
            System.out.println("Ingrese ejemplares restantes");
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            libro.setAutor(autor.crearAutor());
            libro.setEditorial(editorial.crearEditorial());
            libroDAO.insert(libro);
        } catch (Exception e) {
            System.out.println("Inserte caracteres validos");
        }
    }

    public void darAlta() throws Exception {
        try {
            System.out.println("Ingresar ISBN del libro");
            Long alta = read.nextLong();
            Libro libro = libroDAO.getByCode(alta);
            if (libro == null) {
                System.out.println("No existe un libro con este ISBN");
            } else {
                libro.setAlta(Boolean.TRUE);
                libroDAO.update(libro);
            }
        } catch (Exception e) {
            System.out.println("Ingreso un caracter que no correspondia");
        }
    }

    public void buscarLibro() throws Exception {
        System.out.println("Ingresar el ISBN del libro que desea buscar");
        Libro libros = libroDAO.getByCode(read.nextLong());
        if (libros == null) {
            System.out.println("El libro no existe");
        } else {

            System.out.println(libros.getNombre() + " " + libros.getIsbn());

        }
    }

    public void buscarLibroTitulo() throws Exception {
        try {
            System.out.println("Ingrese el titulo del libro");
            List<Libro> libro = libroDAO.getByName(read.next());

            if (libro == null) {
                System.out.println("El libro no existe");
            } else {
                for (Libro libro1 : libro) {
                    System.out.println(libro1.getIsbn() + " " + libro1.getNombre());
                }
            }
        } catch (Exception e) {
            System.out.println("Ingreso un caracter que no correspondia");
        }
    }

    public void editarLibro() throws Exception {
        int opc = 0;
        try {
            System.out.println("Ingrese ISBN del libro");
            Libro libro = libroDAO.getByCode(read.nextLong());
            
            if (libro == null) {
                System.out.println("El libro no existe");
            } else {
                do {
                    System.out.println("****************");
                    System.out.println("Que desa editar?");
                    System.out.println("1- Titulo");
                    System.out.println("2- Ejemplares");
                    System.out.println("3- Ejemplares Prestados");
                    System.out.println("4- Año");
                    System.out.println("5- SALIR");
                    System.out.println("****************");
                    opc=read.nextInt();
                    switch (opc) {
                        case 1:
                            System.out.println("Ingresar nuevo titulo");
                            libro.setNombre(read.next());
                            break;
                        case 2:
                            System.out.println("Ingrese Ejemplares");
                            libro.setEjemplares(read.nextInt());
                            break;
                        case 3:
                            System.out.println("Ingrese Ejemplares Prestados");
                            libro.setEjemplaresPrestados(read.nextInt());
                            break;
                        case 4:
                            System.out.println("Ingrese año");
                            libro.setAnio(read.nextInt());
                            break;
                        case 5:
                            
                            break;

                    }
                } while (opc != 5);
                libroDAO.update(libro);
            }
        } catch (Exception e) {
            System.out.println("Ingreso un caracter que no correspondia");
        }
    }

    public void buscarPorAutor() throws Exception {
        System.out.println("Ingrese autor del libro a buscar ");
        String titulo = read.next();
        try {
            List<Libro> libros = libroDAO.buscarPorAutor(titulo);
            if (libros.isEmpty()) {
                throw new Exception("No existen libros con ese titulo");
            } else {
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s \n",
                        "ISBN", "Titulo", "Año", "Ejemplares", "Autor", "Editorial");

                for (Libro l : libros) {
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s \n", l.getIsbn(), l.getAutor(), l.getAnio(), l.getEjemplares(), l.getAutor().getNombre(), l.getEditorial().getNombre());

                }

            }
        } catch (Exception e) {
            throw new Exception("ERROR al traer lista por nombre de autor");
        }
    }

    public void buscarPorEditorial() throws Exception {
        System.out.println("Ingrese editorial del libro a buscar ");
        String nombre = read.next();
        try {
            List<Libro> libros = libroDAO.buscarPorEditorial(nombre);
            if (libros.isEmpty()) {
                throw new Exception("No existen libros de esa editorial");
            } else {
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s \n",
                        "ISBN", "Titulo", "Año", "Ejemplares", "Autor", "Editorial");

                for (Libro l : libros) {
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s \n", l.getIsbn(), l.getNombre(), l.getAnio(), l.getEjemplares(), l.getAutor().getNombre(), l.getEditorial().getNombre());

                }

            }
        } catch (Exception e) {
            throw new Exception("ERROR al traer lista por nombre de autor");
        }
    }

    public void mostrar() throws Exception {
        try {
            List<Libro> libros = libroDAO.buscarTodos();
            if (libros.isEmpty()) {
                throw new Exception("No existen editoriales con ese nombre");
            } else {
                System.out.println("");
                System.out.printf("%-20s%-25s%-25s%-20s%-20s%-20s \n", "ISBN", "Titulo", "Año", "Ejemplares", "Autor", "Editorial");
                System.out.println("");
                for (Libro l : libros) {

                    System.out.printf("%-20s%-25s%-25s%-20s%-20s%-20s \n", l.getIsbn(), l.getNombre(), l.getAnio(), l.getEjemplares(), l.getAutor().getNombre(), l.getEditorial().getNombre());
                    //
                }
            }
        } catch (Exception e) {
            throw new Exception("ERROR al mostrar");
        }
    }

    public void eliminarLibro() throws Exception {
        try {
            System.out.println("Ingresar el ISBN del libro que desea eliminar");
            Long isbn = read.nextLong();
            Libro libro = libroDAO.getByCode(isbn);
            if (libro == null) {
                System.out.println("No existe el libro");
            } else {
                libroDAO.delete(libro);
                System.out.println("Libro eliminado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Ingreso un caracter que no corresponde");
        }
    }

}
