package Blackjack;
/**
 * A simple text based Blackjack  
 */

/**
 * @author samridh
 */
public class PlayerGame extends Game {

	/**
	 * Name of the player
	 */
	private String name;
	/**
	 * Number of chips the player has
	 */
	private int chips;
	/**
	 * Current bet placed by player
	 */
	private int bet;

	/**
	 * Creates a PlayerGame
	 * <p>
	 * @param name player's name
	 */
	public PlayerGame(String name) {
		this.name = name;
		this.chips = 100;
		this.bet = 1;
	}
	
	/**
	 * Deals first two cards to player and prints the game state for the player
	 */
	@Override
	public void firstDeal() {
		Card firstCard = this.gameDeck.drawCard();
		Card secondCard = this.gameDeck.drawCard();
		
		this.hand.add(firstCard);
		this.hand.add(secondCard);
		
		this.printGameState();
	}
	
	/**
	 * Draws a card, adds it to the hand and prints the card drawn 
	 */
	@Override
	public void hit() {
		this.addCard("You chose to hit.");
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void stand() {
		System.out.println("You chose to stand.");
		System.out.println();
	}	
	
	/**
	 * Doubles player's bet, draws a card, adds it to the hand
	 */
	public void doubleDown() {
		this.chips -= this.bet;
		this.bet *= 2;
		
		this.addCard("You chose to double-down.");
	}
	
	/**
	 * Prints the game state for the Player
	 */
	@Override
	public void printGameState() {
		System.out.println();
		System.out.println("#################################################");
		System.out.println("Cards dealt to you so far are: ");
		for (Card card: this.hand.getCards()) {
			System.out.println(card.toString());
		}
		System.out.println("The value of your hand is " + this.hand.totalValue());
		System.out.println("Your current bet is " + this.bet + " chips.");
		System.out.println("You have " + this.chips + " chips left.");
		System.out.println("#################################################");
	}
	
	/**
	 * Get player's chip count
	 * <p>
	 * @return player's chip count
	 */
	public int getChipCount() {
		return this.chips;
	}
	
	/**
	 * Set number of chips for player
	 * <p>
	 * @param chips number of chips player has
	 */
	public void setChipCount(int chips) {
		this.chips = chips;
	}
	
	/**
	 * Get player's current bet
	 * <p>
	 * @return player's current bet
	 */
	public int getBet() {
		return this.bet;
	}
	
	/**
	 * Set the bet placed by the player
	 * <p>
	 * @param bet bet set by player 
	 */
	public void setBet(int bet) {
		this.bet = bet;
		this.chips -= bet;
	}
	
	/**
	 * Get player's name
	 * <p>
	 * @return player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Draws a card from the deck and adds it to the hand
	 * @param reason reason for drawing a card
	 */
	private void addCard(String reason) {
		Card nextCard = this.gameDeck.drawCard();
		this.hand.add(nextCard);
		System.out.println(String.format("%s You were dealt a(n) %s", reason, nextCard.toString()));
		this.printGameState();
	}
}
