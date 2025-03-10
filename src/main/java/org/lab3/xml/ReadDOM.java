package org.lab3.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadDOM {
    private final static String PATH = "src/main/java/org/lab3/resources/birdsDOM.xml";

    public static void main(String[] args) {
        try {
            File xmlFile = new File(PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            NodeList familiiList = document.getElementsByTagName("familie");

            for (int i = 0; i < familiiList.getLength(); i++) {
                Node familieNode = familiiList.item(i);
                if (familieNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element familieElement = (Element) familieNode;
                    String numeFamilie = familieElement.getAttribute("nume");
                    String idFamilie = familieElement.getAttribute("id");

                    System.out.println("Familie: " + numeFamilie + " (ID: " + idFamilie + ")");

                    NodeList pasariList = familieElement.getElementsByTagName("pasare");

                    for (int j = 0; j < pasariList.getLength(); j++) {
                        Node pasareNode = pasariList.item(j);
                        if (pasareNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element pasareElement = (Element) pasareNode;

                            String idPasare = pasareElement.getAttribute("id");
                            String nume = pasareElement.getElementsByTagName("nume").item(0).getTextContent();
                            String specie = pasareElement.getElementsByTagName("specie").item(0).getTextContent();
                            String anDescoperire = pasareElement.getElementsByTagName("an_descoperire").item(0).getTextContent();
                            String tip = pasareElement.getElementsByTagName("tip").item(0).getTextContent();
                            String populatie = pasareElement.getElementsByTagName("populatie_estimata").item(0).getTextContent();

                            System.out.println("\tPasăre (ID: " + idPasare + ")");
                            System.out.println("\t\tNume: " + nume);
                            System.out.println("\t\tSpecie: " + specie);
                            System.out.println("\t\tAn descoperire: " + anDescoperire);
                            System.out.println("\t\tTip: " + tip);

                            NodeList caracteristiciList = pasareElement.getElementsByTagName("caracteristica");
                            System.out.print("\t\tCaracteristici: ");
                            for (int k = 0; k < caracteristiciList.getLength(); k++) {
                                System.out.print(caracteristiciList.item(k).getTextContent());
                                if (k < caracteristiciList.getLength() - 1) {
                                    System.out.print(", ");
                                }
                            }
                            System.out.println();

                            System.out.println("\t\tPopulație estimată: " + populatie);
                        }
                    }
                    System.out.println("----------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
