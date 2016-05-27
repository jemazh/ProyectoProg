/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Socio;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author Mario
 */
public class CtrlDataBase {
    String bd;
    String login;
    String password;
    String servidor;
    Connection conexion;
    
    public CtrlDataBase(String bd, String login, String password, String servidor) {

        this.bd = bd;
        this.login = login;
        this.password = password;
        this.servidor = servidor;
    }
    
    public boolean abrirConexion() {

        boolean estado = false;

        try {
            DriverManager.registerDriver( new OracleDriver());//oracle

            // Crear conenection a la base de datos.
            conexion = DriverManager.getConnection(servidor + bd, login, password);
            estado = true;
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("SQL Exception:\n" + e.toString());
        } catch (Exception e) {
            System.out.println("Exception:\n" + e.toString());
        }

        return estado;
    }
        
    public void cerrarConexion() {

        try {
            conexion.close();
            System.out.println("Desconectado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
    public int ejecutaInsert(Socio s){
        int n=0;
        String cadena= "INSERT INTO SOCIOS (ID_SOC,COD_SOC,NOMBRE,APELLIDOS,DNI_NIF,DIRECCION,TELEFONO_MOVIL)"
                        + "VALUES (COD_SOC_SQ.NEXTVAL,?,?,?,?,?,?)";
        
        try {
            PreparedStatement st=conexion.prepareStatement(cadena);
            st.setInt(1,Integer.parseInt(s.getCod_soc()));
            st.setString(2,s.getNombre());
            st.setString(3,s.getApellido());
            st.setString(4,s.getDni_nif());
            st.setString(5,s.getDireccion());
            st.setString(6,s.getTlfMovil());
            System.out.println("La sentencia es: "+cadena);
            n=st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n"+ex.getMessage());
        }        
        return n;  
    }
    
    public int ejecutaDelete(String codigo){
        int n=0;
        String cadena= "DELETE ON CASCADE FROM SOCIOS WHERE COD_SOC = ?";
        
        try {
            PreparedStatement st=conexion.prepareStatement(cadena);
            st.setInt(1, Integer.parseInt(codigo));
            System.out.println("La sentencia es: "+cadena);
            n=st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n"+ex.getMessage());
        } catch (NumberFormatException e){
            System.out.println("NumberFormatException: \n"+e.getMessage());
        }       
        return n;  
    }
    
//    public int ejecutaUpdate(String statement) {
//        int n = 0;
//        try {
//            Statement st = conexion.createStatement();
//            System.out.println("La sentencia es: " + statement);
//            n = st.executeUpdate(statement);
//            System.out.println("Se ha ejecutado correctamente");
//        } catch (SQLException ex) {
//            System.out.println("SQL Exception:\n" + ex.getMessage());
//        }
//        return n;
//    }

//    public ResultSet ejecutaConsulta(String consulta) {
//        Statement st = null;
//        ResultSet rs = null;
//        try {
//            st = conexion.createStatement();
//            rs = st.executeQuery(consulta);
//        } catch (SQLException ex) {
//            System.out.println("Error sql: " + ex.getMessage());
//        }
////        try {
////            st.close();
////        } catch (SQLException ex) {
////            System.out.println("Error sql: " + ex.getMessage());
////        }
//        return rs;
//    }

//    public boolean buscaRegistro(String dniBuscar) {
//        ResultSet rs;
//        /*
//        Directamente la consulta sería así
//        rs=db.ejecutaConsulta("SELECT * from alumnos where nombre='" + nombre.getText()+"';");
//        Creo un String con la consulta para ver por consola la inyección de código
//        probaremos poniendo en el nombre: Pepe' or 1='1
//         */
//        String sentencia = "SELECT * from voluntario where dni='" + dniBuscar + "';";
//        System.out.println(sentencia);
//        rs = ejecutaConsulta(sentencia);
//        try {
//            //VIP primero compruebo que rs no es nullo, si lo es, lo segundo no se ejecuta
//            if (rs != null) {
//                if (rs.isBeforeFirst()) {
//                    
////                    VentanaListado vL = new VentanaListado(rs);
//                } else {
//                    return false;
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error con la base de datos: " + ex.getMessage());
//        }
//        return true; //aunque puede ser que se haya producido la excepción.  contamos conn el mensaje
//    }
//
//    public void cierraResultSet(ResultSet rs) {
//        try {
//            //cerramos el rs. porque garbage no puede eliminar el heap
//            rs.close();
//        } catch (SQLException ex) {
//            System.out.println("Error con la base de datos: " + ex.getMessage());
//        }
//    }

//    public void recorreResultado(ResultSet rs) {
//        try {
//            while (rs.next()) {
//                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)
//                        + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6)
//                 + "\t" + rs.getString(7) + "\t" + rs.getString(8) + "\t" + rs.getString(9)
//                 + "\t" + rs.getString(10));
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error sql: " + ex.getMessage());
//        }
//    }
    
//    public void pasarDatos(ResultSet rs, ArrayList voluntarios){
//        try {
//            while (rs.next()) {
//                Voluntario v1= new Voluntario(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
//                v1.setTelefono(rs.getString(7));
//                v1.setHorasDedicadas(Integer.parseInt(rs.getString(10)));
//                voluntarios.add(v1);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error sql: " + ex.getMessage());
//        }
//    }
}
