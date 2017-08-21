package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FileReader fileReader;
        fileReader = new FileReader(request.getServletContext().getRealPath("/")+"data/cuentas.xml");
        String fileContents = "";
        int i ;
        while((i =  fileReader.read())!=-1){
            char ch = (char)i;
            fileContents = fileContents + ch;
        }
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(fileContents);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String index = request.getParameter("index");
        String cuenta_name = request.getParameter("cuenta_name");
        String cuenta_info = request.getParameter("cuenta_info");
        String cuenta_type = request.getParameter("cuenta_type");
        
        if(index.equals("save"))try {saveToServer(request,response,cuenta_name,cuenta_info,cuenta_type);}catch (ParserConfigurationException | SAXException ex) {}
        
    }
    
    protected void saveToServer(
            HttpServletRequest rq, HttpServletResponse rp,
            String cuenta_name, String cuenta_info,String cuenta_type)
            throws ServletException, IOException, ParserConfigurationException, SAXException {

        try {
            Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(rq.getServletContext().getRealPath("/")+"data/cuentas.xsd"));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();dbFactory.setSchema(schema);dbFactory.setNamespaceAware(true);
            Document doc = dbFactory.newDocumentBuilder().parse(new File(rq.getServletContext().getRealPath("/")+"data/cuentas.xml"));
            
            NodeList nodes = doc.getElementsByTagName("cuenta");
            boolean bool = true;
            for(int i=0;i<nodes.getLength();i++){
                if(nodes.item(i).getAttributes().item(0).getTextContent().equals(cuenta_name)){
                    JsonElement from = new JsonParser().parse(cuenta_info);
                    JsonObject objfrom = from.getAsJsonObject();
                    JsonArray arrfrom = objfrom.getAsJsonArray("movs");
                    //nodes.item(i).setTextContent(cuenta_info); 
                    
                    JsonElement _to = new JsonParser().parse(nodes.item(i).getTextContent());
                    JsonObject objto = _to.getAsJsonObject();
                    JsonArray arrto = objto.getAsJsonArray("movs");
                    arrto.addAll(arrfrom);
                    nodes.item(i).setTextContent(_to.toString());
                    bool = false;
                    break;
                }
            }
            if(bool){
                Node cuentas = doc.getElementsByTagName("class").item(0);
                Element cuenta = doc.createElement("cuenta");
                cuenta.setAttribute("name",cuenta_name);
                cuenta.setAttribute("type",cuenta_type);
                cuenta.setTextContent(cuenta_info);
                cuentas.appendChild(cuenta);
            }
             
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rq.getServletContext().getRealPath("/")+"data/cuentas.xml"));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {} catch (TransformerException ex) {}
    }
    protected void getTotal(
            HttpServletRequest rq, HttpServletResponse rp,
            String cuenta_name, String cuenta_info,String cuenta_type)
            throws ServletException, IOException, ParserConfigurationException, SAXException {

            Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(rq.getServletContext().getRealPath("/")+"data/cuentas.xsd"));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();dbFactory.setSchema(schema);dbFactory.setNamespaceAware(true);
            Document doc = dbFactory.newDocumentBuilder().parse(new File(rq.getServletContext().getRealPath("/")+"data/cuentas.xml"));

            NodeList nodes = doc.getElementsByTagName("cuenta");
            int total;
            JsonArray totArr = new JsonArray();
            for(int i=0;i<nodes.getLength();i++){
                JsonElement tot = new JsonParser().parse(nodes.item(i).getTextContent());
                JsonObject totobj  = tot.getAsJsonObject();
                JsonArray arrtot = totobj.getAsJsonArray("movs");
                totArr.addAll(arrtot);
            }
            System.out.println(totArr.toString());
    }
}