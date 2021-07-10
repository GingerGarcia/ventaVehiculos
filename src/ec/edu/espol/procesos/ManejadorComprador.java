/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.actores.*;
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
public class ManejadorComprador {

    /**
     *
     * @param correo
     * @return
     */
    public static Comprador buscarComprador(Correo correo){
        for(Comprador c: compradores){
            if (c.getCorreo_electrico().equals(correo))
                return c;
        }
    return null;
    }
    
    /**
     *
     * @param c
     * @return
     */
    public static boolean file_anadirComprador(Comprador c){
        Validaciones.exitenciaCarpeta("archivos");
        Path p = Paths.get("archivos/compradores.txt");
        boolean existe = Files.exists(p);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/compradores.txt",true))){
            if (!existe){
                bw.write("Nombres, apellidos, correo, organizacion, clave\n");
            }
            bw.write(c.getNombres()+", "+c.getApellidos()+", "+c.getCorreo_electrico()+", "+c.getOrganizacion()+", "+HashCode.toHexString(HashCode.getSHA(c.getClave()))+"\n");
            c.setClave(HashCode.toHexString(HashCode.getSHA(c.getClave())));
            almacenarComprador(c);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
         
    /**
     *
     * @param c
     */
    public static void almacenarComprador(Comprador c){
       compradores.add(c);
    }
    
    /**
     *
     * @return
     */
    public static Comprador inicioSesionComprador(){
        boolean valido;
        System.out.println(ANSI_BLUE+"\t-Iniciar sesion-"+ANSI_RESET);
        Correo correo = Validaciones.validarCorreo("Correo electronico: ");
        String contra = Validaciones.validarEntrada("Contraseña: ", 1);
        Comprador comprador = buscarComprador(correo);
        if(comprador!=null){
            valido = comprador.getClave().equals(HashCode.toHexString(HashCode.getSHA(contra)));            
            if(valido){
                System.out.println(ANSI_GREEN+"**Inicio de sesion existoso**"+ANSI_RESET);
                System.out.println("\nBienvenido "+comprador.getNombres());
                return comprador;
            }                
        }        
        System.out.println(ANSI_RED+"Usuario y/o contraseña incorrecto(s)"+ANSI_RESET);
        return null;
    }
}

