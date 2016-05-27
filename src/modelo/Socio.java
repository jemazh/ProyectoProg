package modelo;

import javax.swing.JTextField;

/**
 * Representa un socio y contiene todos sus datos y métodos necesarios.
 * @author Mario
 * @version 20/05/2016
 */
public class Socio {
    private String cod_soc;
    private String nombre;
    private String apellido;
    private String dni_nif;
    private String direccion;
    private String tlfMovil;

    /**
     * 
     * @param cod_soc Número que representa el codigo
     * @param nombre String con el nombre
     * @param apellido String con los apellidos
     * @param dni_nif String con el DNI o NIF
     * @param direccion String con la direccion
     * @param tlfMovil String con el telefono móvil
     */    
    public Socio(String cod_soc, String nombre, String apellido, String dni_nif, 
                 String direccion, String tlfMovil) {
        this.cod_soc = cod_soc;
        this.nombre = nombre.toUpperCase();
        this.apellido = apellido.toUpperCase();
        this.dni_nif = dni_nif.toUpperCase();
        this.direccion = direccion.toUpperCase();
        this.tlfMovil = tlfMovil.toUpperCase();
        corrigeEspacios();
    }
    
    public Socio(JTextField [] c){
        this.cod_soc = c[0].getText();
        this.nombre = c[1].getText().toUpperCase();
        this.apellido = c[2].getText().toUpperCase();
        this.dni_nif = c[3].getText().toUpperCase();
        this.direccion = c[4].getText().toUpperCase();
        this.tlfMovil = c[5].getText().toUpperCase();
        corrigeEspacios();
    }
    
    private void corrigeEspacios(){    // toDo
        
    }
    
    public String [] getArraySocio(){
        String[] array=new String[4];
        array[0]=cod_soc;
        array[1]=nombre;
        array[2]=apellido;
        array[3]=dni_nif;
        array[4]=direccion;
        array[5]=tlfMovil;
        return array;
    }

    public String getCod_soc() {
        return cod_soc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni_nif() {
        return dni_nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTlfMovil() {
        return tlfMovil;
    }
    
}
