package edu.fiu.dbms.hospital.util;

import java.sql.ResultSet;

/**
 * This class is used to control DB interactive exceptions.
 * 
 * @author PINCHAO LIU
 *
 */
public class InteractDB {

	public static void insertInfoToDB(JDBCConnection connection, String sql) {
		try {
			connection.executeSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet showInfoFromDB(JDBCConnection connection, String sql) {
		return queryFromDB(connection, sql);
	}

	public static ResultSet callFunctionFromDB(JDBCConnection connection, String sql) {
		return queryFromDB(connection, sql);
	}

	private static ResultSet queryFromDB(JDBCConnection connection, String sql) {
		try {
			return connection.executeSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void showResultOnConsole(ResultSet resultSet, boolean isShowHeader) {
		DBTablePrinter.printResultSet(resultSet);

		// try {
		// ResultSetMetaData rsmd = resultSet.getMetaData();
		// List<String> headersList = new ArrayList<String>();
		// List<String> row = new ArrayList<String>();
		// List<List<String>> rowsList = new ArrayList<List<String>>();
		// int columnCount = rsmd.getColumnCount();
		// if (isShowHeader) {
		// for (int i = 0; i < columnCount; i++) {
		// System.out.print(rsmd.getColumnName(i + 1) + "\t");
		// headersList.add(rsmd.getColumnName(i + 1));
		// }
		// System.out.println();
		// }
		// while (resultSet.next()) {
		// for (int i = 0; i < columnCount; i++) {
		// System.out.print(resultSet.getString(i + 1) + "\t");
		// row.add(resultSet.getString(i + 1));
		//
		// }
		// System.out.println();
		//
		// rowsList.add(row);
		//
		// }
		//
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

	}

}
