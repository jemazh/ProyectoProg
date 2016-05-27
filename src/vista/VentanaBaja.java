/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.awt.Color;
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
    JTextField codigo;
    JLabel etiquetaCodigo;
    CtrlDataBase db;
    JFrame padre;
    
    public VentanaBaja(CtrlDataBase db,JFrame padre) {
        this.padre=padre;
        padre.setVisible(false);
        this.setLocation(padre.getLocation());
        this.db=db;
        this.setTitle("Baja socio");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300, 100);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Utilizo todo el fondo del JFrame
        contenedor = (JPanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(2, 2, 5, 5));
        //Inicializo los objetos
        etiquetaCodigo = new JLabel("Codigo: ");
        codigo = new JTextField();
        botonAlta = new JButton("Baja");
        botonAlta.addActionListener(this);
        botonAlta.setActionCommand("baja");
        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(this);
        botonCancelar.setActionCommand("cancelar");
        //los pongo en el contendor
        contenedor.add(etiquetaCodigo);
        contenedor.add(codigo);
        contenedor.add(botonAlta);
        contenedor.add(botonCancelar);
    }

    private void limpiaPantalla() {
        codigo.setText(null);
        codigo.setBackground(Color.white);
    }
    
    private void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
                
    private void baja(){            
            if (db.ejecutaDelete(codigo.getText())>0){
                ventanaInfo("Socio eliminado");
                limpiaPantalla();
            }else{
                codigo.setBackground(Color.yellow);
                ventanaError("No existen Socios con ese código");
            }
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
