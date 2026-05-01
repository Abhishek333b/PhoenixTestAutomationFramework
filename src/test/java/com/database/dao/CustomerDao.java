package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);

	private static final String CUSTOMER_DETAIL_QUERY = """

			select * from tr_customer where id = ? """;

	
	private	CustomerDao() {
	
    }
	
	public static CustomerDBModel getCustomerInfo(int customerId)  {
		CustomerDBModel customerDBModel=null;
		LOGGER.info("getting the connection from database manager");
		try {
		Connection conn = DatabaseManager.getConnection();
		PreparedStatement preparedstatement = conn.prepareStatement(CUSTOMER_DETAIL_QUERY);
		preparedstatement.setInt(1, customerId);
		LOGGER.info("Executing the SQL Query",CUSTOMER_DETAIL_QUERY);
		ResultSet resultSet = preparedstatement.executeQuery();
		
		while (resultSet.next()) {

			System.out.println(resultSet.getString("first_name"));
			System.out.println(resultSet.getString("email_id"));
			customerDBModel = new CustomerDBModel(
					resultSet.getInt("id"),
					
					resultSet.getString("first_name"), resultSet.getString("last_name"),
					resultSet.getString("mobile_number"), resultSet.getString("mobile_number_alt"),
					resultSet.getString("email_id"), resultSet.getString("email_id_alt"),
					resultSet.getInt("tr_customer_address_id")
					 
					
					
					);
		}
		}
		catch (SQLException e) {
			LOGGER.error("can not convert the Resultset to CustomerDBModel bean",e);
			
		}
		return customerDBModel;

	}

}
