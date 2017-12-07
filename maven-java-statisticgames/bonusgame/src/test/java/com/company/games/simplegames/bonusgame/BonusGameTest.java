package com.company.games.simplegames.bonusgame;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.security.SecureRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.company.games.simplegames.exceptions.NoCoinsException;
import com.company.games.simplegames.model.GameResult;
import com.company.games.simplegames.model.Player;
import com.company.games.simplegames.statistics.GameStatistics;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ BonusGame.class, SecureRandom.class })
public class BonusGameTest {

	@Mock
	SecureRandom random;

	@Mock
	Player anyPlayer;

	@Mock
	BoxPickingGame boxGame;

	@Mock
	GameResult result;

	BonusGame bonusGame = new BonusGame();

	@Before
	public void init() throws Exception {
		PowerMockito.whenNew(SecureRandom.class).withNoArguments().thenReturn(random);
		when(anyPlayer.getCoins()).thenReturn(50l);
		when(anyPlayer.getLog()).thenReturn(new GameStatistics());
	}

	@Test
	public void testNormalBonusGameNoBoxing() throws NoCoinsException {
		when(random.nextFloat()).thenReturn(0.0f, 0.75f);
		assertEquals("Bonus Game", bonusGame.playGame(anyPlayer).getType());
	}

	@Test
	public void testWinRoundBoxing() throws NoCoinsException {
		when(random.nextFloat()).thenReturn(0.3f, 0.1f);
		assertEquals("Box Picking Game", bonusGame.playGame(anyPlayer).getType());
	}

	@Test
	public void testNoBoxingRound() throws NoCoinsException {
		when(random.nextFloat()).thenReturn(0.81f);
		assertEquals(false, bonusGame.playGame(anyPlayer).hasSideGame());
	}

	@Test(expected = NoCoinsException.class)
	public void noCoinsTest() throws NoCoinsException {

		when(anyPlayer.getCoins()).thenReturn(5l);
		bonusGame.playGame(anyPlayer);

	}

}
