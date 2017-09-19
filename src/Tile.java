import java.awt.Dimension;

import javax.swing.ImageIcon;
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
					
		ImageIcon picture = new ImageIcon("tilepics/" + letter +".jpg");
		java.awt.Image TileImage = picture.getImage();
		java.awt.Image scaledImage = TileImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			//icon.setIcon(new ImageIcon(scaledImage));
		//}
		icon = new JButton("", new ImageIcon(scaledImage)); 
		//icon.setBackground(Color.yellow);
		//icon.setFont(new Font("calibri", 1, 21));
		icon.setPreferredSize(new Dimension(50, 50));
		
		//icon.setText("");
		
		//if (letter == 'A'){

	}
	
	@Override
	public String toString() {
		return "Tile [letter=" + letter + ", points=" + points + "]";
	}
	
}
