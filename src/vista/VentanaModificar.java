/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.awt.Color;
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
        campo[0].setText(s.getCodSoc());
        campo[0].setEditable(false);
        
        campo[1].setText(s.getNombre());
        campo[2].setText(s.getApellido());
        campo[3].setText(s.getDniNif());
        campo[4].setText(s.getDireccion());
        campo[5].setText(s.getTlfMovil());   
    }
    
    /**
     *
     */
    @Override
    protected void bloqueaCampos(){
        campo[0].setBackground(new Color(238,238,238));
    }
    
    @Override
    protected void ejecutar(){
        if (datosCorrectos()){ 
            Socio s=new Socio(campo);
            if (db.ejecutaUpdate(s)>0){
                ventanaInfo("Modificación realizada!!!");
                // si se realizo la modificacion salgo de la ventana 
                // y regreso al menú principal
                this.dispose();                        
                padre.setVisible(true);
            }else{
                ventanaError("Error en la BD al realizar la modificación");
            }                   
        }else{
            ventanaError("Datos incorrectos");
        }     
    }  
}
