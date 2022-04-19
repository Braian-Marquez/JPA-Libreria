package Libreria.servicio;

import java.util.Locale;
import java.util.Scanner;

public class Menu {

    LibroServicio libroServicio = new LibroServicio();
    AutorServicio autorServicio = new AutorServicio();
    EditorialServicio editorialServicio = new EditorialServicio();

    Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);

    public void menu() throws Exception {
        int opc;
        do {
            System.out.println("*******************");
            System.out.println("1-Cargar un libro");
            System.out.println("2-Cargar un autor");
            System.out.println("3-Cargar una editorial");
            System.out.println("4-Dar de alta/baja libro ");
            System.out.println("5-Dar de alta/baja un autor");
            System.out.println("6-Dar de alta/baja una editorial ");
            System.out.println("7-Editar libro ");
            System.out.println("8-Editar un autor");
            System.out.println("9-Editar una editorial");
            System.out.println("10-Buscar autor por nombre");
            System.out.println("11-Buscar libro por autor");
            System.out.println("12-Buscar libros por editorial");
            System.out.println("13-Mostrar todos los libros");
            System.out.println("14-Eliminar libro");
            System.out.println("15-SALIR");
            System.out.println("*******************");
            opc = read.nextInt();
            switch (opc) {
                case 1:
                    libroServicio.crearLibro();
                    break;
                case 2:
                    autorServicio.crearAutor();
                    break;
                case 3:
                    editorialServicio.crearEditorial();
                    break;
                case 4:
                    libroServicio.darAlta();
                    break;
                case 5:
                    autorServicio.darAlta();
                    break;
                case 6:
                    editorialServicio.darAlta();
                    break;
                case 7:
                    libroServicio.editarLibro();
                    break;
                case 8:
                    autorServicio.editarAutor();
                    break;
                case 9:
                    libroServicio.buscarLibro();
                    break;
                case 10:
                    libroServicio.buscarLibroTitulo();
                case 11:
                    libroServicio.buscarPorAutor();
                    break;
                case 12:
                    libroServicio.buscarPorEditorial();
                    break;
                case 13:
                    libroServicio.mostrar();
                    break;
                case 14:
                    libroServicio.eliminarLibro();
                    break;
                case 15:
                    break;
                default:
                    System.out.println("Ingreso una opcion Incorrecta");
                    break;
            }
        } while (opc != 15);

    }

}
