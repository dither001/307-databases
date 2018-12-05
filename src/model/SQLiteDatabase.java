package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabase {
	private static final String DB_URL = "jdbc:sqlite:C:/ProgramData/chargen.db";
	private static boolean hasData;

	/*
	 * CONSTRUCTORS
	 */
	public SQLiteDatabase() {
		hasData = false;
		setupDatabase();
	}

	/*
	 * PRIVATE METHODS
	 */
	private void setupDatabase() {
		if (!hasData) {
			hasData = true;

			Statement statement;
			String string = null;
			String tableName, key, address, data, fk;

			try (Connection c = DriverManager.getConnection(DB_URL)) {
				statement = c.prepareStatement(string);
				/*
				 * MASSIVE TODO
				 */

			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

	}

}
