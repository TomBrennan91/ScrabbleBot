
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class Scrabble {
	
	int turnCount;
	static JTextArea log;
	
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
		
		JPanel eastPanel =  new JPanel();
		log = new JTextArea();

		
		eastPanel.setLayout(new BorderLayout());
	
		
		
		JLabel lettersInBag = new JLabel("Remaining Letters: ");
		lettersInBag.setHorizontalAlignment(SwingConstants.CENTER);
		lettersInBag.setFont(new Font("Calibri", 1, 30));
		eastPanel.add(lettersInBag, BorderLayout.NORTH);
		
		JPanel controls = getControls();
		
		eastPanel.add(log, BorderLayout.CENTER);
		eastPanel.add(controls, BorderLayout.SOUTH);
		

		
		f.add(board.boardCanvas, BorderLayout.CENTER);
		f.add(user.display, BorderLayout.SOUTH);
		f.add(bot.display, BorderLayout.NORTH);
		f.add(eastPanel, BorderLayout.EAST);
		
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
		}

		f.setSize(1100,1000);  
        f.setVisible(true);
        f.setLocation(100, 100);
		f.requestFocus();

	}

	private static JPanel getControls() {
		JButton play = new JButton("Play");
		JButton pass = new JButton("Pass");
		JButton swap = new JButton("Swap");
		JButton shuffle = new JButton("Shuffle");
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(4, 1));
		controls.add(play, BorderLayout.SOUTH);
		controls.add(pass, BorderLayout.SOUTH);
		controls.add(swap, BorderLayout.SOUTH);
		controls.add(shuffle, BorderLayout.SOUTH);
		return controls;
	}
}
