/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.usuarios;
/**
 *
 * @author Ginger
 */
public class Correo {
    
    private String email;
    private final String remitente = "ventadevehiculosproyecto@hotmail.com";
    private static String clave = "12345678VV";
    
    public Correo(String email) {
        this.email = email;
    }       

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
   
    
}
