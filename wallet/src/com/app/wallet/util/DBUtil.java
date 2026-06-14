package com.app.wallet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@//localhost:1522/XEPDB1";
	private static final String USER = "hr";
	private static final String PASSWORD = "hr";

	private DBUtil() {
	}

	public static Connection dbConnect() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Oracle JDBC Driver를 찾을 수 없습니다.", e);
		} catch (SQLException e) {
			throw new RuntimeException("DB 연결 중 오류: ", e);
		}
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
			System.err.println("DB 자원 해제 중 오류: " + e.getMessage());
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException("commit 처리 중 오류: ", e);
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			System.err.println("rollback 처리 중 오류: " + e.getMessage());
		}
	}
}
