/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.Socio;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
/**
 *
 * @author Mario
 */
public class CtrlDocumentoXML {
        
//    private static Document pasarXmlADom(String nombreFichero) {
//        
//        Document doc = null;
//        try {
//        //1º Creamos una nueva instancia de un fabricante de constructores de documentos
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            //2º A partir de la instancia anterior, fabricamos un constructor
//            // de documentos, que procesará el XML.
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            //3º Procesamos el documento (almacenado en un archivo) y lo convetimos en un árbol DOM.
//            doc = db.parse(new File(nombreFichero));
//            return doc;
//        } catch (ParserConfigurationException | SAXException | IOException ex) {
//            System.out.println("Error Archivo " + nombreFichero);
//        }
//        //Es necesario que la variable doc esté fuera del try si quiero devolverla
//        //en caso de error doc valdrá null
//        return doc;
//    }
    

    

    

    

       
    

//    
//    
//    public static boolean generaXML(String nombreDocumento, ArrayList <Socio> socios ){
//        //sin datos, sólo el elemento raiz
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder db;
//
//        try {
//            db = dbf.newDocumentBuilder();
//            //Creamos el documento XML y le pasamos la etiqueta raiz
//            DOMImplementation implementation = db.getDOMImplementation();
//            Document document = implementation.createDocument(null, "MisPelis", null);
//            document.setXmlVersion("1.0");
//            //Main Node: Primer ejemplos, sólo con el elemento raíz
//            Element raiz = document.getDocumentElement();
//            //System.out.println("Raiz: " + raiz.getNodeName());
//            try {
//                //Ahora creamos un elemento con los datos del ResulSet
//                //Por cada Nombre crearemos una <alumno>
//                Iterator it=socios.listIterator();
//                while (it.hasNext()) {
//                    //Creamos la Etiqueta alumno
//                    Element etiquetaAlumno = document.createElement("alumno");
//                    etiquetaAlumno.setAttribute("id",rs.getString(1) );
//                    
//                    //Creamos la Etiqueta nombre
//                    Element etiquetaNombre = document.createElement("nombre");
//                    Text valorNombre = document.createTextNode(rs.getString(2));
//                    etiquetaNombre.appendChild(valorNombre);
//                    
//                    
//                    //Creamos la Etiqueta nota_media
//                    Element etiquetaMedia = document.createElement("nota_media");
//                    Text valorMedia= document.createTextNode(notaMedia(rs.getInt(3),rs.getInt(4),rs.getInt(5)));
//                    etiquetaMedia.appendChild(valorMedia);
//                    
//                    
//                    //Añadimos la etiqueta nombre y la etiqueta apellidos a la etiqueta persona
//                    etiquetaAlumno.appendChild(etiquetaNombre);
//                    etiquetaAlumno.appendChild(etiquetaMedia);
//                    //Añadimos la etiqueta persona a la raiz
//                    raiz.appendChild(etiquetaAlumno);
//                    //Pegamos el elemento a la raiz "Documento"
//                }
//                //IMPORTANTE cerrar el resultSet
//                rs.close();                
//            } catch (SQLException ex) {
//                System.out.println("Error sql: "+ex.getMessage());
//            }
//            
//            //Generate XML
//            Source source = new DOMSource(document);
//            //Indicamos donde lo queremos almacenar
//            //No tiene porque coincidir el Nombre de la etiqueta Raiz con la Etiqueta Raiz
//            Result result = new StreamResult(new java.io.File("fichero/"+nombreDocumento+".xml"));
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//            transformer.transform(source, result);
//
//        } catch (ParserConfigurationException ex) {
//            System.out.println("Error escribiendo Fichero");
//        } catch (TransformerConfigurationException ex) {
//            System.out.println("Error escribiendo Fichero");
//        } catch (TransformerException ex) {
//            System.out.println("Error escribiendo Fichero");
//        }
//        return true;
     
    
}
