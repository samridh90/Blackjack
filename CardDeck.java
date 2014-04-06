/**
 * A simple text based BlackJack.
 */

import java.util.*;

/**
 * @author samridh
 */
public class CardDeck {
	
	/**
	 * Number of decks; 1 by default
	 */
	private static final int NUM_DECKS = 1; 
	/**
	 * Singleton instance of the CardDeck
	 */
	private static CardDeck instance = null;
	/**
	 * Collection of cards representing a 52 * NUM_DECKS card deck
	 */
	private ArrayList<Card> deck = new ArrayList<Card>();

	/**
	 * Private constructor that initializes deck with 52 * NUM_DECKS new cards
	 */
	private CardDeck() {
		this.initializeDeck();
	}

	/**
	 * Returns an instance of the CardDeck class if it exists,
	 * Creates one and returns it otherwise.
	 * <p>
	 * @return instance of CardDeck class
	 */
	public static CardDeck getInstance() {
		if(instance == null) {
			instance = new CardDeck();
		}
		return instance;
	}

	/**
	 * Clear deck and initialize it with 52 * NUM_DECKS new cards. 
	 */
	private void initializeDeck() {
		deck.clear();
		
		for(int i = 0; i < NUM_DECKS; i++)
		{
			for(Card.Rank rank: Card.Rank.values()) {
				for(Card.Suit suit: Card.Suit.values()) {
					deck.add(new Card(rank, suit));
				}
			}
		}

		Collections.shuffle(deck, new Random());		
	}

	/**
	 * Returns a random card drawn from the deck, removes the card from the deck 
	 * <p>
	 * @return random card drawn from deck
	 */
	public Card drawCard() {
		if(deck.size() == 0)
		{
			this.initializeDeck();
		}
		Random cardGenerator = new Random();
		int randomIndex = cardGenerator.nextInt(this.deck.size());
		
		Card card = this.deck.get(randomIndex);
		deck.remove(randomIndex);
		
		return card;		
	}	
}
 