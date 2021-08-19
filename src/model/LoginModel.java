package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel extends DBConnector{

	SqliteConnection singelton= SqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();

	//User login
	public int LoginFunc(String username,String password,boolean selected) throws Exception
	{			
			String query = "SELECT * FROM Person WHERE username=? and password=? and isManager=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1,username);//username
			pst.setString(2,password);//password
			if(selected)
			{
				pst.setString(3,"True");
			}
			else {pst.setString(3,"False");}
			ResultSet rs = pst.executeQuery();
			int count  = 0;
			int ID=0;
			while(rs.next())
			{
				count++;//counting matches
				ID = rs.getInt("ID");
			}
			if(count ==1)
			{
				if(selected)
				{
				return 1;
				}
				else {
				return ID;
				}
			}
			else if(count>1)
			{
				throw new Exception("Duplicate UserName & Password");
			}
			else 
			{
				throw new Exception("Incorrect UserName or Password");
			}
	}
}
