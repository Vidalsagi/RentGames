package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import com.toedter.calendar.JDateChooser;

public class CustomerRentModel extends DBConnector{

	SqliteConnection singelton= SqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();
		
	//Move a game to the rent table
	public void moveRight(DefaultListModel<String> model1,DefaultListModel<String> model2,JList<String> inStore,JList<String> toRent)
	{
		if (inStore.getSelectedIndices().length > 0) {
            int[] selectedIndices = inStore.getSelectedIndices();
            //Append selected to rent list
            for (int i = 0; i < selectedIndices.length; i++) {
                model2.addElement(inStore.getModel().getElementAt(selectedIndices[i]));
            }
            //Remove selected from Store list
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                model1.removeElementAt(selectedIndices[i]);
            }
            toRent.setModel(model2);
        }
	}
	
	//Move a game to the In store table
	public void moveLeft(DefaultListModel<String> model1,DefaultListModel<String> model2,JList<String> inStore,JList<String> toRent)
	{
		if (toRent.getSelectedIndices().length > 0) {
            int[] selectedIndices = toRent.getSelectedIndices();
            //Append selected to Store list
            for (int i = 0; i < selectedIndices.length; i++) {
                model1.addElement(toRent.getModel().getElementAt(selectedIndices[i]));
            }
            //Remove selected from rent list
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                model2.removeElementAt(selectedIndices[i]);
            }
            inStore.setModel(model1);
        }
	}

	//Rent a game
	public void rentFunc(JList<String> inStore,JList<String> toRent,JPanel contentPane,
			Customer cust,JDateChooser dateChooserFrom,JDateChooser dateChooserUntill) throws Exception {
		
		ListModel<String> LM=toRent.getModel();
		if(LM.getSize()!=0 && dateChooserFrom.getDate()!=null && dateChooserUntill.getDate()!=null)
		{
				
			for(int i=0;i<LM.getSize();i++)
			{
				int gamesStockID=0;
				//Finding the first game that matches the criteria
				PreparedStatement pst = connection.prepareStatement("select GamesStockID from GamesStock join Games using(GameID)where isRented = 'false' and Name = ?");
				pst.setString(1,LM.getElementAt(i) );
				ResultSet rs = pst.executeQuery();
				gamesStockID = rs.getInt(1);
				pst.close();
				rs.close();
				//Updating the value of IsRented to true for the rented game
				String query ="Update GamesStock set IsRented='true' where GamesStockID=?";
				PreparedStatement pst1 = connection.prepareStatement(query);
		    	pst1.setInt(1, gamesStockID);
		    	pst1.execute();
		    	pst1.close();
		    	updateRentedSql(gamesStockID,cust.customer.getID(),dateChooserFrom,dateChooserUntill);
			}
		}
		else
		{
			throw new Exception("Select game and fill all the fields");
		}
		throw new Exception("Congrats! Renting was successful!");
	}
		
	// Update the Rent table	
	private void updateRentedSql(int gamesStockID, int custID,JDateChooser dateChooserFrom,
			JDateChooser dateChooserUntill) throws SQLException 
	{
		System.out.println(gamesStockID);
		System.out.println(custID);
		String query = "insert into Rented (RentedBy,RentedStart,RentedEnd,GamesStockID) values (?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);
		int i=1;
		pst.setInt(i++, custID);
		pst.setString(i++,dateChooserFrom.getDate().toString() );
		pst.setString(i++,dateChooserUntill.getDate().toString());
		pst.setInt(i++, gamesStockID);
		pst.execute();
		System.out.println("rent sql updated , GamesStockID="+gamesStockID);
		pst.close();
	}
}
