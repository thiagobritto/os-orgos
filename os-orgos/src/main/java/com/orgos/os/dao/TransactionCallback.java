package com.orgos.os.dao;

import java.sql.Connection;

@FunctionalInterface
public interface TransactionCallback<T> {
	T doInTransaction(Connection conn) throws Exception;
}
