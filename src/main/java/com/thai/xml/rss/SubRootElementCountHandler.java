package com.thai.xml.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thai.xml.xpath.XMLUtil;

public class SubRootElementCountHandler extends DefaultHandler {

    private boolean isFirstElement = true;
    private int childElementCount;

    private void resetCount() {
        childElementCount = 0;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (isFirstElement) {
            resetCount();
            isFirstElement = false;
            return;
        }
        childElementCount++;
    }

    @Override
    public void endDocument() throws SAXException {
        showChildElementCount();
    }

    private void showChildElementCount() {
        XMLUtil.log("Nombre de sous-éléments de l’élément racine: " + childElementCount);
    }

}