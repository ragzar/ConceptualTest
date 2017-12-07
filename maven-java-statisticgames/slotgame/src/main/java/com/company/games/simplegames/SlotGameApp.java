package com.company.games.simplegames;

import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.Player;
import com.company.games.simplegames.slotgame.SlotGame;

public class SlotGameApp {

	public static void main(String[] args) {
		Player mockPlayer = new Player("Mock Player", Integer.MAX_VALUE);
		mockPlayer.setIlimitedCoins(true);

		int simulations = 1000000;

		SlotGame slotGame = new SlotGame();

		for (int i = 0; i < simulations; i++) {
			try {
				slotGame.playGame(mockPlayer);
			} catch (NoCoinsException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		mockPlayer.printStatistics();
	}
}
