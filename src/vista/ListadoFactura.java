/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDataBase;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Mario
 */
public class ListadoFactura extends VentanaListado{

    ListadoFactura(CtrlDataBase db, JFrame padre, ArrayList fact) {
        super(db,padre,fact);
    }
    
}
