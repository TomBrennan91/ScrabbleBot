import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board implements Constants{
	
	private static final Board instance;
	
	static{ instance = new Board();}
	
	public static Board getInstance(){
		return instance;
	}
	
	JPanel boardCanvas;
	char[][] tileArr;
	
	Board(){
		boardCanvas = new JPanel();
		boardCanvas.setLayout(new GridLayout(BOARD_DIMENSIONS, BOARD_DIMENSIONS));
		//GridBagConstraints gbc = new GridBagConstraints();
		tileArr = new char[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
		
		//fill board with blank buttons
		
		for (int row = 0 ; row < tileArr.length ; row ++){
			for (int col = 0 ; col < tileArr[0].length ; col ++){

				JButton blank = new JButton("");
				blank.setBackground(Color.WHITE);
				boardCanvas.add(blank);
			}
		}
		
	}
	
	
	
}
