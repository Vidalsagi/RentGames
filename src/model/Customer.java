package model;

import java.util.List;

public class Customer extends DBConnector {
	public List<GamesCopy> rentedGames =null;
	public Person customer =null;

	
	public Customer(int custID)
	{
		rentedGames =  new GamesCopyService().getAllGamesCopysBypersonID(custID);
		customer = new PersonService().getPersonByID(custID); 
	}
}
