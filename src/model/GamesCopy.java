package model;


public class GamesCopy extends DBConnector{
	  int gamesStockID;
	  int gameID;
	  int numOfCopy;
	  
	  public GamesCopy() {
			super();
			gamesStockID = 0;
			gameID = 0;
			numOfCopy = 0;
			IsRented = false;
		}
	  
	 
	
	public GamesCopy(int GamesStockID, int GameID, int numOfCopy, Boolean isRented) {
		super();
		this.gamesStockID = GamesStockID;
		this.gameID = GameID;
		numOfCopy = numOfCopy;
		IsRented = isRented;
	}
	@Override
	public String toString() {
		return "GamesCopy [GamesStockID=" + gamesStockID + ", GameID=" + gameID + ", NumOfCopy=" + numOfCopy + ", IsRented=" + IsRented + "]";
	}
	Boolean IsRented;
	public int getGamesStockID() {
		return gamesStockID;
	}
	public void setGamesStockID(int GamesStockID) {
		this.gamesStockID = GamesStockID;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int GameID) {
		this.gameID = GameID;
	}
	public int NumOfCopy() {
		return numOfCopy;
	}
	public void setNumOfCopy(int numOfCopy) {
		numOfCopy = numOfCopy;
	}
	public Boolean getIsRented() {
		return IsRented;
	}
	public void setIsRented(Boolean isRented) {
		IsRented = isRented;
	}
}
