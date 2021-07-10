/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.usuarios;

import ec.edu.espol.model.actores.Oferta;
import ec.edu.espol.model.actores.Vehiculo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 *
 * @author Ginger
 */
public class Vendedor extends Usuario{
    
    private ArrayList<Vehiculo> ventasOfertadas;
    
    /**
     *
     * @param nombres
     * @param apellidos
     * @param correo_electrico
     * @param organizacion
     * @param clave
     */
    public Vendedor(String nombres, String apellidos, Correo correo_electrico, String organizacion, String clave) {
        super(nombres, apellidos, correo_electrico, organizacion, clave);
        ventasOfertadas = new ArrayList<>();
    }
    
    /**
     *
     * @param sc
     * @return
     */
    public static Vendedor crearVendedor(Scanner sc){
       return null;
    }
    
    /**
     *
     * @param v
     */
    public void agregarVehiculo(Vehiculo v){
        ventasOfertadas.add(v);
    }

    /**
     *
     * @return
     */
    public ArrayList<Vehiculo> getVentasOfertadas() {
        return ventasOfertadas;
    }

    /**
     *
     * @param ventasOfertadas
     */
    public void setVentasOfertadas(ArrayList<Vehiculo> ventasOfertadas) {
        this.ventasOfertadas = ventasOfertadas;
    }
    
    /**
     *
     * @param oferta
     */
    public void vender(Oferta oferta){        
        
    }
}
