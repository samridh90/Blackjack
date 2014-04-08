package Blackjack;
/**
 * A simple text based Blackjack.
 */

import java.util.*;

/**
 * @author samridh
 */
public class Blackjack {

	/**
	 * The minimum bet; set to 1 chip
	 */
	private static final int MIN_BET = 1;
	/**
	 * Number of chips player starts with; set to 100
	 */
	private static final int INITIAL_CHIPS = 100;
	/**
	 * String scanner to accept input from player
	 */
	private static final Scanner textScanner = new Scanner(System.in);
	/**
	 * Main logic for blackjack game
	 * @param args
	 */
	public static void main(String[] args) {

		printInitialText();
		/**
		 * Get user's name and initialize the PlayerGame
		 */
		String playerName = getPlayerName();
		PlayerGame player = new PlayerGame(playerName);
		player.setChipCount(INITIAL_CHIPS);
		printPlayerGreeting(player);
		/**
		 * Initialize the DealerGame
		 */
		DealerGame dealer = new DealerGame();
		/**
		 * Variables for player's choice, play again and to check if this is the first time dealing
		 */
		char choice = 'x';
		boolean newGame = true;
		boolean firstDeal = true;
		/**
		 * Outer loop runs as long as player wants to continue playing 
		 */
		do {
			boolean canDoubleDown = true;
			if(firstDeal){
				/**
				 * Initial deal for the round
				 */
				System.out.println("********************************************************");
				/**
				 * Get the number of chips the player wants to bet 
				 */
				int bet;
				do {
					bet = getBet(player.getChipCount());
				}while(bet < MIN_BET || bet > player.getChipCount());					
				player.setBet(bet);

				player.hand.clear();
				dealer.hand.clear();
				
				/**
				 * Deal initial two cards to player and dealer
				 */
				player.firstDeal();
				dealer.firstDeal();
			}
			/**
			 * Player has blackjack. Decide who the winner of the round is.
			 */
			if(player.hand.totalValue() == 21) {
				decideWinner(player, dealer);
			}
			else {
				/**
				 * Ask the player what they want to do next based on cards dealt till now
				 */
				canDoubleDown = canDoubleDown && firstDeal;
				choice = getUserChoice(canDoubleDown);
				
				switch(choice) {
					case 'h': 
						player.hit();
						break;
					case 's':
						player.stand();
						break;
					case 'd':
						canDoubleDown = player.doubleDown();
						break;
				}
				if(player.hand.totalValue() >= 21) {
					/**
					 * Player has 21 or has gone bust. Decide who wins the round
					 */
					decideWinner(player, dealer);
				} else if(choice == 'h' || (firstDeal && !canDoubleDown)) {
					/**
					 * If the player hits or can't double down,
					 * continue and ask them what they want to do next  
					 */
					if(firstDeal) {
						firstDeal = false;
					}
					System.out.println();
					continue;
				} else {
					/**
					 * It's the dealer's turn to play
					 */
					dealer.printGameState();
					boolean dealerHit = true;
					do {
						dealerHit = dealer.dealerPlay();
					}while(dealerHit);
					
					if(dealer.hand.totalValue() <= 21) {
						/**
						 * Dealer chose to stand, did not go bust
						 */
						dealer.stand();
					}
					/**
					 * Decide who wins the round once the dealer is done
					 */
					decideWinner(player, dealer);
				}
			}
			/**
			 * If the players chose to stand, double-down or went bust, a new game is started
			 */
			firstDeal = (choice == 's' ||
						(choice == 'd' && canDoubleDown) ||
						 player.hand.totalValue() >= 21);
			System.out.println("********************************************************");

			if(player.getChipCount() < 1) {
				System.out.println();
				System.out.println("You seem to have run out of chips! :(");
				System.out.println("The house wins this time.");
				System.out.println("Better luck next time!");
				break;
			}
			else {
				System.out.println();
				System.out.println(String.format("You now have %s chips...", player.getChipCount()));
			}
			newGame = playAgain();
		}while(newGame);
		
		textScanner.close();
	}

	/**
	 * Gets the user's choice
	 * @return true if the user wants to play again, false otherwise 
	 */
	private static boolean playAgain() {		
		boolean invalid;
		boolean tryAgain = false;
		
		do {
			invalid = false;
			System.out.print("Would you like to play again? (YES (y) or NO (n)) ");
			char choice = textScanner.next().toLowerCase().charAt(0);
			switch(choice){
				case 'y':
					tryAgain = true;
					break;
				case 'n':
					tryAgain = false;
					break;
				default:
					invalid = true;
					break;
			}
		}while(invalid);
		
		return tryAgain;
	}

	/**
	 * Gets the user's choice
	 * @return 'h', 's' or 'd'
	 */
	private static char getUserChoice(boolean canDoubleDown) {
		char choice;
		boolean invalid = false;
		
		do {
			invalid = false;
			System.out.println();
			System.out.println("What do you choose to do next?");
			if (canDoubleDown) {
				System.out.print("HIT (h), STAND (s) or DOUBLE-DOWN (d): ");
			} else {
				System.out.print("HIT (h) or STAND (s): ");
			}
			choice = textScanner.next().toLowerCase().charAt(0);
			
			switch(choice) {
				case 'h': break;
				case 's': break;
				case 'd': invalid = !canDoubleDown;
						  break;
				default: invalid = true;
			};
		}while(invalid);
		
		return choice;
	}

	/**
	 * Decides who wins the round
	 * @param player the player
	 * @param dealer the dealer
	 */
	private static void decideWinner(PlayerGame player, DealerGame dealer) {
		
		dealer.printGameState();
		
		if(player.hand.totalValue() == 21) {
			if(dealer.hand.totalValue() != 21) {
				if(player.hand.size()  == 2) {
					player.setChipCount(player.getChipCount() + (player.getBet() * 3));
					System.out.println("You have a Blackjack hand. You WIN this round! (1:2 payout)");					
				}
				else {
					player.setChipCount(player.getChipCount() + (player.getBet() * 2));
					System.out.println("You have a hand value of 21. You WIN this round! (1:1 payout)");
				}
			}
			else {
				player.setChipCount(player.getChipCount() + player.getBet());
				System.out.println("This round ends in a tie. Both, you and the dealer, have a hand value of 21.");
			}
		}
		else {
			if(player.hand.totalValue() > 21) {
				System.out.println("You went BUST! :( You LOSE this round.");
			}
			else if (dealer.hand.totalValue() > 21) {
				player.setChipCount(player.getChipCount() + (player.getBet() * 2));
				System.out.println("The dealer went BUST. You WIN this round! (1:1 payout)");
			}
			else {
				if(player.hand.totalValue() > dealer.hand.totalValue()) {
					player.setChipCount(player.getChipCount() + (player.getBet() * 2));
					System.out.println("You have a higher hand value than the dealer. You WIN this round! (1:1 payout)");
				}
				else if(player.hand.totalValue() < dealer.hand.totalValue()) {
					System.out.println("You have lower hand value than the dealer. You LOSE this round :(");
				}
				else {
					player.setChipCount(player.getChipCount() + player.getBet());
					System.out.println("This round ends in a tie. Both, you and the dealer, have the same hand value.");
				}
			}
		}
	}

	/**
	 * Gets the bet the player wants to place
	 * @param maxBet max bet that can be placed
	 * @return bet placed by player
	 */
	private static int getBet(int maxBet) {
		int bet = 0;
		boolean exception = false;
		
		do {
			exception = false;
			try {
				System.out.print("Enter the number of chips you want to BET (min: " + MIN_BET + ", max: " + maxBet + ") ");
				bet = Integer.parseInt(textScanner.next());
			}
			catch(NumberFormatException ex) {
				exception = true;
			}
		}while(exception);
		
		return bet;
	}

	/**
	 * Greets the player and prints some basic information
	 * @param player the player of the game
	 */
	private static void printPlayerGreeting(PlayerGame player) {
		System.out.println("Hey there, " + player.getName());
		System.out.println("Let's play a game of Blackjack.");
		System.out.println("You have " + player.getChipCount() + " chips right now.");
		System.out.println("The minimum bet is " + MIN_BET + " chip(s).");
		System.out.println("The payouts on winning are 1:2 for a Blackjack and 1:1 otherwise.");
		System.out.println("Let's play!");
	}

	/**
	 * Gets the player's name
	 * @return player's name
	 */
	public static String getPlayerName() {
		String playerName = null;
		System.out.print("Please type out your name: ");
		do {
			playerName = textScanner.next();
		} while(playerName == null || playerName.isEmpty());
		
		return playerName;
	}
	
	/**
	 * Prints the initial header text.
	 */
	public static void printInitialText() {
		System.out.println("********************************************************");
		System.out.println("Welcome, to a simple single-player text-based Blackjack.");
		System.out.println("********************************************************");
	}

}
