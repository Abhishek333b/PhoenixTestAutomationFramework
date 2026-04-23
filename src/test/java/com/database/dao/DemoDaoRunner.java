package com.database.dao;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;

import java.sql.SQLException;

import com.database.model.JobHeadModel;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
		JobHeadModel jobHeadModel = JobHeadDao.getDataFromJobHead(259985);
		System.out.println(jobHeadModel);

	}

}
