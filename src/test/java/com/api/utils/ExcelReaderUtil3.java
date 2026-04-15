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

//import com.api.pojo.CreateJobPayload;
import com.api.pojo.request.model.UserCredentials;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;
import com.api.pojo.request.model.CreateJobPayload; 

import org.apache.poi.ss.usermodel.Cell;

public class ExcelReaderUtil3 {
	public static void main(String[] args) {
		Iterator<CreateJobBean> iterator = ExcelReaderUtil2.loadTestData("CreateJobTestData", CreateJobBean.class);

		while(iterator.hasNext()) {
			CreateJobBean bean=iterator.next();
			CreateJobPayload createJobPayload=CreateJobBeanMapper.mapper(bean);
		}
	
	
	}

}
