/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_ej1.servicios;

import java.util.Collection;
import java.util.UUID;
import jpa_ej1.entidades.Autor;
import jpa_ej1.persistencia.AutorDAO;

/**
 *
 * @author Pablo
 */
public class servicioAutor {
    
    
    AutorDAO daoAutor = new AutorDAO();
    
    public Autor ingresaAutor(String nomautor) throws Exception {
        try {
            Autor autor = buscarAutorPorNombre(nomautor);
            //Verificamos
            if (autor == null) {
                autor= crearAutor(nomautor);
                //throw new Exception("El autor ya estaba ingresado");
            }
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Autor crearAutor(String nomautor){
        
        try { 
            Autor autorNuevo=new Autor();
            autorNuevo.setNombre(nomautor);
            autorNuevo.setAlta(true);
            //autorNuevo.setId((int)(Math.random()*1000+1));   
            daoAutor.guardarAutor(autorNuevo);
            return autorNuevo;
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
    }
    }
    
    public Autor buscarAutorPorNombre(String nombre) throws Exception {
        try {
            Autor autor = daoAutor.buscarAutorPorNombre(nombre);            
            return autor;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Autor buscarAutorPorId(int id) throws Exception {
        try {
            Autor autor = daoAutor.buscarAutorPorId(id);
            //Verificamos
            if (autor == null) {
                throw new Exception("No se encontró autor para el id indicado");
            }
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Collection<Autor> listaAutor() throws Exception {
        try {
            Collection<Autor> autores = daoAutor.listarAutores();
            return autores;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirAutores() throws Exception {
        try {
            //Listamos los autores
            Collection<Autor> autores = listaAutor();
            //Imprimimos los autores
            if (autores.isEmpty()) {
                throw new Exception("No existen autores para imprimir");
            } else {
                for (Autor aux : autores) {
                    System.out.println("**********************************");
                    System.out.println(" Nombre Autor:" + aux.getNombre()
                            + "\n ID Autor:" + aux.getId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void imprimirUnAutor(String nomautor) throws Exception {
        System.out.println(daoAutor.buscarAutorPorNombre(nomautor));
    }
    
    
    public void eliminarAutor(String nombre) throws Exception {

        try {            
            daoAutor.eliminarAutorNombre(nombre);
            System.out.println("AUTOR ELIMINADO CON EXITO");
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    
    
    
}
