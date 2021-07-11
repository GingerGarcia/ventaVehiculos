/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.actores.*;
import ec.edu.espol.model.usuarios.Comprador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static ec.edu.espol.ventavehiculos.Main.*;


/**
 *
 * 
 * @Jose Anchundia
 */
public class ManejadorCompraVenta {
    
    /**
     *
     * @param v
     * @return
     */
    public static Oferta mostrarOfertas_a_Vendedor(Vehiculo v){        
        Oferta oferta = null;
        if(v.getOfertas().isEmpty()){
            System.out.println(ANSI_RED+"No hay ofertas disponibles"+ANSI_RESET);
            return oferta;        
        }            
        int i = 0;
        boolean continuar = true;
        int tam = v.getOfertas().size();
        int op;
        do{
            oferta = v.getOfertas().get(i);
            System.out.println("");
            System.out.println(ANSI_BLUE+"Oferta "+(i+1)+ANSI_RESET);
            System.out.println("Correo: "+oferta.getComprador().getCorreo_electrico());
            System.out.println("Precio ofertado: $"+oferta.getPrecioOfertado()); 
            System.out.println("");
            if(i==0 && tam == 1){
                System.out.println("1.- Aceptar oferta\n2.- Regresar");
                System.out.println("");
                op = Validaciones.validarEntero("Seleccione: ", 1, 2);
                switch (op) {
                    case 1:
                        return oferta;
                    case 2:
                        continuar=false;
                        break;                    
                    default:
                        break;
                }
            } else if (i<(tam-1) && i==0){
                System.out.println("1.- Siguiente oferta\n2.- Aceptar oferta\n3.- Regresar");
                System.out.println("");
                op = Validaciones.validarEntero("Seleccione: ", 1, 3);
                switch (op) {
                    case 1:
                        i++;
                        break;
                    case 3:
                        continuar=false;
                        break;                    
                    default:
                        return oferta;
                }
            }else if (i<(tam-1)){
                System.out.println("1.- Siguiente oferta\n2.- Oferta anterior\n3.- Aceptar oferta\n4.- Regresar");
                System.out.println("");
                op = Validaciones.validarEntero("Seleccione: ", 1, 4);
                switch (op) {
                    case 1:
                        i++;
                        break;
                    case 2:
                        i--;
                        break;
                    case 4:
                        continuar=false;
                        break;    
                    default:
                        return oferta;
                }
            }else if(i==(tam-1) && i!=0){
                System.out.println("1.- Oferta anterior\n2.- Aceptar oferta\n3.- Regresar");
                System.out.println("");
                op = Validaciones.validarEntero("Seleccione: ", 1, 3);
                switch (op) {
                    case 3:
                        continuar=false;
                        break;
                    case 1:
                        i--;
                        break;
                    default:
                        return oferta;
                }
            }            
            System.out.println("");
        }while(continuar);
        return null;
    }
    
    /**
     *
     * @param c
     * @param tipo
     * @param rangoRecorrido
     * @param rangoAno
     * @param rangoPrecio
     * @return
     */
    public static Oferta mostrarVehiculosOfertados(Comprador c, TipoVehiculo tipo, double[] rangoRecorrido,  int[] rangoAno, double[] rangoPrecio){
        Oferta oferta = null;                     
        ArrayList<Vehiculo> vehiculoSeleccionados = filtrarVehiculos(tipo,rangoRecorrido,rangoAno,rangoPrecio);        
        if(vehiculoSeleccionados.isEmpty()){
            System.out.println(ANSI_RED+"No hay vehiculos que coincidan con su busqueda"+ANSI_RESET);
            return oferta;        
        }            
        int i = 0;
        boolean continuar = true;
        int tam = vehiculoSeleccionados.size();
        int op;
        Vehiculo vehiculo;
        do{
            vehiculo = vehiculoSeleccionados.get(i);
            System.out.println("");
            System.out.println(ANSI_BLUE+"Vehiculo "+(i+1)+ANSI_RESET);
            System.out.println("Correo del dueño: "+vehiculo.getDueno().getCorreo_electrico());
            System.out.println("Tipo de vehiculo: "+vehiculo.getTipo().name());
            System.out.println("Precio: $"+vehiculo.getPrecio()); 
            System.out.println("Marca: "+vehiculo.getMarca());
            System.out.println("Modelo: "+vehiculo.getModelo());
            System.out.println("Tipo de motor: "+vehiculo.getTipoMotor());
            System.out.println("Recorrido (km): "+vehiculo.getRecorrido());
            System.out.println("Color: "+vehiculo.getColor());
            System.out.println("Traccion: "+vehiculo.getTraccion());
            System.out.println("Año: "+vehiculo.getAño());
            System.out.println("");
            if(i==0 && tam == 1){
                System.out.println("1.- Hacer oferta\n2.- Regresar");
                System.out.println("");
                op = Validaciones.validarEntero("Seleccione: ", 1, 2);
                switch (op) {
                    case 1:
                        oferta = c.ofertar(vehiculo);
                        return oferta;
                    case 2:
                        continuar=false;
                        break;                    
                    default:
                        break;
                }
            } else if (i<(tam-1) && i==0){
                System.out.println("1.- Siguiente vehiculo\n2.- Hacer oferta\n3.- Regresar");
                System.out.println("");
                op = Validaciones.validarEntero("Seleccione: ", 1, 3);
                switch (op) {
                    case 1:
                        i++;
                        break;
                    case 2:
                        oferta = c.ofertar(vehiculo);
                        return oferta;
                    case 3:
                        continuar=false;
                        break;               
                    default:
                        break;
                }
            }else if (i<(tam-1)){
                System.out.println("1.- Vehiculo siguiente\n2.- Vehiculo anterior\n3.- Hacer oferta\n4.- Regresar");
                System.out.println("");
                op = Validaciones.validarEntero("Seleccione: ", 1, 4);
                switch (op) {
                    case 1:
                        i++;
                        break;
                    case 2:
                        i--;
                        break;
                    case 3:
                        oferta = c.ofertar(vehiculo);
                        return oferta;
                    case 4:
                        continuar=false;
                        break;    
                    default:
                        break;
                }
            }else if(i==(tam-1) && i!=0){
                System.out.println("1.- Vehiculo anterior\n2.- Hacer oferta\n3.- Regresar");
                System.out.println("");
                op = Validaciones.validarEntero("Seleccione: ", 1, 3);
                switch (op) {
                    case 3:
                        continuar=false;
                        break;
                    case 2:
                        oferta = c.ofertar(vehiculo);
                        return oferta;
                    case 1:
                        i--;
                        break;
                    default:
                        break;
                }
            }            
            System.out.println("");
        }while(continuar);
        return null;       
    }
    
    /**
     *
     * @param c
     */
    public static void filtro_vehiculosEnOferta(Comprador c){        
        System.out.println(ANSI_RED+"*Parametros de busqueda*"+ANSI_RESET);
        TipoVehiculo tipoVehiculo = Validaciones.validarTipoVehiculo("Tipo de vehiculo: ");
        double[] recorrido = Validaciones.validarRangosDouble("Rango del recorrido (valorInicio - valorFin): ");
        int[] ano = Validaciones.validarRangosInt("Rango del año (valorInicio - valorFin): ");
        double[] precio = Validaciones.validarRangosDouble("Rango del precio (valorInicio - valorFin): ");
        Oferta oferta = mostrarVehiculosOfertados(c,tipoVehiculo,recorrido,ano,precio);
        if(oferta!=null)
            System.out.println(ANSI_GREEN+"**Se ha agregado su oferta**"+ANSI_RESET);
        else
            System.out.println(ANSI_RED+"**No se ha realizado ninguna oferta**"+ANSI_RESET);
    }
    
    private static ArrayList<Vehiculo> filtrarVehiculos(TipoVehiculo tipo, double[] rangoRecorrido,  int[] rangoAno, double[] rangoPrecio){
        ArrayList<Vehiculo> vehiculoSeleccionados = new ArrayList<>();  
        if(tipo!=null){
            for(Vehiculo v: vehiculos){
                if (v.getTipo().equals(tipo) && v.getRecorrido()>=rangoRecorrido[0] && v.getRecorrido()<=rangoRecorrido[1]
                        && v.getAño()>=rangoAno[0] && v.getAño()<=rangoAno[1] && v.getPrecio()>=rangoPrecio[0] && v.getPrecio()<=rangoPrecio[1])
                    vehiculoSeleccionados.add(v);                
            }
        }else{
            for(Vehiculo v: vehiculos){
                if ((v.getRecorrido()>=rangoRecorrido[0] && v.getRecorrido()<=rangoRecorrido[1])
                        && (v.getAño()>=rangoAno[0] && v.getAño()<=rangoAno[1]) 
                        && (v.getPrecio()>=rangoPrecio[0] && v.getPrecio()<=rangoPrecio[1]))
                    vehiculoSeleccionados.add(v);                
            }
        }            
        return vehiculoSeleccionados;
    }
    
    /**
     *
     * @param oferta
     * @return
     */
    public static boolean file_removerVehiculoOfertado(Oferta oferta){
        Validaciones.exitenciaCarpeta("archivos");
        Path p = Paths.get("archivos/vehiculos.txt");
        try {
            Files.deleteIfExists(p.getFileName());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }            
        vehiculos.remove(oferta.getVehiculo());
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/vehiculos.txt",false))){            
            bw.write("Vendedor, tipo, placa, marca, modelo, tipo de motor, año, recorrido, color, tipo de combustible, vidrios, transmision, traccion, precio\n");            
            for(Vehiculo v: vehiculos){
                bw.write(v.toString()+"\n");
            }            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param oferta
     * @return
     */
    public static boolean file_removerOfertasParaVehiculo(Oferta oferta){
        ofertas.remove(oferta);
        Validaciones.exitenciaCarpeta("archivos");
        Path p = Paths.get("archivos/ofertas.txt");
        try {
            Files.deleteIfExists(p.getFileName());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/ofertas.txt",false))){
            bw.write("Comprador,placaVehiculo,precioOfertado\n");            
            for(Oferta o: ofertas){
                bw.write(o+"\n");
            }            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param oferta
     * @return
     */
    public static boolean file_anadirOfertas(Oferta oferta){
        Validaciones.exitenciaCarpeta("archivos");
        Path p = Paths.get("archivos/ofertas.txt");
        boolean existe = Files.exists(p);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/ofertas.txt",true))){
            if (!existe){
                bw.write("correoComprador, placaVehiculo, precioOfertado\n");
            }
            bw.write(oferta.getComprador().getCorreo_electrico()+", "+oferta.getVehiculo().getPlaca()+", "+oferta.getPrecioOfertado()+"\n");
            agregarOferta(oferta);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     *
     * @return
     */
    public static boolean file_actualizarOfertaParaVehiculo(){
        Validaciones.exitenciaCarpeta("archivos");
        Path p = Paths.get("archivos/ofertas.txt");
        try {
            Files.deleteIfExists(p.getFileName());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/ofertas.txt",false))){
            bw.write("Comprador,placaVehiculo,precioOfertado\n");            
            for(Oferta o: ofertas){
                bw.write(o+"\n");
            }            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    private static void agregarOferta(Oferta o){
        ofertas.add(o);
    }
}
