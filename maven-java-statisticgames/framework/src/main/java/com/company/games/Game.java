package com.company.games;

import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.GameResult;
import com.company.games.simplegames.model.Player;

public interface Game {

	String getGameName();

	GameResult playGame(Player player) throws NoCoinsException;

	void chargePlayer(Player player) throws NoCoinsException;

	void rewardPlayer(Player player);

}
