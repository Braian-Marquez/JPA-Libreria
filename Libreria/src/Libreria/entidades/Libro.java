package Libreria.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "libro")
public class Libro implements Serializable {

    @Id
   
    @Column(name = "libro_id")
    private Long isbn;

    @Column(name = "libro_nombre")
    private String nombre;

    @Column(name = "libro_anio" ,columnDefinition = "YEAR" )
    private Integer anio;

    @Column(name = "libro_ejemplares")
    private Integer ejemplares;

    private Integer ejemplaresPrestados;

    private Integer ejemplaresRestantes;

    @Column(name = "libro_alta")
    private Boolean alta;

    @ManyToOne(fetch = FetchType.EAGER)

    private Autor autor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Editorial editorial;

    public Libro() {
    }

    public Libro(Long isbn, String nombre, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

}
