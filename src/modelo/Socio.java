package modelo;

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
    private String codPostal;
    private String provincia;
    private String localidad;

    /**
     * 
     * @param cod_soc Número que representa el codigo
     * @param nombre String con el nombre
     * @param apellido String con los apellidos
     * @param dni_nif String con el DNI o NIF
     * @param direccion String con la direccion
     * @param codPostal Numero que representa el código postal
     * @param provincia String con la provincia
     * @param localidad String con la localidad
     * @see #compruebaDatos() 
     */    
    public Socio(String cod_soc, String nombre, String apellido, String dni_nif, 
                 String direccion, String codPostal, String provincia, String localidad) {
        this.cod_soc = cod_soc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni_nif = dni_nif;
        this.direccion = direccion;
        this.codPostal = codPostal;
        this.provincia = provincia;
        this.localidad = localidad;
        compruebaDatos();        
    }
    
    /**
     * comprueba y corri
     */
    private void compruebaDatos(){
        
    }
    
    
    
    
}
