package edu.fiu.dbms.hospital.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCConnection {
	private String driver;
	private String url;
	private String user;
	private String pass;
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public void initParam(String paramFile) throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream(paramFile));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
	}

	public ResultSet executeSql(String sql) throws Exception {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.createStatement();
			boolean hasResultSet = stmt.execute(sql);
			if (hasResultSet) {
				rs = stmt.getResultSet();
				return rs;
			} else {
				// System.out.println("Impacted records amount is " +
				// stmt.getUpdateCount());
				return null;
			}
		} finally {
			if (rs != null) {
				// rs.close();
			}
			if (stmt != null) {
				// stmt.close();
			}
			if (conn != null) {
				// conn.close();
			}
		}
	}
}
