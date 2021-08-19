package model;

import java.sql.*;

public class DBConnector {

	//Create connection to DataBase
    public Connection Connect() {
        Connection connection = null;

        try {
        	Class.forName("org.sqlite.JDBC");
			String conString = "jdbc:sqlite:"+System.getProperty("user.dir")+"\\RentGame.sqlite";//Directory of the sqlite.db
			connection = DriverManager.getConnection(conString);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return connection;
    }

    //Disconnect from DataBase
    public void Disconnect(Connection connection, Statement statement, PreparedStatement preparedStatement, 
    		ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            System.out.println("Closed database successfully");
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
