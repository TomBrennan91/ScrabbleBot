import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import javax.swing.JFrame;

public class Scrabble {
	public static void main(String[] args) {
		
		System.out.println("begin scrabble");
		new TileBag();
		buildUI();
		
	}
	
	
	static void buildUI(){
		
		Player user = new Player("josef", false);
		Player bot = new Player("ScrabbleBot", true);
		
		
		JFrame f = new JFrame("Tom Brennan's ScrabbleBot");
		Board board = Board.getInstance();
		
		f.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.add(board.boardCanvas, BorderLayout.CENTER);
		f.add(user.letterRack.display, BorderLayout.SOUTH);
		f.add(bot.letterRack.display, BorderLayout.NORTH);
		
		f.setSize(800,900);  
        f.setVisible(true);
        f.setLocation(100, 100);
		f.requestFocus();
		
		
	}
	
	
}
