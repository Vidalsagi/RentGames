package controller;

import model.ManagerAddCustomerModel;

public class ManagerAddCustomerController{
	ManagerAddCustomerModel model;

	public ManagerAddCustomerController() {
		this.model = new ManagerAddCustomerModel();
	}

	public void AddCustomer(String username,String password, String city, String lastName, String age, String firstName, String address) throws Exception
	{
		model.AddCustomerFunc(username, password, city, lastName, age, firstName, address);
	}
}
