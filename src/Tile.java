
public class Tile {
	public Tile(char letter, int points) {
		super();
		this.letter = letter;
		this.points = points;
		//build Tile Icon
	}
	
	@Override
	public String toString() {
		return "Tile [letter=" + letter + ", points=" + points + "]";
	}
	
	char letter;
	int points;
	
}
