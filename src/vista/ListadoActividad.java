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
import modelo.Actividad;
import modelo.Socio;

/**
 *
 * @author Mario
 */
public class ListadoActividad extends VentanaListado{

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
