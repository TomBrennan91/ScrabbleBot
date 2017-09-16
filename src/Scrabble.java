import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Scrabble {
	
	int turnCount;
	
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
		
		JPanel southPanel =  new JPanel();
		JPanel ui = new JPanel();
		
		JLabel lettersInBag = new JLabel("Remaining Letters: ");
		ui.add(lettersInBag, BorderLayout.WEST);
		
		f.add(board.boardCanvas, BorderLayout.CENTER);
		southPanel.add(user.display, BorderLayout.SOUTH);
		southPanel.add(ui,BorderLayout.NORTH);
		f.add(southPanel, BorderLayout.SOUTH);
		f.add(bot.display, BorderLayout.NORTH);
		

		bot.letterRack.refill();

		
		new Dictionary();
		AI ai = new AI(bot);
		ai.makeFirstMove(bot.letterRack.tiles);
		
		
		
		for (Anchor anchor : ai.findAnchors()){
			System.out.println(anchor.toString());
		}
		boolean aiHasMoved = true;
		while (aiHasMoved){
			aiHasMoved = ai.makeSubsequentMove();
//			for (Anchor anchor : ai.findAnchors()){
//				System.out.println(anchor.toString());
//			}
		}
		
		

		f.setSize(900,1000);  
        f.setVisible(true);
        f.setLocation(100, 100);
		f.requestFocus();

	}
}
