/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.usuarios.*;
import static ventavehiculos.Main.*;

/**
 *
 * @author Jose Anchundia
 */
public class ManejadorMain {
    /**
     *
     * @param correo
     * @return
     */
    public static Usuario buscarUsuario(Correo correo){
        for(Vendedor v: vendedores){
            if (v.getCorreo_electrico().equals(correo)){
                return v;
            }
        }
        for(Comprador c: compradores){
            if (c.getCorreo_electrico().equals(correo)){
                return c;
            }
        }
        return null;
    }
        /**
     *
     * @return
     */
    public static int menuOpciones(){
        int op;
        System.out.println("Menu de Opciones");
        System.out.println("1.- Vendedor\n" + "2.- Comprador\n"+ "3.- Salir");
        op = Validaciones.validarEntero("Elija una opcion: ",1,3);
        return op;
    }
     /**
     *
     */
    public static void menuVendedor(){
        int op;
        System.out.println("Opciones del Vendedor");
        System.out.println("1.- Registrar un nuevo vendedor\n" + "2.- Ingresar un nuevo vehículo\n"+ "3.- Aceptar oferta\n"+"4.- Regresar");
        op = Validaciones.validarEntero("Elija una opcion: ",1,4);
        subMenuVendedor(op);
    }
    
        private static void subMenuVendedor(int op){
        System.out.println("");        
        switch(op){
            case 1:
                System.out.println(ANSI_BLUE+"Registrar nuevo vendedor"+ANSI_RESET);
                vendedor_Op1();
                break;
            case 2:
                System.out.println(ANSI_BLUE+"Ingresar un nuevo vehículo"+ANSI_RESET);
                vendedor_Op2();
                break;
            case 3:
                System.out.println(ANSI_BLUE+"Aceptar oferta"+ANSI_RESET);
                vendedor_Op3();
                break;
            default:
                break;
        }
        if(op!=4){
            System.out.println("");
            menuVendedor();
        }
    }
        private static void vendedor_Op1(){
            
        }
        private static void vendedor_Op2(){
            
        }
        private static void vendedor_Op3(){
            
        }
}
