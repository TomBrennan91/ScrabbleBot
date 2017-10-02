import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile {	
	char letter;
	int points;

	Image image;

	
	public Tile(char letter, int points) {
		super();
		this.letter = letter;
		this.points = points;

		setNormal();
	}
	
	Image getImage(String color){
		ImageIcon picture;
		if (isBlank()){
			picture = new ImageIcon("images/blank.jpg");
		} else if(isNumeric()){
			picture = new ImageIcon("images/bonus/" + letter +".jpg");
		} else {
			picture = new ImageIcon("images/" + color + "/" + letter +".jpg");
		}
		
		java.awt.Image TileImage = picture.getImage();
		java.awt.Image scaledImage = TileImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		return scaledImage;
		//return new ImageIcon(scaledImage);
	}
	
	public boolean isBlank(){
		if (letter == ' ') return true;
		return false;
	}
	
	public boolean isNumeric(){
		if (letter >= '0' && letter <= '4')return true;
		return false;
	}
	
	
	public void setNormal(){
		image = getImage("regular");
	}
	
	public void setBlue(){
		image = getImage("blue");	
	}
	
	public void setRed(){
		image = getImage("red");
	}
	
	@Override
	public String toString() {
		return "Tile [letter=" + letter + ", points=" + points + "]";
	}
	
}
