import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile {	
	char letter;
	int points;
	JButton icon;
	ImageIcon regular;
	//ImageIcon green;
	ImageIcon blue;
	ImageIcon red;
	
	public Tile(char letter, int points) {
		super();
		this.letter = letter;
		this.points = points;

		regular = getImage("regular");
		red = getImage("red");
		blue = getImage("blue");
		
		icon = new JButton("");
		setNormal();
	
		icon.setPreferredSize(new Dimension(50, 50));
		
	}
	
	ImageIcon getImage(String color){
		ImageIcon picture = new ImageIcon(color + "/" + letter +".jpg");
		java.awt.Image TileImage = picture.getImage();
		java.awt.Image scaledImage = TileImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}
	
	public void setNormal(){
		icon.setIcon(regular);
	}
	
	public void setBlue(){
		icon.setIcon(blue);	
	}
	
	public void setRed(){
		icon.setIcon(red);
	}
	
	@Override
	public String toString() {
		return "Tile [letter=" + letter + ", points=" + points + "]";
	}
	
}
