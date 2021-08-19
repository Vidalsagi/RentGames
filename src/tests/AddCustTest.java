package tests;

import org.junit.After;
import org.junit.Test;
import view.ManagerAddCustomer;

public class AddCustTest {

	@After
	public void afterEachTest() {
		System.out.println("");
		System.out.println("Test Finished");
	}
	
	@Test
	public void testLoginFuncAddCustomer() { //Manager add customer successfully
		//try {
		ManagerAddCustomer view = new ManagerAddCustomer();
	    view.UserNameField.setText("sa");
		view.PasswordField.setText("123456");
		view.CityField.setText("Rishon LeZion");
		view.LastNameField.setText("vidal");
		view.AgeField.setText("15");
		view.FirstNameField.setText("Or");
		view.AddressField.setText("Karmel");
		view.btnAdd.doClick();
	}
	
	@Test
	public void testLoginFuncCustomerFail() { //Fail, Manager try to add customer without Username 
		ManagerAddCustomer view = new ManagerAddCustomer();
	    view.UserNameField.setText("");
		view.PasswordField.setText("123456");
		view.CityField.setText("Rishon LeZion");
		view.LastNameField.setText("");
		view.AgeField.setText("15");
		view.FirstNameField.setText("Or");
		view.AddressField.setText("Karmel");
		view.btnAdd.doClick();
	}
}
