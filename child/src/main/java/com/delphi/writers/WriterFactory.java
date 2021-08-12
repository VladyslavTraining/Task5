package com.delphi.writers;


import com.delphi.Logger.LoggerInitialization;

import java.util.logging.Logger;

public class WriterFactory {

    private static final Logger LOGGER = new LoggerInitialization().initialization();

    public Writer createWriter(String ch) {
        switch (ch) {
            case "csv":
                LOGGER.config("Creating writer with extension - " + "." + ch);
                return new CSVWriter();
            case "xls":
                LOGGER.config("Creating writer with extension - " + "." + ch);
                return new XLSWriter();
            case "xlsx":
                LOGGER.config("Creating writer with extension - " + "." + ch);
                return new POIWriter();
        }
        LOGGER.severe("Wrong extension " + ch);
        throw new IllegalArgumentException("Wrong type " + ch);
    }
}
