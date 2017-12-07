package com.company.games.simplegames.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.games.Game;
import com.company.games.simplegames.model.GameResult;

public class GameStatistics {

	private Map<Game, List<GameResult>> statistics;

	public GameStatistics() {
		statistics = new HashMap<>();
	}

	public void addStatistic(Game game, GameResult result) {
		statistics.computeIfAbsent(game, empList -> new ArrayList<>()).add(result);
	}

	public Map<Game, List<GameResult>> getStatistics() {
		return statistics;
	}

	public GameResult getLastStatistic(Game game) {
		if (statistics.containsKey(game)) {
			List<GameResult> games = statistics.get(game);
			return games.get(games.size() - 1);
		} else
			return null;
	}
}
