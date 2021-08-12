package com.delphi.parsers;


import com.delphi.readers.CDReader;
import com.delphi.readers.Reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ParserFactory {
    private static final Logger LOGGER;

    static {
        InputStream stream = ParserFactory.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER = Logger.getLogger(ParserFactory.class.getName());
    }

    public Parser<?> createParser(Reader reader) {
        if (reader == null) {
            LOGGER.severe("File not found");
            throw new RuntimeException();
        } else if (reader instanceof CDReader) {
            LOGGER.config("Creating parser from " + reader);
            return new CDParser();
        } else {
            LOGGER.severe("Illegal Argument Exception - " + reader.toString());
            throw new IllegalArgumentException("Wrong type");
        }
    }

}

