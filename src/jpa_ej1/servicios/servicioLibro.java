/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_ej1.servicios;

import java.util.Collection;
import jpa_ej1.entidades.Libro;
import jpa_ej1.persistencia.LibroDAO;

/**
 *
 * @author Pablo
 */
public class servicioLibro {

    LibroDAO daoLibro = new LibroDAO();
    servicioAutor servautor = new servicioAutor();
    servicioEditorial serveditorial = new servicioEditorial();

    public void crearLibro(long isbn, String titulo, int anio, int ejemplares, String nomautor, String nomeditorial) throws Exception {

        try {
            //Creamos el usuario
            Libro libro = new Libro();

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            //libro.setAutor(servautor.crearAutor(nomautor));
            libro.setAutor(servautor.ingresaAutor(nomautor));
            //libro.setEditorial(serveditorial.crearEditorial(nomeditorial));
            libro.setEditorial(serveditorial.ingresaEditorial(nomeditorial));
            libro.setEjemplaresRestantes(ejemplares);
            libro.setEjemplaresPrestados(0);
            daoLibro.guardarLibro(libro);
        } catch (Exception e) {
            System.out.println("No se cargo el libro" + e.getMessage());
        }
    }

    public void modificarLibro(long isbn, String titulo, int anio, int ejemplares, String nomautor, String nomeditorial) {
        try {
            Libro libro = buscarLibroPorIsbn(isbn);
            //libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setAutor(servautor.ingresaAutor(nomautor));
            libro.setEditorial(serveditorial.ingresaEditorial(nomeditorial));
            libro.setEjemplaresRestantes(ejemplares);
            libro.setEjemplaresPrestados(0);
            daoLibro.modificarLibro(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Libro buscarLibroPorIsbn(long isbn) throws Exception {
        try {
            Libro libro = daoLibro.buscarLibroPorId(isbn);
            return libro;
        } catch (Exception e) {
            throw e;
        }
    }

    public Libro buscarLibroPorTitulo(String titulo) throws Exception {
        try {
            Libro libro = daoLibro.buscarLibroPorTitulo(titulo);

            return libro;
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarLibro(long isbn) throws Exception {

        try {
            daoLibro.eliminarLibroIsbn(isbn);
            System.out.println("LIBRO ELIMINADO CON EXITO");
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Libro> listaLibros() throws Exception {

        try {
            Collection<Libro> libros = daoLibro.listarLibros();
            return libros;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Libro> listaLibroXAutor(String nomautor) throws Exception {

        try {
            Collection<Libro> libros = daoLibro.listarLibrosXAutor(nomautor);
            return libros;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Libro> listaLibroXEditorial(String nomeditorial) throws Exception {

        try {
            Collection<Libro> libros = daoLibro.listarLibrosXEditorial(nomeditorial);
            return libros;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirLibros() throws Exception {
        try {
            //Listamos los usuarios
            Collection<Libro> libros = listaLibros();

            //Imprimimos los usuarios - Solo algunos atributos....
            if (libros.isEmpty()) {
                throw new Exception("No existen libros para mostrar");
            } else {
                for (Libro aux : libros) {
                    System.out.println("*****************************************");
                    System.out.println(" ISBN: " + aux.getIsbn()
                            + "\n Titulo: " + aux.getTitulo()
                            + "\n Autor: " + aux.getAutor().getNombre()
                            + "\n Editorial : " + aux.getEditorial().getNombre()
                            + "\n Año :" + aux.getAnio()
                            + "\n Ejemplares: " + aux.getEjemplares()
                            + "\n Prestados: " + aux.getEjemplaresPrestados()
                            + "\n Restantes: " + aux.getEjemplaresRestantes());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void imprimirLibroXAutor(String nomautor) throws Exception {
        try {
            //Listamos los usuarios
            Collection<Libro> libros = listaLibroXAutor(nomautor);

            //Imprimimos los usuarios - Solo algunos atributos....
            if (libros.isEmpty()) {
                throw new Exception("No existen libros para mostrar");
            } else {
                for (Libro aux : libros) {
                    System.out.println("*****************************************");
                    System.out.println(" ISBN: " + aux.getIsbn()
                            + "\n Titulo: " + aux.getTitulo()
                            + "\n Autor: " + aux.getAutor().getNombre()
                            + "\n Editorial : " + aux.getEditorial().getNombre()
                            + "\n Año :" + aux.getAnio()
                            + "\n Ejemplares: " + aux.getEjemplares()
                            + "\n Prestados: " + aux.getEjemplaresPrestados()
                            + "\n Restantes: " + aux.getEjemplaresRestantes());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void imprimirLibroXEditorial(String nomeditorial) throws Exception {
        try {
            //Listamos los usuarios
            Collection<Libro> libros = listaLibroXEditorial(nomeditorial);

            //Imprimimos los usuarios - Solo algunos atributos....
            if (libros.isEmpty()) {
                throw new Exception("No existen libros para mostrar");
            } else {
                for (Libro aux : libros) {
                    System.out.println("*****************************************");
                    System.out.println(" ISBN: " + aux.getIsbn()
                            + "\n Titulo: " + aux.getTitulo()
                            + "\n Autor: " + aux.getAutor().getNombre()
                            + "\n Editorial : " + aux.getEditorial().getNombre()
                            + "\n Año :" + aux.getAnio()
                            + "\n Ejemplares: " + aux.getEjemplares()
                            + "\n Prestados: " + aux.getEjemplaresPrestados()
                            + "\n Restantes: " + aux.getEjemplaresRestantes());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void modificarEjemplares(long isbn, int ejemplaresprestados) throws Exception {

        try {
            //Buscamos
            Libro libro = buscarLibroPorIsbn(isbn);
            //Modificamos
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + ejemplaresprestados);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - libro.getEjemplaresPrestados());
            daoLibro.modificarLibro(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void imprimirLibroXTitulo(String titulo) throws Exception {
        System.out.println(daoLibro.buscarLibroPorTitulo(titulo));
    }

//    public void imprimirLibroXAutor(String titulo) throws Exception {
//        System.out.println(daoLibro.buscarLibroPorTitulo(titulo));
//    }
    public void imprimirUnLibro(long isbn) throws Exception {
        System.out.println(daoLibro.buscarLibroPorId(isbn));
    }

}
