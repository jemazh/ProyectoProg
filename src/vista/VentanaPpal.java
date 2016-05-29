/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import controlador.CtrlDocumentoXML;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modelo.Socio;

/**
 *
 * @author Mario
 */
public class VentanaPpal extends JFrame implements ActionListener,WindowListener {
    JPanel contenedor;
    JButton [] boton;
    JLabel titulo;
    CtrlDataBase db;
    
    public VentanaPpal(CtrlDataBase db){
        this.db=db;
        this.setTitle("Club Social Nosferatus");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        initComponents();
        this.addWindowListener(this);
        
    }
  
    public JComponent menu(){
        String [] opciones={"Alta socio","Baja socio",
                            "Modificacion Socio","Listados",
                            "Crear XML Socios","Fin"};
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(opciones.length,1,0,10));
        
        boton=new JButton [opciones.length];
        for(int x=0;x<boton.length;x++){
            boton[x]=new JButton();
            inner.add(boton[x]);
            boton[x].setText(opciones[x]);
            boton[x].addActionListener(this);  
            boton[x].setActionCommand(""+(x+1));    
        }
        return inner;
    }
    
    private void initComponents(){
       
        contenedor=(JPanel) this.getContentPane();

        contenedor.setLayout(new BorderLayout());
        titulo=new JLabel("Gestion Club",SwingConstants.CENTER);
        titulo.setFont(new Font("Courier", Font.ITALIC, 24));
         
        

        contenedor.add(titulo,BorderLayout.NORTH);
        contenedor.add(menu(),BorderLayout.CENTER);
        contenedor.add(new JLabel("  .  "),BorderLayout.EAST);
        contenedor.add(new JLabel("  .  "),BorderLayout.WEST);
        contenedor.add(new JLabel("Mario L. 1º DAM",SwingConstants.CENTER),BorderLayout.SOUTH);
            
        this.pack();
        this.setSize(300,300);
        
    }
    
    public String preguntaCodigo(){
        String seleccion = JOptionPane.showInputDialog(this,"Introduce codigo del socio");
        
        return seleccion;
    }
    
    private void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
    
    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * 
     */
    private void fin(){
        db.cerrarConexion();
        System.exit(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "1":
                VentanaAlta va=new VentanaAlta(db,this);
                break;
            case "2":
                String ext=preguntaCodigo(); // para evitar que me saque el mensaje de codigo incorrecto
                
                if (ext!=null){
                    Socio s=db.buscaSocio(ext);
                    if (s!=null){
                        VentanaBaja vb=new VentanaBaja(db,this,s);
                    }else{
                        ventanaInfo("No existe socio con ese CODIGO");
                    }     
                }
                break;
            case "3":
                String exit=preguntaCodigo();// para evitar que me saque el mensaje de codigo incorrecto
                if (exit!=null){
                    Socio soc=db.buscaSocio(exit);
                    if (soc!=null){
                        VentanaModificar vl=new VentanaModificar(db,this,soc);
                    }else{
                        ventanaInfo("No existe socio con ese CODIGO");
                    }
                }   
                break;                
            case "4":
                ArrayList a=new ArrayList();
                VentanaListado vp=new VentanaListado(db,this,a);
                break;
            case "5":
                if (CtrlDocumentoXML.generaXML("Club_Social",db)){
                    ventanaInfo("Documento XML creado");
                }else{
                    ventanaError("Error al crear el documento XML");
                }
                break;
            case "6":
                fin();
                break;                
            default: 
                this.dispose();
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        fin();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        fin();
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
    
}
