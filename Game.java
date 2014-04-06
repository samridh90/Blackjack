/**
 * A simple text based Blackjack 
 */

/**
 * @author samridh
 */
public abstract class Game {
	
	/**
	 * Deck of cards being used for the current game
	 */
	protected CardDeck gameDeck = CardDeck.getInstance();
	/**
	 * Cards in hand of the game participant
	 */
	protected Hand hand = new Hand();

	/**
	 * Abstract method declarations
	 */
	public abstract void firstDeal();
	public abstract void printGameState();
	public abstract void hit();
	public abstract void stand();
}