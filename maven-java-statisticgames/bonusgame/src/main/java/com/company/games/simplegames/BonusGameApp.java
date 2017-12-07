package com.company.games.simplegames;

import com.company.games.simplegames.bonusgame.BonusGame;
import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.Player;

public class BonusGameApp {

	
	
	public static void main(String[] args) {
		Player mockPlayer = new Player("Mock Player", Integer.MAX_VALUE);
		mockPlayer.setIlimitedCoins(true);
		int simulations = 1000000;
		BonusGame bonusGame = new BonusGame();

		for (int i = 0; i < simulations; i++) {
			try {
				bonusGame.playGame(mockPlayer);
			} catch (NoCoinsException e) {
				System.out.println(e.getMessage());
				break;
			}
		}

		mockPlayer.printStatistics();

	}
}
