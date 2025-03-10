package org.lab3.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

class BirdSAXHandler extends DefaultHandler {
    private boolean inFamilie = false;
    private boolean inPasare = false;
    private boolean inCaracteristica = false;

    private StringBuilder caracteristici = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("familie")) {
            String numeFamilie = attributes.getValue("nume");
            String idFamilie = attributes.getValue("id");
            System.out.println("Familie: " + numeFamilie + " (ID: " + idFamilie + ")");
            inFamilie = true;
        }

        if (qName.equalsIgnoreCase("pasare")) {
            String idPasare = attributes.getValue("id");
            System.out.println("  Pasăre (ID: " + idPasare + ")");
            inPasare = true;
        }

        if (inPasare) {
            if (qName.equalsIgnoreCase("nume") || qName.equalsIgnoreCase("specie") || qName.equalsIgnoreCase("an_descoperire")
                    || qName.equalsIgnoreCase("tip") || qName.equalsIgnoreCase("populatie_estimata")) {
                System.out.print("    " + qName + ": ");
            }

            if (qName.equalsIgnoreCase("caracteristica")) {
                inCaracteristica = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).trim();
        if (!data.isEmpty()) {
            if (inPasare) {
                if (inCaracteristica) {
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
        if (qName.equalsIgnoreCase("familie")) {
            inFamilie = false;
        }

        if (qName.equalsIgnoreCase("pasare")) {
            if (caracteristici.length() > 0) {
                System.out.println("    Caracteristici: " + caracteristici);
                System.out.println("-------------------");
            }
            inPasare = false;
            caracteristici.setLength(0);
        }

        if (qName.equalsIgnoreCase("caracteristica")) {
            inCaracteristica = false;
        }
    }
}

public class ReadSAX {
    private final static String PATH = "src/main/java/org/lab3/resources/birdsDOM.xml";

    public static void main(String[] args) {
        try {
            // Creăm parserul SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            BirdSAXHandler handler = new BirdSAXHandler();

            // Parsăm fișierul XML
            File xmlFile = new File(PATH);
            saxParser.parse(xmlFile, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
