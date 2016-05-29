/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que contiene el modelo de Factura (variables y métodos)
 * @author Mario
 * @version 28/05/2016
 */
public class Factura {
    private String idFact;
    private String fecha;    
    private String cabeceraFact;
    private String total;
    private String codSoc;
    
    //cabecera-->Me permitirá la modificación del código de una forma más sencilla
    static String [] cabecera={"ID_FACT","FECHA","CABECERA","TOTAL","COD_SOC"};
    
    /**
     * Constructor de la Clase Factura
     * @param idFact Representa id_fact de mi Base de Datos
     * @param fecha Representa fecha_factura de mi Base de Datos
     * @param cabeceraFact Representa Cabecera_factura de mi Base de Datos
     * @param total Representa total de mi Base de Datos
     * @param codSoc Representa Socios_id_soc de mi Base de Datos
     */
    public Factura(String idFact, String fecha, String cabeceraFact, String total, String codSoc) {
        this.idFact = idFact;
        this.fecha = recorta(fecha);    //me quedo solo con la fecha
        this.cabeceraFact = cabeceraFact;
        this.total = total;
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
     * Devuelve todos los datos de una Factura en formato Array de String
     * @return Array Datos de una Factura 
     */
    public String [] getArray(){
        String[] array=new String[cabecera.length];
        array[0]=idFact;
        array[1]=fecha;
        array[2]=cabeceraFact;
        array[3]=String.format("%,.2f€", Double.parseDouble(total)); // En Euros
        array[4]=codSoc;
        return array;
    }
    
    /**
     * Devuelve cabecera
     * @return Array Formato Array de String de cabecera de Factura
     */
    public static String[] getCabecera() {
        return cabecera;
    }
    
    /**
     * Devuelve codSoc de Factura
     * @return String codSoc de una Factura
     */
    public String getCodSoc() {
        return codSoc;
    }
   
}
