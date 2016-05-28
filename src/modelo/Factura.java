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
public class Factura {
    private String id_fact;
    private String fecha;
    private String cabecera_fact;
    private String total;
    private String cod_soc;
    
    static String [] cabecera={"COD_FACT","FECHA","CABECERA","TOTAL","COD_SOC"};

    public Factura(String id_fact, String fecha, String cabecera_fact, String total, String cod_soc) {
        this.id_fact = id_fact;
        this.fecha = fecha;
        this.cabecera_fact = cabecera_fact;
        this.total = total;
        this.cod_soc = cod_soc;
    }
    
    public String [] getArraySocio(){
        String[] array=new String[cabecera.length];
        array[0]=id_fact;
        array[1]=fecha;
        array[2]=cabecera_fact;
        array[3]=total;
        array[4]=cod_soc;
        return array;
    }

    public static String[] getCabecera() {
        return cabecera;
    }
   
    
}
