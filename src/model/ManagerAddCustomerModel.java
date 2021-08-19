package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ManagerAddCustomerModel {

	SqliteConnection singelton = SqliteConnection.getInstance();
	Connection connection = singelton.dbConnector();
     //Manager add Customer
    public void AddCustomerFunc(String userName,String password, String city, String lastName,
    		String age, String firstName, String address) throws Exception
    {
			String query = "insert into Person (UserName,Password,City,Address,FirstName,LastName,Age,isManager) values (?,?,?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(query);
			int i = 1;
			pst.setString(1,userName); //username
			pst.setString(2,password); //password
			pst.setString(3, city); //city
			pst.setString(4, lastName); //lastname
			pst.setString(5, age); //age
			pst.setString(6, firstName); //firstname
			pst.setString(7, address); //address
			
			if(userName.isEmpty() || password.isEmpty() || city.isEmpty() || age.isEmpty() || firstName.isEmpty()
				|| lastName.isEmpty() || address.isEmpty()){
				System.out.println("Please fill all the information");
				throw new Exception("Please fill all the information");
			}
			else {
				pst.setString(i++, userName);
				pst.setString(i++, password);
				pst.setString(i++, city);
				pst.setString(i++, address);
				pst.setString(i++, firstName);
				pst.setString(i++, lastName);
				pst.setString(i++, age);
				pst.setString(i++, "False");
				pst.execute();
				pst.close();
			}

	}
}
