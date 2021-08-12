package com.delphi.parsers;


import com.delphi.Logger.LoggerInitialization;
import com.delphi.readers.CDReader;
import com.delphi.readers.Reader;

import java.util.logging.Logger;

public class ParserFactory {

    private static final Logger LOGGER = new LoggerInitialization().initialization();

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

