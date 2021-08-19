package tests;

import org.junit.After;
import org.junit.Test;

import view.Login;

public class LoginTest {
	@After
	public void afterEachTest() {
		System.out.println("");
		System.out.println("Test Finished");
	}
	@Test
	public void testLoginFuncasManager(){ //Manager login 
		Login view = new Login();
	    view.rdbtnManager.setSelected(true);
		view.userField.setText("or");
		view.passField.setText("123456");
		view.loginBottom.doClick();
	}

	@Test
	public void testLoginFuncasCustomer(){ //Customer login 
		Login view = new Login();
	    view.rdbtnManager.setSelected(false);
		view.userField.setText("sagi");
		view.passField.setText("123456");
		view.loginBottom.doClick();
	}
	
	@Test
	public void testfailLoginFunc(){ //wrong username or passowrd 
		Login view = new Login();
	    view.rdbtnManager.setSelected(false);
		view.userField.setText("absd");
		view.passField.setText("1232456");
		view.loginBottom.doClick();
	}
	

	@Test
	public void testLoginWrongAuthorities() { //Scene where Customer want to connect as Manager
		Login view = new Login();
	    view.rdbtnManager.setSelected(true);
		view.userField.setText("sagi");
		view.passField.setText("123456");
		view.loginBottom.doClick();
	}
}
