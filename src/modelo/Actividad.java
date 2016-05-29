/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que contiene el modelo de Actividad (variables y métodos)
 * @author Mario
 * @version 28/05/2016
 */
public class Actividad {
    private String idAct;
    private String nombre;
    private String fecha; 
    private String monitor;
    private String capacidad;
    private String codSoc;
    
    //cabecera-->Me permitirá la modificación del código de una forma más sencilla
    static String [] cabecera={"COD_ACT","NOMBRE","FECHA","MONITOR",
                                "CAPACIDAD","COD_SOC"};
    
    /**
     * Constructor de la Clase Actividad
     * @param idAct Representa id_actividad de mi Base de Datos
     * @param nombre Representa Nombre_actividad de mi Base de Datos
     * @param fecha Representa fecha_inicio de mi Base de Datos
     * @param monitor Representa persona_imparte de mi Base de Datos
     * @param capacidad Representa el Capacidad de mi Base de Datos
     * @param codSoc Representa el cod_soc de mi Base de Datos
     */
    public Actividad(String idAct, String nombre, String fecha, String monitor, String capacidad, String codSoc) {
        this.idAct = idAct;
        this.nombre = nombre;
        this.fecha = recorta(fecha); //me quedo solo con la fecha
        this.monitor = monitor;
        this.capacidad = capacidad;
        this.codSoc = codSoc;
    }
    
    /**
     * Recorta la Fecha (No nos interesa la hora)
     * @param f String que representa una fecha en formato DATE ISO SQL
     * @return String Fecha en formato año-mes-dia
     */
    private String recorta(String f){
        if (!f.isEmpty()){
            f=f.substring(0, 10);
        }
        return f;
    }
    
    /**
     * Devuelve todos los datos de una Actividad en formato Array de String
     * @return Array Datos de una Actividad 
     */
    public String [] getArray(){
        String[] array=new String[cabecera.length];
        array[0]=idAct;
        array[1]=nombre;
        array[2]=fecha;
        array[3]=monitor;
        array[4]=capacidad;
        array[5]=codSoc;
        return array;
    }
    
    /**
     * Devuelve cabecera
     * @return Array Formato Array de String de cabecera de actividad
     */
    public static String[] getCabecera() {
        return cabecera;
    }
    
    /**
     * Devuelve idAct
     * @return String idAct de actividad
     */
    public String getIdAct() {
        return idAct;
    }
    
    /**
     * Devuelve codSoc de Actividad
     * @return String codSoc de una Actividad
     */
    public String getCodSoc() {
        return codSoc;
    }
     
}
