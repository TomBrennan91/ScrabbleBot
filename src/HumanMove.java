import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		return hasMovedTiles() && (isRowOrCol()) && !hasGaps() && isJoinedUp();
	}
	
	private static boolean isJoinedUp(){
		ArrayList<String> newWords = WordsOnBoard.getNewWords();
		int letterCount  = 0;
		for(String word: newWords) letterCount += word.length();
		
		//System.err.println(letterCount + );
		if (letterCount > actionList.size()){
			return true;
		} else{
			JOptionPane.showMessageDialog(null, "Tiles must be joined up with existing tiles");
			return false;
		}
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
		
		if (actionList.size() <= 1) return false;
		sortActions();		
		if (isRow()){
			int row = actionList.get(0).row;
			for (int col = actionList.get(0).col ; col <= actionList.get(actionList.size()-1).col ; col++){
				if (Board.getInstance().tileArr[row][col].letter == ' '){
					JOptionPane.showMessageDialog(null, "Words must not have gaps");
					return true;
				}
			}
		} else if (isCol()){	
			int col = actionList.get(0).col;
			for (int row = actionList.get(0).row ; row <= actionList.get(actionList.size()-1).row ; row++){
				if (Board.getInstance().tileArr[row][col].letter == ' '){
					JOptionPane.showMessageDialog(null, "Words must not have gaps");
					return true;
				}
			}
		}
		return false;
	}	
	
	public static void execute(){
		
		ArrayList<String> newWords = WordsOnBoard.getNewWords();
		
		for (String word : newWords){
			int score = Dictionary.getWordScore(word);
			Scrabble.log.append(Scrabble.user.name + " plays the word " + word + " for " + score + " points\n");
			if (!Dictionary.trie.searchWord(word)){
				Scrabble.log.append("ScrabbleBot raises her eyebrows at '" + word + "' !\n");
			}
			Scrabble.user.awardPoints(score);
		}
		
		
		for (HumanAction action : actionList){
			action.movedTile.setNormal();
		}
		
		
		actionList = new ArrayList<HumanAction>();
		Scrabble.user.letterRack.refill();
	}
	
	public static void reverse(){
		
		for (HumanAction action : actionList){
			action.movedTile.setNormal();
			Scrabble.user.letterRack.tiles.add(action.movedTile);
			Board.getInstance().tileArr[action.row][action.col] = new Tile(' ', 0);
		}
		actionList = new ArrayList<HumanAction>();
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

	}

	int score;
	String word;
}

