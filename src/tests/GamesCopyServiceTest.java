package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Test;

import model.DBConnector;
import model.GamesCopyService;

public class GamesCopyServiceTest extends DBConnector{
int count =0;


	@After
	public void afterEachTest() {
		System.out.println("");
		System.out.println("Test Finished");
	}

	@Test
	public void getAllGamesCopy() { //Get all the Games copies in DB
		Connection connection = Connect();
        Statement statement = null;
        ResultSet resultSet = null;
    	try {
             statement = connection.createStatement();
             resultSet= statement.executeQuery("SELECT count(*) from GamesStock");
             count = resultSet.getInt(1);
    	 }
    	 catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
         }
    	 finally 
    	 {
             Disconnect(connection, statement, null, resultSet);
         }
    	assertEquals(count,new GamesCopyService().getAllGamesCopy().size() );
	}
}
