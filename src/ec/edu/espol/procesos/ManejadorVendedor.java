/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.actores.HashCode;
import ec.edu.espol.model.usuarios.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static ventavehiculos.Main.*;


/**
 *
 * 
 * @author Jose Anchundia
 */
public class ManejadorVendedor {
    
    /**
     *
     * @param correo
     * @return
     */
    public static Vendedor buscarVendedor(Correo correo){
        for(Vendedor v: vendedores){
            if (v.getCorreo_electrico().equals(correo))
                return v;
        }
        return null;
    }    
    
    public static boolean file_anadirVendedor(Vendedor v){
        Validaciones.exitenciaCarpeta("archivos");
        Path p = Paths.get("archivos/vendedores.txt");
        boolean existe = Files.exists(p);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/vendedores.txt",true))){
            if (!existe){
                bw.write("Nombres, apellidos, correo, organizacion, clave\n");
            }
            bw.write(v.getNombres()+", "+v.getApellidos()+", "+v.getCorreo_electrico()+", "+v.getOrganizacion()+", "+HashCode.toHexString(HashCode.getSHA(v.getClave()))+"\n");
            v.setClave(HashCode.toHexString(HashCode.getSHA(v.getClave())));
            almacenarVendedor(v);
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
    public static void almacenarVendedor(Vendedor v){
       vendedores.add(v);
    }
    
    /**
     *
     * @return
     */
    public static Vendedor inicioSesionVendedor(){
        boolean valido;
        System.out.println(ANSI_BLUE+"\t-Iniciar sesion-"+ANSI_RESET);
        Correo correo = Validaciones.validarCorreo("Correo electronico: ");
        String contra = Validaciones.validarEntrada("Contraseña: ", 1);
        Vendedor v = buscarVendedor(correo);
        if(v!=null){
            valido = v.getClave().equals(HashCode.toHexString(HashCode.getSHA(contra)));            
            if(valido){
                System.out.println(ANSI_GREEN+"**Inicio de sesion existoso**"+ANSI_RESET);
                System.out.println("\nBienvenido "+v.getNombres());
                return v;
            }                
        }        
        System.out.println(ANSI_RED+"Usuario y/o contraseña incorrecto(s)"+ANSI_RESET);
        return null;
    }
}
