package modelo;

/**
 * Representa un socio y contiene todos sus datos y métodos necesarios.
 * @author Mario
 * @version 20/05/2016
 */
public class Socio {
    private int cod_soc;
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
    public Socio(int cod_soc, String nombre, String apellido, String dni_nif, 
                 String direccion, String tlfMovil) {
        this.cod_soc = cod_soc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni_nif = dni_nif;
        this.direccion = direccion;
        this.tlfMovil = tlfMovil;      
    }
    
    public String [] getArraySocio(){
        String[] array=new String[4];
        array[0]=String.valueOf(cod_soc);
        array[1]=nombre;
        array[2]=apellido;
        array[3]=dni_nif;
        array[4]=direccion;
        array[5]=tlfMovil;
        return array;
    }
  
}
