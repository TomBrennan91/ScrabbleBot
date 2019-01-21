package scrabble;

import scrabble.Constants;
import scrabble.Scrabble;
import scrabble.Tile;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Board extends Canvas implements Constants, Runnable{
	

	private static final long serialVersionUID = 1L;
	private static final Board instance;
	
	static{ instance = new Board();}
	
	public static Board getInstance(){
		return instance;
	}
	
	JPanel boardCanvas;
	Tile[][] tileArr;
	Canvas canvas;
	private Image dbImage = null;
	private Graphics dbg;
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
				tileArr[row][col] = new Tile(' ', 0);
			}
		}

		addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent me) {
				if  (me.getX()> 750 || me.getY() > 750){ 
        			return;
        		} else {
    
	        		int row = me.getX()/50;
	        		int col = me.getY()/50;
	        		System.out.print("[" + row + "," + col + "] ");
	        		//cellPool[me.getX()/10][me.getY()/10].click();
	        		if (tileArr[col][row].letter < 53){//tileArr[col][row].letter == ' ' || tileArr[col][row].letter >= '0' && tileArr[col][row].letter <= '4' ){
	        			System.out.println("(blank)");
	        			if (Scrabble.blueTile != null){
	        				tileArr[col][row] = Scrabble.blueTile;
	        				Scrabble.blueTile.setRed();
	        				Scrabble.user.letterRack.tiles.remove(Scrabble.blueTile);
	        				HumanMove.getInstance().add(new HumanAction(Scrabble.blueTile, col, row));
	        				Scrabble.blueTile = null;
	        			}
	        		} else {
	        			System.out.println("'" + tileArr[col][row].letter + "'");
	        		}
        		}
			}
			

			public void mousePressed(MouseEvent me) {
				if (me.getX()> 200 && me.getY() > 775 && me.getX() < 550 && me.getY() < 825) {
        			clickPlayerTiles((me.getX()/50) - 4);
        		}
			}
			

			public void mouseExited(MouseEvent e) {
			}
			

			public void mouseEntered(MouseEvent e) {
				
			}
			

			public void mouseClicked(MouseEvent e) {
			}
		});
     
		new Thread(this).start();
	}
	
	void clickPlayerTiles(int tileClicked){
		if (tileClicked < Scrabble.user.letterRack.tiles.size()){
		
		Tile clickedTile = Scrabble.user.letterRack.tiles.get(tileClicked);
		
		//System.out.println("playertiles " + clickedTile.letter) ;
		if (Scrabble.blueTile != null){
			Scrabble.blueTile.setNormal();
		}
		Scrabble.blueTile = clickedTile;
		clickedTile.setBlue();
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

	
    public void paint(Graphics g) {
    	if (dbImage!= null){
    		g.drawImage(dbImage, 0, 0, null);
    	}
    }
    
    private void paintScreen(){
    	Graphics g;
    	try{
    		g = this.getGraphics();
    		if (g != null && dbImage != null){
    			g.drawImage(dbImage, 0, 0,  null);
    		}
    		g.dispose();
    	} catch (Exception e){
    		//System.err.println("Graphics context error " + e);
    		//e.printStackTrace();
    	}
    }
    
    private void render(){
    	if (dbImage == null){
    		dbImage = createImage(750, 900);
    		if (dbImage == null){
    			//System.err.println("dbImage is null");
    			return;
    		} else {
    			dbg = dbImage.getGraphics();
    		}
    	}
    	
    	
		for (int row = 0 ; row < tileArr.length ; row ++){
			for (int col = 0 ; col < tileArr[0].length ; col ++){
				//g.fillRect(col * 50, row * 50, 48, 48);
				//if (tileArr[row][col].redraw){
				if (tileArr[row][col].letter == ' ' && BonusChecker.check(row, col) > 0){
					dbg.drawImage(BonusChecker.findImage(row, col), col * 50, row * 50, null);
				} else { 
					dbg.drawImage(tileArr[row][col].image , col * 50, row * 50, null);
				}
				
				
				dbg.drawRect(col * 50, row * 50, 50, 50);
				//	tileArr[row][col].redraw = false;
				//}
				
				//canvas.getGraphics().drawImage(tileArr[row][col].image , col*50, row*50, null);// (blank.icon);
			}
		}
		dbg.setColor(new Color(0,100,0));
		dbg.fillRect(0, 750, 750, 100);
		dbg.setColor(Color.BLACK);
		for (int i = 0 ; i < Scrabble.user.letterRack.tiles.size() ; i++ ){
			System.err.println( i + " " + Scrabble.user.letterRack.tiles.size());
			
			dbg.drawImage(Scrabble.user.letterRack.tiles.get(i).image, 200 + (i*50), 775, null);
			dbg.drawRect(200 + (i*50) , 775, 50, 50);
		}
    }

	public void run() {
		boolean running = true;
		while(running){
			render();
			paintScreen();
			try{
				Thread.sleep(20);
			} catch (InterruptedException e){}
		}
		System.exit(0);
	}
}
