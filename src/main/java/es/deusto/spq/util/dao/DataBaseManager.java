package es.deusto.spq.util.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {

	private Connection connection;

	public enum DBType {
		SQLITE, 
	};

	/**
	 * Constructor of the class. Creates the class and connects to the DataBase.
	 * @param dbType DBType (enum value) with the type of the database.
	 * @param dbName String with the name of the database.
	 */
	public DataBaseManager(DBType dbType, String dbName) {
		this.connect(dbType, dbName);
	}
	
	private void connect(DBType dbType, String dbName) {
		switch (dbType) {
			case SQLITE:
				this.connectSQLITE(dbName);
				break;
		}
	}
	
	private void connectSQLITE(String dbName) {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
		} catch (Exception ex) {
			System.err.println(this.getClass().getName() + ".connectSQLITE(): " + ex);
		}
	}

	/**
	 * Closes the connection to the database.
	 */
	public void disconnect() {
		try {
			this.connection.close();
		} catch (SQLException sqle) {
			System.err.println(this.getClass().getName() + ".disconnect(): " + sqle);
		}
	}

	/**
	 * Creates a Statement to interact with the database.
	 * @return Statement with the Statement to interact with the database.
	 */
	public Statement getStatement() {
		try {
			return this.connection.createStatement();
		} catch (SQLException sqle) {
			System.err.println(this.getClass().getName() + ".getStatement(): " + sqle);
			return null;
		}
	}

	/**
	 * Closes and releases a Statement that has been created.
	 * @param statement Statement with the Statement.
	 */
	public void releaseStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException sqle) {
			System.err.println(this.getClass().getName() + ".releaseStatement(): " + sqle);
		}
	}
}