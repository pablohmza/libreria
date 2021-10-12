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

import jpa_ej1.entidades.Editorial;

/**
 *
 * @author Pablo
 */
public class EditorialDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ej1PU");
    private final EntityManager em = emf.createEntityManager();
    
    
    
    public void guardarEditorial(Editorial editorial) throws Exception {
        em.getTransaction().begin();
        em.persist(editorial);       
        em.getTransaction().commit();
    }

    public void modificarEditorial(Editorial editorial) throws Exception {
        em.getTransaction().begin();
        em.merge(editorial);
        em.getTransaction().commit();
    }

    public void eliminarEditorialId(int id) throws Exception {
        Editorial editorial = buscarEditorialPorId(id);
        em.getTransaction().begin();
        em.remove(editorial);
        em.getTransaction().commit();
    }
    
    public void eliminarEditorialNombre(String nombre) throws Exception {
        Editorial editorial = buscarEditorialPorNombre(nombre);
        em.getTransaction().begin();
        em.remove(editorial);
        em.getTransaction().commit();
    }
    
    
    
    public Editorial buscarEditorialPorId(int id) throws Exception {
        Editorial editorial = em.find(Editorial.class, id); // Esto que envio es la llave primaria
        return editorial;
    }
    
    
//        public Autor buscarAutorPorNombre(String nombre) throws Exception {
//        try {
//            Autor autor = em.createQuery("SELECT au FROM Autor au WHERE au.nombre = :nombre", Autor.class)
//                    .setParameter("nombre", nombre)
//                    .getSingleResult();
//            return autor;
//        } catch (NoResultException e) {
//            return null;
//        } catch (Exception e) {
//            throw new Exception("ERROR AL BUSCAR AUTOR POR NOMBRE");
//        }
//    }
    
    public Editorial buscarEditorialPorNombre(String nomeditorial) throws Exception {
        try {
            Editorial editorial = em.createQuery("SELECT ed FROM Editorial ed WHERE ed.nombre = :nombre", Editorial.class)
                    .setParameter("nombre", nomeditorial)
                    .getSingleResult();
            return editorial;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("ERROR AL BUSCAR EDITORIAL POR NOMBRE");
        }
    }
    
    
//    
//    public Editorial buscarEditorialPorNombre(String nomeditorial) throws Exception {
//        Editorial editorial = (Editorial) em.createQuery("SELECT ed "
//                + " FROM Editorial ed"
//                + " WHERE ed.nombre LIKE :nombre",Editorial.class).
//                setParameter("nombre", nomeditorial).
//                getSingleResult();      
//        return editorial;
//    }
    
    public List<Editorial> listarEditoriales() throws Exception {
        List<Editorial> editoriales = em.createQuery("SELECT ed FROM Editorial ed")
                .getResultList();
        return editoriales;
    }
    
}
