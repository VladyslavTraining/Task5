package com.ua.main;



import java.util.Arrays;
import java.util.List;

public class Main {
    private static String resultCSV = "result.csv";
    private static String fileName = "cd_catalog.xml";

    public static void main(String[] args) throws ClassNotFoundException {
        List<CD> cdList = XMLParser.instanceList(fileName);
        String[] row = cdList.get(0).getRow();
        System.out.println(Arrays.toString(row));

    }
}
