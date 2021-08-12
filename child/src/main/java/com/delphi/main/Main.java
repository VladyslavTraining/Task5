package com.delphi.main;


import com.delphi.parsers.Parser;
import com.delphi.parsers.ParserFactory;
import com.delphi.readers.CDReader;
import com.delphi.readers.Reader;
import com.delphi.writers.Writer;
import com.delphi.writers.WriterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.*;

public class Main {

    private static Logger logger;

//    static {
//        String path = Main.class.getClassLoader()
//                .getResource("logging.properties")
//                .getFile();
//        System.setProperty("java.util.logging.config.file", path);
//        logger = Logger.getLogger(Main.class.getName());
//    }


    static {
        InputStream stream = Main.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = Logger.getLogger(Main.class.getName());
    }


//    static {
//        System.setProperty("java.util.logging.config.file",
//                "D:\\GitHub\\Task5\\child\\src\\main\\resources\\logging.properties");
//        //must initialize loggers after setting above property
//        logger = Logger.getLogger(Main.class.getName());
//    }

    private static final String resultCSV = "result.csv";
    private static final String resultXLS = "resultExcel.xls";
    private static final String resultApachePOI = "resultExcelPoi.xlsx";
    private static final String inputFile = "cd_catalog.xml";

    public static void main(String[] args) {
//        setupLogger();
        logger.info("App get start");
        ParserFactory parserFactory = new ParserFactory();
        WriterFactory writerFactory = new WriterFactory();
        Writer csvWriter = writerFactory.createWriter("csv");
        Reader reader = new CDReader(inputFile);
        Parser<?> parser = parserFactory.createParser(reader);
        Executor cdExecutor = new Executor(reader, parser, csvWriter);
        cdExecutor.execute(resultCSV);
        logger.info("App get end");
    }

    private static void setupLogger() {
        FileHandler handler;
        try {
            logger = Logger.getLogger(Main.class.getName());
            logger.setUseParentHandlers(false);
            handler = new FileHandler("myLogger.log", true);
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
            handler.setFormatter(new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working", e);
        }
    }


}
