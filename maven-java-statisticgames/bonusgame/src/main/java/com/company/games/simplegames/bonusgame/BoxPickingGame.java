package com.company.games.simplegames.bonusgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.company.games.simplegames.AbstractSimpleGame;
import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.GameResult;
import com.company.games.simplegames.model.Player;

public class BoxPickingGame extends AbstractSimpleGame {

	private static final String GAME_NAME = "Box Picking Game";
	private static final int BOXES_NUMBER = 5;
	private static final int BOXES_VALUE = 5;

	public List<Integer> boxes;

	public BoxPickingGame() {
		super(GAME_NAME, 0, 0, 0, 0);
		boxes = new ArrayList<>();
		boxes.add(Integer.MIN_VALUE);
		for (int i = 1; i < BOXES_NUMBER; i++) {
			boxes.add(BOXES_VALUE);
		}
		Collections.shuffle(boxes);
	}

	@Override
	public GameResult playGame(Player player) throws NoCoinsException {

		chargePlayer(player);

		int winValue = 0;
		for (Integer box : getBoxes()) {
			if (box == Integer.MIN_VALUE)
				break;
			else {
				winValue += box;
			}
		}
		setGameWinValue(winValue);

		GameResult result = new GameResult(getGameName(), getGameBetValue(), getGameWinValue(), hasSideGame());
		player.getLog().addStatistic(this, result);
		rewardPlayer(player);
		return result;
	}

	public List<Integer> getBoxes() {
		return boxes;
	}

}
