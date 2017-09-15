import java.awt.GridLayout;

import javax.swing.JPanel;

public class Board implements Constants{
	
	private static final Board instance;
	
	static{ instance = new Board();}
	
	public static Board getInstance(){
		return instance;
	}
	
	JPanel boardCanvas;
	Tile[][] tileArr;
	
	Board(){
		boardCanvas = new JPanel();
		boardCanvas.setLayout(new GridLayout(BOARD_DIMENSIONS, BOARD_DIMENSIONS));
		tileArr = new Tile[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
		
		
		//fill board with blank buttons
		for (int row = 0 ; row < tileArr.length ; row ++){
			for (int col = 0 ; col < tileArr[0].length ; col ++){
				Tile blank = new BlankTile();
				boardCanvas.add(blank.icon);
				tileArr[row][col] = blank;
			}
		}
	}
	
	
	
	void print() {
		for (int row = 0 ; row < tileArr.length ; row ++){
			StringBuilder boardString = new StringBuilder();
			for (int col = 0 ; col < tileArr[0].length ; col ++){
				boardString.append(tileArr[row][col].letter);
			}
			System.out.println(boardString.toString());
		}
	}


	void reDraw(){
		for (int row = 0 ; row < tileArr.length ; row ++){
			for (int col = 0 ; col < tileArr[0].length ; col ++){
				boardCanvas.remove(0);
				boardCanvas.add(tileArr[row][col].icon);
			}
		}
	}

}
