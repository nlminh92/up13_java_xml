package com.thai.xml.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thai.xml.xpath.XMLUtil;

public class NameOfRootElementHandler extends DefaultHandler {
    private boolean isFirstElement = true;

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (isFirstElement) {
            isFirstElement = false;
            showRootElement(qName);
        }
    }

    private void showRootElement(String name) {
        XMLUtil.log("Root element: " + name);
    }

}