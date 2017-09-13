
public class Player {
	String name;
	boolean isAI;
	LetterRack letterRack;
	int Score;
	
	
	public Player(String name, boolean isAI) {
		super();
		this.name = name;
		this.isAI = isAI;
		this.letterRack = new LetterRack();
		this.Score = 0;
		System.out.println("Player: " + name + "has entered the game");
	}
	
}
