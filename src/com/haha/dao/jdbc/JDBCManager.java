package com.haha.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCManager {
	public static  DataSource ds;
	public static InitialContext context;
	static {
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/mydb");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}	
	
}
