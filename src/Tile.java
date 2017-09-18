import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Tile {
	char letter;
	int points;
	JButton icon;

	//Tile me;
	
	public Tile(char letter, int points) {
		super();
		this.letter = letter;
		this.points = points;
		//this.me = this;
		//build Tile Icon
		icon = new JButton("" + letter);
		icon.setBackground(Color.yellow);
		icon.setFont(new Font("calibri", 1, 21));
		icon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (letter != ' '){
					if (Scrabble.blueTile != null){
						
						Scrabble.blueTile.setBackground(Color.YELLOW);
						
					}	
					icon.setBackground(Color.CYAN);
					
					Scrabble.blueTile = icon;
						
						
				}
			}
		});

	}
	
	@Override
	public String toString() {
		return "Tile [letter=" + letter + ", points=" + points + "]";
	}
	
}
