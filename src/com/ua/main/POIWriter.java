package com.ua.main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class POIWriter implements Writer {


    private static void writeToXLSX(String[] arr, String fileName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        int rowCount = 0;
        Row row = sheet.createRow(rowCount);
        for (int i = 0; i < arr.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(arr[i]);
        }
        try (
                FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        }
    }

    @Override
    public void write(String[] arr, String fileName) {
        try {
            if (!new File(fileName).exists()) {
                writeToXLSX(arr, fileName);
                return;
            }
            FileInputStream xlsxFile = new FileInputStream(fileName);
            XSSFWorkbook cdSheet = new XSSFWorkbook(xlsxFile);
            XSSFSheet worksheet = cdSheet.getSheetAt(0);
            int lastRow = worksheet.getLastRowNum();
            Row row = worksheet.createRow(++lastRow);
            for (int i = 0; i < arr.length; i++) {
                row.createCell(i).setCellValue(arr[i]);
            }
            xlsxFile.close();
            FileOutputStream outputFile = new FileOutputStream(fileName);
            cdSheet.write(outputFile);
            outputFile.close();
        } catch (Exception e) {
            System.out.println("Exception here");
        }
    }


}
