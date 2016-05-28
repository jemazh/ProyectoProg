/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Mario
 */
public class Actividad {
    private String id_act;
    private String nombre;
    private String fecha;
    private String monitor;
    private String capacidad;
    private String cod_soc;
    
    static String [] cabecera={"COD_ACT","NOMBRE","FECHA","MONITOR",
                                "CAPACIDAD","COD_SOC"};

    public Actividad(String id_act, String nombre, String fecha, String monitor, String capacidad, String cod_soc) {
        this.id_act = id_act;
        this.nombre = nombre;
        this.fecha = fecha;
        this.monitor = monitor;
        this.capacidad = capacidad;
        this.cod_soc = cod_soc;
    }
    
    public String [] getArray(){
        String[] array=new String[cabecera.length];
        array[0]=id_act;
        array[1]=nombre;
        array[2]=fecha;
        array[3]=monitor;
        array[4]=capacidad;
        array[5]=cod_soc;
        return array;
    }
    
    
}
