package com.orgos.os.model;

import java.sql.Connection;
import java.sql.SQLException;

import com.orgos.os.dao.DatabaseConnection;

public class TransactionManager {

	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static void beginTransaction() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        connection.setAutoCommit(false);
        connectionHolder.set(connection);
    }

    public static void commit() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection != null) {
            connection.commit();
            closeConnection();
        }
    }

    public static void rollback() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection != null) {
            connection.rollback();
            closeConnection();
        }
    }

    private static void closeConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection != null) {
            connection.close();
            connectionHolder.remove();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        return (connection != null) ? connection : DatabaseConnection.getConnection();
    }

}
