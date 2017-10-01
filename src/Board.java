import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Board extends Canvas implements Constants{
	

	private static final long serialVersionUID = 1L;
	private static final Board instance;
	
	static{ instance = new Board();}
	
	public static Board getInstance(){
		return instance;
	}
	
	JPanel boardCanvas;
	Tile[][] tileArr;
	Canvas canvas;
	
	Board(){
		//boardCanvas = new JPanel();

		//boardCanvas.setLayout(new GridLayout(BOARD_DIMENSIONS, BOARD_DIMENSIONS));
		tileArr = new Tile[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
		
		//boardCanvas.setPreferredSize(new Dimension(1000,1000));
		//boardCanvas.setMinimumSize(new Dimension(1000,1000));

		//ImageIcon picture = new ImageIcon("/images/blank.jpg");
		//java.awt.Image TileImage = picture.getImage();
		
		canvas = new Canvas();
		canvas.setSize(750, 900);
		canvas.setBackground(Color.GREEN);
		
		//fill board with blank buttons
		for (int row = 0 ; row < tileArr.length ; row ++){
			for (int col = 0 ; col < tileArr[0].length ; col ++){
				//Tile blank = new BlankTile(row,col);
				//canvas.
				
				tileArr[row][col] = new Tile(' ', 0);
			}
		}

		addBonusTiles();
		
		
		
		addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent me){
        		
        		
        		if (me.getX()> 200 && me.getY() > 775 && me.getX() < 550 && me.getY() < 825) {
        			clickPlayerTiles((me.getX()/50) - 4);
        	
        		} else if  (me.getX()> 750 || me.getY() > 750){ 
        			return;
        		} else {
    
	        		int row = me.getX()/50;
	        		int col = me.getY()/50;
	        		System.out.print("[" + row + "," + col + "] ");
	        		//cellPool[me.getX()/10][me.getY()/10].click();
	        		if (tileArr[col][row].letter == ' ' || tileArr[col][row].letter >= '0' && tileArr[col][row].letter <= '4' ){
	        			System.out.println("(blank)");
	        			if (Scrabble.blueTile != null){
	        				tileArr[col][row] = Scrabble.blueTile;
	        				Scrabble.blueTile.setRed();
	        				Scrabble.user.letterRack.tiles.remove(Scrabble.blueTile);
	        				Scrabble.blueTile = null;
	        				
	        			}
	        			
	        		} else {
	        			System.out.println("'" + tileArr[col][row].letter + "'");
	        		}
	        		
        		}
        		
        		repaint();
        		
        	}
		});
		
		repaint();
		
	}
	
	void clickPlayerTiles(int tileClicked){
		if (tileClicked < Scrabble.user.letterRack.tiles.size()){
		
		Tile clickedTile = Scrabble.user.letterRack.tiles.get(tileClicked);
		
		System.out.println("playertiles " + clickedTile.letter) ;
		if (Scrabble.blueTile != null){
			Scrabble.blueTile.setNormal();
		}
		Scrabble.blueTile = clickedTile;
		clickedTile.setBlue();
		}
		
	}
	
	void addBonusTiles(){
		addDoubleWord();
		addTripleWord();
		addDoubleLetter();
		addTripleLetter();
		//add starting tile
		tileArr[7][7] = new Tile('0',0);
	}

	private void addTripleLetter() {
		tileArr[5][1] =  new Tile('2',0);
		tileArr[1][5] =  new Tile('2',0);
		tileArr[5][13] =  new Tile('2',0);
		tileArr[13][5] =  new Tile('2',0);
		
		tileArr[9][1] =  new Tile('2',0);
		tileArr[1][9] =  new Tile('2',0);
		tileArr[9][13] =  new Tile('2',0);
		tileArr[13][9] =  new Tile('2',0);
		
		tileArr[5][5] =  new Tile('2',0);
		tileArr[5][9] =  new Tile('2',0);
		tileArr[9][5] =  new Tile('2',0);
		tileArr[9][9] =  new Tile('2',0);
	}

	private void addDoubleLetter() {
		tileArr[6][6] =  new Tile('1',0);
		tileArr[6][8] =  new Tile('1',0);
		tileArr[8][6] =  new Tile('1',0);
		tileArr[8][8] =  new Tile('1',0);
		
		tileArr[11][7] =  new Tile('1',0);
		tileArr[7][11] =  new Tile('1',0);
		tileArr[3][7] =  new Tile('1',0);
		tileArr[7][3] =  new Tile('1',0);
		
		tileArr[2][6] =  new Tile('1',0);
		tileArr[6][2] =  new Tile('1',0);
		tileArr[2][8] =  new Tile('1',0);
		tileArr[8][2] =  new Tile('1',0);
		
		tileArr[12][6] =  new Tile('1',0);
		tileArr[6][12] =  new Tile('1',0);
		tileArr[12][8] =  new Tile('1',0);
		tileArr[8][12] =  new Tile('1',0);
		
		tileArr[11][0] =  new Tile('1',0);
		tileArr[0][11] =  new Tile('1',0);
		tileArr[11][BOARD_DIMENSIONS-1] =  new Tile('1',0);
		tileArr[BOARD_DIMENSIONS-1][11] =  new Tile('1',0);
		
		tileArr[3][0] =  new Tile('1',0);
		tileArr[0][3] =  new Tile('1',0);
		tileArr[3][BOARD_DIMENSIONS-1] =  new Tile('1',0);
		tileArr[BOARD_DIMENSIONS-1][3] =  new Tile('1',0);
	}

	private void addDoubleWord() {
		for(int i = 1 ; i < BOARD_DIMENSIONS - 1 ; i++){
			tileArr[i][i] = new Tile('3',0);
			tileArr[BOARD_DIMENSIONS - i - 1][i] = new Tile('3',0); 
		}
	}

	private void addTripleWord() {
		tileArr[0][0] = new Tile('4',0);
		tileArr[BOARD_DIMENSIONS-1][BOARD_DIMENSIONS-1] = new Tile('4',0);
		tileArr[0][BOARD_DIMENSIONS-1] = new Tile('4',0);
		tileArr[BOARD_DIMENSIONS-1][0] = new Tile('4',0);
		tileArr[7][0] = new Tile('4',0);
		tileArr[BOARD_DIMENSIONS-1][7] = new Tile('4',0);
		tileArr[7][BOARD_DIMENSIONS-1] = new Tile('4',0);
		tileArr[0][7] = new Tile('4',0);
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

//
//	void reDraw(){
//		for (int row = 0 ; row < tileArr.length ; row ++){
//			for (int col = 0 ; col < tileArr[0].length ; col ++){
//				boardCanvas.remove(0);
//				boardCanvas.add(tileArr[row][col].icon);
//			}
//		}
//	}
	
    public void paint(Graphics g) {
    	super.paint(g);
		for (int row = 0 ; row < tileArr.length ; row ++){
			for (int col = 0 ; col < tileArr[0].length ; col ++){
				//g.fillRect(col * 50, row * 50, 48, 48);
				g.drawImage(tileArr[row][col].image , col * 50, row * 50, null);
				g.drawRect(col * 50, row * 50, 50, 50);
				//canvas.getGraphics().drawImage(tileArr[row][col].image , col*50, row*50, null);// (blank.icon);
			}
		}
		g.setColor(new Color(0,100,0));
		g.fillRect(0, 750, 750, 100);
		g.setColor(Color.BLACK);
		for (int i = 0 ; i < Scrabble.user.letterRack.tiles.size() ; i++ ){
			g.drawImage(Scrabble.user.letterRack.tiles.get(i).image, 200 + (i*50), 775, null);
			g.drawRect(200 + (i*50) , 775, 50, 50);
		}
    }
}
