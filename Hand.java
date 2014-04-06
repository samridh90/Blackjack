/**
 * A simple text based Blackjack 
 */

import java.util.*;

/**
 * @author samridh
 */
public class Hand {
	/**
	 * Represents the cards in the participant's hand
	 */
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	/**
	 * Constructs a Hand object
	 */
	public Hand() {
		super();
	}
	
	/**
	 * Get total value of the participant's hand
	 * <p>
	 * @return total value of hand
	 */
	public int totalValue() {
		int total = 0;
				
		for(int i=0; i < this.size(); i++) {
			total += this.cards.get(i).rank().rankPoints();
		}
		
		//Look for aces in hand in case total exceeds 21, treating hand as soft hand
		if(total > 21) {
			int aceCount = this.getAceCount();
			while(aceCount > 0 && total > 21) {
				aceCount--;
				total -= 10;
			}
		}
		
		return total;				
	}
	
	/**
	 * Get number of cards in participant's hand
	 * <p>
	 * @return number of cards in hand
	 */
	public int size() {
		return this.cards.size();
	}
	
	/**
	 * Clear hand
	 */
	public void clear() {
		this.cards.clear();
	}

	/**
	 * Adds a card to the hand
	 * @param card card to be added to the hand
	 */
	public void add(Card card) {
		this.cards.add(card);
	}
	
	/**
	 * Returns the cards in hand
	 * @return cards in hand
	 */
	public ArrayList<Card> getCards() {
		return this.cards;
	}

	/**
	 * Get number of aces in hand
	 * @return number of aces in hand
	 */
	private int getAceCount() {
		int aceCount = 0;
		
		for(Card card: this.cards) {
			if(card.rank() == Card.Rank.ACE){
				aceCount++;
			}
		}

		return aceCount;
	}
}