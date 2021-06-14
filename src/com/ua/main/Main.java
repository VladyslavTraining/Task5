package com.ua.main;


import java.util.Arrays;
import java.util.List;

public class Main {
    private static String resultCSV = "result.csv";

    public static void main(String[] args) {
        List<CD> cdList = XMLParser.CD_LIST;

        System.out.println(cdList.get(0).getValue("title"));

        String[] row = cdList.get(0).getRow();
        System.out.println(Arrays.toString(row));

        String[] columns = cdList.get(0).getColumn();
        System.out.println(Arrays.toString(columns));
    }
}
