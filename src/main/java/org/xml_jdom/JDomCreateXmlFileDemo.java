package org.xml_jdom;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.IOException;

public class JDomCreateXmlFileDemo {
    public static void main(String[] args) {

        try{
            File inputFile = new File("input2.txt");
            //root element
            Element carsElement = new Element("cars_new");
            Document doc = new Document(carsElement);

            //supercars element
            Element supercarElement = new Element("supercars");
            supercarElement.setAttribute(new Attribute("company","Ferrari"));
            supercarElement.setAttribute(new Attribute("company2","Ferrari2"));

            //supercars element
            Element carElement1 = new Element("carname");
            carElement1.setAttribute(new Attribute("type","formula one"));
            carElement1.setText("Ferrari 101");

            Element carElement2 = new Element("carname");
            carElement2.setAttribute(new Attribute("type","sports"));
            carElement2.setText("Ferrari 202");

            Element carElement3 = new Element("carname");
            carElement3.setAttribute(new Attribute("type","sports"));
            carElement3.setText("Ferrari 203");

            supercarElement.addContent(carElement1);
            supercarElement.addContent(carElement2);
            supercarElement.addContent(carElement3);

            doc.getRootElement().addContent(supercarElement);

            XMLOutputter xmlOutput = new XMLOutputter();

            // display xml
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, System.out);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
