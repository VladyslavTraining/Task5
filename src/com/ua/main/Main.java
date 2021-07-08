package com.ua.main;


import java.util.List;

public class Main {

    private static final String resultCSV = "result.csv";
    private static final String resultXLS = "resultExcel.xls";
    private static final String resultApachePOI = "resultExcelPoi.xlsx";

    public static void main(String[] args) {
        List<CD> cdList = XMLParser.CD_LIST;

        Writer writerPOI = new POIWriter();
        Writer writerXLS = new XLSWriter();
        Writer writerCSV = new CSVWriter();

        getResult(cdList, writerPOI, resultApachePOI);
        getResult(cdList, writerXLS, resultXLS);
        getResult(cdList, writerCSV, resultCSV);

    }

    private static void getResult(List<CD> cdList, Writer writer, String fileName) {
        writer.write(cdList.get(0).getColumn(), fileName);
        for (CD cd : cdList) {
            writer.write(cd.getRow(), fileName);
        }
    }


}
