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
import java.util.ArrayList;
import modelo.Actividad;
import modelo.Factura;
import modelo.Socio;
import oracle.jdbc.driver.OracleDriver;

/**
 * Clase que me controla el acceso a la Base de Datos
 * @author Mario
 * @version 20/05/2016
 */
public class CtrlDataBase {
    String bd;
    String login;
    String password;
    String servidor;
    Connection conexion;
    
    /**
     * Constructor de la Clase CtrlDataBase
     * @param bd Base de datos
     * @param login Login de Usuario
     * @param password Nombre de Usuario
     * @param servidor Punto de Acceso al servidor
     */
    public CtrlDataBase(String bd, String login, String password, String servidor) {

        this.bd = bd;
        this.login = login;
        this.password = password;
        this.servidor = servidor;
    }
    
    /**
     * Establece conexion con la Base de datos
     * @return Boolean True si se ha establecido conexion
     */
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
     /**
      * Cerramos de forma segura la conexión con la base de Datos
      */   
    public void cerrarConexion() {

        try {
            conexion.close();
            System.out.println("Desconectado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Ejecuta un Insert en la tabla Socios
     * @param s Socio al que quiero Insertar en la BD
     * @return Int Cantidad de filas Insertadas
     */
    public int ejecutaInsert(Socio s){
        int n=0;
        String cadena= "INSERT INTO SOCIOS (ID_SOC,COD_SOC,NOMBRE,APELLIDOS,DNI_NIF,DIRECCION,TELEFONO_MOVIL)"
                        + "VALUES (COD_SOC_SQ.NEXTVAL,?,?,?,?,?,?)";
        
        try {
            PreparedStatement st=conexion.prepareStatement(cadena);
            st.setInt(1,Integer.parseInt(s.getCodSoc()));
            st.setString(2,s.getNombre());
            st.setString(3,s.getApellido());
            st.setString(4,s.getDniNif());
            st.setString(5,s.getDireccion());
            st.setString(6,s.getTlfMovil());
            System.out.println("La sentencia es: "+cadena);
            n=st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n"+ex.getMessage());
        }        
        return n;  
    }
    
    /**
     * Ejecuta un Update sobre la tabla Socios
     * @param s Socio sobre el que deseo modificar sus datos
     * @return Int Cantidad de filas modificadas
     */
    public int ejecutaUpdate(Socio s){
        int n=0;
        String cadena= "UPDATE SOCIOS"
                     + " SET NOMBRE = ?,"
                         + " APELLIDOS = ?,"
                         + " DNI_NIF = ?,"
                         + " DIRECCION = ?,"
                         + " TELEFONO_MOVIL = ?"
                     + " WHERE COD_SOC = ?";
        
        try {
            PreparedStatement st=conexion.prepareStatement(cadena);
            st.setString(1,s.getNombre());
            st.setString(2,s.getApellido());
            st.setString(3,s.getDniNif());
            st.setString(4,s.getDireccion());
            st.setString(5,s.getTlfMovil());
            st.setInt(6,Integer.parseInt(s.getCodSoc()));
            System.out.println("La sentencia es: "+cadena);
            n=st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n"+ex.getMessage());
        }        
        return n;  
    }
    
    /**
     * Ejecuta un Delete sobre la tabla Socios
     * @param codigo Código de socio al que quiero Eliminar
     * @return Int Cantidad de filas borradas
     */
    public int ejecutaDelete(String codigo){
        int n=0;
        String cadena= "DELETE FROM SOCIOS WHERE COD_SOC = ?";
        
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
    
    /**
     * Ejecuta una Select sobre la tabla Socios
     * @param codigo Código del socio que buscamos 
     * @return Socio Devuelve el Socio que buscabamos
     */
    public Socio buscaSocio(String codigo) {
        Socio s=null;
        String cadena= "SELECT COD_SOC,NOMBRE,APELLIDOS,DNI_NIF,DIRECCION,TELEFONO_MOVIL"
                     + " FROM SOCIOS"
                     + " WHERE COD_SOC = ? ";
                      
        try {
            PreparedStatement st=conexion.prepareStatement(cadena);
            st.setInt(1, Integer.parseInt(codigo));
            
            System.out.println("La sentencia es: "+cadena);
            
            ResultSet rs=st.executeQuery();
            
            s=toArrayListSocios(rs).get(0);
                
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n"+ex.getMessage());
        } catch (NumberFormatException e){
            System.out.println("NumberFormatException: \n"+e.getMessage());
        } catch (IndexOutOfBoundsException er){
            System.out.println("IndexOutOfBoundsException: \n"+er.getMessage());
        }      
        return s;  
    }
    
    /**
     * Ejecuta una Select sobre la tabla Socios
     * @return ArrayList Devuelve todos los socios
     */
    public ArrayList<Socio> listaSocios() { 
        ArrayList arry=new ArrayList();
        
        String cadena= "SELECT COD_SOC,NOMBRE,APELLIDOS,DNI_NIF,DIRECCION,TELEFONO_MOVIL"
                     + " FROM SOCIOS"
                     + " ORDER BY 2";
        
        try {
            PreparedStatement st=conexion.prepareStatement(cadena);
            
            System.out.println("La sentencia es: "+cadena);
            
            ResultSet rs=st.executeQuery();
            
            arry=toArrayListSocios(rs);
                
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n"+ex.getMessage());    
        }       
        return arry;  
    }
    
    /**
     * Ejecuta una Select sobre la tabla Actividad, Cursa y Socios
     * @return ArrayList Devuelve todas las Actividades y los socios que participan en ellas
     */
    public ArrayList listaActividad() { 
        ArrayList arry=new ArrayList();
        
        String cadena= "SELECT A.ID_ACTIVIDAD,A.NOMBRE_ACTIVIDAD,A.FECHA_INICIO,"
                           + " A.PERSONA_IMPARTE,A.CAPACIDAD,S.COD_SOC"
                     + " FROM CURSA C JOIN ACTIVIDAD A"
                     + " ON (C.ACTIVIDAD_ID_ACTIVIDAD = A.ID_ACTIVIDAD)"
                     + " JOIN SOCIOS S"
                     + " ON (C.SOCIOS_ID_SOC = S.ID_SOC)"
                     + " ORDER BY 1";
        
        try {
            PreparedStatement st=conexion.prepareStatement(cadena);
            
            System.out.println("La sentencia es: "+cadena);
            
            ResultSet rs=st.executeQuery();
            
            arry=toArrayListActividad(rs);
                
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n"+ex.getMessage());    
        }       
        return arry; 
         
    }
    
    /**
     * Ejecuta una Select sobre la tabla Factura y Socios
     * @return ArrayList Devuelve todas las Facturas y los socios a las que corresponden
     */
    public ArrayList listaFactura() { 
        ArrayList arry=new ArrayList(); 
               
        String cadena = "SELECT F.ID_FACT,F.FECHA_FACTURA,F.CABECERA_FACTURA,"
                           +  " F.TOTAL,S.COD_SOC"
                    + " FROM FACTURA F JOIN SOCIOS S"
                    + " ON (F.SOCIOS_ID_SOC = S.ID_SOC)"
                    + " ORDER BY 1";
        
        try {
            PreparedStatement st=conexion.prepareStatement(cadena);
            
            System.out.println("La sentencia es: "+cadena);
            
            ResultSet rs=st.executeQuery();
            
            arry=toArrayListFactura(rs);
            
                
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n"+ex.getMessage());    
        }       
        return arry; 
    }

    /**
     * Convierte un ResulSet en un ArrayList de Socios
     * @param r Resulset Contiene la filas de los socios
     * @return ArrayList Devuelve todos los socios
     * @throws SQLException Capturo en el método listaSocios()
     */
    private ArrayList<Socio> toArrayListSocios(ResultSet r) throws SQLException{
        ArrayList <Socio> arry=new ArrayList();
        
        while(r.next()){
            String codSoc=compruebaNull(r.getString(1));
            String nombre=compruebaNull(r.getString(2));
            String apellido=compruebaNull(r.getString(3));
            String dnif=compruebaNull(r.getString(4));
            String direccion=compruebaNull(r.getString(5));
            String tlfMovil=compruebaNull(r.getString(6));
            arry.add(new Socio(codSoc,nombre,apellido,dnif,direccion,tlfMovil));
            
        }
        r.close(); //Cierro ResultSet
        return arry;
    }
    
    /**
     * Convierte un ResulSet en un ArrayList de Actividad
     * @param r Resulset Contiene la filas de las Actividades
     * @return ArrayList Devuelve todos las Actividades
     * @throws SQLException Capturo en el método listaActividad()
     */
    private ArrayList<Actividad> toArrayListActividad(ResultSet r) throws SQLException{
        ArrayList <Actividad> arry=new ArrayList();
        
        while(r.next()){
            String idAct=compruebaNull(r.getString(1));
            String nombre=compruebaNull(r.getString(2));
            String fecha=compruebaNull(r.getString(3));
            String monitor=compruebaNull(r.getString(4));
            String capacidad=compruebaNull(r.getString(5));
            String codSoc=compruebaNull(r.getString(6));
            arry.add(new Actividad(idAct,nombre,fecha,monitor,capacidad,codSoc));
        }
        r.close(); //Cierro ResultSet
        return arry;
    }
    
    /**
     * Convierte un ResulSet en un ArrayList de Facturas
     * @param r Resulset Contiene la filas de las Facturas
     * @return ArrayList Devuelve todos las Facturas
     * @throws SQLException Capturo en el método listaFactura()
     */
    private ArrayList<Factura> toArrayListFactura(ResultSet r) throws SQLException{
        ArrayList <Factura> arry=new ArrayList();
        
        while(r.next()){
            String idFact=compruebaNull(r.getString(1));
            String fecha=compruebaNull(r.getString(2));
            String cabecera=compruebaNull(r.getString(3));
            String total=compruebaNull(r.getString(4));
            String codSoc=compruebaNull(r.getString(5));
            arry.add(new Factura(idFact,fecha,cabecera,total,codSoc));
        }
        r.close(); //Cierro ResultSet
        return arry;
    }
    
    /**
     * Convierte todas las String nulas en vacias
     * @param dato String que quiero Comprobar/Transformar
     * @return String Devuelve el String en caso de no ser nula
     */
    private String compruebaNull(String dato){       
        if (dato==null){
            dato="";
        }       
        return dato;
    }
}
