/**
 * A simple text based Blackjack 
 */

import java.util.*;

/**
 * @author samridh
 */
public abstract class Game {
	
	/**
	 * Deck of cards being used for the current game
	 */
	protected CardDeck gameDeck = CardDeck.getInstance();
	/**
	 * Cards in the hand of the game participant
	 */
	protected ArrayList<Card> hand = new ArrayList<Card>();

	public abstract void firstDeal();
	public abstract void printGameState();
	public abstract void hit();
	public abstract void stand();
	
	/**
	 * Get total value of the participant's hand
	 * <p>
	 * @return total value of hand
	 */
	public int totalHandValue() {
		int total = 0;
				
		for(int i=0; i < this.handSize(); i++) {
			total += this.hand.get(i).rank().rankPoints();
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
	public int handSize() {
		return this.hand.size();
	}
	
	/**
	 * Clear hand
	 */
	public void clearHand() {
		this.hand.clear();
	}
	
	/**
	 * Get number of aces in hand
	 * @return number of aces in hand
	 */
	private int getAceCount() {
		int aceCount = 0;
		
		for(Card card: this.hand) {
			if(card.rank() == Card.Rank.ACE){
				aceCount++;
			}
		}
		
		return aceCount;
	}
}