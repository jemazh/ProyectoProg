/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mario
 */
public class VentanaBaja extends JFrame implements ActionListener{
    JPanel contenedor;
    JButton botonAlta, botonCancelar;
    JTextField nombre;
    JLabel etiquetaNombre;
    CtrlDataBase db;
    JFrame padre;
    
    public VentanaBaja(CtrlDataBase db,JFrame padre) {
        this.padre=padre;
        padre.setVisible(false);
        this.db=db;
        this.setTitle("Baja socio");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300, 300);
    }

    private void initComponents() {
        //Utilizo todo el fondo del JFrame
        contenedor = (JPanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(2, 2, 5, 5));
        //Inicializo los objetos
        etiquetaNombre = new JLabel("Codigo: ");
        nombre = new JTextField();
        botonAlta = new JButton("Baja");
        botonAlta.addActionListener(this);
        botonAlta.setActionCommand("baja");
        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(this);
        botonCancelar.setActionCommand("cancelar");
        //los pongo en el contendor
        contenedor.add(etiquetaNombre);
        contenedor.add(nombre);
        contenedor.add(botonAlta);
        contenedor.add(botonCancelar);
    }

    private void limpiaPantalla() {
        nombre.setText(null);
    }
    
    private void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
    
    private boolean compruebaCadena20(String cadena){
        return cadena.length() > 0 && cadena.length() <=20;
    }
            
    private void baja(){            
            if (db.ejecutaDelete(nombre.getText())>0){
                ventanaInfo("Socios eliminados");
            }else{
                ventanaError("No existen Socios con ese código");
            }
        limpiaPantalla();
    }
    

    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case "baja":
                baja();
                break;
            case "cancelar":
                padre.setVisible(true);
                this.dispose();
                break;    
            default:
                padre.setVisible(true);
                break;
        }        
    }
    
}
