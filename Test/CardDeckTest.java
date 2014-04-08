package Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import Blackjack.Card;
import Blackjack.CardDeck;

public class CardDeckTest {

	CardDeck deck = null;
	
	@Before
	public void setUp() throws Exception {
		deck = CardDeck.getInstance();
	}

	@Test
	public void drawCardTest() {
		/**
		 * Test deck creation
		 */
		assertNotNull("Card deck creation failed.", deck);
		/**
		 * Test uniqueness of cards in deck
		 */
		Map<Card, Boolean> cardDict = new HashMap<Card, Boolean>();
		
		for(int i = 0; i < 52; i++) {
			Card card = deck.drawCard();
			assertFalse("Cards in deck are NOT unique.", cardDict.containsKey(card));
			
			cardDict.put(card, true);
		}
		/**
		 * Test non emptiness of deck after drawing 52 cards  
		 */
		Card card = deck.drawCard();
		assertNotNull("Deck cannot be empty.", card);
	}
}