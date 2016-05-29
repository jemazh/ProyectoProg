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
 * Primera ventana que se mostrará con el Menú inicial e información
 * @author Mario
 * @version 27/05/2016
 */
public class VentanaPpal extends JFrame implements ActionListener,WindowListener {
    JPanel contenedor;
    JButton [] boton;
    JLabel titulo;
    CtrlDataBase db;
    
    /**
     * Constructor de la clase VentanaPpal
     * @param db Conexión a la Base de Datos
     */
    public VentanaPpal(CtrlDataBase db){
        this.db=db;
        this.setTitle("Club Social Nosferatus");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        initComponents();
        this.addWindowListener(this);        
    }
    
    /**
     * Agrupa y genera los botonos de forma dinámica
     * @return JComponent contiene los botones con mi Menú
     */
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
    
    /**
     * Cuerpo principal de mi Ventana (VentanaPpal)
     */
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
    
    /**
     * Ventana interactiva para insertar datos (Codigo de socio)
     * @return String Contiene el campo que herellenado
     */
    public String preguntaCodigo(){
        String seleccion = JOptionPane.showInputDialog(this,"Introduce codigo del socio");
        
        return seleccion;
    }
    
    /**
     * Ventana interactiva para mostrar información
     * @param cadena Texto a mostrar
     */
    private void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
    
    /**
     * Ventana interactiva que se muestra cuando se ha producido un Error
     * @param cadena Texto a mostrar
     */
    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Contiene los métodos que se ejecutarán al cerrar mi ventana
     */
    private void fin(){
        db.cerrarConexion();
        System.exit(0);
    }
    
    /**
     * Contiene la diversas opciones que se ejecutarán cuando actuemos sobre la ventana
     * @param e Evento generado en la ventana
     */
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
