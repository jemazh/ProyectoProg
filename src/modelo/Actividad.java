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
    private String idAct;
    private String nombre;
    private String fecha; //toDo estudiar la clase Date formato fecha 2016-02-01 00:00:00.0
    private String monitor;
    private String capacidad;
    private String codSoc;
    
    static String [] cabecera={"COD_ACT","NOMBRE","FECHA","MONITOR",
                                "CAPACIDAD","COD_SOC"};

    public Actividad(String idAct, String nombre, String fecha, String monitor, String capacidad, String codSoc) {
        this.idAct = idAct;
        this.nombre = nombre;
        this.fecha = fecha;
        this.monitor = monitor;
        this.capacidad = capacidad;
        this.codSoc = codSoc;
    }
    
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

    public static String[] getCabecera() {
        return cabecera;
    }

    public String getIdAct() {
        return idAct;
    }

    public String getCodSoc() {
        return codSoc;
    }
     
}
