package com.company.games.simplegames;

import java.security.SecureRandom;
import java.util.Random;

import com.company.games.Game;
import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.GameResult;
import com.company.games.simplegames.model.Player;

public abstract class AbstractSimpleGame implements Game {

	private final String gameName;

	private final int gameBetValue;

	private int gameWinValue;

	private final float winPercentageChance;

	private final float sideGameChance;

	public AbstractSimpleGame(String gameName, int gameBetValue, int gameWinValue, float winPercentageChance,
			float sideGameChance) {
		super();
		this.gameName = gameName;
		this.gameBetValue = gameBetValue;
		this.gameWinValue = gameWinValue;
		this.winPercentageChance = winPercentageChance;
		this.sideGameChance = sideGameChance;
	}

	public void setGameWinValue(int gameWinValue) {
		this.gameWinValue = gameWinValue;
	}

	public String getGameName() {
		return gameName;
	}

	public int getGameBetValue() {
		return gameBetValue;
	}

	public int getGameWinValue() {
		return gameWinValue;
	}

	public float getWinPercentageChance() {
		return winPercentageChance;
	}

	public float getSideGameChance() {
		return sideGameChance;
	}

	public boolean hasWon() {
		Random r = new SecureRandom();
		float chance = r.nextFloat();
		return chance <= winPercentageChance / 100;
	}

	public boolean hasSideGame() {
		Random r = new SecureRandom();
		float chance = r.nextFloat();
		return chance <= sideGameChance / 100;
	}

	@Override
	public GameResult playGame(Player player) throws NoCoinsException {

		chargePlayer(player);
		GameResult result;
		if (hasWon()) {
			rewardPlayer(player);
			result = new GameResult(getGameName(), getGameBetValue(), getGameWinValue(), hasSideGame());
		} else {
			result = new GameResult(getGameName(), getGameBetValue(), 0, hasSideGame());
		}

		player.getLog().addStatistic(this, result);
		return result;
	}

	@Override
	public void chargePlayer(Player player) throws NoCoinsException {
		if (player.getCoins() >= getGameBetValue() && !player.isIlimitedCoins())
			player.charge(gameBetValue);
		else if (player.getCoins() <= getGameBetValue() && !player.isIlimitedCoins())
			throw new NoCoinsException("No enough coins to play this game");

	}

	@Override
	public void rewardPlayer(Player player) {
		if (!player.isIlimitedCoins())
			player.reward(gameWinValue);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gameBetValue;
		result = prime * result + ((gameName == null) ? 0 : gameName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractSimpleGame))
			return false;
		AbstractSimpleGame other = (AbstractSimpleGame) obj;
		if (gameBetValue != other.gameBetValue)
			return false;
		if (gameName == null) {
			if (other.gameName != null)
				return false;
		} else if (!gameName.equals(other.gameName))
			return false;
		return true;
	}

}
