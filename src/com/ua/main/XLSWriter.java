package com.ua.main;

import java.io.FileWriter;
import java.io.IOException;

public class XLSWriter implements Writer {


    @Override
    public void write(String[] arr, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            for (int i = 0; i < arr.length; i++) {
                if (i == arr.length - 1) {
                    fw.write(arr[i] + System.lineSeparator());
                } else {
                    fw.write(arr[i] + "\t");
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
