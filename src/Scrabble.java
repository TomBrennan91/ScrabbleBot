
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	static Tile blueTile;
	static ArrayList<String> wordList;
	static JCheckBox hardMode;
	static JCheckBox enforeDictionary;
	
	public static void main(String[] args) {
		
		System.out.println("begin scrabble");
		new TileBag();
		buildUI();
		beginGame();
	}
	
	static void buildUI(){
		JPanel eastPanel = drawEastPanel();
		//PlayerTiles = new ArrayList<Tile>();
		
		user = new Player("Josef", false);
		bot = new Player("ScrabbleBot", true);
		
		JFrame f = new JFrame("Tom Brennan's ScrabbleBot");
		Board board = Board.getInstance();
		
		drawMainFrame(bot, f, board, eastPanel);
		
	}
	
	private static void beginGame() {

		new Dictionary();
		ai = new AI(bot);
		ai.makeFirstMove();
		
		//Board.getInstance().print();
		
		wordList = WordsOnBoard.getWordList();
		
		wordList.forEach(System.out::println);
		
		//Board.getInstance().print();
		//Board.getInstance().reDraw();

//		for (Anchor anchor : ai.findAnchors()){
//			System.out.println(anchor.toString());
//		}
		
		//System.err.println(PlayerTiles.toString());
		
//		boolean aiHasMoved = true;
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
		f.add(board, BorderLayout.CENTER);
		f.add(user.display, BorderLayout.SOUTH);
		f.add(bot.display, BorderLayout.NORTH);
		f.add(eastPanel, BorderLayout.EAST);
		f.setSize(1025,980);  
        f.setVisible(true);
        f.setLocation(100, 100);
		f.requestFocus();
	}

	private static JPanel getControls() {
		JButton play = new JButton("Play");
		JButton pass = new JButton("Pass");
		JButton swap = new JButton("Swap");
		JButton cancel = new JButton("Cancel");
		JButton shuffle = new JButton("Shuffle");
		hardMode = new JCheckBox("Hard Mode");
		enforeDictionary = new JCheckBox("Enforce Dictionary");
		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (HumanMove.isValid()){
					//System.out.println( HumanMove.getInstance().toString());
					HumanMove.execute();
					ai.makeSubsequentMove();
					wordList = WordsOnBoard.getWordList();
				}
			}
		});
		pass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HumanMove.reverse();
				log.append(user.name + " passes this turn\n");
				ai.makeSubsequentMove();
			}
		});
		swap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HumanMove.reverse();
				user.swapTiles();
				ai.makeSubsequentMove();
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HumanMove.reverse();
			}
		});
		
		shuffle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HumanMove.reverse();
				Scrabble.user.letterRack.ShuffleTiles();
			}
		});
		
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(4, 1));
		
		JPanel one = new JPanel(new GridLayout(1, 1));
		one.add(play);
		
		JPanel two = new JPanel(new GridLayout(1, 2));
		two.add(pass);
		two.add(swap);
		
		JPanel three = new JPanel(new GridLayout(1, 2));
		three.add(cancel);
		three.add(shuffle);
		
		JPanel four = new JPanel(new GridLayout(1, 2));
		four.add(hardMode);
		four.add(enforeDictionary);
		
		controls.add(one);
		controls.add(two);
		controls.add(three);
		controls.add(four);
		
//		controls.add(play);
//		controls.add(pass);
//		controls.add(swap);
//		controls.add(cancel);
//		controls.add(shuffle);
//		controls.add(hardMode);
//		controls.add(enforeDictionary);
		return controls;
	}
}
