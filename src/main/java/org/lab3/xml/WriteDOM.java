package org.lab3.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WriteDOM {
    private final static String PATH = "src/main/java/org/lab3/resources/birdsDOM.xml";
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Creare element rădăcină <pasari>
            Element rootElement = document.createElement("pasari");
            document.appendChild(rootElement);

            // Datele despre păsări organizate pe familii
            List<FamiliePasari> familii = Arrays.asList(
                    new FamiliePasari("Hirundinidae", "1", Collections.singletonList(
                            new Pasare("Randunica", "Hirundo rustica", 1758, "migratoare",
                                    Arrays.asList("Zbor rapid", "Cioc ascuțit"), 2000000)
                    )),
                    new FamiliePasari("Accipitridae", "2", Collections.singletonList(
                            new Pasare("Vultur", "Aquila chrysaetos", 1758, "prădător",
                                    Arrays.asList("Vedere ageră", "Gheare puternice"), 150000)
                    )),
                    new FamiliePasari("Anatidae", "3", Arrays.asList(
                            new Pasare("Rata", "Anas platyrhynchos", 1758, "acvatică",
                                    Arrays.asList("Penaj impermeabil", "Cioc lat"), 3000000),
                            new Pasare("Lebada", "Cygnus olor", 1758, "acvatică",
                                    Arrays.asList("Gât lung", "Alb imaculat"), 500000)
                    )),
                    new FamiliePasari("Spheniscidae", "4", Collections.singletonList(
                            new Pasare("Pinguin", "Aptenodytes forsteri", 1844, "polar",
                                    Arrays.asList("Nu zboară", "Înoată rapid"), 250000)
                    ))
            );

            // Construirea structurii XML
            for (FamiliePasari familie : familii) {
                Element familieElement = document.createElement("familie");
                familieElement.setAttribute("nume", familie.nume);
                familieElement.setAttribute("id", familie.id);
                rootElement.appendChild(familieElement);

                for (Pasare pasare : familie.pasari) {
                    Element pasareElement = document.createElement("pasare");
                    pasareElement.setAttribute("id", pasare.id);
                    familieElement.appendChild(pasareElement);

                    addTextElement(document, pasareElement, "nume", pasare.nume);
                    addTextElement(document, pasareElement, "specie", pasare.specie);
                    addTextElement(document, pasareElement, "an_descoperire", String.valueOf(pasare.anDescoperire));
                    addTextElement(document, pasareElement, "tip", pasare.tip);

                    Element caracteristiciElement = document.createElement("caracteristici");
                    pasareElement.appendChild(caracteristiciElement);
                    for (String caracteristica : pasare.caracteristici) {
                        addTextElement(document, caracteristiciElement, "caracteristica", caracteristica);
                    }

                    addTextElement(document, pasareElement, "populatie_estimata", String.valueOf(pasare.populatieEstimata));
                }
            }

            // Scrierea XML-ului într-un fișier
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(PATH));
            transformer.transform(source, result);

            System.out.println("Fișierul XML 'pasari.xml' a fost generat cu succes!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funcție pentru adăugarea elementelor text
    private static void addTextElement(Document doc, Element parent, String tagName, String value) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(value));
        parent.appendChild(element);
    }

    // Clasă pentru familia de păsări
    static class FamiliePasari {
        String nume;
        String id;
        List<Pasare> pasari;

        public FamiliePasari(String nume, String id, List<Pasare> pasari) {
            this.nume = nume;
            this.id = id;
            this.pasari = pasari;
        }
    }

    // Clasă pentru detaliile fiecărei păsări
    static class Pasare {
        static int counter = 1;
        String id;
        String nume;
        String specie;
        int anDescoperire;
        String tip;
        List<String> caracteristici;
        int populatieEstimata;

        public Pasare(String nume, String specie, int anDescoperire, String tip, List<String> caracteristici, int populatieEstimata) {
            this.id = String.format("%03d", counter++);
            this.nume = nume;
            this.specie = specie;
            this.anDescoperire = anDescoperire;
            this.tip = tip;
            this.caracteristici = caracteristici;
            this.populatieEstimata = populatieEstimata;
        }
    }
}
