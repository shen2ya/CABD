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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XMLHelper {




    public static void XMLProccesor(File input, File out, boolean obfuscate) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        InputStream trgt = new ByteArrayInputStream(FileUtils.readFileToByteArray(input));
        DocumentBuilder docBldr = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = docBldr.parse(trgt);
        Element rootElement = doc.getDocumentElement();
        if (obfuscate)
            XMLEditorM(rootElement, Obfuscator::obf);
        else
            XMLEditorM(rootElement, Obfuscator::revert);
        TransformerFactory trnsfrmFctr = TransformerFactory.newInstance();
        Transformer transf = trnsfrmFctr.newTransformer();
        DOMSource src = new DOMSource(doc);
        StreamResult res = new StreamResult(out);
        transf.transform(src, res);
    }

    public static void XMLEditorM(Node root, MyStringChanger chng) {
        switch (root.getNodeType()) {
            case Node.ELEMENT_NODE:
                Element elem = (Element) root;
                for (int i = 0; i < elem.getAttributes().getLength(); i++) {
                    elem.setAttribute(elem.getAttributes().item(i).getNodeName(), chng.change(elem.getAttributes().item(i).getNodeValue()));
                }
                for (int i = 0; i < root.getChildNodes().getLength(); i++) {
                    XMLEditorM(root.getChildNodes().item(i), chng);
                }
                break;
            case Node.TEXT_NODE:
                root.setTextContent(chng.change(root.getTextContent()));
                break;
        }
    }
}
