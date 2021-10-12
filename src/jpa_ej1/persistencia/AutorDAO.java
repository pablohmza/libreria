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
import jpa_ej1.entidades.Autor;


/**
 *
 * @author Pablo
 */
public class AutorDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ej1PU");
    private final EntityManager em = emf.createEntityManager();
    
    public void guardarAutor(Autor autor) throws Exception {
        em.getTransaction().begin();
        em.persist(autor);       
        em.getTransaction().commit();
    }

    public void modificarAutor(Autor autor) throws Exception {
        em.getTransaction().begin();
        em.merge(autor);
        em.getTransaction().commit();
    }

    public void eliminarAutorId(int id) throws Exception {
        Autor autor = buscarAutorPorId(id);
        em.getTransaction().begin();
        em.remove(autor);
        em.getTransaction().commit();
    }
    
    public void eliminarAutorNombre(String nombre) throws Exception {
        Autor autor = buscarAutorPorNombre(nombre);
        em.getTransaction().begin();
        em.remove(autor);
        em.getTransaction().commit();
    }
    
    public Autor buscarAutorPorId(int id) throws Exception {
        Autor autor = em.find(Autor.class, id); // Esto que envio es la llave primaria
        return autor;
    }
    
    public Autor buscarAutorPorNombre(String nombre) throws Exception {
        try {
            Autor autor = em.createQuery("SELECT au FROM Autor au WHERE au.nombre = :nombre", Autor.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            return autor;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("ERROR AL BUSCAR AUTOR POR NOMBRE");
        }
    }
    
    public List<Autor> listarAutor(String nombre) throws Exception {
        List<Autor> autores = em.createQuery("SELECT au "
                + " FROM Libro au"
                + " WHERE au.autor.nombre LIKE :nombre").
                setParameter("nombre", nombre).getResultList();
        return autores;
    }
    
    public List<Autor> listarAutores() throws Exception {
        List<Autor> autores = em.createQuery("SELECT au FROM Autor au")
                .getResultList();
        return autores;
    }
    
}
