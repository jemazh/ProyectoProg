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
    private String idFact;
    private String fecha;    //toDo estudiar la clase Date formato fecha 2016-02-01 00:00:00.0
    private String cabeceraFact;
    private String total;
    private String codSoc;
    
    static String [] cabecera={"ID_FACT","FECHA","CABECERA","TOTAL","COD_SOC"};

    public Factura(String idFact, String fecha, String cabeceraFact, String total, String codSoc) {
        this.idFact = idFact;
        this.fecha = fecha;    
        this.cabeceraFact = cabeceraFact;
        this.total = total;
        this.codSoc = codSoc;
    }
    
    public String [] getArray(){
        String[] array=new String[cabecera.length];
        array[0]=idFact;
        array[1]=fecha;
        array[2]=cabeceraFact;
        array[3]=total;
        array[4]=codSoc;
        System.out.println(fecha);
        return array;
    }

    public static String[] getCabecera() {
        return cabecera;
    }

    public String getCodSoc() {
        return codSoc;
    }
    
    
    
}
