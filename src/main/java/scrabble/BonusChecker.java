package scrabble;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BonusChecker implements Constants{
	

	public static int[][] Arr;
	private static Image doubleLetter;
	private static Image doubleWord;
	private static Image tripleLetter;
	private static Image tripleWord;
	
	private static final BonusChecker instance;
	
	static{ instance = new BonusChecker();}
	
	public static BonusChecker getInstance(){
		return instance;
	}
	public static Image getImage(int bonus){
		ImageIcon picture = new ImageIcon("images/bonus/" + bonus +".jpg");
		java.awt.Image TileImage = picture.getImage();
		java.awt.Image scaledImage = TileImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		return scaledImage;
	}
	
	public static Image findImage(int row, int col){
		int bonus = check(row, col);
		
		switch (bonus) {
			case 1: return doubleLetter;
			case 2: return tripleLetter;
			case 3: return doubleWord;
			case 4: return tripleWord;
			default: return null;
		}
	}
	
	public static int letterMultiplier(int row, int col){
		if (check(row, col) == 1) return 2;
		if (check(row, col) == 2) return 3;
		return 1;
	}
	
	public static int wordMultiplier(int row, int col){
		if (check(row, col) == 3) return 2;
		if (check(row, col) == 4) return 3;
		return 1;
	}
	
	public static void RemovePlayedBonuses(){
		Tile[][] tileArr = Board.getInstance().tileArr;
		
		
		for (int row = 0 ; row < BOARD_DIMENSIONS ; row++){
			for (int col = 0 ; col < BOARD_DIMENSIONS ; col++){
				if(tileArr[row][col].letter != ' ') {
					Arr[row][col] = 0;
				}
			}
		}
	}
	
	public BonusChecker() {
		
		doubleLetter = getImage(1);
		tripleLetter = getImage(2);
		doubleWord = getImage(3);
		tripleWord = getImage(4);
		
		
		Arr = new int[15][15];

		for(int i = 1 ; i < BOARD_DIMENSIONS - 1 ; i++){
			Arr[i][i] = 3;
			Arr[BOARD_DIMENSIONS - i - 1][i] = 3; 
		}
		
		Arr[6][6] =  1;
		Arr[6][8] =  1;
		Arr[8][6] =  1;
		Arr[8][8] =  1;
		
		Arr[11][7] =  1;
		Arr[7][11] =  1;
		Arr[3][7] =  1;
		Arr[7][3] =  1;
		
		Arr[2][6] =  1;
		Arr[6][2] =  1;
		Arr[2][8] =  1;
		Arr[8][2] =  1;
		
		Arr[12][6] =  1;
		Arr[6][12] =  1;
		Arr[12][8] =  1;
		Arr[8][12] =  1;
		
		Arr[11][0] =  1;
		Arr[0][11] =  1;
		Arr[11][14] =  1;
		Arr[14][11] =  1;
		
		Arr[3][0] =  1;
		Arr[0][3] =  1;
		Arr[3][14] =  1;
		Arr[14][3] =  1;
		
		Arr[5][1] =  2;
		Arr[1][5] =  2;
		Arr[5][13] =  2;
		Arr[13][5] =  2;
		
		Arr[9][1] =  2;
		Arr[1][9] =  2;
		Arr[9][13] =  2;
		Arr[13][9] =  2;
		
		Arr[5][5] =  2;
		Arr[5][9] =  2;
		Arr[9][5] =  2;
		Arr[9][9] =  2;
		

		
		Arr[0][0] = 4;
		Arr[14][14] = 4;
		Arr[0][14] = 4;
		Arr[14][0] = 4;
		
		Arr[7][0] = 4;
		Arr[14][7] = 4;
		Arr[7][14] = 4;
		Arr[0][7] = 4;
		
	}
	
	public static Integer check(int row, int col){
		return Arr[row][col];
	}
	
}




	

