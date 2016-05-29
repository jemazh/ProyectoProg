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
 * Ventana que me permitira modificar los datos de un Socio
 * @author Mario
 * @version 27/05/2016
 */
public class VentanaModificar extends VentanaAlta {

    /**
     * Constructor de la Clase VentanaModificar
     * @param db Conexión a la Base de Datos
     * @param padre Ventana Principal (VentanaPpal)
     * @param s Socio a modificar
     */
    public VentanaModificar(CtrlDataBase db, JFrame padre, Socio s) {
        super(db, padre);
        this.setTitle("Modificar Socio");
        botonAlta.setText("Modificar");
        rellena(s);
    }
    
    /**
     * Rellena los campos de mi ventana
     * @param s Socio que se desea modificar
     */
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
     * Cambia el color del campo id_soc
     */
    @Override
    protected void colorCampos(){
        campo[0].setBackground(new Color(238,238,238));
    }
    
    /**
     * Modifica el Socio si los datos son correctos
     */
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
