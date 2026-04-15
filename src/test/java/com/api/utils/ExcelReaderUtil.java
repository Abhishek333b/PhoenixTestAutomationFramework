package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkBook = new XSSFWorkbook(is);

		XSSFSheet mysheet = myWorkBook.getSheet("LoginTestData");
		XSSFRow myRow;
		XSSFCell myCell;

		int lastRowIndex = mysheet.getLastRowNum();
		System.out.println(lastRowIndex);

		XSSFRow rowHeader = mysheet.getRow(0);
		int lastIndexCol = rowHeader.getLastCellNum() - 1;
		System.out.println(lastIndexCol);

		for (int rowIndex = 0; rowIndex <= lastRowIndex; rowIndex++) {
			for (int colIndex = 0; colIndex <= lastIndexCol; colIndex++) {
				myRow = mysheet.getRow(rowIndex);
				myCell = myRow.getCell(colIndex);
				System.out.print(myCell+" ");
			}
			System.out.println();

		}

	}

}
