package com.delphi.writers;


import com.delphi.parsers.CDParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class WriterFactory {
    private static final Logger LOGGER;

    static {
        InputStream stream = WriterFactory.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER = Logger.getLogger(WriterFactory.class.getName());
    }

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
