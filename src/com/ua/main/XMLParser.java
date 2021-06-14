package com.ua.main;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLParser extends AbstractColumnData {

    public static void writeToCSV(String[] arr, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            for (String s : arr) {
                fw.write(s + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<CD> instanceList(String fileName) {
        List<CD> cd = new ArrayList<>();
        File file = new File(fileName);
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
                    cd1.setPrice(element.getElementsByTagName("PRICE").item(0).getTextContent() + "$");
                    cd1.setYear(element.getElementsByTagName("YEAR").item(0).getTextContent());
                    cd.add(cd1);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return cd;
    }


}
