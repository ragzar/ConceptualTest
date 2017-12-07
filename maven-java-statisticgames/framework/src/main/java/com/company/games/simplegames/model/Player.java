package com.company.games.simplegames.model;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.company.games.simplegames.statistics.GameStatistics;

public class Player {

	private String name;

	private long coins;

	private boolean ilimitedCoins;

	private GameStatistics log;

	public Player(String name, long coins) {
		super();
		this.name = name;
		this.coins = coins;
		log = new GameStatistics();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCoins() {
		return coins;
	}

	public void setCoins(long coins) {
		this.coins = coins;
	}

	public boolean isIlimitedCoins() {
		return ilimitedCoins;
	}

	public void setIlimitedCoins(boolean ilimitedCoins) {
		this.ilimitedCoins = ilimitedCoins;
	}

	public void charge(int coins) {
		this.coins -= coins;
	}

	public void reward(int coins) {
		this.coins += coins;
	}

	public GameStatistics getLog() {
		return log;
	}

	public void printStatistics() {
		System.out.println("\n******** STATISTICS *********\n");
		NumberFormat nf = NumberFormat.getInstance();

		System.out.println("Staticstics for player: " + getName());
		List<GameResult> results = getLog().getStatistics().values().stream().flatMap(List::stream)
				.collect(Collectors.toList());

		long gameWins = results.parallelStream().mapToLong(w -> w.getGameWinValue()).sum();
		long gameBets = results.parallelStream().mapToLong(w -> w.getGameBetValue()).sum();

		System.out.println("\nGeneral Totals  ");
		System.out.println("\t[Total Games: " + nf.format(results.size()) + " | Win coins: " + nf.format(gameWins)
				+ " | Bet coins: " + nf.format(gameBets) + " | RTP: " + nf.format((double) gameWins / gameBets) + "]");

		getLog().getStatistics().forEach((game, games) -> {
			long wins = games.parallelStream().mapToLong(w -> w.getGameWinValue()).sum();
			long bets = games.parallelStream().mapToLong(b -> b.getGameBetValue()).sum();

			System.out.println(game.getGameName());
			System.out.println("\t[Total Games: " + nf.format(games.size()) + " | Win coins: " + nf.format(wins)
					+ " | Bet coins: " + nf.format(bets) + "]");
		});
		System.out.println("\n******************************\n\n");
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", coins=" + coins + ", ilimitedCoins=" + ilimitedCoins + "]";
	}

}
