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
 *
 * @author Jemazh
 */
public class VentanaBaja extends VentanaAlta{
    
    public VentanaBaja(CtrlDataBase db, JFrame padre, Socio s) {
        super(db, padre);
        this.setTitle("Baja Socio");
        botonAlta.setText("Baja");
        rellena(s);
    }
    
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
    private int confirma(){
        int n= JOptionPane.showConfirmDialog(this,"¿Deseas Eliminarlo?");               
        return n;
    }
    /**
     *
     */
    @Override
    protected void bloqueaCampos(){
        for (int i = 0; i < campo.length; i++) {
            campo[0].setBackground(new Color(238,238,238));  
        } 
    }
    
    
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
