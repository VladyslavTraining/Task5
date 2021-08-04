package com.delphi.parsers;


import com.delphi.readers.CDReader;
import com.delphi.readers.Reader;

public class ParserFactory {

    public Parser<?> createParser(Reader reader) {
        if (reader instanceof CDReader) {
            return new CDparser(reader);
        } else {
            throw new IllegalArgumentException("Wrong type");
        }
    }
}

