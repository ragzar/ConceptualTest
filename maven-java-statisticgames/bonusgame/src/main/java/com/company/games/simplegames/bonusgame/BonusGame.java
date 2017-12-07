package com.company.games.simplegames.bonusgame;

import com.company.games.simplegames.AbstractSimpleGame;
import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.GameResult;
import com.company.games.simplegames.model.Player;

public class BonusGame extends AbstractSimpleGame {

	public static final String GAME_NAME = "Bonus Game";
	public static final int GAME_COST = 10;
	public static final int GAME_REWARD = 0;
	public static final float GAME_WIN_PERCENTAGE = 0;
	public static final float PLAY_SIDE_GAME_PERCENTAGE = 10;

	public BonusGame() {
		super(GAME_NAME, GAME_COST, GAME_REWARD, GAME_WIN_PERCENTAGE, PLAY_SIDE_GAME_PERCENTAGE);

	}

	@Override
	public GameResult playGame(Player player) throws NoCoinsException {
		GameResult result = super.playGame(player);
		if (result.hasSideGame()) {
			result = new BoxPickingGame().playGame(player);
		}
		return result;
	}

}
