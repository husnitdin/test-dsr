package com.example.dsrtest.utilities;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;

public class ExcelDataProvider {

    XSSFWorkbook wb;

    public ExcelDataProvider()  {

        File src = new File("./TestData/Data.xlsx");
        try {
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
        } catch (Exception e){
            System.out.println("Unable to read excel "+e.getMessage());
        }
    }

    public String getStringData(String sheetName, int row, int column){

        return wb.getSheet(sheetName)
                .getRow(row)
                .getCell(column)
                .getStringCellValue();
    }

    public String getStringData(int sheetIndex, int row, int column){
        return wb.getSheetAt(sheetIndex)
                .getRow(row)
                .getCell(column)
                .getStringCellValue();
    }

    public long getNumericData(String sheetName, int row, int column){
        return (long)wb.getSheet(sheetName)
                .getRow(row)
                .getCell(column)
                .getNumericCellValue();
    }
}
