package main;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class Cuenta {
    //Globales
    public static DateFormat formatoFecha = new SimpleDateFormat("yyyy-mm-dd");
    //Variables
    private String nombre;
    private String tipo;
    private ArrayList<String> movimientos;
    //Constructores
    public Cuenta(String nombre, String tipo,String relpath){
        this.nombre = nombre;
        this.tipo = tipo;
        movimientos = new ArrayList();
    }
    //Getters
    public String getNombre(){return nombre;}
    public String getTipo(){return tipo;}
    public ArrayList getMovimientos(){return movimientos;}
    public float calcSaldo(){
        float total=0;
        return total;
    }
}