package org.lab3.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Scanner;

class BirdHandler extends DefaultHandler {
    private String searchId;
    private boolean found = false;
    private boolean isCaracteristica = false;
    private StringBuilder caracteristici = new StringBuilder();

    public BirdHandler(String searchId) {
        this.searchId = searchId;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("pasare")) {
            String idPasare = attributes.getValue("id");
            if (idPasare.equals(searchId)) {
                found = true;
                System.out.println("\nPasărea găsită:");
                System.out.println("  ID: " + idPasare);
            }
        }
        if (found) {
            if (qName.equalsIgnoreCase("nume") || qName.equalsIgnoreCase("specie") || qName.equalsIgnoreCase("an_descoperire")
                    || qName.equalsIgnoreCase("tip") || qName.equalsIgnoreCase("populatie_estimata")) {
                System.out.print("  " + qName + ": ");
            }
            if (qName.equalsIgnoreCase("caracteristica")) {
                isCaracteristica = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (found) {
            String data = new String(ch, start, length).trim();
            if (!data.isEmpty()) {
                if (isCaracteristica) {
                    if (caracteristici.length() > 0) caracteristici.append(", ");
                    caracteristici.append(data);
                } else {
                    System.out.println(data);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (found && qName.equalsIgnoreCase("caracteristica")) {
            isCaracteristica = false;
        }
        if (found && qName.equalsIgnoreCase("pasare")) {
            if (caracteristici.length() > 0) {
                System.out.println("  Caracteristici: " + caracteristici);
            }
            found = false; // Ne oprim după ce am găsit prima pasăre cu ID-ul căutat
        }
    }
}

public class QuerySAX {
    private final static String PATH = "src/main/java/org/lab3/resources/birdsDOM.xml";

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceți ID-ul păsării: ");
            String searchId = scanner.nextLine();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            BirdHandler handler = new BirdHandler(searchId);

            File xmlFile = new File(PATH);
            saxParser.parse(xmlFile, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
