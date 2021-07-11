/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ventavehiculos;

import ec.edu.espol.model.actores.*;
import ec.edu.espol.model.usuarios.*;
import static ec.edu.espol.procesos.ManejadorMain.*;
import java.util.ArrayList;

/**
 *
 * @author Jose, Ginger
 */
public class Main {
    public static final String ANSI_RESET = "\u001B[0m"; //constante que permite dejar el color del texto en consola a su original    
    public static final String ANSI_RED = "\u001B[31m"; //constante que permite cambiar a rojo el color del texto mostrado en consola 
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_NEGRITA = "\033[0;1m";
    
    public static ArrayList<Comprador> compradores = new ArrayList<>();
    public static ArrayList<Vendedor> vendedores = new ArrayList<>();
    public static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    public static ArrayList<Oferta> ofertas = new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int op = -1;        
        cargarDatos();
        while (op!=3){
            op = menuOpciones();            
            System.out.println("");
            switch(op){
                case 1:
                    menuVendedor();                    
                    break;
                case 2:
                    menuComprador();
                    break;
                default:
                    System.out.println("Gracias :)");
                    break;
            }
            System.out.println("");
        }      
      
    }
    public static void cargarDatos(){
        leerVendedores();
        leerVehiculos();
        leerCompradores();
        leerOfertas();
    }
}
