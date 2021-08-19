package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;
import view.ManagerMenu;

public class ManagerMenuModel extends DBConnector {

	SqliteConnection singelton = SqliteConnection.getInstance();
	Connection connection = singelton.dbConnector();
	
	//Select item from Games Table and show in windows
	public void mouseklicked_pane1(JTextField gameIDField, JTextField nameField,JTextField categoryField,JTextField releaseYearField,
			JTextField platformField,JTextField companyField,JTextField minAgeReqField,JTextField costField)
	{
		JTable table = ManagerMenu.table;
		try {
			int row = table.getSelectedRow();
			String gameID = (table.getModel().getValueAt(row, 0)).toString();
			String query = "select * from Games where GameID =?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, gameID);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				gameIDField.setText(rs.getString("GameID"));
				nameField.setText(rs.getString("Name"));
				categoryField.setText(rs.getString("Category"));
				releaseYearField.setText(rs.getString("ReleaseYear"));
				platformField.setText(rs.getString("Platform"));
				companyField.setText(rs.getString("Company"));
				minAgeReqField.setText(rs.getString("MinAgeReq"));
				costField.setText(rs.getString("Cost"));
			}
			pst.close();
			rs.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void updatePanel1(JTextField gameIDField, JTextField nameField,JTextField categoryField,JTextField releaseYearField,
			JTextField platformField,JTextField companyField,JTextField minAgeReqField,JTextField costField) throws SQLException
	//Update selected item from Games Table
	{
		JTable table = ManagerMenu.table;
		String query = "Update Games set Name=?,Category=?,ReleaseYear=?,Platform=?,Company=?,MinAgeReq=?,Cost=? where GameID=?";
		PreparedStatement pst = connection.prepareStatement(query);
		int i = 1;
		pst.setString(i++, nameField.getText());
		pst.setString(i++, categoryField.getText());
		pst.setInt(i++, Integer.parseInt(releaseYearField.getText()));
		pst.setString(i++, platformField.getText());
		pst.setString(i++, companyField.getText());
		pst.setInt(i++, Integer.parseInt(minAgeReqField.getText()));
		pst.setInt(i++, Integer.parseInt(costField.getText()));
		pst.setInt(i++, Integer.parseInt(gameIDField.getText()));
		pst.execute();
		PanelService.updatePanel(1, table);
		pst.close();
	}
	
	//Add a new Game into Games table DB
	public void savePanel1(String nameField,String categoryField,String releaseYearField,String platformField,
			String companyField,String minAgeReqField,String costField) throws SQLException
	{
		JTable table = ManagerMenu.table;
		JTable table2 = ManagerMenu.table2;
		String query = "insert into Games (Name,Category,ReleaseYear,Platform,Company,MinAgeReq,Cost) values (?,?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);
		int i = 1;
		pst.setString(i++, nameField);
		pst.setString(i++, categoryField);
		pst.setInt(i++, Integer.parseInt(releaseYearField));
		pst.setString(i++, platformField);
		pst.setString(i++, companyField);
		pst.setInt(i++, Integer.parseInt(minAgeReqField));
		pst.setInt(i++, Integer.parseInt(costField));
		pst.execute();
		PanelService.updatePanel(1, table);
		PanelService.updatePanel(2, table2);
		pst.close();
	}
	
	//Delete Game from Games table DB
	public void deletePanel1(String gameIDField) throws SQLException
	{
		JTable table = ManagerMenu.table;
		int action = JOptionPane.showConfirmDialog(null, "do you Really Want To Delete?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (action == 0) {
		String query = "Delete from Games where GameID='" + gameIDField + "' ";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.execute();
		pst.close();
		PanelService.updatePanel(1, table);
		}
	}

	//Search by comboBox value
	public void searchP1(String txtSearch,String selectedComboBox)
	{
		JTable table = ManagerMenu.table;
		try {
			PreparedStatement pst = connection
					.prepareStatement("SELECT * FROM Games WHERE " + selectedComboBox + " like ?");
			pst.setString(1, txtSearch + "%");
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	//Select item from Games Table and show in windows
	public String mouseklickedpane2(JTextField gameIDP2Field){
		JTable table2 = ManagerMenu.table2;
		String gamesStockID = null;
  		try 
  		{
  			int row = table2.getSelectedRow();
  			gamesStockID=(table2.getModel().getValueAt(row, 0)).toString();
  			String query = "select * from GamesStock where GamesStockID =?";
  			PreparedStatement pst = connection.prepareStatement(query);
  			pst.setString(1, gamesStockID);
        	ResultSet rs = pst.executeQuery();
    		while(rs.next())
    		{
    			gameIDP2Field.setText(rs.getString("GameID"));
    			}
    		pst.close();
    		rs.close();
    		}catch (Exception e1) 
  			{
  			e1.printStackTrace();
  		}
		return gamesStockID;
  	}
	
	
	public void pane2_addbutton(String gameIDP2Field) {
		JTable table2 = ManagerMenu.table2;
		try {
			GamesCopyService GCS = new GamesCopyService();
			int numOfCopy = GCS.numcopycheck(Integer.parseInt(gameIDP2Field));

			String query = "INSERT into GamesStock (GameID,NumOfCopy,IsRented) values (?,?,?)";

			PreparedStatement pst = connection.prepareStatement(query);
			int i = 1;
			pst.setInt(i++, Integer.parseInt(gameIDP2Field));
			pst.setInt(i++, numOfCopy);
			pst.setString(i++, "false");
			pst.execute();
			JOptionPane.showMessageDialog(null, "Data Saved");
			PanelService.updatePanel(2, table2);

			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pane2RemoveButton(String gamesStockID,String gameIDP2Field) {
		JTable table2 = ManagerMenu.table2;

		int action = JOptionPane.showConfirmDialog(null, "do you Really Want To Delete?", "Delete",JOptionPane.YES_NO_OPTION);
		if (action == 0) {
			try {
				String query = "DELETE from GamesStock where GamesStockID=?";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setInt(1, Integer.parseInt(gamesStockID));
				pst.execute();
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String query = "Update GamesStock set NumOfCopy=? where GameID=?";
				PreparedStatement pst = connection.prepareStatement(query);
				GamesCopyService GCS = new GamesCopyService();
				int numOfCopy = GCS.numcopycheck(Integer.parseInt(gameIDP2Field)) - 1;
				pst.setInt(1, numOfCopy);
				pst.setInt(2, Integer.parseInt(gameIDP2Field));
				pst.execute();
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			PanelService.updatePanel(2, table2);
		}
	}

	public void pane2RefreshButton() {
		JTable table2 = ManagerMenu.table2;
		try {
			PanelService.updatePanel(2, table2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void pane2_getGameIDbycombobox(String Search_comboBox2,String Search_comboBox2_1,JTextField GameIDP2Field) {
		try {
			String query = "Select * from Games where Name=? and Platform=? ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1,Search_comboBox2);
			pst.setString(2,Search_comboBox2_1);
			ResultSet rs = pst.executeQuery();
			GameIDP2Field.setText(rs.getString("GameID"));
			pst.execute();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pane3RefreshButton() {
		JTable table3 = ManagerMenu.table3;
		try {
			PanelService.updatePanel(3, table3);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
  	
}
