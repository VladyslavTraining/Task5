package com.delphi.readers;


import java.io.*;

public class CDReader implements Reader {

    private final String fileName;

    public CDReader(String fileName) {
        this.fileName = fileName;
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
            e.printStackTrace();
        }
        return arr;
    }

}
