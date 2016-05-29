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
    
    ListadoSocio(CtrlDataBase d, JFrame padre,ArrayList <Socio> s) {
        super(d,padre,s);
    }
    
    @Override
    public void cabeceras(){
        String [] c=Socio.getCabecera();
        
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
        etiquetaBuscar.setText("COD_SOC :");
    }
    
    public ArrayList buscar(String dato) {
        ArrayList <Socio> listaCompleta=db.listaSocios();
        ArrayList <Socio> arry=new ArrayList();
        
        for (Socio s: listaCompleta){
            if (s.getCodSoc().equals(dato)){
                arry.add(s);
            }    
        }
        return arry;
    } 
    
    
    @Override
    protected void muestraLista(){ 
        ArrayList <Socio> soc=buscar(campo.getText());
        
        if (soc.isEmpty()){
            ventanaInfo("No existe socio con ese CODIGO");
        }else{
            ListadoSocio mp= new ListadoSocio(db,padre,soc);
            this.dispose();
        }       
    }
}
