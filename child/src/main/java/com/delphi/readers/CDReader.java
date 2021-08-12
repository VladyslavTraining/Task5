package com.delphi.readers;


import com.delphi.Logger.LoggerInitialization;

import java.io.*;
import java.util.logging.Logger;

public class CDReader implements Reader {

    private static final Logger LOGGER = new LoggerInitialization().initialization();

    private final String fileName;

    public CDReader(String fileName) {
        LOGGER.warning("Can be FileNotFoundException with " + fileName);
        if (new File(fileName).exists())
            this.fileName = fileName;
        else {
            LOGGER.severe("File not found " + fileName);
            throw new IllegalArgumentException();
        }
    }

    @Override
    public byte[] read() {
        byte[] arr = new byte[(int) new File(fileName).length()];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            int count = 0;
            while (br.ready()) {
                arr[count] = (byte) br.read();
                count++;
            }
        } catch (IOException e) {
            LOGGER.severe(e.toString());
        }
        return arr;
    }

    @Override
    public String toString() {
        return "fileName='" + fileName;
    }
}
