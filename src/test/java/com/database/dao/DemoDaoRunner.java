package com.database.dao;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.model.JobHeadModel;

public class DemoDaoRunner {

	private static final Logger LOGGER = LogManager.getLogger(DemoDaoRunner.class);
	public static void main(String[] args) throws SQLException {
		JobHeadModel jobHeadModel = JobHeadDao.getDataFromJobHead(259985);
		System.out.println(jobHeadModel);

	}

}
