/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Mario
 */
public class VentanaAlta extends JFrame implements ActionListener {
    JPanel contenedor;
    JButton botonAlta, botonCancelar;
    JTextField [] campo;
    CtrlDataBase db;
    JFrame padre;
    
    String [][] textEtiqTipoLong={{"CODIGO* :","NUMERIC","6"},
                                  {"NOMBRE* :","VARCHAR","50"},
                                  {"APELLIDOS* :","VARCHAR","60"},
                                  {"DNI/NIF* :","VARCHAR","9"},
                                  {"DIRECCION* :","VARCHAR","70"},
                                  {"TELEFONO MOVIL :","VARCHAR","15"}};

    

    public VentanaAlta(CtrlDataBase db,JFrame padre) {
        this.padre=padre;
        padre.setVisible(false);
        this.db=db;
        this.setTitle("Alta Socio");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300, 300);
    }
    
    private JComponent addCampos(){
        
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(textEtiqTipoLong.length,2,5,5));
        campo=new JTextField[textEtiqTipoLong.length];
        JLabel [] etiquetaCampo=new JLabel[textEtiqTipoLong.length];
        
        for (int i = 0; i < textEtiqTipoLong.length; i++) {
            etiquetaCampo[i].setText(textEtiqTipoLong[i][0]);
            inner.add(etiquetaCampo[i]);
            inner.add(campo[i]);            
        }   
        return inner;
    }
    
     private JComponent addBotones(){
        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(1,2,5,5));
        
        botonAlta = new JButton("Alta");
        botonAlta.addActionListener(this);
        botonAlta.setActionCommand("alta");
        
        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(this);
        botonCancelar.setActionCommand("cancelar");
        
        inner.add(botonAlta);
        inner.add(botonCancelar);
        
        return inner;
     }
    
    private void initComponents() {
        JLabel mensaje=new JLabel("Los campos con Asterisco son obligatorios",SwingConstants.CENTER);
        mensaje.setFont(new Font("Courier", Font.BOLD, 24));
        
        contenedor=(JPanel) this.getContentPane();          
        contenedor.setLayout(new BorderLayout());
        
        contenedor.add(mensaje,BorderLayout.NORTH);
        contenedor.add(addCampos(),BorderLayout.CENTER);
        contenedor.add(addBotones(),BorderLayout.SOUTH);

    }

    private void limpiaPantalla() {
        for (int i = 0; i < campo.length; i++) {
            campo[i].setText(null);
        }
    }
    
    private void ventanaInfo(String cadena){
        JOptionPane.showMessageDialog(this,cadena);
    }
    
    private boolean datosCorrectos(){
        boolean correcto=true;
        
        for (int i = 0; i < textEtiqTipoLong.length; i++) {            
            
            if (!compruebaDatos(textEtiqTipoLong[i],campo[i].getText())){
                campo[i].setBackground(Color.yellow);
                correcto=false;
            }else{
                campo[i].setBackground(Color.white);
            }
            
        }     
        return correcto;
    }
    
    private boolean compruebaDatos(String [] dato, String valor){
        boolean correcto;
            
        switch (dato[0]){
            case "NUMERIC":
                correcto=compruebaEntPositivo(dato,valor);
                break;
            case "VARCHAR":
                correcto=compruebaCadena(dato,valor);
                break;
            default:
                correcto=false;
                break;
        }
        
        return correcto;
    }    
    
    // si NO es obligatorio y esta vacio devolvemos "true"
    private boolean compruebaEntPositivo(String [] dato, String valor) {
        boolean correcto=true;
 
        if ((dato[0].contains("*"))&&(valor.replaceAll(" ","").isEmpty())){
            correcto = false;
        }else{
            if (!(valor.length()>=0 && valor.length()<=Integer.parseInt(dato[2]))){
                correcto=false;
            }else{
                if(!valor.isEmpty()){                    
                    try{
                        int n=Integer.parseInt(valor); 

                    }catch (NumberFormatException e){
                        correcto = false;
                    }
                }    
            }
        }
        return correcto;
    }
    
    private boolean compruebaCadena(String [] dato, String valor){
        boolean correcto=true;
 
        if ((dato[0].contains("*"))&&(valor.replaceAll(" ","").isEmpty())){
            correcto = false;
        }else{
            if (!(valor.length()>=0 && valor.length()<=Integer.parseInt(dato[2]))){
                correcto=false;
            }  
        }
        return correcto;
    }
            
//    private void alta(){
//        //Creo un objeto de la clase persona, pero lo instancio como
//        //alumno, de esta manera
//        if (compruebaCadena20(nombre.getText())){           
//            //lo guardo en la base de datos
//            if (db.ejecutaInsert(nombre.getText())>0){
//                ventanaInfo("Alumno dado de Alta!!!");
//            }
//        }else{
//            ventanaError("El nombre tiene que tener entre 1 y 20 caracteres.");
//        }
//        limpiaPantalla();
//    }
    

    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case "alta":
                //alta();
                //db.recorreResultado(db.ejecutaConsulta("Select * from alumnos"));
                break;
            case "cancelar":
                padre.setVisible(true);
                this.dispose();
                //db.recorreResultado(db.ejecutaConsulta("Select * from alumnos"));
                break;    
            default:
                padre.setVisible(true);
                break;
        }        
    }
}
