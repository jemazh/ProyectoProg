/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
        initComponents();
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
        String seleccion = JOptionPane.showInputDialog(this,
                                               "Introduce codigo del socio");
        
        System.out.println("El usuario ha escrito "+seleccion);
        return seleccion;
    }
    
    private void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
    
    /**
     * ToDo
     */
    private void fin(){
        //CtrlDocumentoXML.generaXML("Clase", db.ejecutaConsulta("SELECT * from alumnos"));
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
                VentanaBaja vb=new VentanaBaja(db,this);
                break;
            case "3":
                Socio soc=db.buscaSocio(preguntaCodigo());
                if (soc!=null){
                    VentanaModificar vl=new VentanaModificar(db,this,soc);
                }else{
                    ventanaInfo("No existe socio con ese CODIGO");
                }               
                break;                
            case "4":
                //VentanaPNota vp=new VentanaPNota(db,this);
                break;
            case "5":
                //VentanaBorrarSus va=new VentanaBorrarSus(db,this);
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
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        fin();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        fin();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
