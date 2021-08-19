package controller;

import java.awt.event.KeyAdapter;

import model.ManagerRentModel;

public class ManagerRentController extends KeyAdapter{
	ManagerRentModel model;

	public ManagerRentController() {
		this.model = new ManagerRentModel();
	}
	public void returnF(int gameCopytable3) {
					System.out.println("controller acceptted");
					model.returnFunc(gameCopytable3);
	}
	public void search(String txtSearchBox)
	{	
		model.search(txtSearchBox);	
 	}
}
