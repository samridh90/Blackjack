/**
 * A simple text based Blackjack  
 */

/**
 * @author samridh
 */
public class DealerGame extends Game {

	/**
	 * Dealer's first card
	 */
	private Card firstCard;
	
	/**
	 * Constructs a DealerGame object
	 */
	public DealerGame() {
		
	}

	/**
	 * Deals first two cards to the dealer, prints only the first card
	 */
	@Override
	public void firstDeal() {
		Card firstCard = this.gameDeck.drawCard();
		Card secondCard = this.gameDeck.drawCard();
		
		this.firstCard = firstCard;
		hand.add(firstCard);
		hand.add(secondCard);
		
		System.out.println();
		System.out.println("The dealer drew a(n) " + this.firstCard.toString() + " and another card.");
	}

	/**
	 * Draws a card, adds it to the hand
	 */
	@Override
	public void hit() {
		Card nextCard = this.gameDeck.drawCard();
		this.hand.add(nextCard);
		
		System.out.println("Dealer hits. Dealer draws a(n) " + nextCard.toString());
	}
	
	/**
	 * Do nothing
	 */
	@Override
	public void stand() {
		System.out.println("Dealer stands.");
	}
	/**
	 * Prints the game state for the dealer
	 */
	@Override
	public void printGameState() {
		System.out.println();
		System.out.println("#################################################");
		System.out.println("Dealer's cards are: ");
		for (Card card: this.hand.getCards()) {
			System.out.println(card.toString());
		}
		System.out.println("Dealer's hand value is " + this.hand.totalValue());
		System.out.println("#################################################");
	}
	
	/**
	 * Hits for the dealer until the dealer has a soft 17.
	 * @return true if the dealer hits, false otherwise
	 */
	public boolean dealerPlay() {
		if(this.hand.totalValue() < 17) {
			this.hit();
			return true;
		}
		return false;
	}
}
