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
import modelo.Actividad;
import modelo.Socio;

/**
 * Ventana que muesta un listado de Actividades
 * @author Mario
 * @version 27/05/2016
 */
public class ListadoActividad extends VentanaListado{
    
    /**
     * Constructor de la Clase VentanaAlta
     * @param db Conexión a la Base de Datos
     * @param j Ventana Principal (VentanaPpal)
     * @param a ArrayList con las clases a Listar 
     */
    ListadoActividad(CtrlDataBase d, JFrame padre,ArrayList s) {
        super(d,padre,s);
    }
        
    @Override
    public void cabeceras(){
        String [] tituloColumnas;
        ListIterator it= lista.listIterator();
        if (it.hasNext()){
            try{
                Actividad a=(Actividad)it.next();
                tituloColumnas=Actividad.getCabecera();
                for (int i = 0; i < tituloColumnas.length; i++) {
                    modelo.addColumn(tituloColumnas[i]);
                }              
            }catch(ClassCastException e){
                tituloColumnas=Socio.getCabecera();
                for (int i = 0; i < tituloColumnas.length; i++) {
                    modelo.addColumn(tituloColumnas[i]);
                }
            }
        }
    }
    
    @Override
    public void centrarDatos(){
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        int longitud;
        
        ListIterator it= lista.listIterator();
        if (it.hasNext()){
            try{
                Actividad a=(Actividad)it.next();
                longitud=Actividad.getCabecera().length;
                for (int i = 0; i < longitud; i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
                }              
            }catch(ClassCastException e){
                longitud=Socio.getCabecera().length;
                for (int i = 0; i < longitud; i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
                }
            }
        }
    }
    
    @Override
    public void muestraFilas(){        
        cabeceras();
        ListIterator it= lista.listIterator();
        
        try {
            Actividad a;
            while(it.hasNext()){
                a=(Actividad)it.next();           
                modelo.addRow(a.getArray());
            }
            
        }catch (ClassCastException e){
            Socio s;
            while(it.hasNext()){
                s=(Socio)it.next();          
                modelo.addRow(s.getArray());
            }
        }
    }
    
    @Override
    public void bloquea(){     
        campo.setEnabled(true);
        bBuscar.setEnabled(true);
        etiquetaBuscar.setText("ID_ACTIVIDAD :");
    }
    
    /**
     * Busca todos los sociosque estan en una Actividad
     * @param dato Campo a buscar iD_Actividad
     * @return 
     */
    public ArrayList<Socio> buscar(String dato) {
        ArrayList <Socio> arryS=db.listaSocios();
        ArrayList <Actividad> arryA=db.listaActividad();
        ArrayList <Socio> arry=new ArrayList();
        
        for (Actividad a: arryA){
            if (a.getIdAct().equals(dato)){
                for (Socio s: arryS){
                    if (a.getCodSoc().equals(s.getCodSoc())){
                        arry.add(s);                   
                    }
                }                
            }   
        }       
        return arry;
    }    
    
    @Override
    protected void muestraLista(){ 
        ArrayList <Socio> soc=buscar(campo.getText());
        
        if (soc.isEmpty()){
            ventanaInfo("No existen socios en esa Actividad");
        }else{
            ListadoActividad mp= new ListadoActividad(db,padre,soc);
            this.dispose();
        }       
    }
    
}
