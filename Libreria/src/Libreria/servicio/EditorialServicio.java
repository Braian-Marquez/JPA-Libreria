package Libreria.servicio;

import Libreria.entidades.Editorial;
import Libreria.persistencia.EditorialDAO;
import java.util.Locale;
import java.util.Scanner;

public class EditorialServicio {

    private EditorialDAO editorialDAO;

    public EditorialServicio() {
        this.editorialDAO = new EditorialDAO();
    }

    Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);

    public Editorial crearEditorial() throws Exception {
        Editorial editorial = new Editorial();
        System.out.println("Ingresar el nombre de la Editorial");
        editorial.setNombre(read.next());
        editorial.setAlta(Boolean.FALSE);
        editorialDAO.insert(editorial);
        return editorial;
    }

    public void darAlta() throws Exception {

        System.out.println("Ingresar ID de la editorial");
        int alta = read.nextInt();
        Editorial editorial = editorialDAO.getByCode(alta);
        if (editorial == null) {
            System.out.println("No existe un autor con este ID");
        } else {
            editorial.setAlta(Boolean.TRUE);
            editorialDAO.update(editorial);
        }
    }

}
