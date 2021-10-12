/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_ej1.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import jpa_ej1.entidades.Libro;

/**
 *
 * @author Pablo
 */
public class LibroDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ej1PU");
    private final EntityManager em = emf.createEntityManager();
    
    public void guardarLibro(Libro libro) throws Exception {
        em.getTransaction().begin();
        em.persist(libro);       
        em.getTransaction().commit();
    }

    public void modificarLibro(Libro libro) throws Exception {
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
    }

    public void eliminarLibroIsbn(long isbn) throws Exception {
        Libro libro = buscarLibroPorId(isbn);
        em.getTransaction().begin();
        em.remove(libro);
        em.getTransaction().commit();
    }
    
    public Libro buscarLibroPorId(long isbn) throws Exception {
        Libro libro = em.find(Libro.class, isbn); // Esto que envio es la llave primaria
        return libro;
    }
    
    public Libro buscarLibroPorTitulo(String titulo) throws Exception {
        try {
            Libro libro = em.createQuery("SELECT li FROM Libro li WHERE li.titulo = :titulo", Libro.class)
                    .setParameter("titulo", titulo)
                    .getSingleResult();
            return libro;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("ERROR AL BUSCAR LIBRO POR TITULO");
        }
    }
    
    
    
    
    public List<Libro> listarLibros() throws Exception {
        List<Libro> libros = em.createQuery("SELECT li FROM Libro li")
                .getResultList();
        return libros;
    }
    
    public List<Libro> listarLibrosXAutor(String nomautor) throws Exception {
        List<Libro> libros = em.createQuery("SELECT li FROM Libro li WHERE li.autor.nombre = :nombre",Libro.class)
                .setParameter("nombre", nomautor)
                .getResultList();
        return libros;
    }
    
    public List<Libro> listarLibrosXEditorial(String nomeditorial) throws Exception {
        List<Libro> libros = em.createQuery("SELECT li FROM Libro li WHERE li.editorial.nombre = :nombre",Libro.class)
                .setParameter("nombre", nomeditorial)
                .getResultList();
        return libros;
    }
    
}
