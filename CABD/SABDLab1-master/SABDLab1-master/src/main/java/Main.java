import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Scanner;

import org.apache.commons.cli.*;

public class Main {


    private  static  XMLHelper helper = new XMLHelper();
    Obfuscator obfuscator = new Obfuscator();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter input file path: ");
        String input = in.nextLine();
        System.out.println("Enter output file path: ");
        String output = in.nextLine();
        System.out.println("Enter mode (obfuscate or deobfuscate): ");
        String mode = in.nextLine();
        try {
            helper.XMLProccesor(new File(input), new File(output), mode.equals("obfuscate"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }




}
