package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import model.Customer;
import model.DBConnector;

public class CustomerTest  extends DBConnector{
	int count=0;

	@After
	public void afterEachTest() {
		System.out.println("");
		System.out.println("Test Finished");
	}
	@Test
	public void constractortest() { //Check if Shay is saved in DB
		Customer S = new Customer(2);
    	assertEquals("shay",S.customer.FirstName);
	}
}
