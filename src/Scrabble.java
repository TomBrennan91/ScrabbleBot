
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class Scrabble {
	
	int turnCount;
	static JTextArea log;
	static JLabel lettersInBag;
	static AI ai;
	static Player user;
	static Player bot;
	public static void main(String[] args) {
		
		System.out.println("begin scrabble");
		new TileBag();
		buildUI();
		beginGame();
	}
	
	static void buildUI(){
		JPanel eastPanel = drawEastPanel();
		user = new Player("Josef", false);
		bot = new Player("ScrabbleBot", true);
		
		JFrame f = new JFrame("Tom Brennan's ScrabbleBot");
		Board board = Board.getInstance();

		drawMainFrame(bot, f, board, eastPanel);
		
	}

	private static void beginGame() {
		bot.letterRack.refill();

		new Dictionary();
		ai = new AI(bot);
		ai.makeFirstMove(bot.letterRack.tiles);

		for (Anchor anchor : ai.findAnchors()){
			System.out.println(anchor.toString());
		}
		
		boolean aiHasMoved = true;
//		while (aiHasMoved){
//			aiHasMoved = ai.makeSubsequentMove();
//		}
	}

	private static JPanel drawEastPanel() {
		lettersInBag = new JLabel("Remaining Letters: ");
		lettersInBag.setHorizontalAlignment(SwingConstants.CENTER);
		lettersInBag.setFont(new Font("Calibri", 1, 30));
		JPanel controls = getControls();
		
		JPanel eastPanel =  new JPanel();
		log = new JTextArea();
		log.setPreferredSize(new Dimension(250, 700));
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(lettersInBag, BorderLayout.NORTH);
		eastPanel.add(log, BorderLayout.CENTER);
		eastPanel.add(controls, BorderLayout.SOUTH);
		return eastPanel;
	}

	private static void drawMainFrame(Player bot, JFrame f, Board board, JPanel eastPanel) {
		f.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(board.boardCanvas, BorderLayout.CENTER);
		f.add(user.display, BorderLayout.SOUTH);
		f.add(bot.display, BorderLayout.NORTH);
		f.add(eastPanel, BorderLayout.EAST);
		f.setSize(1100,1000);  
        f.setVisible(true);
        f.setLocation(100, 100);
		f.requestFocus();
	}

	private static JPanel getControls() {
		JButton play = new JButton("Play");
		JButton pass = new JButton("Pass");
		JButton swap = new JButton("Swap");
		//JButton shuffle = new JButton("Shuffle");
		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ai.makeSubsequentMove();
			}
		});
		
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(3, 1));
		controls.add(play );
		controls.add(pass, BorderLayout.SOUTH);
		controls.add(swap, BorderLayout.SOUTH);
		//controls.add(shuffle, BorderLayout.SOUTH);
		return controls;
	}
}
