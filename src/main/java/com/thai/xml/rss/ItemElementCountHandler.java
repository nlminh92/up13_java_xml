package com.thai.xml.rss;

import java.util.Objects;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thai.xml.xpath.XMLUtil;

public class ItemElementCountHandler extends DefaultHandler {
    private boolean isFirstElement = true;
    private int itemElementCount;

    private void resetCount() {
        itemElementCount = 0;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (isFirstElement) {
            resetCount();
            isFirstElement = false;
            return;
        }
        if (Objects.equals("item", qName)) {
            itemElementCount++;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        showItemElementCount();
    }

    private void showItemElementCount() {
        XMLUtil.log("nombre d’éléments item: " + itemElementCount);
    }
}