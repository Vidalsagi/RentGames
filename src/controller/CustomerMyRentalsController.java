package controller;


import java.awt.event.MouseAdapter;
import model.Customer;
import model.CustomerMyRentalsModel;

public class CustomerMyRentalsController extends MouseAdapter  {
	CustomerMyRentalsModel model;

	public CustomerMyRentalsController(Customer cust) {
		this.model = new CustomerMyRentalsModel(cust);
	}
	
	public void selectedOnTableC() {
		model.selectedOnTable();
	}
	
	public void returnFuncC() throws Exception {
		model.returnFunc();
	}
}