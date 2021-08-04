package com.delphi.main;


import com.delphi.data.AbstractColumnData;
import com.delphi.parsers.Parser;
import com.delphi.parsers.ParserFactory;
import com.delphi.readers.CDReader;
import com.delphi.writers.Writer;
import com.delphi.writers.WriterFactory;


public class Main {

    private static final String resultCSV = "result.csv";
    private static final String resultXLS = "resultExcel.xls";
    private static final String resultApachePOI = "resultExcelPoi.xlsx";
    private static final String inputFile = "cd_catalog.xml";

    public static void main(String[] args) {
        ParserFactory parserFactory = new ParserFactory();
        CDReader reader = new CDReader(inputFile);
        Parser<? extends AbstractColumnData> parser = parserFactory.createParser(reader);

        WriterFactory writerFactory = new WriterFactory();
        Writer csvWriter = writerFactory.createWriter("csv");

        Executor mainExecutor = new Executor(parser, csvWriter);
        mainExecutor.execute(resultCSV);

    }

}
