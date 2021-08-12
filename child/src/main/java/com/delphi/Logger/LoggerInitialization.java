package com.delphi.Logger;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerInitialization {

    public Logger initialization() {
        InputStream stream = this.getClass().getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return Logger.getLogger(this.getClass().getName());
    }
}
