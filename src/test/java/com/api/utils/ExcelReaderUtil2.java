package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.pojo.request.model.UserCredentials;

import org.apache.poi.ss.usermodel.Cell;
//import com.google.common.collect.Table.Cell;

public class ExcelReaderUtil2 {
	private ExcelReaderUtil2() {

	}

	public static Iterator<UserCredentials> loadTestData()  {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet mysheet = myWorkBook.getSheet("LoginTestData");
		XSSFRow myRow;
		XSSFCell myCell;

		// Read Excel file --> store in ArrayList<UserCredentials>
		// i want to know indexes for the username and password in our sheet!
		XSSFRow headerRows = mysheet.getRow(0);

		int userNameIndex = -1;
		int passwordIndex = -1;

		for (Cell cell : headerRows) {
			if (cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				userNameIndex = cell.getColumnIndex();
			}
			if (cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex = cell.getColumnIndex();
			}

		}
		System.out.println(userNameIndex + " " + passwordIndex);
		int lastRowIndex = mysheet.getLastRowNum();
		XSSFRow rowData;
		UserCredentials userCredentials;
		ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();
		for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
			rowData = mysheet.getRow(rowIndex);
			userCredentials = new UserCredentials(rowData.getCell(userNameIndex).toString(),
					rowData.getCell(passwordIndex).toString());
			userList.add(userCredentials);
		}
		return	userList.iterator();

	}

}
