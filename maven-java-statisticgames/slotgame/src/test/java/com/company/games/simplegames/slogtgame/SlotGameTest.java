package com.company.games.simplegames.slogtgame;

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
import com.company.games.simplegames.model.Player;
import com.company.games.simplegames.slotgame.FreeSlotGame;
import com.company.games.simplegames.slotgame.SlotGame;
import com.company.games.simplegames.statistics.GameStatistics;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SlotGame.class, SecureRandom.class })
public class SlotGameTest {

	@Mock
	SecureRandom random;

	@Mock
	Player anyPlayer;

	@Mock
	FreeSlotGame freeGame;

	SlotGame slotGame = new SlotGame();

	@Before
	public void init() throws Exception {
		PowerMockito.whenNew(SecureRandom.class).withNoArguments().thenReturn(random);
		when(anyPlayer.getCoins()).thenReturn(50l);
		when(anyPlayer.getLog()).thenReturn(new GameStatistics());
	}

	@Test
	public void testWinNormalSlotGame() throws NoCoinsException {
		when(random.nextFloat()).thenReturn(0.1f, 0.75f);
		assertEquals(20, slotGame.playGame(anyPlayer).getGameWinValue());
	}

	@Test
	public void testSecondGameFreeSlotGame() throws NoCoinsException {
		when(random.nextFloat()).thenReturn(0.1f, 0.1f);
		assertEquals("Slot Game", slotGame.playGame(anyPlayer).getType());
		assertEquals("Free Slot Game", slotGame.playGame(anyPlayer).getType());
	}

	@Test
	public void testSecondGameNormalGame() throws NoCoinsException {
		when(random.nextFloat()).thenReturn(0.1f, 0.6f);
		assertEquals("Slot Game", slotGame.playGame(anyPlayer).getType());
		assertEquals("Slot Game", slotGame.playGame(anyPlayer).getType());
	}

	@Test
	public void testLostNormalNoFree() throws NoCoinsException {
		when(random.nextFloat()).thenReturn(0.8f, 0.6f);
		assertEquals(0, slotGame.playGame(anyPlayer).getGameWinValue());
	}

	@Test
	public void testLostNormalWinFree() throws NoCoinsException {
		when(random.nextFloat()).thenReturn(0.8f, 0.1f);
		assertEquals(0, slotGame.playGame(anyPlayer).getGameWinValue());
		when(random.nextFloat()).thenReturn(0.1f, 0.1f);
		assertEquals(20, slotGame.playGame(anyPlayer).getGameWinValue());
	}

}
