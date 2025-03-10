package org.lab3.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

public class QueryDOM {
    private final static String PATH = "src/main/java/org/lab3/resources/birdsDOM.xml";

    public static void main(String[] args) {
        try {
            File xmlFile = new File(PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceți ID-ul păsării: ");
            String searchId = scanner.nextLine();

            NodeList pasariList = document.getElementsByTagName("pasare");
            boolean found = false;

            for (int i = 0; i < pasariList.getLength(); i++) {
                Node pasareNode = pasariList.item(i);
                if (pasareNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element pasareElement = (Element) pasareNode;
                    String idPasare = pasareElement.getAttribute("id");

                    if (idPasare.equals(searchId)) {
                        found = true;

                        // Extragem datele păsării
                        String nume = pasareElement.getElementsByTagName("nume").item(0).getTextContent();
                        String specie = pasareElement.getElementsByTagName("specie").item(0).getTextContent();
                        String anDescoperire = pasareElement.getElementsByTagName("an_descoperire").item(0).getTextContent();
                        String tip = pasareElement.getElementsByTagName("tip").item(0).getTextContent();
                        String populatie = pasareElement.getElementsByTagName("populatie_estimata").item(0).getTextContent();

                        System.out.println("\nPasărea găsită:");
                        System.out.println("\t\tID: " + idPasare);
                        System.out.println("\t\tNume: " + nume);
                        System.out.println("\t\tSpecie: " + specie);
                        System.out.println("\t\tAn descoperire: " + anDescoperire);
                        System.out.println("\t\tTip: " + tip);

                        // Citim caracteristicile
                        NodeList caracteristiciList = pasareElement.getElementsByTagName("caracteristica");
                        System.out.print("\t\tCaracteristici: ");
                        for (int j = 0; j < caracteristiciList.getLength(); j++) {
                            System.out.print(caracteristiciList.item(j).getTextContent());
                            if (j < caracteristiciList.getLength() - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println();

                        System.out.println("  Populație estimată: " + populatie);
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Pasărea cu ID-ul " + searchId + " nu a fost găsită.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
