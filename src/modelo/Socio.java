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
    
    static String [] cabecera={"COD_SOC","NOMBRE","APELLIDOS","DNI/NIF",
                                "DIRECCION","TELEFONO"};

    /**
     * 
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
    
    public Socio(JTextField [] c){ //en Mayúsculas, sin espacios Iniciales/Finales
        this.codSoc = c[0].getText();
        this.nombre = c[1].getText().toUpperCase().trim();
        this.apellido = c[2].getText().toUpperCase().trim();
        this.dniNif = c[3].getText().toUpperCase().trim();
        this.direccion = c[4].getText().toUpperCase().trim();
        this.tlfMovil = c[5].getText().toUpperCase().trim();
    }
    
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

    public String getCodSoc() {
        return codSoc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDniNif() {
        return dniNif;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTlfMovil() {
        return tlfMovil;
    }

    public static String[] getCabecera() {
        return cabecera;
    }
    
    
}
