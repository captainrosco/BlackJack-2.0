import java.util.ArrayList;
import java.util.Random;

public class Hand {
		private ArrayList<Integer> hand = new ArrayList<Integer>();
		private int total = 0;
		private int card;
		
		Random random = new Random();

		
		public void buildHand(){
			hit();
			hit();
		}
	
		public void hit(){
			card = random.nextInt((10 - 1) + 1) + 1;
			hand.add(card);
			total += card;
		}
		
		public int handTotal(){
			return total;
		}
		
		public boolean bust(){
			if(handTotal() > 21){
				System.out.println("Busted!");
				return true;
			} 
			return false;
		}

		public void getHand(String player){
			System.out.printf("%s Hand: ", player);
			for (int cards : hand) {
				System.out.print(cards + " ");
			}
			System.out.println("");
		}
		
		public boolean ace(){
			if(hand.contains(1)){
				return true;
			}
			return false;
		}
		
		public void convertAce(){
			int index = hand.indexOf(1);
			hand.set(index, 11);
			total += 10;
		}
		
}
