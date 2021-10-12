/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_ej1;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Collection;
import java.util.Scanner;
import jpa_ej1.entidades.Autor;
import jpa_ej1.entidades.Libro;
import jpa_ej1.servicios.servicioAutor;
import jpa_ej1.servicios.servicioEditorial;
import jpa_ej1.servicios.servicioLibro;

/**
 *
 * @author Pablo
 */
public class menuLibreria {
    
    
    private final servicioLibro servlibro;
    private final servicioAutor servautor;
    private final servicioEditorial serveditorial;
   
    public menuLibreria() {
        this.servlibro = new servicioLibro();
        this.servautor = new servicioAutor();
        this.serveditorial = new servicioEditorial();
    }
    
//    servicioAutor servautor = new servicioAutor();
//    servicioEditorial serveditorial = new servicioEditorial();
//    servicioLibro servlibro = new servicioLibro();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void menu() throws Exception {
        String respuesta;
        do {
            System.out.println("Seleccione la opción:");
            System.out.println("=====================================");

            System.out.println(" 1- Crear Libro");//OK
            System.out.println(" 2- Crear Autor para un libro");//OK
            System.out.println(" 3- Crear Editorial para un libro");//OK

            System.out.println(" 4- Mostrar Libros");//OK
            System.out.println(" 5- Mostrar Autores");//OK
            System.out.println(" 6- Mostrar Editoriales");//OK

            System.out.println(" 7- Modificar Ejemplares Restantes");//OK
            //System.out.println(" 77- Modificar Ejemplares Restantes");//OK
            System.out.println(" 8- Buscar Libro por ISBN");//OK
            System.out.println(" 9- Buscar Libro por Titulo");//OK
            System.out.println(" 10- Buscar Libro por Autor");//OK
            System.out.println(" 11- Buscar Libro por Editorial");//OK
            System.out.println(" 12- Buscar Autor por Nombre");//OK
            System.out.println(" 13- Buscar Editorial por Nombre");//OK
            System.out.println(" 14- Eliminar Libro");//OK
            System.out.println(" 15- Eliminar Autor");//OK
            System.out.println(" 16- Eliminar Editorial");//OK

            System.out.println(" 0 - Salir");
            
            int opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    servlibro.crearLibro(cargarIsbn(), cargarTitulo(), cargarAnio(), cargarEjemplares(), cargarAutor(), cargarEditorial());
                    break;
                case 2:
                    servautor.crearAutor(cargarAutor());                    
                    break;
                case 3:
                    serveditorial.crearEditorial(cargarEditorial());
                    break;
                case 4:
                    servlibro.imprimirLibros();
                    break;
                case 5:
                    servautor.imprimirAutores();
                    break;
                case 6:
                    serveditorial.imprimirEditoriales();
                    break;
                case 7:
                    servlibro.modificarEjemplares(buscarPorIsbn(), cargarEjemplaresPrestados());
                    break;
                case 77:
                    servlibro.modificarLibro(buscarPorIsbn(), cargarTitulo(), cargarAnio(), cargarEjemplares(), cargarAutor(), cargarEditorial());
                    break;
                case 8:
                    servlibro.imprimirUnLibro(buscarPorIsbn());
                    break;
                case 9:
                    servlibro.imprimirLibroXTitulo(cargarTitulo());
                    break;
                case 10:
                    servlibro.imprimirLibroXAutor(cargarAutor());
                    break;
                case 11:
                    servlibro.imprimirLibroXEditorial(cargarEditorial());
                    break;               
               case 12:
                    servautor.imprimirUnAutor(cargarAutor());
                    break;
                case 13:
                    serveditorial.imprimirUnEditorial(cargarEditorial());
                    break;
                case 14:
                    servlibro.eliminarLibro(buscarPorIsbn());
                    break; 
                case 15:
                    servautor.eliminarAutor(cargarAutor());
                    break;
                case 16:
                    serveditorial.eliminarEditorial(cargarEditorial());
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
            
    System.out.println("Desea realizar una nueva consulta o gestion ???:SI/NO");
            respuesta = leer.next();
            respuesta = respuesta.toUpperCase();
            limpiarPantalla();
        } while ("SI".equals(respuesta));

    }

    public void limpiarPantalla() throws AWTException {
        //Dejo esre metodo para ir borrando la consola.. y que no sea un desorden.
        Robot pressbot = new Robot();
        pressbot.setAutoDelay(30); // Tiempo de espera antes de borrar
        pressbot.keyPress(17); // Orden para apretar CTRL key
        pressbot.keyPress(76);// Orden para apretar L key
        pressbot.keyRelease(17); // Orden para soltar CTRL key
        pressbot.keyRelease(76); // Orden para soltar L key

    }

    public long cargarIsbn() throws Exception {
        long isbn;
        System.out.println("Ingrese el ISBN");
        isbn = leer.nextLong();
        try {
            if (validarCodigo(isbn)) {
                System.out.println("Codigo ingresado NO registrado con antelacion, continue con el registro de datos");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            cargarIsbn();
        }
        return isbn;
    }

    public boolean validarCodigo(long isbn) throws Exception {
        boolean validar = false;
        try {
            Collection<Libro> libros = servlibro.listaLibros();
            for (Libro aux : libros) {
                if (aux.getIsbn()==isbn) {
                    throw new Exception("Codigo ISBN ya registrado, ingrese un ISBN valido");
                }
            }
            validar = true;
        } catch (Exception e) {
            throw e;
        }
        return validar;
    }

    public String cargarTitulo() {
        System.out.println("Ingrese el Titulo del Libro");
        String titulo = leer.next();
        return titulo;
    }

    public int cargarAnio() {
        System.out.println("Ingrese el año de publicacion");
        int anio = leer.nextInt();
        return anio;
    }
    
    public int cargarEjemplares() {
        System.out.println("Ingrese el numero de ejemplares");
        int ejemplares = leer.nextInt();
        return ejemplares;
    }
    
    
//    public String cargarAutor() throws Exception {
//        String nomautor;
//        System.out.println("Ingrese el nombre del Autor");
//        nomautor = leer.next();
//        try {
//            if (validarAutor(nomautor)) {
//                System.out.println("Autor nuevo ingresado");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            cargarAutor();
//        }
//        return nomautor;
//    }
//
//    public boolean validarAutor(String nomautor) throws Exception {
//        boolean validar = false;
//        try {
//            Collection<Autor> autores = servautor.listaAutor();
//            for (Autor aux : autores) {
//                if (aux.getNombre().equalsIgnoreCase(nomautor)) {
//                    throw new Exception("Autor ya registrado, seleccione de la lista");                    
//                }
//            }
//            validar = true;
//        } catch (Exception e) {
//            throw e;
//        }
//        return validar;
//    }
    
    public String cargarAutor() {
        System.out.println("Ingrese el nombre del Autor");
        String nomautor = leer.next();
        return nomautor;
    }
    
    public String cargarEditorial() {
        System.out.println("Ingrese el nombre de la Editorial");
        String nomeditorial = leer.next();
        return nomeditorial;
    }
    
    
    
    public int cargarEjemplaresPrestados() {
        System.out.println("Ingrese los ejemplares prestados");
        int prestados = leer.nextInt();
        return prestados;
    }

//    public Date cargarFecha() throws ParseException {
//        System.out.println("Ingrese mes , dia , anio  de nacimiento MM/DD/ANIO");
//        Date fecha = null;
//
//        String inputDate = sc.nextInt() + "/" + sc.nextInt() + "/" + sc.nextInt();
//        fecha = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate);
//        System.out.println(fecha);
//        return fecha;
//    }

//    public String cargarApodo() {
//        System.out.println("Ingrese apodo de la mascota");
//        String apodoI = sc.next();
//        return apodoI;
//    }
//
//    public String cargarRaza() {
//        System.out.println("Ingrese raza  de la mascota");
//        String razaI = sc.next();
//        return razaI;
//    }
//
//    public Usuario encontrarUsuario() throws Exception {
//        System.out.println("Ingrese el ID del usuario");
//        String idUsuario = sc.next();
//        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
//        return usuario;
//    }

    public long buscarPorIsbn() {
        System.out.println("Ingrese el codigo ISBN del libro");
        long codisbn= leer.nextLong();
        return codisbn;
    }

    public String ingresarNombreAutor() {
        System.out.println("Ingrese Nombre del Autor");
        String nomautor = leer.next();
        return nomautor;
    }
//
//    public String cargarIdMascot() {
//        System.out.println("Ingrese el Id Mascota");
//        String idMascota = sc.next();
//        return idMascota;
//    }
}

    
    

        
