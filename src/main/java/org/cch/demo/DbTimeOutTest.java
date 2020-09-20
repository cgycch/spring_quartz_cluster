package org.cch.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Types;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import javax.sql.DataSource;

import org.cch.InAppBeanFactory;
import org.cch.utils.DBUtil;
import org.cch.utils.DateUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

public class DbTimeOutTest {

	private static final String RECEIVED = "received";
	private static final String INDEX = "index";
	// private static String sql = "select * from t_test";
	private static String sqlcall = "{CALL sp_timeout_query(?)}";

	public static void main(String[] args) throws SQLException {
		System.out.println("start" + DateUtils.format(new Date(), "HH:mm:ss"));
		Connection connection = DBUtil.getConnection();
		CallableStatement cstmt = connection.prepareCall(sqlcall);
		cstmt.setQueryTimeout(2);
		cstmt.registerOutParameter(1, Types.VARCHAR);
		try {
			cstmt.execute();
		} catch (Exception e) {
			if (e instanceof SQLTimeoutException) {
				System.out.println("SQLTimeoutException");
			} else {
				e.printStackTrace();
			}
		}
		String name = cstmt.getString(1);
		System.out.println("name is " + name);
		DBUtil.close(cstmt);
		DBUtil.close(connection);
		System.out.println("end" + DateUtils.format(new Date(), "HH:mm:ss"));
	}

	public static void test(String timeoutstr) throws SQLException {
		if (timeoutstr == null) {
			timeoutstr = "-1";
		}
		int timeout = Integer.parseInt(timeoutstr);
		System.out.println("Db query TimeOut Test...timeout is " + timeout);
		DataSource dataSource = InAppBeanFactory.getBean(DataSource.class);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("query start at time: " + DateUtils.format(new Date(), "HH:mm:ss"));

		Map<String, Integer> flagMap = new HashMap<>(2);
		flagMap.put(INDEX, 0);
		flagMap.put(RECEIVED, 0);
		do {
			try {
				extractedcall(timeout, jdbcTemplate, flagMap);
			} catch (QueryTimeoutException e) {
				System.out.println("query time out time " + flagMap.get(INDEX) + " but none receive");
			}
		} while (flagMap.get(RECEIVED) == 0 && flagMap.get(INDEX) < 3);

		if (flagMap.get(RECEIVED) == 0) {
			System.out.println("当前数据查询出现异常，请再次尝试");
		}

		System.out.println("query finished at time: " + DateUtils.format(new Date(), "HH:mm:ss"));
	}

	private static void extractedcall(int timeout, JdbcTemplate jdbcTemplate, Map<String, Integer> flagMap) {
		CallableStatementCreator csc = new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement prepareCall = con.prepareCall(sqlcall);
				prepareCall.registerOutParameter(1, Types.VARCHAR);
				return prepareCall;
			}
		};
		CallableStatementCallback<String> action = new CallableStatementCallback<String>() {
			@Override
			public String doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				return cs.getString(1);
			}
		};
		jdbcTemplate.setQueryTimeout(timeout);
		flagMap.put(INDEX, flagMap.get(INDEX) + 1);

		String result = jdbcTemplate.execute(csc, action);

		flagMap.put(RECEIVED, 1);
		System.out.println("result: " + result);

	}

}
