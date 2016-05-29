/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import modelo.Actividad;
import modelo.Factura;
import modelo.Socio;

/**
 * Ventana que se ejecutará con las diversas opciones del listado
 * @author Mario
 * @version 27/05/2016
 */
public class VentanaListado extends JFrame implements ActionListener {
    protected JPanel contenedor;
    protected JTextField campo;
    protected JLabel etiquetaBuscar;
    protected JButton bBuscar,bCancelar;
    protected JFrame padre;
    protected JButton [] botonLista;
    protected CtrlDataBase db;
    protected DefaultTableModel modelo;
    protected ArrayList lista;
    protected JTable table;
    
    /**
     * Constructor de la Clase VentanaAlta
     * @param db Conexión a la Base de Datos
     * @param j Ventana Principal (VentanaPpal)
     * @param a ArrayList con las clases a Listar 
     */
    public VentanaListado(CtrlDataBase db,JFrame j,ArrayList a){
        this.lista=a;
        this.padre=j;
        this.db=db;
        padre.setVisible(false);
        this.setLocation(j.getLocation());
        this.setTitle("Listados");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setResizable(false);
    }
    
    /**
     * Cuerpo principal de mi ventana
     */
    private void initComponents(){
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        contenedor=(JPanel) this.getContentPane();
          
        contenedor.setLayout(new BorderLayout());
         
        contenedor.add(organizaVentana(),BorderLayout.EAST);
        contenedor.add(opciones(),BorderLayout.WEST);
    }
    
    /**
     * Genera el boton inferior de mi ventana
     * @return JComponent parte inferior de mi ventana
     */
    public  JComponent salir(){
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(1,1,0,10));
        
        bCancelar=new JButton();
        bCancelar.setText("Salir");
        bCancelar.addActionListener(this);  
        bCancelar.setActionCommand("Salir");
        
        inner.add(bCancelar);
        
        return inner;
    }
    
    /**
     * Bloquea el campo y el boton sperior de mi ventana
     */
    public void bloquea(){     // creado par heredarlo
        campo.setEnabled(false);
        bBuscar.setEnabled(false);
    }
    
    /**
     * Opciones de la parte superior de mi ventana
     * @return JComponent Componente superior de la ventana
     */
    public JComponent filtrar(){
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(1,3,5,5));
        
        etiquetaBuscar=new JLabel("");
        etiquetaBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        campo=new JTextField();
        
        bBuscar=new JButton();
        bBuscar.setText("Buscar");
        bBuscar.addActionListener(this);  
        bBuscar.setActionCommand("Buscar");
        
        inner.add(etiquetaBuscar);
        inner.add(campo);
        inner.add(bBuscar);
        
        bloquea();
        
        return inner;
    }
    
    /**
     * Contiene la tabla sobre la que se desea Listar los datos
     * @return JComponent Tabla que se mostrará
     */
    public JComponent tabla(){
        JScrollPane inner; 
        modelo=new DefaultTableModel();
        table=new JTable(modelo);
        inner=new JScrollPane(table);
        
        muestraFilas();
        centrarDatos();
        
        return inner;
    }
    
    /**
     * Genera las opciones de mi ventana (Menu botones)
     * @return JComponent Botonera izquierda de mi ventana
     */
    private JComponent opciones(){
        String [] opciones={"Listado Socios","Listado Actividades",
                            "Listado Facturas"};
        
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(opciones.length,1,10,5));
        //inner.setLayout(new GridLayout(1,opciones.length,5,5));
        botonLista=new JButton [opciones.length];
        for(int x=0;x<botonLista.length;x++){
            botonLista[x]=new JButton();
            inner.add(botonLista[x]);
            botonLista[x].setText(opciones[x]);
            botonLista[x].addActionListener(this);  
            botonLista[x].setActionCommand(opciones[x]);    
        }
        return inner;
    }
    
    /**
     * Organiza los componentes que se agregarán al JPane principal
     * @return JComponent componente central del BorderLayaout
     */
    public JComponent organizaVentana(){
        JPanel inner = new JPanel();
        inner.setLayout(new BorderLayout());
        
        inner.add(filtrar(),BorderLayout.NORTH);
        inner.add(tabla(),BorderLayout.CENTER);
        inner.add(salir(),BorderLayout.SOUTH);
             
        return inner;
    }
    
    /**
     * Ventana interactiva para mostrar información
     * @param cadena Texto a mostrar
     */
    protected void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
    
    /**
     * Se ejecutara cuando presionemos Buscar -->Creado para heredar 
     */
    protected void muestraLista(){

    }
    
    /**
     * Cabeceras de tabla -->Creado para heredar 
     */
    protected void cabeceras(){
        
    }
    
    /**
     * Agrega filas a la tabla -->Creado para heredar
     */
    protected void muestraFilas(){

    }
    
    /**
     * Centra los datos de las columnas de mi tabla -->Creado para heredar
     */
    protected void centrarDatos(){

    }
    
    /**
     * Contiene la diversas opciones que se ejecutarán cuando actuemos sobre la ventana
     * @param ae Evento generado en la ventana
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case "Listado Socios":
                ArrayList <Socio> s=db.listaSocios();
                ListadoSocio mp= new ListadoSocio(db,padre,s);
                this.dispose(); 
                break;                      
            case "Listado Actividades":
                ArrayList <Actividad> a=db.listaActividad();
                ListadoActividad ap= new ListadoActividad(db,padre,a);
                this.dispose();
                break;  
            case "Listado Facturas":
                ArrayList <Factura> fact=db.listaFactura();
                ListadoFactura fa= new ListadoFactura(db,padre,fact);
                this.dispose();               
                break;     
            case "Buscar":
                muestraLista();
                break;
            case "Salir":
                padre.setVisible(true);
                this.dispose();
                break;
            default: 
                this.dispose();
                break;
        }
        
    }
    
    
}
