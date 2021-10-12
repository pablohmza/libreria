/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_ej1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Pablo
 */
public class JPA_ej1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ej1PU");
        EntityManager em = emf.createEntityManager();
        menuLibreria nuevomenu = new menuLibreria();
        nuevomenu.menu();

    }

}
