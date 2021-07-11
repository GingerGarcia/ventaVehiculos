/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.usuarios;

import ec.edu.espol.procesos.ManejadorMain;
import ec.edu.espol.model.actores.Oferta;
import ec.edu.espol.model.actores.Vehiculo;
import static ec.edu.espol.procesos.ManejadorCompraVenta.file_removerOfertasParaVehiculo;
import static ec.edu.espol.procesos.ManejadorCompraVenta.file_removerVehiculoOfertado;
import ec.edu.espol.procesos.ManejadorVendedor;
import ec.edu.espol.procesos.Validaciones;
import java.util.ArrayList;
import java.util.Scanner;
import static ec.edu.espol.ventavehiculos.Main.*;

/**
 *
 *
 * @author Ginger, Anchundia
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
    
    public static Vendedor crearVendedor(Scanner sc){
        Vendedor vendedor = null;
        String nombre = Validaciones.validarCadena("Nombres: ", 2);
        String apellido = Validaciones.validarCadena("Apellidos: ", 2);
        String organizacion = Validaciones.validarCadena("Organizacion: ", 2);
        Correo correo = Validaciones.validarCorreo("Correo electronico: ");
        boolean continuar = ManejadorMain.buscarUsuario(correo)!=null;
        while (continuar){
            System.out.println(ANSI_RED+"Correo ya registado"+ANSI_RESET);
            correo = null;
            System.out.print("¿Desea ingresar otro correo? [S(i)-N(o)]: ");
            char siNo = sc.nextLine().toUpperCase().charAt(0);
            if (siNo=='S'){
                correo = Validaciones.validarCorreo("Correo electronico: ");
                continuar = ManejadorVendedor.buscarVendedor(correo)!=null;
            }else{
                continuar = false;
            }                        
        }        
        if(correo!=null){
            String clave = Validaciones.validarEntrada("Clave: ",8);
            vendedor = new Vendedor(nombre,apellido,correo,organizacion,clave);
        }        
        return vendedor;
    }
    
    /**
     *
     * @param oferta
     */
    public void vender(Oferta oferta){        
        this.ventasOfertadas.remove(oferta.getVehiculo());    
        
        String mensaje = "Estimado(a) "+ oferta.getComprador().getNombres() +" "+oferta.getComprador().getApellidos()+" "+
                ":\nEl usuario \'" + this.getNombres() + " " +this.getApellidos()+
                "\' ha aceptado su oferta para la compra del vehículo \'" +oferta.getVehiculo().getModelo()
                +"\' con placa "+oferta.getVehiculo().getPlaca()+
                ".\nSu compra ha sido exitosa.";
        Correo correo_ = oferta.getComprador().getCorreo_electrico();
        boolean enviado = correo_.enviarCorreo("CONFIRMACION COMPRA VEHICULO", mensaje);
        if (!enviado)
            System.out.println(ANSI_RED+"**No se ha podido enviar el correo de confirmacion al email del cliente**"+ANSI_RESET);
        else
            System.out.println(ANSI_GREEN+"**Se ha enviado los detalles de la confirmacion al cliente**"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"**Se ha realizado la venta**"+ANSI_RESET);
        file_removerVehiculoOfertado(oferta);
        file_removerOfertasParaVehiculo(oferta);
     }
}
