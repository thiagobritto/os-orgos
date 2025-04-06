package com.orgos.os.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.orgos.os.dao.TransactionCallback;

public class TransactionManager {

	public static <T> T executeInTransaction(TransactionCallback<T> callback) throws SQLException {
		try (Connection conn = DatabaseConnection.getConnection()) {
			try {
				conn.setAutoCommit(false);
				T result = callback.doInTransaction(conn);
				conn.commit();
				return result;
			} catch (Exception e) {
				conn.rollback();
				throw new SQLException("Erro na transação, rollback realizado", e);
			}
		}
	}
	
}
