/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.usuarios;

import ec.edu.espol.model.actores.Oferta;
import ec.edu.espol.procesos.ManejadorMain;
import ec.edu.espol.procesos.ManejadorVendedor;
import ec.edu.espol.procesos.Validaciones;
import java.util.ArrayList;
import java.util.Scanner;
import static ventavehiculos.Main.*;

/**
 *
 * 
 * @author Ginger
 */
public class Comprador extends Usuario{
    private ArrayList<Oferta> ofertas;
    
    /**
     *
     * @param nombres
     * @param apellidos
     * @param correo_electrico
     * @param organizacion
     * @param clave
     */
    public Comprador(String nombres, String apellidos, Correo correo_electrico, String organizacion, String clave) {
        super(nombres, apellidos, correo_electrico, organizacion, clave);
        ofertas = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    /**
     *
     * @param ofertas
     */
    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    /**
     *
     * @param oferta
     */
    public void agregarOferta(Oferta oferta){
        ofertas.add(oferta);    
    }
    
    public static Comprador crearComprador(Scanner sc){
        String nombre = Validaciones.validarCadena("Nombres: ", 2);
        String apellido = Validaciones.validarCadena("Apellidos: ", 2);
        String organizacion = Validaciones.validarCadena("Organizacion: ", 2);
        Correo correo = Validaciones.validarCorreo("Correo electronico: ");
        boolean continuar = ManejadorMain.buscarUsuario(correo)!=null;
        while (continuar){
            System.out.println(ANSI_RED+"Correo ya registado"+ANSI_RESET);
            System.out.print("¿Desea ingresar otro correo [S(i)-N(o)]? ");
            char siNo = sc.nextLine().toUpperCase().charAt(0);
            continuar = siNo=='S';
            if (continuar){
                correo = Validaciones.validarCorreo("Correo electronico: ");
            }            
            continuar = ManejadorVendedor.buscarVendedor(correo)!=null;
        }        
        String clave = Validaciones.validarEntrada("Clave: ",8);
        Comprador comprador = new Comprador(nombre,apellido,correo,organizacion,clave);
        return comprador;
    }
}