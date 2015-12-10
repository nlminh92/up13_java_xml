package com.thai.xml.start;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

import com.thai.xml.rss.RSSReader;
import com.thai.xml.xpath.Marin;
import com.thai.xml.xpath.XMLUtil;

public class Main {

    public static void main(String[] args){
        String newLine = System.getProperty("line.separator");

        XMLUtil.log("Exercice 1 - Serialisation et ecrire � fichier");

        Marin marin = new Marin(1, "Ho", "Phu Thai", 26);
        Document document = XMLUtil.serialize(marin);

        File file = new File("output_marin.xml");
        try {
            XMLUtil.write(document, file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        XMLUtil.log(newLine + "Exercice 2 - Lire fichier et deserialisation");
        try {
            XMLUtil.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return;
        }
        XMLUtil.deserialize(document);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream is = RSSReader.read();
            RSSHandler rssHandler = new RSSHandler();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(is, rssHandler);
            is.close();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            XMLUtil.log(e.getMessage());
            e.printStackTrace();
        }

    }

}
