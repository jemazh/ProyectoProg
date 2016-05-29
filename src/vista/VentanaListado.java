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
 *
 * @author Mario
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
    
    public void bloquea(){     // creado par heredarlo
        campo.setEnabled(false);
        bBuscar.setEnabled(false);
    }
    
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
    
    protected void cabeceras(){// para heredar cabeceras de tabla
        
    }
    
    
    protected void muestraFilas(){ //para heredar filas de la tabla

    }
    
    protected void centrarDatos(){
//        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
//        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
//        table.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
    }
    
    public JComponent tabla(){
        JScrollPane inner; 
        modelo=new DefaultTableModel();
        table=new JTable(modelo);
        inner=new JScrollPane(table);
        
        muestraFilas();
        centrarDatos();
        
        return inner;
    }
    
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
    
    private void initComponents(){
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        contenedor=(JPanel) this.getContentPane();
          
        contenedor.setLayout(new BorderLayout());
         
        contenedor.add(organizaVentana(),BorderLayout.EAST);
        contenedor.add(opciones(),BorderLayout.WEST);
    }
    
    public JComponent organizaVentana(){
        JPanel inner = new JPanel();
        inner.setLayout(new BorderLayout());
        
        inner.add(filtrar(),BorderLayout.NORTH);
        inner.add(tabla(),BorderLayout.CENTER);
        inner.add(salir(),BorderLayout.SOUTH);
             
        return inner;
    }
    
    protected void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
    
    protected void muestraLista(){ //creado para heredarlo

    }
    

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
