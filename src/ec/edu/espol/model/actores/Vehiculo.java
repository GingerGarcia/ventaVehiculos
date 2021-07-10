/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.actores;

import ec.edu.espol.model.usuarios.Usuario;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * 
 * @author User
 */
public class Vehiculo {
    private TipoVehiculo tipo;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private int año;
    private double recorrido;
    private String color;
    private String tipoCombustible;
    private String vidrios;
    private String transmision;
    private String traccion;
    private double precio;
    
    private Usuario dueno;
    private ArrayList<Oferta> ofertas;

    /**
     *
     * @param tipo
     * @param marca
     * @param placa
     * @param modelo
     * @param tipoMotor
     * @param año
     * @param tipoCombustible
     * @param transmision
     * @param recorrido
     * @param precio
     * @param vidrios
     * @param traccion
     */
    public Vehiculo(TipoVehiculo tipo, String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, 
            String tipoCombustible, String vidrios, String transmision, String traccion, double precio) {
        this.tipo = tipo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        ofertas = new ArrayList<>();
    }

    /**
     *
     * @param user
     */
    public void asignarDueno(Usuario user){
        this.dueno  = user;
    }

    /**
     *
     * @return
     */
    public Usuario getDueno() {
        return dueno;
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
     * @param dueno
     */
    public void setDueno(Usuario dueno) {
        this.dueno = dueno;
    }

    /**
     *
     * @return
     */
    public TipoVehiculo getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public String getPlaca() {
        return placa;
    }

    /**
     *
     * @param placa
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     *
     * @return
     */
    public String getMarca() {
        return marca;
    }

    /**
     *
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     *
     * @return
     */
    public String getModelo() {
        return modelo;
    }

    /**
     *
     * @param modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     *
     * @return
     */
    public String getTipoMotor() {
        return tipoMotor;
    }

    /**
     *
     * @param tipoMotor
     */
    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    /**
     *
     * @return
     */
    public int getAño() {
        return año;
    }

    /**
     *
     * @param año
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     *
     * @return
     */
    public double getRecorrido() {
        return recorrido;
    }

    /**
     *
     * @param recorrido
     */
    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    /**
     *
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    public String getTipoCombustible() {
        return tipoCombustible;
    }

    /**
     *
     * @param tipoCombustible
     */
    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    /**
     *
     * @return
     */
    public String getVidrios() {
        return vidrios;
    }

    /**
     *
     * @param vidrios
     */
    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    /**
     *
     * @return
     */
    public String getTransmision() {
        return transmision;
    }

    /**
     *
     * @param transmision
     */
    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    /**
     *
     * @return
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public String getTraccion() {
        return traccion;
    }

    /**
     *
     * @param traccion
     */
    public void setTraccion(String traccion) {
        this.traccion = traccion;
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
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.placa);
        return hash;
    }
    
    /**
     * Anotaciones por si a caso 
     * @param sc objeto tipo Scanner
     * @param tipo objeto TipoVehiculo
     * @return un objeto tipo vehiculo con los datos ingresador por el usuario
     */
    public static Vehiculo crearVehiculo(Scanner sc, TipoVehiculo tipo){
        return null;   
    }
    
    /**
     *
     * @param o
     */
    public void agregarOferta(Oferta o){
        this.ofertas.add(o);
    }

    @Override
    public String toString() {
        return dueno.getCorreo_electrico()+", "+tipo.toString() + ", " + placa + ", " + marca + ", " + modelo + ", " + tipoMotor + ", " + año + ", " + recorrido + ", "+ color 
                + ", " + tipoCombustible + ", " + vidrios + ", " + transmision + ", " + traccion + ", " + precio;
    }
    
    
    
}
