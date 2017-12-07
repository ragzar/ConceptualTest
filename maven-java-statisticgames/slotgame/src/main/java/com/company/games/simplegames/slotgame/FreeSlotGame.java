package com.company.games.simplegames.slotgame;

import com.company.games.simplegames.AbstractSimpleGame;

public class FreeSlotGame extends AbstractSimpleGame {

	public static final String GAME_NAME = "Free Slot Game";
	public static final int GAME_COST = 0;
	public static final int GAME_REWARD = 20;
	public static final float GAME_WIN_PERCENTAGE = 30;
	public static final float PLAY_SIDE_GAME_PERCENTAGE = 0;

	public FreeSlotGame() {
		super(GAME_NAME, GAME_COST, GAME_REWARD, GAME_WIN_PERCENTAGE, PLAY_SIDE_GAME_PERCENTAGE);
	}

}
