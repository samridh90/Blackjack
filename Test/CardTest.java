package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Blackjack.Card;


public class CardTest {

	Card card = null;
	
	@Before
	public void setUp() throws Exception {
		card = new Card(Card.Rank.ACE, Card.Suit.SPADES);
	}
	
	@Test
	public void testCard() {
		assertNotNull("Card creation failed.", card);
	}

	@Test
	public void testRank() {
		assertEquals("Rank initialization failed.", card.rank(), Card.Rank.ACE);
	}

	@Test
	public void testSuit() {
		assertEquals("Suit initialization failed.", card.suit(), Card.Suit.SPADES);
	}

	@Test
	public void testToString() {
		assertEquals("toString() failed.", card.toString(), "ACE of SPADES (\u2660)");
	}

}
