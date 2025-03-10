package org.lab3.xml;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XPathMethods {
    private final static String PATH = "src/main/java/org/lab3/resources/birdsDOM.xml";

    private static Document getDocument(String filePath) throws Exception {
        File file = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    public static boolean checkTagPresence(Document doc, String tagName) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//*[name() = '" + tagName + "']";
        NodeList nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        return nodes.getLength() > 0;
    }

    public static boolean checkTagHasChildren(Document doc, String tagName) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//*[name() = '" + tagName + "']/*";
        NodeList nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        return nodes.getLength() > 0;
    }

    public static List<String> getTagValues(Document doc, String tagName) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//*[name() = '" + tagName + "']";
        NodeList nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);

        List<String> values = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                values.add(node.getTextContent().trim());
            }
        }
        return values;
    }

    public static void main(String[] args) {
        try {
            Document doc = getDocument(PATH);

            System.out.println("Tag 'pasare' prezent: " + checkTagPresence(doc, "pasare"));

            System.out.println("Tag 'pasare' are copii: " + checkTagHasChildren(doc, "pasare"));

            List<String> numePasari = getTagValues(doc, "nume");
            System.out.println("Numele păsărilor: " + numePasari);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
