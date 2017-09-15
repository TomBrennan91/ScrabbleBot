import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player {
	String name;
	boolean isAI;
	LetterRack letterRack;
	int Score;
	JPanel display;
	
	public Player(String name, boolean isAI) {
		super();
		this.name = name;
		this.isAI = isAI;
		this.letterRack = new LetterRack(this);
		this.Score = 0;
		System.out.println("Player: '" + name + "' has entered the game");
		//System.out.println(name + " has the Tiles:");
		//this.letterRack.readTiles();
		display = new JPanel();
		display.setBackground(new Color(0, 120, 0));
		display.setLayout(new BorderLayout());
		display.add(letterRack.tilePanel, BorderLayout.CENTER);
		
		JLabel nameLabel = new JLabel(name);
		display.add(nameLabel, BorderLayout.WEST);
	}
	
}
