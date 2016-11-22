package edu.fiu.dbms.hospital.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			List<String> headersList = new ArrayList<String>();
			List<String> row = new ArrayList<String>();
			List<List<String>> rowsList = new ArrayList<List<String>>();
			int columnCount = rsmd.getColumnCount();
			if (isShowHeader) {
				for (int i = 0; i < columnCount; i++) {
					// System.out.print(rsmd.getColumnName(i + 1) + "\t");
					headersList.add(rsmd.getColumnName(i + 1));
				}
				// System.out.println();
			}
			while (resultSet.next()) {
				for (int i = 0; i < columnCount; i++) {
					// System.out.print(resultSet.getString(i + 1) + "\t");
					row.add(resultSet.getString(i + 1));

				}
				// System.out.println();

				rowsList.add(row);

			}
			Board board = new Board(275);
			Table table = new Table(board, 275, headersList, rowsList);
			List<Integer> colWidthsListEdited = Arrays.asList(12, 11, 11, 12, 11, 25, 13, 28, 13, 18, 9, 11);
			table.setGridMode(Table.GRID_FULL).setColWidthsList(colWidthsListEdited);
			Block tableBlock = table.tableToBlocks();
			board.setInitialBlock(tableBlock);
			board.build();
			String tableString = board.getPreview();
			System.out.println(tableString);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
