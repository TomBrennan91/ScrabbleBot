import java.util.ArrayList;

public class WordsOnBoard implements Constants {

	
	public static ArrayList<String> getNewWords(){
		ArrayList<String> old = Scrabble.wordList;
		ArrayList<String> current = getWordList();
		current.removeIf(s -> old.contains(s));
		return current;
	}
	public static ArrayList<String> getWordList(){
		ArrayList<String> words = new ArrayList<>();
		words.addAll(getVerticalWords());
		words.addAll(getHorizontalWords());
		return words;
	}
	
	private static ArrayList<String> getVerticalWords(){
		ArrayList<String> vertical = new ArrayList<>();
		Tile[][] TileArr = Board.getInstance().tileArr;
		StringBuilder curWord = new StringBuilder();
		for (int col = 0 ; col < BOARD_DIMENSIONS ; col++){
			for (int row = 0 ; row < BOARD_DIMENSIONS ; row++){
				if (TileArr[row][col].letter == ' '){
					if (curWord.length() > 1){
						vertical.add(curWord.toString());
					}
					curWord = new StringBuilder();
				} else {
					curWord.append(TileArr[row][col].letter);
				}
			}
			if (curWord.length() > 1){
				vertical.add(curWord.toString());
			}
			curWord = new StringBuilder();
		}
		return vertical;
	}
	
	private static ArrayList<String> getHorizontalWords(){
		ArrayList<String> horizontal = new ArrayList<>();
		Tile[][] TileArr = Board.getInstance().tileArr;
		StringBuilder curWord = new StringBuilder();
		for (int row = 0 ; row < BOARD_DIMENSIONS ; row++){
			for (int col = 0 ; col < BOARD_DIMENSIONS ; col++){
				if (TileArr[row][col].letter == ' '){
					if (curWord.length() > 1){
						horizontal.add(curWord.toString());
					}
					curWord = new StringBuilder();
				} else {
					curWord.append(TileArr[row][col].letter);
				}
			}
			if (curWord.length() > 1){
				horizontal.add(curWord.toString());
			}
			curWord = new StringBuilder();
		}
		
		return horizontal;
	}
	
}
