package com.delphi.writers;


public class WriterFactory {
    public Writer createWriter(String ch) {
        switch (ch) {
            case "csv":
                return new CSVWriter();
            case "xls":
                return new XLSWriter();
            case "xlsx":
                return new POIWriter();
        }
        throw new IllegalArgumentException("Wrong type " + ch);
    }
}
