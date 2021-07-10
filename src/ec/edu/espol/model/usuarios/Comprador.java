/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.usuarios;

import ec.edu.espol.model.actores.Oferta;
import java.util.ArrayList;

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
    }