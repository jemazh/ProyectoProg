/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Socio;

/**
 *  Ventana que me permitirá Eliminar un Socio
 * @author Mario
 * @version 27/05/2016
 */
public class VentanaBaja extends VentanaAlta{
    
    /**
     * Constructor de la Clase VentanaBaja
     * @param db Conexión a la Base de Datos
     * @param padre Ventana Principal (VentanaPpal)
     * @param s Socio a modificar
     */
    public VentanaBaja(CtrlDataBase db, JFrame padre, Socio s) {
        super(db, padre);
        this.setTitle("Baja Socio");
        botonAlta.setText("Baja");
        rellena(s);
    }
    
    /**
     * Rellena los campos de mi ventana
     * @param s Socio que se desea modificar
     */
    protected void rellena(Socio s){
        campo[0].setText(s.getCodSoc());       
        campo[1].setText(s.getNombre());       
        campo[2].setText(s.getApellido());
        campo[3].setText(s.getDniNif());
        campo[4].setText(s.getDireccion());
        campo[5].setText(s.getTlfMovil());
        
        for (int i = 0; i < campo.length; i++) {
            campo[i].setEditable(false);  
        }       
    }
    
    /**
     * Ventana interactiva que confirma la eliminación de un Socio
     */
    private int confirma(){
        int n= JOptionPane.showConfirmDialog(this,"¿Deseas Eliminarlo?");               
        return n;
    }
    
    /**
     * Cambia el color del campo id_soc
     */
    @Override
    protected void colorCampos(){
        for (int i = 0; i < campo.length; i++) {
            campo[0].setBackground(new Color(238,238,238));  
        } 
    }
    
    /**
     * Elimina el Socio, en caso contrario muestra un mensaje
     */
    @Override
    protected void ejecutar(){
        switch (confirma()){
            case 0:
                if (db.ejecutaDelete(campo[0].getText())>0){
                    ventanaInfo("Socio eliminado");
                    this.dispose();                        
                    padre.setVisible(true);
                }else{
                    ventanaError("Error en la BD al realizar la modificación");
                }
                break;
            case 1:  
                        // no hacemos nada
                break;
            case 2:
                this.dispose();  //volvemos a la ventana anterior                      
                padre.setVisible(true);
                break;
            default:
                break;
        }    
    } 
    
}
