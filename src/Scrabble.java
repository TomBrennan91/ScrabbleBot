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
		
		Player user = new Player("Josef", false);
		Player bot = new Player("ScrabbleBot", true);
		
		JFrame f = new JFrame("Tom Brennan's ScrabbleBot");
		Board board = Board.getInstance();
		
		f.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.add(board.boardCanvas, BorderLayout.CENTER);
		f.add(user.display, BorderLayout.SOUTH);
		f.add(bot.display, BorderLayout.NORTH);
		
		new Dictionary();
		AI ai = new AI(bot);
		ai.makeFirstMove(bot.letterRack.tiles);
		
		bot.letterRack.refill();
		
		f.setSize(900,1000);  
        f.setVisible(true);
        f.setLocation(100, 100);
		f.requestFocus();

	}
}
