package scrabble;

import scrabble.*;
import java.util.ArrayList;

public class WordsOnBoard implements Constants {

	
	public static ArrayList<PlayedWord> getNewWords(){
		ArrayList<PlayedWord> old = Scrabble.wordList;
		ArrayList<PlayedWord> current = getWordList();
		for (PlayedWord oldWord : old){
			for (PlayedWord curWord : current){
				if (oldWord.word.equals(curWord.word)){
					current.remove(curWord);
					break;
				}
			}	
		}
		
		for (PlayedWord playedWord : current) {
			System.err.println(playedWord.toString());
		}
		System.out.println("----");
		return current;
	}
	
	public static 	ArrayList<PlayedWord> getWordList(){
		ArrayList<PlayedWord> words = new ArrayList<>();
		words.addAll(getVerticalWords());
		words.addAll(getHorizontalWords());
		return words;
	}
	
	
	
	private static ArrayList<PlayedWord> getVerticalWords(){
		ArrayList<PlayedWord> vertical = new ArrayList<>();
		Tile[][] TileArr = Board.getInstance().tileArr;
		StringBuilder curWord = new StringBuilder();
		int multiplier = 1;
		int score = 0;
		for (int col = 0 ; col < BOARD_DIMENSIONS ; col++){
			for (int row = 0 ; row < BOARD_DIMENSIONS ; row++){
				if (TileArr[row][col].letter == ' '){
					if (curWord.length() > 1){
						vertical.add(new PlayedWord(curWord.toString(), score * multiplier));
					}
					curWord = new StringBuilder();
					multiplier = 1;
					score = 0;
				} else {
					score += TileArr[row][col].points * BonusChecker.letterMultiplier(row, col);
					multiplier *= BonusChecker.wordMultiplier(row, col);
					curWord.append(TileArr[row][col].letter);
				}
			}
			if (curWord.length() > 1){
				vertical.add(new PlayedWord(curWord.toString(), score * multiplier));
			}
			curWord = new StringBuilder();
			multiplier = 1;
			score = 0;
		}
		return vertical;
	}
	
	private static ArrayList<PlayedWord> getHorizontalWords(){
		ArrayList<PlayedWord> horizontal = new ArrayList<>();
		Tile[][] TileArr = Board.getInstance().tileArr;
		StringBuilder curWord = new StringBuilder();
		int multiplier = 1;
		int score = 0;
		for (int row = 0 ; row < BOARD_DIMENSIONS ; row++){
			for (int col = 0 ; col < BOARD_DIMENSIONS ; col++){
				if (TileArr[row][col].letter == ' '){
					if (curWord.length() > 1){
						horizontal.add(new PlayedWord(curWord.toString(), score * multiplier));
					}
					curWord = new StringBuilder();
					multiplier = 1;
					score = 0;
				} else {
					score += TileArr[row][col].points * BonusChecker.letterMultiplier(row, col);
					multiplier *= BonusChecker.wordMultiplier(row, col);
					curWord.append(TileArr[row][col].letter);
				}
			}
			if (curWord.length() > 1){
				horizontal.add(new PlayedWord(curWord.toString(), score * multiplier));
			}
			curWord = new StringBuilder();
			multiplier = 1;
			score = 0;
		}
		return horizontal;
	}
	
}
