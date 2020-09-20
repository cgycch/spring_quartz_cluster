package org.cch.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtil {

	private static final Logger log = LoggerFactory.getLogger(DBUtil.class);
	private static final String default_username = "scott";
	private static final String default_password = "root";
	private static final String default_url = "jdbc:oracle:thin:@localhost:1521:cgycch";
	private static final String default_driver = "oracle.jdbc.OracleDriver";
	private static final Boolean default_auto_commit = true;

	static {
		try {
			Class.forName(default_driver);
			if (log.isDebugEnabled()) {
				log.debug("DBUtils default_driver[{}] register...", default_driver);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private DBUtil() {
	}

	public static Connection getConnection() {
		return getConn(default_auto_commit);
	}

	public static Connection getTxConnection() {
		return getConn(false);
	}

	private static Connection getConn(Boolean autoCommit) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(default_url, default_username, default_password);
			con.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			log.error("Getting connection error...", e);
		}
		return con;
	}

	public static void commit(Connection conn) throws SQLException {
		if (conn == null)
			return;
		conn.commit();
	}

	public static void rollback(Connection conn) throws SQLException {
		if (conn == null)
			return;
		conn.rollback();
	}

	public static void rollback(Connection conn, String message) {
		if (conn == null)
			return;
		try {
			log.info(message);
			conn.rollback();
		} catch (SQLException e) {
			log.error("Error occurred when " + message, e);
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			log.error("Closing connection error...", e);
		}
	}

	public static void close(Statement ps) {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			log.error("Closing preparedStatement error...", e);
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			log.error("Closing ResultSet error...", e);
		}
	}
}
