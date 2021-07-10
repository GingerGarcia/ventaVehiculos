/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.actores.*;

import ec.edu.espol.model.usuarios.*;
import java.util.Scanner;
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
        Vendedor vendedor = Vendedor.crearVendedor(new Scanner(System.in)); 
            if(vendedor!=null){
                if (ManejadorVendedor.file_anadirVendedor(vendedor))
                    System.out.println(ANSI_GREEN+"**Vendedor creado exitosamente**"+ANSI_RESET);
                else
                    System.out.println(ANSI_RED+"**Ha ocurrido un error, vendedor no creado**"+ANSI_RESET);
            }
            else
                System.out.println(ANSI_RED+"**Vendedor ya existe, no ha sido creado ninguno nuevo**"+ANSI_RESET);
    }
    
        private static void vendedor_Op2(){
            char continuar = 'S';
             Vendedor vendedor = ManejadorVendedor.inicioSesionVendedor();
            if (vendedor!=null){
                TipoVehiculo tp = ManejadorVehiculo.elegirTipoVehiculo();
                Vehiculo vehiculo;
            while(continuar!='N'){
                vehiculo = Vehiculo.crearVehiculo(new Scanner(System.in),tp);                        
            if (vehiculo!=null){
                vehiculo.asignarDueno(vendedor);
                file_anadirVehiculo(vehiculo, vendedor);
                System.out.println(ANSI_GREEN+"**Vehiculo registrado**"+ANSI_RESET);
                continuar = 'N';
            }else{
                System.out.println(ANSI_RED+"**Placa ya registrada**"+ANSI_RESET);
                continuar = Validaciones.validarEntrada("¿Desea ingresar otra? [S-N]: ", 1).trim().toUpperCase().charAt(0);
            }
        }                                            
        }
    }
    
    private static void vendedor_Op3(){
        Vendedor vendedor = ManejadorVendedor.inicioSesionVendedor();
        if (vendedor!=null){
            System.out.println("");
            Vehiculo vehiculo = ManejadorVehiculo.buscarPlaca(Validaciones.validarEntrada("Ingrese la placa: ", 3),vendedor);
            if (vehiculo == null)
                System.out.println(ANSI_RED+vendedor.getNombres()+" "+vendedor.getApellidos()+" no tienes vehiculos registrados con esa placa"+ANSI_RESET);
            else{
                Oferta oferta = mostrarOfertas_a_Vendedor(vehiculo);
                if (oferta!=null){
                    vendedor.vender(oferta);
                }else
                    System.out.println(ANSI_RED+"**No ha realizado ninguna venta**"+ANSI_RESET);
            }
        }
    }
        
    /**
     *
     */
}
