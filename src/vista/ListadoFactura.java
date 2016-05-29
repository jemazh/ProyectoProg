/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.Factura;

/**
 * Ventana que muestra un listado de facturas
 * @author Mario
 * @version 27/05/2016
 */
public class ListadoFactura extends VentanaListado{
    
    /**
     * Constructor de la Clase VentanaAlta
     * @param db Conexión a la Base de Datos
     * @param j Ventana Principal (VentanaPpal)
     * @param a ArrayList con las clases a Listar 
     */
    ListadoFactura(CtrlDataBase db, JFrame padre, ArrayList fact) {
        super(db,padre,fact);
    }
    
    @Override
    public void cabeceras(){
        String [] c=Factura.getCabecera();
        
        for (int i = 0; i < c.length; i++) {
            modelo.addColumn(c[i]);
        }
    }
    
    @Override
    public void centrarDatos(){
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        int longitud=Factura.getCabecera().length;
        
        for (int i = 0; i < longitud; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
        }       
    }
    
    @Override
    public void muestraFilas(){
        cabeceras();
        ListIterator it= lista.listIterator();
        Factura f;
        while(it.hasNext()){
            f=(Factura)it.next();
            modelo.addRow(f.getArray());
        }
    }
    
    @Override
    public void bloquea(){     
        campo.setEnabled(true);
        bBuscar.setEnabled(true);
        etiquetaBuscar.setText("COD_SOC :");
    }
    
    /**
     * Contiene un lista de facturas de un determinado socio
     * @param dato cod_soc a buscar
     * @return 
     */
    public ArrayList buscar(String dato) {
        ArrayList <Factura> listaCompleta=db.listaFactura();
        ArrayList <Factura> arry=new ArrayList();
        
        for (Factura f: listaCompleta){
            if (f.getCodSoc().equals(dato)){
                arry.add(f);
            }    
        }
        return arry;
    } 
 
    @Override
    protected void muestraLista(){ 
        ArrayList <Factura> fa=buscar(campo.getText());
        
        if (fa.isEmpty()){
            ventanaInfo("No existe socio con ese CODIGO");
        }else{
            ListadoFactura mp= new ListadoFactura(db,padre,fa);
            this.dispose();
        }       
    }
    
}
