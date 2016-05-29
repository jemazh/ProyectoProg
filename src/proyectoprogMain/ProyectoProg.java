/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogMain;

import controlador.CtrlDataBase;
import vista.VentanaPpal;

/**
 * Clase que contiene le Main de ProyectoProg
 * @author Mario
 * @version 19/05/2016
 */
public class ProyectoProg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String servidor = "jdbc:oracle:thin:@localhost:";
        String bd = "1521:xe";
        String user = "damlocal";
        String password = "case";
        
        CtrlDataBase db = new CtrlDataBase(bd, user, password, servidor);
        
        if (db.abrirConexion()) {
            VentanaPpal v=new VentanaPpal(db);
        }

    }
    
}
