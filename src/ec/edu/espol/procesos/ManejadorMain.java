/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.usuarios.*;
import static ventavehiculos.Main.*;

/**
 *
 * @author User
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
}
