package com.company.games.simplegames.model;

public class GameResult {

	private String type;

	private long gameBetValue;

	private long gameWinValue;

	private boolean sideGame;

	public GameResult(String type, long gameBetValue, long gameWinValue, boolean sideGame) {
		super();
		this.type = type; 
		this.gameBetValue = gameBetValue;
		this.gameWinValue = gameWinValue;
		this.sideGame = sideGame;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getGameBetValue() {
		return gameBetValue;
	}

	public void setGameBetValue(long gameBetValue) {
		this.gameBetValue = gameBetValue;
	}

	public long getGameWinValue() {
		return gameWinValue;
	}

	public void setGameWinValue(long gameWinValue) {
		this.gameWinValue = gameWinValue;
	}

	public boolean hasSideGame() {
		return sideGame;
	}

	public void consumeSideGame() {
		sideGame = false;
	}

	public void setSideGame(boolean sideGame) {
		this.sideGame = sideGame;
	}
	
	@Override
	public String toString() {
		return "GameResult [type=" + type + ", gameBetValue=" + gameBetValue + ", gameWinValue=" + gameWinValue
				+ ", sideGame=" + sideGame + "]";
	}

}
