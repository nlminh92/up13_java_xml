package com.thai.xml.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.thai.xml.xpath.XMLUtil;

public class CategoryHandler extends DefaultHandler {

    String itemElement = null;
    boolean isTitleElement = false;
    boolean isCategoryElement = false;
    List<String> categoryValueList = new ArrayList<String>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (Objects.equals("item", qName)) {
            itemElement = qName;
        }

        if (itemElement != null) {
            if (Objects.equals("title", qName)) {
                isTitleElement = true;
            }
            if (Objects.equals("category", qName)) {
                if (attributes.getValue("domain").startsWith(RSSReader.CATEGORY_URI))
                    isCategoryElement = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (Objects.equals(itemElement, qName)) {
            itemElement = null;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        String content = new String(ch, start, length);
        if (isCategoryElement && isTitleElement) {
            categoryValueList.add(content);
            isCategoryElement = false;
        }
    }

    @Override
    public void endDocument() {
        XMLUtil.log("Valeurs des catégories commençant par " + RSSReader.CATEGORY_URI + ":");
        for (String s : categoryValueList) {
            System.out.println(s);
        }
    }
}
