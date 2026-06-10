package com.app.wallet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1"; // "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "hr";
	private static final String PASSWORD = "hr";

	private DBUtil() {
	}

	public static Connection dbConnect() {
		Connection conn = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.err.println("DBUtil.dbConnect() - ClassNotFoundException : " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("DBUtil.dbConnect() - SQLException : " + e.getMessage());
		}

		return conn;
	}

	public static void dbDisconnect(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println("DBUtil.dbDisconnect() - SQLException : " + e.getMessage());
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			System.err.println("DBUtil.commit() - SQLException : " + e.getMessage());
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			System.err.println("DBUtil.rollback() - SQLException : " + e.getMessage());
		}
	}
}