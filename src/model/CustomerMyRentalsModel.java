package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JPanel;
import javax.swing.JTable;
import view.CustomerMyRentals;

public class CustomerMyRentalsModel extends DBConnector{
	
	int selectedRentedID=0,selectedGamesStockID=0;
	JPanel contentPane;
	Customer cust;
	SqliteConnection singelton= SqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();
	
	public CustomerMyRentalsModel(Customer cust) {
		this.cust = cust;
	}
	
	//Prints to the client the selected game from his rented games list
	public void selectedOnTable() {
		JTable table = CustomerMyRentals.table;
		int row = table.getSelectedRow();
		selectedRentedID=(int) table.getModel().getValueAt(row, 2);
		selectedGamesStockID=(int)table.getModel().getValueAt(row, 3);
		System.out.println(selectedRentedID);
		System.out.println(selectedGamesStockID);
	}
	
	// Return a rented game
	public void returnFunc() throws Exception
	{
		System.out.println(selectedRentedID);
		System.out.println(selectedGamesStockID);
		JTable table = CustomerMyRentals.table;
		if(selectedGamesStockID != 0) // If a game was selected
		{
			// Set Rented attribute to false, then delete the row from Rented
			String query = "Update GamesStock set isRented='false' where GamesStockID=" + selectedGamesStockID;
			PreparedStatement pst = connection.prepareStatement(query);
			pst.execute();
			query = "Delete from Rented where RentedID="+selectedRentedID;
			pst = connection.prepareStatement(query);
			pst.execute();
			pst.close();
			PanelService.updatePanel(1, table, cust.customer.getID()); //Finally, Update the panel
		}
		else //If no game was selected
			throw new Exception("You need to select a game");
	}
}
