/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.actores.TipoVehiculo;
import ec.edu.espol.model.actores.Vehiculo;
import ec.edu.espol.model.usuarios.Vendedor;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static ec.edu.espol.ventavehiculos.Main.ANSI_RED;
import static ec.edu.espol.ventavehiculos.Main.ANSI_RESET;
import static ec.edu.espol.ventavehiculos.Main.vehiculos;
/**
 *
 * 
 * @author Jose 
 */
public class ManejadorVehiculo {    
    /**
     *
     * @param placa
     * @return
     */
    public static Vehiculo buscarPlaca(String placa){
        for (Vehiculo v: vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa.replace(" ", "")))
                return v;
        }
        return null;
    }
    
    /**
     *
     * @param placa
     * @param vendedor
     * @return
     */
    public static Vehiculo buscarPlaca(String placa, Vendedor vendedor){
        for (Vehiculo v:vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa.replace(" ", "")) && vendedor.equals(v.getDueno()))
                return v;
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    public static TipoVehiculo elegirTipoVehiculo(){
        TipoVehiculo tipo = null;
        int totalTipos = TipoVehiculo.values().length;
        System.out.println("Tipo de vehiculo a registrar: ");
        int cont = 1;
        for (TipoVehiculo tp: TipoVehiculo.values()) {
            System.out.println(cont+"- "+tp);
            cont++;
        }
        List<TipoVehiculo> tpv = Arrays.asList(TipoVehiculo.values());
        int seleccion = Validaciones.validarEntero("Escriba el numero del tipo de vehiculo a registrar: ");
            while(seleccion<1 || seleccion>totalTipos)
            {
                System.out.println(ANSI_RED+"No valido"+ANSI_RESET);
                seleccion = Validaciones.validarEntero("Escriba el numero del tipo de vehiculo a registrar: ");
            }            
            tipo = tpv.get(seleccion-1);        
        return tipo;
    }
    
    /**
     *
     * @param v
     * @param vendedor
     * @return
     */
    public static boolean file_anadirVehiculo(Vehiculo v, Vendedor vendedor){
        Validaciones.exitenciaCarpeta("archivos");
        Path p = Paths.get("archivos/vehiculos.txt");
        boolean existe = Files.exists(p);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/vehiculos.txt",true))){
            if (!existe){
                bw.write("Vendedor, tipo, placa, marca, modelo, tipo de motor, año, recorrido, color, tipo de combustible, vidrios, transmision, traccion, precio\n");
            }
            bw.write(v.toString()+"\n");
            lista_anadirVehiculo(v);
            vendedor.agregarVehiculo(v);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param v
     */
    public static void lista_anadirVehiculo(Vehiculo v){
       vehiculos.add(v);
    }   
    
    
}

    

