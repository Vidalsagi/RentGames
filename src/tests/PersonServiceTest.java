package tests;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import model.DBConnector;
import model.Person;
import model.PersonService;

public class PersonServiceTest extends DBConnector {
	
	@After
	public void afterEachTest() {
		System.out.println("");
		System.out.println("Test Finished");
	}
	
	@Test
	public void testGetAllPersons() { //Check if there are 5 users in the list of persons
		List<Person> Cust_list = new PersonService().getAllPersons();
		assertEquals(5, Cust_list.size());
	}

	@Test
	public void testGetPersonByID() { //Get the person by FirstName
		Person P = new PersonService().getPersonByID(4);
		assertEquals("maxim", P.getFirstName());
	}
}
