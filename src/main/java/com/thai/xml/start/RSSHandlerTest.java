package com.thai.xml.start;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thai.xml.rss.CategoryHandler;
import com.thai.xml.rss.ItemElementCountHandler;
import com.thai.xml.rss.NameOfRootElementHandler;
import com.thai.xml.rss.RSSReader;
import com.thai.xml.rss.SubRootElementCountHandler;
import com.thai.xml.rss.TitlesOfItemElementHandler;
import com.thai.xml.xpath.XMLUtil;

public class RSSHandlerTest {

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            InputStream is = RSSReader.read();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler rootNameHandler = new NameOfRootElementHandler();
            saxParser.parse(is, rootNameHandler);

            is = RSSReader.read();
            DefaultHandler subRootElementCountHandler = new SubRootElementCountHandler();
            saxParser.parse(is, subRootElementCountHandler);

            is = RSSReader.read();
            DefaultHandler itemElementCountHandler = new ItemElementCountHandler();
            saxParser.parse(is, itemElementCountHandler);

            is = RSSReader.read();
            DefaultHandler titlesOfItemElementHandler = new TitlesOfItemElementHandler();
            saxParser.parse(is, titlesOfItemElementHandler);

            is = RSSReader.read();
            DefaultHandler categoryHandler = new CategoryHandler();
            saxParser.parse(is, categoryHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            XMLUtil.log(e.getMessage());
            e.printStackTrace();
        }

    }
}
