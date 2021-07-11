/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.actores.*;

import ec.edu.espol.model.usuarios.*;
import static ec.edu.espol.procesos.ManejadorCompraVenta.*;
import static ec.edu.espol.procesos.ManejadorComprador.buscarComprador;
import static ec.edu.espol.procesos.ManejadorVehiculo.*;
import static ec.edu.espol.procesos.ManejadorVendedor.buscarVendedor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import ventavehiculos.Main;
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
                    System.out.println(ANSI_RED+"**No ha aceptado ninguna oferta**"+ANSI_RESET);
            }
        }
    }
     /**
     *
     * 
     * 
     */   
        public static void menuComprador(){
        int op;
        System.out.println("Opciones del Comprador");
        System.out.println("1.- Registrar un nuevo comprador\n" + "2.- Ofertar por un vehículo\n"+"3.- Regresar\n");
        op = Validaciones.validarEntero("Elija una opcion: ",1,3);
        subMenuComprador(op);
    }
    
    private static void subMenuComprador(int op){
        System.out.println("");        
        switch(op){
            case 1:
                System.out.println(ANSI_BLUE+"Registrar nuevo comprador"+ANSI_RESET);
                comprador_Op1();
                break;
            case 2:
                System.out.println(ANSI_BLUE+"Ofertar por un vehiculo"+ANSI_RESET);
                comprador_Op2();
                break;
            default:
                break;
        }
        if(op!=3){
            System.out.println("");
            menuComprador();
        }
    }
    
    private static void comprador_Op1(){
        Comprador comprador = Comprador.crearComprador(new Scanner(System.in));
            if(comprador!=null){
                if (ManejadorComprador.file_anadirComprador(comprador))
                    System.out.println(ANSI_GREEN+"**Comprador creado exitosamente**"+ANSI_RESET);
                else
                    System.out.println(ANSI_RED+"**Ha ocurrido un error, comprador no creado**"+ANSI_RESET);
            }
            else
                System.out.println(ANSI_RED+"**Comprador ya existe, no ha sido creado ninguno nuevo**"+ANSI_RESET);
    }    
    
    private static void comprador_Op2(){
        Comprador comprador = ManejadorComprador.inicioSesionComprador();
       if (comprador!=null){
           filtro_vehiculosEnOferta(comprador);
       }
    }
    /**
     *
     */
    public static void leerVendedores(){
        try(BufferedReader br = new BufferedReader(new FileReader("archivos/vendedores.txt"))){
            String l = br.readLine();
            while((l=br.readLine())!=null){
                String[] data = l.split(",");
                Vendedor v = new Vendedor(data[0].trim(),data[1].trim(),new Correo(data[2].trim()),data[3].trim(),data[4].trim());
                vendedores.add(v);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     *
     */
    public static void leerCompradores(){
        try(BufferedReader br = new BufferedReader(new FileReader("archivos/compradores.txt"))){
            String l = br.readLine();
            while((l=br.readLine())!=null){
                String[] data = l.split(",");
                Comprador c = new Comprador(data[0].trim(),data[1].trim(),new Correo(data[2].trim()),data[3].trim(),data[4].trim());
                compradores.add(c);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     *
     */
    public static void leerVehiculos(){
        try(BufferedReader br = new BufferedReader(new FileReader("archivos/vehiculos.txt"))){
            String l = br.readLine();
            while((l=br.readLine())!=null){
                String[] data = l.split(",");                
                Usuario user = buscarVendedor(new Correo(data[0].trim()));
                TipoVehiculo tipo = TipoVehiculo.valueOf(data[1].trim());
                int ano = Integer.parseInt(data[6].trim());
                double recorrido = Double.parseDouble(data[7].trim());
                String color = data[8].trim();
                String tipoCombustible = data[9].trim();
                String vidrios = data[10].trim();
                String transmision = data[11].trim();
                String traccion = data[12].trim();
                double precio  = Double.parseDouble(data[13].trim());
                Vehiculo v;
                v = new Vehiculo(tipo,data[2].trim(),data[3].trim(),data[4].trim(),data[5].trim(),ano,recorrido,color,tipoCombustible,vidrios,transmision,traccion,precio);
                v.asignarDueno(user);
                asignarVehiculo_a_vendedor(v);
                vehiculos.add(v);
                
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private static  boolean asignarVehiculo_a_vendedor(Vehiculo v){
        for(Vendedor u: Main.vendedores){
            if (u.getCorreo_electrico().getEmail().equalsIgnoreCase(v.getDueno().getCorreo_electrico().getEmail())){
                u.agregarVehiculo(v);
                return true;
            }
        }
        return false;
    }
    /**
     *
     */
    public static void leerOfertas(){
        
        try(BufferedReader br = new BufferedReader(new FileReader("archivos/ofertas.txt"))){
            String l = br.readLine();
            while((l=br.readLine())!=null){
                String[] data = l.split(",");                

                Oferta oferta = new Oferta(buscarComprador(new Correo(data[0].trim())),buscarPlaca(data[1].trim()),Double.parseDouble(data[2]));
                //Oferta oferta = new Oferta(null,buscarPlaca(data[1].trim()),Double.parseDouble(data[2]));
                Main.ofertas.add(oferta);
                asignarOferta_a_Vehiculo(oferta);
                asignarOferta_a_Comprador(oferta);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     *
     * @param o
     * @return
     */
    public static boolean asignarOferta_a_Vehiculo(Oferta o){        
        for(Vehiculo v: Main.vehiculos){
            if (v.getPlaca().equalsIgnoreCase(o.getVehiculo().getPlaca())){
                v.agregarOferta(o);
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @param o
     * @return
     */
    public static boolean asignarOferta_a_Comprador(Oferta o){        
        for(Comprador c: Main.compradores){
            if (o.getComprador().equals(c)){
                c.agregarOferta(o);
                return true;
            }
        }
        return false;
    }
  
}
