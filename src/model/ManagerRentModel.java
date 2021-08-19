package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import view.ManagerMenu;

public class ManagerRentModel {

	SqliteConnection singelton = SqliteConnection.getInstance();
	Connection connection = singelton.dbConnector();

	public void search(String txtSearchbox) {
		JTable table = ManagerMenu.table3;
		try {
			String query = " Select GamesStockID,Games.Name,Person.UserName[Customer],RentedStart,RentedEnd from Rented join GamesStock using(GamesStockID) join"
					+ " Games using(GameID) join Person on RentedBy=id where Customer like '"
					+ txtSearchbox + "%'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void returnFunc(int gameCopytable3) {
		System.out.println(gameCopytable3);
		JTable table = ManagerMenu.table3;
		try {
			String query = "UPDATE GamesStock set IsRented='false' where GamesStockID=" + gameCopytable3;
			PreparedStatement pst = connection.prepareStatement(query);
			pst.execute();
			pst = null;
			query = "DELETE from Rented where GamesStockID=" + gameCopytable3;
			pst = connection.prepareStatement(query);
			pst.execute();
			pst.close();
			System.out.println("Succesfully Returned");
			PanelService.updatePanel(3, table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
