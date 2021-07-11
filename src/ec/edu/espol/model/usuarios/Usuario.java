/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.usuarios;

import java.util.Objects;


/**
 *
 * @author Ginger
 */
public abstract class Usuario {
    protected String nombres;
    protected String apellidos;
    protected Correo correo;
    protected String organizacion;    
    protected String clave;

    public Usuario(String nombres, String apellidos, Correo correo_electrico, String organizacion, String clave) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo_electrico;
        this.organizacion = organizacion;
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Correo getCorreo_electrico() {
        return correo;
    }

    public void setCorreo_electrico(Correo correo_electrico) {
        this.correo = correo_electrico;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return nombres + ", " + apellidos + ", " + correo + ", " + organizacion + ", " + clave;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.correo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }
    
}
