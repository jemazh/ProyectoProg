package modelo;

import javax.swing.JTextField;

/**
 * Representa un socio y contiene todos sus datos y métodos necesarios.
 * @author Mario
 * @version 20/05/2016
 */
public class Socio {
    private String codSoc;
    private String nombre;
    private String apellido;
    private String dniNif;
    private String direccion;
    private String tlfMovil;
    
    //cabecera-->Me permitirá la modificación del código de una forma más sencilla
    static String [] cabecera={"COD_SOC","NOMBRE","APELLIDOS","DNI/NIF",
                                "DIRECCION","TELEFONO"};

    /**
     * Constructor de la Clase Socio
     * @param codSoc Número que representa el codigo
     * @param nombre String con el nombre
     * @param apellido String con los apellidos
     * @param dniNif String con el DNI o NIF
     * @param direccion String con la direccion
     * @param tlfMovil String con el telefono móvil
     */    
    public Socio(String codSoc, String nombre, String apellido, String dniNif, 
                 String direccion, String tlfMovil) {
        this.codSoc = codSoc;
        this.nombre = nombre.toUpperCase();
        this.apellido = apellido.toUpperCase();
        this.dniNif = dniNif.toUpperCase();
        this.direccion = direccion.toUpperCase();
        this.tlfMovil = tlfMovil.toUpperCase();
    }
    
    /**
     * Constructor de la Clase Socio
     * @param c Contiene los campos con los datos del Socio
     */
    public Socio(JTextField [] c){ //en Mayúsculas, sin espacios Iniciales/Finales
        this.codSoc = c[0].getText();
        this.nombre = c[1].getText().toUpperCase().trim();
        this.apellido = c[2].getText().toUpperCase().trim();
        this.dniNif = c[3].getText().toUpperCase().trim();
        this.direccion = c[4].getText().toUpperCase().trim();
        this.tlfMovil = c[5].getText().toUpperCase().trim();
    }
    
    /**
     * Devuelve todos los datos de un Socio en formato Array de String
     * @return Array Datos de una Factura 
     */
    public String [] getArray(){
        String[] array=new String[cabecera.length];
        array[0]=codSoc;
        array[1]=nombre;
        array[2]=apellido;
        array[3]=dniNif;
        array[4]=direccion;
        array[5]=tlfMovil;
        return array;
    }
    
    /**
     * Devuelve codSoc de Socio
     * @return String codSoc de una Socio
     */
    public String getCodSoc() {
        return codSoc;
    }
    
    /**
     * Devuelve nombre de Socio
     * @return String nombre de una Socio
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Devuelve apellido de Socio
     * @return String apellido de una Socio
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     * Devuelve dniNif de Socio
     * @return String dniNif de una Socio
     */
    public String getDniNif() {
        return dniNif;
    }
    
    /**
     * Devuelve direccion de Socio
     * @return String direccion de una Socio
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Devuelve tlfMovil de Socio
     * @return String tlfMovil de una Socio
     */
    public String getTlfMovil() {
        return tlfMovil;
    }
    
    /**
     * Devuelve cabecera de Socio
     * @return String cabecera de una Socio
     */
    public static String[] getCabecera() {
        return cabecera;
    }   
}
