package com.thai.xml.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thai.xml.xpath.XMLUtil;

public class TitlesOfItemElementHandler extends DefaultHandler {

    boolean isItemElement;
    boolean isItemTitle;
    private List<String> titleList = new ArrayList<String>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (isItemElement && Objects.equals("title", qName)) {
            isItemTitle = true;
        }

        if (Objects.equals("item", qName)) {
            isItemElement = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isItemTitle) {
            String s = new String(ch, start, length);
            titleList.add(s);
            isItemTitle = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (isItemElement && Objects.equals("title", qName)) {
            isItemElement = false;
        }

    }

    @Override
    public void endDocument() throws SAXException {
        showTitles();
    }

    public void showTitles() {
        XMLUtil.log("Titres des éléments item de ce flux:");
        for (String s : titleList) {
            XMLUtil.log(s);
        }
    }
}
