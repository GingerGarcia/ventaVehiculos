/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.procesos;

import ec.edu.espol.model.actores.TipoVehiculo;
import ec.edu.espol.model.usuarios.Correo;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static ventavehiculos.Main.*;

/**
 *
 * 
 * @author Jose
 */
public class Validaciones {
    static Scanner sc = new Scanner (System.in);
    
    /**
     *
     * @param mensaje Mensaje a mostrarle al usuario por consola previo al ingreso de datos
     * @return el entero solicitado
     */
    public static int validarEntero(String mensaje){
        boolean valido = true;       
        int numero = -1;
        while(valido){
            try{
                System.out.print(mensaje);
                numero = sc.nextInt();
                valido = false;
            }catch(Exception ex){
                System.out.println(ANSI_RED+"Ingrese valores numericos enteros"+ANSI_RESET);
                sc.nextLine();
            }                        
        }
        sc.nextLine();
        return numero;
    }
    
    /**
     *
     * @param mensaje Mensaje a mostrarle al usuario por consola previo al ingreso de datos
     * @param inicio valor de inicio del rango
     * @param fin valor de final del rango
     * @return Un entero que se encuentren entre el valor de inicio y el final incluidos 
     */
    public static int validarEntero(String mensaje, int inicio, int fin){        
        boolean valido = true;       
        int numero = -1;
        while(valido){
            try{
                System.out.print(mensaje);
                numero = sc.nextInt();
                if(numero>=inicio && numero<=fin)
                    valido = false;
                else
                    System.out.println(ANSI_RED+"Valor no valido"+ANSI_RESET);
            }catch(Exception ex){
                System.out.println(ANSI_RED+"Ingrese valores numericos enteros"+ANSI_RESET);
                sc.nextLine();
            }                        
        }
        sc.nextLine();
        return numero;
    }    
    
    /**
     *
     * @param mensaje Mensaje a mostrarle al usuario por consola previo al ingreso de datos
     * @return Un objeto de tipo correo que cumpla con la estructura de un correo electronico
     */
    public static Correo validarCorreo(String mensaje){
        Correo correo;
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
         
        System.out.print(mensaje);
        String email = sc.nextLine().trim();
        
        Matcher mather = pattern.matcher(email);
        
        while(!mather.find()){
            System.out.println(ANSI_RED+"El email ingresado no es valido"+ANSI_RESET);
            System.out.print(mensaje);
            email = sc.nextLine().trim();
            mather = pattern.matcher(email);
        }        
        correo = new Correo(email);
    
        return correo;
    }
    
    /**
     *
     * @param mensaje Mensaje a mostrarle al usuario por consola previo al ingreso de datos
     * @param longitud Minimo de letras que debe tener la cadena
     * @return Una cadena de solo letras con el minimo de letras (longitud)
     */
    public static String validarCadena(String mensaje, int longitud){
        System.out.print(mensaje);
        String data = sc.nextLine().trim();   
        while (!comprobarLetras(data)|| data.trim().length()<longitud){
            if(data.trim().length()<longitud)
                System.out.println(ANSI_RED+"Texto no ingresado (min: "+longitud+" letras)"+ANSI_RESET);
            else 
                System.out.println(ANSI_RED+"Texto ingresado no valido, solo se permiten letras"+ANSI_RESET);
            System.out.print(mensaje);
            data = sc.nextLine().trim();            
            
        }
        return data;
    }
    
    /**
     *
     * @param mensaje Mensaje a mostrarle al usuario por consola previo al ingreso de datos
     * @param longitud minimo de letras que puede tener la palabra ingresada
     * @return Una cadena que cumpla con el minimo de longitud de cadena
     */
    public static String validarEntrada(String mensaje, int longitud){
        System.out.print(mensaje);
        String data = sc.nextLine().trim();   
        while (data.trim().length()<longitud){
            if(data.trim().length()<longitud)
                System.out.println(ANSI_RED+"Texto no ingresado (minimo de caracteres: "+longitud+" letras)"+ANSI_RESET);
            else 
                System.out.println(ANSI_RED+"Texto ingresado no valido"+ANSI_RESET);
            System.out.print(mensaje);
            data = sc.nextLine().trim();            
        }
        return data;
    }
    
    /**
     * 
     * @param cadena Un mensaje para mostrarle al usuario previo a la pedida de datos
     * @return Verdadero si la cadena contiene solo letras y falso si la cadena tiene algun caracter que no sea letra
     */
    private static boolean comprobarLetras(String cadena){
        cadena = cadena.replace(" ", "");
        List<Character> caracteres = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
        ,'á','é','í','ó','ú','ñ','Á','É','Í','Ó','Ú','Ñ','�');
        for (int i = 0; i < cadena.length(); i++) {
            if(!caracteres.contains(cadena.toUpperCase().charAt(i)))
                return false;

        }
        return true;
    }
    
    /**
     * Comprueba si el directorio existe y en caso de no existir lo crea
     * @param ruta Ruta del directorio
     */
    public static void exitenciaCarpeta(String ruta){
        File directorio = new File(ruta);
        if(!directorio.exists()){
            if (!directorio.mkdirs()) {
                System.out.println("Error al crear directorio");
            } 
        }
    }
    
    /**
     *
     * @param mensaje Mensaje a mostrarle al usuario por consola previo al ingreso de datos
     * @return Un valor de tipo double
     */
    public static double validarDouble(String mensaje){
        boolean valido = true;       
        double numero = -1;
        while(valido){
            try{
                System.out.print(mensaje);
                numero = sc.nextDouble();
                valido = false;
            }catch(Exception ex){
                System.out.println(ANSI_RED+"Ingrese valores numericos"+ANSI_RESET);
                sc.nextLine();
            }                        
        }
        sc.nextLine();
        return numero;
    }
    
    /**
     * Este metodo deben ingresar los datos separados por "-" 
     * Si son dos valores el valor en la posicion 0 sera el numero menor, y el valor en la posicion 1 el mayor
     * Si solo ingresa un valor el primero valor del arreglo será el valor ingresado por el usuario y el segundo valor del arreglo será el maximo de Double
     * Si no ingresa ninguno valor, el primero valor del arreglo será 0 y el segundo valor será el valor maximo de Double
     * @param mensaje Mensaje a mostrarle al usuario por consola previo al ingreso de datos
     * @return un arreglo de double de dos valores 
     */
    public static double[] validarRangosDouble(String mensaje){
        double [] rangos = new double[2];      
        String datos;       
        while(true){
            System.out.print(mensaje);
            datos = sc.nextLine().trim();        
            String [] data = datos.split("-");
            if(datos.length()== 0){
                rangos[0] = 0;
                rangos[1] = Double.MAX_VALUE;
                return rangos;
            }
            else if(data.length==1){
                if(isDouble(data[0])){
                    rangos[0] = Double.parseDouble(data[0].trim());
                    rangos[1] = Double.MAX_VALUE;
                    return rangos;
                }else{
                    System.out.println(ANSI_RED+"Valor no valido"+ANSI_RESET);
                }
            }else if(data.length==2){
                if(isDouble(data[0].trim()) && isDouble(data[1].trim()) ){
                    if(Double.parseDouble(data[0].trim())>Double.parseDouble(data[1].trim())){
                          System.out.println(ANSI_RED+"El valor inicial no puede ser mayor que el final"+ANSI_RESET);
                    }else{
                        rangos[0] = Double.parseDouble(data[0].trim());
                        rangos[1] = Double.parseDouble(data[1].trim());
                        return rangos;
                    }
                }else
                    System.out.println(ANSI_RED+"Valor no valido"+ANSI_RESET);
            }else
                System.out.println(ANSI_RED+"Valor no valido"+ANSI_RESET);
            
        }        
    }
        
    /**
     *
     * @param cadena Cadena a verificar si es double o no
     * @return Verdadero si la cadena recibida por parametro es double, y falso en caso contrario
     
     */
    public static boolean isDouble(String cadena){
        try{
            Double.parseDouble(cadena.trim());
            return true;                    
        }catch(NumberFormatException ex){
            return false;
        }        
    }
    
    /**
     * En este metodo deben ingresar los datos separados por "-" (Si no se usa el guion,el metodo no tomará en cuenta los vlaores ingreados y los volverá a pedir)
     * Si son dos valores el valor en la posicion 0 sera el numero menor, y el valor en la posicion 1 el mayor
     * Si solo ingresa un valor el primero valor del arreglo será el valor ingresado por el usuario y el segundo valor del arreglo será el maximo de Double
     * Si no ingresa ninguno valor, el primero valor del arreglo será 0 y el segundo valor será el valor maximo de Double
     * @param mensaje Mensaje a mostrarle al usuario por consola previo al ingreso de datos
     * @return un arreglo de double de dos valores 
     */
    public static int[] validarRangosInt(String mensaje){
        int [] rangos = new int[2];      
        String datos;        
        while(true){
            System.out.print(mensaje);
            datos = sc.nextLine().trim();      
            String [] data = datos.split("-");
            if(datos.length()== 0){
                rangos[0] = 0;
                rangos[1] = Integer.MAX_VALUE;
                return rangos;
            }
            else if(data.length==1){
                if(isInteger(data[0])){
                    rangos[0] = Integer.parseInt(data[0].trim());
                    rangos[1] = Integer.MAX_VALUE;
                    return rangos;
                }else{
                    System.out.println(ANSI_RED+"Valor no valido"+ANSI_RESET);
                }
            }else if(data.length==2){
                if(isInteger(data[0].trim()) && isInteger(data[1].trim()) ){
                    if(Double.parseDouble(data[0].trim())>Double.parseDouble(data[1].trim())){
                          System.out.println(ANSI_RED+"El valor inicial no puede ser mayor que el final"+ANSI_RESET);
                    }else{
                        rangos[0] = Integer.parseInt(data[0].trim());
                        rangos[1] = Integer.parseInt(data[1].trim());
                        return rangos;
                    }
                }else
                    System.out.println(ANSI_RED+"Valor no valido"+ANSI_RESET);
            }else
                System.out.println(ANSI_RED+"Valor no valido"+ANSI_RESET);
            
        }                        
    }
    
    /**
     *
     * @param cadena Cadena que se va a validar si es entero o no
     * @return Verdadero si la cadena recibida por parametro es entero, y falso en caso contrario.
     */
    public static boolean isInteger(String cadena){
        try{
            Integer.parseInt(cadena.trim());
            return true;                    
        }catch(NumberFormatException ex){
            return false;
        }        
    }
    
    /**
     * 
     * @param mensaje Mensaje a mostrar al usuario en cadena previo a pedir los datos
     * @return unobjeto de tipo tipoVehiculo que fue elegido por el usuario, y null en caso de que no quiera crear el objeto TipoVehiculo
     */
    public static TipoVehiculo validarTipoVehiculo(String mensaje){
        TipoVehiculo tipo = null;
        int totalTipos = TipoVehiculo.values().length;
        int cont = 1;
        for (TipoVehiculo tp: TipoVehiculo.values()) {
            System.out.println(cont+"- "+tp);
            cont++;
        }
        List<TipoVehiculo> tpv = Arrays.asList(TipoVehiculo.values());
        String seleccion;
        String continuar;
        while(true){
            seleccion = Validaciones.validarEntrada(mensaje,0);
            if(seleccion.trim().isEmpty()){                
                return tipo;
            }else{
                if(isInteger(seleccion) && (Integer.parseInt(seleccion)<0 || Integer.parseInt(seleccion)>totalTipos)){
                    continuar = validarEntrada(ANSI_RED+"No valido, ¿Desea ingresar un tipo de vehiculo? [S-N]: "+ANSI_RESET,1);
                    if(continuar.charAt(0)=='N')
                        return tipo;
                }else if(isInteger(seleccion) && (Integer.parseInt(seleccion)>=0 && Integer.parseInt(seleccion)<=totalTipos))
                    return tpv.get(Integer.parseInt(seleccion)-1);
                else{
                    continuar = validarEntrada(ANSI_RED+"No valido, ¿Desea ingresar un tipo de vehiculo? [S-N]: "+ANSI_RESET,1);
                        if(continuar.trim().toUpperCase().charAt(0)=='N')
                            return tipo;
                    
                }                    
            }
        }
    }
}
