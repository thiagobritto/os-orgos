package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:BANCO/os-orgos.db");
    }
    
}
