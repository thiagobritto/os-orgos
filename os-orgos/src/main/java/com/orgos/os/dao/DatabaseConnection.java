package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String resource = DatabaseConnection.class.getResource("/os-orgos.db").getPath();
		return DriverManager.getConnection("jdbc:sqlite:" + resource);
	}

}
