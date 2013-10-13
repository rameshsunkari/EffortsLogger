package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection {
	private static Logger logger = Logger.getLogger(DBConnection.class);

	public static void main(String[] args) {
		
		logger.debug("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			logger.debug("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		logger.debug("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "efforts_logger",
					"efforts_logger");

		} catch (SQLException e) {

			logger.debug("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			logger.debug("You made it, take control your database now!");
		} else {
			logger.debug("Failed to make connection!");
		}
	}

}
