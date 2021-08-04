package com.delphi.writers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class POIWriter implements Writer {
    public static final int START_POINT = 0;

    private void writeAtTheBeginning(String[] arr, String fileName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        writeToCell(arr, sheet, START_POINT);
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        }
    }

    private void writeToCell(String[] arr, XSSFSheet sheet, int rowCount) {
        Row row = sheet.createRow(rowCount);
        for (int i = 0; i < arr.length; i++) {
            row.createCell(i).setCellValue(arr[i]);
        }
    }

    @Override
    public void write(String[] arr, String fileName) {
        try {
            if (!new File(fileName).exists()) {
                writeAtTheBeginning(arr, fileName);
            } else {
                FileInputStream xlsxFile = new FileInputStream(fileName);
                XSSFWorkbook cdBook = new XSSFWorkbook(xlsxFile);
                XSSFSheet cdSheet = cdBook.getSheetAt(START_POINT);
                int lastRow = cdSheet.getLastRowNum();
                writeToCell(arr, cdSheet, ++lastRow);
                xlsxFile.close();
                FileOutputStream outputFile = new FileOutputStream(fileName);
                cdBook.write(outputFile);
                outputFile.close();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}
