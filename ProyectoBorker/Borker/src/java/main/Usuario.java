package main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;
import org.xml.sax.SAXException;

public class Usuario {
    //Variables
    private String id, nombre, password, tipo;
    //Constructor
    public Usuario(String id, String nombre, String password, String tipo){
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
    }
    //Métodos//
    //Getters    
    String getNombre(){return nombre;}
    String getId(){return id;}
    String getPassword(){return password;}
    String getTipo(){return tipo;}
    //Setters
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setId(String id){this.id = id;}
    public void setPassword(String password){this.password = password;}
    public void getTipo(String tipo){this.tipo = tipo;}
    public boolean verifExist(String relpath) throws IOException{
        try {
            SchemaFactory schemafac = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemafac.newSchema(new File(relpath+"/data/login.xsd"));
            XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);
            SAXBuilder sb = new SAXBuilder(factory);
            try {
                Document doc = (Document) sb.build(new File(relpath+"/data/login.xml"));
                List<Element> list = doc.getRootElement().getChildren("usuario");
            for ( int i = 0; i < list.size(); i++ ){
                Element user_element = (Element) list.get(i);
                if(this.nombre.equals(user_element.getChildText("nombre"))){
                    return true;
                }
            }
            } catch (JDOMException ex) {
            }
        }   catch (SAXException ex) {
        }return false;
    }
}
