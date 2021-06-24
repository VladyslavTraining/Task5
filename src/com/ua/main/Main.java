package com.ua.main;


import java.util.List;

public class Main {
    private static String resultCSV = "result.csv";

    public static void main(String[] args) {
        List<CD> cdList = XMLParser.CD_LIST;
        XMLParser.writeToCSV(cdList.get(0).getColumn(), resultCSV);
        for (CD cd : cdList) {
            XMLParser.writeToCSV(cd.getRow(), resultCSV);
        }
    }
}
