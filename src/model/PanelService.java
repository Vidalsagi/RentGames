package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class PanelService extends DBConnector {

	public static void updatePanel(int queryNum, JTable table) {
		
		SqliteConnection singelton = SqliteConnection.getInstance();
		Connection connection = singelton.dbConnector();
		
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "";

		switch (queryNum) {
		case 1:
			query = "SELECT * from Games";
			break;
		case 2:
			query = "SELECT GamesStockID,Name,NumOfCopy,Cost,Platform,Category from GamesStock join Games using(GameID) where IsRented='false'";
			break;
		case 3:
			query = " SELECT GamesStockID,Games.Name,Person.UserName[Customer],RentedStart,RentedEnd from Rented join GamesStock using(GamesStockID) join"
					+ " Games using(GameID) join Person on RentedBy=id ";
			break;
		}

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
			statement.close();
			resultSet.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}
	
	//Update panel in customer --- which games rented by customer 
	public static void updatePanel(int queryNum, JTable table, int ID) {
		SqliteConnection singelton = SqliteConnection.getInstance();
		Connection connection = singelton.dbConnector();
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "";
		switch (queryNum) {
		case 1:
			query = "SELECT Name,RentedEnd,RentedID,GamesStockID from Rented join GamesStock using(GamesStockID) join Games using(GameID) where RentedBy= "
					+ ID;
			break;
		}

		try {
			statement = connection.createStatement();

			resultSet = statement.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
			statement.close();
			resultSet.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

}
