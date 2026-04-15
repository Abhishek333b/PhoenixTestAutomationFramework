package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.pojo.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

import org.apache.poi.ss.usermodel.Cell;
//import com.google.common.collect.Table.Cell;

public class ExcelReaderUtil2 {
	private ExcelReaderUtil2() {

	}

	public static <T> Iterator<T> loadTestData(String sheetName,Class<T> clazz)  {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet mysheet = myWorkBook.getSheet(sheetName);//"LoginTestData"
		List<T>datalist	=Poiji.fromExcel(mysheet, clazz);
		return datalist.iterator();
	}

}
