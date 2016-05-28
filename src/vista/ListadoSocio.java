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
import modelo.Socio;

/**
 *
 * @author Mario
 */
public class ListadoSocio extends VentanaListado{

    ListadoSocio(CtrlDataBase db, JFrame padre, ArrayList socios) {
        super(db,padre,socios);
        
    }
    
    @Override
    public void cabeceras(){
        String [] c=Socio.getCabeceras();
        
        for (int i = 0; i < c.length; i++) {
            modelo.addColumn(c[i]);
        }
    }
    
    
    @Override
    public void muestraFilas(){        
        cabeceras();
        ListIterator it= lista.listIterator();
        Socio s;
        while(it.hasNext()){
            s=(Socio)it.next();
            modelo.addRow(s.getArray());
        }
    }
    
    @Override
    public void bloquea(){     
        campo.setEnabled(true);
        bBuscar.setEnabled(true);
        etiquetaBuscar.setText("COD_SOC");
    }
    
    @Override
    protected void busqueda(){ //toDo hacer la busqueda sobre el ArrayList
        Socio soc=db.buscaSocio(campo.getText());
        if (soc!=null){
            ArrayList a= new ArrayList();
            a.add(soc);
            ListadoSocio mp= new ListadoSocio(db,padre,a);
        }else{
            ventanaInfo("No existe socio con ese CODIGO");
        }               
        
    }
}
