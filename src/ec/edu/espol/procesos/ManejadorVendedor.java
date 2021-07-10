/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.usuarios.*;


/**
 *
 * 
 * @author User
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
}
