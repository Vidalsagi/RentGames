package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GamesCopyService extends GamesCopy{

    private GamesCopy gamesCopyMaker (ResultSet resultSet){
    	GamesCopy gamesCopy = new GamesCopy();
        try {
        	gamesCopy.setGamesStockID(resultSet.getInt("GamesStockID"));
        	gamesCopy.setGameID(resultSet.getInt("GameID"));
        	gamesCopy.setNumOfCopy(resultSet.getInt("NumOfCopy"));
        	gamesCopy.setIsRented(resultSet.getBoolean("IsRented"));
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return gamesCopy;
    }

    //Returns the list of all game copies in stock
    public	 List<GamesCopy> getAllGamesCopy(){
        List<GamesCopy> gamesCopyList = new ArrayList<>();
        Connection connection = Connect();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM GamesStock");

            while ( resultSet.next() ) {
            	GamesCopy gamesCopy = gamesCopyMaker(resultSet);
            	gamesCopyList.add(gamesCopy);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            Disconnect(connection, statement, null, resultSet);
        }
        return gamesCopyList;
    }
    
    //Return a list of games for a specific customer
    public	 List<GamesCopy> getAllGamesCopysBypersonID(int ID){
        List<GamesCopy> GamesCopyList = new ArrayList<>();
        Connection connection = Connect();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM GamesStock join Rented using(GamesStockID) where RentedBy = " + ID);
            while ( resultSet.next() ) {
            	GamesCopy gamesCopy = gamesCopyMaker(resultSet);
            	GamesCopyList.add(gamesCopy);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            Disconnect(connection, statement, null, resultSet);
        }
        return GamesCopyList;
    }
    
    
    //Return how many copies of a specific game in stock
    public int numcopycheck(int gameID)
    {
    	System.out.println(gameID);
    	List<GamesCopy> GamesCopyList = new ArrayList<>();
    	GamesCopyList=getAllGamesCopy();
    	 int count=1;
    	 for (GamesCopy game : GamesCopyList){
             if(game.getGameID()==gameID)
             {
            	 count++;
             }
         }
    	 update_numcop(gameID, count);
    	 return count;
    	
    }
    
    //Update the number of copies
    public void update_numcop(int gameID,int count)
    {
    	SqliteConnection singelton= SqliteConnection.getInstance();
    	Connection connection = singelton.dbConnector();
    	Statement statement = null;
        ResultSet resultSet = null;
    	try {
             statement = connection.createStatement();
             statement.executeUpdate("Update GamesStock set NumOfCopy="+count+" where GameID="+gameID);
            
    	 }
    	 catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
         }
    	 finally 
    	 {
             Disconnect(connection, statement, null, resultSet);
         }
    }
    
}
