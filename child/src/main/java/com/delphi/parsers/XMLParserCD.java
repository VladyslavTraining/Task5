package com.delphi.parsers;

import com.delphi.data.CD;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLParserCD implements Parser<CD> {

    private final String filePath;

    public XMLParserCD(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<CD> parse() {
        List<CD> cd = new ArrayList<>();
        File file = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("CD");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                CD cd1 = new CD();
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    cd1.setTitle(element.getElementsByTagName("TITLE").item(0).getTextContent());
                    cd1.setArtist(element.getElementsByTagName("ARTIST").item(0).getTextContent());
                    cd1.setCountry(element.getElementsByTagName("COUNTRY").item(0).getTextContent());
                    cd1.setCompany(element.getElementsByTagName("COMPANY").item(0).getTextContent());
                    cd1.setPrice(Double.parseDouble(element.getElementsByTagName("PRICE").item(0).getTextContent() + "$"));
                    cd1.setYear(Integer.parseInt(element.getElementsByTagName("YEAR").item(0).getTextContent()));
                    cd.add(cd1);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException();
        }
        return cd;
    }

}
