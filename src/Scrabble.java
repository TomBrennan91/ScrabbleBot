
public class Scrabble {
	public static void main(String[] args) {

		System.out.println("begin scrabble");
		TileBag bag = new TileBag();
		while (!bag.isEmpty()){
			System.out.println(bag.takeOutTile());
		}
		
	}
}
