package com.company.games.simplegames.bonusgame;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.GameResult;
import com.company.games.simplegames.model.Player;
import com.company.games.simplegames.statistics.GameStatistics;

@RunWith(MockitoJUnitRunner.class)
public class BoxPickingGameTest {

	@Mock
	GameResult result;

	@Mock
	Player anyPlayer;

	@InjectMocks
	@Spy
	BoxPickingGame boxGame;

	@Before
	public void init() {
		when(anyPlayer.getLog()).thenReturn(new GameStatistics());
		when(boxGame.getBoxes()).thenReturn(Arrays.asList(5, 5, Integer.MIN_VALUE, 5, 5));
	}

	@Test
	public void testWinNormalBonusGame() throws NoCoinsException {
		assertEquals(10, boxGame.playGame(anyPlayer).getGameWinValue());
	}

	@Test
	public void testWinRoundBoxing() throws NoCoinsException {
		when(result.hasSideGame()).thenReturn(true);
		assertEquals("Box Picking Game", boxGame.playGame(anyPlayer).getType());
	}

	@Test
	public void noCoinsTest() throws NoCoinsException {
		when(anyPlayer.getCoins()).thenReturn(0l);
		assertEquals(0, boxGame.playGame(anyPlayer).getGameBetValue());
	}
}
