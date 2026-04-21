package com.database.dao;

import java.sql.SQLException;

import com.api.pojo.Customer;
import com.database.model.CustomerDBModel;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
		CustomerDBModel customerDBData	=CustomerDao.getCustomerInfo(0);
		System.out.println(customerDBData);
		System.out.println(customerDBData.getFirst_name());
		System.out.println(customerDBData.getEmail_id());
		System.out.println(customerDBData.getMobile_number());
		Customer customer = new Customer("Ab", "b", "3434343343", "", "abhi@gmail.com", "");
	}

}
