package com.company.games.simplegames.slotgame;

import com.company.games.simplegames.AbstractSimpleGame;
import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.GameResult;
import com.company.games.simplegames.model.Player;

public class SlotGame extends AbstractSimpleGame {

	public static final String GAME_NAME = "Slot Game";
	public static final int GAME_COST = 10;
	public static final int GAME_REWARD = 20;
	public static final float GAME_WIN_PERCENTAGE = 30;
	public static final float PLAY_SIDE_GAME_PERCENTAGE = 10;

	public SlotGame() {
		super(GAME_NAME, GAME_COST, GAME_REWARD, GAME_WIN_PERCENTAGE, PLAY_SIDE_GAME_PERCENTAGE);
	}

	@Override
	public GameResult playGame(Player player) throws NoCoinsException {
		GameResult lastResult = player.getLog().getLastStatistic(this);
		if (lastResult != null && lastResult.hasSideGame()) {
			lastResult.consumeSideGame();
			return new FreeSlotGame().playGame(player);
		}
		return super.playGame(player);
	}

}
