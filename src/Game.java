import java.util.Scanner;

public class Game {
	static int bet = 5;
	static int total = 0;

	public static void main(String[] args) {
		boolean BlackJack = false;

		do {
			Hand player = new Hand();
			Hand dealer = new Hand();
			player.buildHand();
			dealer.buildHand();
			player.getHand("Player");
			dealer.getHand("Dealer");
			hitLoop: do {
				//checks Aces
				if (player.ace()) {
					if (ask("Flip Ace?")) {
						player.convertAce();
						player.getHand("Player");
					}
				}
				//Hits
				if (ask("Hit? Y/N")) {
					player.hit();
					if (player.handTotal() == 21) {
						System.out.println("BLACKJACK!");
						BlackJack = true;
						win();
						break hitLoop;
					} else if (player.bust()) {
						lose();
						break hitLoop;
					}
					player.getHand("Player");
				} else {
					break;
				}
			} while (true);
			//Plays Dealer Hand
			do {
				if (dealer.handTotal() < 17 && player.handTotal() < 21) {
					dealer.hit();
					if (dealer.bust()) {
						win();
						break;
					}
					dealer.getHand("Dealer");
				} else {
					break;
				}
			} while (true);
			//Totals
			System.out.println("Player Total: " + player.handTotal() + " Dealer Total: " + dealer.handTotal());
			//Compares Hands
			if (player.handTotal() < 22 && dealer.handTotal() < 22 && !BlackJack) {
				if (player.handTotal() > dealer.handTotal()) {
					System.out.println("YOU WIN!");
					win();
				} else {
					System.out.println("Sorry, You Lose.");
					lose();
				}
			}
			//Ask Play Again.
			if (!ask("Play Again? Y/N")) {
				break;
			}
		} while (true);
		System.out.println("Your Winnings: $" + total);

	}

	static Scanner input = new Scanner(System.in);
	
	static public boolean ask(String message) {
		System.out.println(message);
		String answer = input.nextLine().trim();
		if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
			return true;
		} else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("yes")) {
			return false;
		} else {
			System.out.println("Please Enter Y/N");
			return ask(message);
		}
	}

	static void win() {
		System.out.println("You win $" + bet);
		total += bet;
	}

	static void lose() {
		System.out.println("You lose $" + bet);
		total -= bet;
	}
}
