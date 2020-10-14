package com.projectzero.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EnvironmentConnectionUtil {

	/*
	 * 
	 * This class sets up the connection between the java program and the sql database. 
	 * 
	 * The url, username, and password variables are taken from the environment global environment launch configuration.
	 */
	
	private String url = System.getenv("url");
	private String username = System.getenv("username");
	private String password = System.getenv("password");

	private static EnvironmentConnectionUtil instance;

	public static EnvironmentConnectionUtil getInstance() {
		if (instance == null) {
			instance = new EnvironmentConnectionUtil();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}