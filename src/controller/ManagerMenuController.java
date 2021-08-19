package controller;

import java.sql.SQLException;
import javax.swing.JTextField;
import model.ManagerMenuModel;

public class ManagerMenuController{
	ManagerMenuModel model;

	public ManagerMenuController() {
		this.model = new ManagerMenuModel();
	}
	public void savePanel1(String nameField,String categoryField,String releaseYearField,String platformField,
			String companyField,String minAgeReqField,String costField) throws SQLException {
		model.savePanel1(nameField, categoryField, releaseYearField, platformField, companyField, minAgeReqField, costField);
	}
	
	public void deletePanel1(String gameIDField) throws SQLException {
		model.deletePanel1(gameIDField);
	}
	
	public void mouseClicked1(JTextField gameIDField, JTextField nameField,JTextField categoryField,JTextField releaseYearField,
			JTextField platformField,JTextField companyField,JTextField minAgeReqField,JTextField costField) {
		model.mouseklicked_pane1(gameIDField, nameField, categoryField, releaseYearField, platformField, companyField, minAgeReqField, costField);
	}
	
	public String mouseClicked2(JTextField gameIDField2) {
		return model.mouseklickedpane2(gameIDField2);
	}
	
	public void addButtonP2(String gameIDP2Field) {
		model.pane2_addbutton(gameIDP2Field);
	}
	
	public void getGameIdByomboboxpane2(String searchComboBox2,String searchComboBox21,JTextField gameIDP2Field) {
		model.pane2_getGameIDbycombobox(searchComboBox2, searchComboBox21, gameIDP2Field);
	}
	
	public void updateDate(JTextField gameIDField, JTextField nameField,JTextField categoryField,JTextField releaseYearField,
			JTextField platformField,JTextField companyField,JTextField minAgeReqField,JTextField costField) throws SQLException {
		model.updatePanel1(gameIDField, nameField, categoryField, releaseYearField, platformField, companyField, minAgeReqField, costField);
	}
	
	public void searchP1(String txtSearch,String selectedComboBox) {
		model.searchP1(txtSearch, selectedComboBox);
	}
	
	public void removeP2(String gamesStockID,String gameIDP2Field) {
		model.pane2RemoveButton(gamesStockID, gameIDP2Field);
	}
	
	public void refreshP2() {
		model.pane2RefreshButton();
	}
	
	public void refreshP3() {
		model.pane3RefreshButton();
	}
}
