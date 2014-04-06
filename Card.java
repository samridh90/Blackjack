/**
 * A simple text based BlackJack.
 */

/**
 * @author samridh
 */
public class Card {
	/**
	 * @author samridh
	 */
	public static enum Rank {
		DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
		EIGHT(8), NINE(9), TEN(10), JACK(10), KING(10), QUEEN(10),
		ACE(11);
		
		/**
		 * Blackjack rank value of a card
		 */
		private int rankPoints;
		
		/**
		 * Constructor for a card rank
		 * <p>
		 * @param points blackjack value of a rank
		 */
		Rank(int points) {
			this.rankPoints = points;
		}
		
		/**
		 * Returns the blackjack value of a rank
		 * <p>
		 * @return blackjack value of a rank
		 */
		public int rankPoints() {
			return this.rankPoints;
		}
	}
	
	/**
	 * @author samridh
	 */
	public static enum Suit {
		SPADES(1), CLUBS(2), DIAMONDS(3), HEARTS(4);
		
		/**
		 * Suit value of a card
		 */
		private int suitPoints;
		
		/**
		 * Constructor for a card suit
		 * <p>
		 * @param points value of card suit
		 */
		Suit(int points) {
			this.suitPoints = points;
		}
		
		/**
		 * Returns value of the card suit
		 * <p>
		 * @return value of the card suit
		 */
		public int suitPoints() {
			return this.suitPoints;
		}
		
		/**
		 * Returns unicode string representation of each suit
		 */
		@Override
		public String toString() {
			String out = "";
			switch(this.suitPoints) {
				case 1:
					out = "SPADES (\u2660)";
					break;
				case 2:
					out = "CLUBS (\u2663)";
					break;
				case 3:
					out = "DIAMONDS (\u2666)";
					break;
				case 4:
					out = "HEARTS (\u2665)";
					break;					
			}
			return out;
		}
	}
	
	/**
	 * Rank of the card
	 */
	private final Rank rank;
	/**
	 * Suit of the card
	 */
	private final Suit suit;

	/**
	 * Parametric public constructor
	 * <p>
	 * @param rank rank of the card
	 * @param suit suit of the card
	 */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
	 * Returns rank of the card
	 * <p>
	 * @return rank of the card 
	 */
	public Rank rank() {
		return this.rank;
	}
	
	/**
	 * Returns suit of the card
	 * <p>
	 * @return suit of the card
	 */
	public Suit suit() {
		return this.suit;
	}
	
	/**
	 * Convert card to human readable string 
	 */
	public String toString() {
		return String.format("%s of %s", rank.toString(), suit.toString());
	}
}
