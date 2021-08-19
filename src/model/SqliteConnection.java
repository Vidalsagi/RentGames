package model;

import java.sql.*;
import javax.swing.*;

public class SqliteConnection {
	private static SqliteConnection firstInstance = null;

	private SqliteConnection() {
	}

	static boolean firstThread = true;
	
	//Check for double session 
	public static SqliteConnection getInstance()
	{
		if (firstInstance == null) {
			firstInstance = new SqliteConnection();
			firstThread = false;
			return firstInstance;
		} else {
			return firstInstance;
		}
	}

	Connection conn = null;

	public Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			String conString = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\RentGame.sqlite";// directory of the
																										// sqlite.db
			Connection conn = DriverManager.getConnection(conString);
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
