package com.delphi.main;


import com.delphi.Logger.LoggerInitialization;
import com.delphi.data.AbstractColumnData;
import com.delphi.parsers.Parser;
import com.delphi.readers.Reader;
import com.delphi.writers.Writer;

import java.util.List;
import java.util.logging.Logger;


public class Executor {

    private static final Logger LOGGER = new LoggerInitialization().initialization();

    private final Reader reader;
    private final Writer writer;
    private final Parser<?> parser;

    public Executor(Reader reader, Parser<?> parser, Writer writer) {
        this.reader = reader;
        this.parser = parser;
        this.writer = writer;
    }

    public void execute(String fileName) {
        List<? extends AbstractColumnData> list = parser.parse(reader.read());
        writer.write(list.get(0).getColumn(), fileName);
        list.forEach(cd -> writer.write(cd.getRow(), fileName));
        LOGGER.fine(fileName + " is saved correct");
    }
}
