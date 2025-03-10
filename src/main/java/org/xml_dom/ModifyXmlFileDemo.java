package org.xml_dom;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ModifyXmlFileDemo {
    public static void main(String[] argv) {

        try {
            File inputFile = new File("xml/cars_modified.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            Node cars = doc.getFirstChild();
            Node supercar = doc.getElementsByTagName("supercars").item(0);

            // update supercar attribute
            NamedNodeMap attr = supercar.getAttributes();
            Node nodeAttr = attr.getNamedItem("company");
            nodeAttr.setTextContent("Lamborigini");

            // loop the supercar child node
            NodeList list = supercar.getChildNodes();

            //  modify node "luxurycars"
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if ("carname".equals(eElement.getNodeName())) {
                        if ("Ferrari 101".equals(eElement.getTextContent())) {
                            eElement.setTextContent("Lamborigini 001");
                        }
                        if ("Ferrari 202".equals(eElement.getTextContent()))
                            eElement.setTextContent("Lamborigini 002");
                    }
                }
            }


            //  delete node "luxurycars"
//            NodeList childNodes = cars.getChildNodes();
//            for(int count = 0; count < childNodes.getLength(); count++) {
//                Node node = childNodes.item(count);
//
//                if("luxurycars".equals(node.getNodeName()))
//                    cars.removeChild(node);
//            }

            // on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            //into current file
//            current file
//            StreamResult fileResult = new StreamResult(inputFile);
//            transformer.transform(source, fileResult);

            //into new file
            File outputFile = new File("xml/cars_modified_Denis.xml");
            StreamResult fileResult = new StreamResult(outputFile);
            transformer.transform(source, fileResult);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
