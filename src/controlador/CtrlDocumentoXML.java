/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.ListIterator;
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

/**
 * Clase estática que me controla el acceso a un documento XML
 * @author Mario
 * @version 28/05/2016
 */
public class CtrlDocumentoXML {
     
    /**
     * Genera un documento XML que contiene los datos de todos los Socios
     * @param nombreDocumento Nombre que tendrá mi documento y su elemento Raiz
     * @param dataBase Conexión con la Base de Datos
     * @return Boolean True en caso de el documento se haya creado.
     */
    public static boolean generaXML(String nombreDocumento, CtrlDataBase dataBase ){
        ArrayList <Socio> socios=dataBase.listaSocios();
        boolean correcto=true;
        Socio s;
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        try {
            db = dbf.newDocumentBuilder();
            //Creamos el documento XML y le pasamos la etiqueta raiz
            DOMImplementation implementation = db.getDOMImplementation();
            Document document = implementation.createDocument(null, nombreDocumento, null);
            document.setXmlVersion("1.0");
            Element raiz = document.getDocumentElement();
            ListIterator it=socios.listIterator();
            while (it.hasNext()) {
                s=(Socio)it.next();
                //Creamos la Etiqueta socio
                Element etiquetaSocio = document.createElement("Socio");
                etiquetaSocio.setAttribute("cod",s.getCodSoc());

                //Creamos la Etiqueta nombre
                Element etiquetaNombre = document.createElement("Nombre");
                Text valorNombre = document.createTextNode(s.getNombre());
                etiquetaNombre.appendChild(valorNombre);


                //Creamos la Etiqueta apellido
                Element etiquetaApellido = document.createElement("Apellido");
                Text valorApellido= document.createTextNode(s.getApellido());
                etiquetaApellido.appendChild(valorApellido);

                //Creamos la Etiqueta DNI_NIF
                Element etiquetaDniF = document.createElement("Dni_Nif");
                Text valorDniF= document.createTextNode(s.getDniNif());
                etiquetaDniF.appendChild(valorDniF);

                //Creamos la Etiqueta Direccion
                Element etiquetaDireccion = document.createElement("Direccion");
                Text valorDireccion= document.createTextNode(s.getDireccion());
                etiquetaDireccion.appendChild(valorDireccion);

                //Creamos la Etiqueta Telefono
                Element etiquetaTelefono = document.createElement("Telefono");
                Text valorTelefono= document.createTextNode(s.getTlfMovil());
                etiquetaTelefono.appendChild(valorTelefono);

                //Añadimos las etiquetas a la etiqueta Socio
                etiquetaSocio.appendChild(etiquetaNombre);
                etiquetaSocio.appendChild(etiquetaApellido);
                etiquetaSocio.appendChild(etiquetaDniF);
                etiquetaSocio.appendChild(etiquetaDireccion);
                etiquetaSocio.appendChild(etiquetaTelefono);
                //Añadimos la etiqueta persona a la raiz
                raiz.appendChild(etiquetaSocio);
                //Pegamos el elemento a la raiz "Documento"
            }
           
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            //No tiene porque coincidir el Nombre de la etiqueta Raiz con la Etiqueta Raiz
            Result result = new StreamResult(new java.io.File("fichero/"+nombreDocumento+".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(source, result);

        } catch (ParserConfigurationException ex) {
            correcto=false;
            System.out.println("Error escribiendo Fichero");
        } catch (TransformerConfigurationException ex) {
            correcto=false;
            System.out.println("Error escribiendo Fichero");
        } catch (TransformerException ex) {
            correcto=false;
            System.out.println("Error escribiendo Fichero");
        }
        return correcto;   
    }
        
}
