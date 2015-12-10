package com.thai.xml.xpath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtil {
    private static final String MARIN_NOM = "nom";
    private static final String MARIN_PRN = "prenom";
    private static final String MARIN_AGE = "age";

    /**
     * Exercice 1
     */
    public static Document serialize(Marin marin) {
        Element rootElement = DocumentHelper.createElement("marin");
        rootElement.addAttribute("id", String.valueOf(marin.getId()));

        Document document = DocumentHelper.createDocument();
        document.add(rootElement);

        rootElement.addElement("nom").addText(marin.getNom());
        rootElement.addElement("prenom").addText(marin.getPrenom());
        rootElement.addElement("age").addText(String.valueOf(marin.getAge()));

        return document;
    }

    /**
     * Exercice 1
     * @throws IOException
     */
    public static void write(Document document, File file) throws IOException {
        XMLWriter writer = null;
        writer = new XMLWriter(new FileWriter(file));
        writer.write(document);
        writer.close();

        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        writer = new XMLWriter(System.out, format);
        writer.write(document);
    }

    /**
     * Exercice 2
     * @throws DocumentException
     */
    public static Document read(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(file);
    }


    /**
     * Exercice 2
     */
    @SuppressWarnings("unchecked")
    public static Marin deserialize(Document document) {
        Element rootElement = document.getRootElement();
        if (rootElement == null) {
            return null;
        }
        String rootName = rootElement.getName();
        log("Root name: " + rootName);

        List<Attribute> attrList = rootElement.attributes();

        Marin marin = new Marin();
        for (Attribute attribute : attrList) {
            if (Objects.equals("id", attribute.getName())) {
                marin.setId(Long.parseLong(attribute.getText()));
                break;
            }
        }

        List<Element> elementList = rootElement.elements();
        for (Element element : elementList) {
            switch (element.getName()) {
            case MARIN_NOM:
                marin.setNom(element.getText());
                break;
            case MARIN_PRN:
                marin.setPrenom(element.getText());
                break;
            case MARIN_AGE:
                marin.setAge(Integer.parseInt(element.getText()));
                break;
            default:
                continue;
            }
        }

        System.out.println(marin);
        return marin;
    }

    public static void log(String message) {
        System.out.println(message);
    }
}