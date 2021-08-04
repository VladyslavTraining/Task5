package com.delphi.main;


import com.delphi.data.AbstractColumnData;
import com.delphi.parsers.Parser;
import com.delphi.writers.Writer;

import java.util.List;


public class Executor {

    private final Writer writer;
    private final Parser parser;

    public Executor(Parser<?> parser, Writer writer) {
        this.parser = parser;
        this.writer = writer;
    }

    public void execute(String fileName) {
        List<? extends AbstractColumnData> list = parser.parse();
        writer.write(list.get(0).getColumn(), fileName);
        list.forEach(cd -> writer.write(cd.getRow(), fileName));
    }

}
