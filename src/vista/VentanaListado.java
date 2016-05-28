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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class VentanaListado extends JFrame implements ActionListener {
    JPanel contenedor;
    JTable tabla;
    JTextField campo;
    JLabel etiquetaBuscar;
    JButton bBuscar,bCancelar;
    JFrame padre;
    JButton [] botonLista;
    CtrlDataBase db;
    ArrayList lista;
    DefaultTableModel modelo;
    
    //ResultSet r;
//    String [][] tBusqueda;
    
    public VentanaListado(CtrlDataBase db,JFrame j,ArrayList l){
        this.padre=j;
        this.db=db;
        this.lista=l;
        this.setLocation(j.getLocation());
        this.setTitle("Listados");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(608,300);
        this.setResizable(false);// mantengo mi ventana fija
        
        
    }

    public  JComponent cancelar(){
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(1,1,0,10));
        
        bCancelar=new JButton();
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(this);  
        bCancelar.setActionCommand("Cancelar");
        
        inner.add(bCancelar);
        
        return inner;
    }
    
    public void bloquea(){     // creado par heredarlo
        campo.setEnabled(false);
        bBuscar.setEnabled(false);
    }
    
    public JComponent filtrar(){
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(1,3,0,10));
        
        etiquetaBuscar=new JLabel("");
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
    
    public void cabeceras(){// para heredar cabeceras de tabla
        
    }
    
    
    public void muestraFilas(){ //para heredar filas de la tabla

    } 
    
    public JComponent tabla(){
        JScrollPane inner; 
        modelo=new DefaultTableModel();
        //se crea la Tabla con el modelo DefaultTableModel
        JTable table=new JTable(modelo);
        // se define el tamaño
        //table.setPreferredScrollableViewportSize(new Dimension(500,70));
        //Creamos un JscrollPane y le agregamos la JTable
        
        inner=new JScrollPane(table);
        
        muestraFilas();   
        
        return inner;
    }
    
    private JComponent opciones(){
        String [] opciones={"Listado Socios","Listado Actividades",
                            "Listado Facturas"};
        
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(opciones.length,1,0,10));
        
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
        inner.add(cancelar(),BorderLayout.SOUTH);
             
        return inner;
    }
    
    protected void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
    
    protected void busqueda(){ //ToDo  para heredar ver: Socio soc=db.buscaSocio(preguntaCodigo())
//        ArrayList soc=db.listaSocios();
//        if (soc.isEmpty()){
//            ventanaInfo("No existe ningun socio");
//        }else{
//            ListadoSocio mp= new ListadoSocio(db,padre,soc);
//            this.dispose();
//        } 
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case "Listado Socios":
                ArrayList soc=db.listaSocios();
                if (soc.isEmpty()){
                    ventanaInfo("No existe ningun socio");
                }else{
                    ListadoSocio mp= new ListadoSocio(db,padre,soc);
                    this.dispose();
                } 
                break;                      
            case "Listado Actividades":
                ArrayList act=db.listaActividad();
                if (act.isEmpty()){
                    ventanaInfo("No existe ninguna Actividad");
                }else{
                    ListadoActividad ap= new ListadoActividad(db,padre,act);
                    this.dispose();
                } 
                break;  
            case "Listado Facturas":
                ArrayList fact=db.listaFactura();
                if (fact.isEmpty()){
                    ventanaInfo("No existe ninguna Factura");
                }else{
                    ListadoFactura fa= new ListadoFactura(db,padre,fact);
                    this.dispose();
                }
                break;     
            case "Buscar":
                busqueda();
                break;
            case "Cancelar":
                padre.setVisible(true);
                this.dispose();
                break;
            default: 
                this.dispose();
                break;
        }
        
    }
    
    
}
