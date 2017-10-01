import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class HumanMove implements Constants{
	
	private static ArrayList<HumanAction> actionList;
	static{
		actionList = new ArrayList<HumanAction>();
	}
	
	public static ArrayList<HumanAction> getInstance(){
		return  actionList;
	}
	
	public static boolean isValid(){
		return hasMovedTiles() && (isRowOrCol()) && !hasGaps();
	}
	
	private static boolean hasMovedTiles(){
		if (actionList.size() > 0){
			return true;
		}
		JOptionPane.showMessageDialog(null, "Please move some tiles onto the board before pressing 'Play'");
		return false;
	}
	
	private static boolean isRowOrCol(){
		if (isRow() || isCol() ){
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Tiles must be all be in the same row or column");
			return false;
		}
	}
	
	private static boolean isRow(){
		int moveRow = actionList.get(0).row;
		for (HumanAction a : actionList){
			if (moveRow != a.row){
				return false;
			}
		}
		return true;
	}
	
	private static boolean isCol(){
		int moveCol = actionList.get(0).col;
		for (HumanAction a : actionList){
			if (moveCol != a.col){
				return false;
			}
		}
		return true;
	}

	private static void sortActions(){
		if (isRow()){
			Collections.sort(actionList, new Comparator<HumanAction>() {
				public int compare(HumanAction a1 , HumanAction a2){
					return a1.col - a2.col;
				}
			});			
		} else if (isCol()){
			Collections.sort(actionList, new Comparator<HumanAction>() {
				public int compare(HumanAction a1 , HumanAction a2){
					return a1.row - a2.row;
				}
			});
		}
	}
	
	private static boolean hasGaps(){
		if (getWord() == null){
			JOptionPane.showMessageDialog(null, "Words must not have gaps");
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	
	static HumanWord getWord(){
		sortActions();
		int row = actionList.get(0).row;
		int col = actionList.get(0).col ;
		int score = 0;
		StringBuilder word = new StringBuilder();
		Iterator<HumanAction> iterator = actionList.iterator();
		HumanAction cur = iterator.next();
		word.append(cur.movedTile.letter);
		score += cur.movedTile.points;
		System.out.println("placed " +cur.movedTile.letter + ", col:" + col + "," + cur.col + " row:" + row + "," + cur.row);
		if (isRow()){
			col++;
		} else{
			row++;
		}
		if (iterator.hasNext()) {
		
			cur = iterator.next();
			
			while (iterator.hasNext()){
				
				if (isRow()){
					if (cur.col == col){
						System.out.println("placed " +cur.movedTile.letter + ", col:" + col + "," + cur.col + " row:" + row + "," + cur.row);
						word.append(cur.movedTile.letter);
						score += cur.movedTile.points;
						cur = iterator.next();
						col++;
					} else {
						if(Board.getInstance().tileArr[row][col].letter != ' '){
							System.out.println("on board " + Board.getInstance().tileArr[row][col].letter + ", col:" + col + "," + cur.col + " row:" + row + "," + cur.row);
							word.append(Board.getInstance().tileArr[row][col].letter);
							score+= Board.getInstance().tileArr[row][col].points;
							col++;
						} else {
							System.out.println("fail " +cur.movedTile.letter + ", col:" + col + "," + cur.col + " row:" + row + "," + cur.row);
							return null;
						}
					}
				} else if (isCol()){
					if (cur.row == row){
						System.out.println("placed " +cur.movedTile.letter + ", col:" + col + "," + cur.col + " row:" + row + "," + cur.row);
						word.append(cur.movedTile.letter);
						score += cur.movedTile.points;
						cur = iterator.next();
						row++;
					} else {
						if(Board.getInstance().tileArr[row][col].letter != ' '){
							System.out.println("on board " + Board.getInstance().tileArr[row][col].letter + ", col:" + col + "," + cur.col + " row:" + row + "," + cur.row);
							word.append(Board.getInstance().tileArr[row][col].letter);
							score+= Board.getInstance().tileArr[row][col].points;
							row++;
						} else {
							System.out.println("fail " +cur.movedTile.letter + ", col:" + col + "," + cur.col + " row:" + row + "," + cur.row);
							return null;
						}
					}
				} else {
					System.err.println("i dont know how this happened");
					return null;
				}
			}
			System.out.println("placed " + cur.movedTile.letter + ", col:" + col + "," + cur.col + " row:" + row + "," + cur.row);
			word.append(cur.movedTile.letter);
			score += cur.movedTile.points;
		}
		
		//get trailing letters
		while(true){
			if (isRow()){
				col++;
			} else {
				row++;
			}
			if (row >= BOARD_DIMENSIONS || col >= BOARD_DIMENSIONS|| Board.getInstance().tileArr[row][col].letter == ' '){
				break;
			}
			word.append(Board.getInstance().tileArr[row][col].letter);
			score += Board.getInstance().tileArr[row][col].points;
		}
		
		
		//get leading letters
		row = actionList.get(0).row;
		col = actionList.get(0).col ;
		while(true){
			if (isRow()){
				col--;
			} else {
				row--;
			}
			if (row <= 0 || col <= 0 || Board.getInstance().tileArr[row][col].letter == ' '){
				break;
			}
			word.insert(0,Board.getInstance().tileArr[row][col].letter);
			score += Board.getInstance().tileArr[row][col].points;
		}
		
		HumanWord humanWord = new HumanWord(score, word.toString());
		
		return humanWord;
	}
	
	
	
	public static void execute(){
		for (HumanAction action : actionList){
			//action.movedTile.setNormal();
			//action.movedTile.icon.repaint();
			Board.getInstance().tileArr[action.row][action.col].letter = action.movedTile.letter;
			Board.getInstance().tileArr[action.row][action.col].points = action.movedTile.points;
			Scrabble.user.letterRack.tiles.remove(action.movedTile);
		}
		actionList = new ArrayList<HumanAction>();
		//Board.getInstance().reDraw();
		//Scrabble.user.getMoreTiles();
		Scrabble.user.letterRack.refill();
		//Scrabble.user.letterRack.redrawTiles();
		Board.getInstance().repaint();
	}
	
	public static void reverse(){
		for (HumanAction action : actionList){
			action.movedTile.setNormal();
			Scrabble.user.letterRack.tiles.add(action.movedTile);
			//Board.getInstance().tileArr[action.row][action.col].icon.setIcon(null);
			//Board.getInstance().tileArr[action.row][action.col] = new BlankTile(action.row, action.col);
			//Scrabble.user.letterRack.redrawTiles();
			//Scrabble.user.redrawRack();
		}
	}
}

class HumanAction{
	public HumanAction(Tile movedTile, int row, int col) {
		this.movedTile = movedTile;
		this.row = row;
		this.col = col;
	}
	@Override
	public String toString() {
		return "HumanAction [movedTile=" + movedTile + ", row=" + row + ", col=" + col + "]";
	}
	
	Tile movedTile;
	int row;
	int col;
}

class HumanWord{
	public HumanWord(int score, String word) {
		this.score = score;
		this.word = word;
		Scrabble.log.append(Scrabble.user.name + " plays the word " + word + " for " + score + " points\n");
		Scrabble.user.awardPoints(score);
	}

	int score;
	String word;
}

