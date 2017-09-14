import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scrabble {
	public static void main(String[] args) {
		
		System.out.println("begin scrabble");
		TileBag bag = new TileBag();
		

		
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
		
		f.setSize(800,800);  
        f.setVisible(true);
        f.setLocation(100, 100);
		f.requestFocus();
		
		
	}
	
	
}
