package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner_Checking_OFHikariDatabaseManager {
	public static void main(String[] args) throws SQLException {
		DatabaseManager.getConnection();
	Connection conn=DatabaseManager.getConnection();
	System.out.println(conn);
	}

}
