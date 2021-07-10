/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.actores;

import ec.edu.espol.model.usuarios.Comprador;
import java.util.Objects;

/**
 *
 * @author Jose Anchundia , Jose Murillo
 */
public class Oferta {
    private Comprador comprador;
    private Vehiculo vehiculo;
    private double precioOfertado;

    /**
     *
     * @param comprador
     * @param vehiculo
     * @param precioOfertado
     */
    public Oferta(Comprador comprador, Vehiculo vehiculo, double precioOfertado) {
        this.comprador = comprador;
        this.vehiculo = vehiculo;
        this.precioOfertado = precioOfertado;
    }

    /**
     *
     * @return
     */
    public Comprador getComprador() {
        return comprador;
    }

    /**
     *
     * @param comprador
     */
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    /**
     *
     * @return
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     *
     * @param vehiculo
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     *
     * @return
     */
    public double getPrecioOfertado() {
        return precioOfertado;
    }

    /**
     *
     * @param precioOfertado
     */
    public void setPrecioOfertado(double precioOfertado) {
        this.precioOfertado = precioOfertado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.comprador);
        hash = 53 * hash + Objects.hashCode(this.vehiculo);
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
        final Oferta other = (Oferta) obj;
        if (!Objects.equals(this.comprador, other.comprador)) {
            return false;
        }
        if (!Objects.equals(this.vehiculo, other.vehiculo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return comprador.getCorreo_electrico() + ", " + vehiculo.getPlaca() + ", " + precioOfertado;
    }
    
}

