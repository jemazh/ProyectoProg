/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import javax.swing.JFrame;
import modelo.Socio;

/**
 *
 * @author Mario
 */
public class VentanaModificar extends VentanaAlta {

    public VentanaModificar(CtrlDataBase db, JFrame padre, Socio s) {
        super(db, padre);
        this.setTitle("Modificar Socio");
        botonAlta.setText("Modificar");
        rellena(s);
    }
    
    protected void rellena(Socio s){
        campo[0].setText(s.getCod_soc());
        campo[0].setEditable(false);
        
        campo[1].setText(s.getNombre());
        campo[2].setText(s.getApellido());
        campo[3].setText(s.getDni_nif());
        campo[4].setText(s.getDireccion());
        campo[5].setText(s.getTlfMovil());   
    }
    
    @Override
    protected void alta(){
        if (datosCorrectos()){
            Socio s=new Socio(campo);
            if (db.ejecutaUpdate(s)>0){
                ventanaInfo("Modificación realizada!!!");
            }else{
                ventanaError("Error en la BD al realizar la modificación");
            }                   
        }else{
            ventanaError("Datos incorrectos");
        }     
    }  
}
