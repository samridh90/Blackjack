package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Blackjack.Card;
import Blackjack.Hand;

public class HandTest {

	Hand hand = null;
	
	@Before
	public void setUp() throws Exception {
		hand = new Hand();
	}

	@Test
	public void totalValuetest() {
		/**
		 * Test creation
		 */
		assertNotNull("Hand creation failed.", hand);
		
		Card card1 = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS);
		Card card2 = new Card(Card.Rank.ACE, Card.Suit.SPADES);
		Card card3 = new Card(Card.Rank.KING, Card.Suit.HEARTS);		
		/**
		 * Test add card and total value
		 */
		hand.add(card1);
		hand.add(card2);
		assertTrue("Add card failed.", hand.size() == 2);
		assertTrue("Total value output is incorrect.", hand.totalValue() == 16);		
		/**
		 * Test change to soft hand on total > 21
		 */
		hand.add(card3);
		assertTrue("Change to soft hand failed.", hand.totalValue() == 16);
	}

}
