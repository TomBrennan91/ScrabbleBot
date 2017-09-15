import java.awt.BorderLayout;
import java.awt.Font;

import javax.	swing.JLabel;
import javax.swing.JPanel;

public class Player {
	String name;
	boolean isAI;
	LetterRack letterRack;
	int Score;
	JPanel display;
	JLabel scoreLabel;
	
	public Player(String name, boolean isAI) {
		super();
		this.name = name;
		this.isAI = isAI;
		this.letterRack = new LetterRack(this);
		this.Score = 0;
		System.out.println("Player: '" + name + "' has entered the game");

		display = new JPanel();
		//display.setBackground(new Color(0, 120, 0));
		display.setLayout(new BorderLayout());
		display.add(letterRack.tilePanel, BorderLayout.CENTER);
		
		JLabel nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Calibri", 1, 30));
		display.add(nameLabel, BorderLayout.WEST);
		scoreLabel = new JLabel("Score: " + Score);
		scoreLabel.setFont(new Font("Calibri", 1, 30));
		display.add(scoreLabel, BorderLayout.EAST);
	}
	
	void awardPoints(int points){
		Score += points;
		scoreLabel.setText("Score: " + Score);
	}
	
	void getMoreTiles(){
		
		letterRack.refill();
		
		display.add(letterRack.tilePanel, BorderLayout.CENTER);
		System.out.println("bot now has letters: " + letterRack.tiles.toString());
	}
	
	
}
