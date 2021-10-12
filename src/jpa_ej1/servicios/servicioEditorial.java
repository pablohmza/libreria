/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_ej1.servicios;

import java.util.Collection;
import jpa_ej1.entidades.Autor;
import jpa_ej1.entidades.Editorial;
import jpa_ej1.persistencia.EditorialDAO;

/**
 *
 * @author Pablo
 */
public class servicioEditorial {
     EditorialDAO daoEditorial = new EditorialDAO();
    
    public Editorial ingresaEditorial(String nomeditorial) throws Exception {
        try {
            Editorial editorial = buscarEditorialPorNombre(nomeditorial);
            //Verificamos
            if (editorial == null) {
                editorial = crearEditorial(nomeditorial);
                //throw new Exception("El autor ya estaba ingresado");
            }
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Editorial buscarEditorialPorNombre(String nomeditorial) throws Exception {
        try {
            Editorial editorial = daoEditorial.buscarEditorialPorNombre(nomeditorial);
            return editorial;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Editorial crearEditorial(String nomeditorial){
           Editorial nuevaeditorial = new Editorial();
        try {          
            nuevaeditorial.setNombre(nomeditorial);
            nuevaeditorial.setAlta(true);
            //nuevaeditorial.setId((int)(Math.random()*1000+1));   
            daoEditorial.guardarEditorial(nuevaeditorial);
            return nuevaeditorial;
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
    }
    }
    
    public Editorial buscarEditorialPorId(int id) throws Exception {
        try {
            Editorial editorial = daoEditorial.buscarEditorialPorId(id);
            //Verificamos
            if (editorial == null) {
                throw new Exception("No se encontró autor para el id indicado");
            }
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Collection<Editorial> listaEditorial() throws Exception {
        try {
            Collection<Editorial> editoriales = daoEditorial.listarEditoriales();
            return editoriales;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirEditoriales() throws Exception {
        try {
            Collection<Editorial> editoriales = listaEditorial();
            if (editoriales.isEmpty()) {
                throw new Exception("No existen mascotas para imprimir");
            } else {
                for (Editorial aux : editoriales) {
                    System.out.println("**********************************");
                    System.out.println(" Nombre Editorial:" + aux.getNombre()
                            + "\n ID Editorial:" + aux.getId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void imprimirUnEditorial(String nomeditorial) throws Exception {
        System.out.println(daoEditorial.buscarEditorialPorNombre(nomeditorial));
    }
    
    
    public void eliminarEditorial(String nombre) throws Exception {

        try {            
            daoEditorial.buscarEditorialPorNombre(nombre);
            System.out.println("EDITORIAL ELIMINADA CON EXITO");
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    
}
